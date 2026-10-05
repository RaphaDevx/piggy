import { useState } from 'react';
import { View, Text, TouchableOpacity, TextInput, StyleSheet, ActivityIndicator, Alert } from 'react-native';
import { C, R, S, card } from '../constants/design';
import type { SuggestionKind } from '../types/bank';
import type { SuggestionWithData } from '../hooks/useMatchSuggestions';

const KINDS: { key: SuggestionKind; label: string }[] = [
  { key: 'tip',      label: 'Trinkgeld' },
  { key: 'fx',       label: 'Fremdwährung' },
  { key: 'discount', label: 'Rabatt' },
  { key: 'partial',  label: 'Teilzahlung' },
  { key: 'other',    label: 'Sonstiges' },
];

function money(n: number, cur = 'CHF') { return `${cur} ${n.toFixed(2)}`; }
function signed(n: number) { return `${n < 0 ? '−' : ''}${Math.abs(n).toFixed(2)}`; }
function fmtDate(d: string | null | undefined) {
  return d ? new Date(d).toLocaleDateString('de-CH', { day: '2-digit', month: 'short', year: 'numeric' }) : '—';
}

function suggestionText(kind: SuggestionKind, diff: number): string {
  switch (kind) {
    case 'tip':      return `Trinkgeld CHF ${Math.abs(diff).toFixed(2)}?`;
    case 'fx':       return 'Fremdwährung/Gebühr';
    case 'discount': return `Rabatt CHF ${signed(-Math.abs(diff))}`;
    case 'partial':  return 'Teilzahlung';
    default:         return `Differenz CHF ${signed(diff)}`;
  }
}

/** Ausgleichsposten: Quittung + Posten = Bankbetrag. Rabatt reduziert, FX default 0. */
function defaultAmount(kind: SuggestionKind, diff: number): number {
  return kind === 'fx' ? 0 : diff;
}

interface Props {
  suggestion: SuggestionWithData;
  onAccept: (id: string, kind: SuggestionKind, amount: number, note: string | null) => Promise<void>;
  onReject: (id: string) => Promise<void>;
}

export default function SuggestionCard({ suggestion: s, onAccept, onReject }: Props) {
  const [editing, setEditing] = useState(false);
  const [kind, setKind] = useState<SuggestionKind>(s.kind);
  const [amountText, setAmountText] = useState(defaultAmount(s.kind, s.diff_amount).toFixed(2));
  const [note, setNote] = useState(s.note ?? '');
  const [busy, setBusy] = useState(false);

  const tx = s.transaction;
  const rc = s.receipt;

  async function run(action: () => Promise<void>) {
    setBusy(true);
    try {
      await action();
    } catch (err) {
      Alert.alert('Fehler', (err as Error)?.message ?? 'Aktion fehlgeschlagen');
    } finally {
      setBusy(false);
    }
  }

  function submitEdited() {
    const amount = Number(amountText.replace(',', '.'));
    if (!Number.isFinite(amount)) {
      Alert.alert('Ungültiger Betrag', 'Bitte einen Betrag wie 5.00 oder -3.00 eingeben.');
      return;
    }
    run(() => onAccept(s.id, kind, amount, note.trim() || null));
  }

  return (
    <View style={[card, styles.card]}>
      <Text style={styles.headline}>{suggestionText(s.kind, s.diff_amount)}</Text>

      <View style={styles.compare}>
        <View style={styles.col}>
          <Text style={styles.colTitle}>Quittung</Text>
          <Text style={styles.main} numberOfLines={1}>{rc?.store_name ?? '—'}</Text>
          <Text style={styles.meta}>{fmtDate(rc?.receipt_date)}</Text>
          <Text style={styles.amount}>{rc?.total_amount != null ? money(rc.total_amount, rc.currency) : '—'}</Text>
        </View>
        <View style={styles.col}>
          <Text style={styles.colTitle}>Buchung</Text>
          <Text style={styles.main} numberOfLines={1}>{tx?.description ?? '—'}</Text>
          <Text style={styles.meta}>{fmtDate(tx?.booking_date)}</Text>
          <Text style={styles.amount}>{tx ? money(Math.abs(tx.amount), tx.currency ?? 'CHF') : '—'}</Text>
        </View>
      </View>

      <Text style={styles.diff}>Differenz: CHF {signed(s.diff_amount)}</Text>
      {s.note ? <Text style={styles.meta}>{s.note}</Text> : null}

      {editing && (
        <View style={styles.editBox}>
          <View style={styles.chips}>
            {KINDS.map((k) => (
              <TouchableOpacity
                key={k.key}
                style={[styles.chip, kind === k.key && styles.chipActive]}
                onPress={() => { setKind(k.key); setAmountText(defaultAmount(k.key, s.diff_amount).toFixed(2)); }}
              >
                <Text style={[styles.chipText, kind === k.key && styles.chipTextActive]}>{k.label}</Text>
              </TouchableOpacity>
            ))}
          </View>
          <TextInput
            style={styles.input}
            value={amountText}
            onChangeText={setAmountText}
            keyboardType="numbers-and-punctuation"
            placeholder="Betrag (CHF)"
            placeholderTextColor={C.textTertiary}
          />
          <TextInput
            style={styles.input}
            value={note}
            onChangeText={setNote}
            placeholder="Notiz (optional)"
            placeholderTextColor={C.textTertiary}
          />
        </View>
      )}

      <View style={styles.actions}>
        {busy ? (
          <ActivityIndicator color={C.gold} />
        ) : editing ? (
          <>
            <TouchableOpacity style={[styles.btn, styles.btnPrimary]} onPress={submitEdited}>
              <Text style={styles.btnPrimaryText}>Speichern</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.btn} onPress={() => setEditing(false)}>
              <Text style={styles.btnText}>Zurück</Text>
            </TouchableOpacity>
          </>
        ) : (
          <>
            <TouchableOpacity
              style={[styles.btn, styles.btnPrimary]}
              onPress={() => run(() => onAccept(s.id, s.kind, defaultAmount(s.kind, s.diff_amount), s.note))}
            >
              <Text style={styles.btnPrimaryText}>Übernehmen</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.btn} onPress={() => setEditing(true)}>
              <Text style={styles.btnText}>Bearbeiten</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.btn} onPress={() => run(() => onReject(s.id))}>
              <Text style={[styles.btnText, { color: C.error }]}>Ablehnen</Text>
            </TouchableOpacity>
          </>
        )}
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  card:     { padding: 16, gap: 10 },
  headline: { fontSize: S.md, fontWeight: '700', color: C.textPrimary },
  compare:  { flexDirection: 'row', gap: 10 },
  col:      { flex: 1, backgroundColor: C.bgSoft, borderRadius: R.sm, padding: 10, gap: 2 },
  colTitle: { fontSize: S.xs, fontWeight: '700', color: C.textSecondary, textTransform: 'uppercase', letterSpacing: 0.5 },
  main:     { fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  meta:     { fontSize: S.xs, color: C.textTertiary },
  amount:   { fontSize: S.sm, fontWeight: '800', color: C.textPrimary, marginTop: 2 },
  diff:     { fontSize: S.sm, fontWeight: '700', color: C.gold },

  editBox:  { gap: 8 },
  chips:    { flexDirection: 'row', flexWrap: 'wrap', gap: 6 },
  chip:     { paddingVertical: 6, paddingHorizontal: 12, borderRadius: R.md, backgroundColor: C.bgSoft },
  chipActive:     { backgroundColor: C.gold },
  chipText:       { fontSize: S.xs, fontWeight: '600', color: C.textSecondary },
  chipTextActive: { color: '#fff', fontWeight: '700' },
  input: {
    borderWidth: 1, borderColor: C.border, borderRadius: R.md,
    paddingHorizontal: 14, paddingVertical: 10,
    fontSize: S.sm, color: C.textPrimary, backgroundColor: C.bgCard,
  },

  actions: { flexDirection: 'row', gap: 8, alignItems: 'center', minHeight: 40 },
  btn:     { flex: 1, alignItems: 'center', paddingVertical: 10, borderRadius: R.md, backgroundColor: C.bgSoft },
  btnPrimary:     { backgroundColor: C.gold },
  btnPrimaryText: { color: '#fff', fontSize: S.sm, fontWeight: '700' },
  btnText:        { color: C.textSecondary, fontSize: S.sm, fontWeight: '700' },
});
