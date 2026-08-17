package com.scandit.datacapture.core.internal.module.source;

import android.hardware.camera2.CameraManager;
import com.scandit.datacapture.core.internal.module.source.api2.C0574q;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import kotlin.NoWhenBranchMatchedException;

/* loaded from: classes2.dex */
public final class F {
    public static final InterfaceC0604x a(F f, NativeCameraApi nativeCameraApi) {
        f.getClass();
        int i = E.a[nativeCameraApi.ordinal()];
        if (i == 1) {
            return new C0597p();
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        CameraManager cameraManager = (CameraManager) AppAndroidEnvironment.INSTANCE.getApplicationContext().getSystemService(CameraManager.class);
        if (cameraManager != null) {
            return new C0574q(cameraManager);
        }
        return null;
    }
}
