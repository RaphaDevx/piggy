import { useState, useCallback, useRef } from 'react';
import { Alert, InteractionManager, Platform } from 'react-native';
import * as ImagePicker from 'expo-image-picker';
import { useRouter } from 'expo-router';
import Constants from 'expo-constants';
import DocumentScanner from 'react-native-document-scanner-plugin';

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

  const openSheet  = useCallback(() => setSheetOpen(true), []);
  const closeSheet = useCallback(() => setSheetOpen(false), []);

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
    closeSheet();
    setPendingSource(source);

    // Web path — use native file/camera input
    if (Platform.OS === 'web') {
      launchWebPicker('');
      setPendingSource(null);
      return;
    }

    // Native path — wait for sheet dismiss animation before camera
    await new Promise<void>((resolve) =>
      InteractionManager.runAfterInteractions(() => resolve())
    );

    let imageUri: string | null = null;

    try {
      if (source === 'scan') {
        const { scannedImages, status } = await DocumentScanner.scanDocument({
          croppedImageQuality: 100,
          maxNumDocuments: 1,
        });
        if (status === 'cancel') return;
        imageUri = scannedImages?.[0] ?? null;
      } else {
        const perm = await ImagePicker.requestMediaLibraryPermissionsAsync();
        if (!perm.granted) {
          Alert.alert('Kein Zugriff', 'Bitte erlaube den Zugriff auf die Mediathek in den iPhone-Einstellungen.');
          return;
        }
        const res = await ImagePicker.launchImageLibraryAsync({ mediaTypes: ['images'], quality: 0.9 });
        imageUri = res.canceled ? null : (res.assets?.[0]?.uri ?? null);
      }
    } catch (err: unknown) {
      const msg = (err as Error)?.message ?? '';
      if (msg.includes('DocumentScanner') || msg.includes('TurboModuleRegistry')) {
        Alert.alert('EAS Build nötig', 'Der Document-Scanner funktioniert nur im nativen Build.');
      } else {
        Alert.alert('Fehler', msg || 'Scan fehlgeschlagen');
      }
      return;
    } finally {
      setPendingSource(null);
    }

    if (!imageUri) return;
    // DocumentScanner already crops + corrects perspective → skip manual crop overlay
    router.push({ pathname: '/scan', params: { uri: imageUri, precropped: source === 'scan' ? '1' : '0' } });
  }, [closeSheet, launchWebPicker, router]);

  return { sheetOpen, openSheet, closeSheet, launch, pendingSource };
}
