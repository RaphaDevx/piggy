// RevenueCat-Webhook: Entscheidungslogik (Authentifizierung, Ereignis → Zeile in user_entitlements).
// Reine Logik ohne Deno-/Supabase-Abhängigkeit, damit Jest sie testen kann.

export type EntitlementUpsert = {
  user_id: string;
  plus: boolean;
  expires_at: string | null;
  product_id: string | null;
  environment: string | null;
  updated_at: string;
};

export type WebhookDecision =
  | { kind: 'ignore'; reason: string }
  | { kind: 'upsert'; row: EntitlementUpsert }
  | { kind: 'touch'; user_id: string; updated_at: string };

const UUID_RE = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i;

export function isValidUuid(v: unknown): v is string {
  return typeof v === 'string' && UUID_RE.test(v);
}

/** Vergleich in konstanter Zeit (über die Länge des längeren Strings). */
export function timingSafeEqualStr(a: string, b: string): boolean {
  const ea = new TextEncoder().encode(a);
  const eb = new TextEncoder().encode(b);
  let diff = ea.length ^ eb.length;
  const n = Math.max(ea.length, eb.length);
  for (let i = 0; i < n; i++) diff |= (ea[i] ?? 0) ^ (eb[i] ?? 0);
  return diff === 0;
}

/** Authorization-Header muss exakt dem Secret entsprechen. Ohne Secret nie autorisiert. */
export function isAuthorized(header: string | null | undefined, secret: string | null | undefined): boolean {
  if (!secret || !header) return false;
  return timingSafeEqualStr(header, secret);
}

function isoFromMs(ms: unknown): string | null {
  if (typeof ms !== 'number' || !Number.isFinite(ms)) return null;
  const d = new Date(ms);
  return Number.isNaN(d.getTime()) ? null : d.toISOString();
}

const GRANT_EVENTS = new Set(['INITIAL_PURCHASE', 'RENEWAL', 'PRODUCT_CHANGE', 'UNCANCELLATION']);

/**
 * Entscheidet anhand des Webhook-Bodys, was mit user_entitlements geschieht.
 * Unbekannte Ereignisse, ungültige UUIDs und Ereignisse ohne Ablaufzeit (ausser EXPIRATION und
 * BILLING_ISSUE) werden ignoriert, damit nie unbegrenzter Zugriff entsteht.
 */
export function decideWebhook(body: unknown, now: Date = new Date()): WebhookDecision {
  const ev = (body as { event?: Record<string, unknown> } | null)?.event;
  if (!ev || typeof ev !== 'object') return { kind: 'ignore', reason: 'no_event' };

  const type = typeof ev.type === 'string' ? ev.type : '';
  if (type === 'TEST') return { kind: 'ignore', reason: 'test' };

  if (!isValidUuid(ev.app_user_id)) return { kind: 'ignore', reason: 'invalid_app_user_id' };
  const user_id = ev.app_user_id.toLowerCase();
  const updated_at = now.toISOString();
  const expires = isoFromMs(ev.expiration_at_ms);
  const product =
    (typeof ev.new_product_id === 'string' && ev.new_product_id) ||
    (typeof ev.product_id === 'string' && ev.product_id) || null;
  const environment = typeof ev.environment === 'string' ? ev.environment : null;

  if (GRANT_EVENTS.has(type)) {
    if (!expires) return { kind: 'ignore', reason: 'missing_expiration' };
    return { kind: 'upsert', row: { user_id, plus: true, expires_at: expires, product_id: product, environment, updated_at } };
  }
  if (type === 'CANCELLATION') {
    // Kündigung: Plus läuft bis zum Ablauf der bezahlten Zeit weiter.
    if (!expires) return { kind: 'ignore', reason: 'missing_expiration' };
    return { kind: 'upsert', row: { user_id, plus: true, expires_at: expires, product_id: product, environment, updated_at } };
  }
  if (type === 'EXPIRATION') {
    return { kind: 'upsert', row: { user_id, plus: false, expires_at: expires ?? updated_at, product_id: product, environment, updated_at } };
  }
  if (type === 'BILLING_ISSUE') {
    // Zahlungsproblem: Zugriff unverändert (Apple versucht es weiter), nur Zeitstempel.
    return { kind: 'touch', user_id, updated_at };
  }
  return { kind: 'ignore', reason: 'unhandled_type' };
}
