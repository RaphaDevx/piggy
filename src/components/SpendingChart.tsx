import { View, Text, StyleSheet } from 'react-native';
import type { SpendingByTag } from '../types/receipt';
import { getTagColor } from '../lib/categories';
import { C, R, S } from '../constants/design';

interface Props {
  data: SpendingByTag[];
  currency: string;
}

export default function SpendingChart({ data, currency }: Props) {
  if (data.length === 0) return null;

  const top = [...data].sort((a, b) => b.total - a.total).slice(0, 6);
  const max = top[0].total;

  return (
    <View style={styles.container}>
      {top.map((item) => {
        const pct = Math.max((item.total / max) * 100, 4);
        const color = getTagColor(item.tag);
        return (
          <View key={item.tag} style={styles.row}>
            <Text style={styles.label} numberOfLines={1}>
              {item.tag.length > 14 ? item.tag.slice(0, 13) + '…' : item.tag}
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
