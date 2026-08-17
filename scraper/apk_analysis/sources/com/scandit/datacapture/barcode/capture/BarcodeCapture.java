package com.scandit.datacapture.barcode.capture;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.feedback.BarcodeCaptureFeedback;
import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture;
import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureLicenseInfo;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureContextListener;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import com.scandit.datacapture.core.common.ContextStatus;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.battery.BatterySavingHandler;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera;
import com.scandit.datacapture.core.source.BatterySavingMode;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FocusGestureStrategy;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import io.sentry.protocol.Feedback;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 A2\u00020\u00012\u00020\u0002:\u0002ABB\u0019\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bB#\b\u0010\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eH\u0097\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0003H\u0097\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0014\u001a\u00020\u00132\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J#\u0010\u001c\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010#\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b#\u0010$J\u0015\u0010%\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b%\u0010$R*\u0010.\u001a\u00020&2\u0006\u0010'\u001a\u00020&8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R$\u00101\u001a\u00020/2\u0006\u00100\u001a\u00020/8W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b1\u00102\"\u0004\b3\u00104R(\u0010:\u001a\u0004\u0018\u0001052\b\u00100\u001a\u0004\u0018\u0001058W@WX\u0096\u000f¢\u0006\f\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0016\u0010\n\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<R\u0013\u0010@\u001a\u0004\u0018\u00010=8F¢\u0006\u0006\u001a\u0004\b>\u0010?¨\u0006C"}, d2 = {"Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "Lcom/scandit/datacapture/core/capture/DataCaptureMode;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureProxy;", "Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCapture;", "impl", "Lcom/scandit/datacapture/core/internal/sdk/battery/BatterySavingHandler;", "batterySavingHandler", "<init>", "(Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCapture;Lcom/scandit/datacapture/core/internal/sdk/battery/BatterySavingHandler;)V", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;", "settings", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;Lcom/scandit/datacapture/core/internal/sdk/battery/BatterySavingHandler;)V", "Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureMode;", "_dataCaptureModeImpl", "()Lcom/scandit/datacapture/core/internal/sdk/capture/NativeDataCaptureMode;", "_impl", "()Lcom/scandit/datacapture/barcode/internal/module/capture/NativeBarcodeCapture;", "", "_setDataCaptureContext", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;)V", "", "jsonData", "updateFromJson", "(Ljava/lang/String;)V", "Ljava/lang/Runnable;", "whenDone", "applySettings", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;Ljava/lang/Runnable;)V", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSession;", "_session", "()Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSession;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addListener", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureListener;)V", "removeListener", "Lcom/scandit/datacapture/barcode/feedback/BarcodeCaptureFeedback;", "value", "e", "Lcom/scandit/datacapture/barcode/feedback/BarcodeCaptureFeedback;", "getFeedback", "()Lcom/scandit/datacapture/barcode/feedback/BarcodeCaptureFeedback;", "setFeedback", "(Lcom/scandit/datacapture/barcode/feedback/BarcodeCaptureFeedback;)V", Feedback.TYPE, "", "<set-?>", "isEnabled", "()Z", "setEnabled", "(Z)V", "Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "getPointOfInterest", "()Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", "setPointOfInterest", "(Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;)V", "pointOfInterest", "getDataCaptureContext", "()Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureLicenseInfo;", "getBarcodeCaptureLicenseInfo", "()Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureLicenseInfo;", "barcodeCaptureLicenseInfo", "Companion", "com/scandit/datacapture/barcode/capture/b", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class BarcodeCapture implements DataCaptureMode, BarcodeCaptureProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BatterySavingHandler a;
    private final /* synthetic */ BarcodeCaptureProxyAdapter b;
    private DataCaptureContext c;
    private final BarcodeCapture$dataCaptureContextListener$1 d;

    /* renamed from: e, reason: from kotlin metadata */
    private BarcodeCaptureFeedback feedback;
    private final CopyOnWriteArraySet f;
    private final BarcodeCaptureSession g;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0007¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/barcode/capture/BarcodeCapture$Companion;", "", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;", "settings", "Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "forDataCaptureContext", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;)Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "", "jsonData", "fromJson", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "Lcom/scandit/datacapture/core/source/CameraSettings;", "createRecommendedCameraSettings", "()Lcom/scandit/datacapture/core/source/CameraSettings;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final CameraSettings createRecommendedCameraSettings() {
            CameraSettings cameraSettings = new CameraSettings();
            cameraSettings.setFocusGestureStrategy(FocusGestureStrategy.MANUAL_UNTIL_CAPTURE);
            return cameraSettings;
        }

        @JvmStatic
        public final BarcodeCapture forDataCaptureContext(DataCaptureContext dataCaptureContext, BarcodeCaptureSettings settings) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            BarcodeCapture barcodeCapture = new BarcodeCapture(dataCaptureContext, settings, BatterySavingHandler.Companion.impl$default(BatterySavingHandler.INSTANCE, null, 1, null));
            if (dataCaptureContext != null) {
                dataCaptureContext.setMode(barcodeCapture);
            }
            return barcodeCapture;
        }

        @JvmStatic
        public final BarcodeCapture fromJson(DataCaptureContext dataCaptureContext, String jsonData) {
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            return new BarcodeCaptureDeserializer().modeFromJson(dataCaptureContext, jsonData);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.scandit.datacapture.barcode.capture.BarcodeCapture$dataCaptureContextListener$1] */
    public BarcodeCapture(NativeBarcodeCapture impl, BatterySavingHandler batterySavingHandler) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        Intrinsics.checkNotNullParameter(batterySavingHandler, "batterySavingHandler");
        this.a = batterySavingHandler;
        this.b = new BarcodeCaptureProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
        batterySavingHandler.setListener(new C0269a(this));
        this.d = new DataCaptureContextListener() { // from class: com.scandit.datacapture.barcode.capture.BarcodeCapture$dataCaptureContextListener$1
            @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
            public void onFrameSourceChanged(DataCaptureContext dataCaptureContext, FrameSource frameSource) {
                BatterySavingHandler batterySavingHandler2;
                Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
                BarcodeCapture barcodeCapture = BarcodeCapture.this;
                batterySavingHandler2 = barcodeCapture.a;
                BarcodeCapture.access$applyBatterySavingMode(barcodeCapture, dataCaptureContext, batterySavingHandler2.getShouldSaveBattery());
            }

            @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
            public void onModeAdded(DataCaptureContext dataCaptureContext, DataCaptureMode dataCaptureMode) {
                DataCaptureContextListener.DefaultImpls.onModeAdded(this, dataCaptureContext, dataCaptureMode);
            }

            @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
            public void onModeRemoved(DataCaptureContext dataCaptureContext, DataCaptureMode dataCaptureMode) {
                DataCaptureContextListener.DefaultImpls.onModeRemoved(this, dataCaptureContext, dataCaptureMode);
            }

            @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
            public void onObservationStarted(DataCaptureContext dataCaptureContext) {
                DataCaptureContextListener.DefaultImpls.onObservationStarted(this, dataCaptureContext);
            }

            @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
            public void onObservationStopped(DataCaptureContext dataCaptureContext) {
                DataCaptureContextListener.DefaultImpls.onObservationStopped(this, dataCaptureContext);
            }

            @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
            public void onStatusChanged(DataCaptureContext dataCaptureContext, ContextStatus contextStatus) {
                DataCaptureContextListener.DefaultImpls.onStatusChanged(this, dataCaptureContext, contextStatus);
            }
        };
        BarcodeCaptureFeedback defaultFeedback = BarcodeCaptureFeedback.INSTANCE.defaultFeedback();
        getA().setSuccessFeedback(new BarcodeCapture$setNativeFeedback$1(defaultFeedback));
        this.feedback = defaultFeedback;
        this.f = new CopyOnWriteArraySet();
        getA().addListenerAsync(new BarcodeCaptureListenerReversedAdapter(new C0270b(this), this, null, 4, null));
        BatterySavingMode batterySavingMode = getA().getSettings().getBatterySavingMode();
        Intrinsics.checkNotNullExpressionValue(batterySavingMode, "getBatterySavingMode(...)");
        batterySavingHandler.setBatterySavingMode(batterySavingMode);
        this.g = new BarcodeCaptureSession(new C0271c(impl));
    }

    private static void a(DataCaptureContext dataCaptureContext, boolean z) {
        FrameSource frameSource = dataCaptureContext != null ? dataCaptureContext.get_frameSource() : null;
        Camera camera = frameSource instanceof Camera ? (Camera) frameSource : null;
        NativeAndroidCamera a = camera != null ? camera.getA() : null;
        if (a != null) {
            a.setBatterySavingMode(z);
        }
    }

    public static final /* synthetic */ void access$applyBatterySavingMode(BarcodeCapture barcodeCapture, DataCaptureContext dataCaptureContext, boolean z) {
        barcodeCapture.getClass();
        a(dataCaptureContext, z);
    }

    public static /* synthetic */ void applySettings$default(BarcodeCapture barcodeCapture, BarcodeCaptureSettings barcodeCaptureSettings, Runnable runnable, int i, Object obj) {
        if ((i & 2) != 0) {
            runnable = null;
        }
        barcodeCapture.applySettings(barcodeCaptureSettings, runnable);
    }

    @JvmStatic
    public static final CameraSettings createRecommendedCameraSettings() {
        return INSTANCE.createRecommendedCameraSettings();
    }

    @JvmStatic
    public static final BarcodeCapture forDataCaptureContext(DataCaptureContext dataCaptureContext, BarcodeCaptureSettings barcodeCaptureSettings) {
        return INSTANCE.forDataCaptureContext(dataCaptureContext, barcodeCaptureSettings);
    }

    @JvmStatic
    public static final BarcodeCapture fromJson(DataCaptureContext dataCaptureContext, String str) {
        return INSTANCE.fromJson(dataCaptureContext, str);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    @NativeImpl
    /* renamed from: _dataCaptureModeImpl */
    public NativeDataCaptureMode getC() {
        return this.b.getC();
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeCapture getA() {
        return this.b.getA();
    }

    /* renamed from: _session, reason: from getter */
    public final BarcodeCaptureSession getG() {
        return this.g;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    public void _setDataCaptureContext(DataCaptureContext dataCaptureContext) {
        a(this.c, false);
        DataCaptureContext dataCaptureContext2 = this.c;
        if (dataCaptureContext2 != null) {
            dataCaptureContext2.removeListener(this.d);
        }
        this.c = dataCaptureContext;
        if (dataCaptureContext != null) {
            dataCaptureContext.addListener(this.d);
        }
        a(dataCaptureContext, this.a.getShouldSaveBattery());
    }

    public final void addListener(BarcodeCaptureListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.f.add(listener)) {
            listener.onObservationStarted(this);
        }
    }

    public final void applySettings(BarcodeCaptureSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        applySettings$default(this, settings, null, 2, null);
    }

    public final BarcodeCaptureLicenseInfo getBarcodeCaptureLicenseInfo() {
        NativeBarcodeCaptureLicenseInfo barcodeCaptureLicenseInfo = getA().getBarcodeCaptureLicenseInfo();
        if (barcodeCaptureLicenseInfo != null) {
            return new BarcodeCaptureLicenseInfo(barcodeCaptureLicenseInfo);
        }
        return null;
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode
    /* renamed from: getDataCaptureContext, reason: from getter */
    public DataCaptureContext getC() {
        return this.c;
    }

    public final BarcodeCaptureFeedback getFeedback() {
        return this.feedback;
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureProxy
    @ProxyFunction(property = "pointOfInterest")
    public PointWithUnit getPointOfInterest() {
        return this.b.getPointOfInterest();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    @ProxyFunction(property = "isEnabled")
    public boolean isEnabled() {
        return this.b.isEnabled();
    }

    public final void removeListener(BarcodeCaptureListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.f.remove(listener)) {
            listener.onObservationStopped(this);
        }
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureMode, com.scandit.datacapture.barcode.batch.capture.BarcodeBatchProxy
    @ProxyFunction(property = "isEnabled")
    public void setEnabled(boolean z) {
        this.b.setEnabled(z);
    }

    public final void setFeedback(BarcodeCaptureFeedback value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.feedback = value;
        getA().setSuccessFeedback(new BarcodeCapture$setNativeFeedback$1(value));
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureProxy
    @ProxyFunction(property = "pointOfInterest")
    public void setPointOfInterest(PointWithUnit pointWithUnit) {
        this.b.setPointOfInterest(pointWithUnit);
    }

    public final void updateFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        new BarcodeCaptureDeserializer().updateModeFromJson(this, jsonData);
    }

    public final void applySettings(BarcodeCaptureSettings settings, Runnable whenDone) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.a.setBatterySavingMode(settings.getBatterySaving());
        NativeWrappedFuture applySettingsWrapped = getA().applySettingsWrapped(settings._impl());
        Intrinsics.checkNotNullExpressionValue(applySettingsWrapped, "applySettingsWrapped(...)");
        NativeExtensionsKt.andThen(applySettingsWrapped, whenDone);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeCapture(com.scandit.datacapture.core.capture.DataCaptureContext r2, com.scandit.datacapture.barcode.capture.BarcodeCaptureSettings r3, com.scandit.datacapture.core.internal.sdk.battery.BatterySavingHandler r4) {
        /*
            r1 = this;
            java.lang.String r0 = "settings"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "batterySavingHandler"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            if (r2 == 0) goto L11
            com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext r2 = r2._impl()
            goto L12
        L11:
            r2 = 0
        L12:
            com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureSettings r3 = r3._impl()
            com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture r2 = com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture.create(r2, r3)
            java.lang.String r3 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.<init>(r2, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.capture.BarcodeCapture.<init>(com.scandit.datacapture.core.capture.DataCaptureContext, com.scandit.datacapture.barcode.capture.BarcodeCaptureSettings, com.scandit.datacapture.core.internal.sdk.battery.BatterySavingHandler):void");
    }
}
