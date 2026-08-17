package com.scandit.datacapture.core.internal.module.device.profiles.camera.standard;

import android.hardware.Camera;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.e;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.f;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public class b extends e {
    public final String c = "scandit-phase_af|scandit-phase_af-initial_trigger";
    public final f d = f.a(this.a, false, 0.0f, false, false, 0, 125);

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public f a() {
        return this.d;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public String f() {
        return this.c;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final void a(Camera.Parameters camParams) {
        Intrinsics.checkNotNullParameter(camParams, "camParams");
        camParams.set("phase-af", "on");
        camParams.set("dynamic-range-control", "on");
        e.a(camParams, Math.max(a().d, -1.0f));
    }
}
