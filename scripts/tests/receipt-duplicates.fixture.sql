-- Isolated PostgreSQL fixture: missing baseline receipts/sharing schema is modelled here.
-- Relationship table definitions follow the checked-in migrations; no remote DB is used.
CREATE ROLE anon; CREATE ROLE authenticated;
CREATE SCHEMA auth; CREATE SCHEMA private;
CREATE TABLE auth.users(id uuid PRIMARY KEY);
CREATE FUNCTION auth.uid() RETURNS uuid LANGUAGE sql STABLE AS $$ SELECT nullif(current_setting('request.jwt.claim.sub',true),'')::uuid $$;
GRANT USAGE ON SCHEMA public,auth,private TO authenticated;
CREATE TABLE public.receipts (
 id uuid PRIMARY KEY DEFAULT gen_random_uuid(), user_id uuid NOT NULL REFERENCES auth.users,
 receipt_date date, store_name text NOT NULL, store_category text, total_amount numeric,
 currency text NOT NULL DEFAULT 'CHF',payment_method text,payment_card text,
 image_url text,original_image_url text,raw_ocr_text text,extra_fields jsonb,
 markdown_content text,notes text,created_at timestamptz DEFAULT now(),project_id uuid,company_id uuid
);
CREATE TABLE public.receipt_items (
 id uuid PRIMARY KEY DEFAULT gen_random_uuid(), receipt_id uuid NOT NULL REFERENCES receipts ON DELETE CASCADE,
 name text NOT NULL,quantity numeric,unit text,unit_price numeric,total_price numeric,tags text[],subcategory text,
 is_adjustment boolean NOT NULL DEFAULT false,classified_at timestamptz
);
CREATE TABLE public.bank_transactions(id uuid PRIMARY KEY DEFAULT gen_random_uuid(),user_id uuid,match_status text DEFAULT 'matched');
CREATE TABLE public.receipt_matches(id uuid PRIMARY KEY DEFAULT gen_random_uuid(),user_id uuid,receipt_id uuid REFERENCES receipts ON DELETE CASCADE,transaction_id uuid REFERENCES bank_transactions,score numeric,match_type text,UNIQUE(transaction_id,receipt_id));
CREATE TABLE public.match_suggestions(id uuid PRIMARY KEY DEFAULT gen_random_uuid(),user_id uuid,receipt_id uuid REFERENCES receipts ON DELETE CASCADE,transaction_id uuid,kind text,status text,note text,UNIQUE(transaction_id,receipt_id));
CREATE TABLE public.splits(id uuid PRIMARY KEY DEFAULT gen_random_uuid(),receipt_id uuid UNIQUE REFERENCES receipts ON DELETE CASCADE,payer_id uuid,total_amount numeric,currency text);
CREATE TABLE public.split_participants(id uuid PRIMARY KEY DEFAULT gen_random_uuid(),split_id uuid REFERENCES splits ON DELETE CASCADE,user_id uuid,amount numeric,settled boolean DEFAULT false);
CREATE TABLE public.split_exclusions(id uuid PRIMARY KEY DEFAULT gen_random_uuid(),split_id uuid REFERENCES splits ON DELETE CASCADE,receipt_item_id uuid REFERENCES receipt_items ON DELETE CASCADE);
CREATE TABLE public.shared_receipts(id uuid PRIMARY KEY DEFAULT gen_random_uuid(),receipt_id uuid REFERENCES receipts ON DELETE CASCADE,sender_id uuid,recipient_id uuid,status text,created_at timestamptz DEFAULT now(),UNIQUE(receipt_id,recipient_id));
CREATE TABLE public.expense_submissions(id uuid PRIMARY KEY DEFAULT gen_random_uuid(),receipt_id uuid REFERENCES receipts ON DELETE CASCADE,company_id uuid,submitter_id uuid,status text,UNIQUE(receipt_id,company_id));
CREATE TABLE public.rematch_queue(id uuid PRIMARY KEY DEFAULT gen_random_uuid(),receipt_id uuid REFERENCES receipts ON DELETE CASCADE,user_id uuid);
ALTER TABLE receipts ENABLE ROW LEVEL SECURITY;
GRANT SELECT ON receipts TO authenticated;
CREATE POLICY owned_receipts ON receipts FOR SELECT TO authenticated USING(user_id = (SELECT auth.uid()));
