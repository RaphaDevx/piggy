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
}

export interface ParsedReceiptItem {
  name: string;
  quantity: number;
  unit: string;
  unit_price: number;
  total_price: number;
  tags: string[];
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
