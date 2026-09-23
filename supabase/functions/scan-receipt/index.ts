import { createClient } from 'npm:@supabase/supabase-js@2';

const SUPABASE_URL        = Deno.env.get('SUPABASE_URL')!;
const SUPABASE_SERVICE_KEY = Deno.env.get('SERVICE_ROLE_KEY') ?? Deno.env.get('SUPABASE_SERVICE_ROLE_KEY')!;
const SERVER_GEMINI_KEY   = Deno.env.get('GEMINI_API_KEY') ?? '';

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

const RECEIPT_PROMPT = `Du bist ein Quittungs-Scanner für Schweizer und deutsche Quittungen.

Analysiere diesen OCR-Text einer Quittung und extrahiere alle Daten. Antworte AUSSCHLIESSLICH mit einem JSON-Objekt – kein Text davor oder danach, keine Markdown-Codeblöcke.

JSON-Struktur:
{
  "store_name": "Name des Geschäfts",
  "store_category": "Supermarkt|Drogerie|Apotheke|Baumarkt|Bäckerei|Restaurant|Tankstelle|Elektronik|Kleidung|Diverses",
  "date": "YYYY-MM-DD oder null",
  "total_amount": Gesamtbetrag als Zahl,
  "currency": "CHF oder EUR",
  "payment_method": "Karte|Bargeld|TWINT|Rechnung|Unbekannt",
  "payment_card": "z.B. Visa ···· 4242, PostCard ···· 1234 oder null",
  "items": [
    {
      "name": "Artikelname",
      "quantity": Anzahl als Zahl (Standard 1),
      "unit": "Stk|kg|g|L|ml|Pack|Paar oder leer",
      "unit_price": Stückpreis als Zahl,
      "total_price": Gesamtpreis dieses Artikels als Zahl,
      "tags": ["Tag1", "Tag2"]
    }
  ]
}

Verfügbare Tags (weise jedem Artikel 1-3 passende Tags zu):
- Lebensmittel: "Lebensmittel", "Gemüse & Obst", "Milchprodukte", "Fleisch & Fisch", "Backwaren", "Tiefkühlkost", "Konserven", "Grundnahrungsmittel", "Snacks & Süsswaren"
- Getränke: "Getränke", "Alkohol", "Kaffee & Tee"
- Haushalt: "Haushalt", "Reinigung", "Entsorgung", "Küche", "Wohnen & Deko"
- Hygiene: "Hygiene", "Körperpflege", "Haarpflege", "Mundpflege", "Damenhygiene"
- Gesundheit: "Gesundheit", "Medikamente", "Nahrungsergänzung"
- Diverses: "Restaurant & Take-away", "Kleidung", "Elektronik", "Freizeit & Hobby", "Büro", "Diverses"

Regeln:
- Erkenne Mengenangaben (z.B. "2x", "3 kg") korrekt
- Rabatte, Aktionspreise und Pfand als eigene Artikel erfassen mit passendem negativem/positivem Preis
- Bei unleserlichen Teilen: beste Schätzung verwenden
- Alle Preise als Dezimalzahl ohne Währungssymbol (3.50 nicht "CHF 3.50")
- Wenn kein Datum erkennbar: null
- PostCard, PostFinance, YELLOWONE, Maestro → payment_method: "Karte", payment_card entsprechend`;

Deno.serve(async (req: Request) => {
  if (req.method === 'OPTIONS') return new Response(null, { headers: CORS_HEADERS });

  const authHeader = req.headers.get('Authorization') ?? '';
  const userToken  = authHeader.replace('Bearer ', '');
  if (!userToken) return jsonResponse({ error: 'Unauthorized' }, 401);

  const adminClient = createClient(SUPABASE_URL, SUPABASE_SERVICE_KEY);
  const userClient  = createClient(SUPABASE_URL, Deno.env.get('SUPABASE_ANON_KEY') ?? '', {
    global: { headers: { Authorization: `Bearer ${userToken}` } },
  });

  const { data: { user }, error: authError } = await userClient.auth.getUser();
  if (authError || !user) return jsonResponse({ error: 'Invalid token' }, 401);

  const body = await req.json().catch(() => ({}));

  if (body.ocr_text) {
    // Konfiguration aus app_settings lesen
    const { data: settingsRows } = await adminClient
      .from('app_settings')
      .select('key, value');
    const cfg: Record<string, string> = Object.fromEntries(
      (settingsRows ?? []).map((r: any) => [r.key, r.value])
    );
    const freeLimit   = parseInt(cfg['free_scans_limit'] ?? '10', 10);
    const activeModel = cfg['active_model'] ?? 'gemini-2.0-flash';

    // BYOK-Check
    const { data: profileData } = await adminClient
      .from('profiles')
      .select('gemini_api_key')
      .eq('id', user.id)
      .single();
    const byokKey = (profileData as any)?.gemini_api_key?.trim() || '';

    let geminiKey: string;

    if (byokKey) {
      geminiKey = byokKey;
    } else {
      // Atomarer Check + Increment via RPC
      const { data: allowed, error: rpcError } = await adminClient
        .rpc('check_and_increment_scan', { user_uuid: user.id, daily_limit: freeLimit });

      if (rpcError || !allowed) {
        return jsonResponse({
          error:      'demo_limit_reached',
          message:    `Tageslimit von ${freeLimit} kostenlosen Scans erreicht. Morgen wieder verfügbar oder eigenen Gemini API Key im Profil hinterlegen.`,
          demo_limit: freeLimit,
        }, 402);
      }
      geminiKey = SERVER_GEMINI_KEY;
      if (!geminiKey) return jsonResponse({ error: 'Kein Server-Key konfiguriert' }, 503);
    }

    try {
      const url = `https://generativelanguage.googleapis.com/v1beta/models/${activeModel}:generateContent?key=${geminiKey}`;
      const res = await fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          contents: [{ parts: [{ text: RECEIPT_PROMPT + '\n\nOCR-Text:\n' + body.ocr_text }] }],
        }),
      });
      if (!res.ok) throw new Error(`Gemini ${res.status}: ${await res.text()}`);
      const data    = await res.json();
      const text    = data?.candidates?.[0]?.content?.parts?.[0]?.text ?? '';
      const json    = text.replace(/^```(?:json)?\s*/i, '').replace(/\s*```$/, '').trim();
      const receipt = JSON.parse(json);

      return jsonResponse({ status: 'done', result: receipt });
    } catch (err) {
      return jsonResponse({ error: (err as Error).message }, 500);
    }
  }

  return jsonResponse({ error: 'ocr_text erforderlich' }, 400);
});
