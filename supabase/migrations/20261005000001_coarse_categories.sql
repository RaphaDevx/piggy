-- Grobe Kategorien statt feiner Tags (Feedback: Vorschläge zu fein; wichtig ist
-- Lebensmittel vs. Haushalt). Jeder Artikel bekommt genau eine Kategorie
-- (tags[1]); die alten Tags bleiben in tags_legacy erhalten.
-- Mapping identisch zu LEGACY_TAG_CATEGORY in src/lib/categories.ts.

ALTER TABLE public.receipt_items ADD COLUMN IF NOT EXISTS tags_legacy text[];

UPDATE public.receipt_items SET tags_legacy = tags WHERE tags_legacy IS NULL;

CREATE OR REPLACE FUNCTION pg_temp.coarse_category(tag text) RETURNS text
LANGUAGE sql IMMUTABLE AS $$
  SELECT CASE
    WHEN tag IN ('Gemüse & Obst','Milchprodukte','Fleisch & Fisch','Backwaren','Tiefkühlkost',
                 'Konserven','Grundnahrungsmittel','Snacks & Süsswaren') THEN 'Lebensmittel'
    WHEN tag IN ('Alkohol','Kaffee & Tee') THEN 'Getränke'
    WHEN tag IN ('Reinigung','Entsorgung','Küche','Wohnen & Deko') THEN 'Haushalt'
    WHEN tag IN ('Hygiene','Körperpflege','Haarpflege','Mundpflege','Damenhygiene',
                 'Gesundheit','Medikamente','Nahrungsergänzung') THEN 'Körperpflege & Gesundheit'
    WHEN tag IN ('Kleidung','Elektronik','Freizeit & Hobby','Büro') THEN 'Freizeit & Shopping'
    ELSE tag
  END
$$;

UPDATE public.receipt_items
SET tags = ARRAY[pg_temp.coarse_category(tags[1])]
WHERE cardinality(tags) > 0;

UPDATE public.receipt_items SET tags = ARRAY['Diverses'] WHERE tags IS NULL OR cardinality(tags) = 0;

-- Bank-Buchungen: Kategorie für die Finanzübersicht (NULL = noch nicht kategorisiert)
ALTER TABLE public.bank_transactions ADD COLUMN IF NOT EXISTS category text;
CREATE INDEX IF NOT EXISTS bank_transactions_user_date_idx
  ON public.bank_transactions (user_id, booking_date);
