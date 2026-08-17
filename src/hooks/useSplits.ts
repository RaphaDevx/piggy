import { useCallback, useEffect, useState } from 'react';
import { supabase } from '../lib/supabase';
import type { SplitWithDetails, Balance } from '../types/split';
import type { ReceiptItem } from '../types/receipt';
import type { Profile } from '../types/social';

// Calculate per-person share given items and exclusions
export function calcShares(
  items: ReceiptItem[],
  totalFallback: number,
  participantIds: string[],
  exclusions: Map<string, Set<string>>, // userId -> Set<itemId>
): Map<string, number> {
  const shares = new Map<string, number>(participantIds.map((p) => [p, 0]));

  if (items.length === 0) {
    const each = totalFallback / participantIds.length;
    participantIds.forEach((p) => shares.set(p, each));
    return shares;
  }

  for (const item of items) {
    const price = item.total_price ?? 0;
    if (price === 0) continue;
    const included = participantIds.filter((p) => !exclusions.get(p)?.has(item.id));
    if (included.length === 0) continue;
    const each = price / included.length;
    included.forEach((p) => shares.set(p, (shares.get(p) ?? 0) + each));
  }

  return shares;
}

export function useSplitForReceipt(receiptId: string | null) {
  const [split, setSplit] = useState<SplitWithDetails | null>(null);
  const [loading, setLoading] = useState(true);

  const load = useCallback(async () => {
    if (!receiptId) { setLoading(false); return; }
    setLoading(true);

    const { data } = await supabase
      .from('splits')
      .select(`
        *,
        split_participants(*, profile:profiles(*)),
        split_exclusions(*),
        receipt:receipts(store_name, receipt_date),
        payer_profile:profiles!splits_payer_id_fkey(*)
      `)
      .eq('receipt_id', receiptId)
      .maybeSingle();

    setSplit(data as SplitWithDetails | null);
    setLoading(false);
  }, [receiptId]);

  useEffect(() => { load(); }, [load]);
  return { split, loading, reload: load };
}

interface CreateSplitParams {
  receiptId: string;
  payerId: string;
  totalAmount: number;
  currency: string;
  participantIds: string[];      // includes payer
  shares: Map<string, number>;   // userId → amount
  exclusions: Map<string, Set<string>>; // userId → itemIds
}

export async function createSplit(params: CreateSplitParams): Promise<{ error?: string }> {
  const { receiptId, payerId, totalAmount, currency, participantIds, shares, exclusions } = params;

  // Delete existing split for this receipt (idempotent replace)
  await supabase.from('splits').delete().eq('receipt_id', receiptId);

  const { data: split, error } = await supabase
    .from('splits')
    .insert({ receipt_id: receiptId, payer_id: payerId, total_amount: totalAmount, currency })
    .select()
    .single();

  if (error || !split) return { error: error?.message ?? 'Fehler beim Erstellen' };

  const participants = participantIds.map((uid) => ({
    split_id: split.id,
    user_id:  uid,
    amount:   Math.round((shares.get(uid) ?? 0) * 100) / 100,
    settled:  uid === payerId, // payer pays themselves
  }));

  const { error: pe } = await supabase.from('split_participants').insert(participants);
  if (pe) return { error: pe.message };

  const excRows: { split_id: string; user_id: string; receipt_item_id: string }[] = [];
  exclusions.forEach((itemIds, userId) => {
    itemIds.forEach((itemId) => excRows.push({ split_id: split.id, user_id: userId, receipt_item_id: itemId }));
  });
  if (excRows.length) {
    const { error: ee } = await supabase.from('split_exclusions').insert(excRows);
    if (ee) return { error: ee.message };
  }

  return {};
}

export async function settleParticipant(participantId: string): Promise<{ error?: string }> {
  const { error } = await supabase
    .from('split_participants')
    .update({ settled: true, settled_at: new Date().toISOString() })
    .eq('id', participantId);
  return { error: error?.message };
}

export function useBalances() {
  const [balances, setBalances] = useState<Balance>({ iOwe: [], owedToMe: [] });
  const [loading, setLoading]   = useState(true);

  const load = useCallback(async () => {
    const { data: { user } } = await supabase.auth.getUser();
    if (!user) { setLoading(false); return; }

    const { data } = await supabase
      .from('splits')
      .select(`
        *,
        split_participants(*, profile:profiles(*)),
        split_exclusions(*),
        receipt:receipts(store_name, receipt_date),
        payer_profile:profiles!splits_payer_id_fkey(*)
      `);

    if (!data) { setLoading(false); return; }

    const iOwe: Balance['iOwe']      = [];
    const owedToMe: Balance['owedToMe'] = [];

    for (const row of data as SplitWithDetails[]) {
      const participants = (row.split_participants ?? []) as typeof row.split_participants;

      if (row.payer_id === user.id) {
        // Others might owe me
        for (const p of participants) {
          if (p.user_id !== user.id && !p.settled) {
            owedToMe.push({ participant: p, split: row });
          }
        }
      } else {
        // I might owe them
        const myEntry = participants.find((p) => p.user_id === user.id);
        if (myEntry && !myEntry.settled) {
          iOwe.push({ participant: myEntry, split: row });
        }
      }
    }

    setBalances({ iOwe, owedToMe });
    setLoading(false);
  }, []);

  useEffect(() => { load(); }, [load]);
  return { balances, loading, reload: load };
}
