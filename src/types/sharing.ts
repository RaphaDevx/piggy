import type { Profile } from './social';

export interface ProfileCard {
  id: string;
  user_id: string;
  last_four: string;
  label: string | null;
  created_at: string;
}

export interface SharedReceipt {
  id: string;
  receipt_id: string;
  sender_id: string;
  recipient_id: string;
  status: 'pending' | 'accepted' | 'declined';
  created_at: string;
}

export interface SharedReceiptWithDetails extends SharedReceipt {
  sender_profile?: Profile;
  receipt?: {
    store_name: string;
    receipt_date: string | null;
    total_amount: number;
    currency: string;
  };
}

export interface CardMatch {
  last_four: string;
  profile: Profile;
  card: ProfileCard;
}

// Extract last 4 digits from a payment_card string like "XXXX 5327" or "****5327"
export function extractLastFour(paymentCard: string | null): string | null {
  if (!paymentCard) return null;
  const m = paymentCard.replace(/\s/g, '').match(/(\d{4})$/);
  return m ? m[1] : null;
}
