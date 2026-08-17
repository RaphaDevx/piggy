import type { BankTransaction, AccountType } from '../types/bank';
import type { Receipt } from '../types/receipt';

const MATCH_THRESHOLD = 0.6;

// Betrag: 1.0 bei exakter Übereinstimmung, linear abfallend bis 0 bei Abweichung
// >= max(1 CHF, 5% des Betrags).
function amountScore(transactionAmount: number, receiptTotal: number): number {
  const diff = Math.abs(transactionAmount - receiptTotal);
  if (diff <= 0.02) return 1;
  const tolerance = Math.max(1, receiptTotal * 0.05);
  if (diff >= tolerance) return 0;
  return 1 - diff / tolerance;
}

// Datum: bei Debit/TWINT/Bargeld ±2 Tage um den Kaufzeitpunkt.
// Bei Kreditkarte erscheint die Buchung erst Tage/Wochen später auf der
// Abrechnung (0–45 Tage nach dem Kauf), nie davor.
function dateScore(bookingDate: string, receiptDate: string, accountType: AccountType): number {
  const diffDays = (Date.parse(bookingDate) - Date.parse(receiptDate)) / 86_400_000;

  if (accountType === 'debit') {
    const absDiff = Math.abs(diffDays);
    if (absDiff > 2) return 0;
    return 1 - absDiff / 2;
  }

  if (diffDays < 0 || diffDays > 45) return 0;
  return 1 - diffDays / 45;
}

function normalize(text: string): string {
  return text.toLowerCase().replace(/[^a-z0-9]+/g, ' ').trim();
}

// Anteil der (signifikanten) Wörter aus dem Geschäftsnamen, die im
// Bank-Buchungstext vorkommen (z.B. "Migros" in "MIGROS MM ZUERICH").
function merchantScore(description: string, storeName: string): number {
  const desc  = normalize(description);
  const store = normalize(storeName);
  if (!desc || !store) return 0;

  const storeTokens = store.split(' ').filter((t) => t.length >= 3);
  if (storeTokens.length === 0) return desc.includes(store) || store.includes(desc) ? 1 : 0;

  const matched = storeTokens.filter((t) => desc.includes(t)).length;
  return matched / storeTokens.length;
}

export function scoreMatch(transaction: BankTransaction, receipt: Receipt, accountType: AccountType): number {
  if (transaction.currency !== receipt.currency) return 0;
  if (receipt.receipt_date == null || receipt.total_amount == null) return 0;

  const aScore = amountScore(Math.abs(transaction.amount), receipt.total_amount);
  const dScore = dateScore(transaction.booking_date, receipt.receipt_date, accountType);
  const mScore = merchantScore(transaction.description, receipt.store_name);

  return aScore * 0.5 + dScore * 0.3 + mScore * 0.2;
}

export interface MatchResult {
  transactionId: string;
  receiptId: string;
  score: number;
}

/**
 * Greedy 1:1-Matching: für jede unmatched Ausgaben-Transaktion den besten
 * Kandidaten über dem Schwellwert finden. Jede Quittung/Transaktion wird
 * höchstens einmal verwendet (höchster Score gewinnt).
 *
 * `receipts` sollte bereits auf Quittungen ohne bestehenden Match eingeschränkt sein.
 */
export function runMatching(
  transactions: BankTransaction[],
  receipts: Receipt[],
  accountType: AccountType
): MatchResult[] {
  const expenseTx = transactions.filter((t) => t.amount < 0 && t.match_status === 'unmatched');

  const candidates: MatchResult[] = [];
  for (const tx of expenseTx) {
    for (const receipt of receipts) {
      const score = scoreMatch(tx, receipt, accountType);
      if (score >= MATCH_THRESHOLD) {
        candidates.push({ transactionId: tx.id, receiptId: receipt.id, score });
      }
    }
  }

  candidates.sort((a, b) => b.score - a.score);

  const usedTransactions = new Set<string>();
  const usedReceipts = new Set<string>();
  const results: MatchResult[] = [];

  for (const c of candidates) {
    if (usedTransactions.has(c.transactionId) || usedReceipts.has(c.receiptId)) continue;
    usedTransactions.add(c.transactionId);
    usedReceipts.add(c.receiptId);
    results.push(c);
  }

  return results;
}
