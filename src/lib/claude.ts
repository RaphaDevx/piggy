/**
 * claude.ts — Kompatibilitäts-Re-Export
 *
 * Früher: Claude Vision API via Supabase Edge Function (kostenpflichtig)
 * Jetzt:  Lokale OCR mit @react-native-ml-kit/text-recognition (kostenlos)
 *
 * scan.tsx importiert weiterhin von hier — kein Import-Change nötig.
 */

export type { ProcessResult } from './ocr';
export { processReceiptImage } from './ocr';
