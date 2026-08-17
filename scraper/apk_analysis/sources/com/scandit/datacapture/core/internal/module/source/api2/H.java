package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class H extends CameraCaptureSession.CaptureCallback {
    public final InterfaceC0556a0 a;

    public H(InterfaceC0556a0 callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.a = callback;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(result, "result");
        this.a.a(new G(result));
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long j, long j2) {
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(request, "request");
        this.a.a(j);
    }
}
