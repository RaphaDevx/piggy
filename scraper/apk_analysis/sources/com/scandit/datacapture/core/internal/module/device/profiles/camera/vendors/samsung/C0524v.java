package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

import android.util.Range;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.v, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0524v extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final C0524v d;
    public static final String e;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f f;

    static {
        C0524v c0524v = new C0524v();
        d = c0524v;
        e = "(sm-j50|sm-j51).*";
        f = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(c0524v.a, false, 0.0f, true, false, 0, com.salesforce.marketingcloud.analytics.stats.b.o);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final com.scandit.datacapture.core.internal.module.device.profiles.camera.f a() {
        return f;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final Range a(Range[] frameRateRanges, float f2) {
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        return com.scandit.datacapture.core.internal.module.source.O.c(frameRateRanges, f2);
    }
}
