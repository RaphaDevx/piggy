const mockInvoke = jest.fn();
const mockUpload = jest.fn();
const mockCreateSignedUrls = jest.fn();
const mockRemove = jest.fn();
jest.mock('./supabase', () => ({
  supabase: {
    functions: { invoke: (...a: unknown[]) => mockInvoke(...a) },
    storage: { from: () => ({ upload: mockUpload, createSignedUrls: mockCreateSignedUrls, remove: mockRemove }) },
  },
}));
import { getSignedUrls, upload, remove, getStorageBackend, signedUrlTtlSeconds } from './objectStorage';

const setBackend = (v?: string) => { if (v === undefined) delete process.env.EXPO_PUBLIC_STORAGE_BACKEND; else process.env.EXPO_PUBLIC_STORAGE_BACKEND = v; };
const sbSigned = (paths: string[]) => ({ data: paths.map((path) => ({ path, signedUrl: `https://sb/${path}`, error: null })), error: null });
const fetchMock = jest.fn();

beforeEach(() => {
  jest.resetAllMocks();
  (global as unknown as { fetch: unknown }).fetch = fetchMock;
  mockCreateSignedUrls.mockImplementation(async (paths: string[]) => sbSigned(paths));
  mockUpload.mockResolvedValue({ error: null });
  mockRemove.mockResolvedValue({ error: null });
  jest.spyOn(console, 'warn').mockImplementation(() => {});
  setBackend(undefined);
});
afterAll(() => setBackend(undefined));

describe('Schalter', () => {
  test('Standard: R2 mit Lese-Fallback; unbekannte Werte bleiben explizit kompatibel', () => {
    expect(getStorageBackend()).toBe('r2-with-fallback');
    expect(signedUrlTtlSeconds()).toBe(600);
    setBackend('quatsch');
    expect(getStorageBackend()).toBe('supabase');
    expect(signedUrlTtlSeconds()).toBe(3600);
  });
  test('R2-Modi: 10 Minuten', () => {
    setBackend('r2'); expect(signedUrlTtlSeconds()).toBe(600);
    setBackend('r2-with-fallback'); expect(signedUrlTtlSeconds()).toBe(600);
  });
});

describe('supabase', () => {
  beforeEach(() => setBackend('supabase'));
  test('lesen/schreiben/löschen nur über Supabase, kein Function-Aufruf', async () => {
    expect(await getSignedUrls('receipt-images', ['u/a.jpg'], 3600)).toEqual(['https://sb/u/a.jpg']);
    expect(mockCreateSignedUrls).toHaveBeenCalledWith(['u/a.jpg'], 3600);
    expect(await upload('receipts', 'u/a.jpg', new Uint8Array([1]), { contentType: 'image/jpeg', upsert: true })).toEqual({ error: null });
    expect(mockUpload).toHaveBeenCalledWith('u/a.jpg', expect.anything(), { contentType: 'image/jpeg', upsert: true });
    await remove('receipts', ['u/a.jpg']);
    expect(mockInvoke).not.toHaveBeenCalled();
    expect(fetchMock).not.toHaveBeenCalled();
  });
  test('Upload-Fehler wird als Error geliefert', async () => {
    mockUpload.mockResolvedValue({ error: { message: 'voll' } });
    expect((await upload('receipts', 'u/a', new Uint8Array([1]))).error?.message).toBe('voll');
  });
});

describe('r2', () => {
  beforeEach(() => setBackend('r2'));
  test('lesen: URL von der Function, Supabase unberührt, keine Fallback-Prüfung', async () => {
    mockInvoke.mockResolvedValue({ data: { results: [{ path: 'u/a.jpg', url: 'https://r2/a' }] }, error: null });
    expect(await getSignedUrls('receipt-images', ['u/a.jpg'])).toEqual(['https://r2/a']);
    expect(mockInvoke).toHaveBeenCalledWith('storage-url', { body: { op: 'get', bucket: 'receipt-images', paths: ['u/a.jpg'], verify: false } });
    expect(mockCreateSignedUrls).not.toHaveBeenCalled();
  });
  test('lesen: Function-Fehler wird geworfen, kein stiller Fallback', async () => {
    mockInvoke.mockResolvedValue({ data: null, error: new Error('down') });
    await expect(getSignedUrls('receipt-images', ['u/a.jpg'])).rejects.toThrow('down');
  });
  test('Upload: PUT auf die vorsignierte URL', async () => {
    mockInvoke.mockResolvedValue({ data: { results: [{ path: 'u/a.jpg', url: 'https://r2/put' }] }, error: null });
    fetchMock.mockResolvedValue({ ok: true, status: 200 });
    const body = new Uint8Array([1, 2]);
    expect(await upload('receipt-images', 'u/a.jpg', body, { contentType: 'image/jpeg' })).toEqual({ error: null });
    expect(fetchMock).toHaveBeenCalledWith('https://r2/put', { method: 'PUT', headers: { 'Content-Type': 'image/jpeg' }, body });
    expect(mockUpload).not.toHaveBeenCalled();
  });
  test('Upload-Fehler: Error, kein Supabase-Fallback', async () => {
    mockInvoke.mockResolvedValue({ data: { results: [{ path: 'u/a.jpg', url: 'https://r2/put' }] }, error: null });
    fetchMock.mockResolvedValue({ ok: false, status: 403 });
    const res = await upload('receipt-images', 'u/a.jpg', new Uint8Array([1]));
    expect(res.error?.message).toMatch(/403/);
    expect(mockUpload).not.toHaveBeenCalled();
  });
  test('Löschen: löscht in R2 (404 ist ok), nicht in Supabase', async () => {
    mockInvoke.mockResolvedValue({ data: { results: [{ path: 'u/a', url: 'https://r2/del' }] }, error: null });
    fetchMock.mockResolvedValue({ ok: false, status: 404 });
    expect(await remove('receipts', ['u/a'])).toEqual({ error: null });
    expect(mockRemove).not.toHaveBeenCalled();
  });
});

describe('r2-with-fallback', () => {
  beforeEach(() => setBackend('r2-with-fallback'));
  test('lesen: Treffer aus R2, 404 (found false) aus Supabase, Reihenfolge bleibt', async () => {
    mockInvoke.mockResolvedValue({ data: { results: [
      { path: 'u/new.jpg', url: 'https://r2/new', found: true },
      { path: 'u/old.jpg', url: null, found: false },
    ] }, error: null });
    const out = await getSignedUrls('receipt-images', ['u/old.jpg', 'u/new.jpg']);
    expect(out).toEqual(['https://sb/u/old.jpg', 'https://r2/new']);
    expect(mockInvoke).toHaveBeenCalledWith('storage-url', { body: { op: 'get', bucket: 'receipt-images', paths: ['u/old.jpg', 'u/new.jpg'], verify: true } });
    expect(mockCreateSignedUrls).toHaveBeenCalledWith(['u/old.jpg'], 600);
  });
  test('alles in R2: Supabase wird nicht gefragt', async () => {
    mockInvoke.mockResolvedValue({ data: { results: [{ path: 'u/a', url: 'https://r2/a', found: true }] }, error: null });
    await getSignedUrls('receipts', ['u/a']);
    expect(mockCreateSignedUrls).not.toHaveBeenCalled();
  });
  test('Function nicht erreichbar: alles über Supabase', async () => {
    mockInvoke.mockResolvedValue({ data: null, error: new Error('down') });
    expect(await getSignedUrls('receipts', ['u/a'])).toEqual(['https://sb/u/a']);
  });
  test('Fehler pro Eintrag (nicht 404) ergibt null, kein Fallback', async () => {
    mockInvoke.mockResolvedValue({ data: { results: [{ path: 'u/a', url: null, error: 'unavailable' }] }, error: null });
    expect(await getSignedUrls('receipts', ['u/a'])).toEqual([null]);
    expect(mockCreateSignedUrls).not.toHaveBeenCalled();
  });
  test('Upload: R2-Fehler bleibt sichtbar, kein Zurückschreiben nach Supabase', async () => {
    mockInvoke.mockResolvedValue({ data: null, error: new Error('down') });
    expect((await upload('receipts', 'u/a', new Uint8Array([1]), { contentType: 'image/png' })).error?.message).toBe('down');
    expect(mockUpload).not.toHaveBeenCalled();
  });
  test('Löschen: in beiden', async () => {
    mockInvoke.mockResolvedValue({ data: { results: [{ path: 'u/a', url: 'https://r2/del' }] }, error: null });
    fetchMock.mockResolvedValue({ ok: true, status: 204 });
    expect(await remove('receipts', ['u/a'])).toEqual({ error: null });
    expect(mockRemove).toHaveBeenCalledWith(['u/a']);
    expect(fetchMock).toHaveBeenCalledWith('https://r2/del', { method: 'DELETE' });
  });
});
