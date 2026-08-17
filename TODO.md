# Piggy — Roadmap bis App Store & Google Play

> Letztes Update: 2026-07-08
> Status-Legende: ✅ Fertig · 🔄 In Arbeit · ⬜ Offen · 🚫 Blockiert · 🚀 Nativ-only (ab EAS Build)

---
## ✅ Erledigt (2026-06-14): Google-Verknüpfung + Kontoabgleich

### Google Account Linking
- `src/lib/googleAuth.ts`: gemeinsame OAuth-Logik (`runGoogleOAuth('signin' | 'link')`)
- Login bleibt unverändert (`signInWithGoogle` nutzt jetzt `runGoogleOAuth('signin')`)
- Profil → neue Sektion "Verbundene Konten": Google verknüpfen/trennen via `linkIdentity`/`unlinkIdentity`

**⚠️ Manuelle Setup-Schritte (nicht automatisierbar, kein passendes MCP-Tool):**
- [ ] Supabase Dashboard (Projekt `dsucrlaonnmpwopgidcj`) → Authentication → Providers →
      **Google aktivieren** (Client-ID/Secret eintragen — K-Learnings bestehenden
      Google-Cloud-Client `686577453994-...` wiederverwenden)
- [ ] Authentication → Settings → **"Allow manual linking of accounts"** aktivieren
      (Voraussetzung für `linkIdentity`/`unlinkIdentity`, sonst Fehler beim Verknüpfen)
- [ ] Google Cloud Console → diesen OAuth-Client → Authorized redirect URIs ergänzen:
      `https://dsucrlaonnmpwopgidcj.supabase.co/auth/v1/callback`
- App-Scheme `piggy://auth/callback` ist in `app.json` bereits vorhanden (nativer Redirect)

### Kontoabgleich (Doppelbuchungs-Check)
- Neue Tabellen `bank_statements`, `bank_transactions`, `receipt_matches` (RLS aktiv, Migration angewendet)
- Storage-Bucket `bank-statements` (privat, pro-User RLS)
- Edge Function `process-statement`: Claude-basiertes Parsing von PDF/CSV/CAMT.053
- `src/lib/matching.ts`: gewichtetes Matching (Betrag 50% / Datum 30% / Händler 20%,
  inkl. Kreditkarten-Verzug 0–45 Tage)
- `src/hooks/useBankStatements.ts` + `src/components/AbgleichView.tsx`: Upload, Liste,
  automatischer + manueller Abgleich, "Belege fehlen" / "Bankbeleg fehlt"
- Integration in Analyse-Tab via Segmented Control "Übersicht" / "Abgleich"
- Kategorie-Detailseite zeigt "✅ Bankbeleg" / "⚠️ Kein Bankbeleg" Badge pro Einkauf
- Quittungsdetail zeigt verknüpfte Bankbuchung (Datum, Betrag, Buchungstext)
- Rechtliche Einschätzung: `business/LEGAL_KONTOAUSZUEGE.md` (vor Live-Schaltung lesen!)

**Noch offen (siehe `business/LEGAL_KONTOAUSZUEGE.md` Checkliste):**
- [ ] Lösch-Funktion pro Kontoauszug (UI + Backend)
- [ ] IBAN/Kartennummern-Masking im Buchungstext
- [ ] Separater Consent-Screen vor erstem Statement-Upload
- [ ] AVV/DPA mit Anthropic prüfen (Zero/Limited-Retention)

---

## ✅ Erledigt (2026-07-07/08): Social Layer + Splitting + Quittungen teilen

### Social Layer
- `profiles` Tabelle mit auto-generiertem `PIG-XXXXXXXX` Code (Trigger bei Signup, Backfill bestehender User)
- `friendships` Tabelle (pending/accepted/declined, RLS)
- Profil-Tab: QR-Code anzeigen, Display-Name editieren, Code teilen
- Freunde hinzufügen: Code manuell eingeben oder QR-Code mit Kamera scannen (`expo-camera`)
- Freundesliste: Tabs "Freunde" / "Anfragen" (ein-/ausgehend), Annehmen/Ablehnen
- Realtime: Supabase-Channel bei neuer Anfrage → Toast-Benachrichtigung in App
- `useFriends`, `useProfile` Hooks; `Toast` Komponente

### Ausgaben teilen (Splitwise-Layer)
- `splits`, `split_participants`, `split_exclusions` Tabellen (RLS)
- Split-Screen (`/split/[receiptId]`): Freunde auswählen, Artikel exkludieren, Live-Berechnung
- Settlement: "Beglichen"-Button pro Person, Übersicht offen/bezahlt
- Dashboard: `OpenBalances` Komponente — "Du schuldest" / "Du bekommst" mit Settle-Button
- Personen-Icon in jeder Quittung → öffnet Split-Screen

### Quittungen für Freunde
- `profile_cards` Tabelle: Karten-Endziffern (letzte 4) pro Nutzer, für Freunde sichtbar
- `shared_receipts` Tabelle: Quittung senden/annehmen/ablehnen (RLS)
- Karten-Matching: `payment_card` Feld → letzte 4 extrahieren → Freunde-Karten prüfen
- `CardMatchBanner`: "Möglicherweise von [Name] (···· 5327)" + Senden-Button in Quittungsdetail
- Manuelles Senden: Freund aus Liste wählen, auch ohne Auto-Match
- `IncomingSharedReceipts` im Quittungen-Tab: Annehmen → Split-Screen öffnet sich
- Akzeptierte Empfänger können Quittung lesen + Split erstellen (RLS aktualisiert)
- Profil-Tab: "Meine Karten" Sektion — Karten hinzufügen/entfernen

---

## Offen — App-Qualität

- [ ] **Passwort-Reset** — "Passwort vergessen"-Link im Login-Screen
- [ ] **Duplikats-Check** — erkennt gleiche Quittung zweimal gescannt, bestes Bild behalten
- [ ] **KG-Preise Fix** — Gewichtsartikel (z. B. Budget Cheese 1.2kg): Menge=1 führt zu falschem Total → Cross-Check: alle Artikel müssen auf Gesamtsumme schliessen, sonst markieren
- [ ] **Zoom auf Quittungsfoto** — Pinch-to-zoom auf Originalbild im Detail-Screen
- [ ] **Serien-Scan** — 20–100 Dokumente hintereinander scannen (Stapel-Modus)
- [ ] **PDF-Export** — Quittung als PDF für Spesen / Steuern
- [ ] **VPN-freier Zugang** — App öffentlich erreichbar ohne VPN

---

## ✅ Fundament gelegt (2026-07-08): B2B / Firmen-Abrechnung

Tabellen existieren in Supabase (leer, vollständig mit RLS), kein UI nötig jetzt:
- `companies` — Firma (Name, Slug, Invite-Code auto-generiert)
- `company_members` — Mitglied + Rolle (admin / accountant / member)
- `expense_submissions` — Einreichung: Quittung → Firma, Status (pending/approved/rejected/reimbursed)
- `receipts.company_id` — nullable FK für Firmen-Kontext (backwards-compatible)

**Wenn B2B gebaut wird, braucht es:**
- [ ] "Add Company" Flow im Profil (erstellen + Invite-Code teilen)
- [ ] Beitreten per Invite-Code
- [ ] "Als Spesen einreichen" Button in Quittungsdetail → Kategorie + Absenden
- [ ] Company-Dashboard (Admin/Accountant): Einreichungen genehmigen/ablehnen
- [ ] PDF-Spesenblatt Export (mit MwSt-Ausweis)
- [ ] CSV-Export für Abacus / DATEV
- [ ] Abacus-API Integration (Prio: Schweiz)

---

## Offen — Native App (EAS Build)

- [ ] EAS Account einrichten (expo.dev)
- [ ] EAS Development Build (iOS): `eas build --profile development --platform ios`
- [ ] 🚀 **Dokument-Scanner** — `react-native-document-scanner-plugin` einbinden
  - Echtzeit-Kantenerkennung (iOS: VisionKit, Android: ML Kit)
  - Perspektivkorrektur + Winkel-Ausgleich (G-Sensor Integration)
  - Automatischer Zuschnitt → bessere OCR-Qualität
  - Aufwand: ~3–5h nach EAS Build Setup
- [ ] EAS Production Build → App Store / Google Play

---

## ✅ Erledigte Verbesserungen (2026-05-24)

### ✅ Kategorie-Detailseite
- Alle Kategorien im Dashboard klickbar (chevron-Pfeil)
- Tap auf Kategorie → eigene Seite mit allen Artikeln
- Zeigt: Gesamtausgaben, Artikel-Anzahl, gruppiert nach Einkauf
- Einkaufseintrag → Tap zur Quittungs-Detailseite
- Gilt für ALLE Kategorien (Diverses, Lebensmittel, Haushalt, etc.)
- Neue Dateien: `app/(tabs)/category/[group].tsx`, `src/hooks/useCategoryItems.ts`

### ✅ Dezimaleingabe & Währungswechsler
- `NumericInput`-Komponente: Komma als Dezimaltrennzeichen erlaubt (12,50 ✓)
- Zwischenzustände wie "12," bleiben im Display (kein Reset mehr)
- Währungswähler CHF / EUR / USD im Scan-Review hinzugefügt
- Fix gilt für Gesamtbetrag AND alle Artikel-Felder

### 📝 Notiz: Spezialisierte OCR-KI & Trainingsdaten
- Alternativen: Mindee, Google Document AI (Receipt Parser), Veryfi
- Claude bleibt besser für CH-Deutsch / Migros / Coop Quittungen
- Fine-Tuning: nicht bei Anthropic; via Google Vertex AI / Azure möglich
- Trainingsdaten verkaufen: DSGVO-Consent der Nutzer zwingend (ohne AGB-Klausel → rechtlich riskant)


---

# Plan
---

## Phase 1 — MVP funktionsfähig (Web)
*Ziel: Erste echte Quittung scannen und speichern*

| # | Task | Status | Notes |
|---|------|--------|-------|
| 1.1 | Supabase Backend (DB + Edge Function) | ✅ | Piggy-Projekt dsucrlaonnmpwopgidcj |
| 1.2 | Claude Vision OCR (process-receipt) | ✅ | claude-sonnet-4-6 |
| 1.3 | Auth (Login / Registrierung) | ✅ | |
| 1.4 | Web App Build & Deployment | ✅ | http://192.168.1.31:4000 via PM2 |
| 1.5 | Storage Bucket (Originalfotos) | ✅ | public, RLS nach user_id |
| 1.6 | Web-Kamera / File Upload | ✅ | `<input capture>` auf iOS Safari |
| 1.7 | Dashboard (Ausgaben, Charts, Tags) | ✅ | |
| 1.8 | **Ersten echten Scan testen** | ⬜ | → jetzt tun! |
| 1.9 | Fehlerbehandlung verbessern (Edge Cases) | ⬜ | Schlechtes Foto, kein JSON, Timeout |

---

## Phase 2 — Design & UX
*Ziel: App sieht professionell aus, fühlt sich nativ an*

| # | Task | Status | Notes |
|---|------|--------|-------|
| 2.1 | Design-System definieren (Farben, Fonts, Spacing) | ⬜ | Figma oder direkt im Code |
| 2.2 | Dashboard Redesign | ⬜ | Interaktiv mit Raphael erarbeiten |
| 2.3 | Scan-Flow UX verbessern | ⬜ | Preview, Loading States, Feedback |
| 2.4 | Quittungs-Detail Redesign | ⬜ | |
| 2.5 | Onboarding Screen (erster Start) | ⬜ | |
| 2.6 | App Icon (finales Design) | ⬜ | 1024×1024px |
| 2.7 | Splash Screen | ⬜ | |
| 2.8 | Dark Mode verfeinern | ⬜ | Bereits dark, aber polish nötig |
| 2.9 | Accessibility (Schriftgrössen, Kontrast) | ⬜ | App Store Anforderung |

---

## Phase 3 — Native App (EAS Build)
*Ziel: Echter Document Scanner auf iPhone*

| # | Task | Status | Notes |
|---|------|--------|-------|
| 3.1 | EAS Account einrichten (expo.dev) | ⬜ | |
| 3.2 | EAS Development Build (iOS) | ⬜ | `eas build --profile development --platform ios` |
| 3.3 | Document Scanner testen (react-native-document-scanner-plugin) | ⬜ | Office-Lens-ähnlicher Scan |
| 3.4 | expo-image-picker Kamera-Fix verifizieren | ⬜ | Permissions-Flow |
| 3.5 | Push Notifications (optional) | ⬜ | z.B. monatliche Ausgaben-Summary |
| 3.6 | EAS Production Build (iOS) | ⬜ | |

---

## Phase 4 — Business Model & Monetarisierung
*Ziel: Entscheiden wie die App Geld verdient*

| # | Task | Status | Notes |
|---|------|--------|-------|
| 4.1 | Business Model festlegen | ⬜ | Siehe business/BUSINESS_MODEL.md |
| 4.2 | RevenueCat integrieren (In-App Purchases) | ⬜ | Empfohlen für iOS + Android |
| 4.3 | Free Trial Logik implementieren | ⬜ | z.B. 20 Scans gratis |
| 4.4 | Usage Tracking (Scans zählen) | ⬜ | Supabase Tabelle `usage` |
| 4.5 | Paywall Screen | ⬜ | |
| 4.6 | Stripe für Web-Zahlung (optional) | ⬜ | Falls auch Web-Subscriptions |

---

## Phase 5 — App Store (iOS)
*Ziel: App im Apple App Store veröffentlichen*

| # | Task | Status | Notes |
|---|------|--------|-------|
| 5.1 | Apple Developer Account ($99/Jahr) | ⬜ | developer.apple.com |
| 5.2 | Privacy Policy erstellen | ⬜ | Pflicht, muss öffentlich erreichbar sein |
| 5.3 | Terms of Service | ⬜ | |
| 5.4 | App Store Listing schreiben | ⬜ | Name, Beschreibung, Keywords |
| 5.5 | Screenshots (6.7" + 6.1" iPhone) | ⬜ | Mindestens 3 pro Gerät |
| 5.6 | App Store Preview Video (optional) | ⬜ | |
| 5.7 | App Store Connect Setup | ⬜ | Bundle ID: com.heartbeat.piggy |
| 5.8 | TestFlight Beta (interne Tests) | ⬜ | |
| 5.9 | App Review Einreichen | ⬜ | 1–7 Tage Review-Zeit |

---

## Phase 6 — Google Play (Android)
*Ziel: App im Google Play Store veröffentlichen*

| # | Task | Status | Notes |
|---|------|--------|-------|
| 6.1 | Google Play Developer Account ($25 einmalig) | ⬜ | play.google.com/console |
| 6.2 | EAS Build für Android | ⬜ | `eas build --platform android` |
| 6.3 | Android-spezifische UI-Fixes | ⬜ | Navigation, Back-Button etc. |
| 6.4 | Google Play Listing | ⬜ | |
| 6.5 | Android Screenshots | ⬜ | |
| 6.6 | Internal Testing Track | ⬜ | |
| 6.7 | Production Release | ⬜ | |

---

## Phase 7 — Skalierung & Features
*Ziel: App wächst, neue Features*

| # | Task | Status | Notes |
|---|------|--------|-------|
| 7.1 | Export (CSV / PDF) | ⬜ | Für Steuern, Buchhaltung |
| 7.2 | Budgets & Limits pro Kategorie | ⬜ | |
| 7.3 | Mehrere Benutzer / Familie | ⬜ | |
| 7.4 | Wiederkehrende Ausgaben erkennen | ⬜ | |
| 7.5 | Bank-Integration (Open Banking) | ⬜ | CH: TWINT, Postfinance, UBS |
| 7.6 | Steuer-Export (Schweiz: MwSt-Auswertung) | ⬜ | |
| 7.7 | Web Dashboard (für Desktop) | 🔄 | Bereits als Web-App vorhanden |

---

## Kritischer Pfad (Minimalversion für Store)

```
1.8 Ersten Scan testen
  → 2.1–2.5 Design fertigstellen
    → 3.1–3.3 EAS Build
      → 4.1–4.4 Business Model + Paywall
        → 5.1–5.9 App Store
```

**Realistische Zeitschätzung:** 4–8 Wochen bei konsequenter Arbeit
