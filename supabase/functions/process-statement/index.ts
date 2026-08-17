import Anthropic from 'npm:@anthropic-ai/sdk@0.30.0';

const client = new Anthropic({ apiKey: Deno.env.get('ANTHROPIC_API_KEY') });

const STATEMENT_PROMPT = `Du analysierst einen Kontoauszug oder eine Kreditkarten-Abrechnung (Schweiz oder Deutschland), als PDF, CSV oder CAMT.053-XML.

Extrahiere ALLE Buchungen/Transaktionen und antworte AUSSCHLIESSLICH mit einem JSON-Objekt – kein Text davor oder danach, keine Markdown-Codeblöcke.

JSON-Struktur:
{
  "account_label": "z.B. 'UBS Privatkonto' oder 'Visa Kreditkarte' falls erkennbar, sonst null",
  "period_start": "YYYY-MM-DD oder null",
  "period_end": "YYYY-MM-DD oder null",
  "transactions": [
    {
      "booking_date": "YYYY-MM-DD",
      "amount": Betrag als Zahl,
      "currency": "CHF, EUR oder USD",
      "description": "Originaltext/Buchungstext der Bank, so wie er im Auszug steht"
    }
  ]
}

Regeln:
- WICHTIG: "amount" ist NEGATIV für Ausgaben/Belastungen (Geld verlässt das Konto) und POSITIV für Gutschriften/Eingänge — unabhängig davon, ob die Quelle Soll/Haben in separaten Spalten oder mit Vorzeichen ausweist.
- Bei Kreditkarten-Abrechnungen ist "booking_date" das Datum, an dem die Buchung auf der Abrechnung erscheint (nicht das ursprüngliche Kaufdatum, falls beide vorkommen — falls nur ein Datum vorhanden ist, dieses verwenden).
- Buchungstext möglichst vollständig übernehmen (Händlername, Ort, Referenz), da er später für den Abgleich mit Quittungen genutzt wird.
- Reine Kontoführungsgebühren, Zinsen, interne Umbuchungen etc. ebenfalls als normale Transaktionen aufnehmen.
- Bei CAMT.053-XML: jede <Ntry> ist eine Transaktion, <Amt> mit <CdtDbtInd> (DBIT = negativ, CRDT = positiv) bestimmt das Vorzeichen.
- Bei CSV: Spaltenbedeutung aus der Kopfzeile ableiten (Datum, Betrag/Saldo, Text/Buchungstext, Soll/Haben).
- Wenn ein Feld nicht ermittelbar ist: sinnvollen Default verwenden (currency "CHF", period_start/end null).`;

function buildMessageContent(fileBase64: string, mimeType: string) {
  if (mimeType === 'application/pdf') {
    return [
      {
        type: 'document' as const,
        source: { type: 'base64' as const, media_type: 'application/pdf' as const, data: fileBase64 },
      },
      { type: 'text' as const, text: STATEMENT_PROMPT },
    ];
  }

  // CSV / CAMT.053 (XML) / sonstiger Text — als Klartext einbetten
  const text = new TextDecoder().decode(
    Uint8Array.from(atob(fileBase64), (c) => c.charCodeAt(0))
  );
  return [
    { type: 'text' as const, text: `${STATEMENT_PROMPT}\n\n--- Dateiinhalt ---\n${text}` },
  ];
}

Deno.serve(async (req: Request) => {
  if (req.method === 'OPTIONS') {
    return new Response(null, {
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': 'authorization, x-client-info, apikey, content-type',
      },
    });
  }

  try {
    const { file_base64, mime_type, account_type } = await req.json();

    if (!file_base64 || !mime_type) {
      return new Response(JSON.stringify({ error: 'file_base64 und mime_type sind erforderlich' }), {
        status: 400,
        headers: { 'Content-Type': 'application/json' },
      });
    }

    const msg = await client.messages.create({
      model: 'claude-sonnet-4-6',
      max_tokens: 8192,
      messages: [{ role: 'user', content: buildMessageContent(file_base64, mime_type) }],
    });

    const rawText = msg.content[0].type === 'text' ? msg.content[0].text : '';
    const jsonText = rawText.replace(/^```(?:json)?\s*/i, '').replace(/\s*```$/, '').trim();
    const parsed = JSON.parse(jsonText);

    return new Response(JSON.stringify({
      account_label: parsed.account_label ?? null,
      period_start:  parsed.period_start ?? null,
      period_end:    parsed.period_end ?? null,
      account_type:  account_type === 'credit' ? 'credit' : 'debit',
      transactions:  parsed.transactions ?? [],
    }), {
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
