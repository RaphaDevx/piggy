# Kompakte Belegansicht, Zahleneditor und vollständige Detailansicht

Auftrag 27.11.2026: Tastatur mit Bestätigung, konsistente Zahlendarstellung, einzeilige Artikel mit Menge/Preis, Belegzusatzdaten unter erweiterten Details, alle erkannten Texte behalten.

## Umsetzung

- Alle Dezimalfelder besitzen ein natives UITextField mit eigener inputAccessoryView/UIToolbar und dauerhaftem «Fertig»-Button. Dieser beendet den tatsächlichen First Responder; keine fokussierte, bedingt eingeblendete SwiftUI-Tastaturtoolbar. Auch Kartenendziffern behalten als String ihre führenden Nullen und erhalten den Abschlussknopf. Zahlenfelder berücksichtigen disabled und Dynamic Type; Scrollen kann die Tastatur zusätzlich schliessen.
- Anzeige im Zahleneditor einheitlich mit Punkt, Geldfelder mit mindestens zwei Dezimalstellen. Eingabe akzeptiert weiterhin Komma/Punkt und Zwischenstände wie 12,. Interne Werte bleiben Decimal. Das reine Öffnen/Schliessen eines bestehenden hochpräzisen Werts rundet diesen nicht. Die Tastaturbestätigung ersetzt nicht «Beleg speichern».
- Gemeinsame kompakte Artikelansicht für Beleg und Kategorie: Name einzeilig mit Ellipse, tatsächliche Menge/Einheit, Betrag. Vollständiger Name bleibt für VoiceOver und die Bearbeitung erreichbar. Scanreview und Editor verwenden eine Zeile mit Name/Menge/Betrag, weiterführende Artikelwerte über «…».
- Hauptansicht: Geschäft, Datum, Gesamtbetrag, zusammengefasste Zahlungsangabe, Artikel, ggf. Parkdaten/Notiz. Originalbild, Filiale/Adresse, MwSt-/Bon-/Kassen-/Kassiererangaben, Zahlungsreferenzen, Bank-/Projektaktionen und vollständiger erkannter Text sind unter «Weitere Details». Teilen/Löschen bleiben im Aktionsmenü. Im Editor sind Zusatzfelder standardmässig eingeklappt.
- Apple-Vision-Ergebnis wird weiterhin als gesamter raw_ocr_text gespeichert; keine Textkürzung für die kompakte Anzeige. Zusätzliche explizit beschriftete Angaben werden lokal gelesen, auch bei alten Belegen. Kassierername und Nummer werden nur aus vorhandenen Angaben ergänzt; Kasse und Zahlungsterminal getrennt. Unbekannte/repetierte Schlüssel-Wert-Angaben bleiben sichtbar. Nicht zuordenbare Zeilen bleiben im vollständigen Originaltext. Vorhandene oder ausdrücklich geleerte Werte werden nicht überschrieben.
- Numerische/zusätzliche JSON-Metadaten aus älteren Belegen lassen die Belegdekodierung nicht mehr vollständig scheitern. Verschachtelte Zusatzwerte bleiben lesbar als JSON-Text erhalten. Keine neue Cloudverarbeitung, keine neue Datenbankmigration und keine zusätzliche Weitergabe an andere Anbieter.
- Die erweiterten Zusatzfelder werden nicht als neue Auswahlparameter an den bestehenden Merge-RPC geschickt; dessen ursprüngliche sieben Auswahlfelder bleiben unverändert kompatibel.

## Zahlenstandard und Quellen

Mathematisch ändert ein korrekt interpretiertes Dezimalzeichen den Wert nicht. Für die Schweizer Geldanzeige verwenden wir CHF 12.50. Die Bundeskanzlei unterscheidet im Fliesstext das gewöhnliche Dezimalkomma und den Dezimalpunkt zwischen Währungseinheit/Untereinheit (§514); dies ist keine universelle Vorschrift für jede internationale App-Zahl.

- Bundeskanzlei: [Schreibweisungen, §514](https://www.bk.admin.ch/dam/de/sd-web/YVHazXZRkqKn/schreibweisungen.pdf).
- Abacus: [AbaConnect DEBI Belege](https://downloads.abacus.ch/fileadmin/ablage/abaconnect/htmlfiles/debi/DEBI__Belege_2024.00_AbaDefault_DE.html), Feld Amount: Betrag mit zwei Dezimalstellen; XML-Dezimaltrennzeichen Punkt. Das belegt das Schnittstellenformat, nicht jede mögliche Abacus-Anzeigeeinstellung.

## Verifikation

Erster Quellstand d638e96f, lokaler K-Dev-Job 0cebc6df26d10101 erfolgreich (checks und simulatorBuild). Enthält native Dezimaltastatur, Artikelkomponenten, Metadatendekoder und neue Core-Tests.

Finaler ergänzter Quellstand aa012ac5: reduzierte Hauptansicht und Abschluss der Karten-Zifferntastatur. Job 17a552227da2e5ae läuft. Neue ReceiptPresentationTests prüfen exakte Dezimalwerte, Einheiten, Kassierer/Kasse/Terminal, keine erfundenen Namen, keine Verwechslung von Steuersatz und UID, wiederholte Zusatzfelder, bewusst geleerte Werte und Erhalt des gesamten Texts bei numerischen/verschachtelten JSON-Metadaten. Ressourcenprüfung mit261Hashes/222Frames und Whitespaceprüfung bestanden.

Noch kein interaktiver Gerätenachweis für Tastatur, Ellipse/Spaltenbreiten bei sehr grosser Schrift oder echte OCR verschiedener Händlerbelege. Eine erfolgreiche Apple-Kompilierung beweist diese UI-/Geräteabläufe nicht.

## Finaler Prüfbuild

`aa012ac5602ea6615733c172bebc9339f20ebb81`, Job `17a552227da2e5ae`: succeeded, checks und simulatorBuild passed. Damit sind auch die letzte Reduktion der Hauptansicht, Karten-Zifferntastatur und unveränderte hochpräzise Altwerte auf dem Apple-SDK kompiliert. Der interne Production-Auftrag `0fb8021bb59a3ef3` verwendet denselben Code und ausschliesslich Internal Testers. Kein externer TestFlight- oder App-Store-Auftrag.

## TestFlight bestätigt

K-Dev `0fb8021bb59a3ef3`: succeeded, 27.11.2026 15:17:58 CET. **1.0 (33)**, Apple-Build `d75fae57-95c8-468e-ab83-86f22250dd27`, Verarbeitung **VALID**, released true, Gruppe **Internal Testers** intern bestätigt. Checks, Simulator-Kompilierung, Apple-Upload, Apple-Verarbeitung und TestFlight-Freigabe jeweils passed. Quellstand `aa012ac5602ea6615733c172bebc9339f20ebb81`. Keine externe Freigabe. Direkte iPhone-Bedienprüfung steht weiterhin aus.
