-- Build ≤ 17 uploads .jpg files as "image/jpg" (non-standard); the bucket only
-- allowed image/jpeg and rejected every upload with 400 InvalidMimeType.
-- Applied on 2026-09-30 via SQL; recorded here for reproducibility.
UPDATE storage.buckets
SET allowed_mime_types = ARRAY['image/jpeg', 'image/jpg', 'image/png', 'image/heic', 'image/webp']
WHERE id = 'receipt-images';
