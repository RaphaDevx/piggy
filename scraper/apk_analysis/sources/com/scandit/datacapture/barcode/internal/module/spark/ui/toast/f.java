package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewDefaults;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f extends n {
    public final String c;

    public f() {
        String text = NativeSparkScanViewDefaults.defaultTargetModeDisabledMessage();
        Intrinsics.checkNotNullExpressionValue(text, "defaultTargetModeDisabledMessage(...)");
        Intrinsics.checkNotNullParameter(text, "text");
        this.c = text;
    }
}
