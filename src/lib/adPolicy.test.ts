import { adsEligible, canShowScanAd, emptyAdHistory, parseAdHistory } from './adPolicy';
const now = Date.parse('2026-11-17T12:00:00Z');
test('Gold, unknown subscription, logout, rollout off and declined consent never permit ads', () => {
  const free = { enabled: true, signedIn: true, subscriptionResolved: true, isGold: false, consent: true };
  expect(adsEligible(free)).toBe(true);
  for (const change of [{ enabled: false }, { signedIn: false }, { subscriptionResolved: false }, { isGold: true }, { consent: false }]) {
    expect(adsEligible({ ...free, ...change })).toBe(false);
  }
});
test('first opportunity is the third scan; cooldown and daily cap persist independently', () => {
  const history = emptyAdHistory(now);
  expect(canShowScanAd({ ...history, scans: 2 }, now)).toBe(false);
  expect(canShowScanAd({ ...history, scans: 3 }, now)).toBe(true);
  expect(canShowScanAd({ ...history, scans: 6, shown: 3 }, now)).toBe(false);
  expect(canShowScanAd({ ...history, scans: 6, lastShownAt: now - 179_999 }, now)).toBe(false);
  expect(canShowScanAd({ ...history, scans: 6, lastShownAt: now - 180_000 }, now)).toBe(true);
});
test('new UTC day resets counts while retaining a cross-midnight cooldown', () => {
  const midnight = Date.parse('2026-11-18T00:00:01Z');
  const loaded = parseAdHistory(JSON.stringify({ day: '2026-11-17', shown: 3, scans: 9, lastShownAt: midnight - 2000 }), midnight);
  expect(loaded.shown).toBe(0);
  expect(loaded.scans).toBe(0);
  expect(canShowScanAd({ ...loaded, scans: 3 }, midnight)).toBe(false);
});
test('bad local history never crashes scanning', () => {
  for (const input of [null, 'broken', '{}', JSON.stringify({ day: '2026-11-17', scans: -1, shown: 0, lastShownAt: 0 })]) {
    expect(parseAdHistory(input, now)).toEqual(emptyAdHistory(now));
  }
});
