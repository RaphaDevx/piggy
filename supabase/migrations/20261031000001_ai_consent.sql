-- KI-Einwilligung nachweisbar speichern und im Worker erzwingen (Store-Legal-Gate L6).
--
-- 1. Nachweis: user_settings.ai_consent_at (NULL = keine Einwilligung oder widerrufen)
--    und ai_consent_version (Version des Dialogtexts, App: AI_CONSENT_VERSION).
-- 2. Erzwingung: Die claim_*-RPCs des statement-worker, deren Ergebnis an api.anthropic.com
--    geht, geben nur Zeilen von Nutzern mit ai_consent_at IS NOT NULL zurück.
--
-- ACHTUNG Reihenfolge: Erst die App mit dem Patch ausliefern (sie schreibt ai_consent_at),
-- dann diese Migration anwenden. Davor ruht die Artikel-Klassifizierung für alle Nutzer,
-- bis die App den Zeitstempel geschrieben hat (Nachtrag beim Start via syncAiConsent).
-- Idempotent: mehrfaches Anwenden ist unschädlich.

ALTER TABLE public.user_settings
  ADD COLUMN IF NOT EXISTS ai_consent_at timestamptz,
  ADD COLUMN IF NOT EXISTS ai_consent_version text;

-- Hintergrund-Einordnung von Artikeln (statement-worker, Schritt 5 → Anthropic).
-- Signatur und Rückgabe unverändert (Migration 20261006000005), neu: Einwilligungsfilter.
CREATE OR REPLACE FUNCTION public.claim_items_to_classify(p_limit int)
RETURNS TABLE (id uuid, name text, store text, category text)
LANGUAGE sql SECURITY DEFINER SET search_path = '' AS $$
  WITH picked AS (
    UPDATE public.receipt_items ri SET classify_locked_at = now()
    WHERE ri.id IN (
      SELECT i.id FROM public.receipt_items i
      WHERE i.subcategory IS NULL AND i.classified_at IS NULL AND NOT i.is_adjustment
        AND (i.classify_locked_at IS NULL OR i.classify_locked_at < now() - interval '10 minutes')
        AND EXISTS (
          SELECT 1
          FROM public.receipts rc
          JOIN public.user_settings us ON us.user_id = rc.user_id
          WHERE rc.id = i.receipt_id AND us.ai_consent_at IS NOT NULL
        )
      ORDER BY i.id
      LIMIT p_limit
      FOR UPDATE OF i SKIP LOCKED
    )
    RETURNING ri.id, ri.name, ri.receipt_id, ri.tags
  )
  SELECT p.id, p.name, coalesce(r.store_name, ''), coalesce(p.tags[1], 'Diverses')
  FROM picked p JOIN public.receipts r ON r.id = p.receipt_id;
$$;
REVOKE EXECUTE ON FUNCTION public.claim_items_to_classify(int) FROM PUBLIC, anon, authenticated;
GRANT EXECUTE ON FUNCTION public.claim_items_to_classify(int) TO service_role;

-- Kontoauszug-Seiten einlesen (statement-worker, Stufe 1 → Anthropic bei text-llm/pdf-llm).
-- Basis: Migration 20261006000002 (Drossel), neu: Einwilligungsfilter über statement_pages.user_id.
-- Seiten von Nutzern ohne Einwilligung bleiben 'pending'; der Auszug wartet im Fortschritt.
CREATE OR REPLACE FUNCTION public.claim_statement_pages(p_limit int)
RETURNS SETOF public.statement_pages LANGUAGE sql SECURITY DEFINER SET search_path = '' AS $$
  UPDATE public.statement_pages p
  SET status = 'processing', attempts = p.attempts + 1, locked_at = now()
  WHERE p.id IN (
    SELECT sp.id FROM public.statement_pages sp
    WHERE ((sp.status = 'pending' AND sp.next_attempt_at <= now())
        OR (sp.status = 'processing' AND sp.locked_at < now() - interval '10 minutes'))
      AND EXISTS (
        SELECT 1 FROM public.user_settings us
        WHERE us.user_id = sp.user_id AND us.ai_consent_at IS NOT NULL
      )
    ORDER BY sp.page_no, sp.created_at
    LIMIT greatest(0, p_limit - (
      SELECT count(*) FROM public.statement_pages x
      WHERE x.status = 'processing' AND x.locked_at >= now() - interval '10 minutes'))
    FOR UPDATE OF sp SKIP LOCKED
  )
  RETURNING p.*;
$$;
REVOKE EXECUTE ON FUNCTION public.claim_statement_pages(int) FROM PUBLIC, anon, authenticated;
GRANT EXECUTE ON FUNCTION public.claim_statement_pages(int) TO service_role;
