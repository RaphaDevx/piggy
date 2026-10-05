/**
 * Kategorien — ein grober Satz für Quittungsartikel und Bank-Buchungen.
 *
 * Jeder Artikel hat genau eine Kategorie (receipt_items.tags[0]); Nutzer können
 * eine andere wählen oder eine eigene anlegen. Bank-Buchungen haben dieselben
 * Ausgaben-Kategorien plus Einnahmen/Umbuchung (bank_transactions.category).
 * Feine Tags aus Versionen ≤ Build 17 werden über LEGACY_TAG_CATEGORY abgebildet.
 */

export interface TagInfo {
  label: string;
  color: string;
  group: string;
}

export interface CategoryInfo {
  key: string;
  color: string;
}

/** Ausgaben-Kategorien — Reihenfolge = Anzeige im Picker. */
export const EXPENSE_CATEGORIES: CategoryInfo[] = [
  { key: 'Lebensmittel',              color: '#34C759' },
  { key: 'Getränke',                  color: '#0A84FF' },
  { key: 'Haushalt',                  color: '#FF9F0A' },
  { key: 'Körperpflege & Gesundheit', color: '#64D2FF' },
  { key: 'Restaurant & Take-away',    color: '#FF6B6B' },
  { key: 'Freizeit & Shopping',       color: '#BF5AF2' },
  { key: 'Mobilität',                 color: '#5E5CE6' },
  { key: 'Wohnen & Nebenkosten',      color: '#A2845E' },
  { key: 'Versicherungen & Abos',     color: '#30B0C7' },
  { key: 'Diverses',                  color: '#8E8E93' },
];

/** Kategorien, die auf Quittungsartikeln typischerweise vorkommen (Picker zeigt sie zuerst). */
export const ITEM_CATEGORY_KEYS = [
  'Lebensmittel', 'Getränke', 'Haushalt', 'Körperpflege & Gesundheit',
  'Restaurant & Take-away', 'Freizeit & Shopping', 'Mobilität', 'Diverses',
];

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

/** Feine Tags (Build ≤ 17) → grobe Kategorie. */
export const LEGACY_TAG_CATEGORY: Record<string, string> = {
  'Gemüse & Obst': 'Lebensmittel', 'Milchprodukte': 'Lebensmittel', 'Fleisch & Fisch': 'Lebensmittel',
  'Backwaren': 'Lebensmittel', 'Tiefkühlkost': 'Lebensmittel', 'Konserven': 'Lebensmittel',
  'Grundnahrungsmittel': 'Lebensmittel', 'Snacks & Süsswaren': 'Lebensmittel',
  'Alkohol': 'Getränke', 'Kaffee & Tee': 'Getränke',
  'Reinigung': 'Haushalt', 'Entsorgung': 'Haushalt', 'Küche': 'Haushalt', 'Wohnen & Deko': 'Haushalt',
  'Hygiene': 'Körperpflege & Gesundheit', 'Körperpflege': 'Körperpflege & Gesundheit',
  'Haarpflege': 'Körperpflege & Gesundheit', 'Mundpflege': 'Körperpflege & Gesundheit',
  'Damenhygiene': 'Körperpflege & Gesundheit', 'Gesundheit': 'Körperpflege & Gesundheit',
  'Medikamente': 'Körperpflege & Gesundheit', 'Nahrungsergänzung': 'Körperpflege & Gesundheit',
  'Kleidung': 'Freizeit & Shopping', 'Elektronik': 'Freizeit & Shopping',
  'Freizeit & Hobby': 'Freizeit & Shopping', 'Büro': 'Freizeit & Shopping',
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
