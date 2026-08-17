package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

import android.hardware.Camera;
import android.util.Range;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.q, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0520q extends com.scandit.datacapture.core.internal.module.device.profiles.camera.e {
    public static final C0520q c;
    public static final String d;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f e;

    static {
        C0520q c0520q = new C0520q();
        c = c0520q;
        d = "sm-g350.*";
        e = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(c0520q.a, false, 0.0f, false, false, 0, 123);
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
        if (frameRateRanges.length == 0) {
            return null;
        }
        Range range = null;
        for (Range range2 : frameRateRanges) {
            if ((((Integer) range2.getUpper()) != null ? Float.valueOf(r4.intValue()) : null).floatValue() <= f) {
                Object lower = range2.getLower();
                Intrinsics.checkNotNullExpressionValue(lower, "getLower(...)");
                if (((Number) lower).intValue() < 16000) {
                    if (range != null) {
                        Object upper = range2.getUpper();
                        Intrinsics.checkNotNullExpressionValue(upper, "getUpper(...)");
                        int intValue = ((Number) upper).intValue();
                        Object upper2 = range.getUpper();
                        Intrinsics.checkNotNullExpressionValue(upper2, "getUpper(...)");
                        if (intValue <= ((Number) upper2).intValue()) {
                            if (Intrinsics.areEqual(range2.getUpper(), range.getUpper())) {
                                Object lower2 = range2.getLower();
                                Intrinsics.checkNotNullExpressionValue(lower2, "getLower(...)");
                                int intValue2 = ((Number) lower2).intValue();
                                Object lower3 = range.getLower();
                                Intrinsics.checkNotNullExpressionValue(lower3, "getLower(...)");
                                if (intValue2 <= ((Number) lower3).intValue()) {
                                }
                            }
                        }
                    }
                    range = range2;
                }
            }
        }
        return range != null ? range : com.scandit.datacapture.core.internal.module.source.O.b(frameRateRanges, f);
    }
}
