package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.view.Surface;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class E {
    public static final CaptureRequest.Builder a(CameraDevice cameraDevice, Surface surface, Surface surface2) {
        CaptureRequest.Builder createCaptureRequest = cameraDevice.createCaptureRequest(1);
        Intrinsics.checkNotNullExpressionValue(createCaptureRequest, "createCaptureRequest(...)");
        createCaptureRequest.addTarget(surface);
        createCaptureRequest.addTarget(surface2);
        return createCaptureRequest;
    }
}
