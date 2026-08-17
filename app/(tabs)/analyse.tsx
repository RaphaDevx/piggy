import { useState } from 'react';
import {
  View, Text, ScrollView, TouchableOpacity,
  StyleSheet, ActivityIndicator, RefreshControl,
} from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { useRouter } from 'expo-router';
import { Ionicons } from '@/components/Ionicons';
import SpendingChart from '../../src/components/SpendingChart';
import AbgleichView from '../../src/components/AbgleichView';
import { useDashboard } from '../../src/hooks/useDashboard';
import { C, R, S, card } from '../../src/constants/design';
import { TAG_GROUPS } from '../../src/lib/categories';
import type { Period } from '../../src/types/receipt';

const PERIODS: { key: Period; label: string }[] = [
  { key: 'week',    label: 'Woche'  },
  { key: 'month',   label: 'Monat'  },
  { key: '3months', label: '3 Mon.' },
  { key: 'year',    label: 'Jahr'   },
];

const GROUP_EMOJI: Record<string, string> = {
  'Lebensmittel': '🛒', 'Getränke': '🥤', 'Haushalt': '🏠',
  'Hygiene': '🧴', 'Gesundheit': '💊', 'Diverses': '📦',
};

function fmtCHF(n: number, cur = 'CHF') { return `${cur} ${n.toFixed(2)}`; }
function pct(n: number, total: number)   { return total > 0 ? Math.min((n / total) * 100, 100) : 0; }

export default function AnalyseScreen() {
  const insets = useSafeAreaInsets();
  const router = useRouter();
  const [view, setView] = useState<'overview' | 'abgleich'>('overview');
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
        <TouchableOpacity onPress={() => router.back()} style={styles.backBtn} activeOpacity={0.7}>
          <Ionicons name="chevron-back" size={22} color={C.textPrimary} />
        </TouchableOpacity>
        <Text style={styles.title}>📊 Analyse</Text>
      </View>

      {/* Übersicht / Abgleich Switch */}
      <View style={styles.periodRow}>
        <TouchableOpacity
          style={[styles.periodBtn, view === 'overview' && styles.periodBtnActive]}
          onPress={() => setView('overview')}
        >
          <Text style={[styles.periodText, view === 'overview' && styles.periodTextActive]}>Übersicht</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={[styles.periodBtn, view === 'abgleich' && styles.periodBtnActive]}
          onPress={() => setView('abgleich')}
        >
          <Text style={[styles.periodText, view === 'abgleich' && styles.periodTextActive]}>Abgleich</Text>
        </TouchableOpacity>
      </View>

      {view === 'abgleich' ? (
        <AbgleichView />
      ) : (
        <>
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

      {/* Period Navigation */}
      <View style={styles.navRow}>
        <TouchableOpacity onPress={goPrev} style={styles.navArrow}>
          <Ionicons name="chevron-back" size={20} color={C.textPrimary} />
        </TouchableOpacity>
        <Text style={styles.navLabel}>{loading && !data ? '…' : (data?.rangeLabel ?? '…')}</Text>
        <TouchableOpacity
          onPress={goNext}
          style={[styles.navArrow, offset >= 0 && styles.navArrowDisabled]}
          disabled={offset >= 0}
        >
          <Ionicons name="chevron-forward" size={20} color={offset >= 0 ? C.textTertiary : C.textPrimary} />
        </TouchableOpacity>
      </View>

      {loading ? (
        <View style={styles.loadingBox}>
          <ActivityIndicator size="large" color={C.gold} />
        </View>

      ) : !data || data.byGroup.length === 0 ? (
        <View style={styles.emptyState}>
          <Text style={styles.emptyPig}>📊</Text>
          <Text style={styles.emptyTitle}>Keine Daten</Text>
          <Text style={styles.emptyText}>
            {offset < 0
              ? `Keine Ausgaben für ${data?.rangeLabel ?? 'diesen Zeitraum'}.`
              : 'Scan deine erste Quittung mit 🐷!'}
          </Text>
        </View>

      ) : (
        <>
          {/* Gesamt */}
          <View style={[styles.totalCard, card]}>
            <Text style={styles.totalLabel}>Gesamtausgaben</Text>
            <Text style={styles.totalAmount}>{fmtCHF(data.total, data.currency)}</Text>
            <Text style={styles.totalSub}>Ø {fmtCHF(data.avgWeekly, data.currency)} / Woche</Text>
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

          {/* Kategorien — klickbar */}
          <View style={styles.section}>
            <Text style={styles.sectionTitle}>Kategorien</Text>
            <View style={[card, styles.catCard]}>
              {data.byGroup.map(({ tag, total }, i) => {
                const p     = pct(total, data.total);
                const emoji = GROUP_EMOJI[tag] ?? '📦';
                const color = TAG_GROUPS[tag] ?? C.gold;
                return (
                  <TouchableOpacity
                    key={tag}
                    style={[styles.catRow, i > 0 && styles.catBorder]}
                    onPress={() => router.push({ pathname: '/category/[group]' as any, params: { group: tag } })}
                    activeOpacity={0.7}
                  >
                    <View style={[styles.emojiBox, { backgroundColor: `${color}18` }]}>
                      <Text style={styles.catEmoji}>{emoji}</Text>
                    </View>
                    <View style={{ flex: 1, gap: 6 }}>
                      <View style={styles.catLabelRow}>
                        <Text style={styles.catLabel}>{tag}</Text>
                        <Text style={styles.catPct}>{p.toFixed(0)} %</Text>
                        <Text style={styles.catAmount}>{fmtCHF(total, data.currency)}</Text>
                      </View>
                      <View style={styles.barBg}>
                        <View style={[styles.barFill, { width: `${p}%`, backgroundColor: color }]} />
                      </View>
                    </View>
                    <Ionicons name="chevron-forward" size={14} color={C.textTertiary} style={{ marginLeft: 4 }} />
                  </TouchableOpacity>
                );
              })}
            </View>
          </View>

          {/* Top Geschäfte */}
          {data.topStores.length > 0 && (
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Top Geschäfte</Text>
              <View style={[card, { overflow: 'hidden' }]}>
                {data.topStores.map((store, i) => (
                  <View key={store.store_name} style={[styles.storeRow, i > 0 && styles.storeBorder]}>
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

          {/* Einzelne Tags */}
          {data.byTag.length > 0 && (
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Alle Tags</Text>
              <View style={[card, { padding: 0, overflow: 'hidden' }]}>
                {data.byTag.slice(0, 12).map(({ tag, total }, i) => (
                  <View key={tag} style={[styles.tagRow, i > 0 && styles.catBorder]}>
                    <Text style={styles.tagName}>{tag}</Text>
                    <Text style={styles.tagAmount}>{fmtCHF(total, data.currency)}</Text>
                    <Text style={styles.tagPct}>{pct(total, data.total).toFixed(0)} %</Text>
                  </View>
                ))}
              </View>
            </View>
          )}
        </>
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

  headerRow: { flexDirection: 'row', alignItems: 'center', gap: 8 },
  backBtn:   { padding: 4, marginLeft: -4 },
  title:     { fontSize: S.xxl, fontWeight: '800', color: C.textPrimary, letterSpacing: -0.5 },

  periodRow: { flexDirection: 'row', backgroundColor: C.bgSoft, borderRadius: R.lg, padding: 4, gap: 2 },
  periodBtn:        { flex: 1, alignItems: 'center', paddingVertical: 8, borderRadius: R.md },
  periodBtnActive:  { backgroundColor: C.gold },
  periodText:       { color: C.textSecondary, fontSize: S.sm, fontWeight: '600' },
  periodTextActive: { color: '#fff', fontWeight: '700' },

  navRow: {
    flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between',
    backgroundColor: C.bgCard, borderRadius: R.md,
    paddingHorizontal: 4, paddingVertical: 4,
    borderWidth: 1, borderColor: C.border,
  },
  navArrow:         { padding: 10, borderRadius: R.sm },
  navArrowDisabled: { opacity: 0.3 },
  navLabel:         { fontSize: S.md, fontWeight: '700', color: C.textPrimary },

  loadingBox: { height: 200, alignItems: 'center', justifyContent: 'center' },

  totalCard:   { padding: 24, alignItems: 'center', gap: 4, borderWidth: 1, borderColor: C.goldSoft },
  totalLabel:  { fontSize: S.xs, color: C.textSecondary, fontWeight: '600', textTransform: 'uppercase', letterSpacing: 0.5 },
  totalAmount: { fontSize: S.hero * 0.8, fontWeight: '800', color: C.textPrimary, letterSpacing: -2 },
  totalSub:    { fontSize: S.sm, color: C.textSecondary, marginTop: 2 },

  section:      { gap: 10 },
  sectionTitle: { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },

  catCard:   { padding: 0, overflow: 'hidden' },
  catRow:    { flexDirection: 'row', alignItems: 'center', gap: 12, padding: 14 },
  catBorder: { borderTopWidth: 1, borderTopColor: C.borderSoft },
  emojiBox:  { width: 40, height: 40, borderRadius: R.sm, alignItems: 'center', justifyContent: 'center' },
  catEmoji:  { fontSize: 20 },
  catLabelRow: { flexDirection: 'row', alignItems: 'center', gap: 6 },
  catLabel:  { fontSize: S.sm, fontWeight: '600', color: C.textPrimary, flex: 1 },
  catPct:    { fontSize: S.xs, color: C.textTertiary, fontWeight: '600', minWidth: 32, textAlign: 'right' },
  catAmount: { fontSize: S.sm, fontWeight: '700', color: C.textSecondary, minWidth: 80, textAlign: 'right' },
  barBg:     { height: 5, backgroundColor: C.bgSoft, borderRadius: 3, overflow: 'hidden' },
  barFill:   { height: '100%', borderRadius: 3 },

  storeRow:    { flexDirection: 'row', alignItems: 'center', gap: 12, padding: 14 },
  storeBorder: { borderTopWidth: 1, borderTopColor: C.borderSoft },
  storeIcon:   { width: 38, height: 38, borderRadius: R.sm, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  storeInitial:{ fontSize: S.md, fontWeight: '700', color: C.gold },
  storeName:   { flex: 1, fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  storeCount:  { fontSize: S.sm, color: C.textTertiary },
  storeAmount: { fontSize: S.md, fontWeight: '700', color: C.gold, minWidth: 70, textAlign: 'right' },

  tagRow:    { flexDirection: 'row', alignItems: 'center', gap: 8, paddingHorizontal: 14, paddingVertical: 12 },
  tagName:   { flex: 1, fontSize: S.sm, color: C.textPrimary, fontWeight: '500' },
  tagAmount: { fontSize: S.sm, fontWeight: '700', color: C.textSecondary, minWidth: 80, textAlign: 'right' },
  tagPct:    { fontSize: S.xs, color: C.textTertiary, minWidth: 36, textAlign: 'right' },

  emptyState: { alignItems: 'center', paddingVertical: 60, gap: 12 },
  emptyPig:   { fontSize: 56 },
  emptyTitle: { fontSize: S.xl, fontWeight: '700', color: C.textPrimary },
  emptyText:  { fontSize: S.sm, color: C.textSecondary, textAlign: 'center', lineHeight: 20, maxWidth: 260 },
});
