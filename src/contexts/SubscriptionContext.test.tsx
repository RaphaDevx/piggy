import React from 'react';
import Renderer, { act } from 'react-test-renderer';
import { SubscriptionProvider, useSubscription } from './SubscriptionContext';
const mockRead = jest.fn();
const mockInit = jest.fn(async () => true);
const mockListeners: Array<(status: object) => void> = [];
jest.mock('../constants/features', () => ({ subscriptionAvailable: () => true }));
jest.mock('../lib/subscription', () => ({
  initSubscription: () => mockInit(), logOutSubscription: jest.fn(async () => {}), readPlusStatus: () => mockRead(),
  onPlusStatusChange: (listener: (status: object) => void) => { mockListeners.push(listener); return jest.fn(); },
}));
let state: ReturnType<typeof useSubscription>;
let root: Renderer.ReactTestRenderer;
function Probe() { state = useSubscription(); return null; }
const free = { isPlus: false, expiresAt: null, willRenew: false };
const gold = { ...free, isPlus: true };
beforeEach(() => { jest.clearAllMocks(); mockListeners.length = 0; mockRead.mockResolvedValue(free); });
afterEach(async () => { await act(async () => root.unmount()); });
test('known free and lookup errors remain distinguishable for the ad gate', async () => {
  await act(async () => { root = Renderer.create(<SubscriptionProvider userId="a"><Probe /></SubscriptionProvider>); });
  expect(state.resolved).toBe(true);
  expect(state.status.isPlus).toBe(false);
  mockRead.mockResolvedValueOnce(null);
  await act(async () => state.refresh());
  expect(state.resolved).toBe(false);
});
test('upgrades update all consumers; account switch rejects an old listener', async () => {
  await act(async () => { root = Renderer.create(<SubscriptionProvider userId="a"><Probe /></SubscriptionProvider>); });
  const previous = mockListeners[0];
  act(() => previous(gold));
  expect(state.status.isPlus).toBe(true);
  await act(async () => { root.update(<SubscriptionProvider userId="b"><Probe /></SubscriptionProvider>); });
  expect(state.status.isPlus).toBe(false);
  act(() => previous(gold));
  expect(state.status.isPlus).toBe(false);
  expect(state.userId).toBe('b');
});
