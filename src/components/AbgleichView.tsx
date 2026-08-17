import { useState } from 'react';
import {
  View, Text, TouchableOpacity, StyleSheet, ActivityIndicator,
  Modal, TextInput, Alert, ScrollView,
} from 'react-native';
import { Ionicons } from '@/components/Ionicons';
import * as DocumentPicker from 'expo-document-picker';
import { useBankStatements } from '../hooks/useBankStatements';
import { C, R, S, card } from '../constants/design';
import type { AccountType, BankTransaction } from '../types/bank';

function fmt(n: number, cur = 'CHF') { return `${cur} ${n.toFixed(2)}`; }
function fmtDate(d: string | null) {
  return d ? new Date(d).toLocaleDateString('de-CH', { day: '2-digit', month: 'short', year: 'numeric' }) : '—';
}

const ACCOUNT_TYPES: { key: AccountType; label: string }[] = [
  { key: 'debit',  label: 'Konto' },
  { key: 'credit', label: 'Kreditkarte' },
];

export default function AbgleichView() {
  const {
    statements, unmatchedTransactions, unmatchedReceipts,
    loading, uploading, uploadError,
    uploadStatement, manualMatch, ignoreTransaction,
  } = useBankStatements();

  const [accountType, setAccountType]   = useState<AccountType>('debit');
  const [accountLabel, setAccountLabel] = useState('');
  const [matchTarget, setMatchTarget]   = useState<BankTransaction | null>(null);

  async function pickFile() {
    const result = await DocumentPicker.getDocumentAsync({
      type: ['application/pdf', 'text/csv', 'text/xml', 'application/xml', 'text/plain', 'text/comma-separated-values'],
      copyToCacheDirectory: true,
    });
    if (result.canceled || !result.assets?.[0]) return;
    const file = result.assets[0];

    try {
      const res = await uploadStatement(
        file.uri,
        file.mimeType ?? 'application/octet-stream',
        file.name,
        accountLabel.trim(),
        accountType
      );
      Alert.alert('Erfolg', `${res.transactionCount} Buchungen importiert.`);
      setAccountLabel('');
    } catch (err) {
      Alert.alert('Fehler', (err as Error)?.message ?? 'Upload fehlgeschlagen');
    }
  }

  return (
    <View style={{ gap: 20 }}>
      {/* Upload */}
      <View style={styles.section}>
        <Text style={styles.sectionTitle}>Kontoauszug hochladen</Text>
        <View style={[card, styles.uploadCard]}>
          <View style={styles.typeRow}>
            {ACCOUNT_TYPES.map((t) => (
              <TouchableOpacity
                key={t.key}
                style={[styles.typeBtn, accountType === t.key && styles.typeBtnActive]}
                onPress={() => setAccountType(t.key)}
              >
                <Text style={[styles.typeText, accountType === t.key && styles.typeTextActive]}>{t.label}</Text>
              </TouchableOpacity>
            ))}
          </View>
          <TextInput
            style={styles.input}
            placeholder="Bezeichnung (optional, z.B. UBS Privatkonto)"
            placeholderTextColor={C.textTertiary}
            value={accountLabel}
            onChangeText={setAccountLabel}
          />
          <TouchableOpacity style={[styles.uploadBtn, uploading && { opacity: 0.6 }]} onPress={pickFile} disabled={uploading}>
            {uploading ? (
              <ActivityIndicator color="#fff" />
            ) : (
              <>
                <Ionicons name="document-attach-outline" size={18} color="#fff" />
                <Text style={styles.uploadBtnText}>PDF oder CSV wählen</Text>
              </>
            )}
          </TouchableOpacity>
          {uploadError && <Text style={styles.errorText}>{uploadError}</Text>}
        </View>
      </View>

      {loading && statements.length === 0 ? (
        <View style={{ paddingVertical: 40, alignItems: 'center' }}>
          <ActivityIndicator color={C.gold} />
        </View>
      ) : (
        <>
          {/* Kontoauszüge */}
          {statements.length > 0 && (
            <View style={styles.section}>
              <Text style={styles.sectionTitle}>Kontoauszüge</Text>
              <View style={[card, { padding: 0, overflow: 'hidden' }]}>
                {statements.map((s, i) => (
                  <View key={s.id} style={[styles.stmtRow, i > 0 && styles.rowBorder]}>
                    <View style={styles.stmtIcon}>
                      <Ionicons name={s.account_type === 'credit' ? 'card-outline' : 'business-outline'} size={18} color={C.gold} />
                    </View>
                    <View style={{ flex: 1 }}>
                      <Text style={styles.stmtLabel} numberOfLines={1}>{s.account_label}</Text>
                      <Text style={styles.stmtMeta}>
                        {fmtDate(s.period_start)} – {fmtDate(s.period_end)} · {s.transactionCount} Buchungen
                      </Text>
                    </View>
                    <Text style={styles.stmtMatched}>{s.matchedCount}/{s.transactionCount}</Text>
                  </View>
                ))}
              </View>
            </View>
          )}

          {/* Belege fehlen */}
          <View style={styles.section}>
            <Text style={styles.sectionTitle}>Belege fehlen ({unmatchedTransactions.length})</Text>
            {unmatchedTransactions.length === 0 ? (
              <View style={[card, styles.emptyCard]}>
                <Text style={styles.emptyText}>Alle Buchungen sind belegt 🎉</Text>
              </View>
            ) : (
              <View style={[card, { padding: 0, overflow: 'hidden' }]}>
                {unmatchedTransactions.map((t, i) => (
                  <View key={t.id} style={[styles.txRow, i > 0 && styles.rowBorder]}>
                    <View style={{ flex: 1 }}>
                      <Text style={styles.txDesc} numberOfLines={1}>{t.description}</Text>
                      <Text style={styles.txMeta}>{fmtDate(t.booking_date)}</Text>
                    </View>
                    <Text style={styles.txAmount}>{fmt(Math.abs(t.amount), t.currency)}</Text>
                    <TouchableOpacity style={styles.iconBtn} onPress={() => setMatchTarget(t)}>
                      <Ionicons name="link-outline" size={18} color={C.gold} />
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.iconBtn} onPress={() => ignoreTransaction(t.id)}>
                      <Ionicons name="close-circle-outline" size={18} color={C.textTertiary} />
                    </TouchableOpacity>
                  </View>
                ))}
              </View>
            )}
          </View>

          {/* Bankbeleg fehlt */}
          <View style={styles.section}>
            <Text style={styles.sectionTitle}>Bankbeleg fehlt ({unmatchedReceipts.length})</Text>
            {unmatchedReceipts.length === 0 ? (
              <View style={[card, styles.emptyCard]}>
                <Text style={styles.emptyText}>Alle Quittungen sind verbucht 🎉</Text>
              </View>
            ) : (
              <View style={[card, { padding: 0, overflow: 'hidden' }]}>
                {unmatchedReceipts.map((r, i) => (
                  <View key={r.id} style={[styles.txRow, i > 0 && styles.rowBorder]}>
                    <View style={{ flex: 1 }}>
                      <Text style={styles.txDesc} numberOfLines={1}>{r.store_name}</Text>
                      <Text style={styles.txMeta}>{fmtDate(r.receipt_date)}</Text>
                    </View>
                    <Text style={styles.txAmount}>
                      {r.total_amount != null ? fmt(r.total_amount, r.currency) : '—'}
                    </Text>
                  </View>
                ))}
              </View>
            )}
          </View>
        </>
      )}

      {/* Manuelle Zuordnung */}
      <Modal visible={!!matchTarget} animationType="slide" transparent onRequestClose={() => setMatchTarget(null)}>
        <View style={styles.modalBackdrop}>
          <View style={[styles.modalCard, card]}>
            <Text style={styles.modalTitle}>Beleg zuordnen</Text>
            {matchTarget && (
              <Text style={styles.modalSubtitle} numberOfLines={1}>
                {matchTarget.description} · {fmt(Math.abs(matchTarget.amount), matchTarget.currency)}
              </Text>
            )}
            <ScrollView style={{ maxHeight: 320 }}>
              {unmatchedReceipts.length === 0 ? (
                <Text style={styles.emptyText}>Keine offenen Quittungen.</Text>
              ) : (
                unmatchedReceipts.map((r) => (
                  <TouchableOpacity
                    key={r.id}
                    style={styles.modalRow}
                    onPress={async () => {
                      if (!matchTarget) return;
                      await manualMatch(matchTarget.id, r.id);
                      setMatchTarget(null);
                    }}
                  >
                    <View style={{ flex: 1 }}>
                      <Text style={styles.txDesc} numberOfLines={1}>{r.store_name}</Text>
                      <Text style={styles.txMeta}>{fmtDate(r.receipt_date)}</Text>
                    </View>
                    <Text style={styles.txAmount}>
                      {r.total_amount != null ? fmt(r.total_amount, r.currency) : '—'}
                    </Text>
                  </TouchableOpacity>
                ))
              )}
            </ScrollView>
            <TouchableOpacity style={styles.modalCloseBtn} onPress={() => setMatchTarget(null)}>
              <Text style={styles.modalCloseText}>Abbrechen</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>
    </View>
  );
}

const styles = StyleSheet.create({
  section:      { gap: 10 },
  sectionTitle: { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },

  uploadCard: { padding: 16, gap: 12 },
  typeRow:    { flexDirection: 'row', backgroundColor: C.bgSoft, borderRadius: R.lg, padding: 4, gap: 2 },
  typeBtn:        { flex: 1, alignItems: 'center', paddingVertical: 8, borderRadius: R.md },
  typeBtnActive:  { backgroundColor: C.gold },
  typeText:       { color: C.textSecondary, fontSize: S.sm, fontWeight: '600' },
  typeTextActive: { color: '#fff', fontWeight: '700' },

  input: {
    borderWidth: 1, borderColor: C.border, borderRadius: R.md,
    paddingHorizontal: 14, paddingVertical: 12,
    fontSize: S.sm, color: C.textPrimary, backgroundColor: C.bgCard,
  },

  uploadBtn: {
    flexDirection: 'row', alignItems: 'center', justifyContent: 'center', gap: 8,
    backgroundColor: C.gold, borderRadius: R.md, paddingVertical: 14,
  },
  uploadBtnText: { color: '#fff', fontSize: S.sm, fontWeight: '700' },
  errorText:     { color: C.error, fontSize: S.xs },

  rowBorder: { borderTopWidth: 1, borderTopColor: C.borderSoft },

  stmtRow:    { flexDirection: 'row', alignItems: 'center', gap: 12, padding: 14 },
  stmtIcon:   { width: 38, height: 38, borderRadius: R.sm, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  stmtLabel:  { fontSize: S.sm, fontWeight: '700', color: C.textPrimary },
  stmtMeta:   { fontSize: S.xs, color: C.textTertiary, marginTop: 2 },
  stmtMatched:{ fontSize: S.sm, fontWeight: '700', color: C.gold },

  txRow:  { flexDirection: 'row', alignItems: 'center', gap: 10, padding: 14 },
  txDesc: { fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  txMeta: { fontSize: S.xs, color: C.textTertiary, marginTop: 2 },
  txAmount: { fontSize: S.sm, fontWeight: '700', color: C.textPrimary },
  iconBtn:  { padding: 4 },

  emptyCard: { padding: 20, alignItems: 'center' },
  emptyText: { fontSize: S.sm, color: C.textSecondary, textAlign: 'center' },

  modalBackdrop: { flex: 1, backgroundColor: 'rgba(0,0,0,0.4)', justifyContent: 'flex-end' },
  modalCard:     { padding: 20, gap: 14, borderBottomLeftRadius: 0, borderBottomRightRadius: 0 },
  modalTitle:    { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },
  modalSubtitle: { fontSize: S.sm, color: C.textSecondary },
  modalRow:      { flexDirection: 'row', alignItems: 'center', gap: 10, paddingVertical: 12, borderTopWidth: 1, borderTopColor: C.borderSoft },
  modalCloseBtn: { alignItems: 'center', paddingVertical: 12 },
  modalCloseText:{ fontSize: S.sm, fontWeight: '700', color: C.textSecondary },
});
