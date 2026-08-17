export interface TagInfo {
  label: string;
  color: string;
  group: string;
}

export const TAG_MAP: Record<string, TagInfo> = {
  // Lebensmittel
  'Lebensmittel':         { label: 'Lebensmittel',      color: '#34C759', group: 'Lebensmittel' },
  'Gemüse & Obst':        { label: 'Gemüse & Obst',     color: '#30D158', group: 'Lebensmittel' },
  'Milchprodukte':        { label: 'Milchprodukte',      color: '#32ADE6', group: 'Lebensmittel' },
  'Fleisch & Fisch':      { label: 'Fleisch & Fisch',    color: '#FF6B6B', group: 'Lebensmittel' },
  'Backwaren':            { label: 'Backwaren',          color: '#FF9F0A', group: 'Lebensmittel' },
  'Tiefkühlkost':         { label: 'Tiefkühlkost',       color: '#5AC8FA', group: 'Lebensmittel' },
  'Konserven':            { label: 'Konserven',          color: '#64D2FF', group: 'Lebensmittel' },
  'Grundnahrungsmittel':  { label: 'Grundnahrungsmittel', color: '#A2845E', group: 'Lebensmittel' },
  'Snacks & Süsswaren':   { label: 'Snacks & Süsswaren', color: '#FF375F', group: 'Lebensmittel' },
  // Getränke
  'Getränke':             { label: 'Getränke',           color: '#0A84FF', group: 'Getränke' },
  'Alkohol':              { label: 'Alkohol',            color: '#FF9F0A', group: 'Getränke' },
  'Kaffee & Tee':         { label: 'Kaffee & Tee',       color: '#AC8E68', group: 'Getränke' },
  // Haushalt
  'Haushalt':             { label: 'Haushalt',           color: '#FF9F0A', group: 'Haushalt' },
  'Reinigung':            { label: 'Reinigung',          color: '#30B0C7', group: 'Haushalt' },
  'Entsorgung':           { label: 'Entsorgung',         color: '#636366', group: 'Haushalt' },
  'Küche':                { label: 'Küche',              color: '#FF6B6B', group: 'Haushalt' },
  'Wohnen & Deko':        { label: 'Wohnen & Deko',      color: '#BF5AF2', group: 'Haushalt' },
  // Hygiene
  'Hygiene':              { label: 'Hygiene',            color: '#64D2FF', group: 'Hygiene' },
  'Körperpflege':         { label: 'Körperpflege',       color: '#30B0C7', group: 'Hygiene' },
  'Haarpflege':           { label: 'Haarpflege',         color: '#5E5CE6', group: 'Hygiene' },
  'Mundpflege':           { label: 'Mundpflege',         color: '#32ADE6', group: 'Hygiene' },
  'Damenhygiene':         { label: 'Damenhygiene',       color: '#FF6B9D', group: 'Hygiene' },
  // Gesundheit
  'Gesundheit':           { label: 'Gesundheit',         color: '#30D158', group: 'Gesundheit' },
  'Medikamente':          { label: 'Medikamente',        color: '#34C759', group: 'Gesundheit' },
  'Nahrungsergänzung':    { label: 'Nahrungsergänzung',  color: '#32D74B', group: 'Gesundheit' },
  // Diverses
  'Restaurant & Take-away': { label: 'Restaurant',       color: '#FF6B6B', group: 'Diverses' },
  'Kleidung':             { label: 'Kleidung',           color: '#BF5AF2', group: 'Diverses' },
  'Elektronik':           { label: 'Elektronik',         color: '#0A84FF', group: 'Diverses' },
  'Freizeit & Hobby':     { label: 'Freizeit & Hobby',   color: '#FF9F0A', group: 'Diverses' },
  'Büro':                 { label: 'Büro',               color: '#636366', group: 'Diverses' },
  'Diverses':             { label: 'Diverses',           color: '#48484A', group: 'Diverses' },
};

export const ALL_TAGS = Object.keys(TAG_MAP);

export function getTagColor(tag: string): string {
  return TAG_MAP[tag]?.color ?? '#48484A';
}

export function getTagGroup(tag: string): string {
  return TAG_MAP[tag]?.group ?? 'Diverses';
}

// Unique groups with their display color (for charts)
export const TAG_GROUPS: Record<string, string> = {
  'Lebensmittel': '#34C759',
  'Getränke':     '#0A84FF',
  'Haushalt':     '#FF9F0A',
  'Hygiene':      '#64D2FF',
  'Gesundheit':   '#30D158',
  'Diverses':     '#636366',
};

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
