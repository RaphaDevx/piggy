/**
 * discrepancies.ts — Beinahe-Treffer zwischen Bank-Ausgaben und Quittungen.
 *
 * Der exakte Abgleich (matching.ts) verknüpft nur Paare mit passendem Betrag.
 * Hier werden Paare gefunden, bei denen Datum und Händler passen, der Betrag
 * aber abweicht, und es wird ein plausibler Grund vorgeschlagen
 * (Trinkgeld, Fremdwährung, Rabatt, Teilzahlung, sonstiges).
 */
import { itemCategory } from './categories.ts';
import {
  dateScore, merchantScore, scoreMatch,
  type AccountType, type MatchReceipt, type MatchTransaction,
} from './matching.ts';

export type SuggestionKind = 'tip' | 'fx' | 'discount' | 'partial' | 'other';

export interface SuggestionReceipt extends MatchReceipt {
  store_category?: string | null;
  items?: Array<{ total_price: number | null; tags: string[] | null }>;
}

export interface SuggestionCandidate {
  transactionId: string;
  receiptId: string;
  kind: SuggestionKind;
  /** Bank minus Quittung (positiv = Bank höher); bei `fx` in CHF gegenüber dem erwarteten Betrag */
  diffAmount: number;
  score: number;
}

export const DEFAULT_FX_RATES: Record<string, number> = { EUR: 0.94, USD: 0.80 };

const MIN_PART_SCORE = 0.5;
const EXACT_TOLERANCE = 0.02;
const AUTO_MATCH_THRESHOLD = 0.6;
const TIP_MAX_RATIO = 0.3;
const OTHER_MAX_RATIO = 0.1;
const DISCOUNT_MAX_RATIO = 0.2;
const PARTIAL_MAX_RATIO = 0.8;
const FX_MIN_RATIO = 0.9;
const FX_MAX_RATIO = 1.12;
const EPS = 1e-9;
const RESTAURANT_STORE_CATEGORY = 'Restaurant';
const RESTAURANT_ITEM_CATEGORY = 'Restaurant & Take-away';

const round2 = (n: number) => Math.round(n * 100) / 100;

function isRestaurant(receipt: SuggestionReceipt): boolean {
  if (receipt.store_category === RESTAURANT_STORE_CATEGORY) return true;
  const items = receipt.items ?? [];
  if (items.length === 0) return false;
  const restaurantItems = items.filter((i) => itemCategory(i.tags) === RESTAURANT_ITEM_CATEGORY).length;
  return restaurantItems * 2 > items.length;
}

function classify(
  tx: MatchTransaction,
  receipt: SuggestionReceipt,
  total: number,
  accountType: AccountType,
  fxRates: Record<string, number>,
): { kind: SuggestionKind; diffAmount: number } | null {
  const spent = Math.abs(tx.amount);

  if (tx.currency === receipt.currency) {
    const diff = round2(spent - total);
    if (Math.abs(diff) <= EXACT_TOLERANCE) return null;
    if (scoreMatch(tx, receipt, accountType) >= AUTO_MATCH_THRESHOLD) return null;
    const ratio = Math.abs(diff) / total;
    if (diff > 0) {
      if (isRestaurant(receipt) && ratio <= TIP_MAX_RATIO + EPS) return { kind: 'tip', diffAmount: diff };
      if (!isRestaurant(receipt) && ratio <= OTHER_MAX_RATIO + EPS) return { kind: 'other', diffAmount: diff };
      return null;
    }
    if (ratio <= DISCOUNT_MAX_RATIO + EPS) return { kind: 'discount', diffAmount: diff };
    if (ratio <= PARTIAL_MAX_RATIO + EPS) return { kind: 'partial', diffAmount: diff };
    return null;
  }

  const rate = fxRates[receipt.currency];
  if (tx.currency === 'CHF' && rate && rate > 0) {
    const expected = total * rate;
    const ratio = spent / expected;
    if (ratio >= FX_MIN_RATIO - EPS && ratio <= FX_MAX_RATIO + EPS) {
      return { kind: 'fx', diffAmount: round2(spent - expected) };
    }
  }
  return null;
}

/**
 * Greedy 1:1: Kandidaten nach Score absteigend, jede Buchung und jede Quittung
 * höchstens einmal. `exclude` enthält bereits vorgeschlagene/abgelehnte Paare
 * als `${transactionId}|${receiptId}`.
 */
export function findSuggestions(
  transactions: MatchTransaction[],
  receipts: SuggestionReceipt[],
  accountType: AccountType,
  exclude: Set<string> = new Set(),
  fxRates: Record<string, number> = DEFAULT_FX_RATES,
): SuggestionCandidate[] {
  const expenseTx = transactions.filter((t) => t.amount < 0 && t.match_status === 'unmatched');

  const candidates: SuggestionCandidate[] = [];
  for (const tx of expenseTx) {
    for (const receipt of receipts) {
      const total = receipt.total_amount;
      if (receipt.receipt_date == null || total == null || total <= 0) continue;
      if (exclude.has(`${tx.id}|${receipt.id}`)) continue;

      const dScore = dateScore(tx.booking_date, receipt.receipt_date, accountType);
      const mScore = merchantScore(tx.description, receipt.store_name);
      if (dScore < MIN_PART_SCORE || mScore < MIN_PART_SCORE) continue;

      const verdict = classify(tx, receipt, total, accountType, fxRates);
      if (!verdict) continue;
      candidates.push({
        transactionId: tx.id, receiptId: receipt.id,
        kind: verdict.kind, diffAmount: verdict.diffAmount,
        score: 0.5 * dScore + 0.5 * mScore,
      });
    }
  }

  candidates.sort((a, b) => b.score - a.score);

  const usedTransactions = new Set<string>();
  const usedReceipts = new Set<string>();
  const results: SuggestionCandidate[] = [];
  for (const c of candidates) {
    if (usedTransactions.has(c.transactionId) || usedReceipts.has(c.receiptId)) continue;
    usedTransactions.add(c.transactionId);
    usedReceipts.add(c.receiptId);
    results.push(c);
  }
  return results;
}
