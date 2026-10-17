import { useCallback, useState } from 'react';
import { ActivityIndicator, ScrollView, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { useFocusEffect, useRouter } from 'expo-router';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import DuplicateReceiptReview from '../src/components/DuplicateReceiptReview';
import { deleteReviewedReceipt, fetchDismissedDuplicatePairs, findDuplicatePairs, keepReceiptsSeparate, loadDuplicateReceipts, mergeReceiptDuplicates } from '../src/lib/receiptDuplicates';
import type { ReceiptWithItems } from '../src/types/receipt';
import { C, R } from '../src/constants/design';

export default function DuplicatesScreen() {
  const router = useRouter(); const insets = useSafeAreaInsets();
  const [pairs, setPairs] = useState<[ReceiptWithItems, ReceiptWithItems][]>([]);
  const [selected, setSelected] = useState<[ReceiptWithItems, ReceiptWithItems] | null>(null);
  const [loading, setLoading] = useState(true); const [error, setError] = useState('');
  const load = useCallback(async () => {
    setLoading(true); setError('');
    try {
      const [receipts, dismissed] = await Promise.all([loadDuplicateReceipts(), fetchDismissedDuplicatePairs()]);
      setPairs(findDuplicatePairs(receipts, dismissed).map(pair => [pair.target, pair.source]));
    } catch (e) { setError(e instanceof Error ? e.message : 'Prüfung konnte nicht geladen werden.'); }
    finally { setLoading(false); }
  }, []);
  useFocusEffect(useCallback(() => { void load(); }, [load]));
  async function complete() { setSelected(null); await load(); }
  return <View style={[styles.root, { paddingTop: insets.top, paddingBottom: insets.bottom }]}>
    <View style={styles.header}><TouchableOpacity accessibilityRole="button" style={styles.back} onPress={() => selected ? setSelected(null) : router.back()}><Text style={styles.link}>Zurück</Text></TouchableOpacity><Text style={styles.nav}>Quittungen prüfen</Text></View>
    {selected ? <DuplicateReceiptReview key={selected.map(r => r.id).join(':')} target={selected[0]} source={selected[1]}
      onMerge={async choices => { await mergeReceiptDuplicates(selected[0].id, selected[1].id, choices); await complete(); }}
      onSeparate={async () => { await keepReceiptsSeparate(selected[0].id, selected[1].id); await complete(); }}
      onDelete={async side => { await deleteReviewedReceipt(selected[side === 'target' ? 0 : 1].id); await complete(); }}
      onDefer={() => setSelected(null)} />
      : loading ? <ActivityIndicator style={{ marginTop: 40 }} color={C.gold} />
      : <ScrollView contentContainerStyle={styles.list}>
        <Text style={styles.title}>Duplikate prüfen</Text>
        <Text style={styles.body}>Ähnliche Quittungen werden nur vorgeschlagen. Erst du entscheidest, ob sie zusammengehören.</Text>
        {error ? <><Text style={styles.body}>{error}</Text><TouchableOpacity style={styles.pair} onPress={() => void load()}><Text style={styles.link}>Erneut versuchen</Text></TouchableOpacity></> : pairs.length === 0 ? <Text style={styles.empty}>Keine möglichen Duplikate gefunden.</Text> : pairs.map(([a, b]) => <TouchableOpacity key={`${a.id}:${b.id}`} style={styles.pair} accessibilityRole="button" onPress={() => setSelected([a, b])}>
          <Text style={styles.merchant}>{a.store_name} / {b.store_name}</Text>
          <Text style={styles.body}>{a.receipt_date ?? 'Datum unbekannt'} · {a.total_amount ?? '—'} {a.currency}</Text>
          <Text style={styles.body}>{b.receipt_date ?? 'Datum unbekannt'} · {b.total_amount ?? '—'} {b.currency}</Text>
          <Text style={styles.link}>Nebeneinander prüfen</Text>
        </TouchableOpacity>)}
      </ScrollView>}
  </View>;
}
const styles = StyleSheet.create({ root: { flex: 1, backgroundColor: C.bg }, header: { flexDirection: 'row', alignItems: 'center', paddingHorizontal: 12, borderBottomWidth: 1, borderColor: C.border }, back: { minHeight: 48, justifyContent: 'center', padding: 8 }, nav: { marginLeft: 20, fontSize: 17, fontWeight: '600', color: C.textPrimary }, list: { padding: 20, gap: 14 }, title: { fontSize: 28, fontWeight: '800', color: C.textPrimary }, body: { fontSize: 15, lineHeight: 22, color: C.textSecondary }, empty: { fontSize: 17, color: C.textSecondary, paddingVertical: 28 }, pair: { padding: 16, backgroundColor: C.bgCard, borderWidth: 1, borderColor: C.border, borderRadius: R.md, gap: 8 }, merchant: { fontSize: 17, fontWeight: '700', color: C.textPrimary }, link: { fontSize: 15, fontWeight: '600', color: '#915500' } });
