import { Image, ImageStyle, StyleProp } from 'react-native';
import { useReceiptImageUrl } from '../lib/receiptImages';

/** Zeigt ein Quittungsbild aus dem privaten Bucket (Pfad, Legacy-URL oder lokale URI). */
export function ReceiptImage({ value, style, resizeMode }: {
  value: string | null | undefined;
  style?: StyleProp<ImageStyle>;
  resizeMode?: 'contain' | 'cover';
}) {
  const uri = useReceiptImageUrl(value);
  if (!uri) return null;
  return <Image source={{ uri }} style={style} resizeMode={resizeMode} />;
}
