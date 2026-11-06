jest.mock('./supabase', () => ({
  supabase: { from: jest.fn(), auth: { getSession: jest.fn() } },
}));
jest.mock('@react-native-async-storage/async-storage', () =>
  require('@react-native-async-storage/async-storage/jest/async-storage-mock'));
jest.mock('../constants/features', () => ({ FEATURES: { analytics: true } }));

import AsyncStorage from '@react-native-async-storage/async-storage';
import { supabase } from './supabase';
import { FEATURES } from '../constants/features';
import { buildEvent, track, setAnalyticsEnabled, getAnalyticsEnabled, EVENT_SPEC } from './analytics';

const mockSupabase = supabase as unknown as { auth: { getSession: jest.Mock }; from: jest.Mock };
const flags = FEATURES as { analytics: boolean };

function signedIn(userId: string | null) {
  mockSupabase.auth.getSession.mockResolvedValue({ data: { session: userId ? { user: { id: userId } } : null } });
}

describe('buildEvent', () => {
  it('baut eine Zeile für erlaubte Ereignisse und Eigenschaften', () => {
    const row = buildEvent('scan_saved', { mode: 'merge' }, 'u1');
    expect(row).toMatchObject({ user_id: 'u1', event: 'scan_saved', props: { mode: 'merge' } });
    expect(buildEvent('app_open', undefined, 'u1')).toMatchObject({ event: 'app_open', props: {} });
  });

  it('lehnt unbekannte Ereignisse, fremde Eigenschaften und fremde Werte ab', () => {
    expect(buildEvent('receipt_total', {}, 'u1')).toBeNull();
    expect(buildEvent('toString', {}, 'u1')).toBeNull();
    expect(buildEvent('app_open', { merchant: 'Migros' }, 'u1')).toBeNull();
    expect(buildEvent('scan_saved', { mode: 'Migros 12.50' }, 'u1')).toBeNull();
    expect(buildEvent('scan_failed', { stage: 'x' }, 'u1')).toBeNull();
  });

  it('Eigenschaften sind ausschliesslich feste Werte, nie Inhalte', () => {
    for (const spec of Object.values(EVENT_SPEC)) {
      for (const values of Object.values(spec)) {
        for (const v of values) expect(v).toMatch(/^[a-z]{2,10}$/);
      }
    }
  });
});

describe('track', () => {
  beforeEach(async () => {
    await AsyncStorage.clear();
    flags.analytics = true;
    mockSupabase.from.mockReset();
    mockSupabase.auth.getSession.mockReset();
  });

  it('schreibt eine Zeile in analytics_events', async () => {
    signedIn('u1');
    const insert = jest.fn().mockResolvedValue({ error: null });
    mockSupabase.from.mockReturnValue({ insert });
    await track('scan_started');
    expect(mockSupabase.from).toHaveBeenCalledWith('analytics_events');
    expect(insert).toHaveBeenCalledWith(expect.objectContaining({ user_id: 'u1', event: 'scan_started', props: {} }));
  });

  it('tut nichts, solange FEATURES.analytics aus ist', async () => {
    flags.analytics = false;
    signedIn('u1');
    await track('app_open');
    expect(mockSupabase.from).not.toHaveBeenCalled();
  });

  it('tut nichts nach Abschalten in den Einstellungen und wieder nach Einschalten', async () => {
    signedIn('u1');
    const insert = jest.fn().mockResolvedValue({ error: null });
    mockSupabase.from.mockReturnValue({ insert });
    await setAnalyticsEnabled(false);
    expect(await getAnalyticsEnabled()).toBe(false);
    await track('app_open');
    expect(insert).not.toHaveBeenCalled();
    await setAnalyticsEnabled(true);
    await track('app_open');
    expect(insert).toHaveBeenCalledTimes(1);
  });

  it('tut nichts ohne Sitzung und wirft nie', async () => {
    signedIn(null);
    await expect(track('app_open')).resolves.toBeUndefined();
    expect(mockSupabase.from).not.toHaveBeenCalled();
    signedIn('u1');
    mockSupabase.from.mockImplementation(() => { throw new Error('sync'); });
    await expect(track('app_open')).resolves.toBeUndefined();
    mockSupabase.auth.getSession.mockRejectedValue(new Error('kaputt'));
    await expect(track('app_open')).resolves.toBeUndefined();
  });
});
