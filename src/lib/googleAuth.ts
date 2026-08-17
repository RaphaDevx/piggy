import { Platform } from 'react-native';
import * as WebBrowser from 'expo-web-browser';
import { makeRedirectUri } from 'expo-auth-session';
import { supabase } from './supabase';

export type GoogleAuthMode = 'signin' | 'link';

/**
 * Führt den Google-OAuth-Flow aus — entweder als Login/Registrierung ('signin')
 * oder um Google als zusätzliche Identität an das eingeloggte Konto zu hängen ('link').
 * 'link' setzt voraus, dass im Supabase-Projekt "Allow manual linking" aktiv ist.
 */
export async function runGoogleOAuth(mode: GoogleAuthMode): Promise<{ error?: string }> {
  const oauthOptions = { provider: 'google' as const };

  if (Platform.OS === 'web') {
    const { error } = mode === 'signin'
      ? await supabase.auth.signInWithOAuth({ ...oauthOptions, options: { redirectTo: window.location.origin } })
      : await supabase.auth.linkIdentity({ ...oauthOptions, options: { redirectTo: window.location.origin } });
    if (error) return { error: error.message };
    return {}; // Browser leitet weiter — fertig
  }

  // Native: In-App-Browser öffnen, danach Tokens aus dem Redirect extrahieren
  const redirectTo = makeRedirectUri({ scheme: 'piggy', path: 'auth/callback' });
  const { data, error } = mode === 'signin'
    ? await supabase.auth.signInWithOAuth({ ...oauthOptions, options: { redirectTo, skipBrowserRedirect: true } })
    : await supabase.auth.linkIdentity({ ...oauthOptions, options: { redirectTo, skipBrowserRedirect: true } });

  if (error || !data?.url) {
    return { error: error?.message ?? 'OAuth fehlgeschlagen' };
  }

  const result = await WebBrowser.openAuthSessionAsync(data.url, redirectTo);
  if (result.type === 'success') {
    const hash = result.url.split('#')[1] ?? '';
    const params: Record<string, string> = {};
    hash.split('&').forEach((p) => {
      const [k, v] = p.split('=');
      if (k && v) params[k] = decodeURIComponent(v);
    });
    if (params.access_token && params.refresh_token) {
      const { error: sessionError } = await supabase.auth.setSession({
        access_token: params.access_token,
        refresh_token: params.refresh_token,
      });
      if (sessionError) return { error: sessionError.message };
    } else if (params.error_description) {
      return { error: decodeURIComponent(params.error_description) };
    }
  }

  return {};
}
