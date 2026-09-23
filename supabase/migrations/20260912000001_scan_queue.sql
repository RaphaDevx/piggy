-- scan_queue: Warteschlange für Batch-Uploads
create table public.scan_queue (
  id            uuid primary key default gen_random_uuid(),
  user_id       uuid references auth.users(id) on delete cascade not null,
  image_path    text not null,                     -- Storage-Pfad
  status        text not null default 'pending'    -- pending | processing | done | failed
                check (status in ('pending','processing','done','failed')),
  result        jsonb,                              -- ParsedReceipt JSON nach Verarbeitung
  error_msg     text,
  created_at    timestamptz default now(),
  processed_at  timestamptz
);

alter table public.scan_queue enable row level security;
create policy "User sieht eigene Queue" on public.scan_queue
  for all using (auth.uid() = user_id);

-- processing_mode in profiles
alter table public.profiles
  add column if not exists processing_mode text not null default 'edge'
    check (processing_mode in ('on_device','edge')),
  add column if not exists gemini_api_key  text;  -- BYOK, optional
