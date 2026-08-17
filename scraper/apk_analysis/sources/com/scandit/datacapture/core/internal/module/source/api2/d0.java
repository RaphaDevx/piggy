package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.os.Handler;
import android.view.Surface;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d0 implements InterfaceC0558b0 {
    public final CameraDevice a;
    public final CameraCaptureSession b;
    public final Surface c;
    public final ImageReader d;
    public final C0 e;
    public final Handler f;
    public final CaptureRequest g;

    public d0(CameraDevice cameraDevice, CameraCaptureSession androidSession, Surface previewSurface, ImageReader yuvImageReader, C0 imageAvailableListener, Handler handler, CaptureRequest cleanupRequest) {
        Intrinsics.checkNotNullParameter(cameraDevice, "cameraDevice");
        Intrinsics.checkNotNullParameter(androidSession, "androidSession");
        Intrinsics.checkNotNullParameter(previewSurface, "previewSurface");
        Intrinsics.checkNotNullParameter(yuvImageReader, "yuvImageReader");
        Intrinsics.checkNotNullParameter(imageAvailableListener, "imageAvailableListener");
        Intrinsics.checkNotNullParameter(handler, "handler");
        Intrinsics.checkNotNullParameter(cleanupRequest, "cleanupRequest");
        this.a = cameraDevice;
        this.b = androidSession;
        this.c = previewSurface;
        this.d = yuvImageReader;
        this.e = imageAvailableListener;
        this.f = handler;
        this.g = cleanupRequest;
    }

    public final boolean a() {
        try {
            this.b.abortCaptures();
            this.b.capture(this.g, new C0560c0(), this.f);
            return true;
        } catch (CameraAccessException | IllegalArgumentException | IllegalStateException | SecurityException unused) {
            return false;
        }
    }

    public final void b() {
        try {
            this.b.abortCaptures();
            this.b.close();
        } catch (CameraAccessException | IllegalArgumentException | IllegalStateException | SecurityException unused) {
        }
        this.d.getSurface().release();
    }

    public final boolean a(m0 requestSettings, C0559c callback) {
        Intrinsics.checkNotNullParameter(requestSettings, "requestSettings");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            CameraDevice cameraDevice = this.a;
            Surface surface = this.c;
            Surface surface2 = this.d.getSurface();
            Intrinsics.checkNotNullExpressionValue(surface2, "getSurface(...)");
            this.b.setRepeatingRequest(requestSettings.a(E.a(cameraDevice, surface, surface2)), new H(callback), this.f);
            return true;
        } catch (CameraAccessException e) {
            Intrinsics.checkNotNullParameter(e, "e");
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e2) {
            Intrinsics.checkNotNullParameter(e2, "e");
            e2.printStackTrace();
            return false;
        } catch (IllegalStateException e3) {
            Intrinsics.checkNotNullParameter(e3, "e");
            e3.printStackTrace();
            return false;
        } catch (SecurityException e4) {
            Intrinsics.checkNotNullParameter(e4, "e");
            e4.printStackTrace();
            return false;
        }
    }

    public final boolean a(m0 requestSettings) {
        C0561d callback = C0561d.a;
        Intrinsics.checkNotNullParameter(requestSettings, "requestSettings");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            CameraDevice cameraDevice = this.a;
            Surface surface = this.c;
            Surface surface2 = this.d.getSurface();
            Intrinsics.checkNotNullExpressionValue(surface2, "getSurface(...)");
            this.b.capture(requestSettings.a(E.a(cameraDevice, surface, surface2)), new H(callback), this.f);
            return true;
        } catch (CameraAccessException e) {
            Intrinsics.checkNotNullParameter(e, "e");
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e2) {
            Intrinsics.checkNotNullParameter(e2, "e");
            e2.printStackTrace();
            return false;
        } catch (IllegalStateException e3) {
            Intrinsics.checkNotNullParameter(e3, "e");
            e3.printStackTrace();
            return false;
        } catch (SecurityException e4) {
            Intrinsics.checkNotNullParameter(e4, "e");
            e4.printStackTrace();
            return false;
        }
    }
}
