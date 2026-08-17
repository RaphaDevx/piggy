package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewDefaults;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends n {
    public final String c;

    public a() {
        String text = NativeSparkScanViewDefaults.defaultContinuousModeDisabledMessage();
        Intrinsics.checkNotNullExpressionValue(text, "defaultContinuousModeDisabledMessage(...)");
        Intrinsics.checkNotNullParameter(text, "text");
        this.c = text;
    }
}
