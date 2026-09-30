-- PostgREST embeds like profiles!splits_payer_id_fkey(*) need foreign keys to
-- public.profiles; these columns only referenced auth.users, so friends,
-- projects, splits and shared receipts failed with PGRST200 (HTTP 400).
-- Same constraint names (the app uses them as embed hints). profiles.id
-- cascades from auth.users, so account deletion still cascades.

ALTER TABLE public.friendships
  DROP CONSTRAINT friendships_requester_id_fkey,
  ADD CONSTRAINT friendships_requester_id_fkey FOREIGN KEY (requester_id) REFERENCES public.profiles(id) ON DELETE CASCADE,
  DROP CONSTRAINT friendships_recipient_id_fkey,
  ADD CONSTRAINT friendships_recipient_id_fkey FOREIGN KEY (recipient_id) REFERENCES public.profiles(id) ON DELETE CASCADE;

ALTER TABLE public.project_members
  DROP CONSTRAINT project_members_user_id_fkey,
  ADD CONSTRAINT project_members_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.profiles(id) ON DELETE CASCADE;

ALTER TABLE public.split_participants
  DROP CONSTRAINT split_participants_user_id_fkey,
  ADD CONSTRAINT split_participants_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.profiles(id) ON DELETE CASCADE;

ALTER TABLE public.splits
  DROP CONSTRAINT splits_payer_id_fkey,
  ADD CONSTRAINT splits_payer_id_fkey FOREIGN KEY (payer_id) REFERENCES public.profiles(id) ON DELETE CASCADE;

ALTER TABLE public.shared_receipts
  DROP CONSTRAINT shared_receipts_sender_id_fkey,
  ADD CONSTRAINT shared_receipts_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.profiles(id) ON DELETE CASCADE;

ALTER TABLE public.profile_cards
  DROP CONSTRAINT profile_cards_user_id_fkey,
  ADD CONSTRAINT profile_cards_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.profiles(id) ON DELETE CASCADE;

NOTIFY pgrst, 'reload schema';
