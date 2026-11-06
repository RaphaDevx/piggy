// revenuecat-webhook — schreibt Abo-Ereignisse von RevenueCat in user_entitlements.
//
// verify_jwt = false (RevenueCat sendet kein Supabase-JWT). Deploy:
//   supabase functions deploy revenuecat-webhook --no-verify-jwt
// Authentifizierung: Header "Authorization" muss exakt dem Secret REVENUECAT_WEBHOOK_AUTH
// entsprechen (im RevenueCat-Dashboard als "Authorization header value" eintragen).
// Antworten: 200 = verarbeitet oder bewusst ignoriert, 401 = falsche Authentifizierung,
// 5xx = RevenueCat wiederholt.

import { createClient } from 'npm:@supabase/supabase-js@2';
import { decideWebhook, isAuthorized } from '../_shared/entitlements/revenuecat.ts';

const SUPABASE_URL         = Deno.env.get('SUPABASE_URL')!;
const SUPABASE_SERVICE_KEY = Deno.env.get('SERVICE_ROLE_KEY') ?? Deno.env.get('SUPABASE_SERVICE_ROLE_KEY')!;
const WEBHOOK_AUTH         = Deno.env.get('REVENUECAT_WEBHOOK_AUTH') ?? '';

function jsonResponse(body: unknown, status = 200): Response {
  return new Response(JSON.stringify(body), { status, headers: { 'Content-Type': 'application/json' } });
}

Deno.serve(async (req: Request) => {
  if (req.method !== 'POST') return jsonResponse({ error: 'Method not allowed' }, 405);

  if (!WEBHOOK_AUTH) console.error('revenuecat-webhook: REVENUECAT_WEBHOOK_AUTH nicht gesetzt');
  if (!isAuthorized(req.headers.get('Authorization'), WEBHOOK_AUTH)) {
    return jsonResponse({ error: 'Unauthorized' }, 401);
  }

  const body = await req.json().catch(() => null);
  if (body === null) return jsonResponse({ error: 'Invalid JSON' }, 400);

  const decision = decideWebhook(body);
  if (decision.kind === 'ignore') return jsonResponse({ status: 'ignored', reason: decision.reason });

  const admin = createClient(SUPABASE_URL, SUPABASE_SERVICE_KEY);
  try {
    if (decision.kind === 'upsert') {
      const { error } = await admin.from('user_entitlements').upsert(decision.row, { onConflict: 'user_id' });
      if (error) throw error;
    } else {
      const { error } = await admin.from('user_entitlements')
        .update({ updated_at: decision.updated_at }).eq('user_id', decision.user_id);
      if (error) throw error;
    }
    return jsonResponse({ status: 'ok' });
  } catch (err) {
    // Fremdschlüssel-Fehler (Nutzer existiert nicht mehr) ist kein Fall für Wiederholungen.
    const e = err as { code?: string; message?: string };
    if (e?.code === '23503') return jsonResponse({ status: 'ignored', reason: 'unknown_user' });
    console.error('revenuecat-webhook error:', e?.message ?? String(err));
    return jsonResponse({ error: 'Database error' }, 500);
  }
});
