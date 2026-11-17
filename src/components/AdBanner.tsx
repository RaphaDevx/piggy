import { useState } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { useAds } from '../contexts/AdsContext';
import { adSdk, bannerUnit } from '../lib/admob';
import { C } from '../constants/design';

/** Inline, separated from receipt actions; never an overlay over the scan button. */
export function AdBanner() {
  const { ready } = useAds();
  const [failed, setFailed] = useState(false);
  if (!ready || failed) return null;
  const unitId = bannerUnit();
  if (!unitId) return null;
  const { BannerAd, BannerAdSize } = adSdk();
  return <View style={styles.container}>
    <Text style={styles.label}>Werbung</Text>
    <BannerAd unitId={unitId} size={BannerAdSize.BANNER}
      requestOptions={{ requestNonPersonalizedAdsOnly: true }} onAdFailedToLoad={() => setFailed(true)} />
  </View>;
}
const styles = StyleSheet.create({
  container: { alignItems: 'center', minHeight: 78, gap: 6, paddingVertical: 8 },
  label: { fontSize: 13, color: C.textSecondary },
});
