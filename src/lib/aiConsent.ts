/**
 * aiConsent.ts — Einwilligung zur KI-Verarbeitung durch Drittanbieter
 * (App Store Guideline 5.1.2: Weitergabe an Third-Party-AI nur mit Zustimmung).
 *
 * Quittungstext + Kontoauszüge → Anthropic Claude; mit eigenem Key Quittungstext → Google Gemini.
 * Ohne Einwilligung bleibt die Quittungserkennung lokal (Apple Intelligence / Regex).
 */
import { Alert } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export type AiConsent = 'granted' | 'denied';

const STORAGE_KEY = 'piggy.aiConsent.v1';

export async function getAiConsent(): Promise<AiConsent | null> {
  const value = await AsyncStorage.getItem(STORAGE_KEY);
  return value === 'granted' || value === 'denied' ? value : null;
}

export async function setAiConsent(consent: AiConsent): Promise<void> {
  await AsyncStorage.setItem(STORAGE_KEY, consent);
}

/** Fragt beim ersten Mal nach; danach gilt die gespeicherte Entscheidung (änderbar im Profil). */
export async function ensureAiConsent(): Promise<boolean> {
  const stored = await getAiConsent();
  if (stored) return stored === 'granted';

  const granted = await new Promise<boolean>((resolve) => {
    Alert.alert(
      'KI-Erkennung erlauben?',
      'Für eine genauere Erkennung sendet Piggy den erkannten Quittungstext an Anthropic (Claude). ' +
        'Mit eigenem Gemini-Key geht er stattdessen an Google Gemini. ' +
        'Importierte Kontoauszüge werden ebenfalls von Anthropic analysiert. ' +
        'Die Anbieter nutzen die Daten nicht zum Training. ' +
        'Ohne Zustimmung erkennt Piggy Quittungen nur auf deinem Gerät. ' +
        'Du kannst das jederzeit im Profil ändern.',
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
