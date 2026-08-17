package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.blackview;

import android.util.Range;
import com.scandit.datacapture.core.internal.module.source.O;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final a d = new a();
    public static final String e = "a80|bv5900|bv5100pro|bv5900pro";

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final Range a(Range[] frameRateRanges, float f) {
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        return O.c(frameRateRanges, f);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }
}
