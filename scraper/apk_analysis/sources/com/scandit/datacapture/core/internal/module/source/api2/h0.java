package com.scandit.datacapture.core.internal.module.source.api2;

import com.scandit.datacapture.core.source.CameraSettings;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class h0 implements g0 {
    public final N a;
    public final AtomicReference b;
    public final C0578v c;

    public h0(O cameraInfo) {
        Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
        this.a = cameraInfo;
        this.b = new AtomicReference();
        CameraSettings cameraSettings = cameraInfo.c;
        Object property = cameraSettings != null ? cameraSettings.getProperty("numFramesInCameraApi2FrameDataPool") : null;
        Integer num = property instanceof Integer ? (Integer) property : null;
        this.c = new C0578v(num != null ? num.intValue() : 3);
    }
}
