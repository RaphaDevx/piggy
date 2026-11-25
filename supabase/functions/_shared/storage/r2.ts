// Cloudflare R2 (S3-API, privat). Alle Zugriffe laufen über vorsignierte URLs.
// Fehlertexte enthalten nie die URL (die Signatur ist ein Geheimnis).

import { encodeKeyPath, presignUrl } from './sigv4.ts';
import { r2UserPrefix, USER_BUCKETS } from './paths.ts';

export const MAX_URL_TTL_SECONDS = 600;
const DEFAULT_TTL_SECONDS = 600;

export interface R2Config {
  accountId: string;
  accessKeyId: string;
  secretAccessKey: string;
  bucket: string;
  /** Jurisdiktion des Buckets. Ein EU-Bucket ist nur über `<konto>.eu.r2.cloudflarestorage.com` erreichbar. */
  jurisdiction?: 'eu' | 'fedramp';
}

/** Liest die Konfiguration über einen Getter (Deno.env.get oder process.env). Fehlt etwas: null. */
export function r2ConfigFromEnv(get: (name: string) => string | undefined): R2Config | null {
  const accountId = get('R2_ACCOUNT_ID');
  const accessKeyId = get('R2_ACCESS_KEY_ID');
  const secretAccessKey = get('R2_SECRET_ACCESS_KEY');
  const bucket = get('R2_BUCKET');
  if (!accountId || !accessKeyId || !secretAccessKey || !bucket) return null;
  const j = (get('R2_JURISDICTION') ?? '').trim().toLowerCase();
  if (j && j !== 'eu' && j !== 'fedramp') return null;
  return { accountId, accessKeyId, secretAccessKey, bucket, ...(j ? { jurisdiction: j as 'eu' | 'fedramp' } : {}) };
}

/** Host der S3-API. Ohne Jurisdiktion der Standard-Endpunkt. */
export function r2Host(config: Pick<R2Config, 'accountId' | 'jurisdiction'>): string {
  return `${config.accountId}${config.jurisdiction ? `.${config.jurisdiction}` : ''}.r2.cloudflarestorage.com`;
}

export function clampTtl(value: unknown): number {
  const n = typeof value === 'number' && Number.isFinite(value) ? Math.floor(value) : DEFAULT_TTL_SECONDS;
  return Math.min(Math.max(n, 1), MAX_URL_TTL_SECONDS);
}

export interface R2ObjectInfo { key: string; size: number; etag: string }

const unquote = (etag: string | null): string => (etag ?? '').replace(/^"|"$/g, '');
const xmlUnescape = (s: string): string =>
  s.replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&quot;/g, '"').replace(/&apos;/g, "'").replace(/&amp;/g, '&');

export function parseListXml(xml: string): { objects: R2ObjectInfo[]; nextToken: string | null } {
  const objects: R2ObjectInfo[] = [];
  for (const m of xml.matchAll(/<Contents>([\s\S]*?)<\/Contents>/g)) {
    const key = /<Key>([\s\S]*?)<\/Key>/.exec(m[1])?.[1];
    const size = /<Size>(\d+)<\/Size>/.exec(m[1])?.[1];
    const etag = /<ETag>([\s\S]*?)<\/ETag>/.exec(m[1])?.[1] ?? '';
    if (key !== undefined && size !== undefined) {
      objects.push({ key: xmlUnescape(key), size: Number(size), etag: unquote(xmlUnescape(etag)) });
    }
  }
  const truncated = /<IsTruncated>true<\/IsTruncated>/.test(xml);
  const token = /<NextContinuationToken>([\s\S]*?)<\/NextContinuationToken>/.exec(xml)?.[1];
  return { objects, nextToken: truncated && token ? xmlUnescape(token) : null };
}

export type R2Client = ReturnType<typeof createR2>;

export function createR2(config: R2Config, fetchImpl: typeof fetch = fetch, now: () => Date = () => new Date()) {
  const host = r2Host(config);
  const sign = (method: 'GET' | 'PUT' | 'HEAD' | 'DELETE', path: string, ttl: number, query?: Record<string, string>) =>
    presignUrl({
      method, host, path, region: 'auto', accessKeyId: config.accessKeyId,
      secretAccessKey: config.secretAccessKey, expiresSeconds: ttl, query, now: now(),
    });
  const objectPath = (key: string) => `/${config.bucket}/${encodeKeyPath(key)}`;
  const fail = (op: string, status: number) => new Error(`R2 ${op} fehlgeschlagen (HTTP ${status})`);

  return {
    /** Vorsignierte URL für ein Objekt, Gültigkeit höchstens 10 Minuten. */
    presign(method: 'GET' | 'PUT' | 'HEAD' | 'DELETE', key: string, ttlSeconds?: number): Promise<string> {
      return sign(method, objectPath(key), clampTtl(ttlSeconds));
    },
    /** Metadaten oder null bei 404. */
    async head(key: string): Promise<{ size: number; etag: string } | null> {
      const res = await fetchImpl(await sign('HEAD', objectPath(key), 60), { method: 'HEAD' });
      if (res.status === 404) return null;
      if (!res.ok) throw fail('HEAD', res.status);
      return { size: Number(res.headers.get('content-length') ?? 0), etag: unquote(res.headers.get('etag')) };
    },
    /** Inhalt oder null bei 404. */
    async get(key: string): Promise<Uint8Array | null> {
      const res = await fetchImpl(await sign('GET', objectPath(key), 60));
      if (res.status === 404) return null;
      if (!res.ok) throw fail('GET', res.status);
      return new Uint8Array(await res.arrayBuffer());
    },
    async put(key: string, body: Uint8Array, contentType?: string): Promise<void> {
      const res = await fetchImpl(await sign('PUT', objectPath(key), 300), {
        method: 'PUT',
        headers: contentType ? { 'Content-Type': contentType } : undefined,
        body: new Uint8Array(body).buffer,
      });
      if (!res.ok) throw fail('PUT', res.status);
    },
    async delete(key: string): Promise<void> {
      const res = await fetchImpl(await sign('DELETE', objectPath(key), 60), { method: 'DELETE' });
      if (!res.ok && res.status !== 404) throw fail('DELETE', res.status);
    },
    /** Alle Objekte unter dem Präfix (paginiert). */
    async list(prefix: string): Promise<R2ObjectInfo[]> {
      const out: R2ObjectInfo[] = [];
      let token: string | null = null;
      do {
        const query: Record<string, string> = { 'list-type': '2', prefix };
        if (token) query['continuation-token'] = token;
        const res = await fetchImpl(await sign('GET', `/${config.bucket}`, 60, query));
        if (!res.ok) throw fail('LIST', res.status);
        const page = parseListXml(await res.text());
        out.push(...page.objects);
        token = page.nextToken;
      } while (token);
      return out;
    },
  };
}

const DELETE_CONCURRENCY = 8;

/** Löscht alle R2-Objekte eines Nutzers in allen Buckets (für delete-account). Liefert die Anzahl. */
export async function deleteUserObjects(r2: Pick<R2Client, 'list' | 'delete'>, userId: string): Promise<number> {
  let deleted = 0;
  for (const bucket of USER_BUCKETS) {
    const objects = await r2.list(r2UserPrefix(bucket, userId));
    for (let i = 0; i < objects.length; i += DELETE_CONCURRENCY) {
      await Promise.all(objects.slice(i, i + DELETE_CONCURRENCY).map((o) => r2.delete(o.key)));
      deleted += Math.min(DELETE_CONCURRENCY, objects.length - i);
    }
  }
  return deleted;
}
