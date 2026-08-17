package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import com.scandit.datacapture.core.source.CameraSettings;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class m {
    public final CameraSettings a;
    public final int b;
    public final int c;
    public final String d;

    public m(CameraSettings initialCameraSettings, int i, int i2, String currentResolution) {
        Intrinsics.checkNotNullParameter(initialCameraSettings, "initialCameraSettings");
        Intrinsics.checkNotNullParameter(currentResolution, "currentResolution");
        this.a = initialCameraSettings;
        this.b = i;
        this.c = i2;
        this.d = currentResolution;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return Intrinsics.areEqual(this.a, mVar.a) && this.b == mVar.b && this.c == mVar.c && Intrinsics.areEqual(this.d, mVar.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + ((Integer.hashCode(this.c) + ((Integer.hashCode(this.b) + (this.a.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "DynamicResolutionSettings(initialCameraSettings=" + this.a + ", lowFpsBar=" + this.b + ", measuringIntervalMillis=" + this.c + ", currentResolution=" + this.d + ')';
    }
}
