// delete-account — App Store Guideline 5.1.1(v): in-app account deletion.
//
// POST { confirm: "DELETE", apple_authorization_code?: string }
//   1. Revoke Sign in with Apple (best effort; needs a fresh authorization code
//      from the client and the SIWA key secrets below).
//   2. Remove the user's files from every storage bucket ("<uid>/..." paths).
//   3. Delete auth.users → all public tables cascade (migration 20260929000001).

import { createClient, SupabaseClient } from 'npm:@supabase/supabase-js@2';
import { importPKCS8, SignJWT } from 'npm:jose@5';

const SUPABASE_URL         = Deno.env.get('SUPABASE_URL')!;
const SUPABASE_ANON_KEY    = Deno.env.get('SUPABASE_ANON_KEY')!;
const SUPABASE_SERVICE_KEY = Deno.env.get('SERVICE_ROLE_KEY') ?? Deno.env.get('SUPABASE_SERVICE_ROLE_KEY')!;

const APPLE_TEAM_ID     = Deno.env.get('APPLE_TEAM_ID') ?? '';
const APPLE_KEY_ID      = Deno.env.get('APPLE_SIWA_KEY_ID') ?? '';
const APPLE_PRIVATE_KEY = (Deno.env.get('APPLE_SIWA_PRIVATE_KEY') ?? '').replace(/\\n/g, '\n');
const APPLE_CLIENT_ID   = Deno.env.get('APPLE_CLIENT_ID') ?? 'com.heartbeat.piggy';

const USER_BUCKETS = ['receipts', 'receipt-images', 'bank-statements'];
const LIST_PAGE_SIZE = 1000;
const CLIENT_SECRET_TTL_SECONDS = 300;

const CORS_HEADERS = {
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Headers': 'authorization, x-client-info, apikey, content-type',
};

function jsonResponse(body: unknown, status = 200): Response {
  return new Response(JSON.stringify(body), {
    status,
    headers: { 'Content-Type': 'application/json', ...CORS_HEADERS },
  });
}

// ── Apple ────────────────────────────────────────────────────────────────────

async function appleClientSecret(): Promise<string> {
  const key = await importPKCS8(APPLE_PRIVATE_KEY, 'ES256');
  const now = Math.floor(Date.now() / 1000);
  return new SignJWT({})
    .setProtectedHeader({ alg: 'ES256', kid: APPLE_KEY_ID })
    .setIssuer(APPLE_TEAM_ID)
    .setIssuedAt(now)
    .setExpirationTime(now + CLIENT_SECRET_TTL_SECONDS)
    .setAudience('https://appleid.apple.com')
    .setSubject(APPLE_CLIENT_ID)
    .sign(key);
}

/** Exchanges the authorization code for a refresh token and revokes it. */
async function revokeApple(authorizationCode: string): Promise<'revoked' | 'not_configured' | 'failed'> {
  if (!APPLE_TEAM_ID || !APPLE_KEY_ID || !APPLE_PRIVATE_KEY) return 'not_configured';
  try {
    const clientSecret = await appleClientSecret();
    const tokenRes = await fetch('https://appleid.apple.com/auth/token', {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: new URLSearchParams({
        client_id: APPLE_CLIENT_ID,
        client_secret: clientSecret,
        code: authorizationCode,
        grant_type: 'authorization_code',
      }),
    });
    const tokens = await tokenRes.json();
    const token = tokens.refresh_token ?? tokens.access_token;
    if (!tokenRes.ok || !token) {
      console.error('apple token exchange failed', tokenRes.status, tokens.error);
      return 'failed';
    }
    const revokeRes = await fetch('https://appleid.apple.com/auth/revoke', {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: new URLSearchParams({
        client_id: APPLE_CLIENT_ID,
        client_secret: clientSecret,
        token,
        token_type_hint: tokens.refresh_token ? 'refresh_token' : 'access_token',
      }),
    });
    if (!revokeRes.ok) {
      console.error('apple revoke failed', revokeRes.status);
      return 'failed';
    }
    return 'revoked';
  } catch (e) {
    console.error('apple revoke error', e instanceof Error ? e.message : e);
    return 'failed';
  }
}

// ── Storage ──────────────────────────────────────────────────────────────────

async function listFilesRecursive(admin: SupabaseClient, bucket: string, prefix: string): Promise<string[]> {
  const files: string[] = [];
  for (let offset = 0; ; offset += LIST_PAGE_SIZE) {
    const { data, error } = await admin.storage.from(bucket).list(prefix, { limit: LIST_PAGE_SIZE, offset });
    if (error) throw new Error(`list ${bucket}/${prefix}: ${error.message}`);
    for (const entry of data ?? []) {
      const path = `${prefix}/${entry.name}`;
      // Folders have no id in the storage list API
      if (entry.id === null) files.push(...await listFilesRecursive(admin, bucket, path));
      else files.push(path);
    }
    if ((data ?? []).length < LIST_PAGE_SIZE) return files;
  }
}

async function deleteUserFiles(admin: SupabaseClient, userId: string): Promise<number> {
  let deleted = 0;
  for (const bucket of USER_BUCKETS) {
    const paths = await listFilesRecursive(admin, bucket, userId);
    for (let i = 0; i < paths.length; i += LIST_PAGE_SIZE) {
      const chunk = paths.slice(i, i + LIST_PAGE_SIZE);
      const { error } = await admin.storage.from(bucket).remove(chunk);
      if (error) throw new Error(`remove ${bucket}: ${error.message}`);
      deleted += chunk.length;
    }
  }
  return deleted;
}

// ── Handler ──────────────────────────────────────────────────────────────────

Deno.serve(async (req) => {
  if (req.method === 'OPTIONS') return new Response('ok', { headers: CORS_HEADERS });
  if (req.method !== 'POST') return jsonResponse({ error: 'Method not allowed' }, 405);

  const authHeader = req.headers.get('Authorization') ?? '';
  const userToken = authHeader.replace(/^Bearer\s+/i, '');
  if (!userToken) return jsonResponse({ error: 'Missing token' }, 401);

  const userClient = createClient(SUPABASE_URL, SUPABASE_ANON_KEY, {
    global: { headers: { Authorization: `Bearer ${userToken}` } },
  });
  const { data: { user }, error: authError } = await userClient.auth.getUser();
  if (authError || !user) return jsonResponse({ error: 'Invalid token' }, 401);

  const body = await req.json().catch(() => ({}));
  if (body.confirm !== 'DELETE') return jsonResponse({ error: 'confirm: "DELETE" erforderlich' }, 400);

  const admin = createClient(SUPABASE_URL, SUPABASE_SERVICE_KEY, {
    auth: { persistSession: false },
  });

  const hasApple = (user.identities ?? []).some((i) => i.provider === 'apple');
  const appleCode = typeof body.apple_authorization_code === 'string' ? body.apple_authorization_code : '';
  const apple = hasApple ? (appleCode ? await revokeApple(appleCode) : 'failed') : 'not_linked';

  try {
    const files = await deleteUserFiles(admin, user.id);
    const { error } = await admin.auth.admin.deleteUser(user.id);
    if (error) throw new Error(`deleteUser: ${error.message}`);
    console.log('account deleted', { files, apple });
    return jsonResponse({ deleted: true, files, apple });
  } catch (e) {
    console.error('account deletion failed', e instanceof Error ? e.message : e);
    return jsonResponse({ error: 'Konto konnte nicht gelöscht werden' }, 500);
  }
});
