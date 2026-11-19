// Kern der Edge Function `storage-url`: prüft Nutzer und Pfade, liefert kurzlebige R2-URLs.
// Freigabe-Logik: Heute schränken die Storage-Policies auf den eigenen Ordner `<uid>/` ein
// (receipt_images_own_*, bank_statements_storage_*). Geteilte Belege (shared_receipts) und
// Projekte geben Bilder NICHT frei. Das bildet diese Function 1:1 ab: nur eigene Pfade.

import { isUserBucket, r2Key, validateOwnPath } from './paths.ts';
import { clampTtl, type R2Client } from './r2.ts';

export const MAX_PATHS_PER_REQUEST = 100;

export interface StorageUrlDeps {
  /** Nutzer des Tokens oder null. */
  authenticate(req: Request): Promise<{ id: string } | null>;
  r2: Pick<R2Client, 'presign' | 'head'> | null;
}

const CORS = {
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Headers': 'authorization, x-client-info, apikey, content-type',
};
const json = (body: unknown, status = 200) =>
  new Response(JSON.stringify(body), { status, headers: { 'Content-Type': 'application/json', ...CORS } });

type Op = 'get' | 'put' | 'delete';
const METHOD = { get: 'GET', put: 'PUT', delete: 'DELETE' } as const;

export async function handleStorageUrl(req: Request, deps: StorageUrlDeps): Promise<Response> {
  if (req.method === 'OPTIONS') return new Response('ok', { headers: CORS });
  if (req.method !== 'POST') return json({ error: 'Method not allowed' }, 405);

  const user = await deps.authenticate(req);
  if (!user) return json({ error: 'Invalid token' }, 401);

  const body = await req.json().catch(() => null) as
    { op?: unknown; bucket?: unknown; paths?: unknown; expiresIn?: unknown; verify?: unknown } | null;
  if (!body || !['get', 'put', 'delete'].includes(body.op as string)) return json({ error: 'op: get|put|delete erforderlich' }, 400);
  if (!isUserBucket(body.bucket)) return json({ error: 'Unbekannter Bucket' }, 400);
  const paths = body.paths;
  if (!Array.isArray(paths) || paths.length === 0 || paths.length > MAX_PATHS_PER_REQUEST) {
    return json({ error: `paths: 1 bis ${MAX_PATHS_PER_REQUEST} Einträge erforderlich` }, 400);
  }

  // Ein fremder Pfad lehnt die ganze Anfrage ab (kein Teilergebnis).
  for (const path of paths) {
    if (validateOwnPath(user.id, path)) return json({ error: 'Pfad nicht erlaubt' }, 403);
  }
  if (!deps.r2) return json({ error: 'R2 nicht konfiguriert' }, 503);

  const op = body.op as Op;
  const bucket = body.bucket;
  const ttl = clampTtl(body.expiresIn);
  const verify = op === 'get' && body.verify === true;
  const r2 = deps.r2;

  const results = await Promise.all((paths as string[]).map(async (path) => {
    const key = r2Key(bucket, path);
    try {
      if (verify && !(await r2.head(key))) return { path, url: null, found: false };
      const url = await r2.presign(METHOD[op], key, ttl);
      return verify ? { path, url, found: true } : { path, url };
    } catch (e) {
      console.error('storage-url', op, e instanceof Error ? e.message : 'error');
      return { path, url: null, error: 'unavailable' };
    }
  }));
  return json({ results, expiresIn: ttl });
}
