// Extractor-Auswahl pro Seite. "auto": Text-Layer vorhanden → text-llm (günstig),
// sonst bzw. bei Fehler → pdf-llm. Neue Extractors hier registrieren.
import type { ExtractResult, ExtractorSettings, PageInput } from './types.ts';
import { extractPdfLlm, extractTextLlm } from './llm.ts';

export type { ExtractResult, ExtractorSettings, PageInput, ParsedLine } from './types.ts';
export { extractCamt, isCamt, parseCamt } from './camt.ts';
export { parseAmount, parseLines } from './llm.ts';
export { fixSignsByBalance } from './balance.ts';

/** Mindestmenge Text, ab der eine Seite als "digital" gilt (sonst Scan → Vision). */
const MIN_TEXT_CHARS = 80;

/** Unterhalb dieser Zahl von Datumszeilen wird nicht geprüft (Deckblatt, Summenseite). */
const MIN_LINES_FOR_CHECK = 5;
/** Saldo-/Übertragszeilen tragen auch Daten → etwas Spielraum lassen. */
const PLAUSIBLE_RATIO = 0.75;

/** Zeilen, die mit einem Datum beginnen (TT.MM.JJ[JJ] oder JJJJ-MM-TT) ≈ Anzahl Buchungen. */
export function countDateLines(text: string): number {
  return text.split('\n').filter((l) => /^\s*(\d{1,2}\.\d{1,2}\.(\d{2}|\d{4})|\d{4}-\d{2}-\d{2})\b/.test(l)).length;
}

function hasUsableText(text: string): boolean {
  return text.replace(/\s/g, '').length >= MIN_TEXT_CHARS && /\d/.test(text);
}

export async function extractPage(page: PageInput, settings: ExtractorSettings): Promise<ExtractResult> {
  switch (settings.mode) {
    case 'text-llm':
      return extractTextLlm(page, settings.textModel);
    case 'pdf-llm':
      return extractPdfLlm(page, settings.pdfModel);
    default: {
      if (!hasUsableText(page.text)) return extractPdfLlm(page, settings.pdfModel);
      try {
        const result = await extractTextLlm(page, settings.textModel);
        // Plausibilität: deutlich weniger Buchungen als Datumszeilen im Text → Seite per Vision nachlesen
        const expected = countDateLines(page.text);
        if (expected >= MIN_LINES_FOR_CHECK && result.lines.length < expected * PLAUSIBLE_RATIO) {
          return await extractPdfLlm(page, settings.pdfModel);
        }
        return result;
      } catch {
        return extractPdfLlm(page, settings.pdfModel);
      }
    }
  }
}
