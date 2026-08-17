package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

import com.scandit.datacapture.core.internal.module.source.NativeFocusStrategy;

/* loaded from: classes2.dex */
public final class W extends com.scandit.datacapture.core.internal.module.device.profiles.camera.e {
    public static final W c;
    public static final String d;
    public static final com.scandit.datacapture.core.internal.module.device.profiles.camera.f e;

    static {
        W w = new W();
        c = w;
        d = "sm-g736u|sm-g736u1|sm-g736b";
        e = com.scandit.datacapture.core.internal.module.device.profiles.camera.f.a(w.a, false, 0.0f, false, false, 1, 63);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final com.scandit.datacapture.core.internal.module.device.profiles.camera.f a() {
        return e;
    }

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
