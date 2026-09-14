import { createClient } from 'npm:@supabase/supabase-js@2';

const SUPABASE_URL       = Deno.env.get('SUPABASE_URL')!;
const SUPABASE_SERVICE_KEY = Deno.env.get('SERVICE_ROLE_KEY') ?? Deno.env.get('SUPABASE_SERVICE_ROLE_KEY')!;
const SERVER_GEMINI_KEY  = Deno.env.get('GEMINI_API_KEY') ?? '';

const RECEIPT_PROMPT = `Du bist ein Quittungs-Scanner für Schweizer und deutsche Quittungen.

Analysiere dieses Quittungsbild und extrahiere alle Daten. Antworte AUSSCHLIESSLICH mit einem JSON-Objekt – kein Text davor oder danach, keine Markdown-Codeblöcke.

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
- Wenn kein Datum erkennbar: null`;

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

async function callGemini(imageBase64: string, apiKey: string): Promise<string> {
  const url = `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=${apiKey}`;
  const body = {
    contents: [{
      parts: [
        { inlineData: { mimeType: 'image/jpeg', data: imageBase64 } },
        { text: RECEIPT_PROMPT },
      ],
    }],
  };

  const res = await fetch(url, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body),
  });

  if (!res.ok) {
    const errText = await res.text();
    throw new Error(`Gemini API error ${res.status}: ${errText}`);
  }

  const data = await res.json();
  const rawText: string = data?.candidates?.[0]?.content?.parts?.[0]?.text ?? '';

  // Strip possible markdown code fences
  return rawText.replace(/^```(?:json)?\s*/i, '').replace(/\s*```$/, '').trim();
}

Deno.serve(async (req: Request) => {
  if (req.method === 'OPTIONS') {
    return new Response(null, { headers: CORS_HEADERS });
  }

  // Authenticate request
  const authHeader = req.headers.get('Authorization') ?? '';
  const userToken  = authHeader.replace('Bearer ', '');
  if (!userToken) {
    return jsonResponse({ error: 'Unauthorized' }, 401);
  }

  // Create service-role client (for Storage + queue writes)
  const adminClient = createClient(SUPABASE_URL, SUPABASE_SERVICE_KEY);

  // Create user client (to verify JWT + get user profile)
  const userClient = createClient(SUPABASE_URL, Deno.env.get('SUPABASE_ANON_KEY') ?? '', {
    global: { headers: { Authorization: `Bearer ${userToken}` } },
  });

  const { data: { user }, error: authError } = await userClient.auth.getUser();
  if (authError || !user) {
    return jsonResponse({ error: 'Invalid token' }, 401);
  }

  const body = await req.json().catch(() => ({}));

  // ── Direkter Text-Modus (kein queue_id, kein Bild-Upload) ─────────────────
  if (body.ocr_text) {
    const { data: profileData } = await adminClient
      .from('profiles')
      .select('gemini_api_key, demo_scans_used')
      .eq('id', user.id)
      .single();

    const byokKey   = (profileData as any)?.gemini_api_key?.trim() || '';
    const demoUsed  = (profileData as any)?.demo_scans_used ?? 0;
    const DEMO_LIMIT = 5;

    const geminiKey = byokKey || (demoUsed < DEMO_LIMIT ? SERVER_GEMINI_KEY : '');

    if (!geminiKey) {
      return jsonResponse({
        error: 'demo_limit_reached',
        message: `Du hast ${DEMO_LIMIT} kostenlose Scans aufgebraucht. Hinterlege deinen eigenen Gemini API Key im Profil.`,
        demo_scans_used: demoUsed,
        demo_limit: DEMO_LIMIT,
      }, 402);
    }

    try {
      const url = `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=${geminiKey}`;
      const res = await fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          contents: [{ parts: [{ text: RECEIPT_PROMPT + '\n\nOCR-Text:\n' + body.ocr_text }] }],
        }),
      });
      if (!res.ok) throw new Error(`Gemini ${res.status}`);
      const data   = await res.json();
      const text   = data?.candidates?.[0]?.content?.parts?.[0]?.text ?? '';
      const json   = text.replace(/^```(?:json)?\s*/i, '').replace(/\s*```$/, '').trim();
      const receipt = JSON.parse(json);

      // Demo-Counter hochzählen wenn kein eigener Key
      if (!byokKey) {
        await adminClient
          .from('profiles')
          .update({ demo_scans_used: demoUsed + 1 })
          .eq('id', user.id);
      }

      return jsonResponse({ status: 'done', result: receipt, demo_scans_used: byokKey ? null : demoUsed + 1 });
    } catch (err) {
      return jsonResponse({ error: (err as Error).message }, 500);
    }
  }

  // ── Legacy: Queue-Modus ────────────────────────────────────────────────────
  let queue_id: string;
  try {
    queue_id = body.queue_id;
    if (!queue_id) throw new Error('queue_id fehlt');
  } catch (e) {
    return jsonResponse({ error: (e as Error).message }, 400);
  }

  // Load queue entry
  const { data: queueEntry, error: queueError } = await adminClient
    .from('scan_queue')
    .select('*')
    .eq('id', queue_id)
    .eq('user_id', user.id)
    .single();

  if (queueError || !queueEntry) {
    return jsonResponse({ error: 'Queue-Eintrag nicht gefunden' }, 404);
  }

  if (queueEntry.status !== 'pending') {
    return jsonResponse({ status: queueEntry.status, result: queueEntry.result });
  }

  // Mark as processing
  await adminClient
    .from('scan_queue')
    .update({ status: 'processing' })
    .eq('id', queue_id);

  try {
    // Download image from Storage
    const { data: imageData, error: storageError } = await adminClient
      .storage
      .from('receipts')
      .download(queueEntry.image_path);

    if (storageError || !imageData) {
      throw new Error(`Storage download failed: ${storageError?.message}`);
    }

    // Convert to base64
    const arrayBuffer = await imageData.arrayBuffer();
    const bytes       = new Uint8Array(arrayBuffer);
    let binary        = '';
    for (const b of bytes) binary += String.fromCharCode(b);
    const imageBase64 = btoa(binary);

    // Determine which Gemini API key to use (BYOK > server key)
    const { data: profileData } = await adminClient
      .from('profiles')
      .select('gemini_api_key')
      .eq('id', user.id)
      .single();

    const geminiKey = profileData?.gemini_api_key?.trim() || SERVER_GEMINI_KEY;
    if (!geminiKey) {
      throw new Error('Kein Gemini API Key konfiguriert');
    }

    // Rate-limit guard: 15 RPM → 4s delay between calls
    await new Promise((resolve) => setTimeout(resolve, 4000));

    // Call Gemini
    const jsonText = await callGemini(imageBase64, geminiKey);
    const receipt  = JSON.parse(jsonText);

    // Write result
    await adminClient
      .from('scan_queue')
      .update({
        status:       'done',
        result:       receipt,
        processed_at: new Date().toISOString(),
      })
      .eq('id', queue_id);

    return jsonResponse({ status: 'done', result: receipt });

  } catch (err) {
    const errorMsg = err instanceof Error ? err.message : String(err);

    await adminClient
      .from('scan_queue')
      .update({
        status:       'failed',
        error_msg:    errorMsg,
        processed_at: new Date().toISOString(),
      })
      .eq('id', queue_id);

    return jsonResponse({ error: errorMsg }, 500);
  }
});
