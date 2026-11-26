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
