-- B2B Foundation: companies, members, expense submissions
-- Tabellen existieren, UI folgt später. Nichts an bestehenden Tabellen geändert ausser receipts.company_id.

CREATE TABLE companies (
  id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name        TEXT NOT NULL,
  slug        TEXT UNIQUE NOT NULL,
  invite_code TEXT UNIQUE NOT NULL DEFAULT upper(substring(replace(gen_random_uuid()::text, '-', ''), 1, 8)),
  created_at  TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE company_members (
  id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  company_id UUID NOT NULL REFERENCES companies(id) ON DELETE CASCADE,
  user_id    UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
  role       TEXT NOT NULL DEFAULT 'member' CHECK (role IN ('admin', 'accountant', 'member')),
  joined_at  TIMESTAMPTZ DEFAULT NOW(),
  UNIQUE (company_id, user_id)
);

CREATE TABLE expense_submissions (
  id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  receipt_id   UUID NOT NULL REFERENCES receipts(id) ON DELETE CASCADE,
  company_id   UUID NOT NULL REFERENCES companies(id) ON DELETE CASCADE,
  submitter_id UUID NOT NULL REFERENCES auth.users(id),
  category     TEXT CHECK (category IN ('meal', 'transport', 'accommodation', 'material', 'other')),
  note         TEXT,
  status       TEXT NOT NULL DEFAULT 'pending'
                 CHECK (status IN ('pending', 'approved', 'rejected', 'reimbursed')),
  reviewed_by  UUID REFERENCES auth.users(id),
  reviewed_at  TIMESTAMPTZ,
  submitted_at TIMESTAMPTZ DEFAULT NOW(),
  UNIQUE (receipt_id, company_id)
);

-- Nullable company context on receipts (no data loss, fully backwards compatible)
ALTER TABLE receipts ADD COLUMN company_id UUID REFERENCES companies(id);

CREATE INDEX ON company_members (user_id);
CREATE INDEX ON company_members (company_id);
CREATE INDEX ON expense_submissions (company_id, status);
CREATE INDEX ON expense_submissions (submitter_id);

-- RLS
ALTER TABLE companies ENABLE ROW LEVEL SECURITY;
ALTER TABLE company_members ENABLE ROW LEVEL SECURITY;
ALTER TABLE expense_submissions ENABLE ROW LEVEL SECURITY;

-- companies: sichtbar für Mitglieder
CREATE POLICY "companies_select" ON companies FOR SELECT USING (
  EXISTS (SELECT 1 FROM company_members cm WHERE cm.company_id = id AND cm.user_id = auth.uid())
);
-- Jeder kann eine Firma erstellen (wird dann automatisch Admin)
CREATE POLICY "companies_insert" ON companies FOR INSERT WITH CHECK (true);
-- Nur Admins können Firma bearbeiten
CREATE POLICY "companies_update" ON companies FOR UPDATE USING (
  EXISTS (SELECT 1 FROM company_members cm WHERE cm.company_id = id AND cm.user_id = auth.uid() AND cm.role = 'admin')
);

-- company_members
CREATE POLICY "cm_select" ON company_members FOR SELECT USING (
  user_id = auth.uid() OR
  EXISTS (SELECT 1 FROM company_members cm2 WHERE cm2.company_id = company_id AND cm2.user_id = auth.uid())
);
CREATE POLICY "cm_insert" ON company_members FOR INSERT WITH CHECK (
  -- Admins können Mitglieder hinzufügen, oder man tritt selbst bei (via Invite-Code, geprüft in App-Logik)
  EXISTS (SELECT 1 FROM company_members cm WHERE cm.company_id = company_id AND cm.user_id = auth.uid() AND cm.role = 'admin')
  OR user_id = auth.uid()
);
CREATE POLICY "cm_delete" ON company_members FOR DELETE USING (
  user_id = auth.uid() OR
  EXISTS (SELECT 1 FROM company_members cm WHERE cm.company_id = company_id AND cm.user_id = auth.uid() AND cm.role = 'admin')
);

-- expense_submissions
CREATE POLICY "es_select" ON expense_submissions FOR SELECT USING (
  submitter_id = auth.uid() OR
  EXISTS (SELECT 1 FROM company_members cm WHERE cm.company_id = company_id AND cm.user_id = auth.uid() AND cm.role IN ('admin', 'accountant'))
);
CREATE POLICY "es_insert" ON expense_submissions FOR INSERT WITH CHECK (
  submitter_id = auth.uid() AND
  EXISTS (SELECT 1 FROM company_members cm WHERE cm.company_id = company_id AND cm.user_id = auth.uid())
);
CREATE POLICY "es_update" ON expense_submissions FOR UPDATE USING (
  -- Einreicher kann eigene pending Einreichungen zurückziehen
  (submitter_id = auth.uid() AND status = 'pending') OR
  -- Admin/Accountant kann genehmigen/ablehnen
  EXISTS (SELECT 1 FROM company_members cm WHERE cm.company_id = company_id AND cm.user_id = auth.uid() AND cm.role IN ('admin', 'accountant'))
);
