# ADR 0005: Belege auf Cloudflare R2

Status: Backend und Datenkopie am 25.11.2026 aktiviert; App-Update noch auszuliefern. Ursprünglicher Vorschlag 19.11.2026.

## Kontext

Quittungsbilder und Kontoauszug-Dateien liegen in Supabase Storage (`receipt-images` 303 Dateien / 669 MB, `receipts` 12 / 16 MB, `bank-statements`). Diese historischen Zahlen allein belegen keine ausgeschöpfte Quote. Aktuelle Messung am 25.11.: 333 Objekte / 739.794.292 Bytes (705,5 MiB), Datenbank ca. 31 MiB. Abgerechnete Organisationsquoten sind separat zu prüfen. Datenbank, Auth und Edge Functions sind klein und bleiben auf Supabase. Nur die Dateien sollen nach R2 (S3-kompatibel, privat), damit das Abo gekündigt werden kann.

## Entscheidung

1. **Gleiche Pfade.** App und Datenbank behalten `<user_id>/...` (`receipts.image_url`, `original_image_url`, `scan_queue.image_path`, `bank_statements.file_path` bleiben unverändert). Abweichung nur intern: R2 hat einen Bucket, der Objektschlüssel lautet `<supabase-bucket>/<pfad>`. Grund: ein R2-Bucket, keine Kollision zwischen den drei Supabase-Buckets, Löschen pro Nutzer per Präfix `<bucket>/<uid>/`.
2. **Edge Function `storage-url`** (`supabase/functions/storage-url`, Logik in `_shared/storage/`). Prüft `auth.getUser()`, erlaubt nur Pfade unter `<eigene uid>/` (kein `..`, keine leeren Segmente, kein Backslash), liefert vorsignierte R2-URLs für GET, PUT und DELETE, Gültigkeit höchstens 600 Sekunden. Ein fremder Pfad lehnt die ganze Anfrage mit 403 ab. Secrets nur aus `Deno.env`: `R2_ACCOUNT_ID`, `R2_ACCESS_KEY_ID`, `R2_SECRET_ACCESS_KEY`, `R2_BUCKET`.
   - **Freigaben:** Die vorhandenen Storage-Policies (`receipt_images_own_*`, `bank_statements_storage_*`) schränken auf den eigenen Ordner ein. Geteilte Belege (`shared_receipts`) und Projekte geben Bilder heute nicht frei, nur die Datenbankzeilen. Die Function bildet das ab: nur eigene Pfade. Die `receipt_images_own_*`-Policies stehen nicht im Repo (nur im Kommentar von `20261022000002`); das Verhalten ist deshalb aus dem Code abgeleitet und gegen das echte Projekt zu bestätigen.
3. **Adapter `src/lib/objectStorage.ts`** mit `upload`, `getSignedUrls`/`getSignedUrl`, `remove` und Schalter `EXPO_PUBLIC_STORAGE_BACKEND` = `supabase` (expliziter Rückweg) | `r2` | `r2-with-fallback` (Standard für neue Builds).
   - `r2-with-fallback`: Lesen zuerst R2 (die Function prüft per HEAD, ob das Objekt existiert), bei 404 Supabase; ist die Function nicht erreichbar, ebenfalls Supabase. Neue Dateien ausschliesslich nach R2. Fehler beim R2-Upload bleiben sichtbar und schreiben nicht unbemerkt nach Supabase zurück. Löschen in beiden.
   - Umgestellt: `receiptImages.ts`, `app/scan.tsx` (2 Uploads), `useBankStatements.ts`, `ocr.ts`. `getPublicUrl` wird nirgends mehr benutzt.
   - URL-Cache in `receiptImages.ts` folgt der Gültigkeit (R2: 10 Minuten, Supabase: 1 Stunde).
4. **`delete-account`** löscht zusätzlich alle R2-Objekte des Nutzers (Liste und Löschen je Präfix), sofern die R2-Secrets gesetzt sind. Das Supabase-Löschen bleibt für die Übergangszeit.
5. **`statement-worker`** liest Kontoauszüge über `_shared/storage/read.ts` je nach Function-Secret `STORAGE_BACKEND` (gleiche drei Werte). `scan-receipt` liest keine Dateien; `process-receipt` und `process-statement` existieren im Repo nicht mehr.
6. **Migration `scripts/migrate-storage-to-r2.mjs`**: listet pro Bucket, kopiert nach R2, vergleicht Anzahl, Grösse und MD5/ETag, schreibt einen JSON-Bericht ohne Schlüssel, löscht nichts in Supabase, `--dry-run`, idempotent.

## Folgen

- Mehr Latenz beim Anzeigen (ein Function-Aufruf pro Batch, im Fallback-Modus zusätzlich ein HEAD je Bild).
- Edge-Function-Aufrufe und R2-Operationen verursachen Kosten (R2 ohne Egress-Gebühren, Free-Tier prüfen).
- R2 CORS muss für die Web-App (PUT/GET) konfiguriert werden.
- Der native SwiftUI-Client verwendet seit 25.11. `ObjectStorageService.swift` für Quittungsbilder und Kontoauszüge. Standard `r2-with-fallback`: neue Uploads nur R2, Lese-Fallback für noch nicht übertragene Dateien alter App-Versionen. Installierte Altversionen können bis zu ihrem Update weiter in Supabase schreiben. Vor Abschaltung nochmals synchronisieren.
- Datenschutz: Bilder liegen künftig bei Cloudflare (Jurisdiktion und DPA prüfen, siehe `LEGAL-COMPLIANCE.md` 2.3). Keine Rechtsberatung.

## Einführung

1. R2-Bucket anlegen (privat, Jurisdiktion bewusst wählen), CORS setzen, Zugriffsschlüssel mit Rechten nur auf diesen Bucket.
2. Function-Secrets setzen (`R2_*`, `STORAGE_BACKEND=r2-with-fallback`), Functions `storage-url`, `delete-account`, `statement-worker` bereitstellen.
3. Migration mit `--dry-run`, dann echt; Bericht prüfen.
4. App-Build mit `EXPO_PUBLIC_STORAGE_BACKEND=r2-with-fallback`, danach (wenn alles in R2 ist) `r2`.
5. Erst nach Prüfung und Backup: Supabase-Dateien löschen, Abo kündigen. Das Skript löscht nie.

## Rollback

- App: `EXPO_PUBLIC_STORAGE_BACKEND=supabase` (neuer Build; bis dahin deckt `r2-with-fallback` beide Seiten ab). Function: `STORAGE_BACKEND=supabase` setzen (Secret, ohne Deploy).
- Die Migration verändert Supabase nicht; solange dort nichts gelöscht ist, ist Rollback verlustfrei. In der Übergangszeit in R2 neu geschriebene Objekte (nur R2) müssen vor einem Rollback zurückkopiert werden (Skript in umgekehrter Richtung, noch nicht vorhanden).
- Nach dem Löschen in Supabase ist R2 die einzige Kopie: vorher separat sichern.

## Änderung 2026-11-19: EU-Jurisdiktion
Ein Bucket mit EU-Jurisdiktion ist nur über `<konto>.eu.r2.cloudflarestorage.com` erreichbar. Das Secret `R2_JURISDICTION=eu` schaltet den Endpunkt um (`eu` oder `fedramp`, sonst Standard). Die Jurisdiktion lässt sich nach Anlage des Buckets nicht mehr ändern, deshalb vor dem Anlegen festlegen. Folgeplan: `docs/umzug-eigenes-backend.md`.

## Umsetzung und Nachweis 25.11.2026

Bestehender EU-Bucket `piggy-receipts`, öffentliche Domains deaktiviert, keine Custom Domains. Separater Bucket-Objektschlüssel mit nur Object Read/Write, in kvault unter piggy/R2_ACCESS_KEY_ID und R2_SECRET_ACCESS_KEY, nie in Clients. Secrets in Supabase gesetzt. `storage-url`, `delete-account`, `statement-worker` bereitgestellt; vorhandene JWT-Gateway-Einstellungen beibehalten. Kein Auth-/DB-Umzug.

333 Dateien, 739.794.292 Bytes nach R2 kopiert; keine fehlenden Objekte, keine Grössen-/MD5-Abweichungen. Bericht ausserhalb Git unter `/storage/Public/K-Dev/archive/piggy-r2-migrate-2026-11-25T13-39-48-018Z.json`. Supabase-Dateien bleiben vollständig erhalten.

Fehler im vorbereiteten S3-Adapter behoben: signierte HEAD-/DELETE-Anfragen müssen auch tatsächlich mit HEAD/DELETE gesendet werden. Deno-Typprüfung für WebCrypto-/BodyInit-ArrayBuffer-Kompatibilität ergänzt.

Rückweg ist weiterhin explizit, keine automatische vollständige Backend-Umschaltung: `STORAGE_BACKEND=supabase` bzw. `EXPO_PUBLIC_STORAGE_BACKEND=supabase` erfordert erneute App-Auslieferung. R2-Neudateien sind vor einem solchen Rückweg zurückzukopieren.
