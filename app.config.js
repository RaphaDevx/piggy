// Native AdMob settings. Real ads require both rollout flags and production IDs.
const IOS_TEST_APP = 'ca-app-pub-3940256099942544~1458002511';
const ANDROID_TEST_APP = 'ca-app-pub-3940256099942544~3347511713';

module.exports = ({ config }) => {
  const enabled = process.env.EXPO_PUBLIC_ADS_ENABLED === 'true';
  const approved = process.env.EXPO_PUBLIC_ADS_REVIEW_APPROVED === 'true';
  const appId = process.env.EXPO_PUBLIC_ADMOB_IOS_APP_ID?.trim();
  const unitId = process.env.EXPO_PUBLIC_ADMOB_IOS_INTERSTITIAL_ID?.trim();
  const isProduction = process.env.EAS_BUILD_PROFILE === 'production' || process.env.NODE_ENV === 'production';
  if (enabled) {
    if (!approved) throw new Error('Ads rollout requires the documented privacy/store review (EXPO_PUBLIC_ADS_REVIEW_APPROVED).');
    if (!/^ca-app-pub-\d{16}~\d{10}$/.test(appId || '') || !/^ca-app-pub-\d{16}\/\d{10}$/.test(unitId || '')) {
      throw new Error('Ads enabled: valid iOS AdMob app and interstitial unit IDs are required.');
    }
    if (isProduction && [appId, unitId, process.env.EXPO_PUBLIC_ADMOB_BANNERS_ENABLED === 'true' ? process.env.EXPO_PUBLIC_ADMOB_IOS_BANNER_ID : null].some(id => id?.startsWith('ca-app-pub-3940256099942544'))) {
      throw new Error('Production ads cannot use Google sample IDs.');
    }
    if (!process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY?.trim()) throw new Error('Ads require working Gold subscription status checks.');
    if (process.env.EXPO_PUBLIC_ADMOB_BANNERS_ENABLED === 'true' && !/^ca-app-pub-\d{16}\/\d{10}$/.test(process.env.EXPO_PUBLIC_ADMOB_IOS_BANNER_ID || '')) {
      throw new Error('Banner ads enabled: a valid iOS banner unit ID is required.');
    }
  }
  return {
    ...config,
    ios: { ...config.ios, infoPlist: { ...config.ios?.infoPlist,
      GADDelayAppMeasurementInit: true,
    } },
    plugins: [...(config.plugins || []), ['react-native-google-mobile-ads', {
      iosAppId: appId || IOS_TEST_APP,
      androidAppId: ANDROID_TEST_APP,
      delayAppMeasurementInit: true,
    }]],
  };
};
