# Piggy — iPhone 17 Simulator: Vollständige Anleitung

## Überblick

Ziel: Nativer iOS Build → Xcode → iPhone 17 Simulator → App testen mit Dummy-Quittungen.

---

## Schritt 1 — Projekt auf MacBook synchronisieren

Im Terminal auf dem Linux-Rechner:

```bash
# Projekt zum MacBook syncen (rsync, schnell + inkrementell)
rsync -av --exclude=node_modules --exclude=ios --exclude=dist --exclude=.expo \
  /home/raphael/K-Dev/Projekte/Piggy/ \
  macbook:/Users/Builder/PiggyBuild/
```

---

## Schritt 2 — Auf MacBook: Abhängigkeiten installieren

```bash
ssh macbook
cd /Users/Builder/PiggyBuild

# Node-Umgebung laden
export NVM_DIR="$HOME/.nvm" && source "$NVM_DIR/nvm.sh"
export PATH="$HOME/bin:/opt/homebrew/bin:$PATH"

# npm-Pakete installieren (inkl. lokales piggy-foundation-models Modul)
npm install
```

---

## Schritt 3 — iOS-Projekt generieren (Prebuild)

Expo generiert aus dem JS-Projekt ein vollständiges natives Xcode-Projekt:

```bash
# ios/ Ordner neu generieren (--clean löscht alten Ordner zuerst)
npx expo prebuild --platform ios --clean
```

Danach CocoaPods-Abhängigkeiten installieren:

```bash
cd ios
pod install
cd ..
```

> Falls `pod install` fehlschlägt: `sudo gem install cocoapods` und nochmal.

---

## Schritt 4 — In Xcode öffnen

```bash
open ios/Piggy.xcworkspace
```

Xcode öffnet sich. **Wichtig:** Immer `.xcworkspace` öffnen, nie `.xcodeproj` (sonst fehlen CocoaPods).

---

## Schritt 5 — iPhone 17 Simulator auswählen

In der Xcode-Toolbar oben links:

1. Links neben dem ▶ Play-Button auf das Gerät-Dropdown klicken
2. **"iPhone 17 Pro"** auswählen (oder iPhone 17)
3. Falls nicht in der Liste: **Product → Destinations → Add Additional Simulators...**
   → iOS 19.x Runtime installieren → iPhone 17 Pro hinzufügen

---

## Schritt 6 — App bauen und starten

**`Cmd + R`** — Build & Run

Xcode kompiliert (~3–5 Min beim ersten Mal) und startet den Simulator automatisch.
Die Piggy App öffnet sich im iPhone 17 Simulator.

> Bei Signing-Fehler: **Signing & Capabilities** → Team auf "Personal Team" setzen
> (kostenloser Apple-Account reicht für Simulator).

---

## Schritt 7 — Apple Intelligence aktivieren (für Foundation Models)

Im Simulator:

1. **Settings → Apple Intelligence & Siri**
2. "Apple Intelligence" einschalten
3. Modell herunterladen (~1 GB, einmalig — braucht einen Moment)
4. Warten bis "Ready" angezeigt wird

Danach: In Piggy → Profil → Verarbeitung → **On-Device** auswählen.

---

## Schritt 8 — Dummy-Quittungen auf den Simulator laden

### Methode A: Drag & Drop (einfachste)

1. Quittungsfotos (JPG/PNG) im Finder auswählen
2. Direkt auf das Simulator-Fenster ziehen
3. iOS fragt: "Zu Fotos hinzufügen?" → **Bestätigen**
4. Fotos erscheinen in der Photos-App des Simulators

### Methode B: Terminal-Befehl (für viele Fotos auf einmal)

```bash
# Auf MacBook — einzelnes Foto
xcrun simctl addmedia booted /pfad/zum/foto.jpg

# Mehrere Fotos auf einmal
xcrun simctl addmedia booted ~/Desktop/quittungen/*.jpg
```

### Methode C: Safari im Simulator

1. Im Simulator Safari öffnen
2. Bild-URL öffnen (z.B. ein Quittungsfoto von einer Website)
3. Bild lang drücken → "Add to Photos"

---

## Schritt 9 — Quittung in Piggy scannen (aus Simulator-Fotos)

1. Piggy öffnen → Scan-Button (Kamera-Icon unten)
2. **"Foto auswählen"** statt Kamera (Kamera im Simulator nicht verfügbar)
3. Simulator-Photos öffnen sich → Dummy-Quittung auswählen
4. OCR + Gemini/On-Device verarbeitet das Foto
5. Review-Screen erscheint mit erkannten Daten

---

## Dummy-Quittungsfotos beschaffen

Für Tests eignen sich diese Quellen:
- **Eigene Fotos** auf MacBook via Drag & Drop in Simulator
- **GitHub Piggy Screenshots**: `/home/raphael/K-Dev/Projekte/Piggy/screenshots/`
- Google Images: "Migros Quittung", "Coop Kassenbon" (Beispielfotos)

Alternativ mit Terminal echte Testfotos aus dem Projekt:
```bash
# Auf MacBook
xcrun simctl addmedia booted /Users/Builder/PiggyBuild/screenshots/*.png
```

---

## Wichtige Xcode-Shortcuts

| Shortcut | Funktion |
|----------|----------|
| `Cmd + R` | Build & Run |
| `Cmd + .` | Stop |
| `Cmd + Shift + K` | Clean Build Folder |
| `Cmd + B` | Nur Build (ohne Run) |
| `Cmd + /` | Zeile auskommentieren |

---

## Häufige Fehler

**"No signing certificate"**
→ Xcode → Projekt → Signing & Capabilities → Team: persönlichen Apple Account eintragen

**"Simulator nicht verfügbar"**
→ Xcode → Settings → Platforms → iOS 19.x herunterladen

**`pod install` schlägt fehl**
```bash
sudo gem install cocoapods
cd ios && pod install --repo-update
```

**Foundation Models "not available" in App**
→ Simulator → Settings → Apple Intelligence & Siri → Modell noch nicht heruntergeladen

**App crasht beim Start**
→ Xcode Console (unten) lesen → häufig: fehlender `.env` Wert
→ Sicherstellen dass `EXPO_PUBLIC_SUPABASE_URL` gesetzt ist (via `app.json` extra → `ios.config`)
