import { useState, useCallback, useRef } from 'react';
import { Alert, InteractionManager, Platform } from 'react-native';
import * as ImagePicker from 'expo-image-picker';
import { useRouter } from 'expo-router';
import Constants from 'expo-constants';

const isNative     = Platform.OS !== 'web';
const isExpoGo     = isNative && Constants.appOwnership === 'expo';
const hasDocScanner = isNative && !isExpoGo;

export const ALL_SCAN_SOURCES = [
  { key: 'scan'    as const, label: 'Quittung scannen', icon: 'scan-outline'   as const, color: '#FF9F0A', nativeOnly: true  },
  { key: 'camera'  as const, label: 'Kamera',           icon: 'camera-outline' as const, color: '#0A84FF', nativeOnly: false },
  { key: 'library' as const, label: 'Bild hochladen',   icon: 'images-outline' as const, color: '#30D158', nativeOnly: false },
];

export const SCAN_SOURCES = ALL_SCAN_SOURCES.filter((s) => hasDocScanner || !s.nativeOnly);

export type ScanSource = (typeof ALL_SCAN_SOURCES)[number]['key'];

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
      if (source === 'camera') {
        launchWebPicker('environment');
      } else {
        launchWebPicker('');
      }
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
        // eslint-disable-next-line @typescript-eslint/no-var-requires, @typescript-eslint/no-require-imports
        const { default: DocumentScanner } = require('react-native-document-scanner-plugin');
        const { scannedImages } = await DocumentScanner.scanDocument({ maxNumDocuments: 1 });
        imageUri = scannedImages?.[0] ?? null;
      } else if (source === 'library') {
        const perm = await ImagePicker.requestMediaLibraryPermissionsAsync();
        if (!perm.granted) {
          Alert.alert('Kein Zugriff', 'Bitte erlaube den Zugriff auf die Mediathek in den iPhone-Einstellungen.');
          return;
        }
        const res = await ImagePicker.launchImageLibraryAsync({ mediaTypes: ['images'], quality: 0.9 });
        imageUri = res.canceled ? null : (res.assets?.[0]?.uri ?? null);
      } else {
        const perm = await ImagePicker.requestCameraPermissionsAsync();
        if (!perm.granted) {
          Alert.alert('Kein Kamera-Zugriff', 'Bitte erlaube den Kamera-Zugriff in den iPhone-Einstellungen.');
          return;
        }
        const res = await ImagePicker.launchCameraAsync({ quality: 0.9, allowsEditing: false });
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
    router.push({ pathname: '/scan', params: { uri: imageUri } });
  }, [closeSheet, launchWebPicker, router]);

  return { sheetOpen, openSheet, closeSheet, launch, pendingSource };
}
