# Firmenlösung: Nachweis der Vorbereitung

Stand: 27.11.2026. Keine Pilotfirma; keine produktive Backend-Aktivierung.

## Quellstand und Apple-Prüfung

K-Dev-Handover: signierter Commit `56ef14449e03ea704984dc59a6a1025227937db7`, Branch `codex/piggy-swiftui`, Push nach `kdev`. Lokaler Apple-Prüfjob `1810063402a1aaf5` erfolgreich beendet am 27.11.2026 um 19:52:15 UTC.

Worker-Ergebnis: `status:succeeded`, `exitCode:0`, `gates.checks:passed`, `gates.simulatorBuild:passed`. Der Prüfpfad führte Swift-Coretests und die Simulator-Kompilierung aus. Simulator-Artefakt `piggy-native-simulator-1810063402a1aaf5.zip`, SHA256 `b9f324e1eb74c94a0a62e681c59c52a18a091ae6e8c235bcd16f62420553a94f`. Die 3 neuen Coretests prüfen DTOs/Systemereignisse, Eigenfreigabe und die Trennung von vorbereitetem Export/Freigabestatus. Keine interaktive Geräte-/Staging-Abnahme und kein TestFlight-Upload dieses Firmenpakets.

Die anschliessende Dokumentationsänderung verlinkt ADR/Handbuch in ARCHITECTURE und präzisiert den zufälligen UUIDv4-Einladungscode. Eine zusätzliche DB-Testassertion berechnet den Export-Snapshot-Hash mit Node unabhängig nach. Kein nachträglicher App-Codewechsel.

## Ausgeführte Prüfungen

- `node scripts/tests/company-pilot-db.mjs`: 22 Szenarien bestanden, tatsächliche Foundation-/Hardening-/Pilot-SQL auf isolierter PGlite-Datenbank. Einschliesslich atomarer Anlage, Rollen, abgelaufener/rotierter Einladung, RLS-Fremdzugriff, direkter Schreibsperren, privater Notizen, fixiertem Snapshot, Archivpflicht, Eigenfreigabe durch Admin, Überschreiten der Verpflegungsgrenze, erneutem Bild, anderer Firma und wiederholtem Export. Export-Hash zusätzlich unabhängig mit Node geprüft.
- `npx jest supabase/functions/_shared/company/archive.test.ts supabase/functions/_shared/storage/storage.test.ts --runInBand`: 42 Tests bestanden (9 neue Archivtests, 33 bestehende Speicherzugriffstests). Handler testet serverseitigen Hash der echten Testbytes, Pfad-/Rollenprüfung, Wiederholung, Grössenlimit und Fehler bei Archivbestätigung. Infrastrukturabhängigkeiten sind injiziert; keine Live-R2-Zusage.
- `npx --yes deno@2.9.6 check supabase/functions/company-expenses/index.ts`: erfolgreich. Ein anfänglicher BufferSource-Typfehler wurde vor dem Handover behoben.
- `python3 native/Piggy/scripts/validate_resources.py`: 261 Asset-Hashes, 17 Varianten, 222 Retina-Frames und Projekt-/Plist-Eingaben geprüft.
- `git diff --check`: erfolgreich für den abschliessenden Arbeitsstand.

## Nicht behauptet

Keine Supabase-Migration live angewandt, keine Function deployt, keine Firma angelegt, kein Beleg eines echten Mitarbeitenden geteilt. Kein Abacus-Testzugang, keine Partneranmeldung versandt, kein Abacus-Import und keine Zahlung.

`PIGGY_COMPANIES = NO` bleibt gesetzt. Aktivierung erst nach dem in ADR 0006 beschriebenen Lösch-/Aufbewahrungskonzept sowie weiteren Staging-Abnahmeschritten. Insbesondere darf eine Live-Migration nicht den bestehenden Konto-Löschablauf blockieren. Offene Abacus-, Rollenentzug-/Austritts- und Betriebsarbeiten sind im [Pilot-Handbuch](../ABACUS-COMPANY-PILOT.md) aufgeführt.
