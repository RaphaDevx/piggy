package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewDefaults;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeColorExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class g extends n {
    public final String c;
    public final int d;

    public g() {
        String text = NativeSparkScanViewDefaults.defaultTargetModeEnabledMessage();
        Intrinsics.checkNotNullExpressionValue(text, "defaultTargetModeEnabledMessage(...)");
        NativeColor defaultToastEnabledBackgroundColor = NativeSparkScanViewDefaults.defaultToastEnabledBackgroundColor();
        Intrinsics.checkNotNullExpressionValue(defaultToastEnabledBackgroundColor, "defaultToastEnabledBackgroundColor(...)");
        int i = NativeColorExtensionsKt.toInt(defaultToastEnabledBackgroundColor);
        Intrinsics.checkNotNullParameter(text, "text");
        this.c = text;
        this.d = i;
    }
}
