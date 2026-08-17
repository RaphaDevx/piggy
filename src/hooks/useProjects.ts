import { useCallback, useEffect, useState } from 'react';
import { supabase } from '../lib/supabase';
import type { Project, ProjectWithMembers, ProjectDebt } from '../types/project';

export function useProjects() {
  const [projects, setProjects] = useState<ProjectWithMembers[]>([]);
  const [loading, setLoading] = useState(true);

  const load = useCallback(async () => {
    setLoading(true);
    const { data } = await supabase
      .from('projects')
      .select('*, project_members(*, profile:profiles(*))')
      .order('created_at', { ascending: false });
    setProjects((data as ProjectWithMembers[]) ?? []);
    setLoading(false);
  }, []);

  useEffect(() => { load(); }, [load]);
  return { projects, loading, reload: load };
}

export function useProject(id: string | null) {
  const [project, setProject] = useState<ProjectWithMembers | null>(null);
  const [loading, setLoading] = useState(true);

  const load = useCallback(async () => {
    if (!id) { setLoading(false); return; }
    setLoading(true);
    const { data } = await supabase
      .from('projects')
      .select('*, project_members(*, profile:profiles(*))')
      .eq('id', id)
      .single();
    setProject(data as ProjectWithMembers | null);
    setLoading(false);
  }, [id]);

  useEffect(() => { load(); }, [load]);
  return { project, loading, reload: load };
}

interface CreateProjectParams {
  name: string;
  emoji: string;
  description?: string;
  currency?: string;
  friendIds?: string[];
}

export async function createProject(params: CreateProjectParams): Promise<{ id?: string; error?: string }> {
  const { data: { user } } = await supabase.auth.getUser();
  if (!user) return { error: 'Nicht eingeloggt' };

  const { data: project, error } = await supabase
    .from('projects')
    .insert({
      name: params.name,
      emoji: params.emoji,
      description: params.description ?? null,
      currency: params.currency ?? 'CHF',
      created_by: user.id,
    })
    .select()
    .single();

  if (error || !project) return { error: error?.message ?? 'Fehler' };

  // Add friends as members
  if (params.friendIds?.length) {
    await supabase.from('project_members').insert(
      params.friendIds.map((uid) => ({ project_id: project.id, user_id: uid, invited_by: user.id }))
    );
  }

  return { id: project.id };
}

export async function assignReceiptToProject(receiptId: string, projectId: string | null): Promise<{ error?: string }> {
  const { error } = await supabase
    .from('receipts')
    .update({ project_id: projectId })
    .eq('id', receiptId);
  return { error: error?.message };
}

export async function addProjectMember(projectId: string, userId: string): Promise<{ error?: string }> {
  const { data: { user } } = await supabase.auth.getUser();
  const { error } = await supabase
    .from('project_members')
    .insert({ project_id: projectId, user_id: userId, invited_by: user?.id });
  return { error: error?.message };
}

export async function removeProjectMember(projectId: string, userId: string): Promise<{ error?: string }> {
  const { error } = await supabase
    .from('project_members')
    .delete()
    .eq('project_id', projectId)
    .eq('user_id', userId);
  return { error: error?.message };
}

// Compute net debts for a project from all splits on project receipts
export async function loadProjectDebts(projectId: string): Promise<{ debts: ProjectDebt[]; totalSpent: number }> {
  // Load all receipts in this project with their splits
  const { data: receipts } = await supabase
    .from('receipts')
    .select('id, total_amount, currency')
    .eq('project_id', projectId);

  if (!receipts?.length) return { debts: [], totalSpent: 0 };

  const receiptIds = receipts.map((r) => r.id);
  const totalSpent = receipts.reduce((s, r) => s + (r.total_amount ?? 0), 0);

  const { data: splits } = await supabase
    .from('splits')
    .select('*, split_participants(*, profile:profiles(*))')
    .in('receipt_id', receiptIds);

  if (!splits?.length) return { debts: [], totalSpent };

  // net[userId] = positive means others owe them, negative means they owe others
  const net = new Map<string, number>();
  const names = new Map<string, string>();

  for (const split of splits as any[]) {
    const payer = split.payer_id as string;
    for (const p of (split.split_participants ?? []) as any[]) {
      if (p.settled) continue;
      const participant = p.user_id as string;
      if (participant === payer) continue;
      const amount = p.amount as number;
      // Payer is owed money by participant
      net.set(payer, (net.get(payer) ?? 0) + amount);
      net.set(participant, (net.get(participant) ?? 0) - amount);
      if (p.profile) {
        names.set(participant, p.profile.display_name ?? p.profile.piggy_code ?? participant);
      }
    }
  }

  // Simplify debts (greedy algorithm)
  const creditors: Array<{ id: string; amount: number }> = [];
  const debtors:   Array<{ id: string; amount: number }> = [];

  net.forEach((amount, id) => {
    if (amount > 0.005) creditors.push({ id, amount });
    else if (amount < -0.005) debtors.push({ id, amount: -amount });
  });

  const debts: ProjectDebt[] = [];

  let ci = 0, di = 0;
  while (ci < creditors.length && di < debtors.length) {
    const c = creditors[ci];
    const d = debtors[di];
    const settle = Math.min(c.amount, d.amount);
    debts.push({
      fromUserId: d.id,
      fromName: names.get(d.id) ?? d.id,
      toUserId: c.id,
      toName: names.get(c.id) ?? c.id,
      amount: Math.round(settle * 100) / 100,
    });
    c.amount -= settle;
    d.amount -= settle;
    if (c.amount < 0.005) ci++;
    if (d.amount < 0.005) di++;
  }

  return { debts, totalSpent };
}
