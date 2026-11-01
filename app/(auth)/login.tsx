import { useState } from 'react';
import {
  View, Text, TextInput, TouchableOpacity,
  StyleSheet, Alert, KeyboardAvoidingView, Platform,
  ActivityIndicator, ScrollView, Linking,
} from 'react-native';
import * as WebBrowser from 'expo-web-browser';
import * as AppleAuthentication from 'expo-apple-authentication';
import { Ionicons } from '@/components/Ionicons';
import { supabase } from '../../src/lib/supabase';
import { runGoogleOAuth } from '../../src/lib/googleAuth';
import { PRIVACY_URL, SUPPORT_URL } from '../../src/lib/legalLinks';
import { C, R, S, card } from '../../src/constants/design';

WebBrowser.maybeCompleteAuthSession();

export default function LoginScreen() {
  const [email, setEmail]           = useState('');
  const [password, setPassword]     = useState('');
  const [showEmail, setShowEmail]   = useState(false);
  const [loading, setLoading]       = useState(false);
  const [googleLoading, setGoogleLoading] = useState(false);
  const [appleLoading, setAppleLoading]   = useState(false);

  // Nur Anmeldung bestehender Konten; neue Konten entstehen über Apple/Google.
  async function handleAuth() {
    if (!email || !password) {
      Alert.alert('Fehlende Angaben', 'Bitte E-Mail und Passwort eingeben.');
      return;
    }
    setLoading(true);
    const { error } = await supabase.auth.signInWithPassword({ email: email.trim(), password });
    setLoading(false);
    if (error) {
      const invalid = error.message.toLowerCase().includes('invalid login credentials');
      Alert.alert('Anmeldung fehlgeschlagen', invalid ? 'E-Mail oder Passwort ist falsch.' : 'Die Anmeldung ist fehlgeschlagen. Bitte versuche es erneut.');
    }
  }

  async function signInWithGoogle() {
    setGoogleLoading(true);
    try {
      const { error } = await runGoogleOAuth('signin');
      if (error) Alert.alert('Fehler', error);
    } finally {
      setGoogleLoading(false);
    }
  }

  async function signInWithApple() {
    setAppleLoading(true);
    try {
      const credential = await AppleAuthentication.signInAsync({
        requestedScopes: [
          AppleAuthentication.AppleAuthenticationScope.FULL_NAME,
          AppleAuthentication.AppleAuthenticationScope.EMAIL,
        ],
      });
      if (credential.identityToken) {
        const { error } = await supabase.auth.signInWithIdToken({
          provider: 'apple',
          token: credential.identityToken,
        });
        if (error) {
          if (error.message.toLowerCase().includes('not enabled') || error.message.toLowerCase().includes('provider')) {
            Alert.alert(
              'Apple Sign-In nicht aktiviert',
              'Apple-Anmeldung wird noch eingerichtet. Bitte versuche es später erneut oder verwende Google.',
            );
          } else {
            Alert.alert('Fehler', error.message);
          }
        }
      }
    } catch (e: any) {
      if (e.code !== 'ERR_REQUEST_CANCELED') {
        Alert.alert('Fehler', e.message ?? 'Apple Sign-In fehlgeschlagen');
      }
    } finally {
      setAppleLoading(false);
    }
  }

  return (
    <KeyboardAvoidingView
      style={styles.root}
      behavior={Platform.OS === 'ios' ? 'padding' : undefined}
    >
      <ScrollView contentContainerStyle={styles.scroll} keyboardShouldPersistTaps="handled">

        {/* Hero */}
        <View style={styles.hero}>
          <Text style={styles.pig}>🐷</Text>
          <Text style={styles.title}>Piggy</Text>
          <Text style={styles.subtitle}>Dein smarter Ausgaben-Tracker</Text>
        </View>

        {/* Card */}
        <View style={styles.formCard}>
          <Text style={styles.formTitle}>Willkommen</Text>

          {/* Apple Button — nur auf iOS anzeigen (HIG: System-Button) */}
          {Platform.OS === 'ios' && (
            appleLoading ? (
              <View style={styles.appleLoading}>
                <ActivityIndicator color="#fff" size="small" />
              </View>
            ) : (
              <AppleAuthentication.AppleAuthenticationButton
                buttonType={AppleAuthentication.AppleAuthenticationButtonType.SIGN_IN}
                buttonStyle={AppleAuthentication.AppleAuthenticationButtonStyle.BLACK}
                cornerRadius={R.md}
                style={styles.appleBtn}
                onPress={signInWithApple}
              />
            )
          )}

          {/* Google Button */}
          <TouchableOpacity
            style={styles.googleBtn}
            onPress={signInWithGoogle}
            disabled={googleLoading}
            activeOpacity={0.8}
          >
            {googleLoading ? (
              <ActivityIndicator color={C.textPrimary} size="small" />
            ) : (
              <>
                <Ionicons name="logo-google" size={20} color="#EA4335" />
                <Text style={styles.googleBtnText}>Mit Google anmelden</Text>
              </>
            )}
          </TouchableOpacity>

          {/* E-Mail-Anmeldung (nur bestehende Konten) */}
          {!showEmail ? (
            <TouchableOpacity onPress={() => setShowEmail(true)} style={styles.toggle}>
              <Text style={styles.toggleLink}>Mit E-Mail anmelden</Text>
            </TouchableOpacity>
          ) : (
            <>
              <View style={styles.dividerRow}>
                <View style={styles.dividerLine} />
                <Text style={styles.dividerText}>E-Mail</Text>
                <View style={styles.dividerLine} />
              </View>
              <TextInput
                style={styles.input}
                placeholder="E-Mail"
                placeholderTextColor={C.textTertiary}
                autoCapitalize="none"
                autoComplete="email"
                keyboardType="email-address"
                value={email}
                onChangeText={setEmail}
              />
              <TextInput
                style={styles.input}
                placeholder="Passwort"
                placeholderTextColor={C.textTertiary}
                secureTextEntry
                autoComplete="current-password"
                value={password}
                onChangeText={setPassword}
              />
              <TouchableOpacity style={styles.btn} onPress={handleAuth} disabled={loading}>
                {loading
                  ? <ActivityIndicator color="#fff" />
                  : <Text style={styles.btnText}>Anmelden</Text>}
              </TouchableOpacity>
            </>
          )}
        </View>

        {/* Rechtliches */}
        <View style={styles.legalRow}>
          <TouchableOpacity onPress={() => Linking.openURL(PRIVACY_URL)}>
            <Text style={styles.legalText}>Datenschutz</Text>
          </TouchableOpacity>
          <Text style={styles.legalText}>·</Text>
          <TouchableOpacity onPress={() => Linking.openURL(SUPPORT_URL)}>
            <Text style={styles.legalText}>Support</Text>
          </TouchableOpacity>
        </View>

      </ScrollView>
    </KeyboardAvoidingView>
  );
}

const styles = StyleSheet.create({
  root:   { flex: 1, backgroundColor: C.bg },
  scroll: { flexGrow: 1, justifyContent: 'center', padding: 24, gap: 20 },

  hero:     { alignItems: 'center', gap: 8, marginBottom: 8 },
  pig:      { fontSize: 72 },
  title:    { fontSize: S.hero, fontWeight: '800', color: C.textPrimary, letterSpacing: -1 },
  subtitle: { fontSize: S.md, color: C.textSecondary },

  formCard:  { ...card, padding: 24, gap: 14 },
  formTitle: { fontSize: S.lg, fontWeight: '700', color: C.textPrimary, marginBottom: 4 },

  googleBtn: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    gap: 10,
    backgroundColor: C.bgSoft,
    borderRadius: R.md,
    paddingVertical: 14,
    borderWidth: 1,
    borderColor: C.border,
  },
  googleBtnText: { fontSize: S.md, fontWeight: '600', color: C.textPrimary },

  appleBtn: { height: 48 },
  appleLoading: {
    height: 48,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#000',
    borderRadius: R.md,
  },

  dividerRow: { flexDirection: 'row', alignItems: 'center', gap: 10 },
  dividerLine: { flex: 1, height: 1, backgroundColor: C.border },
  dividerText: { fontSize: S.xs, color: C.textTertiary, fontWeight: '600' },

  input: {
    backgroundColor: C.bgSoft,
    color: C.textPrimary,
    borderRadius: R.md,
    paddingHorizontal: 16,
    paddingVertical: 14,
    fontSize: S.md,
    borderWidth: 1,
    borderColor: C.border,
  },

  btn: {
    backgroundColor: C.gold,
    borderRadius: R.md,
    paddingVertical: 16,
    alignItems: 'center',
    marginTop: 4,
  },
  btnText: { color: '#fff', fontSize: S.lg, fontWeight: '700' },

  toggle:     { alignItems: 'center' },
  toggleLink: { fontSize: S.sm, color: C.textSecondary, textDecorationLine: 'underline' },

  legalRow:  { flexDirection: 'row', justifyContent: 'center', gap: 10 },
  legalText: { fontSize: S.xs, color: C.textTertiary },
});
