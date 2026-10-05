import { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet } from 'react-native';
import { Ionicons } from '@/components/Ionicons';
import { C, S } from '../constants/design';
import { EXPENSE_CATEGORIES, ITEM_CATEGORY_KEYS, getCategoryColor } from '../lib/categories';
import { categoryDef, categoryLabel, subcategoryLabel } from '../lib/taxonomy';
import { resolveSubcategory } from '../lib/itemSubcategory';
import { useLocale } from '../hooks/useLocale';

const MAX_CUSTOM_LENGTH = 30;
const MORE_CATEGORY_KEYS = EXPENSE_CATEGORIES.map((c) => c.key).filter((k) => !ITEM_CATEGORY_KEYS.includes(k));

interface Props {
  value: string;
  /** Unterkategorie-Schlüssel der aktuellen Auswahl (nur bei Taxonomie-Kategorien) */
  subcategory?: string | null;
  /** Artikelname — schlägt beim Kategoriewechsel eine passende Unterkategorie vor */
  itemName?: string;
  onChange: (category: string, subcategory: string | null) => void;
  customCategories?: string[];
  /** Wenn gesetzt: genau diese Optionen in dieser Reihenfolge, ohne "Mehr"/"+ Eigene" */
  options?: string[];
}

export default function TagPicker({ value, subcategory = null, itemName = '', onChange, customCategories = [], options }: Props) {
  const locale = useLocale();
  const [showMore, setShowMore] = useState(false);
  const [adding, setAdding] = useState(false);
  const [draft, setDraft] = useState('');

  // Eigene Kategorien des Users plus ggf. die aktuell gewählte, falls sie noch nirgends gespeichert ist
  const known = [...ITEM_CATEGORY_KEYS, ...MORE_CATEGORY_KEYS];
  const custom = [...customCategories];
  if (value && !known.includes(value) && !custom.includes(value)) custom.push(value);

  const subcategories = categoryDef(value)?.subcategories ?? [];

  function confirmDraft() {
    const name = draft.trim();
    if (name) onChange(name, null);
    setDraft('');
    setAdding(false);
  }

  function selectCategory(key: string) {
    if (options || key === value) { onChange(key, key === value ? subcategory : null); return; }
    onChange(key, itemName ? resolveSubcategory(itemName, key) : null);
  }

  function subChip(key: string) {
    const active = key === subcategory;
    const color = getCategoryColor(value);
    return (
      <TouchableOpacity
        key={key}
        onPress={() => onChange(value, active ? null : key)}
        style={[styles.chip, active && { backgroundColor: `${color}18`, borderColor: color }]}
      >
        <Text style={[styles.chipText, active && { color }]}>{subcategoryLabel(key, locale)}</Text>
      </TouchableOpacity>
    );
  }

  function chip(key: string) {
    const active = key === value;
    const color = getCategoryColor(key);
    return (
      <TouchableOpacity
        key={key}
        onPress={() => selectCategory(key)}
        style={[styles.chip, active && { backgroundColor: `${color}18`, borderColor: color }]}
      >
        <View style={[styles.dot, { backgroundColor: color }]} />
        <Text style={[styles.chipText, active && { color }]}>{categoryLabel(key, locale)}</Text>
      </TouchableOpacity>
    );
  }

  if (options) {
    return <View style={styles.wrap}>{options.map(chip)}</View>;
  }

  return (
    <View>
      <View style={styles.wrap}>
        {ITEM_CATEGORY_KEYS.map(chip)}
        <TouchableOpacity style={styles.chip} onPress={() => setShowMore((v) => !v)}>
          <Text style={styles.chipText}>{showMore ? 'Weniger' : 'Mehr'}</Text>
        </TouchableOpacity>
        {showMore && MORE_CATEGORY_KEYS.map(chip)}
        {custom.map(chip)}
        <TouchableOpacity style={styles.chip} onPress={() => setAdding((v) => !v)}>
          <Text style={styles.chipText}>+ Eigene</Text>
        </TouchableOpacity>
      </View>
      {subcategories.length > 0 && (
        <View style={[styles.wrap, styles.subWrap]}>{subcategories.map((s) => subChip(s.key))}</View>
      )}
      {adding && (
        <View style={styles.inputRow}>
          <TextInput
            style={styles.input}
            value={draft}
            onChangeText={setDraft}
            maxLength={MAX_CUSTOM_LENGTH}
            placeholder="Eigene Kategorie"
            placeholderTextColor={C.textTertiary}
            autoFocus
            returnKeyType="done"
            onSubmitEditing={confirmDraft}
          />
          <TouchableOpacity onPress={confirmDraft} style={styles.confirm}>
            <Ionicons name="checkmark-circle" size={24} color={C.gold} />
          </TouchableOpacity>
        </View>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  wrap:     { flexDirection: 'row', flexWrap: 'wrap', gap: 6, marginTop: 4 },
  chip:     { flexDirection: 'row', alignItems: 'center', gap: 5, borderWidth: 1, borderColor: C.border, borderRadius: 20, paddingHorizontal: 10, paddingVertical: 4, backgroundColor: C.bgSoft },
  chipText: { color: C.textTertiary, fontSize: S.xs },
  dot:      { width: 6, height: 6, borderRadius: 3 },
  subWrap:  { paddingLeft: 8, borderLeftWidth: 2, borderLeftColor: C.border, marginTop: 8 },
  inputRow: { flexDirection: 'row', alignItems: 'center', gap: 8, marginTop: 8 },
  input:    { flex: 1, borderWidth: 1, borderColor: C.border, borderRadius: 10, paddingHorizontal: 10, paddingVertical: 6, color: C.textPrimary, fontSize: S.sm, backgroundColor: C.bgSoft },
  confirm:  { padding: 2 },
});
