package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

/* renamed from: com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.g, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0510g extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final C0510g d = new C0510g();
    public static final String e = "^SM-((A536(B|U1?|0|E|V|W|N)(\\/DS)?)|(S536DL))$";
    public static final boolean f = true;
    public static final int g = 9000000;

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final int b() {
        return g;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean i() {
        return f;
    }
}
