import { useCallback, useEffect, useState } from 'react';
import {
  View, Text, ScrollView, TouchableOpacity, StyleSheet,
  ActivityIndicator, Alert, Modal,
} from 'react-native';
import { useLocalSearchParams, useRouter, useFocusEffect } from 'expo-router';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { Ionicons } from '@/components/Ionicons';
import { supabase } from '../../src/lib/supabase';
import { useFriends } from '../../src/hooks/useFriends';
import { useSplitForReceipt, calcShares, createSplit, settleParticipant } from '../../src/hooks/useSplits';
import { C, R, S, card } from '../../src/constants/design';
import type { ReceiptWithItems } from '../../src/types/receipt';
import type { Profile } from '../../src/types/social';

function fmt(n: number, currency = 'CHF') {
  return `${currency} ${n.toFixed(2)}`;
}

export default function SplitScreen() {
  const { receiptId } = useLocalSearchParams<{ receiptId: string }>();
  const insets = useSafeAreaInsets();
  const router = useRouter();

  const [receipt, setReceipt]           = useState<ReceiptWithItems | null>(null);
  const [myProfile, setMyProfile]       = useState<Profile | null>(null);
  const [receiptLoading, setRL]         = useState(true);

  // Who to split with (friend user IDs, excluding self — added separately)
  const [selected, setSelected]         = useState<Set<string>>(new Set());
  // Exclusions: userId → Set<itemId>
  const [exclusions, setExclusions]     = useState<Map<string, Set<string>>>(new Map());
  // Which person's exclusion modal is open
  const [exclusionFor, setExclusionFor] = useState<string | null>(null);

  const [saving, setSaving]             = useState(false);

  const { friends, loading: friendsLoading } = useFriends();
  const { split, loading: splitLoading, reload: reloadSplit } = useSplitForReceipt(receiptId ?? null);

  useFocusEffect(useCallback(() => { reloadSplit(); }, [reloadSplit]));

  useEffect(() => {
    if (!receiptId) return;
    supabase.from('receipts').select('*, receipt_items(*)').eq('id', receiptId).single()
      .then(({ data }) => { setReceipt(data as ReceiptWithItems | null); setRL(false); });
    supabase.auth.getUser().then(async ({ data: { user } }) => {
      if (!user) return;
      const { data } = await supabase.from('profiles').select('*').eq('id', user.id).single();
      setMyProfile(data);
    });
  }, [receiptId]);

  const items   = receipt?.receipt_items ?? [];
  const total   = receipt?.total_amount ?? 0;
  const currency = receipt?.currency ?? 'CHF';

  // All participants = me + selected friends
  const allParticipants = myProfile
    ? [myProfile.id, ...Array.from(selected)]
    : Array.from(selected);

  const shares = calcShares(items, total, allParticipants, exclusions);

  function toggleFriend(id: string) {
    setSelected((prev) => {
      const next = new Set(prev);
      if (next.has(id)) { next.delete(id); exclusions.delete(id); } else next.add(id);
      return next;
    });
  }

  function toggleExclusion(userId: string, itemId: string) {
    setExclusions((prev) => {
      const next = new Map(prev);
      const s = new Set(next.get(userId) ?? []);
      if (s.has(itemId)) s.delete(itemId); else s.add(itemId);
      next.set(userId, s);
      return next;
    });
  }

  async function handleCreate() {
    if (!myProfile || !receiptId) return;
    if (selected.size === 0) { Alert.alert('Hinweis', 'Wähle mindestens einen Freund aus.'); return; }
    setSaving(true);
    const { error } = await createSplit({
      receiptId,
      payerId:        myProfile.id,
      totalAmount:    total,
      currency,
      participantIds: allParticipants,
      shares,
      exclusions,
    });
    setSaving(false);
    if (error) { Alert.alert('Fehler', error); return; }
    await reloadSplit();
  }

  async function handleSettle(participantId: string, name: string) {
    Alert.alert('Beglichen?', `${name} hat bezahlt?`, [
      { text: 'Abbrechen', style: 'cancel' },
      {
        text: 'Ja, beglichen', onPress: async () => {
          await settleParticipant(participantId);
          reloadSplit();
        },
      },
    ]);
  }

  async function handleDelete() {
    if (!split) return;
    Alert.alert('Split löschen', 'Diesen Split wirklich entfernen?', [
      { text: 'Abbrechen', style: 'cancel' },
      {
        text: 'Löschen', style: 'destructive', onPress: async () => {
          await supabase.from('splits').delete().eq('id', split.id);
          await reloadSplit();
        },
      },
    ]);
  }

  const isLoading = receiptLoading || splitLoading || friendsLoading;

  if (isLoading) {
    return (
      <View style={[styles.container, { paddingTop: insets.top }, styles.center]}>
        <ActivityIndicator color={C.gold} />
      </View>
    );
  }

  // ── EXISTING SPLIT VIEW ─────────────────────────────────────────────────────
  if (split) {
    const participants = split.split_participants ?? [];
    const unsettled = participants.filter((p) => !p.settled && p.user_id !== split.payer_id);
    const totalOwed = unsettled.reduce((s, p) => s + p.amount, 0);

    return (
      <View style={[styles.container, { paddingTop: insets.top }]}>
        <View style={styles.header}>
          <TouchableOpacity onPress={() => router.back()} style={styles.backBtn}>
            <Ionicons name="chevron-back" size={24} color={C.textPrimary} />
          </TouchableOpacity>
          <Text style={styles.title}>Split</Text>
          <TouchableOpacity onPress={handleDelete} style={styles.deleteBtn}>
            <Ionicons name="trash-outline" size={20} color={C.error} />
          </TouchableOpacity>
        </View>

        <ScrollView contentContainerStyle={styles.body} showsVerticalScrollIndicator={false}>
          {/* Receipt Summary */}
          <View style={[card, styles.summaryCard]}>
            <Text style={styles.storeName}>{receipt?.store_name ?? '—'}</Text>
            <Text style={styles.totalBig}>{fmt(split.total_amount, split.currency)}</Text>
            {unsettled.length > 0 && (
              <View style={styles.pendingChip}>
                <Text style={styles.pendingText}>{fmt(totalOwed, split.currency)} ausstehend</Text>
              </View>
            )}
          </View>

          {/* Participants */}
          <Text style={styles.sectionLabel}>Aufteilung</Text>
          {participants.map((p) => {
            const name = p.profile?.display_name ?? p.profile?.piggy_code ?? '…';
            const isPayer = p.user_id === split.payer_id;
            const isMe = p.user_id === myProfile?.id;
            return (
              <View key={p.id} style={[card, styles.participantRow]}>
                <View style={styles.pAvatar}>
                  <Text style={styles.pAvatarText}>{name.charAt(0).toUpperCase()}</Text>
                </View>
                <View style={{ flex: 1 }}>
                  <Text style={styles.pName}>{name}{isMe ? ' (du)' : ''}{isPayer ? ' · bezahlt' : ''}</Text>
                  <Text style={styles.pAmount}>{fmt(p.amount, split.currency)}</Text>
                </View>
                {p.settled
                  ? <View style={styles.settledBadge}><Text style={styles.settledText}>✓ Beglichen</Text></View>
                  : !isPayer
                    ? (
                      <TouchableOpacity
                        style={styles.settleBtn}
                        onPress={() => handleSettle(p.id, name)}
                      >
                        <Text style={styles.settleBtnText}>Beglichen</Text>
                      </TouchableOpacity>
                    ) : null}
              </View>
            );
          })}

          {/* Exclusions detail */}
          {(split.split_exclusions?.length ?? 0) > 0 && (
            <>
              <Text style={[styles.sectionLabel, { marginTop: 8 }]}>Artikel-Ausschlüsse</Text>
              <View style={[card, { padding: 14 }]}>
                {split.split_exclusions?.map((ex) => {
                  const item = items.find((i) => i.id === ex.receipt_item_id);
                  const p = participants.find((p) => p.user_id === ex.user_id);
                  const name = p?.profile?.display_name ?? p?.profile?.piggy_code ?? ex.user_id;
                  return (
                    <Text key={ex.id} style={styles.exRow}>
                      {name} zahlt nicht: {item?.name ?? ex.receipt_item_id}
                    </Text>
                  );
                })}
              </View>
            </>
          )}
        </ScrollView>
      </View>
    );
  }

  // ── CREATE SPLIT VIEW ───────────────────────────────────────────────────────
  return (
    <View style={[styles.container, { paddingTop: insets.top }]}>
      <View style={styles.header}>
        <TouchableOpacity onPress={() => router.back()} style={styles.backBtn}>
          <Ionicons name="chevron-back" size={24} color={C.textPrimary} />
        </TouchableOpacity>
        <Text style={styles.title}>Ausgabe teilen</Text>
        <View style={{ width: 40 }} />
      </View>

      <ScrollView contentContainerStyle={styles.body} showsVerticalScrollIndicator={false}>
        {/* Receipt Card */}
        <View style={[card, styles.summaryCard]}>
          <Text style={styles.storeName}>{receipt?.store_name ?? '—'}</Text>
          <Text style={styles.totalBig}>{fmt(total, currency)}</Text>
        </View>

        {/* Friend Selection */}
        <Text style={styles.sectionLabel}>Mit wem teilen?</Text>
        {friends.length === 0 ? (
          <View style={[card, styles.emptyFriends]}>
            <Ionicons name="people-outline" size={32} color={C.textTertiary} />
            <Text style={styles.emptyText}>Noch keine Freunde hinzugefügt.</Text>
          </View>
        ) : (
          <View style={styles.friendsGrid}>
            {friends.map((f) => {
              const name = f.profile.display_name ?? f.profile.piggy_code;
              const active = selected.has(f.profile.id);
              return (
                <TouchableOpacity
                  key={f.id}
                  style={[styles.friendChip, active && styles.friendChipActive]}
                  onPress={() => toggleFriend(f.profile.id)}
                >
                  <View style={[styles.chipAvatar, active && styles.chipAvatarActive]}>
                    <Text style={[styles.chipAvatarText, active && { color: '#fff' }]}>
                      {name.charAt(0).toUpperCase()}
                    </Text>
                  </View>
                  <Text style={[styles.chipName, active && styles.chipNameActive]} numberOfLines={1}>
                    {name}
                  </Text>
                  {active && <Ionicons name="checkmark-circle" size={16} color={C.gold} />}
                </TouchableOpacity>
              );
            })}
          </View>
        )}

        {/* Article Exclusions */}
        {selected.size > 0 && items.length > 0 && (
          <>
            <Text style={styles.sectionLabel}>Artikel-Ausschlüsse <Text style={styles.sectionOptional}>(optional)</Text></Text>
            <View style={[card, { overflow: 'hidden', padding: 0 }]}>
              {[myProfile, ...friends.filter((f) => selected.has(f.profile.id)).map((f) => f.profile)].filter(Boolean).map((p, idx, arr) => {
                const name = p!.display_name ?? p!.piggy_code;
                const myExcl = exclusions.get(p!.id);
                const count = myExcl?.size ?? 0;
                return (
                  <View key={p!.id}>
                    <TouchableOpacity
                      style={styles.exclusionRow}
                      onPress={() => setExclusionFor(p!.id)}
                    >
                      <View style={styles.pAvatar}>
                        <Text style={styles.pAvatarText}>{name.charAt(0).toUpperCase()}</Text>
                      </View>
                      <Text style={[styles.pName, { flex: 1 }]}>{name}{p!.id === myProfile?.id ? ' (du)' : ''}</Text>
                      <Text style={styles.exclCount}>
                        {count > 0 ? `${count} ausgeschlossen` : 'nichts ausgeschlossen'}
                      </Text>
                      <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
                    </TouchableOpacity>
                    {idx < arr.length - 1 && <View style={styles.divider} />}
                  </View>
                );
              })}
            </View>
          </>
        )}

        {/* Breakdown */}
        {selected.size > 0 && (
          <>
            <Text style={styles.sectionLabel}>Aufteilung</Text>
            <View style={[card, { overflow: 'hidden', padding: 0 }]}>
              {allParticipants.map((uid, idx) => {
                const profile = uid === myProfile?.id
                  ? myProfile
                  : friends.find((f) => f.profile.id === uid)?.profile;
                const name = profile?.display_name ?? profile?.piggy_code ?? uid;
                const amount = shares.get(uid) ?? 0;
                return (
                  <View key={uid}>
                    <View style={styles.breakdownRow}>
                      <Text style={styles.breakdownName}>{name}{uid === myProfile?.id ? ' (du, Zahler)' : ''}</Text>
                      <Text style={styles.breakdownAmount}>{fmt(amount, currency)}</Text>
                    </View>
                    {idx < allParticipants.length - 1 && <View style={styles.divider} />}
                  </View>
                );
              })}
            </View>
          </>
        )}

        {/* Create Button */}
        <TouchableOpacity
          style={[styles.createBtn, (selected.size === 0 || saving) && styles.btnDisabled]}
          onPress={handleCreate}
          disabled={selected.size === 0 || saving}
        >
          {saving
            ? <ActivityIndicator color="#fff" />
            : <Text style={styles.createBtnText}>Split erstellen</Text>}
        </TouchableOpacity>
      </ScrollView>

      {/* Exclusion Modal */}
      <Modal visible={!!exclusionFor} animationType="slide" transparent onRequestClose={() => setExclusionFor(null)}>
        <View style={styles.modalOverlay}>
          <View style={[card, styles.modalBox]}>
            {(() => {
              const p = exclusionFor === myProfile?.id
                ? myProfile
                : friends.find((f) => f.profile.id === exclusionFor)?.profile;
              const name = p?.display_name ?? p?.piggy_code ?? '…';
              const myExcl = exclusions.get(exclusionFor ?? '') ?? new Set<string>();
              return (
                <>
                  <Text style={styles.modalTitle}>Was zahlt {name} nicht?</Text>
                  <ScrollView style={{ maxHeight: 320 }}>
                    {items.map((item) => {
                      const excluded = myExcl.has(item.id);
                      return (
                        <TouchableOpacity
                          key={item.id}
                          style={styles.itemToggleRow}
                          onPress={() => toggleExclusion(exclusionFor!, item.id)}
                        >
                          <View style={[styles.itemToggle, excluded && styles.itemToggleActive]}>
                            {excluded && <Ionicons name="close" size={14} color="#fff" />}
                          </View>
                          <Text style={[styles.itemToggleName, excluded && styles.itemToggleNameExcl]} numberOfLines={1}>
                            {item.name}
                          </Text>
                          <Text style={styles.itemTogglePrice}>{fmt(item.total_price ?? 0, currency)}</Text>
                        </TouchableOpacity>
                      );
                    })}
                  </ScrollView>
                  <TouchableOpacity style={styles.modalDone} onPress={() => setExclusionFor(null)}>
                    <Text style={styles.modalDoneText}>Fertig</Text>
                  </TouchableOpacity>
                </>
              );
            })()}
          </View>
        </View>
      </Modal>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: C.bg },
  center:    { justifyContent: 'center', alignItems: 'center' },

  header:    { flexDirection: 'row', alignItems: 'center', paddingHorizontal: 20, paddingVertical: 14, gap: 12 },
  backBtn:   { width: 40, height: 40, borderRadius: 20, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  deleteBtn: { width: 40, height: 40, borderRadius: 20, backgroundColor: '#FF3B3012', alignItems: 'center', justifyContent: 'center' },
  title:     { flex: 1, fontSize: S.xl, fontWeight: '800', color: C.textPrimary, letterSpacing: -0.5 },

  body: { paddingHorizontal: 20, paddingBottom: 40, gap: 12 },

  sectionLabel:    { fontSize: S.xs, color: C.textTertiary, fontWeight: '700', letterSpacing: 1, textTransform: 'uppercase', marginTop: 4 },
  sectionOptional: { fontSize: S.xs, color: C.textTertiary, fontWeight: '400', textTransform: 'none', letterSpacing: 0 },

  summaryCard: { alignItems: 'center', padding: 24, gap: 8 },
  storeName:   { fontSize: S.sm, color: C.textTertiary, fontWeight: '600' },
  totalBig:    { fontSize: 36, fontWeight: '900', color: C.textPrimary, letterSpacing: -1 },
  pendingChip: { backgroundColor: '#FF9F0A20', paddingHorizontal: 12, paddingVertical: 4, borderRadius: 20 },
  pendingText: { fontSize: S.xs, color: C.gold, fontWeight: '700' },

  emptyFriends: { alignItems: 'center', padding: 24, gap: 10 },
  emptyText:    { fontSize: S.sm, color: C.textTertiary },

  friendsGrid: { flexDirection: 'row', flexWrap: 'wrap', gap: 10 },
  friendChip:  { flexDirection: 'row', alignItems: 'center', gap: 8, paddingHorizontal: 12, paddingVertical: 8, borderRadius: 20, backgroundColor: C.bgAccent, borderWidth: 1, borderColor: C.borderSoft },
  friendChipActive: { borderColor: C.gold, backgroundColor: '#FFF5E6' },
  chipAvatar:  { width: 28, height: 28, borderRadius: 14, backgroundColor: C.border, alignItems: 'center', justifyContent: 'center' },
  chipAvatarActive: { backgroundColor: C.gold },
  chipAvatarText: { fontSize: S.xs, fontWeight: '700', color: C.textSecondary },
  chipName:    { fontSize: S.sm, fontWeight: '600', color: C.textSecondary, maxWidth: 90 },
  chipNameActive: { color: C.textPrimary },

  exclusionRow:  { flexDirection: 'row', alignItems: 'center', paddingHorizontal: 16, paddingVertical: 14, gap: 12 },
  exclCount:     { fontSize: S.xs, color: C.textTertiary },

  participantRow: { flexDirection: 'row', alignItems: 'center', padding: 14, gap: 12 },
  pAvatar:        { width: 40, height: 40, borderRadius: 20, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  pAvatarText:    { fontSize: S.md, fontWeight: '700', color: C.gold },
  pName:          { fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  pAmount:        { fontSize: S.sm, color: C.textTertiary, marginTop: 2 },

  settledBadge: { paddingHorizontal: 10, paddingVertical: 4, backgroundColor: '#34C75920', borderRadius: 12 },
  settledText:  { fontSize: S.xs, color: C.success, fontWeight: '700' },
  settleBtn:    { paddingHorizontal: 12, paddingVertical: 6, backgroundColor: C.bgAccent, borderRadius: 12, borderWidth: 1, borderColor: C.border },
  settleBtnText:{ fontSize: S.xs, fontWeight: '700', color: C.textPrimary },

  breakdownRow:    { flexDirection: 'row', alignItems: 'center', paddingHorizontal: 16, paddingVertical: 12 },
  breakdownName:   { flex: 1, fontSize: S.sm, color: C.textPrimary, fontWeight: '600' },
  breakdownAmount: { fontSize: S.sm, fontWeight: '700', color: C.textPrimary },

  divider: { height: 1, backgroundColor: C.borderSoft, marginLeft: 16 },

  exRow: { fontSize: S.xs, color: C.textTertiary, marginBottom: 4 },

  createBtn:      { backgroundColor: C.gold, borderRadius: R.md, paddingVertical: 16, alignItems: 'center', marginTop: 8 },
  btnDisabled:    { opacity: 0.4 },
  createBtnText:  { color: '#fff', fontWeight: '700', fontSize: S.md },

  // Exclusion modal
  modalOverlay: { flex: 1, backgroundColor: 'rgba(0,0,0,0.4)', justifyContent: 'flex-end' },
  modalBox:     { margin: 16, padding: 24, gap: 16, borderRadius: R.xl },
  modalTitle:   { fontSize: S.lg, fontWeight: '800', color: C.textPrimary },

  itemToggleRow:   { flexDirection: 'row', alignItems: 'center', paddingVertical: 10, gap: 12 },
  itemToggle:      { width: 24, height: 24, borderRadius: 6, borderWidth: 2, borderColor: C.border, alignItems: 'center', justifyContent: 'center' },
  itemToggleActive:{ backgroundColor: C.error, borderColor: C.error },
  itemToggleName:      { flex: 1, fontSize: S.sm, color: C.textPrimary, fontWeight: '600' },
  itemToggleNameExcl:  { color: C.textTertiary, textDecorationLine: 'line-through' },
  itemTogglePrice:     { fontSize: S.xs, color: C.textTertiary },

  modalDone:     { backgroundColor: C.gold, borderRadius: R.md, paddingVertical: 14, alignItems: 'center' },
  modalDoneText: { color: '#fff', fontWeight: '700', fontSize: S.md },
});
