/** @jest-environment node */
import { validateOwnPath, r2Key, parseBackend, USER_BUCKETS } from './paths';
import { handleStorageUrl, type StorageUrlDeps } from './storageUrl';
import { clampTtl, createR2, deleteUserObjects, parseListXml, r2ConfigFromEnv, r2Host } from './r2';
import { readObject } from './read';

const UID = '11111111-1111-1111-1111-111111111111';
const OTHER = '22222222-2222-2222-2222-222222222222';

describe('validateOwnPath', () => {
  test('eigener Pfad ist erlaubt', () => {
    expect(validateOwnPath(UID, `${UID}/123.jpg`)).toBeNull();
    expect(validateOwnPath(UID, `${UID}/sub/123_orig.png`)).toBeNull();
  });
  test.each([
    [`${OTHER}/123.jpg`], [`${UID}`], [`${UID}/`], [`${UID}/../${OTHER}/1.jpg`], [`${UID}//1.jpg`],
    [`/${UID}/1.jpg`], [`${UID}\\1.jpg`], [`${UID}/a\u0000b`], [`${UID}x/1.jpg`], [''], [null], [42],
  ])('wird abgelehnt: %p', (path) => {
    expect(validateOwnPath(UID, path)).not.toBeNull();
  });
  test('zu langer Pfad', () => { expect(validateOwnPath(UID, `${UID}/${'a'.repeat(600)}`)).not.toBeNull(); });
});

test('r2Key, parseBackend, clampTtl, r2ConfigFromEnv', () => {
  expect(r2Key('receipt-images', `${UID}/1.jpg`)).toBe(`receipt-images/${UID}/1.jpg`);
  expect(parseBackend('r2')).toBe('r2');
  expect(parseBackend('r2-with-fallback')).toBe('r2-with-fallback');
  expect(parseBackend('quatsch')).toBe('supabase');
  expect(parseBackend(undefined)).toBe('supabase');
  expect(clampTtl(99999)).toBe(600);
  expect(clampTtl(30)).toBe(30);
  expect(clampTtl('x')).toBe(600);
  expect(r2ConfigFromEnv(() => undefined)).toBeNull();
  expect(r2ConfigFromEnv((n) => (n === 'R2_JURISDICTION' ? undefined : `v-${n}`))).toEqual({ accountId: 'v-R2_ACCOUNT_ID', accessKeyId: 'v-R2_ACCESS_KEY_ID', secretAccessKey: 'v-R2_SECRET_ACCESS_KEY', bucket: 'v-R2_BUCKET' });
});

test('EU-Jurisdiktion: eigener Endpunkt, ungültige Werte werden abgelehnt', () => {
  const base: Record<string, string> = { R2_ACCOUNT_ID: 'acct', R2_ACCESS_KEY_ID: 'k', R2_SECRET_ACCESS_KEY: 's', R2_BUCKET: 'b' };
  expect(r2Host({ accountId: 'acct' })).toBe('acct.r2.cloudflarestorage.com');
  expect(r2Host({ accountId: 'acct', jurisdiction: 'eu' })).toBe('acct.eu.r2.cloudflarestorage.com');
  const eu = r2ConfigFromEnv((n) => ({ ...base, R2_JURISDICTION: ' EU ' })[n]);
  expect(eu?.jurisdiction).toBe('eu');
  expect(r2ConfigFromEnv((n) => ({ ...base, R2_JURISDICTION: 'us' })[n])).toBeNull();
  expect(r2ConfigFromEnv((n) => base[n])?.jurisdiction).toBeUndefined();
});

describe('handleStorageUrl', () => {
  const presign = jest.fn(async (method: string, key: string, ttl?: number) => `https://r2.example/${method}/${key}?ttl=${ttl}`);
  const head = jest.fn(async (_key: string) => ({ size: 1, etag: 'x' }) as { size: number; etag: string } | null);
  const deps = (user: { id: string } | null = { id: UID }): StorageUrlDeps => ({
    authenticate: async () => user, r2: { presign, head } as never,
  });
  const call = (body: unknown, d = deps(), method = 'POST') =>
    handleStorageUrl(new Request('https://f.example/storage-url', { method, body: method === 'POST' ? JSON.stringify(body) : undefined }), d);
  beforeEach(() => { presign.mockClear(); head.mockClear(); });

  test('ohne gültigen Nutzer: 401', async () => {
    expect((await call({ op: 'get', bucket: 'receipt-images', paths: [`${UID}/1.jpg`] }, deps(null))).status).toBe(401);
    expect(presign).not.toHaveBeenCalled();
  });
  test('fremder Nutzerpfad wird abgelehnt (403), nichts wird signiert', async () => {
    const res = await call({ op: 'get', bucket: 'receipt-images', paths: [`${UID}/1.jpg`, `${OTHER}/1.jpg`] });
    expect(res.status).toBe(403);
    expect(presign).not.toHaveBeenCalled();
  });
  test('Pfad-Traversal wird abgelehnt', async () => {
    expect((await call({ op: 'put', bucket: 'receipts', paths: [`${UID}/../${OTHER}/1.jpg`] })).status).toBe(403);
  });
  test('GET liefert URL mit R2-Schlüssel <bucket>/<pfad>, TTL höchstens 600', async () => {
    const res = await call({ op: 'get', bucket: 'receipt-images', paths: [`${UID}/1.jpg`], expiresIn: 99999 });
    expect(res.status).toBe(200);
    const body = await res.json();
    expect(body.expiresIn).toBe(600);
    expect(presign).toHaveBeenCalledWith('GET', `receipt-images/${UID}/1.jpg`, 600);
    expect(body.results[0].url).toContain('receipt-images');
    expect(head).not.toHaveBeenCalled();
  });
  test('PUT und DELETE verwenden die passende Methode', async () => {
    await call({ op: 'put', bucket: 'bank-statements', paths: [`${UID}/a.pdf`] });
    await call({ op: 'delete', bucket: 'bank-statements', paths: [`${UID}/a.pdf`] });
    expect(presign.mock.calls.map((c) => c[0])).toEqual(['PUT', 'DELETE']);
  });
  test('verify: fehlendes Objekt -> found false, keine URL', async () => {
    head.mockResolvedValueOnce(null);
    const body = await (await call({ op: 'get', bucket: 'receipt-images', paths: [`${UID}/1.jpg`, `${UID}/2.jpg`], verify: true })).json();
    expect(body.results).toEqual([
      { path: `${UID}/1.jpg`, url: null, found: false },
      expect.objectContaining({ path: `${UID}/2.jpg`, found: true }),
    ]);
  });
  test('R2-Fehler bei einem Pfad ergibt error je Eintrag, nicht 500', async () => {
    presign.mockRejectedValueOnce(new Error('boom'));
    jest.spyOn(console, 'error').mockImplementation(() => {});
    const body = await (await call({ op: 'get', bucket: 'receipts', paths: [`${UID}/1.jpg`] })).json();
    expect(body.results[0]).toEqual({ path: `${UID}/1.jpg`, url: null, error: 'unavailable' });
  });
  test('ungültige Eingaben: 400, ohne R2: 503, falsche Methode: 405', async () => {
    expect((await call({ op: 'x', bucket: 'receipts', paths: [`${UID}/1`] })).status).toBe(400);
    expect((await call({ op: 'get', bucket: 'fremd', paths: [`${UID}/1`] })).status).toBe(400);
    expect((await call({ op: 'get', bucket: 'receipts', paths: [] })).status).toBe(400);
    expect((await call({ op: 'get', bucket: 'receipts', paths: Array(101).fill(`${UID}/1`) })).status).toBe(400);
    expect((await call({ op: 'get', bucket: 'receipts', paths: [`${UID}/1`] }, { authenticate: async () => ({ id: UID }), r2: null })).status).toBe(503);
    expect((await call(null, deps(), 'GET')).status).toBe(405);
  });
});

describe('R2-Client', () => {
  const config = { accountId: 'acct', accessKeyId: 'AKID', secretAccessKey: 'SECRET', bucket: 'bkt' };
  test('list paginiert und entschlüsselt XML', async () => {
    const pages = [
      '<ListBucketResult><IsTruncated>true</IsTruncated><NextContinuationToken>tok&amp;1</NextContinuationToken><Contents><Key>receipts/u/a&amp;b.jpg</Key><Size>5</Size><ETag>&quot;abc&quot;</ETag></Contents></ListBucketResult>',
      '<ListBucketResult><IsTruncated>false</IsTruncated><Contents><Key>receipts/u/c.jpg</Key><Size>7</Size><ETag>"def"</ETag></Contents></ListBucketResult>',
    ];
    const urls: string[] = [];
    const fetchFake = jest.fn(async (url: string) => { urls.push(url); return new Response(pages.shift()!); });
    const list = await createR2(config, fetchFake as never).list('receipts/u/');
    expect(list).toEqual([{ key: 'receipts/u/a&b.jpg', size: 5, etag: 'abc' }, { key: 'receipts/u/c.jpg', size: 7, etag: 'def' }]);
    expect(urls[0]).toContain('acct.r2.cloudflarestorage.com/bkt?');
    expect(urls[1]).toContain('continuation-token=tok%261');
  });
  test('head: 404 -> null, Fehlertext enthält keine URL', async () => {
    const r2 = createR2(config, (async () => new Response(null, { status: 404 })) as never);
    expect(await r2.head('x/y')).toBeNull();
    const bad = createR2(config, (async () => new Response(null, { status: 500 })) as never);
    await expect(bad.head('x/y')).rejects.toThrow(/^R2 HEAD fehlgeschlagen \(HTTP 500\)$/);
  });
  test('parseListXml ohne Treffer', () => { expect(parseListXml('<ListBucketResult/>')).toEqual({ objects: [], nextToken: null }); });
  test('deleteUserObjects löscht nur unter dem Nutzerpräfix aller Buckets', async () => {
    const list = jest.fn(async (prefix: string) => [{ key: `${prefix}1.jpg`, size: 1, etag: '' }, { key: `${prefix}2.jpg`, size: 1, etag: '' }]);
    const del = jest.fn(async (_k: string) => {});
    const n = await deleteUserObjects({ list, delete: del }, UID);
    expect(n).toBe(2 * USER_BUCKETS.length);
    expect(list.mock.calls.map((c) => c[0])).toEqual(USER_BUCKETS.map((b) => `${b}/${UID}/`));
    expect(del).toHaveBeenCalledWith(`receipts/${UID}/1.jpg`);
  });
});

describe('readObject (Edge-Function-Lesen)', () => {
  const bytes = new Uint8Array([1, 2, 3]);
  const sb = (v: Uint8Array | null) => jest.fn(async () => v);
  test('supabase: R2 wird nie berührt', async () => {
    const get = jest.fn();
    expect(await readObject({ backend: 'supabase', r2: { get }, supabaseDownload: sb(bytes) }, 'bank-statements', 'u/a')).toBe(bytes);
    expect(get).not.toHaveBeenCalled();
  });
  test('r2: Treffer in R2, kein Fallback bei 404', async () => {
    expect(await readObject({ backend: 'r2', r2: { get: async () => bytes }, supabaseDownload: sb(null) }, 'bank-statements', 'u/a')).toBe(bytes);
    const download = sb(bytes);
    await expect(readObject({ backend: 'r2', r2: { get: async () => null }, supabaseDownload: download }, 'bank-statements', 'u/a')).rejects.toThrow();
    expect(download).not.toHaveBeenCalled();
  });
  test('r2-with-fallback: bei 404 liest Supabase, Schlüssel mit Bucket-Präfix', async () => {
    const get = jest.fn(async (_k: string) => null);
    expect(await readObject({ backend: 'r2-with-fallback', r2: { get }, supabaseDownload: sb(bytes) }, 'bank-statements', 'u/a')).toBe(bytes);
    expect(get).toHaveBeenCalledWith('bank-statements/u/a');
  });
  test('beide leer -> Fehler; r2 ohne Konfiguration -> Fehler', async () => {
    await expect(readObject({ backend: 'r2-with-fallback', r2: { get: async () => null }, supabaseDownload: sb(null) }, 'receipts', 'u/a')).rejects.toThrow(/nicht gefunden/);
    await expect(readObject({ backend: 'r2', r2: null, supabaseDownload: sb(bytes) }, 'receipts', 'u/a')).rejects.toThrow(/konfiguriert/);
  });
});

// SigV4 binds the HTTP verb: signing HEAD/DELETE but sending GET fails in real R2.
test('R2 head and delete send the exact signed method', async () => {
  const calls: RequestInit[] = [];
  const r2 = createR2({ accountId: 'account', accessKeyId: 'key', secretAccessKey: 'secret', bucket: 'bucket' }, (async (_url: unknown, init?: RequestInit) => { calls.push(init ?? {}); return new Response(null, { status: 200 }); }) as never);
  await r2.head('receipt-images/user/file.jpg');
  await r2.delete('receipt-images/user/file.jpg');
  expect(calls.map(c => c.method)).toEqual(['HEAD', 'DELETE']);
});
