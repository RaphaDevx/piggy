/**
 * subscriptionLogic.ts — reine Logik für «Piggy Plus» (ohne SDK, ohne natives Modul).
 *
 * Alles, was Apple pro Produkt liefert (Preis, Laufzeit, Einführungsphase), wird nur formatiert,
 * nie hartkodiert. Die SDK-Schicht liegt in src/lib/subscription.ts.
 */

export const PLUS_ENTITLEMENT = 'plus';
export const PLUS_OFFERING = 'default';
export const MANAGE_SUBSCRIPTIONS_URL = 'https://apps.apple.com/account/subscriptions';
/** INTRO_ELIGIBILITY_STATUS_ELIGIBLE im RevenueCat-SDK. */
export const INTRO_ELIGIBLE = 2;

// Strukturelle Teilmengen der SDK-Typen, damit Tests ohne SDK auskommen.
export interface EntitlementInfoLike {
  isActive?: boolean;
  expirationDate?: string | null;
  willRenew?: boolean;
}
export interface CustomerInfoLike {
  entitlements?: { active?: Record<string, EntitlementInfoLike | undefined> };
}
export interface IntroPriceLike {
  price: number;
  priceString: string;
  cycles: number;
  period: string;
  periodUnit: string;
  periodNumberOfUnits: number;
}
export interface ProductLike {
  identifier: string;
  priceString: string;
  subscriptionPeriod: string | null;
  introPrice: IntroPriceLike | null;
}
export interface PackageLike {
  identifier: string;
  packageType: string;
  product: ProductLike;
}
export interface OfferingLike {
  monthly?: PackageLike | null;
  availablePackages?: PackageLike[];
}
export interface OfferingsLike {
  all?: Record<string, OfferingLike | undefined>;
}

export interface PlusStatus {
  isPlus: boolean;
  expiresAt: Date | null;
  willRenew: boolean;
}

export const NO_PLUS: PlusStatus = { isPlus: false, expiresAt: null, willRenew: false };

/** Kundeninfo → Plus-Status (Entitlement «plus» aktiv). */
export function plusStatusFromCustomerInfo(info: CustomerInfoLike | null | undefined): PlusStatus {
  const ent = info?.entitlements?.active?.[PLUS_ENTITLEMENT];
  if (!ent || ent.isActive === false) return NO_PLUS;
  const t = ent.expirationDate ? Date.parse(ent.expirationDate) : NaN;
  return {
    isPlus: true,
    expiresAt: Number.isFinite(t) ? new Date(t) : null,
    willRenew: ent.willRenew === true,
  };
}

/** Abbruch durch den Nutzer ist kein Fehler (SDK: userCancelled bzw. Code «1»). */
export function isUserCancelled(err: unknown): boolean {
  const e = err as { userCancelled?: boolean | null; code?: unknown } | null | undefined;
  return !!e && (e.userCancelled === true || String(e.code) === '1');
}

/** Monatspaket aus dem Offering «default». */
export function pickMonthlyPackage(offerings: OfferingsLike | null | undefined): PackageLike | null {
  const offering = offerings?.all?.[PLUS_OFFERING];
  if (!offering) return null;
  return offering.monthly
    ?? offering.availablePackages?.find(p => p.packageType === 'MONTHLY')
    ?? null;
}

const UNITS: Record<string, [string, string]> = {
  D: ['Tag', 'Tage'],
  W: ['Woche', 'Wochen'],
  M: ['Monat', 'Monate'],
  Y: ['Jahr', 'Jahre'],
};
const UNIT_NAMES: Record<string, string> = { DAY: 'D', WEEK: 'W', MONTH: 'M', YEAR: 'Y' };

function unitText(code: string, count: number): string | null {
  const u = UNITS[code];
  return u ? `${count} ${count === 1 ? u[0] : u[1]}` : null;
}

/** ISO-8601-Laufzeit (z. B. «P1M») → «1 Monat». Unbekanntes Format → null. */
export function formatPeriod(iso: string | null | undefined): string | null {
  const m = /^P(\d+)([DWMY])$/.exec(iso ?? '');
  if (!m) return null;
  const n = parseInt(m[1], 10);
  return n > 0 ? unitText(m[2], n) : null;
}

/** «CHF 4.90 pro Monat» bzw. «CHF 9.90 alle 2 Monate»; ohne Laufzeit nur der Preis. */
export function recurringPhrase(priceString: string, iso: string | null | undefined): string {
  const m = /^P(\d+)([DWMY])$/.exec(iso ?? '');
  if (!m) return priceString;
  const n = parseInt(m[1], 10);
  if (n === 1) return `${priceString} pro ${UNITS[m[2]][0]}`;
  const dur = unitText(m[2], n);
  return dur ? `${priceString} alle ${dur}` : priceString;
}

/** Dauer der gesamten Einführungsphase («7 Tage», «3 Monate») aus cycles × Einheiten. */
export function introDuration(intro: IntroPriceLike): string | null {
  const code = UNIT_NAMES[intro.periodUnit];
  const total = intro.cycles * intro.periodNumberOfUnits;
  return code && total > 0 ? unitText(code, total) : null;
}

/**
 * Text zur Einführungsphase, ausschliesslich aus dem Produkt:
 * «7 Tage kostenlos, danach CHF 4.90 pro Monat» bzw. «CHF 0.99 für 1 Monat, danach CHF 4.90 pro Monat».
 */
export function introText(product: ProductLike): string | null {
  const intro = product.introPrice;
  if (!intro) return null;
  const dur = introDuration(intro);
  if (!dur) return null;
  const after = `danach ${recurringPhrase(product.priceString, product.subscriptionPeriod)}`;
  if (intro.price === 0) return `${dur} kostenlos, ${after}`;
  const perPeriod = intro.cycles > 1 ? ` pro ${formatPeriod(intro.period)?.replace(/^1 /, '') ?? 'Zeitraum'}` : '';
  return `${intro.priceString}${perPeriod} für ${dur}, ${after}`;
}

/** Angebot für die Paywall, vollständig aus dem Produkt abgeleitet. */
export interface PlusOffer {
  pkg: PackageLike;
  priceString: string;
  /** «1 Monat»; Rückfall nur, weil gezielt das Monatspaket gewählt wird. */
  periodText: string;
  renewalPrice: string;
  intro: string | null;
}

export function buildOffer(pkg: PackageLike, introEligible: boolean): PlusOffer {
  const p = pkg.product;
  return {
    pkg,
    priceString: p.priceString,
    periodText: formatPeriod(p.subscriptionPeriod) ?? '1 Monat',
    renewalPrice: recurringPhrase(p.priceString, p.subscriptionPeriod ?? 'P1M'),
    intro: introEligible ? introText(p) : null,
  };
}
