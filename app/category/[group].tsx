import { useMemo, useState } from 'react';
import {
  View, Text, ScrollView, StyleSheet, ActivityIndicator,
  TouchableOpacity, RefreshControl,
} from 'react-native';
import { useLocalSearchParams, useRouter } from 'expo-router';
import { Ionicons } from '@/components/Ionicons';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { useCategoryItems } from '../../src/hooks/useCategoryItems';
import { useLocale } from '../../src/hooks/useLocale';
import { C, R, S, card } from '../../src/constants/design';
import { TAG_GROUPS } from '../../src/lib/categories';
import {
  DEFAULT_GROUP_OPTIONS, categoryLabel, groupAdaptive, natureLabel, natureOf, productKey, productLabel,
  subcategoryLabel, type GroupLevel, type GroupNode, type Locale, type Nature,
} from '../../src/lib/taxonomy';
import type { CategoryItem } from '../../src/hooks/useCategoryItems';

const GROUP_EMOJI: Record<string, string> = {
  'Lebensmittel': '🛒', 'Getränke': '🥤', 'Haushalt': '🏠', 'Gesundheit': '💊',
  'Einrichtung': '🛋️', 'Freizeit & Shopping': '🛍️', 'Mobilität': '🚆',
  'Restaurant & Take-away': '🍜', 'Diverses': '📦',
};

const NO_SUBCATEGORY = '__none__';
const MS_PER_DAY = 86_400_000;

type RangeKey = '30d' | '3m' | '12m' | 'all';
const RANGES: Array<{ key: RangeKey; label: string; days: number | null }> = [
  { key: '30d', label: '30 Tage',   days: 30 },
  { key: '3m',  label: '3 Monate',  days: 91 },
  { key: '12m', label: '12 Monate', days: 365 },
  { key: 'all', label: 'Alles',     days: null },
];

const NATURE_COLORS: Record<Nature, string> = { essential: C.success, treat: C.gold, occasional: C.warning };
const NATURES: Nature[] = ['essential', 'treat', 'occasional'];

function fmtDate(dateStr: string | null) {
  if (!dateStr) return '—';
  return new Date(dateStr).toLocaleDateString('de-CH', {
    day: '2-digit', month: '2-digit', year: 'numeric',
  });
}

const price = (item: CategoryItem) => item.total_price ?? 0;

type ReceiptGroup = {
  receiptId: string;
  receipt:   CategoryItem['receipt'];
  items:     CategoryItem[];
};

/** Artikel nach Quittung gruppieren (Reihenfolge der Artikel bleibt: neueste zuerst). */
function groupByReceipt(items: CategoryItem[]): ReceiptGroup[] {
  const map = new Map<string, ReceiptGroup>();
  for (const item of items) {
    const g = map.get(item.receipt_id) ?? { receiptId: item.receipt_id, receipt: item.receipt, items: [] };
    g.items.push(item);
    map.set(item.receipt_id, g);
  }
  return [...map.values()];
}

function ReceiptList({ items, groupColor, locale, showSub }: {
  items: CategoryItem[]; groupColor: string; locale: Locale; showSub: boolean;
}) {
  const router = useRouter();
  return (
    <View style={{ gap: 12 }}>
      {groupByReceipt(items).map((rg) => {
        const receiptTotal = rg.items.reduce((s, i) => s + price(i), 0);
        return (
          <TouchableOpacity
            key={rg.receiptId}
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
                  <Text style={[styles.itemName, item.is_adjustment && styles.itemAdjustment]}>
                    {item.name}{item.is_adjustment ? ' · Ausgleich' : ''}
                  </Text>
                  {showSub && item.subcategory && (
                    <Text style={styles.itemSub}>{subcategoryLabel(item.subcategory, locale)}</Text>
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
      })}
    </View>
  );
}

function Section({ node, path, currency, expanded, toggle, groupColor, locale, depth }: {
  node: GroupNode<CategoryItem>; path: string; currency: string;
  expanded: Record<string, boolean>; toggle: (path: string) => void;
  groupColor: string; locale: Locale; depth: number;
}) {
  const open = !!expanded[path];
  return (
    <View style={[card, styles.section, depth > 0 && styles.sectionNested]}>
      <TouchableOpacity style={styles.sectionHeader} onPress={() => toggle(path)} activeOpacity={0.7}>
        <View style={{ flex: 1 }}>
          <Text style={styles.sectionLabel} numberOfLines={1}>{node.label}</Text>
          <Text style={styles.sectionCount}>×{node.count}</Text>
        </View>
        <Text style={[styles.sectionTotal, { color: groupColor }]}>{currency} {node.total.toFixed(2)}</Text>
        <Ionicons name={open ? 'close' : 'chevron-forward'} size={14} color={C.textTertiary} />
      </TouchableOpacity>
      {open && (
        <View style={styles.sectionBody}>
          {node.children ? node.children.map((child) => (
            <Section
              key={child.key} node={child} path={`${path}/${child.key}`} currency={currency}
              expanded={expanded} toggle={toggle} groupColor={groupColor} locale={locale} depth={depth + 1}
            />
          )) : (
            <ReceiptList items={node.items ?? []} groupColor={groupColor} locale={locale} showSub={false} />
          )}
        </View>
      )}
    </View>
  );
}

export default function CategoryScreen() {
  const { group = '' } = useLocalSearchParams<{ group: string }>();
  const router    = useRouter();
  const insets    = useSafeAreaInsets();
  const locale    = useLocale();

  const { items: allItems, loading, refreshing, refresh } = useCategoryItems(group);
  const [range, setRange] = useState<RangeKey>('3m');
  const [expanded, setExpanded] = useState<Record<string, boolean>>({});

  const groupColor = TAG_GROUPS[group] ?? C.gold;
  const emoji      = GROUP_EMOJI[group] ?? '📦';
  const title      = categoryLabel(group, locale);

  const items = useMemo(() => {
    const days = RANGES.find((r) => r.key === range)?.days ?? null;
    if (days == null) return allItems;
    const cutoff = new Date(Date.now() - days * MS_PER_DAY).toISOString().slice(0, 10);
    return allItems.filter((i) => (i.receipt.receipt_date ?? '') >= cutoff);
  }, [allItems, range]);

  const tree = useMemo(() => {
    const levels: GroupLevel<CategoryItem>[] = [
      {
        key: (i) => i.subcategory ?? NO_SUBCATEGORY,
        label: (k) => (k === NO_SUBCATEGORY ? categoryLabel('Nicht kategorisiert', locale) : subcategoryLabel(k, locale)),
      },
      { key: (i) => productKey(i.name), label: productLabel },
    ];
    return groupAdaptive(items, levels, price, { ...DEFAULT_GROUP_OPTIONS, otherLabel: categoryLabel('Weitere', locale) });
  }, [items, locale]);

  const natureTotals = useMemo(() => {
    const totals: Record<Nature, number> = { essential: 0, treat: 0, occasional: 0 };
    for (const i of items) totals[natureOf(i.subcategory, group)] += price(i);
    return totals;
  }, [items, group]);

  const totalSpent = items.reduce((sum, i) => sum + price(i), 0);
  const currency   = allItems[0]?.receipt.currency ?? 'CHF';
  const receiptCount = new Set(items.map((i) => i.receipt_id)).size;
  const toggle = (path: string) => setExpanded((p) => ({ ...p, [path]: !p[path] }));

  return (
    <View style={[styles.root, { paddingTop: insets.top }]}>

      {/* Header */}
      <View style={styles.header}>
        <TouchableOpacity onPress={() => router.back()} style={styles.backBtn}>
          <Ionicons name="chevron-back" size={24} color={C.textPrimary} />
        </TouchableOpacity>
        <View style={styles.headerCenter}>
          <Text style={styles.headerEmoji}>{emoji}</Text>
          <Text style={styles.headerText}>{title}</Text>
        </View>
        <View style={{ width: 40 }} />
      </View>

      {loading ? (
        <View style={styles.center}>
          <ActivityIndicator size="large" color={C.gold} />
        </View>
      ) : (
        <ScrollView
          contentContainerStyle={styles.listContent}
          showsVerticalScrollIndicator={false}
          refreshControl={<RefreshControl refreshing={refreshing} onRefresh={refresh} tintColor={C.gold} />}
        >
          <View style={styles.chipRow}>
            {RANGES.map((r) => (
              <TouchableOpacity
                key={r.key}
                style={[styles.chip, range === r.key && styles.chipActive]}
                onPress={() => setRange(r.key)}
              >
                <Text style={[styles.chipText, range === r.key && styles.chipTextActive]}>{r.label}</Text>
              </TouchableOpacity>
            ))}
          </View>

          {items.length > 0 ? (
            <>
              <View style={[styles.summaryCard, card]}>
                <Text style={styles.summaryLabel}>Gesamtausgaben in {title}</Text>
                <Text style={[styles.summaryAmount, { color: groupColor }]}>
                  {currency} {totalSpent.toFixed(2)}
                </Text>
                <Text style={styles.summaryMeta}>
                  {items.length} Artikel · {receiptCount} {receiptCount === 1 ? 'Einkauf' : 'Einkäufe'}
                </Text>
                <View style={styles.natureWrap}>
                  {NATURES.map((n) => (
                    <View key={n} style={styles.natureRow}>
                      <View style={[styles.natureDot, { backgroundColor: NATURE_COLORS[n] }]} />
                      <Text style={styles.natureLabel} numberOfLines={1}>{natureLabel(n, locale)}</Text>
                      <Text style={styles.natureAmount}>{currency} {natureTotals[n].toFixed(2)}</Text>
                    </View>
                  ))}
                </View>
              </View>

              {tree.children ? tree.children.map((child) => (
                <Section
                  key={child.key} node={child} path={child.key} currency={currency}
                  expanded={expanded} toggle={toggle} groupColor={groupColor} locale={locale} depth={0}
                />
              )) : (
                <ReceiptList items={tree.items ?? []} groupColor={groupColor} locale={locale} showSub />
              )}
            </>
          ) : (
            <View style={styles.empty}>
              <Text style={styles.emptyEmoji}>{emoji}</Text>
              <Text style={styles.emptyTitle}>Keine Artikel</Text>
              <Text style={styles.emptyText}>
                {allItems.length > 0
                  ? `Im gewählten Zeitraum keine Ausgaben in ${title}.`
                  : `Noch keine Ausgaben in der Kategorie ${title}.`}
              </Text>
            </View>
          )}
        </ScrollView>
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

  chipRow:        { flexDirection: 'row', flexWrap: 'wrap', gap: 8 },
  chip:           { paddingVertical: 8, paddingHorizontal: 14, borderRadius: R.md, backgroundColor: C.bgSoft },
  chipActive:     { backgroundColor: C.gold },
  chipText:       { fontSize: S.sm, fontWeight: '600', color: C.textSecondary },
  chipTextActive: { color: '#fff', fontWeight: '700' },

  natureWrap:   { alignSelf: 'stretch', gap: 6, marginTop: 14, paddingTop: 12, borderTopWidth: 1, borderTopColor: C.borderSoft },
  natureRow:    { flexDirection: 'row', alignItems: 'center', gap: 8 },
  natureDot:    { width: 8, height: 8, borderRadius: 4 },
  natureLabel:  { flex: 1, fontSize: S.xs, color: C.textSecondary, fontWeight: '600' },
  natureAmount: { fontSize: S.xs, color: C.textPrimary, fontWeight: '700' },

  section:       { padding: 0, overflow: 'hidden' },
  sectionNested: { backgroundColor: C.bgSoft },
  sectionHeader: { flexDirection: 'row', alignItems: 'center', gap: 10, padding: 14 },
  sectionLabel:  { fontSize: S.md, fontWeight: '700', color: C.textPrimary },
  sectionCount:  { fontSize: S.xs, color: C.textTertiary, marginTop: 2 },
  sectionTotal:  { fontSize: S.sm, fontWeight: '700' },
  sectionBody:   { padding: 12, paddingTop: 0, gap: 8 },


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
  itemAdjustment: { fontStyle: 'italic', color: C.textSecondary, fontWeight: '400' },
  itemSub:    { fontSize: S.xs, color: C.textTertiary, marginTop: 2 },
  itemQty:    { fontSize: S.xs, color: C.textTertiary, paddingTop: 2 },
  itemPrice:  { fontSize: S.sm, fontWeight: '700', color: C.textSecondary, minWidth: 72, textAlign: 'right' },

  empty:      { alignItems: 'center', paddingVertical: 70, gap: 12 },
  emptyEmoji: { fontSize: 56 },
  emptyTitle: { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },
  emptyText:  { fontSize: S.sm, color: C.textSecondary, textAlign: 'center', maxWidth: 240, lineHeight: 20 },
});
