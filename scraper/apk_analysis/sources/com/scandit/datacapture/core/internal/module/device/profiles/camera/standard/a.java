package com.scandit.datacapture.core.internal.module.device.profiles.camera.standard;

import android.hardware.Camera;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.e;
import io.sentry.SentryOptions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public class a extends e {
    public final String c = SentryOptions.DEFAULT_PROPAGATION_TARGETS;

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.e, com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public void a(Camera.Parameters camParams) {
        Intrinsics.checkNotNullParameter(camParams, "camParams");
        e.a(camParams, Math.max(a().d, -1.0f));
        String str = camParams.get("min-sharpness");
        Integer intOrNull = str != null ? StringsKt.toIntOrNull(str) : null;
        String str2 = camParams.get("max-sharpness");
        Integer intOrNull2 = str2 != null ? StringsKt.toIntOrNull(str2) : null;
        if (intOrNull == null || intOrNull2 == null) {
            camParams.set("sharpness", "0");
        } else {
            if (intOrNull.intValue() > 0 || intOrNull2.intValue() < 0) {
                return;
            }
            camParams.set("sharpness", "0");
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public String f() {
        return this.c;
    }
}
