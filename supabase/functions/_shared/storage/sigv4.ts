// AWS Signature V4 für vorsignierte URLs (S3-kompatibel, u. a. Cloudflare R2).
// Nur Web-Crypto, läuft in Deno und Node >= 20. Nur erasable TypeScript (Node-Type-Stripping).

const encoder = new TextEncoder();

const toHex = (buf: ArrayBuffer): string =>
  [...new Uint8Array(buf)].map((b) => b.toString(16).padStart(2, '0')).join('');

async function hmac(key: ArrayBuffer | Uint8Array, data: string): Promise<ArrayBuffer> {
  const k = await crypto.subtle.importKey('raw', key, { name: 'HMAC', hash: 'SHA-256' }, false, ['sign']);
  return crypto.subtle.sign('HMAC', k, encoder.encode(data));
}

async function sha256Hex(data: string): Promise<string> {
  return toHex(await crypto.subtle.digest('SHA-256', encoder.encode(data)));
}

/** RFC 3986: alles ausser A-Z a-z 0-9 - _ . ~ wird prozentkodiert. */
export function uriEncode(value: string): string {
  return encodeURIComponent(value).replace(/[!'()*]/g, (c) => `%${c.charCodeAt(0).toString(16).toUpperCase()}`);
}

/** Kodiert einen Objektschlüssel segmentweise (Schrägstriche bleiben). */
export function encodeKeyPath(key: string): string {
  return key.split('/').map(uriEncode).join('/');
}

export interface PresignParams {
  method: 'GET' | 'PUT' | 'HEAD' | 'DELETE';
  host: string;
  /** Bereits kodierter Pfad, beginnt mit '/'. */
  path: string;
  region: string;
  accessKeyId: string;
  secretAccessKey: string;
  expiresSeconds: number;
  query?: Record<string, string>;
  now?: Date;
  service?: string;
}

export async function presignUrl(p: PresignParams): Promise<string> {
  const service = p.service ?? 's3';
  const iso = (p.now ?? new Date()).toISOString().replace(/[:-]|\.\d{3}/g, ''); // 20130524T000000Z
  const date = iso.slice(0, 8);
  const scope = `${date}/${p.region}/${service}/aws4_request`;

  const params: Record<string, string> = {
    ...(p.query ?? {}),
    'X-Amz-Algorithm': 'AWS4-HMAC-SHA256',
    'X-Amz-Credential': `${p.accessKeyId}/${scope}`,
    'X-Amz-Date': iso,
    'X-Amz-Expires': String(p.expiresSeconds),
    'X-Amz-SignedHeaders': 'host',
  };
  const canonicalQuery = Object.entries(params)
    .map(([k, v]) => [uriEncode(k), uriEncode(v)] as const)
    .sort((a, b) => (a[0] < b[0] ? -1 : a[0] > b[0] ? 1 : 0))
    .map(([k, v]) => `${k}=${v}`)
    .join('&');

  const canonicalRequest = [p.method, p.path, canonicalQuery, `host:${p.host}\n`, 'host', 'UNSIGNED-PAYLOAD'].join('\n');
  const stringToSign = ['AWS4-HMAC-SHA256', iso, scope, await sha256Hex(canonicalRequest)].join('\n');

  const kDate = await hmac(encoder.encode(`AWS4${p.secretAccessKey}`), date);
  const kRegion = await hmac(kDate, p.region);
  const kService = await hmac(kRegion, service);
  const kSigning = await hmac(kService, 'aws4_request');
  const signature = toHex(await hmac(kSigning, stringToSign));

  return `https://${p.host}${p.path}?${canonicalQuery}&X-Amz-Signature=${signature}`;
}
