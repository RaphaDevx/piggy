import 'react-native-url-polyfill/auto';
import { createClient, Session } from '@supabase/supabase-js';
import { Platform } from 'react-native';
import * as SecureStore from 'expo-secure-store';
import { useEffect, useState } from 'react';

const webStorage = {
  getItem:    (key: string) => Promise.resolve(localStorage.getItem(key)),
  setItem:    (key: string, value: string) => { localStorage.setItem(key, value); return Promise.resolve(); },
  removeItem: (key: string) => { localStorage.removeItem(key); return Promise.resolve(); },
};

const nativeStorage = {
  getItem:    (key: string) => SecureStore.getItemAsync(key),
  setItem:    (key: string, value: string) => SecureStore.setItemAsync(key, value),
  removeItem: (key: string) => SecureStore.deleteItemAsync(key),
};

const supabaseUrl    = process.env.EXPO_PUBLIC_SUPABASE_URL    ?? '';
const supabaseAnonKey = process.env.EXPO_PUBLIC_SUPABASE_ANON_KEY ?? '';

export const supabase = createClient(supabaseUrl, supabaseAnonKey, {
  auth: {
    storage:          Platform.OS === 'web' ? webStorage : nativeStorage,
    autoRefreshToken: true,
    persistSession:   true,
    detectSessionInUrl: Platform.OS === 'web',
  },
});

export function useAuth() {
  const [session, setSession] = useState<Session | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    supabase.auth.getSession().then(({ data: { session } }) => {
      setSession(session);
      setLoading(false);
    });

    const { data: { subscription } } = supabase.auth.onAuthStateChange((_event, session) => {
      setSession(session);
    });

    return () => subscription.unsubscribe();
  }, []);

  return { session, loading };
}
