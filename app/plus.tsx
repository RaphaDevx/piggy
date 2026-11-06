import { useCallback, useEffect, useState } from 'react';
import {
  View, Text, TouchableOpacity, StyleSheet, ActivityIndicator, ScrollView, Linking, Alert,
} from 'react-native';
import { Redirect, useRouter } from 'expo-router';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { Ionicons } from '../src/components/Ionicons';
import { C, R, S, card } from '../src/constants/design';
import { subscriptionAvailable } from '../src/constants/features';
import { PRIVACY_URL, TERMS_URL } from '../src/lib/legalLinks';
import {
  buyPlus, restorePlus, loadMonthlyOffer, getPlusStatus, MANAGE_SUBSCRIPTIONS_URL,
  type PlusOffer, type PlusStatus,
} from '../src/lib/subscription';
import { NO_PLUS } from '../src/lib/subscriptionLogic';

function formatDate(d: Date | null): string {
  return d ? d.toLocaleDateString('de-CH', { day: 'numeric', month: 'long', year: 'numeric' }) : '';
}

function PlusScreen() {
  const insets = useSafeAreaInsets();
  const router = useRouter();
  const [offer, setOffer]     = useState<PlusOffer | null>(null);
  const [status, setStatus]   = useState<PlusStatus>(NO_PLUS);
  const [loading, setLoading] = useState(true);
  const [busy, setBusy]       = useState(false);

  const load = useCallback(async () => {
    const [o, s] = await Promise.all([loadMonthlyOffer(), getPlusStatus()]);
    setOffer(o);
    setStatus(s);
    setLoading(false);
  }, []);

  useEffect(() => { load(); }, [load]);

  async function subscribe() {
    if (!offer || busy) return;
    setBusy(true);
    const res = await buyPlus(offer.pkg);
    setBusy(false);
    if (res.status === 'success') setStatus(res.plus);   // Abbruch ist kein Fehler: nichts anzeigen
    else if (res.status === 'error') Alert.alert('Kauf nicht abgeschlossen', res.message);
  }

  async function restore() {
    if (busy) return;
    setBusy(true);
    const res = await restorePlus();
    setBusy(false);
    if (res.status === 'success') {
      setStatus(res.plus);
      Alert.alert(
        res.plus.isPlus ? 'Käufe wiederhergestellt' : 'Kein Abo gefunden',
        res.plus.isPlus ? 'Piggy Plus ist wieder aktiv.' : 'Für diese Apple-ID wurde kein aktives Piggy Plus gefunden.',
      );
    } else if (res.status === 'error') {
      Alert.alert('Wiederherstellen nicht möglich', res.message);
    }
  }

  return (
    <View style={[styles.root, { paddingTop: insets.top }]}>
      <View style={styles.topBar}>
        <TouchableOpacity
          onPress={() => router.back()}
          accessibilityRole="button"
          accessibilityLabel="Schliessen"
          hitSlop={12}
        >
          <Ionicons name="close" size={26} color={C.textPrimary} />
        </TouchableOpacity>
      </View>

      <ScrollView contentContainerStyle={[styles.content, { paddingBottom: insets.bottom + 24 }]}>
        <Text style={styles.title} accessibilityRole="header">Piggy Plus</Text>

        <View style={[card, styles.features]}>
          <View style={styles.featureRow}>
            <Ionicons name="sparkles-outline" size={20} color={C.textPrimary} />
            <Text style={styles.featureText}>Höheres Tageslimit für KI-Scans als in der kostenlosen Version</Text>
          </View>
          <View style={styles.featureRow}>
            <Ionicons name="shield-checkmark-outline" size={20} color={C.textPrimary} />
            <Text style={styles.featureText}>Die KI-Auswertung läuft über Anthropic (USA) und braucht deine Zustimmung. Ohne Zustimmung erkennt Piggy Quittungen auf dem Gerät, das höhere Limit gilt dann nicht.</Text>
          </View>
          <View style={styles.featureRow}>
            <Ionicons name="checkmark-circle-outline" size={20} color={C.textPrimary} />
            <Text style={styles.featureText}>Alle übrigen Funktionen von Piggy bleiben kostenlos</Text>
          </View>
        </View>

        {loading ? (
          <ActivityIndicator style={{ marginTop: 32 }} color={C.gold} />
        ) : status.isPlus ? (
          <View style={[card, styles.box]}>
            <Text style={styles.activeTitle}>Piggy Plus ist aktiv</Text>
            {status.expiresAt && (
              <Text style={styles.body}>
                {status.willRenew ? 'Verlängert sich am ' : 'Läuft bis '}{formatDate(status.expiresAt)}
                {status.willRenew ? '.' : ', danach endet das Abo.'}
              </Text>
            )}
            <TouchableOpacity style={styles.secondaryBtn} onPress={() => Linking.openURL(MANAGE_SUBSCRIPTIONS_URL)}>
              <Text style={styles.secondaryBtnText}>Abo verwalten</Text>
            </TouchableOpacity>
          </View>
        ) : offer ? (
          <View style={[card, styles.box]}>
            <Text style={styles.price} accessibilityLabel={`${offer.priceString} pro ${offer.periodText}`}>
              {offer.priceString}
            </Text>
            <Text style={styles.period}>pro {offer.periodText.replace(/^1 /, '')}</Text>
            {offer.intro && <Text style={styles.intro}>{offer.intro}</Text>}

            <TouchableOpacity
              style={[styles.cta, busy && { opacity: 0.6 }]}
              onPress={subscribe}
              disabled={busy}
              accessibilityRole="button"
            >
              {busy
                ? <ActivityIndicator color={C.textPrimary} />
                : <Text style={styles.ctaText}>Piggy Plus abonnieren</Text>}
            </TouchableOpacity>

            <Text style={styles.fine}>
              Abo «Piggy Plus», Laufzeit {offer.periodText}, {offer.renewalPrice}. Verlängert sich automatisch,
              bis du in den Einstellungen deiner Apple-ID kündigst. Kündigen musst du mindestens 24 Stunden vor
              Ende des laufenden Zeitraums.
            </Text>
          </View>
        ) : (
          <View style={[card, styles.box]}>
            <Text style={styles.body}>Das Angebot ist gerade nicht verfügbar. Bitte versuche es später erneut.</Text>
          </View>
        )}

        <TouchableOpacity style={styles.linkBtn} onPress={restore} disabled={busy} accessibilityRole="button">
          <Text style={styles.linkText}>Käufe wiederherstellen</Text>
        </TouchableOpacity>

        {!status.isPlus && !loading && (
          <TouchableOpacity style={styles.linkBtn} onPress={() => Linking.openURL(MANAGE_SUBSCRIPTIONS_URL)} accessibilityRole="link">
            <Text style={styles.linkText}>Abo verwalten</Text>
          </TouchableOpacity>
        )}

        <View style={styles.legalRow}>
          <TouchableOpacity onPress={() => Linking.openURL(TERMS_URL)} hitSlop={8} accessibilityRole="link">
            <Text style={styles.legalLink}>Nutzungsbedingungen</Text>
          </TouchableOpacity>
          <Text style={styles.fine}>·</Text>
          <TouchableOpacity onPress={() => Linking.openURL(PRIVACY_URL)} hitSlop={8} accessibilityRole="link">
            <Text style={styles.legalLink}>Datenschutz</Text>
          </TouchableOpacity>
        </View>
      </ScrollView>
    </View>
  );
}

/** Ohne verfügbares Abo-Feature (Schalter aus oder kein Schlüssel) gibt es diese Seite nicht. */
export default function PlusRoute() {
  if (!subscriptionAvailable()) return <Redirect href="/(tabs)" />;
  return <PlusScreen />;
}

// Kontrast: Fliesstext C.textPrimary / C.textSecondary (>= 4.5:1 auf C.bg), C.gold nur als Fläche mit dunklem Text.
const styles = StyleSheet.create({
  root:        { flex: 1, backgroundColor: C.bg },
  topBar:      { paddingHorizontal: 20, paddingVertical: 12, alignItems: 'flex-end' },
  content:     { paddingHorizontal: 20, gap: 16 },
  title:       { fontSize: S.xxl, fontWeight: '800', color: C.textPrimary },
  features:    { padding: 16, gap: 12 },
  featureRow:  { flexDirection: 'row', alignItems: 'center', gap: 12 },
  featureText: { flex: 1, fontSize: S.md, color: C.textPrimary },
  box:         { padding: 20, gap: 8, alignItems: 'stretch' },
  price:       { fontSize: S.hero, fontWeight: '800', color: C.textPrimary, textAlign: 'center' },
  period:      { fontSize: S.lg, color: C.textSecondary, textAlign: 'center' },
  intro:       { fontSize: S.md, color: C.textPrimary, textAlign: 'center', fontWeight: '600', marginTop: 4 },
  cta:         { marginTop: 12, backgroundColor: C.gold, borderRadius: R.md, paddingVertical: 16, alignItems: 'center' },
  ctaText:     { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },
  fine:        { fontSize: S.sm, color: C.textSecondary, textAlign: 'center', marginTop: 8 },
  activeTitle: { fontSize: S.xl, fontWeight: '700', color: C.textPrimary, textAlign: 'center' },
  body:        { fontSize: S.md, color: C.textSecondary, textAlign: 'center' },
  secondaryBtn:{ marginTop: 12, borderRadius: R.md, borderWidth: 1, borderColor: C.border, paddingVertical: 14, alignItems: 'center' },
  secondaryBtnText: { fontSize: S.md, fontWeight: '600', color: C.textPrimary },
  linkBtn:     { paddingVertical: 12, alignItems: 'center' },
  linkText:    { fontSize: S.md, fontWeight: '600', color: C.textPrimary, textDecorationLine: 'underline' },
  legalRow:    { flexDirection: 'row', justifyContent: 'center', alignItems: 'center', gap: 12 },
  legalLink:   { fontSize: S.sm, color: C.textSecondary, textDecorationLine: 'underline', marginTop: 8 },
});
