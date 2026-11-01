const mockCreateSignedUrls = jest.fn();
jest.mock('./supabase', () => ({
  supabase: { storage: { from: () => ({ createSignedUrls: mockCreateSignedUrls }) } },
}));
import { toStoragePath, getSignedReceiptImageUrls, clearReceiptImageCache } from './receiptImages';

const sign = (paths: string[]) => ({
  data: paths.map(path => ({ path, signedUrl: `https://s/${path}?token=t`, error: null })),
  error: null,
});

beforeEach(() => {
  clearReceiptImageCache();
  mockCreateSignedUrls.mockReset();
  mockCreateSignedUrls.mockImplementation(async (paths: string[]) => sign(paths));
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
