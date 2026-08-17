package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewDefaults;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class k extends n {
    public final String c;

    public k() {
        String text = NativeSparkScanViewDefaults.defaultWorldFacingCameraEnabledMessage();
        Intrinsics.checkNotNullExpressionValue(text, "defaultWorldFacingCameraEnabledMessage(...)");
        Intrinsics.checkNotNullParameter(text, "text");
        this.c = text;
    }
}
