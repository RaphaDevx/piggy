package com.scandit.datacapture.core.internal.module.device;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d extends NativeDeviceInterface {
    public final a a;

    public d(c batteryStatusProvider) {
        Intrinsics.checkNotNullParameter(batteryStatusProvider, "batteryStatusProvider");
        this.a = batteryStatusProvider;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.NativeDeviceInterface
    public final NativeBatteryData getBatteryData() {
        return ((c) this.a).a();
    }
}
