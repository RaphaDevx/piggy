package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google;

import android.hardware.Camera;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b extends com.scandit.datacapture.core.internal.module.device.profiles.camera.e {
    public static final b c;
    public static final String d;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f e;

    static {
        b bVar = new b();
        c = bVar;
        d = "glass 2";
        e = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(bVar.a, false, 0.0f, false, false, 0, 121);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final void a(Camera.Parameters camParams) {
        Intrinsics.checkNotNullParameter(camParams, "camParams");
        com.scandit.datacapture.core.internal.module.device.profiles.camera.e.a(camParams, Math.max(e.d, -1.0f));
        camParams.setPreviewSize(Math.max(camParams.getPreviewSize().width, camParams.getPreviewSize().height), Math.min(camParams.getPreviewSize().width, camParams.getPreviewSize().height));
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return d;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final com.scandit.datacapture.core.internal.module.device.profiles.camera.f a() {
        return e;
    }
}
