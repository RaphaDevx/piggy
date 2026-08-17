package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.crosscall;

import android.util.Range;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.e;
import com.scandit.datacapture.core.internal.module.source.O;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b extends e {
    public static final b c = new b();
    public static final String d = "core-x4";

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final Range a(Range[] frameRateRanges, float f) {
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        return O.c(frameRateRanges, f);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return d;
    }
}
