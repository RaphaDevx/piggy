// CAMT.053 (ISO 20022 Kontoauszug, XML) — deterministisch, ohne LLM.
// Jede <Ntry> ist eine Buchung; <CdtDbtInd> DBIT = Ausgabe (negativ).
import type { ExtractResult, ParsedLine } from './types.ts';

function tag(xml: string, name: string): string | null {
  const m = xml.match(new RegExp(`<(?:\\w+:)?${name}\\b[^>]*>([\\s\\S]*?)</(?:\\w+:)?${name}>`));
  return m ? m[1].trim() : null;
}

function allTags(xml: string, name: string): string[] {
  const re = new RegExp(`<(?:\\w+:)?${name}\\b[^>]*>([\\s\\S]*?)</(?:\\w+:)?${name}>`, 'g');
  return [...xml.matchAll(re)].map((m) => m[1].replace(/<[^>]+>/g, ' ').replace(/\s+/g, ' ').trim());
}

function decode(s: string): string {
  return s.replace(/&amp;/g, '&').replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&quot;/g, '"').replace(/&apos;/g, "'");
}

export function isCamt(text: string): boolean {
  return /<(?:\w+:)?BkToCstmrStmt\b/.test(text) && /<(?:\w+:)?Ntry\b/.test(text);
}

export function parseCamt(xml: string): ParsedLine[] {
  const entries = xml.match(/<(?:\w+:)?Ntry\b[\s\S]*?<\/(?:\w+:)?Ntry>/g) ?? [];
  const lines: ParsedLine[] = [];
  for (const entry of entries) {
    const amtMatch = entry.match(/<(?:\w+:)?Amt\b[^>]*Ccy="([A-Z]{3})"[^>]*>([\d.,-]+)</);
    if (!amtMatch) continue;
    const amount = parseFloat(amtMatch[2].replace(',', '.'));
    const debit = tag(entry, 'CdtDbtInd') === 'DBIT';
    const bookg = tag(entry, 'BookgDt') ?? tag(entry, 'ValDt') ?? '';
    const date = (tag(bookg, 'Dt') ?? tag(bookg, 'DtTm') ?? '').slice(0, 10);
    if (!/^\d{4}-\d{2}-\d{2}$/.test(date) || !isFinite(amount)) continue;

    const parts = [
      ...allTags(entry, 'AddtlNtryInf'),
      ...allTags(entry, 'Nm'),
      ...allTags(entry, 'Ustrd'),
    ].filter(Boolean);
    const description = decode([...new Set(parts)].join(' / ')).slice(0, 300) || 'Buchung';
    lines.push({ booking_date: date, amount: debit ? -Math.abs(amount) : Math.abs(amount), currency: amtMatch[1], description });
  }
  return lines;
}

export function extractCamt(xml: string): ExtractResult {
  return { lines: parseCamt(xml), extractor: 'camt', model: null, tokensIn: 0, tokensOut: 0 };
}
