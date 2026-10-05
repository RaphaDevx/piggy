import { View, Text, TouchableOpacity, StyleSheet, ActivityIndicator } from 'react-native';
import { useFinanceProfile, PROFILE_RANGES } from '../hooks/useFinanceProfile';
import { C, R, S, card } from '../constants/design';
import { getCategoryColor } from '../lib/categories';

const MONTH_SHORT = ['Jan', 'Feb', 'Mär', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dez'];
const MONTH_COUNT = 6;
const WEEK_COUNT = 8;

function chf(n: number) { return `CHF ${Math.round(n).toLocaleString('de-CH')}`; }
function pct(n: number | null) { return n == null ? '—' : `${Math.round(n * 100)} %`; }
function monthLabel(period: string) {
  const [y, m] = period.split('-');
  return `${MONTH_SHORT[Number(m) - 1] ?? m} ${y.slice(2)}`;
}
function weekLabel(period: string) { return `KW ${period.split('-W')[1] ?? period}`; }

export default function ProfileView() {
  const { profile, range, setRange, loading } = useFinanceProfile();

  if (loading && profile.monthly.length === 0) {
    return <View style={styles.loadingBox}><ActivityIndicator color={C.gold} /></View>;
  }

  const kpis: Array<{ label: string; value: string }> = [
    { label: 'Ø pro Woche',        value: chf(profile.avgWeeklyExpenses) },
    { label: 'Ø pro Monat',        value: chf(profile.avgMonthlyExpenses) },
    { label: 'Ø Einnahmen/Monat',  value: chf(profile.avgMonthlyInflow) },
    { label: 'Sparquote',          value: pct(profile.savingsRate) },
    { label: 'Fixkosten-Anteil',   value: pct(profile.fixedCostShare) },
  ];

  const months = profile.monthly.slice(-MONTH_COUNT);
  const weeks = profile.weekly.slice(-WEEK_COUNT);
  const monthMax = Math.max(1, ...months.flatMap((m) => [m.inflow, m.expenses]));
  const weekMax = Math.max(1, ...weeks.map((w) => w.expenses));

  return (
    <View style={{ gap: 20 }}>
      <View style={styles.chipRow}>
        {PROFILE_RANGES.map((r) => (
          <TouchableOpacity
            key={r.key}
            style={[styles.chip, range === r.key && styles.chipActive]}
            onPress={() => setRange(r.key)}
          >
            <Text style={[styles.chipText, range === r.key && styles.chipTextActive]}>{r.label}</Text>
          </TouchableOpacity>
        ))}
      </View>

      {profile.monthly.length === 0 && profile.weekly.length === 0 ? (
        <View style={[card, styles.emptyCard]}>
          <Text style={styles.emptyTitle}>Noch nichts zu sehen</Text>
          <Text style={styles.emptyText}>
            Sobald du einen Kontoauszug importiert oder Quittungen gescannt hast, zeigt Piggy hier dein Finanzprofil.
          </Text>
        </View>
      ) : (
        <>
          <View style={styles.kpiGrid}>
            {kpis.map((k) => (
              <View key={k.label} style={[card, styles.kpi]}>
                <Text style={styles.kpiLabel}>{k.label}</Text>
                <Text style={styles.kpiValue}>{k.value}</Text>
              </View>
            ))}
          </View>

          {months.length > 0 && (
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Monate</Text>
              <View style={[card, styles.chartCard]}>
                {months.map((m) => (
                  <View key={m.period} style={styles.barGroup}>
                    <Text style={styles.barLabel}>{monthLabel(m.period)}</Text>
                    <View style={styles.bars}>
                      <View style={styles.barLine}>
                        <View style={[styles.bar, { width: `${(m.inflow / monthMax) * 100}%`, backgroundColor: C.success }]} />
                        <Text style={styles.barValue}>{chf(m.inflow)}</Text>
                      </View>
                      <View style={styles.barLine}>
                        <View style={[styles.bar, { width: `${(m.expenses / monthMax) * 100}%`, backgroundColor: C.gold }]} />
                        <Text style={styles.barValue}>{chf(m.expenses)}</Text>
                      </View>
                    </View>
                  </View>
                ))}
                <View style={styles.legend}>
                  <View style={[styles.dot, { backgroundColor: C.success }]} /><Text style={styles.legendText}>Einnahmen</Text>
                  <View style={[styles.dot, { backgroundColor: C.gold }]} /><Text style={styles.legendText}>Ausgaben</Text>
                </View>
              </View>
            </View>
          )}

          {weeks.length > 0 && (
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Wochen</Text>
              <View style={[card, styles.chartCard]}>
                {weeks.map((w) => (
                  <View key={w.period} style={styles.barGroup}>
                    <Text style={styles.barLabel}>{weekLabel(w.period)}</Text>
                    <View style={styles.bars}>
                      <View style={styles.barLine}>
                        <View style={[styles.bar, { width: `${(w.expenses / weekMax) * 100}%`, backgroundColor: C.gold }]} />
                        <Text style={styles.barValue}>{chf(w.expenses)}</Text>
                      </View>
                    </View>
                  </View>
                ))}
              </View>
            </View>
          )}

          {profile.topCategories.length > 0 && (
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Top-Kategorien</Text>
              <View style={[card, { padding: 0, overflow: 'hidden' }]}>
                {profile.topCategories.map((c, i) => (
                  <View key={c.category} style={[styles.catRow, i > 0 && styles.catBorder]}>
                    <View style={[styles.dot, { backgroundColor: getCategoryColor(c.category) }]} />
                    <Text style={styles.catLabel} numberOfLines={1}>{c.category}</Text>
                    <Text style={styles.catShare}>{pct(c.share)}</Text>
                    <Text style={styles.catAmount}>{chf(c.amount)}</Text>
                  </View>
                ))}
              </View>
            </View>
          )}
        </>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  loadingBox: { height: 200, alignItems: 'center', justifyContent: 'center' },

  chipRow: { flexDirection: 'row', flexWrap: 'wrap', gap: 8 },
  chip:    { paddingVertical: 8, paddingHorizontal: 14, borderRadius: R.md, backgroundColor: C.bgSoft },
  chipActive:     { backgroundColor: C.gold },
  chipText:       { fontSize: S.sm, fontWeight: '600', color: C.textSecondary },
  chipTextActive: { color: '#fff', fontWeight: '700' },

  kpiGrid:  { flexDirection: 'row', flexWrap: 'wrap', gap: 10 },
  kpi:      { flexGrow: 1, flexBasis: '45%', padding: 14, gap: 4 },
  kpiLabel: { fontSize: S.xs, color: C.textSecondary, fontWeight: '600', textTransform: 'uppercase', letterSpacing: 0.5 },
  kpiValue: { fontSize: S.lg, fontWeight: '800', color: C.textPrimary },

  section:      { gap: 10 },
  sectionTitle: { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },

  chartCard: { padding: 16, gap: 12 },
  barGroup:  { gap: 4 },
  barLabel:  { fontSize: S.xs, fontWeight: '600', color: C.textSecondary },
  bars:      { gap: 3 },
  barLine:   { flexDirection: 'row', alignItems: 'center', gap: 8 },
  bar:       { height: 10, borderRadius: 5, minWidth: 2 },
  barValue:  { fontSize: S.xs, color: C.textTertiary },
  legend:    { flexDirection: 'row', alignItems: 'center', gap: 6 },
  legendText:{ fontSize: S.xs, color: C.textSecondary, marginRight: 10 },
  dot:       { width: 10, height: 10, borderRadius: 5 },

  catRow:    { flexDirection: 'row', alignItems: 'center', gap: 10, padding: 14 },
  catBorder: { borderTopWidth: 1, borderTopColor: C.borderSoft },
  catLabel:  { flex: 1, fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  catShare:  { fontSize: S.xs, color: C.textTertiary },
  catAmount: { fontSize: S.sm, fontWeight: '700', color: C.textSecondary },

  emptyCard:  { padding: 24, gap: 6, alignItems: 'center' },
  emptyTitle: { fontSize: S.md, fontWeight: '700', color: C.textPrimary },
  emptyText:  { fontSize: S.sm, color: C.textSecondary, textAlign: 'center' },
});
