# Piggy Native

SwiftUI iPhone-Client (iOS 17+), Umsetzung noch nicht auf Apple-SDK kompiliert. Bestehende Expo-App bleibt im Repository erhalten. Kein Release/TestFlight ausgeführt.

## Projekt vorbereiten

```bash
python3 native/Piggy/scripts/prepare_config.py --env-file .env
python3 native/Piggy/scripts/sync_brand.py
python3 native/Piggy/scripts/generate_project.py
python3 native/Piggy/scripts/validate_resources.py
```

`Piggy.xcodeproj`, Scheme `PiggyNative`. Neue Swift-Dateien erfordern erneute Projektgenerierung. Brand-Sync erwartet den bestehenden K-Creative-Ordner; APNGs müssen dort vorher vollständig gerendert und geprüft sein. Laufzeitassets sind eingecheckte Kopien mit Herkunfts- und Hashnachweis.

`prepare_config.py` übernimmt ausschliesslich explizit erlaubte öffentliche Clientwerte aus Umgebung bzw. `.env` in die git-ignorierte `Config/Local.xcconfig`. Supabase-Service-Schlüssel werden abgelehnt. Die Datei darf als öffentliche Clientkonfiguration im K-Dev-Quellbundle liegen; sie enthält keine privaten Server-/Signing-Schlüssel. Für einen reproduzierbaren Release muss der Build-Owner dieselben Werte bereitstellen. Leere Konfiguration verhindert Anmeldung und Käufe sichtbar.

Variablen: `EXPO_PUBLIC_SUPABASE_URL`, `EXPO_PUBLIC_SUPABASE_ANON_KEY`, `EXPO_PUBLIC_REVENUECAT_IOS_KEY`, `EXPO_PUBLIC_PRIVACY_URL`, `EXPO_PUBLIC_TERMS_URL`, `EXPO_PUBLIC_SUPPORT_URL`. RevenueCat-Entitlement bleibt technisch `plus`, Offering `default`. Monatspreis CHF 10 in App Store Connect und RevenueCat konfigurieren; die UI liest den tatsächlichen Storepreis. Jahresangebot standardmässig deaktiviert. AdMob ist standardmässig deaktiviert und benötigt explizite Konfiguration samt Freigabe und Nutzerzustimmung; kein garantiertes 30-Sekunden-Format.

## Backend

Der neue read-only Adapter `supabase/functions/native-finance` muss im bestehenden Supabase-Projekt bereitgestellt werden, bevor native Auswertungen funktionieren. Er nutzt den angemeldeten Nutzer und RLS, keinen Service-Key. Keine Schemaänderung. Kamera-OCR läuft mit Apple Vision; KI sendet nur erkannten Text nach ausdrücklicher Zustimmung an den vorhandenen `scan-receipt`-Endpunkt. Bei Erkennungsfehlern bleibt manuelle Erfassung möglich.

## Verifikation und Build

Apple-Builds ausschliesslich über K-Dev, nicht direkt per SSH/Xcode-MCP. `Config/kdev-native-registry.json` dokumentiert den separaten nativen Eintrag `piggy-native`, registriert am 2026-11-18 zur Meldung `KI-20261117-0f3851`. Der Expo-Build wurde aus der aktiven Registry entfernt; sein Quellstand 69353114 und seine Registry liegen unter `/storage/Public/K-Dev/archive/Piggy-Expo-20261118/`. Gemeinsame Quellen bleiben für die Migration im Repository. Ein lokaler Simulator-Build ist beauftragt; kein Production-/TestFlight-Auftrag. Der Worker führt nach Registrierung `PiggyCore`-Tests und eine unsignierte Simulator-Kompilierung aus.

Bereits geprüft: bestehende 266 Jest-Tests/21 Suites und TypeScript; Deno-Typprüfung des neuen Adapters; native Swift-Syntaxparser; Asset-Hashes, 17 Varianten, 222 Frames, APNG-Timing/Naht/Ränder, Icon und Plists. Syntaxparser und Ressourcenprüfung ersetzen keine Apple-Typprüfung. Core-XCTest-Dateien sind geschrieben, noch nicht ausgeführt.

Offen: Apple-Build, echter Simulator-Walkthrough mit Neustart/Skip/Replay/Reduce Motion/Dynamic Type, Login über beide Provider, RLS/Speichern/Bild-URLs, KI-Zustimmungswiderruf, tatsächliche Kamera auf iPhone, StoreKit-Sandbox-Kauf/Restore und Gold-Werbefreiheit. PDF-/Bankimport bleiben ausserhalb des aktivierten Umfangs. Vor Veröffentlichung Datenschutzangaben und SDK-Manifeste mit dem Build-Owner abgleichen. Die eigenen Datentypen folgen [Apples Manifest-Dokumentation](https://developer.apple.com/documentation/bundleresources/app-privacy-configuration/nsprivacycollecteddatatypes/nsprivacycollecteddatatype).

Architektur: [ADR 0004](../../docs/adr/0004-native-swiftui-client.md).
