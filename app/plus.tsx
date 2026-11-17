import { useCallback, useEffect, useState } from 'react';
import { View, Text, TouchableOpacity, StyleSheet, ActivityIndicator, ScrollView, Linking, Alert } from 'react-native';
import { Redirect, useRouter } from 'expo-router';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { Ionicons } from '../src/components/Ionicons';
import { PiggyIcon } from '../src/brand/icons/PiggyIcon';
import { C, R, S, card } from '../src/constants/design';
import { subscriptionAvailable } from '../src/constants/features';
import { PRIVACY_URL, TERMS_URL } from '../src/lib/legalLinks';
import { buyPlus, restorePlus, loadGoldOffers, MANAGE_SUBSCRIPTIONS_URL, type PlusOffer } from '../src/lib/subscription';
import { usePlus } from '../src/hooks/usePlus';

function GoldScreen() {
  const insets = useSafeAreaInsets();
  const router = useRouter();
  const { status, refresh, setStatus } = usePlus();
  const [offers, setOffers] = useState<PlusOffer[]>([]);
  const [selected, setSelected] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);
  const [busy, setBusy] = useState(false);
  const offer = offers.find(o => o.pkg.identifier === selected) ?? offers[0];
  const load = useCallback(async () => {
    setLoading(true);
    const next = await loadGoldOffers();
    setOffers(next);
    setLoading(false);
  }, []);
  useEffect(() => { void load(); }, [load]);

  async function subscribe() {
    if (!offer || busy) return;
    setBusy(true);
    const result = await buyPlus(offer.pkg);
    if (result.status === 'success') {
      setStatus(result.plus);
      if (!result.plus.isPlus) Alert.alert('Abo wird geprüft', 'Der Store hat den Vorgang abgeschlossen. Sobald dein Abo bestätigt ist, wird Piggy Gold aktiv.');
    } else if (result.status === 'error') Alert.alert('Kauf nicht abgeschlossen', result.message);
    await refresh();
    setBusy(false);
  }
  async function restore() {
    if (busy) return;
    setBusy(true);
    const result = await restorePlus();
    if (result.status === 'success') {
      setStatus(result.plus);
      Alert.alert(result.plus.isPlus ? 'Käufe wiederhergestellt' : 'Kein Abo gefunden',
        result.plus.isPlus ? 'Piggy Gold ist wieder aktiv.' : 'Für diese Apple-ID wurde kein aktives Piggy-Gold-Abo gefunden.');
    } else if (result.status === 'error') Alert.alert('Wiederherstellen nicht möglich', result.message);
    await refresh();
    setBusy(false);
  }
  return <View style={[styles.root, { paddingTop: insets.top }]}>
    <View style={styles.topBar}>
      <TouchableOpacity style={{ minWidth: 44, minHeight: 44, alignItems: 'center', justifyContent: 'center' }}
        onPress={() => router.back()} accessibilityRole="button" accessibilityLabel="Schliessen">
        <Ionicons name="close" size={26} color={C.textPrimary} />
      </TouchableOpacity>
    </View>
    <ScrollView contentContainerStyle={[styles.content, { paddingBottom: insets.bottom + 24 }]}>
      <View style={{ alignItems: 'center', gap: 12 }}>
        <PiggyIcon name="piggy" size={72} color={C.gold} />
        <Text style={styles.title} accessibilityRole="header">Piggy Gold</Text>
        <Text style={styles.body}>Mehr Raum für deine Quittungen. Ohne Werbung.</Text>
      </View>
      <View style={[card, styles.features]}>
        {['Keine Werbeanzeigen in Piggy', 'Höheres Tageslimit für KI-Scans', 'Gold-Logo in der App'].map(text =>
          <View key={text} style={styles.featureRow}>
            <Ionicons name="checkmark-circle-outline" size={22} color={C.textPrimary} />
            <Text style={styles.featureText}>{text}</Text>
          </View>)}
        <Text style={styles.body}>Die übrigen Funktionen bleiben kostenlos. Die KI-Auswertung über Anthropic (USA) braucht deine Zustimmung. Ohne sie arbeitet Piggy mit lokaler Erkennung; das höhere KI-Limit gilt dann nicht.</Text>
      </View>
      {status.isPlus ? <View style={[card, styles.box]}>
        <Text style={styles.activeTitle}>Piggy Gold ist aktiv</Text>
        {status.expiresAt && <Text style={styles.body}>
          {status.willRenew ? 'Verlängert sich am ' : 'Läuft bis '}
          {status.expiresAt.toLocaleDateString('de-CH')}{status.willRenew ? '.' : ', danach endet das Abo.'}
        </Text>}
        <TouchableOpacity style={styles.secondaryBtn} accessibilityRole="button" onPress={() => void Linking.openURL(MANAGE_SUBSCRIPTIONS_URL)}>
          <Text style={styles.secondaryBtnText}>Abo verwalten</Text>
        </TouchableOpacity>
      </View> : loading ? <ActivityIndicator accessibilityLabel="Angebote werden geladen" /> : offer ? <View style={[card, styles.box]}>
        {offers.map(o => <TouchableOpacity key={o.pkg.identifier} disabled={busy} accessibilityRole="radio"
          accessibilityState={{ selected: offer.pkg.identifier === o.pkg.identifier, disabled: busy }}
          accessibilityLabel={o.renewalPrice} onPress={() => setSelected(o.pkg.identifier)}
          style={[styles.secondaryBtn, { paddingHorizontal: 16, backgroundColor: offer.pkg.identifier === o.pkg.identifier ? C.goldSoft : C.bgCard }]}>
          <Text style={styles.secondaryBtnText}>{o.pkg.packageType === 'ANNUAL' ? 'Jährlich' : 'Monatlich'}</Text>
          <Text style={styles.body}>{o.renewalPrice}</Text>
        </TouchableOpacity>)}
        {offer.intro && <Text style={styles.intro}>{offer.intro}</Text>}
        <TouchableOpacity style={[styles.cta, busy && { opacity: 0.6 }]} disabled={busy} accessibilityRole="button"
          accessibilityState={{ disabled: busy }} onPress={() => void subscribe()}>
          {busy ? <ActivityIndicator color={C.textPrimary} /> : <Text style={styles.ctaText}>Piggy Gold abonnieren</Text>}
        </TouchableOpacity>
        <Text style={styles.fine}>Abo Piggy Gold, Laufzeit {offer.periodText}, {offer.renewalPrice}. Verlängert sich automatisch, sofern du es nicht spätestens 24 Stunden vor Ablauf in deinen Apple-Abos kündigst. Es gelten die im Kaufdialog angezeigten Bedingungen.</Text>
      </View> : <View style={[card, styles.box]}>
        <Text style={styles.body}>Das Angebot ist gerade nicht verfügbar. Du kannst Piggy kostenlos weiterverwenden.</Text>
        <TouchableOpacity style={styles.secondaryBtn} accessibilityRole="button" onPress={() => void load()}>
          <Text style={styles.secondaryBtnText}>Erneut laden</Text>
        </TouchableOpacity>
      </View>}
      <TouchableOpacity style={styles.linkBtn} disabled={busy} accessibilityRole="button" onPress={() => void restore()}>
        <Text style={styles.linkText}>Käufe wiederherstellen</Text>
      </TouchableOpacity>
      <View style={[styles.legalRow, { flexWrap: 'wrap' }]}>
        <TouchableOpacity style={{ minHeight: 44, justifyContent: 'center' }} accessibilityRole="link" onPress={() => void Linking.openURL(TERMS_URL)}><Text style={styles.legalLink}>Nutzungsbedingungen</Text></TouchableOpacity>
        <TouchableOpacity style={{ minHeight: 44, justifyContent: 'center' }} accessibilityRole="link" onPress={() => void Linking.openURL(PRIVACY_URL)}><Text style={styles.legalLink}>Datenschutz</Text></TouchableOpacity>
      </View>
    </ScrollView>
  </View>;
}
/** Route stays /plus for compatibility with existing deep links. */
export default function PlusRoute() {
  if (!subscriptionAvailable()) return <Redirect href="/(tabs)" />;
  return <GoldScreen />;
}

const styles = StyleSheet.create({
  root:        { flex: 1, backgroundColor: C.bg },
  topBar:      { paddingHorizontal: 20, paddingVertical: 12, alignItems: 'flex-end' },
  content:     { paddingHorizontal: 20, gap: 16 },
  title:       { fontSize: S.xxl, fontWeight: '800', color: C.textPrimary },
  features:    { padding: 16, gap: 12 },
  featureRow:  { flexDirection: 'row', alignItems: 'center', gap: 12 },
  featureText: { flex: 1, fontSize: S.lg, color: C.textPrimary },
  box:         { padding: 20, gap: 8, alignItems: 'stretch' },
  price:       { fontSize: S.hero, fontWeight: '800', color: C.textPrimary, textAlign: 'center' },
  period:      { fontSize: S.lg, color: C.textSecondary, textAlign: 'center' },
  intro:       { fontSize: S.lg, color: C.textPrimary, textAlign: 'center', fontWeight: '600', marginTop: 4 },
  cta:         { marginTop: 12, backgroundColor: C.gold, borderRadius: R.md, paddingVertical: 16, alignItems: 'center' },
  ctaText:     { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },
  fine:        { fontSize: S.sm, color: C.textSecondary, textAlign: 'center', marginTop: 8 },
  activeTitle: { fontSize: S.xl, fontWeight: '700', color: C.textPrimary, textAlign: 'center' },
  body:        { fontSize: S.lg, color: C.textSecondary, textAlign: 'center' },
  secondaryBtn:{ marginTop: 12, borderRadius: R.md, borderWidth: 1, borderColor: C.border, paddingVertical: 14, alignItems: 'center' },
  secondaryBtnText: { fontSize: S.lg, fontWeight: '600', color: C.textPrimary },
  linkBtn:     { paddingVertical: 12, alignItems: 'center' },
  linkText:    { fontSize: S.lg, fontWeight: '600', color: C.textPrimary, textDecorationLine: 'underline' },
  legalRow:    { flexDirection: 'row', justifyContent: 'center', alignItems: 'center', gap: 12 },
  legalLink:   { fontSize: S.sm, color: C.textSecondary, textDecorationLine: 'underline', marginTop: 8 },
});
