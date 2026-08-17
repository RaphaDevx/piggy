-- Social Layer: profiles + friendships

CREATE TABLE profiles (
  id           UUID PRIMARY KEY REFERENCES auth.users(id) ON DELETE CASCADE,
  display_name TEXT,
  piggy_code   TEXT UNIQUE NOT NULL,
  created_at   TIMESTAMPTZ DEFAULT NOW()
);

-- Auto-generate piggy_code from user UUID on signup
CREATE OR REPLACE FUNCTION handle_new_user()
RETURNS TRIGGER LANGUAGE plpgsql SECURITY DEFINER SET search_path = public AS $$
BEGIN
  INSERT INTO public.profiles (id, piggy_code)
  VALUES (
    NEW.id,
    'PIG-' || upper(substring(replace(NEW.id::text, '-', ''), 1, 8))
  );
  RETURN NEW;
END;
$$;

CREATE TRIGGER on_auth_user_created
  AFTER INSERT ON auth.users
  FOR EACH ROW EXECUTE FUNCTION handle_new_user();

-- Backfill profiles for existing users (idempotent)
INSERT INTO profiles (id, piggy_code)
SELECT
  id,
  'PIG-' || upper(substring(replace(id::text, '-', ''), 1, 8))
FROM auth.users
ON CONFLICT (id) DO NOTHING;

CREATE TABLE friendships (
  id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  requester_id UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
  recipient_id UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
  status       TEXT NOT NULL DEFAULT 'pending'
                 CHECK (status IN ('pending', 'accepted', 'declined')),
  created_at   TIMESTAMPTZ DEFAULT NOW(),
  CONSTRAINT no_self_friend CHECK (requester_id != recipient_id),
  UNIQUE (requester_id, recipient_id)
);

CREATE INDEX ON friendships (requester_id);
CREATE INDEX ON friendships (recipient_id);

-- RLS: profiles
ALTER TABLE profiles ENABLE ROW LEVEL SECURITY;
CREATE POLICY "profiles_select_all"   ON profiles FOR SELECT USING (true);
CREATE POLICY "profiles_update_own"   ON profiles FOR UPDATE USING (auth.uid() = id);
CREATE POLICY "profiles_insert_trigger" ON profiles FOR INSERT WITH CHECK (auth.uid() = id OR current_setting('role') = 'postgres');

-- RLS: friendships
ALTER TABLE friendships ENABLE ROW LEVEL SECURITY;
CREATE POLICY "friendships_select_own" ON friendships FOR SELECT
  USING (auth.uid() = requester_id OR auth.uid() = recipient_id);
CREATE POLICY "friendships_insert_own" ON friendships FOR INSERT
  WITH CHECK (auth.uid() = requester_id);
CREATE POLICY "friendships_update_recipient" ON friendships FOR UPDATE
  USING (auth.uid() = recipient_id);
CREATE POLICY "friendships_delete_either" ON friendships FOR DELETE
  USING (auth.uid() = requester_id OR auth.uid() = recipient_id);
