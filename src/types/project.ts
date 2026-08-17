import type { Profile } from './social';

export interface Project {
  id: string;
  name: string;
  emoji: string;
  description: string | null;
  created_by: string;
  currency: string;
  created_at: string;
}

export interface ProjectMember {
  id: string;
  project_id: string;
  user_id: string;
  invited_by: string | null;
  joined_at: string;
  profile?: Profile;
}

export interface ProjectWithMembers extends Project {
  project_members: ProjectMember[];
}

export interface ProjectBalance {
  userId: string;
  name: string;
  netAmount: number; // positive = others owe them, negative = they owe others
}

export interface ProjectDebt {
  fromUserId: string;
  fromName: string;
  toUserId: string;
  toName: string;
  amount: number;
}
