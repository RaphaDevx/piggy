package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class U implements S {
    public final CameraManager a;
    public WeakReference b;

    public U(CameraManager cameraManager) {
        Intrinsics.checkNotNullParameter(cameraManager, "cameraManager");
        this.a = cameraManager;
        this.b = new WeakReference(null);
    }

    public final void a(Handler handler, String cameraId, C0565h callback) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        Intrinsics.checkNotNullParameter(cameraId, "cameraId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            Q q = (Q) this.b.get();
            if (q != null) {
                ((C0565h) q).a = false;
            }
            this.b = new WeakReference(callback);
            this.a.openCamera(cameraId, new T(callback), handler);
        } catch (CameraAccessException e) {
            Intrinsics.checkNotNullParameter(e, "e");
            e.printStackTrace();
            callback.a();
        } catch (IllegalArgumentException e2) {
            Intrinsics.checkNotNullParameter(e2, "e");
            e2.printStackTrace();
            callback.a();
        } catch (SecurityException e3) {
            Intrinsics.checkNotNullParameter(e3, "e");
            e3.printStackTrace();
            callback.a();
        }
    }
}
