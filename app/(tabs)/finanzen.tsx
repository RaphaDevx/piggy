import { useState } from 'react';
import {
  View, Text, ScrollView, TouchableOpacity, StyleSheet, ActivityIndicator, RefreshControl,
} from 'react-native';
import { useRouter } from 'expo-router';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { Ionicons } from '@/components/Ionicons';
import AbgleichView from '../../src/components/AbgleichView';
import TagPicker from '../../src/components/TagPicker';
import { useFinanceOverview } from '../../src/hooks/useFinanceOverview';
import ProfileView from '../../src/components/ProfileView';
import { C, R, S, card } from '../../src/constants/design';
import { TRANSACTION_CATEGORIES, getCategoryColor } from '../../src/lib/categories';
import { categoryDef, categoryLabel } from '../../src/lib/taxonomy';
import { useLocale } from '../../src/hooks/useLocale';

const MONTH_NAMES = [
  'Januar', 'Februar', 'März', 'April', 'Mai', 'Juni',
  'Juli', 'August', 'September', 'Oktober', 'November', 'Dezember',
];

function fmtCHF(n: number, cur = 'CHF') { return `${cur} ${n.toFixed(2)}`; }
function monthLabel(month: string) {
  const [y, m] = month.split('-');
  return `${MONTH_NAMES[Number(m) - 1] ?? m} ${y}`;
}
function fmtDate(d: string) { const [y, m, day] = d.split('-'); return `${day}.${m}.${y}`; }

export default function FinanzenScreen() {
  const insets = useSafeAreaInsets();
  const router = useRouter();
  const locale = useLocale();
  const [view, setView] = useState<'overview' | 'profile' | 'abgleich'>('overview');
  const [showUncat, setShowUncat] = useState(false);
  const {
    months, month, setMonth, overview, uncategorized, hasBankTransactions,
    loading, reload, setTransactionCategory,
  } = useFinanceOverview();

  const idx = months.indexOf(month);
  const canOlder = idx >= 0 && idx < months.length - 1;
  const canNewer = idx > 0;
  const cur = overview.currency;
  const hasData = overview.income > 0 || overview.expenses > 0 || overview.transfers > 0;

  return (
    <ScrollView
      style={styles.root}
      contentContainerStyle={[styles.content, { paddingTop: insets.top + 16 }]}
      showsVerticalScrollIndicator={false}
      refreshControl={view === 'overview'
        ? <RefreshControl refreshing={false} onRefresh={reload} tintColor={C.gold} />
        : undefined}
    >
      <Text style={styles.title}>Finanzen</Text>

      <View style={styles.segmentRow}>
        {([['overview', 'Übersicht'], ['profile', 'Profil'], ['abgleich', 'Abgleich']] as const).map(([key, label]) => (
          <TouchableOpacity
            key={key}
            style={[styles.segmentBtn, view === key && styles.segmentBtnActive]}
            onPress={() => setView(key)}
          >
            <Text style={[styles.segmentText, view === key && styles.segmentTextActive]}>{label}</Text>
          </TouchableOpacity>
        ))}
      </View>

      {view === 'abgleich' ? (
        <AbgleichView />
      ) : view === 'profile' ? (
        <ProfileView />
      ) : loading && months.length === 0 ? (
        <View style={styles.loadingBox}><ActivityIndicator color={C.gold} /></View>
      ) : (
        <>
          {/* Monats-Wähler */}
          <View style={styles.navRow}>
            <TouchableOpacity
              style={[styles.navArrow, !canOlder && styles.navArrowDisabled]}
              disabled={!canOlder}
              onPress={() => setMonth(months[idx + 1])}
            >
              <Ionicons name="chevron-back" size={20} color={C.textPrimary} />
            </TouchableOpacity>
            <Text style={styles.navLabel}>{monthLabel(month)}</Text>
            <TouchableOpacity
              style={[styles.navArrow, !canNewer && styles.navArrowDisabled]}
              disabled={!canNewer}
              onPress={() => setMonth(months[idx - 1])}
            >
              <Ionicons name="chevron-forward" size={20} color={C.textPrimary} />
            </TouchableOpacity>
          </View>

          {/* Kein Kontoauszug */}
          {!hasBankTransactions && (
            <TouchableOpacity style={[card, styles.noticeCard, styles.importCard]} activeOpacity={0.8} onPress={() => setView('abgleich')}>
              <Ionicons name="document-attach-outline" size={24} color={C.gold} />
              <View style={{ flex: 1 }}>
                <Text style={styles.noticeTitle}>Kontoauszug importieren</Text>
                <Text style={styles.noticeSub}>PDF, CSV oder CAMT.053</Text>
              </View>
              <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
            </TouchableOpacity>
          )}

          {/* Kennzahlen */}
          <View style={styles.kpiRow}>
            <View style={[card, styles.kpi]}>
              <Text style={styles.kpiLabel}>Einnahmen</Text>
              <Text style={styles.kpiValue}>{fmtCHF(overview.income, cur)}</Text>
            </View>
            <View style={[card, styles.kpi]}>
              <Text style={styles.kpiLabel}>Ausgaben</Text>
              <Text style={styles.kpiValue}>{fmtCHF(overview.expenses, cur)}</Text>
            </View>
            <View style={[card, styles.kpi]}>
              <Text style={styles.kpiLabel}>Netto</Text>
              <Text style={[styles.kpiValue, { color: overview.net >= 0 ? C.success : C.error }]}>
                {fmtCHF(overview.net, cur)}
              </Text>
            </View>
          </View>

          {/* Ausgaben nach Kategorie */}
          {overview.byCategory.length > 0 && (
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Ausgaben nach Kategorie</Text>
              <View style={[card, styles.catCard]}>
                {overview.byCategory.map((c, i) => {
                  const openable = categoryDef(c.category) !== undefined;
                  return (
                  <TouchableOpacity
                    key={c.category}
                    style={[styles.catRow, i > 0 && styles.catBorder]}
                    disabled={!openable}
                    activeOpacity={0.7}
                    onPress={() => router.push({ pathname: '/category/[group]' as any, params: { group: c.category } })}
                  >
                    <View style={styles.catHeader}>
                      <View style={[styles.dot, { backgroundColor: getCategoryColor(c.category) }]} />
                      <Text style={styles.catLabel} numberOfLines={1}>{categoryLabel(c.category, locale)}</Text>
                      <Text style={styles.catAmount}>{fmtCHF(c.amount, cur)}</Text>
                      {openable && <Ionicons name="chevron-forward" size={14} color={C.textTertiary} />}
                    </View>
                    <View style={styles.barBg}>
                      <View style={[styles.barFill, {
                        width: `${Math.min(c.share * 100, 100)}%`,
                        backgroundColor: getCategoryColor(c.category),
                      }]} />
                    </View>
                    {c.fromBank > 0 && c.fromReceipts > 0 && (
                      <Text style={styles.catSub}>
                        Bank {fmtCHF(c.fromBank, cur)} · Quittung {fmtCHF(c.fromReceipts, cur)}
                      </Text>
                    )}
                  </TouchableOpacity>
                  );
                })}
              </View>
            </View>
          )}

          {/* Beleg-Abdeckung */}
          {(overview.receiptCoverage != null || overview.receiptOnlyExpenses > 0 || overview.transfers > 0) && (
            <View style={[card, styles.coverCard]}>
              {overview.receiptCoverage != null && (
                <>
                  <Text style={styles.coverText}>
                    {Math.round(overview.receiptCoverage * 100)} % deiner Bank-Ausgaben sind mit Quittungen belegt
                  </Text>
                  <View style={styles.barBg}>
                    <View style={[styles.barFill, {
                      width: `${Math.min(overview.receiptCoverage * 100, 100)}%`,
                      backgroundColor: C.gold,
                    }]} />
                  </View>
                </>
              )}
              {overview.receiptOnlyExpenses > 0 && (
                <Text style={styles.coverSub}>Bar/ohne Auszug: {fmtCHF(overview.receiptOnlyExpenses, cur)}</Text>
              )}
              {overview.transfers > 0 && (
                <Text style={styles.coverSub}>Umbuchungen (nicht gezählt): {fmtCHF(overview.transfers, cur)}</Text>
              )}
            </View>
          )}

          {/* Ohne Kategorie */}
          {uncategorized.length > 0 && (
            <View style={[card, styles.noticeWrap]}>
              <TouchableOpacity style={styles.noticeCard} activeOpacity={0.8} onPress={() => setShowUncat((v) => !v)}>
                <Ionicons name="alert-circle-outline" size={24} color={C.warning} />
                <View style={{ flex: 1 }}>
                  <Text style={styles.noticeTitle}>
                    {uncategorized.length} Buchung{uncategorized.length !== 1 ? 'en' : ''} ohne Kategorie
                  </Text>
                  <Text style={styles.noticeSub}>{showUncat ? 'Zuordnen, um sie zu zählen' : 'Tippen zum Zuordnen'}</Text>
                </View>
                <Ionicons name={showUncat ? 'close' : 'chevron-forward'} size={16} color={C.textTertiary} />
              </TouchableOpacity>
              {showUncat && uncategorized.map((t) => (
                <View key={t.id} style={styles.txRow}>
                  <View style={styles.txHeader}>
                    <Text style={styles.txDate}>{fmtDate(t.booking_date)}</Text>
                    <Text style={styles.txText} numberOfLines={2}>{t.description}</Text>
                    <Text style={styles.txAmount}>{fmtCHF(Math.abs(t.amount), t.currency)}</Text>
                  </View>
                  <TagPicker
                    value={t.category ?? ''}
                    onChange={(cat) => setTransactionCategory(t.id, cat)}
                    options={TRANSACTION_CATEGORIES}
                  />
                </View>
              ))}
            </View>
          )}

          {/* Ohne Quittung */}
          {overview.unmatchedExpenseCount > 0 && (
            <TouchableOpacity style={[card, styles.noticeCard]} activeOpacity={0.8} onPress={() => setView('abgleich')}>
              <Ionicons name="receipt-outline" size={24} color={C.gold} />
              <View style={{ flex: 1 }}>
                <Text style={styles.noticeTitle}>
                  {overview.unmatchedExpenseCount} Ausgabe{overview.unmatchedExpenseCount !== 1 ? 'n' : ''} ohne Quittung
                </Text>
                <Text style={styles.noticeSub}>Zum Abgleich wechseln</Text>
              </View>
              <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
            </TouchableOpacity>
          )}

          {!hasData && hasBankTransactions && (
            <Text style={styles.empty}>Keine Daten für diesen Monat.</Text>
          )}
        </>
      )}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  root:    { flex: 1, backgroundColor: C.bg },
  content: { padding: 20, gap: 20, paddingBottom: 40 },
  title:   { fontSize: S.xxl, fontWeight: '800', color: C.textPrimary, letterSpacing: -0.5 },

  segmentRow:        { flexDirection: 'row', backgroundColor: C.bgSoft, borderRadius: R.lg, padding: 4, gap: 2 },
  segmentBtn:        { flex: 1, alignItems: 'center', paddingVertical: 8, borderRadius: R.md },
  segmentBtnActive:  { backgroundColor: C.gold },
  segmentText:       { color: C.textSecondary, fontSize: S.sm, fontWeight: '600' },
  segmentTextActive: { color: '#fff', fontWeight: '700' },

  navRow: {
    flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between',
    backgroundColor: C.bgCard, borderRadius: R.md, padding: 4,
    borderWidth: 1, borderColor: C.border,
  },
  navArrow:         { padding: 10, borderRadius: R.sm },
  navArrowDisabled: { opacity: 0.3 },
  navLabel:         { fontSize: S.md, fontWeight: '700', color: C.textPrimary },

  loadingBox: { height: 200, alignItems: 'center', justifyContent: 'center' },

  kpiRow:   { flexDirection: 'row', gap: 10 },
  kpi:      { flex: 1, padding: 12, gap: 4 },
  kpiLabel: { fontSize: S.xs, color: C.textSecondary, fontWeight: '600', textTransform: 'uppercase', letterSpacing: 0.5 },
  kpiValue: { fontSize: S.sm, fontWeight: '800', color: C.textPrimary },

  section:      { gap: 10 },
  sectionTitle: { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },

  catCard:   { padding: 0, overflow: 'hidden' },
  catRow:    { padding: 14, gap: 8 },
  catBorder: { borderTopWidth: 1, borderTopColor: C.borderSoft },
  catHeader: { flexDirection: 'row', alignItems: 'center', gap: 8 },
  dot:       { width: 10, height: 10, borderRadius: 5 },
  catLabel:  { fontSize: S.sm, fontWeight: '600', color: C.textPrimary, flex: 1 },
  catAmount: { fontSize: S.sm, fontWeight: '700', color: C.textSecondary },
  catSub:    { fontSize: S.xs, color: C.textTertiary },
  barBg:     { height: 5, backgroundColor: C.bgSoft, borderRadius: 3, overflow: 'hidden' },
  barFill:   { height: '100%', borderRadius: 3 },

  coverCard: { padding: 16, gap: 8 },
  coverText: { fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  coverSub:  { fontSize: S.xs, color: C.textSecondary },

  noticeWrap:  { overflow: 'hidden' },
  noticeCard:  { flexDirection: 'row', alignItems: 'center', gap: 12, padding: 16 },
  importCard:  { borderWidth: 1, borderColor: C.goldSoft },
  noticeTitle: { fontSize: S.sm, fontWeight: '700', color: C.textPrimary },
  noticeSub:   { fontSize: S.xs, color: C.textSecondary, marginTop: 2 },

  txRow:    { padding: 14, gap: 8, borderTopWidth: 1, borderTopColor: C.borderSoft },
  txHeader: { flexDirection: 'row', alignItems: 'center', gap: 8 },
  txDate:   { fontSize: S.xs, color: C.textTertiary },
  txText:   { flex: 1, fontSize: S.sm, color: C.textPrimary },
  txAmount: { fontSize: S.sm, fontWeight: '700', color: C.textSecondary },

  empty: { textAlign: 'center', color: C.textTertiary, fontSize: S.sm },
});
