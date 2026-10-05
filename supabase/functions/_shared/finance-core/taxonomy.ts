/**
 * taxonomy.ts — Kategorie-Hierarchie für Artikel und Buchungen.
 *
 * Hauptkategorie (stabile ID = bisheriger deutscher Name, steht so in der DB)
 *   └ Unterkategorie (Schlüssel wie "household.cleaning", in receipt_items.subcategory)
 *        └ Art: Grundbedarf · Genuss & Komfort · Anschaffung
 *
 * Labels sind pro Sprache sinngemäss formuliert (nicht wörtlich übersetzt).
 * Reihenfolge der Regeln zählt: spezifisch vor allgemein.
 */

export type Locale = 'de' | 'en' | 'fr' | 'it';
export type Nature = 'essential' | 'treat' | 'occasional';
export type Scope = 'item' | 'bank' | 'both';
type L10n = Record<Locale, string>;

export interface SubcategoryDef {
  key: string;
  label: L10n;
  nature: Nature;
  /** Erkennung über Artikelnamen (Fallback ohne KI, Rückbefüllung) */
  pattern?: RegExp;
}

export interface CategoryDef {
  id: string;
  label: L10n;
  color: string;
  scope: Scope;
  subcategories: SubcategoryDef[];
}

const sub = (key: string, nature: Nature, label: L10n, pattern?: RegExp): SubcategoryDef => ({ key, nature, label, pattern });

export const TAXONOMY: CategoryDef[] = [
  {
    id: 'Lebensmittel', color: '#34C759', scope: 'both',
    label: { de: 'Lebensmittel', en: 'Groceries', fr: 'Alimentation', it: 'Alimentari' },
    subcategories: [
      sub('food.snacks', 'treat', { de: 'Snacks & Süsses', en: 'Snacks & sweets', fr: 'Snacks & douceurs', it: 'Snack e dolci' },
        /chips|schoggi|schokolade|praliné|kekse|biscuit|guetzli|gummi|bonbon|riegel|popcorn|nüsse gesalzen|apéro|glace|eiscreme|torte|kuchen|dessert|süssigkeit|haribo|toblerone|lindt|ragusa|kägi/i),
      sub('food.ready', 'treat', { de: 'Fertiggerichte', en: 'Ready meals', fr: 'Plats préparés', it: 'Piatti pronti' },
        /fertig|pizza|sandwich|wrap|sushi|salat bowl|lasagne|nuggets|instant|cup noodle|menü|convenience/i),
      sub('food.frozen', 'essential', { de: 'Tiefkühl', en: 'Frozen', fr: 'Surgelés', it: 'Surgelati' },
        /tiefkühl|tk |gefroren|frozen|surgel/i),
      sub('food.dairy', 'essential', { de: 'Milch, Käse & Eier', en: 'Dairy & eggs', fr: 'Produits laitiers & œufs', it: 'Latticini e uova' },
        /milch|rahm|butter|käse|joghurt|jogurt|quark|sahne|mozzarella|gruyère|emmentaler|eier|\bei\b|frischkäse|skyr/i),
      sub('food.meat_fish', 'essential', { de: 'Fleisch & Fisch', en: 'Meat & fish', fr: 'Viande & poisson', it: 'Carne e pesce' },
        /fleisch|hack|wurst|schinken|salami|speck|poulet|huhn|rind|schwein|kalb|lachs|thon|thunfisch|fisch|crevette|cervelat|bratwurst|aufschnitt|tofu/i),
      sub('food.bakery', 'essential', { de: 'Brot & Gebäck', en: 'Bread & bakery', fr: 'Pain & viennoiseries', it: 'Pane e prodotti da forno' },
        /brot|brötli|bürli|zopf|gipfeli|croissant|toast|baguette|semmel|weggli|laugen/i),
      sub('food.produce', 'essential', { de: 'Obst & Gemüse', en: 'Fruit & veg', fr: 'Fruits & légumes', it: 'Frutta e verdura' },
        /apfel|äpfel|banane|orange|zitrone|beere|traube|birne|kiwi|tomate|salat|gurke|karotte|rüebli|zwiebel|kartoffel|härdöpfel|peperoni|zucchetti|brokkoli|spinat|gemüse|obst|avocado|pilz|champignon|kräuter|ingwer|knoblauch/i),
      sub('food.pantry', 'essential', { de: 'Vorrat', en: 'Pantry staples', fr: 'Épicerie', it: 'Dispensa' },
        /(^|[\s-])(pasta|reis|mehl|zucker|salz|öl|olivenöl|rapsöl|essig|dose)($|[\s-])|teigwaren|spaghetti|penne|fusilli|konserve|bohnen|linsen|müesli|cornflakes|haferflocken|honig|konfitüre|bouillon|gewürz|pfeffer|senf|ketchup|mayonnaise|nudel/i),
    ],
  },
  {
    id: 'Getränke', color: '#0A84FF', scope: 'both',
    label: { de: 'Getränke', en: 'Drinks', fr: 'Boissons', it: 'Bevande' },
    subcategories: [
      sub('drinks.alcohol', 'treat', { de: 'Wein, Bier & Spirituosen', en: 'Wine, beer & spirits', fr: 'Vins, bières & spiritueux', it: 'Vino, birra e superalcolici' },
        /bier|wein|prosecco|champagner|cava|schnaps|whisky|vodka|gin\b|rum\b|likör|cider|aperol|spritz/i),
      sub('drinks.soft', 'treat', { de: 'Süssgetränke', en: 'Soft drinks', fr: 'Boissons sucrées', it: 'Bibite' },
        /cola|fanta|sprite|rivella|eistee|ice tea|limonade|energy|red bull|sirup|süssgetränk|schorle|saft|smoothie/i),
      sub('drinks.hot', 'essential', { de: 'Kaffee & Tee', en: 'Coffee & tea', fr: 'Café & thé', it: 'Caffè e tè' },
        /kaffee|espresso|kapseln|nespresso|tee\b|tea\b|matcha|kakao|ovomaltine/i),
      sub('drinks.water', 'essential', { de: 'Wasser', en: 'Water', fr: 'Eau', it: 'Acqua' },
        /wasser|mineral|henniez|valser|aproz|evian/i),
    ],
  },
  {
    id: 'Haushalt', color: '#FF9F0A', scope: 'both',
    label: { de: 'Haushalt', en: 'Household', fr: 'Ménage', it: 'Casa e igiene' },
    subcategories: [
      sub('household.paper', 'essential', { de: 'Papierwaren', en: 'Paper goods', fr: 'Papier ménager', it: 'Carta per la casa' },
        /toilettenpapier|wc-papier|wc papier|klopapier|haushaltpapier|küchenrolle|taschentücher|tempo|servietten|feuchttücher/i),
      sub('household.cleaning', 'essential', { de: 'Putzen & Waschen', en: 'Cleaning & laundry', fr: 'Ménage & lessive', it: 'Pulizia e bucato' },
        /waschmittel|weichspüler|spülmittel|geschirr|tabs|putzmittel|reiniger|entkalker|schwamm|lappen|mikrofaser|javel|wc-ente|fleckentferner|calgon/i),
      sub('household.care', 'essential', { de: 'Körperpflege & Hygiene', en: 'Personal care', fr: 'Hygiène & soins', it: 'Igiene e cura personale' },
        /zahnpasta|zahnbürste|zahnseide|mundwasser|shampoo|spülung|duschgel|seife|deo|deodorant|creme|lotion|bodylotion|rasier|binden|tampon|slipeinlage|wattestäbchen|watte|lippen|sonnencreme|nivea|dove|colgate|elmex|kosmetik|parfum/i),
      sub('household.supplies', 'essential', { de: 'Haushaltsbedarf', en: 'Household supplies', fr: 'Fournitures ménagères', it: 'Articoli per la casa' },
        /abfallsack|kehrichtsack|müllsack|gebührensack|alufolie|frischhaltefolie|backpapier|batterie|glühbirne|leuchtmittel|kerze|zündhölzer|gefrierbeutel|klebeband/i),
    ],
  },
  {
    id: 'Gesundheit', color: '#64D2FF', scope: 'both',
    label: { de: 'Gesundheit', en: 'Health', fr: 'Santé', it: 'Salute' },
    subcategories: [
      sub('health.medicine', 'essential', { de: 'Medikamente', en: 'Medicine', fr: 'Médicaments', it: 'Farmaci' },
        /tablette|kapsel|tropfen|medikament|arznei|dafalgan|ibuprofen|aspirin|pflaster|verband|sirup gegen|nasenspray|halsweh/i),
      sub('health.supplements', 'treat', { de: 'Nahrungsergänzung', en: 'Supplements', fr: 'Compléments alimentaires', it: 'Integratori' },
        /vitamin|magnesium|omega|protein|supplement|zink|eisen|kollagen/i),
      sub('health.care', 'essential', { de: 'Arzt & Therapie', en: 'Doctor & therapy', fr: 'Médecin & thérapie', it: 'Medico e terapie' },
        /arzt|zahnarzt|praxis|spital|physio|therapie|optiker|brille|kontaktlinsen/i),
    ],
  },
  {
    id: 'Einrichtung', color: '#D4A373', scope: 'both',
    label: { de: 'Einrichtung', en: 'Home & furnishing', fr: 'Maison & ameublement', it: 'Casa e arredamento' },
    subcategories: [
      sub('home.kitchen', 'occasional', { de: 'Küche & Kochen', en: 'Kitchenware', fr: 'Cuisine & ustensiles', it: 'Cucina e utensili' },
        /pfanne|bratpfanne|topf|kochtopf|messer|schneidebrett|schüssel|teller|tasse|glas\b|gläser|besteck|sieb|kelle|backform|mixer|wasserkocher/i),
      sub('home.furniture', 'occasional', { de: 'Möbel', en: 'Furniture', fr: 'Meubles', it: 'Mobili' },
        /stuhl|tisch|regal|schrank|kommode|sofa|bett\b|matratze|lampe|leuchte|sessel|hocker/i),
      sub('home.decor', 'occasional', { de: 'Deko & Textilien', en: 'Decor & textiles', fr: 'Déco & textiles', it: 'Decorazione e tessili' },
        /deko|vase|bilderrahmen|kissen|decke|vorhang|teppich|bettwäsche|handtuch|pflanze|blumen/i),
      sub('home.appliances', 'occasional', { de: 'Geräte', en: 'Appliances', fr: 'Appareils', it: 'Elettrodomestici' },
        /staubsauger|bügeleisen|föhn|haartrockner|toaster|kaffeemaschine|mikrowelle|ventilator|heizlüfter/i),
    ],
  },
  {
    id: 'Restaurant & Take-away', color: '#FF6B6B', scope: 'both',
    label: { de: 'Auswärts essen', en: 'Eating out', fr: 'Restaurants & à emporter', it: 'Pasti fuori casa' },
    subcategories: [
      sub('dining.cafe', 'treat', { de: 'Café & Bar', en: 'Café & bar', fr: 'Café & bar', it: 'Caffè e bar' },
        /cappuccino|latte|café crème|caffè|espresso bar|drink|cocktail|bar\b/i),
      sub('dining.takeaway', 'treat', { de: 'Take-away & Imbiss', en: 'Takeaway', fr: 'À emporter', it: "Da asporto" },
        /take ?away|kebab|döner|burger|mcdonald|imbiss|delivery|lieferung/i),
      sub('dining.canteen', 'essential', { de: 'Mensa & Kantine', en: 'Canteen', fr: 'Cantine', it: 'Mensa' },
        /mensa|kantine|personalrestaurant/i),
      sub('dining.restaurant', 'treat', { de: 'Restaurant', en: 'Restaurant', fr: 'Restaurant', it: 'Ristorante' }),
    ],
  },
  {
    id: 'Freizeit & Shopping', color: '#BF5AF2', scope: 'both',
    label: { de: 'Freizeit & Shopping', en: 'Leisure & shopping', fr: 'Loisirs & shopping', it: 'Tempo libero e shopping' },
    subcategories: [
      sub('leisure.clothing', 'occasional', { de: 'Kleidung & Schuhe', en: 'Clothing & shoes', fr: 'Vêtements & chaussures', it: 'Abbigliamento e scarpe' },
        /shirt|hose|jeans|pullover|jacke|socken|unterwäsche|schuhe|sneaker|kleid|mütze|schal/i),
      sub('leisure.electronics', 'occasional', { de: 'Elektronik', en: 'Electronics', fr: 'Électronique', it: 'Elettronica' },
        /kabel|ladegerät|kopfhörer|usb|adapter|maus|tastatur|iphone|handy|laptop|tablet|speicherkarte/i),
      sub('leisure.media', 'treat', { de: 'Bücher & Medien', en: 'Books & media', fr: 'Livres & médias', it: 'Libri e media' },
        /buch|zeitschrift|zeitung|magazin|comic|dvd|vinyl/i),
      sub('leisure.hobby', 'treat', { de: 'Hobby & Sport', en: 'Hobbies & sport', fr: 'Loisirs & sport', it: 'Hobby e sport' },
        /sport|fitness|ball|velo|spiel|lego|farbe|pinsel|garten|samen/i),
      sub('leisure.events', 'treat', { de: 'Ausgang & Events', en: 'Going out', fr: 'Sorties', it: 'Uscite ed eventi' },
        /ticket|eintritt|kino|konzert|museum|festival/i),
      sub('leisure.gifts', 'occasional', { de: 'Geschenke', en: 'Gifts', fr: 'Cadeaux', it: 'Regali' },
        /geschenk|gutschein|karte\b|grusskarte/i),
    ],
  },
  {
    id: 'Mobilität', color: '#5E5CE6', scope: 'both',
    label: { de: 'Mobilität', en: 'Getting around', fr: 'Mobilité', it: 'Mobilità' },
    subcategories: [
      sub('mobility.fuel', 'essential', { de: 'Treibstoff', en: 'Fuel', fr: 'Carburant', it: 'Carburante' },
        /benzin|diesel|bleifrei|super 95|super 98|treibstoff|tankstelle|ladestation/i),
      sub('mobility.public', 'essential', { de: 'ÖV', en: 'Public transport', fr: 'Transports publics', it: 'Trasporti pubblici' },
        /billett|sbb|zvv|tageskarte|halbtax|ga\b|bus|tram/i),
      sub('mobility.parking', 'essential', { de: 'Parkieren', en: 'Parking', fr: 'Stationnement', it: 'Parcheggio' },
        /parking|parkhaus|parkuhr/i),
      sub('mobility.taxi', 'treat', { de: 'Taxi & Fahrdienste', en: 'Taxi & rides', fr: 'Taxi & VTC', it: 'Taxi e passaggi' },
        /taxi|uber|bolt/i),
      sub('mobility.travel', 'occasional', { de: 'Reisen', en: 'Travel', fr: 'Voyages', it: 'Viaggi' },
        /flug|hotel|airbnb|booking|reise/i),
    ],
  },
  {
    id: 'Wohnen & Nebenkosten', color: '#A2845E', scope: 'bank',
    label: { de: 'Wohnen & Nebenkosten', en: 'Housing & utilities', fr: 'Logement & charges', it: 'Casa e utenze' },
    subcategories: [
      sub('housing.rent', 'essential', { de: 'Miete', en: 'Rent', fr: 'Loyer', it: 'Affitto' }),
      sub('housing.utilities', 'essential', { de: 'Energie & Nebenkosten', en: 'Energy & utilities', fr: 'Énergie & charges', it: 'Energia e spese accessorie' }),
    ],
  },
  {
    id: 'Versicherungen & Abos', color: '#30B0C7', scope: 'bank',
    label: { de: 'Versicherungen & Abos', en: 'Insurance & subscriptions', fr: 'Assurances & abonnements', it: 'Assicurazioni e abbonamenti' },
    subcategories: [
      sub('insurance.health', 'essential', { de: 'Krankenkasse', en: 'Health insurance', fr: 'Assurance maladie', it: 'Cassa malati' }),
      sub('insurance.other', 'essential', { de: 'Versicherungen', en: 'Insurance', fr: 'Assurances', it: 'Assicurazioni' }),
      sub('insurance.telecom', 'essential', { de: 'Handy & Internet', en: 'Phone & internet', fr: 'Mobile & internet', it: 'Telefono e internet' }),
      sub('insurance.streaming', 'treat', { de: 'Streaming & Abos', en: 'Streaming & subscriptions', fr: 'Streaming & abonnements', it: 'Streaming e abbonamenti' }),
    ],
  },
  {
    id: 'Diverses', color: '#8E8E93', scope: 'both',
    label: { de: 'Diverses', en: 'Other', fr: 'Divers', it: 'Varie' },
    subcategories: [
      sub('other.deposit', 'essential', { de: 'Pfand', en: 'Deposit', fr: 'Consigne', it: 'Vuoto a rendere' },
        /pfand|depot|harass|leergut/i),
      sub('other.fees', 'essential', { de: 'Gebühren', en: 'Fees', fr: 'Frais', it: 'Commissioni' },
        /gebühr|spesen|kontoführung|bargeldbezug|zins/i),
      sub('other.misc', 'essential', { de: 'Sonstiges', en: 'Other', fr: 'Autres', it: 'Altro' }),
    ],
  },
];

/** Labels für Nicht-Ausgaben und Arten. */
export const SPECIAL_LABELS: Record<string, L10n> = {
  'Einkommen':            { de: 'Einkommen', en: 'Income', fr: 'Revenus', it: 'Entrate' },
  'Umbuchung':            { de: 'Umbuchung', en: 'Transfer', fr: 'Virement interne', it: 'Giroconto' },
  'Nicht kategorisiert':  { de: 'Nicht kategorisiert', en: 'Uncategorized', fr: 'Non classé', it: 'Non classificato' },
  'Weitere':              { de: 'Weitere', en: 'More', fr: 'Autres', it: 'Altri' },
};

export const NATURE_LABELS: Record<Nature, L10n> = {
  essential:  { de: 'Grundbedarf', en: 'Essentials', fr: 'Essentiel', it: 'Essenziale' },
  treat:      { de: 'Genuss & Komfort', en: 'Treats & convenience', fr: 'Plaisirs & confort', it: 'Sfizi e comodità' },
  occasional: { de: 'Anschaffungen', en: 'One-off purchases', fr: 'Achats ponctuels', it: 'Acquisti occasionali' },
};

const CATEGORY_BY_ID = new Map(TAXONOMY.map((c) => [c.id, c]));
const SUB_BY_KEY = new Map(TAXONOMY.flatMap((c) => c.subcategories.map((s) => [s.key, { ...s, categoryId: c.id }] as const)));

export function categoryDef(id: string): CategoryDef | undefined {
  return CATEGORY_BY_ID.get(id);
}

export function subcategoryDef(key: string | null | undefined): (SubcategoryDef & { categoryId: string }) | undefined {
  return key ? SUB_BY_KEY.get(key) : undefined;
}

/** Hauptkategorie einer Unterkategorie ("household.cleaning" → "Haushalt"). */
export function categoryOfSubcategory(key: string | null | undefined): string | undefined {
  return subcategoryDef(key)?.categoryId;
}

export function isSubcategoryOf(key: string | null | undefined, categoryId: string): boolean {
  return categoryOfSubcategory(key) === categoryId;
}

/** Art, wenn nur die Hauptkategorie bekannt ist (z. B. Bank-Buchung ohne Quittung). */
const CATEGORY_DEFAULT_NATURE: Record<string, Nature> = {
  'Restaurant & Take-away': 'treat',
  'Freizeit & Shopping': 'treat',
  'Einrichtung': 'occasional',
};

/** Art eines Artikels/einer Buchung: aus der Unterkategorie, sonst Standard der Hauptkategorie, sonst Grundbedarf. */
export function natureOf(subcategory: string | null | undefined, categoryId?: string): Nature {
  const def = subcategoryDef(subcategory);
  if (def) return def.nature;
  return (categoryId && CATEGORY_DEFAULT_NATURE[categoryId]) || 'essential';
}

/** Prüf-Reihenfolge: Non-Food zuerst ("Zahnpasta" ist keine Pasta, "Pflanzenöl" kein Haushalt). */
const CLASSIFY_ORDER = ['Haushalt', 'Gesundheit', 'Einrichtung', 'Getränke', 'Lebensmittel', 'Mobilität',
  'Freizeit & Shopping', 'Restaurant & Take-away', 'Diverses'];

/** Regelbasierte Einordnung eines Artikelnamens (ohne KI). */
export function classifyItemName(name: string): { category: string; subcategory: string } | null {
  for (const id of CLASSIFY_ORDER) {
    const cat = CATEGORY_BY_ID.get(id);
    if (!cat) continue;
    for (const s of cat.subcategories) {
      if (s.pattern?.test(name)) return { category: cat.id, subcategory: s.key };
    }
  }
  return null;
}

/** Erlaubte Unterkategorie-Schlüssel einer Hauptkategorie (für Prompt-Validierung). */
export const ALL_SUBCATEGORY_KEYS = TAXONOMY.flatMap((c) => c.subcategories.map((s) => s.key));

/** Kompakte Liste für LLM-Prompts: "food.dairy = Lebensmittel › Milch, Käse & Eier" */
export function taxonomyPromptList(scope: 'item' | 'bank' = 'item'): string {
  return TAXONOMY
    .filter((c) => c.scope === 'both' || c.scope === scope)
    .flatMap((c) => c.subcategories.map((s) => `${s.key} = ${c.id} › ${s.label.de}`))
    .join('\n');
}

/** Prompt-Abschnitt für Quittungs-Scans (scan-receipt, Gemini-BYOK): Haupt- + Unterkategorie je Artikel. */
export function receiptItemCategoryPrompt(): string {
  return `Kategorie: Gib jedem Artikel "subcategory" = genau ein Schlüssel aus dieser Liste und "tags" = [die zugehörige Hauptkategorie]:
${taxonomyPromptList('item')}

Hinweise zur Einordnung:
- Zahnpasta, Shampoo, Seife, Deo, Toilettenpapier, Putz- und Waschmittel → Haushalt (household.*)
- Medikamente, Pflaster, Vitamine → Gesundheit (health.*)
- Bratpfanne, Geschirr, Gläser, Möbel, Lampen, Deko, Haushaltsgeräte → Einrichtung (home.*)
- Süssgetränke → drinks.soft, Chips/Schokolade/Süsses → food.snacks, Fertiggerichte/Sandwiches → food.ready
- Verzehrfertiges in Restaurant, Café oder Imbiss → Restaurant & Take-away (dining.*)
- Pfand/Depot → other.deposit; ein Rabatt bekommt die Unterkategorie des rabattierten Artikels`;
}

// ── Lokalisierung ────────────────────────────────────────────────────────────

export function resolveLocale(tag: string | null | undefined): Locale {
  const lang = (tag ?? 'de').slice(0, 2).toLowerCase();
  return lang === 'en' || lang === 'fr' || lang === 'it' ? lang : 'de';
}

export function categoryLabel(id: string, locale: Locale = 'de'): string {
  return CATEGORY_BY_ID.get(id)?.label[locale] ?? SPECIAL_LABELS[id]?.[locale] ?? id;
}

export function subcategoryLabel(key: string, locale: Locale = 'de'): string {
  return SUB_BY_KEY.get(key)?.label[locale] ?? SPECIAL_LABELS[key]?.[locale] ?? key;
}

export function natureLabel(nature: Nature, locale: Locale = 'de'): string {
  return NATURE_LABELS[nature][locale];
}

// ── Adaptive Gruppierung ("semi-smarte Filter") ──────────────────────────────

export interface GroupLevel<T> {
  key: (item: T) => string;
  label: (key: string) => string;
}

export interface GroupNode<T> {
  key: string;
  label: string;
  count: number;
  total: number;
  /** Gesetzt bei Blättern (flache Liste) */
  items?: T[];
  /** Gesetzt, wenn weiter gruppiert wurde */
  children?: GroupNode<T>[];
}

export interface GroupOptions {
  /** Ab so vielen Artikeln wird gruppiert, darunter flache Liste */
  splitThreshold: number;
  /** Gruppen mit weniger Artikeln wandern in "Weitere" */
  minGroupSize: number;
  otherLabel: string;
}

export const DEFAULT_GROUP_OPTIONS: GroupOptions = { splitThreshold: 25, minGroupSize: 3, otherLabel: 'Weitere' };
export const OTHER_GROUP_KEY = '__other__';

/**
 * Wenige Artikel → flache Liste. Viele → nach der ersten Ebene gruppieren
 * (kleine Gruppen zusammengefasst in "Weitere"), rekursiv nach der nächsten Ebene.
 * Ergibt eine Ebene nur eine einzige Gruppe, wird sie übersprungen.
 */
export function groupAdaptive<T>(
  items: T[],
  levels: GroupLevel<T>[],
  amountOf: (item: T) => number,
  options: Partial<GroupOptions> = {},
  key = 'all',
  label = '',
): GroupNode<T> {
  const opts = { ...DEFAULT_GROUP_OPTIONS, ...options };
  const total = items.reduce((s, i) => s + amountOf(i), 0);
  const leaf: GroupNode<T> = { key, label, count: items.length, total, items };
  if (items.length < opts.splitThreshold || levels.length === 0) return leaf;

  const [level, ...rest] = levels;
  const buckets = new Map<string, T[]>();
  for (const item of items) {
    const k = level.key(item);
    buckets.set(k, [...(buckets.get(k) ?? []), item]);
  }

  const big: Array<[string, T[]]> = [];
  const small: T[] = [];
  for (const [k, list] of buckets) {
    if (list.length >= opts.minGroupSize) big.push([k, list]);
    else small.push(...list);
  }

  if (big.length <= 1 && small.length === 0) {
    return groupAdaptive(items, rest, amountOf, opts, key, label);
  }

  const children = big
    .map(([k, list]) => groupAdaptive(list, rest, amountOf, opts, k, level.label(k)))
    .sort((a, b) => b.total - a.total);
  if (small.length > 0) {
    children.push({
      key: OTHER_GROUP_KEY, label: opts.otherLabel, count: small.length,
      total: small.reduce((s, i) => s + amountOf(i), 0), items: small,
    });
  }
  return { key, label, count: items.length, total, children };
}

// ── Produkt-Schlüssel (dritte Ebene: "Toilettenpapier ×12") ──────────────────

const BRAND_PREFIX = /^(m-budget|m-classic|prix garantie|naturaplan|qualité ?& ?prix|coop|migros|bio|denner|aldi|lidl|betty bossi|anna'?s best|sélection|fine food|karma|v-love)\s+/i;
const NOISE = /\b\d+([.,]\d+)?\s*(x|stk|st|kg|g|gr|l|dl|cl|ml|rollen|pack|er)\b|\b\d+\b|[^\p{L}\s-]/giu;

/** Normalisierter Produktname: Marke, Mengen und Zahlen entfernt, erstes Hauptwort. */
export function productKey(name: string): string {
  let n = name.trim().toLowerCase();
  for (let i = 0; i < 2; i++) n = n.replace(BRAND_PREFIX, '');
  n = n.replace(NOISE, ' ').replace(/\s+/g, ' ').trim();
  const word = n.split(' ').find((w) => w.length >= 3) ?? n;
  return word || 'unbekannt';
}

export function productLabel(key: string): string {
  return key.charAt(0).toUpperCase() + key.slice(1);
}
