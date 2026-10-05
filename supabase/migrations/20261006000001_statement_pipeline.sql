-- Kontoauszug-Pipeline im Hintergrund (ADR 0001):
--   bank_statements (stage) → statement_pages (Einlesen pro Seite, gedrosselt)
--   → Verrechnen (Duplikate, bank_transactions, Abgleich, match_suggestions)
-- Der Worker (Edge Function statement-worker) wird jede Minute von pg_cron
-- aufgerufen und authentifiziert sich mit einem Secret aus dem Vault.

CREATE EXTENSION IF NOT EXISTS pg_cron;
CREATE EXTENSION IF NOT EXISTS pg_net WITH SCHEMA extensions;

-- ── Auszüge: Pipeline-Status ─────────────────────────────────────────────────

ALTER TABLE public.bank_statements
  ADD COLUMN IF NOT EXISTS file_path    text,
  ADD COLUMN IF NOT EXISTS mime_type    text,
  ADD COLUMN IF NOT EXISTS stage        text NOT NULL DEFAULT 'done'
    CHECK (stage IN ('queued', 'extracting', 'reconciling', 'done', 'error')),
  ADD COLUMN IF NOT EXISTS page_count   int,
  ADD COLUMN IF NOT EXISTS pages_done   int NOT NULL DEFAULT 0,
  ADD COLUMN IF NOT EXISTS pages_failed int NOT NULL DEFAULT 0,
  ADD COLUMN IF NOT EXISTS transactions_imported int,
  ADD COLUMN IF NOT EXISTS duplicates_skipped    int,
  ADD COLUMN IF NOT EXISTS started_at   timestamptz,
  ADD COLUMN IF NOT EXISTS finished_at  timestamptz,
  ADD COLUMN IF NOT EXISTS locked_at    timestamptz;

-- ── Seiten (Stufe 1: Einlesen) ───────────────────────────────────────────────

CREATE TABLE IF NOT EXISTS public.statement_pages (
  id              uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  statement_id    uuid NOT NULL REFERENCES public.bank_statements(id) ON DELETE CASCADE,
  user_id         uuid NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
  page_no         int  NOT NULL,
  status          text NOT NULL DEFAULT 'pending'
                    CHECK (status IN ('pending', 'processing', 'done', 'error')),
  attempts        int  NOT NULL DEFAULT 0,
  next_attempt_at timestamptz NOT NULL DEFAULT now(),
  locked_at       timestamptz,
  lines           jsonb,
  extractor       text,
  model           text,
  tokens_in       int,
  tokens_out      int,
  ms              int,
  error           text,
  created_at      timestamptz NOT NULL DEFAULT now(),
  processed_at    timestamptz,
  UNIQUE (statement_id, page_no)
);
CREATE INDEX IF NOT EXISTS statement_pages_claim_idx
  ON public.statement_pages (status, next_attempt_at, page_no);

ALTER TABLE public.statement_pages ENABLE ROW LEVEL SECURITY;
CREATE POLICY statement_pages_own_select ON public.statement_pages
  FOR SELECT TO authenticated USING (user_id = (SELECT auth.uid()));

-- ── Abgleich-Aufträge (fortlaufend) ──────────────────────────────────────────

CREATE TABLE IF NOT EXISTS public.rematch_queue (
  id           bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  user_id      uuid NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
  receipt_id   uuid REFERENCES public.receipts(id) ON DELETE CASCADE,  -- NULL = alles neu abgleichen
  created_at   timestamptz NOT NULL DEFAULT now(),
  processed_at timestamptz
);
CREATE INDEX IF NOT EXISTS rematch_queue_open_idx ON public.rematch_queue (processed_at, created_at);
ALTER TABLE public.rematch_queue ENABLE ROW LEVEL SECURITY;
CREATE POLICY rematch_queue_own_select ON public.rematch_queue
  FOR SELECT TO authenticated USING (user_id = (SELECT auth.uid()));

-- ── Vorschläge bei Ungereimtheiten ───────────────────────────────────────────

CREATE TABLE IF NOT EXISTS public.match_suggestions (
  id             uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id        uuid NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
  transaction_id uuid NOT NULL REFERENCES public.bank_transactions(id) ON DELETE CASCADE,
  receipt_id     uuid NOT NULL REFERENCES public.receipts(id) ON DELETE CASCADE,
  kind           text NOT NULL CHECK (kind IN ('tip', 'fx', 'discount', 'partial', 'other')),
  diff_amount    numeric(12,2) NOT NULL,
  score          numeric(5,3),
  status         text NOT NULL DEFAULT 'open'
                   CHECK (status IN ('open', 'accepted', 'rejected', 'dismissed')),
  note           text,
  created_at     timestamptz NOT NULL DEFAULT now(),
  resolved_at    timestamptz,
  UNIQUE (transaction_id, receipt_id)
);
CREATE INDEX IF NOT EXISTS match_suggestions_user_open_idx ON public.match_suggestions (user_id, status);
ALTER TABLE public.match_suggestions ENABLE ROW LEVEL SECURITY;
CREATE POLICY match_suggestions_own_select ON public.match_suggestions
  FOR SELECT TO authenticated USING (user_id = (SELECT auth.uid()));
CREATE POLICY match_suggestions_own_update ON public.match_suggestions
  FOR UPDATE TO authenticated USING (user_id = (SELECT auth.uid())) WITH CHECK (user_id = (SELECT auth.uid()));

-- Ausgleichsposten (z. B. Trinkgeld) als Artikel, Original-Total der Quittung bleibt
ALTER TABLE public.receipt_items ADD COLUMN IF NOT EXISTS is_adjustment boolean NOT NULL DEFAULT false;

-- ── Konfiguration (austauschbarer Extractor, Drossel) ────────────────────────

INSERT INTO public.app_settings (key, value) VALUES
  ('statement_extractor',        'auto'),
  ('statement_text_model',       'claude-haiku-4-5-20251001'),
  ('statement_pdf_model',        'claude-sonnet-4-6'),
  ('statement_pages_per_tick',   '6'),
  ('statement_page_concurrency', '3'),
  ('statement_max_attempts',     '3')
ON CONFLICT (key) DO NOTHING;

-- ── Worker-Secret im Vault (Cron → Worker) ───────────────────────────────────

DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM vault.secrets WHERE name = 'statement_worker_secret') THEN
    PERFORM vault.create_secret(encode(extensions.gen_random_bytes(32), 'hex'), 'statement_worker_secret',
                                'Authentifiziert pg_cron/kick-Aufrufe beim statement-worker');
  END IF;
END $$;

-- ── Worker-RPCs (nur service_role) ───────────────────────────────────────────

CREATE OR REPLACE FUNCTION public.worker_secret_matches(p_secret text)
RETURNS boolean LANGUAGE sql STABLE SECURITY DEFINER SET search_path = '' AS $$
  SELECT EXISTS (SELECT 1 FROM vault.decrypted_secrets
                 WHERE name = 'statement_worker_secret' AND decrypted_secret = p_secret);
$$;

-- Auszüge zum Zerlegen in Seiten übernehmen
CREATE OR REPLACE FUNCTION public.claim_queued_statements(p_limit int)
RETURNS SETOF public.bank_statements LANGUAGE sql SECURITY DEFINER SET search_path = '' AS $$
  UPDATE public.bank_statements s
  SET locked_at = now(), started_at = coalesce(s.started_at, now())
  WHERE s.id IN (
    SELECT id FROM public.bank_statements
    WHERE stage = 'queued' AND (locked_at IS NULL OR locked_at < now() - interval '5 minutes')
    ORDER BY created_at
    LIMIT p_limit
    FOR UPDATE SKIP LOCKED
  )
  RETURNING s.*;
$$;

-- Seiten reihum übernehmen: niedrigste Seitennummer zuerst → alle Auszüge kommen voran.
-- Hängengebliebene 'processing'-Seiten (Worker abgestürzt) werden nach 10 Min. wieder frei.
CREATE OR REPLACE FUNCTION public.claim_statement_pages(p_limit int)
RETURNS SETOF public.statement_pages LANGUAGE sql SECURITY DEFINER SET search_path = '' AS $$
  UPDATE public.statement_pages p
  SET status = 'processing', attempts = p.attempts + 1, locked_at = now()
  WHERE p.id IN (
    SELECT id FROM public.statement_pages
    WHERE (status = 'pending' AND next_attempt_at <= now())
       OR (status = 'processing' AND locked_at < now() - interval '10 minutes')
    ORDER BY page_no, created_at
    LIMIT p_limit
    FOR UPDATE SKIP LOCKED
  )
  RETURNING p.*;
$$;

-- Auszug ins Verrechnen überführen, sobald keine Seite mehr offen ist (genau einmal)
CREATE OR REPLACE FUNCTION public.claim_statements_ready(p_limit int)
RETURNS SETOF public.bank_statements LANGUAGE sql SECURITY DEFINER SET search_path = '' AS $$
  UPDATE public.bank_statements s
  SET stage = 'reconciling', locked_at = now()
  WHERE s.id IN (
    SELECT st.id FROM public.bank_statements st
    WHERE (st.stage = 'extracting'
           AND NOT EXISTS (SELECT 1 FROM public.statement_pages p
                           WHERE p.statement_id = st.id AND p.status IN ('pending', 'processing')))
       OR (st.stage = 'reconciling' AND st.locked_at < now() - interval '10 minutes')
    ORDER BY st.created_at
    LIMIT p_limit
    FOR UPDATE SKIP LOCKED
  )
  RETURNING s.*;
$$;

CREATE OR REPLACE FUNCTION public.claim_rematch_users(p_limit int)
RETURNS TABLE (user_id uuid) LANGUAGE sql SECURITY DEFINER SET search_path = '' AS $$
  WITH picked AS (
    UPDATE public.rematch_queue q SET processed_at = now()
    WHERE q.id IN (
      SELECT id FROM public.rematch_queue WHERE processed_at IS NULL
      ORDER BY created_at LIMIT p_limit FOR UPDATE SKIP LOCKED
    )
    RETURNING q.user_id
  )
  SELECT DISTINCT picked.user_id FROM picked;
$$;

REVOKE EXECUTE ON FUNCTION public.worker_secret_matches(text),
  public.claim_queued_statements(int), public.claim_statement_pages(int),
  public.claim_statements_ready(int), public.claim_rematch_users(int)
  FROM PUBLIC, anon, authenticated;
GRANT EXECUTE ON FUNCTION public.worker_secret_matches(text),
  public.claim_queued_statements(int), public.claim_statement_pages(int),
  public.claim_statements_ready(int), public.claim_rematch_users(int)
  TO service_role;

-- ── Worker anstossen (Cron + App) ────────────────────────────────────────────

CREATE OR REPLACE FUNCTION private.call_statement_worker()
RETURNS void LANGUAGE sql SECURITY DEFINER SET search_path = '' AS $$
  SELECT net.http_post(
    url     := 'https://dsucrlaonnmpwopgidcj.supabase.co/functions/v1/statement-worker',
    headers := jsonb_build_object(
      'Content-Type', 'application/json',
      'x-worker-secret', (SELECT decrypted_secret FROM vault.decrypted_secrets WHERE name = 'statement_worker_secret')
    ),
    body    := '{}'::jsonb,
    timeout_milliseconds := 5000
  );
$$;
REVOKE EXECUTE ON FUNCTION private.call_statement_worker() FROM PUBLIC, anon, authenticated;

-- App: nach Upload sofort starten (statt bis zu 1 Min. auf den Cron zu warten)
CREATE OR REPLACE FUNCTION public.kick_statement_worker()
RETURNS void LANGUAGE plpgsql SECURITY DEFINER SET search_path = '' AS $$
BEGIN
  IF (SELECT auth.uid()) IS NULL THEN RAISE EXCEPTION 'not authenticated'; END IF;
  PERFORM private.call_statement_worker();
END $$;

-- App: Abgleich anfordern (nach Quittung speichern: p_receipt_id; "Neu abgleichen": NULL)
CREATE OR REPLACE FUNCTION public.enqueue_rematch(p_receipt_id uuid DEFAULT NULL)
RETURNS void LANGUAGE plpgsql SECURITY DEFINER SET search_path = '' AS $$
DECLARE uid uuid := (SELECT auth.uid());
BEGIN
  IF uid IS NULL THEN RAISE EXCEPTION 'not authenticated'; END IF;
  IF p_receipt_id IS NOT NULL AND NOT EXISTS (
    SELECT 1 FROM public.receipts WHERE id = p_receipt_id AND user_id = uid) THEN
    RAISE EXCEPTION 'receipt not found';
  END IF;
  INSERT INTO public.rematch_queue (user_id, receipt_id) VALUES (uid, p_receipt_id);
  PERFORM private.call_statement_worker();
END $$;

-- App: fehlgeschlagene Seiten erneut versuchen
CREATE OR REPLACE FUNCTION public.retry_statement(p_statement_id uuid)
RETURNS void LANGUAGE plpgsql SECURITY DEFINER SET search_path = '' AS $$
DECLARE uid uuid := (SELECT auth.uid());
BEGIN
  IF NOT EXISTS (SELECT 1 FROM public.bank_statements WHERE id = p_statement_id AND user_id = uid) THEN
    RAISE EXCEPTION 'statement not found';
  END IF;
  UPDATE public.statement_pages SET status = 'pending', attempts = 0, next_attempt_at = now(), error = NULL
  WHERE statement_id = p_statement_id AND status = 'error';
  UPDATE public.bank_statements
  SET stage = CASE WHEN page_count IS NULL THEN 'queued' ELSE 'extracting' END,
      status = 'processing', pages_failed = 0, finished_at = NULL, error_message = NULL, locked_at = NULL
  WHERE id = p_statement_id;
  PERFORM private.call_statement_worker();
END $$;

REVOKE EXECUTE ON FUNCTION public.kick_statement_worker(), public.enqueue_rematch(uuid),
  public.retry_statement(uuid) FROM PUBLIC, anon;
GRANT EXECUTE ON FUNCTION public.kick_statement_worker(), public.enqueue_rematch(uuid),
  public.retry_statement(uuid) TO authenticated;

-- ── Vorschlag annehmen (atomar, läuft mit den Rechten des Users → RLS greift) ─

CREATE OR REPLACE FUNCTION public.accept_match_suggestion(
  p_suggestion_id uuid, p_kind text, p_amount numeric, p_note text DEFAULT NULL)
RETURNS void LANGUAGE plpgsql SECURITY INVOKER SET search_path = '' AS $$
DECLARE
  s public.match_suggestions;
  item_category text;
  label text;
BEGIN
  SELECT * INTO s FROM public.match_suggestions WHERE id = p_suggestion_id AND status = 'open';
  IF NOT FOUND THEN RAISE EXCEPTION 'suggestion not found'; END IF;
  IF p_kind NOT IN ('tip', 'fx', 'discount', 'partial', 'other') THEN RAISE EXCEPTION 'invalid kind'; END IF;

  INSERT INTO public.receipt_matches (user_id, transaction_id, receipt_id, score, match_type)
  VALUES (s.user_id, s.transaction_id, s.receipt_id, coalesce(s.score, 1), 'manual');
  UPDATE public.bank_transactions SET match_status = 'matched' WHERE id = s.transaction_id;

  -- Ausgleichsposten, damit Quittung + Posten = Bank-Betrag (Teilzahlung ohne Posten)
  IF p_kind <> 'partial' AND coalesce(p_amount, 0) <> 0 THEN
    SELECT coalesce(mode() WITHIN GROUP (ORDER BY tags[1]), 'Diverses') INTO item_category
    FROM public.receipt_items WHERE receipt_id = s.receipt_id AND NOT is_adjustment;
    label := CASE p_kind WHEN 'tip' THEN 'Trinkgeld' WHEN 'fx' THEN 'Fremdwährung / Gebühr'
                         WHEN 'discount' THEN 'Rabatt' ELSE coalesce(nullif(p_note, ''), 'Differenz') END;
    INSERT INTO public.receipt_items (receipt_id, name, quantity, unit, unit_price, total_price, tags, is_adjustment)
    VALUES (s.receipt_id, label, 1, 'Stk', p_amount, p_amount,
            ARRAY[CASE WHEN p_kind = 'tip' THEN 'Restaurant & Take-away' ELSE item_category END], true);
  END IF;

  UPDATE public.match_suggestions
  SET status = 'accepted', kind = p_kind, diff_amount = coalesce(p_amount, 0), note = p_note, resolved_at = now()
  WHERE id = s.id;
  -- Konkurrierende Vorschläge für dieselbe Buchung oder Quittung erledigen sich
  UPDATE public.match_suggestions SET status = 'dismissed', resolved_at = now()
  WHERE status = 'open' AND id <> s.id AND (transaction_id = s.transaction_id OR receipt_id = s.receipt_id);
END $$;
REVOKE EXECUTE ON FUNCTION public.accept_match_suggestion(uuid, text, numeric, text) FROM PUBLIC, anon;
GRANT EXECUTE ON FUNCTION public.accept_match_suggestion(uuid, text, numeric, text) TO authenticated;

-- ── Realtime für Fortschrittsanzeige ─────────────────────────────────────────

DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_publication_tables
                 WHERE pubname = 'supabase_realtime' AND tablename = 'bank_statements') THEN
    ALTER PUBLICATION supabase_realtime ADD TABLE public.bank_statements;
  END IF;
  IF NOT EXISTS (SELECT 1 FROM pg_publication_tables
                 WHERE pubname = 'supabase_realtime' AND tablename = 'match_suggestions') THEN
    ALTER PUBLICATION supabase_realtime ADD TABLE public.match_suggestions;
  END IF;
END $$;

-- ── Cron: jede Minute (gleichmässige Last; Drossel im Worker) ────────────────

SELECT cron.schedule('statement-worker', '* * * * *', $$ SELECT private.call_statement_worker() $$);
