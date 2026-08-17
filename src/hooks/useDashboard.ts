import { useState, useCallback } from 'react';
import { useFocusEffect } from 'expo-router';
import { supabase } from '../lib/supabase';
import { TAG_GROUPS } from '../lib/categories';
import type { Receipt, SpendingByTag, StoreSpending, Period } from '../types/receipt';

export interface DashboardData {
  total: number;
  currency: string;
  byTag: SpendingByTag[];
  byGroup: SpendingByTag[];
  topStores: StoreSpending[];
  recentReceipts: (Receipt & { allTags: string[] })[];
  avgWeekly: number;
  rangeLabel: string;
  periodDays: number;
}

// ── ISO-Wochennummer ─────────────────────────────────────────────────────────
function isoWeek(date: Date): number {
  const d = new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()));
  d.setUTCDate(d.getUTCDate() + 4 - (d.getUTCDay() || 7));
  const y = new Date(Date.UTC(d.getUTCFullYear(), 0, 1));
  return Math.ceil((((d.getTime() - y.getTime()) / 86400000) + 1) / 7);
}

function toStr(d: Date) { return d.toISOString().split('T')[0]; }

const DE_MONTHS = ['Jan', 'Feb', 'Mär', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dez'];
const DE_MONTHS_LONG = ['Januar', 'Februar', 'März', 'April', 'Mai', 'Juni', 'Juli', 'August', 'September', 'Oktober', 'November', 'Dezember'];

// ── Datumsbereich für Periode + Offset ──────────────────────────────────────
export function periodRange(period: Period, offset: number): { start: string; end: string; label: string; days: number } {
  const now = new Date();

  switch (period) {
    case 'week': {
      const ref = new Date(now);
      ref.setDate(ref.getDate() + offset * 7);
      const dow = ref.getDay() === 0 ? 7 : ref.getDay();
      const mon = new Date(ref);
      mon.setDate(ref.getDate() - dow + 1);
      const sun = new Date(mon);
      sun.setDate(mon.getDate() + 6);
      return {
        start: toStr(mon),
        end:   toStr(sun),
        label: `KW ${isoWeek(mon)} ${mon.getFullYear()}`,
        days:  7,
      };
    }

    case 'month': {
      const m = new Date(now.getFullYear(), now.getMonth() + offset, 1);
      const start = new Date(m.getFullYear(), m.getMonth(), 1);
      const end   = new Date(m.getFullYear(), m.getMonth() + 1, 0);
      const days  = end.getDate();
      return {
        start: toStr(start),
        end:   toStr(end),
        label: `${DE_MONTHS_LONG[start.getMonth()]} ${start.getFullYear()}`,
        days,
      };
    }

    case '3months': {
      // Aktuelles Quartal + offset Quartale
      const qm    = Math.floor(now.getMonth() / 3) * 3 + offset * 3;
      const startYear  = now.getFullYear() + Math.floor(qm / 12);
      const startMonth = ((qm % 12) + 12) % 12;
      const start = new Date(startYear, startMonth, 1);
      const end   = new Date(startYear, startMonth + 3, 0);
      const days  = Math.round((end.getTime() - start.getTime()) / 86400000) + 1;
      return {
        start: toStr(start),
        end:   toStr(end),
        label: `${DE_MONTHS[start.getMonth()]}–${DE_MONTHS[end.getMonth()]} ${end.getFullYear()}`,
        days,
      };
    }

    case 'year': {
      const year = now.getFullYear() + offset;
      return {
        start: `${year}-01-01`,
        end:   `${year}-12-31`,
        label: String(year),
        days:  365,
      };
    }
  }
}

// ── Daten laden ──────────────────────────────────────────────────────────────
async function fetchDashboardData(period: Period, offset: number): Promise<DashboardData> {
  const range = periodRange(period, offset);

  const { data: receiptRows } = await supabase
    .from('receipts')
    .select('*')
    .gte('receipt_date', range.start)
    .lte('receipt_date', range.end)
    .order('receipt_date', { ascending: false });

  const rows = receiptRows ?? [];
  const ids  = rows.map((r) => r.id);

  const { data: itemRows } = ids.length > 0
    ? await supabase.from('receipt_items').select('receipt_id, tags, total_price').in('receipt_id', ids)
    : { data: [] };

  const items    = itemRows ?? [];
  const total    = rows.reduce((s, r) => s + (r.total_amount ?? 0), 0);
  const currency = rows[0]?.currency ?? 'CHF';

  // Ausgaben nach Tag
  const tagTotals: Record<string, number> = {};
  for (const item of items) {
    const price = (item.total_price ?? 0) / Math.max((item.tags ?? []).length, 1);
    for (const tag of item.tags ?? []) {
      tagTotals[tag] = (tagTotals[tag] ?? 0) + price;
    }
  }
  const byTag: SpendingByTag[] = Object.entries(tagTotals)
    .map(([tag, total]) => ({ tag, total }))
    .sort((a, b) => b.total - a.total);

  // Ausgaben nach Gruppe
  const groupTotals: Record<string, number> = {};
  for (const { tag, total } of byTag) {
    const group = Object.keys(TAG_GROUPS).find((g) =>
      tag.toLowerCase().includes(g.toLowerCase()) || tag === g
    ) ?? 'Diverses';
    groupTotals[group] = (groupTotals[group] ?? 0) + total;
  }
  const byGroup: SpendingByTag[] = Object.entries(groupTotals)
    .map(([tag, total]) => ({ tag, total }))
    .sort((a, b) => b.total - a.total);

  // Top Geschäfte
  const storeTotals: Record<string, { total: number; count: number }> = {};
  for (const r of rows) {
    if (!storeTotals[r.store_name]) storeTotals[r.store_name] = { total: 0, count: 0 };
    storeTotals[r.store_name].total += r.total_amount ?? 0;
    storeTotals[r.store_name].count++;
  }
  const topStores: StoreSpending[] = Object.entries(storeTotals)
    .map(([store_name, { total, count }]) => ({ store_name, total, count }))
    .sort((a, b) => b.total - a.total)
    .slice(0, 5);

  // Letzte Quittungen mit Tags
  const tagsByReceipt: Record<string, string[]> = {};
  for (const item of items) {
    if (!tagsByReceipt[item.receipt_id]) tagsByReceipt[item.receipt_id] = [];
    for (const tag of item.tags ?? []) {
      if (!tagsByReceipt[item.receipt_id].includes(tag)) tagsByReceipt[item.receipt_id].push(tag);
    }
  }
  const recentReceipts = rows.slice(0, 5).map((r) => ({ ...r, allTags: tagsByReceipt[r.id] ?? [] }));

  const avgWeekly = (total / range.days) * 7;

  return {
    total, currency, byTag, byGroup, topStores, recentReceipts,
    avgWeekly, rangeLabel: range.label, periodDays: range.days,
  };
}

// ── Hook ─────────────────────────────────────────────────────────────────────
export function useDashboard() {
  const [period, setPeriod]   = useState<Period>('month');
  const [offset, setOffset]   = useState(0);
  const [data, setData]       = useState<DashboardData | null>(null);
  const [loading, setLoading] = useState(true);
  const [refreshing, setRefreshing] = useState(false);

  const load = useCallback(async (p: Period, o: number, isRefresh = false) => {
    if (isRefresh) setRefreshing(true);
    else setLoading(true);
    const result = await fetchDashboardData(p, o);
    setData(result);
    setLoading(false);
    setRefreshing(false);
  }, []);

  useFocusEffect(useCallback(() => { load(period, offset); }, [period, offset, load]));

  function changePeriod(p: Period) {
    setPeriod(p);
    setOffset(0);
    load(p, 0);
  }

  function goPrev() {
    const newOffset = offset - 1;
    setOffset(newOffset);
    load(period, newOffset);
  }

  function goNext() {
    if (offset >= 0) return;
    const newOffset = offset + 1;
    setOffset(newOffset);
    load(period, newOffset);
  }

  const refresh = useCallback(() => load(period, offset, true), [period, offset, load]);

  return { period, offset, data, loading, refreshing, changePeriod, goPrev, goNext, refresh };
}
