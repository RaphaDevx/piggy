package com.scandit.datacapture.barcode.capture;

import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture;
import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureSettings;
import com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializerHelper;
import com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlay;
import com.scandit.datacapture.barcode.ui.overlay.NativeBarcodeCaptureOverlayStyle;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.internal.module.source.NativeCameraSettings;
import com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010!\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b!\u0010\"J'\u0010'\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010$\u001a\u00020#2\u0006\u0010&\u001a\u00020%H\u0016¢\u0006\u0004\b'\u0010(J\u000f\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b*\u0010+R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/¨\u00060"}, d2 = {"Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerHelperReversedAdapter;", "Lcom/scandit/datacapture/barcode/internal/module/serialization/NativeBarcodeCaptureDeserializerHelper;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerHelper;", "_BarcodeCaptureDeserializerHelper", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerHelper;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;", "context", "Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCapture;", "createMode", "(Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;)Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCapture;", "mode", "Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCaptureSettings;", "settings", "", "applySettings", "(Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCapture;Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCaptureSettings;)V", "Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;", "json", "updateModeFromJson", "(Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCapture;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "createSettings", "()Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCaptureSettings;", "updateSettingsFromJson", "(Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCaptureSettings;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "Lcom/scandit/datacapture/barcode/ui/overlay/NativeBarcodeCaptureOverlayStyle;", "style", "Lcom/scandit/datacapture/barcode/internal/module/ui/NativeBarcodeCaptureOverlay;", "createOverlay", "(Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCapture;Lcom/scandit/datacapture/barcode/ui/overlay/NativeBarcodeCaptureOverlayStyle;)Lcom/scandit/datacapture/barcode/internal/module/ui/NativeBarcodeCaptureOverlay;", "overlay", "updateOverlayFromJson", "(Lcom/scandit/datacapture/barcode/internal/module/ui/NativeBarcodeCaptureOverlay;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "", "added", "changeOverlayAddedToView", "(Lcom/scandit/datacapture/barcode/internal/module/ui/NativeBarcodeCaptureOverlay;Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;Z)V", "Lcom/scandit/datacapture/core/internal/module/source/NativeCameraSettings;", "createRecommendedCameraSettings", "()Lcom/scandit/datacapture/core/internal/module/source/NativeCameraSettings;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeCaptureDeserializerHelperReversedAdapter extends NativeBarcodeCaptureDeserializerHelper {
    private final BarcodeCaptureDeserializerHelper a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public BarcodeCaptureDeserializerHelperReversedAdapter(BarcodeCaptureDeserializerHelper _BarcodeCaptureDeserializerHelper, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_BarcodeCaptureDeserializerHelper, "_BarcodeCaptureDeserializerHelper");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodeCaptureDeserializerHelper;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializerHelper
    public void applySettings(NativeBarcodeCapture mode, NativeBarcodeCaptureSettings settings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.a.applySettings((BarcodeCapture) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCapture.class), null, mode, new C0275g(mode)), (BarcodeCaptureSettings) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureSettings.class), null, settings, new C0276h(settings)));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializerHelper
    public void changeOverlayAddedToView(NativeBarcodeCaptureOverlay overlay, NativeDataCaptureView view, boolean added) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(view, "view");
        this.a.changeOverlayAddedToView((BarcodeCaptureOverlay) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureOverlay.class), null, overlay), (DataCaptureView) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeDataCaptureView.class), null, view), added);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializerHelper
    public NativeBarcodeCapture createMode(NativeDataCaptureContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BarcodeCapture createMode = this.a.createMode((DataCaptureContext) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, context, new C0277i(context)));
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeCapture.class), null, createMode, createMode._impl());
        NativeBarcodeCapture _impl = createMode._impl();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeCapture.class), null, _impl, createMode);
        return _impl;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializerHelper
    public NativeBarcodeCaptureOverlay createOverlay(NativeBarcodeCapture mode, NativeBarcodeCaptureOverlayStyle style) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(style, "style");
        BarcodeCaptureOverlay createOverlay = this.a.createOverlay((BarcodeCapture) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCapture.class), null, mode, new C0278j(mode)), BarcodeNativeTypeFactory.INSTANCE.convert(style));
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeCaptureOverlay.class), null, createOverlay, createOverlay._impl());
        NativeBarcodeCaptureOverlay _impl = createOverlay._impl();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureOverlay.class), null, _impl, createOverlay);
        return _impl;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializerHelper
    public NativeCameraSettings createRecommendedCameraSettings() {
        return CoreNativeTypeFactory.INSTANCE.convert(this.a.createRecommendedCameraSettings());
    }

    @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializerHelper
    public NativeBarcodeCaptureSettings createSettings() {
        BarcodeCaptureSettings createSettings = this.a.createSettings();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeCaptureSettings.class), null, createSettings, createSettings._impl());
        NativeBarcodeCaptureSettings _impl = createSettings._impl();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureSettings.class), null, _impl, createSettings);
        return _impl;
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializerHelper
    public void updateModeFromJson(NativeBarcodeCapture mode, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.updateModeFromJson((BarcodeCapture) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCapture.class), null, mode, new C0279k(mode)), (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new C0280l(json)));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializerHelper
    public void updateOverlayFromJson(NativeBarcodeCaptureOverlay overlay, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.updateOverlayFromJson((BarcodeCaptureOverlay) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureOverlay.class), null, overlay), (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new C0281m(json)));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializerHelper
    public void updateSettingsFromJson(NativeBarcodeCaptureSettings settings, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.updateSettingsFromJson((BarcodeCaptureSettings) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureSettings.class), null, settings, new C0282n(settings)), (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new C0283o(json)));
    }

    public /* synthetic */ BarcodeCaptureDeserializerHelperReversedAdapter(BarcodeCaptureDeserializerHelper barcodeCaptureDeserializerHelper, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeCaptureDeserializerHelper, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
