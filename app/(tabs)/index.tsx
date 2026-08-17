import {
  View, Text, ScrollView, TouchableOpacity,
  StyleSheet, ActivityIndicator, RefreshControl,
} from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { useRouter } from 'expo-router';
import { Ionicons } from '@/components/Ionicons';
import ReceiptCard from '../../src/components/ReceiptCard';
import SpendingChart from '../../src/components/SpendingChart';
import { OpenBalances } from '../../src/components/OpenBalances';
import { useDashboard } from '../../src/hooks/useDashboard';
import { C, R, S, card } from '../../src/constants/design';
import type { Period } from '../../src/types/receipt';

const PERIODS: { key: Period; label: string }[] = [
  { key: 'week',    label: 'Woche'    },
  { key: 'month',   label: 'Monat'    },
  { key: '3months', label: '3 Mon.'   },
  { key: 'year',    label: 'Jahr'     },
];

const TAG_EMOJI: Record<string, string> = {
  'Lebensmittel': '🛒', 'Getränke': '🥤', 'Haushalt': '🏠',
  'Hygiene': '🧴', 'Gesundheit': '💊', 'Restaurant & Take-away': '🍜',
  'Kleidung': '👕', 'Elektronik': '💻', 'Diverses': '📦',
  'Backwaren': '🥐', 'Milchprodukte': '🥛', 'Fleisch & Fisch': '🥩',
};

function fmtCHF(amount: number, currency = 'CHF') {
  return `${currency} ${amount.toFixed(2)}`;
}

export default function DashboardScreen() {
  const insets = useSafeAreaInsets();
  const router = useRouter();
  const { period, offset, data, loading, refreshing, changePeriod, goPrev, goNext, refresh } = useDashboard();

  return (
    <ScrollView
      style={styles.root}
      contentContainerStyle={[styles.content, { paddingTop: insets.top + 16 }]}
      showsVerticalScrollIndicator={false}
      refreshControl={<RefreshControl refreshing={refreshing} onRefresh={refresh} tintColor={C.gold} />}
    >
      {/* Header */}
      <View style={styles.headerRow}>
        <View>
          <Text style={styles.greeting}>Hallo 👋</Text>
          <Text style={styles.title}>🐷 Piggy</Text>
        </View>
        <TouchableOpacity
          style={styles.analyseBtn}
          onPress={() => router.push('/analyse' as any)}
          activeOpacity={0.75}
        >
          <Ionicons name="bar-chart" size={18} color={C.gold} />
          <Text style={styles.analyseBtnText}>Analyse</Text>
        </TouchableOpacity>
      </View>

      {/* Period Selector */}
      <View style={styles.periodRow}>
        {PERIODS.map((p) => (
          <TouchableOpacity
            key={p.key}
            style={[styles.periodBtn, period === p.key && styles.periodBtnActive]}
            onPress={() => changePeriod(p.key)}
          >
            <Text style={[styles.periodText, period === p.key && styles.periodTextActive]}>
              {p.label}
            </Text>
          </TouchableOpacity>
        ))}
      </View>

      {/* Period Navigation: < KW 21 2026 > */}
      <View style={styles.navRow}>
        <TouchableOpacity onPress={goPrev} style={styles.navArrow}>
          <Ionicons name="chevron-back" size={20} color={C.textPrimary} />
        </TouchableOpacity>
        <Text style={styles.navLabel}>
          {loading && !data ? '…' : (data?.rangeLabel ?? '…')}
        </Text>
        <TouchableOpacity
          onPress={goNext}
          style={[styles.navArrow, offset >= 0 && styles.navArrowDisabled]}
          disabled={offset >= 0}
        >
          <Ionicons name="chevron-forward" size={20} color={offset >= 0 ? C.textTertiary : C.textPrimary} />
        </TouchableOpacity>
      </View>

      {/* Offene Beträge */}
      <OpenBalances />

      {loading ? (
        <View style={styles.loadingBox}>
          <ActivityIndicator size="large" color={C.gold} />
        </View>
      ) : !data ? null : data.recentReceipts.length === 0 ? (

        /* Empty State */
        <View style={styles.emptyState}>
          <Text style={styles.emptyPig}>🐷</Text>
          <Text style={styles.emptyTitle}>Keine Ausgaben</Text>
          <Text style={styles.emptyText}>
            {offset < 0
              ? `Keine Quittungen für ${data.rangeLabel}.`
              : 'Tippe auf 🐷 unten und scan deine erste Quittung!'}
          </Text>
        </View>

      ) : (
        <>
          {/* Total Card */}
          <View style={[styles.totalCard, card]}>
            <Text style={styles.totalLabel}>Gesamtausgaben</Text>
            <Text style={styles.totalAmount}>{fmtCHF(data.total, data.currency)}</Text>
            <View style={styles.totalSubRow}>
              <Text style={styles.totalSub}>Ø {fmtCHF(data.avgWeekly, data.currency)} / Woche</Text>
            </View>
          </View>

          {/* Chart */}
          {data.byTag.length > 0 && (
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Ausgaben nach Tag</Text>
              <View style={card}>
                <SpendingChart data={data.byTag} currency={data.currency} />
              </View>
            </View>
          )}

          {/* Category Breakdown — klickbar */}
          {data.byGroup.length > 0 && (
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Kategorien</Text>
              <View style={[card, styles.categoryCard]}>
                {data.byGroup.map(({ tag, total }, i) => {
                  const pct   = Math.min((total / data.total) * 100, 100);
                  const emoji = TAG_EMOJI[tag] ?? '📦';
                  return (
                    <TouchableOpacity
                      key={tag}
                      style={[styles.catRow, i > 0 && styles.catRowBorder]}
                      onPress={() => router.push({ pathname: '/category/[group]' as any, params: { group: tag } })}
                      activeOpacity={0.7}
                    >
                      <Text style={styles.catEmoji}>{emoji}</Text>
                      <View style={{ flex: 1, gap: 4 }}>
                        <View style={styles.catLabelRow}>
                          <Text style={styles.catLabel}>{tag}</Text>
                          <Text style={styles.catAmount}>{fmtCHF(total, data.currency)}</Text>
                        </View>
                        <View style={styles.barBg}>
                          <View style={[styles.barFill, { width: `${pct}%` }]} />
                        </View>
                      </View>
                      <Ionicons name="chevron-forward" size={14} color={C.textTertiary} style={{ marginLeft: 4 }} />
                    </TouchableOpacity>
                  );
                })}
              </View>
            </View>
          )}

          {/* Top Stores */}
          {data.topStores.length > 0 && (
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Top Geschäfte</Text>
              <View style={[card, { overflow: 'hidden' }]}>
                {data.topStores.map((store, i) => (
                  <View key={store.store_name} style={[styles.storeRow, i > 0 && styles.storeRowBorder]}>
                    <View style={styles.storeIcon}>
                      <Text style={styles.storeInitial}>{store.store_name[0]?.toUpperCase()}</Text>
                    </View>
                    <Text style={styles.storeName} numberOfLines={1}>{store.store_name}</Text>
                    <Text style={styles.storeCount}>{store.count}×</Text>
                    <Text style={styles.storeAmount}>{fmtCHF(store.total, data.currency)}</Text>
                  </View>
                ))}
              </View>
            </View>
          )}

          {/* Recent Receipts */}
          {data.recentReceipts.length > 0 && (
            <View style={styles.section}>
              <View style={styles.sectionHeader}>
                <Text style={styles.sectionTitle}>Zuletzt</Text>
                <TouchableOpacity onPress={() => router.push('/quittungen' as any)}>
                  <Text style={styles.seeAll}>Alle anzeigen →</Text>
                </TouchableOpacity>
              </View>
              {data.recentReceipts.map((r) => (
                <ReceiptCard
                  key={r.id}
                  receipt={r}
                  onPress={() => router.push({ pathname: '/receipt/[id]', params: { id: r.id } })}
                />
              ))}
            </View>
          )}
        </>
      )}

      <View style={{ height: 40 }} />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  root:    { flex: 1, backgroundColor: C.bg },
  content: { padding: 20, gap: 20 },

  headerRow: { flexDirection: 'row', justifyContent: 'space-between', alignItems: 'flex-start' },
  greeting:  { fontSize: S.sm, color: C.textSecondary, fontWeight: '600' },
  title:     { fontSize: S.xxl, fontWeight: '800', color: C.textPrimary, letterSpacing: -0.5, marginTop: 2 },
  analyseBtn: {
    flexDirection: 'row', alignItems: 'center', gap: 6,
    backgroundColor: C.bgAccent,
    borderRadius: R.lg, borderWidth: 1, borderColor: C.goldSoft,
    paddingHorizontal: 12, paddingVertical: 8, alignSelf: 'flex-start', marginTop: 4,
  },
  analyseBtnText: { fontSize: S.sm, fontWeight: '700', color: C.gold },

  periodRow: {
    flexDirection: 'row',
    backgroundColor: C.bgSoft,
    borderRadius: R.lg,
    padding: 4,
    gap: 2,
  },
  periodBtn:        { flex: 1, alignItems: 'center', paddingVertical: 8, borderRadius: R.md },
  periodBtnActive:  { backgroundColor: C.gold },
  periodText:       { color: C.textSecondary, fontSize: S.sm, fontWeight: '600' },
  periodTextActive: { color: '#fff', fontWeight: '700' },

  // Zeitraum-Navigation
  navRow: {
    flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between',
    backgroundColor: C.bgCard,
    borderRadius: R.md,
    paddingHorizontal: 4, paddingVertical: 4,
    borderWidth: 1, borderColor: C.border,
  },
  navArrow:         { padding: 10, borderRadius: R.sm },
  navArrowDisabled: { opacity: 0.3 },
  navLabel:         { fontSize: S.md, fontWeight: '700', color: C.textPrimary },

  loadingBox: { height: 200, alignItems: 'center', justifyContent: 'center' },

  totalCard: {
    padding: 28, alignItems: 'center', gap: 6,
    borderWidth: 1, borderColor: C.goldSoft,
  },
  totalLabel:  { fontSize: S.sm, color: C.textSecondary, fontWeight: '600', textTransform: 'uppercase', letterSpacing: 0.5 },
  totalAmount: { fontSize: S.hero, fontWeight: '800', color: C.textPrimary, letterSpacing: -2 },
  totalSubRow: { flexDirection: 'row', alignItems: 'center', gap: 6 },
  totalSub:    { fontSize: S.sm, color: C.textSecondary },

  section:       { gap: 10 },
  sectionHeader: { flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' },
  sectionTitle:  { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },
  seeAll:        { fontSize: S.sm, color: C.gold, fontWeight: '600' },

  categoryCard: { padding: 0, overflow: 'hidden' },
  catRow:       { flexDirection: 'row', alignItems: 'center', gap: 12, padding: 14 },
  catRowBorder: { borderTopWidth: 1, borderTopColor: C.borderSoft },
  catEmoji:     { fontSize: 20, width: 28, textAlign: 'center' },
  catLabelRow:  { flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' },
  catLabel:     { fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  catAmount:    { fontSize: S.sm, fontWeight: '700', color: C.textSecondary },
  barBg:        { height: 6, backgroundColor: C.bgSoft, borderRadius: 3, overflow: 'hidden' },
  barFill:      { height: '100%', backgroundColor: C.gold, borderRadius: 3 },

  storeRow:       { flexDirection: 'row', alignItems: 'center', gap: 12, padding: 14 },
  storeRowBorder: { borderTopWidth: 1, borderTopColor: C.borderSoft },
  storeIcon:      { width: 38, height: 38, borderRadius: R.sm, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  storeInitial:   { fontSize: S.md, fontWeight: '700', color: C.gold },
  storeName:      { flex: 1, fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  storeCount:     { fontSize: S.sm, color: C.textTertiary },
  storeAmount:    { fontSize: S.md, fontWeight: '700', color: C.gold, minWidth: 70, textAlign: 'right' },

  emptyState: { alignItems: 'center', paddingVertical: 60, gap: 12 },
  emptyPig:   { fontSize: 64 },
  emptyTitle: { fontSize: S.xl, fontWeight: '700', color: C.textPrimary },
  emptyText:  { fontSize: S.sm, color: C.textSecondary, textAlign: 'center', lineHeight: 20, maxWidth: 260 },
});
