# TestFlight mit Share Extension: Startvorbereitung

Stand 28.11.2026. Neuer TestFlight-Build ausdrücklich beauftragt; **noch kein Production-Job gestartet**.

- Der Nutzer bestätigt, die Apple-App-Group-Einrichtung vorgenommen zu haben. Providerseitige Profilprüfung erfolgt erst im Production-Build; diese ist damit noch nicht bewiesen.
- Bestehender Quellstand mit Share Extension zuletzt erfolgreich als Simulatorprodukt gebaut: Job `595ceea75db2a616`, 59 Coretests und eingebettete Extension geprüft.
- Zentrale Registry `/storage/Public/K-Dev/Dashboard/config/projects.json`: ausschliesslich beim bestehenden Eintrag `buildRegistry.piggy-native` die drei vorbereiteten Felder `capabilities`, `appGroups`, `extensions` ergänzt. Andere Werte und bereits bestehende fremde Änderungen erhalten; keine fremden Dashboard-Änderungen gestaged/committet.
- Die Registrydatei lässt sich mit der vorhandenen K-Dev-Konfigurationsvalidierung laden.
- `node --test /storage/Public/K-Dev/Dashboard/tests/native-multitarget.test.js`: 13 Tests bestanden, einschliesslich Profilen für mehrere Targets und App-Group-Abweichungen.
- `GET /api/project-check/piggy-native`: grün. Mac-Worker online und frei.

## Aktueller externer Schritt

Der laufende K-Dev-Core verwendet noch seine beim Start geladene alte Registry. `GET /api/build/registry` bestätigt: Share-Felder fehlen noch im aktiven Prozess. Der Neustartversuch `kdev restart` unter dem Projektkonto `chad` scheitert an fehlender sudo-Berechtigung. Keine Berechtigungsumgehung, kein Neustart der Agent-Sitzungen.

Einmal unter dem berechtigten Benutzer Raphael ausführen:

```bash
kdev restart
```

Der Befehl startet laut installiertem Host-CLI ausschliesslich `kdev-core` neu; Agent-Sitzungen bleiben erhalten.

Danach aktive Registry erneut über API nachlesen und den nativen Production-Build über K-Dev mit expliziter Gruppe `Internal Testers` starten. Die vorgegebene Registry enthält zusätzlich eine externe Gruppe; diese nicht ungeprüft übernehmen. Erfolg erst bei Apple `VALID` und bestätigtem internem Gruppenzugriff melden. Aktuell keine neue Versions-/Buildnummer und keine Freigabe behauptet.
