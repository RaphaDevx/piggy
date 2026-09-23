/**
 * ScanQueueMonitor — Zeigt Fortschritt der scan_queue via Supabase Realtime.
 *
 * Verschwindet automatisch sobald alle Einträge done/failed sind.
 * Klick auf einen done-Eintrag navigiert zum Receipt-Detail.
 */

import { useEffect, useRef, useState, useCallback } from 'react';
import {
  View, Text, TouchableOpacity, StyleSheet, Animated,
} from 'react-native';
import { useRouter } from 'expo-router';
import { supabase } from '../lib/supabase';
import { C, R, S, card } from '../constants/design';

interface QueueEntry {
  id: string;
  status: 'pending' | 'processing' | 'done' | 'failed';
  result: Record<string, unknown> | null;
  error_msg: string | null;
  receipt_id?: string | null; // populated after save
}

export function ScanQueueMonitor() {
  const router   = useRouter();
  const [entries, setEntries] = useState<QueueEntry[]>([]);
  const [userId, setUserId]   = useState<string | null>(null);
  const [showDone, setShowDone] = useState(false);
  const fadeAnim = useRef(new Animated.Value(0)).current;

  // Load user id once
  useEffect(() => {
    supabase.auth.getUser().then(({ data }) => {
      setUserId(data.user?.id ?? null);
    });
  }, []);

  // Load initial active entries and subscribe to changes
  useEffect(() => {
    if (!userId) return;

    // Initial fetch: only pending/processing entries
    supabase
      .from('scan_queue')
      .select('id, status, result, error_msg')
      .eq('user_id', userId)
      .in('status', ['pending', 'processing'])
      .order('created_at', { ascending: true })
      .then(({ data }) => {
        if (data && data.length > 0) {
          setEntries(data as QueueEntry[]);
          Animated.timing(fadeAnim, { toValue: 1, duration: 300, useNativeDriver: true }).start();
        }
      });

    // Realtime subscription for this user's queue
    const channel = supabase
      .channel(`scan_queue_monitor_${userId}`)
      .on(
        'postgres_changes',
        {
          event:  '*',
          schema: 'public',
          table:  'scan_queue',
          filter: `user_id=eq.${userId}`,
        },
        (payload) => {
          const updated = payload.new as QueueEntry;
          if (!updated?.id) return;

          setEntries((prev) => {
            // Insert or update
            const idx = prev.findIndex((e) => e.id === updated.id);
            if (idx >= 0) {
              const next = [...prev];
              next[idx] = { ...next[idx], ...updated };
              return next;
            }
            if (updated.status === 'pending' || updated.status === 'processing') {
              return [...prev, updated];
            }
            return prev;
          });
        }
      )
      .subscribe();

    return () => {
      supabase.removeChannel(channel);
    };
  }, [userId, fadeAnim]);

  // Watch for all-done state
  useEffect(() => {
    if (entries.length === 0) return;

    const allSettled = entries.every((e) => e.status === 'done' || e.status === 'failed');
    if (allSettled) {
      setShowDone(true);
      // Auto-hide after 4 seconds
      const timer = setTimeout(() => {
        Animated.timing(fadeAnim, { toValue: 0, duration: 300, useNativeDriver: true }).start(() => {
          setEntries([]);
          setShowDone(false);
        });
      }, 4000);
      return () => clearTimeout(timer);
    }
  }, [entries, fadeAnim]);

  if (entries.length === 0) return null;

  const doneCount  = entries.filter((e) => e.status === 'done').length;
  const totalCount = entries.length;
  const progress   = totalCount > 0 ? doneCount / totalCount : 0;

  return (
    <Animated.View style={[styles.container, card, { opacity: fadeAnim }]}>
      {showDone ? (
        <View style={styles.doneRow}>
          <Text style={styles.doneEmoji}>✓</Text>
          <Text style={styles.doneText}>
            {doneCount === totalCount
              ? `${doneCount} Quittung${doneCount !== 1 ? 'en' : ''} verarbeitet`
              : `${doneCount} von ${totalCount} verarbeitet`}
          </Text>
        </View>
      ) : (
        <>
          <Text style={styles.label}>
            {doneCount} von {totalCount} Quittung{totalCount !== 1 ? 'en' : ''} verarbeitet
          </Text>
          {/* Progress bar */}
          <View style={styles.progressTrack}>
            <View style={[styles.progressFill, { width: `${Math.round(progress * 100)}%` }]} />
          </View>
          <Text style={styles.hint}>Piggy verarbeitet deine Quittungen im Hintergrund…</Text>
        </>
      )}

      {/* Done entries as tappable chips */}
      {entries
        .filter((e) => e.status === 'done' && e.result)
        .map((e) => {
          const storeName = (e.result as any)?.store_name ?? 'Quittung';
          return (
            <TouchableOpacity
              key={e.id}
              style={styles.entryChip}
              onPress={() => {
                // Navigate to queue entry detail (via receipt saved from queue)
                router.push({ pathname: '/scan-queue/[id]', params: { id: e.id } } as any);
              }}
            >
              <Text style={styles.entryChipText}>{storeName}</Text>
              <Text style={styles.entryChipArrow}>›</Text>
            </TouchableOpacity>
          );
        })}
    </Animated.View>
  );
}

const styles = StyleSheet.create({
  container: {
    marginHorizontal: 16,
    marginVertical:   8,
    padding:          16,
    gap:              10,
  },
  label: {
    fontSize:   S.sm,
    fontWeight: '700',
    color:      C.textPrimary,
  },
  hint: {
    fontSize: S.xs,
    color:    C.textTertiary,
  },
  progressTrack: {
    height:          6,
    backgroundColor: C.bgSoft,
    borderRadius:    3,
    overflow:        'hidden',
  },
  progressFill: {
    height:          6,
    backgroundColor: C.gold,
    borderRadius:    3,
  },
  doneRow: {
    flexDirection: 'row',
    alignItems:    'center',
    gap:           8,
  },
  doneEmoji: {
    fontSize:   18,
    color:      C.success,
  },
  doneText: {
    fontSize:   S.sm,
    fontWeight: '700',
    color:      C.success,
  },
  entryChip: {
    flexDirection:  'row',
    alignItems:     'center',
    justifyContent: 'space-between',
    backgroundColor: C.bgAccent,
    borderRadius:   R.sm,
    paddingVertical:   8,
    paddingHorizontal: 12,
  },
  entryChipText:  { fontSize: S.sm, fontWeight: '600', color: C.textPrimary, flex: 1 },
  entryChipArrow: { fontSize: S.lg, color: C.gold, marginLeft: 8 },
});
