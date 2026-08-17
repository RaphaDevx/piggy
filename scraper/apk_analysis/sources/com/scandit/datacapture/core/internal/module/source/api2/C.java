package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.os.Handler;
import android.view.Surface;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class C extends CameraCaptureSession.StateCallback {
    public final /* synthetic */ InterfaceC0579w a;
    public final /* synthetic */ CameraDevice b;
    public final /* synthetic */ Surface c;
    public final /* synthetic */ ImageReader d;
    public final /* synthetic */ C0 e;
    public final /* synthetic */ Handler f;
    public final /* synthetic */ CaptureRequest g;

    public C(InterfaceC0579w interfaceC0579w, CameraDevice cameraDevice, Surface surface, ImageReader imageReader, C0 c0, Handler handler, CaptureRequest captureRequest) {
        this.a = interfaceC0579w;
        this.b = cameraDevice;
        this.c = surface;
        this.d = imageReader;
        this.e = c0;
        this.f = handler;
        this.g = captureRequest;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public final void onConfigureFailed(CameraCaptureSession session) {
        Intrinsics.checkNotNullParameter(session, "session");
        ((C0570m) this.a).a(I.a);
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public final void onConfigured(CameraCaptureSession session) {
        Intrinsics.checkNotNullParameter(session, "session");
        ((C0570m) this.a).a(new J(new d0(this.b, session, this.c, this.d, this.e, this.f, this.g)));
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public final void onReady(CameraCaptureSession session) {
        Intrinsics.checkNotNullParameter(session, "session");
        ((C0570m) this.a).a.s = false;
    }
}
