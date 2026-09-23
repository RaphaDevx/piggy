import { Platform } from 'react-native';
import * as AuthSession from 'expo-auth-session';
import * as WebBrowser from 'expo-web-browser';
import { supabase } from './supabase';
import { linkIdentityWithIdToken } from './authLinking';

export type GoogleAuthMode = 'signin' | 'link';

// iOS native client (no client secret needed, uses PKCE)
const IOS_CLIENT_ID = '844663909104-0gbgnks89b3ggosos53qfu4f4e7tp4mr.apps.googleusercontent.com';
// Reversed iOS client ID — registered as URL scheme in app.json
const IOS_REDIRECT_SCHEME = 'com.googleusercontent.apps.844663909104-0gbgnks89b3ggosos53qfu4f4e7tp4mr';

const GOOGLE_DISCOVERY: AuthSession.DiscoveryDocument = {
  authorizationEndpoint: 'https://accounts.google.com/o/oauth2/v2/auth',
  tokenEndpoint: 'https://oauth2.googleapis.com/token',
};

export async function runGoogleOAuth(mode: GoogleAuthMode): Promise<{ error?: string }> {
  if (Platform.OS === 'web') {
    const opts = { provider: 'google' as const, options: { redirectTo: window.location.origin } };
    const { error } = mode === 'signin'
      ? await supabase.auth.signInWithOAuth(opts)
      : await supabase.auth.linkIdentity(opts);
    return error ? { error: error.message } : {};
  }

  // iOS: direct Google PKCE flow → avoids Supabase redirect URI entirely
  const redirectUri = AuthSession.makeRedirectUri({ scheme: IOS_REDIRECT_SCHEME });

  const request = new AuthSession.AuthRequest({
    clientId: IOS_CLIENT_ID,
    scopes: ['openid', 'profile', 'email'],
    redirectUri,
    usePKCE: true,
  });

  await request.makeAuthUrlAsync(GOOGLE_DISCOVERY);
  const result = await request.promptAsync(GOOGLE_DISCOVERY);

  if (result.type === 'cancel' || result.type === 'dismiss') return {};
  if (result.type !== 'success') return { error: 'Google Sign-In abgebrochen' };

  const tokenResponse = await AuthSession.exchangeCodeAsync(
    {
      clientId: IOS_CLIENT_ID,
      code: result.params.code,
      redirectUri,
      extraParams: { code_verifier: request.codeVerifier! },
    },
    GOOGLE_DISCOVERY,
  );

  const idToken = tokenResponse.idToken;
  if (!idToken) return { error: 'Kein ID-Token von Google erhalten' };

  if (mode === 'link') {
    return linkIdentityWithIdToken('google', idToken);
  }

  const { error } = await supabase.auth.signInWithIdToken({ provider: 'google', token: idToken });
  return error ? { error: error.message } : {};
}
