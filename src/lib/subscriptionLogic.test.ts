import {
  plusStatusFromCustomerInfo, isUserCancelled, pickMonthlyPackage, formatPeriod, recurringPhrase,
  introText, buildOffer, NO_PLUS, type PackageLike, type ProductLike,
} from './subscriptionLogic';

const product = (over: Partial<ProductLike> = {}): ProductLike => ({
  identifier: 'piggy_plus_monthly', priceString: 'CHF 4.90', subscriptionPeriod: 'P1M', introPrice: null, ...over,
});
const pkg = (over: Partial<PackageLike> = {}): PackageLike => ({
  identifier: '$rc_monthly', packageType: 'MONTHLY', product: product(), ...over,
});

describe('plusStatusFromCustomerInfo', () => {
  it('aktives Entitlement «plus» → isPlus mit Ablaufdatum', () => {
    const s = plusStatusFromCustomerInfo({
      entitlements: { active: { plus: { isActive: true, expirationDate: '2026-12-07T10:00:00Z', willRenew: true } } },
    });
    expect(s.isPlus).toBe(true);
    expect(s.expiresAt?.toISOString()).toBe('2026-12-07T10:00:00.000Z');
    expect(s.willRenew).toBe(true);
  });
  it('anderes Entitlement, leer, null → nicht aktiv', () => {
    expect(plusStatusFromCustomerInfo({ entitlements: { active: { other: { isActive: true } } } })).toEqual(NO_PLUS);
    expect(plusStatusFromCustomerInfo({ entitlements: { active: {} } })).toEqual(NO_PLUS);
    expect(plusStatusFromCustomerInfo({})).toEqual(NO_PLUS);
    expect(plusStatusFromCustomerInfo(null)).toEqual(NO_PLUS);
  });
  it('isActive false → nicht aktiv; ohne Ablaufdatum bleibt expiresAt null', () => {
    expect(plusStatusFromCustomerInfo({ entitlements: { active: { plus: { isActive: false } } } }).isPlus).toBe(false);
    const s = plusStatusFromCustomerInfo({ entitlements: { active: { plus: { isActive: true } } } });
    expect(s.isPlus).toBe(true);
    expect(s.expiresAt).toBeNull();
  });
});

describe('isUserCancelled', () => {
  it('erkennt Abbruch über userCancelled oder Code 1', () => {
    expect(isUserCancelled({ userCancelled: true })).toBe(true);
    expect(isUserCancelled({ code: '1' })).toBe(true);
    expect(isUserCancelled({ code: 1 })).toBe(true);
  });
  it('andere Fehler sind keine Abbrüche', () => {
    expect(isUserCancelled({ userCancelled: false, code: '2' })).toBe(false);
    expect(isUserCancelled(new Error('x'))).toBe(false);
    expect(isUserCancelled(null)).toBe(false);
  });
});

describe('pickMonthlyPackage', () => {
  it('nimmt monthly aus Offering «default»', () => {
    const m = pkg();
    expect(pickMonthlyPackage({ all: { default: { monthly: m } } })).toBe(m);
  });
  it('Rückfall auf Paket mit packageType MONTHLY', () => {
    const m = pkg();
    expect(pickMonthlyPackage({ all: { default: { monthly: null, availablePackages: [pkg({ packageType: 'ANNUAL' }), m] } } })).toBe(m);
  });
  it('kein Offering «default» oder kein Monatspaket → null', () => {
    expect(pickMonthlyPackage({ all: { other: { monthly: pkg() } } })).toBeNull();
    expect(pickMonthlyPackage({ all: { default: { availablePackages: [pkg({ packageType: 'ANNUAL' })] } } })).toBeNull();
    expect(pickMonthlyPackage(null)).toBeNull();
  });
});

describe('Laufzeit und Preis aus dem Produkt', () => {
  it('formatPeriod', () => {
    expect(formatPeriod('P1M')).toBe('1 Monat');
    expect(formatPeriod('P3M')).toBe('3 Monate');
    expect(formatPeriod('P1W')).toBe('1 Woche');
    expect(formatPeriod('P7D')).toBe('7 Tage');
    expect(formatPeriod('P1Y')).toBe('1 Jahr');
    expect(formatPeriod('P0M')).toBeNull();
    expect(formatPeriod('xyz')).toBeNull();
    expect(formatPeriod(null)).toBeNull();
  });
  it('recurringPhrase', () => {
    expect(recurringPhrase('CHF 4.90', 'P1M')).toBe('CHF 4.90 pro Monat');
    expect(recurringPhrase('CHF 9.90', 'P2M')).toBe('CHF 9.90 alle 2 Monate');
    expect(recurringPhrase('CHF 4.90', null)).toBe('CHF 4.90');
  });
});

describe('introText', () => {
  it('ohne Einführungsphase null', () => {
    expect(introText(product())).toBeNull();
  });
  it('kostenloser Test: Dauer und Folgepreis aus dem Produkt', () => {
    const p = product({ introPrice: { price: 0, priceString: 'CHF 0.00', cycles: 1, period: 'P1W', periodUnit: 'WEEK', periodNumberOfUnits: 1 } });
    expect(introText(p)).toBe('1 Woche kostenlos, danach CHF 4.90 pro Monat');
  });
  it('bezahlte Einführung, einmalig', () => {
    const p = product({ introPrice: { price: 0.99, priceString: 'CHF 0.99', cycles: 1, period: 'P1M', periodUnit: 'MONTH', periodNumberOfUnits: 1 } });
    expect(introText(p)).toBe('CHF 0.99 für 1 Monat, danach CHF 4.90 pro Monat');
  });
  it('bezahlte Einführung über mehrere Zyklen', () => {
    const p = product({ introPrice: { price: 0.99, priceString: 'CHF 0.99', cycles: 3, period: 'P1M', periodUnit: 'MONTH', periodNumberOfUnits: 1 } });
    expect(introText(p)).toBe('CHF 0.99 pro Monat für 3 Monate, danach CHF 4.90 pro Monat');
  });
  it('unbekannte Einheit → kein Text (nichts erfinden)', () => {
    const p = product({ introPrice: { price: 0, priceString: 'x', cycles: 1, period: 'P1X', periodUnit: 'FOO', periodNumberOfUnits: 1 } });
    expect(introText(p)).toBeNull();
  });
});

describe('buildOffer', () => {
  const withIntro = pkg({ product: product({ introPrice: { price: 0, priceString: 'CHF 0.00', cycles: 1, period: 'P1W', periodUnit: 'WEEK', periodNumberOfUnits: 1 } }) });
  it('übernimmt Preis und Laufzeit aus dem Produkt', () => {
    const o = buildOffer(pkg(), false);
    expect(o.priceString).toBe('CHF 4.90');
    expect(o.periodText).toBe('1 Monat');
    expect(o.renewalPrice).toBe('CHF 4.90 pro Monat');
    expect(o.intro).toBeNull();
  });
  it('Einführungsphase nur, wenn der Nutzer berechtigt ist', () => {
    expect(buildOffer(withIntro, true).intro).toContain('kostenlos');
    expect(buildOffer(withIntro, false).intro).toBeNull();
  });
});
