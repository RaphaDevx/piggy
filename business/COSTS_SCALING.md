# Piggy — Kosten & Skalierung im Detail

---

## Supabase — Limits & Preise

### Free Tier (solange kein Zahler)
| Resource | Limit | Reicht für... |
|----------|-------|---------------|
| Datenbank | 500 MB | ~1M Quittungen |
| Storage | **1 GB** | ~500 Originalfotos (à 2MB) |
| Edge Functions | 500.000/Monat | ~500K Scans/Monat |
| Bandbreite | 5 GB/Monat | reicht lange |
| Auth | unbegrenzt | ✅ |
| Aktive Projekte | 2 | ✅ |

**Kritisch:** Storage ist der erste Engpass.
- Fotos: Ø 2 MB/Bild (JPEG, qual. 0.9 vom iPhone)
- 1 GB Free = **~500 Fotos** → reicht für 1 Nutzer ~250 Tage
- Lösung: Bilder vor Upload komprimieren auf 800px, ~200KB → dann 5.000 Fotos gratis

### Supabase Pro ($25/Monat)
| Resource | Limit |
|----------|-------|
| Datenbank | 8 GB |
| Storage | **8 GB** (dann $0.021/GB) |
| Edge Functions | 2M/Monat |
| Bandbreite | 250 GB/Monat |

8 GB Storage = ~4.000 Fotos à 2MB oder ~40.000 bei Komprimierung
→ Pro Tier reicht für **200–2.000 Nutzer** bequem

### Break-Even: Wann lohnt sich Pro?
Ab ~100 aktiven Nutzern (je nach Scan-Frequenz) macht Pro Sinn.

---

## Bild-Komprimierung — Quick Win

Aktuell: `quality: 0.9` in expo-image-picker → ~2-4 MB pro Foto

Besser: Vor Upload auf 1200px skalieren und quality 0.7 → ~200-400 KB
- 5× weniger Storage-Kosten
- Kaum sichtbarer Qualitätsverlust für OCR
- Claude verarbeitet kleinere Bilder schneller

**TODO:** `expo-image-manipulator` integrieren

---

## Claude API — Kosten im Detail

### Aktuelles Modell: claude-sonnet-4-6
| | Preis |
|---|---|
| Input | $3.00 / 1M Tokens |
| Output | $15.00 / 1M Tokens |

### Tokens pro Scan
- Prompt-Text: ~500 Tokens
- Bild (1200px, nach Claude-Resize): ~1.500–2.000 Tokens
- Output JSON: ~600–900 Tokens
- **Total Input: ~2.500 Tokens = $0.0075**
- **Total Output: ~750 Tokens = $0.011**
- **Total: ~$0.019 ≈ 2 Rappen pro Scan**

### Alternative: claude-haiku-4-5 (10× günstiger)
| | Preis |
|---|---|
| Input | $0.80 / 1M Tokens |
| Output | $4.00 / 1M Tokens |
| **Kosten/Scan** | **~$0.003 (0.3 Rappen)** |

Haiku-Qualität bei Quittungen: gut für klare Fotos, schlechter bei unleserlichen.
Strategie: Haiku als Default, Sonnet als Fallback bei Fehler.

---

## Lokales OCR auf iPhone — Analyse

### Option A: Apple Vision Framework (kostenlos, offline)
- Integriert in iOS (kein Download nötig)
- Liefert: rohen OCR-Text ("Migros 1.90 Vollmilch 2x")
- **Strukturierung** (JSON mit Feldern) braucht noch LLM
- Kombination: Apple Vision OCR → günstiger Haiku-Call (nur Text, kein Bild)
- **Einsparung: ~70% API-Kosten** (kein Bild-Upload, kleinerer Kontext)

```
[Foto] → [Apple VisionKit OCR] → [Text] → [Claude Haiku] → [JSON]
Kosten: $0 + ~$0.0005 = 0.05 Rappen pro Scan
```

### Option B: Vollständig lokal (kein API)
- Small Vision Model auf-Gerät: z.B. moondream (1.8B) via Core ML
- Qualität: deutlich schlechter als Claude
- Kein Internet nötig
- Realistische Umsetzung: 3–6 Monate Entwicklungsaufwand
- **Empfehlung: Nicht für MVP, evtl. als Premium-"Offline-Mode"**

### Empfehlung für Piggy
**Kurzfristig:** Claude Sonnet (aktuell, beste Qualität)
**Mittelfristig:** Apple VisionKit + Claude Haiku → 10× billiger
**Langfristig:** Apple VisionKit + lokales Haiku-äquivalent

---

## App Store Gebühren

### Apple
- Developer Account: **$99/Jahr** (Pflicht für alle iOS-Apps)
- Umsatzbeteiligung: **30%** (15% für Subscriptions nach Jahr 1)
- Ausnahme: Small Business Program (< $1M/Jahr) → **15% sofort**

### Google Play
- Developer Account: **$25 einmalig**
- Umsatzbeteiligung: **30%** (15% für erste $1M Jahresumsatz)

### Effective Revenue nach Store-Gebühren
| Bruttopreis | Nach Apple (30%) | Nach Apple (15% SBP) |
|---|---|---|
| $1.99 | $1.39 | $1.69 |
| $3.99 | $2.79 | $3.39 |
| $5.99 | $4.19 | $5.09 |
| $9.99 | $6.99 | $8.49 |

---

## Schweizer Steueraspekte

- MwSt auf digitale Services: 8.1% (Normalsatz CH)
- Registrierungspflicht: ab CHF 100'000 Jahresumsatz
- Bis dahin: keine MwSt-Pflicht → App-Preise = Netto-Einnahmen
- Einkünfte aus App Store: als Einzelperson → Einkommenssteuer

---

## Infrastruktur-Stack bei Skalierung

| Nutzer | Supabase Plan | Server | Monatliche Infra-Kosten |
|--------|--------------|--------|------------------------|
| 0–100 | Free | Heimserver (Piggy läuft dort) | $0 |
| 100–500 | Pro ($25) | Heimserver oder Vercel | $25 |
| 500–5.000 | Pro | Cloud (Vercel/Fly.io) | $25–$100 |
| 5.000+ | Team ($599) | Cloud + CDN | $600–$2.000 |
