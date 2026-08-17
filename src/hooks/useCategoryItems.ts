import { useState, useCallback } from 'react';
import { useFocusEffect } from 'expo-router';
import { supabase } from '../lib/supabase';
import { TAG_MAP } from '../lib/categories';
import type { BankMatch } from '../types/bank';

export interface CategoryItem {
  id: string;
  receipt_id: string;
  name: string;
  quantity: number | null;
  unit: string | null;
  total_price: number | null;
  tags: string[];
  receipt: {
    id: string;
    store_name: string;
    receipt_date: string | null;
    currency: string;
    bankMatch: BankMatch | null;
  };
}

function getGroupTags(group: string): string[] {
  return Object.entries(TAG_MAP)
    .filter(([, info]) => info.group === group)
    .map(([tag]) => tag);
}

export function useCategoryItems(group: string) {
  const [items, setItems]         = useState<CategoryItem[]>([]);
  const [loading, setLoading]     = useState(true);
  const [refreshing, setRefreshing] = useState(false);

  const load = useCallback(async (isRefresh = false) => {
    if (isRefresh) setRefreshing(true);
    else setLoading(true);

    const groupTags = getGroupTags(group);

    // Items whose tags overlap with any tag in this group
    const { data: itemRows } = await supabase
      .from('receipt_items')
      .select('id, receipt_id, name, quantity, unit, total_price, tags')
      .overlaps('tags', groupTags);

    if (!itemRows || itemRows.length === 0) {
      setItems([]);
      setLoading(false);
      setRefreshing(false);
      return;
    }

    // Fetch receipt metadata for all affected receipts
    const receiptIds = [...new Set(itemRows.map((i) => i.receipt_id))];
    const { data: receiptRows } = await supabase
      .from('receipts')
      .select('id, store_name, receipt_date, currency')
      .in('id', receiptIds)
      .order('receipt_date', { ascending: false });

    // Bankbeleg-Status pro Quittung (für "✅ Bankbeleg" / "⚠️ kein Bankbeleg" Badge)
    const { data: matchRows } = await supabase
      .from('receipt_matches')
      .select('receipt_id, transaction_id, match_type')
      .in('receipt_id', receiptIds);

    const transactionIds = [...new Set((matchRows ?? []).map((m) => m.transaction_id))];
    const bankMatchByReceipt: Record<string, BankMatch> = {};
    if (transactionIds.length > 0) {
      const { data: txRows } = await supabase
        .from('bank_transactions')
        .select('id, booking_date, amount, currency, description')
        .in('id', transactionIds);

      const txMap: Record<string, { booking_date: string; amount: number; currency: string; description: string }> = {};
      for (const t of txRows ?? []) {
        txMap[t.id] = { booking_date: t.booking_date, amount: t.amount, currency: t.currency, description: t.description };
      }
      for (const m of matchRows ?? []) {
        if (txMap[m.transaction_id]) bankMatchByReceipt[m.receipt_id] = { ...txMap[m.transaction_id], match_type: m.match_type };
      }
    }

    const receiptMap: Record<string, CategoryItem['receipt']> = {};
    for (const r of receiptRows ?? []) {
      receiptMap[r.id] = { ...r, bankMatch: bankMatchByReceipt[r.id] ?? null };
    }

    // Build sorted result
    const result: CategoryItem[] = itemRows
      .filter((item) => receiptMap[item.receipt_id])
      .map((item) => ({ ...item, receipt: receiptMap[item.receipt_id] }))
      .sort((a, b) => {
        const da = a.receipt.receipt_date ?? '';
        const db = b.receipt.receipt_date ?? '';
        return db.localeCompare(da);
      });

    setItems(result);
    setLoading(false);
    setRefreshing(false);
  }, [group]);

  useFocusEffect(useCallback(() => { load(); }, [load]));

  return { items, loading, refreshing, refresh: () => load(true) };
}
