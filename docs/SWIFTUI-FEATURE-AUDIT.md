# Piggy — vollständiger Funktionsvergleich Expo → SwiftUI

Stand 27.11.2026. Grundlage: alle 23 alten App-Dateien (inklusive Layout-Routen), src/hooks, aktive Feature-Schalter und alle nativen Screens/Services. Dies ist eine Quellcode-Inventur mit gezielten automatisierten Prüfungen, kein behaupteter vollständiger iPhone-End-to-End-Test. „Vorhanden“ bedeutet implementiert, nicht am Gerät vollständig verifiziert. Änderungen nach TestFlight 1.0(31) sind separat ausgewiesen.

## Ursache der Regressionen

Die iPhone-App wurde neu in SwiftUI geschrieben. Der Build wurde geprüft, aber die funktionale Gleichwertigkeit war unvollständig: die gespeicherte Duplikatliste fehlt, Zusatzfelder werden im nativen Antwortdecoder verworfen, und mehrere Detail-/Social-Funktionen wurden nur teilweise übernommen. Der lokale Scanvergleich prüft nur exakt Datum, Betrag, Währung und normalisierten Laden; eine Abweichung oder veraltete Belegliste ergibt keinen Treffer. Das ist kein Nachweis, dass das bestehende Backend kaputt ist.

## Inventar und Arbeitsreihenfolge

| ID | Funktion der alten App / Auftrag | Native Feststellung vor Reparatur | Priorität / Abnahme |
|---|---|---|---|
| A01 | Apple-Anmeldung | Vorhanden; kompletter Geräte-Consent offen | Gerät |
| A02 | Google-Anmeldung | Reparierter PKCE-Flow in Build31; kompletter Geräte-Consent offen | Gerät |
| A03 | Bestehende E-Mail-Konten, Passwortreset | Vorhanden seit Build31; reale Zustellung offen | Gerät |
| A04 | Sitzung/Abmelden/Kontowechsel | Vorhanden, Konto-Zustand wird geleert | Regressionstest |
| A05 | Google/Apple an bestehendes Konto verknüpfen/trennen | Profilfunktionen fehlen | P2, bestehende Identitäten erhalten |
| A06 | Konto löschen inkl. Apple-Widerruf | Implementiert; destruktiver Produktionstest nicht durchgeführt | Gerät/Testkonto |
| S01 | Dokumentenkamera mit Kantenerkennung | VisionKit vorhanden; echte Kamera offen | Gerät |
| S02 | Fotoimport | Einzelfoto vorhanden | Gerät |
| S03 | Mehrfachscan | Mehrere Kameraseiten, sequentielle Prüfung vorhanden; kein persistenter Hintergrundjob | P2 |
| S04 | KI- und lokale Erkennung | Vorhanden; bei Cloudfehler bisher leerer manueller Entwurf statt OCR-Fallback | P1 |
| S05 | Manuelle Erfassung | Vorhanden | Gerät |
| S06 | KI-Einwilligung und Widerruf | Vorhanden; asynchrones Speichern im Profil | Gerät |
| S07 | Eigener KI-Key | In alter App ausdrücklich deaktiviert, kein aktiver Paritätsverlust | Bewusst aus |
| S08 | Apple Foundation Models/on-device KI | Native App verwendet Vision-OCR plus konservativen Parser, keinen Foundation-Models-Parser | P2, Geräteverfügbarkeit |
| S09 | Tageslimit/Goldlimit | Server aktiv; spezielle native Limitmeldung fehlt | P2 |
| S10 | Scan nach Kameradismiss starten | Recognition startete innerhalb Kameracallback | P1, Präsentationsrennen |
| R01 | Gesamt-/Artikelbetrag direkt ändern | Dialog vorhanden, schlecht erkennbar; Dezimalformat problematisch | P0, aktuelle Reparatur |
| R02 | Mengen/Einzelpreise/Einheit bearbeiten | In Detailbearbeitung unvollständig | P1 |
| R03 | Artikel hinzufügen/löschen nach Speichern | Fehlte im nativen Editor; nur beim Scan vorhanden | P1, Verknüpfungen schützen |
| R04 | Datum/Geschäft/Währung/Zahlungsart/Notiz | Vorhanden; Kalenderprüfung im Service | Regressionstest |
| R05 | Ladenkategorie bearbeiten/anzeigen | Modell vorhanden, Editor/Detail fehlten | P1 |
| R06 | Artikel-Haupt-/Unterkategorie | Hauptkategorie vorhanden; Unterkategorieauswahl fehlt | P2 |
| R07 | Eigene Kategorien | Alter Hook vorhanden, native Verwaltung fehlt | P2 |
| R08 | Originalbild/Zoom | Vorhanden, aber URL-Altformate werden abgelehnt | P1, signierte Pfade normalisieren |
| R09 | Belegzusatzdaten (Bon-, Kassen-, UID-Nr, Adresse, Kontakt) | Server liefert extra, Swift-Decoder verwirft sie | P1 |
| R10 | OCR-/Markdowntext ansehen | In alter App vorhanden; native Anzeige fehlt | P1 |
| R11 | Projektauswahl am Beleg | Vorhanden | Gerät |
| R12 | Sicheres Löschen | Native RPC schützt Beziehungen, konservativer als alter Direkt-Delete | Regressionstest |
| R13 | Teilen der Übersicht | Vorhanden als Text; kein PDF-Export | P2, PDF war bereits Backlog |
| R14 | Suche nach Geschäft/Artikel | Vorhanden; Notizen/Zahlung/Metadaten fehlen | P1 |
| R15 | Filter nach Zeitraum/Kategorie/Zahlungsart | Alte Belegliste umfangreicher, native nur Währung/Suche | P2 |
| D01 | Scan-Duplikathinweis | Nur exakter Vergleich mit lokalem, eventuell altem Bestand | P0 |
| D02 | Bereits gespeicherte Duplikate finden | Native Route/Verwaltung fehlte vollständig | P0 |
| D03 | Dauerhaft getrennt halten | Backend vorhanden; native Bestandsliste/Entscheidungen fehlen | P0 |
| D04 | Merge mit Auswahl von Bild, Artikeln und Feldern | Native Scanmerge überträgt kein neues Foto, keine Auswahl; alter Bestandmerge fehlt | P0 |
| C01 | Meine Karten anlegen/ändern/löschen | Fehlte in Build31; aktuelle Reparatur nutzt bestehende Tabelle | P0 |
| C02 | Einheitliche Kartenformate | Fehlte; aktuelle konservative Endziffern-/Netzwerkauflösung | P0 |
| C03 | Apple-Pay-Kartennummer | Separate Endziffern möglich, keine automatische Token-zu-Plastikkarte-Behauptung | Gerät |
| C04 | Freunde über Karten vorschlagen, Beleg senden | Alter CardMatchBanner; native Beleg-Sharing fehlt | P2 |
| F01 | Monatsausgaben/Währungsseparation | Vorhanden über kanonischen Serveradapter | Gerät/API |
| F02 | Woche/Monat/3 Monate/Jahr/Kategorien | Woche/Monat/Jahr vorhanden; native Quartalsauswahl ist Kalenderquartal statt altem 3-Monats-Zeitraum | Core/Apple geprüft; Zeitraumparität offen |
| F03 | Kategorie → Artikel/Belege | Vorhanden | Gerät |
| F04 | Händlerstatistik und Finanzprofil | Alte Analyse-/Profilansichten umfangreicher, native nur Kategorien | P2 |
| F05 | Bankdaten in Gesamtübersicht | Native-finance lädt nur Belege, obwohl Banktab aktiv ist | P1, kanonische Backendintegration |
| B01 | PDF/CSV/CAMT-Kontoauszüge importieren | Native UI/Worker vorhanden, Einwilligung vorhanden | Gerät/API |
| B02 | Importfortschritt/Retry | Vorhanden mit Polling | Gerät/API |
| B03 | Automatisch/manuell zuordnen | Vorhanden; Service schreibt mehrere Schritte | Gerät/API |
| B04 | Differenzvorschläge annehmen/ablehnen | Vorhanden; keine automatische Übernahme | Gerät/API |
| B05 | Verknüpften Bankbeleg direkt im Beleg sehen | Im nativen Belegdetail fehlt der Rückverweis | P1 |
| B06 | Auszug löschen | Vorhanden; löscht Datei vor DB-Zeile, Fehlerfall riskant | P1 |
| B07 | Bestätigten Bankposten an Freunde senden | Absichtlich separater Schalter aus, nicht mit Beleg-Sharing verwechseln | Bewusst aus |
| G01 | Freunde per Code / Anfragen / Entfernen | Vorhanden | Gerät/API |
| G02 | QR-Code anzeigen/scannen und piggy://add-Link | Native UI/Deep-Link-Verarbeitung fehlt | P2 |
| G03 | Eingehende Belege annehmen/ablehnen | Native UI/Modell fehlt | P2 |
| G04 | Projekte anlegen, Mitglieder einladen | Service kann Mitglieder, Erstellungsformular übergibt immer leere Liste | P1 |
| G05 | Projektmitglieder/Belege ansehen | Vorhanden; gemeinsame fremde Belege fehlen im eigenen Receiptbestand | P2 |
| G06 | Projektsaldo/Schulden vereinfachen, Projektverwaltung | In altem Projektbildschirm vorhanden, native Detailansicht fehlt | P2 |
| G07 | Ausgaben teilen/Artikel ausschliessen | Vorhanden; vorhandenen Split bearbeiten fehlt, keine zerstörende Neuaufteilung | P2 |
| G08 | Offene/beglichene Beträge | Vorhanden, beglichene Liste auf erste20 begrenzt | P2 |
| G09 | Zahlungsanforderung mit lokalen Zahlungsdaten | Alte PaymentRequestSheet vorhanden, native fehlt | P2 |
| G10 | Echtzeit-/Toast-Aktualisierungen | Native überwiegend manuelles Neuladen statt Realtime | P2 |
| M01 | Gold kaufen/wiederherstellen | Code vorhanden, nur mit öffentlichem RevenueCat-Key; StoreKit-Gerätetest offen | Konfiguration/Gerät |
| M02 | Werbung/Datenschutz/Gold-Werbefreiheit | Standardmässig deaktiviert, bewusst kein Paritätsfehler | Bewusst aus |
| M03 | Nutzungsanalytik | In alter App bewusst deaktiviert | Bewusst aus |
| U01 | Onboarding/Replay/Maskottchen/Reduce Motion | Vorhanden, Retina in Build31 | Gerät/Dynamic Type |
| U02 | Sprache/übersetzte Kategorien | Native UI deutsch; alte Kategorie-Labels localeabhängig | P2 |
| U03 | Datenschutz/Terms/Supportlinks | Nur bei konfigurierten HTTPS-URLs sichtbar; echte Inhalte prüfen | Konfiguration |
| N01 | Parkbeginn/-ende/-dauer und Durchschnittspreise | In alter App keine strukturierte Funktion gefunden; neuer ausdrücklicher Auftrag | P1 neu |
| N02 | Garantie/Rückgabe/Trinkgeld/Rabatte/Preisvergleich | Rabattartikel und Bank-Trinkgeldvorschlag vorhanden; Fristen/Preisvergleich neue Ideen | Vorschlag, kein Altbestand |

## Iterationen / Belege

1. Beträge und Karten: Quellcommit cc2f9458, K-Dev ec405a3719a81b0a: Core-Tests und Simulator-Build erfolgreich. Zusätzliche maskierte Kartenvarianten noch im Arbeitsstand; Build31 enthält diese Reparaturen nicht.
2. Duplikate, Metadaten, Parkbelege und konkrete Quellcodefehler: in Arbeit. Keine stillen automatischen Zusammenführungen. Vor Auslieferung neue Tests und Apple-Build.

Alle offenen Geräte-/Backendprüfungen bleiben offen, bis echte Nachweise vorliegen. Ein erfolgreicher Compilerlauf ist keine Bestätigung von Kamera, OAuth-Consent, Bezahlvorgängen oder authentifiziertem Datenbank-CRUD.

## Reparaturstand nach Iteration 2/3

| Einträge | Aktueller Stand im Quellcode | Nachweis / verbleibende Grenze |
|---|---|---|
| R01, C01, C02 | Beträge direkt antippbar, Dezimaleingabe inkl. Komma/Punkt/Minus; Kartenverwaltung und einheitliche Darstellung | Erste Apple-Prüfung ec405a3719a81b0a erfolgreich; finale Varianten erneut prüfen |
| D01 | Bestand wird vor Scan-Duplikatprüfung neu geladen; unbekanntes Scandatum nicht als heutiger Tag erfunden; parallele Speicherklicks blockiert | Coretests; finaler Apple-Build ausstehend |
| D02, D03 | Belege → Duplikate prüfen, vollständige eigene Belegliste und persistierte Getrennt-Entscheidungen; Paarvergleich pro Konto/Währung | Apple-Prüfung 1327c4b062f1c590 erfolgreich; echte RPC-Bedienung offen |
| D04 | Bestandsmerge mit Auswahl/Vorschau für Felder, Zusatzdaten, Bild und Artikel; Scanmerge mit neuem Fotoupload und ausdrücklicher Bestätigung | Bild-/Artikelauswahl beim Scan; Feldkonflikte dort konservativ beim Bestand. Keine automatische Zusammenführung. Zusätzliche Parkfelder behalten beim Merge vorhandene Werte; anschliessend separat editierbar |
| R02, R03, R05 | Menge/Einzelpreis/Einheit/Ladenkategorie editierbar; Artikel ergänzen/entfernen, Ausgleichsposten geschützt, Entfernung bei vorhandener Aufteilung blockiert | Apple-Prüfung 1327c4b062f1c590 erfolgreich; echte RLS-/CRUD-Abnahme offen |
| R07 | Eigene Kategorie anlegbar und vorhandene eigene Kategorien im Picker | Keine separate Kategorieverwaltung in der alten App; dort ebenfalls aus Artikel-Tags abgeleitet |
| R08 | Legacy-Supabase-Bildadressen werden nur vom konfigurierten Host und eigenen Nutzerpfad in frisch signierte Pfade überführt | Neue Negativtests für fremde Hosts/Nutzer; keine Direktfreigabe öffentlicher Fremd-URLs |
| R09, R10, R14 | Zusatzfelder werden dekodiert/gespeichert, OCR-/Belegtext angezeigt, Suche erweitert auf Notiz/Zahlung/Zusatzfelder | Apple-Prüfung 1327c4b062f1c590 erfolgreich |
| R15 | Zeitraum-/Kategorie-/Zahlungsartfilter ergänzt, leerer Filtertreffer nicht als fehlender Belegbestand bezeichnet | Finaler Apple-Build ausstehend |
| S04, S10 | Cloudfehler behalten lokalen OCR-Entwurf mit sichtbarer Warnung; Erkennung startet nach Kameradismiss | Apple-Prüfung 1327c4b062f1c590 erfolgreich; echte Kamera offen |
| G04 | Projekterstellung erlaubt nun Freundesauswahl statt immer leerer Einladungsliste | Finaler Apple-Build und echtes RLS offen |
| B05 | Bankzuordnung mit Datum/Betrag im Belegdetail und Link zum Abgleich ergänzt | Finaler Apple-Build ausstehend |
| N01 | Ankunft/Abfahrt/gedruckte Minuten/Parkplatz/Kennzeichen aus beschriftetem OCR oder manueller Korrektur; Dauer, Durchschnitt je Minute/Stunde | Neue Tests inkl. Nachtwechsel, fehlender Zeit, ungültigem Datum, gedruckter Dauer und bewusst gelöschtem Wert. Kein Tarifversprechen |

### Weiter offen, nicht als fertig markieren

- Vollständige iPhone-Abnahme für Login/Kamera/Karten-CRUD/Bankworker/Mehrfachscan/StoreKit, Datenschutzlinks und Dynamic Type. Simulator-Kompilierung beweist diese Abläufe nicht.
- A05 Konten verknüpfen/trennen; G02 QR und Add-Deep-Link; G03/C04 echte Belegweitergabe/Empfang/Kartenvorschläge von Freunden; G05/G06 volle gemeinsame Projektbelege und Projektbilanz; G07 vorhandene Splits ändern; G09 Zahlungsanforderungen; G10 Echtzeit.
- R06 Unterkategorieauswahl; F04 Händler-/Finanzprofil; F05 Bankdaten in der zentralen Finanzübersicht; S08 Foundation Models, S09 konkrete Scanlimitmeldungen, S03 persistente Hintergrund-Scanqueue; U02 gesamte UI-Lokalisierung.
- B06 robustes gemeinsames Löschen von Datenbankeintrag und externer Datei braucht eine Backend-Aufräumstrategie. Der bestehende Ablauf wurde nicht als atomar zertifiziert.
- PDF-Export/Garantie-/Rückgabefristen/Preisvergleiche sind zusätzliche Produktideen; die alte App enthielt dafür keinen fertigen Ablauf. Rabatte waren bereits negative Artikel, Trinkgeld ein Bank-Differenzvorschlag.

### Backend-Verifikationsgrenze

Die Spalten der vorhandenen profile_cards-Tabelle sind per leerer anonymer REST-Abfrage erreichbar (HTTP200, keine Zeilen abgerufen). Authentifizierte Karten-CRUD und vorhandene UPDATE-Policy sind dadurch **nicht** verifiziert. Der lokale kvault-Zugriff für eine nur lesende Policy-Abfrage scheitert an Socket-Rechten; sudo ist nicht passwortlos freigegeben. Kein Service-Key/Passwort angefordert, keine Policies verändert. Das ist eine offene Prüfung, kein bestätigter RLS-Fehler.

## Geprüfte alte Routen/Layouts

- `app/(auth)/_layout.tsx`
- `app/(auth)/login.tsx`
- `app/(tabs)/_layout.tsx`
- `app/(tabs)/analyse.tsx`
- `app/(tabs)/finanzen.tsx`
- `app/(tabs)/index.tsx`
- `app/(tabs)/profil.tsx`
- `app/(tabs)/projekte.tsx`
- `app/(tabs)/quittungen.tsx`
- `app/_layout.tsx`
- `app/category/[group].tsx`
- `app/duplicates.tsx`
- `app/friends/_layout.tsx`
- `app/friends/add.tsx`
- `app/friends/index.tsx`
- `app/plus.tsx`
- `app/project/[id].tsx`
- `app/project/_layout.tsx`
- `app/project/create.tsx`
- `app/receipt/[id].tsx`
- `app/scan.tsx`
- `app/split/[receiptId].tsx`
- `app/split/_layout.tsx`

## Abschliessender Prüfstand dieser Reparaturrunde

Finaler Code `92ad51b3`, Apple-Job `50bfd78d5be9fbff`: Coretests und Simulator-Build bestanden. Alle 18 bestehenden PostgreSQL-Duplikatszenarien in isolierter Engine bestanden. Ressourcenhashes/Projektgenerierung und Whitespaceprüfung bestanden. Interner TestFlight-Folgeauftrag `3595b72e0414fa9f` läuft; keine externe Gruppe oder Store-Einreichung. Diese Runde schliesst die oben aufgeführten Reparaturen ab, nicht den gesamten verbleibenden Paritäts- und Geräteprüfungsbacklog.

## Nächste Iterationen mit Abnahmekriterien

1. **Echte Datenabläufe abnehmen:** aktueller interner Build, normales Testkonto; Karten anlegen/ändern/entfernen, Betrag speichern, doppelt scannen, Bestandspaar zusammenführen/getrennt halten, Parkzeiten korrigieren. Keine Besitzerpasswörter oder destruktiven Tests am echten Hauptkonto. Offen bis tatsächlicher Nachweis.
2. **Weitere Social-Parität:** QR/Add-Link, Senden/Annehmen/Ablehnen echter Belege, Projektmitglieder und gemeinsame Belege, vorhandene Aufteilungen; getrennte Tests für Nutzerrechte, Bildzugriff und bestehende Zahlungen. Aufteilung nie still neu erzeugen.
3. **Bank-/Analyse-Parität:** kanonischen Serveradapter um die bestehende Bankübersicht ergänzen, Händler-/Finanzprofil, Zeitraumparität; robusten Datei-Lösch-/Aufräumablauf planen und prüfen. Schema-/Deploymentänderungen erst mit verfügbarer Backendberechtigung und reproduzierbarer Prüfung.
4. **Politur/weitere Optionen:** Unterkategorien, präzise Scanlimitmeldungen, Sprachlabels, lokale Modellverfügbarkeit und Hintergrundscan-Wiederaufnahme. Garantie/Fristen/PDF/Preisvergleich sind zusätzliche Produktentscheidungen.

Ledger `20261127-001850-6fb6` hält Inventur und erste Reparaturrunden fest; die weiteren Social-/Bank-/Geräteprüfschritte bleiben ausdrücklich offen. Keine automatische Behauptung „alles fertig“ aus einem grünen Build.

## Später ergänzter Nutzerauftrag: Social-Media-Demomodus

Eigene spätere Ausbaustufe nach den Kernreparaturen: konkrete lustige/fiktive Quittungen in der echten App hochladen und tatsächlich verarbeiten, mit K-Creative-Piggy (Lupe, Brille, Nachdenken, Schreibmaschine) als ereignisgesteuerte Begleitung. Demoausgaben bleiben getrennt von privaten Finanzen. Konzept und Abnahmekriterien: [SOCIAL-DEMO-MODE.md](SOCIAL-DEMO-MODE.md). Noch nicht implementiert; kein Bestandteil des aktuellen TestFlight-Auftrags.

## Auslieferung dieser Runde

**1.0 (32)** ist intern in TestFlight freigegeben: K-Dev `3595b72e0414fa9f`, Apple **VALID**, `released: true`, ausschliesslich **Internal Testers**, bestätigt 27.11.2026 00:39:46 CET. Quelle `92ad51b3`; alle fünf Build-/Apple-/Freigabegates bestanden. Der spätere Social-Media-Demomodus ist ausschliesslich geplant. Noch offene Punkte der Inventur werden durch diesen Release nicht als erledigt markiert.

## Fortsetzung: kompakter Beleg und erweiterte Details

Auftrag 27.11.: Zahlentastatur sicher abschliessen, klare einzeilige Artikel, Zusatzdaten aus der Hauptansicht auslagern und den gesamten erkannten Text erhalten. Umgesetzt in `d638e96f` und `aa012ac5`: native Tastaturleiste mit Fertig auch für Kartenendziffern; kompakte Artikel und Artikeldetails über «…»; eigene Ansicht «Weitere Details» mit Originalbild, Filiale/Adresse, Kasse/Kassierer, Zahlungsreferenzen und vollständigem OCR-Text. Bestehende/geleerte Angaben bleiben erhalten, numerische und unbekannte Metadaten werden toleriert. Die separaten Merge-Auswahlfelder bleiben mit der ausgerollten RPC-Whitelist kompatibel.

Coretests und Simulatorbuild des finalen Codes `aa012ac5`, K-Dev `17a552227da2e5ae`, erfolgreich. Interne Production-Auslieferung `0fb8021bb59a3ef3` läuft. Quellen zu Zahlenformaten und genaue Abnahmegrenzen: [Verifikation](verification/2026-11-27-receipt-presentation.md). Keine vollständige Geräte- oder OCR-Erkennungsgarantie aus einem grünen Compilerlauf ableiten; die übrigen offenen Inventurpunkte bleiben bestehen.

Auslieferung der kompakten Ansicht und nativen Zahlentastaturen: **1.0 (33)** intern bei Apple **VALID** und **Internal Testers** bestätigt, K-Dev `0fb8021bb59a3ef3`, Quelle `aa012ac5`, 27.11.2026 15:17:58 CET. Die bestehende Geräteprüfliste bleibt offen.


## Manuelle Zusammenführung wieder erreichbar (27.11.2026)

D04: Der bisherige SwiftUI-Vergleich war ausschliesslich über automatisch vorgeschlagene Paare erreichbar. Jetzt öffnet jeder Beleg über das Menü „Mit anderem Beleg zusammenführen …“ eine Suche nach einem zweiten eigenen Beleg. Unter „Duplikate prüfen“ gibt es zusätzlich „Belege manuell zusammenführen“ mit Auswahl von A und B. Abweichende OCR-Angaben und zuvor getrennt bestätigte Vorschläge verhindern die bewusste manuelle Auswahl nicht. Vergleich, Feld-/Bild-/Artikelauswahl, Vorschau und Bestätigung verwenden weiterhin den bestehenden geschützten Merge-RPC. A behält seine ID; B wird erst nach bestätigter erfolgreicher Transaktion entfernt. Backendkonflikte bleiben sichtbar.
