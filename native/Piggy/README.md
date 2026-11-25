# Piggy Native

SwiftUI iPhone-Client (iOS 17+), auf Apple-SDK erfolgreich kompiliert am 2026-11-18. Bestehende Expo-App bleibt im Repository erhalten. Kein Release/TestFlight ausgeführt.

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

Apple-Builds ausschliesslich über K-Dev, nicht direkt per SSH/Xcode-MCP. `Config/kdev-native-registry.json` dokumentiert den separaten nativen Eintrag `piggy-native`, registriert am 2026-11-18 zur Meldung `KI-20261117-0f3851`. Der Expo-Build wurde aus der aktiven Registry entfernt; sein Quellstand 69353114 und seine Registry liegen unter `/storage/Public/K-Dev/archive/Piggy-Expo-20261118/`. Gemeinsame Quellen bleiben für die Migration im Repository. Lokaler Simulator-Build `67279f9d4772c0a9` erfolgreich: Version 1.0 (27), 7 Swift-Core-Tests ohne Fehler. Kein Production-/TestFlight-Auftrag. Der Worker führt nach Registrierung `PiggyCore`-Tests und eine unsignierte Simulator-Kompilierung aus.

Bereits geprüft: bestehende 266 Jest-Tests/21 Suites und TypeScript; Deno-Typprüfung des neuen Adapters; native Swift-Syntaxparser; Asset-Hashes, 17 Varianten, 222 Frames, APNG-Timing/Naht/Ränder, Icon und Plists. Syntaxparser und Ressourcenprüfung ersetzen keine Apple-Typprüfung. Alle 7 Core-XCTest-Prüfungen auf dem MacBook bestanden; unsignierte Simulator-Kompilierung mit Xcode 26.6 bestanden.

Offen: echter Simulator-Walkthrough mit Neustart/Skip/Replay/Reduce Motion/Dynamic Type, Login über beide Provider, RLS/Speichern/Bild-URLs, KI-Zustimmungswiderruf, tatsächliche Kamera auf iPhone, StoreKit-Sandbox-Kauf/Restore und Gold-Werbefreiheit. PDF-/Bankimport bleiben ausserhalb des aktivierten Umfangs. Vor Veröffentlichung Datenschutzangaben und SDK-Manifeste mit dem Build-Owner abgleichen. Die eigenen Datentypen folgen [Apples Manifest-Dokumentation](https://developer.apple.com/documentation/bundleresources/app-privacy-configuration/nsprivacycollecteddatatypes/nsprivacycollecteddatatype).

Architektur: [ADR 0004](../../docs/adr/0004-native-swiftui-client.md).

## R2-Dateispeicher (25.11.2026)

Login, Tokens und Datenbank bleiben bei Supabase. `STORAGE_BACKEND=r2-with-fallback` ist der Standard in Base.xcconfig: neue Belegbilder und Kontoauszüge werden über `storage-url` direkt in den privaten EU-R2-Bucket geschrieben. Nur Lesen hat für ältere Dateien einen Supabase-Fallback. Ein fehlgeschlagener R2-Upload bleibt ein Fehler. Supabase-Tokens werden niemals an R2 geschickt, und R2-Schlüssel gelangen nie in die App.

Die Backendfunktionen und die Kopie der 333 bisherigen Dateien sind aktiv. Installierte Altversionen bleiben bis zum App-Update beim bisherigen Speicherweg; deshalb keine Supabase-Dateien entfernen und vor Abschaltung erneut abgleichen. Details und aktueller Nachweis in ADR 0005.
