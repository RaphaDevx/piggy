# Piggy — Build Instructions

**iOS builds run on MacBook only** (Xcode 26.6 / SwiftPM 6.2 required).
See `/home/raphael/K-Dev/CLAUDE.md` for full build rules.

## Quick Build

```bash
# Trigger via Dashboard (Linux):
curl -X POST http://localhost:4200/api/build \
  -H 'Content-Type: application/json' \
  -d '{"project":"piggy","platform":"ios","profile":"local"}'

# Watch log:
ssh macbook 'tail -f /tmp/piggy_build_mac.log'
```

## DO NOT

- ❌ Run `xcodebuild` on iMac — Xcode 16.4 cannot build Expo SDK 53+ (SwiftPM 6.1 vs required 6.2)
- ❌ Run `eas build` without `--local` (triggers cloud build instead of MacBook)
- ❌ Run `npm start` / `expo start` for building (that's Metro, not a build)

## App Info

- Bundle ID: `com.heartbeat.piggy`
- Supabase project: `dsucrlaonnmpwopgidcj`
- App Store: ascAppId `6802367826`

## Architektur-Regeln

Vor Änderungen an Finanzlogik, Kategorien, Abgleich oder Kontoauszug-Import: `ARCHITECTURE.md` und `docs/adr/` lesen.

**Kopplungen:**
- Finanzlogik liegt nur in `supabase/functions/_shared/finance-core/`; `src/lib/*` re-exportiert. App und `statement-worker` nutzen dieselben Dateien (Imports mit `.ts`).
- Kontoauszüge laufen über `statement-worker` (pg_cron + Vault-Secret), nie synchron in der App. Drossel/Modelle in `app_settings.statement_*`.
- Vorschläge (`match_suggestions`) werden nie automatisch angenommen.
