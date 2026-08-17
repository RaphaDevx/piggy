-- Kontoabgleich: Kontoauszüge / Kreditkarten-Abrechnungen hochladen,
-- Buchungen extrahieren und mit Quittungen abgleichen (Doppelbuchungs-Check).

create table if not exists bank_statements (
  id            uuid primary key default gen_random_uuid(),
  user_id       uuid not null references auth.users(id) on delete cascade,
  account_label text not null,
  account_type  text not null check (account_type in ('debit', 'credit')),
  file_name     text,
  period_start  date,
  period_end    date,
  status        text not null default 'processing' check (status in ('processing', 'done', 'error')),
  error_message text,
  created_at    timestamptz not null default now()
);

create table if not exists bank_transactions (
  id            uuid primary key default gen_random_uuid(),
  statement_id  uuid not null references bank_statements(id) on delete cascade,
  user_id       uuid not null references auth.users(id) on delete cascade,
  booking_date  date not null,
  amount        numeric not null,
  currency      text not null default 'CHF',
  description   text not null,
  match_status  text not null default 'unmatched' check (match_status in ('unmatched', 'matched', 'ignored')),
  created_at    timestamptz not null default now()
);

create table if not exists receipt_matches (
  id             uuid primary key default gen_random_uuid(),
  user_id        uuid not null references auth.users(id) on delete cascade,
  transaction_id uuid not null references bank_transactions(id) on delete cascade,
  receipt_id     uuid not null references receipts(id) on delete cascade,
  score          numeric not null,
  match_type     text not null default 'auto' check (match_type in ('auto', 'manual')),
  created_at     timestamptz not null default now(),
  unique (transaction_id, receipt_id)
);

create index if not exists bank_transactions_user_idx on bank_transactions (user_id, currency, booking_date);
create index if not exists bank_transactions_statement_idx on bank_transactions (statement_id);
create index if not exists receipt_matches_transaction_idx on receipt_matches (transaction_id);
create index if not exists receipt_matches_receipt_idx on receipt_matches (receipt_id);

-- RLS: Nutzer sehen/ändern nur ihre eigenen Daten
alter table bank_statements   enable row level security;
alter table bank_transactions enable row level security;
alter table receipt_matches   enable row level security;

create policy "bank_statements_own" on bank_statements
  for all using (auth.uid() = user_id) with check (auth.uid() = user_id);

create policy "bank_transactions_own" on bank_transactions
  for all using (auth.uid() = user_id) with check (auth.uid() = user_id);

create policy "receipt_matches_own" on receipt_matches
  for all using (auth.uid() = user_id) with check (auth.uid() = user_id);

-- Storage: privater Bucket für Kontoauszug-Originaldateien (im Gegensatz zu receipt-images)
insert into storage.buckets (id, name, public)
values ('bank-statements', 'bank-statements', false)
on conflict (id) do nothing;

create policy "bank_statements_storage_select" on storage.objects
  for select using (bucket_id = 'bank-statements' and (storage.foldername(name))[1] = auth.uid()::text);

create policy "bank_statements_storage_insert" on storage.objects
  for insert with check (bucket_id = 'bank-statements' and (storage.foldername(name))[1] = auth.uid()::text);

create policy "bank_statements_storage_delete" on storage.objects
  for delete using (bucket_id = 'bank-statements' and (storage.foldername(name))[1] = auth.uid()::text);
