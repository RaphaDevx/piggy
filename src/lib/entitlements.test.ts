import { decideWebhook, isAuthorized, isValidUuid, timingSafeEqualStr } from '../../supabase/functions/_shared/entitlements/revenuecat.ts';
import { isPlusActive, limitReachedMessage, parseLimit, scanLimitFor } from '../../supabase/functions/_shared/entitlements/plus.ts';

const UID = '3F2B8A52-6c1e-4d0b-9a77-1c2d3e4f5a6b';
const NOW = new Date('2026-11-07T12:00:00Z');
const EXP = Date.parse('2026-12-07T12:00:00Z');
const ev = (over: Record<string, unknown>) => ({
  event: { type: 'RENEWAL', app_user_id: UID, product_id: 'piggy_plus_monthly', expiration_at_ms: EXP, environment: 'PRODUCTION', ...over },
});

describe('Webhook: Authentifizierung', () => {
  it('nur bei exakt gleichem Header', () => {
    expect(isAuthorized('geheim', 'geheim')).toBe(true);
    expect(isAuthorized('geheim ', 'geheim')).toBe(false);
    expect(isAuthorized('Geheim', 'geheim')).toBe(false);
    expect(isAuthorized('geheimX', 'geheim')).toBe(false);
  });
  it('fehlender Header oder fehlendes Secret → nie autorisiert', () => {
    expect(isAuthorized(null, 'geheim')).toBe(false);
    expect(isAuthorized('', 'geheim')).toBe(false);
    expect(isAuthorized('', '')).toBe(false);
    expect(isAuthorized('x', '')).toBe(false);
    expect(isAuthorized('x', undefined)).toBe(false);
  });
  it('timingSafeEqualStr', () => {
    expect(timingSafeEqualStr('abc', 'abc')).toBe(true);
    expect(timingSafeEqualStr('abc', 'abd')).toBe(false);
    expect(timingSafeEqualStr('abc', 'abcd')).toBe(false);
    expect(timingSafeEqualStr('', '')).toBe(true);
  });
});

describe('Webhook: Entscheidung', () => {
  it.each(['INITIAL_PURCHASE', 'RENEWAL', 'PRODUCT_CHANGE', 'UNCANCELLATION'])('%s → plus true mit Ablaufzeit', (type) => {
    const d = decideWebhook(ev({ type }), NOW);
    expect(d).toEqual({ kind: 'upsert', row: {
      user_id: UID.toLowerCase(), plus: true, expires_at: new Date(EXP).toISOString(),
      product_id: 'piggy_plus_monthly', environment: 'PRODUCTION', updated_at: NOW.toISOString(),
    } });
  });
  it('PRODUCT_CHANGE übernimmt new_product_id', () => {
    const d = decideWebhook(ev({ type: 'PRODUCT_CHANGE', new_product_id: 'piggy_plus_other' }), NOW);
    expect(d.kind === 'upsert' && d.row.product_id).toBe('piggy_plus_other');
  });
  it('CANCELLATION: plus bleibt bis expires_at', () => {
    const d = decideWebhook(ev({ type: 'CANCELLATION' }), NOW);
    expect(d.kind === 'upsert' && d.row.plus).toBe(true);
    expect(d.kind === 'upsert' && d.row.expires_at).toBe(new Date(EXP).toISOString());
  });
  it('EXPIRATION → plus false', () => {
    const d = decideWebhook(ev({ type: 'EXPIRATION' }), NOW);
    expect(d.kind === 'upsert' && d.row.plus).toBe(false);
  });
  it('EXPIRATION ohne Ablaufzeit → plus false, expires_at = jetzt', () => {
    const d = decideWebhook(ev({ type: 'EXPIRATION', expiration_at_ms: undefined }), NOW);
    expect(d.kind === 'upsert' && d.row.plus).toBe(false);
    expect(d.kind === 'upsert' && d.row.expires_at).toBe(NOW.toISOString());
  });
  it('BILLING_ISSUE → nur Zeitstempel, Zugriff unverändert', () => {
    expect(decideWebhook(ev({ type: 'BILLING_ISSUE' }), NOW))
      .toEqual({ kind: 'touch', user_id: UID.toLowerCase(), updated_at: NOW.toISOString() });
  });
  it('TEST → ignoriert (auch ohne gültige UUID)', () => {
    expect(decideWebhook(ev({ type: 'TEST', app_user_id: 'x' }), NOW).kind).toBe('ignore');
  });
  it('ungültige app_user_id wird ignoriert', () => {
    for (const id of ['$RCAnonymousID:abc', 'user@example.com', '', null, 42, '3f2b8a52-6c1e-4d0b-9a77']) {
      expect(decideWebhook(ev({ app_user_id: id }), NOW)).toEqual({ kind: 'ignore', reason: 'invalid_app_user_id' });
    }
  });
  it('Gewährung ohne Ablaufzeit wird ignoriert (nie unbegrenzt)', () => {
    for (const type of ['INITIAL_PURCHASE', 'RENEWAL', 'CANCELLATION']) {
      expect(decideWebhook(ev({ type, expiration_at_ms: null }), NOW)).toEqual({ kind: 'ignore', reason: 'missing_expiration' });
    }
  });
  it('unbekannte Ereignisse und kaputte Bodys werden ignoriert', () => {
    expect(decideWebhook(ev({ type: 'NON_RENEWING_PURCHASE' }), NOW).kind).toBe('ignore');
    expect(decideWebhook({}, NOW).kind).toBe('ignore');
    expect(decideWebhook(null, NOW).kind).toBe('ignore');
    expect(decideWebhook('x', NOW).kind).toBe('ignore');
  });
  it('isValidUuid', () => {
    expect(isValidUuid(UID)).toBe(true);
    expect(isValidUuid('nope')).toBe(false);
  });
});

describe('Plus-Entitlement und Scan-Limit', () => {
  it('isPlusActive', () => {
    expect(isPlusActive({ plus: true, expires_at: null }, NOW)).toBe(true);
    expect(isPlusActive({ plus: true, expires_at: '2026-11-08T00:00:00Z' }, NOW)).toBe(true);
    expect(isPlusActive({ plus: true, expires_at: '2026-11-07T11:59:59Z' }, NOW)).toBe(false);
    expect(isPlusActive({ plus: true, expires_at: 'kaputt' }, NOW)).toBe(false);
    expect(isPlusActive({ plus: false, expires_at: null }, NOW)).toBe(false);
    expect(isPlusActive(null, NOW)).toBe(false);
  });
  it('scanLimitFor und parseLimit', () => {
    expect(scanLimitFor(true, 10, 100)).toBe(100);
    expect(scanLimitFor(false, 10, 100)).toBe(10);
    expect(parseLimit('250', 100)).toBe(250);
    expect(parseLimit(undefined, 100)).toBe(100);
    expect(parseLimit('abc', 100)).toBe(100);
    expect(parseLimit('0', 100)).toBe(100);
  });
  it('Meldung bei Limit unterscheidet Plus und Gratis', () => {
    expect(limitReachedMessage(true, 100)).toContain('Piggy Plus');
    expect(limitReachedMessage(false, 10)).toContain('kostenlosen');
    expect(limitReachedMessage(false, 10)).not.toContain('Piggy Plus');
  });
});
