-- Ausgaben an Freunde senden und von beiden Seiten bestätigen (nur Händler, Datum, Betrag, Notiz).
-- Bewusst ohne Buchungstext, Konto- oder Kartennummer. Nicht angewendet, bevor die Datenschutz-Prüfung
-- (Playbook datenschutz-pruefung-kaufmann-legal.md) abgeschlossen ist.

CREATE TABLE IF NOT EXISTS public.shared_bank_items (
  id           uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  sender_id    uuid NOT NULL REFERENCES public.profiles(id) ON DELETE CASCADE,
  recipient_id uuid NOT NULL REFERENCES public.profiles(id) ON DELETE CASCADE,
  title        text NOT NULL CHECK (char_length(title) BETWEEN 1 AND 120),
  booking_date date NOT NULL,
  amount       numeric(12,2) NOT NULL CHECK (amount >= 0),
  currency     text NOT NULL CHECK (currency ~ '^[A-Z]{3}$'),
  note         text CHECK (note IS NULL OR char_length(note) <= 200),
  status       text NOT NULL DEFAULT 'sent' CHECK (status IN ('sent', 'confirmed', 'declined')),
  created_at   timestamptz NOT NULL DEFAULT now(),
  responded_at timestamptz,
  CHECK (sender_id <> recipient_id)
);
CREATE INDEX IF NOT EXISTS shared_bank_items_sender_idx ON public.shared_bank_items (sender_id, created_at DESC);
CREATE INDEX IF NOT EXISTS shared_bank_items_recipient_idx ON public.shared_bank_items (recipient_id, created_at DESC);

ALTER TABLE public.shared_bank_items ENABLE ROW LEVEL SECURITY;

CREATE POLICY shared_bank_items_select ON public.shared_bank_items
  FOR SELECT TO authenticated
  USING ((SELECT auth.uid()) IN (sender_id, recipient_id));

-- Senden nur an bestätigte Freunde, nur im eigenen Namen, immer mit Status «sent».
CREATE POLICY shared_bank_items_insert ON public.shared_bank_items
  FOR INSERT TO authenticated
  WITH CHECK (
    sender_id = (SELECT auth.uid()) AND status = 'sent' AND responded_at IS NULL
    AND EXISTS (
      SELECT 1 FROM public.friendships f
      WHERE f.status = 'accepted'
        AND ((f.requester_id = sender_id AND f.recipient_id = shared_bank_items.recipient_id)
          OR (f.recipient_id = sender_id AND f.requester_id = shared_bank_items.recipient_id))
    )
  );

-- Zurückziehen darf nur, wer gesendet hat.
CREATE POLICY shared_bank_items_delete ON public.shared_bank_items
  FOR DELETE TO authenticated
  USING (sender_id = (SELECT auth.uid()));

-- Antworten: nur der Empfänger, nur einmal. Kein direkter UPDATE (es gibt keine UPDATE-Policy).
CREATE OR REPLACE FUNCTION public.respond_shared_bank_item(p_id uuid, p_accept boolean)
RETURNS void LANGUAGE plpgsql SECURITY DEFINER SET search_path = '' AS $$
DECLARE uid uuid := (SELECT auth.uid());
BEGIN
  IF uid IS NULL THEN RAISE EXCEPTION 'not authenticated'; END IF;
  UPDATE public.shared_bank_items
  SET status = CASE WHEN p_accept THEN 'confirmed' ELSE 'declined' END, responded_at = now()
  WHERE id = p_id AND recipient_id = uid AND status = 'sent';
  IF NOT FOUND THEN RAISE EXCEPTION 'item not found or already answered'; END IF;
END $$;

REVOKE EXECUTE ON FUNCTION public.respond_shared_bank_item(uuid, boolean) FROM PUBLIC, anon;
GRANT EXECUTE ON FUNCTION public.respond_shared_bank_item(uuid, boolean) TO authenticated;
