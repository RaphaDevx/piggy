-- Security hardening before App Store release (audit 2026-09-29).
--
-- 1. profiles were readable by anon incl. gemini_api_key → key moves to
--    user_settings (own-row RLS), profiles readable by signed-in users only.
-- 2. Several policies compared an unqualified column inside EXISTS, which
--    resolves to the inner table → always true (e.g. anyone could join any
--    company as admin; one accepted share exposed all receipt_items).
--    Membership checks move into SECURITY DEFINER helpers in a non-exposed
--    schema, which also avoids policy recursion.
-- 3. check_and_increment_scan was callable by anon for any user → service_role only.
-- 4. Fixed search_path on all public functions.

-- ── 1. BYOK Gemini key → user_settings ───────────────────────────────────────

ALTER TABLE public.user_settings ADD COLUMN IF NOT EXISTS gemini_api_key text;

INSERT INTO public.user_settings (user_id, gemini_api_key)
SELECT id, gemini_api_key FROM public.profiles
WHERE coalesce(trim(gemini_api_key), '') <> ''
ON CONFLICT (user_id) DO UPDATE SET gemini_api_key = EXCLUDED.gemini_api_key;

ALTER TABLE public.profiles DROP COLUMN gemini_api_key;

DROP POLICY profiles_select_all ON public.profiles;
CREATE POLICY profiles_select_authenticated ON public.profiles
  FOR SELECT TO authenticated USING (true);

-- ── 2. Helpers for membership checks ─────────────────────────────────────────

CREATE SCHEMA IF NOT EXISTS private;
REVOKE ALL ON SCHEMA private FROM PUBLIC, anon;
GRANT USAGE ON SCHEMA private TO authenticated;

CREATE OR REPLACE FUNCTION private.my_company_role(cid uuid)
RETURNS text LANGUAGE sql STABLE SECURITY DEFINER SET search_path = '' AS $$
  SELECT role FROM public.company_members
  WHERE company_id = cid AND user_id = (SELECT auth.uid());
$$;

CREATE OR REPLACE FUNCTION private.company_has_members(cid uuid)
RETURNS boolean LANGUAGE sql STABLE SECURITY DEFINER SET search_path = '' AS $$
  SELECT EXISTS (SELECT 1 FROM public.company_members WHERE company_id = cid);
$$;

CREATE OR REPLACE FUNCTION private.is_split_participant(sid uuid)
RETURNS boolean LANGUAGE sql STABLE SECURITY DEFINER SET search_path = '' AS $$
  SELECT EXISTS (
    SELECT 1 FROM public.split_participants
    WHERE split_id = sid AND user_id = (SELECT auth.uid())
  );
$$;

CREATE OR REPLACE FUNCTION private.is_split_payer(sid uuid)
RETURNS boolean LANGUAGE sql STABLE SECURITY DEFINER SET search_path = '' AS $$
  SELECT EXISTS (
    SELECT 1 FROM public.splits
    WHERE id = sid AND payer_id = (SELECT auth.uid())
  );
$$;

REVOKE EXECUTE ON ALL FUNCTIONS IN SCHEMA private FROM PUBLIC, anon;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA private TO authenticated;

-- companies
DROP POLICY companies_select ON public.companies;
CREATE POLICY companies_select ON public.companies FOR SELECT TO authenticated
  USING (private.my_company_role(id) IS NOT NULL);

DROP POLICY companies_update ON public.companies;
CREATE POLICY companies_update ON public.companies FOR UPDATE TO authenticated
  USING (private.my_company_role(id) = 'admin');

DROP POLICY companies_insert ON public.companies;
CREATE POLICY companies_insert ON public.companies FOR INSERT TO authenticated
  WITH CHECK (true);

-- company_members: admins manage members; the creator may add themselves as
-- first admin of a company without members. Joining via invite code needs an RPC.
DROP POLICY cm_select ON public.company_members;
CREATE POLICY cm_select ON public.company_members FOR SELECT TO authenticated
  USING (user_id = (SELECT auth.uid()) OR private.my_company_role(company_id) IS NOT NULL);

DROP POLICY cm_insert ON public.company_members;
CREATE POLICY cm_insert ON public.company_members FOR INSERT TO authenticated
  WITH CHECK (
    private.my_company_role(company_id) = 'admin'
    OR (user_id = (SELECT auth.uid()) AND role = 'admin' AND NOT private.company_has_members(company_id))
  );

DROP POLICY cm_delete ON public.company_members;
CREATE POLICY cm_delete ON public.company_members FOR DELETE TO authenticated
  USING (user_id = (SELECT auth.uid()) OR private.my_company_role(company_id) = 'admin');

-- expense_submissions
DROP POLICY es_select ON public.expense_submissions;
CREATE POLICY es_select ON public.expense_submissions FOR SELECT TO authenticated
  USING (
    submitter_id = (SELECT auth.uid())
    OR private.my_company_role(company_id) IN ('admin', 'accountant')
  );

DROP POLICY es_insert ON public.expense_submissions;
CREATE POLICY es_insert ON public.expense_submissions FOR INSERT TO authenticated
  WITH CHECK (
    submitter_id = (SELECT auth.uid())
    AND private.my_company_role(company_id) IS NOT NULL
  );

DROP POLICY es_update ON public.expense_submissions;
CREATE POLICY es_update ON public.expense_submissions FOR UPDATE TO authenticated
  USING (
    (submitter_id = (SELECT auth.uid()) AND status = 'pending')
    OR private.my_company_role(company_id) IN ('admin', 'accountant')
  );

-- receipt_items shared with me
DROP POLICY receipt_items_shared_select ON public.receipt_items;
CREATE POLICY receipt_items_shared_select ON public.receipt_items FOR SELECT TO authenticated
  USING (EXISTS (
    SELECT 1 FROM public.shared_receipts sr
    WHERE sr.receipt_id = receipt_items.receipt_id
      AND sr.recipient_id = (SELECT auth.uid())
      AND sr.status = 'accepted'
  ));

-- splits / participants / exclusions (mutual references → helpers)
DROP POLICY splits_select ON public.splits;
CREATE POLICY splits_select ON public.splits FOR SELECT TO authenticated
  USING (payer_id = (SELECT auth.uid()) OR private.is_split_participant(id));

DROP POLICY splits_shared_insert ON public.splits;
CREATE POLICY splits_shared_insert ON public.splits FOR INSERT TO authenticated
  WITH CHECK (
    payer_id = (SELECT auth.uid())
    AND EXISTS (
      SELECT 1 FROM public.shared_receipts sr
      WHERE sr.receipt_id = splits.receipt_id
        AND sr.recipient_id = (SELECT auth.uid())
        AND sr.status = 'accepted'
    )
  );

DROP POLICY sp_select ON public.split_participants;
CREATE POLICY sp_select ON public.split_participants FOR SELECT TO authenticated
  USING (user_id = (SELECT auth.uid()) OR private.is_split_payer(split_id));

DROP POLICY sp_insert ON public.split_participants;
CREATE POLICY sp_insert ON public.split_participants FOR INSERT TO authenticated
  WITH CHECK (private.is_split_payer(split_id));

DROP POLICY sp_update ON public.split_participants;
CREATE POLICY sp_update ON public.split_participants FOR UPDATE TO authenticated
  USING (user_id = (SELECT auth.uid()) OR private.is_split_payer(split_id));

DROP POLICY se_select ON public.split_exclusions;
CREATE POLICY se_select ON public.split_exclusions FOR SELECT TO authenticated
  USING (user_id = (SELECT auth.uid()) OR private.is_split_payer(split_id));

DROP POLICY se_insert ON public.split_exclusions;
CREATE POLICY se_insert ON public.split_exclusions FOR INSERT TO authenticated
  WITH CHECK (private.is_split_payer(split_id));

-- ── 3. Function privileges ───────────────────────────────────────────────────

REVOKE EXECUTE ON FUNCTION public.check_and_increment_scan(uuid, integer) FROM PUBLIC, anon, authenticated;
GRANT EXECUTE ON FUNCTION public.check_and_increment_scan(uuid, integer) TO service_role;

REVOKE EXECUTE ON FUNCTION public.handle_new_user() FROM PUBLIC, anon, authenticated;
REVOKE EXECUTE ON FUNCTION public.get_my_project_ids() FROM PUBLIC, anon;
GRANT EXECUTE ON FUNCTION public.get_my_project_ids() TO authenticated;

-- ── 4. search_path ───────────────────────────────────────────────────────────

ALTER FUNCTION public.check_and_increment_scan(uuid, integer) SET search_path = '';
ALTER FUNCTION public.get_my_project_ids() SET search_path = public;
ALTER FUNCTION public.add_project_creator_as_member() SET search_path = public;
ALTER FUNCTION public.handle_updated_at() SET search_path = public;
