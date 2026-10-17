import { supabase } from './supabase';
import type { ReceiptWithItems, ReceiptExtraFields } from '../types/receipt';
export { duplicatePairKey, isDuplicateCandidate, findDuplicateCandidates, findDuplicatePairs } from '../../supabase/functions/_shared/finance-core/receipt-duplicates.ts';
export type { DismissedDuplicatePair } from '../../supabase/functions/_shared/finance-core/receipt-duplicates.ts';
import { findDuplicateCandidates } from '../../supabase/functions/_shared/finance-core/receipt-duplicates.ts';
import type { DuplicateCandidate, DismissedDuplicatePair } from '../../supabase/functions/_shared/finance-core/receipt-duplicates.ts';

export const MERGE_FIELDS = ['receipt_date', 'store_name', 'store_category', 'total_amount', 'currency', 'payment_method', 'payment_card', 'notes', 'raw_ocr_text', 'markdown_content'] as const;
export type MergeField = typeof MERGE_FIELDS[number];
export type MergeChoices = { fields?: Partial<Record<MergeField, 'target' | 'source'>>; image?: 'target' | 'source'; items?: 'target' | 'source'; extras?: Partial<Record<keyof ReceiptExtraFields, 'target' | 'source'>> };
export type DuplicateReceipt = ReceiptWithItems & {
  receipt_matches?: Array<{ transaction_id: string; match_type: string }>;
  splits?: Array<{ id: string }>;
};
export type IncomingReceiptScan = Omit<ReceiptWithItems, 'id' | 'user_id' | 'created_at'> & { id?: string; user_id?: string; created_at?: string };
const missing = (value: unknown) => value == null || value === '';
/** Matches server defaults: preserve conflicts, fill missing values, never guess image quality. */
export function buildMergePreview(target: ReceiptWithItems, source: IncomingReceiptScan, choices: MergeChoices = {}): ReceiptWithItems {
  const result = { ...target };
  for (const field of MERGE_FIELDS) {
    if (choices.fields?.[field] === 'source' || (choices.fields?.[field] == null && missing(target[field]))) {
      Object.assign(result, { [field]: source[field] });
    }
  }
  const extra = { ...target.extra_fields } as Record<string, string | null>;
  for (const [key, value] of Object.entries(source.extra_fields ?? {})) if (choices.extras?.[key as keyof ReceiptExtraFields] === 'source' || (choices.extras?.[key as keyof ReceiptExtraFields] == null && missing(extra[key]))) extra[key] = value;
  result.extra_fields = Object.keys(extra).length ? extra as unknown as ReceiptExtraFields : null;
  if (choices.image === 'source' || (!choices.image && !target.image_url)) {
    result.image_url = source.image_url;
    result.original_image_url = source.original_image_url;
  }
  const useSourceItems = choices.items === 'source' || (!choices.items && !target.receipt_items.length);
  const normalItems = (useSourceItems ? source : target).receipt_items.filter(item => !item.is_adjustment);
  const adjustments = [...target.receipt_items.filter(item => item.is_adjustment), ...source.receipt_items.filter(item => item.is_adjustment)];
  result.receipt_items = [...normalItems, ...adjustments].map(item => ({ ...item, receipt_id: target.id }));
  return result;
}

async function signedInUserId(): Promise<string> {
  const { data, error } = await supabase.auth.getUser();
  if (error) throw new Error(error.message);
  if (!data.user) throw new Error('Bitte zuerst anmelden.');
  return data.user.id;
}
export async function loadDuplicateReceipts(): Promise<DuplicateReceipt[]> {
  const uid = await signedInUserId();
  const receipts: DuplicateReceipt[] = [];
  const pageSize = 500;
  for (let offset = 0; ; offset += pageSize) {
    const { data, error } = await supabase.from('receipts')
      .select('*,receipt_items(*),receipt_matches(transaction_id,match_type),splits(id)')
      .eq('user_id', uid).order('id').range(offset, offset + pageSize - 1);
    if (error) throw new Error(error.message);
    receipts.push(...(data ?? []) as DuplicateReceipt[]);
    if (!data || data.length < pageSize) return receipts;
  }
}
/** Scan path queries only the matching day/currency/cents, never the whole archive. */
export async function loadDuplicateCandidates(incoming: DuplicateCandidate): Promise<DuplicateReceipt[]> {
  if (!incoming.receipt_date || incoming.total_amount == null || !Number.isFinite(incoming.total_amount) || !incoming.store_name.trim() || !incoming.currency) return [];
  const uid = await signedInUserId();
  const candidates: DuplicateReceipt[] = [];
  const cents = Math.round(incoming.total_amount * 100);
  for (let offset = 0; ; offset += 500) {
    const { data, error } = await supabase.from('receipts')
      .select('*,receipt_items(*),receipt_matches(transaction_id,match_type),splits(id)')
      .eq('user_id', uid).eq('receipt_date', incoming.receipt_date.slice(0, 10))
      .eq('currency', incoming.currency.toUpperCase())
      .gte('total_amount', (cents - 0.5) / 100).lt('total_amount', (cents + 0.5) / 100)
      .order('id').range(offset, offset + 499);
    if (error) throw new Error(error.message);
    candidates.push(...(data ?? []) as DuplicateReceipt[]);
    if (!data || data.length < 500) return findDuplicateCandidates(incoming, candidates);
  }
}
export async function fetchDismissedDuplicatePairs(): Promise<DismissedDuplicatePair[]> {
  const uid = await signedInUserId();
  const pairs: DismissedDuplicatePair[] = [];
  for (let offset = 0; ; offset += 500) {
    const { data, error } = await supabase.from('receipt_duplicate_decisions').select('receipt_a,receipt_b')
      .eq('user_id', uid).order('receipt_a').order('receipt_b').range(offset, offset + 499);
    if (error) throw new Error(error.message);
    pairs.push(...(data ?? []));
    if (!data || data.length < 500) return pairs;
  }
}
export async function keepReceiptsSeparate(a: string, b: string): Promise<void> {
  const { error } = await supabase.rpc('keep_receipts_separate', { p_receipt_a: a, p_receipt_b: b });
  if (error) throw new Error(error.message);
}
export async function mergeReceiptDuplicates(targetId: string, sourceId: string, choices: MergeChoices = {}): Promise<string> {
  const { data, error } = await supabase.rpc('merge_receipt_duplicates', { p_target_id: targetId, p_source_id: sourceId, p_choices: choices });
  if (error) throw new Error(error.message);
  return data as string;
}
export async function mergeReceiptScan(targetId: string, scan: IncomingReceiptScan, choices: MergeChoices = {}): Promise<string> {
  // Send a bounded data object; IDs, ownership and relationships can never be supplied by a scan.
  const { data, error } = await supabase.rpc('merge_receipt_scan', {
    p_target_id: targetId,
    p_scan: Object.fromEntries(([...MERGE_FIELDS, 'image_url', 'original_image_url', 'extra_fields', 'receipt_items'] as const).map(key => [key, scan[key]])),
    p_choices: choices,
  });
  if (error) throw new Error(error.message);
  return data as string;
}
/** Atomic distinct receipt + keep-separate decisions: safe to retry after a rejected transaction. */
export async function saveSeparateReceiptScan(scan: IncomingReceiptScan, separateIds: string[]): Promise<string> {
  const { data, error } = await supabase.rpc('save_separate_receipt_scan', {
    p_scan: Object.fromEntries(([...MERGE_FIELDS, 'image_url', 'original_image_url', 'extra_fields', 'receipt_items'] as const).map(key => [key, scan[key]])),
    p_separate_ids: separateIds,
  });
  if (error) throw new Error(error.message);
  return data as string;
}
export async function deleteReviewedReceipt(id: string): Promise<void> {
  const { error } = await supabase.rpc('delete_reviewed_receipt', { p_receipt_id: id });
  if (error) throw new Error(error.message);
}
