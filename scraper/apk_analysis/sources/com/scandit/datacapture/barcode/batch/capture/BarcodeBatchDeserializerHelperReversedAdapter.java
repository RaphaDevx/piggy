package com.scandit.datacapture.barcode.batch.capture;

import com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking;
import com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings;
import com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper;
import com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingAdvancedOverlay;
import com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingBasicOverlay;
import com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingBasicOverlayStyle;
import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlay;
import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlay;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeObjectTrackerScenario;
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

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\"\u001a\u00020!2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b\"\u0010#J\u001f\u0010%\u001a\u00020\u00102\u0006\u0010$\u001a\u00020!2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b%\u0010&J'\u0010+\u001a\u00020\u00102\u0006\u0010$\u001a\u00020!2\u0006\u0010(\u001a\u00020'2\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020-2\u0006\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b.\u0010/J\u001f\u00100\u001a\u00020\u00102\u0006\u0010$\u001a\u00020-2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b0\u00101J'\u00102\u001a\u00020\u00102\u0006\u0010$\u001a\u00020-2\u0006\u0010(\u001a\u00020'2\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b2\u00103J\u000f\u00105\u001a\u000204H\u0016¢\u0006\u0004\b5\u00106R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:¨\u0006;"}, d2 = {"Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchDeserializerHelperReversedAdapter;", "Lcom/scandit/datacapture/barcode/batch/internal/module/serialization/NativeBarcodeTrackingDeserializerHelper;", "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchDeserializerHelper;", "_BarcodeBatchDeserializerHelper", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchDeserializerHelper;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;", "context", "Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTracking;", "createMode", "(Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;)Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTracking;", "mode", "Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTrackingSettings;", "settings", "", "applySettings", "(Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTracking;Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTrackingSettings;)V", "Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;", "json", "updateModeFromJson", "(Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTracking;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "createSettings", "()Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTrackingSettings;", "Lcom/scandit/datacapture/barcode/tracking/internal/module/data/NativeObjectTrackerScenario;", "scenario", "createSettingsForScenario", "(Lcom/scandit/datacapture/barcode/tracking/internal/module/data/NativeObjectTrackerScenario;)Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTrackingSettings;", "updateSettingsFromJson", "(Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTrackingSettings;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingBasicOverlayStyle;", "style", "Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingBasicOverlay;", "createBasicOverlay", "(Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTracking;Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingBasicOverlayStyle;)Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingBasicOverlay;", "overlay", "updateBasicOverlayFromJson", "(Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingBasicOverlay;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "", "added", "changeBasicOverlayAddedToView", "(Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingBasicOverlay;Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;Z)V", "Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingAdvancedOverlay;", "createAdvancedOverlay", "(Lcom/scandit/datacapture/barcode/batch/internal/module/capture/NativeBarcodeTracking;)Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingAdvancedOverlay;", "updateAdvancedOverlayFromJson", "(Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingAdvancedOverlay;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "changeAdvancedOverlayAddedToView", "(Lcom/scandit/datacapture/barcode/batch/internal/module/ui/overlay/NativeBarcodeTrackingAdvancedOverlay;Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;Z)V", "Lcom/scandit/datacapture/core/internal/module/source/NativeCameraSettings;", "createRecommendedCameraSettings", "()Lcom/scandit/datacapture/core/internal/module/source/NativeCameraSettings;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeBatchDeserializerHelperReversedAdapter extends NativeBarcodeTrackingDeserializerHelper {
    private final BarcodeBatchDeserializerHelper a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public BarcodeBatchDeserializerHelperReversedAdapter(BarcodeBatchDeserializerHelper _BarcodeBatchDeserializerHelper, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_BarcodeBatchDeserializerHelper, "_BarcodeBatchDeserializerHelper");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodeBatchDeserializerHelper;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public void applySettings(NativeBarcodeTracking mode, NativeBarcodeTrackingSettings settings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.a.applySettings((BarcodeBatch) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeTracking.class), null, mode), (BarcodeBatchSettings) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeTrackingSettings.class), null, settings));
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public void changeAdvancedOverlayAddedToView(NativeBarcodeTrackingAdvancedOverlay overlay, NativeDataCaptureView view, boolean added) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(view, "view");
        this.a.changeAdvancedOverlayAddedToView((BarcodeBatchAdvancedOverlay) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeTrackingAdvancedOverlay.class), null, overlay), (DataCaptureView) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeDataCaptureView.class), null, view), added);
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public void changeBasicOverlayAddedToView(NativeBarcodeTrackingBasicOverlay overlay, NativeDataCaptureView view, boolean added) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(view, "view");
        this.a.changeBasicOverlayAddedToView((BarcodeBatchBasicOverlay) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeTrackingBasicOverlay.class), null, overlay), (DataCaptureView) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeDataCaptureView.class), null, view), added);
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public NativeBarcodeTrackingAdvancedOverlay createAdvancedOverlay(NativeBarcodeTracking mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BarcodeBatchAdvancedOverlay createAdvancedOverlay = this.a.createAdvancedOverlay((BarcodeBatch) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeTracking.class), null, mode));
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeBatchAdvancedOverlay.class), null, createAdvancedOverlay, createAdvancedOverlay.getA());
        NativeBarcodeTrackingAdvancedOverlay a = createAdvancedOverlay.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeTrackingAdvancedOverlay.class), null, a, createAdvancedOverlay);
        return a;
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public NativeBarcodeTrackingBasicOverlay createBasicOverlay(NativeBarcodeTracking mode, NativeBarcodeTrackingBasicOverlayStyle style) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(style, "style");
        BarcodeBatchBasicOverlay createBasicOverlay = this.a.createBasicOverlay((BarcodeBatch) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeTracking.class), null, mode), BarcodeNativeTypeFactory.INSTANCE.convert(style));
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeBatchBasicOverlay.class), null, createBasicOverlay, createBasicOverlay.getA());
        NativeBarcodeTrackingBasicOverlay a = createBasicOverlay.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeTrackingBasicOverlay.class), null, a, createBasicOverlay);
        return a;
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public NativeBarcodeTracking createMode(NativeDataCaptureContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BarcodeBatch createMode = this.a.createMode((DataCaptureContext) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, context, new C0267g(context)));
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeBatch.class), null, createMode, createMode.getA());
        NativeBarcodeTracking a = createMode.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeTracking.class), null, a, createMode);
        return a;
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public NativeCameraSettings createRecommendedCameraSettings() {
        return CoreNativeTypeFactory.INSTANCE.convert(this.a.createRecommendedCameraSettings());
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public NativeBarcodeTrackingSettings createSettings() {
        BarcodeBatchSettings createSettings = this.a.createSettings();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeBatchSettings.class), null, createSettings, createSettings._impl());
        NativeBarcodeTrackingSettings _impl = createSettings._impl();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeTrackingSettings.class), null, _impl, createSettings);
        return _impl;
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public NativeBarcodeTrackingSettings createSettingsForScenario(NativeObjectTrackerScenario scenario) {
        Intrinsics.checkNotNullParameter(scenario, "scenario");
        BarcodeBatchSettings createSettingsForScenario = this.a.createSettingsForScenario(scenario);
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeBatchSettings.class), null, createSettingsForScenario, createSettingsForScenario._impl());
        NativeBarcodeTrackingSettings _impl = createSettingsForScenario._impl();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeTrackingSettings.class), null, _impl, createSettingsForScenario);
        return _impl;
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public void updateAdvancedOverlayFromJson(NativeBarcodeTrackingAdvancedOverlay overlay, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.updateAdvancedOverlayFromJson((BarcodeBatchAdvancedOverlay) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeTrackingAdvancedOverlay.class), null, overlay), (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new C0268h(json)));
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public void updateBasicOverlayFromJson(NativeBarcodeTrackingBasicOverlay overlay, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.updateBasicOverlayFromJson((BarcodeBatchBasicOverlay) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeTrackingBasicOverlay.class), null, overlay), (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new i(json)));
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public void updateModeFromJson(NativeBarcodeTracking mode, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.updateModeFromJson((BarcodeBatch) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeTracking.class), null, mode), (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new j(json)));
    }

    @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializerHelper
    public void updateSettingsFromJson(NativeBarcodeTrackingSettings settings, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.updateSettingsFromJson((BarcodeBatchSettings) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeTrackingSettings.class), null, settings), (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new k(json)));
    }

    public /* synthetic */ BarcodeBatchDeserializerHelperReversedAdapter(BarcodeBatchDeserializerHelper barcodeBatchDeserializerHelper, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeBatchDeserializerHelper, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
