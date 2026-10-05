-- Rohausgabe pro Seite (Diagnose) + globale Drossel über alle parallelen Worker-Aufrufe
ALTER TABLE public.statement_pages ADD COLUMN IF NOT EXISTS raw_output text;

CREATE OR REPLACE FUNCTION public.claim_statement_pages(p_limit int)
RETURNS SETOF public.statement_pages LANGUAGE sql SECURITY DEFINER SET search_path = '' AS $$
  UPDATE public.statement_pages p
  SET status = 'processing', attempts = p.attempts + 1, locked_at = now()
  WHERE p.id IN (
    SELECT id FROM public.statement_pages
    WHERE (status = 'pending' AND next_attempt_at <= now())
       OR (status = 'processing' AND locked_at < now() - interval '10 minutes')
    ORDER BY page_no, created_at
    LIMIT greatest(0, p_limit - (
      SELECT count(*) FROM public.statement_pages
      WHERE status = 'processing' AND locked_at >= now() - interval '10 minutes'))
    FOR UPDATE SKIP LOCKED
  )
  RETURNING p.*;
$$;
REVOKE EXECUTE ON FUNCTION public.claim_statement_pages(int) FROM PUBLIC, anon, authenticated;
GRANT EXECUTE ON FUNCTION public.claim_statement_pages(int) TO service_role;
