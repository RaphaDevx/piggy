import { Tabs } from 'expo-router';
import CustomTabBar from '../../src/components/CustomTabBar';

export default function TabLayout() {
  return (
    <Tabs
      tabBar={(props) => <CustomTabBar {...props} />}
      screenOptions={{ headerShown: false }}
    >
      <Tabs.Screen name="index"      options={{ title: 'Dashboard' }} />
      <Tabs.Screen name="quittungen" options={{ title: 'Quittungen' }} />
      <Tabs.Screen name="projekte"   options={{ title: 'Projekte' }} />
      <Tabs.Screen name="analyse"    options={{ title: 'Analyse', href: null }} />
      <Tabs.Screen name="profil"     options={{ title: 'Profil' }} />
    </Tabs>
  );
}
