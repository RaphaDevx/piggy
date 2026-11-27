# Piggy Firmenlösung und Abacus-Vorbereitung

Stand: 27.11.2026. Entscheidung: Noch keine Pilotfirma; allgemeine Firmenlösung vorbereiten.

**Kein Firmenbereich ist live aktiviert. Keine Abacus-Verbindung, Partnerregistrierung oder Auszahlung wurde vorgenommen.** Diese Vorbereitung enthält ausführbaren Backend-/App-Code und einen Integrationsauftrag; sie ist keine Marketplace-Zulassung.

## Was vorhanden ist

| Bereich | Implementierter Stand | Noch erforderlich |
|---|---|---|
| Firmenkonto | Atomare Anlage, Admin, CHF-Standard, weitere Währung per API | Vertrags-/Abrechnungsmodell für Unternehmen |
| Mitgliedschaft | Gehashte Einladung, sieben Tage, Rotation, Mitglied/Admin/Buchhaltung | Austritt/Entzug, verwaltete Einladungen, Missbrauchslimits |
| Beleg einreichen | Eigener Beleg, Grund und Kategorie, expliziter Hinweis auf Firmenkopie, wiederholbar | Staging-Abnahme mit echten Rollen und Originalbildern |
| Prüfbeleg | Unveränderlicher Snapshot und Verlauf; separate Bildkopie mit SHA256 vor Freigabe | Lösch-/Aufbewahrungskonzept, Backups und Integritätsbetrieb |
| Freigabe | Andere Prüfperson; optionale Verpflegungsgrenze | Firmenreglement, Vertretung, Teilbeträge/FX bei Bedarf |
| Übergabe | Wiederholbares JSON-Paket mit Prüfspur und Bildreferenz | Konkretes Abacus-Spesenschema, Originalbelegübertragung und Mapping |
| Auszahlung | Bewusst kein manueller Zahlungserfolgs-Schalter | Abacus-/Bankprozess samt belastbarer Bestätigung |
| SwiftUI | Firmenformulare und echte RPC-/Archivaufrufe | Feature bleibt `NO`; Geräte- und Stagingtest vor Aktivierung |

## Wie Piggy in Abacus kommt

Der vorgesehene Weg ist eine über REST angebundene Partnerlösung. Der [Abacus Marketplace](https://www.abacus.ch/abacus-marketplace/produkte) beschreibt technisch geprüfte Lösungen. Die [API-Partner-Seite](https://www.abacus.ch/abacus-marketplace/api-partner) verknüpft integrierte Partnerprodukte mit deren Veröffentlichung im Marketplace und bietet „Neue Anbindung“ und „API Partner werden“ an. Ein beliebiger iOS-Build lässt sich dadurch noch nicht als Plugin installieren.

1. Produkt-/Schnittstellenanfrage mit dem untenstehenden Dossier vorbereiten.
2. Mit Abacus die API-Partnerschaft, Testumgebung, technische Prüfung und kommerziellen Bedingungen klären. Noch keine Anmeldung versandt. Die verlinkten Antragsseiten waren beim Abruf teilweise durch Bot-Prüfung blockiert; genaue Gebühren, Fristen oder Zulassungszusagen sind nicht bekannt.
3. Mit einem Abacus-Betreuungspartner einen Testmandanten mit benötigten Modulen und einem unabhängigen Prüfer einrichten. Ohne Pilotfirma zunächst eine von Abacus angebotene Entwicklungs-/Testumgebung anfragen.
4. REST-Dokumentation/Swagger und passende Version für Spesen, Belegbilder und Status besorgen. Keine unbestätigten Schnittstellen implementieren.
5. Verifiziertes Mitarbeiter-, Mandanten-, Steuer-, Konto- und Kostenstellenmapping samt wiederholbarer Belegübertragung bauen. Erst danach Testfälle auf der Abacus-Seite durchführen.
6. Marketplace-Veröffentlichung erst nach den von Abacus bestätigten Partner-/Qualitätsanforderungen beantragen.

Die [offizielle REST-Autorisierungsdokumentation](https://downloads.abacus.ch/fileadmin/ablage/abaconnect/htmlfiles/docs/restapi/abacus_authorization_rest_api.html) beschreibt unter anderem Q910 für anwendungsspezifische Service-User, Client-ID/Secret und zugewiesene Berechtigungen. Dort ist auch eine zusätzliche einmalige Freigabe innerhalb von 21 Tagen beschrieben. Q981-Standard-ServiceUser und Q910-Entity-API-Zugang sind nicht austauschbar. Den konkret passenden Flow muss Abacus für die verwendeten Spesen-APIs bestätigen; keine Credentials in iOS, Chat, Dateien oder Exportpaketen. Secrets gehören serverseitig in den Vault. Beim erneuten Abruf war die Dokumentation zeitweise ebenfalls durch Bot-Prüfung blockiert.

## Vorbereitetes Partnerdossier — nicht versandt

Produkt: Piggy, SwiftUI-App zur Belegerfassung. Geplante Erweiterung: Unternehmens-Spesen mit unabhängiger Prüfung und nachvollziehbarem Originalbeleg.

Anwendungsfall: Mitarbeitende bezahlen ein geschäftliches Mittagessen privat, scannen den Beleg und reichen ihn mit Grund bei ihrer Firma ein. Eine zweite berechtigte Person prüft. Das Ziel ist die Übergabe an Abacus mit Original und Prüfspur; Erstattung folgt dem durch Abacus und das Unternehmen bestätigten Zahlungsprozess.

Technische Vorbereitung: Firmenrollen, atomare RPCs, eingefrorene Belegdaten, getrennte archivierte Bildkopie, SHA256, Historie und wiederholbarer neutraler Export. Noch keine Abacus-API-Anbindung oder Zertifizierung. Noch keine Pilotfirma.

Von Abacus zu klären:

- API-Partner-/Marketplace-Anmeldung, Anforderungen, Kosten, technische Prüfung und Testzugang ohne bestehenden Kunden.
- Unterstützte Versionen, Hostingvarianten und erforderliche Spesen-/Finanz-/Zahlungsmodule.
- Exakte Endpunkte und Schemas für Mitarbeitende, Spesen, Einzelpositionen/MWST, Originalbeleg und Freigabestatus.
- Geeigneter OAuth-Flow, Service-User/Scopes, Mandantentrennung, Erneuerung/Widerruf und Netzwerkvoraussetzungen.
- Eindeutige externe Belegreferenz, Idempotenz, Änderungs-/Stornofälle, Rate Limits, Fehlercodes und Wiederanlauf.
- Tatsächliche Quelle einer Auszahlungsbestätigung, deren Bedeutung und Behandlung fehlgeschlagener oder zurückgewiesener Zahlungen.
- Kontakt zum zuständigen Integrations-/Betreuungspartner und zulässige Marketplace-Darstellung.

Vor einer Einreichung ergänzen: rechtlicher Anbietername und Adresse, verantwortlicher Produktkontakt, Supportkontakt, Datenschutz-/Vertragsunterlagen und gewünschtes Geschäftsmodell. Keine Firmendaten erfinden.

## Backend-Paket

Migration: `supabase/migrations/20261127193728_company_expense_pilot.sql`. Edge Function: `supabase/functions/company-expenses/index.ts`.

RPC `company_workspace(p_action,p_input)` unterstützt `list`, `create`, `join`, `workspace`, `rotate_invite`, `set_role`, `submit`, `review`, `withdraw`, `prepare_export`. Firma/Mitgliedschaft und Rolle werden immer aus der angemeldeten Identität geprüft. Clientfelder ersetzen keine Berechtigung. Direkte Legacy-Schreibzugriffe werden entzogen; bestehende Integrationen vor Migration darauf prüfen.

Die Archivfunktion akzeptiert nur `{op:"archive"|"image",submission_id:"UUID"}`. Kein Client darf einen Quellpfad, Empfänger, Archivschlüssel oder Auszahlungsstatus bestimmen. Archivierung liest ausschliesslich den bereits eingefrorenen eigenen Pfad, maximal 15 MiB JPEG/PNG, und kopiert das Bild nach `company-receipts/<company>/<submission>/<sha256>`. HTTPS-Altlinks werden nur vom eigenen Supabase-Origin und eigenen Nutzerpfad akzeptiert. Reguläre Speicher-PUT-/DELETE-URLs bleiben für Firmenkopien gesperrt. Archivbilder erhalten nur kurz gültige GET-Links nach Rollenprüfung.

Voraussetzungen auf Staging: bestehende Firmenfoundation und Hardening, Supabase-JWT-Prüfung, serverseitiger Service-Role-Key und bestehende private R2-Konfiguration. Keine Service-Role in der App. Function mit JWT-Verifikation deployen, niemals `--no-verify-jwt`. Kein Deployment in dieser Session: kein verfügbarer administrativer Supabase-Zugang; keine externen Systeme wurden verändert.

Die App bleibt mit `PIGGY_COMPANIES = NO` deaktiviert. Erst nach Backend- und Datenschutz-Abnahme gezielt für einen Staging-Build aktivieren. Nicht gleichzeitig ungeprüft auf Produktion schalten.

## Abnahme vor Firmenpilot

1. **Löschung zuerst lösen:** Die neue Prüfspur verhindert aktuell über `RESTRICT` das Löschen eingereichter persönlicher Belege bzw. beteiligter Konten. Vor Live-Migration ein abgestimmtes Konzept für Identität, Aufbewahrung und Konto-Löschung umsetzen und den bestehenden Löschablauf testen. Siehe ADR 0006. Kein produktiver Einsatz mit diesem offenen Konflikt.
2. Mitgliederentzug/Austritt und Missbrauchslimits ergänzen, Log-/Backup-/Berechtigungskonzept und grössere Listen prüfen. Alte Firmen ohne neue Einstellungen nicht still migrieren.
3. Auf einer separaten Staging-Instanz Migration und Function ausrollen; rollengetrennte echte Konten verwenden. Erstellung, Einladung, abgelaufener/rotierter Code, Fremdzugriff, Eigenfreigabe, Rückzug/Ablehnung, fehlendes Original und Überschreiten der Grenze testen.
4. R2-Bild nach Einreichung mit dem Hash vergleichen. Privaten Beleg danach ändern; Firmenkopie muss identisch bleiben. Archiv-Fehler und erneuter Versuch dürfen keine zweite Einreichung erzeugen. Identisches Bild in anderem Beleg wird bei Archivierung zurückgewiesen. Unterschiedliche Fotos derselben Quittung sind dadurch allein nicht ausgeschlossen.
5. Firmen-/Abacus-Daten später erfassen: rechtliche Firma, Instance-URL, Version, Mandant, Module, Betreuungspartner und Mitarbeiter-/Kontierungszuordnung. Zugangsdaten nur Vault. Noch keine dieser Angaben vorhanden.
6. Übertragung nur gegen bestätigte Swagger-Spezifikation bauen und mit echter API-Antwort prüfen. Timeout/Wiederholung darf keine Doppelbuchung erzeugen. Bankablehnung darf nie als bezahlt erscheinen.
7. App auf echtem Gerät mit grosser Schrift, VoiceOver, Tastatur, Loginwechsel und zwei getrennten Rollen prüfen. Bisher belegt eine Simulator-Kompilierung keine interaktive Abnahme.

## Lokale Verifikation

`node scripts/tests/company-pilot-db.mjs` nutzt eine isolierte PGlite-Datenbank und die tatsächlichen Foundation-/Hardening-/Pilot-SQL-Dateien. Das PGlite-Modul liegt ausserhalb des Repos; alternativ dessen Pfad als `PIGGY_PGLITE_MODULE` übergeben. Reproduzierbare Installation beispielsweise in einem temporären Verzeichnis: `npm install --prefix /tmp/piggy-duplicate-pg @electric-sql/pglite@0.5.10`.

`npx jest supabase/functions/_shared/company/archive.test.ts --runInBand` prüft Authentifizierung, Zugriffe, Pfade, echte Byte-Hashes, Wiederholung und Fehler. `npx --yes deno@2.9.6 check supabase/functions/company-expenses/index.ts` prüft den deploybaren TypeScript-Einstieg. Coretests und Apple-Kompilierung über K-Dev; Buildnachweis wird im Verifikationsprotokoll festgehalten.
