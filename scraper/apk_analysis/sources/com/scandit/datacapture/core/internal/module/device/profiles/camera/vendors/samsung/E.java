package com.scandit.datacapture.core.internal.module.device.profiles.camera.vendors.samsung;

/* loaded from: classes2.dex */
public final class E extends com.scandit.datacapture.core.internal.module.device.profiles.camera.e {
    public static final E c = new E();
    public static final String d = "sm-g986.*|sc-52a|scg02|sm-g981.*|sc-51a|scg01|sm-g781.*|sm-g780.*|sm-g988.*|scg03";

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final String f() {
        return d;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean i() {
        return true;
    }
}
