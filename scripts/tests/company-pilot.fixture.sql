-- Disposable database only: baseline fields used by the real existing foundation migration.
CREATE ROLE anon; CREATE ROLE authenticated; CREATE ROLE service_role BYPASSRLS;
CREATE SCHEMA auth; CREATE SCHEMA private;
CREATE TABLE auth.users(id uuid PRIMARY KEY);
CREATE FUNCTION auth.uid() RETURNS uuid LANGUAGE sql STABLE AS $$ SELECT nullif(current_setting('request.jwt.claim.sub',true),'')::uuid $$;
GRANT USAGE ON SCHEMA public,auth,private TO authenticated,service_role;
CREATE TABLE public.profiles(id uuid PRIMARY KEY REFERENCES auth.users,display_name text);
CREATE TABLE public.receipts(id uuid PRIMARY KEY DEFAULT gen_random_uuid(),user_id uuid REFERENCES auth.users,
  receipt_date date,store_name text NOT NULL,total_amount numeric,currency text NOT NULL,
  payment_method text,payment_card text,extra_fields jsonb,raw_ocr_text text,image_url text,original_image_url text,
  notes text,created_at timestamptz DEFAULT now());
CREATE TABLE public.receipt_items(id uuid PRIMARY KEY DEFAULT gen_random_uuid(),receipt_id uuid REFERENCES receipts ON DELETE CASCADE,
  name text,quantity numeric,unit text,unit_price numeric,total_price numeric,tags text[],subcategory text);
CREATE TABLE public.splits(id uuid PRIMARY KEY DEFAULT gen_random_uuid(),receipt_id uuid REFERENCES receipts);
ALTER TABLE public.receipts ENABLE ROW LEVEL SECURITY;
GRANT SELECT,INSERT,UPDATE,DELETE ON public.receipts TO authenticated;
CREATE POLICY own_receipts ON public.receipts TO authenticated USING(user_id=(SELECT auth.uid())) WITH CHECK(user_id=(SELECT auth.uid()));
