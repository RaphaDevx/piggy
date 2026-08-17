package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

import com.scandit.datacapture.core.internal.module.source.NativeFocusStrategy;

/* renamed from: com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0508e extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final C0508e d = new C0508e();
    public static final String e = "sm-a525f(/ds)?|sm-a525m(/ds)?|sm-a526b(/ds)?|sm-a5260|sm-a526w|sm-a526u1?";
    public static final boolean f = true;

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final NativeFocusStrategy e() {
        return NativeFocusStrategy.AUTO;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean g() {
        return true;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean i() {
        return f;
    }
}
