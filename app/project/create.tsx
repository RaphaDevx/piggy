import { useState } from 'react';
import {
  View, Text, TextInput, TouchableOpacity, StyleSheet,
  ScrollView, ActivityIndicator, Alert,
} from 'react-native';
import { useRouter } from 'expo-router';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { Ionicons } from '@/components/Ionicons';
import { useFriends } from '../../src/hooks/useFriends';
import { createProject } from '../../src/hooks/useProjects';
import { C, R, S, card } from '../../src/constants/design';

const EMOJIS = ['📍','✈️','🏕️','🍕','🎉','🏠','🚗','⛵','🎭','🎸','🏔️','🌊','🛒','💼','🎓'];

export default function CreateProjectScreen() {
  const insets = useSafeAreaInsets();
  const router = useRouter();

  const [name, setName]           = useState('');
  const [emoji, setEmoji]         = useState('📍');
  const [description, setDesc]    = useState('');
  const [currency, setCurrency]   = useState('CHF');
  const [selected, setSelected]   = useState<Set<string>>(new Set());
  const [saving, setSaving]       = useState(false);

  const { friends, loading: friendsLoading } = useFriends();

  function toggleFriend(id: string) {
    setSelected((prev) => {
      const next = new Set(prev);
      if (next.has(id)) next.delete(id); else next.add(id);
      return next;
    });
  }

  async function handleCreate() {
    const trimmed = name.trim();
    if (!trimmed) { Alert.alert('Hinweis', 'Gib dem Projekt einen Namen.'); return; }
    setSaving(true);
    const { id, error } = await createProject({
      name: trimmed,
      emoji,
      description: description.trim() || undefined,
      currency,
      friendIds: Array.from(selected),
    });
    setSaving(false);
    if (error) { Alert.alert('Fehler', error); return; }
    router.replace(`/project/${id}` as any);
  }

  return (
    <View style={[styles.container, { paddingTop: insets.top }]}>
      <View style={styles.header}>
        <TouchableOpacity onPress={() => router.back()} style={styles.backBtn}>
          <Ionicons name="chevron-back" size={24} color={C.textPrimary} />
        </TouchableOpacity>
        <Text style={styles.title}>Neues Projekt</Text>
        <View style={{ width: 40 }} />
      </View>

      <ScrollView contentContainerStyle={styles.body} showsVerticalScrollIndicator={false}>
        {/* Emoji Picker */}
        <Text style={styles.label}>Emoji</Text>
        <ScrollView horizontal showsHorizontalScrollIndicator={false} style={styles.emojiScroll}>
          {EMOJIS.map((e) => (
            <TouchableOpacity
              key={e}
              style={[styles.emojiOption, emoji === e && styles.emojiSelected]}
              onPress={() => setEmoji(e)}
            >
              <Text style={styles.emojiText}>{e}</Text>
            </TouchableOpacity>
          ))}
        </ScrollView>

        {/* Name */}
        <Text style={styles.label}>Name *</Text>
        <TextInput
          style={[card, styles.input]}
          placeholder="z.B. Road Trip Schweiz"
          placeholderTextColor={C.textTertiary}
          value={name}
          onChangeText={setName}
          maxLength={60}
        />

        {/* Description */}
        <Text style={styles.label}>Beschreibung <Text style={styles.optional}>(optional)</Text></Text>
        <TextInput
          style={[card, styles.input, styles.inputMulti]}
          placeholder="Kurze Notiz zum Projekt…"
          placeholderTextColor={C.textTertiary}
          value={description}
          onChangeText={setDesc}
          multiline
          maxLength={200}
        />

        {/* Currency */}
        <Text style={styles.label}>Währung</Text>
        <View style={styles.currencyRow}>
          {['CHF', 'EUR', 'USD'].map((c) => (
            <TouchableOpacity
              key={c}
              style={[styles.currencyChip, currency === c && styles.currencyActive]}
              onPress={() => setCurrency(c)}
            >
              <Text style={[styles.currencyText, currency === c && styles.currencyTextActive]}>{c}</Text>
            </TouchableOpacity>
          ))}
        </View>

        {/* Friends */}
        <Text style={styles.label}>Mitglieder einladen <Text style={styles.optional}>(optional)</Text></Text>
        {friendsLoading ? (
          <ActivityIndicator color={C.gold} style={{ marginVertical: 12 }} />
        ) : friends.length === 0 ? (
          <View style={[card, styles.emptyFriends]}>
            <Text style={styles.emptyText}>Noch keine Freunde — füge zuerst Freunde im Profil hinzu.</Text>
          </View>
        ) : (
          <View style={styles.friendsGrid}>
            {friends.map((f) => {
              const name = f.profile.display_name ?? f.profile.piggy_code;
              const active = selected.has(f.profile.id);
              return (
                <TouchableOpacity
                  key={f.id}
                  style={[styles.friendChip, active && styles.friendChipActive]}
                  onPress={() => toggleFriend(f.profile.id)}
                >
                  <View style={[styles.chipAvatar, active && styles.chipAvatarActive]}>
                    <Text style={[styles.chipAvatarText, active && { color: '#fff' }]}>
                      {name.charAt(0).toUpperCase()}
                    </Text>
                  </View>
                  <Text style={[styles.chipName, active && styles.chipNameActive]} numberOfLines={1}>
                    {name}
                  </Text>
                  {active && <Ionicons name="checkmark-circle" size={16} color={C.gold} />}
                </TouchableOpacity>
              );
            })}
          </View>
        )}

        <TouchableOpacity
          style={[styles.createBtn, (saving || !name.trim()) && styles.btnDisabled]}
          onPress={handleCreate}
          disabled={saving || !name.trim()}
        >
          {saving
            ? <ActivityIndicator color="#fff" />
            : <Text style={styles.createBtnText}>Projekt erstellen</Text>}
        </TouchableOpacity>
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: C.bg },

  header: { flexDirection: 'row', alignItems: 'center', paddingHorizontal: 20, paddingVertical: 14, gap: 12 },
  backBtn: { width: 40, height: 40, borderRadius: 20, backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center' },
  title:   { flex: 1, fontSize: S.xl, fontWeight: '800', color: C.textPrimary, letterSpacing: -0.5 },

  body: { paddingHorizontal: 20, paddingBottom: 60, gap: 10 },

  label:    { fontSize: S.xs, color: C.textTertiary, fontWeight: '700', letterSpacing: 1, textTransform: 'uppercase', marginTop: 6 },
  optional: { fontWeight: '400', textTransform: 'none', letterSpacing: 0 },

  emojiScroll: { flexGrow: 0, marginBottom: 4 },
  emojiOption: {
    width: 48, height: 48, borderRadius: R.sm, marginRight: 8,
    backgroundColor: C.bgSoft, alignItems: 'center', justifyContent: 'center',
    borderWidth: 2, borderColor: 'transparent',
  },
  emojiSelected: { borderColor: C.gold, backgroundColor: C.bgAccent },
  emojiText:     { fontSize: 24 },

  input: {
    padding: 14, fontSize: S.md, color: C.textPrimary,
    borderWidth: 1, borderColor: C.border,
  },
  inputMulti: { minHeight: 80, textAlignVertical: 'top' },

  currencyRow: { flexDirection: 'row', gap: 10 },
  currencyChip: {
    paddingHorizontal: 20, paddingVertical: 10, borderRadius: R.md,
    backgroundColor: C.bgSoft, borderWidth: 1.5, borderColor: 'transparent',
  },
  currencyActive:     { borderColor: C.gold, backgroundColor: C.bgAccent },
  currencyText:       { fontSize: S.sm, fontWeight: '600', color: C.textSecondary },
  currencyTextActive: { color: C.textPrimary },

  emptyFriends: { padding: 16 },
  emptyText:    { fontSize: S.sm, color: C.textTertiary, textAlign: 'center' },

  friendsGrid:  { flexDirection: 'row', flexWrap: 'wrap', gap: 10 },
  friendChip:   {
    flexDirection: 'row', alignItems: 'center', gap: 8,
    paddingHorizontal: 12, paddingVertical: 8, borderRadius: 20,
    backgroundColor: C.bgAccent, borderWidth: 1, borderColor: C.borderSoft,
  },
  friendChipActive:   { borderColor: C.gold, backgroundColor: '#FFF5E6' },
  chipAvatar:         { width: 28, height: 28, borderRadius: 14, backgroundColor: C.border, alignItems: 'center', justifyContent: 'center' },
  chipAvatarActive:   { backgroundColor: C.gold },
  chipAvatarText:     { fontSize: S.xs, fontWeight: '700', color: C.textSecondary },
  chipName:           { fontSize: S.sm, fontWeight: '600', color: C.textSecondary, maxWidth: 90 },
  chipNameActive:     { color: C.textPrimary },

  createBtn:     { marginTop: 16, backgroundColor: C.gold, borderRadius: R.md, paddingVertical: 16, alignItems: 'center' },
  btnDisabled:   { opacity: 0.4 },
  createBtnText: { color: '#fff', fontWeight: '700', fontSize: S.md },
});
