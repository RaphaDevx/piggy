# Piggy — Vision & Feature Roadmap

> Erstellt: 2026-07-06 · Letztes Update: 2026-10-05
> Dieses Dokument beschreibt die langfristige Produktvision. Konkrete Implementierungsaufgaben → `TODO.md`
>
> **Status:** ✅ = live · 🔄 = in Arbeit · ⬜ = offen · 🚀 = nativ-only (ab EAS Build)

---

## Leitbild

Piggy wird zur **sozialen Finanz-Wallet der Schweiz**: eine App, die Quittungen scannt, Ausgaben teilt, Freunde vernetzt und mittelfristig alle relevanten Kassenbelege, Treuekarten und Bankkonten an einem Ort bündelt. Der Kern bleibt die Quittung — aber um sie herum entsteht ein vollständiges Ausgaben-Ökosystem.

**Fokus v1.0 (Stand 2026-10-05):** Solo-Nutzen zuerst. *Quittung scannen + Kontoauszug importieren = komplette Finanzübersicht.* Jede Bank-Ausgabe wird über die verknüpfte Quittung bis auf Artikel-Ebene aufgeschlüsselt (Lebensmittel vs. Haushalt usw.); Ausgaben ohne Quittung bekommen eine Kategorie aus dem Buchungstext. Social-Features (Freunde, Splits, Projekte) bleiben drin, sind aber Bonus.

---

## 0. Roadmap bis Release v1.0

> Release-Pflichtpunkte für den App Store (Compliance) laufen separat und stehen unten nur zur Vollständigkeit.

### Bereits umgesetzt für v1.0 ✅

| Feature | Stand |
|---------|-------|
| Grobe Kategorien statt feiner Tags (Lebensmittel, Getränke, Haushalt, Körperpflege & Gesundheit, Restaurant, Freizeit & Shopping, Mobilität, Diverses) — genau eine pro Artikel | ✅ 2026-10-05, 1090 Artikel migriert (alte Tags in `tags_legacy`) |
| Kategorie selbst wählen + eigene Kategorien (TagPicker im Scan-Review und in der Quittung, "Alle Artikel"-Aktion) | ✅ 2026-10-05 |
| Kontoauszug-Import (PDF, CSV, CAMT.053) mit KI-Kategorie pro Buchung, Duplikat-Schutz bei überlappenden Auszügen | ✅ 2026-10-05 |
| Automatischer + manueller Abgleich Buchung ↔ Quittung | ✅ (seit Juli, jetzt im Finanzen-Tab sichtbar) |
| **Finanzen-Tab:** Monat, Einnahmen/Ausgaben/Netto, Ausgaben nach Kategorie (Bank + Quittung), Beleg-Abdeckung, Buchungen ohne Kategorie zuordnen; Umbuchungen (Kreditkarten-Rechnung) werden nicht doppelt gezählt | ✅ 2026-10-05 |
| Konto löschen, KI-Einwilligung, Duplikat-Hinweis beim Speichern, Zusatzfelder (Beleg-Nr., MWST-Nr.) | ✅ Build 17 |
| **Kontoauszug-Pipeline im Hintergrund:** Upload → Verarbeitung Seite für Seite (gedrosselt, Fortschritt "Seite x von y"), austauschbarer Extractor (CAMT ohne KI, Text → Haiku, Scan → Sonnet), Saldo-Prüfung fürs Vorzeichen, Duplikat-Schutz | ✅ 2026-10-05, Test: 12-Seiten-PDF mit 276 Buchungen 100 % korrekt in ~70 s |
| **Fortlaufender Abgleich:** jede neue Quittung wird sofort gegen offene Buchungen geprüft, "Neu abgleichen" | ✅ 2026-10-05 |
| **Vorschläge bei Ungereimtheiten:** Trinkgeld, Fremdwährung, Rabatt, Teilzahlung → Übernehmen / Bearbeiten / Ablehnen; Ausgleichsposten auf der Quittung | ✅ 2026-10-05 |
| **Finanzprofil:** Ø pro Woche/Monat, Einnahmen, Sparquote, Fixkosten-Anteil, Monats-/Wochenbalken, Top-Kategorien | ✅ 2026-10-05 (Basis für spätere Charts/Dashboard) |

### Must — ohne das kein Release

| Feature | Nutzen | Akzeptanzkriterium | Aufwand |
|---------|--------|--------------------|---------|
| Finanzübersicht mit echten Auszügen testen | Kernversprechen v1.0 | Je ein echter Auszug PostFinance (CSV/CAMT), eine Kreditkarte (PDF) importiert: keine Fehler, ≥ 80 % Buchungen automatisch kategorisiert, Kreditkarten-Zahlung als Umbuchung erkannt, Summen stimmen mit Auszug überein | S |
| Build 18 auf TestFlight verifizieren | Bild-Upload + Profil-Embeds waren bis 30.09. kaputt | Scan → Bild gespeichert; Freunde/Projekte laden ohne Fehler; Kategorie im Detail ändern bleibt nach Neustart | S |
| Social-Features einmal echt durchspielen | Bisher ungetestet (Fremdschlüssel fehlten bis 30.09.) | Mit 2 Test-Accounts: Freund hinzufügen, Quittung teilen, Split anlegen, Betrag als bezahlt markieren | M |
| Regex-Fallback für Artikel reparieren | Ohne KI-Einwilligung werden heute kaum Artikel erkannt | Die 7 roten Parser-Tests (`ocr.test.ts`) grün | M |

### Should — wenn Zeit bleibt

| Feature | Nutzen | Akzeptanzkriterium | Aufwand |
|---------|--------|--------------------|---------|
| Serien-Scan (Stapel-Modus) | Mehrere Quittungen in einem Durchgang (aktuell `maxNumDocuments: 1`) | Bis 20 Quittungen scannen → Liste zur Prüfung → alle speichern; Duplikat-Hinweis pro Quittung | L |
| Händler-Kategorie lernen | Manuelle Zuordnung einer Buchung gilt künftig für denselben Händler | Nach einmaligem Zuordnen werden neue Buchungen desselben Händlers automatisch so kategorisiert | M — im Pipeline-Test vergab das Modell z. B. „K Kiosk“ mal Lebensmittel, mal Diverses |
| Monatsvergleich / Trend | "Gebe ich mehr aus als letzten Monat?" | Finanzen-Tab zeigt Δ zum Vormonat je Kategorie | S |
| Budget pro Kategorie | Zielsteuerung | Budget setzen, Fortschrittsbalken, Hinweis bei 80 % | M |
| Pinch-Zoom auf Quittungsfoto | Lesbarkeit | Zoom im Detail | S |

### Later — nach v1.0

Open-Banking-Anbindung statt manuellem Import (Phase C), E-Mail-Quittungen (Phase B), Treuekarten Supercard/Cumulus (Phase A), B2B-UI (Tabellen existieren), Commitment-Funktion, PDF-Export, Android.

### Bewusst nicht in v1.0

- **In-App-Käufe / Abo:** Gratis-Scans pro Tag + eigener Gemini-Key reichen für die Testphase; Monetarisierung nach Feedback.
- **B2B / Firmen-Abrechnung:** Datenmodell steht, UI erst mit konkretem Pilotkunden.
- **Passwort-Reset:** Login nur über Apple/Google, E-Mail-Signup wird abgeschaltet.

### Release-Pflicht (Compliance, separat)

Apple-SIWA-Secrets in Supabase · Web-Deploy `privacy.html`/`support.html` · App-Privacy-Angaben + Demo-Account in App Store Connect · E-Mail-Signup aus · Leaked-Password-Protection an.

---

## 1. Social Layer — Freunde verbinden ✅

*Inspiration: Snapchat-QR-Code / Splitwise-Freundesliste*

### Profil-Code ✅
- Jeder Nutzer bekommt einen eindeutigen **Piggy-Code** (`PIG-XXXXXXXX`), auto-generiert
- Profil-Tab: eigener QR-Code (gross, scanbar, teilbar), Display-Name editierbar
- **Freund hinzufügen:** Code manuell eingeben oder QR-Code mit Kamera scannen ✅
- Freundschaftsanfrage → Bestätigung · Realtime-Benachrichtigung via Supabase ✅

### Freundesliste ✅
- Tab "Freunde" (akzeptiert) + "Anfragen" (ein-/ausgehend)
- Badge im Profil-Tab bei offenen Anfragen
- ⬜ Freund-Profil: gemeinsame Gruppen, Schulden-Verlauf

---

## 2. Ausgaben teilen — Splitwise-Layer ✅

*Inspiration: Splitwise — aber direkt aus der Quittung heraus*

### Quick-Split aus Quittung ✅
- Personen-Icon in jeder Quittung → Split-Screen
- Freunde per Chip antippen, Betrag wird gleichmässig aufgeteilt
- Offene Beträge ("Du schuldest" / "Du bekommst") im Dashboard sichtbar ✅

### Artikel-genaues Teilen ✅
- Pro Person: Artikel exkludieren ("das zahle ich allein")
- Berechnung live: ausgeschlossene Artikel werden nur unter verbleibenden Personen verteilt
- "Beglichen"-Button pro Schuld, Settlement-Übersicht ✅

### Quittungen für Freunde scannen ✅
- Karte in Quittung erkannt (z. B. `XXXX 5327`) → automatisch geprüft gegen Freunde-Karten
- Banner: *"Möglicherweise von [Name]"* + direkter Senden-Button
- Freund erhält Quittung → annehmen → direkt splitten
- Jeder Nutzer speichert eigene Karten-Endziffern im Profil (für Matching durch Freunde)

### ⬜ Schulden-Ausgleich (nächster Schritt)
- Gegenseitige Schulden verrechnen (A schuldet B 10, B schuldet A 4 → netto 6)
- Ausgleich per TWINT-Deeplink oder intern markieren
- Verlauf aller gemeinsamen Ausgaben zwischen zwei Personen

---

## 3. Gruppen & Projekte — Pool-Funktion

*Inspiration: Splitwise-Gruppen, aber mit Binding-Optionen*

### Projekt erstellen
- Projektname, Beschreibung, Startdatum (z. B. „Interrail Juli 2026", „WG Haushalt", „Berghütte Wochenende")
- Mitglieder einladen per Piggy-Code, QR oder Link
- Einladungslink funktioniert auch für Nicht-Nutzer (Onboarding-Flow)

### Ausgaben im Projekt
- Alle Quittungen, die im Projekt erfasst werden, landen im gemeinsamen Pool
- Dashboard zeigt: wer hat wie viel bezahlt, wer schuldet wie viel
- Automatische Ausgleichsvorschläge: minimale Anzahl Überweisungen (Splitwise-Algorithmus)

### Rollen & Berechtigungen
- **Admin:** kann Mitglieder hinzufügen/entfernen, Projekt abschliessen
- **Mitglied:** kann Ausgaben erfassen, sieht alle Projektausgaben

---

## 4. Advanced: Verbindliche Zahlungszusagen

*Für Situationen, wo man Sicherheit braucht — z. B. gemeinsame Reisebuchung*

### Das Problem
Wenn jemand eine Reise, Unterkunft oder Ticket für die Gruppe vorschiesst, trägt er das volle Risiko — die anderen können im Nachhinein abspringen oder die Zahlung verweigern.

### Lösung: Commitment-Funktion
- Pro Projekt optional aktivierbar: **„Verbindliche Zusage"**
- Jedes Mitglied kann sich bis zu einem definierten Betrag verpflichten (z. B. max. 500 CHF)
- Die Zusage ist **nicht frei widerrufbar** — ähnlich einem SEPA-Lastschriftmandat
- Technisch: Verknüpfung mit Zahlungsmittel (Karte oder TWINT), Betrag wird bei Fälligkeit automatisch belastet
- Transparenz: jeder sieht, wer zugesagt hat und wer noch nicht

### Anwendungsfall Reise
1. Raphael bucht das Airbnb und lädt die Gruppe ein
2. Gruppe bestätigt Commitment (z. B. je 300 CHF)
3. Bei Buchungsbestätigung → automatische Lastschrift aller Anteile
4. Raphael bekommt sein Geld zurück, ohne hinterherrennen zu müssen

### Schutz & Fairness
- Dispute-Funktion: im Streitfall Einspruch mit Frist (z. B. 7 Tage)
- Keine Commitment-Funktion ohne explizite Aktivierung und Einverständnis aller Mitglieder
- Rechtliche Grundlage: Nutzungsvertrag (AGB) regelt Bindungswirkung klar

---

## 5. Quittungen — Scan-Qualität & Transparenz

### Anteilige Original-Quittung ✅
- Split-Teilnehmer sehen Quittungs-Ausschnitt mit ihren Artikeln
- ⬜ Volle Aufschlüsselung: Anteil pro Artikel sichtbar (25% von CHF 3.90 = CHF 0.98)

### Dokumentenqualität
- ⬜ Zoomfunktion auf Quittungsfoto (Pinch-to-zoom)
- ⬜ Download als PDF für Spesen / Steuern

### 🚀 Nativer Dokument-Scanner (ab EAS Build)

*Wie Office Lens / CamScanner — automatische Kantenerkennung + Perspektivkorrektur in Echtzeit*

**Technische Grundlage (bereits installiert: `react-native-document-scanner-plugin`):**
- iOS: Apples **VisionKit** `VNDetectRectanglesRequest` — dasselbe Framework wie die iOS Notes-App
- Android: **Google ML Kit** Document Scanner API
- Beide liefern: Echtzeit-Overlay mit erkannten Ecken, automatische Perspektivkorrektur (Winkel-Ausgleich), Farbanpassung (Hintergrund aufhellen)

**Was es kann (nach Integration):**
- Kamera öffnen → Quittung wird automatisch erkannt, Ecken live markiert
- Gerät kippt (G-Sensor / Gyro): Erkennung stabilisiert sich automatisch
- Foto wird automatisch zugeschnitten und perspektivisch entzerrt
- Kein weisser Rand, kein schiefer Scan → bessere OCR-Qualität

**Warum nicht im Web:**
- Browser hat keine native Document-Detection API
- OpenCV.js (einzige Alternative) = 30 MB Bundle + CPU-intensiv auf Mobile
- Web bleibt bei File-Upload / einfacher Kamera (akzeptabler Kompromiss)

**Aufwand:** 3–5h nach EAS Build Setup (Library ist bereits installiert)

---

## 6. Wallet-Integration — Mittelfristige Vision

*Piggy wird zur zentralen Finanzschnittstelle*

### Phase A: Digitale Belege automatisch erfassen
- **Coop / Migros:** Wenn die Coop-App oder Migros-Cumulus-App eine Quittung erstellt, soll diese automatisch in Piggy landen — via Deep-Link, Share-Extension oder direkte API-Partnerschaft
- **Supercard (Coop):** Supercard-Konto verknüpfen → Einkäufe erscheinen automatisch als Belege
- **Cumulus (Migros):** Cumulus-Konto verknüpfen → gleiches Prinzip
- **IKEA Family:** IKEA-Konto verknüpfen → E-Mail-Quittungen direkt in Piggy

### Phase B: E-Mail-Quittungen automatisch importieren
- Piggy-Mailbox (z. B. `receipts@piggy-app.ch`) oder Gmail-/Outlook-Verknüpfung
- Eingehende Quittungs-Mails (Amazon, Zalando, SBB, Digitec etc.) werden automatisch erkannt, geparst und als Beleg gespeichert
- Claude Vision / LLM-Parsing für nicht-standardisierte Formate

### Phase C: Banking-Verknüpfung
- ✅ Manueller Import von Kontoauszügen (PDF/CSV/CAMT.053) inkl. Kategorie, Abgleich und Finanzübersicht (2026-10-05)
- Open Banking (PSD2-ähnlich, Schweiz: STET/Swiss Open Finance)
- Kreditkarten, PostFinance, Neobanken (Neon, Revolut, Yuh) verknüpfen
- Transaktionen werden automatisch mit Belegen gematcht (Abgleich-Funktion bereits vorhanden)
- Ziel: 100% Abdeckung — jede Ausgabe hat einen Beleg, jeder Beleg hat eine Transaktion

### Phase D: Händler-Schnittstellen & Partnerschaften
Anfragen / Verhandlungen mit:

| Händler | Ziel | Schnittstelle |
|---------|------|---------------|
| Coop | Supercard + digitale Quittung | API oder CSV-Export |
| Migros | Cumulus + digitale Quittung | API oder CSV-Export |
| IKEA | IKEA Family E-Mail-Quittungen | E-Mail-Parsing oder API |
| Galaxus / Digitec | Bestellbestätigungen | E-Mail-Parsing |
| SBB | Bahnbillette | SBB Open Data oder E-Mail |
| Amazon.ch | Bestellbestätigungen | E-Mail-Parsing |
| Apotheken (Topwell, Amavita) | Kassenbons | Lokal/API |

**Vorgehen:**
1. Erst E-Mail-Parsing (kein Partner nötig, sofort machbar)
2. Dann offizielle Partnerschaft anfragen (bessere Datenqualität, offizieller Kanal)
3. Treueprogramm-APIs prüfen (Supercard, Cumulus haben bestehende Apps → evtl. inoffizielle APIs)

---

## 7. B2B — Firmen-Abrechnung

*"Spesen einreichen in 2 Klicks, nicht 20 Felder ausfüllen"*

### Das Problem heute (bei Konkurrenz)
SAP Concur, Rydoo, Yokoy — alle starten mit einem leeren Formular. Der Mitarbeiter tippt alles ab, was auf der Quittung steht. Piggy hat die Quittung bereits geparst: Laden, Datum, Betrag, Artikel, MwSt-Anteil. **Der Unterschied: 0 manuelle Eingaben.**

### Flow Mitarbeiter
```
1. Quittung scannen (wie immer)
2. "Als Spesen einreichen" antippen
3. Firma auswählen + Kategorie (Mahlzeit / Transport / Hotel / Sonstiges)
4. Absenden — fertig
```

### Flow Buchhaltung / Admin
```
Dashboard → alle eingereichten Belege mit Status
→ Genehmigen / Ablehnen / Nachfragen
→ Export: PDF-Spesenblatt, CSV für Buchhaltung, direkter API-Push
→ Mitarbeiter bekommt Benachrichtigung + Rückerstattung
```

### Firma onboarden
- **"Add Company"** Button im Profil-Tab
- Admin erstellt Firma → erhält Invite-Code / Link
- Mitarbeiter tritt bei → sieht Firmen-Kontext beim Einreichen
- Rollen: `admin` (alles), `accountant` (genehmigen/exportieren), `member` (einreichen)

### Schweizer Besonderheiten (USP gegenüber Konkurrenz)
- Korrekte **MwSt-Ausweisung** (7.7% / 2.5% / 3.7% / 0%) pro Artikel — automatisch aus OCR
- **Abacus-Integration** (dominantes Schweizer ERP/Buchhaltungssoftware)
- CHF als primäre Währung, keine EUR-Umrechnung nötig
- **Zofingia / Vereine**: vereinfachter Modus ohne Buchhaltungs-Overhead

### Monetarisierung B2B
| Tier | Preis | Zielgruppe |
|------|-------|------------|
| Free | CHF 0 | Consumer, bis 5 Einreichungen/Mo. |
| Pro | CHF 5/Mo. | Selbständige, Freelancer |
| Team | CHF 15/User/Mo. | KMU, 5–50 Mitarbeiter |
| Enterprise | auf Anfrage | Grosse Firmen, ERP-Integration |

### Technische Integrationen (Priorität)
| System | Typ | Relevanz CH |
|--------|-----|-------------|
| **Abacus** | API | ⭐⭐⭐ Muss |
| **DATEV** | API | ⭐⭐ Wichtig (D/A) |
| CSV-Export | Standard | ⭐⭐⭐ Sofort machbar |
| PDF-Spesenblatt | Export | ⭐⭐⭐ Sofort machbar |
| SAP (FI/CO) | API | ⭐ Enterprise-Phase |
| Banana Accounting | CSV | ⭐⭐ Kleine CH-Firmen |

### Datenmodell (Fundament bereits gelegt ✅)
```
companies            — Firma (Name, Slug, Invite-Code)
company_members      — Mitglied + Rolle (admin/accountant/member)
expense_submissions  — Einreichung (Quittung → Firma, Status, Kategorie)
receipts.company_id  — nullable FK für Firmen-Kontext
```
*Tabellen existieren bereits in Supabase (leer, mit RLS). UI folgt in einer späteren Phase.*

---

## 9. Produktprinzipien

- **Quittung zuerst:** Jede Funktion beginnt bei der Quittung — das ist das Herzstück
- **Einfach starten, mächtig werden:** Basis-Split ohne Account möglich; erweiterte Funktionen opt-in
- **Privatsphäre first:** Bankdaten und Quittungen verlassen das Gerät nur verschlüsselt; Treuekarten-Verknüpfung explizit opt-in
- **Schweizer Markt:** CHF, Migros, Coop, TWINT, PostFinance als erste Priorität

---

## Entwicklungs-Phasen

```
Phase 1 ✅  Quittungen scannen, Dashboard, Kontoabgleich, Google-Auth
Phase 2 ✅  Social Layer: Profile, QR-Code, Freunde, Realtime-Anfragen
Phase 3 ✅  Ausgaben teilen: Split, Artikel-Ausschlüsse, Offene Beträge
Phase 4 ✅  Quittungen für Freunde: Karten-Matching, Senden, Annehmen
Phase 5 🔄  App-Qualität: Duplikats-Check ✅, Konto löschen ✅, Zoom, PDF-Export
Phase 6 ✅  EAS Build iOS → Dokument-Scanner, TestFlight (Build 17)
Phase 6b ✅ Finanzübersicht: grobe Kategorien, Kontoauszug-Import, Abgleich, Finanzen-Tab (v1.0-Kern)
Phase 7 ⬜  Gruppen & Projekte (Pool für Reisen, WG etc.)
Phase 8 ⬜  Commitment-Funktion (Binding Payments)
Phase 9 ⬜  E-Mail-Quittungen, Treuekarten-Import (Supercard, Cumulus)
Phase 10 ⬜ Banking-Integration, Händler-Partnerschaften
```

---

*Dieses Dokument ist lebendig — es wächst mit der App.*
