import Anthropic from 'npm:@anthropic-ai/sdk@0.30.0';

const client = new Anthropic({ apiKey: Deno.env.get('ANTHROPIC_API_KEY') });

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
  "payment_card": "z.B. Visa ···· 4242 oder null",
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

function generateMarkdown(receipt: Record<string, unknown>): string {
  const items = receipt.items as Array<Record<string, unknown>>;
  const currency = String(receipt.currency || 'CHF');
  const totalAmount = Number(receipt.total_amount || 0);
  const dateStr = receipt.date ? String(receipt.date) : null;

  let date = 'Unbekannt';
  if (dateStr) {
    try {
      date = new Date(dateStr).toLocaleDateString('de-CH', {
        day: '2-digit', month: '2-digit', year: 'numeric',
      });
    } catch {
      date = dateStr;
    }
  }

  const allTags = [...new Set(items.flatMap((i) => i.tags as string[]))];
  const paymentCard = receipt.payment_card ? ` — ${receipt.payment_card}` : '';
  const paymentLine = `${receipt.payment_method}${paymentCard}`;

  const rows = items.map((item) => {
    const qty = (item.quantity as number) !== 1
      ? `${item.quantity}× ${item.unit || ''}`.trim()
      : String(item.unit || '1×');
    const price = `${currency} ${Number(item.total_price).toFixed(2)}`;
    const tags = (item.tags as string[]).join(', ');
    return `| ${item.name} | ${qty} | ${price} | ${tags} |`;
  }).join('\n');

  return `# Quittung: ${receipt.store_name} — ${date}

## Zusammenfassung
- **Geschäft:** ${receipt.store_name} (${receipt.store_category})
- **Datum:** ${date}
- **Betrag:** ${currency} ${totalAmount.toFixed(2)}
- **Zahlungsmittel:** ${paymentLine}

## Tags dieser Quittung
${allTags.map((t) => `\`${t}\``).join(' · ')}

## Artikel

| Artikel | Menge | Preis | Tags |
|---------|-------|-------|------|
${rows}
`;
}

Deno.serve(async (req: Request) => {
  // CORS preflight
  if (req.method === 'OPTIONS') {
    return new Response(null, {
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': 'authorization, x-client-info, apikey, content-type',
      },
    });
  }

  try {
    const { image_base64, mime_type } = await req.json();

    if (!image_base64 || !mime_type) {
      return new Response(JSON.stringify({ error: 'image_base64 und mime_type sind erforderlich' }), {
        status: 400,
        headers: { 'Content-Type': 'application/json' },
      });
    }

    const msg = await client.messages.create({
      model: 'claude-sonnet-4-6',
      max_tokens: 4096,
      messages: [{
        role: 'user',
        content: [
          {
            type: 'image',
            source: {
              type: 'base64',
              media_type: mime_type as 'image/jpeg' | 'image/png' | 'image/gif' | 'image/webp',
              data: image_base64,
            },
          },
          { type: 'text', text: RECEIPT_PROMPT },
        ],
      }],
    });

    const rawText = msg.content[0].type === 'text' ? msg.content[0].text : '';

    // Strip possible markdown code fences
    const jsonText = rawText.replace(/^```(?:json)?\s*/i, '').replace(/\s*```$/, '').trim();
    const receipt = JSON.parse(jsonText);
    const markdown = generateMarkdown(receipt);

    return new Response(JSON.stringify({ receipt, markdown }), {
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      },
    });
  } catch (err) {
    const message = err instanceof Error ? err.message : String(err);
    return new Response(JSON.stringify({ error: message }), {
      status: 500,
      headers: { 'Content-Type': 'application/json' },
    });
  }
});
