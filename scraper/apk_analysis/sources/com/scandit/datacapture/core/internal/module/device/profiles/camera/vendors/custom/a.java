package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.custom;

import android.hardware.Camera;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.e;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.f;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends e {
    public static final a c;
    public static final String d;
    public static final f e;

    static {
        a aVar = new a();
        c = aVar;
        d = "carbon mobile 5";
        e = f.a(aVar.a, false, -1.0f, false, false, 0, 115);
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
    public final void a(Camera.Parameters camParams) {
        Intrinsics.checkNotNullParameter(camParams, "camParams");
        camParams.set("sharpness", 0);
        camParams.set("mce", "disable");
        camParams.set("contrast", 0);
        e.a(camParams, Math.max(e.d, -1.0f));
    }
}
