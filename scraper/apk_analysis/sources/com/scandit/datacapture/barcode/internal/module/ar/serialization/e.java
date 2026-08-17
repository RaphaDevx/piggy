package com.scandit.datacapture.barcode.internal.module.ar.serialization;

import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArSettings;
import com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr;
import com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSettings;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes2.dex */
public final class e extends NativeBarcodeArDeserializerHelper {
    public final BarcodeArDeserializerHelper a;
    public final ProxyCache b;

    public e(a _BarcodeArDeserializerHelper) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_BarcodeArDeserializerHelper, "_BarcodeArDeserializerHelper");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodeArDeserializerHelper;
        this.b = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ar.serialization.NativeBarcodeArDeserializerHelper
    public final void applySettings(NativeBarcodeAr mode, NativeBarcodeArSettings settings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.a.a(BarcodeNativeTypeFactory.INSTANCE.convert(mode), (BarcodeArSettings) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodeArSettings.class), null, settings));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ar.serialization.NativeBarcodeArDeserializerHelper
    public final NativeBarcodeAr createMode(NativeDataCaptureContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BarcodeAr createMode = this.a.createMode((DataCaptureContext) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, context, new b(context)));
        this.b.put(Reflection.getOrCreateKotlinClass(BarcodeAr.class), null, createMode, createMode._impl$scandit_barcode_capture());
        return BarcodeNativeTypeFactory.INSTANCE.convert(createMode);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ar.serialization.NativeBarcodeArDeserializerHelper
    public final NativeBarcodeArSettings createSettings() {
        BarcodeArSettings createSettings = this.a.createSettings();
        this.b.put(Reflection.getOrCreateKotlinClass(BarcodeArSettings.class), null, createSettings, createSettings.getA());
        NativeBarcodeArSettings a = createSettings.getA();
        this.b.put(Reflection.getOrCreateKotlinClass(NativeBarcodeArSettings.class), null, a, createSettings);
        return a;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ar.serialization.NativeBarcodeArDeserializerHelper
    public final void updateModeFromJson(NativeBarcodeAr mode, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.a(BarcodeNativeTypeFactory.INSTANCE.convert(mode), (JsonValue) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new c(json)));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ar.serialization.NativeBarcodeArDeserializerHelper
    public final void updateSettingsFromJson(NativeBarcodeArSettings settings, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.a((BarcodeArSettings) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodeArSettings.class), null, settings), (JsonValue) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new d(json)));
    }
}
