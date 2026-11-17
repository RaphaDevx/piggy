import { createClient } from 'npm:@supabase/supabase-js@2';
import { buildPeriodOverview, type FinanceReceipt } from '../_shared/finance-core/finance.ts';

const headers = { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*', 'Access-Control-Allow-Headers': 'authorization, x-client-info, apikey, content-type' };
const reply = (body: unknown, status = 200) => new Response(JSON.stringify(body), { status, headers });

// Read-only adapter: same canonical finance logic as Expo. Bank import remains disabled.
Deno.serve(async req => {
  if (req.method === 'OPTIONS') return new Response(null, { headers });
  if (req.method !== 'POST') return reply({ error: 'POST erforderlich' }, 405);
  const authorization = req.headers.get('Authorization') ?? '';
  if (!authorization.startsWith('Bearer ')) return reply({ error: 'Nicht angemeldet' }, 401);
  const client = createClient(Deno.env.get('SUPABASE_URL')!, Deno.env.get('SUPABASE_ANON_KEY')!, {
    global: { headers: { Authorization: authorization } }, auth: { persistSession: false },
  });
  const { data: { user }, error: authError } = await client.auth.getUser();
  if (authError || !user) return reply({ error: 'Ungültige Anmeldung' }, 401);
  const input = await req.json().catch(() => null);
  if (!input || !/^[A-Z]{3}$/.test(input.currency ?? '')) {
    return reply({ error: 'Gültiger Monat und Währung erforderlich' }, 400);
  }
  let start: string, end: string, period: string;
  if (/^\d{4}-(0[1-9]|1[0-2])$/.test(input.month ?? '')) {
    const [year, month] = input.month.split('-').map(Number);
    start = `${input.month}-01`;
    end = new Date(Date.UTC(year, month, 1)).toISOString().slice(0, 10);
    period = input.month;
  } else {
    start = input.start ?? ''; end = input.end ?? '';
    const validDate = (value: string) => /^\d{4}-\d{2}-\d{2}$/.test(value)
      && Number.isFinite(Date.parse(value)) && new Date(value).toISOString().slice(0,10) === value;
    if (!validDate(start) || !validDate(end) || end <= start || Date.parse(end) - Date.parse(start) > 366 * 86400000) {
      return reply({ error: 'Gültiger Zeitraum von höchstens einem Jahr erforderlich' }, 400);
    }
    period = `${start}/${end}`;
  }
  const receipts: FinanceReceipt[] = [];
  try {
    for (let offset = 0; ; offset += 500) {
      // The user JWT enforces RLS; explicit user filter is defense in depth.
      const { data, error } = await client.from('receipts')
        .select('id,receipt_date,total_amount,currency,items:receipt_items(total_price,tags,subcategory)')
        .eq('user_id', user.id).eq('currency', input.currency)
        .gte('receipt_date', start).lt('receipt_date', end)
        .order('id').range(offset, offset + 499);
      if (error) throw error;
      receipts.push(...(data ?? []) as FinanceReceipt[]);
      if (!data || data.length < 500) break;
    }
    return reply(buildPeriodOverview(period, () => period, [], receipts, [], input.currency));
  } catch {
    return reply({ error: 'Die Auswertung konnte nicht geladen werden.' }, 500);
  }
});
