package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.google;

import com.scandit.datacapture.core.internal.module.source.NativeFocusStrategy;

/* loaded from: classes2.dex */
public final class l extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final l d = new l();
    public static final String e = "Pixel 8a";

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final NativeFocusStrategy e() {
        return NativeFocusStrategy.FORCE_CONTINUOUS;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }
}
