/** Duplicate detection is a review suggestion only; never an automatic merge. */
export interface DuplicateCandidate {
  id?: string;
  user_id?: string;
  receipt_date: string | null;
  store_name: string;
  total_amount: number | null;
  currency: string;
}
export interface DismissedDuplicatePair { receipt_a: string; receipt_b: string }
export function duplicatePairKey(a: string, b: string): string { return [a, b].sort().join(':'); }
function merchant(value: string): string {
  return value.normalize('NFKD').replace(/[\u0300-\u036f]/g, '').toLocaleLowerCase('de').replace(/[^\p{L}\p{N}]/gu, '');
}
export function isDuplicateCandidate(a: DuplicateCandidate, b: DuplicateCandidate): boolean {
  return a.id !== b.id || !a.id
    ? Boolean(a.receipt_date && b.receipt_date && a.receipt_date.slice(0, 10) === b.receipt_date.slice(0, 10)
      && a.total_amount != null && b.total_amount != null && Number.isFinite(a.total_amount) && Number.isFinite(b.total_amount)
      && Math.round(a.total_amount * 100) === Math.round(b.total_amount * 100)
      && a.currency.toUpperCase() === b.currency.toUpperCase()
      && merchant(a.store_name) && merchant(a.store_name) === merchant(b.store_name)
      && (!a.user_id || !b.user_id || a.user_id === b.user_id))
    : false;
}
export function findDuplicateCandidates<T extends DuplicateCandidate>(incoming: DuplicateCandidate, receipts: T[]): T[] {
  return receipts.filter(receipt => isDuplicateCandidate(incoming, receipt));
}
export function findDuplicatePairs<T extends DuplicateCandidate & { id: string }>(receipts: T[], dismissed: DismissedDuplicatePair[] = []): Array<{ target: T; source: T }> {
  const ignored = new Set(dismissed.map(pair => duplicatePairKey(pair.receipt_a, pair.receipt_b)));
  // Bucket by day/amount/currency/merchant, so a large receipt archive is not O(n²).
  const buckets = new Map<string, T[]>();
  const pairs: Array<{ target: T; source: T }> = [];
  for (const receipt of receipts) {
    if (!receipt.receipt_date || receipt.total_amount == null || !Number.isFinite(receipt.total_amount) || !merchant(receipt.store_name)) continue;
    const key = [receipt.receipt_date.slice(0, 10), Math.round(receipt.total_amount * 100), receipt.currency.toUpperCase(), merchant(receipt.store_name), receipt.user_id ?? ''].join('|');
    const bucket = buckets.get(key) ?? [];
    for (const target of bucket) {
      if (target.id !== receipt.id && !ignored.has(duplicatePairKey(target.id, receipt.id))) pairs.push({ target, source: receipt });
    }
    bucket.push(receipt);
    buckets.set(key, bucket);
  }
  return pairs;
}
