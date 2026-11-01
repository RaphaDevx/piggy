import {
  equalShares, fixedShares, isValidIban, formatIban, buildRequestText, buildSwissQrPayload, hasQrBillDetails,
  EMPTY_PAYMENT_DETAILS, type PaymentDetails,
} from './paymentRequest';

const details: PaymentDetails = {
  ...EMPTY_PAYMENT_DETAILS, name: 'Raphael Kaufmann', street: 'Musterweg 1', zip: '9000', town: 'St. Gallen',
  iban: 'CH93 0076 2011 6238 5295 7', hint: 'TWINT: 079 000 00 00',
};

describe('equalShares', () => {
  it('teilt 50/50 und lässt den Rest beim Zahler', () => {
    const s = equalShares(37.9, ['me', 'a'], 'me');
    expect(s.get('a')).toBe(18.95);
    expect(s.get('me')).toBeCloseTo(18.95, 2);
    const odd = equalShares(37.91, ['me', 'a'], 'me');
    expect(odd.get('a')).toBe(18.95);
    expect(odd.get('me')).toBeCloseTo(18.96, 2);
  });
  it('rundet Mitzahler auf 5 Rappen und summiert auf den Gesamtbetrag', () => {
    const s = equalShares(100, ['me', 'a', 'b'], 'me');
    expect(s.get('a')).toBe(33.35);
    expect(s.get('b')).toBe(33.35);
    expect([...s.values()].reduce((x, y) => x + y, 0)).toBeCloseTo(100, 2);
  });
});

describe('fixedShares', () => {
  it('Zahler trägt den Rest', () => {
    const s = fixedShares(50, 'me', new Map([['a', 12.5]]));
    expect(s.get('me')).toBe(37.5);
  });
  it('wird nie negativ', () => {
    expect(fixedShares(10, 'me', new Map([['a', 30]])).get('me')).toBe(0);
  });
});

describe('IBAN', () => {
  it('erkennt gültige und ungültige IBAN', () => {
    expect(isValidIban('CH93 0076 2011 6238 5295 7')).toBe(true);
    expect(isValidIban('CH93 0076 2011 6238 5295 8')).toBe(false);
    expect(isValidIban('DE89370400440532013000')).toBe(false);
  });
  it('formatiert in Vierergruppen', () => {
    expect(formatIban('ch9300762011623852957')).toBe('CH93 0076 2011 6238 5295 7');
  });
});

describe('Anforderungstext', () => {
  it('enthält Betrag, Hinweis und IBAN', () => {
    const t = buildRequestText({ store: 'Migros', date: '2026-10-24', amount: 18.4, currency: 'CHF', details });
    expect(t).toContain('Migros (2026-10-24): Dein Anteil ist CHF 18.40.');
    expect(t).toContain('TWINT: 079 000 00 00');
    expect(t).toContain('IBAN: CH93 0076 2011 6238 5295 7');
  });
  it('funktioniert ohne Zahlungsangaben', () => {
    expect(buildRequestText({ store: null, amount: 5, currency: 'CHF' })).toBe('Ausgabe: Dein Anteil ist CHF 5.00.');
  });
});

describe('Swiss QR', () => {
  it('hat die Felder der Version 0200 in der richtigen Reihenfolge', () => {
    const lines = buildSwissQrPayload(details, 18.4, 'CHF', 'Migros').split('\r\n');
    expect(lines.slice(0, 4)).toEqual(['SPC', '0200', '1', 'CH9300762011623852957']);
    expect(lines[4]).toBe('K');
    expect(lines[10]).toBe('CH');
    expect(lines[18]).toBe('18.40');
    expect(lines[19]).toBe('CHF');
    expect(lines[27]).toBe('NON');
    expect(lines[29]).toBe('Migros');
    expect(lines[30]).toBe('EPD');
    expect(lines).toHaveLength(31);
  });
  it('verlangt vollständige Angaben', () => {
    expect(hasQrBillDetails({ ...details, zip: '' })).toBe(false);
    expect(() => buildSwissQrPayload({ ...details, iban: 'x' }, 1, 'CHF', '')).toThrow();
  });
});
