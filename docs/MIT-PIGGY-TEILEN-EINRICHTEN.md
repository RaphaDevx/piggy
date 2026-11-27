# «Mit Piggy teilen» einrichten

Stand: 27. November 2026.

Ziel: In Fotos, Dateien oder einer PDF-Ansicht auf **Teilen → Piggy** tippen. Danach die Datei in Piggy als Quittung oder Coupon übernehmen.

**Aktueller Stand:** Share Extension und Dateieingang sind im Code vorhanden. Apple-Prüfbuild mit eingebetteter Erweiterung und 59 Coretests bestanden. Die App-Group-Zuordnung im Developer-Portal, die zentrale Signierungskonfiguration und der neue TestFlight-Build sind noch nicht als erledigt bestätigt. Diese Anleitung ist keine Bestätigung einer bereits erfolgten Freischaltung.

## 1. Was bei Apple benötigt wird

Es braucht **keinen zweiten öffentlichen App-Store-Eintrag** für «Piggy Teilen». Die Erweiterung wird gemeinsam mit Piggy ausgeliefert. Ein neuer Build der bestehenden Piggy-App ist erforderlich.

Die einmalige Einrichtung erfolgt unter **Apple Developer → Certificates, Identifiers & Profiles**. App Store Connect ist anschliessend für Build, TestFlight und die Veröffentlichung zuständig. Auf der Produktseite gibt es keinen Schalter, der Piggy einfach ins Teilen-Menü einträgt.

| Eintrag | Wert |
|---|---|
| Bestehende App | Piggy |
| Apple-Team | `H6X9JDXF96` |
| Bundle-ID der Haupt-App | `com.heartbeat.piggy` |
| Neue Bundle-ID der Share Extension | `com.heartbeat.piggy.share` |
| Gemeinsame App Group | `group.com.heartbeat.piggy` |
| Sichtbarer Name im Teilen-Menü | Piggy |

Benötigte Portalrolle: Account Holder oder Admin. Offizielle Grundlagen: [Apple Share Extensions](https://developer.apple.com/library/archive/documentation/General/Conceptual/ExtensibilityPG/Share.html), [App Group registrieren](https://developer.apple.com/help/account/identifiers/register-an-app-group/).

## 2. Extension-ID registrieren

- [ ] Im richtigen Apple-Team anmelden: [Certificates, Identifiers & Profiles](https://developer.apple.com/account/resources/identifiers/list).
- [ ] Unter **Identifiers** prüfen, ob `com.heartbeat.piggy.share` bereits existiert.
- [ ] Falls nicht vorhanden: **+ → App IDs → App** wählen.
- [ ] Beschreibung beispielsweise **Piggy Share Extension** eintragen.
- [ ] Eine explizite Bundle-ID verwenden: `com.heartbeat.piggy.share`.
- [ ] Die Capability **App Groups** aktivieren und die ID registrieren.

Die bestehende ID `com.heartbeat.piggy` behalten. Keine neue Haupt-App in App Store Connect anlegen.

## 3. Gemeinsame App Group zuweisen

- [ ] Unter **Identifiers → + → App Groups** die Gruppe registrieren, falls sie noch fehlt.
- [ ] Beschreibung beispielsweise **Piggy Share Inbox**.
- [ ] Kennung exakt: `group.com.heartbeat.piggy`.
- [ ] Die Haupt-App-ID `com.heartbeat.piggy` öffnen.
- [ ] **App Groups → Configure** öffnen und `group.com.heartbeat.piggy` zuweisen; Änderungen speichern.
- [ ] Dasselbe für die Extension-ID `com.heartbeat.piggy.share` durchführen.

**Beide IDs müssen derselben Gruppe zugeordnet sein.** Sonst kann die Erweiterung Dateien nicht zuverlässig an den Eingang der Haupt-App übergeben.

Offizielle Anleitung: [App-Groups-Capability aktivieren und zuweisen](https://developer.apple.com/help/account/identifiers/enable-app-capabilities/).

## 4. Signierung und K-Dev aktualisieren

Dieser Abschnitt richtet sich an die Build-Verwaltung. Keine privaten Zertifikate oder Schlüssel in Chat, Git oder diese Datei kopieren.

- [ ] Provisioning-Profile für Haupt-App und Extension mit der zugewiesenen App Group erstellen bzw. erneuern.
- [ ] Die vorbereiteten Zusatzfelder aus [kdev-native-registry.json](../native/Piggy/Config/kdev-native-registry.json) in den bestehenden zentralen Build-Eintrag `piggy-native` übernehmen.
- [ ] Bestehende Registry-Werte wie Team, Scheme und Testgruppen erhalten; nur die erforderlichen Felder ergänzen.

Diese Zusatzfelder gehören in den bestehenden Build-Eintrag:

```json
{
  "capabilities": ["APP_GROUPS"],
  "appGroups": ["group.com.heartbeat.piggy"],
  "extensions": [
    {
      "bundleIdentifier": "com.heartbeat.piggy.share",
      "capabilities": ["APP_GROUPS"]
    }
  ]
}
```

Das ist ein Ausschnitt, keine vollständige Ersatzkonfiguration. Die Projektdatei enthält bereits das Extension-Target und dessen Einbettung. K-Dev muss beide Targets signieren; die App Group nur im Quellcode einzutragen reicht nicht.

Offizielle Profilanleitung: [App-Store-Provisioning-Profile erstellen](https://developer.apple.com/help/account/provisioning-profiles/create-an-app-store-provisioning-profile).

## 5. Neuen Build ausliefern

- [ ] Nach der Apple-/Registry-Einrichtung einen K-Dev-Prüfbuild ausführen.
- [ ] Einen ausdrücklich beauftragten Production-/TestFlight-Build über K-Dev starten.
- [ ] Warten, bis Apple den neuen Build erfolgreich verarbeitet und die gewünschte interne Testgruppe Zugriff hat.
- [ ] Den neuen Build auf dem iPhone über TestFlight installieren.
- [ ] Vor öffentlicher Veröffentlichung Datenschutzangaben und Review-Hinweise anhand des tatsächlichen Verhaltens prüfen.

Kein direkter SSH-Build und kein separater App-Store-Upload der Erweiterung. Für einen öffentlichen Release den aktualisierten Build bei der bestehenden Piggy-App zur Prüfung einreichen.

## 6. Piggy im Teilen-Menü auswählen

1. Piggy einmal öffnen und anmelden.
2. In **Fotos** ein Bild oder in **Dateien** eine PDF-Datei öffnen.
3. Auf **Teilen** tippen.
4. In der App-Reihe **Piggy** auswählen. Falls es fehlt: nach rechts zu **Mehr** und gegebenenfalls über **Bearbeiten** zu den Favoriten hinzufügen. Die genaue Darstellung hängt von iOS und der Quell-App ab.
5. **Im Piggy-Eingang ablegen** antippen.
6. Nach der Bestätigung Piggy öffnen: **Belege → Eingang – geteilte Dateien**.
7. Die Datei öffnen und die Übernahme ins aktuelle Konto bestätigen.
8. **Als Beleg prüfen** oder **Als Coupon erfassen** wählen und die Angaben vor dem Speichern prüfen.

Es wird nicht schon beim Teilen automatisch eine Ausgabe erzeugt. Die Erweiterung springt auch nicht über eine private API in die Haupt-App.

## 7. PDF aus dem Internet

Safari bzw. die Quell-App kann entweder die tatsächliche PDF-Datei oder nur deren Webadresse teilen.

- **PDF-Datei:** Sie erscheint als PDF im Piggy-Eingang und kann verarbeitet werden.
- **Nur Weblink:** Piggy kennzeichnet ihn als Link. Die PDF in der Quelle öffnen und als Datei teilen; falls nötig zuerst in Dateien speichern und von dort teilen.

Alternativ in Piggy: **Belege → Eingang → PDF oder Bild aus Dateien wählen**.

Die erste Version unterstützt je Datei bis zu 20 MB und PDFs mit 1–12 Seiten ohne Passwort. Eine PDF-Datei wird als ein Beleg behandelt. Mehrere unabhängige Quittungen in einer Sammel-PDF vorher aufteilen. Beim bestätigten Belegspeichern bleibt das vollständige Original-PDF zusätzlich zur Vorschau erhalten.

## 8. Testliste auf dem iPhone

- [ ] Foto aus Fotos teilen und als Beleg prüfen.
- [ ] PDF aus Dateien teilen und das vollständige Original nach dem Speichern öffnen.
- [ ] PDF aus Safari testen; bei reinem Link muss Piggy dies klar anzeigen.
- [ ] Foto oder PDF als Coupon übernehmen; Code und Ablaufdatum prüfen.
- [ ] Abbrechen darf keine Ausgabe erzeugen.
- [ ] Datei für Konto A teilen, Konto wechseln: Konto B darf den A-Eintrag nicht sehen.
- [ ] Empfang ohne Anmeldung bleibt zunächst unzugeordnet und verlangt später eine bewusste Übernahme.
- [ ] Mehrseitige bzw. zu grosse/gesperrte PDFs und einen vollen Eingang testen.

## Wenn Piggy nicht erscheint

| Beobachtung | Zuerst prüfen |
|---|---|
| Piggy fehlt vollständig | Wirklich neuer Build installiert? Piggy unter Mehr verfügbar? Teilt die Quelle eine unterstützte Datei bzw. HTTPS-URL? |
| Erweiterung erscheint, kann aber nicht ablegen | App Group beiden IDs zugewiesen und passende Profile im Build enthalten? |
| Datei wird als Link angezeigt | Die Quelle hat keine PDF-Datei übergeben. PDF in Dateien speichern und von dort teilen. |
| Eingang bleibt nach Konto-Wechsel leer | Der Eintrag kann einem anderen Piggy-Konto zugeordnet sein. Nicht automatisch dem neuen Konto zuweisen. |
| Eine Datei wurde noch nicht gespeichert | Eingang öffnen und die Übernahme als Beleg/Coupon abschliessen. Teilen allein legt keine Ausgabe an. |

Weiterführender technischer Stand: [Logos, Share-Eingang, Coupons und SumUp](SHARE-LOGOS-COUPONS-SUMUP.md). Buildnachweis: [Verifikation](verification/2026-11-27-share-brand-coupons.md).
