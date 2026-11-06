import { useCallback, useState } from 'react';
import { useFocusEffect } from 'expo-router';
import { subscriptionAvailable } from '../constants/features';
import { getPlusStatus, type PlusStatus } from '../lib/subscription';
import { NO_PLUS } from '../lib/subscriptionLogic';

/** Plus-Status des Nutzers; lädt bei jedem Fokus neu. Ohne verfügbares Feature passiert nichts. */
export function usePlus() {
  const available = subscriptionAvailable();
  const [status, setStatus] = useState<PlusStatus>(NO_PLUS);
  const [loading, setLoading] = useState(available);

  const refresh = useCallback(async () => {
    if (!available) return;
    setStatus(await getPlusStatus());
    setLoading(false);
  }, [available]);

  useFocusEffect(useCallback(() => { refresh(); }, [refresh]));

  return { available, status, loading, refresh, setStatus };
}
