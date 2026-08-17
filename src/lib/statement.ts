import { Platform } from 'react-native';
import * as FileSystem from 'expo-file-system';
import { supabase } from './supabase';
import type { ParsedStatement, AccountType } from '../types/bank';

async function fileToBase64(uri: string, fallbackMimeType: string): Promise<{ base64: string; mimeType: string }> {
  if (uri.startsWith('data:')) {
    const [header, data] = uri.split(',');
    const mimeType = header.match(/data:(.*?);/)?.[1] ?? fallbackMimeType;
    return { base64: data, mimeType };
  }

  if (Platform.OS === 'web' || uri.startsWith('blob:')) {
    const resp = await fetch(uri);
    const blob = await resp.blob();
    const mimeType = blob.type || fallbackMimeType;
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.onload = () => {
        const dataUrl = reader.result as string;
        resolve({ base64: dataUrl.split(',')[1], mimeType });
      };
      reader.onerror = reject;
      reader.readAsDataURL(blob);
    });
  }

  const base64 = await FileSystem.readAsStringAsync(uri, { encoding: 'base64' });
  return { base64, mimeType: fallbackMimeType };
}

/** Schickt eine Kontoauszug-Datei (PDF, CSV oder CAMT.053-XML) zur Analyse an Claude. */
export async function processStatementFile(
  uri: string,
  mimeType: string,
  accountType: AccountType
): Promise<ParsedStatement> {
  const { base64, mimeType: resolvedMime } = await fileToBase64(uri, mimeType);

  const { data, error } = await supabase.functions.invoke('process-statement', {
    body: { file_base64: base64, mime_type: resolvedMime, account_type: accountType },
  });

  if (error) throw new Error(error.message);
  if (!data?.transactions) throw new Error('Keine Buchungen erkannt');

  return data as ParsedStatement;
}
