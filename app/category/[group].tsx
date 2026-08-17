import {
  View, Text, FlatList, StyleSheet, ActivityIndicator,
  TouchableOpacity, RefreshControl,
} from 'react-native';
import { useLocalSearchParams, useRouter } from 'expo-router';
import { Ionicons } from '@/components/Ionicons';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { useCategoryItems } from '../../src/hooks/useCategoryItems';
import TagBadge from '../../src/components/TagBadge';
import { C, R, S, card } from '../../src/constants/design';
import { TAG_GROUPS } from '../../src/lib/categories';
import type { CategoryItem } from '../../src/hooks/useCategoryItems';

const GROUP_EMOJI: Record<string, string> = {
  'Lebensmittel': '🛒', 'Getränke': '🥤', 'Haushalt': '🏠',
  'Hygiene':      '🧴', 'Gesundheit': '💊', 'Diverses': '📦',
};

function fmtDate(dateStr: string | null) {
  if (!dateStr) return '—';
  return new Date(dateStr).toLocaleDateString('de-CH', {
    day: '2-digit', month: '2-digit', year: 'numeric',
  });
}

type ReceiptGroup = {
  receiptId: string;
  receipt:   CategoryItem['receipt'];
  items:     CategoryItem[];
};

export default function CategoryScreen() {
  const { group } = useLocalSearchParams<{ group: string }>();
  const router    = useRouter();
  const insets    = useSafeAreaInsets();

  const { items, loading, refreshing, refresh } = useCategoryItems(group ?? '');

  const groupColor = TAG_GROUPS[group ?? ''] ?? C.gold;
  const emoji      = GROUP_EMOJI[group ?? ''] ?? '📦';

  // Items nach Quittung gruppieren
  const receiptGroupMap: Record<string, ReceiptGroup> = {};
  for (const item of items) {
    if (!receiptGroupMap[item.receipt_id]) {
      receiptGroupMap[item.receipt_id] = { receiptId: item.receipt_id, receipt: item.receipt, items: [] };
    }
    receiptGroupMap[item.receipt_id].items.push(item);
  }
  const receiptGroups = Object.values(receiptGroupMap);

  const totalSpent = items.reduce((sum, i) => sum + (i.total_price ?? 0), 0);
  const currency   = items[0]?.receipt.currency ?? 'CHF';

  return (
    <View style={[styles.root, { paddingTop: insets.top }]}>

      {/* Header */}
      <View style={styles.header}>
        <TouchableOpacity onPress={() => router.back()} style={styles.backBtn}>
          <Ionicons name="chevron-back" size={24} color={C.textPrimary} />
        </TouchableOpacity>
        <View style={styles.headerCenter}>
          <Text style={styles.headerEmoji}>{emoji}</Text>
          <Text style={styles.headerText}>{group}</Text>
        </View>
        <View style={{ width: 40 }} />
      </View>

      {loading ? (
        <View style={styles.center}>
          <ActivityIndicator size="large" color={C.gold} />
        </View>
      ) : (
        <FlatList
          data={receiptGroups}
          keyExtractor={(g) => g.receiptId}
          contentContainerStyle={styles.listContent}
          showsVerticalScrollIndicator={false}
          refreshControl={<RefreshControl refreshing={refreshing} onRefresh={refresh} tintColor={C.gold} />}
          ListHeaderComponent={
            items.length > 0 ? (
              <View style={[styles.summaryCard, card]}>
                <Text style={styles.summaryLabel}>Gesamtausgaben in {group}</Text>
                <Text style={[styles.summaryAmount, { color: groupColor }]}>
                  {currency} {totalSpent.toFixed(2)}
                </Text>
                <Text style={styles.summaryMeta}>
                  {items.length} {items.length === 1 ? 'Artikel' : 'Artikel'} · {receiptGroups.length}{' '}
                  {receiptGroups.length === 1 ? 'Einkauf' : 'Einkäufe'}
                </Text>
              </View>
            ) : null
          }
          ListEmptyComponent={
            <View style={styles.empty}>
              <Text style={styles.emptyEmoji}>{emoji}</Text>
              <Text style={styles.emptyTitle}>Keine Artikel</Text>
              <Text style={styles.emptyText}>Noch keine Ausgaben in der Kategorie {group}.</Text>
            </View>
          }
          renderItem={({ item: rg }) => {
            const receiptTotal = rg.items.reduce((s, i) => s + (i.total_price ?? 0), 0);
            return (
              <TouchableOpacity
                style={[card, styles.receiptCard]}
                onPress={() => router.push({ pathname: '/receipt/[id]', params: { id: rg.receiptId } })}
                activeOpacity={0.75}
              >
                <View style={styles.receiptHeader}>
                  <View style={{ flex: 1 }}>
                    <Text style={styles.storeName} numberOfLines={1}>{rg.receipt.store_name}</Text>
                    <Text style={styles.receiptDate}>{fmtDate(rg.receipt.receipt_date)}</Text>
                  </View>
                  <View style={styles.receiptAmountWrap}>
                    <Text style={[styles.receiptTotal, { color: groupColor }]}>
                      {rg.receipt.currency} {receiptTotal.toFixed(2)}
                    </Text>
                    <Ionicons name="chevron-forward" size={14} color={C.textTertiary} />
                  </View>
                </View>
                <View style={styles.bankBadge}>
                  <Ionicons
                    name={rg.receipt.bankMatch ? 'checkmark-circle' : 'alert-circle-outline'}
                    size={14}
                    color={rg.receipt.bankMatch ? C.success : C.textTertiary}
                  />
                  <Text style={[styles.bankBadgeText, rg.receipt.bankMatch ? styles.bankBadgeTextOk : styles.bankBadgeTextMissing]}>
                    {rg.receipt.bankMatch
                      ? `Bankbeleg · ${fmtDate(rg.receipt.bankMatch.booking_date)} · ${rg.receipt.bankMatch.currency} ${Math.abs(rg.receipt.bankMatch.amount).toFixed(2)}`
                      : 'Kein Bankbeleg'}
                  </Text>
                </View>
                <View style={styles.divider} />
                {rg.items.map((item, i) => (
                  <View key={item.id} style={[styles.itemRow, i > 0 && styles.itemBorder]}>
                    <View style={{ flex: 1 }}>
                      <Text style={styles.itemName}>{item.name}</Text>
                      {item.tags.length > 0 && (
                        <View style={styles.tagsRow}>
                          {item.tags.map((tag) => <TagBadge key={tag} tag={tag} small />)}
                        </View>
                      )}
                    </View>
                    {item.quantity != null && item.quantity !== 1 && (
                      <Text style={styles.itemQty}>{item.quantity}×</Text>
                    )}
                    <Text style={styles.itemPrice}>
                      {item.total_price != null
                        ? `${rg.receipt.currency} ${item.total_price.toFixed(2)}`
                        : '—'}
                    </Text>
                  </View>
                ))}
              </TouchableOpacity>
            );
          }}
        />
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  root:   { flex: 1, backgroundColor: C.bg },
  center: { flex: 1, alignItems: 'center', justifyContent: 'center' },

  header: {
    flexDirection: 'row', alignItems: 'center',
    paddingHorizontal: 16, paddingVertical: 12,
    borderBottomWidth: 1, borderBottomColor: C.border,
    backgroundColor: C.bgCard,
  },
  backBtn:      { width: 40, alignItems: 'flex-start' },
  headerCenter: { flex: 1, flexDirection: 'row', alignItems: 'center', justifyContent: 'center', gap: 8 },
  headerEmoji:  { fontSize: 22 },
  headerText:   { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },

  listContent: { padding: 16, gap: 12, paddingBottom: 40 },

  summaryCard:   { padding: 24, alignItems: 'center', gap: 4, marginBottom: 4 },
  summaryLabel:  { fontSize: S.xs, color: C.textSecondary, fontWeight: '600', textTransform: 'uppercase', letterSpacing: 0.5 },
  summaryAmount: { fontSize: 36, fontWeight: '800', letterSpacing: -1, marginTop: 4 },
  summaryMeta:   { fontSize: S.sm, color: C.textTertiary, marginTop: 2 },

  receiptCard:       { padding: 0, overflow: 'hidden' },
  receiptHeader:     { flexDirection: 'row', alignItems: 'flex-start', padding: 14, gap: 12 },
  storeName:         { fontSize: S.md, fontWeight: '700', color: C.textPrimary },
  receiptDate:       { fontSize: S.xs, color: C.textTertiary, marginTop: 2 },
  receiptAmountWrap: { flexDirection: 'row', alignItems: 'center', gap: 4 },
  receiptTotal:      { fontSize: S.md, fontWeight: '700' },
  divider:           { height: 1, backgroundColor: C.borderSoft },

  bankBadge:         { flexDirection: 'row', alignItems: 'center', gap: 6, paddingHorizontal: 14, paddingBottom: 10 },
  bankBadgeText:     { fontSize: S.xs, fontWeight: '600' },
  bankBadgeTextOk:      { color: C.success },
  bankBadgeTextMissing: { color: C.textTertiary },

  itemRow:    { flexDirection: 'row', alignItems: 'flex-start', gap: 10, paddingHorizontal: 14, paddingVertical: 10 },
  itemBorder: { borderTopWidth: 1, borderTopColor: C.borderSoft },
  itemName:   { fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  tagsRow:    { flexDirection: 'row', flexWrap: 'wrap', gap: 4, marginTop: 4 },
  itemQty:    { fontSize: S.xs, color: C.textTertiary, paddingTop: 2 },
  itemPrice:  { fontSize: S.sm, fontWeight: '700', color: C.textSecondary, minWidth: 72, textAlign: 'right' },

  empty:      { alignItems: 'center', paddingVertical: 70, gap: 12 },
  emptyEmoji: { fontSize: 56 },
  emptyTitle: { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },
  emptyText:  { fontSize: S.sm, color: C.textSecondary, textAlign: 'center', maxWidth: 240, lineHeight: 20 },
});
