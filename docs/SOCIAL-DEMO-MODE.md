# Piggy Social-Media-Demomodus — geplanter Ausbau

Auftrag vom 27.11.2026, ausdrücklich **später**, nach Reparatur der Kernfunktionen. Status: konkretisiert und im Backlog, noch nicht implementiert und nicht im aktuellen TestFlight-Build. Keine Veröffentlichung von Inhalten beauftragt.

## Gewünschter Ablauf

1. Einen klar gekennzeichneten Demomodus in der tatsächlichen SwiftUI-App öffnen.
2. Eine gezielt ausgewählte lustige/fiktive Quittung als Bild hochladen oder eine gespeicherte Demoquittung auswählen. Dokumente kommen aus einem unterstützten App-Eingabeweg.
3. Die echte OCR-/gegebenenfalls freigegebene KI-Verarbeitung ausführen, mit normaler Prüfung/Korrektur. Die Ergebnisangaben stammen vom konkret verwendeten Dokument.
4. Piggy begleitet die Verarbeitung mit K-Creative-Animationen: mit Lupe untersuchen, mit Brille nachdenken, an einer Schreibmaschine gefundene Angaben ausgeben, danach entdecken/freuen. Zustände an tatsächliche Erkennungs-/Prüf-/Erfolgs-/Fehlerereignisse binden. Kein Erfolgssignal bei fehlgeschlagenem Speichern oder unleserlichem Beleg.
5. Das reale Ergebnis in der App für Demo-/Mockupaufnahmen zeigen. Wiederholbarer Reset und dieselbe Dokumentauswahl erleichtern mehrere Takes. Synthetische Quittungen und Demoansicht klar kennzeichnen.

## Daten und Verhalten

- Eigener abgegrenzter Demokontext/Testprofil: Demobelege erscheinen nie in privaten Ausgaben, Bankabgleich, Splits oder Freundesfeeds. Die Trennung gilt auch für Hintergrund-Schreibvorgänge.
- Derselbe dokumentierte Parser und dieselben Belegansichten wie im echten Produkt. Ein abweichender Demo-/Test-Build wird in der Aufnahmeherkunft entsprechend ausgewiesen.
- Für Cloud-KI gilt die normale explizite Einwilligung, Tageslimits und derselbe Datenfluss. Verarbeitungskosten und Fehler werden nicht durch vorgetäuschte Ergebnisse versteckt.
- Demoquittungen vorzugsweise fiktiv, ohne echte Zahlungs-/Kontakt-/Kennzeichendaten. Vom Nutzer ausgewählte Dokumente werden über den echten Eingabeweg verarbeitet.
- Keine garantierte Dauer der Erkennung durch Animation erfinden. Eine zusätzliche Showanimation ist als Inszenierung erkennbar und überspringbar. Reduce Motion erhält statische Markenbilder.

## K-Creative-Produktionsbrief

Bestehende Piggy-Figur, Geometrie, Markenfarben und K-Creative-Quelldatei weiterverwenden. Neue Requisiten/Posen als eigene Varianten: Lupe, Brille, Überlegen, Schreibmaschine. Gemeinsame Perspektive, Retinaauflösung, transparente Ränder/Schatten, begrenzter Decoderpuffer wie bei app-motion-v3.

Animationen nach Festlegung des konkreten Ablaufs rendern, Ressourcen mit Manifest/Hashes synchronisieren und in der echten App prüfen. Originalassets bleiben in K-Creative; keine Blenderquellen im Appbundle. Zusätzliche externe Overlayanimationen ersetzen keine tatsächliche Appbedienung.

## Abnahme

- Dokument A hochladen → tatsächlich aus A erkannte Daten; Dokument B → tatsächlich aus B erkannte Daten. Kein fest verdrahtetes Demoergebnis.
- Demobelege und Reset verändern nachweislich keine privaten Finanz-/Bank-/Freundesdaten.
- Erfolgs-, Fehler-, Abbruch- und Reduce-Motion-Zustände besitzen passende Animationen/Standbilder; kein endloses Maskottchen bei abgebrochener Verarbeitung.
- Echter unterstützter Geräte-/Simulatorablauf zuerst erfolgreich, dann Aufnahme. Raw-Aufnahme, Build-/Bundle-ID, OS/Locale, SHA256, Ereignis-/Gestenliste und Ergebnis zusammen archivieren.
- Schnitt/Zoom/Telefonrahmen sind möglich; Appinhalte werden nicht nachgebaut oder in der Postproduktion erfunden. Veröffentlichung ist ein gesonderter Auftrag.

## Erste Demoideen

- Fiktiver „Piggy-Kiosk“ mit klar lesbaren Artikeln wie „Notfall-Kaffee“, „Lernsnack“ und „Motivationsgebühr“.
- Parkbeleg mit expliziter Ankunft/Abfahrt und nachprüfbarem Durchschnittspreis.
- Derselbe Beleg zweimal: echte Duplikatprüfung und ausdrückliche Entscheidung.
- Beleg mit Rabatt: Artikel und Gesamtbetrag im echten Editor korrigieren.

Zuerst einen kurzen stabilen Ablauf umsetzen; zusätzliche Requisiten und Takes daran anschliessen. Die offenen Kernfunktionen sind in SWIFTUI-FEATURE-AUDIT.md priorisiert.
