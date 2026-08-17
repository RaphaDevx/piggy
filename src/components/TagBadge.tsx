import { View, Text, StyleSheet } from 'react-native';
import { getTagColor } from '../lib/categories';

interface Props {
  tag: string;
  small?: boolean;
}

export default function TagBadge({ tag, small = false }: Props) {
  const color = getTagColor(tag);
  return (
    <View style={[styles.badge, small && styles.small, { backgroundColor: `${color}22`, borderColor: `${color}55` }]}>
      <Text style={[styles.text, small && styles.smallText, { color }]}>{tag}</Text>
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
