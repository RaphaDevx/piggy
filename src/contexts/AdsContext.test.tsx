import React from 'react';
import Renderer, { act } from 'react-test-renderer';
import { AppState } from 'react-native';
import { AdsProvider, useAds } from './AdsContext';

const mockStorage = new Map<string, string>();
jest.mock('@react-native-async-storage/async-storage', () => ({
  __esModule: true, default: {
    getItem: jest.fn(async (key: string) => mockStorage.get(key) ?? null),
    setItem: jest.fn(async (key: string, value: string) => { mockStorage.set(key, value); }),
  },
}));
const free = { isPlus: false, expiresAt: null, willRenew: false };
let mockSubscription = { userId: 'account-a' as string | null, available: true, resolved: true, status: free,
  setStatus: jest.fn(), refresh: jest.fn(async () => {}) };
jest.mock('./SubscriptionContext', () => ({ useSubscription: () => mockSubscription }));
const mockReadStatus = jest.fn();
jest.mock('../lib/subscription', () => ({ readPlusStatus: () => mockReadStatus() }));
const mockEvents: Record<string, () => void> = {};
const mockAd = {
  load: jest.fn(() => { mockEvents.loaded?.(); }),
  show: jest.fn(async () => { mockEvents.closed?.(); }),
  addAdEventListener: jest.fn((event: string, callback: () => void) => {
    mockEvents[event] = callback;
    return () => { delete mockEvents[event]; };
  }),
};
const mockSdk = {
  AdsConsent: { gatherConsent: jest.fn(), showPrivacyOptionsForm: jest.fn(async () => {}) },
  default: () => ({ setRequestConfiguration: jest.fn(async () => {}), initialize: jest.fn(async () => {}) }),
  MaxAdContentRating: { G: 'G' }, AdEventType: { LOADED: 'loaded', CLOSED: 'closed', ERROR: 'error' },
  InterstitialAd: { createForAdRequest: jest.fn(() => mockAd) },
};
const mockLoadSdk = jest.fn(() => mockSdk);
jest.mock('../lib/admob', () => ({ adsConfigured: () => true, adSdk: () => mockLoadSdk(), interstitialUnit: () => 'test-interstitial' }));
let ads: ReturnType<typeof useAds>;
let root: Renderer.ReactTestRenderer;
function Probe() { ads = useAds(); return null; }
async function mount() { await act(async () => { root = Renderer.create(<AdsProvider><Probe /></AdsProvider>); }); }
async function scan() { await act(async () => { await ads.afterScanSaved(); }); }
beforeEach(() => {
  jest.clearAllMocks();
  mockStorage.clear();
  Object.keys(mockEvents).forEach(key => delete mockEvents[key]);
  Object.defineProperty(AppState, 'currentState', { value: 'active', writable: true, configurable: true });
  mockSubscription = { userId: 'account-a', available: true, resolved: true, status: free, setStatus: jest.fn(), refresh: jest.fn(async () => {}) };
  mockReadStatus.mockResolvedValue(free);
  mockSdk.AdsConsent.gatherConsent.mockResolvedValue({ canRequestAds: true });
});
afterEach(async () => { if (root) await act(async () => root.unmount()); });

test('no ad SDK access before an explicit choice; declining keeps it off', async () => {
  await mount();
  expect(mockLoadSdk).not.toHaveBeenCalled();
  await act(async () => ads.decline());
  await scan();
  expect(mockLoadSdk).not.toHaveBeenCalled();
});
test.each(['gold', 'unknown', 'signed-out'])('%s accounts never initialise advertising even with a previous opt-in', async mode => {
  mockStorage.set('piggy.ads.choice.v1', 'allowed');
  if (mode === 'gold') mockSubscription.status = { ...free, isPlus: true };
  if (mode === 'unknown') mockSubscription.resolved = false;
  if (mode === 'signed-out') mockSubscription.userId = null;
  await mount();
  await scan();
  expect(mockLoadSdk).not.toHaveBeenCalled();
});
test('requests non-personalised ads only after UMP; third save displays, next save respects the cooldown', async () => {
  mockStorage.set('piggy.ads.choice.v1', 'allowed');
  await mount();
  expect(ads.ready).toBe(true);
  expect(mockSdk.InterstitialAd.createForAdRequest).toHaveBeenCalledWith('test-interstitial', { requestNonPersonalizedAdsOnly: true });
  await scan(); await scan();
  expect(mockAd.show).not.toHaveBeenCalled();
  await scan();
  expect(mockReadStatus).toHaveBeenCalledTimes(1);
  expect(mockAd.show).toHaveBeenCalledTimes(1);
  await scan();
  expect(mockAd.show).toHaveBeenCalledTimes(1);
});
test('no-fill never blocks a saved scan or consumes an impression', async () => {
  mockAd.load.mockImplementationOnce(() => {});
  mockStorage.set('piggy.ads.choice.v1', 'allowed');
  await mount();
  await scan(); await scan(); await scan();
  expect(mockAd.show).not.toHaveBeenCalled();
  expect(JSON.parse(mockStorage.get('piggy.ads.frequency.v1')!).shown).toBe(0);
});
test('a newly discovered Gold entitlement suppresses the ready ad', async () => {
  mockStorage.set('piggy.ads.choice.v1', 'allowed');
  await mount();
  await scan(); await scan();
  mockReadStatus.mockResolvedValueOnce({ ...free, isPlus: true });
  await scan();
  expect(mockAd.show).not.toHaveBeenCalled();
  expect(mockSubscription.setStatus).toHaveBeenCalledWith(expect.objectContaining({ isPlus: true }));
});
test('status lookup or consent failure skips advertising without throwing from save', async () => {
  mockStorage.set('piggy.ads.choice.v1', 'allowed');
  await mount();
  await scan(); await scan();
  mockReadStatus.mockResolvedValueOnce(null);
  await scan();
  expect(mockAd.show).not.toHaveBeenCalled();
  await act(async () => ads.decline());
  expect(ads.ready).toBe(false);
});
test('consent rejection does not initialise an interstitial', async () => {
  mockStorage.set('piggy.ads.choice.v1', 'allowed');
  mockSdk.AdsConsent.gatherConsent.mockResolvedValueOnce({ canRequestAds: false });
  await mount();
  expect(ads.ready).toBe(false);
  expect(mockSdk.InterstitialAd.createForAdRequest).not.toHaveBeenCalled();
});

test('a stalled subscription lookup skips the ad after a bounded wait', async () => {
  mockStorage.set('piggy.ads.choice.v1', 'allowed');
  await mount();
  await scan(); await scan();
  jest.useFakeTimers();
  try {
    mockReadStatus.mockReturnValueOnce(new Promise(() => {}));
    await act(async () => {
      const pending = ads.afterScanSaved();
      await jest.advanceTimersByTimeAsync(1500);
      await pending;
    });
    expect(mockAd.show).not.toHaveBeenCalled();
  } finally { jest.useRealTimers(); }
});
