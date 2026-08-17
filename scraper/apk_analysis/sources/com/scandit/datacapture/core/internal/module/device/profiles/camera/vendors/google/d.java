package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google;

/* loaded from: classes2.dex */
public final class d extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final d d;
    public static final String e;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f f;

    static {
        d dVar = new d();
        d = dVar;
        e = "nexus 4";
        f = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(dVar.a, false, 0.0f, true, false, 0, com.salesforce.marketingcloud.analytics.stats.b.o);
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
