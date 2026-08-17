import { View, Text, TouchableOpacity, StyleSheet, ActivityIndicator } from 'react-native';
import { useRouter } from 'expo-router';
import { Ionicons } from '@/components/Ionicons';
import { useBalances, settleParticipant } from '../hooks/useSplits';
import { C, R, S, card } from '../constants/design';
import { Alert } from 'react-native';

function fmt(n: number, currency = 'CHF') {
  return `${currency} ${n.toFixed(2)}`;
}

export function OpenBalances() {
  const router = useRouter();
  const { balances, loading, reload } = useBalances();

  const { iOwe, owedToMe } = balances;
  if (loading) return <ActivityIndicator color={C.gold} style={{ marginVertical: 8 }} />;
  if (iOwe.length === 0 && owedToMe.length === 0) return null;

  const totalIOwe   = iOwe.reduce((s, x) => s + x.participant.amount, 0);
  const totalOwedMe = owedToMe.reduce((s, x) => s + x.participant.amount, 0);

  async function settle(participantId: string, name: string, amount: number, currency: string) {
    Alert.alert('Beglichen?', `${name} hat ${fmt(amount, currency)} bezahlt?`, [
      { text: 'Abbrechen', style: 'cancel' },
      {
        text: 'Ja', onPress: async () => {
          await settleParticipant(participantId);
          reload();
        },
      },
    ]);
  }

  return (
    <View style={styles.wrap}>
      {/* I owe others */}
      {iOwe.length > 0 && (
        <View style={[card, styles.section]}>
          <View style={styles.sectionHeader}>
            <View style={[styles.dot, { backgroundColor: C.error }]} />
            <Text style={styles.sectionTitle}>Du schuldest</Text>
            <Text style={[styles.sectionTotal, { color: C.error }]}>{fmt(totalIOwe)}</Text>
          </View>
          {iOwe.map(({ participant, split }) => {
            const payer = split.payer_profile;
            const name  = payer?.display_name ?? payer?.piggy_code ?? '…';
            const store = split.receipt?.store_name ?? '—';
            return (
              <TouchableOpacity
                key={participant.id}
                style={styles.balanceRow}
                onPress={() => router.push(`/split/${split.receipt_id}` as any)}
              >
                <View style={styles.avatar}>
                  <Text style={styles.avatarText}>{name.charAt(0).toUpperCase()}</Text>
                </View>
                <View style={{ flex: 1 }}>
                  <Text style={styles.balanceName}>{name}</Text>
                  <Text style={styles.balanceSub}>{store}</Text>
                </View>
                <Text style={[styles.balanceAmount, { color: C.error }]}>
                  –{fmt(participant.amount, split.currency)}
                </Text>
              </TouchableOpacity>
            );
          })}
        </View>
      )}

      {/* Others owe me */}
      {owedToMe.length > 0 && (
        <View style={[card, styles.section]}>
          <View style={styles.sectionHeader}>
            <View style={[styles.dot, { backgroundColor: C.success }]} />
            <Text style={styles.sectionTitle}>Du bekommst</Text>
            <Text style={[styles.sectionTotal, { color: C.success }]}>{fmt(totalOwedMe)}</Text>
          </View>
          {owedToMe.map(({ participant, split }) => {
            const profile = participant.profile;
            const name    = profile?.display_name ?? profile?.piggy_code ?? '…';
            const store   = split.receipt?.store_name ?? '—';
            return (
              <View key={participant.id} style={styles.balanceRow}>
                <View style={styles.avatar}>
                  <Text style={styles.avatarText}>{name.charAt(0).toUpperCase()}</Text>
                </View>
                <View style={{ flex: 1 }}>
                  <Text style={styles.balanceName}>{name}</Text>
                  <Text style={styles.balanceSub}>{store}</Text>
                </View>
                <TouchableOpacity
                  style={styles.settleBtn}
                  onPress={() => settle(participant.id, name, participant.amount, split.currency)}
                >
                  <Text style={styles.settleBtnText}>Beglichen</Text>
                </TouchableOpacity>
                <Text style={[styles.balanceAmount, { color: C.success }]}>
                  +{fmt(participant.amount, split.currency)}
                </Text>
              </View>
            );
          })}
        </View>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  wrap: { gap: 10 },

  section:       { padding: 0, overflow: 'hidden' },
  sectionHeader: { flexDirection: 'row', alignItems: 'center', gap: 8, paddingHorizontal: 16, paddingVertical: 12, borderBottomWidth: 1, borderBottomColor: C.borderSoft },
  sectionTitle:  { flex: 1, fontSize: S.sm, fontWeight: '700', color: C.textPrimary },
  sectionTotal:  { fontSize: S.sm, fontWeight: '800' },
  dot:           { width: 8, height: 8, borderRadius: 4 },

  balanceRow:   { flexDirection: 'row', alignItems: 'center', gap: 12, paddingHorizontal: 16, paddingVertical: 12, borderBottomWidth: 1, borderBottomColor: C.borderSoft },
  avatar:       { width: 36, height: 36, borderRadius: 18, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  avatarText:   { fontSize: S.sm, fontWeight: '700', color: C.gold },
  balanceName:  { fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  balanceSub:   { fontSize: S.xs, color: C.textTertiary, marginTop: 1 },
  balanceAmount:{ fontSize: S.sm, fontWeight: '800', minWidth: 80, textAlign: 'right' },

  settleBtn:     { paddingHorizontal: 10, paddingVertical: 5, backgroundColor: C.bgAccent, borderRadius: R.sm, borderWidth: 1, borderColor: C.border, marginRight: 4 },
  settleBtnText: { fontSize: S.xs, fontWeight: '700', color: C.textPrimary },
});
