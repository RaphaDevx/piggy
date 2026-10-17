import {
  View, Text, TouchableOpacity, Modal, Pressable, StyleSheet, Platform,
} from 'react-native';
import { BottomTabBarProps } from '@react-navigation/bottom-tabs';
import { Ionicons } from '@/components/Ionicons';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { useScanner, SCAN_SOURCES } from '../hooks/useScanner';
import { GlassSurface } from './GlassSurface';
import { semantic } from '../brand/theme';
import { C, R, S, cardShadow } from '../constants/design';

// Mapping: Route-Name → Icon (filled = aktiv, -outline = inaktiv)
const ICON_MAP: Record<string, string> = {
  index:      'home',
  quittungen: 'receipt',
  finanzen:   'bar-chart',
  profil:     'person',
};

const TAB_LABELS: Record<string, string> = {
  index:      'Home',
  quittungen: 'Quittungen',
  finanzen:   'Finanzen',
  profil:     'Profil',
};

export default function CustomTabBar({ state, navigation }: BottomTabBarProps) {
  const insets = useSafeAreaInsets();
  const { sheetOpen, openSheet, closeSheet, launch } = useScanner();

  // Nur sichtbare Tabs (ohne href:null-Screens) — jetzt ohnehin nur noch 4
  const visibleRoutes = state.routes.filter(
    (r) => ICON_MAP[r.name] !== undefined
  );
  const mid = Math.floor(visibleRoutes.length / 2); // 2 links, 2 rechts

  function renderTab(route: (typeof state.routes)[number]) {
    const globalIndex = state.routes.findIndex((r) => r.key === route.key);
    const isFocused   = state.index === globalIndex;
    const base        = ICON_MAP[route.name] ?? 'ellipse';
    const icon        = isFocused ? base : `${base}-outline`;

    return (
      <TouchableOpacity
        key={route.key}
        style={styles.tab}
        accessibilityRole="tab"
        accessibilityLabel={TAB_LABELS[route.name]}
        accessibilityState={{ selected: isFocused }}
        onLongPress={() => navigation.emit({ type: 'tabLongPress', target: route.key })}
        activeOpacity={0.7}
        onPress={() => {
          const event = navigation.emit({ type: 'tabPress', target: route.key, canPreventDefault: true });
          if (!isFocused && !event.defaultPrevented) navigation.navigate(route.name);
        }}
      >
        <Ionicons name={icon} size={24} color={isFocused ? semantic.color.text.brand : C.textSecondary} />
        <Text style={[styles.tabLabel, isFocused && styles.tabLabelActive]}>
          {TAB_LABELS[route.name] ?? route.name}
        </Text>
      </TouchableOpacity>
    );
  }

  return (
    <>
      {/* Scan-Quellen-Sheet */}
      <Modal visible={sheetOpen} transparent animationType="slide" statusBarTranslucent onRequestClose={closeSheet}>
        <Pressable style={styles.backdrop} onPress={closeSheet} accessibilityLabel="Schliessen" accessibilityRole="button" />
        <View style={[styles.sheet, { paddingBottom: Math.max(insets.bottom, 24) }]}>
          <View style={styles.handle} />
          <View style={styles.sheetHeader}>
            <TouchableOpacity accessibilityLabel="Schliessen" accessibilityRole="button" onPress={closeSheet} style={styles.sheetClose}><Ionicons name="close" size={24} color={C.textPrimary} /></TouchableOpacity>
            <Text style={styles.sheetTitle}>Quittung hinzufügen</Text>
            <Text style={styles.sheetSub}>Wähle eine Quelle</Text>
          </View>
          {SCAN_SOURCES.map((src) => (
            <TouchableOpacity key={src.key} style={styles.sourceRow} activeOpacity={0.75} onPress={() => launch(src.key)}>
              <View style={[styles.srcIcon, { backgroundColor: `${src.color}18` }]}>
                <Ionicons name={src.icon} size={22} color={src.color} />
              </View>
              <Text style={styles.sourceLabel}>{src.label}</Text>
              <Ionicons name="chevron-forward" size={16} color={C.textTertiary} />
            </TouchableOpacity>
          ))}
        </View>
      </Modal>

      {/* Navigation floats above content; the scanner remains a separate action. */}
      <View style={[styles.barContainer, { paddingBottom: Math.max(insets.bottom, 8) }]}>
      <GlassSurface style={styles.bar}>
        {visibleRoutes.slice(0, mid).map((r) => renderTab(r))}

        <TouchableOpacity style={styles.scanWrapper} activeOpacity={0.85} onPress={openSheet} accessibilityLabel="Quittung hinzufügen" accessibilityRole="button">
          <View style={styles.scanBtn}>
            <Ionicons name="scan-outline" size={28} color={C.textPrimary} />
          </View>
        </TouchableOpacity>

        {visibleRoutes.slice(mid).map((r) => renderTab(r))}
      </GlassSurface>
      </View>
    </>
  );
}

const styles = StyleSheet.create({
  barContainer: { position: 'absolute', bottom: 0, left: 0, right: 0, paddingHorizontal: 12, paddingTop: 8 },
  bar: { flexDirection: 'row', paddingVertical: 6, paddingHorizontal: 4, minHeight: 68 },
  tab:            { flex: 1, alignItems: 'center', justifyContent: 'center', paddingVertical: 6, minHeight: 52, gap: 3 },
  tabLabel:       { fontSize: S.xs, color: C.textSecondary, fontWeight: '500' },
  tabLabelActive: { color: semantic.color.text.brand, fontWeight: '700' },

  scanWrapper: { flex: 1, alignItems: 'center', justifyContent: 'center' },
  scanBtn: {
    width: 52, height: 52, borderRadius: 26,
    backgroundColor: C.gold,
    alignItems: 'center', justifyContent: 'center',
    ...cardShadow,
    ...Platform.select({
      ios: { shadowColor: C.gold, shadowOpacity: 0.45, shadowRadius: 12, shadowOffset: { width: 0, height: 4 } },
    }),
  },
  sheetClose: { position: 'absolute', right: 0, top: 0, minWidth: 44, minHeight: 44, alignItems: 'center', justifyContent: 'center' },

  backdrop: { flex: 1, backgroundColor: 'rgba(0,0,0,0.35)' },
  sheet: { backgroundColor: C.bgCard, borderTopLeftRadius: R.xxl, borderTopRightRadius: R.xxl, paddingHorizontal: 24, paddingTop: 12 },
  handle: { width: 36, height: 4, borderRadius: 2, backgroundColor: C.border, alignSelf: 'center', marginBottom: 20 },
  sheetHeader: { marginBottom: 16, paddingRight: 48 },
  sheetTitle:  { fontSize: S.xl, fontWeight: '800', color: C.textPrimary },
  sheetSub:    { fontSize: S.sm, color: C.textSecondary, marginTop: 2 },
  sourceRow:   { flexDirection: 'row', alignItems: 'center', paddingVertical: 14, gap: 14, borderBottomWidth: 1, borderBottomColor: C.borderSoft },
  sourceLabel: { flex: 1, color: C.textPrimary, fontSize: S.md, fontWeight: '600' },
  srcIcon:     { width: 46, height: 46, borderRadius: R.md, alignItems: 'center', justifyContent: 'center' },
});
