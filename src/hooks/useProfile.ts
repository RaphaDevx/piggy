import { useEffect, useState, useCallback } from 'react';
import { supabase } from '../lib/supabase';
import type { Profile } from '../types/social';

export function useProfile() {
  const [profile, setProfile] = useState<Profile | null>(null);
  const [loading, setLoading] = useState(true);

  const load = useCallback(async () => {
    const { data: { user } } = await supabase.auth.getUser();
    if (!user) return;

    const { data } = await supabase
      .from('profiles')
      .select('*')
      .eq('id', user.id)
      .single();

    setProfile(data ?? null);
    setLoading(false);
  }, []);

  useEffect(() => { load(); }, [load]);

  async function updateDisplayName(name: string) {
    const { data: { user } } = await supabase.auth.getUser();
    if (!user) return;
    const { data } = await supabase
      .from('profiles')
      .update({ display_name: name })
      .eq('id', user.id)
      .select()
      .single();
    if (data) setProfile(data);
  }

  return { profile, loading, reload: load, updateDisplayName };
}
