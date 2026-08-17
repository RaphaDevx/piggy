package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.panasonic;

import com.scandit.datacapture.core.internal.module.device.profiles.camera.e;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.f;

/* loaded from: classes2.dex */
public final class a extends e {
    public static final a c;
    public static final String d;
    public static final f e;
    public static final boolean f;

    static {
        a aVar = new a();
        c = aVar;
        d = "fz-n1";
        e = f.a(aVar.a, false, 0.0f, false, false, 0, 125);
        f = true;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final f a() {
        return e;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return d;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean h() {
        return f;
    }
}
