---
name: receipt-scan
updated: 2026-10-17
---
# Beleg-Scan: Aufnahme, Bestätigung und Auswertung

## Ebene 1 — Idee
Eine Quittung aufnehmen, sichtbar prüfen und ausdrücklich übernehmen. Automatische und manuelle Aufnahme haben denselben Abschluss. Kein Hängenbleiben am blauen Haken; kein unbeabsichtigtes Speichern oder zweites Öffnen der Kamera. Standard ist eine Quittung pro Durchgang. Optionale Stapelverarbeitung ist ein separates geplantes Feature.

## Ebene 2 — Aktueller Ablauf
1. `CustomTabBar` öffnet die Auswahl „Quittung hinzufügen“.
2. `useScanner.launch` sperrt weitere Starts und schliesst das Auswahlmenü. Auf iOS wartet es auf das tatsächliche `Modal.onDismiss`, dann auf laufende Interaktionen.
3. iOS öffnet `PiggyReceiptScanner` bildschirmfüllend. Android verwendet weiterhin den Dokument-Scanner; Bildimport verwendet die Mediathek.
4. iOS: AVFoundation zeigt die Kamera. Vision sucht höchstens ein Dokument-Rechteck (höchstens ca. 3 Auswertungen/s). Nach einer Sekunde stabiler Erkennung wird einmal aufgenommen. Automatik lässt sich ausschalten; „Foto aufnehmen“ bleibt als Alternative.
5. Beide Aufnahmearten wechseln identisch von `scanning` zu `capturing` zu `review`. Die Kamera pausiert. Vorschau und die beschrifteten Buttons „Scan übernehmen“ und „Neu aufnehmen“ erscheinen. Die Bestätigung hängt ausschliesslich von einem erfolgreich aufgenommenen Bild ab, nicht von einem internen VisionKit-Button.
6. „Neu aufnehmen“ verwirft nur die aktuelle Vorschau und startet die Kamera erneut. „Abbrechen“ beendet ohne OCR, Upload oder Quittung. „Scan übernehmen“ schreibt genau eine lokale JPEG-Datei und beendet die Kamera; die native Promise wird erst nach vollständigem Schliessen aufgelöst.
7. Perspektivisch korrigierte Bilder gehen mit `precropped=1` nach `/scan`; sonst bleibt der manuelle Zuschnitt mit `precropped=0` erhalten.
8. `app/scan.tsx` ruft `processReceiptImage` auf: lokale Texterkennung, verfügbarer lokaler Parser, sonst Gemini nach Einwilligung (eigener Key oder Server), ansonsten Regex-Fallback. Dieser aktive Pfad verarbeitet derzeit synchron bis zur Review-Ansicht. Die separat vorhandene `enqueueForEdge`-Funktion beweist keinen aktiven Hintergrundpfad.
9. Erkannte Daten prüfen, Duplikate gegebenenfalls ausdrücklich entscheiden, speichern und Bankabgleich anstossen. Aufnahmebestätigung und dauerhaftes Speichern der Quittung sind unterschiedliche Schritte.

## Fehlerbild und Beleggrenzen
TestFlight-Kommentar vom 17.10.2026 zu Build 18, iPhone14_2, iOS 26.6.2: blauer Abschlussbutton nach automatischer Aufnahme nicht bedienbar. Ergänzung im Chat: manuelle Aufnahme funktioniert. Die alte Bibliothek verwendet Apples `VNDocumentCameraViewController`; Piggy bekommt erst dessen Abschluss-Callback. Der genaue interne iOS-Fehler ist aus JavaScript nicht beobachtbar und auf Linux nicht reproduziert. Zusätzlich war nur `InteractionManager` statt echter Modal-Dismissal abgewartet worden. `maxNumDocuments: 1` wird von der iOS-Bibliothek nicht ausgewertet; zusätzliche Seiten wurden bisher durch `[0]` ignoriert.

Korrektur: eigener öffentlicher AVFoundation/Vision-Aufnahmeablauf mit gemeinsamer Bestätigung; echte Modal-Lebenszyklen und Sperren gegen doppelte Starts. Keine privaten UIKit-Abfragen, kein simuliertes Tippen auf Apples Button. Neue native App-Version nötig.

## Abnahme am physischen iPhone
- Automatik: Quittung erkennen lassen → ohne manuellen Auslöser Vorschau → „Scan übernehmen“ genau einmal → Auswertung/Review.
- Manuell: Automatik aus → Foto → dieselbe Vorschau/Bestätigung.
- Nach automatischer Aufnahme neu aufnehmen und danach bestätigen; mehrfach nacheinander scannen.
- Abbrechen vor/nach Aufnahme erzeugt keinen Beleg; Doppeltippen startet keine zweite Kamera.
- Lange, kurze und schräg gehaltene Quittungen, schlechter Kontrast, Berechtigung verweigert, App-Wechsel, grosse Schrift und VoiceOver prüfen.
- Zuschnitt/Mengen/Lesbarkeit prüfen; kein Verlust durch falsche Dokumentgrenzen.

## Idee-Historie
- 2026-10-17: Aus TestFlight-Feedback und explizitem Nutzerauftrag als eigener Teilprozess erfasst; gemeinsamer Abschluss für Auto/Manuell. Stapel, API-/Qualitätsdiagnose und Zahlungsmittelvorschläge bleiben separat erfasst.
