# Piggy: Logos, Teilen-Eingang, Coupons und SumUp

Stand 28.11.2026. Piggy1.0(35) mit Share Extension ist für Internal Testers in TestFlight freigegeben; AppleVALID und gemeinsame App Group bestätigt. Tests auf echten Geräten bleiben ein gesonderter Abnahmeschritt. Keine SumUp-Verbindung oder Anbieterregistrierung live eingerichtet.

## So wird es benutzt

- **Logos:** Beleg → Weitere Details → Anbieter → Logo, Anbieterinfo & Produktkatalog. Ein eigenes kleines Bild und eine optionale HTTPS-Katalogadresse hinterlegen. Das Logo erscheint danach auch in den Beleglisten und an bestehenden Belegen mit demselben normalisierten Geschäftsnamen. Zuordnung persönlich und auf diesem Gerät; keine automatische Behauptung eines offiziellen Händlerprofils.
- **PDF/Fotos teilen:** In Fotos, Dateien oder der PDF-Ansicht Teilen → Piggy → Im Piggy-Eingang ablegen. Danach Piggy öffnen → Belege → Eingang. Datei bewusst dem aktuellen Konto zuordnen und „Als Beleg prüfen“ oder „Als Coupon erfassen“ wählen. Der Eingang erzeugt selbst noch keine Ausgabe.
- **Weblinks:** Manche Apps teilen die URL statt der PDF-Datei. Piggy legt dann nur den Link ab. Die Quelle im Browser öffnen und die eigentliche Datei teilen bzw. zuerst in Dateien speichern. Kein ungeprüfter automatischer Webdownload.
- **PDF aus Dateien:** Auch direkt unter Eingang importierbar. Maximal 20 MB pro Datei und zehn Dateien je Auswahl; PDF mit 1–12 Seiten, unverschlüsselt. Vorhandener PDF-Text wird lokal gelesen; sonst lokale Texterkennung je Seite. Eine PDF-Datei entspricht einem Beleg. Enthält sie mehrere unabhängige Quittungen, vor dem Import aufteilen. Der bisherige KI-Schalter gilt erst bei der bewussten Belegprüfung.
- **Original-PDF:** Beim bestätigten Speichern zusätzlich zur Bildvorschau privat hochgeladen und unter Weitere Details → Original-PDFs wieder abrufbar. Beim Merge bleiben unterschiedliche PDF-Referenzen erhalten. Die Vorschau ist nur die erste Seite, der PDF-Betrachter zeigt alle Seiten.
- **Coupons:** Belege → Coupons. Fotografieren, Bild auswählen oder über den Eingang PDF importieren. QR-/Barcode wird lokal erkannt; mehrere Funde stehen zur Auswahl. Titel, Anbieter, Datum und Bedingungen werden geprüft eingetragen. Bei mehrseitigem PDF kann die Seite für die Codeerkennung gewählt werden; das vollständige PDF bleibt lokal am Coupon. Keine Ausgabe, Speseneinreichung oder automatische Kasseneinlösung. QR kann wieder dargestellt werden; bei anderen Codeformaten bleibt das Original zum Vorzeigen erhalten.

Logos und Coupons werden kontogetrennt im geschützten App-Speicher dieses Geräts abgelegt, nicht automatisch zwischen Geräten synchronisiert. Betriebssystem-Backups sind davon zu unterscheiden. Wechsel des Piggy-Kontos leert den sichtbaren persönlichen Bereich. Nach bestätigter Konto-Löschung wird die lokale Bereinigung angestossen. Ein Couponstatus „verwendet“ ist lediglich eine persönliche Markierung, kein Verbrauchsnachweis des Händlers.

## Muss App Store Connect angepasst werden?

**Ein neuer App-Build ist notwendig. Ein zusätzlicher öffentlicher App-Store-Eintrag für die Erweiterung ist nicht notwendig.** Die Share Extension ist ein eingebetteter Teil des Piggy-Builds; iOS verwendet sie im Teilen-Menü. Sie wird nicht durch einen Schalter auf der Produktseite aktiviert. Grundlage: [Apple Share Extensions](https://developer.apple.com/library/archive/documentation/General/Conceptual/ExtensibilityPG/Share.html), [App Extensions ausliefern](https://developer.apple.com/library/archive/documentation/General/Conceptual/ExtensibilityPG/index.html).

Einmalige technische Einrichtung im **Apple-Developer-Portal**, nicht bloss auf der App-Store-Connect-Produktseite:

1. App-ID für `com.heartbeat.piggy.share` im richtigen Team registrieren, falls noch nicht vorhanden.
2. App Group `group.com.heartbeat.piggy` registrieren und **beiden** IDs `com.heartbeat.piggy` und `com.heartbeat.piggy.share` zuweisen.
3. Beide Provisioning-Profile mit der App Group erstellen/erneuern und den Build mit eingebetteter, separat korrekt signierter Erweiterung exportieren.
4. In der zentralen K-Dev-Buildregistry die im Repository vorbereiteten Zusatzfelder aus `native/Piggy/Config/kdev-native-registry.json` übernehmen: App `capabilities:["APP_GROUPS"]`, `appGroups:["group.com.heartbeat.piggy"]`, Extension `com.heartbeat.piggy.share` mit `APP_GROUPS`. Bestehende übrige Registry-Werte erhalten. Die drei Zusatzfelder wurden am28.11.2026 für den bestehenden piggy-native-Eintrag übernommen und nach Dienst-Neustart aktiv geprüft.
5. K-Dev-Prüfbuild und anschliessend gezielt beauftragter TestFlight-Build. Vor öffentlichem Release die bestehende Datenschutzdarstellung, App-Privacy-Angaben und Review-Hinweise mit dem tatsächlichen Verhalten abgleichen.

Apple verlangt für die Portal-Einrichtung Account Holder oder Admin. Offizielle Schritte: [App Group registrieren](https://developer.apple.com/help/account/identifiers/register-an-app-group/), [Capability zuweisen](https://developer.apple.com/help/account/identifiers/enable-app-capabilities/), [Provisioning Profile](https://developer.apple.com/help/account/provisioning-profiles/create-an-app-store-provisioning-profile). Der vorhandene K-Dev-Worker unterstützt Extension-Profile, meldet eine fehlende App-Group-Zuordnung jedoch als manuellen Portal-Schritt. Die beiden Profile und ihre App-Group-Zuordnung wurden im IPA35 geprüft; Apple bestätigt die interne TestFlight-Freigabe. [Nachweis](verification/2026-11-28-testflight-share.md).

Nach Installation des passenden Builds: Eine Datei öffnen → Teilen → gegebenenfalls Mehr/Bearbeiten → Piggy als Favorit hinzufügen. Die genaue Auswahl hängt von der Quell-App ab. Die Erweiterung öffnet die Haupt-App nicht über einen privaten API-Trick; sie bestätigt die lokale Ablage und fordert zum Öffnen von Piggy auf.

## Kontogrenzen und lokale Inbox

`ShareInbox.swift` wird in Haupt-App und Extension kompiliert. Die Erweiterung enthält keine Supabase-/Werbe-/Analysebibliothek und lädt nichts hoch. Sie kopiert begrenzte Dateien unter zufälligen IDs in einen gemeinsamen geschützten Ordner und veröffentlicht den Eintrag erst nach vollständigem Schreiben.

Wenn zuletzt ein Konto aktiv war, bleibt der Eingangseintrag diesem Konto zugeordnet. Ein anderes Konto kann ihn weder lesen noch entfernen. Ohne aktives Konto erhält der Eintrag zunächst keine Kontozuordnung; die Übernahme ist später eine bewusste Entscheidung am Gerät. Dieser geräteweite unzugeordnete Eingang ist keine private Mehrbenutzer-Mailbox. Limits: 30 Einträge insgesamt, je 20 MB. Abgebrochene oder teilweise erfolgreiche Shares nennen die übernommene Anzahl. Kein automatisches Löschen nach Importfehlern; nach bestätigtem Speichern wird der Eingang bereinigt.

Logos werden aus ausdrücklich ausgewählten Bildern verkleinert gespeichert. Kein automatisches Favicon-/Logo-Scraping und kein Tracking-Pixel. Ein Katalog ist zunächst ein externer Link auf ausdrücklichen Wunsch, kein automatisch geladener Produktfeed. Persönliche Kataloglinks sind keine Händlerverifikation.

## Anbieter-Selbstverwaltung als nächster Ausbau

Die jetzige Logoablage ist eine persönliche Funktion. Anbieter sollen später ein verifiziertes Profil mit eigenem Logo, Rechtsträger, Filialen, Support und optionalem Produktkatalog pflegen können. Dafür braucht es einen getrennten Händlerbereich mit Eigentumsprüfung, Bildprüfung, rollenbeschränkten Updates und Versionierung. Belege werden über stabile Händler-/Filial-IDs zugeordnet; ein Ladenname oder Logo allein bestätigt keine Identität.

PDF-Originale bleiben unverändert. Ein nachträglich geändertes Händlerlogo darf historische Beleginhalte nicht umschreiben. Aktuelle Händlerinfo und Original-Snapshot sind getrennt. Ein optionaler Katalog kann zunächst ein Link sein; spätere strukturierte Artikel brauchen SKU/GTIN, Menge/Einheit, Steuer-/Preisstand und eine geprüfte Quelle. Katalogpreise ersetzen niemals den damals bezahlten Preis. Katalogzugriff/Marketing bleibt freiwillig. Noch kein solcher Anbieter-Selbstservice produktiv.

## Was für SumUp vorbereitet ist

Ausführbarer, ausschliesslich lesender Adapter: `supabase/functions/_shared/merchants/sumup.ts`. Er ruft den offiziellen Beleg-Endpunkt beim festen SumUp-Host auf, verlangt eine serverseitig gebundene Händlerkennung, prüft die zurückgegebene Händlerkennung, hält den Betrag als Dezimaltext fest und erhält die vollständige Anbieterantwort. Wiederholungen über Transaktionscode bzw. kanonische Transaktions-ID ergeben dieselbe externe Identität; ein Refund-Ereignis besitzt eine eigene Referenz. Fehlende Artikel werden nicht erfunden. Mehr als zwei Dezimalstellen werden im ersten Adapter bewusst nicht akzeptiert.

Offizielle Referenzen: [SumUp Receipt API](https://developer.sumup.com/api/receipts/get/), [OAuth](https://developer.sumup.com/tools/authorization/oauth/), [Autorisierungswahl](https://developer.sumup.com/tools/authorization/). Der Belegabruf erfordert passende lesende Berechtigung; die Receipt-Dokumentation nennt `receipts.read` oder `transactions.history`. Ein echter Artikelkatalog bzw. sämtliche Kassenpositionen dürfen nicht allein aus einem Zahlungsbeleg unterstellt werden. Die vollständige Antwort wird erst mit realen Händlerbeispielen bewertet.

Der Adapter ist noch kein deployter Importdienst und verbindet sich nicht selbst im Hintergrund mit einem unbekannten Konto. Zur Aktivierung:

1. Eigenes Test-Händlerkonto bzw. eine ausdrücklich autorisierte Pilotfirma bestimmen.
2. Für den Einzelpilot einen zulässigen Händler-API-Zugang serverseitig verwenden; für mehrere Firmen eine SumUp-OAuth-Anwendung mit registrierter HTTPS-Callbackadresse vorbereiten. Die Wahl vom konkreten Einsatz und den Anbieterbedingungen abhängig machen.
3. Benötigte lesende Scopes bestätigen; keine Zahlungs-/Auszahlungsrechte für diesen Belegimport verlangen. Client-Secret, Access-/Refresh-Tokens nur im Server-Vault, niemals App, Git oder Chat.
4. Serverseitige einmalige OAuth-State-Sitzung mit Ablauf und sicherer Bindung an den Piggy-Händler ergänzen. Callback prüfen, Tokens erneuern/widerrufen können. Keine Zuordnung aus einem frei vom Client gewählten `mid` übernehmen.
5. Einen privaten Eingangsjob mit Idempotenz-Constraint, Hash/Originalarchiv und expliziter Kundenannahme aus dem papierlosen Händlerplan anbinden. Die hier erzeugte `externalKey` allein verhindert noch keine Doppelimporte ohne diesen persistierten Constraint.
6. Vollständige Testbelege, leere Artikelliste, Storno, Teilrückzahlung, Tokenablauf, Wiederholung und falsche Händlerkennung prüfen. Erst danach Pilot freischalten. Der gleiche Eingangsvertrag kann später weitere autorisierte Kassenadapter aufnehmen.

Keine OAuth-Anwendung registriert, kein Secret angelegt, keine Händlerbelege abgerufen, kein Hintergrundjob deployt. Der konkrete nächste externe Bedarf ist ein autorisiertes SumUp-Testkonto; die allgemeine Vorbereitung benötigt noch keine Firma.

## Abnahme auf echtem iPhone

PDF aus Dateien, PDF aus Safari und Foto aus Fotos teilen; Quelle sendet nur Link; 10 Dateien/20-MB-Grenze; abgebrochener Share; Konto A/B sowie ausgeloggter Empfang; gesperrtes/mehrseitiges PDF; Importabbruch ohne Ausgabe; Original-PDF nach Speichern und Merge vollständig abrufen; Couponcode mit führenden Nullen; mehrere Codes; Ablaufdatum inklusive letztem Gültigkeitstag; Original an der Kasse vorzeigen; grosse Schrift und VoiceOver; Konto-Löschung und lokale Bereinigung. Lokale Kompilierung ersetzt diese Tests nicht.
