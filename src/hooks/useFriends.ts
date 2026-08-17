import { useCallback, useEffect, useRef, useState } from 'react';
import { supabase } from '../lib/supabase';
import type { FriendWithProfile, Profile } from '../types/social';

export function useFriends(onNewRequest?: (name: string) => void) {
  const [friends, setFriends]     = useState<FriendWithProfile[]>([]);
  const [incoming, setIncoming]   = useState<FriendWithProfile[]>([]);
  const [outgoing, setOutgoing]   = useState<FriendWithProfile[]>([]);
  const [loading, setLoading]     = useState(true);
  const userIdRef                 = useRef<string | null>(null);

  const load = useCallback(async () => {
    const { data: { user } } = await supabase.auth.getUser();
    if (!user) return;
    userIdRef.current = user.id;

    const { data } = await supabase
      .from('friendships')
      .select('*, requester:profiles!requester_id(*), recipient:profiles!recipient_id(*)')
      .or(`requester_id.eq.${user.id},recipient_id.eq.${user.id}`);

    if (!data) { setLoading(false); return; }

    const accepted: FriendWithProfile[] = [];
    const inc: FriendWithProfile[]      = [];
    const out: FriendWithProfile[]      = [];

    for (const row of data) {
      const isRequester = row.requester_id === user.id;
      const friendProfile: Profile = isRequester ? row.recipient : row.requester;
      const entry: FriendWithProfile = {
        id: row.id, requester_id: row.requester_id,
        recipient_id: row.recipient_id, status: row.status,
        created_at: row.created_at, profile: friendProfile,
      };
      if (row.status === 'accepted') accepted.push(entry);
      else if (row.status === 'pending') {
        if (isRequester) out.push(entry); else inc.push(entry);
      }
    }

    setFriends(accepted);
    setIncoming(inc);
    setOutgoing(out);
    setLoading(false);
  }, []);

  // Realtime: reload + notify when a new request arrives
  useEffect(() => {
    load();
  }, [load]);

  useEffect(() => {
    let channel: ReturnType<typeof supabase.channel> | null = null;

    supabase.auth.getUser().then(({ data: { user } }) => {
      if (!user) return;

      channel = supabase
        .channel(`friendships:${user.id}`)
        .on(
          'postgres_changes',
          { event: 'INSERT', schema: 'public', table: 'friendships', filter: `recipient_id=eq.${user.id}` },
          async (payload) => {
            // Fetch the requester's profile name for the toast
            const { data: profile } = await supabase
              .from('profiles')
              .select('display_name, piggy_code')
              .eq('id', payload.new.requester_id)
              .single();
            const name = profile?.display_name ?? profile?.piggy_code ?? 'Jemand';
            onNewRequest?.(`${name} möchte dich als Freund hinzufügen`);
            load();
          },
        )
        .on(
          'postgres_changes',
          { event: 'UPDATE', schema: 'public', table: 'friendships' },
          () => load(),
        )
        .on(
          'postgres_changes',
          { event: 'DELETE', schema: 'public', table: 'friendships' },
          () => load(),
        )
        .subscribe();
    });

    return () => {
      if (channel) supabase.removeChannel(channel);
    };
  }, [load, onNewRequest]);

  async function sendRequest(piggyCode: string): Promise<{ error?: string; profile?: Profile }> {
    const { data: { user } } = await supabase.auth.getUser();
    if (!user) return { error: 'Nicht angemeldet' };

    const { data: target } = await supabase
      .from('profiles')
      .select('*')
      .eq('piggy_code', piggyCode.toUpperCase())
      .single();

    if (!target) return { error: 'Kein Nutzer mit diesem Code gefunden.' };
    if (target.id === user.id) return { error: 'Du kannst dich nicht selbst hinzufügen.' };

    const { error } = await supabase.from('friendships').insert({
      requester_id: user.id,
      recipient_id: target.id,
    });

    if (error) {
      if (error.code === '23505') return { error: 'Anfrage bereits gesendet oder ihr seid schon Freunde.' };
      return { error: error.message };
    }

    await load();
    return { profile: target };
  }

  async function acceptRequest(friendshipId: string) {
    await supabase.from('friendships').update({ status: 'accepted' }).eq('id', friendshipId);
    await load();
  }

  async function declineRequest(friendshipId: string) {
    await supabase.from('friendships').delete().eq('id', friendshipId);
    await load();
  }

  async function removeFriend(friendshipId: string) {
    await supabase.from('friendships').delete().eq('id', friendshipId);
    await load();
  }

  return { friends, incoming, outgoing, loading, reload: load, sendRequest, acceptRequest, declineRequest, removeFriend };
}
