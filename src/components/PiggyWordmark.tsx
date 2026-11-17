import { View, Text, StyleSheet } from 'react-native';
import { PiggyIcon } from '../brand/icons/PiggyIcon';
import { C } from '../constants/design';
import { useSubscription } from '../contexts/SubscriptionContext';

/** Gold is a visible entitlement, not a second app or a local preference. */
export function PiggyWordmark({ compact = false }: { compact?: boolean }) {
  const { status } = useSubscription();
  return <View style={styles.row} accessible accessibilityLabel={status.isPlus ? 'Piggy Gold' : 'Piggy'}>
    <PiggyIcon name="piggy" size={compact ? 36 : 30} color={status.isPlus ? C.gold : C.pink} />
    {!compact && <Text style={styles.name}>Piggy</Text>}
    {status.isPlus && <View style={styles.badge}><Text style={styles.gold}>GOLD</Text></View>}
  </View>;
}
const styles = StyleSheet.create({
  row: { flexDirection: 'row', alignItems: 'center', flexWrap: 'wrap', gap: 8 },
  name: { fontSize: 28, fontWeight: '800', color: C.textPrimary, letterSpacing: -0.5 },
  badge: { backgroundColor: C.goldSoft, borderRadius: 8, paddingHorizontal: 8, paddingVertical: 5 },
  gold: { color: C.textPrimary, fontSize: 13, fontWeight: '800', letterSpacing: 0.8 },
});
