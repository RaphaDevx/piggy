package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

import android.hardware.Camera;
import androidx.appcompat.app.AppCompatDelegate;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class N extends com.scandit.datacapture.core.internal.module.device.profiles.camera.e {
    public static final N c;
    public static final String d;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f e;

    static {
        N n = new N();
        c = n;
        d = "sm-g891a";
        e = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(n.a, false, 0.0f, true, false, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY);
        com.scandit.datacapture.core.internal.module.device.profiles.camera.a aVar = n.b;
        String focusModeContinuousVideo = aVar.b;
        String focusModeAuto = aVar.c;
        Intrinsics.checkNotNullParameter("object-tracking-picture", "focusModeContinuousPicture");
        Intrinsics.checkNotNullParameter(focusModeContinuousVideo, "focusModeContinuousVideo");
        Intrinsics.checkNotNullParameter(focusModeAuto, "focusModeAuto");
        Intrinsics.checkNotNullParameter("object-tracking-picture", "focusModeContinuousPicture");
        Intrinsics.checkNotNullParameter(focusModeContinuousVideo, "focusModeContinuousVideo");
        Intrinsics.checkNotNullParameter(focusModeAuto, "focusModeAuto");
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
        camParams.set("phase-af", "on");
        camParams.set("dynamic-range-control", "on");
        camParams.set("slow_ae", "on");
        camParams.set("sw-vdis", "off");
        camParams.set("sharpness", "0");
        camParams.set("video_recording_gamma", "on");
        camParams.set("denoise", "denoise-off");
        camParams.set("zsl", "off");
        com.scandit.datacapture.core.internal.module.device.profiles.camera.e.a(camParams, Math.max(e.d, -1.0f));
    }
}
