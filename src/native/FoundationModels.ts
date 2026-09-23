import { NativeModules, Platform } from 'react-native';

export interface FoundationModelsModule {
  isAvailable(): Promise<boolean>;
  parseReceiptText(rawText: string): Promise<string>; // → JSON string
}

const { PiggyFoundationModels } = NativeModules;

export const FoundationModels: FoundationModelsModule = {
  isAvailable: async () => {
    if (Platform.OS !== 'ios') return false;
    if (!PiggyFoundationModels) return false;
    try { return await PiggyFoundationModels.isAvailable(); }
    catch { return false; }
  },
  parseReceiptText: async (rawText: string) => {
    if (!PiggyFoundationModels) throw new Error('Foundation Models not available');
    return PiggyFoundationModels.parseReceiptText(rawText);
  },
};
