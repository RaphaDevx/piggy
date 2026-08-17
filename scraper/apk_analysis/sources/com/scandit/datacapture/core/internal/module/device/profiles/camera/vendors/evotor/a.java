package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.evotor;

import android.util.Range;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.e;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends e {
    public static final a c = new a();
    public static final String d = "st-5i";

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final Range a(Range[] frameRateRanges, float f) {
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        return null;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return d;
    }
}
