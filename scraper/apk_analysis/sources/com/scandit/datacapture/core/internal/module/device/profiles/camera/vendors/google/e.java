package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google;

import android.hardware.Camera;
import android.util.Range;
import com.scandit.datacapture.core.internal.module.source.O;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e extends com.scandit.datacapture.core.internal.module.device.profiles.camera.e {
    public static final e c;
    public static final String d;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f e;

    static {
        e eVar = new e();
        c = eVar;
        d = "nexus 5|nexus 6p";
        e = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(eVar.a, false, 0.0f, false, false, 0, 123);
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
        Integer num;
        Integer num2;
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        for (Range range : frameRateRanges) {
            Integer num3 = (Integer) range.getLower();
            if (num3 != null && num3.intValue() == 7000 && (num2 = (Integer) range.getUpper()) != null && num2.intValue() == 30000) {
                return new Range(7000, 30000);
            }
        }
        Range range2 = null;
        int i = 30000;
        for (Range range3 : frameRateRanges) {
            Object lower = range3.getLower();
            Intrinsics.checkNotNullExpressionValue(lower, "getLower(...)");
            if (((Number) lower).intValue() < i && (num = (Integer) range3.getUpper()) != null && num.intValue() == 30000) {
                Object lower2 = range3.getLower();
                Intrinsics.checkNotNullExpressionValue(lower2, "getLower(...)");
                i = ((Number) lower2).intValue();
                range2 = range3;
            }
        }
        if (range2 != null) {
            return new Range(range2.getLower(), range2.getUpper());
        }
        return O.b(frameRateRanges, f);
    }
}
