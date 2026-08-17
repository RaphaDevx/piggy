import { useState, useCallback } from 'react';
import {
  View, Text, ScrollView, TouchableOpacity, StyleSheet, ActivityIndicator,
} from 'react-native';
import { useRouter, useFocusEffect } from 'expo-router';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { Ionicons } from '@/components/Ionicons';
import { useProjects } from '../../src/hooks/useProjects';
import { C, R, S, card } from '../../src/constants/design';
import type { ProjectWithMembers } from '../../src/types/project';

function ProjectCard({ project, onPress }: { project: ProjectWithMembers; onPress: () => void }) {
  const members = project.project_members ?? [];
  return (
    <TouchableOpacity style={[card, styles.projectCard]} onPress={onPress} activeOpacity={0.8}>
      <View style={styles.emojiBox}>
        <Text style={styles.emoji}>{project.emoji}</Text>
      </View>
      <View style={{ flex: 1 }}>
        <Text style={styles.projectName} numberOfLines={1}>{project.name}</Text>
        {project.description ? (
          <Text style={styles.projectDesc} numberOfLines={1}>{project.description}</Text>
        ) : null}
        <View style={styles.memberRow}>
          {members.slice(0, 4).map((m) => {
            const name = m.profile?.display_name ?? m.profile?.piggy_code ?? '?';
            return (
              <View key={m.id} style={styles.memberDot}>
                <Text style={styles.memberDotText}>{name.charAt(0).toUpperCase()}</Text>
              </View>
            );
          })}
          {members.length > 4 && (
            <Text style={styles.memberMore}>+{members.length - 4}</Text>
          )}
        </View>
      </View>
      <Ionicons name="chevron-forward" size={18} color={C.textTertiary} />
    </TouchableOpacity>
  );
}

export default function ProjekteScreen() {
  const insets = useSafeAreaInsets();
  const router = useRouter();
  const { projects, loading, reload } = useProjects();

  useFocusEffect(useCallback(() => { reload(); }, [reload]));

  return (
    <View style={[styles.container, { paddingTop: insets.top }]}>
      <View style={styles.header}>
        <Text style={styles.title}>Projekte</Text>
        <TouchableOpacity
          style={styles.addBtn}
          onPress={() => router.push('/project/create' as any)}
        >
          <Ionicons name="add" size={22} color="#fff" />
        </TouchableOpacity>
      </View>

      {loading ? (
        <View style={styles.center}>
          <ActivityIndicator color={C.gold} />
        </View>
      ) : projects.length === 0 ? (
        <View style={styles.empty}>
          <Text style={styles.emptyEmoji}>🗺️</Text>
          <Text style={styles.emptyTitle}>Noch kein Projekt</Text>
          <Text style={styles.emptyText}>
            Erstelle ein Projekt für Reisen, Events oder Wohngemeinschaften – und teile Ausgaben einfach mit Freunden.
          </Text>
          <TouchableOpacity
            style={styles.createBtn}
            onPress={() => router.push('/project/create' as any)}
          >
            <Text style={styles.createBtnText}>Erstes Projekt erstellen</Text>
          </TouchableOpacity>
        </View>
      ) : (
        <ScrollView
          contentContainerStyle={styles.list}
          showsVerticalScrollIndicator={false}
        >
          {projects.map((p) => (
            <ProjectCard
              key={p.id}
              project={p}
              onPress={() => router.push(`/project/${p.id}` as any)}
            />
          ))}
        </ScrollView>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: C.bg },
  center:    { flex: 1, alignItems: 'center', justifyContent: 'center' },

  header: {
    flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between',
    paddingHorizontal: 20, paddingVertical: 14,
  },
  title: { fontSize: S.xxl, fontWeight: '900', color: C.textPrimary, letterSpacing: -0.5 },
  addBtn: {
    width: 40, height: 40, borderRadius: 20,
    backgroundColor: C.gold, alignItems: 'center', justifyContent: 'center',
  },

  list: { paddingHorizontal: 20, paddingBottom: 120, gap: 12 },

  projectCard: {
    flexDirection: 'row', alignItems: 'center',
    padding: 16, gap: 14,
  },
  emojiBox: {
    width: 52, height: 52, borderRadius: R.md,
    backgroundColor: C.bgSoft, alignItems: 'center', justifyContent: 'center',
  },
  emoji:       { fontSize: 26 },
  projectName: { fontSize: S.md, fontWeight: '700', color: C.textPrimary },
  projectDesc: { fontSize: S.xs, color: C.textTertiary, marginTop: 2 },

  memberRow: { flexDirection: 'row', alignItems: 'center', marginTop: 6, gap: 4 },
  memberDot: {
    width: 22, height: 22, borderRadius: 11,
    backgroundColor: C.bgAccent, alignItems: 'center', justifyContent: 'center',
    borderWidth: 1.5, borderColor: C.bgCard,
  },
  memberDotText: { fontSize: 9, fontWeight: '700', color: C.gold },
  memberMore:    { fontSize: S.xs, color: C.textTertiary, marginLeft: 2 },

  empty: { flex: 1, alignItems: 'center', justifyContent: 'center', paddingHorizontal: 40, gap: 12 },
  emptyEmoji: { fontSize: 56, marginBottom: 4 },
  emptyTitle: { fontSize: S.xl, fontWeight: '800', color: C.textPrimary },
  emptyText:  { fontSize: S.sm, color: C.textSecondary, textAlign: 'center', lineHeight: 20 },
  createBtn:  { marginTop: 8, backgroundColor: C.gold, paddingHorizontal: 28, paddingVertical: 14, borderRadius: R.md },
  createBtnText: { color: '#fff', fontWeight: '700', fontSize: S.md },
});
