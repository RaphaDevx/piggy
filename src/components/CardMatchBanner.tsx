import { useState } from 'react';
import { View, Text, TouchableOpacity, StyleSheet, Modal, ActivityIndicator, Alert } from 'react-native';
import { Ionicons } from '@/components/Ionicons';
import { useCardMatching, sendReceipt } from '../hooks/useSharedReceipts';
import { useFriends } from '../hooks/useFriends';
import { C, R, S, card } from '../constants/design';
import type { Profile } from '../types/social';

interface Props {
  receiptId: string;
  paymentCard: string | null;
}

export function CardMatchBanner({ receiptId, paymentCard }: Props) {
  const matches   = useCardMatching(paymentCard);
  const { friends } = useFriends();
  const [sending, setSending]   = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [sent, setSent]         = useState<Set<string>>(new Set());

  if (!paymentCard) return null;

  // Always show send option even without auto-match
  const lastFour = paymentCard.replace(/\s/g, '').match(/(\d{4})$/)?.[1] ?? null;

  async function handleSend(profile: Profile) {
    setSending(true);
    const { error } = await sendReceipt(receiptId, profile.id);
    setSending(false);
    if (error) { Alert.alert('Fehler', error); return; }
    setSent((prev) => new Set([...prev, profile.id]));
    Alert.alert('Gesendet', `Quittung wurde an ${profile.display_name ?? profile.piggy_code} gesendet.`);
  }

  return (
    <>
      {/* Auto-match banner */}
      {matches.map((m) => (
        <View key={m.profile.id} style={[card, styles.matchBanner]}>
          <View style={styles.matchIcon}>
            <Ionicons name="card-outline" size={20} color={C.gold} />
          </View>
          <View style={{ flex: 1 }}>
            <Text style={styles.matchTitle}>
              Möglicherweise von <Text style={{ color: C.gold }}>{m.profile.display_name ?? m.profile.piggy_code}</Text>
            </Text>
            <Text style={styles.matchSub}>Karte ···· {m.last_four} passt zu diesem Konto</Text>
          </View>
          {sent.has(m.profile.id) ? (
            <View style={styles.sentBadge}>
              <Ionicons name="checkmark" size={14} color={C.success} />
              <Text style={styles.sentText}>Gesendet</Text>
            </View>
          ) : (
            <TouchableOpacity
              style={styles.sendBtn}
              onPress={() => handleSend(m.profile)}
              disabled={sending}
            >
              {sending ? <ActivityIndicator size="small" color="#fff" /> : <Text style={styles.sendBtnText}>Senden</Text>}
            </TouchableOpacity>
          )}
        </View>
      ))}

      {/* Manual send button (always available if has card info) */}
      {lastFour && friends.length > 0 && (
        <TouchableOpacity style={[card, styles.manualSendRow]} onPress={() => setShowModal(true)}>
          <Ionicons name="paper-plane-outline" size={18} color={C.gold} />
          <Text style={styles.manualSendText}>Quittung an Freund senden</Text>
          <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
        </TouchableOpacity>
      )}

      {/* Friend picker modal */}
      <Modal visible={showModal} transparent animationType="slide" onRequestClose={() => setShowModal(false)}>
        <View style={styles.overlay}>
          <View style={[card, styles.modalBox]}>
            <Text style={styles.modalTitle}>An wen senden?</Text>
            <Text style={styles.modalSub}>Karte ···· {lastFour}</Text>
            {friends.map((f) => {
              const name = f.profile.display_name ?? f.profile.piggy_code;
              const isSent = sent.has(f.profile.id);
              return (
                <TouchableOpacity
                  key={f.id}
                  style={styles.friendRow}
                  onPress={async () => {
                    setShowModal(false);
                    await handleSend(f.profile);
                  }}
                  disabled={isSent}
                >
                  <View style={styles.avatar}>
                    <Text style={styles.avatarText}>{name.charAt(0).toUpperCase()}</Text>
                  </View>
                  <Text style={[styles.friendName, isSent && { color: C.textTertiary }]}>{name}</Text>
                  {isSent && <Text style={styles.sentLabel}>✓ Gesendet</Text>}
                </TouchableOpacity>
              );
            })}
            <TouchableOpacity style={styles.cancelBtn} onPress={() => setShowModal(false)}>
              <Text style={styles.cancelText}>Abbrechen</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>
    </>
  );
}

const styles = StyleSheet.create({
  matchBanner:   { flexDirection: 'row', alignItems: 'center', padding: 14, gap: 12 },
  matchIcon:     { width: 38, height: 38, borderRadius: R.sm, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  matchTitle:    { fontSize: S.sm, fontWeight: '700', color: C.textPrimary },
  matchSub:      { fontSize: S.xs, color: C.textTertiary, marginTop: 2 },
  sendBtn:       { backgroundColor: C.gold, paddingHorizontal: 14, paddingVertical: 7, borderRadius: R.sm },
  sendBtnText:   { color: '#fff', fontWeight: '700', fontSize: S.xs },
  sentBadge:     { flexDirection: 'row', alignItems: 'center', gap: 4 },
  sentText:      { fontSize: S.xs, color: C.success, fontWeight: '600' },

  manualSendRow: { flexDirection: 'row', alignItems: 'center', gap: 12, padding: 14 },
  manualSendText:{ flex: 1, fontSize: S.sm, fontWeight: '600', color: C.textPrimary },

  overlay:    { flex: 1, backgroundColor: 'rgba(0,0,0,0.4)', justifyContent: 'flex-end' },
  modalBox:   { margin: 16, padding: 24, gap: 4, borderRadius: R.xl },
  modalTitle: { fontSize: S.lg, fontWeight: '800', color: C.textPrimary, marginBottom: 2 },
  modalSub:   { fontSize: S.xs, color: C.textTertiary, marginBottom: 12 },
  friendRow:  { flexDirection: 'row', alignItems: 'center', gap: 12, paddingVertical: 12, borderBottomWidth: 1, borderBottomColor: C.borderSoft },
  avatar:     { width: 36, height: 36, borderRadius: 18, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  avatarText: { fontSize: S.sm, fontWeight: '700', color: C.gold },
  friendName: { flex: 1, fontSize: S.md, fontWeight: '600', color: C.textPrimary },
  sentLabel:  { fontSize: S.xs, color: C.success, fontWeight: '600' },
  cancelBtn:  { marginTop: 8, paddingVertical: 14, alignItems: 'center', backgroundColor: C.bgAccent, borderRadius: R.md },
  cancelText: { fontSize: S.sm, fontWeight: '700', color: C.textSecondary },
});
