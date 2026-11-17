# ADR 0004: Nativer SwiftUI-Client für Piggy

Status: Umsetzung, Apple-Build und Geräteprüfung noch offen. 17.11.2026.

## Entscheidung

Der iPhone-Client wird unter `native/Piggy` eigenständig in Swift/SwiftUI umgesetzt, Ziel iOS 17. Der bestehende Expo-Client bleibt erhalten. Dieselbe Bundle-ID und dasselbe Supabase-Konto ermöglichen die spätere Aktualisierung im vorhandenen App-Store-Eintrag. Eine Übernahme der Expo-SecureStore-Sitzung ist nicht implementiert; Nutzer müssen sich beim Wechsel neu anmelden.

SwiftPM bindet exakt Supabase 2.55.7, RevenueCat 5.43.0, Google Mobile Ads 12.11.0 und UMP 3.0.0 ein. Pure Foundation-Regeln werden separat als `PiggyCore` getestet. Das Xcode-Projekt wird deterministisch aus den tatsächlich vorhandenen Swift-Dateien erzeugt; keine Expo-Laufzeit oder WebView für Produktfunktionen.

Finanzsummen bleiben in `_shared/finance-core`. Der neue authentifizierte, nur lesende Edge-Adapter `native-finance` verwendet Nutzer-JWT, RLS und expliziten Nutzerfilter. Er muss vor Nutzung bereitgestellt werden. Bankimport bleibt deaktiviert. Es wird keine zweite Summenlogik in Swift eingeführt.

## Marke und Bewegung

K-Creative bleibt die Quelle. 17 offizielle Varianten sind manuell wählbar; Automatik verwendet nur die vorhandenen Jahreszeitenfenster, keine verschiebbaren Feiertage. Das Home-Screen-Icon wird nicht automatisch geändert. Referenzordner sind Produktionsreferenzen und keine auswählbaren Markenvarianten.

Drei echte 3D-Schleifen wurden als 222 RGBA-Bilder gerendert und als 30-fps-APNG gepackt. Die App lädt nur Laufzeitdateien mit Herkunfts- und Hashnachweis. Blender-/GLB-Quellen und Einzelbilder werden nicht in die App kopiert. Reduce Motion verwendet statische Originalsprites; beim Verlassen bzw. Hintergrundwechsel werden dekodierte Frames freigegeben.

## Käufe, Werbung und Grenzen

Gold verwendet das bestehende technische Entitlement `plus`. Preise stammen aus StoreKit/RevenueCat; CHF 10/Monat muss im Store konfiguriert werden. Das Jahresangebot bleibt ohne separate Freigabe deaktiviert. Werbung ist standardmässig aus, benötigt Konfiguration, geprüfte Freigabe, lokale Zustimmung und UMP. Kein Ad-Request bei unklarem Gold-Status. Der Anbieter bestimmt Anzeigenlänge und Schliessmöglichkeit. Es gibt keine garantierte 30-Sekunden-Sperre.

Beleg-Scan/Duplikatentscheidungen nutzen vorhandene atomare RPCs. Belegbearbeitung und Social-Schreibvorgänge haben noch mehrere Datenbankaufrufe: Fehler werden sichtbar, Daten neu geladen, neue Projekte/Splits nach Teilfehlern soweit möglich zurückgenommen. Vorhandene Splits werden nicht destruktiv ersetzt. Diese Abläufe sind keine neu erfundene atomare Transaktion und müssen gegen echte RLS geprüft werden.

## Build und Auslieferung

K-Dev erhält einen separaten Registry-Vorschlag `Config/kdev-native-registry.json`; bestehende Expo-Builds bleiben erhalten. Die Signer-/Registry-Änderung gehört gemäss globalem AGENTS in die K-Dev-Session. Keine direkten SSH- oder MCP-Builds. Aktuell fehlt die native Registrierung (Meldung KI-20261117-0f3851), daher noch kein Apple-Build. Kein Production- oder TestFlight-Auftrag aus diesem Implementierungsauftrag ableiten.

## Prüfungen

Linux: Ressourcenhashes, APNG-Framezahl/Timing/Naht, Icon/Plists, deterministische Projektgenerierung, Swift-Syntaxparser, Deno-Check des Adapters, bestehende TypeScript-/Jest-Prüfungen. Offen: SwiftPM-Tests, Apple-Typprüfung/Simulator-Kompilierung, interaktiver Walkthrough, Auth/Käufe/Restore und reales Kamera-/OCR-/RLS-Verhalten.
