# TestFlight-Feedback — Sichtung 17.10.2026

Quelle: App Store Connect, App 6802367826, Worker-Aktion `feedback`, Importjob `d58a23fdb133854f`. Fünf Screenshot-Kommentare, keine Crash-Kommentare im gelieferten Ergebnis. Kommentartexte und Metadaten gesichtet; die angehängten Bilder wurden über diese Schnittstelle nicht geliefert und deshalb nicht visuell geprüft. Keine E-Mail-Adressen oder Originalbelege in diesem Dokument.

| Datum | Build | Inhalt | Befund / Behandlung |
|---|---|---|---|
| 17.10.2026 | 18 | Blauer Haken reagiert nach Auto-Aufnahme nicht; standardmässig eine Quittung, optional Stapel und schnelle Hintergrundprüfung | Priorität 1: gemeinsame Auto-/Manuell-Bestätigung und Modal-Übergabe korrigieren. Stapel als separates Feature aufnehmen; nicht weiterhin zusätzliche iOS-Seiten still ignorieren. |
| 08.10.2026 | 18 | Verdacht auf fehlenden API-Key / schlechtere Scanqualität | Offen, nicht als Schlüsselverlust bestätigt. Aktiver Parserpfad: lokale OCR → optional lokaler Parser → Gemini mit Einwilligung → Regex-Fallback. Separat anhand fehlgeschlagener konkreter Belege und Serverantworten untersuchen, ohne Keys zu protokollieren. |
| 07.10.2026 | 18 | Notizen an Quittungen | Im aktuellen Code bereits vorhanden: Quittung öffnen → Bearbeiten → Notiz. Sichtbarkeit als mögliches UX-Thema; Speicherung nicht erneut implementieren. |
| 07.10.2026 | 18 | Zahlungsmittel fehlen; bekannte Karten beim manuellen Eingeben vorschlagen | Offen. Fehlende Angabe nicht erraten; Vorschläge aus eigenen bekannten Zahlungsmitteln, ausdrücklich bestätigen. |
| 30.09.2026 | 17 | „Wieso geht das noch nicht?“ | Ohne Screenshot nicht eindeutig zuordenbar; keine Ursache erfinden. Bildansicht in App Store Connect für spätere Einordnung nötig. |

## Abgeleitetes Stapel-Konzept (noch nicht umgesetzt)
Einzelaufnahme bleibt Standard. Optional mehrere Quittungen erfassen, jede Vorschau ausdrücklich übernehmen, in einer sichtbaren Warteschlange verarbeiten und anschliessend Beleg für Beleg bestätigen. Fehlgeschlagene Einträge erneut versuchen; Duplikatprüfungen und manuelle Entscheidungen erhalten. Erfordert eigene persistente Queue und ist nicht durch das Weglassen eines iOS-Hakens gelöst.

## Technische Verfolgung
Aufnahme/Bestätigung: `.kdev/partials/receipt-scan.md`; Navigator-Stufen: `.kdev/flow.json`. Gerätestatus und Buildbelege im Verifikationsbericht ergänzen, nicht mit TestFlight-Kommentar vom älteren Build gleichsetzen.
