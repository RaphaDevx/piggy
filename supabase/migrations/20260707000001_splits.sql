-- Expense splitting

CREATE TABLE splits (
  id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  receipt_id   UUID NOT NULL REFERENCES receipts(id) ON DELETE CASCADE,
  payer_id     UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
  total_amount NUMERIC(10,2) NOT NULL,
  currency     TEXT NOT NULL DEFAULT 'CHF',
  note         TEXT,
  created_at   TIMESTAMPTZ DEFAULT NOW(),
  UNIQUE (receipt_id)
);

CREATE TABLE split_participants (
  id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  split_id   UUID NOT NULL REFERENCES splits(id) ON DELETE CASCADE,
  user_id    UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
  amount     NUMERIC(10,2) NOT NULL,
  settled    BOOLEAN NOT NULL DEFAULT false,
  settled_at TIMESTAMPTZ,
  UNIQUE (split_id, user_id)
);

-- Which items a participant is excluded from (for transparent breakdown)
CREATE TABLE split_exclusions (
  id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  split_id        UUID NOT NULL REFERENCES splits(id) ON DELETE CASCADE,
  user_id         UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
  receipt_item_id UUID NOT NULL REFERENCES receipt_items(id) ON DELETE CASCADE,
  UNIQUE (split_id, user_id, receipt_item_id)
);

CREATE INDEX ON splits (payer_id);
CREATE INDEX ON splits (receipt_id);
CREATE INDEX ON split_participants (split_id);
CREATE INDEX ON split_participants (user_id);

-- RLS
ALTER TABLE splits ENABLE ROW LEVEL SECURITY;
ALTER TABLE split_participants ENABLE ROW LEVEL SECURITY;
ALTER TABLE split_exclusions ENABLE ROW LEVEL SECURITY;

-- splits: visible to payer and all participants
CREATE POLICY "splits_select" ON splits FOR SELECT USING (
  auth.uid() = payer_id OR
  EXISTS (SELECT 1 FROM split_participants sp WHERE sp.split_id = id AND sp.user_id = auth.uid())
);
CREATE POLICY "splits_insert" ON splits FOR INSERT WITH CHECK (auth.uid() = payer_id);
CREATE POLICY "splits_delete" ON splits FOR DELETE USING (auth.uid() = payer_id);

-- split_participants: visible if you're payer or participant in same split
CREATE POLICY "sp_select" ON split_participants FOR SELECT USING (
  user_id = auth.uid() OR
  EXISTS (SELECT 1 FROM splits s WHERE s.id = split_id AND s.payer_id = auth.uid())
);
CREATE POLICY "sp_insert" ON split_participants FOR INSERT WITH CHECK (
  EXISTS (SELECT 1 FROM splits s WHERE s.id = split_id AND s.payer_id = auth.uid())
);
-- Payer can settle anyone; participant can settle themselves
CREATE POLICY "sp_update" ON split_participants FOR UPDATE USING (
  user_id = auth.uid() OR
  EXISTS (SELECT 1 FROM splits s WHERE s.id = split_id AND s.payer_id = auth.uid())
);

-- split_exclusions
CREATE POLICY "se_select" ON split_exclusions FOR SELECT USING (
  user_id = auth.uid() OR
  EXISTS (SELECT 1 FROM splits s WHERE s.id = split_id AND s.payer_id = auth.uid())
);
CREATE POLICY "se_insert" ON split_exclusions FOR INSERT WITH CHECK (
  EXISTS (SELECT 1 FROM splits s WHERE s.id = split_id AND s.payer_id = auth.uid())
);
