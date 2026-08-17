package com.scandit.datacapture.barcode.pick.serialization;

import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSettings;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeProductProvider;
import com.scandit.datacapture.barcode.internal.module.pick.data.NativeBarcodePickProduct;
import com.scandit.datacapture.barcode.internal.module.pick.serialization.BarcodePickDeserializerHelper;
import com.scandit.datacapture.barcode.internal.module.pick.serialization.NativeBarcodePickDeserializer;
import com.scandit.datacapture.barcode.internal.module.pick.ui.BarcodePickBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickSettings;
import com.scandit.datacapture.barcode.pick.data.BarcodePickProduct;
import com.scandit.datacapture.barcode.pick.data.BarcodePickProductProvider;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettings;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureModeDeserializer;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010!\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b!\u0010\"J\u001f\u0010$\u001a\u00020 2\u0006\u0010#\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b$\u0010%J\u0017\u0010'\u001a\u00020&2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b'\u0010(J\u001f\u0010)\u001a\u00020&2\u0006\u0010#\u001a\u00020&2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b)\u0010*J\u001f\u0010-\u001a\u00020,2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020+H\u0016¢\u0006\u0004\b-\u0010.J\u001f\u00100\u001a\u00020,2\u0006\u0010/\u001a\u00020,2\u0006\u0010\u0019\u001a\u00020+H\u0016¢\u0006\u0004\b0\u00101J\u001d\u00104\u001a\b\u0012\u0004\u0012\u000203022\u0006\u0010\u0019\u001a\u00020+H\u0016¢\u0006\u0004\b4\u00105R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u0014\u0010=\u001a\u00020:8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<¨\u0006>"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializerProxyAdapter;", "Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializerProxy;", "Lcom/scandit/datacapture/barcode/internal/module/pick/serialization/NativeBarcodePickDeserializer;", "_NativeBarcodePickDeserializer", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "proxyCache", "<init>", "(Lcom/scandit/datacapture/barcode/internal/module/pick/serialization/NativeBarcodePickDeserializer;Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;)V", "_impl", "()Lcom/scandit/datacapture/barcode/internal/module/pick/serialization/NativeBarcodePickDeserializer;", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureModeDeserializer;", "_modeDeserializerImpl", "()Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureModeDeserializer;", "Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializer;", "deserializerProxy", "", "_setDeserializer", "(Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializer;)V", "_deserializer", "()Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializer;", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "context", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProvider;", "productProvider", "", "json", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;", "_modeFromJson", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProvider;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;", "mode", "updateModeFromJson", "(Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSettings;", "_settingsFromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSettings;", "settings", "updateSettingsFromJson", "(Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSettings;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSettings;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;", "_viewSettingsFromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;", "_updateViewSettingsFromJson", "(Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewSettings;", "Lcom/scandit/datacapture/core/json/JsonValue;", "Lcom/scandit/datacapture/barcode/internal/module/pick/ui/BarcodePickBasicOverlay;", "_overlayFromJson", "(Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;Lcom/scandit/datacapture/core/json/JsonValue;)Lcom/scandit/datacapture/barcode/internal/module/pick/ui/BarcodePickBasicOverlay;", "overlay", "_updateOverlayFromJson", "(Lcom/scandit/datacapture/barcode/internal/module/pick/ui/BarcodePickBasicOverlay;Lcom/scandit/datacapture/core/json/JsonValue;)Lcom/scandit/datacapture/barcode/internal/module/pick/ui/BarcodePickBasicOverlay;", "", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProduct;", "_productsFromJson", "(Lcom/scandit/datacapture/core/json/JsonValue;)Ljava/util/List;", "b", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "getProxyCache$scandit_barcode_capture", "()Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "Lcom/scandit/datacapture/barcode/internal/module/pick/serialization/BarcodePickDeserializerHelper;", "get_helper", "()Lcom/scandit/datacapture/barcode/internal/module/pick/serialization/BarcodePickDeserializerHelper;", "_helper", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodePickDeserializerProxyAdapter implements BarcodePickDeserializerProxy {
    private final NativeBarcodePickDeserializer a;

    /* renamed from: b, reason: from kotlin metadata */
    private final ProxyCache proxyCache;
    private final NativeDataCaptureModeDeserializer c;
    private BarcodePickDeserializer d;

    public BarcodePickDeserializerProxyAdapter(NativeBarcodePickDeserializer _NativeBarcodePickDeserializer, ProxyCache proxyCache) {
        Intrinsics.checkNotNullParameter(_NativeBarcodePickDeserializer, "_NativeBarcodePickDeserializer");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodePickDeserializer;
        this.proxyCache = proxyCache;
        NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer = _NativeBarcodePickDeserializer.asDataCaptureModeDeserializer();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureModeDeserializer, "asDataCaptureModeDeserializer(...)");
        this.c = asDataCaptureModeDeserializer;
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    public BarcodePickDeserializer _deserializer() {
        BarcodePickDeserializer barcodePickDeserializer = this.d;
        if (barcodePickDeserializer != null) {
            return barcodePickDeserializer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("_setDeserializer_backing_field");
        return null;
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    /* renamed from: _impl, reason: from getter */
    public NativeBarcodePickDeserializer getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy, com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer
    /* renamed from: _modeDeserializerImpl, reason: from getter */
    public NativeDataCaptureModeDeserializer getC() {
        return this.c;
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    public BarcodePick _modeFromJson(DataCaptureContext context, BarcodePickProductProvider productProvider, String json) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(productProvider, "productProvider");
        Intrinsics.checkNotNullParameter(json, "json");
        NativeDataCaptureContext a = context.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureContext.class), null, a, context);
        NativeProductProvider c = productProvider.getC();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeProductProvider.class), null, c, productProvider);
        NativeBarcodePick barcodePickFromJson = this.a.barcodePickFromJson(a, c, CoreNativeTypeFactory.INSTANCE.convert(json));
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(barcodePickFromJson);
        return barcodeNativeTypeFactory.convert(barcodePickFromJson);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    public BarcodePickBasicOverlay _overlayFromJson(BarcodePick mode, JsonValue json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        NativeBarcodePick convert = BarcodeNativeTypeFactory.INSTANCE.convert(mode);
        NativeJsonValue a = json.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, a, json);
        NativeDataCaptureOverlay overlayFromJson = this.a.overlayFromJson(convert, a);
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeDataCaptureOverlay.class);
        Intrinsics.checkNotNull(overlayFromJson);
        return (BarcodePickBasicOverlay) proxyCache.require(orCreateKotlinClass, null, overlayFromJson);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    public List<BarcodePickProduct> _productsFromJson(JsonValue json) {
        Intrinsics.checkNotNullParameter(json, "json");
        NativeJsonValue a = json.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, a, json);
        ArrayList<NativeBarcodePickProduct> barcodePickProductsFromJson = this.a.barcodePickProductsFromJson(a);
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        Intrinsics.checkNotNull(barcodePickProductsFromJson);
        return barcodeNativeTypeFactory.convertBarcodePickProductFromNativeImpl(barcodePickProductsFromJson);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    public void _setDeserializer(BarcodePickDeserializer deserializerProxy) {
        Intrinsics.checkNotNullParameter(deserializerProxy, "deserializerProxy");
        this.d = deserializerProxy;
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    public BarcodePickSettings _settingsFromJson(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        NativeBarcodePickSettings nativeBarcodePickSettings = this.a.settingsFromJson(CoreNativeTypeFactory.INSTANCE.convert(json));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodePickSettings.class);
        Intrinsics.checkNotNull(nativeBarcodePickSettings);
        return (BarcodePickSettings) proxyCache.require(orCreateKotlinClass, null, nativeBarcodePickSettings);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    public BarcodePickBasicOverlay _updateOverlayFromJson(BarcodePickBasicOverlay overlay, JsonValue json) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        NativeDataCaptureOverlay c = overlay.getC();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeDataCaptureOverlay.class), null, c, overlay);
        NativeJsonValue a = json.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, a, json);
        NativeDataCaptureOverlay updateOverlayFromJson = this.a.updateOverlayFromJson(c, a);
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeDataCaptureOverlay.class);
        Intrinsics.checkNotNull(updateOverlayFromJson);
        return (BarcodePickBasicOverlay) proxyCache.require(orCreateKotlinClass, null, updateOverlayFromJson);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    public BarcodePickViewSettings _updateViewSettingsFromJson(BarcodePickViewSettings settings, String json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        NativeBarcodePickViewSettings a = settings.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodePickViewSettings.class), null, a, settings);
        NativeBarcodePickViewSettings updateViewSettingsFromJson = this.a.updateViewSettingsFromJson(a, CoreNativeTypeFactory.INSTANCE.convert(json));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodePickViewSettings.class);
        Intrinsics.checkNotNull(updateViewSettingsFromJson);
        return (BarcodePickViewSettings) proxyCache.require(orCreateKotlinClass, null, updateViewSettingsFromJson);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    public BarcodePickViewSettings _viewSettingsFromJson(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        NativeBarcodePickViewSettings viewSettingsFromJson = this.a.viewSettingsFromJson(CoreNativeTypeFactory.INSTANCE.convert(json));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodePickViewSettings.class);
        Intrinsics.checkNotNull(viewSettingsFromJson);
        return (BarcodePickViewSettings) proxyCache.require(orCreateKotlinClass, null, viewSettingsFromJson);
    }

    /* renamed from: getProxyCache$scandit_barcode_capture, reason: from getter */
    public final ProxyCache getProxyCache() {
        return this.proxyCache;
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    public BarcodePick updateModeFromJson(BarcodePick mode, String json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        NativeBarcodePick updateBarcodePickFromJson = this.a.updateBarcodePickFromJson(barcodeNativeTypeFactory.convert(mode), CoreNativeTypeFactory.INSTANCE.convert(json));
        Intrinsics.checkNotNull(updateBarcodePickFromJson);
        return barcodeNativeTypeFactory.convert(updateBarcodePickFromJson);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerProxy
    public BarcodePickSettings updateSettingsFromJson(BarcodePickSettings settings, String json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        NativeBarcodePickSettings a = settings.getA();
        this.proxyCache.put(Reflection.getOrCreateKotlinClass(NativeBarcodePickSettings.class), null, a, settings);
        NativeBarcodePickSettings updateSettingsFromJson = this.a.updateSettingsFromJson(a, CoreNativeTypeFactory.INSTANCE.convert(json));
        ProxyCache proxyCache = this.proxyCache;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(NativeBarcodePickSettings.class);
        Intrinsics.checkNotNull(updateSettingsFromJson);
        return (BarcodePickSettings) proxyCache.require(orCreateKotlinClass, null, updateSettingsFromJson);
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer
    public BarcodePickDeserializerHelper get_helper() {
        return (BarcodePickDeserializerHelper) this.proxyCache.requireByValue(Reflection.getOrCreateKotlinClass(BarcodePickDeserializerHelper.class), this.a.getHelper());
    }

    public /* synthetic */ BarcodePickDeserializerProxyAdapter(NativeBarcodePickDeserializer nativeBarcodePickDeserializer, ProxyCache proxyCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nativeBarcodePickDeserializer, (i & 2) != 0 ? ProxyCacheKt.getGlobalProxyCache() : proxyCache);
    }
}
