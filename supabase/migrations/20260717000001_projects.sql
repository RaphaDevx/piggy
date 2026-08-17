-- Projects & Project Members
-- Quittungen können einem Projekt (z.B. Roadtrip) zugeordnet werden.

CREATE TABLE projects (
  id          UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  name        TEXT NOT NULL,
  emoji       TEXT DEFAULT '📍',
  description TEXT,
  created_by  UUID REFERENCES auth.users(id) ON DELETE CASCADE NOT NULL,
  currency    TEXT DEFAULT 'CHF',
  created_at  TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE project_members (
  id          UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  project_id  UUID REFERENCES projects(id) ON DELETE CASCADE NOT NULL,
  user_id     UUID REFERENCES auth.users(id) ON DELETE CASCADE NOT NULL,
  invited_by  UUID REFERENCES auth.users(id),
  joined_at   TIMESTAMPTZ DEFAULT NOW(),
  UNIQUE(project_id, user_id)
);

-- Ersteller wird automatisch Mitglied
CREATE OR REPLACE FUNCTION add_project_creator_as_member()
RETURNS TRIGGER LANGUAGE plpgsql AS $$
BEGIN
  INSERT INTO project_members (project_id, user_id, invited_by)
  VALUES (NEW.id, NEW.created_by, NEW.created_by);
  RETURN NEW;
END;
$$;

CREATE TRIGGER trg_project_creator_member
  AFTER INSERT ON projects
  FOR EACH ROW EXECUTE FUNCTION add_project_creator_as_member();

-- project_id auf receipts (nullable, rückwärtskompatibel)
ALTER TABLE receipts ADD COLUMN project_id UUID REFERENCES projects(id) ON DELETE SET NULL;
CREATE INDEX idx_receipts_project_id ON receipts(project_id) WHERE project_id IS NOT NULL;

-- ── RLS ──────────────────────────────────────────────────────────────────────

ALTER TABLE projects ENABLE ROW LEVEL SECURITY;
ALTER TABLE project_members ENABLE ROW LEVEL SECURITY;

-- Security-Definer-Funktion: liest project_members ohne RLS
-- WICHTIG: Verhindert infinite recursion wenn pm_select project_members liest
CREATE OR REPLACE FUNCTION get_my_project_ids()
RETURNS SETOF UUID
LANGUAGE SQL SECURITY DEFINER STABLE AS $$
  SELECT project_id FROM project_members WHERE user_id = auth.uid();
$$;

-- projects: sichtbar für Mitglieder
CREATE POLICY "project_select" ON projects FOR SELECT
  USING (id IN (SELECT get_my_project_ids()));

CREATE POLICY "project_insert" ON projects FOR INSERT
  WITH CHECK (auth.uid() = created_by);

CREATE POLICY "project_update" ON projects FOR UPDATE
  USING (created_by = auth.uid());

CREATE POLICY "project_delete" ON projects FOR DELETE
  USING (created_by = auth.uid());

-- project_members: sichtbar für alle Mitglieder desselben Projekts
-- MUSS über Security-Definer-Funktion gehen – NIEMALS direkt SELECT FROM project_members in USING()!
CREATE POLICY "pm_select" ON project_members FOR SELECT
  USING (project_id IN (SELECT get_my_project_ids()));

CREATE POLICY "pm_insert" ON project_members FOR INSERT
  WITH CHECK (project_id IN (SELECT id FROM projects WHERE created_by = auth.uid()));

CREATE POLICY "pm_delete" ON project_members FOR DELETE
  USING (project_id IN (SELECT id FROM projects WHERE created_by = auth.uid()) OR user_id = auth.uid());

-- receipts: Projektmitglieder können Quittungen des Projekts lesen
-- Ebenfalls über Security-Definer – direkte Abfrage auf project_members wäre rekursiv!
CREATE POLICY "receipt_project_select" ON receipts FOR SELECT
  USING (
    project_id IS NOT NULL AND
    project_id IN (SELECT get_my_project_ids())
  );
