package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.nokia;

import android.util.Range;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.e;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.f;
import com.scandit.datacapture.core.internal.module.source.O;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b extends e {
    public static final b c;
    public static final String d;
    public static final f e;

    static {
        b bVar = new b();
        c = bVar;
        d = "nokia 7\\.2";
        e = f.a(bVar.a, false, 0.0f, false, false, 0, 125);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final f a() {
        return e;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return d;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final Range a(Range[] frameRateRanges, float f) {
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        return O.c(frameRateRanges, f);
    }
}
