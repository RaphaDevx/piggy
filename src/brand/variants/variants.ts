// AUTO-GENERATED von K-Creative-Cloud/brand/piggy/tools/build.js — NICHT manuell bearbeiten.
// Quelle: brand/piggy/seasons/*.json
// Neu erzeugen: bash K-Creative-Cloud/brand/piggy/build.sh --sync

import type { ImageSourcePropType } from 'react-native';

export type VariantKind = 'base' | 'season' | 'month';
export type PiggyVariant = {
  id: string;
  kind: VariantKind;
  name: string;
  /** MM-DD inklusiv; from > to = über Jahreswechsel */
  valid: { month?: number; from: string; to: string } | null;
  accessories: string[];
  background: string[];
  splashBg: string;
  note: string;
};

export const PIGGY_VARIANTS: PiggyVariant[] = [
  {
    "id": "base",
    "kind": "base",
    "name": "Basis",
    "valid": null,
    "accessories": [],
    "background": [],
    "splashBg": "#FAFAF9",
    "note": ""
  },
  {
    "id": "month-01-neujahr",
    "kind": "month",
    "name": "Neujahr",
    "valid": {
      "month": 1,
      "from": "01-01",
      "to": "01-07"
    },
    "accessories": [
      "partyHat"
    ],
    "background": [
      "confetti"
    ],
    "splashBg": "#FAFAF9",
    "note": "Partyhut, Konfetti"
  },
  {
    "id": "month-02-fasnacht",
    "kind": "month",
    "name": "Fasnacht",
    "valid": {
      "month": 2,
      "from": "02-10",
      "to": "02-28"
    },
    "accessories": [
      "dominoMask"
    ],
    "background": [
      "confetti"
    ],
    "splashBg": "#FAFAF9",
    "note": "Larve/Maske, Räppli. Fasnachtsdaten variieren jährlich (Luzern/Basel) — Fenster jährlich prüfen"
  },
  {
    "id": "month-03-steuern",
    "kind": "month",
    "name": "Steuererklärung",
    "valid": {
      "month": 3,
      "from": "03-15",
      "to": "03-31"
    },
    "accessories": [
      "roundGlasses",
      "receipt"
    ],
    "background": [
      "coins"
    ],
    "splashBg": "#FAFAF9",
    "note": "Brille + Quittung, fallende Münzen — Schweizer Steuerfrist 31. März, passt zu Quittungs-App"
  },
  {
    "id": "month-04-ostern",
    "kind": "month",
    "name": "Ostern",
    "valid": {
      "month": 4,
      "from": "04-01",
      "to": "04-21"
    },
    "accessories": [
      "bunnyEars",
      "easterEgg"
    ],
    "background": [
      "petals"
    ],
    "splashBg": "#FAFAF9",
    "note": "Hasenohren, Osterei. Ostern ist beweglich — Fenster deckt die häufigsten Daten ab"
  },
  {
    "id": "month-05-muttertag",
    "kind": "month",
    "name": "Muttertag",
    "valid": {
      "month": 5,
      "from": "05-01",
      "to": "05-14"
    },
    "accessories": [
      "heartBalloon"
    ],
    "background": [
      "hearts"
    ],
    "splashBg": "#FAFAF9",
    "note": "Herzballon, Herzen"
  },
  {
    "id": "month-06-badi",
    "kind": "month",
    "name": "Badi",
    "valid": {
      "month": 6,
      "from": "06-15",
      "to": "06-30"
    },
    "accessories": [
      "swimRing"
    ],
    "background": [
      "waves"
    ],
    "splashBg": "#FAFAF9",
    "note": "Schwimmring, Wellen — Badi-Saison"
  },
  {
    "id": "month-07-ferien",
    "kind": "month",
    "name": "Sommerferien",
    "valid": {
      "month": 7,
      "from": "07-01",
      "to": "07-24"
    },
    "accessories": [
      "strawHat"
    ],
    "background": [
      "sun"
    ],
    "splashBg": "#FAFAF9",
    "note": "Strohhut, Sonne"
  },
  {
    "id": "month-08-bundesfeier",
    "kind": "month",
    "name": "1. August",
    "valid": {
      "month": 8,
      "from": "08-01",
      "to": "08-07"
    },
    "accessories": [
      "swissFlag"
    ],
    "background": [
      "fireworks"
    ],
    "splashBg": "#3A1E2A",
    "note": "Schweizerfahne, Feuerwerk am Nachthimmel"
  },
  {
    "id": "month-09-alpabzug",
    "kind": "month",
    "name": "Alpabzug",
    "valid": {
      "month": 9,
      "from": "09-15",
      "to": "09-30"
    },
    "accessories": [
      "cowBell",
      "flowerCrown"
    ],
    "background": [
      "mountains"
    ],
    "splashBg": "#FAFAF9",
    "note": "Treichel + Blumenkranz, Alpen"
  },
  {
    "id": "month-10-halloween",
    "kind": "month",
    "name": "Halloween",
    "valid": {
      "month": 10,
      "from": "10-24",
      "to": "10-31"
    },
    "accessories": [
      "witchHat"
    ],
    "background": [
      "bats"
    ],
    "splashBg": "#FAFAF9",
    "note": "Hexenhut, Fledermäuse, Mond"
  },
  {
    "id": "month-11-raebeliechtli",
    "kind": "month",
    "name": "Räbeliechtli",
    "valid": {
      "month": 11,
      "from": "11-01",
      "to": "11-15"
    },
    "accessories": [
      "lantern"
    ],
    "background": [
      "stars"
    ],
    "splashBg": "#5B3FB0",
    "note": "Räben-Laterne, Sternenhimmel — Schweizer Räbeliechtli-Umzug"
  },
  {
    "id": "month-12-samichlaus",
    "kind": "month",
    "name": "Samichlaus / Advent",
    "valid": {
      "month": 12,
      "from": "12-01",
      "to": "12-26"
    },
    "accessories": [
      "santaHat"
    ],
    "background": [
      "snow"
    ],
    "splashBg": "#FAFAF9",
    "note": "Samichlaus-Mütze, Schnee"
  },
  {
    "id": "season-autumn",
    "kind": "season",
    "name": "Herbst",
    "valid": {
      "from": "09-23",
      "to": "12-20"
    },
    "accessories": [
      "scarf"
    ],
    "background": [
      "leaves"
    ],
    "splashBg": "#FAFAF9",
    "note": "Schal, fallende Blätter, warme Orangetöne"
  },
  {
    "id": "season-spring",
    "kind": "season",
    "name": "Frühling",
    "valid": {
      "from": "03-20",
      "to": "06-20"
    },
    "accessories": [
      "flowerCrown"
    ],
    "background": [
      "petals"
    ],
    "splashBg": "#FAFAF9",
    "note": "Blumenkranz, Blütenblätter, frisches Mint"
  },
  {
    "id": "season-summer",
    "kind": "season",
    "name": "Sommer",
    "valid": {
      "from": "06-21",
      "to": "09-22"
    },
    "accessories": [
      "sunglasses"
    ],
    "background": [
      "sun"
    ],
    "splashBg": "#FAFAF9",
    "note": "Sonnenbrille, Sonne, Himmelblau"
  },
  {
    "id": "season-winter",
    "kind": "season",
    "name": "Winter / Ski",
    "valid": {
      "from": "12-21",
      "to": "03-19"
    },
    "accessories": [
      "beanie",
      "skiGoggles"
    ],
    "background": [
      "mountains",
      "snow"
    ],
    "splashBg": "#FAFAF9",
    "note": "Mütze + Skibrille, Alpen, Schnee"
  }
] as PiggyVariant[];

export const BASE_VARIANT = PIGGY_VARIANTS.find((v) => v.id === 'base')!;

const pad2 = (n: number) => String(n).padStart(2, '0');
const inWindow = (md: string, from: string, to: string) => (from <= to ? md >= from && md <= to : md >= from || md <= to);

/**
 * Gültige Variante für ein Datum. Regeln (identisch zu K-Creative lib/tokens.js → currentVariant):
 *   1. Monat schlägt Saison.
 *   2. Mehr als ein Treffer auf der entscheidenden Ebene (Gleichstand) → Basis-Logo.
 *   3. Kein Treffer → Basis-Logo.
 */
export function currentVariant(date: Date = new Date(), variants: PiggyVariant[] = PIGGY_VARIANTS): PiggyVariant {
  const md = pad2(date.getMonth() + 1) + '-' + pad2(date.getDate());
  const hits = variants.filter((v) => v.kind !== 'base' && v.valid && inWindow(md, v.valid.from, v.valid.to));
  for (const kind of ['month', 'season'] as const) {
    const k = hits.filter((v) => v.kind === kind);
    if (k.length === 1) return k[0];
    if (k.length > 1) return BASE_VARIANT;
  }
  return BASE_VARIANT;
}

/** Gerenderte Assets je Variante (@1x/@2x/@3x, Metro wählt automatisch). Logo 240 pt breit, Signet 128 pt. */
export const VARIANT_ASSETS: Record<string, { logo: ImageSourcePropType; signet: ImageSourcePropType }> = {
  'base': {
    logo: require('./base/logo.png'),
    signet: require('./base/signet.png'),
  },
  'month-01-neujahr': {
    logo: require('./month-01-neujahr/logo.png'),
    signet: require('./month-01-neujahr/signet.png'),
  },
  'month-02-fasnacht': {
    logo: require('./month-02-fasnacht/logo.png'),
    signet: require('./month-02-fasnacht/signet.png'),
  },
  'month-03-steuern': {
    logo: require('./month-03-steuern/logo.png'),
    signet: require('./month-03-steuern/signet.png'),
  },
  'month-04-ostern': {
    logo: require('./month-04-ostern/logo.png'),
    signet: require('./month-04-ostern/signet.png'),
  },
  'month-05-muttertag': {
    logo: require('./month-05-muttertag/logo.png'),
    signet: require('./month-05-muttertag/signet.png'),
  },
  'month-06-badi': {
    logo: require('./month-06-badi/logo.png'),
    signet: require('./month-06-badi/signet.png'),
  },
  'month-07-ferien': {
    logo: require('./month-07-ferien/logo.png'),
    signet: require('./month-07-ferien/signet.png'),
  },
  'month-08-bundesfeier': {
    logo: require('./month-08-bundesfeier/logo.png'),
    signet: require('./month-08-bundesfeier/signet.png'),
  },
  'month-09-alpabzug': {
    logo: require('./month-09-alpabzug/logo.png'),
    signet: require('./month-09-alpabzug/signet.png'),
  },
  'month-10-halloween': {
    logo: require('./month-10-halloween/logo.png'),
    signet: require('./month-10-halloween/signet.png'),
  },
  'month-11-raebeliechtli': {
    logo: require('./month-11-raebeliechtli/logo.png'),
    signet: require('./month-11-raebeliechtli/signet.png'),
  },
  'month-12-samichlaus': {
    logo: require('./month-12-samichlaus/logo.png'),
    signet: require('./month-12-samichlaus/signet.png'),
  },
  'season-autumn': {
    logo: require('./season-autumn/logo.png'),
    signet: require('./season-autumn/signet.png'),
  },
  'season-spring': {
    logo: require('./season-spring/logo.png'),
    signet: require('./season-spring/signet.png'),
  },
  'season-summer': {
    logo: require('./season-summer/logo.png'),
    signet: require('./season-summer/signet.png'),
  },
  'season-winter': {
    logo: require('./season-winter/logo.png'),
    signet: require('./season-winter/signet.png'),
  },
};
