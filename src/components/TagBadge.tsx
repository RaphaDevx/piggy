import { View, Text, StyleSheet } from 'react-native';
import { getTagColor, normalizeCategory } from '../lib/categories';
import { categoryLabel, subcategoryLabel } from '../lib/taxonomy';
import { useLocale } from '../hooks/useLocale';

interface Props {
  tag: string;
  /** Unterkategorie-Schlüssel — zeigt "Hauptkategorie › Unterkategorie" */
  subcategory?: string | null;
  small?: boolean;
}

export default function TagBadge({ tag, subcategory, small = false }: Props) {
  const locale = useLocale();
  const color = getTagColor(tag);
  const main = categoryLabel(normalizeCategory(tag), locale);
  const label = subcategory
    ? `${main} › ${subcategoryLabel(subcategory, locale)}`
    : main;
  return (
    <View style={[styles.badge, small && styles.small, { backgroundColor: `${color}22`, borderColor: `${color}55` }]}>
      <Text style={[styles.text, small && styles.smallText, { color }]}>{label}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  badge: {
    borderRadius: 20, paddingHorizontal: 10, paddingVertical: 4,
    borderWidth: 1,
  },
  small: { paddingHorizontal: 7, paddingVertical: 2 },
  text:      { fontSize: 12, fontWeight: '600' },
  smallText: { fontSize: 10 },
});
