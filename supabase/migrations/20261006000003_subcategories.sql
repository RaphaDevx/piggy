-- Kategorie-Hierarchie: Hauptkategorie (tags[1]) + Unterkategorie (subcategory).
-- "Körperpflege & Gesundheit" wird aufgeteilt: Pflege/Hygiene → Haushalt, Medizin → Gesundheit.
-- Neue Hauptkategorie "Einrichtung" (Küche & Kochen, Möbel, Deko, Geräte).
-- Mapping identisch zu LEGACY_TAG_SUBCATEGORY (finance-core/categories.ts).
-- Artikel ohne eindeutige Zuordnung bleiben subcategory = NULL → statement-worker klassifiziert per LLM.

ALTER TABLE public.receipt_items ADD COLUMN IF NOT EXISTS subcategory text;
CREATE INDEX IF NOT EXISTS receipt_items_unclassified_idx
  ON public.receipt_items (id) WHERE subcategory IS NULL AND NOT is_adjustment;

CREATE TEMP TABLE legacy_map (tag text PRIMARY KEY, sub text NOT NULL) ON COMMIT DROP;
INSERT INTO legacy_map VALUES
  ('Gemüse & Obst','food.produce'), ('Milchprodukte','food.dairy'), ('Fleisch & Fisch','food.meat_fish'),
  ('Backwaren','food.bakery'), ('Tiefkühlkost','food.frozen'), ('Konserven','food.pantry'),
  ('Grundnahrungsmittel','food.pantry'), ('Snacks & Süsswaren','food.snacks'),
  ('Alkohol','drinks.alcohol'), ('Kaffee & Tee','drinks.hot'),
  ('Reinigung','household.cleaning'), ('Entsorgung','household.supplies'), ('Küche','home.kitchen'), ('Wohnen & Deko','home.decor'),
  ('Hygiene','household.care'), ('Körperpflege','household.care'), ('Haarpflege','household.care'), ('Mundpflege','household.care'),
  ('Damenhygiene','household.care'), ('Medikamente','health.medicine'), ('Nahrungsergänzung','health.supplements'),
  ('Kleidung','leisure.clothing'), ('Elektronik','leisure.electronics'), ('Freizeit & Hobby','leisure.hobby');

-- Unterkategorie aus dem ersten eindeutigen Alt-Tag
UPDATE public.receipt_items ri
SET subcategory = (
  SELECT m.sub FROM unnest(ri.tags_legacy) WITH ORDINALITY AS t(tag, ord)
  JOIN legacy_map m ON m.tag = t.tag ORDER BY t.ord LIMIT 1)
WHERE ri.subcategory IS NULL AND ri.tags_legacy IS NOT NULL;

-- Hauptkategorie passend zur Unterkategorie
UPDATE public.receipt_items
SET tags = ARRAY[CASE split_part(subcategory, '.', 1)
  WHEN 'food' THEN 'Lebensmittel' WHEN 'drinks' THEN 'Getränke' WHEN 'household' THEN 'Haushalt'
  WHEN 'health' THEN 'Gesundheit' WHEN 'home' THEN 'Einrichtung' WHEN 'dining' THEN 'Restaurant & Take-away'
  WHEN 'leisure' THEN 'Freizeit & Shopping' WHEN 'mobility' THEN 'Mobilität' ELSE 'Diverses' END]
WHERE subcategory IS NOT NULL;

-- Rest der alten Sammelkategorie (ohne eindeutigen Alt-Tag)
UPDATE public.receipt_items SET tags = ARRAY['Gesundheit'] WHERE tags[1] = 'Körperpflege & Gesundheit';
UPDATE public.bank_transactions SET category = 'Gesundheit' WHERE category = 'Körperpflege & Gesundheit';
