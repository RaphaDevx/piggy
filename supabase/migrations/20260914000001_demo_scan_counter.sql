-- Demo-Scan-Counter: zählt Scans mit Server-Demo-Key (ohne eigenen Gemini-Key)
alter table public.profiles
  add column if not exists demo_scans_used integer not null default 0;
