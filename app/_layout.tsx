import { useEffect } from 'react';
import { Stack, useRouter, useSegments } from 'expo-router';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import { useAuth } from '../src/lib/supabase';
import { syncAiConsent } from '../src/lib/aiConsent';
import { track } from '../src/lib/analytics';
import { AdsProvider } from '../src/contexts/AdsContext';
import { SubscriptionProvider } from '../src/contexts/SubscriptionContext';

function AuthGuard() {
  const { session, loading } = useAuth();
  const segments = useSegments();
  const router   = useRouter();

  useEffect(() => {
    if (loading) return;
    const inAuthGroup = segments[0] === '(auth)';
    if (!session && !inAuthGroup) {
      router.replace('/(auth)/login');
    } else if (session && inAuthGroup) {
      router.replace('/(tabs)' as any);
    }
  }, [session, loading, segments]);

  // Bereits lokal erteilte KI-Einwilligung einmalig in die Datenbank nachtragen (best-effort)
  const userId = session?.user?.id;
  useEffect(() => { if (userId) syncAiConsent(); }, [userId]);
  useEffect(() => { if (userId) void track('app_open'); }, [userId]);

  return (
    <SubscriptionProvider userId={userId ?? null}>
      <AdsProvider>
      <Stack screenOptions={{ headerShown: false }}>
        <Stack.Screen name="(auth)"          options={{ headerShown: false }} />
        <Stack.Screen name="(tabs)"          options={{ headerShown: false }} />
        <Stack.Screen name="duplicates" options={{ headerShown: false }} />
        <Stack.Screen name="scan"            options={{ presentation: 'modal', headerShown: false }} />
        {/* Geteilte Sub-Screens: zugänglich von jedem Tab */}
        <Stack.Screen name="receipt/[id]"    options={{ headerShown: false }} />
        <Stack.Screen name="category/[group]" options={{ headerShown: false }} />
        <Stack.Screen name="friends"          options={{ headerShown: false }} />
        <Stack.Screen name="split"            options={{ headerShown: false }} />
        <Stack.Screen name="plus"             options={{ presentation: 'modal', headerShown: false }} />
      </Stack>
      </AdsProvider>
    </SubscriptionProvider>
  );
}

export default function RootLayout() {
  return (
    <SafeAreaProvider>
      <AuthGuard />
    </SafeAreaProvider>
  );
}
