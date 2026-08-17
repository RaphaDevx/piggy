# Piggy — Design Direction

> Ziel: Clean, minimalistisch, aber verspielt und süss.
> Piggy = süsses Sparschwein-Maskottchen das bei Finanzen hilft.

---

## Persönlichkeit der App

- **Charakter:** Piggy ist freundlich, humorvoll, hilfsbereit
- **Ton:** "Dein persönlicher Finanz-Buddy" — nicht steril/banking
- **Metapher:** Sparschwein das dir sagt "Du hast diese Woche CHF 12 bei Fast Food gespart 🎉"
- **Später:** Chatbot-Interface ("Hey Piggy, wie viel hab ich für Bio ausgegeben?")

---

## Visuelles System

### Farbpalette

```
Hintergrund:  #FAFAF9  (warmes Off-White)
Cards:        #FFFFFF  (weiss, mit Schatten)
Text primär:  #1A1A1A
Text sekundär:#6B7280
Akzent 1:     #FF9F0A  (Piggy Gold / Amber — behalten)
Akzent 2:     #FFB8C6  (Piggy Pink — für Schwein-Elemente)
Erfolg:       #34C759
Warnung:      #FF9F0A
Fehler:       #FF3B30
```

*Alternativ Dark Mode (beides anbieten):*
```
Hintergrund:  #111111
Cards:        #1C1C1E  (iOS System Gray 6)
Akzente:      wie oben
```

### Typografie
- **Headlines:** SF Pro Rounded (iOS) / Nunito (Android/Web) — rund, freundlich
- **Body:** SF Pro / Inter
- **Zahlen:** SF Pro Mono / Fira Code — für Beträge, tabellarisch

### Formen
- Border Radius: 20–28px auf Cards (sehr rund)
- Buttons: 16px Radius, kräftig
- Icons: abgerundet, "Cute"-Stil (nicht kantiger Business-Look)

### Pig Mascot
- Einfaches, flaches Piggy-Illustration (wie Duolingo-Owl)
- Erscheint auf: Splash, leerer Zustand, Erfolgs-Animationen
- Emotionen: Happy (nach Scan), Staunend (grosser Betrag), Sparsam (Budget eingehalten)
- Tool: Figma + einfache SVG-Illustration oder Lottie-Animation

---

## Screen-by-Screen Plan

### Dashboard (höchste Priorität)
```
┌─────────────────────┐
│ Hallo Raphael 👋    │  ← persönliche Begrüssung
│ 🐷 Piggy            │
├─────────────────────┤
│   CHF 247.30        │  ← gross, zentriert
│   dieser Monat      │  ← klein, grau
│   ↑ 12% vs. letzter │  ← Trend-Indikator
├─────────────────────┤
│ [Woche][Monat][Jahr]│  ← Pill-Selector
├─────────────────────┤
│ 🛒 Lebensmittel  62%│  ← Kategorie-Bars
│ 🏠 Haushalt      21%│    mit Emoji + Farbe
│ ☕ Cafés          9% │
├─────────────────────┤
│ Letzte Quittungen   │
│ [Migros] [Coop] ... │
└─────────────────────┘
```

### Scan-Flow
- Grosser runder "🐷 Scannen" Button (floating, prominent)
- Kamera-View: Guide-Overlay mit Quittungs-Silhouette
- Processing: animiertes Piggy "isst" die Quittung 🐷✨
- Erfolg: Piggy-Animation + "Fertig gespeichert!"

### Quittungs-Detail
- Foto des Originals oben (falls vorhanden)
- Artikel-Liste mit sauberen Chips für Tags
- Betrag gross und klar

---

## Implementierungs-Reihenfolge

1. **Farben + Fonts** updaten (15 min)
2. **Dashboard** neu gestalten (2h)
3. **Scan-Button** verbessern (30 min)
4. **Empty States** mit Piggy-Charakter (1h)
5. **Animationen** (Lottie) — nach TestFlight

---

## Referenz-Apps für Inspiration

- **Duolingo** — Charakter-Persönlichkeit, Gamification
- **Revolut** — saubere Zahlen-Darstellung
- **Nubank** — Purple + freundlich + FinTech
- **Copilot** (US) — schönstes Budget-App Design

---

## Was NICHT

- Keine corporate Blau/Grau Töne
- Keine kantigen, harten UI-Elemente
- Keine überladenen Charts
- Keine "Buchhaltungs-App" Ästhetik
