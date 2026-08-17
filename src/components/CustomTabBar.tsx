import {
  View, Text, TouchableOpacity, Modal, Pressable, StyleSheet, Platform,
} from 'react-native';
import { BottomTabBarProps } from '@react-navigation/bottom-tabs';
import { Ionicons } from '@/components/Ionicons';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { useScanner, SCAN_SOURCES } from '../hooks/useScanner';
import { C, R, S, cardShadow } from '../constants/design';

// Mapping: Route-Name → Icon (filled = aktiv, -outline = inaktiv)
const ICON_MAP: Record<string, string> = {
  index:      'home',
  quittungen: 'receipt',
  projekte:   'folder',
  profil:     'person',
};

const TAB_LABELS: Record<string, string> = {
  index:      'Home',
  quittungen: 'Quittungen',
  projekte:   'Projekte',
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
        activeOpacity={0.7}
        onPress={() => {
          const event = navigation.emit({ type: 'tabPress', target: route.key, canPreventDefault: true });
          if (!isFocused && !event.defaultPrevented) navigation.navigate(route.name);
        }}
      >
        <Ionicons name={icon} size={24} color={isFocused ? C.gold : C.textTertiary} />
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
        <Pressable style={styles.backdrop} onPress={closeSheet} />
        <View style={[styles.sheet, { paddingBottom: Math.max(insets.bottom, 24) }]}>
          <View style={styles.handle} />
          <View style={styles.sheetHeader}>
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

      {/* Tab Bar: 2 links | 🐷 center | 2 rechts */}
      <View style={[styles.bar, { paddingBottom: insets.bottom || 8 }]}>
        {visibleRoutes.slice(0, mid).map((r) => renderTab(r))}

        <TouchableOpacity style={styles.scanWrapper} activeOpacity={0.85} onPress={openSheet}>
          <View style={styles.scanBtn}>
            <Text style={styles.scanEmoji}>🐷</Text>
          </View>
        </TouchableOpacity>

        {visibleRoutes.slice(mid).map((r) => renderTab(r))}
      </View>
    </>
  );
}

const styles = StyleSheet.create({
  bar: {
    flexDirection: 'row',
    backgroundColor: C.bgCard,
    borderTopWidth: 1,
    borderTopColor: C.border,
    paddingTop: 8,
    ...Platform.select({
      ios:     { shadowColor: '#00000015', shadowOffset: { width: 0, height: -4 }, shadowOpacity: 1, shadowRadius: 12 },
      android: { elevation: 8 },
    }),
  },
  tab:            { flex: 1, alignItems: 'center', justifyContent: 'center', paddingVertical: 4, gap: 3 },
  tabLabel:       { fontSize: S.xs, color: C.textTertiary, fontWeight: '500' },
  tabLabelActive: { color: C.gold, fontWeight: '700' },

  scanWrapper: { flex: 1, alignItems: 'center', justifyContent: 'center' },
  scanBtn: {
    width: 60, height: 60, borderRadius: 20,
    backgroundColor: C.gold,
    alignItems: 'center', justifyContent: 'center',
    marginBottom: 8,
    ...cardShadow,
    ...Platform.select({
      ios: { shadowColor: C.gold, shadowOpacity: 0.45, shadowRadius: 12, shadowOffset: { width: 0, height: 4 } },
    }),
  },
  scanEmoji: { fontSize: 28 },

  backdrop: { flex: 1, backgroundColor: 'rgba(0,0,0,0.35)' },
  sheet: { backgroundColor: C.bgCard, borderTopLeftRadius: R.xxl, borderTopRightRadius: R.xxl, paddingHorizontal: 24, paddingTop: 12 },
  handle: { width: 36, height: 4, borderRadius: 2, backgroundColor: C.border, alignSelf: 'center', marginBottom: 20 },
  sheetHeader: { marginBottom: 16 },
  sheetTitle:  { fontSize: S.xl, fontWeight: '800', color: C.textPrimary },
  sheetSub:    { fontSize: S.sm, color: C.textSecondary, marginTop: 2 },
  sourceRow:   { flexDirection: 'row', alignItems: 'center', paddingVertical: 14, gap: 14, borderBottomWidth: 1, borderBottomColor: C.borderSoft },
  sourceLabel: { flex: 1, color: C.textPrimary, fontSize: S.md, fontWeight: '600' },
  srcIcon:     { width: 46, height: 46, borderRadius: R.md, alignItems: 'center', justifyContent: 'center' },
});
