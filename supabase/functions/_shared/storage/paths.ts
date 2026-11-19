// Pfad-Regeln für den Objektspeicher (Supabase Storage und Cloudflare R2).
// Die Objekt-Pfade bleiben `<user_id>/...` wie in der Datenbank. In R2 liegen alle
// Buckets in einem R2-Bucket unter dem Präfix `<bucket>/` (siehe docs/adr/0005).

export const USER_BUCKETS = ['receipts', 'receipt-images', 'bank-statements'] as const;
export type UserBucket = typeof USER_BUCKETS[number];

export function isUserBucket(value: unknown): value is UserBucket {
  return typeof value === 'string' && (USER_BUCKETS as readonly string[]).includes(value);
}

const MAX_PATH_LENGTH = 512;

/** Liefert null, wenn der Pfad unter dem eigenen `<userId>/`-Präfix liegt und sauber ist, sonst den Ablehnungsgrund. */
export function validateOwnPath(userId: string, path: unknown): string | null {
  if (typeof path !== 'string' || !path) return 'Pfad fehlt';
  if (!userId) return 'Nutzer fehlt';
  if (path.length > MAX_PATH_LENGTH) return 'Pfad zu lang';
  // eslint-disable-next-line no-control-regex
  if (/[\u0000-\u001f\u007f\\]/.test(path)) return 'Ungültige Zeichen';
  if (!path.startsWith(`${userId}/`)) return 'Fremder Pfad';
  const segments = path.split('/');
  if (segments.some((s) => s === '' || s === '.' || s === '..')) return 'Ungültiger Pfad';
  return null;
}

/** R2-Objektschlüssel eines Pfads. */
export function r2Key(bucket: UserBucket, path: string): string {
  return `${bucket}/${path}`;
}

export function r2UserPrefix(bucket: UserBucket, userId: string): string {
  return `${bucket}/${userId}/`;
}

export type StorageBackend = 'supabase' | 'r2' | 'r2-with-fallback';

/** Unbekannte oder fehlende Werte ergeben 'supabase' (sicherer Standard). */
export function parseBackend(value: string | undefined | null): StorageBackend {
  return value === 'r2' || value === 'r2-with-fallback' ? value : 'supabase';
}
