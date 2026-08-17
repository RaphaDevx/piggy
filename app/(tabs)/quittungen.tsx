import { useState } from 'react';
import {
  View, Text, FlatList, TextInput, TouchableOpacity,
  StyleSheet, ActivityIndicator, RefreshControl, ScrollView,
  Modal, Pressable,
} from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { useRouter } from 'expo-router';
import { Ionicons } from '@/components/Ionicons';
import ReceiptCard from '../../src/components/ReceiptCard';
import { IncomingSharedReceipts } from '../../src/components/IncomingSharedReceipts';
import { useReceipts, SortKey } from '../../src/hooks/useReceipts';
import { C, R, S } from '../../src/constants/design';

type PeriodFilter = 'all' | 'week' | 'month' | '3months';

const SORT_OPTIONS: { key: SortKey; label: string; icon: string }[] = [
  { key: 'date_desc',   label: 'Datum (neu zuerst)',   icon: 'calendar' },
  { key: 'date_asc',    label: 'Datum (alt zuerst)',   icon: 'calendar-outline' },
  { key: 'amount_desc', label: 'Betrag (hoch → tief)', icon: 'arrow-down' },
  { key: 'amount_asc',  label: 'Betrag (tief → hoch)', icon: 'arrow-up' },
  { key: 'store_asc',   label: 'Geschäft (A–Z)',        icon: 'text' },
];

const PERIOD_OPTIONS: { key: PeriodFilter; label: string }[] = [
  { key: 'all',     label: 'Alle'     },
  { key: 'week',    label: '7 Tage'   },
  { key: 'month',   label: '30 Tage'  },
  { key: '3months', label: '3 Monate' },
];

export default function QuittungenScreen() {
  const insets = useSafeAreaInsets();
  const router  = useRouter();
  const {
    filtered, receipts, loading, refreshing,
    search, setSearch, refresh,
    sortKey, setSortKey,
    filterTag, setFilterTag,
    filterPeriod, setFilterPeriod,
    availableTags,
  } = useReceipts();

  const [showSortModal, setShowSortModal] = useState(false);
  const [showTagFilter, setShowTagFilter] = useState(false);

  const activeFilters = (filterTag ? 1 : 0) + (filterPeriod !== 'all' ? 1 : 0);

  if (loading) {
    return (
      <View style={[styles.center, { paddingTop: insets.top }]}>
        <ActivityIndicator size="large" color={C.gold} />
      </View>
    );
  }

  return (
    <View style={[styles.root, { paddingTop: insets.top }]}>

      {/* Header */}
      <View style={styles.header}>
        <View>
          <Text style={styles.title}>Quittungen</Text>
          <Text style={styles.subtitle}>{filtered.length} von {receipts.length}</Text>
        </View>
        <View style={styles.headerRight}>
          <TouchableOpacity
            style={[styles.headerBtn, activeFilters > 0 && styles.headerBtnActive]}
            onPress={() => setShowTagFilter((v) => !v)}
          >
            <Ionicons name="filter" size={16} color={activeFilters > 0 ? '#fff' : C.textSecondary} />
            {activeFilters > 0 && <Text style={styles.headerBtnBadge}>{activeFilters}</Text>}
          </TouchableOpacity>
          <TouchableOpacity style={styles.headerBtn} onPress={() => setShowSortModal(true)}>
            <Ionicons name="swap-vertical" size={16} color={C.textSecondary} />
          </TouchableOpacity>
        </View>
      </View>

      {/* Eingegangene Quittungen */}
      <View style={{ paddingHorizontal: 20, paddingBottom: 4 }}>
        <IncomingSharedReceipts />
      </View>

      {/* Suche */}
      <View style={styles.searchWrap}>
        <View style={styles.searchBar}>
          <Ionicons name="search-outline" size={16} color={C.textTertiary} />
          <TextInput
            style={styles.searchInput}
            placeholder="Geschäft, Kategorie, Tag…"
            placeholderTextColor={C.textTertiary}
            value={search}
            onChangeText={setSearch}
          />
          {search ? (
            <TouchableOpacity onPress={() => setSearch('')}>
              <Ionicons name="close-circle" size={16} color={C.textTertiary} />
            </TouchableOpacity>
          ) : null}
        </View>
      </View>

      {/* Zeitraum-Chips */}
      <View style={styles.periodChips}>
        {PERIOD_OPTIONS.map((p) => (
          <TouchableOpacity
            key={p.key}
            style={[styles.chip, filterPeriod === p.key && styles.chipActive]}
            onPress={() => setFilterPeriod(p.key)}
          >
            <Text style={[styles.chipText, filterPeriod === p.key && styles.chipTextActive]}>{p.label}</Text>
          </TouchableOpacity>
        ))}
      </View>

      {/* Tag-Filter */}
      {showTagFilter && availableTags.length > 0 && (
        <View style={styles.tagFilterWrap}>
          <ScrollView horizontal showsHorizontalScrollIndicator={false} contentContainerStyle={{ gap: 6, paddingHorizontal: 20, paddingVertical: 8 }}>
            <TouchableOpacity
              style={[styles.tagChip, !filterTag && styles.tagChipActive]}
              onPress={() => setFilterTag(null)}
            >
              <Text style={[styles.tagChipText, !filterTag && styles.tagChipTextActive]}>Alle Tags</Text>
            </TouchableOpacity>
            {availableTags.map((tag) => (
              <TouchableOpacity
                key={tag}
                style={[styles.tagChip, filterTag === tag && styles.tagChipActive]}
                onPress={() => setFilterTag(filterTag === tag ? null : tag)}
              >
                <Text style={[styles.tagChipText, filterTag === tag && styles.tagChipTextActive]}>{tag}</Text>
              </TouchableOpacity>
            ))}
          </ScrollView>
        </View>
      )}

      {/* Sortier-Hinweis */}
      {sortKey !== 'date_desc' && (
        <View style={styles.sortHint}>
          <Ionicons name="swap-vertical" size={12} color={C.textTertiary} />
          <Text style={styles.sortHintText}>{SORT_OPTIONS.find((s) => s.key === sortKey)?.label}</Text>
          <TouchableOpacity onPress={() => setSortKey('date_desc')}>
            <Ionicons name="close-circle" size={14} color={C.textTertiary} />
          </TouchableOpacity>
        </View>
      )}

      <FlatList
        data={filtered}
        keyExtractor={(r) => r.id}
        contentContainerStyle={styles.list}
        showsVerticalScrollIndicator={false}
        refreshControl={<RefreshControl refreshing={refreshing} onRefresh={refresh} tintColor={C.gold} />}
        ListEmptyComponent={
          <View style={styles.empty}>
            <Text style={styles.emptyPig}>🐷</Text>
            <Text style={styles.emptyTitle}>
              {search || filterTag || filterPeriod !== 'all' ? 'Nichts gefunden' : 'Noch keine Quittungen'}
            </Text>
            <Text style={styles.emptyText}>
              {search || filterTag || filterPeriod !== 'all'
                ? 'Keine Treffer für deine Filter.'
                : 'Tippe auf 🐷 und scan deine erste Quittung!'}
            </Text>
          </View>
        }
        renderItem={({ item }) => (
          <ReceiptCard
            receipt={item}
            onPress={() => router.push({ pathname: '/receipt/[id]', params: { id: item.id } })}
          />
        )}
      />

      {/* Sortier-Modal */}
      <Modal visible={showSortModal} transparent animationType="slide" onRequestClose={() => setShowSortModal(false)}>
        <Pressable style={styles.modalOverlay} onPress={() => setShowSortModal(false)}>
          <View style={styles.modalSheet}>
            <View style={styles.modalHandle} />
            <Text style={styles.modalTitle}>Sortieren nach</Text>
            {SORT_OPTIONS.map((opt) => (
              <TouchableOpacity
                key={opt.key}
                style={styles.modalOption}
                onPress={() => { setSortKey(opt.key); setShowSortModal(false); }}
              >
                <Ionicons name={opt.icon as any} size={18} color={sortKey === opt.key ? C.gold : C.textSecondary} />
                <Text style={[styles.modalOptionText, sortKey === opt.key && styles.modalOptionTextActive]}>
                  {opt.label}
                </Text>
                {sortKey === opt.key && <Ionicons name="checkmark" size={18} color={C.gold} />}
              </TouchableOpacity>
            ))}
            <View style={{ height: 20 }} />
          </View>
        </Pressable>
      </Modal>
    </View>
  );
}

const styles = StyleSheet.create({
  root:   { flex: 1, backgroundColor: C.bg },
  center: { flex: 1, backgroundColor: C.bg, alignItems: 'center', justifyContent: 'center' },

  header: {
    flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between',
    paddingHorizontal: 20, paddingTop: 16, paddingBottom: 8,
  },
  title:       { fontSize: S.xxl, fontWeight: '800', color: C.textPrimary, letterSpacing: -0.5 },
  subtitle:    { fontSize: S.sm, color: C.textSecondary, marginTop: 2 },
  headerRight: { flexDirection: 'row', gap: 8 },
  headerBtn: {
    flexDirection: 'row', alignItems: 'center', gap: 4,
    backgroundColor: C.bgCard, borderRadius: R.md,
    paddingHorizontal: 12, paddingVertical: 8,
    borderWidth: 1, borderColor: C.border,
  },
  headerBtnActive: { backgroundColor: C.gold, borderColor: C.gold },
  headerBtnBadge:  { fontSize: 11, fontWeight: '800', color: '#fff' },

  searchWrap: { paddingHorizontal: 20, paddingBottom: 8 },
  searchBar: {
    flexDirection: 'row', alignItems: 'center', gap: 8,
    backgroundColor: C.bgCard, borderRadius: R.lg,
    paddingHorizontal: 14, paddingVertical: 12,
    borderWidth: 1, borderColor: C.border,
  },
  searchInput: { flex: 1, color: C.textPrimary, fontSize: S.md },

  periodChips:  { flexDirection: 'row', gap: 8, paddingHorizontal: 20, paddingBottom: 8 },
  chip:         { borderWidth: 1, borderColor: C.border, borderRadius: 20, paddingHorizontal: 14, paddingVertical: 6, backgroundColor: C.bgCard },
  chipActive:   { backgroundColor: C.gold, borderColor: C.gold },
  chipText:     { fontSize: S.xs, fontWeight: '600', color: C.textSecondary },
  chipTextActive: { color: '#fff' },

  tagFilterWrap: { borderTopWidth: 1, borderTopColor: C.borderSoft, paddingVertical: 4 },
  tagChip:        { borderWidth: 1, borderColor: C.border, borderRadius: 20, paddingHorizontal: 12, paddingVertical: 5, backgroundColor: C.bgCard },
  tagChipActive:  { backgroundColor: C.bgAccent, borderColor: C.gold },
  tagChipText:    { fontSize: S.xs, fontWeight: '600', color: C.textSecondary },
  tagChipTextActive: { color: C.gold, fontWeight: '700' },

  sortHint: { flexDirection: 'row', alignItems: 'center', gap: 6, paddingHorizontal: 20, paddingBottom: 8 },
  sortHintText: { fontSize: S.xs, color: C.textTertiary, flex: 1 },

  list: { paddingHorizontal: 20, paddingBottom: 20, gap: 10 },

  empty:      { alignItems: 'center', marginTop: 60, gap: 12 },
  emptyPig:   { fontSize: 56 },
  emptyTitle: { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },
  emptyText:  { fontSize: S.sm, color: C.textSecondary, textAlign: 'center', maxWidth: 240 },

  modalOverlay: { flex: 1, backgroundColor: '#00000060', justifyContent: 'flex-end' },
  modalSheet:   { backgroundColor: C.bgCard, borderTopLeftRadius: 24, borderTopRightRadius: 24, padding: 20, paddingTop: 12 },
  modalHandle:  { width: 36, height: 4, backgroundColor: C.border, borderRadius: 2, alignSelf: 'center', marginBottom: 16 },
  modalTitle:   { fontSize: S.lg, fontWeight: '800', color: C.textPrimary, marginBottom: 12 },
  modalOption:  { flexDirection: 'row', alignItems: 'center', gap: 12, paddingVertical: 14, borderBottomWidth: 1, borderBottomColor: C.borderSoft },
  modalOptionText:       { flex: 1, fontSize: S.md, color: C.textSecondary },
  modalOptionTextActive: { color: C.textPrimary, fontWeight: '700' },
});
