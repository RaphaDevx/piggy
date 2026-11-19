/** @jest-environment node */
// Das Skript läuft als eigener Node-Prozess (ESM + Type-Stripping), die Clients sind Fakes.
import { execFileSync, spawnSync } from 'node:child_process';
import { mkdtempSync, readFileSync, writeFileSync } from 'node:fs';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { createHash } from 'node:crypto';

const SCRIPT = join(__dirname, '..', 'migrate-storage-to-r2.mjs');
const md5 = (s: string) => createHash('md5').update(s).digest('hex');

function setup(fake: string) {
  const dir = mkdtempSync(join(tmpdir(), 'migrate-test-'));
  const clients = join(dir, 'clients.mjs');
  writeFileSync(clients, fake);
  return { dir, clients, report: join(dir, 'report.json') };
}

// Fake-Quelle: 3 Objekte; Fake-Ziel: speichert in einem Map und zählt Schreibzugriffe in calls.json.
const fakeModule = (preexisting: Record<string, { size: number; etag: string }>) => `
import { writeFileSync } from 'node:fs';
import { createHash } from 'node:crypto';
const md5 = (s) => createHash('md5').update(s).digest('hex');
const data = { 'u1/a.jpg': 'AAAA', 'u1/b.jpg': 'BBBBBB', 'u2/c.pdf': 'CC' };
const calls = { download: 0, put: 0 };
const store = new Map(Object.entries(${JSON.stringify(preexisting)}));
export function createClients() {
  process.on('exit', () => writeFileSync(process.env.CALLS_FILE, JSON.stringify(calls)));
  return {
    source: {
      list: async (bucket) => bucket === 'receipt-images'
        ? Object.entries(data).map(([path, v]) => ({ path, size: v.length, etag: '"' + md5(v) + '"', contentType: 'image/jpeg' })) : [],
      download: async (bucket, path) => { calls.download++; return new TextEncoder().encode(data[path]); },
    },
    target: {
      head: async (key) => store.get(key) ?? null,
      put: async (key, bytes) => { calls.put++; store.set(key, { size: bytes.byteLength, etag: md5(Buffer.from(bytes).toString()) }); },
      list: async (prefix) => [...store].filter(([k]) => k.startsWith(prefix)).map(([key, v]) => ({ key, ...v })),
    },
  };
}`;

function run(args: string[], clients: string, callsFile: string) {
  const res = spawnSync(process.execPath, [SCRIPT, '--clients-module', clients, ...args], {
    encoding: 'utf8', env: { PATH: process.env.PATH, CALLS_FILE: callsFile } as unknown as NodeJS.ProcessEnv,
  });
  return res;
}

test('Node unterstützt Type-Stripping für das Skript', () => {
  expect(() => execFileSync(process.execPath, ['--check', SCRIPT])).not.toThrow();
});

test('--dry-run: nichts wird heruntergeladen oder geschrieben, Bericht zählt', () => {
  const t = setup(fakeModule({}));
  const calls = join(t.dir, 'calls.json');
  const res = run(['--dry-run', '--buckets', 'receipt-images', '--report', t.report], t.clients, calls);
  expect(res.status).toBe(0);
  expect(JSON.parse(readFileSync(calls, 'utf8'))).toEqual({ download: 0, put: 0 });
  const report = JSON.parse(readFileSync(t.report, 'utf8'));
  expect(report.dryRun).toBe(true);
  expect(report.buckets['receipt-images']).toMatchObject({ sourceCount: 3, sourceBytes: 12, wouldCopy: 3, copied: 0, skipped: 0 });
  expect(report.ok).toBe(true);
});

test('--dry-run: bereits kopierte Objekte werden als übersprungen gezählt (idempotent)', () => {
  const pre = { 'receipt-images/u1/a.jpg': { size: 4, etag: md5('AAAA') } };
  const t = setup(fakeModule(pre));
  const calls = join(t.dir, 'calls.json');
  run(['--dry-run', '--buckets', 'receipt-images', '--report', t.report], t.clients, calls);
  const stat = JSON.parse(readFileSync(t.report, 'utf8')).buckets['receipt-images'];
  expect(stat).toMatchObject({ skipped: 1, wouldCopy: 2 });
});

test('echter Lauf mit Fake-Clients: kopiert, vergleicht, zweiter Lauf überspringt alles', () => {
  const t = setup(fakeModule({ 'receipt-images/u1/a.jpg': { size: 4, etag: md5('AAAA') } }));
  const calls = join(t.dir, 'calls.json');
  const res = run(['--buckets', 'receipt-images', '--report', t.report], t.clients, calls);
  expect(res.status).toBe(0);
  expect(JSON.parse(readFileSync(calls, 'utf8'))).toEqual({ download: 2, put: 2 });
  const stat = JSON.parse(readFileSync(t.report, 'utf8')).buckets['receipt-images'];
  expect(stat).toMatchObject({ sourceCount: 3, copied: 2, skipped: 1, targetCount: 3, failed: [], mismatches: [], missing: [] });
});

test('Abweichung (gleiche Grösse, anderer MD5 im Ziel) wird überschrieben und geprüft; Bericht enthält keine Schlüssel', () => {
  const t = setup(fakeModule({ 'receipt-images/u1/a.jpg': { size: 4, etag: md5('XXXX') } }));
  const calls = join(t.dir, 'calls.json');
  run(['--buckets', 'receipt-images', '--report', t.report], t.clients, calls);
  const text = readFileSync(t.report, 'utf8');
  expect(JSON.parse(text).buckets['receipt-images']).toMatchObject({ copied: 3, mismatches: [] });
  expect(text).not.toMatch(/SECRET|secret|AKID|Bearer/);
});

test('unbekannte Option und unbekannter Bucket: Exit 1', () => {
  const t = setup(fakeModule({}));
  expect(run(['--loeschen'], t.clients, join(t.dir, 'c.json')).status).toBe(1);
  expect(run(['--buckets', 'fremd'], t.clients, join(t.dir, 'c.json')).status).toBe(1);
});

test('ohne Umgebungsvariablen bricht das Skript ab, ohne Netzwerk', () => {
  const res = spawnSync(process.execPath, [SCRIPT, '--dry-run'], { encoding: 'utf8', env: { PATH: process.env.PATH } as unknown as NodeJS.ProcessEnv });
  expect(res.status).toBe(1);
  expect(res.stderr).toMatch(/R2_ACCOUNT_ID/);
});
