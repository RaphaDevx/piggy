package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

import android.hardware.Camera;
import android.util.Range;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class Z extends com.scandit.datacapture.core.internal.module.device.profiles.camera.e {
    public static final Z c;
    public static final String d;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f e;

    static {
        Z z = new Z();
        c = z;
        d = "gt-s536.*";
        e = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(z.a, false, 0.0f, false, false, 0, 121);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final void a(Camera.Parameters camParams) {
        Intrinsics.checkNotNullParameter(camParams, "camParams");
        com.scandit.datacapture.core.internal.module.device.profiles.camera.e.a(camParams, Math.max(e.d, -1.0f));
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return d;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final com.scandit.datacapture.core.internal.module.device.profiles.camera.f a() {
        return e;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final Range a(Range[] frameRateRanges, float f) {
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        return com.scandit.datacapture.core.internal.module.source.O.c(frameRateRanges, f);
    }
}
