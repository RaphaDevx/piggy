import { useState, useCallback } from 'react';
import {
  View, Text, TextInput, TouchableOpacity, StyleSheet,
  ActivityIndicator, KeyboardAvoidingView, Platform, Modal,
} from 'react-native';
import { useRouter } from 'expo-router';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { CameraView, useCameraPermissions } from 'expo-camera';
import { Ionicons } from '@/components/Ionicons';
import { useFriends } from '../../src/hooks/useFriends';
import { C, R, S, card } from '../../src/constants/design';
import type { Profile } from '../../src/types/social';

export default function AddFriendScreen() {
  const insets  = useSafeAreaInsets();
  const router  = useRouter();
  const { sendRequest } = useFriends();

  const [code, setCode]         = useState('');
  const [loading, setLoading]   = useState(false);
  const [error, setError]       = useState('');
  const [success, setSuccess]   = useState<Profile | null>(null);
  const [scanning, setScanning] = useState(false);
  const [scanned, setScanned]   = useState(false);

  const [permission, requestPermission] = useCameraPermissions();

  async function handleSend(overrideCode?: string) {
    const raw = (overrideCode ?? code).trim().toUpperCase();
    const normalized = raw.startsWith('PIG-') ? raw : `PIG-${raw.replace(/^PIG-?/, '')}`;
    if (!normalized) return;
    setLoading(true);
    setError('');
    setSuccess(null);
    const result = await sendRequest(normalized);
    if (result.error) setError(result.error);
    else if (result.profile) setSuccess(result.profile);
    setLoading(false);
  }

  const handleBarcode = useCallback(({ data }: { data: string }) => {
    if (scanned) return;
    setScanned(true);

    // Parse piggy://add/PIG-XXXXXXXX
    const match = data.match(/piggy:\/\/add\/(PIG-[A-Z0-9]+)/i);
    const extracted = match ? match[1].toUpperCase() : data.trim().toUpperCase();

    setScanning(false);
    setCode(extracted);
    setScanned(false);
    handleSend(extracted);
  }, [scanned]);

  async function openScanner() {
    if (!permission?.granted) {
      const { granted } = await requestPermission();
      if (!granted) { setError('Kamerazugriff benötigt zum Scannen.'); return; }
    }
    setScanned(false);
    setScanning(true);
  }

  return (
    <KeyboardAvoidingView style={{ flex: 1 }} behavior={Platform.OS === 'ios' ? 'padding' : undefined}>
      <View style={[styles.container, { paddingTop: insets.top }]}>

        {/* Header */}
        <View style={styles.header}>
          <TouchableOpacity onPress={() => router.back()} style={styles.closeBtn}>
            <Ionicons name="close" size={24} color={C.textPrimary} />
          </TouchableOpacity>
          <Text style={styles.title}>Freund hinzufügen</Text>
        </View>

        <View style={styles.body}>
          <Text style={styles.hint}>
            Gib den Piggy-Code ein (z. B. <Text style={{ color: C.gold }}>PIG-4A3F9E1B</Text>) oder scanne den QR-Code.
          </Text>

          {/* Input */}
          <View style={styles.inputRow}>
            <Ionicons name="person-outline" size={20} color={C.textTertiary} style={{ marginLeft: 14 }} />
            <TextInput
              style={styles.input}
              placeholder="PIG-XXXXXXXX"
              placeholderTextColor={C.textTertiary}
              value={code}
              onChangeText={(v) => { setCode(v); setError(''); setSuccess(null); }}
              autoCapitalize="characters"
              autoCorrect={false}
              returnKeyType="search"
              onSubmitEditing={() => handleSend()}
            />
            {code.length > 0 && (
              <TouchableOpacity onPress={() => { setCode(''); setError(''); setSuccess(null); }} style={{ marginRight: 8 }}>
                <Ionicons name="close-circle" size={20} color={C.textTertiary} />
              </TouchableOpacity>
            )}
          </View>

          {/* Error */}
          {!!error && (
            <View style={styles.errorBox}>
              <Ionicons name="alert-circle-outline" size={18} color={C.error} />
              <Text style={styles.errorText}>{error}</Text>
            </View>
          )}

          {/* Success */}
          {success && (
            <View style={[card, styles.successBox]}>
              <View style={styles.avatar}>
                <Text style={styles.avatarText}>
                  {(success.display_name ?? success.piggy_code).charAt(0).toUpperCase()}
                </Text>
              </View>
              <View style={{ flex: 1 }}>
                <Text style={styles.successName}>{success.display_name ?? success.piggy_code}</Text>
                <Text style={styles.successSub}>{success.piggy_code} · Anfrage gesendet ✓</Text>
              </View>
              <Ionicons name="checkmark-circle" size={24} color={C.success} />
            </View>
          )}

          {/* Send Button */}
          <TouchableOpacity
            style={[styles.primaryBtn, (!code.trim() || loading) && styles.btnDisabled]}
            onPress={() => handleSend()}
            disabled={!code.trim() || loading}
          >
            {loading
              ? <ActivityIndicator color="#fff" />
              : <Text style={styles.primaryBtnText}>Anfrage senden</Text>}
          </TouchableOpacity>

          {/* Divider */}
          <View style={styles.dividerRow}>
            <View style={styles.dividerLine} />
            <Text style={styles.dividerText}>oder</Text>
            <View style={styles.dividerLine} />
          </View>

          {/* QR Scan Button */}
          <TouchableOpacity style={[card, styles.scanBtn]} onPress={openScanner}>
            <View style={styles.scanIcon}>
              <Ionicons name="qr-code-outline" size={28} color={C.gold} />
            </View>
            <View style={{ flex: 1 }}>
              <Text style={styles.scanTitle}>QR-Code scannen</Text>
              <Text style={styles.scanSub}>Kamera öffnen und QR-Code des Freundes scannen</Text>
            </View>
            <Ionicons name="chevron-forward" size={18} color={C.textTertiary} />
          </TouchableOpacity>
        </View>
      </View>

      {/* QR Scanner Modal */}
      <Modal visible={scanning} animationType="slide" onRequestClose={() => setScanning(false)}>
        <View style={styles.scannerContainer}>
          <CameraView
            style={StyleSheet.absoluteFill}
            facing="back"
            barcodeScannerSettings={{ barcodeTypes: ['qr'] }}
            onBarcodeScanned={handleBarcode}
          />

          {/* Overlay */}
          <View style={styles.scannerOverlay}>
            <View style={styles.scannerTop} />
            <View style={styles.scannerMiddle}>
              <View style={styles.scannerSide} />
              <View style={styles.scannerFrame}>
                <View style={[styles.corner, styles.cornerTL]} />
                <View style={[styles.corner, styles.cornerTR]} />
                <View style={[styles.corner, styles.cornerBL]} />
                <View style={[styles.corner, styles.cornerBR]} />
              </View>
              <View style={styles.scannerSide} />
            </View>
            <View style={styles.scannerBottom}>
              <Text style={styles.scannerHint}>QR-Code in den Rahmen halten</Text>
              <TouchableOpacity style={styles.cancelScanBtn} onPress={() => setScanning(false)}>
                <Text style={styles.cancelScanText}>Abbrechen</Text>
              </TouchableOpacity>
            </View>
          </View>
        </View>
      </Modal>
    </KeyboardAvoidingView>
  );
}

const FRAME = 220;
const CORNER = 24;
const BORDER = 4;

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: C.bg },
  header:    { flexDirection: 'row', alignItems: 'center', paddingHorizontal: 20, paddingVertical: 16, gap: 12 },
  closeBtn:  { width: 40, height: 40, borderRadius: 20, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  title:     { fontSize: S.xl, fontWeight: '800', color: C.textPrimary, letterSpacing: -0.5 },

  body: { paddingHorizontal: 20, gap: 16, flex: 1 },
  hint: { fontSize: S.sm, color: C.textTertiary, lineHeight: 20 },

  inputRow: {
    flexDirection: 'row', alignItems: 'center',
    backgroundColor: C.bgAccent, borderRadius: R.md,
    borderWidth: 1, borderColor: C.borderSoft, gap: 10,
  },
  input: { flex: 1, fontSize: S.md, color: C.textPrimary, paddingVertical: 14, paddingRight: 4, fontWeight: '600', letterSpacing: 1 },

  errorBox:  { flexDirection: 'row', alignItems: 'center', gap: 8, padding: 12, backgroundColor: '#FF3B3012', borderRadius: R.sm },
  errorText: { color: C.error, fontSize: S.sm, flex: 1 },

  successBox:  { flexDirection: 'row', alignItems: 'center', gap: 14, padding: 14 },
  avatar:      { width: 44, height: 44, borderRadius: 22, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  avatarText:  { fontSize: S.lg, fontWeight: '700', color: C.gold },
  successName: { fontSize: S.md, fontWeight: '600', color: C.textPrimary },
  successSub:  { fontSize: S.xs, color: C.textTertiary, marginTop: 2 },

  primaryBtn:      { backgroundColor: C.gold, borderRadius: R.md, paddingVertical: 16, alignItems: 'center' },
  btnDisabled:     { opacity: 0.4 },
  primaryBtnText:  { color: '#fff', fontWeight: '700', fontSize: S.md },

  dividerRow:  { flexDirection: 'row', alignItems: 'center', gap: 12 },
  dividerLine: { flex: 1, height: 1, backgroundColor: C.borderSoft },
  dividerText: { fontSize: S.xs, color: C.textTertiary, fontWeight: '600' },

  scanBtn:   { flexDirection: 'row', alignItems: 'center', gap: 14, padding: 16 },
  scanIcon:  { width: 48, height: 48, borderRadius: R.md, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  scanTitle: { fontSize: S.md, fontWeight: '700', color: C.textPrimary },
  scanSub:   { fontSize: S.xs, color: C.textTertiary, marginTop: 2 },

  // Scanner Modal
  scannerContainer: { flex: 1, backgroundColor: '#000' },
  scannerOverlay:   { ...StyleSheet.absoluteFillObject, flexDirection: 'column' },
  scannerTop:       { flex: 1, backgroundColor: 'rgba(0,0,0,0.6)' },
  scannerMiddle:    { height: FRAME, flexDirection: 'row' },
  scannerSide:      { flex: 1, backgroundColor: 'rgba(0,0,0,0.6)' },
  scannerFrame:     { width: FRAME, height: FRAME },
  scannerBottom:    { flex: 1, backgroundColor: 'rgba(0,0,0,0.6)', alignItems: 'center', paddingTop: 32, gap: 20 },
  scannerHint:      { color: '#fff', fontSize: S.sm, fontWeight: '600', opacity: 0.85 },
  cancelScanBtn:    { paddingVertical: 12, paddingHorizontal: 32, backgroundColor: 'rgba(255,255,255,0.15)', borderRadius: R.xl },
  cancelScanText:   { color: '#fff', fontWeight: '700', fontSize: S.md },

  // Corner markers
  corner:     { position: 'absolute', width: CORNER, height: CORNER, borderColor: '#fff', borderWidth: BORDER },
  cornerTL:   { top: 0, left: 0, borderRightWidth: 0, borderBottomWidth: 0, borderTopLeftRadius: 6 },
  cornerTR:   { top: 0, right: 0, borderLeftWidth: 0, borderBottomWidth: 0, borderTopRightRadius: 6 },
  cornerBL:   { bottom: 0, left: 0, borderRightWidth: 0, borderTopWidth: 0, borderBottomLeftRadius: 6 },
  cornerBR:   { bottom: 0, right: 0, borderLeftWidth: 0, borderTopWidth: 0, borderBottomRightRadius: 6 },
});
