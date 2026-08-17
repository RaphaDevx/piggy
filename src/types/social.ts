export interface Profile {
  id: string;
  display_name: string | null;
  piggy_code: string;
  created_at: string;
}

export type FriendshipStatus = 'pending' | 'accepted' | 'declined';

export interface Friendship {
  id: string;
  requester_id: string;
  recipient_id: string;
  status: FriendshipStatus;
  created_at: string;
}

export interface FriendWithProfile extends Friendship {
  profile: Profile;
}
