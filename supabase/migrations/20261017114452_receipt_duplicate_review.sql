-- Explicit, atomic duplicate review. No automatic matching/merge and no production data backfill.
CREATE TABLE public.receipt_duplicate_decisions (
  user_id uuid NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
  receipt_a uuid NOT NULL REFERENCES public.receipts(id) ON DELETE CASCADE,
  receipt_b uuid NOT NULL REFERENCES public.receipts(id) ON DELETE CASCADE,
  created_at timestamptz NOT NULL DEFAULT now(),
  PRIMARY KEY (user_id, receipt_a, receipt_b),
  CHECK (receipt_a < receipt_b)
);
ALTER TABLE public.receipt_duplicate_decisions ENABLE ROW LEVEL SECURITY;
CREATE POLICY receipt_duplicate_decisions_select ON public.receipt_duplicate_decisions
  FOR SELECT TO authenticated USING (user_id = (SELECT auth.uid()));
GRANT SELECT ON public.receipt_duplicate_decisions TO authenticated;

-- Immutable audit is intentionally private: arbitrary snapshots must not be client-writable.
CREATE TABLE private.receipt_merge_history (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id uuid NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
  target_id uuid NOT NULL,
  source_id uuid,
  target_before jsonb NOT NULL,
  source_before jsonb NOT NULL,
  choices jsonb NOT NULL,
  relationships jsonb NOT NULL,
  created_at timestamptz NOT NULL DEFAULT now()
);
ALTER TABLE private.receipt_merge_history ENABLE ROW LEVEL SECURITY;
CREATE INDEX receipt_merge_history_user_idx ON private.receipt_merge_history(user_id, created_at);
REVOKE ALL ON private.receipt_merge_history FROM PUBLIC, anon, authenticated;

CREATE OR REPLACE FUNCTION private.keep_receipts_separate(p_receipt_a uuid, p_receipt_b uuid)
RETURNS void LANGUAGE plpgsql SECURITY DEFINER SET search_path = '' AS $$
DECLARE uid uuid := auth.uid();
BEGIN
  IF uid IS NULL OR p_receipt_a IS NULL OR p_receipt_b IS NULL OR p_receipt_a = p_receipt_b THEN
    RAISE EXCEPTION 'Bitte zwei eigene Quittungen auswählen.' USING ERRCODE = '22023';
  END IF;
  PERFORM id FROM public.receipts WHERE id IN (p_receipt_a, p_receipt_b) ORDER BY id FOR UPDATE;
  IF (SELECT count(*) FROM public.receipts WHERE id IN (p_receipt_a, p_receipt_b) AND user_id = uid) <> 2 THEN
    RAISE EXCEPTION 'Quittung nicht gefunden oder nicht im eigenen Besitz.' USING ERRCODE = '42501';
  END IF;
  INSERT INTO public.receipt_duplicate_decisions(user_id, receipt_a, receipt_b)
    VALUES(uid, least(p_receipt_a, p_receipt_b), greatest(p_receipt_a, p_receipt_b)) ON CONFLICT DO NOTHING;
END;
$$;

-- Privilege is necessary ONLY to move dependent rows whose RLS deliberately disallows
-- client updates (splits, shares, suggestions, worker queue). All entry points verify
-- auth.uid() and BOTH receipt owners, lock IDs in stable order, and whitelist input.
CREATE OR REPLACE FUNCTION private.merge_receipt_review(
  p_target_id uuid, p_source_id uuid, p_scan jsonb, p_choices jsonb
) RETURNS uuid LANGUAGE plpgsql SECURITY DEFINER SET search_path = '' AS $$
DECLARE
  uid uuid := auth.uid();
  target public.receipts%ROWTYPE;
  source public.receipts%ROWTYPE;
  target_json jsonb;
  source_json jsonb;
  merged jsonb;
  item_json jsonb;
  field text;
  choice text;
  use_source_items boolean;
  source_items jsonb;
  target_items jsonb;
  extras jsonb;
  rels jsonb := '{}'::jsonb;
  tab text;
  foreign_ref record;
  has_split boolean;
  has_bank boolean;
BEGIN
  IF uid IS NULL THEN RAISE EXCEPTION 'Bitte zuerst anmelden.' USING ERRCODE = '42501'; END IF;
  IF p_target_id IS NULL OR (p_source_id IS NULL AND p_scan IS NULL) OR p_target_id = p_source_id THEN
    RAISE EXCEPTION 'Ungültige Quittungsauswahl.' USING ERRCODE = '22023';
  END IF;
  IF p_choices IS NULL OR jsonb_typeof(p_choices) <> 'object' OR
     EXISTS (SELECT 1 FROM jsonb_object_keys(p_choices) k WHERE k NOT IN ('fields','image','items','extras')) OR
     (p_choices ? 'fields' AND jsonb_typeof(p_choices->'fields') <> 'object') OR (p_choices ? 'extras' AND jsonb_typeof(p_choices->'extras') <> 'object') THEN
    RAISE EXCEPTION 'Ungültige Auswahl.' USING ERRCODE = '22023';
  END IF;
  FOR field, choice IN SELECT key, value FROM jsonb_each_text(coalesce(p_choices->'fields', '{}'::jsonb)) LOOP
    IF field NOT IN ('receipt_date','store_name','store_category','total_amount','currency','payment_method','payment_card','notes','raw_ocr_text','markdown_content') OR choice NOT IN ('target','source') OR choice IS NULL THEN
      RAISE EXCEPTION 'Ungültige Feldauswahl.' USING ERRCODE = '22023';
    END IF;
  END LOOP;
  FOR field, choice IN SELECT key, value FROM jsonb_each_text(coalesce(p_choices->'extras', '{}'::jsonb)) LOOP
    IF field NOT IN ('cashier_number','receipt_number','vat_number','store_address','store_email','store_phone','store_website') OR choice NOT IN ('target','source') OR choice IS NULL THEN
      RAISE EXCEPTION 'Ungültige Auswahl der Zusatzangaben.' USING ERRCODE = '22023';
    END IF;
  END LOOP;
  IF (p_choices ? 'image' AND coalesce(p_choices->>'image','') NOT IN ('target','source')) OR
     (p_choices ? 'items' AND coalesce(p_choices->>'items','') NOT IN ('target','source')) THEN
    RAISE EXCEPTION 'Ungültige Bild- oder Artikelauswahl.' USING ERRCODE = '22023';
  END IF;

  -- FOR UPDATE also prevents concurrent FK inserts against a soon-to-be-removed source.
  PERFORM id FROM public.receipts WHERE id IN (p_target_id, p_source_id) ORDER BY id FOR UPDATE;
  SELECT * INTO target FROM public.receipts WHERE id = p_target_id AND user_id = uid;
  IF NOT FOUND THEN RAISE EXCEPTION 'Quittung nicht gefunden oder nicht im eigenen Besitz.' USING ERRCODE = '42501'; END IF;
  IF p_source_id IS NOT NULL THEN
    SELECT * INTO source FROM public.receipts WHERE id = p_source_id AND user_id = uid;
    IF NOT FOUND THEN RAISE EXCEPTION 'Quittung nicht gefunden oder nicht im eigenen Besitz.' USING ERRCODE = '42501'; END IF;
    source_json := to_jsonb(source);
    SELECT coalesce(jsonb_agg(to_jsonb(i) ORDER BY i.id), '[]'::jsonb) INTO source_items FROM public.receipt_items i WHERE receipt_id = p_source_id;
  ELSE
    IF jsonb_typeof(p_scan) <> 'object' OR pg_column_size(p_scan) > 4000000 OR
       jsonb_typeof(coalesce(p_scan->'receipt_items', '[]'::jsonb)) <> 'array' THEN
      RAISE EXCEPTION 'Ungültiger Scan.' USING ERRCODE = '22023';
    END IF;
    source_json := p_scan;
    source_items := coalesce(p_scan->'receipt_items', '[]'::jsonb);
    IF jsonb_array_length(source_items) > 2000 THEN RAISE EXCEPTION 'Zu viele Artikel.' USING ERRCODE = '22023'; END IF;
  END IF;
  -- Lock editable children before snapshot/conflict checking, including background classifications.
  PERFORM id FROM public.receipt_items WHERE receipt_id IN (p_target_id, p_source_id) ORDER BY id FOR UPDATE;
  IF p_source_id IS NOT NULL THEN
    SELECT coalesce(jsonb_agg(to_jsonb(i) ORDER BY i.id), '[]'::jsonb) INTO source_items FROM public.receipt_items i WHERE receipt_id = p_source_id;
  END IF;
  SELECT coalesce(jsonb_agg(to_jsonb(i) ORDER BY i.id), '[]'::jsonb) INTO target_items FROM public.receipt_items i WHERE receipt_id = p_target_id;
  -- Freeze concurrent review/matching writes before conflict checks and audit snapshots.
  FOREACH tab IN ARRAY ARRAY['receipt_matches','match_suggestions','splits','shared_receipts','expense_submissions','rematch_queue'] LOOP
    EXECUTE format('SELECT id FROM public.%I WHERE receipt_id IN ($1,$2) ORDER BY id FOR UPDATE',tab) USING p_target_id,p_source_id;
  END LOOP;
  target_json := to_jsonb(target);
  merged := target_json;
  FOREACH field IN ARRAY ARRAY['receipt_date','store_name','store_category','total_amount','currency','payment_method','payment_card','notes','raw_ocr_text','markdown_content'] LOOP
    IF p_choices->'fields'->>field = 'source' OR
       (NOT coalesce(p_choices->'fields' ? field, false) AND coalesce(target_json->>field,'') = '' AND source_json ? field) THEN
      merged := jsonb_set(merged, ARRAY[field], coalesce(source_json->field,'null'::jsonb));
    END IF;
  END LOOP;
  IF coalesce(merged->>'store_name','') = '' OR coalesce(merged->>'currency','') = '' THEN
    RAISE EXCEPTION 'Laden und Währung dürfen nicht leer sein.' USING ERRCODE = '22023';
  END IF;
  -- Image choice applies to both processed and original image, independently of items/data.
  IF p_choices->>'image' = 'source' OR (NOT p_choices ? 'image' AND coalesce(target_json->>'image_url','') = '') THEN
    merged := merged || jsonb_build_object('image_url', source_json->'image_url', 'original_image_url', source_json->'original_image_url');
  END IF;
  extras := coalesce(nullif(target_json->'extra_fields','null'::jsonb),'{}'::jsonb);
  IF jsonb_typeof(extras) <> 'object' OR (source_json->'extra_fields' IS NOT NULL AND source_json->'extra_fields' <> 'null'::jsonb AND jsonb_typeof(source_json->'extra_fields') <> 'object') THEN
    RAISE EXCEPTION 'Ungültige Zusatzangaben.' USING ERRCODE = '22023';
  END IF;
  FOR field IN SELECT jsonb_object_keys(coalesce(nullif(source_json->'extra_fields','null'::jsonb),'{}'::jsonb)) LOOP
    IF p_choices->'extras'->>field = 'source' OR (NOT coalesce(p_choices->'extras' ? field,false) AND coalesce(extras->>field,'') = '') THEN extras := jsonb_set(extras, ARRAY[field], source_json->'extra_fields'->field); END IF;
  END LOOP;
  merged := merged || jsonb_build_object('extra_fields', extras);
  has_split := EXISTS(SELECT 1 FROM public.splits WHERE receipt_id IN (p_target_id,p_source_id));
  has_bank := EXISTS(SELECT 1 FROM public.receipt_matches WHERE receipt_id IN (p_target_id,p_source_id));
  IF p_source_id IS NOT NULL THEN
    IF target.project_id IS NOT NULL AND source.project_id IS NOT NULL AND target.project_id <> source.project_id OR
       target.company_id IS NOT NULL AND source.company_id IS NOT NULL AND target.company_id <> source.company_id THEN
      RAISE EXCEPTION 'Unterschiedliche Projekte oder Firmen: Zuordnung zuerst prüfen.' USING ERRCODE = '22023';
    END IF;
    merged := merged || jsonb_build_object('project_id', coalesce(target.project_id, source.project_id), 'company_id', coalesce(target.company_id, source.company_id));
    IF (SELECT count(*) FROM public.splits WHERE receipt_id IN (p_target_id,p_source_id)) > 1 THEN
      RAISE EXCEPTION 'Beide Quittungen haben eine Aufteilung. Bitte diese zuerst prüfen.' USING ERRCODE = '22023';
    END IF;
    IF (SELECT count(DISTINCT transaction_id) FROM public.receipt_matches WHERE receipt_id IN (p_target_id,p_source_id)) > 1 THEN
      RAISE EXCEPTION 'Unterschiedliche Bankbuchungen verknüpft. Bitte diese zuerst prüfen.' USING ERRCODE = '22023';
    END IF;
    -- Do not silently resolve social/business decisions with different recipients/statuses.
    IF EXISTS(SELECT 1 FROM public.shared_receipts a JOIN public.shared_receipts b USING(recipient_id)
      WHERE a.receipt_id = p_target_id AND b.receipt_id = p_source_id AND to_jsonb(a) - ARRAY['id','receipt_id','created_at'] <> to_jsonb(b) - ARRAY['id','receipt_id','created_at']) OR
       EXISTS(SELECT 1 FROM public.expense_submissions a JOIN public.expense_submissions b USING(company_id)
      WHERE a.receipt_id = p_target_id AND b.receipt_id = p_source_id) THEN
      RAISE EXCEPTION 'Geteilte oder eingereichte Quittungen enthalten widersprüchliche Zuordnungen. Bitte zuerst prüfen.' USING ERRCODE = '22023';
    END IF;
    IF EXISTS(SELECT 1 FROM public.match_suggestions a JOIN public.match_suggestions b USING(transaction_id)
      WHERE a.receipt_id = p_target_id AND b.receipt_id = p_source_id AND a.status <> 'open' AND b.status <> 'open'
        AND to_jsonb(a) - ARRAY['id','receipt_id','created_at','resolved_at'] <> to_jsonb(b) - ARRAY['id','receipt_id','created_at','resolved_at']) THEN
      RAISE EXCEPTION 'Widersprüchliche Bankvorschlags-Entscheidungen. Bitte zuerst prüfen.' USING ERRCODE = '22023';
    END IF;
    -- Fail closed if a future migration introduces a dependent table not handled below.
    FOR foreign_ref IN SELECT conrelid FROM pg_catalog.pg_constraint WHERE confrelid = 'public.receipts'::regclass AND contype = 'f' LOOP
      IF foreign_ref.conrelid NOT IN ('public.receipt_items'::regclass, 'public.receipt_matches'::regclass,
        'public.match_suggestions'::regclass, 'public.splits'::regclass, 'public.shared_receipts'::regclass,
        'public.expense_submissions'::regclass, 'public.rematch_queue'::regclass, 'public.receipt_duplicate_decisions'::regclass) THEN
        RAISE EXCEPTION 'Weitere Quittungsverknüpfung vorhanden. Zusammenführen erfordert ein Update.' USING ERRCODE = '22023';
      END IF;
    END LOOP;
  END IF;
  -- Financially linked amounts must remain stable (including when the linked row was source).
  IF has_bank OR has_split THEN
    IF EXISTS(SELECT 1 FROM public.receipts r WHERE r.id IN (p_target_id,p_source_id)
      AND (EXISTS(SELECT 1 FROM public.receipt_matches m WHERE m.receipt_id = r.id) OR EXISTS(SELECT 1 FROM public.splits s WHERE s.receipt_id = r.id))
      AND (r.total_amount IS DISTINCT FROM (merged->>'total_amount')::numeric OR r.currency IS DISTINCT FROM merged->>'currency')) THEN
      RAISE EXCEPTION 'Betrag und Währung einer verknüpften Quittung müssen erhalten bleiben.' USING ERRCODE = '22023';
    END IF;
  END IF;
  use_source_items := coalesce(p_choices->>'items' = 'source', false) OR (NOT p_choices ? 'items' AND jsonb_array_length(target_items) = 0);
  IF use_source_items AND EXISTS(SELECT 1 FROM public.receipt_items WHERE receipt_id = p_target_id AND NOT is_adjustment AND classified_at IS NOT NULL) THEN
    RAISE EXCEPTION 'Geprüfte Artikel bleiben erhalten. Bitte vorhandene Artikel wählen.' USING ERRCODE = '22023';
  END IF;
  IF NOT use_source_items AND EXISTS(SELECT 1 FROM public.receipt_items WHERE receipt_id = p_source_id AND NOT is_adjustment AND classified_at IS NOT NULL) THEN
    RAISE EXCEPTION 'Die zweite Quittung enthält geprüfte Artikel. Bitte diese erhalten oder zuerst prüfen.' USING ERRCODE = '22023';
  END IF;
  IF use_source_items AND EXISTS(SELECT 1 FROM public.splits WHERE receipt_id = p_target_id) OR
     NOT use_source_items AND EXISTS(SELECT 1 FROM public.splits WHERE receipt_id = p_source_id) THEN
    RAISE EXCEPTION 'Artikel einer aufgeteilten Quittung müssen erhalten bleiben.' USING ERRCODE = '22023';
  END IF;
  IF EXISTS(SELECT 1 FROM public.receipt_items WHERE receipt_id = p_target_id AND is_adjustment) AND
     EXISTS(SELECT 1 FROM public.receipt_items WHERE receipt_id = p_source_id AND is_adjustment) THEN
    RAISE EXCEPTION 'Beide Quittungen haben Ausgleichsposten. Bitte diese zuerst prüfen.' USING ERRCODE = '22023';
  END IF;
  -- Snapshot all child data before mutation, retaining original images and manual classifications.
  FOREACH tab IN ARRAY ARRAY['receipt_matches','match_suggestions','splits','shared_receipts','expense_submissions','rematch_queue'] LOOP
    EXECUTE format('SELECT coalesce(jsonb_agg(to_jsonb(t)), ''[]''::jsonb) FROM public.%I t WHERE receipt_id IN ($1,$2)', tab)
      INTO item_json USING p_target_id,p_source_id;
    rels := rels || jsonb_build_object(tab,item_json);
  END LOOP;
  INSERT INTO private.receipt_merge_history(user_id,target_id,source_id,target_before,source_before,choices,relationships)
    VALUES(uid,p_target_id,p_source_id,target_json || jsonb_build_object('receipt_items',target_items),source_json || jsonb_build_object('receipt_items',source_items),p_choices,rels);

  UPDATE public.receipts SET
    receipt_date = (merged->>'receipt_date')::date, store_name = merged->>'store_name', store_category = merged->>'store_category',
    total_amount = (merged->>'total_amount')::numeric, currency = merged->>'currency', payment_method = merged->>'payment_method',
    payment_card = merged->>'payment_card', notes = merged->>'notes', raw_ocr_text = merged->>'raw_ocr_text', markdown_content = merged->>'markdown_content',
    image_url = merged->>'image_url', original_image_url = merged->>'original_image_url', extra_fields = merged->'extra_fields',
    project_id = (merged->>'project_id')::uuid, company_id = (merged->>'company_id')::uuid
    WHERE id = p_target_id;
  IF use_source_items THEN
    DELETE FROM public.receipt_items WHERE receipt_id = p_target_id AND NOT is_adjustment;
    IF p_source_id IS NOT NULL THEN
      UPDATE public.receipt_items SET receipt_id = p_target_id WHERE receipt_id = p_source_id AND NOT is_adjustment;
    ELSE
      FOR item_json IN SELECT value FROM jsonb_array_elements(source_items) LOOP
        IF coalesce(item_json->>'name','') = '' OR coalesce((item_json->>'is_adjustment')::boolean,false) THEN
          RAISE EXCEPTION 'Ungültiger Scan-Artikel.' USING ERRCODE = '22023';
        END IF;
        INSERT INTO public.receipt_items(receipt_id,name,quantity,unit,unit_price,total_price,tags,subcategory,is_adjustment)
          VALUES(p_target_id,item_json->>'name',(item_json->>'quantity')::numeric,item_json->>'unit',
            (item_json->>'unit_price')::numeric,(item_json->>'total_price')::numeric,
            ARRAY(SELECT jsonb_array_elements_text(coalesce(item_json->'tags','[]'::jsonb))),item_json->>'subcategory',false);
      END LOOP;
    END IF;
  END IF;
  IF p_source_id IS NOT NULL THEN
    UPDATE public.receipt_items SET receipt_id = p_target_id WHERE receipt_id = p_source_id AND is_adjustment;
    -- Identical match on both sides collapses without resetting bank match_status.
    UPDATE public.receipt_matches a SET match_type = 'manual'
      FROM public.receipt_matches b WHERE a.receipt_id = p_target_id AND b.receipt_id = p_source_id AND a.transaction_id = b.transaction_id AND b.match_type = 'manual';
    DELETE FROM public.receipt_matches a USING public.receipt_matches b WHERE a.receipt_id = p_source_id AND b.receipt_id = p_target_id AND a.transaction_id = b.transaction_id;
    UPDATE public.receipt_matches SET receipt_id = p_target_id WHERE receipt_id = p_source_id;
    -- Rejections/accepted decisions win over open suggestions; never accept a suggestion here.
    DELETE FROM public.match_suggestions a USING public.match_suggestions b WHERE a.receipt_id = p_target_id AND b.receipt_id = p_source_id AND a.transaction_id = b.transaction_id AND a.status = 'open' AND b.status <> 'open';
    DELETE FROM public.match_suggestions a USING public.match_suggestions b WHERE a.receipt_id = p_source_id AND b.receipt_id = p_target_id AND a.transaction_id = b.transaction_id;
    UPDATE public.match_suggestions SET receipt_id = p_target_id WHERE receipt_id = p_source_id;
    UPDATE public.splits SET receipt_id = p_target_id WHERE receipt_id = p_source_id;
    DELETE FROM public.shared_receipts a USING public.shared_receipts b WHERE a.receipt_id = p_source_id AND b.receipt_id = p_target_id AND a.recipient_id = b.recipient_id;
    UPDATE public.shared_receipts SET receipt_id = p_target_id WHERE receipt_id = p_source_id;
    UPDATE public.expense_submissions SET receipt_id = p_target_id WHERE receipt_id = p_source_id;
    UPDATE public.rematch_queue SET receipt_id = p_target_id WHERE receipt_id = p_source_id;
    -- Carry forward keep-separate decisions so rejected pairs do not reappear after merge.
    INSERT INTO public.receipt_duplicate_decisions(user_id,receipt_a,receipt_b)
      SELECT uid, least(p_target_id,CASE WHEN receipt_a = p_source_id THEN receipt_b ELSE receipt_a END),greatest(p_target_id,CASE WHEN receipt_a = p_source_id THEN receipt_b ELSE receipt_a END)
      FROM public.receipt_duplicate_decisions WHERE user_id = uid AND p_source_id IN(receipt_a,receipt_b) AND p_target_id NOT IN(receipt_a,receipt_b) ON CONFLICT DO NOTHING;
    DELETE FROM public.receipts WHERE id = p_source_id;
  END IF;
  RETURN p_target_id;
END;
$$;

CREATE OR REPLACE FUNCTION public.keep_receipts_separate(p_receipt_a uuid,p_receipt_b uuid)
RETURNS void LANGUAGE sql SECURITY INVOKER SET search_path = '' AS $$ SELECT private.keep_receipts_separate(p_receipt_a,p_receipt_b); $$;
CREATE OR REPLACE FUNCTION public.merge_receipt_duplicates(p_target_id uuid,p_source_id uuid,p_choices jsonb DEFAULT '{}'::jsonb)
RETURNS uuid LANGUAGE sql SECURITY INVOKER SET search_path = '' AS $$ SELECT private.merge_receipt_review(p_target_id,p_source_id,NULL,p_choices); $$;
CREATE OR REPLACE FUNCTION public.merge_receipt_scan(p_target_id uuid,p_scan jsonb,p_choices jsonb DEFAULT '{}'::jsonb)
RETURNS uuid LANGUAGE sql SECURITY INVOKER SET search_path = '' AS $$ SELECT private.merge_receipt_review(p_target_id,NULL,p_scan,p_choices); $$;
REVOKE ALL ON FUNCTION private.keep_receipts_separate(uuid,uuid), private.merge_receipt_review(uuid,uuid,jsonb,jsonb) FROM PUBLIC,anon;
REVOKE ALL ON FUNCTION public.keep_receipts_separate(uuid,uuid), public.merge_receipt_duplicates(uuid,uuid,jsonb), public.merge_receipt_scan(uuid,jsonb,jsonb) FROM PUBLIC,anon;
GRANT EXECUTE ON FUNCTION private.keep_receipts_separate(uuid,uuid), private.merge_receipt_review(uuid,uuid,jsonb,jsonb) TO authenticated;
GRANT EXECUTE ON FUNCTION public.keep_receipts_separate(uuid,uuid), public.merge_receipt_duplicates(uuid,uuid,jsonb), public.merge_receipt_scan(uuid,jsonb,jsonb) TO authenticated;

-- Saving a deliberate distinct purchase and suppressing all reviewed pairs is one transaction.
-- Reuse the merge validator/whitelist with a new, empty owner row, never trust scan ownership/IDs.
CREATE OR REPLACE FUNCTION private.save_separate_receipt_scan(p_scan jsonb,p_separate_ids uuid[])
RETURNS uuid LANGUAGE plpgsql SECURITY DEFINER SET search_path = '' AS $$
DECLARE uid uuid := auth.uid(); new_id uuid; other_id uuid; ids uuid[];
BEGIN
  IF uid IS NULL THEN RAISE EXCEPTION 'Bitte zuerst anmelden.' USING ERRCODE = '42501'; END IF;
  IF p_scan IS NULL OR jsonb_typeof(p_scan) <> 'object' OR coalesce(p_scan->>'store_name','') = '' OR coalesce(p_scan->>'currency','') = '' THEN
    RAISE EXCEPTION 'Ungültiger Scan.' USING ERRCODE = '22023';
  END IF;
  IF p_separate_ids IS NULL OR array_position(p_separate_ids,NULL) IS NOT NULL OR cardinality(p_separate_ids) > 200 THEN
    RAISE EXCEPTION 'Ungültige Quittungsauswahl.' USING ERRCODE = '22023';
  END IF;
  SELECT coalesce(array_agg(DISTINCT x ORDER BY x),'{}'::uuid[]) INTO ids FROM unnest(p_separate_ids) x;
  PERFORM id FROM public.receipts WHERE id = ANY(ids) ORDER BY id FOR UPDATE;
  IF (SELECT count(*) FROM public.receipts WHERE id = ANY(ids) AND user_id = uid) <> cardinality(ids) THEN
    RAISE EXCEPTION 'Quittung nicht gefunden oder nicht im eigenen Besitz.' USING ERRCODE = '42501';
  END IF;
  INSERT INTO public.receipts(user_id,store_name,currency) VALUES(uid,p_scan->>'store_name',p_scan->>'currency') RETURNING id INTO new_id;
  PERFORM private.merge_receipt_review(new_id,NULL,p_scan,jsonb_build_object('image','source','items','source'));
  FOREACH other_id IN ARRAY ids LOOP
    INSERT INTO public.receipt_duplicate_decisions(user_id,receipt_a,receipt_b) VALUES(uid,least(other_id,new_id),greatest(other_id,new_id));
  END LOOP;
  RETURN new_id;
END;
$$;
CREATE OR REPLACE FUNCTION public.save_separate_receipt_scan(p_scan jsonb,p_separate_ids uuid[] DEFAULT '{}'::uuid[])
RETURNS uuid LANGUAGE sql SECURITY INVOKER SET search_path = '' AS $$ SELECT private.save_separate_receipt_scan(p_scan,p_separate_ids); $$;

CREATE OR REPLACE FUNCTION private.delete_reviewed_receipt(p_receipt_id uuid)
RETURNS void LANGUAGE plpgsql SECURITY DEFINER SET search_path = '' AS $$
DECLARE uid uuid := auth.uid();
BEGIN
  IF uid IS NULL THEN RAISE EXCEPTION 'Bitte zuerst anmelden.' USING ERRCODE = '42501'; END IF;
  PERFORM id FROM public.receipts WHERE id = p_receipt_id AND user_id = uid FOR UPDATE;
  IF NOT FOUND THEN RAISE EXCEPTION 'Quittung nicht gefunden oder nicht im eigenen Besitz.' USING ERRCODE = '42501'; END IF;
  IF EXISTS(SELECT 1 FROM public.receipt_matches WHERE receipt_id = p_receipt_id) OR
     EXISTS(SELECT 1 FROM public.splits WHERE receipt_id = p_receipt_id) OR
     EXISTS(SELECT 1 FROM public.shared_receipts WHERE receipt_id = p_receipt_id) OR
     EXISTS(SELECT 1 FROM public.expense_submissions WHERE receipt_id = p_receipt_id) THEN
    RAISE EXCEPTION 'Diese Quittung ist verknüpft, geteilt oder aufgeteilt. Bitte zuerst die Verknüpfungen prüfen und lösen.' USING ERRCODE = '22023';
  END IF;
  DELETE FROM public.receipts WHERE id = p_receipt_id;
END;
$$;
CREATE OR REPLACE FUNCTION public.delete_reviewed_receipt(p_receipt_id uuid)
RETURNS void LANGUAGE sql SECURITY INVOKER SET search_path = '' AS $$ SELECT private.delete_reviewed_receipt(p_receipt_id); $$;
REVOKE ALL ON FUNCTION private.save_separate_receipt_scan(jsonb,uuid[]),public.save_separate_receipt_scan(jsonb,uuid[]),private.delete_reviewed_receipt(uuid),public.delete_reviewed_receipt(uuid) FROM PUBLIC,anon;
GRANT EXECUTE ON FUNCTION private.save_separate_receipt_scan(jsonb,uuid[]),public.save_separate_receipt_scan(jsonb,uuid[]),private.delete_reviewed_receipt(uuid),public.delete_reviewed_receipt(uuid) TO authenticated;
