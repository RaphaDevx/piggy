import { createContext, useCallback, useContext, useEffect, useRef, useState, type ReactNode } from 'react';
import { AppState } from 'react-native';
import { subscriptionAvailable } from '../constants/features';
import { initSubscription, logOutSubscription, readPlusStatus, onPlusStatusChange } from '../lib/subscription';
import { NO_PLUS, type PlusStatus } from '../lib/subscriptionLogic';

type Snapshot = { userId: string | null; status: PlusStatus; resolved: boolean };
type SubscriptionValue = {
  userId: string | null; available: boolean; status: PlusStatus; loading: boolean; resolved: boolean;
  refresh: () => Promise<void>; setStatus: (status: PlusStatus) => void;
};
const Context = createContext<SubscriptionValue | null>(null);

/** One account-scoped status for branding, purchases and ads. Unknown status never permits ads. */
export function SubscriptionProvider({ userId, children }: { userId: string | null; children: ReactNode }) {
  const available = subscriptionAvailable();
  const [snapshot, setSnapshot] = useState<Snapshot>({ userId: null, status: NO_PLUS, resolved: false });
  const currentUser = useRef(userId);
  currentUser.current = userId;
  const generation = useRef(0);
  const refresh = useCallback(async () => {
    const epoch = generation.current;
    if (!userId || !available) return;
    if (!(await initSubscription(userId))) return;
    const status = await readPlusStatus();
    if (currentUser.current !== userId || generation.current !== epoch) return;
    // A failed lookup keeps the previous known status, but disables ad eligibility.
    setSnapshot(previous => ({ userId, status: status ?? (previous.userId === userId ? previous.status : NO_PLUS), resolved: status !== null }));
  }, [userId, available]);

  useEffect(() => {
    generation.current += 1;
    const epoch = generation.current;
    let unsubscribe = () => {};
    setSnapshot({ userId, status: NO_PLUS, resolved: false });
    if (userId && available) {
      void (async () => {
        await refresh();
        if (generation.current !== epoch || currentUser.current !== userId) return;
        unsubscribe = onPlusStatusChange(status => {
          if (generation.current === epoch && currentUser.current === userId) setSnapshot({ userId, status, resolved: true });
        });
      })();
    }
    const appState = AppState.addEventListener('change', state => { if (state === 'active') void refresh(); });
    return () => { generation.current += 1; unsubscribe(); appState.remove(); void logOutSubscription(); };
  }, [userId, available, refresh]);

  const sameAccount = snapshot.userId === userId;
  const resolved = !!userId && sameAccount && snapshot.resolved;
  const setStatus = useCallback((status: PlusStatus) => {
    if (userId && currentUser.current === userId) setSnapshot({ userId, status, resolved: true });
  }, [userId]);
  return <Context.Provider value={{ userId, available, status: sameAccount ? snapshot.status : NO_PLUS,
    resolved, loading: available && !resolved, refresh, setStatus }}>{children}</Context.Provider>;
}

export function useSubscription() {
  const value = useContext(Context);
  if (!value) throw new Error('SubscriptionProvider is missing');
  return value;
}
