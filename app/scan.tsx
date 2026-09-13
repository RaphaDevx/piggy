import { useState, useCallback, useRef, useEffect } from 'react';
import {
  View, Text, Image, ScrollView, TextInput, TouchableOpacity,
  StyleSheet, Alert, ActivityIndicator, Platform, KeyboardAvoidingView,
  Dimensions,
} from 'react-native';
import { useLocalSearchParams, useRouter } from 'expo-router';
import {
  GestureHandlerRootView,
  PanGestureHandler,
  PanGestureHandlerGestureEvent,
} from 'react-native-gesture-handler';
import Svg, { Line, Rect } from 'react-native-svg';
import { Ionicons } from '@/components/Ionicons';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import * as FileSystem from 'expo-file-system/legacy';
import { supabase } from '../src/lib/supabase';
import { processReceiptImage, ProcessResult } from '../src/lib/claude';
import { generateMarkdown } from '../src/lib/markdown';
import { getTagColor, ALL_TAGS } from '../src/lib/categories';
import { C, R, S, card } from '../src/constants/design';
import type { ParsedReceipt, ParsedReceiptItem } from '../src/types/receipt';
import type { CropRegion } from '../src/lib/ocr';

// ── NumericInput ────────────────────────────────────────────────────────────
interface NumericInputProps {
  value: number;
  onChange: (n: number) => void;
  style?: object;
  placeholder?: string;
  placeholderTextColor?: string;
}
function NumericInput({ value, onChange, style, placeholder, placeholderTextColor }: NumericInputProps) {
  const [str, setStr]   = useState(() => (value !== 0 ? String(value) : ''));
  const lastNum         = useRef(value);

  useEffect(() => {
    if (Math.abs(lastNum.current - value) > 0.0001) {
      const display = parseFloat(str.replace(',', '.'));
      if (isNaN(display) || Math.abs(display - value) > 0.0001) {
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

// ── CurrencyPicker ───────────────────────────────────────────────────────────
const CURRENCIES = ['CHF', 'EUR', 'USD'];

function CurrencyPicker({ value, onChange }: { value: string; onChange: (c: string) => void }) {
  return (
    <View style={cpStyles.row}>
      {CURRENCIES.map((c) => (
        <TouchableOpacity
          key={c}
          style={[cpStyles.btn, value === c && cpStyles.btnActive]}
          onPress={() => onChange(c)}
        >
          <Text style={[cpStyles.text, value === c && cpStyles.textActive]}>{c}</Text>
        </TouchableOpacity>
      ))}
    </View>
  );
}
const cpStyles = StyleSheet.create({
  row:        { flexDirection: 'row', gap: 6 },
  btn:        { flex: 1, alignItems: 'center', paddingVertical: 8, borderRadius: 10, backgroundColor: '#F5F3EF', borderWidth: 1, borderColor: '#EDE9E3' },
  btnActive:  { backgroundColor: '#FF9F0A', borderColor: '#FF9F0A' },
  text:       { fontSize: 12, fontWeight: '700', color: '#6B7280' },
  textActive: { color: '#fff' },
});

// ─────────────────────────────────────────────────────────────────────────────

function decodeBase64(base64: string): Uint8Array {
  const binaryStr = globalThis.atob(base64);
  const bytes = new Uint8Array(binaryStr.length);
  for (let i = 0; i < binaryStr.length; i++) bytes[i] = binaryStr.charCodeAt(i);
  return bytes;
}

// ── CropOverlay ──────────────────────────────────────────────────────────────

interface Corner { x: number; y: number }

const HANDLE_SIZE  = 28;
const SCREEN_W     = Dimensions.get('window').width;
const SCREEN_H     = Dimensions.get('window').height;

function defaultCorners(w: number, h: number): [Corner, Corner, Corner, Corner] {
  const pad = Math.min(w, h) * 0.08;
  return [
    { x: pad,     y: pad },       // top-left
    { x: w - pad, y: pad },       // top-right
    { x: w - pad, y: h - pad },   // bottom-right
    { x: pad,     y: h - pad },   // bottom-left
  ];
}

interface CropOverlayProps {
  imageUri:  string;
  imageW:    number;
  imageH:    number;
  onConfirm: (crop: CropRegion | null) => void;
  onRetake:  () => void;
}

function CropOverlay({ imageUri, imageW, imageH, onConfirm, onRetake }: CropOverlayProps) {
  const insets = useSafeAreaInsets();

  // Display area (full screen minus nav area)
  const displayW = SCREEN_W;
  const displayH = SCREEN_H - insets.top - 120; // minus nav + footer

  // Fit image into display area (maintain aspect ratio)
  const imageAspect   = imageH > 0 ? imageW / imageH : 1;
  const displayAspect = displayW / displayH;
  let renderW: number, renderH: number, offsetX: number, offsetY: number;
  if (imageAspect > displayAspect) {
    renderW = displayW;
    renderH = displayW / imageAspect;
    offsetX = 0;
    offsetY = (displayH - renderH) / 2;
  } else {
    renderH = displayH;
    renderW = displayH * imageAspect;
    offsetX = (displayW - renderW) / 2;
    offsetY = 0;
  }

  const [corners, setCorners] = useState<[Corner, Corner, Corner, Corner]>(() =>
    defaultCorners(renderW, renderH).map((c) => ({
      x: c.x + offsetX,
      y: c.y + offsetY,
    })) as [Corner, Corner, Corner, Corner]
  );

  // Track drag offset per corner
  const dragStart = useRef<Corner>({ x: 0, y: 0 });

  function makeHandler(idx: 0 | 1 | 2 | 3) {
    return {
      onGestureEvent: (e: PanGestureHandlerGestureEvent) => {
        const { translationX, translationY } = e.nativeEvent;
        setCorners((prev) => {
          const next = [...prev] as [Corner, Corner, Corner, Corner];
          next[idx] = {
            x: Math.max(offsetX, Math.min(offsetX + renderW, dragStart.current.x + translationX)),
            y: Math.max(offsetY, Math.min(offsetY + renderH, dragStart.current.y + translationY)),
          };
          return next;
        });
      },
      onHandlerStateChange: (e: PanGestureHandlerGestureEvent) => {
        // Save position at gesture start
        if (e.nativeEvent.state === 2 /* ACTIVE */) {
          dragStart.current = { ...corners[idx] };
        }
      },
    };
  }

  const [h0, h1, h2, h3] = [makeHandler(0), makeHandler(1), makeHandler(2), makeHandler(3)];

  function handleConfirm() {
    // Convert display-space corners to image-space crop rect
    const minX = Math.min(corners[0].x, corners[1].x, corners[2].x, corners[3].x) - offsetX;
    const minY = Math.min(corners[0].y, corners[1].y, corners[2].y, corners[3].y) - offsetY;
    const maxX = Math.max(corners[0].x, corners[1].x, corners[2].x, corners[3].x) - offsetX;
    const maxY = Math.max(corners[0].y, corners[1].y, corners[2].y, corners[3].y) - offsetY;

    const scaleX = imageW / renderW;
    const scaleY = imageH / renderH;

    const crop: CropRegion = {
      originX: Math.max(0, Math.round(minX * scaleX)),
      originY: Math.max(0, Math.round(minY * scaleY)),
      width:   Math.min(imageW, Math.round((maxX - minX) * scaleX)),
      height:  Math.min(imageH, Math.round((maxY - minY) * scaleY)),
    };

    // If crop is nearly the full image, skip cropping
    const skipCrop = crop.width > imageW * 0.95 && crop.height > imageH * 0.95;
    onConfirm(skipCrop ? null : crop);
  }

  const [tl, tr, br, bl] = corners;

  return (
    <View style={{ flex: 1, backgroundColor: '#000' }}>
      {/* Full-screen image */}
      <View style={{ width: displayW, height: displayH + insets.top, position: 'relative' }}>
        <Image
          source={{ uri: imageUri }}
          style={{ position: 'absolute', left: offsetX, top: offsetY + insets.top, width: renderW, height: renderH }}
          resizeMode="contain"
        />

        {/* Rule-of-thirds grid + crop frame overlay */}
        <Svg
          style={{ position: 'absolute', top: 0, left: 0, width: displayW, height: displayH + insets.top }}
        >
          {/* Darkened outside area hint — simple semi-transparent rect */}
          {/* Rule-of-thirds lines (30% opacity white) */}
          {[1, 2].map((i) => (
            <Line
              key={`h${i}`}
              x1={offsetX}              y1={offsetY + insets.top + (renderH / 3) * i}
              x2={offsetX + renderW}   y2={offsetY + insets.top + (renderH / 3) * i}
              stroke="rgba(255,255,255,0.3)" strokeWidth={1}
            />
          ))}
          {[1, 2].map((i) => (
            <Line
              key={`v${i}`}
              x1={offsetX + (renderW / 3) * i} y1={offsetY + insets.top}
              x2={offsetX + (renderW / 3) * i} y2={offsetY + insets.top + renderH}
              stroke="rgba(255,255,255,0.3)" strokeWidth={1}
            />
          ))}

          {/* Crop frame rectangle */}
          <Rect
            x={Math.min(tl.x, tr.x, br.x, bl.x)}
            y={Math.min(tl.y, tr.y, br.y, bl.y) + insets.top}
            width={Math.max(tl.x, tr.x, br.x, bl.x) - Math.min(tl.x, tr.x, br.x, bl.x)}
            height={Math.max(tl.y, tr.y, br.y, bl.y) - Math.min(tl.y, tr.y, br.y, bl.y)}
            fill="none"
            stroke="rgba(255,255,255,0.9)"
            strokeWidth={2}
          />

          {/* Crop frame edge lines connecting corners */}
          <Line x1={tl.x} y1={tl.y + insets.top} x2={tr.x} y2={tr.y + insets.top} stroke="white" strokeWidth={2} />
          <Line x1={tr.x} y1={tr.y + insets.top} x2={br.x} y2={br.y + insets.top} stroke="white" strokeWidth={2} />
          <Line x1={br.x} y1={br.y + insets.top} x2={bl.x} y2={bl.y + insets.top} stroke="white" strokeWidth={2} />
          <Line x1={bl.x} y1={bl.y + insets.top} x2={tl.x} y2={tl.y + insets.top} stroke="white" strokeWidth={2} />
        </Svg>

        {/* Drag handles (4 corners) */}
        {([tl, tr, br, bl] as Corner[]).map((corner, i) => {
          const handler = [h0, h1, h2, h3][i];
          return (
            <PanGestureHandler key={i} onGestureEvent={handler.onGestureEvent} onHandlerStateChange={handler.onHandlerStateChange}>
              <View
                style={[
                  cropStyles.handle,
                  {
                    left: corner.x - HANDLE_SIZE / 2,
                    top:  corner.y - HANDLE_SIZE / 2 + insets.top,
                  },
                ]}
              />
            </PanGestureHandler>
          );
        })}
      </View>

      {/* Footer */}
      <View style={[cropStyles.footer, { paddingBottom: insets.bottom + 16 }]}>
        <TouchableOpacity style={cropStyles.retakeBtn} onPress={onRetake}>
          <Ionicons name="camera-outline" size={20} color="#fff" />
          <Text style={cropStyles.retakeBtnText}>Neu aufnehmen</Text>
        </TouchableOpacity>
        <TouchableOpacity style={cropStyles.confirmBtn} onPress={handleConfirm}>
          <Text style={cropStyles.confirmBtnEmoji}>🐷</Text>
          <Text style={cropStyles.confirmBtnText}>Scannen</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const cropStyles = StyleSheet.create({
  handle: {
    position:        'absolute',
    width:           HANDLE_SIZE,
    height:          HANDLE_SIZE,
    borderRadius:    HANDLE_SIZE / 2,
    backgroundColor: 'white',
    shadowColor:     '#000',
    shadowOffset:    { width: 0, height: 2 },
    shadowOpacity:   0.5,
    shadowRadius:    4,
    elevation:       6,
  },
  footer: {
    flexDirection:   'row',
    gap:             12,
    paddingHorizontal: 20,
    paddingTop:      16,
    backgroundColor: '#111',
    alignItems:      'center',
  },
  retakeBtn: {
    flexDirection:  'row',
    alignItems:     'center',
    gap:            8,
    paddingVertical: 14,
    paddingHorizontal: 20,
    borderRadius:   R.lg,
    borderWidth:    1,
    borderColor:    'rgba(255,255,255,0.3)',
  },
  retakeBtnText: { color: '#fff', fontSize: S.md, fontWeight: '600' },
  confirmBtn: {
    flex:           1,
    flexDirection:  'row',
    alignItems:     'center',
    justifyContent: 'center',
    gap:            10,
    backgroundColor: C.gold,
    borderRadius:   R.lg,
    paddingVertical: 14,
  },
  confirmBtnEmoji: { fontSize: 20 },
  confirmBtnText:  { color: '#fff', fontSize: S.lg, fontWeight: '700' },
});

// ── Hauptscreen ──────────────────────────────────────────────────────────────

type Step = 'crop' | 'processing' | 'review' | 'saving';

export default function ScanScreen() {
  const { uri, precropped } = useLocalSearchParams<{ uri: string; precropped?: string }>();
  const router   = useRouter();
  const insets   = useSafeAreaInsets();

  const [step, setStep]         = useState<Step>(precropped === '1' ? 'processing' : 'crop');
  const [receipt, setReceipt]   = useState<ParsedReceipt | null>(null);
  const [markdown, setMarkdown] = useState('');
  const [errorMsg, setErrorMsg] = useState('');

  // Bildgrösse für Crop-Berechnungen
  const [imageSize, setImageSize] = useState({ width: SCREEN_W, height: SCREEN_H });

  useEffect(() => {
    if (uri) {
      Image.getSize(
        uri,
        (w, h) => setImageSize({ width: w, height: h }),
        () => {}
      );
    }
  }, [uri]);

  // DocumentScanner already cropped — start processing immediately
  useEffect(() => {
    if (precropped === '1' && uri) {
      startProcessing(null);
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const startProcessing = useCallback(async (cropRegion: CropRegion | null) => {
    if (!uri) return;
    setStep('processing');
    setErrorMsg('');
    try {
      const result: ProcessResult = await processReceiptImage(uri, cropRegion ?? undefined);

      if (result.type === 'queued') {
        // Edge-Queue-Pfad: zur Quittungen-Liste navigieren, Monitor zeigt Fortschritt
        router.replace('/(tabs)/quittungen' as any);
        return;
      }

      // done-Pfad: direkt zum Review-Screen
      setReceipt(result.receipt);
      setMarkdown(result.markdown);
      setStep('review');
    } catch (err: unknown) {
      const msg = (err as Error)?.message ?? '';
      setErrorMsg(msg || 'Verarbeitung fehlgeschlagen');
      setStep('crop');
    }
  }, [uri, router]);

  const saveReceipt = useCallback(async () => {
    if (!receipt) return;
    setStep('saving');

    const { data: userData } = await supabase.auth.getUser();
    const userId = userData.user?.id;
    if (!userId) { setStep('review'); return; }

    let imageUrl: string | null = null;
    try {
      if (Platform.OS !== 'web' && uri) {
        const ext  = uri.toLowerCase().endsWith('.png') ? 'png' : 'jpg';
        const path = `${userId}/${Date.now()}.${ext}`;
        const base64 = await FileSystem.readAsStringAsync(uri, { encoding: 'base64' });
        const fileData = decodeBase64(base64);
        const { error: uploadError } = await supabase.storage
          .from('receipt-images')
          .upload(path, fileData, { contentType: `image/${ext}` });
        if (!uploadError) {
          const { data: urlData } = supabase.storage.from('receipt-images').getPublicUrl(path);
          imageUrl = urlData?.publicUrl ?? null;
        }
      } else if (Platform.OS === 'web' && uri?.startsWith('blob:')) {
        const resp = await fetch(uri);
        const blob = await resp.blob();
        const ext  = blob.type === 'image/png' ? 'png' : 'jpg';
        const path = `${userId}/${Date.now()}.${ext}`;
        const { error: uploadError } = await supabase.storage
          .from('receipt-images')
          .upload(path, blob, { contentType: blob.type });
        if (!uploadError) {
          const { data: urlData } = supabase.storage.from('receipt-images').getPublicUrl(path);
          imageUrl = urlData?.publicUrl ?? null;
        }
      }
    } catch {
      // non-critical
    }

    const { data: receiptRow, error: receiptError } = await supabase
      .from('receipts')
      .insert({
        user_id:          userId,
        receipt_date:     receipt.date,
        store_name:       receipt.store_name,
        store_category:   receipt.store_category,
        total_amount:     receipt.total_amount,
        currency:         receipt.currency,
        payment_method:   receipt.payment_method,
        payment_card:     receipt.payment_card,
        image_url:        imageUrl,
        markdown_content: generateMarkdown(receipt),
      })
      .select('id')
      .single();

    if (receiptError || !receiptRow) {
      Alert.alert('Fehler', receiptError?.message ?? 'Speichern fehlgeschlagen');
      setStep('review');
      return;
    }

    if (receipt.items.length > 0) {
      await supabase.from('receipt_items').insert(
        receipt.items.map((item) => ({
          receipt_id:  receiptRow.id,
          name:        item.name,
          quantity:    item.quantity,
          unit:        item.unit,
          unit_price:  item.unit_price,
          total_price: item.total_price,
          tags:        item.tags,
        }))
      );
    }

    router.replace({ pathname: '/receipt/[id]', params: { id: receiptRow.id } });
  }, [receipt, uri, router]);

  function updateItem(index: number, patch: Partial<ParsedReceiptItem>) {
    setReceipt((prev) => {
      if (!prev) return prev;
      const items = [...prev.items];
      items[index] = { ...items[index], ...patch };
      return { ...prev, items };
    });
  }

  function removeItem(index: number) {
    setReceipt((prev) => {
      if (!prev) return prev;
      return { ...prev, items: prev.items.filter((_, i) => i !== index) };
    });
  }

  function toggleTag(index: number, tag: string) {
    setReceipt((prev) => {
      if (!prev) return prev;
      const items = [...prev.items];
      const tags = items[index].tags.includes(tag)
        ? items[index].tags.filter((t) => t !== tag)
        : [...items[index].tags, tag];
      items[index] = { ...items[index], tags };
      return { ...prev, items };
    });
  }

  if (!uri) {
    return (
      <View style={[styles.center, { paddingTop: insets.top }]}>
        <Text style={styles.emptyPig}>🐷</Text>
        <Text style={styles.errorText}>Kein Bild übergeben.</Text>
        <TouchableOpacity onPress={() => router.back()} style={styles.backLink}>
          <Text style={styles.backLinkText}>Zurück</Text>
        </TouchableOpacity>
      </View>
    );
  }

  if (step === 'processing') {
    return (
      <View style={[styles.center, { paddingTop: insets.top }]}>
        <Text style={styles.processingPig}>🐷</Text>
        <ActivityIndicator size="large" color={C.gold} style={{ marginTop: 8 }} />
        <Text style={styles.processingText}>Piggy liest deine Quittung…</Text>
        <Text style={styles.processingHint}>Bild wird hochgeladen &amp; in die Warteschlange eingereiht</Text>
      </View>
    );
  }

  if (step === 'saving') {
    return (
      <View style={[styles.center, { paddingTop: insets.top }]}>
        <Text style={styles.processingPig}>🐷</Text>
        <ActivityIndicator size="large" color={C.gold} style={{ marginTop: 8 }} />
        <Text style={styles.processingText}>Wird gespeichert…</Text>
      </View>
    );
  }

  if (step === 'crop') {
    return (
      <GestureHandlerRootView style={{ flex: 1 }}>
        {errorMsg ? (
          <View style={[styles.errorBanner, { top: insets.top + 8, zIndex: 99, position: 'absolute', left: 16, right: 16 }]}>
            <Ionicons name="alert-circle" size={16} color={C.error} />
            <Text style={styles.errorBannerText}>{errorMsg}</Text>
          </View>
        ) : null}
        <CropOverlay
          imageUri={uri}
          imageW={imageSize.width}
          imageH={imageSize.height}
          onConfirm={(crop) => startProcessing(crop)}
          onRetake={() => router.back()}
        />
      </GestureHandlerRootView>
    );
  }

  if (!receipt) return null;

  return (
    <KeyboardAvoidingView
      style={[styles.root, { paddingTop: insets.top }]}
      behavior={Platform.OS === 'ios' ? 'padding' : undefined}
    >
      <View style={styles.navBar}>
        <TouchableOpacity onPress={() => setStep('crop')} style={styles.navBtn}>
          <Ionicons name="chevron-back" size={22} color={C.textPrimary} />
        </TouchableOpacity>
        <Text style={styles.navTitle}>Prüfen & Speichern</Text>
        <TouchableOpacity onPress={saveReceipt} style={styles.saveBtn}>
          <Text style={styles.saveBtnText}>Speichern</Text>
        </TouchableOpacity>
      </View>

      <ScrollView style={{ flex: 1 }} contentContainerStyle={styles.scrollContent} keyboardShouldPersistTaps="handled">

        {/* Header Card */}
        <View style={[card, styles.headerCard]}>
          <Text style={styles.fieldLabel}>Geschäft</Text>
          <TextInput
            style={styles.fieldInput}
            value={receipt.store_name}
            onChangeText={(v) => setReceipt((p) => p ? { ...p, store_name: v } : p)}
            placeholderTextColor={C.textTertiary}
          />
          <View style={styles.row2}>
            <View style={{ flex: 1 }}>
              <Text style={styles.fieldLabel}>Datum</Text>
              <TextInput
                style={styles.fieldInput}
                value={receipt.date ?? ''}
                onChangeText={(v) => setReceipt((p) => p ? { ...p, date: v } : p)}
                placeholder="YYYY-MM-DD"
                placeholderTextColor={C.textTertiary}
              />
            </View>
            <View style={{ flex: 1 }}>
              <Text style={styles.fieldLabel}>Betrag</Text>
              <NumericInput
                value={receipt.total_amount}
                onChange={(v) => setReceipt((p) => p ? { ...p, total_amount: v } : p)}
                style={[styles.fieldInput, styles.amountInput]}
                placeholder="0.00"
                placeholderTextColor={C.textTertiary}
              />
            </View>
          </View>
          <View>
            <Text style={styles.fieldLabel}>Währung</Text>
            <CurrencyPicker
              value={receipt.currency}
              onChange={(c) => setReceipt((p) => p ? { ...p, currency: c } : p)}
            />
          </View>
          <View style={styles.row2}>
            <View style={{ flex: 1 }}>
              <Text style={styles.fieldLabel}>Zahlungsmittel</Text>
              <TextInput style={styles.fieldInput} value={receipt.payment_method}
                onChangeText={(v) => setReceipt((p) => p ? { ...p, payment_method: v } : p)}
                placeholderTextColor={C.textTertiary} />
            </View>
            <View style={{ flex: 1 }}>
              <Text style={styles.fieldLabel}>Karte</Text>
              <TextInput style={styles.fieldInput} value={receipt.payment_card ?? ''}
                onChangeText={(v) => setReceipt((p) => p ? { ...p, payment_card: v || null } : p)}
                placeholder="Visa ···· 1234" placeholderTextColor={C.textTertiary} />
            </View>
          </View>
        </View>

        {/* Items */}
        <Text style={styles.sectionTitle}>Artikel ({receipt.items.length})</Text>

        {receipt.items.map((item, idx) => (
          <View key={idx} style={[card, styles.itemCard]}>
            <View style={styles.itemHeader}>
              <TextInput
                style={[styles.fieldInput, { flex: 1 }]}
                value={item.name}
                onChangeText={(v) => updateItem(idx, { name: v })}
                placeholderTextColor={C.textTertiary}
              />
              <TouchableOpacity onPress={() => removeItem(idx)} style={styles.removeBtn}>
                <Ionicons name="trash-outline" size={18} color={C.error} />
              </TouchableOpacity>
            </View>
            <View style={styles.row3}>
              <View style={{ flex: 1 }}>
                <Text style={styles.fieldLabel}>Menge</Text>
                <NumericInput
                  value={item.quantity}
                  onChange={(v) => updateItem(idx, { quantity: v || 1 })}
                  style={styles.fieldInput}
                  placeholder="1"
                  placeholderTextColor={C.textTertiary}
                />
              </View>
              <View style={{ flex: 1 }}>
                <Text style={styles.fieldLabel}>Einheit</Text>
                <TextInput style={styles.fieldInput} value={item.unit}
                  onChangeText={(v) => updateItem(idx, { unit: v })}
                  placeholder="Stk" placeholderTextColor={C.textTertiary} />
              </View>
              <View style={{ flex: 1 }}>
                <Text style={styles.fieldLabel}>Preis</Text>
                <NumericInput
                  value={item.total_price}
                  onChange={(v) => updateItem(idx, { total_price: v })}
                  style={styles.fieldInput}
                  placeholder="0.00"
                  placeholderTextColor={C.textTertiary}
                />
              </View>
            </View>
            <View style={styles.tagsRow}>
              {ALL_TAGS.map((tag) => {
                const active = item.tags.includes(tag);
                const color  = getTagColor(tag);
                return (
                  <TouchableOpacity
                    key={tag}
                    onPress={() => toggleTag(idx, tag)}
                    style={[styles.tagChip, active && { backgroundColor: `${color}18`, borderColor: color }]}
                  >
                    <Text style={[styles.tagChipText, active && { color }]}>{tag}</Text>
                  </TouchableOpacity>
                );
              })}
            </View>
          </View>
        ))}

        <TouchableOpacity
          style={styles.addItemBtn}
          onPress={() => setReceipt((p) => p ? {
            ...p, items: [...p.items, { name: '', quantity: 1, unit: 'Stk', unit_price: 0, total_price: 0, tags: [] }],
          } : p)}
        >
          <Ionicons name="add-circle-outline" size={20} color={C.gold} />
          <Text style={styles.addItemText}>Artikel hinzufügen</Text>
        </TouchableOpacity>

        <View style={{ height: 40 }} />
      </ScrollView>
    </KeyboardAvoidingView>
  );
}

const styles = StyleSheet.create({
  root:   { flex: 1, backgroundColor: C.bg },
  center: { flex: 1, backgroundColor: C.bg, alignItems: 'center', justifyContent: 'center', gap: 12 },

  navBar: {
    flexDirection: 'row', alignItems: 'center', paddingHorizontal: 16,
    paddingVertical: 12, borderBottomWidth: 1, borderBottomColor: C.border,
    backgroundColor: C.bgCard,
  },
  navBtn:    { width: 40, alignItems: 'flex-start' },
  navTitle:  { flex: 1, color: C.textPrimary, fontSize: S.lg, fontWeight: '700', textAlign: 'center' },
  saveBtn:   { backgroundColor: C.gold, borderRadius: R.md, paddingHorizontal: 16, paddingVertical: 8 },
  saveBtnText: { color: '#fff', fontWeight: '700', fontSize: S.md },

  errorBanner: {
    flexDirection: 'row', alignItems: 'center', gap: 8,
    marginHorizontal: 20, backgroundColor: '#FFF0F0',
    borderRadius: R.md, paddingHorizontal: 14, paddingVertical: 10,
    borderWidth: 1, borderColor: '#FFD0D0',
  },
  errorBannerText: { color: C.error, fontSize: S.sm, flex: 1 },

  processingPig:  { fontSize: 64 },
  processingText: { color: C.textPrimary, fontSize: S.lg, fontWeight: '700', marginTop: 8 },
  processingHint: { color: C.textSecondary, fontSize: S.sm },

  emptyPig:   { fontSize: 56 },
  errorText:  { color: C.error, fontSize: S.md },
  backLink:   { marginTop: 8 },
  backLinkText: { color: C.gold, fontSize: S.md, fontWeight: '600' },

  scrollContent: { padding: 16, gap: 12 },
  headerCard:    { padding: 16, gap: 12 },
  fieldLabel:    { fontSize: S.xs, fontWeight: '700', color: C.textTertiary, textTransform: 'uppercase', letterSpacing: 0.5, marginBottom: 4 },
  fieldInput: {
    backgroundColor: C.bgSoft, color: C.textPrimary,
    borderRadius: R.sm, paddingHorizontal: 12, paddingVertical: 10,
    fontSize: S.md, borderWidth: 1, borderColor: C.border,
  },
  amountInput: { fontWeight: '700', color: C.gold },
  row2:  { flexDirection: 'row', gap: 10 },
  row3:  { flexDirection: 'row', gap: 8 },

  sectionTitle: { color: C.textPrimary, fontSize: S.lg, fontWeight: '700', marginTop: 4 },
  itemCard:   { padding: 14, gap: 10 },
  itemHeader: { flexDirection: 'row', gap: 10, alignItems: 'center' },
  removeBtn:  { padding: 4 },

  tagsRow:     { flexDirection: 'row', flexWrap: 'wrap', gap: 6, marginTop: 4 },
  tagChip:     { borderWidth: 1, borderColor: C.border, borderRadius: 20, paddingHorizontal: 10, paddingVertical: 4, backgroundColor: C.bgSoft },
  tagChipText: { color: C.textTertiary, fontSize: S.xs },

  addItemBtn: {
    flexDirection: 'row', alignItems: 'center', justifyContent: 'center', gap: 8,
    backgroundColor: C.bgCard, borderRadius: R.lg, paddingVertical: 14,
    borderWidth: 1, borderColor: `${C.gold}44`,
  },
  addItemText: { color: C.gold, fontSize: S.md, fontWeight: '600' },
});
