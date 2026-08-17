package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

import android.hardware.Camera;
import android.util.Range;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class J extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final J d;
    public static final String e;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f f;

    static {
        J j = new J();
        d = j;
        e = "(sm-g900|gt-i9600|sm-g870|sm-g800).*";
        f = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(j.a, false, 0.0f, true, false, 0, com.salesforce.marketingcloud.analytics.stats.b.m);
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
        return com.scandit.datacapture.core.internal.module.source.O.a(frameRateRanges, f2);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final void a(Camera.Parameters camParams) {
        Intrinsics.checkNotNullParameter(camParams, "camParams");
        super.a(camParams);
        camParams.set("slow_ae", "off");
        camParams.set("sw-vdis", "off");
        camParams.set("dynamic-range-control", "on");
        camParams.set("phase-af", "on");
        com.scandit.datacapture.core.internal.module.device.profiles.camera.e.a(camParams, Math.max(f.d, -1.0f));
    }
}
