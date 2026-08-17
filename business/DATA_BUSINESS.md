# Piggy — Das Data Business

> Du hast recht: Die Daten sind das eigentlich wertvolle Asset.
> Wer Ausgabe-Daten von echten Konsumenten hat, hat Gold.

---

## Was Piggy wirklich hat

Wenn Piggy 10.000 aktive Nutzer hat, sieht Piggy:

- **Was Menschen kaufen** — auf Artikel-Ebene, nicht nur Kategorie
- **Wo sie kaufen** — Migros vs. Coop vs. Aldi vs. Bio-Laden
- **Was sie zahlen** — echte Marktpreise, nicht Listenpreise
- **Wie preissensitiv sie sind** — wechseln sie bei Preiserhöhung?
- **Lebensgewohnheiten** — Convenience vs. Kochen, Bio vs. konventionell
- **Zeitliche Muster** — wann, wie oft, Wochentag-Effekte
- **Zahlungsmittel** — Karte, TWINT, Bargeld

Das ist was Nielsen, IRI, GfK, Circana für hunderte Millionen verkaufen.
Der Unterschied: Du hast echte Receipt-Level-Daten, nicht Panel-Schätzungen.

---

## Wer kauft solche Daten

| Käufer | Wofür | Zahlungsbereitschaft |
|--------|-------|---------------------|
| FMCG-Konzerne (Nestlé, Unilever, Migros) | Marktanteile, Preispositionierung | $$$$ |
| Marktforschungsfirmen (Nielsen, GfK) | Panel-Ergänzung | $$$ |
| Hedge Funds / Investment Research | Alternative Data | $$$$ |
| Retail / E-Commerce | Wettbewerber-Analyse | $$$ |
| Versicherungen | Lifestyle-Scoring | $$ |
| Startups / VC-backed Apps | Consumer Insights | $$ |

**Typische Preise:**
- Aggregierte Category-Daten: $5.000–50.000/Jahr pro Firma
- Granulare SKU-Daten: $50.000–500.000/Jahr
- Custom Reports: $2.000–20.000 per Report

---

## Rechtliche Grundlage (GDPR / CH-DSG)

### Was erlaubt ist

**Aggregierte, anonymisierte Statistiken:**
- "In der Schweiz kaufen 65% der Migros-Käufer auch bei Aldi ein"
- "Bio-Produkte werden 3× öfter von Personen über 35 gekauft"
- Kein Rückschluss auf Einzelpersonen möglich → datenschutzrechtlich sauber

**Voraussetzungen für Datenweitergabe an Dritte:**
1. **Explizite Einwilligung** (Opt-in, nicht Opt-out) im Onboarding
2. **Klare Privacy Policy** mit Erklärung was weitergegeben wird
3. **Echte Anonymisierung** (k-Anonymity: Gruppen von mind. 100 Personen)
4. **Keine Rückidentifikation** möglich (kein Name, keine genaue Adresse, kein UUID)
5. **Datenschutzbeauftragter** ab gewisser Grösse (CH: freiwillig empfohlen)

### Was NICHT erlaubt ist
- Weitergabe von Einzel-Datensätzen ohne explizite Einwilligung
- Pseudonymisierte Daten an Dritte (UUID reicht nicht als Anonymisierung)
- Daten ohne Privacy Policy

### Best Practice — Opt-in mit Anreiz
```
"Teile anonymisierte Einkaufs-Trends und erhalte 
 Premium kostenlos solange du dabei bist."
```
→ Nutzer geben Daten frei, bekommen Gegenwert
→ Rechtlich sauber, ethisch transparent
→ Opt-out jederzeit möglich

---

## Umsetzungs-Roadmap Data Business

### Stufe 1: Fundament (während App-Aufbau)
- Privacy Policy mit Daten-Opt-in
- Einwilligungs-Layer im Onboarding
- Daten sauber strukturiert speichern (tun wir bereits)

### Stufe 2: Erste Auswertungen (ab 500 Nutzern)
- Aggregiertes Dashboard für interne Analyse
- Verstehen was die Daten zeigen
- Erste "Research Report" zum Testen der Zahlungsbereitschaft

### Stufe 3: Produktisierung (ab 2.000 Nutzern)
- Daten-API für verifizierte Käufer
- Subscription für Research-Zugang: $500–5.000/Monat
- Custom Reports auf Anfrage

### Stufe 4: Scale (ab 10.000 Nutzern)
- Partnerschaft mit Marktforschungsfirma (Reseller)
- Direkt an FMCG-Konzerne
- Alternative Data Provider werden

---

## Revenue Potential

| Nutzer | Opt-in Rate | Datensätze | Jahres-Revenue Data |
|--------|------------|------------|---------------------|
| 1.000 | 60% | 600 | $0 (zu klein) |
| 5.000 | 60% | 3.000 | $10.000–50.000 |
| 20.000 | 60% | 12.000 | $100.000–500.000 |
| 100.000 | 60% | 60.000 | $1M–5M |

**Wichtig:** Ab ~5.000 Nutzern mit Opt-in wird das Data Business grösser als das Abo-Business.

---

## Positionierung

Piggy ist nicht "eine App" — Piggy ist eine **Consumer Intelligence Platform**:

1. **Consumer-Seite:** Kostenlose / günstige App → Nutzer-Wachstum
2. **B2B-Seite:** Ausgaben-Insights für Unternehmen → Hauptumsatz

Das ist das Modell von:
- **Waze** (Nutzer gratis, Daten an Gemeinden/Firmen)
- **Nielsen** (Panel-Haushalte bekommen Vergünstigungen)
- **Plaid** (Banktransaktionen → API für Fintechs)

---

## Nächste Schritte (konkret)

1. [ ] Anwalt für Privacy Policy beauftragen (CH-DSG + GDPR konform)
2. [ ] Opt-in Formulierung im Onboarding einbauen
3. [ ] Erste 100 Eigendaten sammeln und analysieren
4. [ ] Research: Wer in CH/DE/AT kauft Receipt-Level Consumer Data?
5. [ ] LinkedIn-Outreach an 3 Market Research Firmen mit "Data Partnership"
