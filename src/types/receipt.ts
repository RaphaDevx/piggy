export interface ReceiptExtraFields {
  cashier_number: string | null;
  receipt_number: string | null;
  vat_number: string | null;
  store_address: string | null;
  store_email: string | null;
  store_phone: string | null;
  store_website: string | null;
}

export interface Receipt {
  id: string;
  user_id: string;
  receipt_date: string | null;
  store_name: string;
  store_category: string | null;
  total_amount: number | null;
  currency: string;
  payment_method: string | null;
  payment_card: string | null;
  image_url: string | null;
  original_image_url: string | null;
  raw_ocr_text: string | null;
  extra_fields: ReceiptExtraFields | null;
  markdown_content: string | null;
  notes: string | null;
  created_at: string;
  project_id: string | null;
}

export interface ReceiptItem {
  id: string;
  receipt_id: string;
  name: string;
  quantity: number | null;
  unit: string | null;
  unit_price: number | null;
  total_price: number | null;
  tags: string[];
  subcategory?: string | null;
  is_adjustment?: boolean;
}

export interface ParsedReceipt {
  store_name: string;
  store_category: string;
  date: string | null;
  total_amount: number;
  currency: string;
  payment_method: string;
  payment_card: string | null;
  items: ParsedReceiptItem[];
  extra?: ReceiptExtraFields | null;
}

export interface ParsedReceiptItem {
  name: string;
  quantity: number;
  unit: string;
  unit_price: number;
  total_price: number;
  tags: string[];
  subcategory?: string | null;
  is_adjustment?: boolean;
}

export interface ReceiptWithItems extends Receipt {
  receipt_items: ReceiptItem[];
}

export type Period = 'week' | 'month' | '3months' | 'year';

export interface SpendingByTag {
  tag: string;
  total: number;
}

export interface StoreSpending {
  store_name: string;
  total: number;
  count: number;
}
