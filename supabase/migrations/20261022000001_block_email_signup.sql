-- Neue Konten nur über Apple/Google. E-Mail-Anmeldung bleibt für bestehende Konten
-- (App-Review-Konto) möglich; nur das Anlegen per E-Mail wird serverseitig abgelehnt.
-- Ein Trigger statt Auth-Hook: braucht keinen Dashboard-Schritt und ist reproduzierbar.
-- Für Admin-Seeds: `set local piggy.allow_email_signup = 'on';` in derselben Transaktion.
create or replace function private.block_email_signup()
returns trigger
language plpgsql
security definer
set search_path = ''
as $$
begin
  if new.raw_app_meta_data ->> 'provider' = 'email'
     and current_setting('piggy.allow_email_signup', true) is distinct from 'on' then
    raise exception 'E-Mail-Registrierung ist deaktiviert' using errcode = '42501';
  end if;
  return new;
end
$$;

revoke all on function private.block_email_signup() from public, anon, authenticated;

drop trigger if exists block_email_signup on auth.users;
create trigger block_email_signup
  before insert on auth.users
  for each row execute function private.block_email_signup();
