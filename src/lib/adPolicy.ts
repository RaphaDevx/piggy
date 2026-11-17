/** Monetisation rules, independent of Google and receipt contents. */
export const AD_POLICY = { scansBetweenAds: 3, minIntervalMs: 180_000, maxPerDay: 3 } as const;
export type AdHistory = { day: string; shown: number; scans: number; lastShownAt: number };
export function emptyAdHistory(now: number): AdHistory {
  return { day: new Date(now).toISOString().slice(0, 10), shown: 0, scans: 0, lastShownAt: 0 };
}
export function parseAdHistory(raw: string | null, now: number): AdHistory {
  const empty = emptyAdHistory(now);
  try {
    const value = JSON.parse(raw ?? 'null');
    if (!value || !['shown', 'scans', 'lastShownAt'].every(k => Number.isSafeInteger(value[k]) && value[k] >= 0)) return empty;
    return value.day === empty.day ? value : { ...empty, lastShownAt: value.lastShownAt };
  } catch { return empty; }
}
export function canShowScanAd(history: AdHistory, now: number): boolean {
  return history.scans >= AD_POLICY.scansBetweenAds && history.shown < AD_POLICY.maxPerDay &&
    (history.lastShownAt === 0 || now - history.lastShownAt >= AD_POLICY.minIntervalMs);
}
export function adsEligible(input: { enabled: boolean; signedIn: boolean; subscriptionResolved: boolean; isGold: boolean; consent: boolean }): boolean {
  return input.enabled && input.signedIn && input.subscriptionResolved && !input.isGold && input.consent;
}
