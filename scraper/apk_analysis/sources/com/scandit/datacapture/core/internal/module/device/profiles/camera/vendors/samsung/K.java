package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

/* loaded from: classes2.dex */
public final class K extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final K d;
    public static final String e;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f f;

    static {
        K k = new K();
        d = k;
        e = "sm-g920.*|scv31|404sc|sm-g890a";
        f = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(k.a, false, -1.0f, false, false, 0, 119);
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
