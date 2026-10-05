/**
 * StatementProgress — Fortschritt der Kontoauszug-Verarbeitung (Hintergrund-Worker).
 *
 * Zeigt Auszüge mit stage ≠ done sowie die in den letzten 24 h fertig gewordenen.
 * Aktualisiert sich live via Supabase Realtime (bank_statements).
 */
import { useCallback, useEffect, useState } from 'react';
import { View, Text, TouchableOpacity, StyleSheet, ActivityIndicator } from 'react-native';
import { supabase } from '../lib/supabase';
import { C, R, S, card } from '../constants/design';
import type { BankStatement } from '../types/bank';

const DAY_MS = 86_400_000;

type ProgressStatement = Pick<
  BankStatement,
  'id' | 'account_label' | 'file_name' | 'stage' | 'page_count' | 'pages_done' | 'pages_failed'
  | 'transactions_imported' | 'duplicates_skipped' | 'error_message' | 'created_at'
>;

const COLUMNS =
  'id, account_label, file_name, stage, page_count, pages_done, pages_failed, transactions_imported, duplicates_skipped, error_message, created_at';

function isVisible(s: ProgressStatement): boolean {
  if (s.stage !== 'done') return true;
  return Date.now() - new Date(s.created_at).getTime() < DAY_MS;
}

function stageText(s: ProgressStatement): string {
  switch (s.stage) {
    case 'queued':      return 'In der Warteschlange';
    case 'extracting':  return 'Seiten werden eingelesen';
    case 'reconciling': return 'Buchungen werden verrechnet';
    case 'done': {
      const n = s.transactions_imported ?? 0;
      const d = s.duplicates_skipped ?? 0;
      return `Fertig: ${n} Buchung${n !== 1 ? 'en' : ''} importiert, ${d} Duplikat${d !== 1 ? 'e' : ''} übersprungen`;
    }
    case 'error':       return s.error_message || 'Verarbeitung fehlgeschlagen';
    default:            return 'Wird verarbeitet';
  }
}

interface Props {
  onRetry: (statementId: string) => Promise<void>;
}

export default function StatementProgress({ onRetry }: Props) {
  const [items, setItems] = useState<ProgressStatement[]>([]);
  const [userId, setUserId] = useState<string | null>(null);
  const [retrying, setRetrying] = useState<string | null>(null);

  useEffect(() => {
    supabase.auth.getUser().then(({ data }) => setUserId(data.user?.id ?? null));
  }, []);

  useEffect(() => {
    if (!userId) return;
    let active = true;

    supabase
      .from('bank_statements')
      .select(COLUMNS)
      .eq('user_id', userId)
      .order('created_at', { ascending: false })
      .limit(20)
      .then(({ data }) => {
        if (active && data) setItems((data as ProgressStatement[]).filter(isVisible));
      });

    const channel = supabase
      .channel(`bank_statements_progress_${userId}`)
      .on(
        'postgres_changes',
        { event: '*', schema: 'public', table: 'bank_statements', filter: `user_id=eq.${userId}` },
        (payload) => {
          if (payload.eventType === 'DELETE') {
            const oldId = (payload.old as { id?: string }).id;
            if (oldId) setItems((prev) => prev.filter((i) => i.id !== oldId));
            return;
          }
          const row = payload.new as ProgressStatement;
          if (!row?.id) return;
          setItems((prev) => {
            const idx = prev.findIndex((i) => i.id === row.id);
            if (idx >= 0) {
              const next = [...prev];
              next[idx] = { ...next[idx], ...row };
              return next;
            }
            return isVisible(row) ? [row, ...prev] : prev;
          });
        },
      )
      .subscribe();

    return () => {
      active = false;
      supabase.removeChannel(channel);
    };
  }, [userId]);

  const retry = useCallback(async (id: string) => {
    setRetrying(id);
    try {
      await onRetry(id);
    } catch {
      // Status kommt über Realtime; bei Fehlschlag bleibt der Button sichtbar
    } finally {
      setRetrying(null);
    }
  }, [onRetry]);

  if (items.length === 0) return null;

  return (
    <View style={styles.wrap}>
      {items.map((s) => {
        const total = s.page_count ?? 0;
        const done = s.pages_done ?? 0;
        const progress = total > 0 ? Math.min(done / total, 1) : s.stage === 'done' ? 1 : 0;
        const failed = (s.pages_failed ?? 0) > 0;
        const processing = s.stage === 'extracting' && total > 0;
        return (
          <View key={s.id} style={[card, styles.card]}>
            <Text style={styles.title} numberOfLines={1}>{s.account_label || s.file_name || 'Kontoauszug'}</Text>
            {processing && (
              <Text style={styles.label}>Verarbeitung: Seite {Math.min(done + 1, total)} von {total}</Text>
            )}
            {s.stage !== 'done' && s.stage !== 'error' && (
              <View style={styles.track}>
                <View style={[styles.fill, { width: `${Math.round(progress * 100)}%` }]} />
              </View>
            )}
            <Text style={[
              styles.status,
              s.stage === 'done' && { color: C.success, fontWeight: '700' },
              s.stage === 'error' && { color: C.error },
            ]}>
              {stageText(s)}
            </Text>
            {failed && s.stage !== 'error' && (
              <Text style={styles.status}>{s.pages_failed} Seite{s.pages_failed !== 1 ? 'n' : ''} fehlgeschlagen</Text>
            )}
            {(s.stage === 'error' || (failed && s.stage === 'done')) && (
              <TouchableOpacity style={styles.retryBtn} onPress={() => retry(s.id)} disabled={retrying === s.id}>
                {retrying === s.id
                  ? <ActivityIndicator color="#fff" />
                  : <Text style={styles.retryText}>Erneut versuchen</Text>}
              </TouchableOpacity>
            )}
          </View>
        );
      })}
    </View>
  );
}

const styles = StyleSheet.create({
  wrap:   { gap: 10 },
  card:   { padding: 16, gap: 8 },
  title:  { fontSize: S.sm, fontWeight: '700', color: C.textPrimary },
  label:  { fontSize: S.sm, fontWeight: '600', color: C.textPrimary },
  status: { fontSize: S.xs, color: C.textSecondary },
  track:  { height: 6, backgroundColor: C.bgSoft, borderRadius: 3, overflow: 'hidden' },
  fill:   { height: 6, backgroundColor: C.gold, borderRadius: 3 },
  retryBtn: {
    alignItems: 'center', justifyContent: 'center',
    backgroundColor: C.gold, borderRadius: R.md, paddingVertical: 10,
  },
  retryText: { color: '#fff', fontSize: S.sm, fontWeight: '700' },
});
