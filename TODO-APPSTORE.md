# Piggy – App-Store-Checkliste (was noch fehlt)

Stand: 2026-10-21. Bundle `com.heartbeat.piggy`, ASC-App-ID 6802367826. Belege stammen aus dem Code im Projektordner; nichts wurde in App Store Connect oder Supabase geprüft. **Frage** = nicht aus Dateien entscheidbar. Zusammenhang und Reihenfolge: `ROADMAP-RELEASE.md`.
Guideline-Nummern beziehen sich auf die App Store Review Guidelines.

---

## A. Raphael muss selbst tun

### A1. App Store Connect – Pflichtfelder
- [ ] Vereinbarungen: Paid-Apps-Agreement, Steuer- und Bankangaben (auch bei kostenloser App Voraussetzung für spätere Käufe; **Frage:** reicht für eine rein kostenlose App der Status „Free Apps“? In ASC unter „Business“ prüfen). Beleg: `VISION.md` „Bewusst nicht in v1.0: In-App-Käufe“.
- [ ] App-Name, Untertitel, Beschreibung, Keywords, Werbetext, Kategorie (Finanzen oder Produktivität, **Frage**) eintragen. Texte vorbereiten lässt sich mit Skill `appstore-launch`.
- [ ] Support-URL eintragen. Datei vorhanden: `public/support.html`; **Frage:** unter welcher öffentlichen URL ist sie erreichbar (`VISION.md`: „Web-Deploy offen“)? Guideline 1.5.
- [ ] Datenschutz-URL eintragen. Datei vorhanden: `public/privacy.html`; gleiche Deploy-Frage. Guideline 5.1.1(i).
- [ ] Marketing-URL (optional) festlegen.
- [ ] Altersfreigabe-Fragebogen ausfüllen (keine nutzergenerierten öffentlichen Inhalte; Freunde/Splits sind privat – **Frage:** zählt das als „User-generated content / Messaging“?). Guideline 1.2.
- [ ] Preis: Gratis (Stufe 0) wählen und Länder/Regionen festlegen.
- [ ] Copyright-Zeile und Kontaktdaten des Reviews (Name, Telefon, E-Mail) eintragen.
- [ ] Export-Compliance: In `app.json` ist `ITSAppUsesNonExemptEncryption: false` gesetzt; in ASC bestätigen, dass nur Standard-HTTPS/Systemverschlüsselung verwendet wird. Guideline 5 / US-Exportrecht.
- [ ] Werbe-ID (IDFA): „Nein“ angeben. Beleg: `NSPrivacyTracking: false` in `app.json`, kein Tracking-SDK in `package.json`.
- [ ] Inhaltsrechte (Content Rights): angeben, dass keine Drittinhalte verwendet werden (Logo/Icon eigene Erstellung bestätigen).

### A2. App-Privacy („Nährwertkennzeichnung“)
- [ ] Datentypen in ASC deckungsgleich mit Privacy Manifest erfassen: E-Mail, Name, Nutzer-ID, Fotos, Kaufverlauf, Sonstige Finanzdaten (alle „mit Nutzer verknüpft“, kein Tracking). Beleg: `app.json` → `privacyManifests.NSPrivacyCollectedDataTypes`.
- [ ] **Frage:** Kontoauszüge und Buchungstexte (Finanzdaten) und Quittungstext, der an Anthropic geht: als „Sonstige Finanzinformationen“/„Nutzerinhalte“ plus Weitergabe an Drittanbieter ausweisen? Das Manifest enthält „Nutzerinhalte“ nicht – Abgleich mit Claude-Session (siehe B2).

### A3. Screenshots & Medien
- [ ] Screenshots hochladen. Vorhanden in `screenshots/appstore/`: 6.1" (1179×2556), 6.7" (1290×2796), 6.9" (1320×2868), je 4 Motive. Für ASC genügt aktuell der grösste Satz (6.9"); aktuelle Pflichtgrössen mit Skill `appstore-launch` prüfen.
- [ ] Inhalt der Screenshots gegen den aktuellen Stand prüfen: sie stammen vom 2026-08/09 (Dateien), zeigen Home/Quittungen/Profil/Projekte, **nicht** den Finanzen-Tab (Kernversprechen v1.0) oder den Scan-Ablauf. Neue Aufnahmen nötig (**Frage:** gewünscht?).
- [ ] Kein Drittlogo, keine echten Personendaten oder echten Belege im Bild (Guideline 5.1.1, 2.3).
- [ ] App-Icon 1024×1024 ohne Transparenz kontrollieren (`assets/icon.png` ist 1024×1024; Alphakanal und Endgültigkeit des Designs prüfen – `SETUP.md` nennt es noch „Placeholder“). Guideline 2.3.
- [ ] App-Vorschauvideo (optional).

### A4. Review-Informationen
- [x] (2026-10-22, Variante A umgesetzt: Konto + Notizen docs/APPSTORE-REVIEW-NOTES.md, Zugang ~/Public/ToDo/piggy-review-zugang.txt; Eintrag in ASC steht aus) Review-Zugang bereitstellen (Guideline 2.1 / 5.1.1): die App erlaubt Login nur über Apple/Google/E-Mail (`app/(auth)/login.tsx`). **Entscheidung nötig:** Variante A (E-Mail-Review-Konto mit Passwort) oder Variante B (Reviewer registriert sich per Sign in with Apple) – Details `ROADMAP-RELEASE.md` Abschnitt 4. Zugangsdaten **nur** im ASC-Feld „Anmeldeinformationen“, nie im Repo.
- [ ] Review-Notizen schreiben: Funktion der App, KI-Einwilligungsdialog erklären, Kontoauszug-Import mit Beispieldatei, Konto löschen unter Profil → „Konto löschen“, Kamera nur für Quittungen und QR-Codes.
- [ ] Beispiel-Kontoauszug (anonymisiert, CSV oder PDF) für den Reviewer bereitstellen (ASC Attachment). Keine echten Daten.
- [ ] „Version freigeben“: manuell oder automatisch wählen.

### A5. Supabase / Google / Apple-Konsolen (nur Raphael hat Zugriff)
- [ ] Supabase → Authentication: **E-Mail-Provider deaktivieren** bzw. Registrierung sperren (Soll laut `VISION.md`). **Frage:** Variante A oder B (A4).
- [ ] Supabase → Authentication: Provider Apple und Google aktiviert; „Allow manual linking“ aktiv (benötigt `linkIdentity`, siehe `TODO.md` Zeile 53–61); Leaked-Password-Protection an (nur relevant, wenn E-Mail bleibt).
- [ ] Supabase → Edge-Function-Secrets für `delete-account`: `APPLE_TEAM_ID`, `APPLE_SIWA_KEY_ID`, `APPLE_SIWA_PRIVATE_KEY`, optional `APPLE_CLIENT_ID` setzen. Beleg: `supabase/functions/delete-account/index.ts`. Ohne sie meldet die Function `apple: not_configured` und das Apple-Token wird nicht widerrufen (Guideline 5.1.1(v), Apple-Vorgabe zum Widerruf).
- [ ] Apple Developer → Sign in with Apple Key (.p8) erzeugen und nur als Secret hinterlegen.
- [ ] Google Cloud Console: Redirect-URI `https://<projekt>.supabase.co/auth/v1/callback` ergänzen (Beleg `TODO.md` Zeile 59–60).
- [ ] Supabase: Plan = Pro bestätigen, Region bestätigen (Datenschutztext sagt `eu-central-2`, Profil-Tab ebenso – nicht aus Repo belegbar). Wahrheitsgemäss sein: Guideline 5.1.1 / 2.3.
- [ ] Supabase Security Advisor und Performance Advisor durchsehen; Befunde an Claude-Session geben.
- [ ] `public/privacy.html` und `public/support.html` öffentlich deployen (Hosting-Ort festlegen, **Frage**) und URL an Claude melden für Verlinkung in der App.
- [ ] Rechtliche Prüfung Kontoauszug-Funktion durch Anwalt oder bewusste Entscheidung dagegen. Beleg: `business/LEGAL_KONTOAUSZUEGE.md` Abschnitt 9 (Checkliste komplett offen). AVV/DPA mit Anthropic prüfen.
- [ ] Datenschutzerklärung inhaltlich freigeben, inkl. Aufbewahrungsfristen und Verantwortlichem (Name/E-Mail stehen schon, Postadresse **Frage:** nötig laut nDSG/Impressum?).
- [ ] Nutzungsbedingungen: ja/nein entscheiden (`TODO.md` Phase 5.3 offen).

### A6. TestFlight / Gerätetests (kann nur am echten iPhone geprüft werden)
- [ ] Scanner abnehmen: Auto-Aufnahme → blauer Haken → Review öffnet genau einmal; Abbrechen; mehrfach nacheinander. Beleg: `docs/verification/2026-10-20-scanner-handoff.md` Punkt 4.
- [ ] Anmeldung per Apple und Google auf frisch installiertem Gerät; Abmelden; Neustart der App behält Sitzung.
- [ ] Konto löschen: einmal mit Apple-Konto, einmal mit Google-Konto; danach Neuanmeldung ergibt leeres Konto, alte Daten weg.
- [ ] Kontoauszug-Test mit echten Auszügen (PostFinance CSV/CAMT, Kreditkarten-PDF): Kriterien `VISION.md` Abschnitt „Must“.
- [ ] Social-Features mit zwei Konten durchspielen (Freund, Teilen, Split, Beglichen). Beleg: `VISION.md` „Must“.
- [ ] Berechtigungen verweigern und wieder erlauben (Kamera, Fotos) – App darf nicht hängen.
- [ ] Zwei externe Beta-Tester bestätigen den Release-Kandidaten.

---

## B. Kann Claude / K-Dev tun

### B1. Privacy Manifest und Berechtigungen
- [ ] Privacy Manifest vollständig machen. Vorhanden in `app.json` (API-Kategorien UserDefaults, FileTimestamp, SystemBootTime, DiskSpace; 6 Datentypen). Fehlt vermutlich: Datentyp „Nutzerinhalte“ (Quittungstext/Kontoauszüge werden verarbeitet), „Kontoinformationen“ falls IBANs gespeichert werden, sowie Eintrag für Weitergabe an Drittanbieter. Abgleich mit tatsächlichem Datenfluss (`ARCHITECTURE.md`). Guideline 5.1.2, Privacy-Manifest-Pflicht für SDKs.
- [ ] Prüfen, ob eingebundene Drittbibliotheken eigene Manifeste mitbringen (`node_modules/**/PrivacyInfo.xcprivacy`) und ob der Build sie enthält; fehlende ergänzen. Pakete: `react-native-document-scanner-plugin`, `@dariyd/react-native-text-recognition`, `expo-*`.
- [ ] Berechtigungstexte vereinheitlichen: `app.json` hat `NSCameraUsageDescription` und `NSPhotoLibraryUsageDescription` auf Deutsch, aber die Plugin-Einträge `expo-media-library` sind **englisch** („Allow Piggy to save receipt images.“). Entscheiden, ob Speichern überhaupt nötig ist; sonst Plugin entfernen. Zwei Sprachen gemischt ist Review-Risiko (Guideline 5.1.1(ii), Zweckangabe präzise).
- [ ] `NSPhotoLibraryAddUsageDescription` prüfen (kommt von `expo-media-library`), ob tatsächlich verwendet wird.
- [ ] **Frage:** Nutzt die App Mikrofon oder Standort? Nein im Code gefunden (`microphonePermission: false`). Keine Texte nötig.
- [ ] Ungenutzte Pakete entfernen: `expo-notifications` ist in `package.json`, im Code ohne Verwendung gefunden. Sonst fragt Apple nach Push-Entitlement/Begründung; Android-Berechtigungen `READ/WRITE_EXTERNAL_STORAGE` in `app.json` irrelevant für iOS, aber für späteren Play-Release bereinigen.
- [ ] iOS-26-Modul `piggy-foundation-models` (Apple Intelligence, lokal): bestätigen, dass es ohne iOS 26 sauber ausfällt (Deployment-Target 16.4 in `app.json`). Crash-Test auf iOS 16.4/17.
- [ ] Altes Modul `modules/piggy-receipt-scanner` wird noch autogelinkt, aber nicht mehr importiert (Handoff). Entfernen oder bewusst behalten, damit Build kein unnötiges natives Modul enthält.

### B2. Konto, Login, Löschung
- [x] (2026-10-22, UI ohne Registrierung, Server-Trigger 20261022000001) E-Mail/Passwort-Registrierung aus der UI entfernen (`app/(auth)/login.tsx`: Zeilen mit `signUp`, `signInWithPassword`, Umschalter „Neu hier?“), Texte anpassen (Fehlermeldung „Bitte verwende E-Mail oder Google“). Abhängig von Entscheid A4. Beleg: `VISION.md` „Release-Pflicht“.
- [x] (2026-10-22) Apple-Button auf `AppleAuthenticationButton` umstellen (HIG/Guideline 4.8-Konformität der Darstellung).
- [ ] Konto-Löschen: Fehlerpfad prüfen, wenn Apple-Autorisierung abgebrochen wird (aktuell `return` ohne Meldung) und wenn `delete-account` teilweise gelöscht hat. Test mit Mock. Beleg: `app/(tabs)/profil.tsx` Zeilen ~130–160.
- [ ] `delete-account`: Pfadschema aller hochgeladenen Dateien gegen `{uid}/…` abgleichen (`app/scan.tsx`), damit keine Bilder übrig bleiben. Aufräumen der Bucket-Liste (`receipts` existiert **Frage**).
- [ ] Löschfunktion pro Kontoauszug (UI in `AbgleichView.tsx`, Backend: Datei in `bank-statements` + Zeilen kaskadierend). Beleg: im Code nicht vorhanden, `LEGAL_KONTOAUSZUEGE.md` Abschnitt 9.
- [ ] Einwilligungsschirm für Kontoauszug-Upload (heute nur der globale KI-Dialog in `src/lib/aiConsent.ts`). Guideline 5.1.1(i), 5.1.2(i).
- [ ] IBAN-/Kartennummern-Maskierung vor dem Speichern (`LEGAL_KONTOAUSZUEGE.md`).
- [x] (2026-10-22, URLs in src/lib/legalLinks.ts noch Platzhalter) Link „Datenschutzerklärung“ und „Support“ in Profil und Login einbauen (heute nur statische Zeile „Datenschutz“ ohne Link in `profil.tsx` ~Zeile 810). Guideline 5.1.1(i): in der App erreichbar.
- [ ] KI-Einwilligung zusätzlich serverseitig oder mit Zeitstempel protokollieren (heute nur lokal in AsyncStorage); `scan-receipt` ohne Einwilligung nicht aufrufbar machen oder dokumentieren. Beleg: `src/lib/aiConsent.ts`.
- [ ] Funktionen „Gemini-Key“: Hinweis, dass Text an Google geht, vor Eingabe des Keys anzeigen. Gemini-Key im Klartext (`user_settings.gemini_api_key`) bewerten/verschlüsseln.

### B3. Backend-Sicherheit
- [x] (2026-10-22, Bucket privat, signierte URLs, Migration 20261022000002) `receipt-images` auf privat stellen, App auf signierte URLs umbauen (`app/scan.tsx:129,516`, Anzeige in Quittungsdetail und `ReceiptCard`), Migration mit Policies anlegen. Ohne diesen Schritt widerspricht die Datenschutzerklärung der Realität. Beleg: `LEGAL_KONTOAUSZUEGE.md`.
- [x] (2026-10-22, im Repo entfernt; in Supabase noch `supabase functions delete process-receipt process-statement` ausführen) `process-receipt` entfernen oder mit `auth.getUser()` absichern; `process-statement` prüfen (App ruft es in `src/lib/statement.ts` auf, die Pipeline läuft aber über `statement-worker`). Beleg: Functions-Code ohne Nutzerprüfung.
- [ ] Basis-Schema als Migration nachziehen (`receipts`, `receipt_items`, `user_settings`, `user_usage`, `app_settings`, `profile_cards`, `shared_receipts`, Bucket `receipt-images` inkl. Policies) – benötigt Schema-Dump von Raphael (nur lesend).
- [ ] Limit für Kontoauszug-Analyse pro Nutzer (Kostenschutz); `app_settings.statement_*` erweitern. Beleg: `ARCHITECTURE.md`, `VISION.md`.
- [ ] RLS-Test mit zwei Konten automatisieren (SQL- oder Jest-Test): A liest nicht B (Belege, Bilder, Auszüge, Profile-Felder).
- [ ] Datenschutz-Seite `public/privacy.html` überarbeiten: E-Mail-Login streichen (falls entfernt), Kontoauszug-Abschnitt, Aufbewahrungsdauer, Löschprozess, Gemini-Hinweis, Region belegen. Sprache DE/EN synchron halten.
- [ ] `public/support.html` auf aktuelle Funktionen prüfen; Kontaktadresse prüfen.

### B4. Stabilität / Crash-Tests
- [x] (2026-10-22, 161/161 grün) Jest-Suite laufen lassen und Ergebnis dokumentieren; die 7 roten Parser-Tests (`src/lib/ocr.test.ts`) grün machen. Beleg: `VISION.md` „Must“.
- [ ] `npx tsc --noEmit` ohne Fehler (laut Handoff zuletzt erfüllt).
- [ ] Fehlerpfade testen und Meldungen kontrollieren: kein Netz, `demo_limit_reached`, KI abgelehnt, leeres Bild, Timeout, grosse PDF (40 Seiten, ~1000 Buchungen), Mehrfach-Upload.
- [ ] Duplikat-Flow (ADR 0003) auf Gerät und per Test abdecken; `receiptDuplicates.test.ts` läuft.
- [ ] Crash-Reporting klären (**Frage:** keines im Code; TestFlight-Crashlogs genügen für v1.0?).
- [ ] Accessibility-Grundlage: Dynamic Type, Kontrast, VoiceOver-Labels für Hauptaktionen (Beleg: `TODO.md` 2.9 „App Store Anforderung“ offen). App erzwingt `userInterfaceStyle: dark` (`app.json`); Dark-only ist erlaubt, im Review-Text erwähnen.
- [ ] Bildkompression vor Upload prüfen (`expo-image-manipulator`), Speicher/Kosten.
- [ ] Onboarding/Leerzustand für Neukonto (Review-Konto sieht sonst leere App): Beispieldaten oder klarer Leerzustand, Guideline 2.1 (vollständige Funktionalität).

### B5. Metadaten und Build
- [ ] Store-Texte schreiben (DE, optional EN): Name ≤ 30 Zeichen, Untertitel ≤ 30, Keywords ≤ 100, Beschreibung, Neuerungen – Skill `appstore-launch`.
- [ ] Asset-Prüfung der Screenshots mit `scripts/check-assets.py` (aus dem Skill) und neue Screenshots aufnehmen (Finanzen-Tab, Scan, Kategorien).
- [ ] `buildNumber` in `app.json` an TestFlight-Stand anpassen (steht auf 18, TestFlight bei 23/24); Fehlerbild laut Projekt-CLAUDE: „You've already submitted this build“. Version 1.0.0 beibehalten.
- [ ] `app.json` bereinigen: Android-Block unkritisch lassen, `expo-media-library`-Texte siehe B1.
- [ ] Übergabe an K-Dev über `/api/handover` (Staging mit `git add`, Commit-Nachricht auf Deutsch); Release-Build `ios/production`; Ergebnis im Dashboard prüfen. Sessions bauen nicht selbst (K-Dev-Regel).
- [ ] Security-Gate vor Abschluss: `kdev-security-gate <projektpfad>` laufen lassen (Regel aus K-Dev `CLAUDE.md`). Hinweis: im Projektordner liegt eine `.env` (Supabase-URL/Anon-Key, Google-Client-IDs); sie darf nicht in Commits/Builds gelangen (`.gitignore`/`.easignore` kontrollieren).
- [ ] Doku nachziehen: `TODO.md` (Stand 2026-07-08) und `SETUP.md` (veraltete Pfade) auf aktuelle Roadmap verweisen; `.kdev/PROJECT.md` Ebene 1/2 ausfüllen.

---

## C. Offene Fragen (Zusammenfassung)

1. Review-Zugang: Variante A (E-Mail-Konto) oder B (SIWA durch Reviewer)?
2. Wo ist `privacy.html`/`support.html` öffentlich gehostet?
3. Kontoauszug-Funktion im Release 1.0 aktiv (dann Anwalt/DPA und Einwilligungsschirm Pflicht) oder zunächst ausgeblendet?
4. Existiert Bucket `receipts`? Wird `process-receipt` gebraucht?
5. Reale Supabase-Region und Plan (Pro)?
6. Build-Nummer: setzt der Worker sie selbst?
7. Kategorie in ASC (Finanzen vs. Produktivität) und Altersfreigabe-Antworten zu Freunde-/Teilen-Funktion.
