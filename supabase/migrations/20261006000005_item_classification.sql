-- Hintergrund-Klassifizierung von Artikeln ohne Unterkategorie (statement-worker, Schritt 5).
-- classified_at: einmal versucht (auch wenn ohne Ergebnis) → keine Endlosschleife.
ALTER TABLE public.receipt_items
  ADD COLUMN IF NOT EXISTS classified_at timestamptz,
  ADD COLUMN IF NOT EXISTS classify_locked_at timestamptz;

CREATE OR REPLACE FUNCTION public.claim_items_to_classify(p_limit int)
RETURNS TABLE (id uuid, name text, store text, category text)
LANGUAGE sql SECURITY DEFINER SET search_path = '' AS $$
  WITH picked AS (
    UPDATE public.receipt_items ri SET classify_locked_at = now()
    WHERE ri.id IN (
      SELECT i.id FROM public.receipt_items i
      WHERE i.subcategory IS NULL AND i.classified_at IS NULL AND NOT i.is_adjustment
        AND (i.classify_locked_at IS NULL OR i.classify_locked_at < now() - interval '10 minutes')
      ORDER BY i.id
      LIMIT p_limit
      FOR UPDATE SKIP LOCKED
    )
    RETURNING ri.id, ri.name, ri.receipt_id, ri.tags
  )
  SELECT p.id, p.name, coalesce(r.store_name, ''), coalesce(p.tags[1], 'Diverses')
  FROM picked p JOIN public.receipts r ON r.id = p.receipt_id;
$$;
REVOKE EXECUTE ON FUNCTION public.claim_items_to_classify(int) FROM PUBLIC, anon, authenticated;
GRANT EXECUTE ON FUNCTION public.claim_items_to_classify(int) TO service_role;

INSERT INTO public.app_settings (key, value) VALUES
  ('statement_classify_batch', '120'),
  ('statement_classify_batches_per_tick', '2')
ON CONFLICT (key) DO NOTHING;
