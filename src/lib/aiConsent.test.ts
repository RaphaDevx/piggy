jest.unmock('./aiConsent');
jest.mock('./supabase', () => ({
  supabase: { from: jest.fn(), auth: { getSession: jest.fn() } },
}));
jest.mock('@react-native-async-storage/async-storage', () =>
  require('@react-native-async-storage/async-storage/jest/async-storage-mock'));

import AsyncStorage from '@react-native-async-storage/async-storage';
import { supabase } from './supabase';
import { setAiConsent, getAiConsent, syncAiConsent, AI_CONSENT_VERSION } from './aiConsent';
import { featureCopy } from './featureCopy';

const mockSupabase = supabase as unknown as {
  auth: { getSession: jest.Mock };
  from: jest.Mock;
};

function session(userId: string | null) {
  mockSupabase.auth.getSession.mockResolvedValue({ data: { session: userId ? { user: { id: userId } } : null } });
}

describe('setAiConsent', () => {
  beforeEach(async () => {
    await AsyncStorage.clear();
    mockSupabase.from.mockReset();
    mockSupabase.auth.getSession.mockReset();
  });

  it('wirft nie, wenn supabase (upsert) fehlschlägt, und speichert lokal', async () => {
    session('u1');
    mockSupabase.from.mockReturnValue({ upsert: jest.fn().mockRejectedValue(new Error('netz')) });
    await expect(setAiConsent('granted')).resolves.toBeUndefined();
    expect(await getAiConsent()).toBe('granted');
  });

  it('wirft nie, wenn getSession wirft oder from() synchron wirft', async () => {
    mockSupabase.auth.getSession.mockRejectedValue(new Error('kaputt'));
    await expect(setAiConsent('granted')).resolves.toBeUndefined();
    session('u1');
    mockSupabase.from.mockImplementation(() => { throw new Error('sync'); });
    await expect(setAiConsent('granted')).resolves.toBeUndefined();
  });

  it('bleibt ohne Sitzung still und schreibt nichts in die Datenbank', async () => {
    session(null);
    await setAiConsent('granted');
    expect(mockSupabase.from).not.toHaveBeenCalled();
    expect(await getAiConsent()).toBe('granted');
  });

  it('schreibt Zeitstempel und Version bei granted, null bei denied', async () => {
    session('u1');
    const upsert = jest.fn().mockResolvedValue({ error: null });
    mockSupabase.from.mockReturnValue({ upsert });
    await setAiConsent('granted');
    expect(mockSupabase.from).toHaveBeenCalledWith('user_settings');
    const [row, opts] = upsert.mock.calls[0];
    expect(row.user_id).toBe('u1');
    expect(row.ai_consent_version).toBe(AI_CONSENT_VERSION);
    expect(Number.isNaN(Date.parse(row.ai_consent_at))).toBe(false);
    expect(opts).toEqual({ onConflict: 'user_id' });

    await setAiConsent('denied');
    expect(upsert.mock.calls[1][0].ai_consent_at).toBeNull();
  });

  it('syncAiConsent wirft nie und schreibt nur ohne vorhandenen Zeitstempel', async () => {
    await AsyncStorage.setItem('piggy.aiConsent.v1', 'granted');
    session('u1');
    const upsert = jest.fn().mockResolvedValue({ error: null });
    const maybeSingle = jest.fn().mockResolvedValue({ data: { ai_consent_at: '2026-10-31T10:00:00Z' } });
    mockSupabase.from.mockReturnValue({
      select: () => ({ eq: () => ({ maybeSingle }) }),
      upsert,
    });
    await syncAiConsent();
    expect(upsert).not.toHaveBeenCalled();

    maybeSingle.mockResolvedValue({ data: null });
    await syncAiConsent();
    expect(upsert).toHaveBeenCalledTimes(1);

    maybeSingle.mockRejectedValue(new Error('x'));
    await expect(syncAiConsent()).resolves.toBeUndefined();
  });
});

describe('featureCopy', () => {
  it('nennt Kontoauszüge nur bei eingeschaltetem Schalter', () => {
    const off = featureCopy({ bankStatements: false });
    const on = featureCopy({ bankStatements: true });
    const texts = (c: ReturnType<typeof featureCopy>) =>
      [c.aiConsentDialog, c.aiConsentRowSub, c.deleteAccountBody, c.profileEmpty, c.financeEmpty, c.duplicateProtectionNote].join(' ');
    expect(texts(off)).not.toMatch(/Kontoausz|Bank/i);
    expect(texts(on)).toMatch(/Kontoausz/);
  });

  it('Dialog nennt Anbieter, USA und dass Ablehnen die App nutzbar lässt', () => {
    const t = featureCopy({ bankStatements: false }).aiConsentDialog;
    expect(t).toMatch(/Anthropic/);
    expect(t).toMatch(/USA/);
    expect(t).toMatch(/weiter nutzbar/);
    expect(t).not.toMatch(/Training/);
    expect(t).toMatch(/Datenschutzerklärung/);
  });
});
