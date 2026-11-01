/**
 * features.ts — Funktionsschalter für den Release-Umfang.
 *
 * bankStatements: Kontoauszug-Import, Abgleich mit Quittungen und Vorschläge.
 *   v1.0 ohne Kontoauszüge (Empfehlung Kaufmann-Legal, Store-Legal-Gate). Der Code bleibt
 *   vollständig erhalten; auf `true` stellen schaltet UI und alle Texte wieder ein.
 *   Texte, die davon abhängen, kommen aus src/lib/featureCopy.ts.
 * friendRequests: Forderungen an Freunde (Zahlungsangaben nur lokal).
 */
export const FEATURES = {
  bankStatements: false,
  friendRequests: true,
} as const;
