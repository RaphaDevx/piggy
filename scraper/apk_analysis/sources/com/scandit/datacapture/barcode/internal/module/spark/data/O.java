package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.barcode.internal.module.spark.ui.S;
import com.scandit.datacapture.barcode.internal.module.spark.ui.d0;
import com.scandit.datacapture.barcode.spark.capture.SparkScanSettings;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningMode;
import com.scandit.datacapture.barcode.spark.ui.SparkScanView$sparkScanCameraStartListener$1;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewSettings;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureContextListener;
import com.scandit.datacapture.core.capture.DataCaptureMode;
import com.scandit.datacapture.core.common.ContextStatus;
import com.scandit.datacapture.core.common.async.Callback;
import com.scandit.datacapture.core.internal.sdk.battery.BatterySavingHandler;
import com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManager;
import com.scandit.datacapture.core.source.BatterySavingMode;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.source.TorchState;
import com.scandit.datacapture.core.source.VideoResolution;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class O implements SparkScanViewCameraManager, DataCaptureContextListener {
    public static final Semaphore l = new Semaphore(1, true);
    public final DataCaptureContext a;
    public final SparkScanStateManager b;
    public final SparkScanSettings c;
    public final SparkScanViewSettings d;
    public Camera e;
    public final Camera f;
    public final Camera g;
    public volatile InterfaceC0429h h;
    public final ExecutorService i;
    public BatterySavingMode j;
    public final BatterySavingHandler k;

    public O(DataCaptureContext dataCaptureContext, SparkScanStateManager stateManager, BatterySavingManager batterySavingManager, SparkScanSettings modeSettings, SparkScanViewSettings viewSettings) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        Intrinsics.checkNotNullParameter(batterySavingManager, "batterySavingManager");
        Intrinsics.checkNotNullParameter(modeSettings, "modeSettings");
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        Intrinsics.checkNotNullParameter(batterySavingManager, "batterySavingManager");
        Intrinsics.checkNotNullParameter(modeSettings, "modeSettings");
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        this.a = dataCaptureContext;
        this.b = stateManager;
        this.c = modeSettings;
        this.d = viewSettings;
        this.i = Executors.newSingleThreadExecutor();
        this.j = BatterySavingMode.OFF;
        q qVar = new q(this);
        BatterySavingHandler impl = BatterySavingHandler.INSTANCE.impl(batterySavingManager);
        impl.setListener(qVar);
        this.k = impl;
        Camera.Companion companion = Camera.INSTANCE;
        Camera camera = companion.getCamera(CameraPosition.WORLD_FACING, a(stateManager.s(), c()));
        this.f = camera;
        Camera camera2 = companion.getCamera(CameraPosition.USER_FACING, a(stateManager.s(), c()));
        this.g = camera2;
        if (p.a[viewSettings.getDefaultCameraPosition().ordinal()] == 1) {
            onFrameSourceChanged(dataCaptureContext, camera2);
            dataCaptureContext.setFrameSource(camera2, null);
        } else {
            onFrameSourceChanged(dataCaptureContext, camera);
            dataCaptureContext.setFrameSource(camera, null);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager
    public final void a(S onDone) {
        Intrinsics.checkNotNullParameter(onDone, "onDone");
        b((Callback) null);
        b(new E(this, onDone));
    }

    public final void b(final Function0 function0) {
        this.i.submit(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.data.O$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                O.c(Function0.this);
            }
        });
    }

    public final boolean c() {
        return Intrinsics.areEqual(this.c.getProperty("uhd_resolution_enabled"), (Object) 1);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager
    public final boolean isTorchAvailable() {
        Camera camera = this.e;
        return camera != null && camera.isTorchAvailable();
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public final void onFrameSourceChanged(DataCaptureContext dataCaptureContext, FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Camera camera = frameSource instanceof Camera ? (Camera) frameSource : null;
        if (Intrinsics.areEqual(camera, this.f) || Intrinsics.areEqual(camera, this.g)) {
            this.e = camera;
        }
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public final void onModeAdded(DataCaptureContext dataCaptureContext, DataCaptureMode dataCaptureMode) {
        DataCaptureContextListener.DefaultImpls.onModeAdded(this, dataCaptureContext, dataCaptureMode);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public final void onModeRemoved(DataCaptureContext dataCaptureContext, DataCaptureMode dataCaptureMode) {
        DataCaptureContextListener.DefaultImpls.onModeRemoved(this, dataCaptureContext, dataCaptureMode);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public final void onObservationStarted(DataCaptureContext dataCaptureContext) {
        DataCaptureContextListener.DefaultImpls.onObservationStarted(this, dataCaptureContext);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public final void onObservationStopped(DataCaptureContext dataCaptureContext) {
        DataCaptureContextListener.DefaultImpls.onObservationStopped(this, dataCaptureContext);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextListener
    public final void onStatusChanged(DataCaptureContext dataCaptureContext, ContextStatus contextStatus) {
        DataCaptureContextListener.DefaultImpls.onStatusChanged(this, dataCaptureContext, contextStatus);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager
    public final void release() {
        this.a.removeListener(this);
        setBatterySavingMode(BatterySavingMode.OFF);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager
    public final void setBatterySavingMode(BatterySavingMode batterySavingMode) {
        Intrinsics.checkNotNullParameter(batterySavingMode, "batterySavingMode");
        this.j = batterySavingMode;
        this.k.setBatterySavingMode(batterySavingMode);
    }

    public static final void c(Function0 block) {
        Intrinsics.checkNotNullParameter(block, "$block");
        l.acquire();
        block.invoke();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager
    public final void b(Callback callback) {
        b(new I(this, callback));
    }

    public static final void a(O o, Function0 function0) {
        o.getClass();
        function0.invoke();
        l.release();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager
    public final void b() {
        b(new N(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager
    public final Camera a() {
        return this.e;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager
    public final void a(SparkScanView$sparkScanCameraStartListener$1 sparkScanView$sparkScanCameraStartListener$1) {
        this.h = sparkScanView$sparkScanCameraStartListener$1;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager
    public final void a(d0 whenDone) {
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        b(new u(this, whenDone));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager
    public final void a(Callback callback) {
        b(new M(this, callback));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager
    public final void a(TorchState torchState, Function0 whenDone) {
        Intrinsics.checkNotNullParameter(torchState, "torchState");
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        b(new A(this, torchState, whenDone));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager
    public final void a(Function0 whenDone) {
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        this.a.addListener(this);
        b(new y(this));
        b((Callback) null);
        b(new C(this, whenDone));
        setBatterySavingMode(this.j);
    }

    public final CameraSettings a(SparkScanScanningMode sparkScanScanningMode, boolean z) {
        CameraSettings cameraSettings;
        float zoomFactorOut;
        if (sparkScanScanningMode instanceof SparkScanScanningMode.Default) {
            cameraSettings = new CameraSettings();
            cameraSettings.setPreferredResolution(VideoResolution.FULL_HD);
            cameraSettings.setZoomFactor(1.0f);
            cameraSettings.setProperty("exposureTargetBias", -1);
            cameraSettings.setProperty("repeatedTriggerInterval", 1);
            cameraSettings.setProperty("stage1StandbyDuration", 4);
            cameraSettings.setProperty("usesApi2Features", Boolean.TRUE);
        } else if (sparkScanScanningMode instanceof SparkScanScanningMode.Target) {
            cameraSettings = new CameraSettings();
            cameraSettings.setPreferredResolution(VideoResolution.FULL_HD);
            cameraSettings.setProperty("exposureTargetBias", -1);
            cameraSettings.setProperty("repeatedTriggerInterval", 1);
            cameraSettings.setProperty("stage1StandbyDuration", 4);
            cameraSettings.setProperty("usesApi2Features", Boolean.TRUE);
            cameraSettings.setProperty("regionStrategy", "spot");
        } else {
            throw new NoWhenBranchMatchedException();
        }
        if (this.b.B()) {
            zoomFactorOut = this.d.getZoomFactorIn();
        } else {
            zoomFactorOut = this.d.getZoomFactorOut();
        }
        cameraSettings.setZoomFactor(zoomFactorOut);
        if (z) {
            cameraSettings.setPreferredResolution(VideoResolution.UHD4K);
        }
        return cameraSettings;
    }
}
