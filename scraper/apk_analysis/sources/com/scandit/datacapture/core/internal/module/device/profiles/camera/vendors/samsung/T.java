package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

/* loaded from: classes2.dex */
public final class T extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final T d;
    public static final String e;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f f;

    static {
        T t = new T();
        d = t;
        e = "gt-p1000";
        f = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(t.a, true, 0.0f, false, false, 0, 122);
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
