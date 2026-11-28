# Piggy 1.0 (35): TestFlight mit Share Extension

Stand: 28.11.2026. Production-Job `0484e01e06ae8783` über K-Dev, explizite Zielgruppe `Internal Testers`. Quellstand `67e56d0cba5fada3bd253b5a151dad72ac27e788`.

## Bestätigte Vorbereitung

Die zentrale Registry wurde ausschliesslich um die drei Share-Signierungsfelder des bestehenden `piggy-native`-Eintrags ergänzt. Nach dem vom Nutzer ausgeführten `kdev restart` bestätigt die aktive Registry beide Bundle-IDs und `group.com.heartbeat.piggy`. Keine fremden Dashboard-Änderungen gestaged oder committet. Die 13 vorhandenen Multitarget-Tests bestehen.

K-Dev hat Profile für App `com.heartbeat.piggy` und Extension `com.heartbeat.piggy.share` erstellt. 59 Swift-Coretests, Simulator-Kompilierung, signiertes Archiv und IPA-Export bestanden. Im tatsächlichen IPA-Paket geprüft: beide Targets Buildnummer35, beide eingebetteten Profile mit der gemeinsamen App Group, Extension unter `PlugIns/PiggyShare.appex`.

## Datenschutzdeklarationen

Bei der Paketprüfung von Build34 fehlte ein eigenes Privacy Manifest in der neuen Extension und die passende App-Group-Begründung. Dessen Freigabe wurde deshalb nach Upload abgebrochen (Job `a157dcf0036c5b28`, `cancelled`); keine erfolgreiche Gruppenfreigabe für Build34 behauptet.

Korrektur: App und Extension deklarieren Gruppen-UserDefaults mit `1C8F.1`; Dateimetadaten im eigenen/Group-Container mit `C617.1` und ausdrücklich ausgewählten Dateien mit `3B52.1`. Die bestehende App-only-Begründung `CA92.1` bleibt für die Haupt-App erhalten. Die neue Resource-Phase bettet das Extension-Manifest ein. Validator prüft beide Manifeste; beide sind zusätzlich im IPA35 geprüft. Grundlage: [Apples erlaubte API-Begründungen](https://developer.apple.com/documentation/bundleresources/app-privacy-configuration/nsprivacyaccessedapitypes/nsprivacyaccessedapitype).

## Artefakt und Upload

`piggy-native-1.0-35.ipa`, SHA256 `74153e2802f5854c774722555e61c94526b7539a82d2017d03eabd8140dec64f`. Upload von Apple angenommen am 28.11.2026 um00:03:14 UTC; Delivery-ID `bb810f80-857b-4de0-946a-b8a06d33490c`.

## Apple-Verarbeitung und interne Freigabe

**Erfolgreich abgeschlossen:** `status:succeeded`, `processingState:VALID`, `released:true`, Version1.0, Build35. Apple-Build-ID `bb810f80-857b-4de0-946a-b8a06d33490c`. Bestätigte Gruppe: `Internal Testers`, intern, ID`a64f98a8-c50e-44fd-9dbe-7b4d150fb9a3`. Freigabe am28.11.2026 um00:05:55 UTC (01:05:55 Europe/Zurich). Alle Gates (`checks`, `simulatorBuild`, `appleUpload`, `appleProcessing`, `testflight`) bestanden. Kein öffentlicher App-Store-Release und keine externe Testgruppe freigegeben.

Der Build enthält manuelle Beleg-Zusammenführung, gemeinsame Bild-/Datenansicht, persönliche Logos/Kataloglinks, Share-Eingang mit PDF-Original und separate lokale Coupons. Firmen-/Abacus- und SumUp-Liveanbindung bleiben deaktiviert bzw. nicht eingerichtet. Kein interaktiver Gerätetest oder Kassenscanner-Test in diesem Buildauftrag.
