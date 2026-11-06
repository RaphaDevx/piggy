/**
 * subscription.ts — «Piggy Plus» über RevenueCat (react-native-purchases).
 *
 * Ohne EXPO_PUBLIC_REVENUECAT_IOS_KEY (bzw. FEATURES.subscription aus) wird das SDK nie geladen
 * oder konfiguriert; alle Funktionen liefern dann neutrale Werte. appUserID = Supabase-User-ID.
 * Fehler werden nie geworfen: Abbruch durch den Nutzer ist 'cancelled', der Rest 'error'.
 * Reine Logik: src/lib/subscriptionLogic.ts.
 */
import { subscriptionAvailable } from '../constants/features';
import {
  buildOffer, isUserCancelled, pickMonthlyPackage, plusStatusFromCustomerInfo,
  INTRO_ELIGIBLE, NO_PLUS,
  type PlusOffer, type PlusStatus, type PackageLike,
} from './subscriptionLogic';

export { MANAGE_SUBSCRIPTIONS_URL } from './subscriptionLogic';
export type { PlusOffer, PlusStatus } from './subscriptionLogic';

type Sdk = typeof import('react-native-purchases').default;

let sdk: Sdk | null = null;
let configured = false;
let currentUser: string | null = null;
let chain: Promise<unknown> = Promise.resolve();

/** SDK erst bei Bedarf laden, damit ohne Schlüssel kein natives Modul angefasst wird. */
function loadSdk(): Sdk {
  if (!sdk) sdk = require('react-native-purchases').default as Sdk;
  return sdk;
}

/** Aufrufe nacheinander ausführen (configure/logIn/logOut dürfen sich nicht überholen). */
function enqueue<T>(fn: () => Promise<T>): Promise<T> {
  const run = chain.then(fn, fn);
  chain = run.catch(() => undefined);
  return run;
}

/** Konfiguriert RevenueCat mit der Supabase-User-ID. false, wenn nicht verfügbar oder fehlgeschlagen. */
export function initSubscription(userId: string): Promise<boolean> {
  const apiKey = process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY;
  if (!subscriptionAvailable(apiKey) || !userId) return Promise.resolve(false);
  return enqueue(async () => {
    try {
      const Purchases = loadSdk();
      if (!configured) {
        Purchases.configure({ apiKey: apiKey as string, appUserID: userId });
        configured = true;
      } else if (currentUser !== userId) {
        await Purchases.logIn(userId);
      }
      currentUser = userId;
      return true;
    } catch {
      return false;
    }
  });
}

/** Bei Logout: zurück auf anonymen Nutzer. Tut nichts, wenn nie konfiguriert. */
export function logOutSubscription(): Promise<void> {
  return enqueue(async () => {
    if (!configured || currentUser === null) return;
    currentUser = null;
    try { await loadSdk().logOut(); } catch { /* war schon anonym */ }
  });
}

function ready(): Promise<boolean> {
  return enqueue(async () => configured && currentUser !== null);
}

/** Plus-Status aus der Kundeninfo; bei Fehler oder nicht verfügbar: nicht aktiv. */
export async function getPlusStatus(): Promise<PlusStatus> {
  if (!(await ready())) return NO_PLUS;
  try {
    return plusStatusFromCustomerInfo(await loadSdk().getCustomerInfo());
  } catch {
    return NO_PLUS;
  }
}

/** Monatsangebot aus dem Offering «default»; null, wenn es keines gibt oder der Abruf scheitert. */
export async function loadMonthlyOffer(): Promise<PlusOffer | null> {
  if (!(await ready())) return null;
  try {
    const Purchases = loadSdk();
    const pkg = pickMonthlyPackage(await Purchases.getOfferings());
    if (!pkg) return null;
    let eligible = false;
    if (pkg.product.introPrice) {
      try {
        const map = await Purchases.checkTrialOrIntroductoryPriceEligibility([pkg.product.identifier]);
        eligible = Number(map?.[pkg.product.identifier]?.status) === INTRO_ELIGIBLE;
      } catch { /* unklar → Einführungsphase nicht anzeigen */ }
    }
    return buildOffer(pkg, eligible);
  } catch {
    return null;
  }
}

export type PurchaseOutcome =
  | { status: 'success'; plus: PlusStatus }
  | { status: 'cancelled' }
  | { status: 'error'; message: string };

const ERR_TEXT = 'Der Vorgang konnte nicht abgeschlossen werden. Bitte versuche es später erneut.';

export async function buyPlus(pkg: PackageLike): Promise<PurchaseOutcome> {
  if (!(await ready())) return { status: 'error', message: ERR_TEXT };
  try {
    const res = await loadSdk().purchasePackage(pkg as never);
    return { status: 'success', plus: plusStatusFromCustomerInfo(res.customerInfo) };
  } catch (e) {
    return isUserCancelled(e) ? { status: 'cancelled' } : { status: 'error', message: ERR_TEXT };
  }
}

export async function restorePlus(): Promise<PurchaseOutcome> {
  if (!(await ready())) return { status: 'error', message: ERR_TEXT };
  try {
    const info = await loadSdk().restorePurchases();
    return { status: 'success', plus: plusStatusFromCustomerInfo(info) };
  } catch (e) {
    return isUserCancelled(e) ? { status: 'cancelled' } : { status: 'error', message: ERR_TEXT };
  }
}
