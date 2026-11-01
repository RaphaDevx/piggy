/**
 * aiConsent.ts — Einwilligung zur KI-Verarbeitung durch Drittanbieter
 * (App Store Guideline 5.1.2: Weitergabe an Third-Party-AI nur mit Zustimmung).
 *
 * Quittungstext (und bei FEATURES.bankStatements Kontoauszüge) → Anthropic Claude; mit eigenem Key
 * Quittungstext → Google Gemini. Ohne Einwilligung bleibt die Quittungserkennung lokal.
 *
 * Nachweis: Die Entscheidung liegt lokal (AsyncStorage) und best-effort in user_settings
 * (ai_consent_at, ai_consent_version). Der statement-worker klassifiziert nur Artikel von
 * Nutzern mit ai_consent_at (Migration 20261031000001).
 */
import { Alert } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { supabase } from './supabase';
import { featureCopy } from './featureCopy';

export type AiConsent = 'granted' | 'denied';

const STORAGE_KEY = 'piggy.aiConsent.v1';

/** Version des Einwilligungstexts; bei inhaltlicher Änderung des Dialogs erhöhen. */
export const AI_CONSENT_VERSION = '2026-10-31';

export async function getAiConsent(): Promise<AiConsent | null> {
  const value = await AsyncStorage.getItem(STORAGE_KEY);
  return value === 'granted' || value === 'denied' ? value : null;
}

/**
 * Schreibt die Entscheidung in user_settings. Best-effort: Fehler und fehlende Sitzung
 * werden verschluckt, die App darf nie an diesem Nachweis scheitern.
 */
async function persistAiConsent(consent: AiConsent): Promise<void> {
  try {
    const { data } = await supabase.auth.getSession();
    const userId = data?.session?.user?.id;
    if (!userId) return;
    await supabase.from('user_settings').upsert(
      {
        user_id: userId,
        ai_consent_at: consent === 'granted' ? new Date().toISOString() : null,
        ai_consent_version: AI_CONSENT_VERSION,
      },
      { onConflict: 'user_id' },
    );
  } catch {
    // bewusst ignoriert
  }
}

export async function setAiConsent(consent: AiConsent): Promise<void> {
  await AsyncStorage.setItem(STORAGE_KEY, consent);
  await persistAiConsent(consent);
}

/**
 * Holt eine lokal bereits erteilte Einwilligung (vor diesem Update) in die Datenbank nach.
 * Schreibt nur, wenn dort noch kein Zeitstempel steht. Best-effort.
 */
export async function syncAiConsent(): Promise<void> {
  try {
    if ((await getAiConsent()) !== 'granted') return;
    const { data } = await supabase.auth.getSession();
    const userId = data?.session?.user?.id;
    if (!userId) return;
    const { data: row } = await supabase
      .from('user_settings')
      .select('ai_consent_at, ai_consent_version')
      .eq('user_id', userId)
      .maybeSingle();
    if (row?.ai_consent_at) return;
    await persistAiConsent('granted');
  } catch {
    // bewusst ignoriert
  }
}

/** Fragt beim ersten Mal nach; danach gilt die gespeicherte Entscheidung (änderbar im Profil). */
export async function ensureAiConsent(): Promise<boolean> {
  const stored = await getAiConsent();
  if (stored) return stored === 'granted';
  return askAiConsent();
}

/** Zeigt den Einwilligungsdialog immer und speichert die Antwort (z. B. für „Mit KI erneut erkennen“). */
export async function askAiConsent(): Promise<boolean> {
  const granted = await new Promise<boolean>((resolve) => {
    Alert.alert(
      'KI-Erkennung erlauben?',
      featureCopy().aiConsentDialog,
      [
        { text: 'Nicht erlauben', style: 'cancel', onPress: () => resolve(false) },
        { text: 'Erlauben', onPress: () => resolve(true) },
      ],
      { cancelable: false },
    );
  });
  await setAiConsent(granted ? 'granted' : 'denied');
  return granted;
}
