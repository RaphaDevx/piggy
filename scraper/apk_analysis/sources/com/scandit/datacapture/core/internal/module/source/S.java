package com.scandit.datacapture.core.internal.module.source;

import android.util.Range;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class S {
    public static final Range a(CameraProfile cameraProfile, float f, Range[] frameRateRanges, boolean z, NativeCameraApi cameraApi, NativePreferredFrameRateRange nativePreferredFrameRateRange) {
        Integer num;
        Intrinsics.checkNotNullParameter(cameraProfile, "cameraProfile");
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        Intrinsics.checkNotNullParameter(cameraApi, "cameraApi");
        Intrinsics.checkNotNullParameter(cameraApi, "<this>");
        int i = P.a[cameraApi.ordinal()];
        int i2 = 1;
        if (i == 1) {
            i2 = 1000;
        } else if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        Intrinsics.checkNotNullParameter(cameraProfile, "cameraProfile");
        Intrinsics.checkNotNullParameter(cameraApi, "cameraApi");
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        Q frameRateRangeSelector = new Q(cameraApi, cameraProfile, frameRateRanges, nativePreferredFrameRateRange);
        if (!z) {
            return frameRateRangeSelector.a(Float.valueOf(f).floatValue());
        }
        float f2 = i2 * 20.0f;
        Intrinsics.checkNotNullParameter(frameRateRangeSelector, "frameRateRangeSelector");
        Range a = frameRateRangeSelector.a(Float.valueOf(25.0f).floatValue());
        if (f2 >= ((a == null || (num = (Integer) a.getUpper()) == null) ? 0 : num.intValue())) {
            a = null;
        }
        return a == null ? frameRateRangeSelector.a(Float.valueOf(f).floatValue()) : a;
    }
}
