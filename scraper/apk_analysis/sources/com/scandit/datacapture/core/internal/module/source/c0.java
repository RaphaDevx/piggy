package com.scandit.datacapture.core.internal.module.source;

import android.hardware.Camera;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c0 implements InterfaceC0600t {
    public final String a;
    public final boolean b;
    public final int c;
    public final int d;

    public c0(String cameraId, Camera.CameraInfo cameraInfo) {
        Intrinsics.checkNotNullParameter(cameraId, "cameraId");
        Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
        this.a = cameraId;
        this.b = cameraInfo.canDisableShutterSound;
        this.c = cameraInfo.facing;
        this.d = cameraInfo.orientation;
    }
}
