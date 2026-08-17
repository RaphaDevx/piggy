package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.sony;

import com.scandit.datacapture.core.internal.module.device.profiles.camera.f;

/* loaded from: classes2.dex */
public final class a extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final a d;
    public static final String e;
    public static final f f;
    public static final boolean g;

    static {
        a aVar = new a();
        d = aVar;
        e = "501so|e6603|e6653|so-01h|sov32|e5803|e5823|so-02h|e6853|so-03h|e6833|e6883|e6633|e6683";
        f = f.a(aVar.a, false, 0.0f, false, false, 0, 121);
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
