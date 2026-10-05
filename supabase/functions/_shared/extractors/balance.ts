// Vorzeichen-Korrektur über den laufenden Saldo.
// Im Text-Layer eines PDFs geht die Spalte (Belastung/Gutschrift) verloren; der Saldo
// nach jeder Buchung zeigt eindeutig, ob Geld ab- oder zugeflossen ist.
import type { ParsedLine } from './types.ts';

const TOLERANCE = 0.01;

/**
 * Erwartet die Zeilen in Auszugs-Reihenfolge (Seite, dann Zeile). Dreht das Vorzeichen,
 * wenn die Saldo-Veränderung zum umgekehrten Betrag passt. Zeilen ohne Saldo bleiben,
 * wie sie sind, und unterbrechen die Kette nicht.
 */
export function fixSignsByBalance(lines: ParsedLine[]): { lines: ParsedLine[]; flipped: number } {
  let previous: number | null = null;
  let flipped = 0;
  const fixed = lines.map((line) => {
    const balance = line.balance;
    if (balance == null || !isFinite(balance)) return line;
    let result = line;
    if (previous != null) {
      const delta = balance - previous;
      const fitsAsIs = Math.abs(delta - line.amount) <= TOLERANCE;
      const fitsFlipped = Math.abs(delta + line.amount) <= TOLERANCE;
      if (!fitsAsIs && fitsFlipped) {
        result = { ...line, amount: -line.amount };
        flipped++;
      }
    }
    previous = balance;
    return result;
  });
  return { lines: fixed, flipped };
}
