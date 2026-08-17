package com.scandit.datacapture.barcode.count.serialization;

import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.count.capture.BarcodeCountSettings;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings;
import com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializer;
import com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializerListener;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ'\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0013\u0010\u0012J'\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0018\u0010\u0017J'\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ'\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u001d\u0010\u001cR\u001a\u0010\u0007\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/scandit/datacapture/barcode/count/serialization/BarcodeCountDeserializerListenerReversedAdapter;", "Lcom/scandit/datacapture/barcode/count/internal/module/serialization/NativeBarcodeCountDeserializerListener;", "Lcom/scandit/datacapture/barcode/count/serialization/BarcodeCountDeserializerListener;", "_BarcodeCountDeserializerListener", "Lcom/scandit/datacapture/barcode/count/serialization/BarcodeCountDeserializer;", "_BarcodeCountDeserializer", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/count/serialization/BarcodeCountDeserializerListener;Lcom/scandit/datacapture/barcode/count/serialization/BarcodeCountDeserializer;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/barcode/count/internal/module/serialization/NativeBarcodeCountDeserializer;", "deserializer", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCount;", "mode", "Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;", "json", "", "onModeDeserializationStarted", "(Lcom/scandit/datacapture/barcode/count/internal/module/serialization/NativeBarcodeCountDeserializer;Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCount;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "onModeDeserializationFinished", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountSettings;", "settings", "onSettingsDeserializationStarted", "(Lcom/scandit/datacapture/barcode/count/internal/module/serialization/NativeBarcodeCountDeserializer;Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountSettings;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "onSettingsDeserializationFinished", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountBasicOverlay;", "overlay", "onBasicOverlayDeserializationStarted", "(Lcom/scandit/datacapture/barcode/count/internal/module/serialization/NativeBarcodeCountDeserializer;Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountBasicOverlay;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "onBasicOverlayDeserializationFinished", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeCountDeserializerListenerReversedAdapter extends NativeBarcodeCountDeserializerListener {
    private final BarcodeCountDeserializerListener a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final WeakReference c;

    public /* synthetic */ BarcodeCountDeserializerListenerReversedAdapter(BarcodeCountDeserializerListener barcodeCountDeserializerListener, BarcodeCountDeserializer barcodeCountDeserializer, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeCountDeserializerListener, barcodeCountDeserializer, (i & 4) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializerListener
    public void onBasicOverlayDeserializationFinished(NativeBarcodeCountDeserializer deserializer, NativeBarcodeCountBasicOverlay overlay, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCountDeserializer barcodeCountDeserializer = (BarcodeCountDeserializer) this.c.get();
        if (barcodeCountDeserializer != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountDeserializer.class), null, deserializer, new h(barcodeCountDeserializer));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeCountBasicOverlay.class), null, overlay);
            JsonValue jsonValue = (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new i(json));
            this.a.onBasicOverlayDeserializationFinished((BarcodeCountDeserializer) orPut, barcodeCountBasicOverlay, jsonValue);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializerListener
    public void onBasicOverlayDeserializationStarted(NativeBarcodeCountDeserializer deserializer, NativeBarcodeCountBasicOverlay overlay, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCountDeserializer barcodeCountDeserializer = (BarcodeCountDeserializer) this.c.get();
        if (barcodeCountDeserializer != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountDeserializer.class), null, deserializer, new j(barcodeCountDeserializer));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeCountBasicOverlay.class), null, overlay);
            JsonValue jsonValue = (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new k(json));
            this.a.onBasicOverlayDeserializationStarted((BarcodeCountDeserializer) orPut, barcodeCountBasicOverlay, jsonValue);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializerListener
    public void onModeDeserializationFinished(NativeBarcodeCountDeserializer deserializer, NativeBarcodeCount mode, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCountDeserializer barcodeCountDeserializer = (BarcodeCountDeserializer) this.c.get();
        if (barcodeCountDeserializer != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountDeserializer.class), null, deserializer, new l(barcodeCountDeserializer));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeCount barcodeCount = (BarcodeCount) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeCount.class), null, mode);
            JsonValue jsonValue = (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new m(json));
            this.a.onModeDeserializationFinished((BarcodeCountDeserializer) orPut, barcodeCount, jsonValue);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializerListener
    public void onModeDeserializationStarted(NativeBarcodeCountDeserializer deserializer, NativeBarcodeCount mode, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCountDeserializer barcodeCountDeserializer = (BarcodeCountDeserializer) this.c.get();
        if (barcodeCountDeserializer != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountDeserializer.class), null, deserializer, new n(barcodeCountDeserializer));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeCount barcodeCount = (BarcodeCount) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeCount.class), null, mode);
            JsonValue jsonValue = (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new o(json));
            this.a.onModeDeserializationStarted((BarcodeCountDeserializer) orPut, barcodeCount, jsonValue);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializerListener
    public void onSettingsDeserializationFinished(NativeBarcodeCountDeserializer deserializer, NativeBarcodeCountSettings settings, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCountDeserializer barcodeCountDeserializer = (BarcodeCountDeserializer) this.c.get();
        if (barcodeCountDeserializer != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountDeserializer.class), null, deserializer, new p(barcodeCountDeserializer));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeCountSettings barcodeCountSettings = (BarcodeCountSettings) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeCountSettings.class), null, settings);
            JsonValue jsonValue = (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new q(json));
            this.a.onSettingsDeserializationFinished((BarcodeCountDeserializer) orPut, barcodeCountSettings, jsonValue);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializerListener
    public void onSettingsDeserializationStarted(NativeBarcodeCountDeserializer deserializer, NativeBarcodeCountSettings settings, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCountDeserializer barcodeCountDeserializer = (BarcodeCountDeserializer) this.c.get();
        if (barcodeCountDeserializer != null) {
            Object orPut = this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCountDeserializer.class), null, deserializer, new r(barcodeCountDeserializer));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeCountSettings barcodeCountSettings = (BarcodeCountSettings) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeCountSettings.class), null, settings);
            JsonValue jsonValue = (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new s(json));
            this.a.onSettingsDeserializationStarted((BarcodeCountDeserializer) orPut, barcodeCountSettings, jsonValue);
        }
    }

    public BarcodeCountDeserializerListenerReversedAdapter(BarcodeCountDeserializerListener _BarcodeCountDeserializerListener, BarcodeCountDeserializer _BarcodeCountDeserializer, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_BarcodeCountDeserializerListener, "_BarcodeCountDeserializerListener");
        Intrinsics.checkNotNullParameter(_BarcodeCountDeserializer, "_BarcodeCountDeserializer");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodeCountDeserializerListener;
        this.proxyCache = proxyCache;
        this.c = new WeakReference(_BarcodeCountDeserializer);
    }
}
