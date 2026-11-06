import { FEATURES, subscriptionAvailable } from './features';

describe('subscriptionAvailable', () => {
  it('ist an, wenn Schalter an, Schlüssel gesetzt und iOS', () => {
    expect(subscriptionAvailable('appl_abc', 'ios', true)).toBe(true);
  });
  it('ist aus ohne Schlüssel (undefined, null, leer, nur Leerzeichen)', () => {
    expect(subscriptionAvailable(undefined, 'ios', true)).toBe(false);
    expect(subscriptionAvailable(null, 'ios', true)).toBe(false);
    expect(subscriptionAvailable('', 'ios', true)).toBe(false);
    expect(subscriptionAvailable('   ', 'ios', true)).toBe(false);
  });
  it('ist aus, wenn der Schalter aus ist', () => {
    expect(subscriptionAvailable('appl_abc', 'ios', false)).toBe(false);
  });
  it('ist aus ausserhalb von iOS (der Schlüssel gilt nur für iOS)', () => {
    expect(subscriptionAvailable('appl_abc', 'web', true)).toBe(false);
    expect(subscriptionAvailable('appl_abc', 'android', true)).toBe(false);
  });
  it('Standardschalter ist an, ohne Umgebungsvariable aber nicht verfügbar', () => {
    expect(FEATURES.subscription).toBe(true);
    const old = process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY;
    delete process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY;
    expect(subscriptionAvailable()).toBe(false);
    if (old !== undefined) process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY = old;
  });
});
