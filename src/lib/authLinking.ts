import { supabase } from './supabase';

const SUPABASE_URL = process.env.EXPO_PUBLIC_SUPABASE_URL!;
const SUPABASE_ANON_KEY = process.env.EXPO_PUBLIC_SUPABASE_ANON_KEY!;

/**
 * Links an external identity (Google / Apple) to the currently logged-in user
 * via GoTrue's id_token grant with an Authorization header.
 * When a session header is present, GoTrue links instead of signing in.
 */
export async function linkIdentityWithIdToken(
  provider: 'google' | 'apple',
  idToken: string,
  nonce?: string,
): Promise<{ error?: string }> {
  const { data: { session } } = await supabase.auth.getSession();
  if (!session) return { error: 'Kein aktiver Login gefunden' };

  const body: Record<string, string> = { provider, id_token: idToken };
  if (nonce) body.nonce = nonce;

  const res = await fetch(`${SUPABASE_URL}/auth/v1/token?grant_type=id_token`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'apikey': SUPABASE_ANON_KEY,
      'Authorization': `Bearer ${session.access_token}`,
    },
    body: JSON.stringify(body),
  });

  const data = await res.json();
  if (!res.ok) {
    return { error: data.error_description ?? data.msg ?? `HTTP ${res.status}` };
  }

  // Refresh local session with the new tokens
  if (data.access_token && data.refresh_token) {
    await supabase.auth.setSession({
      access_token: data.access_token,
      refresh_token: data.refresh_token,
    });
  }
  return {};
}
