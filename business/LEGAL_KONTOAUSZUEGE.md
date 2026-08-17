# Piggy — Rechtliche Einschätzung: Kontoauszug-Upload & Kontoabgleich

> **Disclaimer:** Dies ist eine fachliche Ersteinschätzung, **keine Rechtsberatung**.
> Vor Live-Schaltung des Kontoabgleich-Features sollte eine Anwältin/ein Anwalt
> mit Spezialisierung auf Datenschutz (CH-DSG/revDSG + GDPR) die konkrete
> Umsetzung (Privacy Policy, Einwilligungstexte, AVV/DPA mit Anthropic) prüfen.

---

## 1. Wie sensibel sind Kontoauszüge wirklich?

Quittungen (bestehendes Feature) zeigen **einzelne, vom Nutzer ausgewählte
Käufe**. Kontoauszüge zeigen **alles**:

- **Vollständiges Ausgabeverhalten** über den gesamten Zeitraum — nicht nur
  das, was der Nutzer bewusst scannt
- **Einkommen** (Lohneingänge, Renten, Kindergeld) inkl. Arbeitgeber-Namen
  im Buchungstext
- **Indirekte Rückschlüsse auf besonders schützenswerte Daten** (Art. 5 lit. c
  revDSG bzw. "besondere Kategorien" Art. 9 GDPR), z.B.:
  - **Gesundheit**: Apotheken, Spitäler, Psychotherapie, Fitness/Reha
  - **Religion/Weltanschauung**: Kirchensteuer, Spenden an religiöse/politische
    Organisationen
  - **Sexuelle Orientierung / Beziehungsstatus**: Dating-Apps, Apotheken
    (Verhütung), gemeinsame vs. getrennte Haushaltsführung
  - **Politische Ansichten**: Parteispenden, Abos politischer Medien
  - **Finanzielle Notlage**: Überziehungszinsen, Inkassobüros, Kredite
- **Vollständige Kontonummern/IBAN** im Auszugskopf, ggf. **Kartennummern**
  (meist maskiert, aber nicht garantiert)
- **Bewegungsprofil über Zahlungsorte** (Tankstellen, Restaurants, Reisen)

**Fazit:** Kontoauszüge sind eine **deutlich höhere Risikoklasse** als
Quittungsdaten. Ein einzelner Kontoauszug kann faktisch ein vollständiges
Persönlichkeitsprofil ergeben. Das Feature darf nicht "nebenbei" wie ein
weiterer Upload-Typ behandelt werden.

---

## 2. Rechtsgrundlage & Einwilligung

### Warum die allgemeine Registrierungs-Einwilligung NICHT reicht

Die App-Nutzung an sich (Quittungen scannen) basiert auf Vertragserfüllung
(Art. 31 revDSG / Art. 6 Abs. 1 lit. b GDPR — "der Nutzer will diese
Funktion"). Für den Kontoabgleich braucht es mehr, weil:

1. Die Datenkategorie (Bankdaten, potenziell besondere Personendaten) deutlich
   sensibler ist als der Hauptzweck der App
2. Eine zusätzliche Verarbeitung durch einen Drittanbieter (Anthropic, USA)
   hinzukommt (siehe Abschnitt 4)

### Empfehlung: separater, granularer Consent-Screen

Vor dem ersten Upload eines Kontoauszugs muss ein dedizierter Screen erscheinen,
der **nicht** Teil des allgemeinen Onboardings ist:

- Klartext-Erklärung: *"Du lädst einen Kontoauszug hoch. Wir extrahieren daraus
  nur Buchungsdatum, Betrag, Währung und Buchungstext, um sie automatisch mit
  deinen Quittungen abzugleichen. Die Datei wird zur Analyse an Anthropic
  (Claude AI, USA) übermittelt."*
- Explizite Zustimmung (Checkbox/Button "Verstanden, Kontoauszug hochladen")
  — **Opt-in pro Feature**, nicht global
- Zweckbindung: Daten werden **ausschliesslich** für den Abgleich verwendet,
  **nicht** für Aggregation/Statistik/Data-Business (siehe Abschnitt 5)
- Widerrufbarkeit: Hinweis, dass Statements jederzeit gelöscht werden können
  (Löschfunktion, siehe Abschnitt 8)

---

## 3. Technische & organisatorische Massnahmen (TOM)

### Bereits umgesetzt (Stand dieser Implementierung)

- **Storage-Bucket `bank-statements` ist privat** (im Unterschied zum
  aktuell *publicen* `receipt-images`-Bucket — siehe Hinweis unten)
- **RLS auf allen drei neuen Tabellen** (`bank_statements`,
  `bank_transactions`, `receipt_matches`): `auth.uid() = user_id` für
  select/insert/update/delete
- **Storage-RLS** pfadbasiert: nur `user_id/...` lesbar/schreibbar für den
  jeweiligen Nutzer

### ⚠️ Verwandtes Risiko: `receipt-images` ist public

Der bestehende `receipt-images`-Bucket für Quittungsfotos ist **public**.
Quittungsfotos können ebenfalls indirekt sensible Infos enthalten (Apotheken,
Arztrechnungen als "Quittung" hochgeladen). Empfehlung: **mittelfristig auch
`receipt-images` auf privat + signierte URLs umstellen** — unabhängig vom
Kontoabgleich-Feature, aber im selben Kontext zu bewerten.

### Noch zu tun

1. **Datenminimierung / Masking**: Falls im Buchungstext vollständige IBANs
   oder Kartennummern vorkommen (z.B. bei internen Umbuchungen), sollten diese
   vor der Speicherung erkannt und maskiert werden (z.B. Regex auf
   IBAN-Pattern → `CH** **** **** **** *1234`). Aktuell übernimmt die
   Edge Function den Buchungstext 1:1 — hier besteht Nachschärfungsbedarf.
2. **Retention / Löschung der Original-Datei**: Aktuell wird die
   Original-PDF/CSV dauerhaft im `bank-statements`-Bucket gespeichert. Empfehlung:
   - Originaldatei nach erfolgreichem Parsing **automatisch löschen**
     (nur strukturierte `bank_transactions`-Zeilen bleiben), ODER
   - Nutzer im Consent-Screen explizit fragen, ob die Originaldatei
     aufbewahrt werden soll ("für spätere Nachweise")
3. **Lösch-Funktion pro Statement**: UI-Button "Kontoauszug löschen", der
   `bank_statements` (cascadiert auf `bank_transactions`, `receipt_matches`)
   sowie die Storage-Datei entfernt. Aktuell in `useBankStatements` noch nicht
   implementiert — siehe Checkliste.
4. **Verschlüsselung**: Supabase verschlüsselt Storage/DB at-rest standardmässig
   — keine Zusatzmassnahme nötig, aber im DPIA/Dokumentation erwähnen.

---

## 4. Drittanbieter-Verarbeitung: Anthropic (Claude API)

Jeder Kontoauszug wird zur Strukturierung **base64-codiert an die Anthropic
API (USA) übermittelt** (`supabase/functions/process-statement`). Das ist eine
**grenzüberschreitende Datenübermittlung** von potenziell besonders
schützenswerten Personendaten in die USA.

### Was zu tun ist

1. **Auftragsverarbeitungsvertrag (AVV/DPA) mit Anthropic prüfen/abschliessen**
   — Anthropic bietet kommerzielle DPAs für API-Kunden an (Stand: über die
   Anthropic Console / Sales). Für ein Projekt dieser Grösse: mindestens die
   Standard-DPA-Bedingungen der Anthropic API-Nutzungsbedingungen prüfen und
   referenzieren.
2. **Zero/Limited Data Retention anfragen**: Anthropic bietet für API-Kunden
   die Möglichkeit, die Default-Retention (Abuse-Monitoring, i.d.R. 30 Tage)
   zu reduzieren/abzubestellen. Für Finanzdaten sollte dies explizit angefragt
   werden.
3. **Standard Contractual Clauses (SCCs)**: Soweit Anthropic als
   US-Unternehmen personenbezogene Daten von Schweizer/EU-Nutzern verarbeitet,
   müssen die Übermittlung über SCCs (GDPR) bzw. die entsprechenden
   CH-DSG-Mechanismen abgesichert sein — i.d.R. durch Anthropics
   Standard-Vertragswerk bereits abgedeckt, aber **dokumentieren**.
4. **Offenlegung in der Privacy Policy**: Konkret benennen, dass
   Kontoauszugsdaten an Anthropic (USA) zur automatisierten Analyse
   übermittelt werden — als eigener Punkt, getrennt von der allgemeinen
   "wir nutzen KI für Quittungen"-Klausel, weil die Datenkategorie eine andere
   ist.
5. **Gesonderte Einwilligung für KI-Verarbeitung von Finanzdaten** — kann mit
   dem Consent-Screen aus Abschnitt 2 kombiniert werden, sollte aber den
   Anthropic-Bezug explizit nennen.

---

## 5. Bezug zu `business/DATA_BUSINESS.md`: explizite Ausnahme

`DATA_BUSINESS.md` beschreibt den Plan, **aggregierte, anonymisierte
Quittungsdaten** (Einkaufsverhalten) potenziell an Marktforschung/FMCG zu
lizenzieren.

**Kontoauszug- und Transaktionsdaten (`bank_statements`, `bank_transactions`,
`receipt_matches`) sind von diesem Plan explizit auszuschliessen**, solange
keine separate, zusätzliche, granulare Einwilligung dafür existiert. Gründe:

- Kontodaten erlauben Rückschlüsse auf **Einkommen und Vermögensverhältnisse**
  — eine Datenkategorie, die selbst in aggregierter/anonymisierter Form ein
  deutlich höheres Re-Identifikations- und Reputationsrisiko trägt als
  "kauft oft Bio-Müsli bei Migros"
- Banken/FINMA-Umfeld reagiert sensibel auf Datenflüsse, die wie
  "Bankdaten-Weiterverkauf" aussehen könnten (siehe Abschnitt 7)

**Konkrete Empfehlung**: In der Datenbank/Architektur sicherstellen, dass
etwaige zukünftige Aggregations-/Export-Jobs für das Data-Business
(`DATA_BUSINESS.md`) die Tabellen `bank_statements`, `bank_transactions`,
`receipt_matches` **nicht** einbeziehen — am einfachsten durch eine
Allowlist statt Blocklist im Export-Code, wenn dieser dereinst gebaut wird.

---

## 6. Datenschutz-Folgenabschätzung (DSFA / DPIA)

Nach Art. 22 revDSG (bzw. Art. 35 GDPR) ist eine DSFA erforderlich, wenn eine
Verarbeitung "voraussichtlich ein hohes Risiko für die Persönlichkeit oder
Grundrechte der betroffenen Person" mit sich bringt. Kriterien, die hier
(potenziell) zutreffen:

- Systematische Verarbeitung besonders schützenswerter Personendaten
  (indirekt, via Kontoauszug — siehe Abschnitt 1)
- Einsatz neuer Technologien (KI-basierte Extraktion) zur Auswertung
  persönlicher Finanzdaten
- Automatisiertes Matching/Profiling von Ausgabeverhalten

### Einschätzung für Piggy

Bei einer **kleinen Nutzerzahl und rein nutzerinitiierter, nicht
weitergegebener Verarbeitung** (aktueller Stand: Abgleich nur für den
Nutzer selbst sichtbar, keine Aggregation) ist das Risiko vertretbar, eine
formelle DSFA ist (noch) nicht zwingend.

**Schwelle für eine formelle DSFA**: Sobald
1. die Nutzerzahl signifikant wächst (> einige hundert aktive Nutzer mit
   Kontoabgleich), oder
2. Profiling-Ergebnisse für andere Zwecke verwendet werden (z.B.
   Premium-Feature "Finanz-Score", Werbung, Data-Business),

sollte eine **schriftliche DSFA** erstellt werden (Verarbeitungszweck,
Datenkategorien, Risiken, Massnahmen — die Struktur dieses Dokuments kann
als Ausgangspunkt dienen).

---

## 7. Bankgeheimnis (Art. 47 BankG) — Einordnung

Art. 47 BankG richtet sich an **Banken und ihre Mitarbeitenden/Beauftragten**,
nicht direkt an Endnutzer-Apps wie Piggy. Solange Piggy:

- **keinen Zugriff auf Bank-Logins/Banking-APIs** hat (kein Screen-Scraping,
  kein Open-Banking-Zugriff via Bankschnittstelle), und
- der Nutzer **selbst** seinen eigenen Auszug manuell hochlädt (Datei, die er
  legal von seiner eigenen Bank erhalten hat),

ist Piggy **nicht direkter Adressat** von Art. 47 BankG.

**Trotzdem relevant**:
- Banken/FINMA beobachten Drittanbieter, die mit Bankdaten arbeiten, kritisch.
  Der **manuelle Upload-Ansatz (kein Banking-Login)** ist bewusst die
  risikoärmere Variante und sollte so beibehalten werden — **kein
  Open-Banking/Screen-Scraping ohne erneute Rechtsprüfung** hinzufügen.
- Sollte Piggy später eine direkte Bank-Anbindung (z.B. via Open Banking API,
  PSD2-äquivalent) anbieten wollen, ändert sich die rechtliche Lage
  grundlegend (Lizenzpflicht als Finanzintermediär möglich) — das ist dann
  ein komplett neues Rechtsgutachten wert.

---

## 8. Nutzerrechte: Auskunft, Export, Löschung

Nach DSG Art. 25/32 (CH) bzw. GDPR Art. 15/17 (EU) haben Nutzer Anspruch auf:

- **Auskunft**: welche Kontoauszugsdaten sind gespeichert (Statements,
  Transaktionen, Matches)
- **Export**: Daten in maschinenlesbarem Format
- **Löschung** ("Recht auf Vergessenwerden")

### Konkrete Umsetzung (Empfehlung für Roadmap)

1. **Lösch-Button pro Kontoauszug** in der Abgleich-Ansicht: löscht
   `bank_statements`-Zeile (cascadiert via FK auf `bank_transactions` und
   `receipt_matches`) + Datei im `bank-statements`-Bucket. *(Noch nicht
   implementiert — siehe Checkliste unten.)*
2. **"Alle Kontodaten löschen"** im Profil — analog zu einer evtl.
   bestehenden "Account löschen"-Funktion, aber granular nur für
   Bankdaten.
3. **Export**: Da `bank_transactions` bereits strukturiert (JSON/CSV-fähig)
   in der DB liegt, ist ein Export technisch einfach nachrüstbar (z.B.
   CSV-Download über eine Edge Function) — nicht initial kritisch, aber
   rechtlich gefordert sobald Nutzer es verlangen (Frist: i.d.R. 30 Tage).

---

## 9. Checkliste vor Live-Schaltung des Kontoabgleich-Features

- [ ] **Anwalt konsultieren** (CH-DSG/revDSG + GDPR, Fokus: besondere
      Personendaten via Kontoauszug, Drittland-Übermittlung an Anthropic)
- [ ] **Privacy Policy aktualisieren**: neuer Abschnitt "Kontoabgleich" mit
      Beschreibung der Datenkategorien, Zweck, Anthropic-Übermittlung,
      Aufbewahrungsdauer, Löschmöglichkeit
- [ ] **Separater Consent-Screen** vor erstem Statement-Upload (Abschnitt 2)
- [ ] **AVV/DPA mit Anthropic** prüfen, ggf. Zero/Limited-Retention anfragen
      (Abschnitt 4)
- [ ] **`bank-statements`-Bucket privat** ✅ bereits umgesetzt
- [ ] **RLS auf allen drei neuen Tabellen** ✅ bereits umgesetzt
- [ ] **IBAN/Kartennummern-Masking** im Buchungstext vor Speicherung
      implementieren (Abschnitt 3)
- [ ] **Lösch-Funktion pro Statement** (UI + Backend) implementieren
      (Abschnitt 3 & 8)
- [ ] **Data-Business-Ausschluss** für Bankdaten in Code/Architektur
      dokumentieren bzw. technisch absichern (Abschnitt 5)
- [ ] **DSFA-Schwelle im Auge behalten**: bei Wachstum oder
      Zweckänderung (Profiling, Data-Business) erneut prüfen (Abschnitt 6)
- [ ] Mittelfristig prüfen: **`receipt-images`-Bucket auf privat** umstellen
      (verwandtes Risiko, Abschnitt 3)
