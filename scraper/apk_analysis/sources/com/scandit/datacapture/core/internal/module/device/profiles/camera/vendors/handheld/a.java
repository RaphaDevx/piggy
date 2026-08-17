package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.handheld;

import android.util.Range;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.e;
import com.scandit.datacapture.core.internal.module.source.NativeCameraApi;
import com.scandit.datacapture.core.internal.module.source.O;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends e {
    public static final a c = new a();
    public static final String d = "algiz_rt8";

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final Range a(Range[] frameRateRanges, float f) {
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        return O.c(frameRateRanges, f);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return d;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final NativeCameraApi j() {
        return NativeCameraApi.CAMERA2;
    }
}
