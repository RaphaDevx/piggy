# Piggy: Analytics und Statistik-Verkauf (Konzept, Entwurf)

Stand 2026-11-01. Nichts davon ist gebaut. Vor jedem Code gilt das Datenschutz-Gate aus `K-Dev/CLAUDE.md`: Kaufmann-Legal prüft (`Dashboard/docs/playbooks/datenschutz-pruefung-kaufmann-legal.md`). Keine Rechtsberatung.

## Ziel
1. Produkt-Analytics: verstehen, was genutzt wird und wo Nutzer abspringen.
2. Statistik-Produkt: Aussagen über Gruppen verkaufen, z. B. "35 % der Haushalte mit 100–150k kaufen regelmässig Bio". Nie Daten einzelner Personen.

## Drei Ebenen
| Ebene | Inhalt | Einwilligung | Verlässt Piggy? |
|---|---|---|---|
| A Produkt-Events | Ereignisse ohne Inhalt (Scan gestartet/ok/Fehler, Korrektur ja/nein, Teilen erstellt), App- und iOS-Version, grobes Land | Hinweis in Datenschutzerklärung, Schalter zum Abschalten | Nein |
| B Beitrag zu Statistiken (Opt-in) | Freiwillige Grobangaben: Altersband (10 Jahre), Einkommensband, Grossregion, Haushaltsgrösse. Quittungen nur reduziert: Produktkategorie, Bio/Label ja/nein, Händlerkette, Betragsband, Monat | Eigener Schalter, standardmässig aus, jederzeit widerrufbar, getrennt von den Nutzungsbedingungen | Nur als Aggregat |
| C Verkauf | Aggregierte Tabellen/Berichte nach Regeln unten | gedeckt durch B | Ja, nur Aggregate |

## Schutzregeln für Aggregate (Mindestanforderung, mit Fachperson festlegen)
- Eine Zelle wird nur gezeigt, wenn mindestens k Personen beitragen (Vorschlag k = 50) und kein Einzelner mehr als 10 % der Zelle ausmacht.
- Höchstens zwei Merkmale kombinieren (z. B. Einkommensband x Bio). Keine Drilldowns darunter.
- Zellen unter der Schwelle zusammenlegen oder unterdrücken, ergänzende Zellen mitunterdrücken (sonst per Differenz rückrechenbar).
- Rundung (ganze Prozent) und optional Rauschen (Differential Privacy).
- Keine Rohdaten, keine Zeitstempel unter Monatsgenauigkeit, keine Händler-Einzelbelege in den Export.
- Käufer erhalten nur fertige Berichte oder eine API auf Aggregate, nie Zeilen.
- Verbot der Re-Identifikation und der Weitergabe im Liefervertrag.
- Sensible Kategorien (Apotheke, Arzt, Religion, Politik, Sexualität) nie als eigene Auswertung.

## Technik
- Eigenes Schema `insights` in Supabase, getrennt von den Nutzerdaten. Beitragende nur über eine gesalzene Pseudonym-ID, Salz rotiert.
- Rohbeiträge nur für die Aggregations-Funktion lesbar (RLS, kein App-Zugriff). Aggregation als Datenbank-Funktion mit k-Prüfung, mit Tests (Zelle unter k wird nie ausgegeben).
- Widerruf oder Kontolöschung löscht Beitragszeilen. Bereits veröffentlichte Aggregate bleiben, das in der Erklärung nennen.
- K-Dev Dashboard "Analytics": Reiter Produkt (Ebene A) und Reiter Insights (nur Aggregate, k-Regeln auch hier). Nur Besitzer.

## App Store / Datenschutz
- Label ergänzen: Nutzungsdaten (Produktinteraktion). Mit Ebene B zusätzlich Finanzinfos/Demografie je nach Angabe, "Weitere Finanzdaten" wird dann "Ja". Kein "Tracking", solange nichts mit Drittdaten verknüpft oder an Datenhändler gegeben wird. Prüfen.
- Datenschutzerklärung, `PrivacyInfo.xcprivacy`, `piggy.json` (K-Adv) und Label müssen übereinstimmen.
- Satz in die Erklärung: "Wir verkaufen keine Daten einzelner Personen."

## Realismus
- Verkaufbare Zellen brauchen Tausende aktive Beitragende. Typisch geben nur 10 bis 30 % ein Opt-in. Mit wenigen hundert Nutzern ist (fast) nichts veröffentlichbar.
- Einkommen ist selbst angegeben, also Qualität für Käufer begrenzt. Das offen ausweisen.
- Mögliche Käufer: Marktforschung, Händler, Marken, Bio-Labels. Preis erst nach den ersten echten Berichten festlegen.

## Reihenfolge
0. Kaufmann-Legal: Konzept, Einwilligungstexte, Schwellen, Liefervertrag-Rahmen.
1. Ebene A + Dashboard-Reiter Produkt (geringes Risiko).
2. Opt-in-Bildschirm und Schema `insights` (ohne Verkauf).
3. Aggregation mit k-Regeln und Tests, Reiter Insights.
4. Verkauf erst nach 0 und genug Beitragenden.

## Offene Entscheidungen
- Ebene B schon in 1.0 oder erst 1.1? Vor 1.0 sind es kaum Nutzer, ein späteres Opt-in verliert fast nichts.
- Welche Angaben genau (Einkommensbänder, Regionen)?
- Hosting-Ort der Aggregate und wer Käufer-Zugang bekommt.

## Ergebnis Kaufmann-Legal (2026-11-02), ersetzt widersprechende Stellen oben
Recherche und Entwurf, keine Rechtsberatung. Vor Start von B und C ist eine Fachperson (Datenschutzrecht CH mit EU-Bezug) nötig. Alle Zahlen sind Annahmen.
- **Ebene A:** nach DSG ohne Einwilligung zulässig, aber Apple 5.1.1(ii) verlangt auch bei anonymen Nutzungsdaten Einwilligung und einfachen Widerruf. Umsetzen: Hinweis beim ersten Start plus Schalter. Besser ohne Konto-ID erheben (sonst "verknüpft" im Label). Region nur aus Geräte-Region, nie aus der IP. DE/AT: Einwilligung vorsehen. Heute schreibt die App `user_id` mit: entscheiden (ohne ID oder verknüpft deklarieren).
- **Ebene B:** eigenes, freiwilliges Opt-in, standardmässig aus, nicht an Funktionen gekoppelt, jede Angabe einzeln optional, mindestens 18 Jahre, Verkauf an Marktforschung/Handel/Marken im Text nennen. Die gesalzene, rotierende Pseudonym-ID aus dem Konzept ist falsch: Beiträge müssen dem Konto zuordenbar bleiben (Widerruf, Löschung, Auskunft Art. 25 DSG). Rohbeiträge sind Personendaten, nur das veröffentlichte Aggregat kann anonym sein.
- **Gesundheit:** Apotheke, Arzt, Medikamente, Hygiene, Verhütung im Client ausfiltern, vor jeder Übertragung.
- **Aggregate:** k = 50 Personen (nicht Belege), keine Person über 10 %, höchstens 2 Dimensionen inklusive Monat und Region, nur vorberechnete Berichte (keine freie API), Komplementärunterdrückung, Rundung.
- **Käufer:** Lizenzvertrag mit Verbot der Re-Identifikation und Verknüpfung, keine Weitergabe von Rohzellen, Meldepflicht, Prüfrecht, Offenlegung der Datenqualität, Schweizer Recht.
- **Label:** A = Nutzungsdaten/Produktinteraktion. B = Finanzinfos (Einkommen), Kaufhistorie, übrige Angaben unter "Other Data", verknüpft ja, Tracking nein (Auslegung, Apple 5.1.2(i)/(iii) bleibt Risiko). `PrivacyInfo.xcprivacy`, Datenschutzerklärung, `piggy.json` und Label müssen übereinstimmen.
- **Pflichten:** Informationspflicht Art. 19, Verzeichnis Art. 12, DSFA Art. 22 vor B/C, DPA mit Supabase, Auslandsbekanntgabe (Supabase, Anthropic, Google).
- **piggy.json:** "Daten werden nicht verkauft" widerspricht C. Anthropic und Google fehlen. Änderungen erst nach Freigabe.
- **Offen für Raphael:** B in 1.0 oder 1.1, Bänder und Angaben, DE/AT ja/nein, Mindestalter, Apple-Rückfrage zu 5.1.2.
