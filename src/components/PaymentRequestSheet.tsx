import { useEffect, useState } from 'react';
import { Modal, View, Text, TextInput, TouchableOpacity, ScrollView, Share, Alert, StyleSheet } from 'react-native';
import QRCode from 'react-native-qrcode-svg';
import { C, R, S, card } from '../constants/design';
import { loadPaymentDetails, savePaymentDetails } from '../lib/paymentDetails';
import {
  EMPTY_PAYMENT_DETAILS, buildRequestText, buildSwissQrPayload, hasQrBillDetails, isValidIban, type PaymentDetails,
} from '../lib/paymentRequest';

interface Props {
  visible: boolean;
  onClose: () => void;
  personName: string;
  amount: number;
  currency: string;
  store?: string | null;
  date?: string | null;
}

const FIELDS: { key: keyof PaymentDetails; label: string; placeholder: string }[] = [
  { key: 'name',   label: 'Name',                 placeholder: 'Vorname Nachname' },
  { key: 'street', label: 'Strasse und Nr.',      placeholder: 'Musterweg 1' },
  { key: 'zip',    label: 'PLZ',                  placeholder: '9000' },
  { key: 'town',   label: 'Ort',                  placeholder: 'St. Gallen' },
  { key: 'iban',   label: 'IBAN (CH oder LI)',    placeholder: 'CH00 0000 0000 0000 0000 0' },
  { key: 'hint',   label: 'Hinweis (z. B. TWINT-Nummer)', placeholder: 'TWINT: 079 …' },
];

/** Geld von einer Person anfordern: Text zum Teilen und, mit Angaben, eine Swiss-QR-Rechnung. */
export default function PaymentRequestSheet({ visible, onClose, personName, amount, currency, store, date }: Props) {
  const [details, setDetails] = useState<PaymentDetails>(EMPTY_PAYMENT_DETAILS);
  const [editing, setEditing] = useState(false);

  useEffect(() => {
    if (!visible) return;
    void loadPaymentDetails().then(d => { setDetails(d); setEditing(false); });
  }, [visible]);

  const ibanBad = details.iban.trim() !== '' && !isValidIban(details.iban);
  const qrReady = hasQrBillDetails(details) && (currency === 'CHF' || currency === 'EUR');
  const message = buildRequestText({ store, date, amount, currency, details });

  async function save() {
    if (ibanBad) { Alert.alert('IBAN ungültig', 'Bitte eine Schweizer oder Liechtensteiner IBAN eingeben.'); return; }
    await savePaymentDetails(details);
    setEditing(false);
  }

  async function share() {
    try { await Share.share({ message }); }
    catch { Alert.alert('Teilen nicht möglich', message); }
  }

  return (
    <Modal visible={visible} animationType="slide" transparent onRequestClose={onClose}>
      <View style={styles.overlay}>
        <View style={[card, styles.box]}>
          <Text style={styles.title}>{personName} anfordern</Text>
          <Text style={styles.amount}>{currency} {amount.toFixed(2)}</Text>

          <ScrollView style={{ maxHeight: 420 }} keyboardShouldPersistTaps="handled">
            {editing ? (
              <View style={{ gap: 10 }}>
                {FIELDS.map(f => (
                  <View key={f.key}>
                    <Text style={styles.label}>{f.label}</Text>
                    <TextInput
                      style={[styles.input, f.key === 'iban' && ibanBad && styles.inputBad]}
                      value={details[f.key]}
                      placeholder={f.placeholder}
                      placeholderTextColor={C.textTertiary}
                      autoCapitalize={f.key === 'iban' ? 'characters' : 'sentences'}
                      onChangeText={v => setDetails(d => ({ ...d, [f.key]: v }))}
                    />
                  </View>
                ))}
                <Text style={styles.privacy}>Diese Angaben bleiben nur auf diesem Gerät und werden nicht an Piggy-Server gesendet.</Text>
                <TouchableOpacity style={styles.primary} onPress={() => { void save(); }}>
                  <Text style={styles.primaryText}>Speichern</Text>
                </TouchableOpacity>
              </View>
            ) : (
              <View style={{ gap: 12 }}>
                <Text style={styles.message}>{message}</Text>
                {qrReady ? (
                  <View style={styles.qrWrap}>
                    <QRCode value={buildSwissQrPayload(details, amount, currency as 'CHF' | 'EUR', store ?? 'Piggy')} size={200} ecl="M" quietZone={8} />
                    <View style={styles.cross} pointerEvents="none">
                      <View style={styles.crossV} /><View style={styles.crossH} />
                    </View>
                    <Text style={styles.qrHint}>QR-Rechnung: im E-Banking „QR-Rechnung scannen“ wählen.</Text>
                  </View>
                ) : (
                  <Text style={styles.qrHint}>Für eine QR-Rechnung fehlen Name, PLZ, Ort und eine gültige IBAN.</Text>
                )}
                <TouchableOpacity style={styles.primary} onPress={() => { void share(); }}>
                  <Text style={styles.primaryText}>Text teilen</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.secondary} onPress={() => setEditing(true)}>
                  <Text style={styles.secondaryText}>Zahlungsangaben bearbeiten</Text>
                </TouchableOpacity>
              </View>
            )}
          </ScrollView>

          <TouchableOpacity onPress={onClose} style={styles.close}><Text style={styles.secondaryText}>Schliessen</Text></TouchableOpacity>
        </View>
      </View>
    </Modal>
  );
}

const styles = StyleSheet.create({
  overlay:  { flex: 1, backgroundColor: 'rgba(0,0,0,0.4)', justifyContent: 'flex-end' },
  box:      { margin: 16, padding: 24, gap: 12, borderRadius: R.xl },
  title:    { fontSize: S.lg, fontWeight: '800', color: C.textPrimary },
  amount:   { fontSize: S.xl, fontWeight: '800', color: C.gold },
  label:    { fontSize: S.xs, color: C.textSecondary, marginBottom: 4, fontWeight: '600' },
  input:    { borderWidth: 1, borderColor: C.border, borderRadius: R.md, paddingHorizontal: 12, paddingVertical: 10, fontSize: S.md, color: C.textPrimary },
  inputBad: { borderColor: C.error },
  privacy:  { fontSize: S.xs, color: C.textTertiary },
  message:  { fontSize: S.sm, color: C.textPrimary, lineHeight: 20, backgroundColor: C.bgSoft, borderRadius: R.md, padding: 12 },
  qrWrap:   { alignItems: 'center', gap: 8 },
  qrHint:   { fontSize: S.xs, color: C.textTertiary, textAlign: 'center' },
  // Schweizer Kreuz in der Mitte des QR-Codes (weisses Kreuz auf schwarzem Grund)
  cross:    { position: 'absolute', top: 100 - 20 + 0, width: 40, height: 40, backgroundColor: '#000', alignItems: 'center', justifyContent: 'center', borderWidth: 3, borderColor: '#fff' },
  crossV:   { position: 'absolute', width: 8, height: 22, backgroundColor: '#fff' },
  crossH:   { position: 'absolute', width: 22, height: 8, backgroundColor: '#fff' },
  primary:  { backgroundColor: C.gold, borderRadius: R.md, paddingVertical: 14, alignItems: 'center' },
  primaryText: { color: '#fff', fontWeight: '700', fontSize: S.md },
  secondary:{ paddingVertical: 10, alignItems: 'center' },
  secondaryText: { color: C.textSecondary, fontWeight: '600', fontSize: S.sm },
  close:    { alignItems: 'center', paddingTop: 4 },
});
