# Piggy: Wie Geld verdienen (Modell und Rechnung)

Stand 2026-11-04. Rechnung: `docs/monetarisierung.py` (Annahmen oben im Skript, ändern und neu laufen lassen). Alle Erlöszahlen sind Schätzungen, keine Messwerte. Rechtlich massgebend ist das Dossier `K-Adv/docs/rechtsanalyse/piggy-analytics/99-gesamtergebnis.md`. Keine Rechtsberatung.

## 1. Wie vergleichbare Apps verdienen
Aus Allgemeinwissen, nicht frisch belegt (vor Entscheidungen prüfen):
- **Splitwise, YNAB, Monarch:** Abo, meist Freemium. Splitwise begrenzt die Gratis-Nutzung (Nutzer berichten von 3 Einträgen pro Tag), YNAB verlangt ein höheres Jahresabo ohne dauerhaftes Gratis-Angebot.
- **Fetch, Ibotta, Receipt-Hog-Apps:** Nutzer scannen Quittungen gegen Punkte. Bezahlt werden sie von **Marken**, die Aktionen und Kampagnen kaufen, nicht von Nutzern. Das setzt Millionen Nutzer und Markenverträge voraus.
- **Mint (eingestellt), Finanz-Aggregatoren:** Provisionen für vermittelte Kredit-, Versicherungs- und Kontoprodukte (Affiliate).
- **Marktforschungs-Panels (z. B. Kantar, Nielsen Consumer Panel):** Aggregierte Einkaufsdaten, die Haushalte freiwillig und meist gegen Entgelt beitragen. Das ist der Markt für Ebene C.
Bestätigt durch Recherche (Quellen unten): Gebührensätze und die Gratis-zu-Zahlend-Quote.

## 2. Kanäle und Gebühren
| Kanal | Gebühr | Netto bei 39 CHF Jahresabo (nach 8,1 % MWST) |
|---|---|---|
| iOS App Store | 15 % mit Small Business Program (Anmeldung nötig, unter 1 Mio. USD Umsatz) | 30.67 |
| Google Play (Android) | 15 % bei Abos (neues Modell ab 2026 teils 10 % + 5 % Abrechnung, Quellen widersprechen sich) | 30.67 |
| Web (Stripe) | 2,9 % + 0.30 CHF Schweizer Karte, 3,25 % + 0.30 Auslandskarte | 34.61 |
Web bringt rund 13 % mehr pro Abo. Aber: Apple verbietet in der App, auf externe Zahlung zu verweisen (ausser in einzelnen Märkten), also kommt Web-Umsatz nur von Nutzern, die Piggy dort direkt finden. Die Regeln für Hinweise auf Web-Zahlung vor Einführung prüfen.

## 3. Drei Ertragsquellen
**A. Abo (Piggy Plus), tragende Säule.** Gratis: Scannen, Übersicht, Teilen mit Limit. Plus (Vorschlag 39 CHF/Jahr oder 4.90/Monat): unbegrenzte KI-Scans, Exporte (CSV/PDF), Budgets, Auswertungen über Jahre, Familienkonto. Gratis-Nutzer verursachen KI-Kosten (ca. 0.15–0.30 CHF pro aktivem Nutzer und Jahr), deshalb Tageslimit beibehalten. Quote Gratis → Zahlend: Freemium im Schnitt rund 2 % (RevenueCat 2026), im Modell 1,5 / 2,5 / 4 %.

**B. Partner-Integration (Affiliate/Angebote), Beiträge unter 1 CHF pro Nutzer.** Passende, klar gekennzeichnete Hinweise: Strom- und Krankenkassen-Vergleiche, Cashback-Partner, Bio-Lieferdienste, Haushaltsversicherung. Annahme 0.10 / 0.40 / 1.20 CHF pro aktivem Nutzer und Jahr. Wichtig: Personalisierte Angebote auf Basis der Quittungen sind Profiling und brauchen eigene Einwilligung und Label-Angaben (Zweck «Third-Party Advertising» bzw. «Product Personalization»). Allgemeine Hinweise ohne Auswertung sind einfacher. Keine Werbebanner: zu wenig Ertrag, schadet dem Vertrauen.

**C. Daten (Ebene B/C), erst bei grosser Basis und nur aggregiert.** Käufer: Marktforschung, Händler, Marken. Voraussetzungen aus dem Dossier: Opt-in ab 18, k ≥ 50, keine Zelle mit einer Person über 10 %, höchstens 2 Dimensionen, Käufervertrag. Mindestgrundgesamtheit 1000 Beitragende, dafür braucht es bei 15 % Opt-in rund 7000 aktive Nutzer. Annahme: 1 / 3 / 6 Käufer je nach Grösse, 3000 / 6000 / 12000 CHF Lizenz pro Käufer und Jahr. **Gesetzlich zulässig, aber für iOS-Daten wegen Apple-Vertrag verboten (C-02).** Rechnung zeigt beide Fälle: nur Web-Nutzer als Quelle, oder Apple stimmt schriftlich zu.

## 4. Ergebnis (Basisszenario, CHF pro Jahr)
Kanäle: iOS 75 %, Web 25 % (Android erst später, ändert die Summe kaum). Kosten: 300 fix + 0.25 pro aktivem Nutzer (Supabase, KI).
| Aktive Nutzer | Zahlende | Abo | Partner | Daten (nur Web) | Daten (Apple ok) | Kosten | Gewinn (Daten nur Web) |
|---|---|---|---|---|---|---|---|
| 1 000 | 25 | 791 | 400 | 0 | 0 | 550 | 641 |
| 5 000 | 125 | 3 956 | 2 000 | 0 | 0 | 1 550 | 4 406 |
| 20 000 | 500 | 15 825 | 8 000 | 0 | 6 000 | 5 300 | 18 525 |
| 100 000 | 2 500 | 79 127 | 40 000 | 6 000 | 18 000 | 25 300 | 99 827 |
Vorsichtig / optimistisch bei 20 000 Nutzern: Gewinn 1 755 bzw. 63 529 (Daten nur Web). Alle Zeilen: `python3 docs/monetarisierung.py`.

**Was das heisst**
- Das **Abo trägt** ca. 60–75 % des Ertrags. Partner-Integration bringt je nach Qualität 15–40 % dazu. Daten bringen vor 20 000 aktiven Nutzern praktisch nichts, danach fünfstellig, bleiben aber das Risiko-reichste Feld (Apple, Vertrauen).
- Bei 1000 Nutzern deckt der Ertrag knapp die Kosten. Reich wird man mit Piggy erst ab einigen zehntausend aktiven Nutzern. Das eigentliche Problem ist Nutzergewinnung, nicht Preisgestaltung.
- Einkommen aus Selbstangabe (Einkommensband) macht Daten für Käufer weniger wertvoll. Das senkt die Lizenzpreise unten im Modell eher als es sie erhöht.
- Nicht eingerechnet: Einkommenssteuer, AHV (rund 10 % bei Einzelfirma), MWST-Pflicht ab 100 000 CHF Umsatz, Rechtsform (R-01 im Dossier), Marketing, Support.

## 5. Empfehlung, Reihenfolge
1. **1.0:** gratis, ohne Analytics (wie jetzt). Ziel: Nutzer, Rückmeldungen.
2. **1.1:** Analytics Ebene A (mit Zustimmung), Abo Plus mit RevenueCat oder StoreKit, Small Business Program anmelden.
3. **Web-App und Stripe:** Abo auch im Web anbieten (13 % mehr Netto), Android über Play Billing.
4. **Partner-Hinweise** ab einigen tausend aktiven Nutzern, zuerst ohne Personalisierung.
5. **Daten (B/C)** erst bei etwa 7 000 aktiven Nutzern und nach Entscheid zu Apple (C-02). Bis dahin nur Web-Quelle denkbar.

## Quellen
- Apple Small Business Program: adapty.io/blog/app-store-small-business-program und weitere Blogs. Zu Abos im ersten Jahr widersprechen sich die Quellen, Apple-Seite prüfen.
- Google Play Abo-Gebühren 2026: pricepush.app/blog/google-play-subscription-fees-2026-real-math und weitere. Widersprüchlich, Play-Console-Seite prüfen.
- Stripe Schweiz: support.stripe.com/questions/pricing-updates-for-businesses-in-switzerland (2.9 % + 0.30 CHF; Auslandskarte 3,25 %).
- RevenueCat State of Subscription Apps 2026 (Freemium-Median rund 2 %): revenuecat.com/blog/growth/subscription-app-trends-benchmarks-2026.
