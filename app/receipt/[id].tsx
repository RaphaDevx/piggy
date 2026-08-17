import { useEffect, useState, useCallback } from 'react';
import {
  View, Text, ScrollView, TouchableOpacity, TextInput,
  StyleSheet, ActivityIndicator, Image, Alert, Platform,
  KeyboardAvoidingView,
} from 'react-native';
import { useLocalSearchParams, useRouter } from 'expo-router';
import { Ionicons } from '@/components/Ionicons';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { supabase } from '../../src/lib/supabase';
import TagBadge from '../../src/components/TagBadge';
import NumericInput from '../../src/components/NumericInput';
import { CardMatchBanner } from '../../src/components/CardMatchBanner';
import { C, R, S, card } from '../../src/constants/design';
import { ALL_TAGS, getTagColor, PAYMENT_METHODS, STORE_CATEGORIES } from '../../src/lib/categories';
import { useProjects, assignReceiptToProject } from '../../src/hooks/useProjects';
import type { ReceiptWithItems, ReceiptItem } from '../../src/types/receipt';
import type { BankMatch } from '../../src/types/bank';

const CURRENCIES = ['CHF', 'EUR', 'USD'];

interface EditReceipt {
  store_name:     string;
  store_category: string | null;
  receipt_date:   string | null;
  total_amount:   number;
  currency:       string;
  payment_method: string | null;
  payment_card:   string | null;
  notes:          string | null;
}
type EditItem = Omit<ReceiptItem, 'receipt_id'>;

function PickerRow({ options, value, onChange }: { options: string[]; value: string | null; onChange: (v: string) => void }) {
  return (
    <ScrollView horizontal showsHorizontalScrollIndicator={false} contentContainerStyle={{ gap: 6, paddingVertical: 2 }}>
      {options.map((opt) => (
        <TouchableOpacity
          key={opt}
          onPress={() => onChange(opt)}
          style={[pkStyles.chip, value === opt && pkStyles.chipActive]}
        >
          <Text style={[pkStyles.label, value === opt && pkStyles.labelActive]}>{opt}</Text>
        </TouchableOpacity>
      ))}
    </ScrollView>
  );
}
const pkStyles = StyleSheet.create({
  chip:        { borderWidth: 1, borderColor: C.border, borderRadius: 20, paddingHorizontal: 12, paddingVertical: 5, backgroundColor: C.bgSoft },
  chipActive:  { backgroundColor: C.gold, borderColor: C.gold },
  label:       { fontSize: S.xs, fontWeight: '600', color: C.textTertiary },
  labelActive: { color: '#fff' },
});

export default function ReceiptDetailScreen() {
  const { id }  = useLocalSearchParams<{ id: string }>();
  const router  = useRouter();
  const insets  = useSafeAreaInsets();

  const [receipt, setReceipt]   = useState<ReceiptWithItems | null>(null);
  const [loading, setLoading]   = useState(true);
  const [saving, setSaving]     = useState(false);
  const [showMd, setShowMd]     = useState(false);
  const [editing, setEditing]   = useState(false);

  const [editData, setEditData]   = useState<EditReceipt | null>(null);
  const [editItems, setEditItems] = useState<EditItem[]>([]);
  const [bankMatch, setBankMatch] = useState<BankMatch | null>(null);

  const { projects } = useProjects();

  const loadReceipt = useCallback(async () => {
    if (!id) return;
    const { data } = await supabase
      .from('receipts').select('*, receipt_items(*)').eq('id', id).single();
    setReceipt(data as ReceiptWithItems | null);
    setLoading(false);

    const { data: match } = await supabase
      .from('receipt_matches')
      .select('match_type, bank_transactions(booking_date, amount, currency, description)')
      .eq('receipt_id', id)
      .maybeSingle();

    const tx = match?.bank_transactions as unknown as { booking_date: string; amount: number; currency: string; description: string } | null;
    setBankMatch(tx && match ? { ...tx, match_type: match.match_type } : null);
  }, [id]);

  useEffect(() => { loadReceipt(); }, [loadReceipt]);

  function startEditing() {
    if (!receipt) return;
    setEditData({
      store_name:     receipt.store_name,
      store_category: receipt.store_category,
      receipt_date:   receipt.receipt_date,
      total_amount:   receipt.total_amount ?? 0,
      currency:       receipt.currency,
      payment_method: receipt.payment_method,
      payment_card:   receipt.payment_card,
      notes:          receipt.notes,
    });
    setEditItems(receipt.receipt_items.map((i) => ({ ...i })));
    setEditing(true);
  }

  function cancelEditing() { setEditing(false); setEditData(null); setEditItems([]); }

  async function saveEdits() {
    if (!receipt || !editData) return;
    setSaving(true);
    const { error } = await supabase.from('receipts').update({
      store_name: editData.store_name, store_category: editData.store_category,
      receipt_date: editData.receipt_date || null, total_amount: editData.total_amount,
      currency: editData.currency, payment_method: editData.payment_method,
      payment_card: editData.payment_card, notes: editData.notes,
    }).eq('id', receipt.id);
    if (error) { Alert.alert('Fehler', error.message); setSaving(false); return; }

    const editIds = editItems.filter((i) => i.id).map((i) => i.id);
    const toDelete = receipt.receipt_items.map((i) => i.id).filter((id) => !editIds.includes(id));
    if (toDelete.length) await supabase.from('receipt_items').delete().in('id', toDelete);

    for (const item of editItems) {
      const payload = { name: item.name, quantity: item.quantity, unit: item.unit, unit_price: item.unit_price, total_price: item.total_price, tags: item.tags };
      if (item.id && receipt.receipt_items.some((r) => r.id === item.id)) {
        await supabase.from('receipt_items').update(payload).eq('id', item.id);
      } else {
        await supabase.from('receipt_items').insert({ receipt_id: receipt.id, ...payload });
      }
    }
    setSaving(false);
    setEditing(false);
    await loadReceipt();
  }

  function updateItem(idx: number, patch: Partial<EditItem>) {
    setEditItems((prev) => { const n = [...prev]; n[idx] = { ...n[idx], ...patch }; return n; });
  }
  function toggleTag(idx: number, tag: string) {
    setEditItems((prev) => {
      const n = [...prev];
      const tags = n[idx].tags.includes(tag) ? n[idx].tags.filter((t) => t !== tag) : [...n[idx].tags, tag];
      n[idx] = { ...n[idx], tags }; return n;
    });
  }

  async function deleteReceipt() {
    Alert.alert('Quittung löschen', 'Wirklich löschen?', [
      { text: 'Abbrechen', style: 'cancel' },
      { text: 'Löschen', style: 'destructive', onPress: async () => {
        await supabase.from('receipts').delete().eq('id', id!); router.back();
      }},
    ]);
  }

  if (loading) return (
    <View style={[styles.center, { paddingTop: insets.top }]}>
      <ActivityIndicator size="large" color={C.gold} />
    </View>
  );
  if (!receipt) return (
    <View style={[styles.center, { paddingTop: insets.top }]}>
      <Text style={styles.errorText}>Quittung nicht gefunden.</Text>
      <TouchableOpacity onPress={() => router.back()}><Text style={styles.link}>Zurück</Text></TouchableOpacity>
    </View>
  );

  const date    = receipt.receipt_date
    ? new Date(receipt.receipt_date).toLocaleDateString('de-CH', { day: '2-digit', month: '2-digit', year: 'numeric' })
    : '—';
  const amount  = receipt.total_amount != null ? `${receipt.currency} ${receipt.total_amount.toFixed(2)}` : '—';
  const allTags = [...new Set(receipt.receipt_items.flatMap((i) => i.tags ?? []))];
  const paymentLine = receipt.payment_card ? `${receipt.payment_method} — ${receipt.payment_card}` : receipt.payment_method ?? '—';

  // ── Edit-Modus ────────────────────────────────────────────────────────────
  if (editing && editData) {
    return (
      <KeyboardAvoidingView style={[styles.container, { paddingTop: insets.top }]} behavior={Platform.OS === 'ios' ? 'padding' : undefined}>
        <View style={styles.header}>
          <TouchableOpacity onPress={cancelEditing} style={styles.backBtn}>
            <Ionicons name="close" size={24} color={C.textPrimary} />
          </TouchableOpacity>
          <Text style={styles.headerTitle}>Bearbeiten</Text>
          <TouchableOpacity onPress={saveEdits} style={[styles.saveBtn, saving && { opacity: 0.6 }]} disabled={saving}>
            {saving ? <ActivityIndicator size="small" color="#fff" /> : <Text style={styles.saveBtnText}>Speichern</Text>}
          </TouchableOpacity>
        </View>

        <ScrollView style={{ flex: 1 }} contentContainerStyle={styles.editContent} keyboardShouldPersistTaps="handled">
          <View style={[card, styles.editCard]}>
            <Text style={styles.editLabel}>Geschäft</Text>
            <TextInput style={styles.editInput} value={editData.store_name}
              onChangeText={(v) => setEditData((p) => p ? { ...p, store_name: v } : p)} placeholderTextColor={C.textTertiary} />

            <Text style={styles.editLabel}>Kategorie</Text>
            <PickerRow options={STORE_CATEGORIES} value={editData.store_category}
              onChange={(v) => setEditData((p) => p ? { ...p, store_category: v } : p)} />

            <View style={styles.editRow2}>
              <View style={{ flex: 1 }}>
                <Text style={styles.editLabel}>Datum</Text>
                <TextInput style={styles.editInput} value={editData.receipt_date ?? ''} placeholder="YYYY-MM-DD"
                  onChangeText={(v) => setEditData((p) => p ? { ...p, receipt_date: v } : p)} placeholderTextColor={C.textTertiary} />
              </View>
              <View style={{ flex: 1 }}>
                <Text style={styles.editLabel}>Betrag</Text>
                <NumericInput value={editData.total_amount}
                  onChange={(v) => setEditData((p) => p ? { ...p, total_amount: v } : p)}
                  style={[styles.editInput, { color: C.gold, fontWeight: '700' }]} placeholder="0.00" placeholderTextColor={C.textTertiary} />
              </View>
            </View>

            <Text style={styles.editLabel}>Währung</Text>
            <PickerRow options={CURRENCIES} value={editData.currency}
              onChange={(v) => setEditData((p) => p ? { ...p, currency: v } : p)} />

            <Text style={styles.editLabel}>Zahlungsmittel</Text>
            <PickerRow options={PAYMENT_METHODS} value={editData.payment_method}
              onChange={(v) => setEditData((p) => p ? { ...p, payment_method: v } : p)} />

            <Text style={styles.editLabel}>Notiz</Text>
            <TextInput style={[styles.editInput, { minHeight: 60 }]} value={editData.notes ?? ''}
              onChangeText={(v) => setEditData((p) => p ? { ...p, notes: v || null } : p)}
              placeholder="Optionale Notiz…" placeholderTextColor={C.textTertiary} multiline />
          </View>

          <Text style={styles.sectionTitle}>Artikel ({editItems.length})</Text>
          {editItems.map((item, idx) => (
            <View key={idx} style={[card, styles.itemCard]}>
              <View style={styles.itemHeader}>
                <TextInput style={[styles.editInput, { flex: 1 }]} value={item.name}
                  onChangeText={(v) => updateItem(idx, { name: v })} placeholder="Artikel" placeholderTextColor={C.textTertiary} />
                <TouchableOpacity onPress={() => setEditItems((p) => p.filter((_, i) => i !== idx))} style={{ padding: 4 }}>
                  <Ionicons name="trash-outline" size={18} color={C.error} />
                </TouchableOpacity>
              </View>
              <View style={styles.editRow3}>
                <View style={{ flex: 1 }}>
                  <Text style={styles.editLabel}>Menge</Text>
                  <NumericInput value={item.quantity ?? 1} onChange={(v) => updateItem(idx, { quantity: v })}
                    style={styles.editInput} placeholder="1" placeholderTextColor={C.textTertiary} />
                </View>
                <View style={{ flex: 1 }}>
                  <Text style={styles.editLabel}>Einheit</Text>
                  <TextInput style={styles.editInput} value={item.unit ?? ''}
                    onChangeText={(v) => updateItem(idx, { unit: v })} placeholder="Stk" placeholderTextColor={C.textTertiary} />
                </View>
                <View style={{ flex: 1 }}>
                  <Text style={styles.editLabel}>Preis</Text>
                  <NumericInput value={item.total_price ?? 0} onChange={(v) => updateItem(idx, { total_price: v })}
                    style={styles.editInput} placeholder="0.00" placeholderTextColor={C.textTertiary} />
                </View>
              </View>
              <View style={styles.tagsWrap}>
                {ALL_TAGS.map((tag) => {
                  const active = item.tags.includes(tag); const color = getTagColor(tag);
                  return (
                    <TouchableOpacity key={tag} onPress={() => toggleTag(idx, tag)}
                      style={[styles.tagChip, active && { backgroundColor: `${color}18`, borderColor: color }]}>
                      <Text style={[styles.tagChipText, active && { color }]}>{tag}</Text>
                    </TouchableOpacity>
                  );
                })}
              </View>
            </View>
          ))}
          <TouchableOpacity style={styles.addItemBtn}
            onPress={() => setEditItems((p) => [...p, { id: '', name: '', quantity: 1, unit: 'Stk', unit_price: 0, total_price: 0, tags: [] }])}>
            <Ionicons name="add-circle-outline" size={20} color={C.gold} />
            <Text style={styles.addItemText}>Artikel hinzufügen</Text>
          </TouchableOpacity>
          <View style={{ height: 40 }} />
        </ScrollView>
      </KeyboardAvoidingView>
    );
  }

  // ── Ansicht ───────────────────────────────────────────────────────────────
  return (
    <View style={[styles.container, { paddingTop: insets.top }]}>
      <View style={styles.header}>
        <TouchableOpacity onPress={() => router.back()} style={styles.backBtn}>
          <Ionicons name="chevron-back" size={24} color={C.textPrimary} />
        </TouchableOpacity>
        <Text style={styles.headerTitle} numberOfLines={1}>{receipt.store_name}</Text>
        <View style={styles.headerActions}>
          <TouchableOpacity onPress={() => router.push(`/split/${receipt.id}` as any)} style={styles.iconBtn}>
            <Ionicons name="people-outline" size={20} color={C.gold} />
          </TouchableOpacity>
          <TouchableOpacity onPress={startEditing} style={styles.iconBtn}>
            <Ionicons name="pencil-outline" size={20} color={C.gold} />
          </TouchableOpacity>
          <TouchableOpacity onPress={() => setShowMd((v) => !v)} style={styles.iconBtn}>
            <Ionicons name={showMd ? 'document-text' : 'code-outline'} size={20} color={C.textSecondary} />
          </TouchableOpacity>
          <TouchableOpacity onPress={deleteReceipt} style={styles.iconBtn}>
            <Ionicons name="trash-outline" size={20} color={C.error} />
          </TouchableOpacity>
        </View>
      </View>

      <ScrollView style={{ flex: 1 }} contentContainerStyle={styles.content} showsVerticalScrollIndicator={false}>
        {receipt.image_url && (
          <Image source={{ uri: receipt.image_url }} style={styles.image} resizeMode="contain" />
        )}
        <View style={[styles.summaryCard, card]}>
          <View style={styles.amountRow}>
            <Text style={styles.amountLabel}>Gesamtbetrag</Text>
            <Text style={styles.amountValue}>{amount}</Text>
          </View>
          <View style={styles.divider} />
          <View style={styles.metaRow}><Text style={styles.metaLabel}>Datum</Text><Text style={styles.metaValue}>{date}</Text></View>
          <View style={styles.metaRow}><Text style={styles.metaLabel}>Kategorie</Text><Text style={styles.metaValue}>{receipt.store_category ?? '—'}</Text></View>
          <View style={styles.metaRow}><Text style={styles.metaLabel}>Zahlungsmittel</Text><Text style={styles.metaValue}>{paymentLine}</Text></View>
          {receipt.notes ? <View style={styles.metaRow}><Text style={styles.metaLabel}>Notiz</Text><Text style={[styles.metaValue, { maxWidth: '70%' }]} numberOfLines={3}>{receipt.notes}</Text></View> : null}
        </View>

        {/* Karten-Matching & Senden */}
        <View style={styles.section}>
          <Text style={styles.sectionTitle}>Karte & Teilen</Text>
          <CardMatchBanner receiptId={receipt.id} paymentCard={receipt.payment_card} />
        </View>

        <View style={styles.section}>
          <Text style={styles.sectionTitle}>Bankbeleg</Text>
          <View style={[card, styles.bankCard]}>
            {bankMatch ? (
              <>
                <View style={styles.metaRow}>
                  <Ionicons name="checkmark-circle" size={16} color={C.success} />
                  <Text style={[styles.metaValue, { flex: 1, marginLeft: 8 }]} numberOfLines={1}>{bankMatch.description}</Text>
                </View>
                <View style={styles.metaRow}>
                  <Text style={styles.metaLabel}>Buchungsdatum</Text>
                  <Text style={styles.metaValue}>
                    {new Date(bankMatch.booking_date).toLocaleDateString('de-CH', { day: '2-digit', month: '2-digit', year: 'numeric' })}
                  </Text>
                </View>
                <View style={styles.metaRow}>
                  <Text style={styles.metaLabel}>Betrag</Text>
                  <Text style={styles.metaValue}>{bankMatch.currency} {Math.abs(bankMatch.amount).toFixed(2)}</Text>
                </View>
              </>
            ) : (
              <View style={styles.metaRow}>
                <Ionicons name="alert-circle-outline" size={16} color={C.textTertiary} />
                <Text style={[styles.metaValue, { marginLeft: 8 }]}>Kein Bankbeleg zugeordnet</Text>
              </View>
            )}
          </View>
        </View>

        {/* Projekt */}
        <View style={styles.section}>
          <Text style={styles.sectionTitle}>Projekt</Text>
          <View style={[card, styles.bankCard]}>
            {receipt.project_id ? (
              (() => {
                const proj = projects.find((p) => p.id === receipt.project_id);
                return (
                  <View style={styles.metaRow}>
                    <Text style={{ fontSize: 20 }}>{proj?.emoji ?? '📍'}</Text>
                    <Text style={[styles.metaValue, { flex: 1, textAlign: 'left', marginLeft: 8 }]}>
                      {proj?.name ?? '…'}
                    </Text>
                    <TouchableOpacity
                      onPress={() => router.push(`/project/${receipt.project_id}` as any)}
                      style={{ marginRight: 8 }}
                    >
                      <Ionicons name="arrow-forward-circle-outline" size={22} color={C.gold} />
                    </TouchableOpacity>
                    <TouchableOpacity
                      onPress={() => Alert.alert('Aus Projekt entfernen?', '', [
                        { text: 'Abbrechen', style: 'cancel' },
                        { text: 'Entfernen', style: 'destructive', onPress: async () => {
                          await assignReceiptToProject(receipt.id, null);
                          await loadReceipt();
                        }},
                      ])}
                    >
                      <Ionicons name="close-circle-outline" size={22} color={C.error} />
                    </TouchableOpacity>
                  </View>
                );
              })()
            ) : (
              <TouchableOpacity
                style={styles.metaRow}
                onPress={() => {
                  if (!projects.length) { Alert.alert('Kein Projekt', 'Erstelle zuerst ein Projekt im Projekte-Tab.'); return; }
                  Alert.alert(
                    'Zu Projekt hinzufügen',
                    'Wähle ein Projekt:',
                    [
                      ...projects.map((p) => ({
                        text: `${p.emoji} ${p.name}`,
                        onPress: async () => {
                          await assignReceiptToProject(receipt.id, p.id);
                          await loadReceipt();
                        },
                      })),
                      { text: 'Abbrechen', style: 'cancel' },
                    ]
                  );
                }}
              >
                <Ionicons name="folder-open-outline" size={16} color={C.textTertiary} />
                <Text style={[styles.metaValue, { color: C.textTertiary, marginLeft: 8 }]}>Zu Projekt hinzufügen…</Text>
              </TouchableOpacity>
            )}
          </View>
        </View>

        {allTags.length > 0 && (
          <View style={styles.section}>
            <Text style={styles.sectionTitle}>Tags</Text>
            <View style={styles.tagsWrap}>
              {allTags.map((tag) => <TagBadge key={tag} tag={tag} />)}
            </View>
          </View>
        )}

        {showMd && receipt.markdown_content && (
          <View style={styles.section}>
            <Text style={styles.sectionTitle}>Markdown</Text>
            <View style={styles.mdBox}><Text style={styles.mdText} selectable>{receipt.markdown_content}</Text></View>
          </View>
        )}

        {!showMd && (
          <View style={styles.section}>
            <Text style={styles.sectionTitle}>Artikel ({receipt.receipt_items.length})</Text>
            <View style={[card, styles.itemsCard]}>
              {receipt.receipt_items.map((item, i) => (
                <View key={item.id} style={[styles.itemRow, i > 0 && styles.itemBorder]}>
                  <View style={{ flex: 1 }}>
                    <Text style={styles.itemName}>{item.name}</Text>
                    {((item.tags ?? []).length > 0 || (item.quantity != null && item.quantity !== 1)) && (
                      <View style={styles.itemMeta}>
                        {item.quantity != null && item.quantity !== 1 && <Text style={styles.itemMetaText}>{item.quantity}× {item.unit ?? ''}</Text>}
                        {(item.tags ?? []).map((tag) => <TagBadge key={tag} tag={tag} small />)}
                      </View>
                    )}
                  </View>
                  <Text style={styles.itemPrice}>
                    {item.total_price != null ? `${receipt.currency} ${item.total_price.toFixed(2)}` : '—'}
                  </Text>
                </View>
              ))}
            </View>
          </View>
        )}
        <View style={{ height: 40 }} />
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: C.bg },
  center:    { flex: 1, backgroundColor: C.bg, alignItems: 'center', justifyContent: 'center', gap: 12 },
  header:    { flexDirection: 'row', alignItems: 'center', paddingHorizontal: 16, paddingVertical: 12, borderBottomWidth: 1, borderBottomColor: C.border, backgroundColor: C.bgCard },
  backBtn:       { width: 36 },
  headerTitle:   { flex: 1, color: C.textPrimary, fontSize: S.md, fontWeight: '700' },
  headerActions: { flexDirection: 'row', gap: 4 },
  iconBtn:       { padding: 8 },
  saveBtn:       { backgroundColor: C.gold, borderRadius: R.md, paddingHorizontal: 16, paddingVertical: 8 },
  saveBtnText:   { color: '#fff', fontWeight: '700', fontSize: S.sm },
  content: { padding: 16, gap: 16 },
  image:   { width: '100%', height: 220, borderRadius: R.xl, backgroundColor: C.bgSoft },
  summaryCard: { padding: 20, gap: 10 },
  amountRow:   { alignItems: 'center', gap: 4, paddingBottom: 4 },
  amountLabel: { fontSize: S.xs, color: C.textSecondary, fontWeight: '600', textTransform: 'uppercase', letterSpacing: 0.5 },
  amountValue: { fontSize: S.hero * 0.7, fontWeight: '800', color: C.gold, letterSpacing: -1 },
  divider:     { height: 1, backgroundColor: C.border, marginVertical: 4 },
  metaRow:     { flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' },
  metaLabel:   { color: C.textSecondary, fontSize: S.sm },
  metaValue:   { color: C.textPrimary, fontSize: S.sm, fontWeight: '600', maxWidth: '60%', textAlign: 'right' },
  section:      { gap: 10 },
  sectionTitle: { color: C.textPrimary, fontSize: S.lg, fontWeight: '700' },
  bankCard:     { padding: 16, gap: 8 },
  tagsWrap:     { flexDirection: 'row', flexWrap: 'wrap', gap: 8 },
  mdBox:  { backgroundColor: C.bgSoft, borderRadius: R.lg, padding: 14, borderWidth: 1, borderColor: C.border },
  mdText: { color: C.textSecondary, fontSize: 12, fontFamily: Platform.OS === 'ios' ? 'Courier' : 'monospace', lineHeight: 18 },
  itemsCard:    { padding: 0, overflow: 'hidden' },
  itemRow:      { flexDirection: 'row', alignItems: 'flex-start', gap: 12, padding: 14 },
  itemBorder:   { borderTopWidth: 1, borderTopColor: C.borderSoft },
  itemName:     { color: C.textPrimary, fontSize: S.sm, fontWeight: '600' },
  itemMeta:     { flexDirection: 'row', flexWrap: 'wrap', gap: 4, marginTop: 4 },
  itemMetaText: { color: C.textTertiary, fontSize: S.xs },
  itemPrice:    { color: C.gold, fontSize: S.md, fontWeight: '700', minWidth: 80, textAlign: 'right' },
  errorText: { color: C.error, fontSize: S.md },
  link:      { color: C.gold, fontSize: S.md, marginTop: 8 },
  editContent: { padding: 16, gap: 12 },
  editCard:    { padding: 16, gap: 10 },
  editLabel:   { fontSize: S.xs, fontWeight: '700', color: C.textTertiary, textTransform: 'uppercase', letterSpacing: 0.5 },
  editInput:   { backgroundColor: C.bgSoft, color: C.textPrimary, borderRadius: R.sm, paddingHorizontal: 12, paddingVertical: 10, fontSize: S.md, borderWidth: 1, borderColor: C.border },
  editRow2: { flexDirection: 'row', gap: 10 },
  editRow3: { flexDirection: 'row', gap: 8 },
  itemCard:   { padding: 14, gap: 10 },
  itemHeader: { flexDirection: 'row', gap: 10, alignItems: 'center' },
  tagChip:     { borderWidth: 1, borderColor: C.border, borderRadius: 20, paddingHorizontal: 10, paddingVertical: 4, backgroundColor: C.bgSoft },
  tagChipText: { color: C.textTertiary, fontSize: S.xs },
  addItemBtn: { flexDirection: 'row', alignItems: 'center', justifyContent: 'center', gap: 8, backgroundColor: C.bgCard, borderRadius: R.lg, paddingVertical: 14, borderWidth: 1, borderColor: `${C.gold}44` },
  addItemText: { color: C.gold, fontSize: S.md, fontWeight: '600' },
});
