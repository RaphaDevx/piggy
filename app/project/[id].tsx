import { useCallback, useEffect, useState } from 'react';
import {
  View, Text, ScrollView, TouchableOpacity, StyleSheet,
  ActivityIndicator, Alert,
} from 'react-native';
import { useLocalSearchParams, useRouter, useFocusEffect } from 'expo-router';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { Ionicons } from '@/components/Ionicons';
import { supabase } from '../../src/lib/supabase';
import { useProject, loadProjectDebts, addProjectMember, removeProjectMember } from '../../src/hooks/useProjects';
import { useFriends } from '../../src/hooks/useFriends';
import { C, R, S, card } from '../../src/constants/design';
import type { ProjectDebt } from '../../src/types/project';

interface ProjectReceipt {
  id: string;
  store_name: string;
  receipt_date: string | null;
  total_amount: number;
  currency: string;
}

function fmt(n: number, currency = 'CHF') {
  return `${currency} ${n.toFixed(2)}`;
}

export default function ProjectDetailScreen() {
  const { id } = useLocalSearchParams<{ id: string }>();
  const insets = useSafeAreaInsets();
  const router = useRouter();

  const { project, loading, reload } = useProject(id ?? null);
  const { friends } = useFriends();
  const [receipts, setReceipts]  = useState<ProjectReceipt[]>([]);
  const [debts, setDebts]        = useState<ProjectDebt[]>([]);
  const [totalSpent, setTotal]   = useState(0);
  const [myId, setMyId]          = useState<string | null>(null);
  const [dataLoading, setDL]     = useState(true);

  const loadData = useCallback(async () => {
    if (!id) return;
    setDL(true);

    const [{ data: { user } }, { data: recs }] = await Promise.all([
      supabase.auth.getUser(),
      supabase
        .from('receipts')
        .select('id, store_name, receipt_date, total_amount, currency')
        .eq('project_id', id)
        .order('receipt_date', { ascending: false }),
    ]);

    setMyId(user?.id ?? null);
    setReceipts((recs as ProjectReceipt[]) ?? []);

    const { debts: d, totalSpent: t } = await loadProjectDebts(id);
    setDebts(d);
    setTotal(t);
    setDL(false);
  }, [id]);

  useFocusEffect(useCallback(() => { reload(); loadData(); }, [reload, loadData]));

  async function handleAddFriend() {
    const memberIds = new Set((project?.project_members ?? []).map((m) => m.user_id));
    const available = friends.filter((f) => !memberIds.has(f.profile.id));
    if (!available.length) {
      Alert.alert('Keine Freunde verfügbar', 'Alle deine Freunde sind bereits im Projekt.');
      return;
    }
    Alert.alert(
      'Freund hinzufügen',
      'Wähle einen Freund:',
      [
        ...available.map((f) => ({
          text: f.profile.display_name ?? f.profile.piggy_code,
          onPress: async () => {
            const { error } = await addProjectMember(id!, f.profile.id);
            if (error) Alert.alert('Fehler', error);
            else reload();
          },
        })),
        { text: 'Abbrechen', style: 'cancel' },
      ]
    );
  }

  async function handleRemoveMember(userId: string, name: string) {
    if (userId === myId) {
      Alert.alert('Hinweis', 'Du kannst dich nicht selbst entfernen.');
      return;
    }
    Alert.alert(`${name} entfernen?`, 'Die Person verliert den Zugang zum Projekt.', [
      { text: 'Abbrechen', style: 'cancel' },
      {
        text: 'Entfernen', style: 'destructive',
        onPress: async () => {
          await removeProjectMember(id!, userId);
          reload();
        },
      },
    ]);
  }

  if (loading || dataLoading) {
    return (
      <View style={[styles.container, { paddingTop: insets.top }, styles.center]}>
        <ActivityIndicator color={C.gold} />
      </View>
    );
  }

  if (!project) {
    return (
      <View style={[styles.container, { paddingTop: insets.top }, styles.center]}>
        <Text style={{ color: C.textTertiary }}>Projekt nicht gefunden.</Text>
      </View>
    );
  }

  const members = project.project_members ?? [];
  const isCreator = project.created_by === myId;

  return (
    <View style={[styles.container, { paddingTop: insets.top }]}>
      <View style={styles.header}>
        <TouchableOpacity onPress={() => router.back()} style={styles.backBtn}>
          <Ionicons name="chevron-back" size={24} color={C.textPrimary} />
        </TouchableOpacity>
        <Text style={styles.headerTitle} numberOfLines={1}>
          {project.emoji} {project.name}
        </Text>
        <View style={{ width: 40 }} />
      </View>

      <ScrollView contentContainerStyle={styles.body} showsVerticalScrollIndicator={false}>
        {/* Summary Card */}
        <View style={[card, styles.summaryCard]}>
          <Text style={styles.summaryEmoji}>{project.emoji}</Text>
          <Text style={styles.summaryName}>{project.name}</Text>
          {project.description ? (
            <Text style={styles.summaryDesc}>{project.description}</Text>
          ) : null}
          <View style={styles.summaryStats}>
            <View style={styles.statItem}>
              <Text style={styles.statValue}>{fmt(totalSpent, project.currency)}</Text>
              <Text style={styles.statLabel}>Total ausgegeben</Text>
            </View>
            <View style={styles.statDivider} />
            <View style={styles.statItem}>
              <Text style={styles.statValue}>{receipts.length}</Text>
              <Text style={styles.statLabel}>Quittungen</Text>
            </View>
            <View style={styles.statDivider} />
            <View style={styles.statItem}>
              <Text style={styles.statValue}>{members.length}</Text>
              <Text style={styles.statLabel}>Mitglieder</Text>
            </View>
          </View>
        </View>

        {/* Balances */}
        {debts.length > 0 && (
          <>
            <Text style={styles.sectionLabel}>Offene Schulden</Text>
            {debts.map((d, i) => (
              <View key={i} style={[card, styles.debtRow]}>
                <View style={styles.debtAvatar}>
                  <Text style={styles.debtAvatarText}>{d.fromName.charAt(0).toUpperCase()}</Text>
                </View>
                <View style={{ flex: 1 }}>
                  <Text style={styles.debtText}>
                    <Text style={styles.debtName}>{d.fromName}</Text>
                    {' schuldet '}
                    <Text style={styles.debtName}>{d.toName}</Text>
                  </Text>
                </View>
                <Text style={styles.debtAmount}>{fmt(d.amount, project.currency)}</Text>
              </View>
            ))}
          </>
        )}
        {debts.length === 0 && receipts.length > 0 && (
          <View style={[card, styles.allClearRow]}>
            <Ionicons name="checkmark-circle" size={22} color={C.success} />
            <Text style={styles.allClearText}>Alle Schulden beglichen ✓</Text>
          </View>
        )}

        {/* Members */}
        <View style={styles.sectionRow}>
          <Text style={styles.sectionLabel}>Mitglieder</Text>
          {isCreator && (
            <TouchableOpacity onPress={handleAddFriend}>
              <Text style={styles.sectionAction}>+ Hinzufügen</Text>
            </TouchableOpacity>
          )}
        </View>
        <View style={[card, { overflow: 'hidden', padding: 0 }]}>
          {members.map((m, idx) => {
            const name = m.profile?.display_name ?? m.profile?.piggy_code ?? '?';
            const isMe = m.user_id === myId;
            const isOwner = m.user_id === project.created_by;
            return (
              <View key={m.id}>
                <TouchableOpacity
                  style={styles.memberRow}
                  onLongPress={() => isCreator && !isOwner && handleRemoveMember(m.user_id, name)}
                >
                  <View style={styles.memberAvatar}>
                    <Text style={styles.memberAvatarText}>{name.charAt(0).toUpperCase()}</Text>
                  </View>
                  <Text style={styles.memberName}>{name}{isMe ? ' (du)' : ''}</Text>
                  {isOwner && (
                    <View style={styles.ownerBadge}>
                      <Text style={styles.ownerBadgeText}>Admin</Text>
                    </View>
                  )}
                </TouchableOpacity>
                {idx < members.length - 1 && <View style={styles.divider} />}
              </View>
            );
          })}
        </View>
        {isCreator && <Text style={styles.hintText}>Mitglied lang drücken zum Entfernen</Text>}

        {/* Receipts */}
        <Text style={styles.sectionLabel}>Quittungen im Projekt</Text>
        {receipts.length === 0 ? (
          <View style={[card, styles.emptyReceipts]}>
            <Ionicons name="receipt-outline" size={28} color={C.textTertiary} />
            <Text style={styles.emptyReceiptsText}>
              Noch keine Quittungen. Öffne eine Quittung und weise sie diesem Projekt zu.
            </Text>
          </View>
        ) : (
          receipts.map((r, idx) => (
            <TouchableOpacity
              key={r.id}
              style={[card, styles.receiptRow]}
              onPress={() => router.push(`/receipt/${r.id}`)}
              activeOpacity={0.8}
            >
              <View style={{ flex: 1 }}>
                <Text style={styles.receiptStore} numberOfLines={1}>{r.store_name}</Text>
                {r.receipt_date && (
                  <Text style={styles.receiptDate}>
                    {new Date(r.receipt_date).toLocaleDateString('de-CH', { day: '2-digit', month: 'short' })}
                  </Text>
                )}
              </View>
              <Text style={styles.receiptAmount}>{fmt(r.total_amount, r.currency)}</Text>
              <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
            </TouchableOpacity>
          ))
        )}
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: C.bg },
  center:    { justifyContent: 'center', alignItems: 'center' },

  header:      { flexDirection: 'row', alignItems: 'center', paddingHorizontal: 20, paddingVertical: 14, gap: 12 },
  backBtn:     { width: 40, height: 40, borderRadius: 20, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  headerTitle: { flex: 1, fontSize: S.xl, fontWeight: '800', color: C.textPrimary, letterSpacing: -0.5 },

  body: { paddingHorizontal: 20, paddingBottom: 120, gap: 12 },

  summaryCard: { alignItems: 'center', padding: 24, gap: 6 },
  summaryEmoji:{ fontSize: 44, marginBottom: 4 },
  summaryName: { fontSize: S.xl, fontWeight: '900', color: C.textPrimary, letterSpacing: -0.5 },
  summaryDesc: { fontSize: S.sm, color: C.textSecondary, textAlign: 'center' },
  summaryStats:{ flexDirection: 'row', marginTop: 16, gap: 0 },
  statItem:    { flex: 1, alignItems: 'center', gap: 2 },
  statValue:   { fontSize: S.md, fontWeight: '800', color: C.textPrimary },
  statLabel:   { fontSize: 10, color: C.textTertiary, fontWeight: '600', textTransform: 'uppercase', letterSpacing: 0.5 },
  statDivider: { width: 1, backgroundColor: C.borderSoft },

  sectionLabel: { fontSize: S.xs, color: C.textTertiary, fontWeight: '700', letterSpacing: 1, textTransform: 'uppercase', marginTop: 4 },
  sectionRow:   { flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between', marginTop: 4 },
  sectionAction:{ fontSize: S.sm, color: C.gold, fontWeight: '700' },

  debtRow:      { flexDirection: 'row', alignItems: 'center', padding: 14, gap: 12 },
  debtAvatar:   { width: 36, height: 36, borderRadius: 18, backgroundColor: '#FF9F0A20', alignItems: 'center', justifyContent: 'center' },
  debtAvatarText: { fontSize: S.sm, fontWeight: '700', color: C.gold },
  debtText:     { fontSize: S.sm, color: C.textSecondary },
  debtName:     { fontWeight: '700', color: C.textPrimary },
  debtAmount:   { fontSize: S.md, fontWeight: '800', color: C.textPrimary },

  allClearRow:  { flexDirection: 'row', alignItems: 'center', padding: 14, gap: 10 },
  allClearText: { fontSize: S.sm, color: C.success, fontWeight: '600' },

  memberRow:        { flexDirection: 'row', alignItems: 'center', paddingHorizontal: 16, paddingVertical: 12, gap: 12 },
  memberAvatar:     { width: 38, height: 38, borderRadius: 19, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  memberAvatarText: { fontSize: S.sm, fontWeight: '700', color: C.gold },
  memberName:       { flex: 1, fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  ownerBadge:       { paddingHorizontal: 8, paddingVertical: 3, backgroundColor: '#FF9F0A15', borderRadius: 8 },
  ownerBadgeText:   { fontSize: 10, color: C.gold, fontWeight: '700' },
  hintText:         { fontSize: S.xs, color: C.textTertiary, textAlign: 'center', marginTop: -4 },

  divider: { height: 1, backgroundColor: C.borderSoft, marginLeft: 66 },

  emptyReceipts:    { alignItems: 'center', padding: 24, gap: 10 },
  emptyReceiptsText:{ fontSize: S.sm, color: C.textTertiary, textAlign: 'center', lineHeight: 20 },

  receiptRow:   { flexDirection: 'row', alignItems: 'center', padding: 14, gap: 10 },
  receiptStore: { fontSize: S.sm, fontWeight: '700', color: C.textPrimary },
  receiptDate:  { fontSize: S.xs, color: C.textTertiary, marginTop: 2 },
  receiptAmount:{ fontSize: S.md, fontWeight: '800', color: C.textPrimary },
});
