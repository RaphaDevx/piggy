// storage-url — vorsignierte Cloudflare-R2-URLs (GET/PUT/DELETE, max. 10 Minuten).
//
// POST { op: 'get'|'put'|'delete', bucket, paths: string[], verify?: boolean }
//   -> { results: [{ path, url, found? }], expiresIn }
// Nur Pfade unter `<eigene uid>/`. Secrets: R2_ACCOUNT_ID, R2_ACCESS_KEY_ID,
// R2_SECRET_ACCESS_KEY, R2_BUCKET, R2_JURISDICTION=eu (Supabase Function Secrets, nie im Code).

import { createClient } from 'npm:@supabase/supabase-js@2';
import { createR2, r2ConfigFromEnv } from '../_shared/storage/r2.ts';
import { handleStorageUrl } from '../_shared/storage/storageUrl.ts';

const config = r2ConfigFromEnv((name) => Deno.env.get(name));
const r2 = config ? createR2(config) : null;

Deno.serve((req) => handleStorageUrl(req, {
  r2,
  async authenticate(request) {
    const token = (request.headers.get('Authorization') ?? '').replace(/^Bearer\s+/i, '');
    if (!token) return null;
    const client = createClient(Deno.env.get('SUPABASE_URL')!, Deno.env.get('SUPABASE_ANON_KEY')!, {
      global: { headers: { Authorization: `Bearer ${token}` } },
      auth: { persistSession: false },
    });
    const { data: { user }, error } = await client.auth.getUser();
    return error || !user ? null : { id: user.id };
  },
}));
