-- Quittungsbilder sind privat: Zugriff nur über signierte URLs (RLS-Policies
-- receipt_images_own_* schränken auf den eigenen Ordner {uid}/… ein).
update storage.buckets set public = false where id = 'receipt-images';

-- Gespeichert wird nur noch der Pfad, nie eine URL.
update public.receipts
   set original_image_url = regexp_replace(original_image_url, '^.*/object/(public|sign)/receipt-images/([^?]+).*$', '\2')
 where original_image_url like '%/object/%/receipt-images/%';
update public.receipts
   set image_url = regexp_replace(image_url, '^.*/object/(public|sign)/receipt-images/([^?]+).*$', '\2')
 where image_url like '%/object/%/receipt-images/%';
