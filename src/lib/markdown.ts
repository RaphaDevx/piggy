import type { ParsedReceipt, ParsedReceiptItem } from '../types/receipt';

function fmt(amount: number, currency: string): string {
  return `${currency} ${amount.toFixed(2)}`;
}

function formatDate(dateStr: string | null): string {
  if (!dateStr) return 'Unbekannt';
  try {
    return new Date(dateStr).toLocaleDateString('de-CH', {
      day: '2-digit', month: '2-digit', year: 'numeric',
    });
  } catch {
    return dateStr;
  }
}

export function generateMarkdown(receipt: ParsedReceipt): string {
  const date = formatDate(receipt.date);
  const allTags = [...new Set(receipt.items.flatMap((i) => i.tags))];

  const rows = receipt.items.map((item: ParsedReceiptItem) => {
    const qty = item.quantity && item.quantity !== 1
      ? `${item.quantity}× ${item.unit || ''}`.trim()
      : item.unit || '1×';
    const price = fmt(item.total_price, receipt.currency);
    const tags = item.tags.join(', ');
    return `| ${item.name} | ${qty} | ${price} | ${tags} |`;
  }).join('\n');

  const paymentLine = receipt.payment_card
    ? `${receipt.payment_method} — ${receipt.payment_card}`
    : receipt.payment_method;

  return `# Quittung: ${receipt.store_name} — ${date}

## Zusammenfassung
- **Geschäft:** ${receipt.store_name} (${receipt.store_category})
- **Datum:** ${date}
- **Betrag:** ${fmt(receipt.total_amount, receipt.currency)}
- **Zahlungsmittel:** ${paymentLine}

## Tags dieser Quittung
${allTags.map((t) => `\`${t}\``).join(' · ')}

## Artikel

| Artikel | Menge | Preis | Tags |
|---------|-------|-------|------|
${rows}
`;
}
