// Stufe 1 der Kontoauszug-Pipeline: eine Seite → Buchungszeilen.
// Jeder Extractor erfüllt dieselbe Schnittstelle; welcher läuft, steuert
// app_settings.statement_extractor (auto | camt | text-llm | pdf-llm).

export interface ParsedLine {
  booking_date: string;   // YYYY-MM-DD
  amount: number;         // negativ = Ausgabe
  currency: string;
  description: string;
  category?: string | null;
  /** Saldo nach der Buchung, falls im Auszug angegeben (für die Vorzeichen-Prüfung) */
  balance?: number | null;
}

export interface ExtractResult {
  lines: ParsedLine[];
  extractor: string;
  model: string | null;
  tokensIn: number;
  tokensOut: number;
  /** Rohantwort des Modells (Diagnose) */
  rawOutput?: string;
}

export interface PageInput {
  pageNo: number;
  pageCount: number;
  /** Text-Layer der Seite (leer bei gescannten Seiten) */
  text: string;
  /** Einzelseite als PDF (nur für pdf-llm) */
  pdfBase64?: string;
  /** Jahr als Hilfe, falls Daten auf der Seite ohne Jahr stehen */
  yearHint: number;
}

export interface ExtractorSettings {
  mode: string;
  textModel: string;
  pdfModel: string;
}
