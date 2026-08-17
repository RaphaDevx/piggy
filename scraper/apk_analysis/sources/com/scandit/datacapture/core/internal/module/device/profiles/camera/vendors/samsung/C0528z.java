package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

/* renamed from: com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.z, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0528z extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final C0528z d;
    public static final String e;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f f;
    public static final boolean g;

    static {
        C0528z c0528z = new C0528z();
        d = c0528z;
        e = "sm-n920.*";
        f = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(c0528z.a, false, 0.0f, true, false, 0, com.salesforce.marketingcloud.analytics.stats.b.o);
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
