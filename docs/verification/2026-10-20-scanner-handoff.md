# Übergabe an K-Dev — Scanner zurück auf Apples Dokument-Scanner (20.10.2026)

## Auftrag von Raphael
Der eigene iOS-Scanner (`modules/piggy-receipt-scanner`, Build 23) sieht schlechter aus als Apples Scanner davor. Der Haken-Bug aus Build 18 (blauer Haken nach Auto-Aufnahme nicht bedienbar) wird als Integrationsfehler vermutet, nicht als Apple-Fehler. Daher: iOS wieder auf Apples `VNDocumentCameraViewController` über `react-native-document-scanner-plugin` 2.0.4, Integration korrigiert.

## Stand
- Geändert, **gestaged, nicht committet**: `src/hooks/useScanner.ts`, `src/hooks/useScanner.test.tsx`.
- iOS nutzt wieder `DocumentScanner.scanDocument`, `precropped=1`. Android unverändert.
- Neu: nach erfolgreichem Scan auf iOS 600 ms warten (`SCANNER_DISMISS_SETTLE_MS`), dann `router.push('/scan')`. Grund: Die Bibliothek löst ihr Promise aus, bevor UIKit das `dismiss` beendet hat (`goBackToPreviousView` läuft per `DispatchQueue.main.async`, danach sofort `successHandler`).
- Unverändert aus Build 23: Warten auf echtes `Modal.onDismiss`, Sperre gegen Doppelstart.
- `modules/piggy-receipt-scanner` bleibt im Repo (wird weiter autogelinkt, aber nicht mehr importiert). Rückweg: Import und iOS-Zweig in `useScanner.ts` wiederherstellen (siehe `git show b2cae928`).
- Geprüft: `tsc --noEmit` ohne Fehler, 6 Scanner-Tests grün (mocken die Kamera, beweisen nichts am Gerät).

## Offen / blockiert
1. **Commit**: scheitert, weil der Signing-Key `~/.ssh/id_github_lumina` auf dieser Maschine fehlt (`commit.gpgsign=true`). Signierung wurde nicht umgangen. Vorgesehene Nachricht (Deutsch): „fix: iOS-Scan wieder über Apples Dokument-Scanner, Navigation erst nach dem Schliessen“, mit Co-Authored-By- und Claude-Session-Zeile.
2. **Build 24**: nicht gestartet. Dashboard auf :4200 läuft nicht, Host `macbook` ist nicht auflösbar. `app.json` steht auf `buildNumber` 18, TestFlight auf 23: Bitte prüfen, ob der Worker die Nummer selbst setzt.
3. **Saubere Lösung statt 600 ms**: `patch-package` ist nicht installiert. Ein Patch an `DocScanner.swift` (Handler erst im Completion von `dismiss(animated:completion:)` aufrufen) wäre korrekt. Vorher klären, ob der Mac-Worker `postinstall` ausführt (Transport schliesst Verzeichnisse namens `ios` aus).
4. **Abnahme am iPhone** (Hypothese ungeprüft): Auto-Aufnahme → blauer Haken → Review öffnet genau einmal; Abbrechen; mehrfach nacheinander scannen. Falls der Haken weiter hängt, ist die Integrationsthese widerlegt → eigenen Scanner behalten und optisch an Apples Original angleichen.

## Hinweise
- `.kdev/partials/receipt-scan.md` beschreibt noch den eigenen Scanner als aktiven Ablauf und ist nach diesem Wechsel veraltet; erst nach Abnahme aktualisieren.
- Die TestFlight-Screenshots waren über den Worker nicht abrufbar (siehe `docs/feedback/2026-10-17-testflight.md`); die Kritik „sieht schlecht aus“ ist nicht visuell belegt.
- Die Vision-Session (VISION.md/TODO.md) ist davon getrennt und steht noch bei der Interviewrunde 1.
