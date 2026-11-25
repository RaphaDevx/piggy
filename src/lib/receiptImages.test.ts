const mockCreateSignedUrls = jest.fn();
const mockInvoke = jest.fn();
const previousStorageBackend = process.env.EXPO_PUBLIC_STORAGE_BACKEND;
jest.mock('./supabase', () => ({
  supabase: { functions: { invoke: (...args: unknown[]) => mockInvoke(...args) }, storage: { from: () => ({ createSignedUrls: mockCreateSignedUrls }) } },
}));
import { toStoragePath, getSignedReceiptImageUrls, clearReceiptImageCache } from './receiptImages';

const sign = (paths: string[]) => ({
  data: paths.map(path => ({ path, signedUrl: `https://s/${path}?token=t`, error: null })),
  error: null,
});

beforeEach(() => {
  process.env.EXPO_PUBLIC_STORAGE_BACKEND = 'supabase';
  mockInvoke.mockReset();
  clearReceiptImageCache();
  mockCreateSignedUrls.mockReset();
  mockCreateSignedUrls.mockImplementation(async (paths: string[]) => sign(paths));
});

afterAll(() => {
  if (previousStorageBackend === undefined) delete process.env.EXPO_PUBLIC_STORAGE_BACKEND;
  else process.env.EXPO_PUBLIC_STORAGE_BACKEND = previousStorageBackend;
});

describe('toStoragePath', () => {
  test('legacy public URL', () => {
    expect(toStoragePath('https://x.supabase.co/storage/v1/object/public/receipt-images/u1/123.jpg')).toBe('u1/123.jpg');
  });
  test('plain path', () => { expect(toStoragePath('u1/123_orig.png')).toBe('u1/123_orig.png'); });
  test('strips query string', () => {
    expect(toStoragePath('https://x.supabase.co/storage/v1/object/public/receipt-images/u1/1.jpg?t=5')).toBe('u1/1.jpg');
    expect(toStoragePath('u1/1.jpg?x=1')).toBe('u1/1.jpg');
  });
  test('local URIs and empty values are not storage paths', () => {
    expect(toStoragePath('file:///tmp/a.jpg')).toBeNull();
    expect(toStoragePath('blob:http://localhost/abc')).toBeNull();
    expect(toStoragePath(null)).toBeNull();
    expect(toStoragePath('')).toBeNull();
  });
});

describe('getSignedReceiptImageUrls', () => {
  test('one request per batch, deduplicated', async () => {
    const out = await getSignedReceiptImageUrls(['u/a.jpg', 'u/b.jpg', 'u/a.jpg']);
    expect(mockCreateSignedUrls).toHaveBeenCalledTimes(1);
    expect(mockCreateSignedUrls).toHaveBeenCalledWith(['u/a.jpg', 'u/b.jpg'], 3600);
    expect(out).toEqual(['https://s/u/a.jpg?token=t', 'https://s/u/b.jpg?token=t', 'https://s/u/a.jpg?token=t']);
  });
  test('cache hit avoids a second request, legacy URL maps to same key', async () => {
    await getSignedReceiptImageUrls(['u/a.jpg']);
    await getSignedReceiptImageUrls(['https://x.supabase.co/storage/v1/object/public/receipt-images/u/a.jpg']);
    expect(mockCreateSignedUrls).toHaveBeenCalledTimes(1);
  });
  test('only uncached paths are requested', async () => {
    await getSignedReceiptImageUrls(['u/a.jpg']);
    await getSignedReceiptImageUrls(['u/a.jpg', 'u/c.jpg']);
    expect(mockCreateSignedUrls).toHaveBeenLastCalledWith(['u/c.jpg'], 3600);
  });
  test('concurrent calls share one in-flight request', async () => {
    await Promise.all([getSignedReceiptImageUrls(['u/a.jpg']), getSignedReceiptImageUrls(['u/a.jpg'])]);
    expect(mockCreateSignedUrls).toHaveBeenCalledTimes(1);
  });
  test('expired cache entries are re-signed', async () => {
    const now = jest.spyOn(Date, 'now');
    now.mockReturnValue(1_000_000);
    await getSignedReceiptImageUrls(['u/a.jpg']);
    now.mockReturnValue(1_000_000 + 51 * 60 * 1000);
    await getSignedReceiptImageUrls(['u/a.jpg']);
    expect(mockCreateSignedUrls).toHaveBeenCalledTimes(2);
    now.mockRestore();
  });
  test('errors yield null and are not cached', async () => {
    mockCreateSignedUrls.mockResolvedValueOnce({ data: null, error: new Error('boom') });
    jest.spyOn(console, 'warn').mockImplementation(() => {});
    expect(await getSignedReceiptImageUrls(['u/a.jpg'])).toEqual([null]);
    expect(await getSignedReceiptImageUrls(['u/a.jpg'])).toEqual(['https://s/u/a.jpg?token=t']);
  });
  test('per-row error -> null; local URI passes through', async () => {
    mockCreateSignedUrls.mockResolvedValueOnce({ data: [{ path: 'u/x.jpg', signedUrl: null, error: 'Object not found' }], error: null });
    expect(await getSignedReceiptImageUrls(['u/x.jpg', 'file:///a.jpg'])).toEqual([null, 'file:///a.jpg']);
  });
});

test('R2 links are renewed within their ten-minute validity, without touching Supabase', async () => {
  process.env.EXPO_PUBLIC_STORAGE_BACKEND = 'r2-with-fallback';
  mockInvoke.mockResolvedValue({ data: { results: [{ path: 'u/r2.jpg', url: 'https://account.eu.r2.cloudflarestorage.com/bucket/u/r2.jpg', found: true }], expiresIn: 600 }, error: null });
  const now = jest.spyOn(Date, 'now');
  try {
    now.mockReturnValue(1_000_000);
    await getSignedReceiptImageUrls(['u/r2.jpg']);
    now.mockReturnValue(1_000_000 + 9 * 60 * 1000);
    await getSignedReceiptImageUrls(['u/r2.jpg']);
    expect(mockInvoke).toHaveBeenCalledTimes(2);
    expect(mockCreateSignedUrls).not.toHaveBeenCalled();
  } finally { now.mockRestore(); }
});
