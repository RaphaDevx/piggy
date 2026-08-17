import { useCallback, useEffect, useState } from 'react';
import { supabase } from '../lib/supabase';
import type { SharedReceiptWithDetails, CardMatch, ProfileCard } from '../types/sharing';
import type { Profile } from '../types/social';
import { extractLastFour } from '../types/sharing';

// ── Incoming shared receipts (I am recipient) ─────────────────────────────────
export function useIncomingSharedReceipts() {
  const [items, setItems]   = useState<SharedReceiptWithDetails[]>([]);
  const [loading, setLoading] = useState(true);

  const load = useCallback(async () => {
    const { data: { user } } = await supabase.auth.getUser();
    if (!user) { setLoading(false); return; }

    const { data } = await supabase
      .from('shared_receipts')
      .select('*, sender_profile:profiles!shared_receipts_sender_id_fkey(*), receipt:receipts(store_name, receipt_date, total_amount, currency)')
      .eq('recipient_id', user.id)
      .eq('status', 'pending')
      .order('created_at', { ascending: false });

    setItems((data as SharedReceiptWithDetails[]) ?? []);
    setLoading(false);
  }, []);

  useEffect(() => { load(); }, [load]);

  async function accept(sharedId: string) {
    await supabase.from('shared_receipts').update({ status: 'accepted' }).eq('id', sharedId);
    await load();
  }

  async function decline(sharedId: string) {
    await supabase.from('shared_receipts').update({ status: 'declined' }).eq('id', sharedId);
    await load();
  }

  return { items, loading, reload: load, accept, decline };
}

// ── Send a receipt to a friend ────────────────────────────────────────────────
export async function sendReceipt(receiptId: string, recipientId: string): Promise<{ error?: string }> {
  const { data: { user } } = await supabase.auth.getUser();
  if (!user) return { error: 'Nicht angemeldet' };

  const { error } = await supabase.from('shared_receipts').upsert({
    receipt_id:   receiptId,
    sender_id:    user.id,
    recipient_id: recipientId,
    status:       'pending',
  }, { onConflict: 'receipt_id,recipient_id' });

  return { error: error?.message };
}

// ── Check if a payment_card matches any friend's stored cards ─────────────────
export function useCardMatching(paymentCard: string | null) {
  const [matches, setMatches] = useState<CardMatch[]>([]);

  useEffect(() => {
    const lastFour = extractLastFour(paymentCard);
    if (!lastFour) { setMatches([]); return; }

    (async () => {
      // Get all cards from friends that are visible to me
      const { data } = await supabase
        .from('profile_cards')
        .select('*, profile:profiles!profile_cards_user_id_fkey(*)')
        .eq('last_four', lastFour);

      if (!data) return;

      const { data: { user } } = await supabase.auth.getUser();
      const found: CardMatch[] = (data as (ProfileCard & { profile: Profile })[])
        .filter((c) => c.user_id !== user?.id) // exclude own cards
        .map((c) => ({ last_four: c.last_four, profile: c.profile, card: c }));

      setMatches(found);
    })();
  }, [paymentCard]);

  return matches;
}

// ── Profile cards management (own cards) ──────────────────────────────────────
export function useProfileCards() {
  const [cards, setCards]   = useState<ProfileCard[]>([]);
  const [loading, setLoading] = useState(true);

  const load = useCallback(async () => {
    const { data: { user } } = await supabase.auth.getUser();
    if (!user) { setLoading(false); return; }
    const { data } = await supabase.from('profile_cards').select('*').eq('user_id', user.id).order('created_at');
    setCards(data ?? []);
    setLoading(false);
  }, []);

  useEffect(() => { load(); }, [load]);

  async function addCard(lastFour: string, label: string): Promise<{ error?: string }> {
    const { data: { user } } = await supabase.auth.getUser();
    if (!user) return { error: 'Nicht angemeldet' };
    const { error } = await supabase.from('profile_cards').insert({ user_id: user.id, last_four: lastFour, label: label || null });
    if (!error) await load();
    return { error: error?.message };
  }

  async function removeCard(cardId: string) {
    await supabase.from('profile_cards').delete().eq('id', cardId);
    await load();
  }

  return { cards, loading, reload: load, addCard, removeCard };
}

// ── Check if I received this receipt (for split screen context) ───────────────
export async function getSharedReceiptContext(receiptId: string) {
  const { data: { user } } = await supabase.auth.getUser();
  if (!user) return null;
  const { data } = await supabase
    .from('shared_receipts')
    .select('*, sender_profile:profiles!shared_receipts_sender_id_fkey(*)')
    .eq('receipt_id', receiptId)
    .eq('recipient_id', user.id)
    .eq('status', 'accepted')
    .maybeSingle();
  return data as (SharedReceiptWithDetails & { sender_profile: Profile }) | null;
}
