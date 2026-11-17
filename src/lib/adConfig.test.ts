const buildConfig = require('../../app.config');
const keys = ['EXPO_PUBLIC_ADS_ENABLED', 'EXPO_PUBLIC_ADS_REVIEW_APPROVED', 'EXPO_PUBLIC_ADMOB_IOS_APP_ID',
  'EXPO_PUBLIC_ADMOB_IOS_INTERSTITIAL_ID', 'EXPO_PUBLIC_ADMOB_IOS_BANNER_ID', 'EXPO_PUBLIC_ADMOB_BANNERS_ENABLED',
  'EXPO_PUBLIC_REVENUECAT_IOS_KEY', 'EAS_BUILD_PROFILE'];
const previous = Object.fromEntries(keys.map(key => [key, process.env[key]]));
afterAll(() => keys.forEach(key => { if (previous[key] === undefined) delete process.env[key]; else process.env[key] = previous[key]; }));
beforeEach(() => keys.forEach(key => delete process.env[key]));
const base = { name: 'Piggy', ios: { infoPlist: { Existing: true } }, plugins: ['expo-router'] };
test('disabled ads retain valid native setup and delayed measurement', () => {
  const config = buildConfig({ config: base });
  expect(config.ios.infoPlist).toMatchObject({ Existing: true, GADDelayAppMeasurementInit: true });
  expect(config.plugins[1][1].iosAppId).toContain('~');
  expect(config.plugins[1][1].delayAppMeasurementInit).toBe(true);
});
test('cannot accidentally ship enabled ads without review, IDs and Gold status', () => {
  process.env.EXPO_PUBLIC_ADS_ENABLED = 'true';
  expect(() => buildConfig({ config: base })).toThrow('review');
  process.env.EXPO_PUBLIC_ADS_REVIEW_APPROVED = 'true';
  expect(() => buildConfig({ config: base })).toThrow('IDs');
  process.env.EXPO_PUBLIC_ADMOB_IOS_APP_ID = 'ca-app-pub-1234567890123456~1234567890';
  process.env.EXPO_PUBLIC_ADMOB_IOS_INTERSTITIAL_ID = 'ca-app-pub-1234567890123456/1234567890';
  expect(() => buildConfig({ config: base })).toThrow('Gold');
  process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY = 'appl_example';
  expect(() => buildConfig({ config: base })).not.toThrow();
});
test('Google sample IDs are rejected for production monetisation', () => {
  Object.assign(process.env, { EXPO_PUBLIC_ADS_ENABLED: 'true', EXPO_PUBLIC_ADS_REVIEW_APPROVED: 'true',
    EXPO_PUBLIC_ADMOB_IOS_APP_ID: 'ca-app-pub-3940256099942544~1458002511',
    EXPO_PUBLIC_ADMOB_IOS_INTERSTITIAL_ID: 'ca-app-pub-3940256099942544/4411468910',
    EXPO_PUBLIC_REVENUECAT_IOS_KEY: 'appl_example', EAS_BUILD_PROFILE: 'production' });
  expect(() => buildConfig({ config: base })).toThrow('sample IDs');
});
