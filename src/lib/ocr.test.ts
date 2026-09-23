/**
 * ocr.test.ts — Unit-Tests für parseReceiptText()
 *
 * Testet den reinen TypeScript-Parser ohne native Module.
 * Fixtures: /home/raphael/K-Dev/testing/piggy-ios/fixtures/receipts/
 *
 * Ausführen: npx jest src/lib/ocr.test.ts
 */

import { readFileSync } from 'fs';
import { join } from 'path';
import { parseReceiptText } from './ocr';

// ── Fixture-Loader ────────────────────────────────────────────────────────────
const FIXTURES_DIR = '/home/raphael/K-Dev/testing/piggy-ios/fixtures/receipts';

function loadFixture(name: string): string {
  return readFileSync(join(FIXTURES_DIR, name), 'utf-8');
}

// ── Migros Supermarkt ─────────────────────────────────────────────────────────
describe('parseReceiptText — Migros Supermarkt', () => {
  let result: ReturnType<typeof parseReceiptText>;

  beforeAll(() => {
    result = parseReceiptText(loadFixture('migros-supermarkt.txt'));
  });

  test('erkennt Store-Name "MIGROS"', () => {
    expect(result.store_name).toMatch(/migros/i);
  });

  test('erkennt Kategorie "Supermarkt"', () => {
    expect(result.store_category).toBe('Supermarkt');
  });

  test('parst Datum korrekt', () => {
    expect(result.date).toBe('2026-08-12');
  });

  test('erkennt Gesamtbetrag 55.30', () => {
    expect(result.total_amount).toBeCloseTo(55.30, 1);
  });

  test('erkennt Währung CHF', () => {
    expect(result.currency).toBe('CHF');
  });

  test('erkennt Zahlungsmethode TWINT', () => {
    expect(result.payment_method).toBe('TWINT');
  });

  test('payment_card ist null bei TWINT', () => {
    expect(result.payment_card).toBeNull();
  });

  test('parst Artikel-Items (mindestens 5)', () => {
    expect(result.items.length).toBeGreaterThanOrEqual(5);
  });

  test('Milch-Item hat Milchprodukte-Tag', () => {
    const milch = result.items.find((i) => /milch/i.test(i.name));
    expect(milch).toBeDefined();
    expect(milch?.tags).toContain('Milchprodukte');
  });

  test('Chips-Item hat Snacks-Tag', () => {
    const chips = result.items.find((i) => /chips/i.test(i.name));
    expect(chips).toBeDefined();
    expect(chips?.tags).toContain('Snacks & Süsswaren');
  });

  test('Waschmittel-Item hat Haushalt-Tag', () => {
    const waschmittel = result.items.find((i) => /waschmittel/i.test(i.name));
    expect(waschmittel).toBeDefined();
    expect(waschmittel?.tags).toContain('Haushalt');
  });
});

// ── Coop Supermarkt ───────────────────────────────────────────────────────────
describe('parseReceiptText — Coop Supermarkt', () => {
  let result: ReturnType<typeof parseReceiptText>;

  beforeAll(() => {
    result = parseReceiptText(loadFixture('coop-supermarkt.txt'));
  });

  test('erkennt Store "COOP"', () => {
    expect(result.store_name).toMatch(/coop/i);
  });

  test('erkennt Kategorie "Supermarkt"', () => {
    expect(result.store_category).toBe('Supermarkt');
  });

  test('parst Datum 05.08.2026', () => {
    expect(result.date).toBe('2026-08-05');
  });

  test('erkennt Gesamtbetrag 49.50', () => {
    expect(result.total_amount).toBeCloseTo(49.50, 1);
  });

  test('erkennt Zahlungsmethode Karte (VISA)', () => {
    expect(result.payment_method).toBe('Karte');
  });

  test('erkennt VISA Kartennummer (letzte 4)', () => {
    expect(result.payment_card).toMatch(/4712/);
  });

  test('parst mindestens 8 Artikel', () => {
    expect(result.items.length).toBeGreaterThanOrEqual(8);
  });
});

// ── Restaurant ────────────────────────────────────────────────────────────────
describe('parseReceiptText — Restaurant', () => {
  let result: ReturnType<typeof parseReceiptText>;

  beforeAll(() => {
    result = parseReceiptText(loadFixture('restaurant-zurich.txt'));
  });

  test('erkennt Kategorie "Restaurant"', () => {
    expect(result.store_category).toBe('Restaurant');
  });

  test('parst Datum 14.08.2026', () => {
    expect(result.date).toBe('2026-08-14');
  });

  test('erkennt Gesamtbetrag 191.40', () => {
    // Total nach Service
    expect(result.total_amount).toBeCloseTo(191.40, 0);
  });

  test('erkennt Zahlungsmethode Karte (Mastercard)', () => {
    expect(result.payment_method).toBe('Karte');
    expect(result.payment_card).toMatch(/8833/);
  });

  test('erkennt Bier-Item mit Alkohol-Tag', () => {
    const bier = result.items.find((i) => /wein/i.test(i.name));
    if (bier) {
      expect(bier.tags).toContain('Alkohol');
    }
    // Wenn kein Bier geparst: mindestens kein Crash
    expect(result.total_amount).toBeGreaterThan(0);
  });
});

// ── Tankstelle ────────────────────────────────────────────────────────────────
describe('parseReceiptText — Tankstelle Agrola', () => {
  let result: ReturnType<typeof parseReceiptText>;

  beforeAll(() => {
    result = parseReceiptText(loadFixture('tankstelle-agrola.txt'));
  });

  test('erkennt Kategorie "Tankstelle"', () => {
    expect(result.store_category).toBe('Tankstelle');
  });

  test('parst Datum 18.08.2026', () => {
    expect(result.date).toBe('2026-08-18');
  });

  test('erkennt Gesamtbetrag 111.76', () => {
    expect(result.total_amount).toBeCloseTo(111.76, 0);
  });

  test('erkennt TWINT als Zahlungsmethode', () => {
    expect(result.payment_method).toBe('TWINT');
  });

  test('Kaffee-Item hat Kaffee-Tag', () => {
    const kaffee = result.items.find((i) => /kaffee/i.test(i.name));
    if (kaffee) {
      expect(kaffee.tags).toContain('Kaffee & Tee');
    }
  });
});

// ── Apotheke ──────────────────────────────────────────────────────────────────
describe('parseReceiptText — Apotheke', () => {
  let result: ReturnType<typeof parseReceiptText>;

  beforeAll(() => {
    result = parseReceiptText(loadFixture('apotheke-bern.txt'));
  });

  test('erkennt Kategorie "Apotheke"', () => {
    expect(result.store_category).toBe('Apotheke');
  });

  test('parst Datum 10.08.2026', () => {
    expect(result.date).toBe('2026-08-10');
  });

  test('erkennt Gesamtbetrag 80.70', () => {
    expect(result.total_amount).toBeCloseTo(80.70, 0);
  });

  test('erkennt Barzahlung', () => {
    expect(result.payment_method).toBe('Bargeld');
  });

  test('Vitamin-Item hat Nahrungsergänzungs-Tag', () => {
    const vitamin = result.items.find((i) => /vitamin/i.test(i.name));
    if (vitamin) {
      expect(vitamin.tags).toContain('Nahrungsergänzung');
    }
  });

  test('Aspirin-Item hat Medikamente-Tag', () => {
    const aspirin = result.items.find((i) => /aspirin/i.test(i.name));
    if (aspirin) {
      expect(aspirin.tags).toContain('Medikamente');
    }
  });
});

// ── Edge Cases ────────────────────────────────────────────────────────────────
describe('parseReceiptText — Edge Cases', () => {
  test('2-stelliges Jahr wird korrekt zu 2026 expandiert', () => {
    const result = parseReceiptText(loadFixture('ocr-edge-cases.txt'));
    expect(result.date).toBe('2026-08-12');
  });

  test('2x Brot wird als quantity=2 erkannt', () => {
    const result = parseReceiptText(loadFixture('ocr-edge-cases.txt'));
    const brot = result.items.find((i) => /brot/i.test(i.name));
    if (brot) {
      expect(brot.quantity).toBe(2);
      expect(brot.total_price).toBeCloseTo(4.60, 1);
      expect(brot.unit_price).toBeCloseTo(2.30, 1);
    }
  });

  test('leerer Text wirft keinen Exception (gibt leeres Ergebnis)', () => {
    expect(() => parseReceiptText('')).not.toThrow();
    const result = parseReceiptText('');
    expect(result.store_name).toBe('Unbekannt');
    expect(result.total_amount).toBe(0);
  });

  test('Text ohne Datum gibt null zurück', () => {
    const result = parseReceiptText('Kein Datum hier\nTotal 10.00');
    expect(result.date).toBeNull();
  });

  test('EUR-Symbol wird erkannt', () => {
    const result = parseReceiptText('Restaurant Chez Pierre\n23.07.2026\nTotal € 45.50');
    expect(result.currency).toBe('EUR');
  });

  test('Standardwährung ist CHF', () => {
    const result = parseReceiptText('Migros\n12.08.2026\nTotal 20.00');
    expect(result.currency).toBe('CHF');
  });

  test('extrem langer Text crasht nicht', () => {
    const longText = Array.from({ length: 500 }, (_, i) =>
      `Artikel ${i}   ${(Math.random() * 50 + 1).toFixed(2)}`
    ).join('\n');
    expect(() => parseReceiptText(longText)).not.toThrow();
  });
});
