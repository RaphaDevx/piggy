# Native Belegbearbeitung und bekannte Karten

## Umfang

Direktes Antippen von Gesamt-/Artikelbeträgen öffnet den Belegeditor. Persistente Feldbeschriftungen, frei bearbeitbarer Dezimaltext mit Komma/Punkt, Löschen und Tastaturabschluss. Ungültige/geleerte Eingaben werden als ungültig geführt, nicht als null gespeichert. Gesamtbetrag, Menge, Einzelpreis und Artikelbetrag werden serverseitig bestätigt; fehlende durch RLS nicht aktualisierte Zeilen führen zu sichtbaren Fehlern. Der bestehende mehrteilige Schreibablauf bleibt mehrteilig; nach Fehler wird neu geladen.

Eigene Karten über vorhandene profile_cards-Tabelle, keine Migration/Policy-Änderung. Name/Typ in label, vier Ziffern in last_four. Kartenverwaltung, Scanreview, Offline-OCR und Belegansicht verwenden PaymentCard. Keine Kartenbesitzererkennung allein aus vier Ziffern, keine automatische Profilanlage und keine Massendatenkorrektur. Mehrdeutige Treffer bleiben unzugeordnet. Keine volle Kartennummer im Kartenformular.

## Lokale Nachweise

- Deterministisches Xcode-Projekt aktualisiert; Ressourcenprüfung bestätigt 261 Hashes/222 Retinaframes.
- Anonyme REST-Abfrage auf profile_cards mit expliziten Spalten und limit=0: HTTP200. Keine Nutzerdaten angefordert/geändert. Dies beweist keine authentifizierte CRUD-/RLS-Prüfung.
- Neue PaymentEditingTests für Eingabe und Kartenauflösung. Apple-Ergebnis noch ausstehend.

## Grenzen

Interaktiver Gerätedurchlauf, echte CRUD-Prüfung mit normalem Nutzerkonto, unterschiedliche Händlerlayouts und Apple-Pay-Endziffern separat prüfen. Die letzten vier Ziffern sind keine eindeutige globale Karten-ID. Die vorhandene Freundesichtbarkeit bleibt bestehen und ist in der UI erklärt. Keine zusätzliche Datenübermittlung oder SDK-Änderung.

## Verifizierte Iterationen

- Quellcommit `cc2f945819ffe3796076f08f06dbe88bd1739c5e`, K-Dev `ec405a3719a81b0a`: Swift-Core-Tests und Apple-Simulator-Kompilierung erfolgreich.
- Quellcommit `27af6789a12fb29743d47faf0f37edd45cc66f2c`, K-Dev `1327c4b062f1c590`: Duplikatliste, Metadaten, Parkdetails, lokale OCR-Rettung und Artikelbearbeitung; Core-Tests und Apple-Simulator erfolgreich.
- Finaler Code `92ad51b3db2b2bb857e046c5d3456605c78b04d1`, K-Dev `50bfd78d5be9fbff`: zusätzliche Merge-Zusatzfeldauswahl, sichere Legacy-Bildpfade, Filter/eigene Kategorien/Projekteinladungen/Bankrückverweis, Bearbeitung undatierter Altbelege. Status succeeded, checks und simulatorBuild passed. Simulatorartefakt SHA256 `6390bf519b26dcd0422742de1714170f00f779dd00380473355310fa3d7cb51f`.
- `node scripts/tests/receipt-duplicates-db.mjs`: alle 18 Szenarien in einer isolierten PGlite-PostgreSQL-Engine bestanden, darunter Eigentumsgrenzen, Rollback, Bank-/Split-/Sharingbeziehungen, erhaltene Ausgleichsposten und explizite Bild-/Artikelauswahl. Keine Produktionsdaten verändert. Toolpaket 0.5.10 ausschliesslich unter /tmp installiert; Projektabhängigkeiten unverändert.
- Ressourcenprüfung und git diff --check erfolgreich.
- Production-Job `3595b72e0414fa9f` für denselben Code und ausschliesslich `Internal Testers` gestartet. Apple-Verarbeitung/Gruppenfreigabe noch separat zu bestätigen.

Vollständiger Quellcodevergleich mit 69 Funktionspunkten und allen 23 alten App-Dateien inklusive Layouts: [Funktionsinventur](../SWIFTUI-FEATURE-AUDIT.md). Offene Funktionsübernahmen und echte Geräte-/Backendabnahmen sind dort ausdrücklich keine erledigten Punkte.

## Interne Auslieferung bestätigt

K-Dev `3595b72e0414fa9f` erfolgreich, finaler Code `92ad51b3`, Version **1.0 (32)**. Apple-Build-ID `0b118d1f-b5ac-4fbd-a264-4a079471d15f`, Verarbeitung **VALID**, `released: true`, Gruppenbeziehung **Internal Testers** (`a64f98a8-c50e-44fd-9dbe-7b4d150fb9a3`, intern) bestätigt am 27.11.2026 um 00:39:46 CET. Alle Gates checks, simulatorBuild, appleUpload, appleProcessing und testflight passed. Keine externe Gruppe und keine App-Store-Einreichung. Offene Geräte-/CRUDprüfungen und weitere Funktionsübernahmen bleiben offen.
