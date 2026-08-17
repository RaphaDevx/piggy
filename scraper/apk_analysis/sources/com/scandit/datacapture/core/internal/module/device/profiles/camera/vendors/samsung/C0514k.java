package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

/* renamed from: com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.k, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0514k extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.b {
    public static final C0514k e;
    public static final String f;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f g;

    static {
        C0514k c0514k = new C0514k();
        e = c0514k;
        f = "(sm-a530|sm-a730).*";
        g = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(c0514k.d, false, 0.0f, false, false, 0, 125);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.b, com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final com.scandit.datacapture.core.internal.module.device.profiles.camera.f a() {
        return g;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.b, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return f;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean g() {
        return true;
    }
}
