# Piggy — vollständiger Funktionsvergleich Expo → SwiftUI

Stand 27.11.2026. Grundlage: alle 27 alten App-Routen, src/hooks, aktive Feature-Schalter und alle nativen Screens/Services. Dies ist eine Quellcode-Inventur mit gezielten automatisierten Prüfungen, kein behaupteter vollständiger iPhone-End-to-End-Test. „Vorhanden“ bedeutet implementiert, nicht am Gerät vollständig verifiziert. Änderungen nach TestFlight 1.0(31) sind separat ausgewiesen.

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
| F02 | Woche/Monat/Quartal/Jahr/Kategorien | Vorhanden | Core/Apple geprüft, Gerät offen |
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
