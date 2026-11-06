# Piggy – Rechts- und Compliance-Bericht

Stand: 2026-10-21 · Codebasis: Build 18 (app.json), letzter Commit 2026-10-20 · Betreiber: Raphael Kaufmann (Einzelperson, CH)

## Zusammenfassung (Ampel)

1. **BLOCKER:** Datenschutzerklärung ist veraltet und unvollständig (Kontoauszüge/Finanzdaten, Freunde/Splits, Karten-Endziffern, USA-Übermittlung, Rechtsgrundlagen, Aufbewahrung fehlen; Aussagen wie "verschlüsselter Storage" und "Quittungsfoto nicht an KI" sind nicht durchgehend belegt). Sie ist ausserdem in der App nirgends verlinkt (kein Link im Login, die Zeile "Datenschutz" im Profil ist nicht klickbar).
2. **BLOCKER:** Review-Login und Sign in with Apple sind nicht sicher funktionsfähig (Apple-SIWA-Secrets laut VISION.md noch offen, Login zeigt sonst "Apple Sign-In nicht aktiviert"; E-Mail-Signup soll abgeschaltet werden, ein Demo-Account für den Reviewer braucht aber E-Mail-Login). Dazu kommt das Risiko aus Guideline 5.1.1(ix) (Finanz-App von Einzelperson).
3. **WICHTIG:** Server-seitiger Hintergrund-Job (`statement-worker`, Schritt "Artikel einordnen") sendet Artikelnamen und Händler an Anthropic ohne Prüfung der KI-Einwilligung (diese liegt nur lokal in AsyncStorage). Bucket `receipt-images` ist laut Doku öffentlich. Kein Melden/Blockieren (Guideline 1.2), keine Nutzungsbedingungen, keine Lösch-Funktion pro Kontoauszug, kein IBAN-Masking.
4. **WICHTIG:** Zwei Alt-Funktionen (`process-receipt`, `process-statement`) enthalten keinerlei Nutzerprüfung im Code und senden Bilder/PDFs an Anthropic. Nicht aus der App aufgerufen, aber falls deployed, ein offener Proxy (Missbrauch, Kosten, Datenschutz). Ausserdem widerspricht `business/DATA_BUSINESS.md` (Datenverkauf) der Aussage "wir verkaufen keine Daten" und Guideline 5.1.2(i).
5. **OK:** In-App-Kontolöschung vorhanden (inkl. Storage-Dateien, Apple-Token-Widerruf vorbereitet), KI-Einwilligung vor Drittanbieter-Übermittlung, privater Bucket und RLS für Kontoauszüge, Privacy Manifest vorhanden, `ITSAppUsesNonExemptEncryption=false`, kein Tracking/Analytics/Push im Code, Kamera-/Foto-Texte vorhanden, Daten in Zürich (eu-central-2) laut Policy.

**Hinweis:** Dies ist keine Rechtsberatung. Eine Anwältin bzw. ein Anwalt mit Datenschutzerfahrung (nDSG/DSGVO) sollte die Datenschutzerklärung, die Nutzungsbedingungen und die Einwilligungstexte prüfen, bevor die App öffentlich angeboten wird. Aussagen zu Gesetzesartikeln stammen teils aus allgemeinem Wissen und sind mit "(prüfen)" markiert, wo ich sie nicht mit einer Quelle belegen konnte.

---

## 1. Welche Daten die App erhebt und überträgt (aus dem Code belegt)

### 1.1 Befunde je Funktion

| Funktion | Beleg im Code | Was passiert |
|---|---|---|
| Login | `app/(auth)/login.tsx`, `src/lib/googleAuth.ts`, `src/lib/supabase.ts` | Apple (Scopes Name + E-Mail), Google OAuth, zusätzlich E-Mail/Passwort inkl. `signUp`. Session-Token in iOS Keychain (`expo-secure-store`). |
| Quittung scannen | `modules/piggy-receipt-scanner`, `src/lib/ocr.ts`, `app/scan.tsx` | Foto auf dem Gerät; OCR lokal (Apple Vision, `@dariyd/react-native-text-recognition`); optional Apple Foundation Models lokal (`piggy-foundation-models`). |
| KI-Strukturierung | `src/lib/ocr.ts` (Z. ~540ff), `supabase/functions/scan-receipt/index.ts` | Nur **OCR-Text** (nicht das Bild) wird bei Einwilligung an die Edge Function gesendet, diese ruft Anthropic (Claude, Standard `claude-haiku-4-5`) auf. Mit eigenem Key: Gemini direkt aus der App (`callGeminiText`) bzw. in der Edge Function. Der OCR-Text enthält typischerweise Händler, Artikel, Beträge, Kartenendziffern, Kassierer-Nr., Beleg-Nr. |
| Quittungsbild | `app/scan.tsx` Z. 127, 514 | Upload in Bucket `receipt-images` (`{uid}/...`), URL über `getPublicUrl` gespeichert (`receipts.image_url`, `original_image_url`). |
| Kontoauszüge | `src/hooks/useBankStatements.ts`, `supabase/functions/statement-worker`, `_shared/extractors/llm.ts` | Upload (PDF/CSV/CAMT) in privaten Bucket `bank-statements`; Worker (pg_cron jede Minute) zerlegt Seiten, CAMT ohne KI, Text/Scan an Anthropic (Haiku/Sonnet), Ergebnis in `bank_transactions`, Rohausgabe in `statement_pages.raw_output`. |
| Artikel-Einordnung | `_shared/classify/items.ts`, Migration `20261006000005_item_classification.sql` | Worker nimmt **alle** `receipt_items` ohne Unterkategorie (Name, Händler, Kategorie) und sendet sie an Anthropic. Keine Prüfung der Einwilligung, nur Client-seitig in AsyncStorage (`src/lib/aiConsent.ts`). |
| Freunde | `app/friends/*`, `src/hooks/useFriends.ts`, Migration `20260707000000_social_layer.sql` | `profiles` (Anzeigename, `piggy_code` = "PIG-" + Anfang der User-UUID, QR-Code), `friendships`, Realtime. Seit Migration `20260930000001` ist `profiles` für alle angemeldeten Nutzer lesbar. |
| Split/Teilen | `app/split/*`, `src/hooks/useSplits.ts`, `useSharedReceipts.ts` | `splits`, `split_participants`, `split_exclusions`, `shared_receipts`: Empfänger sehen Quittung inkl. Artikel (RLS-Policy `receipt_items_shared_select`). |
| Karten-Matching | TODO.md ("profile_cards"), `ocr.ts::extractPaymentMethod` | Letzte 4 Ziffern der Zahlkarte werden gespeichert und für Freunde sichtbar gemacht ("Möglicherweise von [Name] ···· 5327"). |
| Projekte, Firmen | `app/project/*`, Migration `20260708000001_company_foundation.sql` | Tabellen für B2B existieren (leer, ohne UI). |
| API-Keys der Nutzer | `app/(tabs)/profil.tsx`, Tabelle `user_settings` | `gemini_api_key` und `claude_api_key` im Klartext in der DB (RLS: nur eigene Zeile; Edge Function liest per Service-Role). |
| Zähler/Limit | `scan-receipt`, `check_and_increment_scan`, `app_settings.free_scans_limit` | Tageszähler pro Nutzer. |
| Analytics / Crash / Push / Ads | Suche in `src/`, `app/`, `package.json` | **Keine** Analytics-, Crash-, Werbe- oder Tracking-SDKs. `expo-notifications` ist als Abhängigkeit installiert, wird aber im Code nicht verwendet (keine Push-Funktion). |
| Fotos speichern | `app.json` Plugin `expo-media-library` | Plugin mit englischen Berechtigungstexten eingebunden, im Code kein Aufruf (nicht belegt, dass es gebraucht wird). |

### 1.2 Datenfluss-Tabelle

| Datum | Zweck | Speicherort | Drittland / Empfänger | Aufbewahrung (Ist) |
|---|---|---|---|---|
| E-Mail, Name, Apple-/Google-ID | Konto, Login | Supabase Auth (eu-central-2, Zürich laut Policy) | Apple, Google (Login) | bis Kontolöschung |
| Quittungsfoto (Original + bearbeitet) | Beleg-Archiv | Supabase Storage `receipt-images` (laut Doku öffentlich) | nein | bis Löschung/Kontolöschung (keine Frist) |
| OCR-Text | Strukturierung | flüchtig; Rohtext teils in `receipts` (nicht abschliessend geprüft) | Anthropic (USA) bzw. Google Gemini (mit eigenem Key) | Dienstseitig laut Anbieter (siehe 2.4) |
| Quittungsdaten (Händler, Betrag, Datum, Artikel, Karte-Endziffern, Beleg-Nr., MwSt-Nr., Notizen) | Ausgabenübersicht, Abgleich | Postgres (`receipts`, `receipt_items`) | Artikelnamen + Händler zusätzlich an Anthropic (Klassifizierung, ohne Consent-Prüfung) | bis Löschung |
| Kontoauszug-Datei (PDF/CSV/CAMT) | Import | Storage `bank-statements` (privat, RLS pro Uid) | Seiten/PDF an Anthropic (USA) | **unbegrenzt**, Originaldatei wird nicht nach dem Parsen gelöscht |
| Buchungen (Datum, Betrag, Buchungstext, ggf. Gegenpartei/IBAN) | Abgleich, Finanzübersicht | `bank_transactions`, `statement_pages.raw_output` | Anthropic (beim Einlesen) | unbegrenzt; kein Masking von IBAN/Kartennummern |
| Anzeigename, Piggy-Code | Freunde | `profiles` | für alle angemeldeten Nutzer lesbar | bis Kontolöschung |
| Freundschaften, Splits, Teilen | Kostenaufteilung | Postgres | andere Nutzer (Freunde) | bis Löschung; Verhalten bei Kontolöschung einer Seite offen (Frage 12) |
| Karten-Endziffern | Beleg-Zuordnung unter Freunden | `profile_cards` | Freunde sehen sie | bis Löschung |
| Gemini-/Claude-Key des Nutzers | BYOK | `user_settings` Klartext | Google bzw. Anthropic (vom Nutzer gewollt) | bis Löschung |
| KI-Einwilligung | Nachweis | **nur lokal** (AsyncStorage `piggy.aiConsent.v1`), nicht serverseitig | – | bis App-Löschung |
| Scan-Zähler | Fair-Use-Limit | `profiles`/Zähler | nein | bis Kontolöschung |
| Server-/Funktionslogs | Betrieb | Supabase Logs | Supabase | Supabase-Standard (prüfen) |

---

## 2. Rechtsgrundlagen

### 2.1 Anwendbares Recht
- **nDSG (CH, in Kraft seit 1.9.2023):** Piggy wird von einer Schweizer Privatperson betrieben; sobald Dritte bearbeitet werden (Testflight-Tester, öffentliche Nutzer), ist Raphael Verantwortlicher im Sinne des nDSG. Die Haushaltsausnahme gilt für die Nutzer persönlich, nicht für den Betreiber.
- **DSGVO:** Anwendbar, sobald die App gezielt EU-Nutzer anspricht (Art. 3 Abs. 2 DSGVO; Indizien: Sprachen DE/EN/FR/IT, EUR-Unterstützung, App Store Verfügbarkeit in EU-Ländern). Bei reinem CH-Launch ist die DSGVO nicht zwingend, ein öffentlicher App Store Release in der EU löst sie aber sehr wahrscheinlich aus. Dann Art. 27 DSGVO (EU-Vertreter) prüfen.
- **OR/Steuerrecht:** Belegaufbewahrung (10 Jahre, Art. 958f OR bzw. MWST) trifft Piggy nicht als Betreiber; sie trifft Nutzer mit Buchführungspflicht. Piggy sollte deshalb **keine Aufbewahrungsgarantie** versprechen und Export anbieten (siehe 2.7).

### 2.2 Rechtsgrundlagen je Verarbeitung (Vorschlag)
- Konto, Quittungen, Abgleich, Freunde: Vertragserfüllung bzw. Bearbeitung nach Treu und Glauben (nDSG kennt keine Pflicht zu einer Rechtsgrundlage ausser bei Rechtfertigung einer Persönlichkeitsverletzung, Art. 31); DSGVO Art. 6 Abs. 1 lit. b.
- KI-Übermittlung an Anthropic/Google: Einwilligung (Art. 6 Abs. 1 lit. a DSGVO; nDSG: ausdrückliche Einwilligung, wenn besonders schützenswerte Daten, Art. 6 Abs. 7). Die Einwilligung muss widerrufbar, informiert und **nachweisbar** sein. Heute nur lokal gespeichert.
- Kontoauszüge: Können indirekt besonders schützenswerte Daten enthalten (Gesundheit, Religion, Notlage; siehe `business/LEGAL_KONTOAUSZUEGE.md`). Empfehlung: separate, ausdrückliche Einwilligung vor dem ersten Upload (heute nur der allgemeine KI-Dialog).

### 2.3 Auftragsbearbeitung und Drittland
| Dienstleister | Rolle | Sitz/Ort | Zu tun |
|---|---|---|---|
| Supabase | Auftragsbearbeiter (DB, Storage, Auth, Edge Functions, Logs) | Projekt in AWS eu-central-2 (Zürich) laut Policy; Supabase Inc. USA | DPA von Supabase annehmen/aufbewahren; Region der Edge Functions und Logs prüfen; Unterauftragnehmerliste verlinken |
| Anthropic | Auftragsbearbeiter für Text/PDF | USA | Commercial Terms inkl. DPA prüfen; Datenübermittlung CH→USA (nDSG Art. 16: Garantien, z. B. Standardvertragsklauseln oder Swiss-US DPF-Zertifizierung, **prüfen**); Retention: Zero Data Retention gibt es nur auf Anfrage und nach Freigabe (Quelle unten); Standard-Aufbewahrungsfristen bitte direkt in Anthropics aktuellen Bedingungen nachlesen, die von mir gefundenen Drittquellen widersprechen sich (7 vs. 30 Tage). |
| Google (Gemini, Google Sign-In) | Gemini mit eigenem Key des Nutzers: Nutzer ist Vertragspartner; Sign-In: eigene Verantwortliche | USA/IE | in Policy nennen |
| Apple | Sign in with Apple, App Store, TestFlight | USA | in Policy nennen |
| Cloudflare | im Piggy-Code nicht belegt | – | Falls Web-App/Domain/Tunnel über Cloudflare läuft (Frage 7), in Policy nennen und DPA beachten |
| Expo/EAS | Build-Dienst, keine Nutzerdaten in der App | – | nur falls OTA-Updates (`expo-updates` nicht installiert) |

### 2.4 Pflichtinhalte der Datenschutzerklärung (nDSG Art. 19, ergänzt um DSGVO Art. 13/14)
Identität und Kontakt des Verantwortlichen; Bearbeitungszweck; Empfänger bzw. Kategorien; bei Auslandübermittlung Staat und Garantien; Kategorien erhobener Daten, wenn nicht beim Betroffenen beschafft (Art. 19 Abs. 3). Für DSGVO zusätzlich: Rechtsgrundlagen, Speicherdauer, Betroffenenrechte inkl. Widerruf und Beschwerde bei Aufsichtsbehörde, Vorliegen automatisierter Entscheidungen, EU-Vertreter. Quelle zu Art. 19: law.ch (Abruf 2026-10-21), https://law.ch/lawinfo/datenschutzrecht/datenschutz-prinzipien/informationspflicht/.

**Abgleich mit `public/privacy.html` ("Zuletzt aktualisiert: September 2026"):**
- Vorhanden: Verantwortlicher + E-Mail, Datenkategorien (Konto, Quittungen), KI-Einwilligung mit Anthropic/Gemini, Kontolöschung in der App, Kontakt.
- Fehlt: Kontoauszüge als Datenkategorie (nur im Löschabschnitt erwähnt); Finanz-/Buchungsdaten; Freunde/Profil/Piggy-Code/QR; Splits und Teilen; Karten-Endziffern; Artikel-Einordnung durch KI im Hintergrund; USA als Empfängerstaat und Garantien; Rechtsgrundlagen; Aufbewahrung; Auskunft/Berichtigung/Export/Widerspruch/Beschwerde (nur "Löschen" und "Anfragen an"); Hinweis Drittpersonendaten; Haftungsausschluss KI; Beschwerdemöglichkeit EDÖB; Cloudflare falls relevant.
- Unzutreffend oder riskant: "Quittungsfotos werden in verschlüsseltem Supabase Storage abgelegt" (verschlüsselt at rest, aber Bucket laut Doku öffentlich abrufbar); "Alle Daten ... in der Schweiz" (KI-Verarbeitung in USA, Login-Anbieter); "Das Quittungsfoto selbst wird nicht an KI-Anbieter übermittelt" (stimmt für den aktiven Pfad, aber `process-receipt` und `scan_queue`-Pfad im Code würden Bilder senden, siehe 5); "wir verkaufen keine Daten" (Konflikt mit `business/DATA_BUSINESS.md`); Nutzer ohne Einwilligung: "danach nur lokale Regex" stimmt nicht, weil der Worker Artikel trotzdem klassifiziert.
- Die Policy ist zweisprachig (DE/EN), gut. Es fehlt eine FR/IT-Fassung (nicht Pflicht).

### 2.5 Verzeichnis, DSFA, Meldepflicht
- Bearbeitungsverzeichnis (nDSG Art. 12): Ausnahme für Unternehmen < 250 Mitarbeitende, **sofern** keine umfangreiche Bearbeitung besonders schützenswerter Daten bzw. kein Hochrisiko-Profiling (Verordnung; prüfen). Ein einfaches internes Verzeichnis (diese Tabelle) ist trotzdem empfehlenswert.
- DSFA (nDSG Art. 22): bei Kontoauszügen/KI später sinnvoll (Schwelle laut `LEGAL_KONTOAUSZUEGE.md` Abschnitt 6: einige hundert aktive Nutzer).
- Meldung von Datenschutzverletzungen an EDÖB (nDSG Art. 24): Ablauf vorbereiten, besonders wegen öffentlichem Bucket.

### 2.6 Betroffenenrechte
Auskunft (nDSG Art. 25, Frist in der Regel 30 Tage), Berichtigung, Löschung, Herausgabe/Übertragung (Art. 28). Ist: Kontolöschung in der App vorhanden. Fehlt: Export der eigenen Daten (Quittungen, Buchungen, Bilder), Lösch-Funktion einzelner Kontoauszüge, benannter Prozess für Auskunftsanfragen (E-Mail genügt, aber dokumentieren).

### 2.7 Aufbewahrung von Belegen
Nutzer mit Buchführungspflicht müssen Geschäftsbücher/Belege 10 Jahre aufbewahren (Art. 958f OR, **prüfen** ob auch für ihren Fall); Piggy ist keine revisionssichere Archivlösung. In AGB/Policy festhalten: "Kein Ersatz für gesetzliche Aufbewahrung; Export empfohlen; Daten werden bei Kontolöschung unwiderruflich gelöscht." Für Backups (Supabase, `kdev-backup` der Projektordner enthält keine Nutzerdaten) Aufbewahrungsfrist in der Policy nennen.

---

## 3. Apple App Store

Quelle: App Store Review Guidelines, abgerufen 2026-10-21, https://developer.apple.com/app-store/review/guidelines/ (Seite nennt kein Änderungsdatum; die gefetchten Auszüge bildeten die Grundlage der Zitate unten).

| Punkt | Anforderung | Piggy-Stand | Bewertung |
|---|---|---|---|
| 5.1.1(i) | Datenschutzerklärung-Link in App Store Connect und **in der App** | `public/privacy.html` existiert, Web-Deploy laut VISION.md noch offen; in der App nur nicht klickbare Zeile "Datenschutz" (`profil.tsx` ~Z. 810); kein Link im Login | Blocker |
| 5.1.1(v) | "If your app supports account creation, you must also offer account deletion within the app" | `profil.tsx` → Edge Function `delete-account` löscht Storage + `auth.users` (Cascade, Migration `20260929000001`) | OK; Apple-Token-Widerruf braucht die SIWA-Secrets (`APPLE_TEAM_ID`, Key-ID, Private Key) in Supabase, sonst Antwort `not_configured`/`failed` |
| 5.1.1(ix) | Apps in "highly regulated fields (such as banking and financial services ...) or that require sensitive user information should be submitted by a legal entity ... and not by an individual developer" | Developer-Account läuft vermutlich als Einzelperson; Piggy ist Ausgabenverwaltung mit Kontoauszug-Import, keine Bank/Zahlungsdienst | **Risiko**: Reviewer kann Organisation (D-U-N-S, ggf. Einzelfirma/GmbH) verlangen. Vorab Positionierung "Haushaltsbuch/Quittungsarchiv, kein Finanzdienst" in Review-Notizen. Frage 1. |
| 5.1.2(i) | Offenlegung und **ausdrückliche** Erlaubnis vor Weitergabe an Dritte "including with third-party AI" | `ensureAiConsent` zeigt Alert vor KI-Nutzung, benennt Anthropic und Gemini; Schalter im Profil | OK für Scan und Auszug-Upload; Lücke: Worker-Klassifizierung ohne Server-Check; Consent nicht serverseitig protokolliert |
| 5.1.2 (Datenverkauf) | Weitergabe an Dritte nur "to improve the app or serve advertising" | `business/DATA_BUSINESS.md` plant Verkauf aggregierter Daten | Konflikt, solange der Plan existiert. Vor Umsetzung neu bewerten; bis dahin Policy nicht widersprechen. |
| 4.8 | Wer Google u. Ä. anbietet, muss eine gleichwertige Option mit Datenminimierung anbieten | Sign in with Apple ist vorhanden (`expo-apple-authentication`) | OK, sofern Apple-Login technisch läuft (Secrets/Provider in Supabase aktiv). Heute meldet Login ggf. "nicht aktiviert". |
| 1.2 | Apps mit Nutzerinhalten: Filter, Melden, Blockieren, Kontakt | Freunde mit selbstgewähltem Anzeigenamen, Teilen von Quittungen, Notizen; **keine** Melde-/Blockier-Funktion (Suche in `app/friends`, `useFriends.ts` ohne Treffer), keine Nutzungsbedingungen | Wichtig (siehe 4.3) |
| 3.1.1 / 3.1 | Digitale Features nur über In-App Purchase | v1.0 ohne Abo/IAP (VISION.md "Bewusst nicht in v1.0"); Tageslimit gratis; eigener Key als Alternative | OK. Bei Paywall später: IAP für Scan-Kontingent; "Eigenen API-Key hinterlegen" als Umgehung vorab mit Apple klären |
| Berechtigungstexte | Zweck klar benennen | Kamera: "zum Fotografieren von Quittungen" (deckt QR-Scan für Freunde nicht ab); Fotos: "um Quittungsbilder zu laden"; Plugin `expo-media-library` mit englischem Text, im Code nicht genutzt | Wichtig: Kameratext um "und QR-Codes von Freunden" ergänzen; ungenutztes `expo-media-library` entfernen oder Text deutsch/korrekt |
| Privacy Manifest (App) | `NSPrivacyAccessedAPITypes` mit Required-Reason-Codes; `NSPrivacyCollectedDataTypes` | In `app.json` vorhanden: UserDefaults CA92.1, FileTimestamp C617.1/0A2A.1/3B52.1, SystemBootTime 35F9.1, DiskSpace E174.1/85F4.1; `NSPrivacyTracking=false`; Datentypen E-Mail, Name, User-ID, Fotos, Purchase History, Other Financial Info | Weitgehend OK. Prüfen: (a) ob das gebaute Archiv eine zusammengeführte `PrivacyInfo.xcprivacy` enthält und Apple keine ITMS-91053-Warnungen schickt; (b) eigene Module `piggy-receipt-scanner`, `piggy-foundation-models` haben keine eigene Manifest-Datei gefunden; (c) fehlende Datentypen siehe unten |
| Export Compliance | Verschlüsselung deklarieren | `ITSAppUsesNonExemptEncryption=false` | OK, solange nur HTTPS/OS-Krypto genutzt wird (aktuell der Fall). Bei eigener Krypto neu bewerten. |
| Alterseinstufung | Seit 2025 neuer Fragebogen mit Stufen 4+, 9+, 13+, 16+, 18+ (Frist war 31.1.2026) | Keine Gewalt, Glücksspiel, offene Chats, kein freier Web-Zugriff | Voraussichtlich 4+; ehrlich beantworten, Fragen zu "User-Generated Content/Messaging" prüfen. Quelle: 9to5mac, 2025-07-24, https://9to5mac.com/2025/07/24/apple-notifies-developers-of-new-app-store-age-rating-system/ |
| Review-Account | Demo-Login mit Daten | nicht vorhanden dokumentiert | Blocker, siehe unten |

### 3.1 Test-Account für den Review
Der Reviewer kann Apple-/Google-Login nicht mit eigenem Konto sinnvoll nutzen. Geplant ist, **E-Mail-Signup abzuschalten** (VISION.md). Lösung: Signup in der UI/Supabase abschalten (`disable_signup`), das **Anmelden per E-Mail/Passwort** aber für einen manuell angelegten Demo-Account lassen (Account im Supabase-Dashboard erstellen, Beispielquittungen und ein Beispiel-Kontoauszug mit **erfundenen** Daten befüllen). Zugangsdaten nur im Feld "App Review Information" von App Store Connect hinterlegen, nicht im Repo. Dazu kurze Review-Notiz: Funktion der KI-Einwilligung, wie Kontolöschung gefunden wird, dass der Kontoauszug-Import manuell ohne Bankanbindung erfolgt.

### 3.2 App-Privacy-Angaben ("Nährwertkennzeichen") – ausfüllbare Antworten
Annahme: kein Analytics, kein Tracking, Daten werden nicht für Werbung genutzt und nicht an Datenhändler verkauft.

**Tracking:** "Data Used to Track You" = **Nein** (kein ATT nötig, `NSPrivacyTracking=false`). Änderte sich bei Datenverkauf an Dritte/Datenhändler (Apple zählt das als Tracking-nahe Weitergabe; vorab prüfen).

| Datentyp (Apple-Kategorie) | Erhoben? | Mit Nutzer verknüpft? | Tracking? | Zweck | Anmerkung |
|---|---|---|---|---|---|
| Contact Info: Email Address | Ja | Ja | Nein | App Functionality | Bei Apple "Hide My Email" ggf. Relay-Adresse |
| Contact Info: Name | Ja | Ja | Nein | App Functionality | Apple/Google-Name, Anzeigename |
| Identifiers: User ID | Ja | Ja | Nein | App Functionality | Supabase-UID, Piggy-Code |
| User Content: Photos or Videos | Ja | Ja | Nein | App Functionality | Quittungsbilder |
| User Content: Other User Content | Ja | Ja | Nein | App Functionality | Notizen, Projekte, Anzeigename, Artikelnamen (**fehlt im Manifest**) |
| Financial Info: Other Financial Info | Ja | Ja | Nein | App Functionality | Kontoauszüge, Buchungen |
| Financial Info: Purchase History | Ja | Ja | Nein | App Functionality | Quittungen/Artikel |
| Sensitive Info | Nein (absichtlich nicht abgefragt) | – | – | – | Kontoauszüge können es indirekt enthalten; im Zweifel konservativ prüfen |
| Contacts (Adressbuch) | Nein | – | – | – | Freunde nur per Code/QR, kein Adressbuch-Zugriff |
| Usage Data / Diagnostics | Nein | – | – | – | keine SDKs; Supabase-Serverlogs prüfen |
| Location, Health, Browsing, Search | Nein | – | – | – | |

Weitergabe an Dritte (Anthropic, optional Google) zur Verarbeitung als "Service Provider" ist in den Angaben als Erhebung durch die App zu behandeln; Apple verlangt dafür keinen separaten Eintrag, wohl aber die Konsistenz mit der Policy. Wichtig: Der Datenschutztext im Store muss mit Policy und Manifest übereinstimmen.

---

## 4. Besondere Risiken

### 4.1 Finanzdaten / Kontoauszüge
- Höhere Risikoklasse als Quittungen (vollständiges Ausgabe- und Einkommensprofil, indirekt besondere Personendaten); detailliert in `business/LEGAL_KONTOAUSZUEGE.md` (Ersteinschätzung, gleiche Kernpunkte wie hier).
- **Bankgeheimnis (Art. 47 BankG)** richtet sich an Banken und deren Beauftragte, nicht an Endnutzer-Apps; da Nutzer ihre eigene Datei manuell hochladen und kein Banking-Login besteht, ist Piggy nicht direkter Adressat. **PSD2** ist in der Schweiz nicht anwendbar und für Piggy ohne Kontoinformationsdienst irrelevant. Vorsicht bei künftigem Open Banking oder Screen-Scraping: neue Rechtsprüfung (Lizenzfragen).
- Offene Massnahmen belegt: kein IBAN-/Kartennummern-Masking (kein Treffer in `_shared`/`statement.ts`), keine Lösch-Funktion pro Statement (`useBankStatements.ts` löscht nur Matches), Originaldatei bleibt dauerhaft, `statement_pages.raw_output` speichert Rohausgabe ohne Frist.
- Trennung vom Data-Business sicherstellen (Allowlist-Export, siehe LEGAL_KONTOAUSZUEGE Abschnitt 5).

### 4.2 Belege mit Drittpersonendaten
Quittungen und Auszüge enthalten Namen (TWINT-Zahlungen an Personen), Kassierer-Nr., Kartenendziffern, Adressen, bei Apotheken/Arztrechnungen Gesundheitsbezug. Folgen: (a) Der öffentliche Bucket erhöht das Risiko; (b) Teilen und Splits legen Artikel und Beträge gegenüber Freunden offen; (c) Karten-Endziffern von Freunden sind für Freunde sichtbar. Policy und AGB sollten Nutzer verpflichten, nur eigene bzw. berechtigt erlangte Belege hochzuladen.

### 4.3 Freunde-Funktion (Guideline 1.2)
Piggy hat keine öffentlichen Inhalte und keinen Chat, aber nutzergenerierte Inhalte (Anzeigename, Notizen, geteilte Belege). Da `profiles` für alle angemeldeten Nutzer lesbar ist und der `piggy_code` aus der User-UUID abgeleitet wird (nicht geheim), können Fremde Anzeigenamen sammeln. Mindestmassnahmen: Freund entfernen/blockieren (Anfragen ablehnen existiert nur als Status), "Melden" per E-Mail-Link, veröffentlichte Kontaktadresse (vorhanden in `support.html`), kurze Nutzungsbedingungen mit Verbot missbräuchlicher Namen, Moderationsprozess (manuell, Sperren des Kontos). Optional: Piggy-Code zufällig statt UUID-abgeleitet; `profiles`-Lesezugriff auf Freunde/Code-Suche einschränken.

### 4.4 KI-Fehler und Haftung
Das Modell kann Beträge, Datum, Kategorien und Vorzeichen falsch erkennen (ARCHITECTURE.md: Plausibilitätsprüfungen, Vorschläge nie automatisch angenommen). In der App und den AGB: "Automatisch erkannte Daten können fehlerhaft sein; bitte prüfen"; Haftungsausschluss soweit gesetzlich zulässig (zwingende Haftung für Absicht/grobe Fahrlässigkeit bleibt, Art. 100 OR). Kein Anspruch auf Richtigkeit.

### 4.5 Steuer/Buchhaltung
Piggy ist kein Steuer-, Buchhaltungs- oder Anlageberatungswerkzeug. Hinweis in AGB, Store-Beschreibung und beim Export/MwSt-Feldern ("Keine Steuerberatung, keine revisionssichere Buchführung"). Vorsicht bei Marketingaussagen wie "Steuern" (TODO.md Phase 7: MwSt-Auswertung, Spesen-Export, DATEV/Abacus) – dort eigene Prüfung.

### 4.6 Weitere
- Mindestalter: nirgends geregelt. Vorschlag 16 Jahre (DSGVO-Obergrenze) bzw. mit Eltern-Einwilligung; Alterseinstufung dazu stimmig halten.
- Einwilligung nur lokal gespeichert: bei Geräte-/App-Wechsel verloren, nicht nachweisbar.
- KI-Trainingsdaten: `TODO.md` erwähnt Idee "Trainingsdaten verkaufen"; ohne eigene ausdrückliche Einwilligung und Policy-Änderung nicht zulässig.

---

## 5. Lücken im Code und Setup (belegt)

| # | Befund | Beleg |
|---|---|---|
| L1 | Datenschutzerklärung lückenhaft/veraltet, in App nicht verlinkt | `public/privacy.html`, `app/(auth)/login.tsx` (kein Link), `app/(tabs)/profil.tsx` ~Z. 810 (Zeile ohne Aktion) |
| L2 | Keine Nutzungsbedingungen/AGB, kein Impressum | keine Datei/Seite; `TODO.md` 5.3 offen |
| L3 | SIWA-Secrets und Provider-Konfiguration offen; Login-Fallbacktext "Apple Sign-In nicht aktiviert" | `VISION.md` Abschn. "Release-Pflicht"; `login.tsx` Z. ~64; `delete-account/index.ts` `not_configured` |
| L4 | Demo-/Review-Account fehlt; Konflikt mit geplanter Abschaltung des E-Mail-Signups | `login.tsx` (`signUp`), VISION.md |
| L5 | Hintergrund-Klassifizierung ohne Consent-Prüfung | `claim_items_to_classify` (alle Nutzer), `_shared/classify/items.ts`, Consent nur in `aiConsent.ts` (AsyncStorage) |
| L6 | `receipt-images` laut Doku öffentlicher Bucket, App nutzt `getPublicUrl`; Bucket wird in den Migrationen nicht angelegt (nur `20260930000003` ändert ihn) | `LEGAL_KONTOAUSZUEGE.md`, `TODO.md` 1.5 "public", `app/scan.tsx` Z. 129/516 |
| L7 | `process-receipt` und `process-statement`: kein `getUser`/Auth-Check im Code | `supabase/functions/process-receipt/index.ts`, `process-statement/index.ts` (nur `scan-receipt` und `delete-account` prüfen den Token); ob `verify_jwt` gesetzt ist, ist ohne Zugriff auf Produktion nicht prüfbar |
| L8 | Altpfad `enqueueForEdge` lädt Bild in Bucket `receipts` (`ocr.ts`), Funktion im aktiven Pfad nicht aufgerufen (kein Treffer ausser Definition) | `src/lib/ocr.ts` Z. ~357 |
| L9 | Kontoauszug: kein Masking, keine Statement-Löschung, Original dauerhaft, `raw_output` | siehe 4.1 |
| L10 | Kein Melden/Blockieren (1.2), `profiles` weltweit für Angemeldete lesbar | `app/friends/*`, Migration `20260930000001` |
| L11 | API-Keys der Nutzer im Klartext in der DB | `user_settings`, `profil.tsx` |
| L12 | KI-Einwilligung nur lokal, nicht serverseitig/versioniert | `src/lib/aiConsent.ts` |
| L13 | Kameratext deckt QR-Scan nicht ab; `expo-media-library` mit englischem, im Code ungenutztem Plugin; `expo-notifications` ungenutzt | `app.json`, `package.json` |
| L14 | Privacy-Manifest: "Other User Content" fehlt; eigene Module ohne Manifest | `app.json`, `modules/*` |
| L15 | Kein Export der Nutzerdaten, kein Auskunftsprozess | Code-Suche ohne Treffer |
| L16 | Kein Dokument zu Rollen/Unterauftragsverarbeitern, kein DPA-Nachweis (Anthropic) | `LEGAL_KONTOAUSZUEGE.md` Checkliste offen |
| L17 | Support/Kontakt über Outlook-Adresse einer Privatperson, kein Impressum | `public/support.html`; für CH-Apps ist ein Impressum nur bei E-Commerce gesetzlich zwingend (Art. 3 UWG, prüfen), erleichtert aber die Review |

### 5.1 Priorisierte Handlungsliste

**Muss vor App-Store-Einreichung**
| Nr. | Aufgabe | Aufwand | Wer |
|---|---|---|---|
| 1 | Datenschutzerklärung überarbeiten (Inhalte aus Abschnitt 6), anwaltlich prüfen lassen, unter fester URL veröffentlichen (`privacy.html`/`support.html` deployen) | M | Raphael (Inhalt/Anwalt/Deploy), Entwurf kann ich liefern |
| 2 | Privacy-URL und Support-URL in App Store Connect eintragen; App-Privacy-Angaben nach 3.2 ausfüllen | S | Raphael |
| 3 | Link zur Datenschutzerklärung im Login-Screen und Profil klickbar machen (`Linking.openURL`) | S | Code |
| 4 | SIWA-Secrets in Supabase setzen, Apple-Provider aktivieren, End-to-End testen (Login, Kontolöschung mit Token-Widerruf) | S | Raphael (Dashboard/Apple Developer), Test durch Code-Session |
| 5 | Demo-Account anlegen (Signup serverseitig aus, Login per E-Mail für Demo bleibt), Beispieldaten erfinden, Zugang in App Review Information | S | Raphael |
| 6 | Klärung 5.1.1(ix): Individual- vs. Organisation-Account, Review-Notiz formulieren | S–M | Raphael |
| 7 | Server-seitige Einwilligung: Flag (Zeitpunkt, Version) in `user_settings`; `claim_items_to_classify` und Worker nur für Nutzer mit Consent; sonst Klassifizierung lokal/Regex | M | Code |
| 8 | Alt-Funktionen `process-receipt`/`process-statement` auf Produktion prüfen und **undeployen** oder mit Auth absichern | S | Raphael (Prüfung Dashboard) + Code |
| 9 | Policy-Aussagen angleichen (Bucket, Region, "kein Bild an KI", "verkaufen keine Daten") oder Code anpassen (Bucket privat) | S–M | Raphael/Code |
| 10 | Kamera-Berechtigungstext um QR-Scan ergänzen; `expo-media-library` und `expo-notifications` entfernen, falls ungenutzt (Neubuild nötig) | S | Code |
| 11 | Mindest-Nutzungsbedingungen (inkl. Missbrauchsverbot, KI-Haftungsausschluss, keine Steuerberatung, Alter) mit Link in App | M | Raphael (Entwurf/Anwalt) + Code (Link) |
| 12 | Melden/Blockieren (mind. Freund entfernen/blockieren + "Melden" per mailto) und Kontakt im Profil | M | Code |
| 13 | Separater Einwilligungsdialog vor erstem Kontoauszug-Upload (Zweck, Anthropic USA, Löschmöglichkeit) | S–M | Code |

**Muss vor öffentlichem Launch**
| Nr. | Aufgabe | Aufwand | Wer |
|---|---|---|---|
| 14 | `receipt-images` auf privaten Bucket mit signierten URLs umstellen, bestehende `image_url`-Werte migrieren | M–L | Code + Raphael (Produktions-Migration) |
| 15 | Lösch-Funktion pro Kontoauszug (Zeile, Transaktionen, Matches, Datei) + Option "Original nach Parsing löschen" + Frist für `raw_output` | M | Code |
| 16 | IBAN-/Kartennummern-Masking im Buchungstext vor Speicherung | M | Code |
| 17 | DPA/AVV Anthropic, Supabase annehmen; Übermittlung CH→USA dokumentieren; Retention/ZDR bei Anthropic anfragen | M | Raphael |
| 18 | Datenexport (JSON/CSV, Bilder) und Auskunftsprozess (30 Tage) | M | Code + Raphael (Prozess) |
| 19 | Entscheid zu EU-Nutzern: nur CH-Storefront oder DSGVO-Konformität inkl. Art. 27-Vertreter prüfen | S–M | Raphael |
| 20 | Einfaches Bearbeitungsverzeichnis, Verfahren für Datenpannen (EDÖB-Meldung) | S | Raphael |
| 21 | Nutzer-API-Keys verschlüsseln (Supabase Vault/Spalten-Verschlüsselung) oder Funktion streichen | M | Code |
| 22 | Piggy-Code zufällig statt UUID-abgeleitet; `profiles`-Lesezugriff einschränken | M | Code |
| 23 | Impressum/Betreiberangaben, eigene Kontaktadresse (Domain statt outlook.de) | S | Raphael |
| 24 | Entscheid zum Data-Business: Wenn gewünscht, vorab Rechtsprüfung, neue Einwilligung, Policy und Apple 5.1.2 | L | Raphael |

**Später**
| Nr. | Aufgabe | Aufwand | Wer |
|---|---|---|---|
| 25 | DSFA bei Wachstum (> einige hundert aktive Auszug-Nutzer) | M | Raphael/Anwalt |
| 26 | IAP/Abo-Compliance (3.1.1, Widerruf, Preisangaben) bei Monetarisierung | M | Code + Raphael |
| 27 | Policy zusätzlich auf FR/IT; Cookie/Web-App-Hinweise falls Web-Dashboard öffentlich | S | Raphael |
| 28 | Prüfung Open-Banking-Pläne (Lizenzfragen, Art. 47 BankG, FIDLEG) | L | Anwalt |
| 29 | B2B: Auftragsbearbeitung für Firmen-Kunden, AVV-Vorlage | L | Anwalt |

---

## 6. Entwurf Kernpunkte (keine fertige Vorlage)

### 6.1 Datenschutzerklärung – Stichpunkte
1. Verantwortlicher: Raphael Kaufmann, Adresse (Impressum), Kontakt-E-Mail; ggf. EU-Vertreter.
2. Erhobene Daten: Kontodaten (E-Mail, Name, Apple/Google-ID, Anzeigename, Piggy-Code); Quittungsfotos und erkannte Inhalte (Händler, Betrag, Datum, Artikel, Beleg-/MwSt-Nr., Zahlungsart, Karten-Endziffern, Notizen); Kontoauszüge und Buchungen; Freundeslisten, Splits, geteilte Belege, Projekte; optional eigener API-Key; technische Daten (Server-Logs). Keine Analytics, Werbung, Tracking, Standort, Adressbuch.
3. Zwecke: Archiv/Auswertung, Abgleich Quittung↔Buchung, Kategorisierung, Teilen/Splitten, Kontosicherheit, Fair-Use-Limit, Support.
4. Rechtsgrundlagen (DSGVO, falls anwendbar): Vertrag, berechtigtes Interesse (Sicherheit), Einwilligung (KI).
5. KI-Verarbeitung: Was wird gesendet (OCR-Text, Kontoauszugsseiten, Artikelnamen/Händler zur Einordnung), an wen (Anthropic; Google nur mit eigenem Key), nur nach Einwilligung, Widerruf im Profil, Wirkung des Widerrufs (genauer Wortlaut nach Umsetzung von Aufgabe 7), kein Training laut API-Bedingungen (aktuell mit Quelle nachprüfen), Aufbewahrung beim Anbieter, Fehlerhaftigkeit möglich.
6. Empfänger/Auftragsbearbeiter: Supabase (Region Zürich), Anthropic (USA), Google (Gemini, Sign-In), Apple (SIWA, App Store), ggf. Cloudflare; Freunde als Empfänger bei Teilen.
7. Auslandübermittlung: USA; Garantien (SCC/DPF), Link zu Vertragsgrundlagen.
8. Speicherdauer: bis Kontolöschung bzw. Löschung einzelner Daten; Backups/Logs mit Fristen; Kontoauszug-Original und Rohausgabe (Fristen nach Umsetzung von Aufgabe 15).
9. Sicherheit: TLS, Verschlüsselung at rest, Zugriffsregeln pro Nutzer (RLS), privater Bucket (nach Umsetzung von Aufgabe 14).
10. Rechte: Auskunft, Berichtigung, Löschung (in der App), Export, Widerruf, Widerspruch; Frist 30 Tage; Beschwerde beim EDÖB (CH) bzw. Aufsichtsbehörde (EU).
11. Drittpersonendaten: Nur Belege hochladen, die man selbst verwenden darf; Freunde sehen geteilte Inhalte.
12. Kinder: Mindestalter.
13. Änderungen der Erklärung, Datum, Versionsnummer.

### 6.2 Weitere Texte in der App
- KI-Einwilligung: Aufgliedern in (a) Quittungstext und (b) Kontoauszüge, jeweils mit Empfänger "Anthropic, USA".
- Hinweis beim Scan-Review: "Automatisch erkannt, bitte prüfen".
- Footer-Satz "Deine Daten werden sicher in der Schweiz gespeichert" ändern ("gespeichert in Zürich; KI-Verarbeitung auf Wunsch in den USA").
- Hinweis "Keine Steuer-/Rechtsberatung".

### 6.3 App-Privacy-Angaben
Siehe Tabelle in 3.2; zusätzlich Manifest ergänzen: `NSPrivacyCollectedDataTypeOtherUserContent` (Linked=true, Tracking=false, AppFunctionality).

---

## 7. Offene Fragen an Raphael

1. Läuft das Apple-Developer-Konto als Einzelperson oder Organisation? Bist du bereit, für den öffentlichen Release eine Organisation (Einzelfirma/GmbH, D-U-N-S) zu führen, falls Apple 5.1.1(ix) geltend macht?
2. Soll der Launch nur in der Schweiz oder auch in der EU stattfinden? (DSGVO, EU-Vertreter, Storefront-Auswahl)
3. Welche Postadresse/Impressum willst du veröffentlichen, und gibt es eine neutrale Kontakt-Domain statt `outlook.de`?
4. Ist `receipt-images` im produktiven Projekt tatsächlich öffentlich, und sind `process-receipt` bzw. `process-statement` dort deployed (und mit JWT-Pflicht)? Ich hatte keinen Produktionszugriff.
5. Besteht bereits ein Vertrag/DPA mit Anthropic (Konto-Typ, Region, Retention-Einstellung)? Hast du Supabase-DPA/Region-Einstellungen geprüft (auch für Edge Functions und Logs)?
6. Soll die KI-Klassifizierung der Artikel auch ohne Einwilligung laufen (nein empfohlen), oder soll bei Verweigerung nur lokal gearbeitet werden?
7. Wird Cloudflare (Tunnel, Pages, DNS) für die Piggy-Web-App oder Policy-Seiten genutzt?
8. Bleibt die Web-App öffentlich erreichbar (VPN-freier Zugang laut TODO)? Dann braucht sie Policy-Link, Impressum und ggf. Cookie-Hinweise.
9. Ist das Data-Business (`business/DATA_BUSINESS.md`) noch geplant? Falls ja, wann und mit welcher Einwilligung? Bis dahin: Policy sagt "kein Verkauf".
10. Wie lange sollen Kontoauszug-Originale und Rohausgaben aufbewahrt werden (Vorschlag: Original nach erfolgreichem Parsing löschen, Rohausgabe max. 30 Tage)?
11. Wie viele echte Nutzer sind derzeit auf TestFlight und enthalten deren Daten reale Kontoauszüge? (Auswirkung auf Dringlichkeit der Punkte 5, 7, 14)
12. Was soll beim Löschen eines Kontos mit Freunden/Splits passieren (Splits des Gegenübers löschen, anonymisieren, Schuld bestehen lassen)? Heute Cascade.
13. Mindestalter und Zielgruppe (Studierende, Familien, Firmen)?
14. Sollen Nutzer ihre eigenen API-Keys (Gemini/Claude) behalten dürfen? Falls ja, wie speichern (verschlüsselt)?
15. Möchtest du eine kostenlose Erstberatung mit einer Datenschutz-Kanzlei einplanen (empfohlen vor öffentlichem Launch), und soll ich dafür ein 1-seitiges Briefing vorbereiten?

---

## Quellen (Abruf 2026-10-21)
- Apple, App Store Review Guidelines (1.2, 3.1.1, 4.8, 5.1.1, 5.1.2): https://developer.apple.com/app-store/review/guidelines/
- Apple Age Rating Update (13+/16+/18+, Frist 31.1.2026), 9to5mac vom 2025-07-24: https://9to5mac.com/2025/07/24/apple-notifies-developers-of-new-app-store-age-rating-system/
- Anthropic, API and data retention: https://platform.claude.com/docs/en/manage-claude/api-and-data-retention ; ZDR nur per Freigabe: https://privacy.claude.com/en/articles/8956058-i-have-a-zero-data-retention-agreement-with-anthropic-what-products-does-it-apply-to
- nDSG Art. 19 Informationspflicht (law.ch): https://law.ch/lawinfo/datenschutzrecht/datenschutz-prinzipien/informationspflicht/
- Nicht per Quelle verifiziert, aus allgemeinem Wissen und mit "prüfen" zu behandeln: nDSG Art. 12, 16, 22, 24, 25, 28, 31; Art. 958f OR; Art. 100 OR; Art. 3 UWG; Swiss-US Data Privacy Framework; Anthropic-Standardretention; SCC-Einbindung im Anthropic-DPA.
- Interne Dokumente: `business/LEGAL_KONTOAUSZUEGE.md`, `business/DATA_BUSINESS.md`, `VISION.md`, `ARCHITECTURE.md`, `TODO.md`.
