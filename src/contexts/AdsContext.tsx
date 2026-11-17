import { createContext, useCallback, useContext, useEffect, useRef, useState, type ReactNode } from 'react';
import { AppState } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { useSubscription } from './SubscriptionContext';
import { adSdk, adsConfigured, interstitialUnit } from '../lib/admob';
import { readPlusStatus } from '../lib/subscription';
import { adsEligible, canShowScanAd, parseAdHistory } from '../lib/adPolicy';

const CHOICE_KEY = 'piggy.ads.choice.v1';
const HISTORY_KEY = 'piggy.ads.frequency.v1';
type Choice = 'unknown' | 'allowed' | 'declined';
type Value = {
  configured: boolean; ready: boolean; choice: Choice; busy: boolean;
  allow: () => Promise<void>; decline: () => Promise<void>; privacyOptions: () => Promise<void>;
  afterScanSaved: () => Promise<void>;
};
const Context = createContext<Value | null>(null);

/** Ads never receive receipt contents, purchase categories or the Piggy account ID. */
export function AdsProvider({ children }: { children: ReactNode }) {
  const subscription = useSubscription();
  const configured = adsConfigured() && subscription.available;
  const [choice, setChoice] = useState<Choice>('unknown');
  const [choiceLoaded, setChoiceLoaded] = useState(false);
  const [ready, setReady] = useState(false);
  const [busy, setBusy] = useState(false);
  const [consentRun, setConsentRun] = useState(0);
  const generation = useRef(0);
  const consentFlight = useRef<ReturnType<ReturnType<typeof adSdk>['AdsConsent']['gatherConsent']> | null>(null);
  const showing = useRef(false);
  const ad = useRef<ReturnType<ReturnType<typeof adSdk>['InterstitialAd']['createForAdRequest']> | null>(null);
  const adLoaded = useRef(false);
  const finishShowing = useRef<(() => void) | null>(null);
  const eligible = adsEligible({ enabled: configured, signedIn: !!subscription.userId,
    subscriptionResolved: subscription.resolved, isGold: subscription.status.isPlus,
    consent: choiceLoaded && choice === 'allowed' });
  const current = useRef({ eligible, userId: subscription.userId });
  current.current = { eligible, userId: subscription.userId };

  useEffect(() => {
    let live = true;
    AsyncStorage.getItem(CHOICE_KEY).then(value => {
      if (live) { setChoice(value === 'allowed' || value === 'declined' ? value : 'unknown'); setChoiceLoaded(true); }
    }).catch(() => { if (live) setChoiceLoaded(true); });
    return () => { live = false; };
  }, []);
  useEffect(() => {
    const listener = AppState.addEventListener('change', state => {
      if (state === 'active' && !showing.current) setConsentRun(run => run + 1);
    });
    return () => listener.remove();
  }, []);

  useEffect(() => {
    const epoch = ++generation.current;
    setReady(false);
    adLoaded.current = false;
    ad.current = null;
    if (!eligible || AppState.currentState !== 'active') return;
    let removeListeners = () => {};
    const stillEligible = () => generation.current === epoch && current.current.eligible;
    void (async () => {
      try {
        const sdk = adSdk();
        const flight = consentFlight.current ?? sdk.AdsConsent.gatherConsent();
        consentFlight.current = flight;
        const consent = await flight.finally(() => { if (consentFlight.current === flight) consentFlight.current = null; });
        if (!consent.canRequestAds || !stillEligible()) return;
        await sdk.default().setRequestConfiguration({ maxAdContentRating: sdk.MaxAdContentRating.G });
        if (!stillEligible()) return;
        await sdk.default().initialize();
        if (!stillEligible()) return;
        const interstitial = sdk.InterstitialAd.createForAdRequest(interstitialUnit(), { requestNonPersonalizedAdsOnly: true });
        ad.current = interstitial;
        const off = [
          interstitial.addAdEventListener(sdk.AdEventType.LOADED, () => { if (stillEligible()) adLoaded.current = true; }),
          interstitial.addAdEventListener(sdk.AdEventType.ERROR, () => { adLoaded.current = false; finishShowing.current?.(); }),
          interstitial.addAdEventListener(sdk.AdEventType.CLOSED, () => {
            adLoaded.current = false;
            finishShowing.current?.();
            if (stillEligible() && AppState.currentState === 'active') interstitial.load();
          }),
        ];
        removeListeners = () => off.forEach(remove => remove());
        setReady(true);
        interstitial.load();
      } catch { if (stillEligible()) setReady(false); }
    })();
    return () => { removeListeners(); ad.current = null; adLoaded.current = false; finishShowing.current?.(); };
  }, [eligible, consentRun]);

  const decline = useCallback(async () => {
    current.current.eligible = false;
    generation.current += 1;
    setReady(false);
    setChoice('declined');
    await AsyncStorage.setItem(CHOICE_KEY, 'declined').catch(() => {});
  }, []);
  const allow = useCallback(async () => {
    setChoice('allowed');
    await AsyncStorage.setItem(CHOICE_KEY, 'allowed').catch(() => {});
    setConsentRun(run => run + 1);
  }, []);
  const privacyOptions = useCallback(async () => {
    if (!configured || busy || subscription.status.isPlus || !subscription.resolved) return;
    setBusy(true);
    setReady(false);
    generation.current += 1;
    try {
      await adSdk().AdsConsent.showPrivacyOptionsForm();
    } catch { /* A form may not be required; declining remains available independently. */ }
    finally { setBusy(false); setConsentRun(run => run + 1); }
  }, [configured, busy, subscription.status.isPlus, subscription.resolved]);

  const afterScanSaved = useCallback(async () => {
    if (!current.current.eligible || showing.current) return;
    const userId = current.current.userId;
    showing.current = true;
    try {
      const now = Date.now();
      const history = parseAdHistory(await AsyncStorage.getItem(HISTORY_KEY), now);
      history.scans += 1;
      await AsyncStorage.setItem(HISTORY_KEY, JSON.stringify(history));
      if (!current.current.eligible || current.current.userId !== userId || AppState.currentState !== 'active' ||
          !ready || !canShowScanAd(history, now) || !ad.current || !adLoaded.current) return;
      // Recheck entitlement immediately before presenting. An error or an upgrade skips the ad.
      let statusTimer: ReturnType<typeof setTimeout> | undefined;
      const verified = await Promise.race([
        readPlusStatus(),
        new Promise<null>(resolve => { statusTimer = setTimeout(() => resolve(null), 1500); }),
      ]).finally(() => { if (statusTimer) clearTimeout(statusTimer); });
      if (!verified || verified.isPlus) {
        if (verified) subscription.setStatus(verified);
        return;
      }
      if (!current.current.eligible || current.current.userId !== userId) return;
      const interstitial = ad.current;
      if (!interstitial || !adLoaded.current) return;
      const marked = { ...history, scans: 0, shown: history.shown + 1, lastShownAt: now };
      await AsyncStorage.setItem(HISTORY_KEY, JSON.stringify(marked));
      if (!current.current.eligible || current.current.userId !== userId || AppState.currentState !== 'active') return;
      adLoaded.current = false;
      await new Promise<void>(resolve => {
        finishShowing.current = () => { finishShowing.current = null; resolve(); };
        // Never invent a video-duration timer or reward completion from elapsed time.
        interstitial.show().catch(() => finishShowing.current?.());
      });
    } catch { /* A committed receipt always remains saved, even if consent/storage/ads fail. */ }
    finally { showing.current = false; }
  }, [ready, subscription.setStatus]);

  return <Context.Provider value={{ configured, ready: ready && eligible, choice, busy, allow, decline, privacyOptions, afterScanSaved }}>{children}</Context.Provider>;
}
export function useAds() {
  const value = useContext(Context);
  if (!value) throw new Error('AdsProvider is missing');
  return value;
}
