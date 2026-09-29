-- Account deletion (App Store Guideline 5.1.1(v)): deleting auth.users must
-- remove the user's data instead of failing on NO ACTION foreign keys.
-- Owned data cascades; references to the user in other people's records are nulled.

ALTER TABLE receipts
  DROP CONSTRAINT receipts_user_id_fkey,
  ADD CONSTRAINT receipts_user_id_fkey
    FOREIGN KEY (user_id) REFERENCES auth.users(id) ON DELETE CASCADE;

ALTER TABLE shared_receipts
  DROP CONSTRAINT shared_receipts_sender_id_fkey,
  ADD CONSTRAINT shared_receipts_sender_id_fkey
    FOREIGN KEY (sender_id) REFERENCES auth.users(id) ON DELETE CASCADE,
  DROP CONSTRAINT shared_receipts_recipient_id_fkey,
  ADD CONSTRAINT shared_receipts_recipient_id_fkey
    FOREIGN KEY (recipient_id) REFERENCES auth.users(id) ON DELETE CASCADE;

ALTER TABLE expense_submissions
  DROP CONSTRAINT expense_submissions_submitter_id_fkey,
  ADD CONSTRAINT expense_submissions_submitter_id_fkey
    FOREIGN KEY (submitter_id) REFERENCES auth.users(id) ON DELETE CASCADE,
  DROP CONSTRAINT expense_submissions_reviewed_by_fkey,
  ADD CONSTRAINT expense_submissions_reviewed_by_fkey
    FOREIGN KEY (reviewed_by) REFERENCES auth.users(id) ON DELETE SET NULL;

ALTER TABLE project_members
  DROP CONSTRAINT project_members_invited_by_fkey,
  ADD CONSTRAINT project_members_invited_by_fkey
    FOREIGN KEY (invited_by) REFERENCES auth.users(id) ON DELETE SET NULL;

ALTER TABLE user_usage
  DROP CONSTRAINT user_usage_user_id_fkey,
  ADD CONSTRAINT user_usage_user_id_fkey
    FOREIGN KEY (user_id) REFERENCES auth.users(id) ON DELETE CASCADE;
