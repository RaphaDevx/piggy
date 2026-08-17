package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

/* loaded from: classes2.dex */
public final class P extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.b {
    public static final P e;
    public static final String f;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f g;

    static {
        P p = new P();
        e = p;
        f = "sm-g955.*";
        g = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(p.d, false, 0.0f, true, false, 0, com.salesforce.marketingcloud.analytics.stats.b.o);
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
