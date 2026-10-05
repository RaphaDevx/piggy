# ADR 0001: Kontoauszüge als gedrosselte Hintergrund-Pipeline (pg_cron + Edge-Worker)

## Status
Accepted (2026-10-05)

## Kontext
Bisher las die App einen Kontoauszug in einem synchronen Aufruf (`process-statement`, Claude Sonnet, JSON-Ausgabe, `max_tokens` 16k) und glich danach einmalig auf dem Handy ab. Bei 10–40 Seiten / ~1000 Buchungen bricht das: Output-Limit (~250 Buchungen), Edge-Timeout (150–400 s), App muss offen bleiben, ein Fehler verwirft den ganzen Import. Quittungen, die nach dem Import gescannt wurden, wurden nie automatisch verknüpft. Gewünscht: Verarbeitung im Hintergrund, gleichmässig (Lastspitzen ausgleichen), sichtbarer Fortschritt pro Seite, austauschbares Einlesen, fortlaufender Abgleich.

## Entscheidung
- Upload legt die Datei in Storage und einen `bank_statements`-Eintrag mit `stage=queued` an.
- `pg_cron` ruft jede Minute (zusätzlich "Kick" nach Upload) die Edge Function `statement-worker` über `pg_net` auf; Auth über ein Vault-Secret.
- Der Worker zerlegt PDFs in Seiten (`statement_pages`), liest pro Tick höchstens N Seiten (Default 6, 3 parallel) über einen konfigurierbaren Extractor ein (CAMT deterministisch; Text-Layer → Haiku; Scan → Sonnet-PDF), mit Retry/Backoff pro Seite.
- Sind alle Seiten durch, wird verrechnet: Duplikate (Multiset, paginiert), Insert, exakter Abgleich, Vorschläge für Beinahe-Treffer.
- `rematch_queue` sorgt für fortlaufenden Abgleich nach jeder neuen Quittung.

## Begründung
- **Postgres-Queue statt pgmq/externer Queue:** Tabellen mit `FOR UPDATE SKIP LOCKED` sind gleichzeitig Fortschrittsanzeige (Realtime) und Audit (Tokens, Modell, Laufzeit pro Seite); pgmq bräuchte eine zweite Statusquelle.
- **Seiten statt Zeiträume:** Zeiträume sind vor dem Lesen unbekannt; Auszüge sind chronologisch, eine Seite ≈ ein Zeitabschnitt, und Seiten sind unabhängig wiederholbar.
- **Zeilenformat statt JSON:** etwa halb so viele Output-Tokens; ein kaputter Eintrag verwirft nicht die ganze Seite.
- **Drossel im Worker statt pro User:** gleicht Spitzen global aus; fair, weil reihum nach Seitennummer abgearbeitet wird.

## Konsequenzen
- Positiv: beliebig grosse Auszüge; App kann geschlossen werden; Fortschritt "Seite x von y"; Modell/Verfahren ohne App-Update tauschbar; Kosten pro Seite messbar.
- Negativ: Ein 40-Seiten-PDF braucht bei Default-Drossel ~7 Min.; Cron erzeugt ~43 000 Function-Aufrufe/Monat (leer ~0.2 s); mehr bewegliche Teile (Cron, Vault, Worker).

## Was passiert bei Austausch?
1. `cron.unschedule('statement-worker')`.
2. `useBankStatements.uploadStatement` wieder synchron auf `process-statement` umstellen (Edge Function existiert noch als Fallback).
3. Tabellen `statement_pages`, `rematch_queue` können bleiben oder per Migration entfernt werden; `match_suggestions` ist unabhängig vom Einlesen nutzbar.
