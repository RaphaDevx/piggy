package com.scandit.datacapture.barcode.internal.module.find.capture;

import com.scandit.datacapture.core.internal.module.source.NativeCameraInfo;
import com.scandit.datacapture.core.internal.sdk.data.NativeFloatRange;
import com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FrameSourceState;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements BarcodeFindCameraManager {
    public final CameraSettings b;
    public final Function2 c;
    public final Lazy d;

    public c(CameraSettings cameraSettings, com.scandit.datacapture.barcode.find.ui.c onResolutionChange) {
        Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
        Intrinsics.checkNotNullParameter(onResolutionChange, "onResolutionChange");
        this.b = cameraSettings;
        this.c = onResolutionChange;
        this.d = LazyKt.lazy(new b(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.BarcodeFindCameraManager
    public final void a(float f) {
        this.b.setZoomFactor(f);
        Camera camera = (Camera) this.d.getValue();
        if (camera != null) {
            Camera.applySettings$default(camera, this.b, null, 2, null);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.BarcodeFindCameraManager
    public final void b() {
        Camera camera = (Camera) this.d.getValue();
        if (camera == null) {
            return;
        }
        FrameSourceState currentState = camera.getCurrentState();
        FrameSourceState frameSourceState = FrameSourceState.OFF;
        if (currentState != frameSourceState) {
            camera.switchToDesiredState(frameSourceState);
            camera.getA().flushPendingTasks();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.BarcodeFindCameraManager
    public final Float c() {
        NativeAndroidCamera a;
        NativeCameraInfo cameraInfo;
        NativeFloatRange availableZoomRange;
        Camera camera = (Camera) this.d.getValue();
        if (camera != null && (a = camera.getA()) != null && (cameraInfo = a.getCameraInfo()) != null && (availableZoomRange = cameraInfo.getAvailableZoomRange()) != null) {
            Float valueOf = Float.valueOf(availableZoomRange.getMin());
            if (valueOf.floatValue() < 1.0f) {
                return valueOf;
            }
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.BarcodeFindCameraManager
    public final float d() {
        return this.b.getZoomFactor();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.BarcodeFindCameraManager
    public final void e() {
        Camera camera = (Camera) this.d.getValue();
        if (camera != null) {
            camera.switchToDesiredState(FrameSourceState.ON);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.BarcodeFindCameraManager
    public final Camera a() {
        return (Camera) this.d.getValue();
    }
}
