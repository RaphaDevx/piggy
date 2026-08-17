package com.scandit.datacapture.barcode.internal.module.find.serialization;

import com.scandit.datacapture.barcode.find.capture.BarcodeFind;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindSettings;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSettings;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes2.dex */
public final class d extends NativeBarcodeFindDeserializerHelper {
    public final BarcodeFindDeserializerHelper a;
    public final ProxyCache b;

    public d(a _BarcodeFindDeserializerHelper) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_BarcodeFindDeserializerHelper, "_BarcodeFindDeserializerHelper");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodeFindDeserializerHelper;
        this.b = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializerHelper
    public final void applySettings(NativeBarcodeFind mode, NativeBarcodeFindSettings settings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.a.a((BarcodeFind) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodeFind.class), null, mode), (BarcodeFindSettings) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodeFindSettings.class), null, settings));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializerHelper
    public final NativeBarcodeFind createMode() {
        BarcodeFind b = this.a.b();
        NativeBarcodeFind _impl = b._impl();
        this.b.put(Reflection.getOrCreateKotlinClass(NativeBarcodeFind.class), null, _impl, b);
        return _impl;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializerHelper
    public final NativeBarcodeFindSettings createSettings() {
        BarcodeFindSettings createSettings = this.a.createSettings();
        NativeBarcodeFindSettings _impl = createSettings._impl();
        this.b.put(Reflection.getOrCreateKotlinClass(NativeBarcodeFindSettings.class), null, _impl, createSettings);
        return _impl;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializerHelper
    public final void updateModeFromJson(NativeBarcodeFind mode, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.a((BarcodeFind) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodeFind.class), null, mode), (JsonValue) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new b(json)));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializerHelper
    public final void updateSettingsFromJson(NativeBarcodeFindSettings settings, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.a((BarcodeFindSettings) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodeFindSettings.class), null, settings), (JsonValue) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new c(json)));
    }
}
