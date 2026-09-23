alter table public.profiles
  add column if not exists demo_scans_today      integer not null default 0,
  add column if not exists demo_scans_reset_date date;
