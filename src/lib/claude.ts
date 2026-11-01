/**
 * claude.ts — Kompatibilitäts-Re-Export
 *
 * scan.tsx importiert weiterhin von hier — kein Import-Change nötig.
 */

export type { ProcessResult, CropRegion, ScanSource, LocalReason } from './ocr';
export { processReceiptImage, reparseWithAi, LOCAL_REASON_TEXT } from './ocr';
