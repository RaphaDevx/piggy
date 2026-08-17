package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

/* loaded from: classes2.dex */
public final class I extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final I d;
    public static final String e;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f f;
    public static final boolean g;

    static {
        I i = new I();
        d = i;
        e = "gt-i9500|gt-i9502|gt-i9505|sc-04e|sch-i545|sch-i959|sch-r970|sgh-i337|sgh-m919|shv-e300k|shv-e300s|sph-l720";
        f = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(i.a, false, -1.0f, false, false, 0, 119);
        g = true;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final com.scandit.datacapture.core.internal.module.device.profiles.camera.f a() {
        return f;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean h() {
        return g;
    }
}
