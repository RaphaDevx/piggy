package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.zebra;

import com.scandit.datacapture.core.internal.module.source.NativeFocusStrategy;

/* loaded from: classes2.dex */
public final class b extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final b d = new b();
    public static final String e = "TC52.*";
    public static final boolean f = true;
    public static final boolean g = true;

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final NativeFocusStrategy e() {
        return NativeFocusStrategy.FORCE_RETRIGGER;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean h() {
        return g;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean i() {
        return f;
    }
}
