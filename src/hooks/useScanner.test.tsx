import React from 'react';
import Renderer, { act } from 'react-test-renderer';
import { Alert, InteractionManager } from 'react-native';
import { useScanner } from './useScanner';
import DocumentScanner from 'react-native-document-scanner-plugin';
import * as ImagePicker from 'expo-image-picker';

const mockPush = jest.fn();
jest.mock('expo-router', () => ({ useRouter: () => ({ push: mockPush }) }));
jest.mock('expo-constants', () => ({ __esModule: true, default: { appOwnership: 'standalone' } }));
jest.mock('react-native-document-scanner-plugin', () => ({ __esModule: true, default: { scanDocument: jest.fn() } }));
jest.mock('expo-image-picker', () => ({
  requestMediaLibraryPermissionsAsync: jest.fn(), launchImageLibraryAsync: jest.fn(),
}));

let scanner: ReturnType<typeof useScanner>;
let root: Renderer.ReactTestRenderer;
function Harness() { scanner = useScanner(); return null; }
const scan = DocumentScanner.scanDocument as jest.Mock;

beforeEach(async () => {
  jest.clearAllMocks();
  jest.spyOn(InteractionManager, 'runAfterInteractions').mockImplementation((callback) => {
    if (typeof callback === 'function') callback();
    return { then: jest.fn(), done: jest.fn(), cancel: jest.fn() };
  });
  jest.spyOn(Alert, 'alert').mockImplementation(() => {});
  await act(async () => { root = Renderer.create(<Harness />); });
});
afterEach(async () => { await act(async () => { root.unmount(); }); jest.restoreAllMocks(); });

test('waits for real sheet dismissal and rejects a second launch while capture is pending', async () => {
  let complete!: (value: object) => void;
  scan.mockImplementation(() => new Promise((resolve) => { complete = resolve; }));
  act(() => scanner.openSheet());
  let first!: Promise<void>;
  act(() => { first = scanner.launch('scan'); });
  expect(scan).not.toHaveBeenCalled();
  await act(async () => { await scanner.launch('scan'); scanner.onSheetDismiss(); });
  expect(scan).toHaveBeenCalledTimes(1);
  expect(mockPush).not.toHaveBeenCalled();
  await act(async () => { complete({ status: 'success', scannedImages: ['file:///auto.jpg'] }); await first; });
  expect(mockPush).toHaveBeenCalledWith({ pathname: '/scan', params: { uri: 'file:///auto.jpg', precropped: '1' } });
  expect(scanner.pendingSource).toBeNull();
});

test('confirmed native capture reaches the review route once', async () => {
  scan.mockResolvedValue({ status: 'success', scannedImages: ['file:///receipt.jpg'] });
  await act(async () => { await scanner.launch('scan'); });
  expect(mockPush).toHaveBeenCalledTimes(1);
  expect(mockPush.mock.calls[0][0].params.precropped).toBe('1');
});

test('an empty native result never navigates to OCR', async () => {
  scan.mockResolvedValue({ status: 'success', scannedImages: [] });
  await act(async () => { await scanner.launch('scan'); });
  expect(mockPush).not.toHaveBeenCalled();
  expect(scanner.pendingSource).toBeNull();
});

test('cancel never starts OCR or navigation and allows another scan', async () => {
  scan.mockResolvedValue({ status: 'cancel' });
  await act(async () => { await scanner.launch('scan'); await scanner.launch('scan'); });
  expect(scan).toHaveBeenCalledTimes(2);
  expect(mockPush).not.toHaveBeenCalled();
  expect(scanner.pendingSource).toBeNull();
});

test('native failure releases busy state and allows retry', async () => {
  scan.mockRejectedValueOnce(new Error('Camera unavailable')).mockResolvedValueOnce({ status: 'cancel' });
  await act(async () => { await scanner.launch('scan'); await scanner.launch('scan'); });
  expect(Alert.alert).toHaveBeenCalledWith('Fehler', 'Camera unavailable');
  expect(scan).toHaveBeenCalledTimes(2);
  expect(scanner.pendingSource).toBeNull();
});

test('library permission rejection does not start scanner or navigation', async () => {
  (ImagePicker.requestMediaLibraryPermissionsAsync as jest.Mock).mockResolvedValue({ granted: false });
  await act(async () => { await scanner.launch('library'); });
  expect(scan).not.toHaveBeenCalled();
  expect(mockPush).not.toHaveBeenCalled();
  expect(scanner.pendingSource).toBeNull();
});
