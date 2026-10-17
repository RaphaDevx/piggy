import { useEffect, useState } from 'react';
import { AccessibilityInfo, Platform, StyleSheet, View, type ViewProps } from 'react-native';
import { requireOptionalNativeModule } from 'expo-modules-core';
import type { GlassViewProps } from 'expo-glass-effect';
import type { ComponentType } from 'react';

// Older installed binaries may not include this newly added native module yet.
const nativeGlass = Platform.OS === 'ios' ? requireOptionalNativeModule<{
  isLiquidGlassAvailable: boolean;
  isGlassEffectAPIAvailable: boolean;
}>('ExpoGlassEffect') : null;
const NativeGlassView: ComponentType<GlassViewProps> | null =
  nativeGlass?.isLiquidGlassAvailable && nativeGlass.isGlassEffectAPIAvailable
    ? require('expo-glass-effect').GlassView
    : null;

/** Reserved for navigation and floating controls. Content stays on opaque surfaces. */
export function GlassSurface({ style, children, ...props }: ViewProps) {
  const [reducedTransparency, setReducedTransparency] = useState(true);
  const [highContrast, setHighContrast] = useState(true);
  useEffect(() => {
    let active = true;
    AccessibilityInfo.isReduceTransparencyEnabled().then(value => { if (active) setReducedTransparency(value); }).catch(() => {});
    AccessibilityInfo.isDarkerSystemColorsEnabled().then(value => { if (active) setHighContrast(value); }).catch(() => {});
    const transparency = AccessibilityInfo.addEventListener('reduceTransparencyChanged', setReducedTransparency);
    const contrast = AccessibilityInfo.addEventListener('darkerSystemColorsChanged', setHighContrast);
    return () => { active = false; transparency.remove(); contrast.remove(); };
  }, []);

  const showGlass = NativeGlassView && !reducedTransparency && !highContrast;
  return (
    <View {...props} style={[styles.surface, style]}>
      {showGlass && NativeGlassView
        ? <NativeGlassView pointerEvents="none" glassEffectStyle="regular" colorScheme="light" style={StyleSheet.absoluteFill} />
        : <View pointerEvents="none" style={[StyleSheet.absoluteFill, styles.opaque, highContrast && styles.contrast]} />}
      {children}
    </View>
  );
}

const styles = StyleSheet.create({
  surface: { borderRadius: 30, overflow: 'hidden' },
  opaque: { backgroundColor: '#FFFFFF', borderWidth: StyleSheet.hairlineWidth, borderColor: '#D9D3CA', borderRadius: 30 },
  contrast: { borderWidth: 1, borderColor: '#6B7280' },
});
