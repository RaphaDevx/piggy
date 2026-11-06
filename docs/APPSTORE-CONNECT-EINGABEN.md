# Piggy – App Store Connect: Eingaben (Vorschlag)

Stand 2026-11-01. App `com.heartbeat.piggy`, ASC-ID 6802367826, Version 1.0 (seit 2026-11-04, vorher 1.0.0), nächster Build 26.
Per API setzbar: `docs/appstore-metadata.json` über den K-Dev-Job `store-metadata` (Anleitung: `Dashboard/docs/TESTFLIGHT.md`). Nur Trockenlauf, bis du `dryRun:false` freigibst. Von Hand bleiben: App Privacy (4), EU-DSA, Screenshots, Demo-Zugang, Review-Kontakt (Name, Telefon, E-Mail), Datenschutz- und Support-URL (erst setzen, wenn gehostet).
Alle Texte sind Vorschläge und halten die Grenzen aus der Skill-Referenz ein (Name 2–30, Untertitel ≤30, Werbetext ≤170, Keywords ≤100 Bytes, keine Preise/URLs/©). Was nicht aus dem Code belegt ist, ist als "prüfen" markiert.

## 0. Reihenfolge
1. Privacy-URL und Support-URL hosten (siehe 2). Ohne echte URLs nicht einreichen.
2. App-Informationen (1), Altersfreigabe (3), App Privacy (4), Version-Seite (5), Review-Infos (6).
3. Build 25 der Version zuordnen, Export-Compliance bestätigen (7).
4. Erst dann "Zur Prüfung hinzufügen". Release-Einstellung: **manuell** (empfohlen für die erste Version).

## 1. App-Informationen
| Feld | Vorschlag |
|---|---|
| Name | **PiggyFinance** (bleibt wie in ASC angelegt) |
| Untertitel | Quittungen scannen und teilen (29 Zeichen; der frühere Vorschlag hatte 34 und war zu lang) |
| Primäre Kategorie | Finanzen |
| Sekundäre Kategorie | Produktivität (alternativ Lifestyle) |
| Preis | Gratis, keine In-App-Käufe |
| Inhaltsrechte (Content Rights) | Nein, enthält keine Inhalte Dritter |
| Datenschutz-URL | Gehostete `privacy.html` (heute Platzhalter in `src/lib/legalLinks.ts`, danach dort ersetzen und neuen Build machen) |
| EU-DSA (Trader) | Ausfüllen, wenn die App in der EU erscheint: Händlerstatus mit Adresse und Telefon. Als Privatperson/Einzelfirma "Trader" ist eine öffentlich sichtbare Adresse nötig. Prüfen |
| Verfügbarkeit | Zuerst nur Schweiz (und evtl. DE/AT). Die Datenschutzerklärung ist auf die Schweiz ausgelegt |

## 2. URLs
- Support-URL: gehostete `support.html`.
- Marketing-URL: leer lassen.
- Eine Kontaktadresse festlegen und überall gleich verwenden (`support.html`, `privacy.html`, Profil, Review-Kontakt). Heute ist es eine private Outlook-Adresse.

## 3. Altersfreigabe (Fragebogen)
Alle Fragen zu Gewalt, Sexualität, Horror, Glücksspiel, Alkohol/Drogen, Medizin, Waffen, Beleidigungen: **Keine**.
| Frage | Antwort | Begründung |
|---|---|---|
| Uneingeschränkter Webzugriff | Nein | Keine Web-Ansicht beliebiger Seiten |
| Nutzergenerierte Inhalte / Chat | **Prüfen**: Freunde sehen Namen und Notizen in geteilten Rechnungen, kein Chat, keine öffentlichen Inhalte | Ehrlich "Nein" nur, wenn nichts Freitext-Inhaltliches an andere geht. Bei "Ja" verlangt Apple Melden und Blockieren (Guideline 1.2) |
| Simuliertes Glücksspiel / Lootboxen | Nein | |
| Echtes Geld / Zahlungen | Nein | Piggy hält kein Geld und löst keine Zahlung aus. Die Zahlungsanforderung ist nur ein Text oder QR-Code |
| Erwartetes Ergebnis | 4+ | Nach dem Fragebogen kontrollieren, Apple kann abweichen |

Offen: Mindestalter in `privacy.html` festlegen (z. B. 16, schweizerisch/EU-üblich für Datenverarbeitung). Das ist eine rechtliche Entscheidung, nicht Teil des Fragebogens.

## 4. App Privacy (Datenschutzangaben)
Tracking: **Nein**. Werbe-ID: **Nein**. Keine Analytics-/Crash-/Werbe-SDKs im Code (laut Befund geprüft).
| Datenart | Erhoben | Mit Nutzer verknüpft | Tracking | Zweck |
|---|---|---|---|---|
| E-Mail-Adresse | Ja | Ja | Nein | App-Funktionalität |
| Name | Ja | Ja | Nein | App-Funktionalität |
| User ID | Ja | Ja | Nein | App-Funktionalität |
| Fotos | Ja (Quittungsbilder, privater Bucket) | Ja | Nein | App-Funktionalität |
| Weitere Nutzerinhalte | Ja (Notizen, Artikel, Projekte, Rohtext) | Ja | Nein | App-Funktionalität |
| Zahlungsinfo | Ja (nur Kartenendziffern auf Quittungen) | Ja | Nein | App-Funktionalität |
| Kaufhistorie | Ja (Quittungsposten) | Ja | Nein | App-Funktionalität |
| Weitere Finanzdaten | **Ja** (Rechtsprüfung 2026-11-02, L-01): `PrivacyInfo` in app.json deklariert es, und Kontoauszug-Altdaten können in Konten liegen. Verknüpft Ja, Tracking Nein, App-Funktionalität | Ja | Nein | App-Funktionalität |
| Kontakte, Standort, Gesundheit, Nutzungsdaten, Diagnose | Nein | | | |
Hinweis: Quittungstext geht nur nach Einwilligung an Anthropic (USA). Eigene Gemini-/Claude-Schlüssel sind in 1.0 ausgeblendet (`FEATURES.ownApiKeys=false`), Google erhält nichts. Die Angaben müssen mit `privacy.html`, `PrivacyInfo.xcprivacy` und `piggy.json` (K-Adv) übereinstimmen.

## 5. Version 1.0.0 (Deutsch, Schweiz)
**Werbetext (≤170):**
Quittung fotografieren, Piggy liest Händler, Artikel und Betrag aus. Ausgaben nach Kategorie sehen und Einkäufe mit Freunden fair teilen.

**Beschreibung (Entwurf):**
Piggy macht aus Quittungen eine Übersicht deiner Ausgaben.

• Quittung scannen: Dokumentenscanner der Kamera, Piggy erkennt Händler, Datum, Artikel und Betrag.
• Eigene Kontrolle: Du siehst, was erkannt wurde, korrigierst es und kannst eine Quittung mit KI erneut auslesen.
• Ausgaben verstehen: Monats- und Kategorieübersicht aus deinen Quittungen.
• Mit Freunden teilen: Einkauf nach Artikeln, 50/50 oder festem Betrag aufteilen. Piggy erstellt den Text oder einen QR-Rechnungscode, den du verschickst. Piggy bewegt kein Geld.
• Deine Daten: Quittungsbilder liegen in deinem privaten Bereich. Die KI-Auswertung startet erst nach deiner Zustimmung. Dein Konto kannst du jederzeit in der App löschen.

**Keywords (≤100 Bytes, kommagetrennt, ohne Markennamen):**
`Quittung,Kassenzettel,Beleg,Ausgaben,Budget,scannen,teilen,Haushalt,Rechnung,Einkauf`
(Länge vor dem Einfügen in ASC prüfen.)

**Neuerungen:** Erste Version.
**Copyright:** 2026 Raphael Kaufmann
**Lokalisierung:** Deutsch (Schweiz) als Hauptsprache. Englisch nur, wenn die App englisch ist (prüfen).

**Screenshots:** iPhone 6,9 Zoll (z. B. 1320x2868 oder 1290x2796, nach Skill-Referenz prüfen), PNG ohne Alpha, bis zu 10. Aufnahme im Simulator oder auf dem Gerät, nicht aus dem Web-Export.
Reihenfolge: 1 Scan, 2 erkannte Quittung, 3 Ausgabenübersicht, 4 Kategorien, 5 Teilen mit Freunden. Keine Preise, kein ©, keine Fremdplattform. Danach `check-assets.py` laufen lassen.

## 6. App-Review-Informationen
| Feld | Eintrag |
|---|---|
| Anmeldung erforderlich | Ja |
| Benutzername / Passwort | Aus `~/Public/ToDo/piggy-review-zugang.txt` (nicht ablaufendes Demo-Konto, Passwort nach der Freigabe rotieren) |
| Kontakt | Name, Telefon mit Landesvorwahl (+41…), eine einheitliche E-Mail |
| Notizen | Text aus `docs/APPSTORE-REVIEW-NOTES.md` (englisch, Abschnitt "Text für das Feld Notizen"). Dort steht jetzt "light appearance only" |
Wichtig: Das Demo-Konto muss vor der Einreichung wirklich mit E-Mail und Passwort anmelden können (Registrierung per E-Mail ist absichtlich gesperrt). Einmal selbst im TestFlight-Build testen.

## 7. Build und Compliance
- Build 25 steht in TestFlight (hochgeladen). Der Job meldete "failed" nur, weil die externe Gruppe "Raphael Private Beta" Apples Beta-Review braucht. Der Build selbst ist da. Das Gate (L1–L10) gilt für externes TestFlight, es ist noch nicht bestanden.
- Verschlüsselung (Export-Compliance): Standard-HTTPS, nichts Eigenes. Laut Befund im Code erfüllt, in ASC "Ja, nur Standard-Verschlüsselung / ausgenommen" wählen. Prüfen, ob `ITSAppUsesNonExemptEncryption=false` im Build steht.
- Sign in with Apple: Die Löschfunktion widerruft das Token. Secrets für `delete-account` in Supabase müssen gesetzt sein, sonst scheitert die Löschung im Review.
- Vor dem Einreichen im TestFlight-Build testen: Apple-Login, Konto löschen, Scan mit und ohne KI-Zustimmung, QR-Rechnung mit einer Banking-App.

## 8. Bekannte Ablehnungsrisiken
1. Platzhalter-URLs für Datenschutz und Support (Guideline 5.1.1).
2. Demo-Konto funktioniert nicht (2.1).
3. Freunde-Funktion ohne Melden/Blockieren (1.2), wenn Apple sie als nutzergenerierte Inhalte wertet.
4. Datenschutzangaben stimmen nicht mit dem Verhalten überein, besonders KI-Weitergabe (5.1.1, 5.1.2).
5. Finanz-App: Piggy darf nicht den Eindruck eines Zahlungsdienstes erwecken (Texte neutral halten).

## 9. Stand 2026-11-04 (Rechtsprüfung Dossier piggy-analytics)
- Analytics, Ebene B und C bleiben in 1.0 aus (`FEATURES.analytics=false`, Migration nicht angewendet). Label deshalb ohne Nutzungsdaten.
- Datenschutzerklärung: Gemini entfernt, Aussage zu Verkauf präzisiert, Mindestalter 16, Garantie für Anthropic genannt. `piggy.json` entsprechend.
- Offen von Hand: Supabase-Region und Lage der Edge Functions im Dashboard bestätigen; Anthropic-Vertragsart (Commercial Terms) bestätigen.
