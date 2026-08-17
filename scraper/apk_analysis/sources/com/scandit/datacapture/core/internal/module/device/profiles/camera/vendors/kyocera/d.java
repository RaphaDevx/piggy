package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.kyocera;

/* loaded from: classes2.dex */
public final class d extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final d d = new d();
    public static final String e = "(?:kyocera-)?(?:e6820|e6810)";
    public static final boolean f = true;

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean h() {
        return f;
    }
}
