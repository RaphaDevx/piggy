import { resolveLocale, type Locale } from '../lib/taxonomy';

function detectLocale(): Locale {
  try {
    return resolveLocale(Intl.DateTimeFormat().resolvedOptions().locale);
  } catch {
    return 'de';
  }
}

// Gerätesprache ändert sich während der Laufzeit nicht — einmal bestimmen genügt
const DEVICE_LOCALE = detectLocale();

/** Anzeigesprache für Kategorie-Labels (de/en/fr/it), abgeleitet aus der Gerätesprache. */
export function useLocale(): Locale {
  return DEVICE_LOCALE;
}
