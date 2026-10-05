import { findSuggestions, type SuggestionReceipt } from './discrepancies';
import type { MatchTransaction } from '../../supabase/functions/_shared/finance-core/matching.ts';

const tx = (id: string, booking_date: string, amount: number, description: string, currency = 'CHF'): MatchTransaction =>
  ({ id, booking_date, amount, currency, description, match_status: 'unmatched' });

const rc = (id: string, receipt_date: string, total_amount: number, store_name: string, extra: Partial<SuggestionReceipt> = {}): SuggestionReceipt =>
  ({ id, receipt_date, total_amount, currency: 'CHF', store_name, ...extra });

describe('findSuggestions', () => {
  test('Trinkgeld: 48.50 Quittung, 53.50 Buchung', () => {
    const r = findSuggestions(
      [tx('t1', '2026-03-10', -53.5, 'Restaurant Adler Zürich')],
      [rc('r1', '2026-03-10', 48.5, 'Restaurant Adler', { store_category: 'Restaurant' })],
      'debit',
    );
    expect(r).toHaveLength(1);
    expect(r[0]).toMatchObject({ transactionId: 't1', receiptId: 'r1', kind: 'tip', diffAmount: 5 });
    expect(r[0].score).toBeCloseTo(1);
  });

  test('Restaurant wird über Artikel-Kategorien erkannt', () => {
    const items = [
      { total_price: 20, tags: ['Restaurant & Take-away'] },
      { total_price: 18, tags: ['Restaurant & Take-away'] },
      { total_price: 10.5, tags: ['Getränke'] },
    ];
    const r = findSuggestions(
      [tx('t1', '2026-03-10', -53.5, 'Osteria Roma')],
      [rc('r1', '2026-03-10', 48.5, 'Osteria Roma', { items })],
      'debit',
    );
    expect(r[0].kind).toBe('tip');
  });

  test('kein Restaurant und kleine Differenz → other', () => {
    const r = findSuggestions(
      [tx('t1', '2026-03-10', -52, 'Migros Zürich')],
      [rc('r1', '2026-03-10', 48.5, 'Migros', { store_category: 'Supermarkt' })],
      'debit',
    );
    expect(r[0]).toMatchObject({ kind: 'other', diffAmount: 3.5 });
  });

  test('Rabatt: 30 → 27 = discount -3.00', () => {
    const r = findSuggestions([tx('t1', '2026-03-10', -27, 'Coop Bern')], [rc('r1', '2026-03-10', 30, 'Coop')], 'debit');
    expect(r[0]).toMatchObject({ kind: 'discount', diffAmount: -3 });
  });

  test('Teilzahlung: 100 → 50 = partial', () => {
    const r = findSuggestions([tx('t1', '2026-03-10', -50, 'Interio Zürich')], [rc('r1', '2026-03-10', 100, 'Interio')], 'debit');
    expect(r[0]).toMatchObject({ kind: 'partial', diffAmount: -50 });
  });

  test('Fremdwährung: EUR 20 → CHF 19.20 = fx', () => {
    const r = findSuggestions(
      [tx('t1', '2026-03-10', -19.2, 'Cafe Paris')],
      [rc('r1', '2026-03-10', 20, 'Cafe Paris', { currency: 'EUR' })],
      'debit',
    );
    expect(r[0].kind).toBe('fx');
    expect(r[0].diffAmount).toBeCloseTo(0.4, 2);
  });

  test('Fremdwährung ausserhalb des Kursbands → kein Vorschlag; eigene Kurse', () => {
    const t = [tx('t1', '2026-03-10', -30, 'Cafe Paris')];
    const q = [rc('r1', '2026-03-10', 20, 'Cafe Paris', { currency: 'EUR' })];
    expect(findSuggestions(t, q, 'debit')).toEqual([]);
    expect(findSuggestions(t, q, 'debit', new Set(), { EUR: 1.5 })[0].kind).toBe('fx');
  });

  test('exakter Betrag → kein Vorschlag', () => {
    expect(findSuggestions([tx('t1', '2026-03-10', -48.5, 'Migros')], [rc('r1', '2026-03-10', 48.5, 'Migros')], 'debit')).toEqual([]);
  });

  test('anderer Händler → kein Vorschlag', () => {
    expect(findSuggestions([tx('t1', '2026-03-10', -53.5, 'Coop Bern')], [rc('r1', '2026-03-10', 48.5, 'Restaurant Adler', { store_category: 'Restaurant' })], 'debit')).toEqual([]);
  });

  test('Datum zu weit → kein Vorschlag (Debit), Kreditkarte erlaubt Verzug', () => {
    const t = [tx('t1', '2026-03-20', -53.5, 'Restaurant Adler')];
    const q = [rc('r1', '2026-03-10', 48.5, 'Restaurant Adler', { store_category: 'Restaurant' })];
    expect(findSuggestions(t, q, 'debit')).toEqual([]);
    expect(findSuggestions(t, q, 'credit')[0].kind).toBe('tip');
  });

  test('exclude wird respektiert', () => {
    const t = [tx('t1', '2026-03-10', -27, 'Coop Bern')];
    const q = [rc('r1', '2026-03-10', 30, 'Coop')];
    expect(findSuggestions(t, q, 'debit', new Set(['t1|r1']))).toEqual([]);
  });

  test('nur Ausgaben mit Status unmatched', () => {
    const t = [{ ...tx('t1', '2026-03-10', -27, 'Coop Bern'), match_status: 'matched' }, tx('t2', '2026-03-10', 27, 'Coop Bern')];
    expect(findSuggestions(t, [rc('r1', '2026-03-10', 30, 'Coop')], 'debit')).toEqual([]);
  });

  test('1:1 — jede Buchung und Quittung höchstens einmal, bester Score gewinnt', () => {
    const t = [tx('t1', '2026-03-10', -27, 'Coop Bern'), tx('t2', '2026-03-11', -27, 'Coop Bern')];
    const q = [rc('r1', '2026-03-10', 30, 'Coop'), rc('r2', '2026-03-11', 30, 'Coop')];
    const r = findSuggestions(t, q, 'debit');
    expect(r).toHaveLength(2);
    expect(new Set(r.map((c) => c.transactionId)).size).toBe(2);
    expect(new Set(r.map((c) => c.receiptId)).size).toBe(2);
    expect(r.find((c) => c.transactionId === 't1')?.receiptId).toBe('r1');

    const one = findSuggestions([t[0]], q, 'debit');
    expect(one).toHaveLength(1);
    expect(one[0].receiptId).toBe('r1');
  });
});
