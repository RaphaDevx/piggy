import { useEffect } from 'react';
import { Stack, useRouter, useSegments } from 'expo-router';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import { useAuth } from '../src/lib/supabase';

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

  return (
    <Stack screenOptions={{ headerShown: false }}>
      <Stack.Screen name="(auth)"          options={{ headerShown: false }} />
      <Stack.Screen name="(tabs)"          options={{ headerShown: false }} />
      <Stack.Screen name="scan"            options={{ presentation: 'modal', headerShown: false }} />
      {/* Geteilte Sub-Screens: zugänglich von jedem Tab */}
      <Stack.Screen name="receipt/[id]"    options={{ headerShown: false }} />
      <Stack.Screen name="category/[group]" options={{ headerShown: false }} />
      <Stack.Screen name="friends"          options={{ headerShown: false }} />
      <Stack.Screen name="split"            options={{ headerShown: false }} />
    </Stack>
  );
}

export default function RootLayout() {
  return (
    <SafeAreaProvider>
      <AuthGuard />
    </SafeAreaProvider>
  );
}
