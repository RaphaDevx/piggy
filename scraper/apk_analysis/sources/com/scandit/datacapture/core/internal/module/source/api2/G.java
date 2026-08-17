package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class G implements F {
    public final TotalCaptureResult a;

    public G(TotalCaptureResult totalCaptureResult) {
        Intrinsics.checkNotNullParameter(totalCaptureResult, "totalCaptureResult");
        this.a = totalCaptureResult;
    }

    public final Integer a() {
        return (Integer) this.a.get(CaptureResult.CONTROL_AF_MODE);
    }

    public final float[] b() {
        return (float[]) this.a.get(CaptureResult.LENS_INTRINSIC_CALIBRATION);
    }
}
