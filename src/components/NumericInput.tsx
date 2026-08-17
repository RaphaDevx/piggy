import { useState, useRef, useEffect } from 'react';
import { TextInput } from 'react-native';

interface Props {
  value: number;
  onChange: (n: number) => void;
  style?: object;
  placeholder?: string;
  placeholderTextColor?: string;
}

/**
 * TextInput für Zahlen: akzeptiert Komma als Dezimaltrennzeichen (12,50 → 12.50),
 * verhindert das Reset-Problem bei Zwischenzuständen wie "12," oder "0.".
 */
export default function NumericInput({ value, onChange, style, placeholder, placeholderTextColor }: Props) {
  const [str, setStr]   = useState(() => (value !== 0 ? String(value) : ''));
  const lastNum         = useRef(value);

  // Sync wenn Wert extern geändert wird (z.B. nach KI-Erkennung)
  useEffect(() => {
    if (Math.abs(lastNum.current - value) > 0.0001) {
      const cur = parseFloat(str.replace(',', '.'));
      if (isNaN(cur) || Math.abs(cur - value) > 0.0001) {
        setStr(value !== 0 ? String(value) : '');
      }
      lastNum.current = value;
    }
  }, [value]); // eslint-disable-line react-hooks/exhaustive-deps

  return (
    <TextInput
      value={str}
      onChangeText={(v) => {
        setStr(v);
        const n = parseFloat(v.replace(',', '.'));
        if (!isNaN(n)) {
          lastNum.current = n;
          onChange(n);
        } else if (v === '' || v === ',' || v === '.') {
          lastNum.current = 0;
          onChange(0);
        }
      }}
      keyboardType="decimal-pad"
      style={style}
      placeholder={placeholder}
      placeholderTextColor={placeholderTextColor}
    />
  );
}
