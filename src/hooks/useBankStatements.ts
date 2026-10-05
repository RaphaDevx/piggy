import { useState, useCallback } from 'react';
import { useFocusEffect } from 'expo-router';
import { supabase } from '../lib/supabase';
import { ensureAiConsent } from '../lib/aiConsent';
import type { AccountType, BankStatement, BankTransaction } from '../types/bank';
import type { Receipt } from '../types/receipt';

export interface StatementSummary extends BankStatement {
  transactionCount: number;
  matchedCount: number;
}

export interface UnmatchedReceipt extends Receipt {}

async function fetchStatements(): Promise<StatementSummary[]> {
  const { data: statements } = await supabase
    .from('bank_statements')
    .select('*')
    .order('created_at', { ascending: false });

  if (!statements || statements.length === 0) return [];

  const { data: txRows } = await supabase
    .from('bank_transactions')
    .select('statement_id, match_status')
    .in('statement_id', statements.map((s) => s.id));

  return statements.map((s) => {
    const txs = (txRows ?? []).filter((t) => t.statement_id === s.id);
    return {
      ...s,
      transactionCount: txs.length,
      matchedCount: txs.filter((t) => t.match_status === 'matched').length,
    };
  });
}

async function fetchUnmatched(): Promise<{ transactions: BankTransaction[]; receipts: UnmatchedReceipt[] }> {
  const { data: transactions } = await supabase
    .from('bank_transactions')
    .select('*')
    .eq('match_status', 'unmatched')
    .lt('amount', 0)
    .order('booking_date', { ascending: false });

  const { data: receipts } = await supabase
    .from('receipts')
    .select('*')
    .order('receipt_date', { ascending: false });

  const { data: matches } = await supabase
    .from('receipt_matches')
    .select('receipt_id');

  const matchedReceiptIds = new Set((matches ?? []).map((m) => m.receipt_id));
  const unmatchedReceipts = (receipts ?? []).filter((r) => !matchedReceiptIds.has(r.id));

  return { transactions: transactions ?? [], receipts: unmatchedReceipts };
}

export function useBankStatements() {
  const [statements, setStatements] = useState<StatementSummary[]>([]);
  const [unmatchedTransactions, setUnmatchedTransactions] = useState<BankTransaction[]>([]);
  const [unmatchedReceipts, setUnmatchedReceipts] = useState<UnmatchedReceipt[]>([]);
  const [loading, setLoading] = useState(true);
  const [uploading, setUploading] = useState(false);
  const [uploadError, setUploadError] = useState<string | null>(null);

  const load = useCallback(async () => {
    setLoading(true);
    const [stmts, unmatched] = await Promise.all([fetchStatements(), fetchUnmatched()]);
    setStatements(stmts);
    setUnmatchedTransactions(unmatched.transactions);
    setUnmatchedReceipts(unmatched.receipts);
    setLoading(false);
  }, []);

  useFocusEffect(useCallback(() => { load(); }, [load]));

  const uploadStatement = useCallback(async (
    uri: string,
    mimeType: string,
    fileName: string,
    accountLabel: string,
    accountType: AccountType
  ) => {
    setUploading(true);
    setUploadError(null);
    try {
      if (!await ensureAiConsent()) {
        throw new Error('Kontoauszug-Analyse braucht die KI-Einwilligung (Profil → Verarbeitung).');
      }

      const { data: userData } = await supabase.auth.getUser();
      const userId = userData.user?.id;
      if (!userId) throw new Error('Nicht eingeloggt');

      // 1. Datei in den privaten Bucket laden (Ordner = eigene uid)
      const resp = await fetch(uri);
      const blob = await resp.blob();
      const filePath = `${userId}/${Date.now()}_${fileName}`;
      const { error: uploadErr } = await supabase.storage
        .from('bank-statements')
        .upload(filePath, blob, { contentType: mimeType });
      if (uploadErr) throw new Error(`Datei-Upload fehlgeschlagen: ${uploadErr.message}`);

      // 2. Auszug in die Warteschlange stellen
      const { data: statementRow, error: statementError } = await supabase
        .from('bank_statements')
        .insert({
          user_id:       userId,
          account_label: accountLabel || (accountType === 'credit' ? 'Kreditkarte' : 'Konto'),
          account_type:  accountType,
          file_name:     fileName,
          file_path:     filePath,
          mime_type:     mimeType,
          stage:         'queued',
          status:        'processing',
        })
        .select('id')
        .single();
      if (statementError || !statementRow) {
        throw new Error(statementError?.message ?? 'Speichern fehlgeschlagen');
      }

      // 3. Verarbeitung sofort anstossen (sonst spätestens nach 1 Min. per Cron)
      try {
        await supabase.rpc('kick_statement_worker');
      } catch {
        // nicht kritisch
      }

      await load();
      return { statementId: statementRow.id as string };
    } catch (err) {
      const message = (err as Error)?.message ?? 'Upload fehlgeschlagen';
      setUploadError(message);
      throw err;
    } finally {
      setUploading(false);
    }
  }, [load]);

  const retryStatement = useCallback(async (statementId: string) => {
    const { error } = await supabase.rpc('retry_statement', { p_statement_id: statementId });
    if (error) throw new Error(error.message);
  }, []);

  const rematchAll = useCallback(async () => {
    const { error } = await supabase.rpc('enqueue_rematch');
    if (error) throw new Error(error.message);
  }, []);

  const manualMatch = useCallback(async (transactionId: string, receiptId: string) => {
    const { data: userData } = await supabase.auth.getUser();
    const userId = userData.user?.id;
    if (!userId) return;

    await supabase.from('receipt_matches').insert({
      user_id: userId, transaction_id: transactionId, receipt_id: receiptId, score: 1, match_type: 'manual',
    });
    await supabase.from('bank_transactions').update({ match_status: 'matched' }).eq('id', transactionId);
    await load();
  }, [load]);

  const removeMatch = useCallback(async (transactionId: string) => {
    await supabase.from('receipt_matches').delete().eq('transaction_id', transactionId);
    await supabase.from('bank_transactions').update({ match_status: 'unmatched' }).eq('id', transactionId);
    await load();
  }, [load]);

  const ignoreTransaction = useCallback(async (transactionId: string) => {
    await supabase.from('bank_transactions').update({ match_status: 'ignored' }).eq('id', transactionId);
    await load();
  }, [load]);

  return {
    statements,
    unmatchedTransactions,
    unmatchedReceipts,
    loading,
    uploading,
    uploadError,
    uploadStatement,
    retryStatement,
    rematchAll,
    manualMatch,
    removeMatch,
    ignoreTransaction,
    refresh: load,
  };
}
