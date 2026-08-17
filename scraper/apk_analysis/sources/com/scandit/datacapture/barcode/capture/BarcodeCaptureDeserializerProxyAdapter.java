package com.scandit.datacapture.barcode.capture;

import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture;
import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureSettings;
import com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializer;
import com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay;
import com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlay;
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

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010\"\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\"\u0010#J\u001f\u0010%\u001a\u00020$2\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b%\u0010&J\u001f\u0010(\u001a\u00020$2\u0006\u0010'\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b(\u0010)R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0014\u00101\u001a\u00020.8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020\u0016028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u00104¨\u00066"}, d2 = {"Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerProxyAdapter;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerProxy;", "Lcom/scandit/datacapture/barcode/internal/module/serialization/NativeBarcodeCaptureDeserializer;", "_NativeBarcodeCaptureDeserializer", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/internal/module/serialization/NativeBarcodeCaptureDeserializer;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/internal/module/serialization/NativeBarcodeCaptureDeserializer;", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureModeDeserializer;", "_modeDeserializerImpl", "()Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureModeDeserializer;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializer;", "deserializer", "", "_setDeserializer", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializer;)V", "_deserializer", "()Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializer;", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "", "jsonData", "Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "_modeFromJson", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "mode", "updateModeFromJson", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;", "_settingsFromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;", "settings", "updateSettingsFromJson", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;", "Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;", "_overlayFromJson", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;", "overlay", "updateOverlayFromJson", "(Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerHelper;", "get_helper", "()Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerHelper;", "_helper", "", "getWarnings", "()Ljava/util/List;", "warnings", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeCaptureDeserializerProxyAdapter implements BarcodeCaptureDeserializerProxy {
    private final NativeBarcodeCaptureDeserializer a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeDataCaptureModeDeserializer c;
    private BarcodeCaptureDeserializer d;

    public BarcodeCaptureDeserializerProxyAdapter(NativeBarcodeCaptureDeserializer _NativeBarcodeCaptureDeserializer, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeBarcodeCaptureDeserializer, "_NativeBarcodeCaptureDeserializer");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodeCaptureDeserializer;
        this.proxyCache = proxyCache;
        NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer = _NativeBarcodeCaptureDeserializer.asDataCaptureModeDeserializer();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureModeDeserializer, "asDataCaptureModeDeserializer(...)");
        this.c = asDataCaptureModeDeserializer;
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    public BarcodeCaptureDeserializer _deserializer() {
        BarcodeCaptureDeserializer barcodeCaptureDeserializer = this.d;
        if (barcodeCaptureDeserializer != null) {
            return barcodeCaptureDeserializer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("_setDeserializer_backing_field");
        return null;
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    /* renamed from: _impl, reason: from getter */
    public NativeBarcodeCaptureDeserializer getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy, com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer
    /* renamed from: _modeDeserializerImpl, reason: from getter */
    public NativeDataCaptureModeDeserializer getC() {
        return this.c;
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    public BarcodeCapture _modeFromJson(DataCaptureContext dataCaptureContext, String jsonData) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeDataCaptureContext a = dataCaptureContext.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, a, dataCaptureContext);
        NativeBarcodeCapture barcodeCaptureFromJson = this.a.barcodeCaptureFromJson(a, CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeCapture.class);
        Intrinsics.checkNotNull(barcodeCaptureFromJson);
        return (BarcodeCapture) proxyCache.getOrPut(orCreateKotlinClass, null, barcodeCaptureFromJson, new F(barcodeCaptureFromJson));
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    public BarcodeCaptureOverlay _overlayFromJson(BarcodeCapture mode, String jsonData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeBarcodeCapture a = mode.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeCapture.class), null, a, mode);
        NativeBarcodeCaptureOverlay barcodeCaptureOverlayFromJson = this.a.barcodeCaptureOverlayFromJson(a, CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureOverlay.class);
        Intrinsics.checkNotNull(barcodeCaptureOverlayFromJson);
        return (BarcodeCaptureOverlay) proxyCache.require(orCreateKotlinClass, null, barcodeCaptureOverlayFromJson);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    public void _setDeserializer(BarcodeCaptureDeserializer deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        this.d = deserializer;
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    public BarcodeCaptureSettings _settingsFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeBarcodeCaptureSettings nativeBarcodeCaptureSettings = this.a.settingsFromJson(CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureSettings.class);
        Intrinsics.checkNotNull(nativeBarcodeCaptureSettings);
        return (BarcodeCaptureSettings) proxyCache.getOrPut(orCreateKotlinClass, null, nativeBarcodeCaptureSettings, new G(nativeBarcodeCaptureSettings));
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    public List<String> getWarnings() {
        ArrayList<String> warnings = this.a.getWarnings();
        Intrinsics.checkNotNull(warnings);
        return warnings;
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    public BarcodeCapture updateModeFromJson(BarcodeCapture mode, String jsonData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeBarcodeCapture a = mode.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeCapture.class), null, a, mode);
        NativeBarcodeCapture updateBarcodeCaptureFromJson = this.a.updateBarcodeCaptureFromJson(a, CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeCapture.class);
        Intrinsics.checkNotNull(updateBarcodeCaptureFromJson);
        return (BarcodeCapture) proxyCache.getOrPut(orCreateKotlinClass, null, updateBarcodeCaptureFromJson, new H(updateBarcodeCaptureFromJson));
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    public BarcodeCaptureOverlay updateOverlayFromJson(BarcodeCaptureOverlay overlay, String jsonData) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeBarcodeCaptureOverlay a = overlay.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureOverlay.class), null, a, overlay);
        NativeBarcodeCaptureOverlay updateBarcodeCaptureOverlayFromJson = this.a.updateBarcodeCaptureOverlayFromJson(a, CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureOverlay.class);
        Intrinsics.checkNotNull(updateBarcodeCaptureOverlayFromJson);
        return (BarcodeCaptureOverlay) proxyCache.require(orCreateKotlinClass, null, updateBarcodeCaptureOverlayFromJson);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    public BarcodeCaptureSettings updateSettingsFromJson(BarcodeCaptureSettings settings, String jsonData) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        NativeBarcodeCaptureSettings a = settings.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureSettings.class), null, a, settings);
        NativeBarcodeCaptureSettings updateSettingsFromJson = this.a.updateSettingsFromJson(a, CoreNativeTypeFactory.INSTANCE.convert(jsonData));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodeCaptureSettings.class);
        Intrinsics.checkNotNull(updateSettingsFromJson);
        return (BarcodeCaptureSettings) proxyCache.getOrPut(orCreateKotlinClass, null, updateSettingsFromJson, new I(updateSettingsFromJson));
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer
    public BarcodeCaptureDeserializerHelper get_helper() {
        return (BarcodeCaptureDeserializerHelper) this.proxyCache.requireByValue(Reflection.getOrCreateKotlinClass(BarcodeCaptureDeserializerHelper.class), this.a.getHelper());
    }

    public /* synthetic */ BarcodeCaptureDeserializerProxyAdapter(NativeBarcodeCaptureDeserializer nativeBarcodeCaptureDeserializer, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeBarcodeCaptureDeserializer, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
