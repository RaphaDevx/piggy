import { useState, useCallback, useEffect } from 'react';
import { supabase } from '../lib/supabase';
import type { MatchSuggestion, SuggestionKind } from '../types/bank';

export interface SuggestionWithData extends MatchSuggestion {
  transaction: { booking_date: string; amount: number; description: string; currency?: string } | null;
  receipt: { store_name: string; receipt_date: string | null; total_amount: number | null; currency: string } | null;
}

interface SuggestionRow extends MatchSuggestion {
  bank_transactions: SuggestionWithData['transaction'] | SuggestionWithData['transaction'][];
  receipts: SuggestionWithData['receipt'] | SuggestionWithData['receipt'][];
}

const one = <T,>(v: T | T[] | null): T | null => (Array.isArray(v) ? (v[0] ?? null) : v);

const SELECT =
  'id, transaction_id, receipt_id, kind, diff_amount, score, status, note, ' +
  'bank_transactions(booking_date, amount, currency, description), ' +
  'receipts(store_name, receipt_date, total_amount, currency)';

async function fetchOpen(): Promise<SuggestionWithData[]> {
  const { data } = await supabase
    .from('match_suggestions')
    .select(SELECT)
    .eq('status', 'open')
    .order('score', { ascending: false });
  return ((data ?? []) as unknown as SuggestionRow[]).map(({ bank_transactions, receipts, ...s }) => ({
    ...s,
    transaction: one(bank_transactions),
    receipt: one(receipts),
  }));
}

export function useMatchSuggestions(onResolved?: () => void) {
  const [suggestions, setSuggestions] = useState<SuggestionWithData[]>([]);
  const [loading, setLoading] = useState(true);

  const load = useCallback(async () => {
    setSuggestions(await fetchOpen());
    setLoading(false);
  }, []);

  useEffect(() => {
    load();
    const channel = supabase
      .channel('match_suggestions_open')
      .on('postgres_changes', { event: '*', schema: 'public', table: 'match_suggestions' }, () => { load(); })
      .subscribe();
    return () => { supabase.removeChannel(channel); };
  }, [load]);

  const accept = useCallback(async (
    id: string, kind: SuggestionKind, amount: number, note: string | null,
  ) => {
    const { error } = await supabase.rpc('accept_match_suggestion', {
      p_suggestion_id: id, p_kind: kind, p_amount: amount, p_note: note,
    });
    if (error) throw new Error(error.message);
    setSuggestions((prev) => prev.filter((s) => s.id !== id));
    onResolved?.();
  }, [onResolved]);

  const reject = useCallback(async (id: string) => {
    const { error } = await supabase
      .from('match_suggestions')
      .update({ status: 'rejected', resolved_at: new Date().toISOString() })
      .eq('id', id);
    if (error) throw new Error(error.message);
    setSuggestions((prev) => prev.filter((s) => s.id !== id));
  }, []);

  return { suggestions, loading, accept, reject, reload: load };
}
