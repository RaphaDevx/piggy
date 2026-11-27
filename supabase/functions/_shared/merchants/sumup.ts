/** Server-only read adapter. No OAuth tokens, merchant secrets or provider URLs belong in iOS. */
export interface SumUpReceipt {
  source: 'sumup'; externalKey: string; merchantCode: string; transactionID: string;
  amount: string; currency: string; raw: Record<string, unknown>;
}
export interface SumUpAccess { accessToken: string; merchantCode: string }
const object = (v: unknown): v is Record<string, unknown> => !!v && typeof v === 'object' && !Array.isArray(v);

export async function readSumUpReceipt(access: SumUpAccess, transaction: string, eventID?: number, fetcher: typeof fetch = fetch): Promise<SumUpReceipt> {
  if (!/^[A-Za-z0-9]{6,32}$/.test(access.merchantCode) || !/^[A-Za-z0-9-]{4,80}$/.test(transaction) || !access.accessToken || /[\r\n]/.test(access.accessToken)) throw new Error('Invalid SumUp access or receipt reference');
  if (eventID !== undefined && (!Number.isSafeInteger(eventID) || eventID <= 0)) throw new Error('Invalid transaction event');
  const url = new URL(`https://api.sumup.com/v1.1/receipts/${encodeURIComponent(transaction)}`);
  url.searchParams.set('mid', access.merchantCode);
  if (eventID !== undefined) url.searchParams.set('tx_event_id', String(eventID));
  const response = await fetcher(url, {method:'GET',headers:{Authorization:`Bearer ${access.accessToken}`,Accept:'application/json'},redirect:'error',signal:AbortSignal.timeout(15000)});
  if (response.status !== 200) { await response.body?.cancel(); throw new Error(`SumUp receipt unavailable (HTTP ${response.status})`); }
  if (!response.body) throw new Error('Missing SumUp receipt');
  const reader = response.body.getReader(); let size = 0; const chunks: Uint8Array[] = [];
  try {
    while (true) { const part = await reader.read(); if (part.done) break; size += part.value.byteLength; if (size > 1024*1024) throw new Error('SumUp receipt too large'); chunks.push(part.value); }
  } finally { await reader.cancel().catch(()=>{}); }
  const bytes = new Uint8Array(size);let offset=0;for(const part of chunks) { bytes.set(part,offset);offset+=part.length; }
  let raw: unknown;try { raw=JSON.parse(new TextDecoder().decode(bytes)); } catch { throw new Error('Invalid SumUp receipt JSON'); }
  if (!object(raw) || !object(raw.transaction_data)) throw new Error('Invalid SumUp receipt structure');
  const tx = raw.transaction_data;
  if (tx.merchant_code !== access.merchantCode) throw new Error('SumUp merchant mismatch');
  if (typeof tx.transaction_id !== 'string' || !/^[A-Za-z0-9-]{4,80}$/.test(tx.transaction_id)) throw new Error('Missing canonical transaction ID');
  const amount = typeof tx.amount === 'string' || typeof tx.amount === 'number' ? String(tx.amount) : '';
  if (!/^-?[0-9]+(?:\.[0-9]{1,2})?$/.test(amount) || typeof tx.currency !== 'string' || !/^[A-Z]{3}$/.test(tx.currency)) throw new Error('Unsupported receipt amount or currency');
  return {source:'sumup',externalKey:`sumup:${access.merchantCode}:${tx.transaction_id}:${eventID ?? 'sale'}`,merchantCode:access.merchantCode,transactionID:tx.transaction_id,amount,currency:tx.currency,raw};
}
