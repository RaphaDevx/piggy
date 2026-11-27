# ADR 0007: Lokaler Share-Eingang, persönliche Anbieterinfos und Coupons

Datum: 27.11.2026. Status: implementierter App-Entwurf, Apple-Freigabe und Geräteabnahme offen.

Die Share Extension dient als kleine lokale Übergabestelle für PDFs, Fotos und ausdrücklich gekennzeichnete HTTPS-Links. Kein Auth-Token, keine Supabase-/Werbe-SDKs und kein automatischer Cloud-Upload im Extension-Prozess. Dateien werden atomar im App-Group-Ordner veröffentlicht, begrenzt und nach Konto gefiltert. Ohne zuletzt aktives Konto bleiben Einträge geräteweit unzugeordnet und brauchen ausdrückliche Übernahme. Die Haupt-App teilt Empfang in Belegprüfung und Couponerfassung auf.

PDF-Text wird lokal gelesen oder pro Seite lokal erkannt. Ein Dokument ist ein Beleg; Sammel-PDFs müssen vorher getrennt werden. Bestehende KI-Einwilligung wird respektiert. Nach bestätigter Speicherung bleiben das vollständige PDF und ein Vorschaubild privat erhalten. Eindeutige interne `_piggy_original_pdf_<UUID>`-Referenzen in `extra_fields` sind keine gedruckten Händlerangaben und werden dort nicht angezeigt. Der vorhandene Merge erhält unterschiedliche Zusatzschlüssel und damit mehrere Original-PDFs. PDF-Referenzen dürfen bei späteren Metadaten-/Lösch-/Archivänderungen nicht verworfen werden. Die deaktivierte Firmen-Spesenlösung archiviert bisher nur das Bild: eine Firmenfreigabe mit PDF-Originalen braucht vor Aktivierung zusätzlich deren eigenen Archivnachweis.

Persönliche Logos/Kataloglinks und Coupons leben getrennt von Finanzbelegen im kontogetrennten lokalen App-Speicher. Keine automatische Serverseitensynchronisation, keine Händlerverifikation allein aus einem Logo, keine stille Katalogabfrage. System-Backups sind davon zu unterscheiden. Kontoabmeldung entfernt die sichtbare Zuordnung; bestätigte Konto-Löschung stösst lokale Bereinigung an. Ein späterer Cloud-Sync benötigt eigene Mandanten-/Zugriffstests und transparente Datenregeln.

Coupons werden aus Bildern lokal nach Codes durchsucht; erkannte Werte müssen mit Titel/Bedingungen/Datum geprüft werden. Unbekannte Ablaufdaten bleiben leer. Ein QR kann zum Vorzeigen erneut gerendert werden, andere Symbologien behalten das Original statt einer erfundenen Umwandlung. Persönliches „verwendet“ ist keine vom Händler bestätigte Einlösung.

Die Share Extension benötigt eigene Bundle-ID, App-Group-Capability und Profile. Die vorhandene App wird samt Extension als ein Produkt ausgeliefert. Vor Produktion muss die vorbereitete Registrykonfiguration zentral übernommen sowie die App Group beiden Apple-IDs zugewiesen werden. Keine Signing-Ausnahme oder Umgehung durch private APIs.

SumUp bleibt ein serverseitiger lesender Adapter ohne produktive Zugangsdaten. Der Adapter prüft Anbieterantwort und liefert eine stabile externe Referenz, implementiert jedoch noch keinen OAuth-Callback, persistenten Importjob oder Kundenempfang. Ausführlicher Setup-/Betriebsstand: `docs/SHARE-LOGOS-COUPONS-SUMUP.md`.
