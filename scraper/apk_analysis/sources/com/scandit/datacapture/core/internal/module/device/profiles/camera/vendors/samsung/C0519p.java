package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

/* renamed from: com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.p, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0519p extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final C0519p d;
    public static final String e;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f f;

    static {
        C0519p c0519p = new C0519p();
        d = c0519p;
        e = "sm-g355.*";
        f = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(c0519p.a, true, 0.0f, false, false, 0, 120);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final com.scandit.datacapture.core.internal.module.device.profiles.camera.f a() {
        return f;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }
}
