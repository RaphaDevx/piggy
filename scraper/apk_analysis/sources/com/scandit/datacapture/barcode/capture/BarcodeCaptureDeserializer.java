package com.scandit.datacapture.barcode.capture;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializer;
import com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlay;
import com.scandit.datacapture.core.area.serialization.LocationSelectionDeserializer;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureModeDeserializer;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.ui.viewfinder.serialization.ViewfinderDeserializer;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import com.scandit.datacapture.tools.internal.sdk.ProxyGetter;
import com.scandit.datacapture.tools.internal.sdk.ProxyGetterKind;
import com.scandit.datacapture.tools.internal.sdk.ProxySetter;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0002FGB\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u0000H\u0097\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007H\u0097\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nH\u0097\u0001¢\u0006\u0004\b\u000b\u0010\fJ \u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0097\u0001¢\u0006\u0004\b\u0012\u0010\u0013J \u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0097\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0018\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0000H\u0097\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u000fH\u0097\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ \u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0097\u0001¢\u0006\u0004\b\u001f\u0010 J \u0010\"\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u000fH\u0097\u0001¢\u0006\u0004\b\"\u0010#J \u0010%\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u000fH\u0097\u0001¢\u0006\u0004\b%\u0010&J\u001d\u0010'\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b'\u0010\u0013J\u0015\u0010(\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b(\u0010\u001eJ\u001d\u0010)\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b)\u0010\u0017R\u001a\u0010/\u001a\u00020*8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u001a\u00105\u001a\u0002008\u0000X\u0080\u0004¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R$\u0010=\u001a\u0004\u0018\u0001068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u0014\u0010A\u001a\u00020>8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b?\u0010@R\u001a\u0010E\u001a\b\u0012\u0004\u0012\u00020\u000f0B8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\bC\u0010D¨\u0006H"}, d2 = {"Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializer;", "Lcom/scandit/datacapture/core/capture/serialization/DataCaptureModeDeserializer;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerProxy;", "<init>", "()V", "_deserializer", "()Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializer;", "Lcom/scandit/datacapture/barcode/internal/module/serialization/NativeBarcodeCaptureDeserializer;", "_impl", "()Lcom/scandit/datacapture/barcode/internal/module/serialization/NativeBarcodeCaptureDeserializer;", "Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureModeDeserializer;", "_modeDeserializerImpl", "()Lcom/scandit/datacapture/core/internal/module/serialization/NativeDataCaptureModeDeserializer;", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "", "jsonData", "Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "_modeFromJson", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "mode", "Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;", "_overlayFromJson", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;", "deserializer", "", "_setDeserializer", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializer;)V", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;", "_settingsFromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;", "updateModeFromJson", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "overlay", "updateOverlayFromJson", "(Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/ui/overlay/BarcodeCaptureOverlay;", "settings", "updateSettingsFromJson", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;", "modeFromJson", "settingsFromJson", "overlayFromJson", "Lcom/scandit/datacapture/core/area/serialization/LocationSelectionDeserializer;", "a", "Lcom/scandit/datacapture/core/area/serialization/LocationSelectionDeserializer;", "getLocationSelectionDeserializer$scandit_barcode_capture", "()Lcom/scandit/datacapture/core/area/serialization/LocationSelectionDeserializer;", "locationSelectionDeserializer", "Lcom/scandit/datacapture/core/ui/viewfinder/serialization/ViewfinderDeserializer;", "b", "Lcom/scandit/datacapture/core/ui/viewfinder/serialization/ViewfinderDeserializer;", "getViewfinderDeserializer$scandit_barcode_capture", "()Lcom/scandit/datacapture/core/ui/viewfinder/serialization/ViewfinderDeserializer;", "viewfinderDeserializer", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerListener;", "e", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerListener;", "getListener", "()Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerListener;", "setListener", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerListener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerHelper;", "get_helper", "()Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializerHelper;", "_helper", "", "getWarnings", "()Ljava/util/List;", "warnings", "com/scandit/datacapture/barcode/capture/e", "com/scandit/datacapture/barcode/capture/f", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeCaptureDeserializer implements DataCaptureModeDeserializer, BarcodeCaptureDeserializerProxy {

    /* renamed from: a, reason: from kotlin metadata */
    private final LocationSelectionDeserializer locationSelectionDeserializer;

    /* renamed from: b, reason: from kotlin metadata */
    private final ViewfinderDeserializer viewfinderDeserializer;
    private final C0274f c;
    private final /* synthetic */ BarcodeCaptureDeserializerProxyAdapter d;

    /* renamed from: e, reason: from kotlin metadata */
    private BarcodeCaptureDeserializerListener listener;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeCaptureDeserializer() {
        LocationSelectionDeserializer locationSelectionDeserializer = new LocationSelectionDeserializer();
        ViewfinderDeserializer viewfinderDeserializer = new ViewfinderDeserializer();
        C0274f c0274f = new C0274f();
        int i = 2;
        BarcodeCaptureDeserializerHelperReversedAdapter barcodeCaptureDeserializerHelperReversedAdapter = new BarcodeCaptureDeserializerHelperReversedAdapter(c0274f, null, i, 0 == true ? 1 : 0);
        NativeBarcodeCaptureDeserializer create = NativeBarcodeCaptureDeserializer.create(locationSelectionDeserializer._impl(), viewfinderDeserializer._impl(), barcodeCaptureDeserializerHelperReversedAdapter);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        this.locationSelectionDeserializer = locationSelectionDeserializer;
        this.viewfinderDeserializer = viewfinderDeserializer;
        this.c = c0274f;
        this.d = new BarcodeCaptureDeserializerProxyAdapter(create, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
        ProxyCacheKt.getGlobalProxyCache().getOrPut(Reflection.getOrCreateKotlinClass(BarcodeCaptureDeserializerHelper.class), null, c0274f, new C0272d(barcodeCaptureDeserializerHelperReversedAdapter));
        _setDeserializer(this);
        create.setListener(new BarcodeCaptureDeserializerListenerReversedAdapter(new C0273e(this), this, null, 4, null));
        WeakReference weakReference = new WeakReference(this);
        Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
        c0274f.a = weakReference;
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    @ProxyGetter(ProxyGetterKind.WITH_SETTER)
    public BarcodeCaptureDeserializer _deserializer() {
        return this.d._deserializer();
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeCaptureDeserializer getA() {
        return this.d.getA();
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer
    @NativeImpl
    /* renamed from: _modeDeserializerImpl */
    public NativeDataCaptureModeDeserializer getC() {
        return this.d.getC();
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    @ProxyFunction(nativeName = "barcodeCaptureFromJson")
    public BarcodeCapture _modeFromJson(DataCaptureContext dataCaptureContext, String jsonData) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        return this.d._modeFromJson(dataCaptureContext, jsonData);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    @ProxyFunction(nativeName = "barcodeCaptureOverlayFromJson")
    public BarcodeCaptureOverlay _overlayFromJson(BarcodeCapture mode, String jsonData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        return this.d._overlayFromJson(mode, jsonData);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    @ProxySetter
    public void _setDeserializer(BarcodeCaptureDeserializer deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        this.d._setDeserializer(deserializer);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    @ProxyFunction(nativeName = "settingsFromJson")
    public BarcodeCaptureSettings _settingsFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        return this.d._settingsFromJson(jsonData);
    }

    public final BarcodeCaptureDeserializerListener getListener() {
        return this.listener;
    }

    /* renamed from: getLocationSelectionDeserializer$scandit_barcode_capture, reason: from getter */
    public final LocationSelectionDeserializer getLocationSelectionDeserializer() {
        return this.locationSelectionDeserializer;
    }

    /* renamed from: getViewfinderDeserializer$scandit_barcode_capture, reason: from getter */
    public final ViewfinderDeserializer getViewfinderDeserializer() {
        return this.viewfinderDeserializer;
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    @ProxyFunction(property = "warnings")
    public List<String> getWarnings() {
        return this.d.getWarnings();
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureModeDeserializer
    @ProxyFunction(nativeName = "getHelper", property = "_helper")
    public BarcodeCaptureDeserializerHelper get_helper() {
        return this.d.get_helper();
    }

    public final BarcodeCapture modeFromJson(DataCaptureContext dataCaptureContext, String jsonData) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        BarcodeCapture _modeFromJson = _modeFromJson(dataCaptureContext, jsonData);
        this.c.getClass();
        return _modeFromJson;
    }

    public final BarcodeCaptureOverlay overlayFromJson(BarcodeCapture mode, String jsonData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        BarcodeCaptureOverlay _overlayFromJson = _overlayFromJson(mode, jsonData);
        this.c.getClass();
        return _overlayFromJson;
    }

    public final void setListener(BarcodeCaptureDeserializerListener barcodeCaptureDeserializerListener) {
        this.listener = barcodeCaptureDeserializerListener;
    }

    public final BarcodeCaptureSettings settingsFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        BarcodeCaptureSettings _settingsFromJson = _settingsFromJson(jsonData);
        this.c.getClass();
        return _settingsFromJson;
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    @ProxyFunction(nativeName = "updateBarcodeCaptureFromJson")
    public BarcodeCapture updateModeFromJson(BarcodeCapture mode, String jsonData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        return this.d.updateModeFromJson(mode, jsonData);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    @ProxyFunction(nativeName = "updateBarcodeCaptureOverlayFromJson")
    public BarcodeCaptureOverlay updateOverlayFromJson(BarcodeCaptureOverlay overlay, String jsonData) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        return this.d.updateOverlayFromJson(overlay, jsonData);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerProxy
    @ProxyFunction
    public BarcodeCaptureSettings updateSettingsFromJson(BarcodeCaptureSettings settings, String jsonData) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        return this.d.updateSettingsFromJson(settings, jsonData);
    }
}
