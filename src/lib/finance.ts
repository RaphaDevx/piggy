/**
 * finance.ts — Finanzübersicht aus Bank-Buchungen + Quittungen.
 *
 * - Bank-Buchungen bekommen eine Kategorie (KI beim Import, sonst Regeln hier).
 * - Eine mit einer Quittung verknüpfte Ausgabe wird nach den Artikel-Kategorien
 *   der Quittung aufgeteilt (z. B. Migros-Einkauf → Lebensmittel + Haushalt).
 * - Quittungen ohne Buchung (Bargeld, Auszug fehlt) zählen zusätzlich.
 * - Umbuchungen (z. B. Kreditkarten-Rechnung vom Konto) zählen nie, sonst würden
 *   Kreditkarten-Ausgaben doppelt erscheinen.
 */
import {
  EXPENSE_CATEGORIES, INCOME_CATEGORY, TRANSFER_CATEGORY, UNCATEGORIZED, itemCategory,
} from './categories';

export interface FinanceTransaction {
  id: string;
  booking_date: string;
  amount: number;
  currency: string;
  description: string;
  category?: string | null;
  match_status?: string;
}

export interface FinanceReceipt {
  id: string;
  receipt_date: string | null;
  total_amount: number | null;
  currency: string;
  items: Array<{ total_price: number | null; tags: string[] | null }>;
}

export interface FinanceMatch {
  transaction_id: string;
  receipt_id: string;
}

export interface CategoryTotal {
  category: string;
  amount: number;
  /** Anteil an den Ausgaben (0–1) */
  share: number;
  fromBank: number;
  fromReceipts: number;
}

export interface MonthlyOverview {
  month: string;              // YYYY-MM
  currency: string;
  income: number;
  expenses: number;
  net: number;
  byCategory: CategoryTotal[];
  /** Anteil der Bank-Ausgaben, die mit einer Quittung belegt sind (0–1); null ohne Bank-Ausgaben */
  receiptCoverage: number | null;
  bankExpenses: number;
  /** Ausgaben aus Quittungen ohne Bank-Buchung (z. B. Bargeld) */
  receiptOnlyExpenses: number;
  transfers: number;
  uncategorizedCount: number;
  unmatchedExpenseCount: number;
}

// ── Kategorisierung von Buchungstexten ───────────────────────────────────────

const TRANSFER_PATTERN =
  /(kreditkart|viseca|cembra|swisscard|cornercard|cornèrcard|postfinance card|visa|mastercard).{0,40}(zahlung|rechnung|lsv|einzug)|(zahlung|rechnung|lsv|einzug).{0,40}(kreditkart|viseca|cembra|swisscard|cornercard|cornèrcard)|konto(ü|ue)bertrag|(ü|ue)bertrag (auf|von) (eigen|konto)|umbuchung|sparkonto/i;

const INCOME_PATTERN = /lohn|gehalt|sal(ä|ae)r|salary|rente|ahv|stipendium|gutschrift arbeitgeber/i;

// Reihenfolge zählt: spezifische Händler vor allgemeinen Begriffen.
const EXPENSE_RULES: Array<{ pattern: RegExp; category: string }> = [
  { pattern: /versicherung|krankenkasse|\bcss\b|helsana|swica|sanitas|visana|concordia|groupe mutuel|\baxa\b|mobiliar|allianz|generali|helvetia|netflix|spotify|apple\.com|itunes|google \*|youtube|disney|swisscom|\bsalt\b|sunrise|wingo|yallo|serafe|abonnement|\babo\b/i, category: 'Versicherungen & Abos' },
  { pattern: /miete|mietzins|liegenschaft|immobilien|verwaltung|nebenkosten|\bewz\b|\bekz\b|elektrizit|stromrechnung|wasserwerk/i, category: 'Wohnen & Nebenkosten' },
  { pattern: /\bsbb\b|\bbls\b|\bzvv\b|postauto|tankstelle|\bshell\b|\bavia\b|\bbp\b|agrola|\beni\b|socar|migrol|tamoil|parking|parkhaus|uber(?! eats)|\bbolt\b|mobility|publibike|easyjet|swiss intl|flughafen/i, category: 'Mobilität' },
  { pattern: /restaurant|mcdonald|burger king|starbucks|caf(é|e)\b|pizzeria|kebab|sushi|take ?away|uber eats|just eat|smood|subway|dean ?& ?david|tibits|holy cow|bistro|brasserie|\bbar\b/i, category: 'Restaurant & Take-away' },
  { pattern: /migros(?!.*(do it|micasa))|\bcoop\b(?!.*(pronto|bau))|denner|\baldi\b|\blidl\b|\bvolg\b|\bspar\b|alnatura|b(ä|ae)ckerei|metzgerei|globus delica|manor food|farmy/i, category: 'Lebensmittel' },
  { pattern: /apotheke|amavita|sun ?store|topwell|dropa|drogerie|\bdm\b|m(ü|ue)ller|arzt|zahnarzt|praxis|spital|physio|optiker|fielmann/i, category: 'Körperpflege & Gesundheit' },
  { pattern: /\bikea\b|jumbo|hornbach|bauhaus|\bobi\b|do it|micasa|interio|pfister|landi|coop bau/i, category: 'Haushalt' },
  { pattern: /zalando|h ?& ?m|\bzara\b|galaxus|digitec|interdiscount|media ?markt|\bfust\b|ochsner|manor|globus|amazon|aliexpress|\bkino\b|path(é|e)|fitness|ticketcorner|steam|playstation|nintendo|decathlon|sportx|bücher|orell f/i, category: 'Freizeit & Shopping' },
  { pattern: /coop pronto|migrolino|avec\b|k kiosk|kiosk/i, category: 'Lebensmittel' },
];

/** Regel-basierte Kategorie eines Buchungstexts. */
export function categorizeTransaction(description: string, amount: number): string {
  const text = description.toLowerCase();
  if (TRANSFER_PATTERN.test(text)) return TRANSFER_CATEGORY;
  if (amount > 0) return INCOME_CATEGORY;
  if (INCOME_PATTERN.test(text)) return INCOME_CATEGORY;
  return EXPENSE_RULES.find((r) => r.pattern.test(text))?.category ?? UNCATEGORIZED;
}

const KNOWN_TX_CATEGORIES = new Set<string>([
  ...EXPENSE_CATEGORIES.map((c) => c.key), INCOME_CATEGORY, TRANSFER_CATEGORY,
]);

/** Gespeicherte Kategorie (manuell/KI) vor Regel-Ergebnis. */
export function effectiveCategory(tx: FinanceTransaction): string {
  const stored = tx.category?.trim();
  if (stored) return stored;
  return categorizeTransaction(tx.description, tx.amount);
}

/** KI-Ergebnis übernehmen, wenn es eine bekannte Kategorie ist, sonst Regeln. */
export function normalizeTransactionCategory(category: string | null | undefined, description: string, amount: number): string {
  const c = category?.trim();
  return c && KNOWN_TX_CATEGORIES.has(c) ? c : categorizeTransaction(description, amount);
}

// ── Duplikate beim Import ────────────────────────────────────────────────────

type TxKeyFields = Pick<FinanceTransaction, 'booking_date' | 'amount' | 'description'>;

function txKey(t: TxKeyFields): string {
  return `${t.booking_date}|${Number(t.amount).toFixed(2)}|${t.description.toLowerCase().replace(/\s+/g, ' ').trim()}`;
}

/**
 * Entfernt Buchungen, die schon gespeichert sind (gleiches Datum, Betrag, Text).
 * Als Multiset: zwei identische Buchungen im Auszug bleiben zwei, wenn bisher keine existiert.
 */
export function dedupeTransactions<T extends TxKeyFields>(incoming: T[], existing: TxKeyFields[]): T[] {
  const remaining = new Map<string, number>();
  for (const t of existing) remaining.set(txKey(t), (remaining.get(txKey(t)) ?? 0) + 1);
  return incoming.filter((t) => {
    const key = txKey(t);
    const left = remaining.get(key) ?? 0;
    if (left > 0) { remaining.set(key, left - 1); return false; }
    return true;
  });
}

// ── Monatsübersicht ──────────────────────────────────────────────────────────

const round2 = (n: number) => Math.round(n * 100) / 100;
export const monthOf = (date: string) => date.slice(0, 7);

/** Verteilt einen Betrag auf die Artikel-Kategorien einer Quittung (anteilig nach Artikelpreis). */
export function splitByItemCategories(amount: number, receipt: FinanceReceipt): Record<string, number> {
  const weights: Record<string, number> = {};
  for (const item of receipt.items) {
    const price = item.total_price ?? 0;
    if (price <= 0) continue;
    const cat = itemCategory(item.tags);
    weights[cat] = (weights[cat] ?? 0) + price;
  }
  const total = Object.values(weights).reduce((a, b) => a + b, 0);
  if (total <= 0) return { Diverses: amount };
  return Object.fromEntries(Object.entries(weights).map(([c, w]) => [c, (amount * w) / total]));
}

export function availableMonths(transactions: FinanceTransaction[], receipts: FinanceReceipt[]): string[] {
  const months = new Set<string>();
  for (const t of transactions) months.add(monthOf(t.booking_date));
  for (const r of receipts) if (r.receipt_date) months.add(monthOf(r.receipt_date));
  return [...months].sort().reverse();
}

export function buildMonthlyOverview(
  month: string,
  transactions: FinanceTransaction[],
  receipts: FinanceReceipt[],
  matches: FinanceMatch[],
  currency = 'CHF',
): MonthlyOverview {
  const receiptById = new Map(receipts.map((r) => [r.id, r]));
  const receiptForTx = new Map<string, FinanceReceipt>();
  for (const m of matches) {
    const r = receiptById.get(m.receipt_id);
    if (r) receiptForTx.set(m.transaction_id, r);
  }
  const matchedReceiptIds = new Set(matches.map((m) => m.receipt_id));

  const bank: Record<string, number> = {};
  const fromReceipts: Record<string, number> = {};
  const add = (target: Record<string, number>, cat: string, amount: number) => {
    target[cat] = (target[cat] ?? 0) + amount;
  };

  let income = 0, transfers = 0, bankExpenses = 0, coveredExpenses = 0;
  let uncategorizedCount = 0, unmatchedExpenseCount = 0;

  for (const tx of transactions) {
    if (tx.currency !== currency || monthOf(tx.booking_date) !== month) continue;
    const cat = effectiveCategory(tx);
    if (cat === TRANSFER_CATEGORY) { transfers += Math.abs(tx.amount); continue; }

    if (tx.amount > 0) {
      // Gutschrift in einer Ausgaben-Kategorie = Rückerstattung → reduziert die Ausgabe
      if (cat === INCOME_CATEGORY || cat === UNCATEGORIZED) income += tx.amount;
      else add(bank, cat, -tx.amount);
      continue;
    }

    const spent = -tx.amount;
    bankExpenses += spent;
    const receipt = receiptForTx.get(tx.id);
    if (receipt) {
      coveredExpenses += spent;
      for (const [c, a] of Object.entries(splitByItemCategories(spent, receipt))) add(bank, c, a);
    } else {
      if (tx.match_status !== 'ignored') unmatchedExpenseCount++;
      if (cat === UNCATEGORIZED) uncategorizedCount++;
      add(bank, cat === INCOME_CATEGORY ? UNCATEGORIZED : cat, spent);
    }
  }

  let receiptOnlyExpenses = 0;
  for (const r of receipts) {
    if (matchedReceiptIds.has(r.id) || r.currency !== currency) continue;
    if (!r.receipt_date || monthOf(r.receipt_date) !== month) continue;
    const total = r.total_amount ?? 0;
    if (total <= 0) continue;
    receiptOnlyExpenses += total;
    for (const [c, a] of Object.entries(splitByItemCategories(total, r))) add(fromReceipts, c, a);
  }

  const categories = new Set([...Object.keys(bank), ...Object.keys(fromReceipts)]);
  const rows = [...categories].map((category) => {
    const b = bank[category] ?? 0, q = fromReceipts[category] ?? 0;
    return { category, amount: b + q, fromBank: round2(b), fromReceipts: round2(q) };
  }).filter((r) => Math.abs(r.amount) >= 0.005);

  const expenses = rows.reduce((s, r) => s + r.amount, 0);
  const byCategory: CategoryTotal[] = rows
    .map((r) => ({ ...r, amount: round2(r.amount), share: expenses > 0 ? r.amount / expenses : 0 }))
    .sort((a, b) => b.amount - a.amount);

  return {
    month,
    currency,
    income: round2(income),
    expenses: round2(expenses),
    net: round2(income - expenses),
    byCategory,
    receiptCoverage: bankExpenses > 0 ? coveredExpenses / bankExpenses : null,
    bankExpenses: round2(bankExpenses),
    receiptOnlyExpenses: round2(receiptOnlyExpenses),
    transfers: round2(transfers),
    uncategorizedCount,
    unmatchedExpenseCount,
  };
}
