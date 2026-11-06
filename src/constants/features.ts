import { Platform } from 'react-native';

/**
 * features.ts — Funktionsschalter für den Release-Umfang.
 *
 * bankStatements: Kontoauszug-Import, Abgleich mit Quittungen und Vorschläge.
 *   v1.0 ohne Kontoauszüge (Empfehlung Kaufmann-Legal, Store-Legal-Gate). Der Code bleibt
 *   vollständig erhalten; auf `true` stellen schaltet UI und alle Texte wieder ein.
 *   Texte, die davon abhängen, kommen aus src/lib/featureCopy.ts.
 * friendRequests: Forderungen an Freunde (Zahlungsangaben nur lokal).
 * ownApiKeys: Eigene Gemini-/Claude-Schlüssel im Profil. Aus in 1.0 (Rechtsprüfung 2026-11-02, D-02):
 *   sonst ginge Quittungstext ohne eigenen Einwilligungsdialog an Google. Zurück nur mit eigener Einwilligung.
 * analytics: Produkt-Ereignisse ohne Inhalte (src/lib/analytics.ts). Aus, bis Kaufmann-Legal geprüft hat,
 *   Datenschutzerklärung und App-Store-Label ergänzt sind und Migration 20261102000001 angewendet ist.
 * subscription: Abo «Piggy Plus» (RevenueCat, src/lib/subscription.ts). Verfügbar nur, wenn der Schalter an
 *   UND EXPO_PUBLIC_REVENUECAT_IOS_KEY gesetzt ist (siehe subscriptionAvailable); ohne Schlüssel bleibt alles unsichtbar.
 */
export const FEATURES = {
  bankStatements: false,
  friendRequests: true,
  analytics: false,
  ownApiKeys: false,
  subscription: true,
} as const;

/** Abo-Feature ist nutzbar: Schalter an, öffentlicher RevenueCat-iOS-Schlüssel gesetzt, Plattform iOS. */
export function subscriptionAvailable(
  apiKey: string | undefined | null = process.env.EXPO_PUBLIC_REVENUECAT_IOS_KEY,
  os: string = Platform.OS,
  flag: boolean = FEATURES.subscription,
): boolean {
  return flag && os === 'ios' && !!apiKey && apiKey.trim() !== '';
}
