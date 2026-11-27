# Händlerlogos, Share-Eingang, Coupons und SumUp: Verifikation

## App-Quellstand und Apple-Artefakt

Initialer signierter Stand `fd2e53a753a586ba890bb8766cb2d54a25969d79`, Korrektur `bbd5fc4aa34353690fa39a5ec456e2b9a4f2cd62`. Über K-Dev nach `kdev` übergeben. Erster Job `1aac4318adbce5fa` fand einen Syntaxfehler durch eine fehlende schliessende Klammer im Coupon-PDF-Sheet; vor der erneuten Prüfung behoben.

**Erfolgreicher Job `595ceea75db2a616`**, Profil `local`, Abschluss 27.11.2026 20:54:17 UTC: `succeeded`, `exitCode:0`, `gates.checks:passed`, `gates.simulatorBuild:passed`. Vollständiges Joblog belegt **59 Swift-Coretests ohne Fehler**, darunter 4 neue Tests für Inbox-Besitzrechte inklusive gefälschtem Record-Owner, Inputlimits/URLs, Coupon-Gültigkeit und interne PDF-Metadaten.

Artefakt `piggy-native-simulator-595ceea75db2a616.zip`, SHA256 `05649a3aa8d17cce1010613b0572b35fc426fe83e66c75cf3afbe3451d3f0aa5`. ZIP direkt geprüft:

- Haupt-App `Piggy.app`, Bundle-ID `com.heartbeat.piggy`.
- Eingebettete Extension `Piggy.app/PlugIns/PiggyShare.appex`, Bundle-ID `com.heartbeat.piggy.share`, ausführbare Datei vorhanden.
- Extension Point `com.apple.share-services`, Principal Class `PiggyShare.ShareViewController`.
- Aktivierung für bis zu 10 Bilder/Dateien bzw. 1 Web-URL, kein pauschales TRUEPREDICATE.
- Beide Simulatorprodukte mit Buildnummer 28. Dies ist keine neue Apple-TestFlight-Buildnummer.
- Joblog enthält Kompilierung der ShareViewController-Quelle und erfolgreiche Validierung des eingebetteten Binaries.

## Weitere Prüfungen

- Ressourcenvalidierung erfolgreich: 261 Hashes, 17 Varianten, 222 Retina-Frames, Plists und deterministisches Xcode-Projekt.
- `npx jest supabase/functions/_shared/merchants/sumup.test.ts --runInBand`: **6 Tests bestanden**, fester GET-Endpunkt, Originaldaten/Dezimaltext, kanonische Identität, Refund-Ereignis, fremder Händler, ungültige Eingaben, Providerfehler und nicht erfundene Positionen.
- `npx --yes deno@2.9.6 check supabase/functions/_shared/merchants/sumup.ts`: erfolgreich.
- `node scripts/tests/receipt-duplicates-db.mjs`: **19 tatsächliche SQL-Szenarien bestanden**, einschliesslich neuem Fall mit zwei privaten Original-PDF-Referenzen, die den Merge überleben.
- `git diff --check`: erfolgreich.

## Tatsächlich verbleibend

Kein Test auf echtem iPhone, keine visuelle/VoiceOver-/Kassenscanner-Abnahme, keine erfolgreiche Signierung mit registrierter App Group behauptet. Zentrale K-Dev-Signierungsregistry noch ohne die neu vorbereiteten Zusatzfelder; diese liegen konkret unter `native/Piggy/Config/kdev-native-registry.json` vor. Vor Produktion Registry aktualisieren, beide Apple-IDs mit `group.com.heartbeat.piggy` verbinden und Profile erneuern. Kein TestFlight-Upload in diesem Auftrag.

Logos/Kataloglinks sind persönliche lokale Zuordnungen, kein bestehender Anbieter-Selbstservice. Coupons sind lokal und kontogetrennt; keine Händler-Verbrauchsprüfung oder automatische Rabattaktivierung. Nur QR wird erneut gerendert, andere Symbologien werden über das Original gezeigt. Web-URLs werden nicht automatisch in PDF-Dateien umgewandelt. PDF-Sammelbelege müssen vor Import getrennt werden.

SumUp ist ein isoliert geprüfter lesender Adapter. Keine OAuth-Anwendung, Tokenablage, Callback, persistente Importwarteschlange, Händlerverbindung oder Belegzustellung live eingerichtet. Offizielle Apple-/SumUp-Quellen und konkrete verbleibende Schritte: `docs/SHARE-LOGOS-COUPONS-SUMUP.md`.
