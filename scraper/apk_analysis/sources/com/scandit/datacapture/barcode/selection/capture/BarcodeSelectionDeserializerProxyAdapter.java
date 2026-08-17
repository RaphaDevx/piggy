package com.scandit.datacapture.barcode.selection.capture;

import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings;
import com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializer;
import com.scandit.datacapture.barcode.selection.internal.module.ui.overlay.NativeBarcodeSelectionBasicOverlay;
import com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlay;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureModeDeserializer;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010\"\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\"\u0010#J\u001f\u0010%\u001a\u00020$2\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b%\u0010&J\u001f\u0010(\u001a\u00020$2\u0006\u0010'\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b(\u0010)R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0014\u00101\u001a\u00020.8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020\u0016028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u00104¨\u00066"}, d2 = {"Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializerProxyAdapter;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializerProxy;", "Lcom/scandit/datacapture/barcode/selection/internal/module/serialization/NativeBarcodeSelectionDeserializer;", "_NativeBarcodeSelectionDeserializer", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/selection/internal/module/serialization/NativeBarcodeSelectionDeserializer;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/selection/internal/module/serialization/NativeBarcodeSelectionDeserializer;", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureModeDeserializer;", "_modeDeserializerImpl", "()Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureModeDeserializer;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializer;", "deserializer", "", "_setDeserializer", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializer;)V", "_deserializer", "()Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializer;", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "", "jsonData", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;", "_modeFromJson", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;", "mode", "updateModeFromJson", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSettings;", "_settingsFromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSettings;", "settings", "updateSettingsFromJson", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSettings;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSettings;", "Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlay;", "_basicOverlayFromJson", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlay;", "overlay", "updateBasicOverlayFromJson", "(Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlay;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlay;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializerHelper;", "get_helper", "()Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializerHelper;", "_helper", "", "getWarnings", "()Ljava/util/List;", "warnings", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeSelectionDeserializerProxyAdapter implements BarcodeSelectionDeserializerProxy {
    private final NativeBarcodeSelectionDeserializer a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeDataCaptureModeDeserializer c;
    private BarcodeSelectionDeserializer d;

    public BarcodeSelectionDeserializerProxyAdapter(NativeBarcodeSelectionDeserializer _NativeBarcodeSelectionDeserializer, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeBarcodeSelectionDeserializer, "_NativeBarcodeSelectionDeserializer");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodeSelectionDeserializer;
        this.proxyCache = proxyCache;
        NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer = _NativeBarcodeSelectionDeserializer.asDataCaptureModeDeserializer();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureModeDeserializer, "asDataCaptureModeDeserializer(...)");
        this.c = asDataCaptureModeDeserializer;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerProxy
    public BarcodeSelectionBasicOverlay _basicOverlayFromJson(BarcodeSelection mode, String jsonData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeBarcodeSelection a = mode.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeSelection.class), null, a, mode);
        NativeBarcodeSelectionBasicOverlay barcodeSelectionBasicOverlayFromJson = this.a.barcodeSelectionBasicOverlayFromJson(a, CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionBasicOverlay.class);
        Intrinsics.checkNotNull(barcodeSelectionBasicOverlayFromJson);
        return (BarcodeSelectionBasicOverlay) proxyCache.require(orCreateKotlinClass, null, barcodeSelectionBasicOverlayFromJson);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerProxy
    public BarcodeSelectionDeserializer _deserializer() {
        BarcodeSelectionDeserializer barcodeSelectionDeserializer = this.d;
        if (barcodeSelectionDeserializer != null) {
            return barcodeSelectionDeserializer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("_setDeserializer_backing_field");
        return null;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerProxy
    /* renamed from: _impl, reason: from getter */
    public NativeBarcodeSelectionDeserializer getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerProxy, com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer
    /* renamed from: _modeDeserializerImpl, reason: from getter */
    public NativeDataCaptureModeDeserializer getC() {
        return this.c;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerProxy
    public BarcodeSelection _modeFromJson(DataCaptureContext dataCaptureContext, String jsonData) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeDataCaptureContext a = dataCaptureContext.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, a, dataCaptureContext);
        NativeBarcodeSelection barcodeSelectionFromJson = this.a.barcodeSelectionFromJson(a, CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeSelection.class);
        Intrinsics.checkNotNull(barcodeSelectionFromJson);
        return (BarcodeSelection) proxyCache.require(orCreateKotlinClass, null, barcodeSelectionFromJson);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerProxy
    public void _setDeserializer(BarcodeSelectionDeserializer deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        this.d = deserializer;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerProxy
    public BarcodeSelectionSettings _settingsFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeBarcodeSelectionSettings nativeBarcodeSelectionSettings = this.a.settingsFromJson(CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionSettings.class);
        Intrinsics.checkNotNull(nativeBarcodeSelectionSettings);
        return (BarcodeSelectionSettings) proxyCache.require(orCreateKotlinClass, null, nativeBarcodeSelectionSettings);
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerProxy
    public List<String> getWarnings() {
        ArrayList<String> warnings = this.a.getWarnings();
        Intrinsics.checkNotNull(warnings);
        return warnings;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerProxy
    public BarcodeSelectionBasicOverlay updateBasicOverlayFromJson(BarcodeSelectionBasicOverlay overlay, String jsonData) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeBarcodeSelectionBasicOverlay a = overlay.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionBasicOverlay.class), null, a, overlay);
        NativeBarcodeSelectionBasicOverlay updateBarcodeSelectionBasicOverlayFromJson = this.a.updateBarcodeSelectionBasicOverlayFromJson(a, CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionBasicOverlay.class);
        Intrinsics.checkNotNull(updateBarcodeSelectionBasicOverlayFromJson);
        return (BarcodeSelectionBasicOverlay) proxyCache.require(orCreateKotlinClass, null, updateBarcodeSelectionBasicOverlayFromJson);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerProxy
    public BarcodeSelection updateModeFromJson(BarcodeSelection mode, String jsonData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeBarcodeSelection a = mode.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeSelection.class), null, a, mode);
        NativeBarcodeSelection updateBarcodeSelectionFromJson = this.a.updateBarcodeSelectionFromJson(a, CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeSelection.class);
        Intrinsics.checkNotNull(updateBarcodeSelectionFromJson);
        return (BarcodeSelection) proxyCache.require(orCreateKotlinClass, null, updateBarcodeSelectionFromJson);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerProxy
    public BarcodeSelectionSettings updateSettingsFromJson(BarcodeSelectionSettings settings, String jsonData) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeBarcodeSelectionSettings a = settings.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionSettings.class), null, a, settings);
        NativeBarcodeSelectionSettings updateSettingsFromJson = this.a.updateSettingsFromJson(a, CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeSelectionSettings.class);
        Intrinsics.checkNotNull(updateSettingsFromJson);
        return (BarcodeSelectionSettings) proxyCache.require(orCreateKotlinClass, null, updateSettingsFromJson);
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer
    public BarcodeSelectionDeserializerHelper get_helper() {
        return (BarcodeSelectionDeserializerHelper) this.proxyCache.requireByValue(Reflection.getOrCreateKotlinClass(BarcodeSelectionDeserializerHelper.class), this.a.getHelper());
    }

    public /* synthetic */ BarcodeSelectionDeserializerProxyAdapter(NativeBarcodeSelectionDeserializer nativeBarcodeSelectionDeserializer, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeBarcodeSelectionDeserializer, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
