// Artikel → Unterkategorie per LLM (Hintergrund-Klassifizierung im statement-worker).
// Kompaktes Zeilenformat: Eingabe "nr|Artikel|Laden|Hauptkategorie", Ausgabe "nr|unterkategorie".
import { ALL_SUBCATEGORY_KEYS, categoryOfSubcategory, classifyItemName, taxonomyPromptList } from '../finance-core/taxonomy.ts';

const ANTHROPIC_URL = 'https://api.anthropic.com/v1/messages';
const MAX_OUTPUT_TOKENS = 4000;
/** Hauptkategorie darf das Modell nur ändern, wenn sie bisher unspezifisch ist. */
const REASSIGNABLE = new Set(['Diverses', 'Nicht kategorisiert']);
/** Verwandte Kategorien, zwischen denen alte Zuordnungen oft falsch sind (Küche lag früher unter Haushalt). */
const RELATED_MOVES: Record<string, string[]> = {
  'Haushalt': ['Einrichtung', 'Gesundheit'],
  'Einrichtung': ['Haushalt'],
  'Lebensmittel': ['Getränke', 'Haushalt', 'Gesundheit'],
  'Getränke': ['Lebensmittel'],
};

function mayMove(from: string, to: string): boolean {
  return from === to || REASSIGNABLE.has(from) || (RELATED_MOVES[from]?.includes(to) ?? false);
}

export interface ItemToClassify {
  id: string;
  name: string;
  store: string;
  category: string;
}

export interface ItemClassification {
  id: string;
  category: string;
  subcategory: string | null;
}

function prompt(items: ItemToClassify[]): string {
  return `Ordne jeden Einkaufsartikel einer Unterkategorie zu. Zahlen und Abkürzungen auf Schweizer Quittungen sind üblich (z. B. "M-Budget", "Prix Garantie", "Naturaplan" sind Marken).

Erlaubte Unterkategorien (Schlüssel = Hauptkategorie › Name):
${taxonomyPromptList('item')}

Regeln:
- Bleib in der angegebenen Hauptkategorie, ausser sie ist "Diverses". Ausnahmen: Geschirr, Kochzubehör, Lampen, Möbel, Deko aus "Haushalt" → home.*; Medikamente aus "Haushalt" → health.*; Getränke unter "Lebensmittel" → drinks.* (und umgekehrt); Pflege-/Putzartikel unter "Lebensmittel" → household.*.
- Restaurant-Artikel: Getränke/Kaffee im Café → dining.cafe, Imbiss/Fast Food → dining.takeaway, sonst dining.restaurant.
- Süssgetränke und Snacks sind Genuss, nicht Grundbedarf: drinks.soft bzw. food.snacks.
- Pfand/Depot → other.deposit; Rabatte: Unterkategorie des rabattierten Artikels (z. B. "Rabatt Lyoner" → food.meat_fish), nur ohne erkennbaren Artikel → other.misc.
- Antworte NUR mit Zeilen "Nr|Schlüssel", eine pro Artikel, keine Erklärung.

Artikel (Nr|Artikel|Laden|Hauptkategorie):
${items.map((it, i) => `${i + 1}|${it.name.replace(/\|/g, '/')}|${it.store.replace(/\|/g, '/')}|${it.category}`).join('\n')}`;
}

/** Ergebnis validieren: nur bekannte Schlüssel, Hauptkategorie bleibt (ausser "Diverses" und verwandte Wechsel). */
export function resolveClassification(item: ItemToClassify, key: string | undefined): ItemClassification {
  const valid = key && ALL_SUBCATEGORY_KEYS.includes(key) ? key : undefined;
  const keyCategory = categoryOfSubcategory(valid);
  if (valid && keyCategory && mayMove(item.category, keyCategory)) {
    return { id: item.id, category: keyCategory, subcategory: valid };
  }
  // Fallback: Regeln, aber nur innerhalb der bisherigen Hauptkategorie
  const rule = classifyItemName(item.name);
  if (rule && mayMove(item.category, rule.category)) {
    return { id: item.id, category: rule.category, subcategory: rule.subcategory };
  }
  return { id: item.id, category: item.category, subcategory: null };
}

export function parseClassificationLines(text: string, count: number): Map<number, string> {
  const result = new Map<number, string>();
  for (const raw of text.split('\n')) {
    const m = raw.trim().replace(/^[-*•|]\s*/, '').match(/^(\d+)\s*\|\s*([a-z_]+\.[a-z_]+)/);
    if (!m) continue;
    const nr = parseInt(m[1], 10);
    if (nr >= 1 && nr <= count) result.set(nr, m[2]);
  }
  return result;
}

export async function classifyItemsLlm(items: ItemToClassify[], model: string): Promise<{ results: ItemClassification[]; tokensIn: number; tokensOut: number }> {
  const apiKey = Deno.env.get('ANTHROPIC_API_KEY');
  if (!apiKey) throw new Error('ANTHROPIC_API_KEY fehlt');
  const res = await fetch(ANTHROPIC_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json', 'x-api-key': apiKey, 'anthropic-version': '2023-06-01' },
    body: JSON.stringify({ model, max_tokens: MAX_OUTPUT_TOKENS, messages: [{ role: 'user', content: prompt(items) }] }),
  });
  if (!res.ok) throw new Error(`Anthropic ${res.status}: ${(await res.text()).slice(0, 300)}`);
  const data = await res.json();
  const keys = parseClassificationLines(data?.content?.[0]?.text ?? '', items.length);
  return {
    results: items.map((it, i) => resolveClassification(it, keys.get(i + 1))),
    tokensIn: data?.usage?.input_tokens ?? 0,
    tokensOut: data?.usage?.output_tokens ?? 0,
  };
}
