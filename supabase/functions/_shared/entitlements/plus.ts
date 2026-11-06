// Piggy Plus: Entitlement auswerten und Scan-Limit wählen.
// Reine Logik ohne Deno-/Supabase-Abhängigkeit, damit Jest sie testen kann.

export interface EntitlementRow {
  plus: boolean | null;
  expires_at: string | null;
}

/** Aktiv, wenn plus = true und kein Ablaufdatum gesetzt ist oder es in der Zukunft liegt. */
export function isPlusActive(row: EntitlementRow | null | undefined, now: Date = new Date()): boolean {
  if (!row || row.plus !== true) return false;
  if (row.expires_at == null) return true;
  const t = Date.parse(row.expires_at);
  return Number.isFinite(t) && t > now.getTime();
}

/** Positive ganze Zahl aus app_settings, sonst Rückfallwert. */
export function parseLimit(raw: string | undefined | null, fallback: number): number {
  const n = parseInt(raw ?? '', 10);
  return Number.isFinite(n) && n > 0 ? n : fallback;
}

export function scanLimitFor(plus: boolean, freeLimit: number, plusLimit: number): number {
  return plus ? plusLimit : freeLimit;
}

export function limitReachedMessage(plus: boolean, limit: number): string {
  return plus
    ? `Tageslimit von ${limit} Scans mit Piggy Plus erreicht. Morgen wieder verfügbar.`
    : `Tageslimit von ${limit} kostenlosen Scans erreicht. Morgen wieder verfügbar oder eigenen Gemini API Key im Profil hinterlegen.`;
}
