import { useEffect, useRef } from 'react';
import { Animated, Text, StyleSheet } from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { C, R, S } from '../constants/design';

interface Props {
  message: string;
  onDone: () => void;
}

export function Toast({ message, onDone }: Props) {
  const insets   = useSafeAreaInsets();
  const opacity  = useRef(new Animated.Value(0)).current;
  const translateY = useRef(new Animated.Value(-20)).current;

  useEffect(() => {
    Animated.parallel([
      Animated.spring(translateY, { toValue: 0, useNativeDriver: true }),
      Animated.timing(opacity, { toValue: 1, duration: 200, useNativeDriver: true }),
    ]).start();

    const timer = setTimeout(() => {
      Animated.parallel([
        Animated.timing(translateY, { toValue: -20, duration: 250, useNativeDriver: true }),
        Animated.timing(opacity, { toValue: 0, duration: 250, useNativeDriver: true }),
      ]).start(onDone);
    }, 3500);

    return () => clearTimeout(timer);
  }, []);

  return (
    <Animated.View style={[styles.toast, { top: insets.top + 12, opacity, transform: [{ translateY }] }]}>
      <Text style={styles.text}>{message}</Text>
    </Animated.View>
  );
}

const styles = StyleSheet.create({
  toast: {
    position: 'absolute', left: 20, right: 20, zIndex: 999,
    backgroundColor: C.textPrimary, borderRadius: R.md,
    paddingVertical: 12, paddingHorizontal: 16,
  },
  text: { color: '#fff', fontSize: S.sm, fontWeight: '600', textAlign: 'center' },
});
