/**
 * paymentRequest.ts — Geld von Freunden anfordern (ohne dass Piggy Geld berührt).
 *
 * Reine Funktionen: Aufteilung (50/50 mit 5-Rappen-Rundung, fester Betrag), Anforderungstext
 * und Swiss-QR-Rechnung (SIX Swiss Implementation Guidelines QR-bill, Version 0200).
 * Vor dem Öffnen für andere Nutzer die Nutzlast gegen die aktuelle SIX-Spezifikation prüfen.
 */

export interface PaymentDetails {
  name: string;
  street: string;
  zip: string;
  town: string;
  iban: string;
  /** Freitext, z. B. „TWINT: 079 123 45 67“ — wird an den Anforderungstext gehängt. */
  hint: string;
}

export const EMPTY_PAYMENT_DETAILS: PaymentDetails = { name: '', street: '', zip: '', town: '', iban: '', hint: '' };

const cents = (n: number) => Math.round(n * 100) / 100;
const roundTo5 = (n: number) => Math.round(n * 20) / 20;

/** Jeder Mitzahler zahlt seinen Anteil auf 5 Rappen gerundet, der Rest bleibt beim Zahler. */
export function equalShares(total: number, participantIds: string[], payerId: string): Map<string, number> {
  const shares = new Map<string, number>();
  const others = participantIds.filter(id => id !== payerId);
  const each = participantIds.length > 0 ? roundTo5(total / participantIds.length) : 0;
  others.forEach(id => shares.set(id, each));
  shares.set(payerId, Math.max(0, cents(total - each * others.length)));
  return shares;
}

/** Feste Beträge je Freund; der Zahler trägt den Rest (nie negativ). */
export function fixedShares(total: number, payerId: string, fixed: Map<string, number>): Map<string, number> {
  const shares = new Map<string, number>();
  let sum = 0;
  fixed.forEach((amount, id) => {
    if (id === payerId) return;
    const value = Math.max(0, cents(amount));
    shares.set(id, value);
    sum += value;
  });
  shares.set(payerId, Math.max(0, cents(total - sum)));
  return shares;
}

export function normalizeIban(iban: string): string {
  return iban.replace(/\s+/g, '').toUpperCase();
}

export function formatIban(iban: string): string {
  return normalizeIban(iban).replace(/(.{4})/g, '$1 ').trim();
}

/** Prüft Länge, Länderpräfix (CH/LI) und Prüfziffer (mod 97). */
export function isValidIban(raw: string): boolean {
  const iban = normalizeIban(raw);
  if (!/^(CH|LI)\d{2}[0-9A-Z]{17}$/.test(iban)) return false;
  const rearranged = iban.slice(4) + iban.slice(0, 4);
  let remainder = 0;
  for (const ch of rearranged) {
    const digits = /\d/.test(ch) ? ch : String(ch.charCodeAt(0) - 55);
    for (const d of digits) remainder = (remainder * 10 + Number(d)) % 97;
  }
  return remainder === 1;
}

export function hasQrBillDetails(d: PaymentDetails): boolean {
  return isValidIban(d.iban) && !!d.name.trim() && !!d.zip.trim() && !!d.town.trim();
}

const oneLine = (s: string, max: number) => s.replace(/[\r\n]+/g, ' ').trim().slice(0, max);

/** Lesbarer Text für Nachrichten, auch für Empfänger ohne Piggy. */
export function buildRequestText(opts: {
  store: string | null | undefined;
  date?: string | null;
  amount: number;
  currency: string;
  note?: string | null;
  details?: PaymentDetails | null;
}): string {
  const { store, date, amount, currency, note, details } = opts;
  const what = [store?.trim() || 'Ausgabe', date ? `(${date})` : ''].filter(Boolean).join(' ');
  const lines = [`${what}: Dein Anteil ist ${currency} ${amount.toFixed(2)}.`];
  if (note?.trim()) lines.push(note.trim());
  if (details?.hint.trim()) lines.push(details.hint.trim());
  if (details && isValidIban(details.iban)) lines.push(`IBAN: ${formatIban(details.iban)}${details.name.trim() ? ` (${details.name.trim()})` : ''}`);
  return lines.join('\n');
}

/**
 * Nutzlast des Swiss QR Code: Zahlung ohne Referenz („NON“) auf eine normale IBAN.
 * Der Schuldner bleibt leer, so kann er den Betrag in seinem E-Banking selbst bestätigen.
 */
export function buildSwissQrPayload(d: PaymentDetails, amount: number, currency: 'CHF' | 'EUR', message: string): string {
  if (!hasQrBillDetails(d)) throw new Error('Zahlungsangaben unvollständig.');
  const iban = normalizeIban(d.iban);
  const country = iban.slice(0, 2);
  const fields = [
    'SPC', '0200', '1',
    iban,
    'K', oneLine(d.name, 70), oneLine(`${d.street}`, 70), oneLine(`${d.zip} ${d.town}`, 70), '', '', country,
    '', '', '', '', '', '', '',
    amount > 0 ? amount.toFixed(2) : '',
    currency,
    '', '', '', '', '', '', '',
    'NON', '',
    oneLine(message, 140),
    'EPD',
  ];
  return fields.join('\r\n');
}
