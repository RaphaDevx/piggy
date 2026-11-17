import { Platform } from 'react-native';

type AdSdk = typeof import('react-native-google-mobile-ads');
let sdk: AdSdk | null = null;
/** Lazy: no native module access while ads are disabled or before an explicit choice. */
export function adSdk(): AdSdk {
  if (!sdk) sdk = require('react-native-google-mobile-ads') as AdSdk;
  return sdk;
}
export function adsConfigured(): boolean {
  return Platform.OS === 'ios' && process.env.EXPO_PUBLIC_ADS_ENABLED === 'true' &&
    process.env.EXPO_PUBLIC_ADS_REVIEW_APPROVED === 'true' &&
    !!process.env.EXPO_PUBLIC_ADMOB_IOS_INTERSTITIAL_ID?.trim();
}
export function interstitialUnit(): string {
  return __DEV__ ? adSdk().TestIds.INTERSTITIAL : process.env.EXPO_PUBLIC_ADMOB_IOS_INTERSTITIAL_ID!.trim();
}
export function bannerUnit(): string | null {
  if (process.env.EXPO_PUBLIC_ADMOB_BANNERS_ENABLED !== 'true') return null;
  return __DEV__ ? adSdk().TestIds.BANNER : process.env.EXPO_PUBLIC_ADMOB_IOS_BANNER_ID?.trim() || null;
}
