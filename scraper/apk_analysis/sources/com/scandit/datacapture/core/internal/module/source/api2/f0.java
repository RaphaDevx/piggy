package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.hardware.camera2.params.RggbChannelVector;
import android.util.Log;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f0 {
    public static final ColorSpaceTransform c = new ColorSpaceTransform(new int[]{1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1});
    public RggbChannelVector a;
    public boolean b;

    public final void a(m0 requestSettings, boolean z) {
        Intrinsics.checkNotNullParameter(requestSettings, "requestSettings");
        if (z || this.b) {
            if (z && this.b) {
                Intrinsics.checkNotNullParameter("CAMCTRL CONTROL_AWB_MODE_AUTO", "message");
                Log.i("sdc-core", "CAMCTRL CONTROL_AWB_MODE_AUTO");
                Intrinsics.checkNotNullParameter(requestSettings, "requestSettings");
                CaptureRequest.Key key = CaptureRequest.CONTROL_AWB_MODE;
                e0.a(key, "CONTROL_AWB_MODE", 1, requestSettings, key);
                this.b = false;
                return;
            }
            return;
        }
        this.b = true;
        Intrinsics.checkNotNullParameter("CAMCTRL CONTROL_AWB_MODE_OFF", "message");
        Log.i("sdc-core", "CAMCTRL CONTROL_AWB_MODE_OFF");
        RggbChannelVector rggbChannelVector = this.a;
        if (rggbChannelVector == null) {
            rggbChannelVector = new RggbChannelVector(1.0f, 1.0f, 1.0f, 1.0f);
        }
        Intrinsics.checkNotNullParameter(requestSettings, "requestSettings");
        CaptureRequest.Key key2 = CaptureRequest.CONTROL_AWB_MODE;
        e0.a(key2, "CONTROL_AWB_MODE", 0, requestSettings, key2);
        CaptureRequest.Key key3 = CaptureRequest.COLOR_CORRECTION_MODE;
        e0.a(key3, "COLOR_CORRECTION_MODE", 0, requestSettings, key3);
        CaptureRequest.Key COLOR_CORRECTION_GAINS = CaptureRequest.COLOR_CORRECTION_GAINS;
        Intrinsics.checkNotNullExpressionValue(COLOR_CORRECTION_GAINS, "COLOR_CORRECTION_GAINS");
        requestSettings.a(COLOR_CORRECTION_GAINS, rggbChannelVector);
        CaptureRequest.Key COLOR_CORRECTION_TRANSFORM = CaptureRequest.COLOR_CORRECTION_TRANSFORM;
        Intrinsics.checkNotNullExpressionValue(COLOR_CORRECTION_TRANSFORM, "COLOR_CORRECTION_TRANSFORM");
        requestSettings.a(COLOR_CORRECTION_TRANSFORM, c);
    }
}
