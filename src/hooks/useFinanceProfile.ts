import { useState, useCallback, useMemo } from 'react';
import { useFocusEffect } from 'expo-router';
import { fetchAll } from '../lib/fetchAll';
import { buildFinanceProfile, buildNatureBreakdown } from '../lib/profile';
import type { FinanceTransaction, FinanceReceipt, FinanceMatch } from '../lib/finance';

export type ProfileRange = '3' | '6' | '12' | 'all';

export const PROFILE_RANGES: { key: ProfileRange; label: string }[] = [
  { key: '3',   label: '3 Monate' },
  { key: '6',   label: '6 Monate' },
  { key: '12',  label: '12 Monate' },
  { key: 'all', label: 'Alles' },
];

interface ReceiptRow {
  id: string;
  receipt_date: string | null;
  total_amount: number | null;
  currency: string;
  receipt_items: Array<{ total_price: number | null; tags: string[] | null; subcategory: string | null }> | null;
}

/** Erster Tag des Monats, `months - 1` Monate zurück (aktueller Monat zählt mit). */
function rangeStart(range: ProfileRange): string | undefined {
  if (range === 'all') return undefined;
  const d = new Date();
  const start = new Date(d.getFullYear(), d.getMonth() - (Number(range) - 1), 1);
  const mm = String(start.getMonth() + 1).padStart(2, '0');
  return `${start.getFullYear()}-${mm}-01`;
}

export function useFinanceProfile() {
  const [transactions, setTransactions] = useState<FinanceTransaction[]>([]);
  const [receipts, setReceipts] = useState<FinanceReceipt[]>([]);
  const [matches, setMatches] = useState<FinanceMatch[]>([]);
  const [range, setRange] = useState<ProfileRange>('6');
  const [loading, setLoading] = useState(true);

  const load = useCallback(async () => {
    setLoading(true);
    const [tx, rc, mt] = await Promise.all([
      fetchAll<FinanceTransaction>('bank_transactions', 'id, booking_date, amount, currency, description, category, match_status'),
      fetchAll<ReceiptRow>('receipts', 'id, receipt_date, total_amount, currency, receipt_items(total_price, tags, subcategory)'),
      fetchAll<FinanceMatch>('receipt_matches', 'transaction_id, receipt_id'),
    ]);
    setTransactions(tx);
    setReceipts(rc.map(({ receipt_items, ...r }) => ({ ...r, items: receipt_items ?? [] })));
    setMatches(mt);
    setLoading(false);
  }, []);

  useFocusEffect(useCallback(() => { load(); }, [load]));

  const profile = useMemo(
    () => buildFinanceProfile(transactions, receipts, matches, { from: rangeStart(range) }),
    [transactions, receipts, matches, range],
  );

  const nature = useMemo(
    () => buildNatureBreakdown(transactions, receipts, matches, { from: rangeStart(range) }),
    [transactions, receipts, matches, range],
  );

  return { profile, nature, range, setRange, loading, reload: load };
}
