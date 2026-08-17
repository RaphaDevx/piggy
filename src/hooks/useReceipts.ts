import { useState, useCallback, useMemo } from 'react';
import { useFocusEffect } from 'expo-router';
import { supabase } from '../lib/supabase';
import type { Receipt } from '../types/receipt';

export interface ReceiptWithTags extends Receipt {
  allTags: string[];
}

export type SortKey = 'date_desc' | 'date_asc' | 'amount_desc' | 'amount_asc' | 'store_asc';

async function fetchReceipts(): Promise<ReceiptWithTags[]> {
  const { data: receiptRows } = await supabase
    .from('receipts')
    .select('*')
    .order('receipt_date', { ascending: false })
    .order('created_at',   { ascending: false });

  if (!receiptRows) return [];

  const ids = receiptRows.map((r) => r.id);
  const { data: itemRows } = await supabase
    .from('receipt_items')
    .select('receipt_id, tags')
    .in('receipt_id', ids);

  const tagsByReceipt: Record<string, string[]> = {};
  for (const item of itemRows ?? []) {
    if (!tagsByReceipt[item.receipt_id]) tagsByReceipt[item.receipt_id] = [];
    for (const tag of item.tags ?? []) {
      if (!tagsByReceipt[item.receipt_id].includes(tag)) tagsByReceipt[item.receipt_id].push(tag);
    }
  }

  return receiptRows.map((r) => ({ ...r, allTags: tagsByReceipt[r.id] ?? [] }));
}

export function useReceipts() {
  const [receipts, setReceipts]         = useState<ReceiptWithTags[]>([]);
  const [loading, setLoading]           = useState(true);
  const [refreshing, setRefreshing]     = useState(false);
  const [search, setSearch]             = useState('');
  const [sortKey, setSortKey]           = useState<SortKey>('date_desc');
  const [filterTag, setFilterTag]       = useState<string | null>(null);  // Kategorie-Filter
  const [filterPeriod, setFilterPeriod] = useState<'all' | 'week' | 'month' | '3months'>('all');

  const load = useCallback(async (isRefresh = false) => {
    if (isRefresh) setRefreshing(true);
    else setLoading(true);
    const data = await fetchReceipts();
    setReceipts(data);
    setLoading(false);
    setRefreshing(false);
  }, []);

  useFocusEffect(useCallback(() => { load(); }, [load]));

  // Zeitraum-Start für Filter
  const periodStart = useMemo(() => {
    if (filterPeriod === 'all') return null;
    const now = new Date();
    const days = { week: 7, month: 30, '3months': 91 }[filterPeriod];
    const d = new Date(now.getFullYear(), now.getMonth(), now.getDate() - days);
    return d.toISOString().split('T')[0];
  }, [filterPeriod]);

  const filtered = useMemo(() => {
    let list = [...receipts];

    // Textsuche (Geschäft, Kategorie, Tags)
    if (search) {
      const q = search.toLowerCase();
      list = list.filter((r) =>
        r.store_name.toLowerCase().includes(q) ||
        r.store_category?.toLowerCase().includes(q) ||
        r.allTags.some((t) => t.toLowerCase().includes(q))
      );
    }

    // Tag-Filter
    if (filterTag) {
      list = list.filter((r) => r.allTags.includes(filterTag));
    }

    // Zeitraum-Filter
    if (periodStart) {
      list = list.filter((r) => (r.receipt_date ?? '') >= periodStart);
    }

    // Sortierung
    list.sort((a, b) => {
      switch (sortKey) {
        case 'date_desc': return (b.receipt_date ?? '') < (a.receipt_date ?? '') ? -1 : 1;
        case 'date_asc':  return (a.receipt_date ?? '') < (b.receipt_date ?? '') ? -1 : 1;
        case 'amount_desc': return (b.total_amount ?? 0) - (a.total_amount ?? 0);
        case 'amount_asc':  return (a.total_amount ?? 0) - (b.total_amount ?? 0);
        case 'store_asc':   return a.store_name.localeCompare(b.store_name, 'de');
        default: return 0;
      }
    });

    return list;
  }, [receipts, search, filterTag, periodStart, sortKey]);

  // Alle vorhandenen Tags für Filter-Chips
  const availableTags = useMemo(() => {
    const tags = new Set<string>();
    for (const r of receipts) {
      for (const t of r.allTags) tags.add(t);
    }
    return [...tags].sort();
  }, [receipts]);

  return {
    receipts,
    filtered,
    loading,
    refreshing,
    search,     setSearch,
    sortKey,    setSortKey,
    filterTag,  setFilterTag,
    filterPeriod, setFilterPeriod,
    availableTags,
    refresh: () => load(true),
  };
}
