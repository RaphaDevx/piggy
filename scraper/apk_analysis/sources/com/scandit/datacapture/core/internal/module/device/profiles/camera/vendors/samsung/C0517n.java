package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

import android.hardware.Camera;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.n, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0517n extends com.scandit.datacapture.core.internal.module.device.profiles.camera.e {
    public static final C0517n c;
    public static final String d;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f e;

    static {
        C0517n c0517n = new C0517n();
        c = c0517n;
        d = "sm-t365.*";
        e = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(c0517n.a, false, 0.0f, true, false, 0, com.salesforce.marketingcloud.analytics.stats.b.o);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final com.scandit.datacapture.core.internal.module.device.profiles.camera.f a() {
        return e;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return d;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final void a(Camera.Parameters camParams) {
        Intrinsics.checkNotNullParameter(camParams, "camParams");
        camParams.set("slow_ae", "on");
        camParams.set("sw-vdis", "off");
        camParams.set("sharpness", "0");
        camParams.set("video_recording_gamma", "on");
        camParams.set("denoise", "denoise-off");
        camParams.set("zsl", "off");
        com.scandit.datacapture.core.internal.module.device.profiles.camera.e.a(camParams, Math.max(e.d, -1.0f));
    }
}
