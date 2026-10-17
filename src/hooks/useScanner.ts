import { useState, useCallback, useRef } from 'react';
import { Alert, InteractionManager, Platform } from 'react-native';
import * as ImagePicker from 'expo-image-picker';
import { useRouter } from 'expo-router';
import Constants from 'expo-constants';
import DocumentScanner from 'react-native-document-scanner-plugin';
import ReceiptScanner from '../../modules/piggy-receipt-scanner';

const isNative      = Platform.OS !== 'web';
const isExpoGo      = isNative && Constants.appOwnership === 'expo';
const hasDocScanner = isNative && !isExpoGo;

export const ALL_SCAN_SOURCES = [
  { key: 'scan'    as const, label: 'Quittung scannen', icon: 'scan-outline'   as const, color: '#FF9F0A', nativeOnly: true  },
  { key: 'library' as const, label: 'Bild hochladen',   icon: 'images-outline' as const, color: '#30D158', nativeOnly: false },
];

export const SCAN_SOURCES = ALL_SCAN_SOURCES.filter((s) => hasDocScanner || !s.nativeOnly);

export type ScanSource = 'scan' | 'library';

export function useScanner() {
  const router   = useRouter();
  const inputRef = useRef<HTMLInputElement | null>(null);
  const [sheetOpen, setSheetOpen] = useState(false);
  const [pendingSource, setPendingSource] = useState<ScanSource | null>(null);
  const launching = useRef(false);
  const sheetVisible = useRef(false);
  const dismissResolver = useRef<(() => void) | null>(null);

  const openSheet = useCallback(() => {
    if (launching.current) return;
    sheetVisible.current = true;
    setSheetOpen(true);
  }, []);
  const closeSheet = useCallback(() => {
    sheetVisible.current = false;
    setSheetOpen(false);
  }, []);
  const onSheetDismiss = useCallback(() => {
    dismissResolver.current?.();
    dismissResolver.current = null;
  }, []);

  // Web: handle file input change
  const handleWebFile = useCallback((e: Event) => {
    const file = (e.target as HTMLInputElement).files?.[0];
    if (!file) return;
    const url = URL.createObjectURL(file);
    router.push({ pathname: '/scan', params: { uri: url } });
    if (inputRef.current) inputRef.current.value = '';
  }, [router]);

  const launchWebPicker = useCallback((capture: 'environment' | '') => {
    if (typeof document === 'undefined') return;
    const input = document.createElement('input');
    input.type = 'file';
    input.accept = 'image/*';
    if (capture) input.setAttribute('capture', capture);
    input.style.display = 'none';
    input.addEventListener('change', handleWebFile as EventListener);
    document.body.appendChild(input);
    input.click();
    setTimeout(() => document.body.removeChild(input), 30_000);
  }, [handleWebFile]);

  const launch = useCallback(async (source: ScanSource) => {
    if (launching.current) return;
    launching.current = true;
    const shouldWaitForDismiss = Platform.OS === 'ios' && sheetVisible.current;
    const dismissed = shouldWaitForDismiss
      ? new Promise<void>((resolve) => { dismissResolver.current = resolve; })
      : Promise.resolve();
    setPendingSource(source);
    closeSheet();

    try {
      // React Native's real onDismiss fires after UIKit has released the modal.
      await dismissed;

      // Web path — use native file/camera input
      if (Platform.OS === 'web') {
        launchWebPicker('');
        return;
      }

      // Native path — wait for sheet dismiss animation before camera
      await new Promise<void>((resolve) =>
        InteractionManager.runAfterInteractions(() => resolve())
      );

      let imageUri: string | null = null;
      let precropped = false;

      if (source === 'scan') {
        if (Platform.OS === 'ios') {
          if (!ReceiptScanner) throw new Error('Scanner-Update benötigt: Bitte installiere den aktuellen Piggy-Build.');
          const result = await ReceiptScanner.scanReceipt();
          if (result.status === 'cancel') return;
          imageUri = result.uri ?? null;
          precropped = result.precropped === true;
        } else {
          const { scannedImages, status } = await DocumentScanner.scanDocument({
            croppedImageQuality: 100,
            maxNumDocuments: 1,
          });
          if (status === 'cancel') return;
          imageUri = scannedImages?.[0] ?? null;
          precropped = true;
        }
      } else {
        const perm = await ImagePicker.requestMediaLibraryPermissionsAsync();
        if (!perm.granted) {
          Alert.alert('Kein Zugriff', 'Bitte erlaube den Zugriff auf die Mediathek in den iPhone-Einstellungen.');
          return;
        }
        const res = await ImagePicker.launchImageLibraryAsync({ mediaTypes: ['images'], quality: 0.9 });
        imageUri = res.canceled ? null : (res.assets?.[0]?.uri ?? null);
      }
      if (!imageUri) return;
      router.push({ pathname: '/scan', params: { uri: imageUri, precropped: precropped ? '1' : '0' } });
    } catch (err: unknown) {
      const msg = (err as Error)?.message ?? '';
      if (msg.includes('DocumentScanner') || msg.includes('TurboModuleRegistry')) {
        Alert.alert('EAS Build nötig', 'Der Document-Scanner funktioniert nur im nativen Build.');
      } else {
        Alert.alert('Fehler', msg || 'Scan fehlgeschlagen');
      }
      return;
    } finally {
      launching.current = false;
      setPendingSource(null);
    }
  }, [closeSheet, launchWebPicker, router]);

  return { sheetOpen, openSheet, closeSheet, onSheetDismiss, launch, pendingSource };
}
