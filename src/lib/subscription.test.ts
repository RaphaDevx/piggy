// SDK-Schicht mit gemocktem react-native-purchases: kein natives Modul nötig.
const mockSdk = {
  configure: jest.fn(),
  logIn: jest.fn().mockResolvedValue({}),
  logOut: jest.fn().mockResolvedValue({}),
  getCustomerInfo: jest.fn(),
  getOfferings: jest.fn(),
  purchasePackage: jest.fn(),
  restorePurchases: jest.fn(),
  checkTrialOrIntroductoryPriceEligibility: jest.fn(),
};
jest.mock('react-native-purchases', () => ({ __esModule: true, default: mockSdk }));

const UID = '3f2b8a52-6c1e-4d0b-9a77-1c2d3e4f5a6b';
const active = { entitlements: { active: { plus: { isActive: true, expirationDate: '2027-01-01T00:00:00Z', willRenew: true } } } };

function load() {
  let mod!: typeof import('./subscription');
  jest.isolateModules(() => { mod = require('./subscription'); });
  return mod;
}

beforeEach(() => {
  jest.clearAllMocks();
  process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY = 'appl_test';
});
afterAll(() => { delete process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY; });

describe('ohne Schlüssel', () => {
  beforeEach(() => { delete process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY; });

  it('initialisiert nichts und lädt das SDK nicht', async () => {
    const s = load();
    expect(await s.initSubscription(UID)).toBe(false);
    await s.logOutSubscription();
    expect(mockSdk.configure).not.toHaveBeenCalled();
    expect(mockSdk.logOut).not.toHaveBeenCalled();
  });
  it('liefert neutrale Werte statt Fehler', async () => {
    const s = load();
    expect((await s.getPlusStatus()).isPlus).toBe(false);
    expect(await s.loadMonthlyOffer()).toBeNull();
    expect((await s.restorePlus()).status).toBe('error');
    expect(mockSdk.getCustomerInfo).not.toHaveBeenCalled();
  });
});

describe('mit Schlüssel', () => {
  it('konfiguriert einmal mit der User-ID, danach logIn bei anderem Nutzer', async () => {
    const s = load();
    expect(await s.initSubscription(UID)).toBe(true);
    expect(mockSdk.configure).toHaveBeenCalledWith({ apiKey: 'appl_test', appUserID: UID });
    await s.initSubscription(UID);
    expect(mockSdk.configure).toHaveBeenCalledTimes(1);
    expect(mockSdk.logIn).not.toHaveBeenCalled();
    await s.initSubscription('aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaaa');
    expect(mockSdk.logIn).toHaveBeenCalledWith('aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaaa');
  });
  it('logOut beim Abmelden, nur einmal', async () => {
    const s = load();
    await s.initSubscription(UID);
    await s.logOutSubscription();
    await s.logOutSubscription();
    expect(mockSdk.logOut).toHaveBeenCalledTimes(1);
  });
  it('Konfigurationsfehler werden verschluckt', async () => {
    mockSdk.configure.mockImplementationOnce(() => { throw new Error('native'); });
    const s = load();
    expect(await s.initSubscription(UID)).toBe(false);
  });
  it('getPlusStatus liest das Entitlement', async () => {
    mockSdk.getCustomerInfo.mockResolvedValue(active);
    const s = load();
    await s.initSubscription(UID);
    expect((await s.getPlusStatus()).isPlus).toBe(true);
    mockSdk.getCustomerInfo.mockRejectedValue(new Error('offline'));
    expect((await s.getPlusStatus()).isPlus).toBe(false);
  });
  it('Kauf: Erfolg liefert Status', async () => {
    mockSdk.purchasePackage.mockResolvedValue({ customerInfo: active });
    const s = load();
    await s.initSubscription(UID);
    const r = await s.buyPlus({} as any);
    expect(r.status).toBe('success');
    expect(r.status === 'success' && r.plus.isPlus).toBe(true);
  });
  it('Kauf: Abbruch durch den Nutzer ist kein Fehler', async () => {
    mockSdk.purchasePackage.mockRejectedValue({ userCancelled: true, code: '1', message: 'cancelled' });
    const s = load();
    await s.initSubscription(UID);
    expect(await s.buyPlus({} as any)).toEqual({ status: 'cancelled' });
  });
  it('Kauf: echter Fehler wird gemeldet, ohne SDK-Interna', async () => {
    mockSdk.purchasePackage.mockRejectedValue({ userCancelled: false, code: '2', message: 'StoreKit boom' });
    const s = load();
    await s.initSubscription(UID);
    const r = await s.buyPlus({} as any);
    expect(r.status).toBe('error');
    expect(JSON.stringify(r)).not.toContain('boom');
  });
  it('Wiederherstellen: ohne Abo ok, aber nicht aktiv', async () => {
    mockSdk.restorePurchases.mockResolvedValue({ entitlements: { active: {} } });
    const s = load();
    await s.initSubscription(UID);
    const r = await s.restorePlus();
    expect(r.status === 'success' && r.plus.isPlus).toBe(false);
  });
  it('Angebot: Intro nur bei Berechtigung', async () => {
    const monthly = {
      identifier: '$rc_monthly', packageType: 'MONTHLY',
      product: {
        identifier: 'piggy_plus_monthly', priceString: 'CHF 4.90', subscriptionPeriod: 'P1M',
        introPrice: { price: 0, priceString: 'CHF 0.00', cycles: 1, period: 'P1W', periodUnit: 'WEEK', periodNumberOfUnits: 1 },
      },
    };
    mockSdk.getOfferings.mockResolvedValue({ all: { default: { monthly } } });
    const s = load();
    await s.initSubscription(UID);
    mockSdk.checkTrialOrIntroductoryPriceEligibility.mockResolvedValue({ piggy_plus_monthly: { status: 2 } });
    expect((await s.loadMonthlyOffer())?.intro).toContain('kostenlos');
    mockSdk.checkTrialOrIntroductoryPriceEligibility.mockResolvedValue({ piggy_plus_monthly: { status: 0 } });
    expect((await s.loadMonthlyOffer())?.intro).toBeNull();
    mockSdk.checkTrialOrIntroductoryPriceEligibility.mockRejectedValue(new Error('x'));
    expect((await s.loadMonthlyOffer())?.intro).toBeNull();
  });
});
