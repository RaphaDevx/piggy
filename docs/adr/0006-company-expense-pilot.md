# ADR 0006: Firmen-Spesen als vorbereiteter, abgeschalteter Bereich

Datum: 2026-11-27. Status: implementierte Vorbereitung, kein produktiver Rollout.

## Anlass

Noch keine Pilotfirma und keine Abacus-Testinstallation. Firmen sollen Mitarbeitende einladen, eingescannte Geschäftsbelege entgegennehmen und unabhängig prüfen können. Ein freigegebener Beleg ist kein Nachweis einer Auszahlung.

## Entscheidung

Bestehende Firmen-/Mitgliedschaftstabellen werden durch atomare authentifizierte RPCs ergänzt. Direkte Clientschreibrechte auf Firmen, Mitgliedschaften und Speseneinreichungen entfallen. Neue Firmen bekommen Einstellungen und einen ersten Admin in derselben Transaktion. Einladungen enthalten einen zufälligen 128-Bit-Code, dessen SHA256 serverseitig gespeichert wird; Gültigkeit sieben Tage, Rotation ersetzt ihn. Ein Beitritt erzeugt ausschliesslich die Mitgliedsrolle.

Mitarbeitende sehen ihre eigenen Einreichungen; Admin und Buchhaltung sehen die Einreichungen ihrer Firma. Eigenfreigabe ist auch für Admins verboten. Eine optionale Verpflegungsgrenze verhindert die Freigabe eines überhöhten Gesamtbetrags. Teilzahlungen und Fremdwährungsumrechnung sind nicht implementiert.

Bei Einreichung entsteht ein unveränderlicher JSONB-Belegstand mit Betrag, Artikeln, Zahlungsangaben, Metadaten und OCR-Volltext. Persönliche Notizen werden ausgeschlossen. Explizite Freigabe in der App weist auf die Kopie in den Firmenbereich hin. Das Originalbild wird von einer authentifizierten Edge Function ausschliesslich aus dem eingefrorenen eigenen Objektpfad gelesen, grössenbegrenzt heruntergeladen und unter einem SHA256-Schlüssel separat in R2 archiviert. Firmenbildschlüssel sind über die normale Speicher-URL-Funktion weder beschreibbar noch löschbar. Ein kurzer GET-Link verlangt aktuelle Mitgliedschaft plus eigene Einreichung oder Prüfrolle. Die App kann keine Archivbestätigung selbst schreiben.

Erst nach erfolgreicher Archivbestätigung ist eine unabhängige Freigabe möglich. Historie und Exportdatensatz sind gegen normale App-Schreibzugriffe unveränderlich. Ein identisches Bild kann über den globalen SHA256-Index nicht zwei Mal archiviert werden. Abweichende Fotos derselben Quittung erfordern weitere Erkennung/manuelle Prüfung; dies ist kein vollständiger Betrugsschutz.

Ein wiederholter Export liefert denselben Datensatz. Das Format `piggy-expense-v1` enthält den exakten kanonischen Snapshot-String, dessen SHA256, Archivschlüssel, Bildhash und Prüfperson. Es ist ein Piggy-JSON-Datenauszug, kein Abacus-Importformat. Es enthält ausdrücklich `abacus_transfer_confirmed:false` und `payment_confirmed:false`. Weder Bankzahlung noch Abacus-Endpunkt werden angesprochen. Es gibt keine Clientaktion „bezahlt setzen“.

SwiftUI verwendet echte RPCs und die Archivfunktion. `PIGGY_COMPANIES = NO` bleibt Standard; ohne Backend-Abnahme wird der Einstieg nicht angezeigt und der Service lehnt Aufrufe ab.

## Grenzen und Voraussetzungen für Aktivierung

Die unveränderlichen Fremdschlüssel blockieren aktuell die Löschung eingereichter privater Belege und beteiligter Konten. Das verhindert stillen Verlust des Firmenprüfpfads, ist aber noch kein fertiges Lösch-/Aufbewahrungskonzept. Vor Live-Migration müssen Aufbewahrungsfristen, Arbeitgeberverantwortung, Auskunft, Trennung persönlicher Identität und die bestehende Konto-Löschung technisch und rechtlich abgestimmt und getestet sein. Kein Rollout mit blockierter Konto-Löschung.

R2-Zugangsberechtigte Infrastrukturadministratoren können weiterhin Objekte ändern/löschen. Der Hash erlaubt Integritätsprüfung, ersetzt jedoch weder WORM-/Object-Lock-Konfiguration noch eine rechtliche Archivzertifizierung. Zugriffsentzug/Austritt, Rate Limits gegen Firmengründung/Einladungsversuche, Exportbereinigung bei Backups und grosse Listen mit Pagination müssen vor einem Firmenpilot ergänzt bzw. abgenommen werden. Aktuell enthält der Entwurf Rollenänderung, aber noch keine Mitgliederentfernung.

Eine spätere Abacus-Verbindung braucht dokumentierte Spesen-/Beleg-APIs und bestätigte Module, Mandanten, OAuth-Berechtigungen, Mitarbeiter-/Kontierungszuordnung, wiederholbare Übertragung mit eindeutiger Fremdreferenz sowie verifizierte Rückmeldungen zu Belegannahme und Auszahlung. Keine erfundenen Endpunkte, Scopes oder Webhooks. Eine Marketplace-Aufnahme wird separat mit Abacus abgestimmt.

## Nachweis

Isolierte PostgreSQL-Szenarien: `node scripts/tests/company-pilot-db.mjs`. Edge-Vertragstests: `npx jest supabase/functions/_shared/company/archive.test.ts --runInBand`. Deno-Prüfung: `npx --yes deno@2.9.6 check supabase/functions/company-expenses/index.ts`. Swift-Coretests und Simulator-Kompilierung laufen ausschliesslich über K-Dev. Keine dieser Prüfungen belegt ein produktives Abacus-System oder eine tatsächlich erfolgte Zahlung.
