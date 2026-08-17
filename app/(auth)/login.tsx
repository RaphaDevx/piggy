import { useState } from 'react';
import {
  View, Text, TextInput, TouchableOpacity,
  StyleSheet, Alert, KeyboardAvoidingView, Platform,
  ActivityIndicator, ScrollView,
} from 'react-native';
import * as WebBrowser from 'expo-web-browser';
import * as AppleAuthentication from 'expo-apple-authentication';
import { Ionicons } from '@/components/Ionicons';
import { supabase } from '../../src/lib/supabase';
import { runGoogleOAuth } from '../../src/lib/googleAuth';
import { C, R, S, card } from '../../src/constants/design';

WebBrowser.maybeCompleteAuthSession();

export default function LoginScreen() {
  const [email, setEmail]           = useState('');
  const [password, setPassword]     = useState('');
  const [isSignUp, setIsSignUp]     = useState(false);
  const [loading, setLoading]       = useState(false);
  const [googleLoading, setGoogleLoading] = useState(false);
  const [appleLoading, setAppleLoading]   = useState(false);

  async function handleAuth() {
    if (!email || !password) {
      Alert.alert('Fehlende Angaben', 'Bitte E-Mail und Passwort eingeben.');
      return;
    }
    setLoading(true);
    const { error } = isSignUp
      ? await supabase.auth.signUp({ email, password })
      : await supabase.auth.signInWithPassword({ email, password });
    setLoading(false);
    if (error) Alert.alert('Fehler', error.message);
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
        if (error) Alert.alert('Fehler', error.message);
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
          <Text style={styles.formTitle}>{isSignUp ? 'Konto erstellen' : 'Willkommen zurück'}</Text>

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

          {/* Apple Button — nur auf iOS anzeigen */}
          {Platform.OS === 'ios' && (
            <TouchableOpacity
              style={styles.appleBtn}
              onPress={signInWithApple}
              disabled={appleLoading}
              activeOpacity={0.8}
            >
              {appleLoading ? (
                <ActivityIndicator color="#fff" size="small" />
              ) : (
                <>
                  <Ionicons name="logo-apple" size={20} color="#fff" />
                  <Text style={styles.appleBtnText}>Mit Apple anmelden</Text>
                </>
              )}
            </TouchableOpacity>
          )}

          {/* Divider */}
          <View style={styles.dividerRow}>
            <View style={styles.dividerLine} />
            <Text style={styles.dividerText}>oder</Text>
            <View style={styles.dividerLine} />
          </View>

          {/* Email + Password */}
          <TextInput
            style={styles.input}
            placeholder="E-Mail"
            placeholderTextColor={C.textTertiary}
            autoCapitalize="none"
            keyboardType="email-address"
            value={email}
            onChangeText={setEmail}
          />
          <TextInput
            style={styles.input}
            placeholder="Passwort"
            placeholderTextColor={C.textTertiary}
            secureTextEntry
            value={password}
            onChangeText={setPassword}
          />

          <TouchableOpacity style={styles.btn} onPress={handleAuth} disabled={loading}>
            {loading
              ? <ActivityIndicator color="#fff" />
              : <Text style={styles.btnText}>{isSignUp ? 'Registrieren' : 'Anmelden'}</Text>}
          </TouchableOpacity>
        </View>

        <TouchableOpacity onPress={() => setIsSignUp((v) => !v)} style={styles.toggle}>
          <Text style={styles.toggleText}>
            {isSignUp ? 'Bereits ein Konto? ' : 'Neu hier? '}
            <Text style={styles.toggleLink}>{isSignUp ? 'Anmelden' : 'Konto erstellen'}</Text>
          </Text>
        </TouchableOpacity>

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

  appleBtn: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    gap: 10,
    backgroundColor: '#000',
    borderRadius: R.md,
    paddingVertical: 14,
  },
  appleBtnText: { fontSize: S.md, fontWeight: '600', color: '#fff' },

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
  toggleText: { fontSize: S.sm, color: C.textSecondary },
  toggleLink: { color: C.gold, fontWeight: '700' },
});
