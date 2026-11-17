import { useCallback } from 'react';
import { useFocusEffect } from 'expo-router';
import { useSubscription } from '../contexts/SubscriptionContext';

/** Shared Gold entitlement. Internal «plus» identifier remains compatible with existing purchases. */
export function usePlus() {
  const subscription = useSubscription();
  useFocusEffect(useCallback(() => { void subscription.refresh(); }, [subscription.refresh]));
  return subscription;
}
