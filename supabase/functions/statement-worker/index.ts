// statement-worker — Kontoauszug-Pipeline im Hintergrund (ADR 0001).
//
// Aufgerufen von pg_cron (jede Minute) und per "Kick" nach Upload/Quittung,
// authentifiziert über x-worker-secret (Vault). Pro Aufruf:
//   1. neue Auszüge in Seiten zerlegen          (bank_statements.stage queued → extracting)
//   2. gedrosselt Seiten einlesen (Stufe 1)       (statement_pages, Retry mit Backoff)
//   3. fertige Auszüge verrechnen (Stufe 2)       (Duplikate, bank_transactions, Abgleich)
//   4. Abgleich-Aufträge (neue Quittungen, "Neu abgleichen")
// Alle Claims laufen über FOR UPDATE SKIP LOCKED → parallele Aufrufe sind sicher.

import { createClient, SupabaseClient } from 'npm:@supabase/supabase-js@2';
import { PDFDocument } from 'npm:pdf-lib@1.17.1';
import { getDocumentProxy } from 'npm:unpdf@1.8.1';
import { encodeBase64 } from 'jsr:@std/encoding@1/base64';
import { extractCamt, extractPage, fixSignsByBalance, isCamt, type ExtractorSettings, type ParsedLine } from '../_shared/extractors/index.ts';
import { dedupeTransactions, normalizeTransactionCategory } from '../_shared/finance-core/finance.ts';
import { runMatching, type AccountType } from '../_shared/finance-core/matching.ts';
import { findSuggestions } from '../_shared/finance-core/discrepancies.ts';
import { classifyItemsLlm, type ItemToClassify } from '../_shared/classify/items.ts';

const SUPABASE_URL = Deno.env.get('SUPABASE_URL')!;
const SERVICE_KEY  = Deno.env.get('SERVICE_ROLE_KEY') ?? Deno.env.get('SUPABASE_SERVICE_ROLE_KEY')!;
const PAGE_SIZE = 1000;
const INSERT_CHUNK = 500;
const BACKOFF_BASE_MINUTES = 2;
const RAW_OUTPUT_MAX = 20_000;

type Admin = SupabaseClient;

interface Settings extends ExtractorSettings {
  classifyBatch: number;
  classifyBatchesPerTick: number;
  pagesPerTick: number;
  concurrency: number;
  maxAttempts: number;
}

interface StatementRow {
  id: string;
  user_id: string;
  account_type: AccountType;
  file_path: string | null;
  mime_type: string | null;
  page_count: number | null;
  created_at: string;
}

interface PageRow {
  id: string;
  statement_id: string;
  user_id: string;
  page_no: number;
  attempts: number;
}

const json = (body: unknown, status = 200) =>
  new Response(JSON.stringify(body), { status, headers: { 'Content-Type': 'application/json' } });

async function loadSettings(admin: Admin): Promise<Settings> {
  const { data } = await admin.from('app_settings').select('key, value').like('key', 'statement_%');
  const cfg = Object.fromEntries((data ?? []).map((r: { key: string; value: string }) => [r.key, r.value]));
  const int = (k: string, d: number) => Math.max(1, parseInt(cfg[k] ?? '', 10) || d);
  return {
    mode:         cfg.statement_extractor ?? 'auto',
    textModel:    cfg.statement_text_model ?? 'claude-haiku-4-5-20251001',
    pdfModel:     cfg.statement_pdf_model ?? 'claude-sonnet-4-6',
    pagesPerTick: int('statement_pages_per_tick', 6),
    classifyBatch: int('statement_classify_batch', 120),
    classifyBatchesPerTick: int('statement_classify_batches_per_tick', 2),
    concurrency:  int('statement_page_concurrency', 3),
    maxAttempts:  int('statement_max_attempts', 3),
  };
}

// ── Dateien ──────────────────────────────────────────────────────────────────

const fileCache = new Map<string, Uint8Array>();

async function loadFile(admin: Admin, path: string): Promise<Uint8Array> {
  const cached = fileCache.get(path);
  if (cached) return cached;
  const { data, error } = await admin.storage.from('bank-statements').download(path);
  if (error || !data) throw new Error(`Datei nicht ladbar: ${error?.message ?? path}`);
  const bytes = new Uint8Array(await data.arrayBuffer());
  fileCache.set(path, bytes);
  return bytes;
}

const isPdf = (s: StatementRow) => (s.mime_type ?? '').includes('pdf') || (s.file_path ?? '').toLowerCase().endsWith('.pdf');

// ── 1. Zerlegen ──────────────────────────────────────────────────────────────

async function splitStatements(admin: Admin): Promise<number> {
  const { data } = await admin.rpc('claim_queued_statements', { p_limit: 2 });
  const statements = (data ?? []) as StatementRow[];
  for (const s of statements) {
    try {
      if (!s.file_path) throw new Error('Kein Dateipfad');
      let pageCount = 1;
      if (isPdf(s)) {
        const pdf = await PDFDocument.load(await loadFile(admin, s.file_path), { ignoreEncryption: true });
        pageCount = pdf.getPageCount();
      }
      const pages = Array.from({ length: pageCount }, (_, i) => ({
        statement_id: s.id, user_id: s.user_id, page_no: i + 1,
      }));
      const { error } = await admin.from('statement_pages').upsert(pages, { onConflict: 'statement_id,page_no', ignoreDuplicates: true });
      if (error) throw error;
      await admin.from('bank_statements')
        .update({ stage: 'extracting', status: 'processing', page_count: pageCount, locked_at: null })
        .eq('id', s.id);
    } catch (e) {
      await admin.from('bank_statements')
        .update({ stage: 'error', status: 'error', error_message: (e as Error).message, locked_at: null })
        .eq('id', s.id);
    }
  }
  return statements.length;
}

// ── 2. Einlesen (Stufe 1) ────────────────────────────────────────────────────

async function readPage(admin: Admin, s: StatementRow, pageNo: number, settings: Settings) {
  const bytes = await loadFile(admin, s.file_path!);
  const yearHint = new Date(s.created_at).getFullYear();

  if (!isPdf(s)) {
    const text = new TextDecoder().decode(bytes);
    if (isCamt(text)) return extractCamt(text);
    // CSV & Co.: Text an das Text-Modell (ein Bank-spezifischer Parser kann hier später einhängen)
    return extractPage({ pageNo: 1, pageCount: 1, text, yearHint }, { ...settings, mode: 'text-llm' });
  }

  const proxy = await getDocumentProxy(bytes);
  const content = await (await proxy.getPage(pageNo)).getTextContent();
  const text = content.items.map((i) => ('str' in i ? i.str + (i.hasEOL ? '\n' : ' ') : '')).join('');

  let pdfBase64: string | undefined;
  const needsPdf = settings.mode === 'pdf-llm' || (settings.mode === 'auto');
  if (needsPdf) {
    const src = await PDFDocument.load(bytes, { ignoreEncryption: true });
    const single = await PDFDocument.create();
    const [copied] = await single.copyPages(src, [pageNo - 1]);
    single.addPage(copied);
    pdfBase64 = encodeBase64(await single.save());
  }
  return extractPage({ pageNo, pageCount: s.page_count ?? 1, text, pdfBase64, yearHint }, settings);
}

async function processPage(admin: Admin, page: PageRow, settings: Settings) {
  const started = Date.now();
  const { data: s } = await admin.from('bank_statements')
    .select('id, user_id, account_type, file_path, mime_type, page_count, created_at')
    .eq('id', page.statement_id).single();
  try {
    if (!s) throw new Error('Auszug fehlt');
    const result = await readPage(admin, s as StatementRow, page.page_no, settings);
    await admin.from('statement_pages').update({
      status: 'done', lines: result.lines, extractor: result.extractor, model: result.model,
      tokens_in: result.tokensIn, tokens_out: result.tokensOut, ms: Date.now() - started,
      raw_output: result.rawOutput?.slice(0, RAW_OUTPUT_MAX) ?? null,
      error: null, processed_at: new Date().toISOString(), locked_at: null,
    }).eq('id', page.id);
  } catch (e) {
    const failed = page.attempts >= settings.maxAttempts;
    const backoffMs = BACKOFF_BASE_MINUTES * 60_000 * 2 ** (page.attempts - 1);
    await admin.from('statement_pages').update({
      status: failed ? 'error' : 'pending',
      next_attempt_at: new Date(Date.now() + backoffMs).toISOString(),
      error: (e as Error).message.slice(0, 500), ms: Date.now() - started, locked_at: null,
    }).eq('id', page.id);
  }
  await refreshProgress(admin, page.statement_id);
}

async function refreshProgress(admin: Admin, statementId: string) {
  const [{ count: done }, { count: failed }] = await Promise.all([
    admin.from('statement_pages').select('id', { count: 'exact', head: true }).eq('statement_id', statementId).eq('status', 'done'),
    admin.from('statement_pages').select('id', { count: 'exact', head: true }).eq('statement_id', statementId).eq('status', 'error'),
  ]);
  await admin.from('bank_statements').update({ pages_done: done ?? 0, pages_failed: failed ?? 0 }).eq('id', statementId);
}

async function processPages(admin: Admin, settings: Settings): Promise<number> {
  const { data } = await admin.rpc('claim_statement_pages', { p_limit: settings.pagesPerTick });
  const queue = [...((data ?? []) as PageRow[])];
  const workers = Array.from({ length: Math.min(settings.concurrency, queue.length) }, async () => {
    for (let page = queue.shift(); page; page = queue.shift()) await processPage(admin, page, settings);
  });
  await Promise.all(workers);
  return (data ?? []).length;
}

// ── 3. Verrechnen (Stufe 2) ──────────────────────────────────────────────────

async function fetchAll<T>(build: (from: number, to: number) => PromiseLike<{ data: unknown[] | null }>): Promise<T[]> {
  const rows: T[] = [];
  for (let from = 0; ; from += PAGE_SIZE) {
    const { data } = await build(from, from + PAGE_SIZE - 1);
    rows.push(...((data ?? []) as T[]));
    if (!data || data.length < PAGE_SIZE) return rows;
  }
}

async function reconcileStatements(admin: Admin): Promise<string[]> {
  const { data } = await admin.rpc('claim_statements_ready', { p_limit: 2 });
  const statements = (data ?? []) as StatementRow[];
  const users: string[] = [];
  for (const s of statements) {
    try {
      const pages = await fetchAll<{ lines: ParsedLine[] | null; status: string }>((f, t) =>
        admin.from('statement_pages').select('lines, status').eq('statement_id', s.id).order('page_no').range(f, t));
      // Seitenübergreifend in Auszugs-Reihenfolge, damit die Saldo-Kette durchläuft
      const { lines } = fixSignsByBalance(pages.flatMap((p) => p.lines ?? []));
      const failedPages = pages.filter((p) => p.status === 'error').length;

      let inserted = 0, skipped = 0;
      if (lines.length > 0) {
        const dates = lines.map((l) => l.booking_date).sort();
        const existing = await fetchAll<{ booking_date: string; amount: number; description: string }>((f, t) =>
          admin.from('bank_transactions').select('booking_date, amount, description')
            .eq('user_id', s.user_id).gte('booking_date', dates[0]).lte('booking_date', dates[dates.length - 1])
            .order('id').range(f, t));
        const fresh = dedupeTransactions(lines, existing);
        skipped = lines.length - fresh.length;
        for (let i = 0; i < fresh.length; i += INSERT_CHUNK) {
          const { error } = await admin.from('bank_transactions').insert(fresh.slice(i, i + INSERT_CHUNK).map((l) => ({
            statement_id: s.id, user_id: s.user_id, booking_date: l.booking_date, amount: l.amount,
            currency: l.currency, description: l.description,
            category: normalizeTransactionCategory(l.category, l.description, l.amount),
          })));
          if (error) throw error;
        }
        inserted = fresh.length;
        await admin.from('bank_statements').update({ period_start: dates[0], period_end: dates[dates.length - 1] })
          .eq('id', s.id).is('period_start', null);
      }

      await admin.from('bank_statements').update({
        stage: 'done', status: 'done', locked_at: null, finished_at: new Date().toISOString(),
        transactions_imported: inserted, duplicates_skipped: skipped,
        error_message: failedPages > 0 ? `${failedPages} Seite(n) konnten nicht gelesen werden` : null,
      }).eq('id', s.id);
      users.push(s.user_id);
    } catch (e) {
      await admin.from('bank_statements')
        .update({ stage: 'error', status: 'error', error_message: (e as Error).message, locked_at: null })
        .eq('id', s.id);
    }
  }
  return users;
}

// ── 4. Abgleich (fortlaufend) ────────────────────────────────────────────────

async function matchUser(admin: Admin, userId: string) {
  const [txRows, receipts, matches, suggestions] = await Promise.all([
    fetchAll<{ id: string; booking_date: string; amount: number; currency: string; description: string; match_status: string; statement: { account_type: AccountType } | Array<{ account_type: AccountType }> | null }>((f, t) =>
      admin.from('bank_transactions')
        .select('id, booking_date, amount, currency, description, match_status, statement:bank_statements(account_type)')
        .eq('user_id', userId).eq('match_status', 'unmatched').lt('amount', 0).order('id').range(f, t)),
    fetchAll<{ id: string; receipt_date: string | null; total_amount: number | null; currency: string; store_name: string; store_category: string | null; receipt_items: Array<{ total_price: number | null; tags: string[] | null }> }>((f, t) =>
      admin.from('receipts')
        .select('id, receipt_date, total_amount, currency, store_name, store_category, receipt_items(total_price, tags)')
        .eq('user_id', userId).order('id').range(f, t)),
    fetchAll<{ receipt_id: string }>((f, t) =>
      admin.from('receipt_matches').select('receipt_id').eq('user_id', userId).order('id').range(f, t)),
    fetchAll<{ transaction_id: string; receipt_id: string }>((f, t) =>
      admin.from('match_suggestions').select('transaction_id, receipt_id').eq('user_id', userId).order('id').range(f, t)),
  ]);

  const matched = new Set(matches.map((m) => m.receipt_id));
  let openReceipts = receipts.filter((r) => !matched.has(r.id)).map((r) => ({ ...r, items: r.receipt_items }));
  const exclude = new Set(suggestions.map((s) => `${s.transaction_id}|${s.receipt_id}`));
  const linkedTx: string[] = [], linkedReceipts: string[] = [];

  for (const accountType of ['debit', 'credit'] as AccountType[]) {
    const txs = txRows.filter((t) => {
      const st = Array.isArray(t.statement) ? t.statement[0] : t.statement;
      return (st?.account_type ?? 'debit') === accountType;
    });
    if (txs.length === 0 || openReceipts.length === 0) continue;

    const results = runMatching(txs, openReceipts, accountType);
    if (results.length > 0) {
      await admin.from('receipt_matches').insert(results.map((m) => ({
        user_id: userId, transaction_id: m.transactionId, receipt_id: m.receiptId, score: m.score, match_type: 'auto',
      })));
      await admin.from('bank_transactions').update({ match_status: 'matched' }).in('id', results.map((m) => m.transactionId));
      linkedTx.push(...results.map((m) => m.transactionId));
      linkedReceipts.push(...results.map((m) => m.receiptId));
    }

    const usedTx = new Set(results.map((m) => m.transactionId));
    const usedReceipts = new Set(results.map((m) => m.receiptId));
    openReceipts = openReceipts.filter((r) => !usedReceipts.has(r.id));
    const candidates = findSuggestions(txs.filter((t) => !usedTx.has(t.id)), openReceipts, accountType, exclude);
    if (candidates.length > 0) {
      await admin.from('match_suggestions').upsert(candidates.map((c) => ({
        user_id: userId, transaction_id: c.transactionId, receipt_id: c.receiptId,
        kind: c.kind, diff_amount: c.diffAmount, score: c.score,
      })), { onConflict: 'transaction_id,receipt_id', ignoreDuplicates: true });
    }
  }

  // Vorschläge, deren Buchung oder Quittung inzwischen exakt verknüpft ist, erledigen sich
  if (linkedTx.length > 0) {
    await admin.from('match_suggestions').update({ status: 'dismissed', resolved_at: new Date().toISOString() })
      .eq('status', 'open').in('transaction_id', linkedTx);
    await admin.from('match_suggestions').update({ status: 'dismissed', resolved_at: new Date().toISOString() })
      .eq('status', 'open').in('receipt_id', linkedReceipts);
  }
  return linkedTx.length;
}

// ── 5. Artikel einordnen (Unterkategorien, Hintergrund) ─────────────────────

/** Nur, wenn im Takt noch Zeit bleibt — Einlesen von Auszügen hat Vorrang. */
const CLASSIFY_TIME_BUDGET_MS = 60_000;

async function classifyItems(admin: Admin, settings: Settings, startedAt: number): Promise<number> {
  let done = 0;
  for (let b = 0; b < settings.classifyBatchesPerTick; b++) {
    if (Date.now() - startedAt > CLASSIFY_TIME_BUDGET_MS) break;
    const { data } = await admin.rpc('claim_items_to_classify', { p_limit: settings.classifyBatch });
    const items = (data ?? []) as ItemToClassify[];
    if (items.length === 0) break;
    try {
      const { results } = await classifyItemsLlm(items, settings.textModel);
      // Gleiche Ergebnisse gebündelt schreiben (wenige Requests statt einer pro Artikel)
      const groups = new Map<string, { category: string; subcategory: string | null; ids: string[] }>();
      for (const r of results) {
        const k = `${r.category}|${r.subcategory ?? ''}`;
        const g = groups.get(k) ?? { category: r.category, subcategory: r.subcategory, ids: [] };
        g.ids.push(r.id);
        groups.set(k, g);
      }
      const now = new Date().toISOString();
      for (const g of groups.values()) {
        await admin.from('receipt_items')
          .update({ tags: [g.category], subcategory: g.subcategory, classified_at: now, classify_locked_at: null })
          .in('id', g.ids);
      }
      done += items.length;
    } catch (e) {
      // Lock läuft nach 10 Min. ab → nächster Takt versucht es erneut
      console.error('classify failed', (e as Error).message);
      break;
    }
  }
  return done;
}

// ── Handler ──────────────────────────────────────────────────────────────────

Deno.serve(async (req) => {
  const admin = createClient(SUPABASE_URL, SERVICE_KEY, { auth: { persistSession: false } });
  const secret = req.headers.get('x-worker-secret') ?? '';
  const { data: ok } = await admin.rpc('worker_secret_matches', { p_secret: secret });
  if (!secret || ok !== true) return json({ error: 'unauthorized' }, 401);

  const started = Date.now();
  const settings = await loadSettings(admin);
  const split = await splitStatements(admin);
  const pages = await processPages(admin, settings);
  const reconciledUsers = await reconcileStatements(admin);

  const { data: rematch } = await admin.rpc('claim_rematch_users', { p_limit: 20 });
  const users = new Set([...reconciledUsers, ...((rematch ?? []) as { user_id: string }[]).map((r) => r.user_id)]);
  let linked = 0;
  for (const userId of users) linked += await matchUser(admin, userId);
  const classified = await classifyItems(admin, settings, started);

  return json({ split, pages, reconciled: reconciledUsers.length, rematched: users.size, linked, classified, ms: Date.now() - started });
});
