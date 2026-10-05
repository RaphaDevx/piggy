/**
 * profile.ts — Finanzprofil (Wochen-/Monatsreihen, Durchschnitte, Sparquote).
 * Summen kommen ausschliesslich aus buildPeriodOverview (keine zweite Logik →
 * keine Doppelzählung, Umbuchungen bleiben ausgeschlossen).
 */
import {
  buildPeriodOverview, monthOf, effectiveCategory,
  type FinanceMatch, type FinanceReceipt, type FinanceTransaction,
} from './finance.ts';
import { INCOME_CATEGORY, TRANSFER_CATEGORY, UNCATEGORIZED, itemCategory } from './categories.ts';
import { natureOf, type Nature } from './taxonomy.ts';

export interface PeriodPoint {
  period: string;
  expenses: number;
  inflow: number;
  net: number;
  byCategory: Record<string, number>;
}

export interface FinanceProfile {
  weekly: PeriodPoint[];
  monthly: PeriodPoint[];
  avgWeeklyExpenses: number;
  avgMonthlyExpenses: number;
  avgMonthlyInflow: number;
  savingsRate: number | null;
  topCategories: Array<{ category: string; amount: number; share: number }>;
  fixedCostShare: number | null;
  periodStart: string | null;
  periodEnd: string | null;
  monthsCovered: number;
}

const FIXED_COST_CATEGORIES = ['Wohnen & Nebenkosten', 'Versicherungen & Abos'];
const TOP_CATEGORY_COUNT = 5;
const MS_PER_DAY = 86_400_000;

const round2 = (n: number) => Math.round(n * 100) / 100;

/** ISO-8601-Woche `YYYY-Www` (Montag-Start, ISO-Jahr). */
export function weekOf(isoDate: string): string {
  const [y, m, d] = isoDate.slice(0, 10).split('-').map(Number);
  const date = new Date(Date.UTC(y, m - 1, d));
  const dayNr = (date.getUTCDay() + 6) % 7; // Mo=0 … So=6
  date.setUTCDate(date.getUTCDate() - dayNr + 3); // Donnerstag der Woche
  const isoYear = date.getUTCFullYear();
  const firstThursday = new Date(Date.UTC(isoYear, 0, 4));
  const week = 1 + Math.round(
    ((date.getTime() - firstThursday.getTime()) / MS_PER_DAY - 3 + ((firstThursday.getUTCDay() + 6) % 7)) / 7,
  );
  return `${isoYear}-W${String(week).padStart(2, '0')}`;
}

function buildSeries(
  periodOf: (isoDate: string) => string,
  transactions: FinanceTransaction[],
  receipts: FinanceReceipt[],
  matches: FinanceMatch[],
  currency: string,
): PeriodPoint[] {
  const periods = new Set<string>();
  for (const t of transactions) if (t.currency === currency) periods.add(periodOf(t.booking_date));
  for (const r of receipts) if (r.currency === currency && r.receipt_date) periods.add(periodOf(r.receipt_date));

  return [...periods].sort().flatMap((period) => {
    const o = buildPeriodOverview(period, periodOf, transactions, receipts, matches, currency);
    if (o.income === 0 && o.expenses === 0 && o.byCategory.length === 0) return [];
    return [{
      period,
      expenses: o.expenses,
      inflow: o.income,
      net: o.net,
      byCategory: Object.fromEntries(o.byCategory.map((c) => [c.category, c.amount])),
    }];
  });
}

const sum = (points: PeriodPoint[], pick: (p: PeriodPoint) => number) =>
  points.reduce((s, p) => s + pick(p), 0);
const avg = (points: PeriodPoint[], pick: (p: PeriodPoint) => number) =>
  points.length ? round2(sum(points, pick) / points.length) : 0;

export function buildFinanceProfile(
  transactions: FinanceTransaction[],
  receipts: FinanceReceipt[],
  matches: FinanceMatch[],
  opts: { from?: string; to?: string; currency?: string } = {},
): FinanceProfile {
  const { from, to, currency = 'CHF' } = opts;
  const inRange = (date: string) => (!from || date >= from) && (!to || date <= to);

  const txs = transactions.filter((t) => inRange(t.booking_date));
  const rcs = receipts.filter((r) => r.receipt_date != null && inRange(r.receipt_date));

  const weekly = buildSeries(weekOf, txs, rcs, matches, currency);
  const monthly = buildSeries(monthOf, txs, rcs, matches, currency);

  const totalExpenses = sum(monthly, (p) => p.expenses);
  const totalInflow = sum(monthly, (p) => p.inflow);

  const byCategory: Record<string, number> = {};
  for (const p of monthly) {
    for (const [c, a] of Object.entries(p.byCategory)) byCategory[c] = (byCategory[c] ?? 0) + a;
  }
  const topCategories = Object.entries(byCategory)
    .filter(([, amount]) => amount > 0)
    .sort((a, b) => b[1] - a[1])
    .slice(0, TOP_CATEGORY_COUNT)
    .map(([category, amount]) => ({
      category,
      amount: round2(amount),
      share: totalExpenses > 0 ? amount / totalExpenses : 0,
    }));

  const fixed = FIXED_COST_CATEGORIES.reduce((s, c) => s + (byCategory[c] ?? 0), 0);

  const dates = [
    ...txs.filter((t) => t.currency === currency).map((t) => t.booking_date),
    ...rcs.filter((r) => r.currency === currency).map((r) => r.receipt_date as string),
  ].sort();

  return {
    weekly,
    monthly,
    avgWeeklyExpenses: avg(weekly, (p) => p.expenses),
    avgMonthlyExpenses: avg(monthly, (p) => p.expenses),
    avgMonthlyInflow: avg(monthly, (p) => p.inflow),
    savingsRate: totalInflow > 0 ? (totalInflow - totalExpenses) / totalInflow : null,
    topCategories,
    fixedCostShare: totalExpenses > 0 ? fixed / totalExpenses : null,
    periodStart: dates[0] ?? null,
    periodEnd: dates[dates.length - 1] ?? null,
    monthsCovered: monthly.length,
  };
}

// ── Aufteilung nach Art ("Wofür geht dein Geld?") ────────────────────────────

export interface NatureBreakdown {
  total: number;
  amounts: Record<Nature, number>;
  /** Anteile an `total` (0–1) */
  shares: Record<Nature, number>;
}

const NATURES: Nature[] = ['essential', 'treat', 'occasional'];

/** Verteilt einen Betrag anteilig nach Artikelpreis auf die Arten einer Quittung; null ohne verwertbare Artikel. */
function splitByNature(amount: number, receipt: FinanceReceipt): Record<Nature, number> | null {
  const weights: Record<Nature, number> = { essential: 0, treat: 0, occasional: 0 };
  for (const item of receipt.items) {
    const price = item.total_price ?? 0;
    if (price <= 0) continue;
    weights[natureOf(item.subcategory, itemCategory(item.tags))] += price;
  }
  const total = NATURES.reduce((s, n) => s + weights[n], 0);
  if (total <= 0) return null;
  return {
    essential: (amount * weights.essential) / total,
    treat: (amount * weights.treat) / total,
    occasional: (amount * weights.occasional) / total,
  };
}

/**
 * Ausgaben nach Art (Grundbedarf / Genuss & Komfort / Anschaffungen).
 * Verknüpfte Buchungen werden nach den Artikeln ihrer Quittung aufgeteilt, Buchungen ohne
 * Quittung nach ihrer Kategorie; Quittungen ohne Buchung zählen über ihre Artikel.
 * Umbuchungen und Einkommen zählen nicht.
 */
export function buildNatureBreakdown(
  transactions: FinanceTransaction[],
  receipts: FinanceReceipt[],
  matches: FinanceMatch[],
  opts: { from?: string; to?: string; currency?: string } = {},
): NatureBreakdown {
  const { from, to, currency = 'CHF' } = opts;
  const inRange = (date: string) => (!from || date >= from) && (!to || date <= to);

  const receiptById = new Map(receipts.map((r) => [r.id, r]));
  const receiptForTx = new Map<string, FinanceReceipt>();
  for (const m of matches) {
    const r = receiptById.get(m.receipt_id);
    if (r) receiptForTx.set(m.transaction_id, r);
  }
  const matchedReceiptIds = new Set(matches.map((m) => m.receipt_id));

  const amounts: Record<Nature, number> = { essential: 0, treat: 0, occasional: 0 };
  const addSplit = (split: Record<Nature, number>) => { for (const n of NATURES) amounts[n] += split[n]; };

  for (const tx of transactions) {
    if (tx.currency !== currency || !inRange(tx.booking_date)) continue;
    const cat = effectiveCategory(tx);
    if (cat === TRANSFER_CATEGORY) continue;
    if (tx.amount > 0) {
      // Rückerstattung in einer Ausgaben-Kategorie mindert die Ausgabe, Einkommen zählt nicht
      if (cat !== INCOME_CATEGORY && cat !== UNCATEGORIZED) amounts[natureOf(null, cat)] -= tx.amount;
      continue;
    }
    const spent = -tx.amount;
    const receipt = receiptForTx.get(tx.id);
    const split = receipt ? splitByNature(spent, receipt) : null;
    if (split) addSplit(split);
    else amounts[natureOf(null, cat === INCOME_CATEGORY ? UNCATEGORIZED : cat)] += spent;
  }

  for (const r of receipts) {
    if (matchedReceiptIds.has(r.id) || r.currency !== currency) continue;
    if (!r.receipt_date || !inRange(r.receipt_date)) continue;
    const total = r.total_amount ?? 0;
    if (total <= 0) continue;
    addSplit(splitByNature(total, r) ?? { essential: total, treat: 0, occasional: 0 });
  }

  const total = NATURES.reduce((s, n) => s + amounts[n], 0);
  const rounded = Object.fromEntries(NATURES.map((n) => [n, round2(amounts[n])])) as Record<Nature, number>;
  const shares = Object.fromEntries(
    NATURES.map((n) => [n, total > 0 ? amounts[n] / total : 0]),
  ) as Record<Nature, number>;
  return { total: round2(total), amounts: rounded, shares };
}
