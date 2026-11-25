// Adapter für Beleg-Dateien: Supabase Storage oder Cloudflare R2 (ADR 0005).
// Umschalter: EXPO_PUBLIC_STORAGE_BACKEND = 'supabase' (Rollback) | 'r2' | 'r2-with-fallback' (Standard).
//   r2-with-fallback: Lesen zuerst R2, fehlt das Objekt dort (404) -> Supabase.
//                     Schreiben nach R2, schlägt das fehl -> Supabase (Übergangszeit).
// Die Objekt-Pfade sind in allen Modi gleich (`<user_id>/...`), DB-Zeilen bleiben unverändert.

import { supabase } from './supabase';

export type StorageBackend = 'supabase' | 'r2' | 'r2-with-fallback';
export type StorageBucket = 'receipts' | 'receipt-images' | 'bank-statements';
export type UploadBody = Blob | Uint8Array | ArrayBuffer;

const FUNCTION_NAME = 'storage-url';
/** R2-URLs gelten höchstens 10 Minuten (Server-Obergrenze). */
export const R2_URL_TTL_SECONDS = 600;
const SUPABASE_URL_TTL_SECONDS = 3600;

export function getStorageBackend(): StorageBackend {
  const value = process.env.EXPO_PUBLIC_STORAGE_BACKEND ?? 'r2-with-fallback';
  return value === 'r2' || value === 'r2-with-fallback' ? value : 'supabase';
}

/** Gültigkeit der vom Adapter gelieferten URLs; Grundlage für Zwischenspeicher. */
export function signedUrlTtlSeconds(): number {
  return getStorageBackend() === 'supabase' ? SUPABASE_URL_TTL_SECONDS : R2_URL_TTL_SECONDS;
}

interface UrlResult { path: string; url: string | null; found?: boolean; error?: string }

async function requestUrls(
  op: 'get' | 'put' | 'delete', bucket: StorageBucket, paths: string[], verify = false,
): Promise<UrlResult[]> {
  const { data, error } = await supabase.functions.invoke(FUNCTION_NAME, { body: { op, bucket, paths, verify } });
  if (error) throw error;
  return (data?.results ?? []) as UrlResult[];
}

// ── Lesen ────────────────────────────────────────────────────────────────────

async function supabaseSignedUrls(bucket: StorageBucket, paths: string[], ttl: number): Promise<Map<string, string>> {
  const out = new Map<string, string>();
  if (!paths.length) return out;
  const { data, error } = await supabase.storage.from(bucket).createSignedUrls(paths, ttl);
  if (error) throw error;
  for (const row of data ?? []) {
    if (row.path && row.signedUrl && !row.error) out.set(row.path, row.signedUrl);
  }
  return out;
}

/** Signierte Lese-URLs je Pfad (null = nicht verfügbar). Wirft nur, wenn der Dienst selbst ausfällt. */
export async function getSignedUrls(bucket: StorageBucket, paths: string[], ttl = signedUrlTtlSeconds()): Promise<(string | null)[]> {
  const backend = getStorageBackend();
  if (!paths.length) return [];
  if (backend === 'supabase') {
    const urls = await supabaseSignedUrls(bucket, paths, ttl);
    return paths.map((p) => urls.get(p) ?? null);
  }

  const fallback = backend === 'r2-with-fallback';
  const urls = new Map<string, string>();
  let missing = paths;
  try {
    const results = await requestUrls('get', bucket, paths, fallback);
    missing = [];
    for (const r of results) {
      if (r.url) urls.set(r.path, r.url);
      else if (fallback && r.found === false) missing.push(r.path);
    }
  } catch (e) {
    if (!fallback) throw e;
    missing = paths; // Function nicht erreichbar -> alles über Supabase versuchen
  }
  if (fallback && missing.length) {
    const viaSupabase = await supabaseSignedUrls(bucket, missing, Math.min(ttl, R2_URL_TTL_SECONDS));
    for (const [p, u] of viaSupabase) urls.set(p, u);
  }
  return paths.map((p) => urls.get(p) ?? null);
}

export async function getSignedUrl(bucket: StorageBucket, path: string, ttl?: number): Promise<string | null> {
  return (await getSignedUrls(bucket, [path], ttl))[0];
}

// ── Schreiben ────────────────────────────────────────────────────────────────

async function supabaseUpload(bucket: StorageBucket, path: string, body: UploadBody, contentType?: string, upsert?: boolean) {
  const { error } = await supabase.storage.from(bucket).upload(path, body, { contentType, upsert });
  return { error: error ? new Error(error.message) : null };
}

async function r2Upload(bucket: StorageBucket, path: string, body: UploadBody, contentType?: string) {
  try {
    const [target] = await requestUrls('put', bucket, [path]);
    if (!target?.url) throw new Error('Keine Upload-URL erhalten');
    const res = await fetch(target.url, {
      method: 'PUT',
      headers: contentType ? { 'Content-Type': contentType } : undefined,
      body: body as BodyInit,
    });
    if (!res.ok) throw new Error(`R2-Upload fehlgeschlagen (HTTP ${res.status})`);
    return { error: null };
  } catch (e) {
    return { error: e instanceof Error ? e : new Error('R2-Upload fehlgeschlagen') };
  }
}

export async function upload(
  bucket: StorageBucket, path: string, body: UploadBody, options: { contentType?: string; upsert?: boolean } = {},
): Promise<{ error: Error | null }> {
  const backend = getStorageBackend();
  if (backend === 'supabase') return supabaseUpload(bucket, path, body, options.contentType, options.upsert);
  // The migration fallback is read-only; never silently write new files back to Supabase.
  return r2Upload(bucket, path, body, options.contentType);
}

// ── Löschen ──────────────────────────────────────────────────────────────────

export async function remove(bucket: StorageBucket, paths: string[]): Promise<{ error: Error | null }> {
  if (!paths.length) return { error: null };
  const backend = getStorageBackend();
  let error: Error | null = null;
  if (backend !== 'r2') {
    const res = await supabase.storage.from(bucket).remove(paths);
    if (res.error) error = new Error(res.error.message);
  }
  if (backend !== 'supabase') {
    try {
      const targets = await requestUrls('delete', bucket, paths);
      for (const t of targets) {
        if (!t.url) { error = error ?? new Error('Keine Lösch-URL erhalten'); continue; }
        const res = await fetch(t.url, { method: 'DELETE' });
        if (!res.ok && res.status !== 404) error = error ?? new Error(`R2-Löschen fehlgeschlagen (HTTP ${res.status})`);
      }
    } catch (e) {
      error = error ?? (e instanceof Error ? e : new Error('R2-Löschen fehlgeschlagen'));
    }
  }
  return { error };
}
