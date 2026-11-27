# SwiftUI: manuelle Beleg-Zusammenführung

Der vorhandene Vergleich war nur über exakte automatische Duplikatvorschläge erreichbar. Jetzt gibt es einen direkten Einstieg im Belegmenü „Mit anderem Beleg zusammenführen …“ und unter „Duplikate prüfen“ die manuelle Auswahl von A/B. Die Suche zeigt eigene andere Belege auch bei abweichendem Datum/Betrag/Ladenname oder fehlenden Angaben. Der bestehende Vergleich mit Vorschau, Feld-/Bild-/Artikelauswahl, Bestätigung und transaktionaler Konfliktprüfung bleibt erhalten. Aus dem Beleg gestartete Abläufe schliessen nach erfolgreichem Merge das Auswahlblatt.

Quellstand: `c77b4caa6bf07c4fe4d404b48ff15fb3a5d91da9`, signiert/gepusht über K-Dev.

Prüfung:

- Neue Swift-Core-Regression: manuelle Auswahl trotz OCR-Abweichungen; Ausschluss des Zielbelegs und fremder Belege; Suche.
- `node scripts/tests/receipt-duplicates-db.mjs`: alle 18 isolierten PostgreSQL-Szenarien bestanden, unter anderem Eigentum, Beziehungen, Konflikte und Rollback.
- Ressourcenvalidierung: 261 Hashes, 17 Varianten, 222 Retina-Frames; deterministisches Xcode-Projekt.
- K-Dev Apple-Prüfjob `71e60f4ae8b2d859`: `succeeded`, `exitCode:0`, Corechecks und Simulator-Kompilierung `passed`; Abschluss 27.11.2026 20:25:51 UTC.
- Simulator-Artefakt SHA256 `1c7a418f2390b9c07fe7339a16948cc33030d4ba0dab47451571db64aa6caab9`.

Keine Backendänderung oder neue Migration. Keine interaktive Geräteprüfung und kein TestFlight-Upload dieses Quellstands in diesem Auftrag.
