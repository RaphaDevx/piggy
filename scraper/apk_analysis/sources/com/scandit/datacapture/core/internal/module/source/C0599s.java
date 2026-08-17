package com.scandit.datacapture.core.internal.module.source;

import android.hardware.Camera;
import com.scandit.datacapture.core.internal.module.common.geometry.NativeAxis;
import com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameterKey;
import com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters;
import com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.source.s, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0599s {
    public final C0603w a;
    public final InterfaceC0600t b;
    public int c;
    public int d;
    public final Function1 e;
    public final C0595n f;
    public final HandlerC0598q g;
    public final K h;
    public final r i;
    public boolean j;
    public final Camera.PreviewCallback k;

    public C0599s(C0603w cameraWrapper, InterfaceC0600t cameraInfo, int i, int i2, Function1 frameDataCallback, C0595n delegate) {
        Intrinsics.checkNotNullParameter(cameraWrapper, "cameraWrapper");
        Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
        Intrinsics.checkNotNullParameter(frameDataCallback, "frameDataCallback");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.a = cameraWrapper;
        this.b = cameraInfo;
        this.c = i;
        this.d = i2;
        this.e = frameDataCallback;
        this.f = delegate;
        this.g = new HandlerC0598q(this);
        this.h = new K(3);
        this.i = new r(this);
        this.j = true;
        this.k = new Camera.PreviewCallback() { // from class: com.scandit.datacapture.core.internal.module.source.s$$ExternalSyntheticLambda0
            @Override // android.hardware.Camera.PreviewCallback
            public final void onPreviewFrame(byte[] bArr, Camera camera) {
                C0599s.a(C0599s.this, bArr, camera);
            }
        };
        a();
        for (int i3 = 0; i3 < 3; i3++) {
            a(new byte[((this.c * this.d) * 12) / 8]);
        }
    }

    public static final void a(C0599s this$0, byte[] bArr, Camera camera) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (bArr == null) {
            return;
        }
        this$0.getClass();
        NativeCameraFrameData nativeCameraFrameData = null;
        if (bArr.length == ((this$0.c * this$0.d) * 12) / 8) {
            K k = this$0.h;
            NativeCameraFrameData nativeCameraFrameData2 = !k.b.isEmpty() ? (NativeCameraFrameData) k.b.pop() : null;
            if (nativeCameraFrameData2 != null) {
                NativeCameraCaptureParameters captureParameters = nativeCameraFrameData2.getCaptureParameters();
                if (captureParameters != null) {
                    Intrinsics.checkNotNull(captureParameters);
                    captureParameters.clear();
                } else {
                    captureParameters = NativeCameraCaptureParameters.create();
                }
                NativeCameraCaptureParameters nativeCameraCaptureParameters = captureParameters;
                int i = this$0.c;
                int i2 = this$0.d;
                r rVar = this$0.i;
                int cameraToNativeDeviceOrientation = this$0.f.getCameraToNativeDeviceOrientation();
                NativeAxis nativeAxis = this$0.f.shouldMirrorAroundYAxis() ? NativeAxis.Y : NativeAxis.NONE;
                Intrinsics.checkNotNull(nativeCameraCaptureParameters);
                InterfaceC0600t cameraInfo = this$0.b;
                Intrinsics.checkNotNullParameter(nativeCameraCaptureParameters, "<this>");
                Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
                NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey = NativeCameraCaptureParameterKey.POSITION;
                int i3 = ((c0) cameraInfo).c;
                nativeCameraCaptureParameters.insertInt64(nativeCameraCaptureParameterKey, i3 != 0 ? i3 != 1 ? -1L : 2L : 1L);
                nativeCameraFrameData2.update(i, i2, bArr, rVar, cameraToNativeDeviceOrientation, nativeAxis, nativeCameraCaptureParameters, null);
                nativeCameraFrameData = nativeCameraFrameData2;
            }
        }
        if (nativeCameraFrameData != null) {
            nativeCameraFrameData.retain();
            try {
                if (this$0.j) {
                    this$0.e.invoke(nativeCameraFrameData);
                }
            } finally {
                nativeCameraFrameData.release();
            }
        }
    }

    public final void b() {
        Camera camera = this.a.a;
        if (camera != null) {
            camera.setPreviewCallbackWithBuffer(null);
        }
    }

    public final void a() {
        Camera camera = this.a.a;
        if (camera != null) {
            camera.setPreviewCallbackWithBuffer(this.k);
        }
    }

    public final void a(byte[] bArr) {
        Camera camera;
        if (bArr.length != ((this.c * this.d) * 12) / 8 || (camera = this.a.a) == null) {
            return;
        }
        camera.addCallbackBuffer(bArr);
    }
}
