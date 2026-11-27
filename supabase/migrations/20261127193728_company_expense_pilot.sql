-- Pilot preparation only. Apply in staging after privacy/retention review, not automatically.
-- Reuse the existing company foundation, but make business writes atomic and auditable.
CREATE SCHEMA IF NOT EXISTS private;
REVOKE ALL ON SCHEMA private FROM PUBLIC, anon;
GRANT USAGE ON SCHEMA private TO authenticated, service_role;

CREATE TABLE public.company_workspace_settings (
  company_id uuid PRIMARY KEY REFERENCES public.companies(id) ON DELETE RESTRICT,
  created_by uuid NOT NULL REFERENCES auth.users(id) ON DELETE RESTRICT,
  currency text NOT NULL CHECK (currency ~ '^[A-Z]{3}$'),
  meal_limit numeric(12,2) CHECK (meal_limit > 0),
  created_at timestamptz NOT NULL DEFAULT now()
);
CREATE TABLE private.company_invites (
  company_id uuid PRIMARY KEY REFERENCES public.companies(id) ON DELETE RESTRICT,
  token_hash text NOT NULL UNIQUE,
  expires_at timestamptz NOT NULL,
  created_by uuid NOT NULL REFERENCES auth.users(id) ON DELETE RESTRICT
);
CREATE TABLE public.company_expense_snapshots (
  submission_id uuid PRIMARY KEY REFERENCES public.expense_submissions(id) ON DELETE RESTRICT,
  company_id uuid NOT NULL REFERENCES public.companies(id) ON DELETE RESTRICT,
  submitter_id uuid NOT NULL REFERENCES auth.users(id) ON DELETE RESTRICT,
  snapshot jsonb NOT NULL,
  snapshot_hash text NOT NULL,
  image_state text NOT NULL DEFAULT 'pending' CHECK (image_state IN ('pending','archived')),
  image_key text,
  image_sha256 text,
  archived_at timestamptz,
  created_at timestamptz NOT NULL DEFAULT now(),
  CHECK ((image_state='pending' AND image_key IS NULL AND image_sha256 IS NULL AND archived_at IS NULL)
      OR (image_state='archived' AND image_key IS NOT NULL AND image_sha256 ~ '^[0-9a-f]{64}$' AND archived_at IS NOT NULL))
);
CREATE UNIQUE INDEX company_expense_image_once ON public.company_expense_snapshots(image_sha256) WHERE image_sha256 IS NOT NULL;
CREATE INDEX company_expense_snapshots_company ON public.company_expense_snapshots(company_id,created_at);
CREATE TABLE public.company_expense_events (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  submission_id uuid NOT NULL REFERENCES public.expense_submissions(id) ON DELETE RESTRICT,
  company_id uuid NOT NULL REFERENCES public.companies(id) ON DELETE RESTRICT,
  actor_id uuid REFERENCES auth.users(id) ON DELETE RESTRICT,
  event_type text NOT NULL CHECK (event_type IN ('submitted','archived','approved','rejected','withdrawn','export_prepared')),
  detail jsonb NOT NULL DEFAULT '{}'::jsonb,
  created_at timestamptz NOT NULL DEFAULT now()
);
CREATE INDEX company_expense_events_submission ON public.company_expense_events(submission_id,created_at,id);
CREATE TABLE public.company_expense_exports (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  submission_id uuid NOT NULL UNIQUE REFERENCES public.expense_submissions(id) ON DELETE RESTRICT,
  company_id uuid NOT NULL REFERENCES public.companies(id) ON DELETE RESTRICT,
  created_by uuid NOT NULL REFERENCES auth.users(id) ON DELETE RESTRICT,
  format text NOT NULL DEFAULT 'piggy-expense-v1' CHECK (format='piggy-expense-v1'),
  payload jsonb NOT NULL,
  created_at timestamptz NOT NULL DEFAULT now()
);
CREATE INDEX company_expense_exports_company ON public.company_expense_exports(company_id);

ALTER TABLE public.expense_submissions DROP CONSTRAINT expense_submissions_status_check;
ALTER TABLE public.expense_submissions ADD CONSTRAINT expense_submissions_status_check
  CHECK (status IN ('pending','approved','rejected','reimbursed','withdrawn'));

-- Existing public clients must not bypass the new workflow or self-mark a reimbursement.
REVOKE INSERT,UPDATE,DELETE ON public.companies,public.company_members,public.expense_submissions FROM PUBLIC,anon,authenticated;
GRANT SELECT ON public.companies,public.company_members,public.expense_submissions TO authenticated;
ALTER TABLE public.company_workspace_settings ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.company_expense_snapshots ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.company_expense_events ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.company_expense_exports ENABLE ROW LEVEL SECURITY;
ALTER TABLE private.company_invites ENABLE ROW LEVEL SECURITY;
REVOKE ALL ON public.company_workspace_settings,public.company_expense_snapshots,public.company_expense_events,public.company_expense_exports,private.company_invites FROM PUBLIC,anon,authenticated;
GRANT SELECT ON public.company_workspace_settings,public.company_expense_snapshots,public.company_expense_events,public.company_expense_exports TO authenticated;
CREATE POLICY company_settings_read ON public.company_workspace_settings FOR SELECT TO authenticated
  USING (private.my_company_role(company_id) IS NOT NULL);
CREATE POLICY company_snapshot_read ON public.company_expense_snapshots FOR SELECT TO authenticated
  USING ((submitter_id=(SELECT auth.uid()) AND private.my_company_role(company_id) IS NOT NULL)
      OR private.my_company_role(company_id) IN ('admin','accountant'));
CREATE POLICY company_events_read ON public.company_expense_events FOR SELECT TO authenticated
  USING (EXISTS(SELECT 1 FROM public.company_expense_snapshots s WHERE s.submission_id=company_expense_events.submission_id));
CREATE POLICY company_exports_read ON public.company_expense_exports FOR SELECT TO authenticated
  USING (private.my_company_role(company_id) IN ('admin','accountant'));

CREATE FUNCTION private.company_immutable() RETURNS trigger LANGUAGE plpgsql SET search_path='' AS $$
BEGIN
  IF TG_OP='DELETE' THEN RAISE EXCEPTION 'Firmenhistorie darf nicht gelöscht werden.' USING ERRCODE='42501'; END IF;
  IF TG_TABLE_NAME='company_expense_snapshots' THEN
    IF NEW.submission_id<>OLD.submission_id OR NEW.company_id<>OLD.company_id OR NEW.submitter_id<>OLD.submitter_id
       OR NEW.snapshot IS DISTINCT FROM OLD.snapshot OR NEW.snapshot_hash<>OLD.snapshot_hash OR NEW.created_at<>OLD.created_at
       OR OLD.image_state='archived' THEN
      RAISE EXCEPTION 'Der eingereichte Belegstand ist unveränderlich.' USING ERRCODE='42501';
    END IF;
    RETURN NEW;
  END IF;
  RAISE EXCEPTION 'Firmenhistorie darf nicht verändert werden.' USING ERRCODE='42501';
END; $$;
REVOKE ALL ON FUNCTION private.company_immutable() FROM PUBLIC,anon,authenticated;
CREATE TRIGGER company_snapshot_immutable BEFORE UPDATE OR DELETE ON public.company_expense_snapshots FOR EACH ROW EXECUTE FUNCTION private.company_immutable();
CREATE TRIGGER company_event_immutable BEFORE UPDATE OR DELETE ON public.company_expense_events FOR EACH ROW EXECUTE FUNCTION private.company_immutable();
CREATE TRIGGER company_export_immutable BEFORE UPDATE OR DELETE ON public.company_expense_exports FOR EACH ROW EXECUTE FUNCTION private.company_immutable();

CREATE FUNCTION private.company_workspace(p_action text,p_input jsonb DEFAULT '{}'::jsonb)
RETURNS jsonb LANGUAGE plpgsql SECURITY DEFINER SET search_path='' AS $$
DECLARE
  uid uuid := auth.uid(); cid uuid; sid uuid; rid uuid; token text; member_role text;
  input_name text; cur text; category text; purpose text; verdict text; next_role text;
  r public.receipts%ROWTYPE; sub public.expense_submissions%ROWTYPE;
  snap public.company_expense_snapshots%ROWTYPE; exp public.company_expense_exports%ROWTYPE;
  data jsonb; result jsonb; target_user uuid; limit_amount numeric;
BEGIN
  IF uid IS NULL THEN RAISE EXCEPTION 'Bitte anmelden.' USING ERRCODE='42501'; END IF;
  IF p_input IS NULL OR jsonb_typeof(p_input)<>'object' OR pg_column_size(p_input)>20000 THEN
    RAISE EXCEPTION 'Ungültige Eingabe.' USING ERRCODE='22023';
  END IF;
  IF p_action='list' THEN
    SELECT coalesce(jsonb_agg(jsonb_build_object('id',c.id,'name',c.name,'role',m.role,'currency',s.currency,'meal_limit',s.meal_limit) ORDER BY c.created_at),'[]'::jsonb)
      INTO result FROM public.companies c JOIN public.company_members m ON m.company_id=c.id
      JOIN public.company_workspace_settings s ON s.company_id=c.id WHERE m.user_id=uid;
    RETURN jsonb_build_object('companies',result);
  ELSIF p_action='create' THEN
    input_name := btrim(p_input->>'name'); cur := upper(coalesce(p_input->>'currency','CHF'));
    IF input_name IS NULL OR length(input_name) NOT BETWEEN 2 AND 100 OR cur !~ '^[A-Z]{3}$' THEN
      RAISE EXCEPTION 'Bitte Firmenname und Währung prüfen.' USING ERRCODE='22023'; END IF;
    cid := gen_random_uuid(); token := replace(gen_random_uuid()::text,'-','');
    IF p_input->>'meal_limit' IS NOT NULL THEN
      limit_amount := (p_input->>'meal_limit')::numeric;
      IF limit_amount<=0 OR limit_amount>100000 OR round(limit_amount,2)<>limit_amount THEN RAISE EXCEPTION 'Ungültige Verpflegungsgrenze.' USING ERRCODE='22023'; END IF;
    END IF;
    INSERT INTO public.companies(id,name,slug) VALUES(cid,input_name,'piggy-'||cid::text);
    INSERT INTO public.company_members(company_id,user_id,role) VALUES(cid,uid,'admin');
    INSERT INTO public.company_workspace_settings(company_id,created_by,currency,meal_limit) VALUES(cid,uid,cur,limit_amount);
    INSERT INTO private.company_invites VALUES(cid,encode(sha256(convert_to(token,'UTF8')),'hex'),now()+interval '7 days',uid);
    RETURN jsonb_build_object('company_id',cid,'invite_code',token);
  ELSIF p_action='join' THEN
    token := lower(btrim(p_input->>'invite_code'));
    IF token IS NULL OR token !~ '^[0-9a-f]{32}$' THEN RAISE EXCEPTION 'Einladung ungültig oder abgelaufen.' USING ERRCODE='22023'; END IF;
    SELECT company_id INTO cid FROM private.company_invites
      WHERE token_hash=encode(sha256(convert_to(token,'UTF8')),'hex') AND expires_at>now();
    IF cid IS NULL THEN RAISE EXCEPTION 'Einladung ungültig oder abgelaufen.' USING ERRCODE='22023'; END IF;
    -- Match the company-before-invite lock order used by rotation. Recheck after locking.
    PERFORM id FROM public.companies WHERE id=cid FOR UPDATE;
    IF NOT EXISTS(SELECT 1 FROM private.company_invites WHERE company_id=cid AND token_hash=encode(sha256(convert_to(token,'UTF8')),'hex') AND expires_at>now()) THEN
      RAISE EXCEPTION 'Einladung ungültig oder abgelaufen.' USING ERRCODE='22023'; END IF;
    INSERT INTO public.company_members(company_id,user_id,role) VALUES(cid,uid,'member') ON CONFLICT(company_id,user_id) DO NOTHING;
    RETURN jsonb_build_object('company_id',cid);
  END IF;

  cid := (p_input->>'company_id')::uuid;
  PERFORM id FROM public.companies WHERE id=cid FOR UPDATE;
  SELECT role INTO member_role FROM public.company_members WHERE company_id=cid AND user_id=uid FOR SHARE;
  IF member_role IS NULL THEN RAISE EXCEPTION 'Kein Zugriff auf diese Firma.' USING ERRCODE='42501'; END IF;
  IF NOT EXISTS(SELECT 1 FROM public.company_workspace_settings WHERE company_id=cid) THEN RAISE EXCEPTION 'Firmenbereich noch nicht eingerichtet.' USING ERRCODE='22023'; END IF;
  IF p_action='workspace' THEN
    SELECT jsonb_build_object(
      'company',jsonb_build_object('id',c.id,'name',c.name,'role',member_role,'currency',s.currency,'meal_limit',s.meal_limit),
      'members',coalesce((SELECT jsonb_agg(jsonb_build_object('id',m.id,'user_id',m.user_id,'role',m.role,'name',coalesce(p.display_name,'Mitglied')) ORDER BY m.joined_at)
        FROM public.company_members m LEFT JOIN public.profiles p ON p.id=m.user_id WHERE m.company_id=cid),'[]'::jsonb),
      'submissions',coalesce((SELECT jsonb_agg(jsonb_build_object('id',e.id,'submitter_id',e.submitter_id,'category',e.category,'note',e.note,'status',e.status,'submitted_at',e.submitted_at,
        'reviewed_by',e.reviewed_by,'reviewed_at',e.reviewed_at,'snapshot',ss.snapshot,'snapshot_hash',ss.snapshot_hash,'image_state',ss.image_state,'image_sha256',ss.image_sha256,
        'export_prepared',EXISTS(SELECT 1 FROM public.company_expense_exports x WHERE x.submission_id=e.id),
        'events',coalesce((SELECT jsonb_agg(jsonb_build_object('id',v.id,'event_type',v.event_type,'actor_id',v.actor_id,'detail',v.detail,'created_at',v.created_at) ORDER BY v.created_at,v.id) FROM public.company_expense_events v WHERE v.submission_id=e.id),'[]'::jsonb)) ORDER BY e.submitted_at DESC)
        FROM public.expense_submissions e JOIN public.company_expense_snapshots ss ON ss.submission_id=e.id
        WHERE e.company_id=cid AND (e.submitter_id=uid OR member_role IN ('admin','accountant'))),'[]'::jsonb))
      INTO result FROM public.companies c JOIN public.company_workspace_settings s ON s.company_id=c.id WHERE c.id=cid;
    IF result IS NULL THEN RAISE EXCEPTION 'Firmenbereich noch nicht eingerichtet.' USING ERRCODE='22023'; END IF;
    RETURN result;
  ELSIF p_action='rotate_invite' THEN
    IF member_role<>'admin' THEN RAISE EXCEPTION 'Nur Firmenadmins erstellen Einladungen.' USING ERRCODE='42501'; END IF;
    token := replace(gen_random_uuid()::text,'-','');
    UPDATE private.company_invites SET token_hash=encode(sha256(convert_to(token,'UTF8')),'hex'),expires_at=now()+interval '7 days',created_by=uid WHERE company_id=cid;
    RETURN jsonb_build_object('company_id',cid,'invite_code',token);
  ELSIF p_action='set_role' THEN
    IF member_role<>'admin' THEN RAISE EXCEPTION 'Nur Firmenadmins ändern Rollen.' USING ERRCODE='42501'; END IF;
    target_user := (p_input->>'user_id')::uuid; next_role := p_input->>'role';
    IF next_role IS NULL OR next_role NOT IN ('admin','accountant','member') THEN RAISE EXCEPTION 'Ungültige Rolle.' USING ERRCODE='22023'; END IF;
    PERFORM id FROM public.companies WHERE id=cid FOR UPDATE;
    IF next_role<>'admin' AND NOT EXISTS(SELECT 1 FROM public.company_members WHERE company_id=cid AND role='admin' AND user_id<>target_user) THEN
      RAISE EXCEPTION 'Mindestens ein Firmenadmin muss erhalten bleiben.' USING ERRCODE='22023'; END IF;
    UPDATE public.company_members SET role=next_role WHERE company_id=cid AND user_id=target_user;
    IF NOT FOUND THEN RAISE EXCEPTION 'Mitglied nicht gefunden.' USING ERRCODE='22023'; END IF;
    RETURN jsonb_build_object('ok',true);
  ELSIF p_action='submit' THEN
    rid := (p_input->>'receipt_id')::uuid; category := p_input->>'category'; purpose := btrim(p_input->>'note');
    IF category IS NULL OR category NOT IN ('meal','transport','accommodation','material','other') OR purpose IS NULL OR length(purpose) NOT BETWEEN 3 AND 1000 THEN
      RAISE EXCEPTION 'Bitte Spesenart und geschäftlichen Grund angeben.' USING ERRCODE='22023'; END IF;
    SELECT * INTO r FROM public.receipts WHERE id=rid AND user_id=uid FOR UPDATE;
    IF NOT FOUND THEN RAISE EXCEPTION 'Eigener Beleg nicht gefunden.' USING ERRCODE='42501'; END IF;
    SELECT id INTO sid FROM public.expense_submissions WHERE company_id=cid AND receipt_id=rid AND submitter_id=uid;
    IF sid IS NOT NULL THEN RETURN jsonb_build_object('submission_id',sid,'existing',true); END IF;
    IF EXISTS(SELECT 1 FROM public.expense_submissions WHERE receipt_id=rid AND status IN ('pending','approved','reimbursed')) THEN
      RAISE EXCEPTION 'Dieser Beleg wurde bereits bei einer Firma eingereicht.' USING ERRCODE='22023'; END IF;
    IF EXISTS(SELECT 1 FROM public.splits WHERE receipt_id=rid) THEN RAISE EXCEPTION 'Bereits geteilte Belege zuerst klären.' USING ERRCODE='22023'; END IF;
    SELECT currency INTO cur FROM public.company_workspace_settings WHERE company_id=cid;
    IF r.receipt_date IS NULL OR r.total_amount IS NULL OR r.total_amount<=0 OR r.total_amount>1000000 OR round(r.total_amount,2)<>r.total_amount OR r.currency IS DISTINCT FROM cur OR coalesce(r.image_url,r.original_image_url,'')='' THEN
      RAISE EXCEPTION 'Originalbild, Datum, positiver Betrag mit zwei Dezimalstellen und Firmenwährung erforderlich.' USING ERRCODE='22023'; END IF;
    data := jsonb_build_object('receipt_id',r.id,'store_name',r.store_name,'receipt_date',r.receipt_date,'total_amount',r.total_amount,'currency',r.currency,
      'payment_method',r.payment_method,'payment_card',r.payment_card,'extra_fields',r.extra_fields,'raw_ocr_text',r.raw_ocr_text,
      'image_url',coalesce(r.image_url,r.original_image_url),
      'receipt_items',coalesce((SELECT jsonb_agg(jsonb_build_object('name',i.name,'quantity',i.quantity,'unit',i.unit,'unit_price',i.unit_price,'total_price',i.total_price,'tags',i.tags,'subcategory',i.subcategory) ORDER BY i.id) FROM public.receipt_items i WHERE i.receipt_id=rid),'[]'::jsonb));
    INSERT INTO public.expense_submissions(receipt_id,company_id,submitter_id,category,note) VALUES(rid,cid,uid,category,purpose) RETURNING id INTO sid;
    INSERT INTO public.company_expense_snapshots(submission_id,company_id,submitter_id,snapshot,snapshot_hash)
      VALUES(sid,cid,uid,data,encode(sha256(convert_to(data::text,'UTF8')),'hex'));
    INSERT INTO public.company_expense_events(submission_id,company_id,actor_id,event_type,detail) VALUES(sid,cid,uid,'submitted',jsonb_build_object('category',category,'note',purpose));
    RETURN jsonb_build_object('submission_id',sid,'existing',false);
  ELSIF p_action IN ('review','withdraw','prepare_export') THEN
    sid := (p_input->>'submission_id')::uuid;
    SELECT * INTO sub FROM public.expense_submissions WHERE id=sid AND company_id=cid FOR UPDATE;
    IF NOT FOUND THEN RAISE EXCEPTION 'Einreichung nicht gefunden.' USING ERRCODE='42501'; END IF;
    SELECT * INTO snap FROM public.company_expense_snapshots WHERE submission_id=sid;
    IF NOT FOUND THEN RAISE EXCEPTION 'Einreichung besitzt keinen geprüften Belegstand.' USING ERRCODE='22023'; END IF;
    IF p_action='withdraw' THEN
      IF sub.submitter_id<>uid OR sub.status<>'pending' THEN RAISE EXCEPTION 'Nur eigene offene Einreichungen können zurückgezogen werden.' USING ERRCODE='42501'; END IF;
      UPDATE public.expense_submissions SET status='withdrawn' WHERE id=sid;
      INSERT INTO public.company_expense_events(submission_id,company_id,actor_id,event_type) VALUES(sid,cid,uid,'withdrawn');
    ELSIF p_action='review' THEN
      verdict := p_input->>'decision'; purpose := btrim(coalesce(p_input->>'note',''));
      IF member_role NOT IN ('admin','accountant') OR sub.submitter_id=uid THEN RAISE EXCEPTION 'Eine andere berechtigte Person muss die Spesen prüfen.' USING ERRCODE='42501'; END IF;
      IF sub.status<>'pending' OR verdict IS NULL OR verdict NOT IN ('approved','rejected') THEN RAISE EXCEPTION 'Einreichung wurde bereits entschieden oder Auswahl ist ungültig.' USING ERRCODE='22023'; END IF;
      IF length(purpose)>1000 OR (verdict='rejected' AND length(purpose)<3) THEN RAISE EXCEPTION 'Bitte einen kurzen Ablehnungsgrund angeben.' USING ERRCODE='22023'; END IF;
      IF verdict='approved' THEN
        IF snap.image_state<>'archived' THEN RAISE EXCEPTION 'Originalbeleg zuerst vollständig archivieren.' USING ERRCODE='22023'; END IF;
        SELECT meal_limit INTO limit_amount FROM public.company_workspace_settings WHERE company_id=cid;
        IF sub.category='meal' AND limit_amount IS NOT NULL AND (snap.snapshot->>'total_amount')::numeric>limit_amount THEN
          RAISE EXCEPTION 'Verpflegungsgrenze überschritten. Keine automatische Teilzahlung.' USING ERRCODE='22023'; END IF;
      END IF;
      UPDATE public.expense_submissions SET status=verdict,reviewed_by=uid,reviewed_at=now() WHERE id=sid;
      INSERT INTO public.company_expense_events(submission_id,company_id,actor_id,event_type,detail) VALUES(sid,cid,uid,verdict,jsonb_build_object('note',purpose));
    ELSE
      IF member_role NOT IN ('admin','accountant') OR sub.status<>'approved' OR snap.image_state<>'archived' THEN RAISE EXCEPTION 'Nur freigegebene archivierte Spesen können vorbereitet werden.' USING ERRCODE='42501'; END IF;
      SELECT * INTO exp FROM public.company_expense_exports WHERE submission_id=sid;
      IF NOT FOUND THEN
        data := jsonb_build_object('schema','piggy-expense-v1','company_id',cid,'submission_id',sid,'submitter_id',sub.submitter_id,
          'category',sub.category,'business_purpose',sub.note,'reviewed_by',sub.reviewed_by,'reviewed_at',sub.reviewed_at,
          'canonical_snapshot',snap.snapshot::text,'snapshot_sha256',snap.snapshot_hash,'image_key',snap.image_key,'image_sha256',snap.image_sha256,
          'abacus_transfer_confirmed',false,'payment_confirmed',false);
        INSERT INTO public.company_expense_exports(submission_id,company_id,created_by,payload) VALUES(sid,cid,uid,data) RETURNING * INTO exp;
        INSERT INTO public.company_expense_events(submission_id,company_id,actor_id,event_type,detail) VALUES(sid,cid,uid,'export_prepared',jsonb_build_object('export_id',exp.id));
      END IF;
      RETURN jsonb_build_object('export_id',exp.id,'payload',exp.payload);
    END IF;
    RETURN jsonb_build_object('ok',true);
  END IF;
  RAISE EXCEPTION 'Unbekannte Firmenaktion.' USING ERRCODE='22023';
END; $$;
REVOKE ALL ON FUNCTION private.company_workspace(text,jsonb) FROM PUBLIC,anon;
GRANT EXECUTE ON FUNCTION private.company_workspace(text,jsonb) TO authenticated;
CREATE FUNCTION public.company_workspace(p_action text,p_input jsonb DEFAULT '{}'::jsonb)
RETURNS jsonb LANGUAGE sql SECURITY INVOKER SET search_path='' AS $$ SELECT private.company_workspace(p_action,p_input); $$;
REVOKE ALL ON FUNCTION public.company_workspace(text,jsonb) FROM PUBLIC,anon;
GRANT EXECUTE ON FUNCTION public.company_workspace(text,jsonb) TO authenticated;

-- Trusted archiver only. No authenticated client can attest its own evidence or payment.
CREATE FUNCTION private.company_archive_complete(p_submission_id uuid,p_image_key text,p_sha256 text)
RETURNS boolean LANGUAGE plpgsql SECURITY DEFINER SET search_path='' AS $$
DECLARE s public.company_expense_snapshots%ROWTYPE;
BEGIN
  PERFORM id FROM public.expense_submissions WHERE id=p_submission_id FOR UPDATE;
  SELECT * INTO s FROM public.company_expense_snapshots WHERE submission_id=p_submission_id FOR UPDATE;
  IF NOT FOUND OR p_sha256 IS NULL OR p_sha256 !~ '^[0-9a-f]{64}$' OR p_image_key IS DISTINCT FROM 'company-receipts/'||s.company_id::text||'/'||s.submission_id::text||'/'||p_sha256 THEN
    RAISE EXCEPTION 'Ungültige Archivbestätigung.' USING ERRCODE='22023'; END IF;
  IF s.image_state='archived' THEN
    IF s.image_key=p_image_key AND s.image_sha256=p_sha256 THEN RETURN true; END IF;
    RAISE EXCEPTION 'Originalbeleg wurde bereits anders archiviert.' USING ERRCODE='22023';
  END IF;
  IF EXISTS(SELECT 1 FROM public.company_expense_snapshots WHERE image_sha256=p_sha256 AND submission_id<>p_submission_id) THEN
    RAISE EXCEPTION 'Dieses Originalbild ist bereits eingereicht.' USING ERRCODE='22023'; END IF;
  UPDATE public.company_expense_snapshots SET image_state='archived',image_key=p_image_key,image_sha256=p_sha256,archived_at=now() WHERE submission_id=p_submission_id;
  INSERT INTO public.company_expense_events(submission_id,company_id,actor_id,event_type,detail) VALUES(p_submission_id,s.company_id,NULL,'archived',jsonb_build_object('sha256',p_sha256));
  RETURN true;
END; $$;
REVOKE ALL ON FUNCTION private.company_archive_complete(uuid,text,text) FROM PUBLIC,anon,authenticated;
GRANT EXECUTE ON FUNCTION private.company_archive_complete(uuid,text,text) TO service_role;
CREATE FUNCTION public.company_archive_complete(p_submission_id uuid,p_image_key text,p_sha256 text)
RETURNS boolean LANGUAGE sql SECURITY INVOKER SET search_path='' AS $$ SELECT private.company_archive_complete(p_submission_id,p_image_key,p_sha256); $$;
REVOKE ALL ON FUNCTION public.company_archive_complete(uuid,text,text) FROM PUBLIC,anon,authenticated;
GRANT EXECUTE ON FUNCTION public.company_archive_complete(uuid,text,text) TO service_role;

GRANT SELECT ON public.company_expense_snapshots,public.company_members,public.expense_submissions,public.company_workspace_settings TO service_role;
