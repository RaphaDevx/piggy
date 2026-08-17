package com.scandit.datacapture.core.internal.module.source;

import android.hardware.Camera;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.source.v, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0602v {
    public static final int a(InterfaceC0600t info) {
        Intrinsics.checkNotNullParameter(info, "info");
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            try {
                Camera.getCameraInfo(i, cameraInfo);
                if (cameraInfo.canDisableShutterSound == ((c0) info).b && cameraInfo.orientation == ((c0) info).d && cameraInfo.facing == ((c0) info).c) {
                    return i;
                }
            } catch (Error e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return -1;
    }
}
