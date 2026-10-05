import { buildFinanceProfile, weekOf } from './profile';
import type { FinanceReceipt, FinanceTransaction } from './finance';

const tx = (id: string, booking_date: string, amount: number, description: string, extra: Partial<FinanceTransaction> = {}): FinanceTransaction =>
  ({ id, booking_date, amount, currency: 'CHF', description, match_status: 'unmatched', ...extra });

describe('weekOf', () => {
  test.each([
    ['2026-01-01', '2026-W01'],
    ['2026-12-31', '2026-W53'],
    ['2027-01-01', '2026-W53'],
    ['2027-01-04', '2027-W01'],
    ['2024-12-30', '2025-W01'],
    ['2026-10-05', '2026-W41'],
    ['2026-10-04', '2026-W40'],
  ])('%s → %s', (d, w) => expect(weekOf(d)).toBe(w));
});

describe('buildFinanceProfile', () => {
  const data = [
    tx('1', '2026-01-25', 5000, 'Lohn Januar Muster AG'),
    tx('2', '2026-01-26', -1500, 'Mietzins Verwaltung'),
    tx('3', '2026-01-28', -500, 'Migros Zürich'),
    tx('4', '2026-02-25', 5000, 'Lohn Februar Muster AG'),
    tx('5', '2026-02-26', -1500, 'Mietzins Verwaltung'),
    tx('6', '2026-02-27', -1500, 'Migros Zürich'),
  ];

  test('Durchschnitte, Sparquote, Top-Kategorien, Fixkosten', () => {
    const p = buildFinanceProfile(data, [], []);
    expect(p.monthly.map((m) => m.period)).toEqual(['2026-01', '2026-02']);
    expect(p.avgMonthlyExpenses).toBe(2500);
    expect(p.avgMonthlyInflow).toBe(5000);
    expect(p.savingsRate).toBeCloseTo(0.5);
    expect(p.fixedCostShare).toBeCloseTo(0.6);
    expect(p.topCategories[0]).toMatchObject({ category: 'Wohnen & Nebenkosten', amount: 3000 });
    expect(p.topCategories[0].share).toBeCloseTo(0.6);
    expect(p.monthsCovered).toBe(2);
    expect(p.periodStart).toBe('2026-01-25');
    expect(p.periodEnd).toBe('2026-02-27');
    expect(p.weekly.map((w) => w.period)).toEqual(['2026-W04', '2026-W05', '2026-W09']);
    expect(p.avgWeeklyExpenses).toBeCloseTo(1666.67, 2);
  });

  test('Umbuchung zählt nicht', () => {
    const p = buildFinanceProfile([...data, tx('7', '2026-02-28', -900, 'Zahlung Kreditkarte Viseca LSV')], [], []);
    expect(p.avgMonthlyExpenses).toBe(2500);
    expect(p.monthly[1].byCategory['Umbuchung']).toBeUndefined();
  });

  test('verknüpfte Quittung wird nicht doppelt gezählt', () => {
    const r: FinanceReceipt = {
      id: 'r1', receipt_date: '2026-01-28', currency: 'CHF', total_amount: 500,
      items: [{ total_price: 500, tags: ['Haushalt'] }],
    };
    const p = buildFinanceProfile(data, [r], [{ transaction_id: '3', receipt_id: 'r1' }]);
    expect(p.monthly[0].expenses).toBe(2000);
    expect(p.monthly[0].byCategory['Haushalt']).toBe(500);
  });

  test('from/to filtert inklusiv', () => {
    const p = buildFinanceProfile(data, [], [], { from: '2026-02-01', to: '2026-02-26' });
    expect(p.monthly.map((m) => m.period)).toEqual(['2026-02']);
    expect(p.monthly[0].expenses).toBe(1500);
    expect(p.periodStart).toBe('2026-02-25');
    expect(p.periodEnd).toBe('2026-02-26');
  });

  test('leere Daten → Nullen/null', () => {
    expect(buildFinanceProfile([], [], [])).toEqual({
      weekly: [], monthly: [], avgWeeklyExpenses: 0, avgMonthlyExpenses: 0, avgMonthlyInflow: 0,
      savingsRate: null, topCategories: [], fixedCostShare: null,
      periodStart: null, periodEnd: null, monthsCovered: 0,
    });
  });
});
