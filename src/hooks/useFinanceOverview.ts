import { useState, useCallback, useMemo } from 'react';
import { useFocusEffect } from 'expo-router';
import { supabase } from '../lib/supabase';
import { fetchAll } from '../lib/fetchAll';
import {
  availableMonths, buildMonthlyOverview, effectiveCategory, monthOf,
  type FinanceTransaction, type FinanceReceipt, type FinanceMatch,
} from '../lib/finance';
import { UNCATEGORIZED } from '../lib/categories';

interface ReceiptRow {
  id: string;
  receipt_date: string | null;
  total_amount: number | null;
  currency: string;
  receipt_items: Array<{ total_price: number | null; tags: string[] | null }> | null;
}

export function useFinanceOverview() {
  const [transactions, setTransactions] = useState<FinanceTransaction[]>([]);
  const [receipts, setReceipts] = useState<FinanceReceipt[]>([]);
  const [matches, setMatches] = useState<FinanceMatch[]>([]);
  const [selected, setSelected] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);

  const load = useCallback(async () => {
    setLoading(true);
    const [tx, rc, mt] = await Promise.all([
      fetchAll<FinanceTransaction>('bank_transactions', 'id, booking_date, amount, currency, description, category, match_status'),
      fetchAll<ReceiptRow>('receipts', 'id, receipt_date, total_amount, currency, receipt_items(total_price, tags)'),
      fetchAll<FinanceMatch>('receipt_matches', 'transaction_id, receipt_id'),
    ]);
    setTransactions(tx);
    setReceipts(rc.map(({ receipt_items, ...r }) => ({ ...r, items: receipt_items ?? [] })));
    setMatches(mt);
    setLoading(false);
  }, []);

  useFocusEffect(useCallback(() => { load(); }, [load]));

  const months = useMemo(() => availableMonths(transactions, receipts), [transactions, receipts]);
  const month = selected && months.includes(selected) ? selected : (months[0] ?? monthOf(new Date().toISOString()));

  const overview = useMemo(
    () => buildMonthlyOverview(month, transactions, receipts, matches),
    [month, transactions, receipts, matches],
  );

  const uncategorized = useMemo(
    () => transactions
      // Verknüpfte Buchungen werden über die Quittungs-Artikel kategorisiert
      .filter((t) => monthOf(t.booking_date) === month && t.amount < 0 && t.match_status !== 'matched'
        && effectiveCategory(t) === UNCATEGORIZED)
      .sort((a, b) => b.booking_date.localeCompare(a.booking_date)),
    [transactions, month],
  );

  const setTransactionCategory = useCallback(async (txId: string, category: string) => {
    const { error } = await supabase.from('bank_transactions').update({ category }).eq('id', txId);
    if (error) return;
    setTransactions((prev) => prev.map((t) => (t.id === txId ? { ...t, category } : t)));
  }, []);

  return {
    months, month, setMonth: setSelected, overview, uncategorized,
    hasBankTransactions: transactions.length > 0,
    loading, reload: load, setTransactionCategory,
  };
}
