// LLM-Extractors: text-llm (Text-Layer → günstiges Modell) und pdf-llm (Seite als PDF → Vision-Modell).
// Ausgabe als kompaktes Zeilenformat statt JSON (≈ halb so viele Output-Tokens).
import type { ExtractResult, PageInput, ParsedLine } from './types.ts';

const ANTHROPIC_URL = 'https://api.anthropic.com/v1/messages';
const MAX_OUTPUT_TOKENS = 8000;

const CATEGORIES = [
  'Lebensmittel', 'Getränke', 'Haushalt', 'Gesundheit', 'Einrichtung', 'Restaurant & Take-away',
  'Freizeit & Shopping', 'Mobilität', 'Wohnen & Nebenkosten', 'Versicherungen & Abos', 'Diverses',
  'Einkommen', 'Umbuchung',
];

function prompt(page: PageInput): string {
  return `Du liest Seite ${page.pageNo} von ${page.pageCount} eines Kontoauszugs oder einer Kreditkarten-Abrechnung (Schweiz/Deutschland).
Gib JEDE Buchung dieser Seite als eine Zeile aus, sonst nichts (keine Kopfzeile, keine Erklärung):
YYYY-MM-DD|Betrag|Währung|Buchungstext|Kategorie|Saldo

Regeln:
- Betrag NEGATIV für Belastungen/Ausgaben (Soll), POSITIV für Gutschriften (Haben). Punkt als Dezimaltrennzeichen, keine Tausendertrennzeichen.
- Saldo = Kontostand nach dieser Buchung, falls auf der Seite angegeben, sonst leer lassen. Prüfe das Vorzeichen des Betrags an der Saldo-Veränderung.
- Buchungstext vollständig (Händler, Ort, Referenz); ein "|" im Text durch "/" ersetzen.
- Fehlt das Jahr beim Datum, nimm ${page.yearHint} (bzw. das Vorjahr, wenn das Datum sonst in der Zukunft läge).
- Saldo-, Übertrags-, Zwischensummen- und Totalzeilen sind KEINE Buchungen.
- Kategorie exakt eine aus: ${CATEGORIES.join(', ')}. Drogerie (Pflege, Putzmittel) → Haushalt; Apotheke/Arzt → Gesundheit; Möbel/Einrichtungshaus (IKEA, Micasa) → Einrichtung. "Umbuchung" NUR für Überträge zwischen eigenen Konten oder die Zahlung der Kreditkarten-Rechnung — TWINT/Überweisungen von oder an andere Personen sind keine Umbuchung (Gutschrift → Einkommen, Belastung → passende Ausgaben-Kategorie oder Diverses).
- Enthält die Seite keine Buchungen, gib genau "LEER" aus.`;
}

/** TT.MM.JJJJ / TT.MM.JJ / JJJJ-MM-TT → JJJJ-MM-TT */
function normalizeDate(s: string): string | null {
  if (/^\d{4}-\d{2}-\d{2}$/.test(s)) return s;
  const m = s.match(/^(\d{1,2})\.(\d{1,2})\.(\d{2}|\d{4})$/);
  if (!m) return null;
  const year = m[3].length === 2 ? `20${m[3]}` : m[3];
  return `${year}-${m[2].padStart(2, '0')}-${m[1].padStart(2, '0')}`;
}

/** "1'234.50", "1,234.50", "1.234,50", "-12,5", "−12.50" (Unicode-Minus), "12.50-", "(12.50)" → Zahl */
export function parseAmount(s: string): number {
  let t = s.replace(/['’\s]|CHF|EUR|USD/g, '').replace(/[−‒–—]/g, '-');
  const trailing = t.match(/^\+?(\d[\d.,]*)-$/) ?? t.match(/^\((\d[\d.,]*)\)$/);
  if (trailing) t = `-${trailing[1]}`;
  if (t.includes(',') && t.includes('.')) {
    t = t.lastIndexOf(',') > t.lastIndexOf('.') ? t.replace(/\./g, '').replace(',', '.') : t.replace(/,/g, '');
  } else {
    t = t.replace(',', '.');
  }
  return parseFloat(t);
}

export function parseLines(text: string): ParsedLine[] {
  return parseLinesChecked(text).lines;
}

/** Wie parseLines, meldet aber Datenzeilen (beginnen mit Datum), die nicht lesbar waren. */
export function parseLinesChecked(text: string): { lines: ParsedLine[]; rejected: string[] } {
  const lines: ParsedLine[] = [];
  const rejected: string[] = [];
  for (const raw of text.split('\n')) {
    // Toleriert Markdown-Tabellen ("| … |"), Aufzählungen ("- ", "1. ") und Leerraum
    const cleaned = raw.trim().replace(/^[-*•]\s+/, '').replace(/^\d{1,3}[.)]\s+(?=\d)/, '').replace(/^\|/, '').replace(/\|$/, '');
    const parts = cleaned.split('|').map((p) => p.trim());
    const date = parts.length >= 4 ? normalizeDate(parts[0].replace(/[‐-–]/g, '-')) : null;
    if (!date) continue;
    const amount = parseAmount(parts[1]);
    if (!isFinite(amount)) { rejected.push(raw.trim()); continue; }
    lines.push({
      booking_date: date,
      amount,
      currency: /^[A-Z]{3}$/.test(parts[2]) ? parts[2] : 'CHF',
      description: parts[3] || 'Buchung',
      category: parts[4] || null,
      balance: parts[5] ? (isFinite(parseAmount(parts[5])) ? parseAmount(parts[5]) : null) : null,
    });
  }
  return { lines, rejected };
}

/** Keine stillen Verluste: unlesbare Datenzeilen → Fehler (auto: Vision-Fallback, sonst Retry/Fehleranzeige). */
function parseStrict(text: string): ParsedLine[] {
  const { lines, rejected } = parseLinesChecked(text);
  if (rejected.length > 0) {
    throw new Error(`${rejected.length} Buchungszeile(n) nicht lesbar, z. B. "${rejected[0].slice(0, 80)}"`);
  }
  return lines;
}

async function callAnthropic(model: string, content: unknown[]): Promise<{ text: string; tokensIn: number; tokensOut: number }> {
  const apiKey = Deno.env.get('ANTHROPIC_API_KEY');
  if (!apiKey) throw new Error('ANTHROPIC_API_KEY fehlt');
  const res = await fetch(ANTHROPIC_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json', 'x-api-key': apiKey, 'anthropic-version': '2023-06-01' },
    body: JSON.stringify({ model, max_tokens: MAX_OUTPUT_TOKENS, messages: [{ role: 'user', content }] }),
  });
  if (!res.ok) throw new Error(`Anthropic ${res.status}: ${(await res.text()).slice(0, 300)}`);
  const data = await res.json();
  if (data.stop_reason === 'max_tokens') throw new Error('Seite zu lang für ein Output-Limit');
  return {
    text: data?.content?.[0]?.text ?? '',
    tokensIn: data?.usage?.input_tokens ?? 0,
    tokensOut: data?.usage?.output_tokens ?? 0,
  };
}

export async function extractTextLlm(page: PageInput, model: string): Promise<ExtractResult> {
  const r = await callAnthropic(model, [{ type: 'text', text: `${prompt(page)}\n\n--- Seitentext ---\n${page.text}` }]);
  return { lines: parseStrict(r.text), extractor: 'text-llm', model, tokensIn: r.tokensIn, tokensOut: r.tokensOut, rawOutput: r.text };
}

export async function extractPdfLlm(page: PageInput, model: string): Promise<ExtractResult> {
  if (!page.pdfBase64) throw new Error('Seiten-PDF fehlt');
  const r = await callAnthropic(model, [
    { type: 'document', source: { type: 'base64', media_type: 'application/pdf', data: page.pdfBase64 } },
    { type: 'text', text: prompt(page) },
  ]);
  return { lines: parseStrict(r.text), extractor: 'pdf-llm', model, tokensIn: r.tokensIn, tokensOut: r.tokensOut, rawOutput: r.text };
}
