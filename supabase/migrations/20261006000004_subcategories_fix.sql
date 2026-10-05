-- Korrektur zu 20261006000003: Hauptkategorie kommt (wie bei der ersten Migration) vom
-- ERSTEN Alt-Tag; eine Unterkategorie wird nur übernommen, wenn sie zu dieser Hauptkategorie
-- gehört. Vorher konnte ein Restaurant-Burger ([Restaurant, Fleisch & Fisch]) zu Lebensmittel wandern.
-- Restaurant-Artikel bekommen ihre Unterkategorie (Restaurant / Café / Take-away) vom LLM.

CREATE TEMP TABLE legacy_map (tag text PRIMARY KEY, cat text NOT NULL, sub text) ON COMMIT DROP;
INSERT INTO legacy_map VALUES
  ('Gemüse & Obst','Lebensmittel','food.produce'), ('Milchprodukte','Lebensmittel','food.dairy'),
  ('Fleisch & Fisch','Lebensmittel','food.meat_fish'), ('Backwaren','Lebensmittel','food.bakery'),
  ('Tiefkühlkost','Lebensmittel','food.frozen'), ('Konserven','Lebensmittel','food.pantry'),
  ('Grundnahrungsmittel','Lebensmittel','food.pantry'), ('Snacks & Süsswaren','Lebensmittel','food.snacks'),
  ('Alkohol','Getränke','drinks.alcohol'), ('Kaffee & Tee','Getränke','drinks.hot'),
  ('Reinigung','Haushalt','household.cleaning'), ('Entsorgung','Haushalt','household.supplies'),
  ('Küche','Einrichtung','home.kitchen'), ('Wohnen & Deko','Einrichtung','home.decor'),
  ('Hygiene','Haushalt','household.care'), ('Körperpflege','Haushalt','household.care'),
  ('Haarpflege','Haushalt','household.care'), ('Mundpflege','Haushalt','household.care'),
  ('Damenhygiene','Haushalt','household.care'), ('Gesundheit','Gesundheit',NULL),
  ('Medikamente','Gesundheit','health.medicine'), ('Nahrungsergänzung','Gesundheit','health.supplements'),
  ('Kleidung','Freizeit & Shopping','leisure.clothing'), ('Elektronik','Freizeit & Shopping','leisure.electronics'),
  ('Freizeit & Hobby','Freizeit & Shopping','leisure.hobby'), ('Büro','Freizeit & Shopping',NULL),
  ('Lebensmittel','Lebensmittel',NULL), ('Getränke','Getränke',NULL), ('Haushalt','Haushalt',NULL),
  ('Restaurant & Take-away','Restaurant & Take-away',NULL), ('Diverses','Diverses',NULL);

WITH base AS (
  SELECT ri.id,
         coalesce((SELECT m.cat FROM legacy_map m WHERE m.tag = ri.tags_legacy[1]), ri.tags_legacy[1]) AS cat0,
         ri.tags_legacy
  FROM public.receipt_items ri
  WHERE ri.tags_legacy IS NOT NULL AND cardinality(ri.tags_legacy) > 0
)
UPDATE public.receipt_items ri
SET tags = ARRAY[b.cat0],
    subcategory = (
      SELECT m.sub FROM unnest(b.tags_legacy) WITH ORDINALITY AS t(tag, ord)
      JOIN legacy_map m ON m.tag = t.tag
      WHERE m.sub IS NOT NULL AND m.cat = b.cat0
      ORDER BY t.ord LIMIT 1)
FROM base b
WHERE ri.id = b.id;
