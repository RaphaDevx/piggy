import { Text, View, TouchableOpacity, StyleSheet } from 'react-native';
import { useAds } from '../contexts/AdsContext';
import { useSubscription } from '../contexts/SubscriptionContext';
import { C, R } from '../constants/design';

export function AdPrivacyCard({ settings = false }: { settings?: boolean }) {
  const ads = useAds();
  const subscription = useSubscription();
  if (!ads.configured || !subscription.resolved || subscription.status.isPlus || (!settings && ads.choice !== 'unknown')) return null;
  return <View style={styles.card}>
    <Text style={styles.title} accessibilityRole="header">Werbung in Piggy</Text>
    <Text style={styles.body}>Werbung hilft, die kostenlose Version zu finanzieren. Google AdMob erhält technische Werbedaten, aber keine Inhalte deiner Quittungen. Du kannst deine Wahl hier jederzeit ändern.</Text>
    {settings && <Text style={styles.body}>{ads.choice === 'allowed' ? 'Werbung erlaubt. Welche Anzeigen möglich sind, hängt zusätzlich von deiner Datenschutzwahl ab.' : 'Werbung ist nicht aktiviert.'}</Text>}
    {ads.choice !== 'allowed' ? <TouchableOpacity accessibilityRole="button" disabled={ads.busy} style={styles.button} onPress={() => void ads.allow()}>
      <Text style={styles.buttonText}>Werbung aktivieren</Text>
    </TouchableOpacity> : <TouchableOpacity accessibilityRole="button" disabled={ads.busy} style={styles.button} onPress={() => void ads.privacyOptions()}>
      <Text style={styles.buttonText}>Datenschutzwahl verwalten</Text>
    </TouchableOpacity>}
    {ads.choice !== 'declined' && <TouchableOpacity accessibilityRole="button" style={styles.button} onPress={() => void ads.decline()}>
      <Text style={styles.buttonText}>{settings ? 'Werbung deaktivieren' : 'Jetzt nicht'}</Text>
    </TouchableOpacity>}
  </View>;
}
const styles = StyleSheet.create({
  card: { padding: 16, borderRadius: R.md, backgroundColor: C.bgSoft, gap: 10 },
  title: { fontSize: 17, fontWeight: '700', color: C.textPrimary },
  body: { fontSize: 15, lineHeight: 22, color: C.textPrimary },
  button: { minHeight: 44, justifyContent: 'center', alignItems: 'center', padding: 10, borderRadius: R.sm, borderWidth: 1, borderColor: C.textSecondary },
  buttonText: { fontSize: 17, fontWeight: '600', color: C.textPrimary, textAlign: 'center' },
});
