import { useCallback, useEffect, useState } from 'react';
import {
  View, Text, TouchableOpacity, StyleSheet, Alert, ActivityIndicator, Share,
  ScrollView, TextInput, Modal, KeyboardAvoidingView, Platform, Switch,
} from 'react-native';
import { useFocusEffect, useRouter } from 'expo-router';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import QRCode from 'react-native-qrcode-svg';
import * as AppleAuthentication from 'expo-apple-authentication';
import { Ionicons } from '@/components/Ionicons';
import { supabase } from '../../src/lib/supabase';
import { runGoogleOAuth } from '../../src/lib/googleAuth';
import { linkIdentityWithIdToken } from '../../src/lib/authLinking';
import { C, R, S, card } from '../../src/constants/design';
import { useProfile } from '../../src/hooks/useProfile';
import { useFriends } from '../../src/hooks/useFriends';
import { useProfileCards } from '../../src/hooks/useSharedReceipts';
import { Toast } from '../../src/components/Toast';
import { FoundationModels } from '../../src/native/FoundationModels';

export default function ProfilScreen() {
  const insets  = useSafeAreaInsets();
  const router  = useRouter();
  const [email, setEmail]   = useState('');
  const [loading, setLoading] = useState(false);
  const [googleLinked, setGoogleLinked] = useState(false);
  const [appleLinked, setAppleLinked]   = useState(false);
  const [identityCount, setIdentityCount] = useState(0);
  const [googleLoading, setGoogleLoading] = useState(false);
  const [appleLoading, setAppleLoading]   = useState(false);

  // Claude API Key
  const [claudeKeySet, setClaudeKeySet] = useState(false);
  const [showKeyModal, setShowKeyModal] = useState(false);
  const [keyInput, setKeyInput]         = useState('');
  const [keySaving, setKeySaving]       = useState(false);

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

  // Verarbeitung (processing_mode + gemini_api_key)
  const [processingMode, setProcessingMode] = useState<'edge' | 'on_device'>('edge');
  const [geminiKey, setGeminiKey]           = useState('');
  const [geminiKeySaved, setGeminiKeySaved] = useState(false);
  const [geminiKeySaving, setGeminiKeySaving] = useState(false);
  const [onDeviceAvailable, setOnDeviceAvailable] = useState<boolean | null>(null);

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
      setAppleLinked(identities.some((i) => i.provider === 'apple'));
    });
    supabase.from('user_settings').select('claude_api_key').single().then(({ data }) => {
      setClaudeKeySet(!!data?.claude_api_key);
    });
    // Load processing mode and gemini key from profiles
    supabase.auth.getUser().then(async ({ data: userData }) => {
      if (!userData.user) return;
      const { data } = await supabase
        .from('profiles')
        .select('processing_mode, gemini_api_key')
        .eq('id', userData.user.id)
        .single();
      if (data) {
        setProcessingMode((data.processing_mode as 'edge' | 'on_device') ?? 'edge');
        setGeminiKeySaved(!!data.gemini_api_key);
      }
    });
    reloadProfile();
  }, [reloadProfile]);

  // Check Foundation Models availability on mount
  useEffect(() => {
    FoundationModels.isAvailable().then(setOnDeviceAvailable);
  }, []);

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
            const identity = data?.identities.find((i) => i.provider === 'google');
            if (!identity) return;
            const { error } = await supabase.auth.unlinkIdentity(identity);
            if (error) Alert.alert('Fehler', error.message);
            else loadAccount();
          } finally {
            setGoogleLoading(false);
          }
        },
      },
    ]);
  }

  async function linkApple() {
    setAppleLoading(true);
    try {
      const credential = await AppleAuthentication.signInAsync({
        requestedScopes: [
          AppleAuthentication.AppleAuthenticationScope.FULL_NAME,
          AppleAuthentication.AppleAuthenticationScope.EMAIL,
        ],
      });
      if (!credential.identityToken) return;
      const { error } = await linkIdentityWithIdToken('apple', credential.identityToken);
      if (error) Alert.alert('Fehler', error);
      else { Alert.alert('✅ Apple ID verknüpft', 'Du kannst dich jetzt auch mit Apple anmelden.'); loadAccount(); }
    } catch (e: any) {
      if (e.code !== 'ERR_REQUEST_CANCELED') Alert.alert('Fehler', e.message ?? 'Apple Sign-In fehlgeschlagen');
    } finally {
      setAppleLoading(false);
    }
  }

  async function unlinkApple() {
    Alert.alert('Apple ID trennen', 'Verknüpfung mit Apple ID wirklich entfernen?', [
      { text: 'Abbrechen', style: 'cancel' },
      {
        text: 'Trennen', style: 'destructive',
        onPress: async () => {
          setAppleLoading(true);
          try {
            const { data } = await supabase.auth.getUserIdentities();
            const identity = data?.identities.find((i) => i.provider === 'apple');
            if (!identity) return;
            const { error } = await supabase.auth.unlinkIdentity(identity);
            if (error) Alert.alert('Fehler', error.message);
            else loadAccount();
          } finally {
            setAppleLoading(false);
          }
        },
      },
    ]);
  }

  async function saveApiKey() {
    const trimmed = keyInput.trim();
    if (!trimmed) return;
    setKeySaving(true);
    const { data: { user } } = await supabase.auth.getUser();
    if (user) {
      await supabase.from('user_settings').upsert(
        { user_id: user.id, claude_api_key: trimmed },
        { onConflict: 'user_id' }
      );
      setClaudeKeySet(true);
    }
    setKeySaving(false);
    setShowKeyModal(false);
  }

  async function removeApiKey() {
    Alert.alert('API Key entfernen', 'Claude API Key wirklich löschen? KI-Scanning wird dann deaktiviert.', [
      { text: 'Abbrechen', style: 'cancel' },
      {
        text: 'Entfernen', style: 'destructive',
        onPress: async () => {
          const { data: { user } } = await supabase.auth.getUser();
          if (user) {
            await supabase.from('user_settings').upsert(
              { user_id: user.id, claude_api_key: null },
              { onConflict: 'user_id' }
            );
            setClaudeKeySet(false);
          }
        },
      },
    ]);
  }

  async function saveProcessingMode(mode: 'edge' | 'on_device') {
    setProcessingMode(mode);
    const { data: { user } } = await supabase.auth.getUser();
    if (!user) return;
    await supabase
      .from('profiles')
      .update({ processing_mode: mode })
      .eq('id', user.id);
  }

  async function saveGeminiKey() {
    const trimmed = geminiKey.trim();
    if (!trimmed) return;
    setGeminiKeySaving(true);
    const { data: { user } } = await supabase.auth.getUser();
    if (user) {
      await supabase
        .from('profiles')
        .update({ gemini_api_key: trimmed })
        .eq('id', user.id);
      setGeminiKeySaved(true);
      setGeminiKey('');
    }
    setGeminiKeySaving(false);
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

      {/* Verarbeitung */}
      <View style={styles.section}>
        <Text style={styles.sectionLabel}>Verarbeitung</Text>
        <View style={[card, styles.cardInner]}>

          {/* Edge Computing */}
          <TouchableOpacity style={styles.row} onPress={() => saveProcessingMode('edge')}>
            <View style={[styles.iconBox, { backgroundColor: processingMode === 'edge' ? '#FF9F0A22' : C.bgAccent }]}>
              <Ionicons name="cloud-outline" size={20} color={processingMode === 'edge' ? C.gold : C.textTertiary} />
            </View>
            <View style={{ flex: 1 }}>
              <Text style={styles.rowText}>Edge Computing (Gemini)</Text>
              <Text style={styles.rowSub}>Quittungen werden via Gemini 2.0 Flash verarbeitet</Text>
            </View>
            {processingMode === 'edge' && (
              <Ionicons name="checkmark-circle" size={20} color={C.gold} />
            )}
          </TouchableOpacity>

          <View style={styles.divider} />

          {/* On-Device */}
          <TouchableOpacity
            style={styles.row}
            onPress={() => {
              if (onDeviceAvailable === false) {
                Alert.alert(
                  'Nicht verfügbar',
                  'On-Device-Verarbeitung erfordert iOS 18.4+ und einen nativen Build.',
                );
                return;
              }
              saveProcessingMode('on_device');
            }}
          >
            <View style={[styles.iconBox, { backgroundColor: processingMode === 'on_device' ? '#34C75912' : C.bgAccent }]}>
              <Ionicons name="phone-portrait-outline" size={20} color={processingMode === 'on_device' ? C.success : C.textTertiary} />
            </View>
            <View style={{ flex: 1 }}>
              <Text style={[styles.rowText, onDeviceAvailable === false && { color: C.textTertiary }]}>
                On-Device (iPhone 15 Pro+)
              </Text>
              <Text style={styles.rowSub}>
                {onDeviceAvailable === false
                  ? 'Erfordert iOS 18.4+ und nativen Build'
                  : 'Kostenlos, privat — läuft vollständig auf dem Gerät'}
              </Text>
            </View>
            {processingMode === 'on_device' && (
              <Ionicons name="checkmark-circle" size={20} color={C.success} />
            )}
          </TouchableOpacity>

          <View style={styles.divider} />

          {/* Gemini API Key */}
          <View style={[styles.row, { alignItems: 'flex-start', paddingVertical: 14 }]}>
            <View style={[styles.iconBox, { backgroundColor: geminiKeySaved ? '#34C75912' : C.bgAccent }]}>
              <Ionicons name="key-outline" size={20} color={geminiKeySaved ? C.success : C.textTertiary} />
            </View>
            <View style={{ flex: 1, gap: 6 }}>
              <Text style={styles.rowText}>Eigener Gemini API Key</Text>
              <Text style={styles.rowSub}>
                {geminiKeySaved ? 'Hinterlegt (BYOK)' : 'Optional — sonst wird Server-Key verwendet'}
              </Text>
              <View style={processingStyles.keyRow}>
                <TextInput
                  style={processingStyles.keyInput}
                  value={geminiKey}
                  onChangeText={setGeminiKey}
                  placeholder="AIza..."
                  placeholderTextColor={C.textTertiary}
                  autoCapitalize="none"
                  autoCorrect={false}
                  secureTextEntry
                />
                <TouchableOpacity
                  style={[processingStyles.keyBtn, (!geminiKey.trim() || geminiKeySaving) && { opacity: 0.4 }]}
                  onPress={saveGeminiKey}
                  disabled={!geminiKey.trim() || geminiKeySaving}
                >
                  {geminiKeySaving
                    ? <ActivityIndicator size="small" color="#fff" />
                    : <Ionicons name="checkmark" size={18} color="#fff" />}
                </TouchableOpacity>
              </View>
            </View>
          </View>

        </View>
      </View>

      {/* KI-Scanner */}
      <View style={styles.section}>
        <Text style={styles.sectionLabel}>KI-Scanner</Text>
        <View style={[card, styles.cardInner]}>
          <TouchableOpacity style={styles.row} onPress={() => { setKeyInput(''); setShowKeyModal(true); }}>
            <View style={[styles.iconBox, { backgroundColor: claudeKeySet ? '#34C75912' : C.bgAccent }]}>
              <Ionicons name="sparkles-outline" size={20} color={claudeKeySet ? '#34C759' : C.textTertiary} />
            </View>
            <View style={{ flex: 1 }}>
              <Text style={styles.rowText}>Claude API Key</Text>
              <Text style={styles.rowSub}>
                {claudeKeySet
                  ? 'Aktiv — Quittungen werden mit Claude KI gescannt'
                  : 'Nicht hinterlegt — tippen zum Einrichten'}
              </Text>
            </View>
            <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
          </TouchableOpacity>
        </View>
      </View>

      {/* Claude Key Modal */}
      <Modal visible={showKeyModal} transparent animationType="fade" onRequestClose={() => setShowKeyModal(false)}>
        <KeyboardAvoidingView style={styles.modalOverlay} behavior={Platform.OS === 'ios' ? 'padding' : undefined}>
          <View style={[card, styles.modalBox]}>
            <Text style={styles.modalTitle}>Claude API Key</Text>
            <Text style={{ fontSize: S.xs, color: C.textTertiary, marginBottom: 4 }}>
              Key von console.anthropic.com — wird sicher auf deinem Account gespeichert, nie lokal auf dem Gerät. Funktioniert automatisch auf allen deinen verbundenen Logins.
            </Text>
            <TextInput
              style={styles.modalInput}
              value={keyInput}
              onChangeText={setKeyInput}
              placeholder="sk-ant-api03-..."
              placeholderTextColor={C.textTertiary}
              autoCapitalize="none"
              autoCorrect={false}
              autoFocus
            />
            <View style={styles.modalBtns}>
              <TouchableOpacity style={styles.modalCancel} onPress={() => setShowKeyModal(false)}>
                <Text style={styles.modalCancelText}>Abbrechen</Text>
              </TouchableOpacity>
              <TouchableOpacity
                style={[styles.modalSave, (!keyInput.trim() || keySaving) && styles.btnDisabled]}
                disabled={!keyInput.trim() || keySaving}
                onPress={saveApiKey}
              >
                {keySaving
                  ? <ActivityIndicator color="#fff" size="small" />
                  : <Text style={styles.modalSaveText}>Speichern</Text>}
              </TouchableOpacity>
            </View>
            {claudeKeySet && (
              <TouchableOpacity onPress={() => { setShowKeyModal(false); removeApiKey(); }} style={{ alignItems: 'center', paddingTop: 4 }}>
                <Text style={{ fontSize: S.xs, color: C.error, fontWeight: '600' }}>Key entfernen</Text>
              </TouchableOpacity>
            )}
          </View>
        </KeyboardAvoidingView>
      </Modal>

      {/* Verbundene Konten */}
      <View style={styles.section}>
        <Text style={styles.sectionLabel}>Verbundene Konten</Text>
        <View style={[card, styles.cardInner]}>

          {/* Google */}
          {googleLinked ? (
            <TouchableOpacity style={styles.row} onPress={unlinkGoogle} disabled={googleLoading || identityCount <= 1}>
              <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
                {googleLoading ? <ActivityIndicator size="small" color={C.gold} /> : <Ionicons name="logo-google" size={20} color="#EA4335" />}
              </View>
              <View style={{ flex: 1 }}>
                <Text style={styles.rowText}>Google</Text>
                <Text style={styles.rowSub}>{identityCount > 1 ? 'Verknüpft — tippen zum Trennen' : 'Verknüpft (einzige Anmeldemethode)'}</Text>
              </View>
              {identityCount > 1 && <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />}
            </TouchableOpacity>
          ) : (
            <TouchableOpacity style={styles.row} onPress={linkGoogle} disabled={googleLoading}>
              <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
                {googleLoading ? <ActivityIndicator size="small" color={C.gold} /> : <Ionicons name="logo-google" size={20} color="#EA4335" />}
              </View>
              <View style={{ flex: 1 }}>
                <Text style={styles.rowText}>Mit Google verknüpfen</Text>
                <Text style={styles.rowSub}>Künftig auch mit Google anmelden</Text>
              </View>
              <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
            </TouchableOpacity>
          )}

          <View style={styles.divider} />

          {/* Apple ID */}
          {Platform.OS === 'ios' && (
            appleLinked ? (
              <TouchableOpacity style={styles.row} onPress={unlinkApple} disabled={appleLoading || identityCount <= 1}>
                <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
                  {appleLoading ? <ActivityIndicator size="small" color={C.gold} /> : <Ionicons name="logo-apple" size={20} color={C.textPrimary} />}
                </View>
                <View style={{ flex: 1 }}>
                  <Text style={styles.rowText}>Apple ID</Text>
                  <Text style={styles.rowSub}>{identityCount > 1 ? 'Verknüpft — tippen zum Trennen' : 'Verknüpft (einzige Anmeldemethode)'}</Text>
                </View>
                {identityCount > 1 && <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />}
              </TouchableOpacity>
            ) : (
              <TouchableOpacity style={styles.row} onPress={linkApple} disabled={appleLoading}>
                <View style={[styles.iconBox, { backgroundColor: C.bgAccent }]}>
                  {appleLoading ? <ActivityIndicator size="small" color={C.gold} /> : <Ionicons name="logo-apple" size={20} color={C.textPrimary} />}
                </View>
                <View style={{ flex: 1 }}>
                  <Text style={styles.rowText}>Mit Apple ID verknüpfen</Text>
                  <Text style={styles.rowSub}>Künftig auch mit Apple anmelden</Text>
                </View>
                <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
              </TouchableOpacity>
            )
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

const processingStyles = StyleSheet.create({
  keyRow: {
    flexDirection: 'row',
    alignItems:    'center',
    gap:           8,
  },
  keyInput: {
    flex:             1,
    borderWidth:      1,
    borderColor:      C.border,
    borderRadius:     R.sm,
    paddingHorizontal: 10,
    paddingVertical:   8,
    fontSize:         S.sm,
    color:            C.textPrimary,
    backgroundColor:  C.bgAccent,
  },
  keyBtn: {
    width:           36,
    height:          36,
    borderRadius:    R.sm,
    backgroundColor: C.gold,
    alignItems:      'center',
    justifyContent:  'center',
  },
});
