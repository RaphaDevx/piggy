import {
  TAXONOMY, ALL_SUBCATEGORY_KEYS, classifyItemName, categoryOfSubcategory, natureOf, resolveLocale,
  categoryLabel, subcategoryLabel, natureLabel, groupAdaptive, productKey, OTHER_GROUP_KEY,
  type GroupNode, type Locale,
} from './taxonomy';
import { normalizeCategory, LEGACY_TAG_SUBCATEGORY, ITEM_CATEGORY_KEYS } from './categories';

describe('Taxonomie-Struktur', () => {
  test('Unterkategorie-Schlüssel sind eindeutig und beginnen mit dem Bereich', () => {
    expect(new Set(ALL_SUBCATEGORY_KEYS).size).toBe(ALL_SUBCATEGORY_KEYS.length);
    for (const c of TAXONOMY) expect(c.subcategories.length).toBeGreaterThan(0);
  });

  test('jede Kategorie und Unterkategorie hat Labels in allen Sprachen', () => {
    const locales: Locale[] = ['de', 'en', 'fr', 'it'];
    for (const c of TAXONOMY) {
      for (const l of locales) expect(c.label[l]).toBeTruthy();
      for (const s of c.subcategories) for (const l of locales) expect(s.label[l]).toBeTruthy();
    }
  });

  test('Bank-only-Kategorien erscheinen nicht im Artikel-Picker', () => {
    expect(ITEM_CATEGORY_KEYS).not.toContain('Wohnen & Nebenkosten');
    expect(ITEM_CATEGORY_KEYS).toContain('Einrichtung');
  });

  test('Alt-Tags zeigen auf existierende Unterkategorien', () => {
    for (const key of Object.values(LEGACY_TAG_SUBCATEGORY)) expect(ALL_SUBCATEGORY_KEYS).toContain(key);
    expect(normalizeCategory('Körperpflege & Gesundheit')).toBe('Gesundheit');
    expect(normalizeCategory('Mundpflege')).toBe('Haushalt');
  });
});

describe('classifyItemName', () => {
  test.each([
    ['M-Budget Toilettenpapier 8 Rollen', 'Haushalt', 'household.paper'],
    ['Colgate Zahnpasta Total', 'Haushalt', 'household.care'],
    ['Ariel Waschmittel 2.5l', 'Haushalt', 'household.cleaning'],
    ['Coca-Cola Zero 1.5l', 'Getränke', 'drinks.soft'],
    ['Zweifel Chips Paprika', 'Lebensmittel', 'food.snacks'],
    ['Vollmilch 1l', 'Lebensmittel', 'food.dairy'],
    ['Bratpfanne 28cm', 'Einrichtung', 'home.kitchen'],
    ['Dafalgan 500mg', 'Gesundheit', 'health.medicine'],
    ['Feldschlösschen Bier 6x50cl', 'Getränke', 'drinks.alcohol'],
    ['Abfallsack 35l', 'Haushalt', 'household.supplies'],
  ])('%s → %s › %s', (name, cat, sub) => {
    expect(classifyItemName(name)).toEqual({ category: cat, subcategory: sub });
  });

  test('Unbekanntes → null', () => {
    expect(classifyItemName('XYZ 123')).toBeNull();
  });
});

describe('Art (Grundbedarf / Genuss / Anschaffung)', () => {
  test('aus der Unterkategorie', () => {
    expect(natureOf('drinks.soft')).toBe('treat');
    expect(natureOf('food.snacks')).toBe('treat');
    expect(natureOf('household.paper')).toBe('essential');
    expect(natureOf('home.furniture')).toBe('occasional');
  });
  test('ohne Unterkategorie aus der Hauptkategorie', () => {
    expect(natureOf(null, 'Restaurant & Take-away')).toBe('treat');
    expect(natureOf(null, 'Einrichtung')).toBe('occasional');
    expect(natureOf(null, 'Lebensmittel')).toBe('essential');
  });
  test('Unterkategorie gehört zur richtigen Hauptkategorie', () => {
    expect(categoryOfSubcategory('household.care')).toBe('Haushalt');
  });
});

describe('Lokalisierung', () => {
  test('Gerätesprache → unterstützte Sprache, Fallback Deutsch', () => {
    expect(resolveLocale('en-US')).toBe('en');
    expect(resolveLocale('fr-CH')).toBe('fr');
    expect(resolveLocale('es-ES')).toBe('de');
    expect(resolveLocale(undefined)).toBe('de');
  });
  test('sinngemässe Labels', () => {
    expect(categoryLabel('Restaurant & Take-away', 'en')).toBe('Eating out');
    expect(subcategoryLabel('household.paper', 'fr')).toBe('Papier ménager');
    expect(natureLabel('treat', 'it')).toBe('Sfizi e comodità');
    expect(categoryLabel('Einkommen', 'en')).toBe('Income');
    expect(categoryLabel('Meine Kategorie', 'en')).toBe('Meine Kategorie');
  });
});

describe('groupAdaptive', () => {
  interface Item { name: string; sub: string; price: number }
  const levels = [
    { key: (i: Item) => i.sub, label: (k: string) => subcategoryLabel(k) },
    { key: (i: Item) => productKey(i.name), label: (k: string) => k },
  ];
  const make = (n: number, sub: string, name: string): Item[] =>
    Array.from({ length: n }, () => ({ name, sub, price: 2 }));

  test('wenige Artikel → flache Liste', () => {
    const items = [...make(6, 'household.care', 'Zahnpasta'), ...make(4, 'household.paper', 'Toilettenpapier')];
    const g = groupAdaptive(items, levels, (i) => i.price);
    expect(g.children).toBeUndefined();
    expect(g.items).toHaveLength(10);
  });

  test('viele Artikel → nach Unterkategorie, kleine Gruppen in "Weitere"', () => {
    const items = [
      ...make(40, 'household.care', 'Zahnpasta'), ...make(30, 'household.cleaning', 'Waschmittel'),
      ...make(28, 'household.paper', 'Toilettenpapier'), ...make(2, 'household.supplies', 'Batterie'),
    ];
    const g = groupAdaptive(items, levels, (i) => i.price);
    const keys = g.children!.map((c) => c.key);
    expect(keys).toEqual(['household.care', 'household.cleaning', 'household.paper', OTHER_GROUP_KEY]);
    expect(g.children![0].label).toBe('Körperpflege & Hygiene');
    expect(g.children![3].count).toBe(2);
  });

  test('sehr viele Artikel einer Unterkategorie → Produktgruppen', () => {
    const items = [...make(20, 'household.care', 'Colgate Zahnpasta'), ...make(15, 'household.care', 'Duschgel Nivea')];
    const g = groupAdaptive(items, levels, (i) => i.price);
    // nur eine Unterkategorie → Ebene übersprungen, direkt Produkte
    const names = g.children!.map((c: GroupNode<Item>) => c.key);
    expect(names).toEqual(['colgate', 'duschgel']);
  });
});

describe('productKey', () => {
  test.each([
    ['M-Budget Toilettenpapier 8 Rollen', 'toilettenpapier'],
    ['Naturaplan Bio Vollmilch 1l', 'vollmilch'],
    ['Coop Zahnpasta 2x75ml', 'zahnpasta'],
  ])('%s → %s', (name, key) => expect(productKey(name)).toBe(key));
});

describe('classifyItemName — Verwechslungen', () => {
  test.each([
    ['Spaghetti Barilla 500g', 'food.pantry'],
    ['Reis Basmati 1kg', 'food.pantry'],
    ['Olivenöl extra vergine', 'food.pantry'],
    ['Elmex Zahnpasta', 'household.care'],
    ['Preiselbeeren', 'food.produce'],
  ])('%s → %s', (name, sub) => expect(classifyItemName(name)?.subcategory).toBe(sub));
});
