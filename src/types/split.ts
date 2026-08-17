import type { Profile } from './social';

export interface Split {
  id: string;
  receipt_id: string;
  payer_id: string;
  total_amount: number;
  currency: string;
  note: string | null;
  created_at: string;
}

export interface SplitParticipant {
  id: string;
  split_id: string;
  user_id: string;
  amount: number;
  settled: boolean;
  settled_at: string | null;
  profile?: Profile;
}

export interface SplitExclusion {
  id: string;
  split_id: string;
  user_id: string;
  receipt_item_id: string;
}

export interface SplitWithDetails extends Split {
  participants: SplitParticipant[];
  exclusions: SplitExclusion[];
  payer_profile?: Profile;
  receipt?: { store_name: string; receipt_date: string | null };
}

export interface Balance {
  // I owe them (they paid, I'm a participant)
  iOwe: Array<{ participant: SplitParticipant; split: SplitWithDetails }>;
  // They owe me (I paid, they're participants)
  owedToMe: Array<{ participant: SplitParticipant; split: SplitWithDetails }>;
}
