import { Platform, StyleSheet } from 'react-native';

export const C = {
  // Backgrounds
  bg:         '#FAFAF9',
  bgCard:     '#FFFFFF',
  bgSoft:     '#F5F3EF',
  bgAccent:   '#FFF5E6',
  bgPink:     '#FFF0F4',

  // Text
  textPrimary:   '#1A1A1A',
  textSecondary: '#6B7280',
  textTertiary:  '#9CA3AF',

  // Brand
  gold:       '#FF9F0A',
  goldSoft:   '#FFE4A8',
  pink:       '#FFB8C6',
  pinkSoft:   '#FFDCE6',

  // Semantic
  success:    '#34C759',
  error:      '#FF3B30',
  warning:    '#FF9F0A',

  // Borders
  border:     '#EDE9E3',
  borderSoft: '#F5F3EF',
} as const;

export const R = {
  xs: 8,
  sm: 12,
  md: 16,
  lg: 20,
  xl: 24,
  xxl: 32,
} as const;

export const S = {
  xs:   11,
  sm:   13,
  md:   15,
  lg:   17,
  xl:   22,
  xxl:  28,
  hero: 42,
} as const;

export const cardShadow = Platform.select({
  ios: {
    shadowColor: '#00000020',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 1,
    shadowRadius: 12,
  },
  android: { elevation: 3 },
  default: {
    shadowColor: '#00000015',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 1,
    shadowRadius: 8,
  },
});

export const card = StyleSheet.flatten({
  backgroundColor: C.bgCard,
  borderRadius: R.xl,
  ...cardShadow,
});
