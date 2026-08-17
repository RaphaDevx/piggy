package com.scandit.datacapture.barcode.selection.capture;

import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAimerSelection;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAutoSelectionStrategy;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeManualSelectionStrategy;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeTapSelection;
import com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper;
import com.scandit.datacapture.barcode.selection.internal.module.ui.overlay.NativeBarcodeSelectionBasicOverlay;
import com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlay;
import com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayStyle;
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

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010$J\u001f\u0010%\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b%\u0010&J\u001f\u0010*\u001a\u00020)2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b*\u0010+J\u001f\u0010-\u001a\u00020\u00102\u0006\u0010,\u001a\u00020)2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b-\u0010.J'\u00103\u001a\u00020\u00102\u0006\u0010,\u001a\u00020)2\u0006\u00100\u001a\u00020/2\u0006\u00102\u001a\u000201H\u0016¢\u0006\u0004\b3\u00104J\u000f\u00106\u001a\u000205H\u0016¢\u0006\u0004\b6\u00107R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;¨\u0006<"}, d2 = {"Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializerHelperReversedAdapter;", "Lcom/scandit/datacapture/barcode/selection/internal/module/serialization/NativeBarcodeSelectionDeserializerHelper;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializerHelper;", "_BarcodeSelectionDeserializerHelper", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializerHelper;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;", "context", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;", "createMode", "(Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureContext;)Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;", "mode", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelectionSettings;", "settings", "", "applySettings", "(Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelectionSettings;)V", "Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;", "json", "updateModeFromJson", "(Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "createSettings", "()Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelectionSettings;", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeTapSelection;", "createTapSelection", "()Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeTapSelection;", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeAimerSelection;", "createAimerSelection", "()Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeAimerSelection;", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeAutoSelectionStrategy;", "createAutoSelectionStrategy", "()Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeAutoSelectionStrategy;", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeManualSelectionStrategy;", "createManualSelectionStrategy", "()Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeManualSelectionStrategy;", "updateSettingsFromJson", "(Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelectionSettings;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlayStyle;", "style", "Lcom/scandit/datacapture/barcode/selection/internal/module/ui/overlay/NativeBarcodeSelectionBasicOverlay;", "createBasicOverlay", "(Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlayStyle;)Lcom/scandit/datacapture/barcode/selection/internal/module/ui/overlay/NativeBarcodeSelectionBasicOverlay;", "overlay", "updateBasicOverlayFromJson", "(Lcom/scandit/datacapture/barcode/selection/internal/module/ui/overlay/NativeBarcodeSelectionBasicOverlay;Lcom/scandit/datacapture/core/internal/sdk/json/NativeJsonValue;)V", "Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "", "added", "changeBasicOverlayAddedToView", "(Lcom/scandit/datacapture/barcode/selection/internal/module/ui/overlay/NativeBarcodeSelectionBasicOverlay;Lcom/scandit/datacapture/core/internal/module/ui/NativeDataCaptureView;Z)V", "Lcom/scandit/datacapture/core/internal/module/source/NativeCameraSettings;", "createRecommendedCameraSettings", "()Lcom/scandit/datacapture/core/internal/module/source/NativeCameraSettings;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeSelectionDeserializerHelperReversedAdapter extends NativeBarcodeSelectionDeserializerHelper {
    private final BarcodeSelectionDeserializerHelper a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;

    public BarcodeSelectionDeserializerHelperReversedAdapter(BarcodeSelectionDeserializerHelper _BarcodeSelectionDeserializerHelper, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_BarcodeSelectionDeserializerHelper, "_BarcodeSelectionDeserializerHelper");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodeSelectionDeserializerHelper;
        this.proxyCache = proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public void applySettings(NativeBarcodeSelection mode, NativeBarcodeSelectionSettings settings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.a.applySettings((BarcodeSelection) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeSelection.class), null, mode), (BarcodeSelectionSettings) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionSettings.class), null, settings));
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public void changeBasicOverlayAddedToView(NativeBarcodeSelectionBasicOverlay overlay, NativeDataCaptureView view, boolean added) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(view, "view");
        this.a.changeBasicOverlayAddedToView((BarcodeSelectionBasicOverlay) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionBasicOverlay.class), null, overlay), (DataCaptureView) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeDataCaptureView.class), null, view), added);
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public NativeAimerSelection createAimerSelection() {
        BarcodeSelectionAimerSelection createAimerSelection = this.a.createAimerSelection();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeSelectionAimerSelection.class), null, createAimerSelection, createAimerSelection.getA());
        NativeAimerSelection a = createAimerSelection.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeAimerSelection.class), null, a, createAimerSelection);
        return a;
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public NativeAutoSelectionStrategy createAutoSelectionStrategy() {
        BarcodeSelectionAutoSelectionStrategy createAutoSelectionStrategy = this.a.createAutoSelectionStrategy();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeSelectionAutoSelectionStrategy.class), null, createAutoSelectionStrategy, createAutoSelectionStrategy.getA());
        NativeAutoSelectionStrategy a = createAutoSelectionStrategy.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeAutoSelectionStrategy.class), null, a, createAutoSelectionStrategy);
        return a;
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public NativeBarcodeSelectionBasicOverlay createBasicOverlay(NativeBarcodeSelection mode, BarcodeSelectionBasicOverlayStyle style) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(style, "style");
        BarcodeSelectionBasicOverlay createBasicOverlay = this.a.createBasicOverlay((BarcodeSelection) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeSelection.class), null, mode), style);
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeSelectionBasicOverlay.class), null, createBasicOverlay, createBasicOverlay._impl());
        NativeBarcodeSelectionBasicOverlay _impl = createBasicOverlay._impl();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionBasicOverlay.class), null, _impl, createBasicOverlay);
        return _impl;
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public NativeManualSelectionStrategy createManualSelectionStrategy() {
        BarcodeSelectionManualSelectionStrategy createManualSelectionStrategy = this.a.createManualSelectionStrategy();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeSelectionManualSelectionStrategy.class), null, createManualSelectionStrategy, createManualSelectionStrategy.getA());
        NativeManualSelectionStrategy a = createManualSelectionStrategy.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeManualSelectionStrategy.class), null, a, createManualSelectionStrategy);
        return a;
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public NativeBarcodeSelection createMode(NativeDataCaptureContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BarcodeSelection createMode = this.a.createMode((DataCaptureContext) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, context, new h(context)));
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeSelection.class), null, createMode, createMode._impl());
        NativeBarcodeSelection _impl = createMode._impl();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeSelection.class), null, _impl, createMode);
        return _impl;
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public NativeCameraSettings createRecommendedCameraSettings() {
        return CoreNativeTypeFactory.INSTANCE.convert(this.a.createRecommendedCameraSettings());
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public NativeBarcodeSelectionSettings createSettings() {
        BarcodeSelectionSettings createSettings = this.a.createSettings();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeSelectionSettings.class), null, createSettings, createSettings.getA());
        NativeBarcodeSelectionSettings a = createSettings.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionSettings.class), null, a, createSettings);
        return a;
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public NativeTapSelection createTapSelection() {
        BarcodeSelectionTapSelection createTapSelection = this.a.createTapSelection();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(BarcodeSelectionTapSelection.class), null, createTapSelection, createTapSelection.getA());
        NativeTapSelection a = createTapSelection.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeTapSelection.class), null, a, createTapSelection);
        return a;
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public void updateBasicOverlayFromJson(NativeBarcodeSelectionBasicOverlay overlay, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.updateBasicOverlayFromJson((BarcodeSelectionBasicOverlay) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionBasicOverlay.class), null, overlay), (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new i(json)));
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public void updateModeFromJson(NativeBarcodeSelection mode, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.updateModeFromJson((BarcodeSelection) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeSelection.class), null, mode), (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new j(json)));
    }

    @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializerHelper
    public void updateSettingsFromJson(NativeBarcodeSelectionSettings settings, NativeJsonValue json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.updateSettingsFromJson((BarcodeSelectionSettings) this.proxyCache.require(Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionSettings.class), null, settings), (JsonValue) this.proxyCache.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new k(json)));
    }

    public /* synthetic */ BarcodeSelectionDeserializerHelperReversedAdapter(BarcodeSelectionDeserializerHelper barcodeSelectionDeserializerHelper, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeSelectionDeserializerHelper, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
