package com.scandit.datacapture.barcode.selection.capture;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.selection.feedback.BarcodeSelectionFeedback;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionLicenseInfo;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.internal.sdk.extensions.CollectionsExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeExtensionsKt;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FocusGestureStrategy;
import com.scandit.datacapture.core.source.VideoResolution;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import io.sentry.cache.EnvelopeCache;
import io.sentry.protocol.Feedback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 T2\u00020\u00012\u00020\u0002:\u0002TUB\u0019\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tH\u0097\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0003H\u0097\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eH\u0097\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0011H\u0097\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u000eH\u0097\u0001¢\u0006\u0004\b\u0015\u0010\u0010J\u0010\u0010\u0016\u001a\u00020\u000eH\u0097\u0001¢\u0006\u0004\b\u0016\u0010\u0010J \u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0017H\u0097\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u000eH\u0097\u0001¢\u0006\u0004\b\u001b\u0010\u0010J\u0018\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0011H\u0097\u0001¢\u0006\u0004\b\u001c\u0010\u0014J\u0019\u0010\u001f\u001a\u00020\u000e2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0016¢\u0006\u0004\b\u001f\u0010 J#\u0010%\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#H\u0007¢\u0006\u0004\b%\u0010&J\u0015\u0010)\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020'¢\u0006\u0004\b)\u0010*J\u0015\u0010+\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020'¢\u0006\u0004\b+\u0010*J\u0015\u0010-\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u0011¢\u0006\u0004\b-\u0010\u0014J\r\u0010.\u001a\u00020\u0005¢\u0006\u0004\b.\u0010/J\u001d\u00102\u001a\u00020\u000e2\u0006\u00101\u001a\u0002002\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b2\u00103J\u001b\u00106\u001a\u00020\u000e2\f\u00105\u001a\b\u0012\u0004\u0012\u00020004¢\u0006\u0004\b6\u00107J\u001b\u00108\u001a\u00020\u000e2\f\u00105\u001a\b\u0012\u0004\u0012\u00020004¢\u0006\u0004\b8\u00107J\r\u00109\u001a\u00020\u000e¢\u0006\u0004\b9\u0010\u0010R*\u0010B\u001a\u00020:2\u0006\u0010;\u001a\u00020:8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR$\u0010D\u001a\u00020\u00172\u0006\u0010C\u001a\u00020\u00178W@WX\u0096\u000f¢\u0006\f\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR(\u0010M\u001a\u0004\u0018\u00010H2\b\u0010C\u001a\u0004\u0018\u00010H8W@WX\u0096\u000f¢\u0006\f\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0013\u0010S\u001a\u0004\u0018\u00010P8F¢\u0006\u0006\u001a\u0004\bQ\u0010R¨\u0006V"}, d2 = {"Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;", "Lcom/scandit/datacapture/core/capture/DataCaptureMode;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionProxy;", "Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;", "impl", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSession;", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "<init>", "(Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSession;)V", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureMode;", "_dataCaptureModeImpl", "()Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureMode;", "_impl", "()Lcom/scandit/datacapture/barcode/selection/internal/module/capture/NativeBarcodeSelection;", "", "freezeCamera", "()V", "", "json", "increaseCountForBarcodesFromJsonString", "(Ljava/lang/String;)V", "selectAimedBarcode", "selectUnselectedBarcodes", "", ExtentionsKt.ENABLED_KEY, "setSelectBarcodeEnabledFromJsonString", "(Ljava/lang/String;Z)V", "unfreezeCamera", "unselectBarcodesFromJsonString", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "_setDataCaptureContext", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;)V", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSettings;", "settings", "Ljava/lang/Runnable;", "whenDone", "applySettings", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSettings;Ljava/lang/Runnable;)V", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addListener", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionListener;)V", "removeListener", "jsonData", "updateFromJson", "_session", "()Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSession;", "Lcom/scandit/datacapture/barcode/data/Barcode;", "barcode", "setSelectBarcodeEnabled", "(Lcom/scandit/datacapture/barcode/data/Barcode;Z)V", "", "barcodes", "unselectBarcodes", "(Ljava/util/List;)V", "increaseCountForBarcodes", "reset", "Lcom/scandit/datacapture/barcode/selection/feedback/BarcodeSelectionFeedback;", "value", "d", "Lcom/scandit/datacapture/barcode/selection/feedback/BarcodeSelectionFeedback;", "getFeedback", "()Lcom/scandit/datacapture/barcode/selection/feedback/BarcodeSelectionFeedback;", "setFeedback", "(Lcom/scandit/datacapture/barcode/selection/feedback/BarcodeSelectionFeedback;)V", Feedback.TYPE, "<set-?>", "isEnabled", "()Z", "setEnabled", "(Z)V", "Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "getPointOfInterest", "()Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "setPointOfInterest", "(Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;)V", "pointOfInterest", "getDataCaptureContext", "()Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionLicenseInfo;", "getBarcodeSelectionLicenseInfo", "()Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionLicenseInfo;", "barcodeSelectionLicenseInfo", "Companion", "com/scandit/datacapture/barcode/selection/capture/c", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeSelection implements DataCaptureMode, BarcodeSelectionProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BarcodeSelectionSession a;
    private final /* synthetic */ BarcodeSelectionProxyAdapter b;
    private DataCaptureContext c;

    /* renamed from: d, reason: from kotlin metadata */
    private BarcodeSelectionFeedback feedback;
    private final CopyOnWriteArraySet e;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection$Companion;", "", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSettings;", "settings", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;", "forDataCaptureContext", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSettings;)Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;", "Lcom/scandit/datacapture/core/source/CameraSettings;", "createRecommendedCameraSettings", "()Lcom/scandit/datacapture/core/source/CameraSettings;", "", "jsonData", "fromJson", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final CameraSettings createRecommendedCameraSettings() {
            CameraSettings cameraSettings = new CameraSettings();
            cameraSettings.setShouldPreferSmoothAutoFocus(true);
            cameraSettings.setFocusGestureStrategy(FocusGestureStrategy.NONE);
            cameraSettings.setPreferredResolution(VideoResolution.FULL_HD);
            cameraSettings.setZoomGestureZoomFactor(1.0f);
            return cameraSettings;
        }

        @JvmStatic
        public final BarcodeSelection forDataCaptureContext(DataCaptureContext dataCaptureContext, BarcodeSelectionSettings settings) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            BarcodeSelection barcodeSelection = new BarcodeSelection(dataCaptureContext, settings, null);
            ProxyCacheKt.getGlobalProxyCache().getOrPut(Reflection.getOrCreateKotlinClass(BarcodeSelection.class), null, barcodeSelection, new C0498b(barcodeSelection));
            if (dataCaptureContext != null) {
                dataCaptureContext.setMode(barcodeSelection);
            }
            return barcodeSelection;
        }

        @JvmStatic
        public final BarcodeSelection fromJson(DataCaptureContext dataCaptureContext, String jsonData) {
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            return new BarcodeSelectionDeserializer().modeFromJson(dataCaptureContext, jsonData);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeSelection(NativeBarcodeSelection impl, BarcodeSelectionSession session) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        Intrinsics.checkNotNullParameter(session, "session");
        this.a = session;
        this.b = new BarcodeSelectionProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
        BarcodeSelectionFeedback defaultFeedback = BarcodeSelectionFeedback.INSTANCE.defaultFeedback();
        getA().setSelectionFeedback(new BarcodeSelection$setNativeFeedback$1(defaultFeedback));
        this.feedback = defaultFeedback;
        this.e = new CopyOnWriteArraySet();
        getA().addListenerAsync(new BarcodeSelectionListenerReversedAdapter(new C0499c(this), this, null, 4, null));
    }

    public static /* synthetic */ void applySettings$default(BarcodeSelection barcodeSelection, BarcodeSelectionSettings barcodeSelectionSettings, Runnable runnable, int i, Object obj) {
        if ((i & 2) != 0) {
            runnable = null;
        }
        barcodeSelection.applySettings(barcodeSelectionSettings, runnable);
    }

    @JvmStatic
    public static final CameraSettings createRecommendedCameraSettings() {
        return INSTANCE.createRecommendedCameraSettings();
    }

    @JvmStatic
    public static final BarcodeSelection forDataCaptureContext(DataCaptureContext dataCaptureContext, BarcodeSelectionSettings barcodeSelectionSettings) {
        return INSTANCE.forDataCaptureContext(dataCaptureContext, barcodeSelectionSettings);
    }

    @JvmStatic
    public static final BarcodeSelection fromJson(DataCaptureContext dataCaptureContext, String str) {
        return INSTANCE.fromJson(dataCaptureContext, str);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    @NativeImpl
    /* renamed from: _dataCaptureModeImpl */
    public NativeDataCaptureMode getC() {
        return this.b.getC();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeSelection getA() {
        return this.b.getA();
    }

    /* renamed from: _session, reason: from getter */
    public final BarcodeSelectionSession getA() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    public void _setDataCaptureContext(DataCaptureContext dataCaptureContext) {
        this.c = dataCaptureContext;
    }

    public final void addListener(BarcodeSelectionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.e.add(listener)) {
            listener.onObservationStarted(this);
        }
    }

    public final void applySettings(BarcodeSelectionSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        applySettings$default(this, settings, null, 2, null);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    @ProxyFunction
    public void freezeCamera() {
        this.b.freezeCamera();
    }

    public final BarcodeSelectionLicenseInfo getBarcodeSelectionLicenseInfo() {
        NativeBarcodeSelectionLicenseInfo barcodeSelectionLicenseInfo = getA().getBarcodeSelectionLicenseInfo();
        if (barcodeSelectionLicenseInfo != null) {
            return new BarcodeSelectionLicenseInfo(barcodeSelectionLicenseInfo);
        }
        return null;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    /* renamed from: getDataCaptureContext, reason: from getter */
    public DataCaptureContext getC() {
        return this.c;
    }

    public final BarcodeSelectionFeedback getFeedback() {
        return this.feedback;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    @ProxyFunction(property = "pointOfInterest")
    public PointWithUnit getPointOfInterest() {
        return this.b.getPointOfInterest();
    }

    public final void increaseCountForBarcodes(List<Barcode> barcodes) {
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        NativeBarcodeSelection a = getA();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(barcodes, 10));
        Iterator<T> it = barcodes.iterator();
        while (it.hasNext()) {
            arrayList.add(((Barcode) it.next()).getA());
        }
        a.increaseCountForBarcodes(CollectionsExtensionsKt.toArrayList(arrayList));
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    @ProxyFunction
    public void increaseCountForBarcodesFromJsonString(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.b.increaseCountForBarcodesFromJsonString(json);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    @ProxyFunction(property = "isEnabled")
    public boolean isEnabled() {
        return this.b.isEnabled();
    }

    public final void removeListener(BarcodeSelectionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.e.remove(listener)) {
            listener.onObservationStopped(this);
        }
    }

    public final void reset() {
        getA().reset();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    @ProxyFunction
    public void selectAimedBarcode() {
        this.b.selectAimedBarcode();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    @ProxyFunction(nativeName = "selectAllUnselectedBarcodes")
    public void selectUnselectedBarcodes() {
        this.b.selectUnselectedBarcodes();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    @ProxyFunction(property = "isEnabled")
    public void setEnabled(boolean z) {
        this.b.setEnabled(z);
    }

    public final void setFeedback(BarcodeSelectionFeedback value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.feedback = value;
        getA().setSelectionFeedback(new BarcodeSelection$setNativeFeedback$1(value));
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    @ProxyFunction(property = "pointOfInterest")
    public void setPointOfInterest(PointWithUnit pointWithUnit) {
        this.b.setPointOfInterest(pointWithUnit);
    }

    public final void setSelectBarcodeEnabled(Barcode barcode, boolean enabled) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        getA().setSelectBarcodeEnabled(barcode.getA(), enabled);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    @ProxyFunction
    public void setSelectBarcodeEnabledFromJsonString(String json, boolean enabled) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.b.setSelectBarcodeEnabledFromJsonString(json, enabled);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    @ProxyFunction
    public void unfreezeCamera() {
        this.b.unfreezeCamera();
    }

    public final void unselectBarcodes(List<Barcode> barcodes) {
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        NativeBarcodeSelection a = getA();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(barcodes, 10));
        Iterator<T> it = barcodes.iterator();
        while (it.hasNext()) {
            arrayList.add(((Barcode) it.next()).getA());
        }
        a.unselectBarcodes(CollectionsExtensionsKt.toArrayList(arrayList));
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionProxy
    @ProxyFunction
    public void unselectBarcodesFromJsonString(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.b.unselectBarcodesFromJsonString(json);
    }

    public final void updateFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        new BarcodeSelectionDeserializer().updateModeFromJson(this, jsonData);
    }

    public final void applySettings(BarcodeSelectionSettings settings, Runnable whenDone) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        NativeWrappedFuture applySettingsWrapped = getA().applySettingsWrapped(settings.getA());
        Intrinsics.checkNotNullExpressionValue(applySettingsWrapped, "applySettingsWrapped(...)");
        NativeExtensionsKt.andThen(applySettingsWrapped, whenDone);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeSelection(com.scandit.datacapture.core.capture.DataCaptureContext r1, com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSettings r2, kotlin.jvm.internal.DefaultConstructorMarker r3) {
        /*
            r0 = this;
            if (r1 == 0) goto L7
            com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext r1 = r1._impl()
            goto L8
        L7:
            r1 = 0
        L8:
            com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings r2 = r2.getA()
            com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection r1 = com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection.create(r1, r2)
            java.lang.String r2 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSession r2 = new com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSession
            com.scandit.datacapture.barcode.selection.capture.a r3 = new com.scandit.datacapture.barcode.selection.capture.a
            r3.<init>(r1)
            r2.<init>(r3)
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.selection.capture.BarcodeSelection.<init>(com.scandit.datacapture.core.capture.DataCaptureContext, com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSettings, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
