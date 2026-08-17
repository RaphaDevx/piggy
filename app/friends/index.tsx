import { useState } from 'react';
import {
  View, Text, TouchableOpacity, StyleSheet, ScrollView,
  ActivityIndicator, Alert,
} from 'react-native';
import { useRouter } from 'expo-router';
import { useFocusEffect } from 'expo-router';
import { useCallback } from 'react';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { Ionicons } from '@/components/Ionicons';
import { useFriends } from '../../src/hooks/useFriends';
import { C, R, S, card } from '../../src/constants/design';
import type { FriendWithProfile } from '../../src/types/social';

type Tab = 'freunde' | 'anfragen';

export default function FriendsScreen() {
  const insets  = useSafeAreaInsets();
  const router  = useRouter();
  const { friends, incoming, outgoing, loading, reload, acceptRequest, declineRequest, removeFriend } = useFriends();
  const [tab, setTab] = useState<Tab>('freunde');

  useFocusEffect(useCallback(() => { reload(); }, [reload]));

  function confirmRemove(item: FriendWithProfile) {
    Alert.alert(
      'Freund entfernen',
      `${item.profile.display_name ?? item.profile.piggy_code} wirklich entfernen?`,
      [
        { text: 'Abbrechen', style: 'cancel' },
        { text: 'Entfernen', style: 'destructive', onPress: () => removeFriend(item.id) },
      ],
    );
  }

  const pendingCount = incoming.length;

  return (
    <View style={[styles.container, { paddingTop: insets.top }]}>
      {/* Header */}
      <View style={styles.header}>
        <TouchableOpacity onPress={() => router.back()} style={styles.backBtn}>
          <Ionicons name="chevron-back" size={24} color={C.textPrimary} />
        </TouchableOpacity>
        <Text style={styles.title}>Freunde</Text>
        <TouchableOpacity style={styles.addBtn} onPress={() => router.push('/friends/add' as any)}>
          <Ionicons name="person-add-outline" size={22} color={C.gold} />
        </TouchableOpacity>
      </View>

      {/* Tabs */}
      <View style={styles.tabRow}>
        {(['freunde', 'anfragen'] as Tab[]).map((t) => (
          <TouchableOpacity key={t} style={[styles.tabBtn, tab === t && styles.tabBtnActive]} onPress={() => setTab(t)}>
            <Text style={[styles.tabText, tab === t && styles.tabTextActive]}>
              {t === 'freunde' ? 'Freunde' : 'Anfragen'}
              {t === 'anfragen' && pendingCount > 0 ? ` (${pendingCount})` : ''}
            </Text>
          </TouchableOpacity>
        ))}
      </View>

      {loading ? (
        <View style={styles.center}><ActivityIndicator color={C.gold} /></View>
      ) : (
        <ScrollView contentContainerStyle={styles.list} showsVerticalScrollIndicator={false}>
          {tab === 'freunde' && (
            friends.length === 0 ? (
              <EmptyState icon="people-outline" text="Noch keine Freunde" sub='Tippe auf "+" um Freunde hinzuzufügen' />
            ) : (
              friends.map((f) => (
                <FriendRow key={f.id} item={f} onRemove={() => confirmRemove(f)} />
              ))
            )
          )}

          {tab === 'anfragen' && (
            <>
              {incoming.length > 0 && (
                <>
                  <Text style={styles.groupLabel}>Eingegangen</Text>
                  {incoming.map((f) => (
                    <RequestRow
                      key={f.id} item={f} type="incoming"
                      onAccept={() => acceptRequest(f.id)}
                      onDecline={() => declineRequest(f.id)}
                    />
                  ))}
                </>
              )}
              {outgoing.length > 0 && (
                <>
                  <Text style={[styles.groupLabel, { marginTop: 20 }]}>Gesendet</Text>
                  {outgoing.map((f) => (
                    <RequestRow
                      key={f.id} item={f} type="outgoing"
                      onDecline={() => declineRequest(f.id)}
                    />
                  ))}
                </>
              )}
              {incoming.length === 0 && outgoing.length === 0 && (
                <EmptyState icon="mail-outline" text="Keine Anfragen" sub="Hier erscheinen ein- und ausgehende Freundschaftsanfragen" />
              )}
            </>
          )}
        </ScrollView>
      )}
    </View>
  );
}

function FriendRow({ item, onRemove }: { item: FriendWithProfile; onRemove: () => void }) {
  const name = item.profile.display_name ?? item.profile.piggy_code;
  const sub  = item.profile.display_name ? item.profile.piggy_code : '';
  return (
    <View style={[card, styles.row]}>
      <View style={styles.avatar}>
        <Text style={styles.avatarText}>{name.charAt(0).toUpperCase()}</Text>
      </View>
      <View style={{ flex: 1 }}>
        <Text style={styles.rowName}>{name}</Text>
        {sub ? <Text style={styles.rowSub}>{sub}</Text> : null}
      </View>
      <TouchableOpacity onPress={onRemove} hitSlop={8}>
        <Ionicons name="person-remove-outline" size={20} color={C.textTertiary} />
      </TouchableOpacity>
    </View>
  );
}

function RequestRow({ item, type, onAccept, onDecline }: {
  item: FriendWithProfile; type: 'incoming' | 'outgoing';
  onAccept?: () => void; onDecline: () => void;
}) {
  const name = item.profile.display_name ?? item.profile.piggy_code;
  return (
    <View style={[card, styles.row]}>
      <View style={styles.avatar}>
        <Text style={styles.avatarText}>{name.charAt(0).toUpperCase()}</Text>
      </View>
      <View style={{ flex: 1 }}>
        <Text style={styles.rowName}>{name}</Text>
        <Text style={styles.rowSub}>{item.profile.piggy_code}</Text>
      </View>
      {type === 'incoming' && (
        <View style={styles.actionRow}>
          <TouchableOpacity style={styles.acceptBtn} onPress={onAccept}>
            <Ionicons name="checkmark" size={18} color="#fff" />
          </TouchableOpacity>
          <TouchableOpacity style={styles.declineBtn} onPress={onDecline}>
            <Ionicons name="close" size={18} color={C.textTertiary} />
          </TouchableOpacity>
        </View>
      )}
      {type === 'outgoing' && (
        <TouchableOpacity onPress={onDecline} hitSlop={8}>
          <Text style={styles.cancelText}>Abbrechen</Text>
        </TouchableOpacity>
      )}
    </View>
  );
}

function EmptyState({ icon, text, sub }: { icon: string; text: string; sub: string }) {
  return (
    <View style={styles.emptyBox}>
      <Ionicons name={icon as any} size={48} color={C.textTertiary} />
      <Text style={styles.emptyText}>{text}</Text>
      <Text style={styles.emptySub}>{sub}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: C.bg },
  header:    { flexDirection: 'row', alignItems: 'center', paddingHorizontal: 20, paddingVertical: 16, gap: 12 },
  backBtn:   { padding: 4 },
  title:     { flex: 1, fontSize: S.xxl, fontWeight: '800', color: C.textPrimary, letterSpacing: -0.5 },
  addBtn:    { width: 40, height: 40, borderRadius: 20, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },

  tabRow:        { flexDirection: 'row', marginHorizontal: 20, marginBottom: 16, backgroundColor: C.bgAccent, borderRadius: R.md, padding: 4 },
  tabBtn:        { flex: 1, paddingVertical: 8, alignItems: 'center', borderRadius: R.sm - 2 },
  tabBtnActive:  { backgroundColor: C.bg },
  tabText:       { fontSize: S.sm, fontWeight: '600', color: C.textTertiary },
  tabTextActive: { color: C.textPrimary },

  center: { flex: 1, alignItems: 'center', justifyContent: 'center' },
  list:   { paddingHorizontal: 20, paddingBottom: 40, gap: 10 },

  groupLabel: { fontSize: S.xs, color: C.textTertiary, fontWeight: '700', letterSpacing: 1, textTransform: 'uppercase', marginBottom: 4 },

  row:        { flexDirection: 'row', alignItems: 'center', gap: 14, padding: 14 },
  avatar:     { width: 44, height: 44, borderRadius: 22, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  avatarText: { fontSize: S.lg, fontWeight: '700', color: C.gold },
  rowName:    { fontSize: S.md, fontWeight: '600', color: C.textPrimary },
  rowSub:     { fontSize: S.xs, color: C.textTertiary, marginTop: 2 },

  actionRow:  { flexDirection: 'row', gap: 8 },
  acceptBtn:  { width: 36, height: 36, borderRadius: 18, backgroundColor: '#34C759', alignItems: 'center', justifyContent: 'center' },
  declineBtn: { width: 36, height: 36, borderRadius: 18, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  cancelText: { fontSize: S.xs, color: C.textTertiary, fontWeight: '600' },

  emptyBox:  { alignItems: 'center', paddingTop: 80, gap: 12 },
  emptyText: { fontSize: S.lg, fontWeight: '700', color: C.textPrimary },
  emptySub:  { fontSize: S.sm, color: C.textTertiary, textAlign: 'center', maxWidth: 260 },
});
