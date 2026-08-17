package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

import com.scandit.datacapture.core.internal.module.source.NativeCameraApi;

/* renamed from: com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.m, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0516m extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final C0516m d = new C0516m();
    public static final String e = "sm-t575.*";
    public static final NativeCameraApi f = NativeCameraApi.CAMERA2;

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final NativeCameraApi j() {
        return f;
    }
}
