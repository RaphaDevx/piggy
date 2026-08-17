import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import TagBadge from './TagBadge';
import { C, R, S, card } from '../constants/design';
import type { Receipt } from '../types/receipt';

interface Props {
  receipt: Receipt & { allTags?: string[] };
  onPress: () => void;
}

const STORE_COLORS = ['#FF9F0A', '#34C759', '#007AFF', '#FF6B9D', '#AF52DE', '#FF3B30'];

function storeColor(name: string) {
  return STORE_COLORS[name.charCodeAt(0) % STORE_COLORS.length];
}

export default function ReceiptCard({ receipt, onPress }: Props) {
  const date = receipt.receipt_date
    ? new Date(receipt.receipt_date).toLocaleDateString('de-CH', { day: '2-digit', month: 'short' })
    : '—';
  const amount = receipt.total_amount != null
    ? `${receipt.currency} ${receipt.total_amount.toFixed(2)}`
    : '—';
  const initial = receipt.store_name?.[0]?.toUpperCase() ?? '?';
  const color   = storeColor(receipt.store_name ?? '');

  return (
    <TouchableOpacity style={[styles.card, card]} onPress={onPress} activeOpacity={0.75}>
      <View style={styles.top}>
        <View style={[styles.storeIcon, { backgroundColor: `${color}18` }]}>
          <Text style={[styles.initial, { color }]}>{initial}</Text>
        </View>
        <View style={{ flex: 1 }}>
          <Text style={styles.storeName} numberOfLines={1}>{receipt.store_name}</Text>
          <Text style={styles.meta}>{date} · {receipt.store_category ?? '—'}</Text>
        </View>
        <View style={styles.amountCol}>
          <Text style={styles.amount}>{amount}</Text>
          {receipt.payment_method && (
            <Text style={styles.payment}>{receipt.payment_method}</Text>
          )}
        </View>
      </View>

      {receipt.allTags && receipt.allTags.length > 0 && (
        <View style={styles.tagsRow}>
          {receipt.allTags.slice(0, 4).map((tag) => (
            <TagBadge key={tag} tag={tag} small />
          ))}
          {receipt.allTags.length > 4 && (
            <Text style={styles.moreTags}>+{receipt.allTags.length - 4}</Text>
          )}
        </View>
      )}
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  card:     { padding: 16, gap: 10 },
  top:      { flexDirection: 'row', alignItems: 'center', gap: 12 },
  storeIcon:{ width: 44, height: 44, borderRadius: R.md, alignItems: 'center', justifyContent: 'center' },
  initial:  { fontSize: S.lg, fontWeight: '800' },
  storeName:{ color: C.textPrimary, fontSize: S.md, fontWeight: '700' },
  meta:     { color: C.textTertiary, fontSize: S.xs, marginTop: 2 },
  amountCol:{ alignItems: 'flex-end' },
  amount:   { color: C.textPrimary, fontSize: S.lg, fontWeight: '800' },
  payment:  { color: C.textTertiary, fontSize: S.xs, marginTop: 2 },
  tagsRow:  { flexDirection: 'row', flexWrap: 'wrap', gap: 6 },
  moreTags: { color: C.textTertiary, fontSize: S.xs, alignSelf: 'center' },
});
