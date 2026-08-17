package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

/* renamed from: com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0505b extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final C0505b d = new C0505b();
    public static final String e = "^(SM-A156.*)|(SM-A155.*)|(SM-S156VL)$";
    public static final boolean f = true;

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean c() {
        return f;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }
}
