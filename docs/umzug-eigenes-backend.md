# Umzugsplan: Piggy auf eigenes Backend (weg von Supabase Cloud)

Stand 2026-11-19. Entwurf zur Entscheidung, nichts davon ist umgesetzt. Voraussetzung ist Phase 1 (Belege auf R2, siehe `adr/0005-belege-auf-r2.md`).

## Warum, und warum nicht sofort
- Phase 1 reicht, um das Pro-Abo zu kündigen: Die Datenbank passt ohne Belege in die Free-Stufe.
- Phase 2 lohnt sich, wenn Piggy länger in der Testphase bleibt, wenn man generell unabhängig von Supabase sein will oder wenn alles lokal laufen soll.
- Preis dafür: Ausfälle des Heimanschlusses sind Ausfälle der App (Apple-Review, Guideline 2.1, «unable to sign in»). Die Strategie (ADR 0012, Dashboard) hält das bis ca. 50 Nutzer für vertretbar.

## Ausgangslage (aus dem Code geprüft)
- **App** nutzt Supabase Auth (Apple- und Google-ID-Token, Passwort, Identitäten verknüpfen), 10 RPC-Aufrufe, 2 Function-Aufrufe, einen Realtime-Kanal (`src/hooks/useFriends.ts`), Belege über R2 (nach Phase 1).
- **Edge Functions:** `scan-receipt`, `statement-worker`, `delete-account`, `revenuecat-webhook`, `native-finance`, `storage-url`.
- **Secrets der Functions:** `ANTHROPIC_API_KEY`, `APPLE_CLIENT_ID`, `APPLE_SIWA_KEY_ID`, `APPLE_SIWA_PRIVATE_KEY`, `APPLE_TEAM_ID`, `REVENUECAT_WEBHOOK_AUTH`, `SERVICE_ROLE_KEY`, `SUPABASE_ANON_KEY`, `SUPABASE_SERVICE_ROLE_KEY`, `SUPABASE_URL`, `STORAGE_BACKEND`, `R2_*`.
- **Datenbank:** 25 Migrationen, `pg_cron` (Statement-Worker jede Minute), `pg_net`, Supabase Vault (`statement_worker_secret`).
- **Feste Projektadresse** steht in `supabase/migrations/20261006000001_statement_pipeline.sql` und in Skripten und Doku (`scripts/test-scan-pipeline.js`, `CLAUDE.md`, `SETUP.md`, `.env`).

## Lücke gegenüber der Vorlage K-Fitness
`K-Fitness/backend` hat nur `db`, `auth`, `rest`, `gateway` (Caddy) und `tunnel`. Piggy braucht zusätzlich:
1. **Edge-Functions-Laufzeit** (z. B. `supabase/edge-runtime`) mit Anbindung an das Gateway und JWT-Prüfung.
2. **pg_cron, pg_net und Vault** in der Datenbank. Das Image `supabase/postgres` bringt sie mit, `pg_cron` braucht aber einen Eintrag in `shared_preload_libraries`. Noch nicht geprüft.
3. **Realtime** für einen Kanal. Entweder Container `supabase/realtime` oder die Freundesliste per Abfrage im Intervall.
4. **Storage** entfällt (R2).

## Bausteine
- **API-Domain** `api.piggy.kaufmannadvisory.ch` als weitere Route am zentralen Tunnel (`127.0.0.1:<Port>`), kein neuer Tunnel. Studio nur im Tailnet.
- **Gleiche Schlüssel mitnehmen:** JWT-Secret, Anon- und Service-Key des bestehenden Projekts übernehmen (die Anon-Key-Kennung in `.env` ist HS256, also das alte Verfahren mit einem Secret). Dann bleiben vorhandene Anmeldungen gültig, und die App muss nur die neue Adresse kennen.
- **Nutzerkonten:** Schema `auth` mit Passwort-Hashes und Identitäten per `pg_dump` mitnehmen. Apple- und Google-Anbieter in GoTrue neu konfigurieren (Client-IDs, Apple-Schlüssel).
- **Daten:** Schema `public` plus Daten, danach `pg_cron`-Job und Worker-Adresse auf die neue Adresse setzen.
- **Webhooks:** RevenueCat-Webhook-Adresse umstellen, gleiches Auth-Secret.
- **Secrets:** im Tresor unter `piggy/...` ablegen, Namen in `.kdev/keys.json` und der Schlüssel-Matrix führen.
- **Sicherung:** nächtlicher `pg_dump -Fc` über `kdev-backup`, zusätzlich nach R2. Wiederherstellung einmal proben, bevor es ernst wird.

## Reihenfolge
0. Phase 1 fertig und verifiziert (Belege auf R2, Swift-Client umgestellt).
1. **Stack aufbauen** (Staging, nicht öffentlich): Vorlage K-Fitness kopieren, Edge Runtime und Realtime ergänzen, Erweiterungen prüfen.
2. **Probe-Wiederherstellung:** Dump des echten Projekts einspielen, `auth`-Konten prüfen, Migrationen und Cron laufen lassen.
3. **Functions deployen** und mit `scripts/test-scan-pipeline.js` gegen Staging testen (Scan, Kontoauszug, Konto löschen, Webhook).
4. **Domain und Route** anlegen, Probe-Build (TestFlight intern) mit neuer Adresse, auf dem Gerät testen: Apple-Login, Google-Login, Scan, Beleg anzeigen, Konto löschen.
5. **Umschalten:** Schreibsperre, letzter Dump, Wiederherstellung, Adresse in App und Webhook umstellen, neue App-Version über K-Dev.
6. **Beobachten** (mindestens zwei Wochen): Supabase-Projekt bleibt im Free-Plan als Rückweg bestehen.
7. **Abschalten** des Supabase-Projekts erst nach Sicherung und Freigabe.

## Rückweg
- Dump des neuen Backends nach Supabase Cloud einspielen und die Adresse in der App zurückstellen. Vorhandene App-Versionen zeigen auf die neue Adresse, deshalb muss die API-Domain nach einem Umzug per DNS umgebogen werden können (Regel 1 der Strategie).
- Ältere TestFlight-Builds zeigen auf die Supabase-Adresse. Solange das Cloud-Projekt läuft, funktionieren sie weiter. Daten, die danach auf dem neuen Backend entstehen, fehlen dort. Deshalb im Umschaltmoment ein klarer Stichtag, am besten Force-Update.

## Offene Entscheidungen (Raphael)
1. Realtime: Container oder Abfrage im Intervall?
2. Wie lange darf Piggy ausfallen, bevor ein verwalteter Anbieter besser ist (Schwelle, z. B. Nutzerzahl oder Store-Release)?
3. Force-Update beim Umschalten ja oder nein?
4. Später «alles lokal»: R2 ebenfalls ersetzen (z. B. durch MinIO)? Dafür fehlt im Code noch eine freie Endpunkt-Einstellung neben `R2_JURISDICTION`. Die baue ich, sobald das ansteht.

## Nicht geprüft
Ob `pg_cron` im Image ohne Änderung startet, wie die Edge Runtime JWTs im Gateway prüft, ob Apple-Login mit anderer Domain ohne neue Apple-Konfiguration läuft (Redirect- und Dienst-IDs), die Realtime-Last, die Native-Swift-Konfiguration der Adresse. Diese Punkte sind im Schritt 1 bis 4 zu klären, nicht vorab zu raten.
