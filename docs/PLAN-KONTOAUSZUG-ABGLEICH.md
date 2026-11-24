# Plan: Kontoauszug-Import, Abgleich und Teilen in der SwiftUI-App

Stand 2026-11-24. Aufwände sind meine Schätzung (reine Arbeitszeit, ohne Wartezeit auf Apple oder Anwalt), im Repo nicht belegt. Grundlage: Code und Doku gelesen, nichts auf dem Gerät oder Live-System getestet.

## Ausgangslage
- Backend vorhanden: `statement-worker` (ADR 0001), Tabellen `bank_statements`, `bank_transactions`, `receipt_matches`, `match_suggestions`. Vorschläge werden nie automatisch angenommen.
- Alte Expo-App hatte `AbgleichView.tsx` (archiviert 2026-11-18). Die SwiftUI-App hat weder Upload noch Abgleich-Ansicht (ADR 0004: «Bankimport bleibt deaktiviert»).
- Release 1.0 ist laut Store-Metadaten ohne Kontoauszüge. Dieser Plan gilt für 1.1 (oder 1.0, wenn bewusst entschieden).
- Rollen: **C** Claude-Session (Code, Tests, kein Build), **K** K-Dev (Signieren, Build, TestFlight), **R** Raphael (Konsolen, Entscheidungen), **L** Kaufmann-Legal / Anwalt.

## Schritte

| # | Schritt | Inhalt | Aufwand | Wer | Abnahme |
|---|---|---|---|---|---|
| 0 | Entscheidung und Rechtsprüfung | Version festlegen (1.0 oder 1.1). Prüfung nach `business/LEGAL_KONTOAUSZUEGE.md` Abschnitt 9 (Checkliste), AVV mit Anthropic, Datenschutz-Meldung nach Playbook `datenschutz-pruefung-kaufmann-legal.md` | 0,5 Tag eigene Arbeit, Prüfung des Anwalts extern 1–2 Wochen | R, L | Schriftliches Ja/Nein, Auflagen notiert |
| 1 | Backend prüfen und bereitstellen | `statement-worker`, pg_cron, Vault-Secret und Bucket live prüfen. `native-finance` bereitstellen. Limit pro Nutzer (`app_settings.statement_*`). Löschfunktion Backend (Datei und Zeilen kaskadierend). Speicherort (Supabase oder R2, ADR 0005) festlegen | 2–3 Tage | C, R | Testauszug läuft durch bis `stage=done`, Limit greift, Löschen entfernt Datei und Zeilen |
| 2 | Einwilligung und Löschen in der App | Eigener Einwilligungsschirm vor dem ersten Upload (Guideline 5.1.1(i), 5.1.2(i)). Löschen pro Auszug in der UI. IBAN- und Kartennummern maskieren | 1,5–2 Tage | C | Ohne Zustimmung kein Upload. Löschen entfernt den Auszug sichtbar und im Backend |
| 3 | Upload in SwiftUI | Dateiauswahl (PDF, CSV, CAMT), Upload, Fortschrittsanzeige über `stage` und `pages_done`, Fehlerzustände (Limit, unlesbar, Netz) | 2–3 Tage | C | Drei Beispielauszüge laden, Fortschritt zeigt sich, Fehler sind verständlich |
| 4 | Abgleich-Liste | Neue Ansicht mit vier Gruppen: **bestätigt** (Quittung und Buchung zugeordnet), **nur Quittung** (keine Buchung), **nur Buchung** (keine Quittung), **Vorschläge** (annehmen oder ablehnen). Handzuordnung. Summen aus `finance-core` über `native-finance`, keine zweite Rechenlogik in Swift | 4–5 Tage | C | Zahlen der Liste stimmen mit dem Backend überein, Vorschläge werden nur auf Tippen übernommen |
| 5 | Teilen mit Freunden | Entscheidung, was geteilt wird (siehe unten). Dazu Melden und Blockieren (Guideline 1.2, falls Apple die Freunde-Funktion als nutzergenerierte Inhalte wertet) | 2–4 Tage | R, C | Zwei Konten: Freund sieht nur Freigegebenes, Blockieren und Melden gehen |
| 6 | Datenschutz und Store | `public/privacy.html` (Kontoauszug, Aufbewahrung, Löschprozess, Dienstleister), App-Privacy-Angaben, Review-Notizen, anonymisierter Beispielauszug als Anhang. Store-Text nur anpassen, wenn die Funktion drin ist | 1–1,5 Tage | C, R | Texte decken sich mit dem Verhalten der App, Store-Legal-Gate L1–L10 erfüllt |
| 7 | Tests | Echte Auszüge (PostFinance CSV/CAMT, Kreditkarten-PDF), `VISION.md` «Must». Social-Test mit zwei Konten. Randfälle: Fremdwährung, Trinkgeld, Teilzahlung, Duplikate | 2–3 Tage | C, R | Kriterien aus `VISION.md` erfüllt, keine falschen Auto-Zuordnungen |
| 8 | Build und Freigabe | Übergabe an K-Dev (`/api/handover`), TestFlight, Regression mit 2 Testern, Einreichung | 0,5 Tag plus Apple-Review 1–7 Tage | K, R | Build in TestFlight, Status «Bereit zur Einreichung» |

**Summe:** etwa 14–20 Arbeitstage, plus Wartezeit Anwalt und Apple. Kritischer Pfad: 0 → 1 → 2 → 3 → 4 → 7 → 8. Schritte 5 und 6 laufen parallel zu 3 und 4.

## Offene Entscheidung zu Schritt 5: Was heisst «an Freunde schicken»?
- **A. Bestätigungsstatus im Split anzeigen** (der Freund sieht, ob der Betrag beglichen ist): klein, 1–2 Tage, kein Kontoauszug-Inhalt geht an Dritte.
- **B. Abgleich-Liste als Text oder PDF teilen** (`ShareLink`, Raphael verschickt selbst): 1–2 Tage, die Daten verlassen die App nur auf seine Handlung. Datenschutz-Hinweis nötig, damit keine Kontonummern mitgehen.
- **C. Buchungen direkt an Freunde in Piggy senden:** grösster Aufwand (3–4 Tage), neue Tabellen und Rechte, rechtlich am heikelsten. Empfehlung: nicht in der ersten Version.

Empfehlung: A, dazu B ohne IBAN und Kartennummern.

## Risiken
1. **Rechtsprüfung** kann Auflagen bringen, die Schritt 2 und 6 vergrössern. Darum zuerst.
2. **Kosten pro Auszug** (KI-Extraktion): Limit aus Schritt 1 vor dem Start setzen.
3. **Falsche Zuordnungen** sind bei Finanzdaten schlimmer als fehlende. Darum keine Auto-Annahme, siehe Schritt 7.
4. **Speicherort:** Wenn Belege nach R2 wandern (ADR 0005), den Auszug-Bucket gleich mitplanen, damit nicht zweimal migriert wird.
5. **Doku-Stand:** `TODO-APPSTORE.md` stammt teils aus der Expo-Zeit. Einzelne Punkte gelten für die SwiftUI-App neu zu prüfen.
