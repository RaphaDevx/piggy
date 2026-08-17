package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.scandit;

import androidx.appcompat.app.AppCompatDelegate;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.f;

/* loaded from: classes2.dex */
public final class a extends com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a {
    public static final a d;
    public static final String e;
    public static final f f;

    static {
        a aVar = new a();
        d = aVar;
        e = "scandit-default-continuous-custom_metering_disabled.*";
        f = f.a(aVar.a, false, 0.0f, true, false, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final f a() {
        return f;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.standard.a, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return e;
    }
}
