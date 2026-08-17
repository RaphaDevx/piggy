import { View, Text, TouchableOpacity, StyleSheet, ActivityIndicator } from 'react-native';
import { useRouter } from 'expo-router';
import { Ionicons } from '@/components/Ionicons';
import { useIncomingSharedReceipts } from '../hooks/useSharedReceipts';
import { C, R, S, card } from '../constants/design';

function fmt(n: number, currency = 'CHF') {
  return `${currency} ${n.toFixed(2)}`;
}

export function IncomingSharedReceipts() {
  const router = useRouter();
  const { items, loading, accept, decline } = useIncomingSharedReceipts();

  if (loading) return <ActivityIndicator color={C.gold} style={{ marginVertical: 12 }} />;
  if (items.length === 0) return null;

  return (
    <View style={styles.wrap}>
      <View style={styles.sectionHeader}>
        <Ionicons name="paper-plane-outline" size={16} color={C.gold} />
        <Text style={styles.sectionTitle}>Eingegangene Quittungen</Text>
        <View style={styles.badge}><Text style={styles.badgeText}>{items.length}</Text></View>
      </View>

      {items.map((item) => {
        const sender = item.sender_profile;
        const senderName = sender?.display_name ?? sender?.piggy_code ?? '…';
        const store = item.receipt?.store_name ?? '—';
        const total = item.receipt ? fmt(item.receipt.total_amount, item.receipt.currency) : '—';

        return (
          <View key={item.id} style={[card, styles.itemCard]}>
            <View style={styles.itemHeader}>
              <View style={styles.avatar}>
                <Text style={styles.avatarText}>{senderName.charAt(0).toUpperCase()}</Text>
              </View>
              <View style={{ flex: 1 }}>
                <Text style={styles.fromText}>Von <Text style={{ color: C.gold }}>{senderName}</Text></Text>
                <Text style={styles.storeText}>{store} · {total}</Text>
              </View>
              <Ionicons name="receipt-outline" size={20} color={C.textTertiary} />
            </View>

            <View style={styles.actions}>
              <TouchableOpacity
                style={styles.declineBtn}
                onPress={() => decline(item.id)}
              >
                <Text style={styles.declineBtnText}>Ablehnen</Text>
              </TouchableOpacity>
              <TouchableOpacity
                style={styles.acceptBtn}
                onPress={async () => {
                  await accept(item.id);
                  router.push(`/split/${item.receipt_id}` as any);
                }}
              >
                <Ionicons name="checkmark" size={16} color="#fff" />
                <Text style={styles.acceptBtnText}>Annehmen & Splitten</Text>
              </TouchableOpacity>
            </View>
          </View>
        );
      })}
    </View>
  );
}

const styles = StyleSheet.create({
  wrap: { gap: 10 },

  sectionHeader: { flexDirection: 'row', alignItems: 'center', gap: 8 },
  sectionTitle:  { flex: 1, fontSize: S.sm, fontWeight: '700', color: C.textPrimary },
  badge:         { backgroundColor: C.gold, borderRadius: 10, paddingHorizontal: 7, paddingVertical: 2 },
  badgeText:     { fontSize: 11, fontWeight: '700', color: '#fff' },

  itemCard:   { padding: 14, gap: 12 },
  itemHeader: { flexDirection: 'row', alignItems: 'center', gap: 12 },
  avatar:     { width: 40, height: 40, borderRadius: 20, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  avatarText: { fontSize: S.md, fontWeight: '700', color: C.gold },
  fromText:   { fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  storeText:  { fontSize: S.xs, color: C.textTertiary, marginTop: 2 },

  actions:      { flexDirection: 'row', gap: 8 },
  declineBtn:   { flex: 1, paddingVertical: 10, alignItems: 'center', backgroundColor: C.bgAccent, borderRadius: R.sm, borderWidth: 1, borderColor: C.border },
  declineBtnText: { fontSize: S.xs, fontWeight: '700', color: C.textSecondary },
  acceptBtn:    { flex: 2, flexDirection: 'row', alignItems: 'center', justifyContent: 'center', gap: 6, paddingVertical: 10, backgroundColor: C.gold, borderRadius: R.sm },
  acceptBtnText:{ fontSize: S.xs, fontWeight: '700', color: '#fff' },
});
