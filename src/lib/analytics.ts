/**
 * analytics.ts — Produkt-Analytics Ebene A (docs/ANALYTICS-KONZEPT.md).
 *
 * Nur Ereignisse ohne Inhalte: Name aus fester Liste, Eigenschaften nur aus festen Werten.
 * Nie Händler, Artikel, Beträge, Bilder oder Freitext. Eigene Datenbank, kein Drittanbieter,
 * keine Werbe-ID. Abschaltbar in den Einstellungen (Standard: an). Erst aktiv, wenn
 * FEATURES.analytics true ist (nach Prüfung durch Kaufmann-Legal und Label-Anpassung).
 * Fehler werden verschluckt, die App darf nie an Analytics scheitern.
 */
import { Platform } from 'react-native';
import Constants from 'expo-constants';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { supabase } from './supabase';
import { FEATURES } from '../constants/features';

/** Erlaubte Ereignisse und je Ereignis die erlaubten Eigenschaften mit festen Werten. */
export const EVENT_SPEC = {
  app_open: {},
  scan_started: {},
  scan_saved: { mode: ['new', 'merge', 'separate'] },
  scan_failed: { stage: ['ocr', 'ai', 'save'] },
  receipt_edited: {},
  split_created: { mode: ['items', 'equal', 'fixed'] },
} as const satisfies Record<string, Record<string, readonly string[]>>;

export type EventName = keyof typeof EVENT_SPEC;
export type EventProps = Record<string, string>;

const STORAGE_KEY = 'piggy.analytics.v1';

export interface EventRow {
  user_id: string;
  event: string;
  props: EventProps;
  app_version: string | null;
  os: string;
  region: string | null;
}

export async function getAnalyticsEnabled(): Promise<boolean> {
  try { return (await AsyncStorage.getItem(STORAGE_KEY)) !== 'off'; } catch { return true; }
}

export async function setAnalyticsEnabled(enabled: boolean): Promise<void> {
  try { await AsyncStorage.setItem(STORAGE_KEY, enabled ? 'on' : 'off'); } catch { /* bewusst ignoriert */ }
}

function regionFromLocale(): string | null {
  try {
    const m = Intl.DateTimeFormat().resolvedOptions().locale.match(/-([A-Z]{2})\b/);
    return m ? m[1] : null;
  } catch { return null; }
}

/** Prüft Name und Eigenschaften gegen EVENT_SPEC; null, wenn etwas nicht erlaubt ist. */
export function buildEvent(event: string, props: EventProps | undefined, userId: string): EventRow | null {
  const spec = (EVENT_SPEC as Record<string, Record<string, readonly string[]>>)[event];
  if (!spec || !Object.hasOwn(EVENT_SPEC, event)) return null;
  const given = props ?? {};
  for (const [k, v] of Object.entries(given)) {
    if (!Object.hasOwn(spec, k) || !spec[k].includes(v)) return null;
  }
  return {
    user_id: userId,
    event,
    props: { ...given },
    app_version: Constants.expoConfig?.version ?? null,
    os: `${Platform.OS} ${String(Platform.Version).split('.')[0]}`.slice(0, 20),
    region: regionFromLocale(),
  };
}

/** Meldet ein Ereignis. Feuer und vergessen: wirft nie, wartet nicht auf das Netz. */
export async function track(event: EventName, props?: EventProps): Promise<void> {
  try {
    if (!FEATURES.analytics) return;
    if (!(await getAnalyticsEnabled())) return;
    const { data } = await supabase.auth.getSession();
    const userId = data?.session?.user?.id;
    if (!userId) return;
    const row = buildEvent(event, props, userId);
    if (!row) return;
    await supabase.from('analytics_events').insert(row);
  } catch {
    // bewusst ignoriert
  }
}
