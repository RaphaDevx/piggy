import { requireOptionalNativeModule } from 'expo-modules-core';

export interface ReceiptCaptureResult {
  status: 'success' | 'cancel';
  uri?: string;
  precropped?: boolean;
}

export interface ReceiptScannerModule {
  scanReceipt(): Promise<ReceiptCaptureResult>;
}

export default requireOptionalNativeModule<ReceiptScannerModule>('PiggyReceiptScanner');
