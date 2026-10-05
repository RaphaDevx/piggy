# ADR 0002: Kategorie-Hierarchie mit Unterkategorien und „Art“

## Status
Accepted (2026-10-05)

## Kontext
Bisher hatte jeder Artikel genau eine grobe Kategorie. Gewünscht: jede Hauptkategorie mit Unterkategorien
(Haushalt › Putzen & Waschen, Papierwaren, Körperpflege & Hygiene …), eine Unterscheidung Grundbedarf vs.
Genuss/Komfort (Süssgetränke, Snacks) vs. Anschaffungen (Bratpfanne, Möbel → „Einrichtung“), Listen, die erst
bei vielen Artikeln gruppieren, und Bezeichnungen, die in anderen Sprachen sinngemäss statt wörtlich übersetzt sind.

## Entscheidung
- Taxonomie als Code in `supabase/functions/_shared/finance-core/taxonomy.ts` (App + Deno, eine Quelle).
- DB: Hauptkategorie bleibt `receipt_items.tags[1]` (stabile deutsche ID), neu `receipt_items.subcategory` (Schlüssel `household.cleaning`).
- „Art“ (essential/treat/occasional) hängt an der Unterkategorie, nicht in der DB — Umdeuten ohne Migration möglich.
- Einordnung: beim Scan direkt vom LLM (Prompt aus der Taxonomie); fehlende Unterkategorien füllt der statement-worker
  im Hintergrund per Haiku (Batch 120, Zeilenformat `Nr|Schlüssel`), validiert gegen die Taxonomie.
- „Körperpflege & Gesundheit“ aufgeteilt: Pflege/Hygiene → Haushalt, Medizin → Gesundheit; neue Hauptkategorie „Einrichtung“.
- Semi-smarte Listen: `groupAdaptive` — unter 25 Artikeln flach, darüber nach Unterkategorie → Produkt, Gruppen < 3 in „Weitere“.
- Sprache: Gerätesprache (`Intl`), Labels de/en/fr/it pro Eintrag gepflegt; keine i18n-Bibliothek.

## Begründung
- Code-Taxonomie statt DB-Tabelle: Prompt, Regeln, Labels und Tests liegen beieinander; Änderungen sind reviewbar und
  brauchen keine Admin-UI. Nutzer-eigene Kategorien bleiben möglich (freie Hauptkategorie ohne Unterkategorien).
- LLM im Hintergrund statt beim Lesen: Kosten einmalig (~1 Rappen pro 100 Artikel), UI bleibt schnell und offline-fähig.
- Wechsel der Hauptkategorie durch das LLM nur eingeschränkt (`REASSIGNABLE`, `RELATED_MOVES`) — schützt vor
  Fehlklassifizierung, erlaubt aber die Korrektur alter Zuordnungen (Küche lag früher unter Haushalt).

## Konsequenzen
- Positiv: Auswertung „Wofür geht dein Geld?“ (Grundbedarf/Genuss/Anschaffungen), Kategorie-Detail mit Drilldown.
- Negativ: zwei Edge Functions (scan-receipt, statement-worker) bündeln die Taxonomie → nach Änderungen beide deployen.
- Restliche App-Texte sind noch deutsch; nur Kategorie-/Art-Labels sind lokalisiert.

## Was passiert bei Austausch?
Unterkategorien entfernen: Spalte `subcategory` ignorieren (UI fällt auf flache Listen zurück), Aufruf von
`classifyItems` im statement-worker entfernen. Hauptkategorien bleiben unverändert nutzbar.
