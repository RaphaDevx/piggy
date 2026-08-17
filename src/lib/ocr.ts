/**
 * ocr.ts — Lokale OCR-Verarbeitung mit ML Kit (kein API-Call nötig)
 *
 * Ersetzt die kostenpflichtige Claude Vision API.
 * Exportiert processReceiptImage mit identischem Interface zu claude.ts.
 */

import TextRecognition from '@react-native-ml-kit/text-recognition';
import * as ImageManipulator from 'expo-image-manipulator';
import type { ParsedReceipt, ParsedReceiptItem } from '../types/receipt';
import { generateMarkdown } from './markdown';

export interface ProcessResult {
  receipt: ParsedReceipt;
  markdown: string;
}

// ── Hilfsfunktionen ──────────────────────────────────────────────────────────

function normalizeAmount(raw: string): number {
  // Entfernt Währungssymbole, wandelt Komma zu Punkt
  const cleaned = raw.replace(/[^0-9.,]/g, '').replace(',', '.');
  const val = parseFloat(cleaned);
  return isNaN(val) ? 0 : val;
}

function extractDate(lines: string[]): string | null {
  // DD.MM.YYYY oder DD.MM.YY
  const dateRegex = /\b(\d{1,2})\.(\d{1,2})\.(\d{2}|\d{4})\b/;
  for (const line of lines) {
    const match = line.match(dateRegex);
    if (match) {
      const day   = match[1].padStart(2, '0');
      const month = match[2].padStart(2, '0');
      let   year  = match[3];
      if (year.length === 2) year = '20' + year;
      // Nur plausible Monate/Tage
      if (parseInt(month) > 12 || parseInt(day) > 31) continue;
      return `${year}-${month}-${day}`;
    }
  }
  return null;
}

function extractCurrency(text: string): string {
  if (/\bEUR\b|€/i.test(text)) return 'EUR';
  if (/\bUSD\b|\$/i.test(text)) return 'USD';
  return 'CHF'; // Schweizer Default
}

function extractPaymentMethod(lines: string[]): { method: string; card: string | null } {
  const text = lines.join(' ').toLowerCase();

  if (/twint/i.test(text)) return { method: 'TWINT', card: null };

  const visaMatch = text.match(/visa[\s\S]{0,20}(\d{4})/i);
  if (visaMatch) return { method: 'Karte', card: `Visa ···· ${visaMatch[1]}` };

  const mcMatch = text.match(/mastercard[\s\S]{0,20}(\d{4})/i);
  if (mcMatch) return { method: 'Karte', card: `Mastercard ···· ${mcMatch[1]}` };

  // Allgemeine Karte mit letzten 4 Ziffern
  const cardMatch = text.match(/karte[\s\S]{0,30}(\d{4})/i)
    ?? text.match(/card[\s\S]{0,30}(\d{4})/i)
    ?? text.match(/debit[\s\S]{0,30}(\d{4})/i);
  if (cardMatch) return { method: 'Karte', card: `···· ${cardMatch[1]}` };

  if (/bar|cash|bargeld/i.test(text)) return { method: 'Bargeld', card: null };
  if (/rechnung|invoice/i.test(text)) return { method: 'Rechnung', card: null };

  // Karte ohne Nummer erkannt
  if (/karte|card|ec|debit|kredit/i.test(text)) return { method: 'Karte', card: null };

  return { method: 'Unbekannt', card: null };
}

function extractTotal(lines: string[]): number {
  // Schlüsselwörter für Gesamtbetrag (absteigend nach Spezifizität)
  const totalKeywords = [
    /total[\s:]*?([\d.,]+)/i,
    /betrag[\s:]*?([\d.,]+)/i,
    /summe[\s:]*?([\d.,]+)/i,
    /gesamt[\s:]*?([\d.,]+)/i,
    /zu\s+zahlen[\s:]*?([\d.,]+)/i,
    /rp\.?\s*([\d.,]+)/i,
    /chf[\s:]*?([\d.,]+)/i,
    /eur[\s:]*?([\d.,]+)/i,
  ];

  // Suche in den letzten 30% der Zeilen (Gesamtbetrag steht meist unten)
  const bottomLines = lines.slice(Math.floor(lines.length * 0.5));
  const allLines    = [...bottomLines, ...lines]; // zuerst unten, dann alles

  for (const regex of totalKeywords) {
    for (const line of allLines) {
      const match = line.match(regex);
      if (match) {
        const amount = normalizeAmount(match[1]);
        if (amount > 0) return amount;
      }
    }
  }

  // Fallback: größte Zahl im ganzen Text
  const amounts: number[] = [];
  for (const line of lines) {
    const matches = line.matchAll(/\b(\d{1,5}[.,]\d{2})\b/g);
    for (const m of matches) {
      const v = normalizeAmount(m[1]);
      if (v > 0) amounts.push(v);
    }
  }
  return amounts.length > 0 ? Math.max(...amounts) : 0;
}

function extractStoreName(lines: string[]): string {
  // Die ersten 3–5 nicht-leeren Zeilen enthalten meist den Ladennamen
  const candidates = lines
    .slice(0, 8)
    .map((l) => l.trim())
    .filter((l) => l.length > 2 && l.length < 60)
    // Keine Zeilen mit nur Zahlen oder typischen Header-Inhalten
    .filter((l) => !/^\d+$/.test(l) && !/^(Quittung|Receipt|Kassenbon|Datum|Date)/i.test(l));

  if (candidates.length === 0) return 'Unbekannt';

  // Bevorzuge Zeile in ALL CAPS (Firmenname)
  const capsLine = candidates.find((l) => l === l.toUpperCase() && l.length > 3);
  return capsLine ?? candidates[0];
}

function guessStoreCategory(storeName: string, text: string): string {
  const combined = (storeName + ' ' + text).toLowerCase();
  if (/migros|coop|aldi|lidl|spar|denner|volg|rewe|edeka|supermarkt/i.test(combined)) return 'Supermarkt';
  if (/drogerie|dm |rossmann|müller|parfümerie/i.test(combined)) return 'Drogerie';
  if (/apotheke|pharmacy|pharma/i.test(combined)) return 'Apotheke';
  if (/bäckerei|bakery|konditorei|confiserie/i.test(combined)) return 'Bäckerei';
  if (/restaurant|bistro|café|cafe|mcdonalds|burger|pizza|sushi/i.test(combined)) return 'Restaurant';
  if (/tankstelle|petrol|shell|bp |esso|agrola/i.test(combined)) return 'Tankstelle';
  if (/baumarkt|hornbach|ikea|obi /i.test(combined)) return 'Baumarkt';
  if (/h&m|zara|uniqlo|kleidung|fashion|manor/i.test(combined)) return 'Kleidung';
  if (/mediamarkt|galaxus|digitec|electronics/i.test(combined)) return 'Elektronik';
  return 'Diverses';
}

// Einfache Keyword-basierte Tag-Zuweisung für Artikel
const ITEM_TAG_RULES: Array<{ pattern: RegExp; tags: string[] }> = [
  { pattern: /milch|rahm|butter|käse|joghurt|quark|sahne/i,      tags: ['Milchprodukte', 'Lebensmittel'] },
  { pattern: /brot|brötchen|gipfeli|croissant|gebäck|toast/i,    tags: ['Backwaren', 'Lebensmittel'] },
  { pattern: /wasser|mineralwasser|saft|cola|fanta|sprite|limonade/i, tags: ['Getränke'] },
  { pattern: /bier|wein|champagner|prosecco|schnaps|whisky/i,    tags: ['Alkohol', 'Getränke'] },
  { pattern: /kaffee|espresso|tee|matcha/i,                      tags: ['Kaffee & Tee', 'Getränke'] },
  { pattern: /fleisch|hackfleisch|wurst|schinken|salami|lachs|fisch|poulet/i, tags: ['Fleisch & Fisch', 'Lebensmittel'] },
  { pattern: /apfel|banane|orange|tomate|salat|gurke|karotte|gemüse|obst/i,   tags: ['Gemüse & Obst', 'Lebensmittel'] },
  { pattern: /tiefkühl|gefroren|frozen/i,                        tags: ['Tiefkühlkost', 'Lebensmittel'] },
  { pattern: /konserve|dose|eingemacht/i,                        tags: ['Konserven', 'Lebensmittel'] },
  { pattern: /pasta|reis|mehl|zucker|salz|öl|nudel/i,            tags: ['Grundnahrungsmittel', 'Lebensmittel'] },
  { pattern: /chips|schokolade|gummibären|kekse|süss/i,          tags: ['Snacks & Süsswaren', 'Lebensmittel'] },
  { pattern: /shampoo|duschgel|seife|deo|parfum|creme|lotion/i,  tags: ['Körperpflege', 'Hygiene'] },
  { pattern: /zahnbürste|zahnpasta|mundwasser/i,                 tags: ['Mundpflege', 'Hygiene'] },
  { pattern: /waschmittel|spülmittel|putzmittel|reiniger/i,      tags: ['Reinigung', 'Haushalt'] },
  { pattern: /tablette|kapsel|tropfen|medikament|arznei/i,       tags: ['Medikamente', 'Gesundheit'] },
  { pattern: /vitamin|supplement|omega|protein/i,                tags: ['Nahrungsergänzung', 'Gesundheit'] },
];

function assignItemTags(name: string): string[] {
  const result: string[] = [];
  for (const rule of ITEM_TAG_RULES) {
    if (rule.pattern.test(name)) {
      result.push(...rule.tags);
    }
  }
  // Falls nichts matched: leeres Array (User kann manuell zuweisen)
  return [...new Set(result)];
}

// ── Zeilen-Parser für Artikel ────────────────────────────────────────────────

function parseItems(lines: string[]): ParsedReceiptItem[] {
  const items: ParsedReceiptItem[] = [];

  // Muster: "Artikelname   4.50" oder "Artikelname CHF 4.50" oder "3x Artikel  12.90"
  const itemRegex = /^(.{2,40?}?)\s+([\d]{1,4}[.,]\d{2})\s*$/;
  const qtyRegex  = /^(\d+)\s*[xX×]\s+(.+)/;

  // Zeilen die nach Total/Summe kommen, überspringen
  let pastTotal = false;
  for (const line of lines) {
    const trimmed = line.trim();
    if (!trimmed) continue;

    // Stopp nach Total-Block
    if (/^(total|summe|betrag|gesamt|zu zahlen)/i.test(trimmed)) {
      pastTotal = true;
    }
    if (pastTotal) continue;

    // Überspringe reine Header-Zeilen
    if (/^(datum|date|kasse|mwst|ust|quittung|receipt|kassenbon)/i.test(trimmed)) continue;

    const match = trimmed.match(itemRegex);
    if (!match) continue;

    let name  = match[1].trim();
    let price = normalizeAmount(match[2]);

    if (price <= 0 || price > 9999) continue;

    // Menge erkennen
    let quantity = 1;
    const qtyMatch = name.match(qtyRegex);
    if (qtyMatch) {
      quantity = parseInt(qtyMatch[1]) || 1;
      name     = qtyMatch[2].trim();
    }

    // Zu kurze oder zahlen-only Namen skippen
    if (name.length < 2 || /^\d+$/.test(name)) continue;

    items.push({
      name,
      quantity,
      unit:       'Stk',
      unit_price: quantity > 1 ? Math.round((price / quantity) * 100) / 100 : price,
      total_price: price,
      tags:       assignItemTags(name),
    });
  }

  return items;
}

// ── Haupt-Parser ─────────────────────────────────────────────────────────────

export function parseReceiptText(rawText: string): ParsedReceipt {
  const lines     = rawText.split('\n').map((l) => l.trim()).filter(Boolean);
  const fullText  = rawText;

  const storeName    = extractStoreName(lines);
  const date         = extractDate(lines);
  const currency     = extractCurrency(fullText);
  const total        = extractTotal(lines);
  const { method, card } = extractPaymentMethod(lines);
  const category     = guessStoreCategory(storeName, fullText);
  const items        = parseItems(lines);

  return {
    store_name:     storeName,
    store_category: category,
    date,
    total_amount:   total,
    currency,
    payment_method: method,
    payment_card:   card,
    items,
  };
}

// ── Crop-Hilfsfunktion ───────────────────────────────────────────────────────

export interface CropRegion {
  originX: number;
  originY: number;
  width:   number;
  height:  number;
}

async function cropImage(imageUri: string, crop: CropRegion | null): Promise<string> {
  if (!crop) return imageUri;
  try {
    const result = await ImageManipulator.manipulateAsync(
      imageUri,
      [{ crop }],
      { compress: 0.9, format: ImageManipulator.SaveFormat.JPEG }
    );
    return result.uri;
  } catch {
    return imageUri; // Fallback: Original
  }
}

// ── Öffentliches Interface (kompatibel mit claude.ts) ───────────────────────

export async function processReceiptImage(
  imageUri: string,
  cropRegion?: CropRegion | null
): Promise<ProcessResult> {
  // 1. Optional croppen
  const processUri = cropRegion
    ? await cropImage(imageUri, cropRegion)
    : imageUri;

  // 2. OCR via ML Kit
  const result = await TextRecognition.recognize(processUri);
  const rawText = result.text ?? '';

  if (!rawText.trim()) {
    throw new Error('Kein Text erkannt. Bitte Quittung erneut fotografieren.');
  }

  // 3. Text parsen
  const receipt  = parseReceiptText(rawText);
  const markdown = generateMarkdown(receipt);

  return { receipt, markdown };
}
