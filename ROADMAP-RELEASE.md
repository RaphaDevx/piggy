# Piggy – Roadmap bis App-Store-Release

Stand: 2026-10-21. Grundlage: Lesen von Code, Migrationen, Edge Functions, `app.json`, `eas.json`, `VISION.md`, `TODO.md`, `ARCHITECTURE.md`, `docs/`, `business/`, `public/`. Es wurde kein Produktivsystem abgefragt. Was nur aus Doku, nicht aus Code belegt ist, steht als Frage (**?**). Keine Secrets in diesem Dokument.

Begleitdatei: `TODO-APPSTORE.md` (Checkliste, was bis zur Freigabe noch fehlt).

---

## 1. Wo steht das Projekt?

| Punkt | Befund (Beleg) |
|---|---|
| Version / Build | `app.json`: Version 1.0.0, `buildNumber` **18**. Auftrag nennt TestFlight Build 24; `docs/verification/2026-10-20-scanner-handoff.md` nennt TestFlight 23 und „Build 24 nicht gestartet“. **?** Setzt der Mac-Worker die Build-Nummer selbst (`appVersionSource: local` in `eas.json`)? |
| Git | Letzter Commit `9776ac97` (iOS-Scanner wieder Apples Dokumentenscanner). Handoff meldet gestagte, nicht committete Änderungen in `useScanner.ts`; Commit scheiterte am fehlenden Signier-Key (Übergabe läuft nach Regel über `/api/handover`). |
| Funktionsumfang | Scan (Apple Vision lokal → Claude Haiku über `scan-receipt`, optional Gemini mit eigenem Key, Regex-Fallback), Review, Kategorien mit Unterkategorien (ADR 0002), Duplikat-Prüfung (ADR 0003), Kontoauszug-Pipeline (ADR 0001), Abgleich Buchung↔Beleg, Finanzen-Tab, Freunde/Splits/Projekte, Konto löschen, KI-Einwilligung. |
| Scanner | Hängt seit Build 18 (Feedback 17.10.): blauer Haken reagiert nicht. Build 23 mit eigenem Scanner, danach Rückkehr zu Apples Scanner mit 600-ms-Wartezeit (nicht am Gerät abgenommen). |
| Compliance | Privacy Manifest in `app.json` vorhanden, `ITSAppUsesNonExemptEncryption: false`, Kamera-/Foto-Texte vorhanden, Konto-Löschen in App und Edge Function vorhanden. Offen siehe Abschnitt 4 und `TODO-APPSTORE.md`. |
| Tests | Jest-Suiten in `src/lib/*.test.ts`. `VISION.md` nennt 7 rote Parser-Tests (`ocr.test.ts`) – aktueller Stand nicht neu ausgeführt. |
| Doku-Drift | `TODO.md` (Stand 2026-07-08) und `SETUP.md` sind veraltet (z. B. Phase 5 alles „offen“, Pfade `/home/raphael/Piggy`). `.kdev/PROJECT.md` ist ein leeres Gerüst. |

### Die grössten Release-Risiken (belegt)
1. **E-Mail/Passwort-Registrierung ist noch im Login** (`app/(auth)/login.tsx`: `signUp`, `signInWithPassword`), obwohl `VISION.md` „E-Mail-Signup aus“ will. Ein Passwort-Reset existiert nicht (kein `resetPasswordForEmail` im Code).
2. **Bucket `receipt-images` wird öffentlich ausgeliefert** (`getPublicUrl` in `app/scan.tsx:129,516`), die Datenschutzerklärung spricht von „verschlüsseltem Storage“. Quittungsbilder sind per URL ohne Login abrufbar.
3. **Edge Functions `process-receipt` und `process-statement` enthalten keine Nutzerprüfung im Code** (nur `scan-receipt` und `delete-account` prüfen `auth.getUser()`). `process-receipt` wird von der App nicht aufgerufen (Legacy). Ob `verify_jwt` aktiv ist, steht nicht im Repo (keine `supabase/config.toml`).
4. **Datenschutzseite nicht verlinkt in der App**: `profil.tsx` zeigt die Zeile „Datenschutz“ ohne Link (kein `openURL` im Code). Ob `public/privacy.html` bereits öffentlich erreichbar ist, ist nicht belegt (VISION: „Web-Deploy offen“).
5. **Datenschutztext widerspricht dem Stand**: Text erwähnt Login per E-Mail; Kontoauszug-Abschnitt aus `business/LEGAL_KONTOAUSZUEGE.md` fehlt; Aufbewahrungsdauer nicht genannt.
6. **Basistabellen nicht in Migrationen** (`receipts`, `receipt_items`, `user_settings`, `user_usage`, `app_settings`, `profile_cards`, `shared_receipts`, Bucket `receipt-images`): RLS dort ist aus dem Repo nicht belegbar (siehe Abschnitt 3).

---

## 2. Meilensteine bis zum Release

Rollen: **C** = Claude-Session (Projekt, Code/Doku/Tests, committet nicht selbst), **K** = K-Dev-Builder (Signieren, Build, TestFlight über `/api/handover`), **R** = Raphael (Apple/Supabase-Konsole, Entscheidungen, Gerätetests).
Aufwand = reine Arbeitszeit, ohne Apple-Wartezeiten.

| # | Meilenstein | Inhalt | Aufwand | Abhängigkeiten | Abnahmekriterium | Wer |
|---|---|---|---|---|---|---|
| M0 | Baseline klären | Build-Nummer-Logik klären (app.json 18 vs. TestFlight 23/24); gestagte Scanner-Änderung übergeben; Doku-Drift (`TODO.md`, `SETUP.md`) korrigieren; Fragen aus Abschnitt 1 beantworten | 2–3 Std | – | `buildNumber` in `app.json` > letzte TestFlight-Nummer; Git sauber; TODO.md verweist auf diese Roadmap | C, R (Antworten), K (Handover) |
| M1 | Scanner am Gerät abnehmen | Auto-Aufnahme → Haken → Review genau einmal; Abbrechen; mehrfach nacheinander; sonst eigenen Scanner behalten | 0,5 Tag (Test) + 0,5 Tag (Fix bei Misserfolg) | M0, Build auf Gerät | Prüfliste aus `docs/verification/2026-10-20-scanner-handoff.md` Punkt 4 bestanden, dokumentiert in `docs/verification/` | R (Test), C (Fix), K (Build) |
| M2 | Login härten | E-Mail-Signup aus (UI + Supabase-Einstellung), Apple- und Google-Login produktiv (SIWA-Secrets, Google Redirect), Leaked-Password-Protection, Apple-Button nach HIG | 0,5–1 Tag | Supabase-Dashboard-Zugriff (R) | Neuinstallation: Anmeldung nur mit Apple/Google; kein Registrieren per Passwort möglich; Löschen eines Apple-Kontos widerruft das Token (`apple: "revoked"`) | C (Code), R (Dashboard) |
| M3 | Review-Zugang | Demo-/Review-Konto bestimmen (siehe Abschnitt 4), Beispieldaten, Notiz für Review | 0,5 Tag | M2 | Review-Konto kann sich auf frischem Gerät anmelden, sieht Belege, Finanzen-Tab, kann Konto löschen (zweites Wegwerf-Konto) | R, C |
| M4 | Datenschutz & Rechtliches | `privacy.html` aktualisieren (Login-Methoden, Kontoauszüge, Aufbewahrung, Anthropic/Gemini, Region), `support.html` prüfen, deployen, in App verlinken (Profil + Login), Nutzungsbedingungen entscheiden | 1 Tag + Wartezeit Anwalt (**?** gewünscht laut `LEGAL_KONTOAUSZUEGE.md`) | M2 (Texte) | URLs liefern HTTP 200 öffentlich; Link in App öffnet sie; Inhalt deckt alle Datenarten aus dem Privacy Manifest | C (Text/Code), R (Deploy, Anwalt) |
| M5 | Storage & Zugriff absichern | `receipt-images` auf privat + signierte URLs; Nutzerprüfung in `process-receipt`/`process-statement` oder Funktionen entfernen; `companies_insert WITH CHECK (true)` bewerten; fehlende Basistabellen/RLS als Migration dokumentieren (Dump aus Produktion, nur lesend durch R) | 2–3 Tage | M0; R liefert Schema-Dump | Bild-URL ohne Token → 401/403; Test mit zwei Konten: B sieht keine Daten von A; Migrationen reproduzieren das Schema auf leerem Projekt | C (Code/SQL), R (Anwenden in Produktion) |
| M6 | Kontoauszug-Pflichtpunkte | Löschfunktion je Auszug (UI + Backend), separater Einwilligungsschirm vor erstem Upload (Einwilligung gibt es global in `aiConsent.ts`), IBAN-/Kartenmaskierung, Limit pro Nutzer | 2–3 Tage | M5 | Auszug löschen entfernt Datei, `bank_statements`, `statement_pages`, `bank_transactions`; Test mit echten Auszügen (VISION Must) | C, R (echte Auszüge) |
| M7 | Funktionsabnahme v1.0 (VISION „Must“) | Finanzübersicht mit echten Auszügen, Social-Features mit 2 Konten, Regex-Fallback-Tests grün, Duplikat-Flow am Gerät | 2 Tage | M1, M6 | Kriterien aus `VISION.md` Abschnitt 0 „Must“ erfüllt und protokolliert | R (Tests), C (Fixes) |
| M8 | Robustheit & Crash-Tests | Offline/Timeout/Fehlerpfade (leeres Netz, Limit erreicht, KI abgelehnt), Kamera-/Foto-Berechtigung verweigert, Konto-Löschen mit/ohne Apple, Speicherdruck bei grossen Auszügen | 1–2 Tage | M7 | Prüfliste ohne Absturz; Fehlermeldungen verständlich; Crash-Logs in TestFlight leer | C (Tests/Fixes), R (Gerät) |
| M9 | Store-Metadaten | Name, Untertitel, Beschreibung, Keywords, Werbetext, Kategorie, Altersfreigabe, Preis (gratis), Support-/Datenschutz-URL, Screenshots prüfen, App-Privacy-Angaben, Exportcompliance, Review-Notizen | 1 Tag | M3, M4 | Alle Felder in App Store Connect grün; Asset-Prüfung mit Skill `appstore-launch` (`scripts/check-assets.py`) | R (Eingabe), C (Texte, Asset-Check) |
| M10 | Release-Build & Einreichung | Release-Kandidat bauen, TestFlight-Regression (extern 2 Tester), „Zur Prüfung einreichen“ | 0,5 Tag + Apple-Review 1–7 Tage | M1–M9 | Build „Bereit zur Einreichung“, Review-Status „In Prüfung“ | K (Build), R (Einreichen) |
| M11 | Review-Reaktion & Freigabe | Rückfragen beantworten, ggf. neuer Build, manuelle oder automatische Freigabe | 0,5–2 Tage | M10 | Status „Bereit für den Verkauf“ | R, C, K |

**Gesamt:** ca. 12–17 Arbeitstage reine Arbeit (Schätzung, nicht im Repo belegt), plus Apple-Wartezeit. Kritischer Pfad: M0 → M2 → M4 → M9 → M10. M5/M6 laufen parallel zu M3/M4; M1 ist unabhängig und sollte sofort starten (Scanner ist Kernfunktion).

**Bewusst nicht vor Release** (laut `VISION.md`): In-App-Käufe/Abo, B2B-UI, Serien-Scan (Should), Android, PDF-Export.

---

## 3. Datenstruktur

Quelle: `supabase/migrations/*.sql` (20 Dateien), `supabase/functions/*`. **Wichtig:** Die Tabellen `receipts`, `receipt_items`, `user_settings`, `user_usage`, `app_settings`, `profile_cards`, `shared_receipts` und der Bucket `receipt-images` werden von Migrationen nur verändert, nicht angelegt. Ihre Definition und RLS liegen nur in der Produktivdatenbank (nicht geprüft).

### 3.1 Tabellen (aus Migrationen belegt)

| Tabelle | Spalten (Auszug) | Quelle |
|---|---|---|
| `profiles` | id (FK auth.users, CASCADE), display_name, piggy_code (UNIQUE, `PIG-XXXXXXXX`), created_at, processing_mode (`on_device`/`edge`), demo_scans_used, demo_scans_today, demo_scans_reset_date | social_layer, scan_queue, demo_scan_counter, daily_scan_counter (Spalte `gemini_api_key` wurde in security_hardening entfernt) |
| `user_settings` | user_id, gemini_api_key (Klartext-Text) | security_hardening (Anlage nicht in Repo) |
| `friendships` | requester_id, recipient_id, status (pending/accepted/declined), UNIQUE-Paar | social_layer |
| `receipts` | user_id (FK CASCADE), original_image_url, raw_ocr_text, extra_fields (jsonb), company_id, project_id (aus Doku/Policies) | receipts_extra_fields, account_deletion_fks, company_foundation, projects |
| `receipt_items` | receipt_id, tags[] (tags[0] = Hauptkategorie), tags_legacy, subcategory, classified_at, is_adjustment | coarse_categories, subcategories, item_classification, statement_pipeline |
| `bank_statements` | user_id, account_label, account_type, file_name, file_path, mime_type, period_start/end, stage (queued/extracting/reconciling/done/error), page_count, pages_done, pages_failed, … | bank_statements, statement_pipeline |
| `statement_pages` | statement_id, user_id, page_no, status, lines (jsonb), extractor, model, tokens_in/out, raw_output | statement_pipeline, throttle_raw |
| `bank_transactions` | statement_id, user_id, booking_date, amount, currency, description, match_status | bank_statements |
| `receipt_matches` | user_id, transaction_id, receipt_id, score, match_type | bank_statements |
| `match_suggestions` | Vorschläge tip/fx/discount/partial/other, UNIQUE (transaction_id, receipt_id) | statement_pipeline |
| `rematch_queue` | user_id, Warteschlange für Neuabgleich | statement_pipeline |
| `scan_queue` | user_id, image_path, status, result (jsonb), error_msg | scan_queue (App nutzt sie in 2 Stellen) |
| `receipt_duplicate_decisions` | user_id, receipt_a, receipt_b (CHECK a<b) | receipt_duplicate_review |
| `private.receipt_merge_history` | Audit: target_before, source_before (jsonb) – für Clients gesperrt | receipt_duplicate_review |
| `splits`, `split_participants`, `split_exclusions` | payer_id, Beträge, Teilnehmer, ausgeschlossene Artikel | splits |
| `projects`, `project_members` | Projekt und Mitglieder | projects |
| `companies`, `company_members`, `expense_submissions` | B2B-Fundament, UI nicht vorhanden | company_foundation |
| `profile_cards`, `shared_receipts` | Kartenendziffern, geteilte Belege | im Code genutzt, **nicht in Migrationen angelegt** |
| `app_settings` | key/value (z. B. `free_scans_limit` Default 10, `active_model` Default `claude-haiku-4-5-20251001`, `statement_*`) | von Functions gelesen, Anlage nicht im Repo |
| `user_usage` | user_id (FK CASCADE) | account_deletion_fks |

Funktionen/RPC (belegt): `accept_match_suggestion`, `enqueue_rematch`, `kick_statement_worker`, `retry_statement`, `merge_receipt_duplicates`, `merge_receipt_scan`, `save_separate_receipt_scan`, `keep_receipts_separate`, `delete_reviewed_receipt`, `check_and_increment_scan` (nur service_role), `get_my_project_ids`, `claim_*` (SKIP LOCKED). Cron: `statement-worker` jede Minute (pg_cron + pg_net, Secret im Vault).

### 3.2 Storage-Buckets

| Bucket | Zweck | Zugriff | Beleg |
|---|---|---|---|
| `bank-statements` | Originaldateien der Kontoauszüge, Pfad `{uid}/…` | **privat**, Policies select/insert/delete nach `foldername[1] = auth.uid()` | Migration 20260614 |
| `receipt-images` | Quittungsfotos | Code nutzt `getPublicUrl` → **öffentlich ausgelieferte URLs**; erlaubte MIME-Typen jpeg/jpg/png/heic/webp; Policies nicht im Repo | Migration 20260930000003, `app/scan.tsx` |
| `receipts` | wird nur von `delete-account` in `USER_BUCKETS` gelistet | **?** existiert der Bucket / wird er benutzt? In `app/` und `src/` kein Zugriff gefunden | `delete-account/index.ts` |

### 3.3 Edge Functions

| Function | Aufgabe | Nutzerprüfung im Code |
|---|---|---|
| `scan-receipt` | OCR-Text → strukturierte Quittung (Claude Haiku oder Gemini mit eigenem Key), Tageslimit über `check_and_increment_scan` | ja (`auth.getUser`) |
| `statement-worker` | Hintergrundverarbeitung Kontoauszüge, Auth per Header `x-worker-secret` (Vault) | Secret-basiert |
| `delete-account` | Storage leeren, Apple-Token widerrufen, `auth.users` löschen (Cascade) | ja |
| `process-statement` | Kontoauszug-Parsing (Claude); von `src/lib/statement.ts` aufgerufen | **nein im Code** (? `verify_jwt`) |
| `process-receipt` | ältere Bildverarbeitung, von der App nicht aufgerufen | **nein im Code** |

### 3.4 Wo liegen Nutzerdaten?

- **Supabase-Projekt** `dsucrlaonnmpwopgidcj` (aus `CLAUDE.md`). Datenschutztext und Profil-Tab behaupten Region `eu-central-2` (Schweiz). **?** Nicht aus dem Repo prüfbar – im Supabase-Dashboard bestätigen (Projekt-Region).
- **Datenbank:** Belege, Artikel, Buchungen, Profile, Freundschaften, Splits.
- **Storage:** `receipt-images`, `bank-statements`.
- **Gerät:** Supabase-Session in `expo-secure-store` (`src/lib/supabase.ts`); KI-Einwilligung nur lokal in AsyncStorage (`piggy.aiConsent.v1`, nicht serverseitig protokolliert).
- **Drittanbieter:** Anthropic erhält Quittungstext und Kontoauszug-Dokumente (Einwilligung nötig); Google Gemini nur mit eigenem Key des Nutzers.
- **R2 (geplant):** Im Repo **kein** Code und keine Doku zu Cloudflare R2 für Belege gefunden (Suche nach „R2/Cloudflare“ ohne Treffer). Es handelt sich um eine Planung ausserhalb des Projekts. Voraussetzung dafür wäre: Upload über signierte URLs, Anpassung von `original_image_url` (heute volle öffentliche URL), Anpassung von `delete-account` (löscht nur Supabase-Buckets!), Datenschutztext (neuer Auftragsverarbeiter).

### 3.5 Zeilenschutz (RLS) und Lücken

Belegt in Migrationen: RLS aktiv auf `bank_*`, `receipt_matches`, `statement_pages`, `rematch_queue`, `match_suggestions`, `scan_queue`, `profiles`, `friendships`, `splits*`, `projects*`, `companies*`, `expense_submissions`, `receipt_duplicate_decisions`, `private.receipt_merge_history`. Security-Audit vom 2026-09-29 (Migration `security_hardening`) hat mehrere Fehlpolicies behoben.

| Lücke / Risiko | Beleg | Empfehlung |
|---|---|---|
| `receipt-images` öffentlich, Datenschutztext sagt „verschlüsselt“ | `getPublicUrl`; `LEGAL_KONTOAUSZUEGE.md` Checkliste „mittelfristig privat“ | Vor Release privat + signierte URLs (M5) |
| RLS von `receipts`, `receipt_items`, `user_settings` (enthält Gemini-Key im Klartext), `shared_receipts`, `profile_cards`, `app_settings`, `user_usage` nicht im Repo | nur `ALTER`-Statements vorhanden | Schema-Dump als Baseline-Migration, RLS einzeln prüfen; Supabase-Advisors durch R laufen lassen |
| `profiles_select_authenticated USING (true)`: jeder angemeldete Nutzer liest alle Profile (Name, Code, Spalten wie `demo_scans_*`) | security_hardening | akzeptabel für Freundesuche, Datensparsamkeit prüfen (View mit nur Name/Code) |
| `companies_insert WITH CHECK (true)` | security_hardening | gewollt (jeder darf Firma anlegen); ohne UI risikoarm, trotzdem notieren |
| `process-receipt`/`process-statement` ohne Nutzerprüfung im Code | siehe 3.3 | JWT prüfen oder Function entfernen |
| Gemini-Key im Klartext in `user_settings` | security_hardening | Verschlüsselung/Vault prüfen oder Funktion „eigener Key“ nicht bewerben |
| `delete-account` CORS `*` | Code | Funktion verlangt Bearer-Token; geringes Risiko |
| `bank_statements`-Policies `for all` mit `auth.uid() = user_id` | Migration | in Ordnung |

### 3.6 Aufbewahrung und Löschung

- **Konto löschen:** App (`profil.tsx` → `delete-account`): löscht Dateien in `receipts`, `receipt-images`, `bank-statements` unter `{uid}/…`, widerruft Apple-Token (nur mit frischem Authorization Code), löscht `auth.users`; Fremdschlüssel mit `ON DELETE CASCADE` bzw. `SET NULL` (Migration 20260929). **Lücken:** (a) bei Apple-Konto ohne Code wird „failed“ gemeldet, das Löschen läuft trotzdem; (b) Bilder, deren Pfad nicht mit `{uid}/` beginnt, bleiben liegen – **?** Pfadschema in `app/scan.tsx` prüfen; (c) `private.receipt_merge_history` hat CASCADE (ok).
- **Einzellöschung:** Beleg löschen vorhanden (RPC `delete_reviewed_receipt`, Detailseite). **Fehlt:** Löschen eines einzelnen Kontoauszugs (UI + Backend), im Code nicht auffindbar.
- **Aufbewahrungsfrist:** nirgends definiert (weder Code noch Datenschutztext). `statement_pages.raw_output` und `raw_ocr_text` halten Rohdaten dauerhaft. Vorschlag: Frist festlegen, Rohdaten nach Verarbeitung begrenzen.
- **Export:** nicht vorhanden (laut `LEGAL_KONTOAUSZUEGE.md` nachrüstbar, bei Anfrage erforderlich).

---

## 4. Login & Konten

| Thema | Ist-Stand (Beleg) | Soll / Lücke |
|---|---|---|
| Methoden | Apple (`signInWithIdToken`), Google (`runGoogleOAuth`, Konten verknüpfen/trennen im Profil), **E-Mail + Passwort** (`login.tsx`) | `VISION.md`: nur Apple/Google. E-Mail-Registrierung im UI und im Supabase-Dashboard abschalten. Guideline 4.8: Da Google angeboten wird, muss Apple angeboten werden – ist vorhanden. |
| Apple-Button | eigener `TouchableOpacity` mit Ionicons-Logo | HIG verlangt Apple-Stil; `AppleAuthenticationButton` aus `expo-apple-authentication` verwenden |
| Fehlermeldung | Text im Apple-Fehlerfall: „Bitte verwende E-Mail oder Google“ | anpassen, wenn E-Mail wegfällt |
| Session | `supabase-js` mit `expo-secure-store`, `autoRefreshToken`, `persistSession` (`src/lib/supabase.ts`); `useAuth` hört auf `onAuthStateChange` | gut; Sitzungsdauer/Refresh-Token-Rotation im Dashboard **?** |
| Konto löschen | in der App (`profil.tsx`), Edge Function `delete-account`, Apple-Token-Widerruf. Voraussetzung: Secrets `APPLE_TEAM_ID`, `APPLE_SIWA_KEY_ID`, `APPLE_SIWA_PRIVATE_KEY` in Supabase (Status **?**, `VISION.md` listet es als offen) | Guideline 5.1.1(v) erfüllt, sobald SIWA-Secrets gesetzt und am Gerät mit Apple-Konto getestet |
| Passwort-Reset | nicht vorhanden | entfällt, wenn E-Mail-Login entfällt (`TODO.md`); sonst Pflicht |
| Test-Account für Review | nicht vorhanden. Apple prüft Anmeldung per Apple/Google; ein Review-Konto ohne E-Mail/Passwort ist nicht möglich | Entscheidung nötig (**Frage**): (A) E-Mail-Login nur für das Review-Konto behalten (z. B. ausgeblendet, Zugang über versteckte Geste) – erfordert Dashboard-Sperre für Neuregistrierung; (B) Apple-ID des Reviewers kann sich per SIWA selbst registrieren, Review-Notiz erklärt es und App enthält Beispieldaten-Button. Empfehlung: B plus Demo-Daten, weil Apple SIWA selbst testen kann. |
| Datenschutz | Text nennt E-Mail-Login (veraltet) | nach M2 angleichen |

---

## 5. Backend / Kosten

Gebucht laut Auftrag: **Supabase Pro**. Das ist aus dem Repo nicht prüfbar (**?** Plan im Dashboard bestätigen). Zahlen unten aus `business/COSTS_SCALING.md` (Stand der Doku unklar, Preise vor Entscheidung neu prüfen).

| Punkt | Free | Pro (gebucht) |
|---|---|---|
| Preis | 0 | ca. 25 USD/Monat |
| DB | 500 MB | 8 GB |
| Storage | 1 GB | 8 GB, danach ca. 0,021 USD/GB |
| Edge Functions | 500 000 Aufrufe/Monat | 2 Mio. |
| Pausierung | Free-Projekte werden bei Inaktivität pausiert (Risiko für Review-Zeitraum) | nein |
| Backups | keine zuverlässigen | tägliche Backups |

Hinweise:
- **Review-Zeitraum:** Pro vermeidet Pausierung des Projekts während der Apple-Prüfung. Bis zum Release beibehalten.
- **Cron-Last:** `statement-worker` läuft jede Minute (pg_cron); verbraucht Funktionsaufrufe auch ohne Nutzer (~43 000/Monat, eigene Rechnung).
- **LLM-Kosten:** Quittung ca. 0,3 Rp. mit Haiku (Doku); Kontoauszüge (Seite für Seite, Text → Haiku, Scan → Sonnet) sind der Kostentreiber. Es gibt kein Limit pro Nutzer für Auszüge (`VISION.md`: „Limit für Kontoauszug-Analyse“ offen). Vor Release ein tägliches/monatliches Limit setzen, sonst offene Kostenfalle.
- **Gratis-Scans:** `free_scans_limit` Default 10 pro Tag (`app_settings`); bei Abuse greift nur das Tageslimit pro Nutzer, keine Registrierungsbremse bei Apple/Google-Neukonten.
- **Storage-Wachstum:** Bilder in Originalqualität (Doku: 2–4 MB). `expo-image-manipulator` ist installiert; ob Kompression aktiv ist, **?** in `scan.tsx` prüfen.

### R2-Migration der Belege (geplant, nicht im Repo)
| Schritt | Aufwand | Anmerkung |
|---|---|---|
| Entscheid, ob vor oder nach v1.0 | – | Empfehlung: **nach** Release. Vor Release nur `receipt-images` privat machen (M5). |
| Upload-Pfad (Edge Function stellt signierte R2-URL aus, App lädt direkt hoch) | 1–2 Tage | Auth nur über Supabase-JWT |
| Auslieferung per signierter URL (kurze Gültigkeit) statt öffentlicher URL | 0,5 Tag | `original_image_url` heute als volle URL gespeichert → auf Objekt-Schlüssel umstellen |
| Bestandsmigration der bestehenden Bilder | 0,5–1 Tag | Skript durch R/K-Dev ausführen (Produktivzugriff) |
| `delete-account` erweitern (R2-Objekte löschen) | 0,5 Tag | sonst DSGVO/Apple-Verstoss |
| Datenschutztext (neuer Auftragsverarbeiter, Region) | 0,5 Tag | Cloudflare-Region/Datenhaltung klären |
| Videos | – | **?** Piggy speichert keine Videos (kein Treffer im Code); gemeint ist vermutlich ein anderes Projekt. |

Aufwand R2 gesamt: ca. 4–5 Tage.

---

## 6. Offene Fragen an Raphael

1. Welche Build-Nummer ist aktuell in TestFlight, und setzt der Worker `buildNumber` selbst?
2. Plan, Region und Zustand des Supabase-Projekts (Pro? `eu-central-2`?), und sind die SIWA-Secrets gesetzt?
3. Review-Konto: Variante A oder B (Abschnitt 4)?
4. Sind `privacy.html` und `support.html` bereits öffentlich erreichbar, unter welcher URL?
5. Wird `process-receipt` noch gebraucht, oder kann die Function gelöscht werden?
6. Existiert der Bucket `receipts` tatsächlich?
7. Anwaltsprüfung für Kontoauszüge vor Release oder Funktion zunächst ausblenden?
