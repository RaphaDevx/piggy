-- Piggy Plus: Berechtigungen aus dem Abo (RevenueCat-Webhook → Edge Function revenuecat-webhook).
-- Nur eigene Zeile lesbar, geschrieben wird ausschliesslich mit dem service_role-Schlüssel
-- (Webhook). Löschung mit dem Konto (CASCADE). Leistung von Plus: höheres Tageslimit für KI-Scans.
-- NICHT anwenden, bevor RevenueCat-Konto, App-Store-Produkt und Webhook-Secret eingerichtet sind.
-- Idempotent: mehrfaches Anwenden ist unschädlich.

CREATE TABLE IF NOT EXISTS public.user_entitlements (
  user_id     uuid        PRIMARY KEY REFERENCES auth.users(id) ON DELETE CASCADE,
  plus        boolean     NOT NULL DEFAULT false,
  expires_at  timestamptz,
  product_id  text,
  environment text,
  updated_at  timestamptz NOT NULL DEFAULT now()
);

ALTER TABLE public.user_entitlements ENABLE ROW LEVEL SECURITY;

-- Kein Schreibzugriff für Clients, nur Lesen der eigenen Zeile.
REVOKE ALL ON public.user_entitlements FROM PUBLIC, anon, authenticated;
GRANT SELECT ON public.user_entitlements TO authenticated;
GRANT ALL ON public.user_entitlements TO service_role;

DROP POLICY IF EXISTS user_entitlements_select_own ON public.user_entitlements;
CREATE POLICY user_entitlements_select_own ON public.user_entitlements
  FOR SELECT TO authenticated USING ((SELECT auth.uid()) = user_id);

-- Tageslimit KI-Scans für Plus (Gratis: free_scans_limit)
INSERT INTO public.app_settings (key, value) VALUES
  ('plus_scans_limit', '100')
ON CONFLICT (key) DO NOTHING;
