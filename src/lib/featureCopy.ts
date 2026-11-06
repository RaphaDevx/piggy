/**
 * featureCopy.ts — Texte, die vom Funktionsschalter FEATURES.bankStatements abhängen.
 * Einzige Stelle für diese Varianten, damit keine Ifs in den Screens verstreut sind.
 * Der Schalter ist ein Parameter (Default: aktueller Wert), damit beide Varianten testbar sind.
 */
import { FEATURES } from '../constants/features';

type Flags = { bankStatements: boolean; ownApiKeys?: boolean };

export function featureCopy(flags: Flags = FEATURES) {
  const bank = flags.bankStatements;
  const byok = !!flags.ownApiKeys;
  return {
    /** Einwilligungsdialog zur KI-Erkennung (Anbieter, Land, Folgen einer Ablehnung). */
    aiConsentDialog:
      'Für eine genauere Erkennung sendet Piggy den erkannten Quittungstext an Anthropic (Claude), ' +
      'einen Anbieter in den USA. ' + (byok ? 'Mit eigenem Gemini-Key geht er stattdessen an Google Gemini (USA). ' : '') +
      (bank ? 'Importierte Kontoauszüge werden ebenfalls von Anthropic analysiert. ' : '') +
      'Wie die Anbieter die Daten behandeln, steht in der Datenschutzerklärung. ' +
      'Wenn du nicht zustimmst, erkennt Piggy Quittungen nur auf deinem Gerät und die App bleibt weiter nutzbar. ' +
      'Du kannst das jederzeit im Profil ändern.',
    /** Untertitel der Einwilligungs-Zeile im Profil. */
    aiConsentRowSub: (bank ? 'Quittungstext und Kontoauszüge an Anthropic Claude (USA)' : 'Quittungstext an Anthropic Claude (USA)') +
      (byok ? ', mit eigenem Key an Google Gemini (USA)' : ''),
    /** Text im Dialog „Konto löschen“. */
    deleteAccountBody: bank
      ? 'Dein Konto und alle Daten (Quittungen, Bilder, Kontoauszüge, Projekte, Freunde, Splits) werden endgültig gelöscht. Das kann nicht rückgängig gemacht werden.'
      : 'Dein Konto und alle Daten (Quittungen, Bilder, Projekte, Freunde, Splits) werden endgültig gelöscht. Das kann nicht rückgängig gemacht werden.',
    /** Leerzustand im Finanzprofil. */
    profileEmpty: bank
      ? 'Sobald du einen Kontoauszug importiert oder Quittungen gescannt hast, zeigt Piggy hier dein Finanzprofil.'
      : 'Sobald du Quittungen gescannt hast, zeigt Piggy hier dein Finanzprofil.',
    /** Leerzustand der Monatsübersicht im Finanzen-Tab. */
    financeEmpty: 'Keine Daten für diesen Monat. Scanne eine Quittung, dann erscheint sie hier.',
    /** Hinweis im Dialog zum Zusammenführen doppelter Quittungen. */
    duplicateProtectionNote:
      (bank
        ? 'Geprüfte Artikel und bestehende Bank-, Split- oder Projektzuordnungen werden geschützt.'
        : 'Geprüfte Artikel und bestehende Split- oder Projektzuordnungen werden geschützt.') +
      ' Bei einem Konflikt wird nichts zusammengeführt und eine Erklärung angezeigt.',
    /** Speicherort und KI-Hinweis im Profil (Footer und Datenschutz-Zeile). */
    storageFooter: 'Deine Belege werden in Zürich gespeichert. Die KI-Erkennung läuft nur auf Wunsch über Anbieter in den USA.',
    storageRowSub: 'Belege in Zürich (eu-central-2), KI nur auf Wunsch über Anbieter in den USA',
  };
}
