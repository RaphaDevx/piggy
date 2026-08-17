package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewDefaults;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e extends n {
    public final String c;

    public e() {
        String text = NativeSparkScanViewDefaults.defaultScanPausedMessage();
        Intrinsics.checkNotNullExpressionValue(text, "defaultScanPausedMessage(...)");
        Intrinsics.checkNotNullParameter(text, "text");
        this.c = text;
    }
}
