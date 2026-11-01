import { useEffect, useState } from 'react';
import { supabase } from './supabase';

const BUCKET = 'receipt-images';
const TTL_SECONDS = 3600;
/** Cache etwas kürzer als die Signatur gültig ist, damit nie ein abgelaufener Link ausgeliefert wird. */
const CACHE_MS = 50 * 60 * 1000;
const LEGACY_MARKER = `/storage/v1/object/public/${BUCKET}/`;

const cache = new Map<string, { url: string; expires: number }>();
const pending = new Map<string, Promise<string | null>>();

/** Legacy-Public-URL oder reiner Pfad -> Storage-Pfad (ohne Query/Fragment). Alles andere -> null. */
export function toStoragePath(value: string | null | undefined): string | null {
  if (!value) return null;
  let path = value;
  const at = value.indexOf(LEGACY_MARKER);
  if (at >= 0) path = value.slice(at + LEGACY_MARKER.length);
  else if (/^[a-z][a-z0-9+.-]*:/i.test(value) || value.startsWith('/')) return null; // lokale/fremde URI
  path = path.split(/[?#]/)[0];
  try { path = decodeURIComponent(path); } catch { /* unverändert lassen */ }
  return path || null;
}

/** Signierte URLs für Pfade/Legacy-URLs; ein Request pro Batch. Fehler -> null je Eintrag. */
export async function getSignedReceiptImageUrls(values: (string | null | undefined)[]): Promise<(string | null)[]> {
  const now = Date.now();
  const paths = values.map(toStoragePath);
  const missing = new Set<string>();
  const waiting: Promise<unknown>[] = [];
  for (const path of paths) {
    if (!path) continue;
    const hit = cache.get(path);
    if (hit && hit.expires > now) continue;
    const inflight = pending.get(path);
    if (inflight) waiting.push(inflight);
    else missing.add(path);
  }

  if (missing.size) {
    const list = [...missing];
    const request = (async () => {
      const result = new Map<string, string>();
      try {
        const { data, error } = await supabase.storage.from(BUCKET).createSignedUrls(list, TTL_SECONDS);
        if (error) throw error;
        for (const row of data ?? []) {
          if (row.path && row.signedUrl && !row.error) result.set(row.path, row.signedUrl);
        }
      } catch (e) {
        console.warn('receipt image sign failed', (e as Error)?.message);
      }
      const expires = Date.now() + CACHE_MS;
      for (const [path, url] of result) cache.set(path, { url, expires });
      return result;
    })();
    for (const path of list) {
      const one = request.then(result => result.get(path) ?? null);
      pending.set(path, one);
      void one.finally(() => pending.delete(path));
    }
    waiting.push(request);
  }

  await Promise.all(waiting);
  return values.map((value, i) => {
    const path = paths[i];
    if (!path) return value ?? null; // lokale URI (Vorschau vor dem Speichern) unverändert
    const hit = cache.get(path);
    return hit && hit.expires > Date.now() ? hit.url : null;
  });
}

/** Test-Hilfe. */
export function clearReceiptImageCache() {
  cache.clear();
  pending.clear();
}

/** Anzeige-URL für einen gespeicherten Pfad (oder Legacy-URL); null solange sie lädt oder bei Fehler. */
export function useReceiptImageUrl(value: string | null | undefined): string | null {
  const [url, setUrl] = useState<string | null>(null);
  useEffect(() => {
    let active = true;
    setUrl(null);
    if (!value) return;
    void getSignedReceiptImageUrls([value]).then(([signed]) => { if (active) setUrl(signed); });
    return () => { active = false; };
  }, [value]);
  return url;
}
