package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

import com.scandit.datacapture.core.internal.module.source.NativeFocusStrategy;

/* loaded from: classes2.dex */
public final class F extends com.scandit.datacapture.core.internal.module.device.profiles.camera.e {
    public static final F c = new F();
    public static final String d = "sm-g991.*|sc-51b|scg09|sm-g998.*|sc-52b";

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final NativeFocusStrategy e() {
        return NativeFocusStrategy.CONTINUOUS_UNTIL_NO_SCAN;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return d;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean i() {
        return true;
    }
}
