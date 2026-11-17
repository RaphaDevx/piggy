# Piggy Gratis + Piggy Gold: Launch-Konfiguration

Stand: 17.11.2026. Implementierung lokal; keine Store-Produkte oder Werbekonten angelegt, kein Release eingereicht. App- und Werbe-IDs sind öffentliche Konfiguration, RevenueCat- und Webhook-Secrets gehören weiterhin in die vorhandene Schlüsselverwaltung. Keine Schlüssel in diesen Text kopieren.

## Angebot zum Start

| Piggy | Piggy Gold |
|---|---|
| Vorhandene Beleg-/Analyse-/Teilfunktionen kostenlos | Dieselben Funktionen, werbefrei |
| Nach eigener Werbeaktivierung und Google-UMP-Prüfung: Interstitial nach drei neu gespeicherten Belegen | Höheres vorhandenes KI-Tageslimit |
| Mindestens 180 Sekunden Abstand, maximal drei Versuche pro UTC-Tag auf dem Gerät | Goldfarbiges Piggy mit GOLD-Kennzeichnung im Dashboard und Profil |
| Kleiner Banner im Dashboard optional, standardmässig aus | Wahl zwischen Monats- und Jahresabo, sofern im Store vorhanden |

**Monatspreis, Nutzerentscheid vom 17.11.2026:** CHF 10.00 pro Monat. Der bisherige Jahresvorschlag CHF 39.90 ist bei diesem Monatspreis noch nicht bestätigt; Jahresangebot vor Store-Konfiguration neu festlegen (CHF 39.90 entspräche rund 67 % Rabatt gegenüber zwölf Monatszahlungen). Die App zeigt ausschliesslich den von Apple gelieferten lokalisierten Preis samt Laufzeit und Verlängerung. Kein Preis wird im UI erfunden, kein nicht verfügbares Jahresabo angezeigt. Der Nutzer wählt den Tarif; Monatsangebot steht zuerst. Einführungsangebote werden nur bei bestätigter Berechtigung angezeigt.

Das ist ein Gold-Logo **innerhalb der App**. Ein automatischer Wechsel des iPhone-Home-Screen-Icons ist nicht enthalten. Bestehende Standard-App-Assets bleiben gültig.

**Kein Leistungsversprechen „unbegrenzt“:** Die bestehenden Serverlimits bleiben erhalten. `scan-receipt` verwendet Rückfallwerte 10 Free-/100 Gold-KI-Scans pro Tag; die tatsächlichen `app_settings` sind hier nicht abgefragt. Kosten und konfigurierte Limits vor Release prüfen. Bei 100 Scans täglich können im 30-Tage-Monat allein 3’000 × angenommene CHF 0.004 = CHF 12 Cloudkosten entstehen. Auch Gold für CHF 10 ist bei dieser Vollauslastung nach Store-/Steuerabzügen nicht kostendeckend. Tages-/Monatskontingente sind verschiedene Produkte; die App verspricht keinen neuen Monatsumfang. Für die erste kleine Nutzerbasis tatsächliche Verbrauchskosten beobachten und neue Kontingente gesondert beschliessen.

## Warum keine erzwungenen 30 Sekunden nach jedem Scan?

Interstitials sind für natürliche Unterbrechungen vorgesehen. Videodauer, Format und Schliessmöglichkeit bestimmt das Netzwerk/Creative, nicht ein selbst erfundener Piggy-Timer. Die aktuelle Implementierung zeigt ein **Interstitial**, nicht zwingend ein Video. Kein zusätzliches Reward-Versprechen und keine vergüteten Scan-Credits.

Google warnt vor zu häufigen und navigationsbehindernden Interstitials. Quelle: [AdMob-Richtlinie](https://support.google.com/admob/answer/6201362), Abruf 17.11.2026. Piggy zeigt nichts beim Start, in der Kamera oder während der Korrektur. Erst speichern, dann gegebenenfalls ein bereits geladenes Interstitial, danach Belegdetail. Fehlt eine Anzeige, folgt das Detail ohne Nachladen/Wartebildschirm. Zusammenführen bestehender Belege erzeugt keinen Werbezähler.

**Banner rentieren sich erst über Volumen.** Eigene Szenariorechnung, keine gemessenen Schweizer Marktpreise:

| Format, jeweils 1’000 Werbe-Nutzer/Monat | Hypothese | Monatserlös vor eigenen Kosten |
|---|---|---:|
| Banner | 8 sichtbare Plätze × 60 % monetarisierbar × CHF 1 Netto-eCPM / 1’000 | CHF 4.80 |
| Interstitial | 3 Gelegenheiten × 60 % monetarisierbar × CHF 8 Netto-eCPM / 1’000 | CHF 14.40 |

Bei 10’000 entsprechend aktiven Werbe-Nutzern wären es CHF 48 bzw. CHF 144. Nicht alle MAU aktivieren Werbung; Gold sieht keine Werbung. Auslastung, Datenschutzwahl, Sessionzahl, Format und Land verändern den tatsächlichen Erlös. Deshalb Banner als Option, Gold als wichtigster Erlöshebel. Nichts in der Implementierung schickt Kaufbeträge oder Belegkategorien zur Werbezielgruppenauswahl.

## 1. Apple / RevenueCat konfigurieren

1. Im bestehenden Apple-App-Eintrag `com.heartbeat.piggy` eine Subscription Group verwenden. Bestehende Produkte und Kunden **nicht neu anlegen**, wenn sie bereits existieren.
2. Vorhandenes Monatsprodukt unter dem Verkaufsnamen **Piggy Gold** weiterführen. Falls noch keines existiert, beispielsweise `piggy_gold_monthly`, Laufzeit 1 Monat, Schweizer Zielpreis CHF 10.00. Produkt-ID ist ein Beispiel, kein bereits angelegtes Produkt.
3. Jahresprodukt beispielsweise `piggy_gold_annual`, Laufzeit 1 Jahr. Neuen Zielpreis noch festlegen; der frühere Vorschlag CHF 39.90 ist bei CHF 10 monatlich nicht bestätigt. Gleiche Subscription Group und Leistungsstufe, damit keine zwei parallelen Abos verkauft werden. Verfügbarkeit, Steuern und Preise je Land in Apple prüfen.
4. In RevenueCat beide Produkte an das **bestehende Entitlement `plus`** hängen. Den technischen Namen nicht in `gold` umbenennen: App, Webhook und Backend nutzen ihn bereits.
5. Offering `default`: `$rc_monthly`/Monatspaket und `$rc_annual`/Jahrespaket zuordnen. Existiert nur Monat, zeigt die App nur Monat. Existiert kein Angebot, bleibt Free nutzbar und ein erneuter Abruf ist möglich.
6. Den öffentlichen iOS-SDK-Key als `EXPO_PUBLIC_REVENUECAT_IOS_KEY` in der **K-Dev-/Worker-Buildumgebung** setzen. `eas.json` nutzt einen lokalen Buildpfad; eine nur in der lokalen Shell vorhandene Variable erreicht den Mac-Worker nicht automatisch.
7. Bestehenden `revenuecat-webhook` samt serverseitigem Authorization-Secret, Supabase-Migration/`user_entitlements` und sicherer Ereignisverarbeitung prüfen. In dieser Änderung kein Datenbankschema angepasst. Die kleine Textumbenennung in `_shared/entitlements/plus.ts` erfordert für identische Backendfehlermeldungen später einen Deploy von `scan-receipt`.
8. Apple-Small-Business-Teilnahme, Vereinbarungen, Steuer- und Bankangaben kontrollieren. Empfehlungen sind keine tatsächlich aktivierten Preise.

## 2. AdMob konfigurieren

1. Eigenes AdMob-Konto mit Zahlungsdaten verwenden; iOS-App mit richtigem Bundle und Storeeintrag verknüpfen. Benötigt werden eine **App-ID mit `~`** und Anzeigenblock-IDs mit **`/`**.
2. Einen Interstitial-Anzeigenblock anlegen. Keine Reward- oder App-Open-Einheit in den Interstitial-Slot setzen. AdMob-Frequenzbegrenzung zusätzlich passend zur App einstellen.
3. Optional einen eigenen Banner-Anzeigenblock anlegen. UI-Platz ist im Dashboardfluss, nicht über Scan-, Navigation- oder Eingabeflächen.
4. In „Privacy & messaging“ eine passende UMP-Nachricht für die vorgesehenen Regionen konfigurieren, Datenschutzadresse und Partner prüfen. Die App fordert nicht personalisierte Anzeigen an und fragt selbst keinen ATT-Zugriff an. Kein ATT-/IDFA-Dialog in AdMob aktivieren, ohne die entsprechende technische und rechtliche Prüfung. NPA bedeutet **nicht**, dass Google keine technischen Personendaten erhält.
5. `app-ads.txt` auf der von Google verlangten Entwicklerwebsite veröffentlichen, Publisher-Zeile aus dem eigenen Konto nehmen und App-Verifizierung/Anzeigenfreigabe kontrollieren. In diesem Auftrag keine Website geändert.
6. Für Geräte-/Entwicklungstests ausschliesslich Testanzeigen verwenden. Der Code verwendet bei `__DEV__` Google-Test-Anzeigenblöcke; TestFlight ist ein Release-Binary und benötigt explizit korrekt konfigurierte Testgeräte bzw. Testblöcke im internen Testprofil. Nie eigene echte Anzeigen anklicken.

### Build-Variablen

| Variable | Bedeutung |
|---|---|
| `EXPO_PUBLIC_REVENUECAT_IOS_KEY` | Öffentlicher iOS-SDK-Key von RevenueCat; lokal zum Prüfzeitpunkt nicht gesetzt |
| `EXPO_PUBLIC_ADS_ENABLED=true` | Rollout-Schalter, ohne ihn keinerlei Werbe-SDK-Initialisierung durch die App |
| `EXPO_PUBLIC_ADS_REVIEW_APPROVED=true` | Erst nach dokumentierter Prüfung setzen; kein Ersatz für die Prüfung |
| `EXPO_PUBLIC_ADMOB_IOS_APP_ID` | Eigene iOS-AdMob-App-ID; lokal nicht gesetzt |
| `EXPO_PUBLIC_ADMOB_IOS_INTERSTITIAL_ID` | Eigener Interstitial-Block; lokal nicht gesetzt |
| `EXPO_PUBLIC_ADMOB_BANNERS_ENABLED=true` | Optional Banner einschalten; Empfehlung für ersten Pilot: aus |
| `EXPO_PUBLIC_ADMOB_IOS_BANNER_ID` | Nur bei eingeschaltetem Banner erforderlich; lokal nicht gesetzt |

`app.config.js` prüft aktivierte Werbung auf Reviewflag, IDs und RevenueCat-Konfiguration. Produktionsprofile verweigern Google-Beispiel-IDs für aktive Monetarisierung. Bei deaktivierter Werbung wird eine gültige native Beispiel-App-ID mit verzögerter Messinitialisierung eingetragen, aber kein Werbe-SDK durch Piggy gestartet. Ein neues **natives Binary** ist nötig; OTA kann das neue native Werbemodul nicht hinzufügen. Expo Go unterstützt es nicht.

Gepinnt: `react-native-google-mobile-ads@15.8.3`, enthaltene iOS-SDKs Google Mobile Ads 12.11.0 / UMP 3.0.0. Die aktuellste Bibliotheksdoku verlangt eine neuere RN-Version als Piggy 0.81; deshalb versionierte Doku und installierte Typen verwendet. Der echte Mac-Build und die aktuelle Store-Akzeptanz dieser SDK-Version müssen vor Release bestätigt werden.

## 3. Datenschutz und Rechtstexte

Die technische Änderungsmeldung liegt unter `K-Adv/docs/aenderungen/2026-11-17-piggy-gold-admob.md`. Das vorhandene Playbook verlangt vor Build/Einreichung bei **neuem Werbeanbieter** und **Bezahlfunktionen** eine neue Prüfung. Produktdatei, Dienstleister, App-Store-Labels, SDK-Privacy-Manifeste, Nutzungsbedingungen und Rechte müssen zusammenpassen. Hier wurde weder die Produktdatei stillschweigend freigegeben noch `released` gesetzt.

Die vorhandenen App-Links in `src/lib/legalLinks.ts` sind weiterhin mit einem Platzhalter-TODO versehen. Öffentliche Erreichbarkeit konnte bei der Recherche nicht bestätigt werden. Vor Verkauf müssen gültige, veröffentlichte Datenschutz-/Terms-Seiten eingetragen und auf dem Gerät geöffnet werden. Nicht ungeprüft auf eine Entwurfsseite umstellen.

Werbeablauf: lokale Wahl → Google UMP → `canRequestAds` → nicht personalisierte Anzeigen. Bei Ablehnung bleibt Piggy nutzbar. Keine Verknüpfung von Werbeanfragen mit der Piggy-User-ID; lokale Frequenzdatei enthält nur Tag/Zähler/Zeit. Technische Daten, die das Google-SDK selbst erhebt, sind gesondert im Datenschutzhinweis offenzulegen.

## 4. Freigabe am Gerät vor Launch

- Frische Installation, Free, Werbung noch nicht aktiviert: keine Anzeige und kein Werbe-SDK-Aufruf durch Piggy.
- Consent für CH und EWR testen: erlauben, ablehnen, Netzwerkfehler, erneute Wahl im Profil. Keine Beleginhalte in Werbeanfragen.
- Drei neue Belege speichern: frühestens dritte Gelegenheit; Beleg existiert auch nach Werbefehler/Abbruch. Kein Interstitial in Kamera/Review, kein Doppelaufruf bei schnellem Tippen.
- Weitere Belege: 180-Sekunden-Abstand und Tagesgrenze; Neustart und Mitternachtswechsel. No-fill darf den Belegablauf nicht anhalten. Eine hängende erneute Aboprüfung überspringt Werbung nach spätestens 1,5 Sekunden.
- Monats-/Jahreskauf, Abbruch, ausstehende Zahlung, Restore, Kündigung, Ablauf und Erstattung. Vorhandene Plus-Kunden behalten Gold-Zugriff.
- Gold: kein Interstitial und kein Banner, auch nach Neustart, erneutem Login und Rückkehr aus den Apple-Abos. Goldlogo ändert sich nach Kauf/Restore. Bereits geöffnete native Werbung lässt sich bei einer extern eintreffenden Aboänderung nicht programmgesteuert schliessen; danach keine weiteren Anfragen.
- Kontowechsel und temporär nicht lesbarer Abostatus: keine Werbung auf Verdacht.
- VoiceOver, grosse Schrift, kleine iPhones: vollständige Preise/Laufzeiten, erreichbarer Schliessen-/Restore-Knopf, keine abgeschnittenen Jahrespreise.
- AdMob-Anzeigenfreigabe, app-ads.txt, echte URLs, Labels/Manifest und Review-Notizen prüfen.

Build, Signierung, Commit und Push nur über K-Dev-Handover; keine manuelle Ausführung. Production/TestFlight und Storeeinreichung wurden hier nicht gestartet.

## Nachweise

`npx tsc --noEmit` erfolgreich. Vollständige Jest-Suite: **266 Tests in 21 Suiten bestanden** zum Prüfzeitpunkt. Expo-Introspektion erfolgreich; native iOS-App-ID und `GADDelayAppMeasurementInit=true` bestätigt. SDK-Calls in Tests gemockt: kein Ersatz für Testkauf, reale Einwilligungsform oder physischen iPhone-Test.

Quellen (Abruf 17.11.2026): [versionierte SDK-Integration](https://github.com/invertase/react-native-google-mobile-ads/blob/v15.8.3/docs/index.mdx), [versionierter Consent](https://github.com/invertase/react-native-google-mobile-ads/blob/v15.8.3/docs/european-user-consent.mdx), [Google Interstitials](https://developers.google.com/admob/ios/interstitial), [RevenueCat Offerings](https://www.revenuecat.com/docs/getting-started/displaying-products), [RevenueCat Kundenstatus](https://www.revenuecat.com/docs/customers/customer-info).


## Erlös je Monatszahler bei CHF 10

Vereinfachte CH-Rechnung: Kundenpreis inklusive angenommener 8,1 % MWST, 15 % Storeprovision nach bestätigter Small-Business-Teilnahme. CHF 10 / 1.081 × 0.85 = CHF 7.863 vor RevenueCat. Solange RevenueCat kostenlos ist: rund CHF 7.86; nach 1 % auf Kundenbrutto als Planungsansatz: rund CHF 7.76. Mit angenommenen variablen CHF 0.34 pro Zahler-Monat: rund CHF 7.52 bzw. CHF 7.42 Deckungsbeitrag. Keine Erstattungen, Fixkosten, Kosten der Gratisnutzer, Betreiberzeit, Ertragsteuern oder separate Steuern auf Anbietergebühren enthalten. Tatsächliche Auszahlung und RevenueCat-Rechnung anhand der Abrechnungen abgleichen. Bei 30 % Storeprovision: rund CHF 6.48 vor bzw. CHF 6.38 nach dieser RevenueCat-Annahme.

Der ältere Businessplan/Finanzmodell-Snapshot unter `business/strategy-2026-11-17/` verwendet CHF 4.90 als damaliges Szenario und wurde durch diese Preisentscheidung nicht nachträglich umgeschrieben. Der effektive Preis wird weiterhin in App Store Connect gesetzt; in dieser Session kein Storepreis geändert.
