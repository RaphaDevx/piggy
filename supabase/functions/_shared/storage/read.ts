// Server-seitiges Lesen eines Objekts je nach STORAGE_BACKEND (für Edge Functions wie statement-worker).

import { r2Key, type StorageBackend, type UserBucket } from './paths.ts';

export interface ReadDeps {
  backend: StorageBackend;
  r2: { get(key: string): Promise<Uint8Array | null> } | null;
  supabaseDownload(bucket: UserBucket, path: string): Promise<Uint8Array | null>;
}

/** 'r2': nur R2. 'r2-with-fallback': R2, bei 404 Supabase. 'supabase': nur Supabase. Wirft, wenn nichts gefunden wird. */
export async function readObject(deps: ReadDeps, bucket: UserBucket, path: string): Promise<Uint8Array> {
  if (deps.backend !== 'supabase') {
    if (!deps.r2) throw new Error('R2 ist nicht konfiguriert');
    const bytes = await deps.r2.get(r2Key(bucket, path));
    if (bytes) return bytes;
    if (deps.backend === 'r2') throw new Error(`Datei nicht gefunden: ${path}`);
  }
  const bytes = await deps.supabaseDownload(bucket, path);
  if (!bytes) throw new Error(`Datei nicht gefunden: ${path}`);
  return bytes;
}
