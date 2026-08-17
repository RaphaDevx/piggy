package com.scandit.datacapture.core.internal.module.source;

import android.hardware.Camera;
import com.scandit.datacapture.core.logger.CameraInfoProviderEvent;
import com.scandit.datacapture.core.logger.SdcLogger;
import java.util.ArrayList;
import kotlin.ExceptionsKt;

/* loaded from: classes2.dex */
public final class d0 implements InterfaceC0601u {
    public static final d0 a = new d0();

    public final ArrayList a() {
        int numberOfCameras = Camera.getNumberOfCameras();
        ArrayList arrayList = new ArrayList();
        if (numberOfCameras == 0) {
            SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new CameraInfoProviderEvent(1, 0, "No exception thrown, 0 cameras available", null, 8, null));
        }
        for (int i = 0; i < numberOfCameras; i++) {
            try {
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
                arrayList.add(new c0(String.valueOf(i), cameraInfo));
            } catch (RuntimeException e) {
                e.printStackTrace();
                SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new CameraInfoProviderEvent(1, numberOfCameras, ExceptionsKt.stackTraceToString(e), "Failed on camera index " + i + ';'));
            }
        }
        return arrayList;
    }
}
