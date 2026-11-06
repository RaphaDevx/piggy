// Paywall-Route: ohne Schlüssel kein Absturz, nur Weiterleitung; mit Schlüssel Pflichtangaben sichtbar.
import React from 'react';
import renderer, { act } from 'react-test-renderer';

const mockRedirect = jest.fn((_props: unknown) => null);
jest.mock('expo-router', () => ({
  Redirect: (props: any) => mockRedirect(props),
  useRouter: () => ({ back: jest.fn() }),
}));
jest.mock('react-native-safe-area-context', () => ({ useSafeAreaInsets: () => ({ top: 0, bottom: 0, left: 0, right: 0 }) }));
jest.mock('../components/Ionicons', () => ({ Ionicons: () => null }));
const mockOffer = {
  pkg: { identifier: 'm', packageType: 'MONTHLY', product: {} }, priceString: 'CHF 4.90', periodText: '1 Monat',
  renewalPrice: 'CHF 4.90 pro Monat', intro: null,
};
jest.mock('../lib/subscription', () => ({
  buyPlus: jest.fn(), restorePlus: jest.fn(),
  loadMonthlyOffer: jest.fn(async () => mockOffer),
  getPlusStatus: jest.fn(async () => ({ isPlus: false, expiresAt: null, willRenew: false })),
  MANAGE_SUBSCRIPTIONS_URL: 'https://apps.apple.com/account/subscriptions',
}));

import PlusRoute from '../../app/plus';

afterEach(() => { delete process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY; mockRedirect.mockClear(); });

function texts(tree: renderer.ReactTestRenderer): string {
  return JSON.stringify(tree.toJSON());
}

it('ohne Schlüssel: Weiterleitung, kein Absturz, kein Angebot geladen', () => {
  delete process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY;
  let tree!: renderer.ReactTestRenderer;
  act(() => { tree = renderer.create(<PlusRoute />); });
  expect(mockRedirect).toHaveBeenCalled();
  expect(texts(tree)).not.toContain('Piggy Plus');
});

it('mit Schlüssel: Pflichtangaben stehen da', async () => {
  process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY = 'appl_test';
  let tree!: renderer.ReactTestRenderer;
  await act(async () => { tree = renderer.create(<PlusRoute />); });
  const t = texts(tree);
  for (const s of ['Piggy Plus', 'CHF 4.90', '1 Monat', 'Verlängert sich automatisch', 'Nutzungsbedingungen', 'Datenschutz', 'Käufe wiederherstellen']) {
    expect(t).toContain(s);
  }
  expect(mockRedirect).not.toHaveBeenCalled();
});
