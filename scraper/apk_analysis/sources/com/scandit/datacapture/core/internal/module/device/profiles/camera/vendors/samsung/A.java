package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

/* loaded from: classes2.dex */
public final class A extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.b {
    public static final A e;
    public static final String f;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f g;

    static {
        A a = new A();
        e = a;
        f = "sm-n950.*";
        g = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(a.d, false, 0.0f, false, false, 0, 125);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.b, com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final com.scandit.datacapture.core.internal.module.device.profiles.camera.f a() {
        return g;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.b, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return f;
    }
}
