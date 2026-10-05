import { useState, useCallback } from 'react';
import { useFocusEffect } from 'expo-router';
import { supabase } from '../lib/supabase';
import { fetchAll } from '../lib/fetchAll';
import { LEGACY_TAG_CATEGORY, itemCategory } from '../lib/categories';
import type { BankMatch } from '../types/bank';

export interface CategoryItem {
  id: string;
  receipt_id: string;
  name: string;
  quantity: number | null;
  unit: string | null;
  total_price: number | null;
  tags: string[];
  subcategory: string | null;
  is_adjustment: boolean | null;
  receipt: {
    id: string;
    store_name: string;
    receipt_date: string | null;
    currency: string;
    bankMatch: BankMatch | null;
  };
}

/** Chunkgrösse für `.in()`-Filter — lange ID-Listen sprengen sonst die URL-Länge. */
const IN_CHUNK = 100;

function chunk<T>(list: T[]): T[][] {
  const out: T[][] = [];
  for (let i = 0; i < list.length; i += IN_CHUNK) out.push(list.slice(i, i + IN_CHUNK));
  return out;
}

/** Hauptkategorie plus alte Tags, die darauf abgebildet werden. */
function getGroupTags(group: string): string[] {
  const legacy = Object.entries(LEGACY_TAG_CATEGORY).filter(([, c]) => c === group).map(([tag]) => tag);
  return [group, ...legacy];
}

type ItemRow = Omit<CategoryItem, 'receipt'>;

export function useCategoryItems(group: string) {
  const [items, setItems]         = useState<CategoryItem[]>([]);
  const [loading, setLoading]     = useState(true);
  const [refreshing, setRefreshing] = useState(false);

  const load = useCallback(async (isRefresh = false) => {
    if (isRefresh) setRefreshing(true);
    else setLoading(true);

    const groupTags = getGroupTags(group);

    // Artikel mit passendem Tag; tags[0] ist die Hauptkategorie, ältere Builds schrieben feine Tags
    const candidates = await fetchAll<ItemRow>(
      'receipt_items',
      'id, receipt_id, name, quantity, unit, total_price, tags, subcategory, is_adjustment',
      (q) => q.overlaps('tags', groupTags).order('id'),
    );
    const itemRows = candidates.filter((i) => itemCategory(i.tags) === group);

    if (itemRows.length === 0) {
      setItems([]);
      setLoading(false);
      setRefreshing(false);
      return;
    }

    // Fetch receipt metadata for all affected receipts
    const receiptIds = [...new Set(itemRows.map((i) => i.receipt_id))];
    const receiptRows: Array<{ id: string; store_name: string; receipt_date: string | null; currency: string }> = [];
    const matchRows: Array<{ receipt_id: string; transaction_id: string; match_type: BankMatch['match_type'] }> = [];
    for (const ids of chunk(receiptIds)) {
      const [{ data: rc }, { data: mt }] = await Promise.all([
        supabase.from('receipts').select('id, store_name, receipt_date, currency').in('id', ids),
        // Bankbeleg-Status pro Quittung (für "Bankbeleg" / "Kein Bankbeleg" Badge)
        supabase.from('receipt_matches').select('receipt_id, transaction_id, match_type').in('receipt_id', ids),
      ]);
      receiptRows.push(...(rc ?? []));
      matchRows.push(...(mt ?? []));
    }

    const transactionIds = [...new Set(matchRows.map((m) => m.transaction_id))];
    const bankMatchByReceipt: Record<string, BankMatch> = {};
    if (transactionIds.length > 0) {
      const txMap: Record<string, { booking_date: string; amount: number; currency: string; description: string }> = {};
      for (const ids of chunk(transactionIds)) {
        const { data: txRows } = await supabase
          .from('bank_transactions')
          .select('id, booking_date, amount, currency, description')
          .in('id', ids);
        for (const t of txRows ?? []) {
          txMap[t.id] = { booking_date: t.booking_date, amount: t.amount, currency: t.currency, description: t.description };
        }
      }
      for (const m of matchRows) {
        if (txMap[m.transaction_id]) bankMatchByReceipt[m.receipt_id] = { ...txMap[m.transaction_id], match_type: m.match_type };
      }
    }

    const receiptMap: Record<string, CategoryItem['receipt']> = {};
    for (const r of receiptRows) {
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
