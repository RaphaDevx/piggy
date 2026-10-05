import {
  buildMonthlyOverview, categorizeTransaction, dedupeTransactions, normalizeTransactionCategory,
  splitByItemCategories, availableMonths,
  type FinanceReceipt, type FinanceTransaction,
} from './finance';

const tx = (id: string, booking_date: string, amount: number, description: string, extra: Partial<FinanceTransaction> = {}): FinanceTransaction =>
  ({ id, booking_date, amount, currency: 'CHF', description, match_status: 'unmatched', ...extra });

const receipt = (id: string, receipt_date: string, items: Array<[number, string]>): FinanceReceipt => ({
  id, receipt_date, currency: 'CHF',
  total_amount: items.reduce((s, [p]) => s + p, 0),
  items: items.map(([total_price, tag]) => ({ total_price, tags: [tag] })),
});

describe('categorizeTransaction', () => {
  test.each([
    ['MIGROS MM ZUERICH HB', -45.2, 'Lebensmittel'],
    ['Coop-1234 Winterthur', -12.9, 'Lebensmittel'],
    ['SBB CFF FFS Mobile Ticket', -8.8, 'Mobilität'],
    ['Shell Tankstelle Wil', -80, 'Mobilität'],
    ['CSS Kranken-Versicherung AG', -389.5, 'Versicherungen & Abos'],
    ['Swisscom (Schweiz) AG', -59, 'Versicherungen & Abos'],
    ['Mietzins Oktober Verwaltung Muster', -1450, 'Wohnen & Nebenkosten'],
    ['McDonalds St. Gallen', -14.5, 'Restaurant & Take-away'],
    ['IKEA AG Dietlikon', -120, 'Haushalt'],
    ['Amavita Apotheke', -22.4, 'Körperpflege & Gesundheit'],
    ['Galaxus.ch', -199, 'Freizeit & Shopping'],
    ['Zahlung Kreditkarte Viseca LSV', -812.4, 'Umbuchung'],
    ['Kontoübertrag Sparkonto', -500, 'Umbuchung'],
    ['Lohn September Muster AG', 5200, 'Einkommen'],
    ['TWINT von Anna', 25, 'Einkommen'],
    ['XYZ GmbH 4711', -33, 'Nicht kategorisiert'],
  ])('%s → %s', (desc, amount, expected) => {
    expect(categorizeTransaction(desc, amount)).toBe(expected);
  });

  test('KI-Kategorie wird übernommen, wenn bekannt, sonst Regeln', () => {
    expect(normalizeTransactionCategory('Haushalt', 'Migros', -10)).toBe('Haushalt');
    expect(normalizeTransactionCategory('Groceries', 'Migros', -10)).toBe('Lebensmittel');
    expect(normalizeTransactionCategory(null, 'Migros', -10)).toBe('Lebensmittel');
  });
});

describe('dedupeTransactions', () => {
  const existing = [
    { booking_date: '2026-09-01', amount: -4.5, description: 'Starbucks Zürich' },
  ];

  test('bereits importierte Buchung wird übersprungen (Text normalisiert)', () => {
    const incoming = [
      { booking_date: '2026-09-01', amount: -4.5, description: 'STARBUCKS  ZÜRICH ' },
      { booking_date: '2026-09-02', amount: -9, description: 'Migros' },
    ];
    expect(dedupeTransactions(incoming, existing)).toEqual([incoming[1]]);
  });

  test('zwei echte gleiche Buchungen bleiben als Multiset erhalten', () => {
    const incoming = [
      { booking_date: '2026-09-01', amount: -4.5, description: 'Starbucks Zürich' },
      { booking_date: '2026-09-01', amount: -4.5, description: 'Starbucks Zürich' },
    ];
    expect(dedupeTransactions(incoming, existing)).toHaveLength(1);
    expect(dedupeTransactions(incoming, [])).toHaveLength(2);
  });
});

describe('splitByItemCategories', () => {
  test('verteilt Bankbetrag anteilig nach Artikelpreisen', () => {
    const r = receipt('r1', '2026-09-03', [[30, 'Lebensmittel'], [10, 'Haushalt']]);
    const split = splitByItemCategories(40, r);
    expect(split.Lebensmittel).toBeCloseTo(30);
    expect(split.Haushalt).toBeCloseTo(10);
  });

  test('ohne verwertbare Artikel → Diverses', () => {
    const r: FinanceReceipt = { id: 'r', receipt_date: '2026-09-01', total_amount: 5, currency: 'CHF', items: [] };
    expect(splitByItemCategories(5, r)).toEqual({ Diverses: 5 });
  });
});

describe('buildMonthlyOverview', () => {
  const transactions = [
    tx('t1', '2026-09-03', -40, 'MIGROS ZUERICH'),           // mit Quittung r1 verknüpft
    tx('t2', '2026-09-05', -1450, 'Mietzins Oktober'),
    tx('t3', '2026-09-25', 5200, 'Lohn September'),
    tx('t4', '2026-09-28', -812.4, 'Zahlung Kreditkarte Viseca'), // Umbuchung, zählt nicht
    tx('t5', '2026-09-10', -33, 'XYZ GmbH'),                  // nicht kategorisiert
    tx('t6', '2026-09-12', 20, 'Migros Rückerstattung', { category: 'Lebensmittel' }),
    tx('t7', '2026-08-30', -99, 'Galaxus'),                   // anderer Monat
    tx('t8', '2026-09-15', -15, 'Starbucks', { category: 'Restaurant & Take-away', currency: 'EUR' }),
  ];
  const receipts = [
    receipt('r1', '2026-09-03', [[30, 'Lebensmittel'], [10, 'Haushalt']]),
    receipt('r2', '2026-09-07', [[12, 'Restaurant & Take-away']]), // bar bezahlt, keine Buchung
    receipt('r3', '2026-08-01', [[50, 'Lebensmittel']]),            // anderer Monat
  ];
  const matches = [{ transaction_id: 't1', receipt_id: 'r1' }];
  const o = buildMonthlyOverview('2026-09', transactions, receipts, matches);

  test('Einnahmen, Ausgaben, Netto', () => {
    expect(o.income).toBe(5200);
    // 40 (Migros) + 1450 (Miete) + 33 (XYZ) − 20 (Rückerstattung) + 12 (bar)
    expect(o.expenses).toBe(1515);
    expect(o.net).toBe(3685);
  });

  test('verknüpfte Quittung teilt die Bank-Ausgabe nach Artikeln auf', () => {
    const lm = o.byCategory.find((c) => c.category === 'Lebensmittel')!;
    const hh = o.byCategory.find((c) => c.category === 'Haushalt')!;
    expect(lm.amount).toBe(10);   // 30 aus Quittung − 20 Rückerstattung
    expect(hh.amount).toBe(10);
  });

  test('Umbuchungen und Fremdwährungen zählen nicht, Bargeld-Quittungen schon', () => {
    expect(o.transfers).toBe(812.4);
    expect(o.byCategory.find((c) => c.category === 'Umbuchung')).toBeUndefined();
    const resto = o.byCategory.find((c) => c.category === 'Restaurant & Take-away')!;
    expect(resto.amount).toBe(12);
    expect(resto.fromReceipts).toBe(12);
    expect(o.receiptOnlyExpenses).toBe(12);
  });

  test('Beleg-Abdeckung und offene Punkte', () => {
    expect(o.bankExpenses).toBe(1523);
    expect(o.receiptCoverage).toBeCloseTo(40 / 1523);
    expect(o.uncategorizedCount).toBe(1);
    expect(o.unmatchedExpenseCount).toBe(2);
  });

  test('Monate aus Buchungen und Quittungen, neueste zuerst', () => {
    expect(availableMonths(transactions, receipts)).toEqual(['2026-09', '2026-08']);
  });
});
