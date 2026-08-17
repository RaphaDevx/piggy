import { useCallback, useState } from 'react';
import {
  View, Text, TouchableOpacity, StyleSheet, Alert, ActivityIndicator, Share,
  ScrollView, TextInput, Modal, KeyboardAvoidingView, Platform,
} from 'react-native';
import { useFocusEffect, useRouter } from 'expo-router';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import QRCode from 'react-native-qrcode-svg';
import { Ionicons } from '@/components/Ionicons';
import { supabase } from '../../src/lib/supabase';
import { runGoogleOAuth } from '../../src/lib/googleAuth';
import { C, R, S, card } from '../../src/constants/design';
import { useProfile } from '../../src/hooks/useProfile';
import { useFriends } from '../../src/hooks/useFriends';
import { useProfileCards } from '../../src/hooks/useSharedReceipts';
import { Toast } from '../../src/components/Toast';

export default function ProfilScreen() {
  const insets  = useSafeAreaInsets();
  const router  = useRouter();
  const [email, setEmail]   = useState('');
  const [loading, setLoading] = useState(false);
  const [googleLinked, setGoogleLinked] = useState(false);
  const [identityCount, setIdentityCount] = useState(0);
  const [googleLoading, setGoogleLoading] = useState(false);

  // Display-Name editing
  const [editingName, setEditingName] = useState(false);
  const [nameInput, setNameInput]     = useState('');
  const [nameSaving, setNameSaving]   = useState(false);

  // Toast for incoming requests
  const [toast, setToast] = useState('');

  // Card management
  const [showCardModal, setShowCardModal] = useState(false);
  const [cardInput, setCardInput]         = useState('');
  const [cardLabel, setCardLabel]         = useState('');
  const [cardSaving, setCardSaving]       = useState(false);

  const { profile, reload: reloadProfile, updateDisplayName } = useProfile();
  const { friends, incoming } = useFriends((msg) => setToast(msg));
  const { cards, addCard, removeCard } = useProfileCards();

  const loadAccount = useCallback(() => {
    supabase.auth.getUser().then(({ data }) => {
      setEmail(data.user?.email ?? '');
    });
    supabase.auth.getUserIdentities().then(({ data }) => {
      const identities = data?.identities ?? [];
      setIdentityCount(identities.length);
      setGoogleLinked(identities.some((i) => i.provider === 'google'));
    });
    reloadProfile();
  }, [reloadProfile]);

  useFocusEffect(useCallback(() => { loadAccount(); }, [loadAccount]));

  async function logout() {
    Alert.alert('Abmelden', 'Wirklich abmelden?', [
      { text: 'Abbrechen', style: 'cancel' },
      {
        text: 'Abmelden', style: 'destructive',
        onPress: async () => {
          setLoading(true);
          await supabase.auth.signOut();
          setLoading(false);
        },
      },
    ]);
  }

  async function saveName() {
    const trimmed = nameInput.trim();
    if (!trimmed) return;
    setNameSaving(true);
    await updateDisplayName(trimmed);
    setNameSaving(false);
    setEditingName(false);
  }

  function openEditName() {
    setNameInput(profile?.display_name ?? '');
    setEditingName(true);
  }

  async function linkGoogle() {
    setGoogleLoading(true);
    try {
      const { error } = await runGoogleOAuth('link');
      if (error) Alert.alert('Fehler', error);
      else loadAccount();
    } finally {
      setGoogleLoading(false);
    }
  }

  async function unlinkGoogle() {
    Alert.alert('Google trennen', 'Verknüpfung mit Google wirklich entfernen?', [
      { text: 'Abbrechen', style: 'cancel' },
      {
        text: 'Trennen', style: 'destructive',
        onPress: async () => {
          setGoogleLoading(true);
          try {
            const { data } = await supabase.auth.getUserIdentities();
            const googleIdentity = data?.identities.find((i) => i.provider === 'google');
            if (!googleIdentity) return;
            const { error } = await supabase.auth.unlinkIdentity(googleIdentity);
            if (error) Alert.alert('Fehler', error.message);
            else loadAccount();
          } finally {
            setGoogleLoading(false);
          }
        },
      },
    ]);
  }

  const piggyCode   = profile?.piggy_code ?? '';
  const displayName = profile?.display_name;
  const qrValue     = piggyCode ? `piggy://add/${piggyCode}` : 'piggy://loading';

  async function shareCode() {
    if (!piggyCode) return;
    const name = displayName ? `${displayName} (${piggyCode})` : piggyCode;
    await Share.share({ message: `Füge mich auf Piggy hinzu! Mein Code: ${name}` });
  }

  return (
    <ScrollView style={{ flex: 1, backgroundColor: C.bg }} contentContainerStyle={[styles.container, { paddingTop: insets.top }]}>
      <View style={styles.headerRow}>
        <Text style={styles.title}>Profil</Text>
        <Text style={styles.piggy}>🐷</Text>
      </View>

      {/* Toast */}
      {!!toast && <Toast message={toast} onDone={() => setToast('')} />}

      {/* Piggy-Code & QR */}
      {piggyCode ? (
        <View style={styles.section}>
          <Text style={styles.sectionLabel}>Dein Piggy-Code</Text>
          <View style={[card, styles.qrCard]}>
            <View style={styles.qrBox}>
              <QRCode value={qrValue} size={140} backgroundColor="transparent" color={C.textPrimary} />
            </View>

            {/* Display Name */}
            <TouchableOpacity style={styles.nameRow} onPress={openEditName}>
              <Text style={styles.displayName}>{displayName ?? piggyCode}</Text>
              <Ionicons name="pencil-outline" size={16} color={C.textTertiary} />
            </TouchableOpacity>
            {displayName && <Text style={styles.piggyCode}>{piggyCode}</Text>}

            <Text style={styles.qrSub}>Lass Freunde diesen Code scannen oder teile ihn direkt.</Text>
            <TouchableOpacity style={styles.shareBtn} onPress={shareCode}>
              <Ionicons name="share-outline" size={18} color={C.gold} />
              <Text style={styles.shareBtnText}>Code teilen</Text>
            </TouchableOpacity>
          </View>
        </View>
      ) : null}

      {/* Edit Name Modal */}
      <Modal visible={editingName} transparent animationType="fade" onRequestClose={() => setEditingName(false)}>
        <KeyboardAvoidingView style={styles.modalOverlay} behavior={Platform.OS === 'ios' ? 'padding' : undefined}>
          <View style={[card, styles.modalBox]}>
            <Text style={styles.modalTitle}>Name bearbeiten</Text>
            <TextInput
              style={styles.modalInput}
              value={nameInput}
              onChangeText={setNameInput}
              placeholder="Dein Name (z.B. Raphael)"
              placeholderTextColor={C.textTertiary}
              autoFocus
              returnKeyType="done"
              onSubmitEditing={saveName}
              maxLength={40}
            />
            <View style={styles.modalBtns}>
              <TouchableOpacity style={styles.modalCancel} onPress={() => setEditingName(false)}>
                <Text style={styles.modalCancelText}>Abbrechen</Text>
              </TouchableOpacity>
              <TouchableOpacity style={[styles.modalSave, (!nameInput.trim() || nameSaving) && styles.btnDisabled]} onPress={saveName} disabled={!nameInput.trim() || nameSaving}>
                {nameSaving
                  ? <ActivityIndicator color="#fff" size="small" />
                  : <Text style={styles.modalSaveText}>Speichern</Text>}
              </TouchableOpacity>
            </View>
          </View>
        </KeyboardAvoidingView>
      </Modal>

      {/* Freunde */}
      <View style={styles.section}>
        <Text style={styles.sectionLabel}>Freunde</Text>
        <View style={[card, styles.cardInner]}>
          <TouchableOpacity style={styles.row} onPress={() => router.push('/friends' as any)}>
            <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
              <Ionicons name="people-outline" size={20} color={C.gold} />
            </View>
            <View style={{ flex: 1 }}>
              <Text style={styles.rowText}>Freundesliste</Text>
              <Text style={styles.rowSub}>
                {friends.length} Freund{friends.length !== 1 ? 'e' : ''}
                {incoming.length > 0 ? ` · ${incoming.length} neue Anfrage${incoming.length !== 1 ? 'n' : ''}` : ''}
              </Text>
            </View>
            {incoming.length > 0 && (
              <View style={styles.badge}><Text style={styles.badgeText}>{incoming.length}</Text></View>
            )}
            <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
          </TouchableOpacity>
          <View style={styles.divider} />
          <TouchableOpacity style={styles.row} onPress={() => router.push('/friends/add' as any)}>
            <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
              <Ionicons name="person-add-outline" size={20} color={C.gold} />
            </View>
            <View style={{ flex: 1 }}>
              <Text style={styles.rowText}>Freund hinzufügen</Text>
              <Text style={styles.rowSub}>Per Piggy-Code oder QR-Code</Text>
            </View>
            <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
          </TouchableOpacity>
        </View>
      </View>

      {/* Meine Karten */}
      <View style={styles.section}>
        <Text style={styles.sectionLabel}>Meine Karten</Text>
        <View style={[card, styles.cardInner]}>
          {cards.map((c, idx) => (
            <View key={c.id}>
              <View style={styles.row}>
                <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
                  <Ionicons name="card-outline" size={20} color={C.gold} />
                </View>
                <View style={{ flex: 1 }}>
                  <Text style={styles.rowText}>{c.label || `Karte ···· ${c.last_four}`}</Text>
                  <Text style={styles.rowSub}>···· {c.last_four} · sichtbar für Freunde</Text>
                </View>
                <TouchableOpacity onPress={() => Alert.alert('Karte entfernen', `···· ${c.last_four} wirklich entfernen?`, [
                  { text: 'Abbrechen', style: 'cancel' },
                  { text: 'Entfernen', style: 'destructive', onPress: () => removeCard(c.id) },
                ])} hitSlop={8}>
                  <Ionicons name="trash-outline" size={18} color={C.error} />
                </TouchableOpacity>
              </View>
              {idx < cards.length - 1 && <View style={styles.divider} />}
            </View>
          ))}
          {cards.length > 0 && <View style={styles.divider} />}
          <TouchableOpacity style={styles.row} onPress={() => { setCardInput(''); setCardLabel(''); setShowCardModal(true); }}>
            <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
              <Ionicons name="add-circle-outline" size={20} color={C.gold} />
            </View>
            <View style={{ flex: 1 }}>
              <Text style={styles.rowText}>Karte hinzufügen</Text>
              <Text style={styles.rowSub}>Letzte 4 Ziffern — Freunde können dich erkennen</Text>
            </View>
            <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
          </TouchableOpacity>
        </View>
      </View>

      {/* Card Add Modal */}
      <Modal visible={showCardModal} transparent animationType="fade" onRequestClose={() => setShowCardModal(false)}>
        <KeyboardAvoidingView style={styles.modalOverlay} behavior={Platform.OS === 'ios' ? 'padding' : undefined}>
          <View style={[card, styles.modalBox]}>
            <Text style={styles.modalTitle}>Karte hinzufügen</Text>
            <TextInput
              style={styles.modalInput}
              value={cardInput}
              onChangeText={(v) => setCardInput(v.replace(/\D/g, '').slice(0, 4))}
              placeholder="Letzte 4 Ziffern (z.B. 5327)"
              placeholderTextColor={C.textTertiary}
              keyboardType="number-pad"
              maxLength={4}
              autoFocus
            />
            <TextInput
              style={[styles.modalInput, { marginTop: 8 }]}
              value={cardLabel}
              onChangeText={setCardLabel}
              placeholder="Bezeichnung (z.B. Visa, optional)"
              placeholderTextColor={C.textTertiary}
              returnKeyType="done"
            />
            <Text style={{ fontSize: S.xs, color: C.textTertiary, marginTop: 4 }}>
              Nur die letzten 4 Ziffern werden gespeichert — kein Sicherheitsrisiko.
            </Text>
            <View style={styles.modalBtns}>
              <TouchableOpacity style={styles.modalCancel} onPress={() => setShowCardModal(false)}>
                <Text style={styles.modalCancelText}>Abbrechen</Text>
              </TouchableOpacity>
              <TouchableOpacity
                style={[styles.modalSave, (cardInput.length !== 4 || cardSaving) && styles.btnDisabled]}
                disabled={cardInput.length !== 4 || cardSaving}
                onPress={async () => {
                  setCardSaving(true);
                  const { error } = await addCard(cardInput, cardLabel);
                  setCardSaving(false);
                  if (error) Alert.alert('Fehler', error);
                  else setShowCardModal(false);
                }}
              >
                {cardSaving
                  ? <ActivityIndicator color="#fff" size="small" />
                  : <Text style={styles.modalSaveText}>Speichern</Text>}
              </TouchableOpacity>
            </View>
          </View>
        </KeyboardAvoidingView>
      </Modal>

      {/* Konto */}
      <View style={styles.section}>
        <Text style={styles.sectionLabel}>Konto</Text>
        <View style={[card, styles.cardInner]}>
          <View style={styles.row}>
            <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
              <Ionicons name="person-outline" size={20} color={C.gold} />
            </View>
            <Text style={styles.rowText} numberOfLines={1}>{email || '—'}</Text>
          </View>
          <View style={styles.divider} />
          <TouchableOpacity style={styles.row} onPress={logout} disabled={loading}>
            <View style={[styles.iconBox, { backgroundColor: '#FF3B3012' }]}>
              {loading
                ? <ActivityIndicator size="small" color={C.error} />
                : <Ionicons name="log-out-outline" size={20} color={C.error} />}
            </View>
            <Text style={[styles.rowText, { color: C.error }]}>Abmelden</Text>
            <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
          </TouchableOpacity>
        </View>
      </View>

      {/* Verbundene Konten */}
      <View style={styles.section}>
        <Text style={styles.sectionLabel}>Verbundene Konten</Text>
        <View style={[card, styles.cardInner]}>
          {googleLinked ? (
            <TouchableOpacity style={styles.row} onPress={unlinkGoogle} disabled={googleLoading || identityCount <= 1}>
              <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
                {googleLoading
                  ? <ActivityIndicator size="small" color={C.gold} />
                  : <Ionicons name="logo-google" size={20} color="#EA4335" />}
              </View>
              <View style={{ flex: 1 }}>
                <Text style={styles.rowText}>Google</Text>
                <Text style={styles.rowSub}>
                  {identityCount > 1 ? 'Verknüpft — tippen zum Trennen' : 'Verknüpft (einzige Anmeldemethode)'}
                </Text>
              </View>
              {identityCount > 1 && <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />}
            </TouchableOpacity>
          ) : (
            <TouchableOpacity style={styles.row} onPress={linkGoogle} disabled={googleLoading}>
              <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
                {googleLoading
                  ? <ActivityIndicator size="small" color={C.gold} />
                  : <Ionicons name="logo-google" size={20} color="#EA4335" />}
              </View>
              <View style={{ flex: 1 }}>
                <Text style={styles.rowText}>Mit Google verknüpfen</Text>
                <Text style={styles.rowSub}>Künftig auch mit Google anmelden</Text>
              </View>
              <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
            </TouchableOpacity>
          )}
        </View>
      </View>

      {/* App-Info */}
      <View style={styles.section}>
        <Text style={styles.sectionLabel}>App</Text>
        <View style={[card, styles.cardInner]}>
          <View style={styles.row}>
            <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
              <Text style={{ fontSize: 18 }}>🐷</Text>
            </View>
            <View style={{ flex: 1 }}>
              <Text style={styles.rowText}>Piggy</Text>
              <Text style={styles.rowSub}>Version 1.0.0</Text>
            </View>
          </View>
          <View style={styles.divider} />
          <View style={styles.row}>
            <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
              <Ionicons name="sparkles-outline" size={20} color={C.gold} />
            </View>
            <View style={{ flex: 1 }}>
              <Text style={styles.rowText}>Claude Vision AI</Text>
              <Text style={styles.rowSub}>Powered by Anthropic</Text>
            </View>
          </View>
          <View style={styles.divider} />
          <View style={styles.row}>
            <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
              <Ionicons name="shield-checkmark-outline" size={20} color={C.gold} />
            </View>
            <View style={{ flex: 1 }}>
              <Text style={styles.rowText}>Datenschutz</Text>
              <Text style={styles.rowSub}>Daten in der Schweiz (eu-central-2)</Text>
            </View>
          </View>
        </View>
      </View>

      <Text style={styles.footer}>Deine Daten werden sicher in der Schweiz gespeichert.</Text>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: { backgroundColor: C.bg, padding: 20, gap: 24, paddingBottom: 40 },

  headerRow: { flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between' },
  title:     { fontSize: S.xxl, fontWeight: '800', color: C.textPrimary, letterSpacing: -0.5 },
  piggy:     { fontSize: 36 },

  section:      { gap: 8 },
  sectionLabel: { fontSize: S.xs, color: C.textTertiary, fontWeight: '700', letterSpacing: 1, textTransform: 'uppercase', paddingHorizontal: 4 },

  cardInner: { padding: 0, overflow: 'hidden' },
  row:  { flexDirection: 'row', alignItems: 'center', paddingHorizontal: 16, paddingVertical: 14, gap: 14 },
  iconBox: { width: 38, height: 38, borderRadius: R.sm, alignItems: 'center', justifyContent: 'center' },
  rowText: { color: C.textPrimary, fontSize: S.md, fontWeight: '600', flex: 1 },
  rowSub:  { color: C.textTertiary, fontSize: S.xs, marginTop: 2 },
  divider: { height: 1, backgroundColor: C.borderSoft, marginLeft: 68 },

  footer: { color: C.textTertiary, fontSize: S.xs, textAlign: 'center', marginBottom: 16 },

  qrCard:      { alignItems: 'center', padding: 24, gap: 12 },
  qrBox:       { padding: 16, backgroundColor: C.bgAccent, borderRadius: R.lg },
  nameRow:     { flexDirection: 'row', alignItems: 'center', gap: 6 },
  displayName: { fontSize: S.xl, fontWeight: '800', color: C.textPrimary, letterSpacing: -0.3 },
  piggyCode:   { fontSize: S.sm, color: C.textTertiary, fontWeight: '600', letterSpacing: 1 },
  qrSub:       { fontSize: S.xs, color: C.textTertiary, textAlign: 'center', maxWidth: 240 },
  shareBtn:    { flexDirection: 'row', alignItems: 'center', gap: 8, paddingVertical: 10, paddingHorizontal: 20, backgroundColor: C.bgAccent, borderRadius: R.md },
  shareBtnText:{ fontSize: S.sm, fontWeight: '700', color: C.gold },

  badge:     { minWidth: 20, height: 20, borderRadius: 10, backgroundColor: C.error, alignItems: 'center', justifyContent: 'center', paddingHorizontal: 5 },
  badgeText: { fontSize: 11, fontWeight: '700', color: '#fff' },

  modalOverlay: { flex: 1, backgroundColor: 'rgba(0,0,0,0.4)', justifyContent: 'center', padding: 24 },
  modalBox:     { padding: 24, gap: 16 },
  modalTitle:   { fontSize: S.lg, fontWeight: '800', color: C.textPrimary },
  modalInput:   { borderWidth: 1, borderColor: C.border, borderRadius: R.md, paddingHorizontal: 14, paddingVertical: 12, fontSize: S.md, color: C.textPrimary, backgroundColor: C.bgAccent },
  modalBtns:    { flexDirection: 'row', gap: 10 },
  modalCancel:  { flex: 1, paddingVertical: 13, alignItems: 'center', borderRadius: R.md, backgroundColor: C.bgAccent },
  modalCancelText: { fontWeight: '700', fontSize: S.sm, color: C.textSecondary },
  modalSave:    { flex: 1, paddingVertical: 13, alignItems: 'center', borderRadius: R.md, backgroundColor: C.gold },
  modalSaveText:{ fontWeight: '700', fontSize: S.sm, color: '#fff' },
  btnDisabled:  { opacity: 0.4 },
});
