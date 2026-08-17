package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.custom;

import android.hardware.Camera;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.e;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c extends e {
    public static final c c = new c();
    public static final String d = "m100";

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final void a(Camera.Parameters camParams) {
        Intrinsics.checkNotNullParameter(camParams, "camParams");
        e.a(camParams, Math.max(this.a.d, -1.0f));
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return d;
    }
}
