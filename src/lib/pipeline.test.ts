/**
 * pipeline.test.ts — Isolierter Test der scan-Pipeline
 *
 * Mockt alle nativen Module (OCR, FoundationModels, ImageManipulator etc.)
 * und testet den kompletten processReceiptImage-Durchlauf ohne Build.
 *
 * Ausführen: npx jest src/lib/pipeline.test.ts
 */

import { readFileSync } from 'fs';
import { join } from 'path';

// ── Mocks müssen vor dem Import von ocr.ts stehen ────────────────────────────

const FIXTURES = '/home/raphael/K-Dev/testing/piggy-ios/fixtures/receipts';
let mockOcrText = '';

jest.mock('@dariyd/react-native-text-recognition', () => ({
  recognizeText: jest.fn().mockImplementation(async () => ({
    success: true,
    fullText: mockOcrText,
  })),
}));

jest.mock('../native/FoundationModels', () => ({
  FoundationModels: {
    isAvailable: jest.fn().mockResolvedValue(false), // Stub → Regex-Fallback
    parseReceiptText: jest.fn().mockRejectedValue(new Error('stub')),
  },
}));

jest.mock('expo-image-manipulator', () => ({
  manipulateAsync: jest.fn().mockImplementation(async (uri) => ({ uri })),
  SaveFormat: { JPEG: 'jpeg' },
}));

jest.mock('expo-file-system/legacy', () => ({
  readAsStringAsync: jest.fn().mockResolvedValue(''),
}));

jest.mock('./supabase', () => ({
  supabase: { from: jest.fn(), storage: { from: jest.fn() } },
}));

jest.mock('./markdown', () => ({
  generateMarkdown: jest.fn().mockReturnValue('## Quittung\n'),
}));

// ── Nach Mocks importieren ───────────────────────────────────────────────────

import { processReceiptImage } from './ocr';
import { recognizeText } from '@dariyd/react-native-text-recognition';

// ── Hilfsfunktion ────────────────────────────────────────────────────────────

function setOcrText(filename: string) {
  mockOcrText = readFileSync(join(FIXTURES, filename), 'utf-8');
}

// ── Tests ────────────────────────────────────────────────────────────────────

describe('processReceiptImage — Pipeline-Integration', () => {

  beforeEach(() => jest.clearAllMocks());

  test('gibt immer type="done" zurück (kein "queued" mehr)', async () => {
    setOcrText('migros-supermarkt.txt');
    const result = await processReceiptImage('file:///tmp/test.jpg');
    expect(result.type).toBe('done');
  });

  test('receipt und markdown sind gesetzt', async () => {
    setOcrText('migros-supermarkt.txt');
    const result = await processReceiptImage('file:///tmp/test.jpg');
    if (result.type !== 'done') throw new Error('Expected done');
    expect(result.receipt).toBeDefined();
    expect(result.markdown).toBeDefined();
  });

  test('recognizeText wird mit "line"-Level aufgerufen', async () => {
    setOcrText('migros-supermarkt.txt');
    await processReceiptImage('file:///tmp/test.jpg');
    expect(recognizeText).toHaveBeenCalledWith(
      'file:///tmp/test.jpg',
      { recognitionLevel: 'line' }
    );
  });

  test('bei leerem OCR-Ergebnis wird ein Fehler geworfen', async () => {
    mockOcrText = '   ';
    await expect(processReceiptImage('file:///tmp/blank.jpg')).rejects.toThrow(
      'Kein Text erkannt'
    );
  });

  test('Migros: store_name enthält "migros"', async () => {
    setOcrText('migros-supermarkt.txt');
    const result = await processReceiptImage('file:///tmp/migros.jpg');
    if (result.type !== 'done') throw new Error('Expected done');
    expect(result.receipt.store_name).toMatch(/migros/i);
  });

  test('Migros: total_amount ist 55.30', async () => {
    setOcrText('migros-supermarkt.txt');
    const result = await processReceiptImage('file:///tmp/migros.jpg');
    if (result.type !== 'done') throw new Error('Expected done');
    expect(result.receipt.total_amount).toBeCloseTo(55.30, 1);
  });

  test('Coop: Visa-Karte erkannt', async () => {
    setOcrText('coop-supermarkt.txt');
    const result = await processReceiptImage('file:///tmp/coop.jpg');
    if (result.type !== 'done') throw new Error('Expected done');
    expect(result.receipt.payment_method).toBe('Karte');
    expect(result.receipt.payment_card).toMatch(/4712/);
  });

  test('Restaurant: total_amount ist plausibel (>100 CHF)', async () => {
    setOcrText('restaurant-zurich.txt');
    const result = await processReceiptImage('file:///tmp/rest.jpg');
    if (result.type !== 'done') throw new Error('Expected done');
    expect(result.receipt.total_amount).toBeGreaterThan(100);
    expect(result.receipt.store_category).toBe('Restaurant');
  });

  test('cropRegion wird an recognizeText als gecropte URI weitergegeben', async () => {
    setOcrText('migros-supermarkt.txt');
    const crop = { originX: 0, originY: 0, width: 400, height: 800 };
    await processReceiptImage('file:///tmp/test.jpg', crop);
    // ImageManipulator gibt 'file:///tmp/test.jpg' zurück (Mock), recognizeText bekommt es
    expect(recognizeText).toHaveBeenCalledWith('file:///tmp/test.jpg', expect.anything());
  });
});
