import { Ionicons, type IoniconName } from './Ionicons';

const CATEGORY_ICONS: Record<string, IoniconName> = {
  'Lebensmittel': 'cart-outline', 'Getränke': 'cafe-outline', 'Haushalt': 'home-outline',
  'Hygiene': 'water-outline', 'Gesundheit': 'medical-outline',
  'Restaurant & Take-away': 'restaurant-outline', 'Kleidung': 'shirt-outline',
  'Elektronik': 'laptop-outline', 'Backwaren': 'nutrition-outline',
  'Milchprodukte': 'nutrition-outline', 'Fleisch & Fisch': 'fish-outline',
  'Einrichtung': 'bed-outline', 'Freizeit & Shopping': 'bag-handle-outline',
  'Mobilität': 'train-outline', 'Wohnen & Nebenkosten': 'key-outline',
  'Versicherungen & Abos': 'shield-checkmark-outline', 'Diverses': 'cube-outline',
};

export function CategoryIcon({ category, size = 22, color = '#6B7280' }: { category: string; size?: number; color?: string }) {
  return <Ionicons name={CATEGORY_ICONS[category] ?? 'pricetag-outline'} size={size} color={color} />;
}
