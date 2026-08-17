package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.sonim;

import com.scandit.datacapture.core.internal.module.device.profiles.camera.f;

/* loaded from: classes2.dex */
public final class c extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final c d;
    public static final String e;
    public static final f f;
    public static final boolean g;

    static {
        c cVar = new c();
        d = cVar;
        e = "xp8800";
        f = f.a(cVar.a, false, 0.0f, false, true, 0, 95);
        g = true;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final f a() {
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
