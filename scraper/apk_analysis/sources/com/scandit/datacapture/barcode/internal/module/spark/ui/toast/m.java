package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewDefaults;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class m extends n {
    public final String c;

    public m() {
        String text = NativeSparkScanViewDefaults.defaultZoomedOutMessage();
        Intrinsics.checkNotNullExpressionValue(text, "defaultZoomedOutMessage(...)");
        Intrinsics.checkNotNullParameter(text, "text");
        this.c = text;
    }
}
