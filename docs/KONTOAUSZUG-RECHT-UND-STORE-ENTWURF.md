# Kontoauszug-Abgleich: Texte und Freigabe-Liste (Entwurf, keine Rechtsberatung)

Stand 2026-11-24. Die Live-Datenschutzseite (`public/privacy.html`, wird auch auf piggyfinance.ch gebaut) ist bewusst **nicht** geändert. Erst nach der Prüfung durch Kaufmann-Legal (Playbook `Dashboard/docs/playbooks/datenschutz-pruefung-kaufmann-legal.md`).

## 1. Schalter im Build
| Schalter | Ort | Stand | Bedeutung |
|---|---|---|---|
| `PIGGY_BANK_IMPORT` | `native/Piggy/Config/Base.xcconfig` | `YES` | Tab «Abgleich», Upload, Abgleich-Liste |
| `PIGGY_SHARE_BANK_ITEMS` | `native/Piggy/Config/Base.xcconfig` | `NO` | «An Freund senden» (Option C), braucht Migration `20261124000001_shared_bank_items.sql` |

**Vor Store-Einreichung und vor externem TestFlight:** beide auf `NO`, ausser die Rechtsprüfung ist abgeschlossen (Store-Legal-Gate L1 bis L10, `Dashboard/docs/adr/0020-store-legal-gate.md`).

## 2. Entwurf Ergänzung Datenschutzerklärung
**Kontoauszüge (optional).** Wenn du einen Kontoauszug hochlädst, speichern wir die Datei in einem privaten Speicherbereich und lesen die Buchungen aus (Datum, Betrag, Buchungstext). Wir gleichen sie mit deinen Quittungen ab. PDF- und CSV-Dateien werden dafür an Anthropic (Claude) übermittelt, CAMT-XML-Dateien werden ohne KI gelesen. Du stimmst vor dem ersten Upload ausdrücklich zu. Piggy greift nicht auf dein Bankkonto zu und bewegt kein Geld. Du kannst jeden Auszug in der App löschen; Datei, Buchungen und Zuordnungen werden dann entfernt. Beim Löschen des Kontos werden alle Auszüge gelöscht.

**Ausgaben an Freunde senden (nur wenn aktiviert).** Auf Wunsch sendest du einem bestätigten Freund eine einzelne Ausgabe: Händlername, Datum, Betrag und eine kurze Notiz. Buchungstext, Konto- und Kartennummern werden nicht gesendet. Der Empfänger kann bestätigen oder ablehnen. Du kannst eine nicht beantwortete Sendung zurückziehen.

**Dienstleister ergänzen (Abschnitt «Empfänger»):** Anthropic (Auslesen von PDF und CSV), Supabase (Speicher und Datenbank), Cloudflare (Website, später Belegspeicher).

## 3. Zu klären durch Kaufmann-Legal
1. Rechtsgrundlage und Einwilligung für Finanzdaten bei Anthropic (Drittland USA), AVV und Standardvertragsklauseln.
2. Aufbewahrungsdauer der Auszugsdatei: aktuell bleibt sie bis zum Löschen durch den Nutzer. Alternative: Datei nach erfolgreichem Auslesen automatisch löschen und nur Buchungen behalten.
3. Option C: Datenfluss zwischen Nutzern, Freunde-Funktion ohne Melden und Blockieren (Guideline 1.2).
4. Hinweis in der App, dass Buchungstexte Namen Dritter enthalten können.

## 4. App-Store-Angaben, wenn die Funktion in die Version kommt
- **App-Privacy-Label:** «Weitere Finanzdaten» (verknüpft, kein Tracking) bleibt Ja. «Nutzerinhalte» prüfen, weil Auszugsdateien hochgeladen werden.
- **Review-Notizen:** Kontoauszug-Import beschreiben, Beispielauszug (anonymisiert, CSV) als Anhang, Löschen unter Abgleich → Auszug nach links wischen.
- **Store-Text:** Erst anpassen, wenn die Funktion im Release ist. `docs/appstore-metadata.json` nennt sie bewusst nicht.
- **Privacy Manifest** (`native/Piggy/Resources/PrivacyInfo.xcprivacy`): Datentypen mit dem Label abgleichen.

## 5. Beispielauszug für den Reviewer
Anonymisierte CSV mit 10 erfundenen Buchungen (keine echten Namen, IBAN erfunden). Liegt noch nicht vor, vor der Einreichung erstellen.
