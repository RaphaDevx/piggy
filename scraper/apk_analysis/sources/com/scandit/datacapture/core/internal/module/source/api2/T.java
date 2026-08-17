package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraDevice;
import android.os.Handler;
import android.util.Log;
import com.scandit.datacapture.core.source.FrameSourceState;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class T extends CameraDevice.StateCallback {
    public final AtomicBoolean a = new AtomicBoolean(false);
    public final /* synthetic */ Q b;

    public T(C0565h c0565h) {
        this.b = c0565h;
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onOpened(CameraDevice camera) {
        Intrinsics.checkNotNullParameter(camera, "camera");
        if (this.a.compareAndSet(false, true)) {
            C0565h c0565h = (C0565h) this.b;
            c0565h.getClass();
            Intrinsics.checkNotNullParameter(camera, "camera");
            if (c0565h.a) {
                C0573p c0573p = c0565h.b;
                c0573p.k = camera;
                i0 i0Var = c0565h.c;
                Function1 function1 = c0565h.d;
                p0 p0Var = c0573p.j;
                Handler a = c0573p.a();
                if (camera == null || p0Var == null || a == null) {
                    return;
                }
                InterfaceC0558b0 interfaceC0558b0 = c0573p.l;
                if (interfaceC0558b0 != null) {
                    ((d0) interfaceC0558b0).b();
                }
                ((D) c0573p.f).a(a, camera, c0573p.a, p0Var, i0Var, new C0570m(c0573p, function1, p0Var));
            }
        }
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onDisconnected(CameraDevice camera) {
        Intrinsics.checkNotNullParameter(camera, "camera");
        Intrinsics.checkNotNullParameter("CameraDevice disconnected", "message");
        Log.e("sdc-core", "CameraDevice disconnected");
        if (this.a.compareAndSet(false, true)) {
            ((C0565h) this.b).a();
            return;
        }
        C0565h c0565h = (C0565h) this.b;
        if (c0565h.a) {
            c0565h.b.c.invoke(FrameSourceState.OFF);
        }
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onError(CameraDevice camera, int i) {
        Intrinsics.checkNotNullParameter(camera, "camera");
        Intrinsics.checkNotNullParameter("Failed to open camera with camera API 2", "message");
        Log.e("sdc-core", "Failed to open camera with camera API 2");
        if (this.a.compareAndSet(false, true)) {
            ((C0565h) this.b).a();
            return;
        }
        C0565h c0565h = (C0565h) this.b;
        if (c0565h.a) {
            c0565h.b.c.invoke(FrameSourceState.OFF);
        }
    }
}
