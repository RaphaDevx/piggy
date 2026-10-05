import { View, Text, StyleSheet } from 'react-native';
import type { SpendingByTag } from '../types/receipt';
import { getTagColor, normalizeCategory } from '../lib/categories';
import { categoryLabel } from '../lib/taxonomy';
import { useLocale } from '../hooks/useLocale';
import { C, R, S } from '../constants/design';

interface Props {
  data: SpendingByTag[];
  currency: string;
}

export default function SpendingChart({ data, currency }: Props) {
  const locale = useLocale();
  if (data.length === 0) return null;

  const top = [...data].sort((a, b) => b.total - a.total).slice(0, 6);
  const max = top[0].total;

  return (
    <View style={styles.container}>
      {top.map((item) => {
        const pct = Math.max((item.total / max) * 100, 4);
        const color = getTagColor(item.tag);
        const label = categoryLabel(normalizeCategory(item.tag), locale);
        return (
          <View key={item.tag} style={styles.row}>
            <Text style={styles.label} numberOfLines={1}>
              {label.length > 14 ? label.slice(0, 13) + '…' : label}
            </Text>
            <View style={styles.barTrack}>
              <View style={[styles.barFill, { width: `${pct}%`, backgroundColor: color }]} />
            </View>
            <Text style={styles.value}>{currency} {item.total.toFixed(0)}</Text>
          </View>
        );
      })}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { padding: 16, gap: 12 },
  row:       { flexDirection: 'row', alignItems: 'center', gap: 10 },
  label:     { width: 100, fontSize: S.xs, color: C.textSecondary, fontWeight: '600' },
  barTrack:  { flex: 1, height: 8, backgroundColor: C.bgSoft, borderRadius: 4, overflow: 'hidden' },
  barFill:   { height: '100%', borderRadius: 4 },
  value:     { width: 64, fontSize: S.xs, color: C.textSecondary, fontWeight: '700', textAlign: 'right' },
});
