# Piggy — Business Model

> Letzte Überarbeitung: 2026-05-19

---

## Unsere echten Kosten pro Nutzer

### Claude API (OCR + Extraktion)
- ~$0.02 pro Quittung (2 Rappen)
- 2 Quittungen/Tag → $0.04/Tag → **~$1.20/Monat**

### Supabase (Infrastruktur)
| Tier | Preis | DB | Storage | Edge Functions | Nutzer |
|------|-------|----|---------|----------------|--------|
| Free | $0 | 500 MB | 1 GB | 500K/Monat | bis ~50 aktive User |
| Pro | $25/Monat | 8 GB | 8 GB | 2M/Monat | bis ~500 aktive User |
| Team | $599/Monat | unbegrenzt | unbegrenzt | unbegrenzt | enterprise |

**Storage pro Nutzer:** ~2 MB/Bild × 60 Bilder/Monat = ~120 MB/Monat
→ Free Tier reicht für 1 Nutzer ~8 Monate
→ Pro Tier reicht für ~65 aktive Nutzer bequem

### Apple / Google (App Stores)
- Apple: 30% von jedem Kauf / Abo (15% ab Jahr 2 für Subscriptions)
- Google: 30% (15% für erste $1M Jahresumsatz)
- Apple Developer: $99/Jahr
- Google Play Developer: $25 einmalig

---

## Monetarisierungs-Modelle im Vergleich

### Modell A — Freemium + Subscription ⭐ (Empfehlung)

| Plan | Preis | Scans/Monat | Features |
|------|-------|-------------|---------|
| Free | $0 | 15 | Basis-Scan, Dashboard |
| Plus | $2.99/Monat | 100 | Alle Features, Export |
| Pro | $5.99/Monat | unbegrenzt | + Priorisierung, früher Zugang |

**Jahresabo-Rabatt:** -40% (Plus: $21.99/Jahr, Pro: $39.99/Jahr)

**Warum:**
- Gratis-Plan = virales Wachstum durch Mundpropaganda
- Klare Upgrade-Motivation (15 Scans ist bei aktivem Nutzer zu wenig)
- Vorhersehbare Revenue, skaliert mit Nutzerbasis
- Standard im App-Markt, Nutzer kennen das Modell

**Unit Economics (Plus, $2.99/Monat):**
- Nach Apple 30%: $2.09 netto
- Kosten API (100 Scans): ~$2.00
- Kosten Infra anteilig: ~$0.10
- **Marge: ~$0 auf Plus — fast breakeven**

→ Plus-Preis auf **$3.99/Monat** setzen für echte Marge:
- Netto nach Apple: $2.79
- Kosten: ~$2.10
- **Marge: ~$0.70/Monat/Nutzer** = $8.40/Jahr

→ Pro ($5.99/Monat, unbegrenzt, Durchschnitt 150 Scans):
- Netto nach Apple: $4.19
- Kosten: ~$3.00 (API) + $0.15 (Infra)
- **Marge: ~$1.04/Monat/Nutzer** = $12.50/Jahr

---

### Modell B — Credits / Pay-per-Scan

| Paket | Preis | Scans | Preis/Scan |
|-------|-------|-------|------------|
| Starter | $1.99 | 25 | $0.08 |
| Standard | $4.99 | 75 | $0.07 |
| Large | $8.99 | 200 | $0.045 |

**Warum gut:** Kein Abo-Risiko für Nutzer, einfach verständlich
**Warum schlecht:** Unvorhersehbare Revenue, Nutzer kaufen selten nach

---

### Modell C — Einmaliger Kauf (nicht empfohlen)

- App: $9.99 einmalig, unbegrenzt Scans
- Problem: API-Kosten laufen weiter, kein Revenue-Stream
- Nicht nachhaltig bei wachsender Nutzerbasis

---

### Modell D — B2B / Firmen (langfristig)

Für Kleinunternehmer, Freelancer, Treuhänder:
- Team-Plan: $19.99/Monat, 5 Nutzer, 500 Scans, MwSt-Export
- Hohe Zahlungsbereitschaft, wenig Preis-Sensitivität
- Erfordert Buchhaltungs-Features (MwSt, Kategorien nach SKR04)

---

## Empfohlene Strategie

**Phase 1 (Launch):** Free + $3.99/Monat Abo
- 15 Scans gratis (grosszügig genug zum Testen)
- 7-Tage Free Trial auf Plus automatisch bei Registrierung
- Kein Kreditkartenzwang für Free Trial

**Phase 2 (Wachstum):** Yearly Plans, Referral-Programm
- Freund einladen = beide bekommen 10 Extra-Scans

**Phase 3 (Skalierung):** B2B-Tier für Firmen

---

## RevenueCat (Empfohlen für Implementation)

RevenueCat abstrahiert Apple + Google In-App Purchases:
- Unified API für iOS + Android + Web
- Gratis bis $2500/Monat MRR
- Danach 1% des Revenue
- Analytics, Churn-Tracking, A/B Tests inklusive
- GitHub: revenuecat/purchases-expo (Expo-Plugin verfügbar)

```bash
npx expo install react-native-purchases
```

---

## Skalierungs-Szenarien

| Nutzer (MAU) | Aktive Abos | MRR (brutto) | Infra-Kosten | API-Kosten | Netto MRR |
|---|---|---|---|---|---|
| 100 | 10 (10%) | $40 | $0 (Free) | $12 | **$28** |
| 500 | 75 (15%) | $300 | $25 (Pro) | $90 | **$185** |
| 2.000 | 400 (20%) | $1.600 | $25 | $480 | **$1.095** |
| 10.000 | 2.500 (25%) | $10.000 | $150 | $3.000 | **$6.850** |
| 50.000 | 15.000 (30%) | $60.000 | $500 | $18.000 | **$41.500** |

*MAU = Monthly Active Users, Abo-Rate typisch 10-30% bei Nischen-Apps*
*API-Kosten = Annahme Ø 60 Scans/Monat/Nutzer*
