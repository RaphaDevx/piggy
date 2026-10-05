/**
 * Kategorien — ein grober Satz für Quittungsartikel und Bank-Buchungen.
 *
 * Jeder Artikel hat genau eine Kategorie (receipt_items.tags[0]); Nutzer können
 * eine andere wählen oder eine eigene anlegen. Bank-Buchungen haben dieselben
 * Ausgaben-Kategorien plus Einnahmen/Umbuchung (bank_transactions.category).
 * Feine Tags aus Versionen ≤ Build 17 werden über LEGACY_TAG_CATEGORY abgebildet.
 */

import { TAXONOMY } from './taxonomy.ts';

export interface TagInfo {
  label: string;
  color: string;
  group: string;
}

export interface CategoryInfo {
  key: string;
  color: string;
}

/** Ausgaben-Kategorien — aus der Taxonomie, Reihenfolge = Anzeige im Picker. */
export const EXPENSE_CATEGORIES: CategoryInfo[] = TAXONOMY.map((c) => ({ key: c.id, color: c.color }));

/** Kategorien, die auf Quittungsartikeln vorkommen (Picker zeigt sie zuerst). */
export const ITEM_CATEGORY_KEYS = TAXONOMY.filter((c) => c.scope !== 'bank').map((c) => c.id);

export const INCOME_CATEGORY = 'Einkommen';
/** Geld zwischen eigenen Konten (z. B. Kreditkarten-Rechnung) — zählt nicht als Ausgabe/Einnahme. */
export const TRANSFER_CATEGORY = 'Umbuchung';
export const UNCATEGORIZED = 'Nicht kategorisiert';

export const TRANSACTION_CATEGORIES = [
  ...EXPENSE_CATEGORIES.map((c) => c.key),
  INCOME_CATEGORY,
  TRANSFER_CATEGORY,
];

const SPECIAL_COLORS: Record<string, string> = {
  [INCOME_CATEGORY]:   '#30D158',
  [TRANSFER_CATEGORY]: '#636366',
  [UNCATEGORIZED]:     '#48484A',
};

const CUSTOM_COLOR = '#AC8E68';

/** Frühere Hauptkategorie, seit 2026-10-06 aufgeteilt in Haushalt (Pflege) und Gesundheit. */
export const LEGACY_CARE_CATEGORY = 'Körperpflege & Gesundheit';

/** Feine Tags (Build ≤ 17) → Hauptkategorie. */
export const LEGACY_TAG_CATEGORY: Record<string, string> = {
  'Gemüse & Obst': 'Lebensmittel', 'Milchprodukte': 'Lebensmittel', 'Fleisch & Fisch': 'Lebensmittel',
  'Backwaren': 'Lebensmittel', 'Tiefkühlkost': 'Lebensmittel', 'Konserven': 'Lebensmittel',
  'Grundnahrungsmittel': 'Lebensmittel', 'Snacks & Süsswaren': 'Lebensmittel',
  'Alkohol': 'Getränke', 'Kaffee & Tee': 'Getränke',
  'Reinigung': 'Haushalt', 'Entsorgung': 'Haushalt', 'Küche': 'Einrichtung', 'Wohnen & Deko': 'Einrichtung',
  'Hygiene': 'Haushalt', 'Körperpflege': 'Haushalt', 'Haarpflege': 'Haushalt', 'Mundpflege': 'Haushalt',
  'Damenhygiene': 'Haushalt', 'Gesundheit': 'Gesundheit', 'Medikamente': 'Gesundheit', 'Nahrungsergänzung': 'Gesundheit',
  'Kleidung': 'Freizeit & Shopping', 'Elektronik': 'Freizeit & Shopping',
  'Freizeit & Hobby': 'Freizeit & Shopping', 'Büro': 'Freizeit & Shopping',
  [LEGACY_CARE_CATEGORY]: 'Gesundheit',
};

/** Feine Tags (Build ≤ 17) → Unterkategorie, wo eindeutig. */
export const LEGACY_TAG_SUBCATEGORY: Record<string, string> = {
  'Gemüse & Obst': 'food.produce', 'Milchprodukte': 'food.dairy', 'Fleisch & Fisch': 'food.meat_fish',
  'Backwaren': 'food.bakery', 'Tiefkühlkost': 'food.frozen', 'Konserven': 'food.pantry',
  'Grundnahrungsmittel': 'food.pantry', 'Snacks & Süsswaren': 'food.snacks',
  'Alkohol': 'drinks.alcohol', 'Kaffee & Tee': 'drinks.hot',
  'Reinigung': 'household.cleaning', 'Entsorgung': 'household.supplies', 'Küche': 'home.kitchen', 'Wohnen & Deko': 'home.decor',
  'Hygiene': 'household.care', 'Körperpflege': 'household.care', 'Haarpflege': 'household.care', 'Mundpflege': 'household.care',
  'Damenhygiene': 'household.care', 'Medikamente': 'health.medicine', 'Nahrungsergänzung': 'health.supplements',
  'Kleidung': 'leisure.clothing', 'Elektronik': 'leisure.electronics', 'Freizeit & Hobby': 'leisure.hobby',
};

const CATEGORY_BY_KEY: Record<string, CategoryInfo> =
  Object.fromEntries(EXPENSE_CATEGORIES.map((c) => [c.key, c]));

/** Bekannte Kategorie für einen (evtl. alten) Tag, sonst der Tag selbst (eigene Kategorie). */
export function normalizeCategory(tag: string): string {
  return LEGACY_TAG_CATEGORY[tag] ?? tag;
}

export function isKnownCategory(tag: string): boolean {
  return tag in CATEGORY_BY_KEY || tag in SPECIAL_COLORS;
}

/** Kategorie eines Artikels: erster Tag, normalisiert. */
export function itemCategory(tags: string[] | null | undefined): string {
  const first = tags?.find((t) => t.trim());
  return first ? normalizeCategory(first) : 'Diverses';
}

/** Kategorie aus KI-/Regel-Ausgabe: nur bekannte Kategorien, sonst Diverses. */
export function suggestedCategory(tags: string[] | null | undefined): string {
  const category = itemCategory(tags);
  return category in CATEGORY_BY_KEY ? category : 'Diverses';
}

export function getCategoryColor(category: string): string {
  const key = normalizeCategory(category);
  return CATEGORY_BY_KEY[key]?.color ?? SPECIAL_COLORS[key] ?? CUSTOM_COLOR;
}

// ── Kompatibilität mit bestehenden Screens ───────────────────────────────────

export const TAG_MAP: Record<string, TagInfo> = Object.fromEntries(
  EXPENSE_CATEGORIES.map((c) => [c.key, { label: c.key, color: c.color, group: c.key }]),
);

export const ALL_TAGS = ITEM_CATEGORY_KEYS;

export function getTagColor(tag: string): string {
  return getCategoryColor(tag);
}

/** Eigene Kategorien bilden ihre eigene Gruppe. */
export function getTagGroup(tag: string): string {
  return normalizeCategory(tag);
}

export const TAG_GROUPS: Record<string, string> = Object.fromEntries(
  EXPENSE_CATEGORIES.map((c) => [c.key, c.color]),
);

export const STORE_CATEGORIES = [
  'Supermarkt',
  'Drogerie',
  'Apotheke',
  'Baumarkt',
  'Bäckerei',
  'Restaurant',
  'Tankstelle',
  'Elektronik',
  'Kleidung',
  'Diverses',
];

export const PAYMENT_METHODS = [
  'Karte',
  'Bargeld',
  'TWINT',
  'Rechnung',
  'Unbekannt',
];
