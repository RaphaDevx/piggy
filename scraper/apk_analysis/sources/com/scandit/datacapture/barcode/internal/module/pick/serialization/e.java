package com.scandit.datacapture.barcode.internal.module.pick.serialization;

import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSettings;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeProductProvider;
import com.scandit.datacapture.barcode.internal.module.pick.ui.BarcodePickBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickSettings;
import com.scandit.datacapture.barcode.pick.data.BarcodePickProductProvider;
import com.scandit.datacapture.barcode.pick.serialization.k;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettings;
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
public final class e extends NativeBarcodePickDeserializerHelper {
    public final BarcodePickDeserializerHelper a;
    public final ProxyCache b;

    public e(k _BarcodePickDeserializerHelper) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_BarcodePickDeserializerHelper, "_BarcodePickDeserializerHelper");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodePickDeserializerHelper;
        this.b = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.NativeBarcodePickDeserializerHelper
    public final void applySettings(NativeBarcodePick mode, NativeBarcodePickSettings settings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.a.a(BarcodeNativeTypeFactory.INSTANCE.convert(mode), (BarcodePickSettings) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodePickSettings.class), null, settings));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.NativeBarcodePickDeserializerHelper
    public final NativeBarcodePickBasicOverlay createBasicOverlay(NativeBarcodePick mode, NativeBarcodePickViewSettings viewSettings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        BarcodePickBasicOverlay a = this.a.a(BarcodeNativeTypeFactory.INSTANCE.convert(mode), (BarcodePickViewSettings) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodePickViewSettings.class), null, viewSettings));
        NativeBarcodePickBasicOverlay a2 = a.a();
        this.b.put(Reflection.getOrCreateKotlinClass(NativeBarcodePickBasicOverlay.class), null, a2, a);
        return a2;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.NativeBarcodePickDeserializerHelper
    public final NativeBarcodePick createMode(NativeDataCaptureContext context, NativeProductProvider provider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(provider, "provider");
        BarcodePick a = this.a.a((DataCaptureContext) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, context, new a(context)), (BarcodePickProductProvider) this.b.require(Reflection.getOrCreateKotlinClass(NativeProductProvider.class), null, provider));
        this.b.put(Reflection.getOrCreateKotlinClass(BarcodePick.class), null, a, a._impl$scandit_barcode_capture());
        return BarcodeNativeTypeFactory.INSTANCE.convert(a);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.NativeBarcodePickDeserializerHelper
    public final NativeBarcodePickSettings createSettings() {
        BarcodePickSettings createSettings = this.a.createSettings();
        this.b.put(Reflection.getOrCreateKotlinClass(BarcodePickSettings.class), null, createSettings, createSettings.getA());
        NativeBarcodePickSettings a = createSettings.getA();
        this.b.put(Reflection.getOrCreateKotlinClass(NativeBarcodePickSettings.class), null, a, createSettings);
        return a;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.NativeBarcodePickDeserializerHelper
    public final NativeBarcodePickViewSettings createViewSettings() {
        BarcodePickViewSettings a = this.a.a();
        this.b.put(Reflection.getOrCreateKotlinClass(BarcodePickViewSettings.class), null, a, a.getA());
        NativeBarcodePickViewSettings a2 = a.getA();
        this.b.put(Reflection.getOrCreateKotlinClass(NativeBarcodePickViewSettings.class), null, a2, a);
        return a2;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.NativeBarcodePickDeserializerHelper
    public final void updateModeFromJson(NativeBarcodePick mode, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.a(BarcodeNativeTypeFactory.INSTANCE.convert(mode), (JsonValue) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new b(json)));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.NativeBarcodePickDeserializerHelper
    public final void updateSettingsFromJson(NativeBarcodePickSettings settings, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.a((BarcodePickSettings) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodePickSettings.class), null, settings), (JsonValue) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new c(json)));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.serialization.NativeBarcodePickDeserializerHelper
    public final void updateViewSettingsFromJson(NativeBarcodePickViewSettings viewSettings, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.a((BarcodePickViewSettings) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodePickViewSettings.class), null, viewSettings), (JsonValue) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new d(json)));
    }
}
