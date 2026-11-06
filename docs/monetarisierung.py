#!/usr/bin/env python3
"""Piggy: Ertragsmodell (Jahr, CHF). Alle Annahmen stehen in SZEN und sind Schätzungen, keine Messwerte.
Aufruf: python3 docs/monetarisierung.py [--md]"""
import sys

MWST = 0.081
KANAL = {  # Anteil der aktiven Nutzer je Kanal; Android erst nach Release
    'ios_web':       {'ios': 0.75, 'android': 0.00, 'web': 0.25},
    'ios_and_web':   {'ios': 0.60, 'android': 0.20, 'web': 0.20},
}
GEB = {'ios': 0.15, 'android': 0.15}          # Small Business Program bzw. Play-Abo; Web: Stripe unten
STRIPE_PCT, STRIPE_FIX = 0.0325, 0.30          # Auslandskarte als vorsichtiger Wert
SZEN = {
    #            Abo-Quote  Preis/Jahr  Affiliate CHF/MAU  Opt-in B (nur Web)  Lizenz/Käufer  KI-Kosten/MAU
    'vorsichtig': dict(conv=0.015, preis=29, aff=0.10, optin=0.08, lizenz=3000, ki=0.30),
    'basis':      dict(conv=0.025, preis=39, aff=0.40, optin=0.15, lizenz=6000, ki=0.20),
    'optimistisch': dict(conv=0.040, preis=49, aff=1.20, optin=0.25, lizenz=12000, ki=0.15),
}
FIX = 25 * 12          # Supabase u. ä. pro Jahr
VAR = 0.05             # Infrastruktur pro MAU

def kaeufer(beitragende):
    # Mindestgrundgesamtheit 1000 (Dossier C-01); danach wenige Käufer, grob geschätzt
    return 0 if beitragende < 1000 else 1 if beitragende < 5000 else 3 if beitragende < 20000 else 6

def rechne(mau, szen, mix, apple_ok=False):
    s, k = SZEN[szen], KANAL[mix]
    zahler = {c: mau * a * s['conv'] for c, a in k.items()}
    netto_preis = s['preis'] / (1 + MWST)
    abo = {}
    for c, z in zahler.items():
        if c == 'web':
            abo[c] = z * (netto_preis * (1 - STRIPE_PCT) - STRIPE_FIX)
        else:
            abo[c] = z * netto_preis * (1 - GEB[c])
    aff = mau * s['aff']
    web_nutzer = mau * (1.0 if apple_ok else k['web'])
    beitr = web_nutzer * s['optin']
    daten = kaeufer(beitr) * s['lizenz']
    kosten = FIX + mau * (VAR + s['ki'])
    erlös = sum(abo.values()) + aff + daten
    return dict(abo=sum(abo.values()), abo_ios=abo['ios'], abo_and=abo['android'], abo_web=abo['web'],
                aff=aff, daten=daten, beitragende=beitr, kosten=kosten, erlös=erlös, gewinn=erlös - kosten,
                zahler=sum(zahler.values()))

def tabelle(mix, apple_ok=False):
    rows = []
    for szen in SZEN:
        for mau in (1000, 5000, 20000, 100000):
            r = rechne(mau, szen, mix, apple_ok)
            rows.append((szen, mau, r))
    return rows

if __name__ == '__main__':
    for mix in KANAL:
        for ap in (False, True):
            print(f"\n== Kanäle: {mix} | Daten aus iOS erlaubt (Apple stimmt zu): {ap}")
            print(f"{'Szenario':13}{'MAU':>8}{'Zahler':>8}{'Abo':>9}{'Affil.':>8}{'Daten':>8}{'Kosten':>9}{'Gewinn':>9}")
            for szen, mau, r in tabelle(mix, ap):
                print(f"{szen:13}{mau:>8}{r['zahler']:>8.0f}{r['abo']:>9.0f}{r['aff']:>8.0f}{r['daten']:>8.0f}{r['kosten']:>9.0f}{r['gewinn']:>9.0f}")
