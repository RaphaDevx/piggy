import { useState } from 'react';
import { ActivityIndicator, Alert, Image, Modal, ScrollView, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import type { ReceiptExtraFields, ReceiptWithItems } from '../types/receipt';
import { buildMergePreview, type DuplicateReceipt, type MergeChoices, type MergeField } from '../lib/receiptDuplicates';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { C, R } from '../constants/design';

type Side = 'target' | 'source';
const FIELDS: { key: MergeField; label: string }[] = [
  { key: 'receipt_date', label: 'Datum' }, { key: 'total_amount', label: 'Betrag' },
  { key: 'currency', label: 'Währung' }, { key: 'store_name', label: 'Laden' },
  { key: 'store_category', label: 'Kategorie' }, { key: 'payment_method', label: 'Zahlungsmittel' },
  { key: 'payment_card', label: 'Karte' }, { key: 'notes', label: 'Notizen' },
];
const EXTRA_LABELS: Record<keyof ReceiptExtraFields, string> = { cashier_number: 'Kassierer-Nr.', receipt_number: 'Belegnummer', vat_number: 'MwSt-Nr.', store_address: 'Adresse', store_email: 'E-Mail', store_phone: 'Telefon', store_website: 'Website' };
const value = (v: unknown) => v === null || v === undefined || v === '' ? '—' : String(v);
const empty = (v: unknown) => v === null || v === undefined || v === '';

export default function DuplicateReceiptReview({ target, source, sourceLabel = 'Quittung B', deferLabel = 'Später prüfen', onMerge, onSeparate, onDelete, onDefer }: {
  target: ReceiptWithItems; source: ReceiptWithItems; sourceLabel?: string; deferLabel?: string;
  onMerge: (choices: MergeChoices) => Promise<void>;
  onSeparate: () => Promise<void>; onDelete?: (side: Side) => Promise<void>; onDefer: () => void;
}) {
  const insets = useSafeAreaInsets();
  const incoming = sourceLabel === 'Neuer Scan';
  const [choices, setChoices] = useState<MergeChoices>({ image: !target.image_url && source.image_url ? 'source' : 'target', items: !target.receipt_items.length && source.receipt_items.length ? 'source' : 'target' });
  const [busy, setBusy] = useState(false);
  const [zoom, setZoom] = useState<string | null>(null);
  const [preview, setPreview] = useState(false);
  const [expanded, setExpanded] = useState(false);
  const mergedPreview = buildMergePreview(target, source, choices);
  async function run(action: () => Promise<void>) {
    if (busy) return;
    setBusy(true);
    try { await action(); } catch (error) { Alert.alert('Prüfung nicht abgeschlossen', error instanceof Error ? error.message : 'Bitte erneut versuchen.'); }
    finally { setBusy(false); }
  }
  function select(field: MergeField, side: Side) {
    setPreview(false);
    setChoices(previous => ({ ...previous, fields: { ...previous.fields, [field]: side } }));
  }
  function confirmMerge() {
    Alert.alert('Quittungen zusammenführen?', 'Die Vorschau wird übernommen. Verknüpfungen bleiben erhalten. Eine zweite Ausgabe wird nicht angelegt.', [
      { text: 'Zurück', style: 'cancel' },
      { text: 'Zusammenführen', onPress: () => void run(() => onMerge(choices)) },
    ]);
  }
  const details = (receipt: ReceiptWithItems, label: string) => (
    <View style={styles.column}>
      <Text style={styles.columnTitle}>{label}</Text>
      <Text style={styles.small}>Datum</Text><Text style={styles.important}>{value(receipt.receipt_date)}</Text>
      <Text style={styles.small}>Betrag</Text><Text style={styles.important}>{receipt.total_amount == null ? '—' : receipt.total_amount.toLocaleString('de-CH', { minimumFractionDigits: 2, maximumFractionDigits: 2 })} {receipt.currency}</Text>
      <Text style={styles.small}>Laden</Text><Text style={styles.important}>{receipt.store_name || 'Unbekannt'}</Text>
      {receipt.image_url ? <TouchableOpacity accessibilityRole="button" accessibilityLabel={`${label}: Bild vergrössern`} onPress={() => setZoom(receipt.image_url)}>
        <Image source={{ uri: receipt.image_url }} style={styles.image} resizeMode="contain" /><Text style={styles.link}>Bild vergrössern</Text>
      </TouchableOpacity> : <Text style={styles.placeholder}>Kein Bild vorhanden</Text>}
      <Text style={styles.small}>{receipt.receipt_items.length} Artikel</Text>
      {expanded && receipt.receipt_items.map(item => <View key={item.id} style={styles.item}><Text style={styles.body}>{item.name}</Text><Text style={styles.small}>{value(item.quantity)} {item.unit} · {value(item.total_price)} {receipt.currency}</Text><Text style={styles.small}>{item.tags.join(', ')}</Text></View>)}
      {expanded && receipt.raw_ocr_text && <Text style={styles.small}>{receipt.raw_ocr_text}</Text>}
      {!!(receipt as DuplicateReceipt).receipt_matches?.length && <Text style={styles.small}>Mit Bankbuchung verknüpft</Text>}
      {!!(receipt as DuplicateReceipt).splits?.length && <Text style={styles.small}>Aufteilung vorhanden</Text>}
      {receipt.project_id && <Text style={styles.small}>Projekt zugeordnet</Text>}
    </View>
  );
  return <View style={styles.root}>
    <ScrollView contentContainerStyle={styles.content}>
      <Text style={styles.title}>Duplikat prüfen</Text>
      <Text style={styles.body}>Vergleiche zuerst Datum, Betrag und Laden. Wähle anschliessend, welche Angaben und welches Bild erhalten bleiben.</Text>
      <View style={styles.columns}>{details(target, 'Quittung A')}{details(source, sourceLabel)}</View>
      <TouchableOpacity style={styles.secondary} accessibilityRole="button" onPress={() => setExpanded(v => !v)}><Text style={styles.link}>{expanded ? 'Artikel und erkannten Text ausblenden' : 'Artikel und erkannten Text vergleichen'}</Text></TouchableOpacity>
      <Text style={styles.heading}>Angaben übernehmen</Text>
      <Text style={styles.body}>Fehlende Angaben werden ergänzt. Bei unterschiedlichen Angaben bleibt A vorausgewählt; du kannst jeden Wert ändern.</Text>
      {FIELDS.map(({ key, label }) => {
        const a = target[key]; const b = source[key];
        const selected = choices.fields?.[key] ?? (empty(a) && !empty(b) ? 'source' : 'target');
        return <View key={key} style={styles.field}>
          <Text style={styles.fieldLabel}>{label}{!empty(a) && !empty(b) && String(a) !== String(b) ? ' · unterschiedlich' : ''}</Text>
          <View style={styles.columns}>{(['target', 'source'] as const).map(side => <TouchableOpacity key={side} disabled={busy} accessibilityRole="radio" accessibilityState={{ checked: selected === side }} accessibilityLabel={`${label} aus ${side === 'target' ? 'A' : 'B'}: ${value(side === 'target' ? a : b)}`} style={[styles.choice, selected === side && styles.selected]} onPress={() => select(key, side)}>
            <Text style={styles.choiceText}>{selected === side ? '✓ ' : ''}{value(side === 'target' ? a : b)}</Text>
          </TouchableOpacity>)}</View>
        </View>;
      })}
      <Text style={styles.heading}>Bild und Artikelliste</Text>
      {(['image', 'items'] as const).map(key => <View key={key} style={styles.field}>
        <Text style={styles.fieldLabel}>{key === 'image' ? 'Besseres Bild auswählen' : 'Eine Artikelliste erhalten (keine doppelten Artikel)'}</Text>
        <View style={styles.columns}>{(['target', 'source'] as const).map(side => <TouchableOpacity key={side} disabled={busy} accessibilityRole="radio" accessibilityState={{ checked: choices[key] === side }} style={[styles.choice, choices[key] === side && styles.selected]} onPress={() => { setPreview(false); setChoices(c => ({ ...c, [key]: side })); }}><Text style={styles.choiceText}>{choices[key] === side ? '✓ ' : ''}Quittung {side === 'target' ? 'A' : 'B'}</Text></TouchableOpacity>)}</View>
      </View>)}
      <Text style={styles.body}>Geprüfte Artikel und bestehende Bank-, Split- oder Projektzuordnungen werden geschützt. Bei einem Konflikt wird nichts zusammengeführt und eine Erklärung angezeigt.</Text>
      <View style={styles.field}>
        <Text style={styles.fieldLabel}>Weitere Belegangaben</Text>
        {(Object.keys(EXTRA_LABELS) as (keyof ReceiptExtraFields)[]).filter(key => !empty(target.extra_fields?.[key]) || !empty(source.extra_fields?.[key])).map(key => {
          const a = target.extra_fields?.[key]; const b = source.extra_fields?.[key];
          const selected = choices.extras?.[key] ?? (empty(a) && !empty(b) ? 'source' : 'target');
          return <View key={key} style={styles.field}><Text style={styles.fieldLabel}>{EXTRA_LABELS[key]}{!empty(a) && !empty(b) && a !== b ? ' · unterschiedlich' : ''}</Text><View style={styles.columns}>{(['target', 'source'] as const).map(side => <TouchableOpacity key={side} disabled={busy} accessibilityRole="radio" accessibilityState={{ checked: selected === side }} style={[styles.choice, selected === side && styles.selected]} onPress={() => { setPreview(false); setChoices(c => ({ ...c, extras: { ...c.extras, [key]: side } })); }}><Text style={styles.choiceText}>{selected === side ? '✓ ' : ''}{value(side === 'target' ? a : b)}</Text></TouchableOpacity>)}</View></View>;
        })}
      </View>
      {preview && <View style={styles.preview}>
        <Text style={styles.heading}>So wird die Quittung gespeichert</Text>
        {FIELDS.map(({ key, label }) => {
          const side = choices.fields?.[key] ?? (empty(target[key]) && !empty(source[key]) ? 'source' : 'target');
          return <Text key={key} style={styles.body}>{label}: {value((side === 'target' ? target : source)[key])}</Text>;
        })}
        {(Object.keys(EXTRA_LABELS) as (keyof ReceiptExtraFields)[]).map(key => {
          const selected = choices.extras?.[key] ?? (empty(target.extra_fields?.[key]) ? 'source' : 'target');
          const entry = (selected === 'source' ? source : target).extra_fields?.[key];
          return empty(entry) ? null : <Text key={key} style={styles.body}>{EXTRA_LABELS[key]}: {entry}</Text>;
        })}
        <Text style={styles.body}>Bild aus {choices.image === 'source' ? 'B' : 'A'} · Artikelliste aus {choices.items === 'source' ? 'B' : 'A'}</Text>
        {mergedPreview.receipt_items.map((item, index) => <Text key={`${item.id}-${index}`} style={styles.body}>{item.name} · {value(item.total_price)} {mergedPreview.currency}{item.is_adjustment ? ' · bestehender Ausgleichsposten' : ''}</Text>)}
        <Text style={styles.body}>Quittung A und ihre Verknüpfungen bleiben erhalten. B wird erst nach deiner Bestätigung zusammengeführt.</Text>
      </View>}
      <TouchableOpacity disabled={busy} style={styles.primary} onPress={() => preview ? confirmMerge() : setPreview(true)} accessibilityRole="button"><Text style={styles.primaryText}>{preview ? 'Zusammenführen bestätigen' : 'Zusammenführen · Vorschau'}</Text></TouchableOpacity>
      <TouchableOpacity disabled={busy} style={styles.secondary} onPress={() => Alert.alert('Getrennt behalten?', 'Beide Quittungen bleiben bestehen. Dieses Paar wird nicht erneut vorgeschlagen.', [{ text: 'Zurück', style: 'cancel' }, { text: 'Getrennt behalten', onPress: () => void run(onSeparate) }])}><Text style={styles.link}>Getrennt behalten</Text></TouchableOpacity>
      {onDelete && <View>{(['target', 'source'] as const).map(side => <TouchableOpacity key={side} disabled={busy} style={styles.secondary} onPress={() => Alert.alert(side === 'source' && incoming ? 'Neuen Scan verwerfen?' : `Quittung ${side === 'target' ? 'A' : 'B'} löschen?`, side === 'source' && incoming ? 'Der neue Scan wurde noch nicht gespeichert und wird verworfen.' : 'Die ausgewählte Quittung wird gelöscht. Verknüpfte Quittungen müssen zuerst in der Detailansicht geprüft werden.', [{ text: 'Zurück', style: 'cancel' }, { text: 'Löschen', style: 'destructive', onPress: () => void run(() => onDelete(side)) }])}><Text style={styles.delete}>{side === 'source' && incoming ? 'Neuen Scan verwerfen' : `Quittung ${side === 'target' ? 'A' : 'B'} löschen`}</Text></TouchableOpacity>)}</View>}
      <TouchableOpacity disabled={busy} style={styles.secondary} onPress={onDefer}><Text style={styles.link}>{deferLabel}</Text></TouchableOpacity>
      {busy && <ActivityIndicator color={C.gold} accessibilityLabel="Wird gespeichert" />}
    </ScrollView>
    <Modal visible={zoom !== null} onRequestClose={() => setZoom(null)} animationType="slide">
      <View style={[styles.zoomRoot, { paddingTop: insets.top, paddingBottom: insets.bottom }]}><TouchableOpacity style={styles.secondary} onPress={() => setZoom(null)} accessibilityRole="button"><Text style={styles.link}>Bild schliessen</Text></TouchableOpacity><ScrollView maximumZoomScale={5} minimumZoomScale={1} centerContent contentContainerStyle={{ flexGrow: 1 }}>{zoom && <Image source={{ uri: zoom }} style={{ flex: 1, minHeight: 600, width: '100%' }} resizeMode="contain" />}</ScrollView></View>
    </Modal>
  </View>;
}
const styles = StyleSheet.create({
  root: { flex: 1, backgroundColor: C.bg }, content: { padding: 16, gap: 14, paddingBottom: 40 },
  title: { fontSize: 26, fontWeight: '800', color: C.textPrimary }, heading: { fontSize: 18, fontWeight: '700', color: C.textPrimary },
  body: { fontSize: 15, color: C.textSecondary, lineHeight: 22 }, small: { fontSize: 13, color: C.textSecondary, marginTop: 4 },
  columns: { flexDirection: 'row', gap: 8 }, column: { flex: 1, minWidth: 0, backgroundColor: C.bgCard, padding: 12, borderRadius: R.md, gap: 4 },
  columnTitle: { fontSize: 15, color: C.textPrimary, fontWeight: '700' }, important: { fontSize: 17, fontWeight: '700', color: C.textPrimary },
  image: { height: 190, width: '100%', backgroundColor: C.bgSoft, borderRadius: R.sm, marginTop: 10 }, placeholder: { paddingVertical: 36, color: C.textSecondary },
  item: { paddingVertical: 8, borderTopWidth: 1, borderColor: C.border },
  link: { color: '#915500', fontSize: 15, fontWeight: '600', textAlign: 'center' }, field: { gap: 8, paddingVertical: 6 }, fieldLabel: { fontSize: 14, fontWeight: '600', color: C.textPrimary },
  choice: { flex: 1, minWidth: 0, minHeight: 48, padding: 12, borderWidth: 1, borderColor: C.border, borderRadius: R.sm, backgroundColor: C.bgCard }, selected: { borderColor: '#915500', backgroundColor: C.bgAccent }, choiceText: { fontSize: 15, color: C.textPrimary },
  preview: { padding: 16, backgroundColor: C.bgCard, borderRadius: R.md, gap: 8 }, primary: { backgroundColor: '#915500', minHeight: 50, borderRadius: R.md, padding: 14, justifyContent: 'center' }, primaryText: { color: '#fff', textAlign: 'center', fontSize: 17, fontWeight: '700' },
  secondary: { flexGrow: 1, minHeight: 48, padding: 12, justifyContent: 'center' }, delete: { color: '#B42318', textAlign: 'center', fontSize: 14 }, zoomRoot: { flex: 1, backgroundColor: C.bg },
});
