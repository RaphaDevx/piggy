# Piggy – Setup Anleitung

## 1. Anthropic API Key setzen (Supabase Secret)

Die Edge Function `process-receipt` braucht deinen Claude API Key als Supabase Secret.

**Option A: Supabase Dashboard**
1. Öffne https://supabase.com/dashboard/project/dsucrlaonnmpwopgidcj/functions
2. Klicke auf `process-receipt`
3. Gehe zu "Secrets" → "Add secret"
4. Name: `ANTHROPIC_API_KEY`
5. Wert: dein Anthropic API Key (aus console.anthropic.com)

**Option B: Supabase CLI**
```bash
supabase secrets set ANTHROPIC_API_KEY=<dein-anthropic-key> --project-ref dsucrlaonnmpwopgidcj
```

---

## 2. App starten (ohne Document Scanner)

Für erste Tests ohne nativen Build:

```bash
cd /home/raphael/Piggy
npx expo start
```

→ QR-Code mit Expo Go scannen (Scanner-Button nutzt dann Kamera/Bibliothek Fallback)

---

## 3. EAS Development Build (mit Document Scanner)

Für den echten Office-Lens-ähnlichen Scan:

```bash
# EAS CLI installieren (falls noch nicht vorhanden)
npm install -g eas-cli

# EAS Login
eas login

# Development Build für iPhone
cd /home/raphael/Piggy
eas build --profile development --platform ios
```

→ Build-Link öffnen → auf iPhone installieren → `expo start` → über URL verbinden

---

## 4. Sparschwein-Icon ersetzen

Ersetze die Placeholder-Assets in `/home/raphael/Piggy/assets/` mit echten Piggy-Icons:
- `icon.png` (1024×1024, für App Store)
- `splash-icon.png` (Splash Screen)
- `adaptive-icon.png` (Android, optional)

Tipp: Nutze https://icon.kitchen oder Figma mit dem Sparschwein-Emoji 🐷 auf goldenem Hintergrund (#FF9F0A).

---

## App-Architektur

```
🐷 Foto aufnehmen (Document Scanner / Kamera)
  ↓
📤 Bild → Supabase Edge Function
  ↓
🤖 Claude Vision API (claude-sonnet-4-6)
  ↓
📋 Geparste Quittung anzeigen & bearbeiten
  ↓
💾 Speichern in Supabase (receipts + receipt_items)
  ↓
📊 Dashboard mit Statistiken & Charts
```

## Supabase Tabellen

- `receipts` – Quittungs-Header (Datum, Geschäft, Betrag, Zahlungsmittel)
- `receipt_items` – Einzelartikel mit Tags
- Storage Bucket `receipt-images` – Quittungsfotos

## Farbschema

| Element         | Farbe    |
|-----------------|----------|
| Hintergrund     | #0A0A0F  |
| Karten          | #16161E  |
| Akzent (Gold)   | #FF9F0A  |
| Tab-Bar         | #0D0D14  |
