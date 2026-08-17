package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.view.Surface;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class A {
    public static final ImageReader a(i0 i0Var) {
        ImageReader newInstance = ImageReader.newInstance(i0Var.a, i0Var.b, 35, 2);
        Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
        return newInstance;
    }

    public final CaptureRequest a(CameraDevice cameraDevice, Surface surface) {
        CaptureRequest.Builder createCaptureRequest = cameraDevice.createCaptureRequest(1);
        createCaptureRequest.addTarget(surface);
        createCaptureRequest.set(CaptureRequest.FLASH_MODE, 0);
        CaptureRequest build = createCaptureRequest.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }
}
