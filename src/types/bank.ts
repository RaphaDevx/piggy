export type AccountType = 'debit' | 'credit';

export interface BankStatement {
  id: string;
  user_id: string;
  account_label: string;
  account_type: AccountType;
  file_name: string | null;
  period_start: string | null;
  period_end: string | null;
  status: 'processing' | 'done' | 'error';
  error_message: string | null;
  created_at: string;
}

export interface BankTransaction {
  id: string;
  statement_id: string;
  user_id: string;
  booking_date: string;
  amount: number;
  currency: string;
  description: string;
  match_status: 'unmatched' | 'matched' | 'ignored';
  created_at: string;
}

export interface ReceiptMatch {
  id: string;
  user_id: string;
  transaction_id: string;
  receipt_id: string;
  score: number;
  match_type: 'auto' | 'manual';
  created_at: string;
}

export interface BankMatch {
  booking_date: string;
  amount: number;
  currency: string;
  description: string;
  match_type: 'auto' | 'manual';
}

export interface ParsedTransaction {
  booking_date: string;
  amount: number;
  currency: string;
  description: string;
}

export interface ParsedStatement {
  account_label: string | null;
  period_start: string | null;
  period_end: string | null;
  account_type: AccountType;
  transactions: ParsedTransaction[];
}
