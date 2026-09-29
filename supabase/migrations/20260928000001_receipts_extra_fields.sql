-- Add image, OCR, and extra-fields columns to receipts table

ALTER TABLE receipts
  ADD COLUMN IF NOT EXISTS original_image_url TEXT,
  ADD COLUMN IF NOT EXISTS raw_ocr_text TEXT,
  ADD COLUMN IF NOT EXISTS extra_fields JSONB;
