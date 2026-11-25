#!/usr/bin/env node
// Kopiert Supabase-Storage-Objekte nach Cloudflare R2 (ADR 0005). Löscht NICHTS in Supabase.
//
//   node scripts/migrate-storage-to-r2.mjs [--dry-run] [--buckets a,b] [--report pfad.json]
//
// Umgebungsvariablen (nie im Code, nie im Bericht):
//   Quelle:  SOURCE_SUPABASE_URL, SOURCE_SUPABASE_SERVICE_KEY
//   Ziel:    R2_ACCOUNT_ID, R2_ACCESS_KEY_ID, R2_SECRET_ACCESS_KEY, R2_BUCKET, R2_JURISDICTION (eu)
// R2-Schlüssel eines Objekts: `<bucket>/<pfad>` (gleicher Pfad wie in Supabase).
// Idempotent: Objekte, die in R2 mit gleicher Grösse (und gleichem MD5, wenn bekannt) liegen, werden übersprungen.
// Danach wird jedes Objekt verglichen (Anzahl, Grösse, MD5/ETag); Abweichung -> Exit-Code 2.
// Braucht Node >= 22.18 (importiert die geteilten .ts-Module per Type-Stripping).

import { createHash } from 'node:crypto';
import { writeFileSync } from 'node:fs';
import { pathToFileURL } from 'node:url';
import { USER_BUCKETS, r2Key } from '../supabase/functions/_shared/storage/paths.ts';
import { createR2, r2ConfigFromEnv } from '../supabase/functions/_shared/storage/r2.ts';

const md5 = (bytes) => createHash('md5').update(bytes).digest('hex');
const unquote = (s) => (s ?? '').replace(/^"|"$/g, '');
/** Mehrteilige ETags (mit '-') sind kein MD5 und werden nicht verglichen. */
const usableMd5 = (etag) => (/^[0-9a-f]{32}$/i.test(unquote(etag)) ? unquote(etag).toLowerCase() : null);

export function parseArgs(argv) {
  const args = { dryRun: false, buckets: [...USER_BUCKETS], report: null, clientsModule: null };
  for (let i = 0; i < argv.length; i++) {
    const a = argv[i];
    if (a === '--dry-run') args.dryRun = true;
    else if (a === '--buckets') args.buckets = String(argv[++i] ?? '').split(',').filter(Boolean);
    else if (a === '--report') args.report = argv[++i];
    else if (a === '--clients-module') args.clientsModule = argv[++i]; // nur für Tests (Fake-Clients)
    else throw new Error(`Unbekannte Option: ${a}`);
  }
  const unknown = args.buckets.filter((b) => !USER_BUCKETS.includes(b));
  if (unknown.length) throw new Error(`Unbekannter Bucket: ${unknown.join(', ')}`);
  return args;
}

/** Supabase Storage über die REST-API (Service-Key aus der Umgebung). */
export function createSupabaseSource(url, serviceKey, fetchImpl = fetch) {
  const base = url.replace(/\/$/, '') + '/storage/v1';
  const headers = { Authorization: `Bearer ${serviceKey}`, apikey: serviceKey };
  const fail = (what, res) => new Error(`Supabase ${what} fehlgeschlagen (HTTP ${res.status})`);
  async function listFolder(bucket, prefix, out) {
    for (let offset = 0; ; offset += 1000) {
      const res = await fetchImpl(`${base}/object/list/${bucket}`, {
        method: 'POST', headers: { ...headers, 'Content-Type': 'application/json' },
        body: JSON.stringify({ prefix, limit: 1000, offset, sortBy: { column: 'name', order: 'asc' } }),
      });
      if (!res.ok) throw fail('list', res);
      const rows = await res.json();
      for (const row of rows) {
        const path = prefix ? `${prefix}/${row.name}` : row.name;
        if (row.id === null) await listFolder(bucket, path, out); // Ordner
        else out.push({ path, size: Number(row.metadata?.size ?? 0), etag: row.metadata?.eTag ?? null, contentType: row.metadata?.mimetype ?? null });
      }
      if (rows.length < 1000) return;
    }
  }
  return {
    async list(bucket) { const out = []; await listFolder(bucket, '', out); return out; },
    async download(bucket, path) {
      const res = await fetchImpl(`${base}/object/${bucket}/${path.split('/').map(encodeURIComponent).join('/')}`, { headers });
      if (!res.ok) throw fail('download', res);
      return new Uint8Array(await res.arrayBuffer());
    },
  };
}

export function createR2Target(config, fetchImpl = fetch) {
  const r2 = createR2(config, fetchImpl);
  return {
    head: (key) => r2.head(key),
    put: (key, bytes, contentType) => r2.put(key, bytes, contentType),
    list: (prefix) => r2.list(prefix),
  };
}

async function pool(items, limit, fn) {
  const queue = [...items];
  await Promise.all(Array.from({ length: Math.min(limit, queue.length) }, async () => {
    while (queue.length) await fn(queue.shift());
  }));
}

/**
 * Kernlogik, mit austauschbaren Clients.
 * source: { list(bucket) -> [{path,size,etag?,contentType?}], download(bucket,path) -> Uint8Array }
 * target: { head(key) -> {size,etag}|null, put(key,bytes,contentType), list(prefix) -> [{key,size,etag}] }
 */
export async function migrate({ source, target, buckets, dryRun, concurrency = 4, log = () => {} }) {
  const report = { startedAt: new Date().toISOString(), dryRun, buckets: {}, ok: true };
  for (const bucket of buckets) {
    const objects = await source.list(bucket);
    const stat = { sourceCount: objects.length, sourceBytes: 0, copied: 0, skipped: 0, wouldCopy: 0, failed: [], mismatches: [], missing: [], targetCount: null, targetExtra: 0 };
    stat.sourceBytes = objects.reduce((n, o) => n + o.size, 0);

    await pool(objects, concurrency, async (obj) => {
      const key = r2Key(bucket, obj.path);
      try {
        const existing = await target.head(key);
        const srcMd5 = usableMd5(obj.etag);
        const same = existing && existing.size === obj.size && (!srcMd5 || usableMd5(existing.etag) === srcMd5);
        if (same) { stat.skipped++; return; }
        if (dryRun) { stat.wouldCopy++; return; }
        const bytes = await source.download(bucket, obj.path);
        const sum = md5(bytes);
        if (bytes.byteLength !== obj.size && obj.size) throw new Error('Grösse der Quelle weicht von der Liste ab');
        if (srcMd5 && srcMd5 !== sum) throw new Error('MD5 der Quelle weicht vom Download ab');
        await target.put(key, bytes, obj.contentType ?? undefined);
        const after = await target.head(key);
        if (!after) stat.missing.push(obj.path);
        else if (after.size !== bytes.byteLength || (usableMd5(after.etag) && usableMd5(after.etag) !== sum)) stat.mismatches.push(obj.path);
        else stat.copied++;
      } catch (e) {
        stat.failed.push({ path: obj.path, error: e instanceof Error ? e.message : 'Fehler' });
      }
    });

    if (!dryRun) {
      // Abgleich: Anzahl im Ziel und Grösse/ETag je Quellobjekt
      const inTarget = await target.list(`${bucket}/`);
      stat.targetCount = inTarget.length;
      const byKey = new Map(inTarget.map((o) => [o.key, o]));
      stat.targetExtra = inTarget.length - objects.filter((o) => byKey.has(r2Key(bucket, o.path))).length;
      for (const obj of objects) {
        const t = byKey.get(r2Key(bucket, obj.path));
        const srcMd5 = usableMd5(obj.etag);
        if (!t) { if (!stat.missing.includes(obj.path)) stat.missing.push(obj.path); }
        else if (t.size !== obj.size || (srcMd5 && usableMd5(t.etag) && usableMd5(t.etag) !== srcMd5)) {
          if (!stat.mismatches.includes(obj.path)) stat.mismatches.push(obj.path);
        }
      }
    }
    if (stat.failed.length || stat.mismatches.length || stat.missing.length) report.ok = false;
    report.buckets[bucket] = stat;
    log(`${bucket}: ${stat.sourceCount} Quellobjekte, kopiert ${stat.copied}, übersprungen ${stat.skipped}, würde kopieren ${stat.wouldCopy}, Fehler ${stat.failed.length}`);
  }
  report.finishedAt = new Date().toISOString();
  return report;
}

async function main() {
  const args = parseArgs(process.argv.slice(2));
  let source, target;
  if (args.clientsModule) {
    ({ source, target } = await (await import(pathToFileURL(args.clientsModule).href)).createClients());
  } else {
    const need = (n) => { const v = process.env[n]; if (!v) throw new Error(`Umgebungsvariable fehlt: ${n}`); return v; };
    const config = r2ConfigFromEnv((n) => process.env[n]);
    if (!config) throw new Error('R2_ACCOUNT_ID, R2_ACCESS_KEY_ID, R2_SECRET_ACCESS_KEY und R2_BUCKET müssen gesetzt sein (R2_JURISDICTION nur eu oder fedramp)');
    source = createSupabaseSource(need('SOURCE_SUPABASE_URL'), need('SOURCE_SUPABASE_SERVICE_KEY'));
    target = createR2Target(config);
  }
  const report = await migrate({ source, target, buckets: args.buckets, dryRun: args.dryRun, log: (m) => console.log(m) });
  const json = JSON.stringify(report, null, 2);
  if (args.report) writeFileSync(args.report, json + '\n', { mode: 0o600 });
  else console.log(json);
  if (!report.ok) process.exitCode = 2;
}

if (import.meta.url === pathToFileURL(process.argv[1] ?? '').href) {
  main().catch((e) => { console.error(e instanceof Error ? e.message : 'Fehler'); process.exit(1); });
}
