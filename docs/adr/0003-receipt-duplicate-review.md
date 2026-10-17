# ADR 0003: Explizite, transaktionale Quittungs-Duplikatprüfung

## Status
Accepted and deployed (2026-10-17). Rollout und Verifikation: `docs/verification/2026-10-17-duplicates-backend.md`.

## Kontext
Ein erneuter Scan darf bestehende Bankverknüpfungen, manuell geprüfte Angaben oder Aufteilungen nicht verlieren. Ein besseres Bild muss unabhängig von Artikeln und anderen Feldern übernommen werden können. Zwei tatsächliche Käufe mit identischem Datum/Betrag/Laden sollen dauerhaft getrennt bleiben können.

## Entscheidung
- Kandidaten-Erkennung in `finance-core/receipt-duplicates.ts`: Datum, Betrag auf Rappen, Währung und normalisierter Ladenname müssen übereinstimmen. Nur Vorschlag, keine automatische Entscheidung. Fehlende Daten erzeugen keinen Treffer. Die App prüft eigene Quittungen, nicht die von Projektmitgliedern.
- Der Vergleich zeigt Datum, Betrag, Laden, beide Bilder und Feldkonflikte. Zusammenführen ist hervorgehobene Aktion, benötigt aber eine bewusste Bestätigung mit Vorschau. Feld-, Zusatzfeld-, Bild- und Artikelauswahl sind unabhängig. Leere Angaben werden standardmässig ergänzt, Konflikte bleiben beim bestehenden Beleg.
- RPC `merge_receipt_duplicates` behält die ID des gewählten Zielbelegs. Ein privater Snapshot archiviert beide Originaldatensätze, Artikel und direkte Beziehungen vor jeder Änderung. Danach werden Verknüpfungen verschoben und der zweite aktive Beleg gelöscht, damit er nicht doppelt in Finanzsummen erscheint. Bilddateien werden nicht gelöscht.
- RPC `merge_receipt_scan` übernimmt einen noch ungespeicherten Scan direkt in den Zielbeleg. Es gibt keinen zwischenzeitlichen zweiten Finanzbeleg.
- RPC `save_separate_receipt_scan` speichert neuen Beleg, Artikel und alle Getrennt-Entscheidungen in einer Transaktion. `keep_receipts_separate` normalisiert Paar-IDs; Entscheidungen werden beim späteren Merge auf die überlebende ID übertragen.
- RPC `delete_reviewed_receipt` erlaubt Löschen nur für eigene, unverknüpfte Belege. Bank-, Split-, Sharing- und Firmeneinreichungsbeziehungen müssen zuvor geprüft/gelöst werden.
- Öffentliche RPCs sind `SECURITY INVOKER`; notwendige privilegierte Relationstransfers liegen im nicht exponierten Schema `private`, mit leerem `search_path`, expliziter Authentifizierung, Eigentumsprüfung beider Belege, stabil sortierten Zeilensperren, Input-Whitelist und eingeschränkten Execute-Grants. Neue Entscheidungstabelle hat RLS und nur eigene SELECT-Rechte; die private Historie ist für Clients nicht les-/schreibbar.

## Schutzregeln
- Verschiedene Bankbuchungen, zwei Aufteilungen, verschiedene Projekte/Firmen sowie widersprüchliche geteilte/eingereichte Belege blockieren die Zusammenführung. Es werden keine Beziehungen still verworfen.
- Bereits verknüpfter Betrag/Währung bleiben erhalten; Ausgleichsposten werden nicht doppelt übernommen. Ein vorhandener Ausgleichsposten bleibt mit dem Ziel verbunden, unabhängig von der normalen Artikelauswahl. Zwei vorhandene Ausgleichslisten müssen zunächst manuell geprüft werden.
- Geprüfte Artikel dürfen durch diesen Ablauf nicht verworfen werden. Eine Split-Artikelliste muss wegen bestehender Ausschlüsse mit ihren IDs erhalten bleiben. Bei Konflikten muss der Nutzer zuerst die Daten prüfen; das ist bewusst konservativ.
- Bankverknüpfungen bleiben inklusive Status bestehen. Identische Paarverknüpfungen werden kollabiert. Abgelehnte oder bereits entschiedene Bankvorschläge haben Vorrang vor offenen Vorschlägen; keine automatische Annahme. Nicht identische abgeschlossene Vorschlagsentscheidungen müssen zuerst geklärt werden.
- Wenn eine künftige Migration eine zusätzliche Fremdschlüsselbeziehung zu `receipts` einführt, blockiert Merge zunächst, bis deren Erhalt implementiert ist.

## Verifikation und Grenzen
`src/lib/receiptDuplicates.test.ts` prüft Kandidaten und Vorschau. `scripts/tests/receipt-duplicates-db.mjs` wendet die echte Migration auf eine isolierte PostgreSQL-Engine (PGlite) mit Schema-Fixture an und prüft Eigentum, Rechte, Rollback, bessere Bilder mit Bankverknüpfung, Splits, Quelle-Ziel-Beziehungstransfers und getrennt gespeicherte Scans. Das ersetzt keinen vollständigen Staging-Test: Das historische Basisschema für Quittungen und Sharing ist nicht vollständig im Repository versioniert. Vor Release die Migration gegen eine vollständige Staging-Datenbank und parallele reale Workerläufe prüfen.

Die Historie ermöglicht eine spätere kontrollierte Wiederherstellung, enthält aber noch keine automatische Undo-Funktion. Ein verlorener Netzwerk-Response nach erfolgreichem Speichern muss vor erneutem Speichern durch Neuladen geprüft werden; die Transaktion schützt vor Teilzuständen, nicht vor bewusst erneut gesendeten neuen Scan-Aufträgen.
