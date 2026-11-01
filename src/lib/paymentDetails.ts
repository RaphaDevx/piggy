/**
 * paymentDetails.ts — eigene Zahlungsangaben (IBAN, Adresse, Hinweis) nur lokal auf dem Gerät.
 * Bewusst nicht in Supabase: Piggy-Server sehen weder IBAN noch Adresse.
 */
import AsyncStorage from '@react-native-async-storage/async-storage';
import { EMPTY_PAYMENT_DETAILS, type PaymentDetails } from './paymentRequest';

const STORAGE_KEY = 'piggy.paymentDetails.v1';

export async function loadPaymentDetails(): Promise<PaymentDetails> {
  try {
    const raw = await AsyncStorage.getItem(STORAGE_KEY);
    return raw ? { ...EMPTY_PAYMENT_DETAILS, ...(JSON.parse(raw) as Partial<PaymentDetails>) } : EMPTY_PAYMENT_DETAILS;
  } catch {
    return EMPTY_PAYMENT_DETAILS;
  }
}

export async function savePaymentDetails(details: PaymentDetails): Promise<void> {
  await AsyncStorage.setItem(STORAGE_KEY, JSON.stringify(details));
}
