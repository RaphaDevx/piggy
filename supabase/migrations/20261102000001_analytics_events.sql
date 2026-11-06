-- Produkt-Analytics Ebene A (docs/ANALYTICS-KONZEPT.md): Ereignisse ohne Inhalte.
-- Nur eigene Zeilen schreibbar, kein Lesezugriff für Nutzer. Auswertung nur über
-- analytics_summary() (service_role, K-Dev Dashboard). Löschung mit dem Konto (CASCADE).
-- NICHT anwenden, bevor Kaufmann-Legal geprüft hat und FEATURES.analytics in der App true ist.
-- Idempotent: mehrfaches Anwenden ist unschädlich.

CREATE TABLE IF NOT EXISTS public.analytics_events (
  id          bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  user_id     uuid        NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
  event       text        NOT NULL CHECK (event ~ '^[a-z][a-z0-9_]{2,40}$'),
  props       jsonb       NOT NULL DEFAULT '{}'::jsonb CHECK (pg_column_size(props) <= 512),
  app_version text        CHECK (length(app_version) <= 20),
  os          text        CHECK (length(os) <= 20),
  region      text        CHECK (region ~ '^[A-Z]{2}$'),
  created_at  timestamptz NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS analytics_events_event_created_idx ON public.analytics_events (event, created_at);
CREATE INDEX IF NOT EXISTS analytics_events_created_idx ON public.analytics_events (created_at);

ALTER TABLE public.analytics_events ENABLE ROW LEVEL SECURITY;
REVOKE ALL ON public.analytics_events FROM PUBLIC, anon, authenticated;
GRANT INSERT ON public.analytics_events TO authenticated;

DROP POLICY IF EXISTS analytics_events_insert_own ON public.analytics_events;
CREATE POLICY analytics_events_insert_own ON public.analytics_events
  FOR INSERT TO authenticated WITH CHECK (user_id = auth.uid());

-- Aufbewahrung: 13 Monate. Aufruf z. B. über pg_cron (täglich), sonst von Hand.
CREATE OR REPLACE FUNCTION public.analytics_purge()
RETURNS bigint LANGUAGE plpgsql SECURITY DEFINER SET search_path = '' AS $$
DECLARE n bigint;
BEGIN
  DELETE FROM public.analytics_events WHERE created_at < now() - interval '13 months';
  GET DIAGNOSTICS n = ROW_COUNT;
  RETURN n;
END $$;
REVOKE EXECUTE ON FUNCTION public.analytics_purge() FROM PUBLIC, anon, authenticated;
GRANT EXECUTE ON FUNCTION public.analytics_purge() TO service_role;

-- Auswertung für das Dashboard: nur Zähler, keine Einzelzeilen, keine Nutzer-IDs.
CREATE OR REPLACE FUNCTION public.analytics_summary(p_days int DEFAULT 30)
RETURNS jsonb LANGUAGE sql STABLE SECURITY DEFINER SET search_path = '' AS $$
  WITH w AS (
    SELECT * FROM public.analytics_events
    WHERE created_at >= now() - make_interval(days => greatest(1, least(coalesce(p_days, 30), 400)))
  )
  SELECT jsonb_build_object(
    'days', greatest(1, least(coalesce(p_days, 30), 400)),
    'users', (SELECT count(DISTINCT user_id) FROM w),
    'events', (SELECT count(*) FROM w),
    'by_event', coalesce((SELECT jsonb_agg(jsonb_build_object('event', event, 'count', c, 'users', u) ORDER BY c DESC)
                          FROM (SELECT event, count(*) c, count(DISTINCT user_id) u FROM w GROUP BY event) t), '[]'::jsonb),
    'daily', coalesce((SELECT jsonb_agg(jsonb_build_object('day', d, 'users', u, 'events', c) ORDER BY d)
                       FROM (SELECT created_at::date d, count(DISTINCT user_id) u, count(*) c FROM w GROUP BY 1) t), '[]'::jsonb),
    'versions', coalesce((SELECT jsonb_agg(jsonb_build_object('version', v, 'users', u) ORDER BY u DESC)
                          FROM (SELECT coalesce(app_version, '?') v, count(DISTINCT user_id) u FROM w GROUP BY 1) t), '[]'::jsonb),
    'funnel', jsonb_build_object(
      'app_open',      (SELECT count(DISTINCT user_id) FROM w WHERE event = 'app_open'),
      'scan_started',  (SELECT count(DISTINCT user_id) FROM w WHERE event = 'scan_started'),
      'scan_saved',    (SELECT count(DISTINCT user_id) FROM w WHERE event = 'scan_saved'),
      'split_created', (SELECT count(DISTINCT user_id) FROM w WHERE event = 'split_created')
    )
  );
$$;
REVOKE EXECUTE ON FUNCTION public.analytics_summary(int) FROM PUBLIC, anon, authenticated;
GRANT EXECUTE ON FUNCTION public.analytics_summary(int) TO service_role;
