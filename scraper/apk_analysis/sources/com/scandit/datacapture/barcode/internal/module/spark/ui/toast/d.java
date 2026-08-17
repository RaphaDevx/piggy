package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewDefaults;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeColorExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d extends n {
    public final String c;
    public final int d;
    public final int e;

    public d(String text) {
        NativeColor defaultErrorToastBackgroundColor = NativeSparkScanViewDefaults.defaultErrorToastBackgroundColor();
        Intrinsics.checkNotNullExpressionValue(defaultErrorToastBackgroundColor, "defaultErrorToastBackgroundColor(...)");
        int i = NativeColorExtensionsKt.toInt(defaultErrorToastBackgroundColor);
        NativeColor defaultErrorToastTextColor = NativeSparkScanViewDefaults.defaultErrorToastTextColor();
        Intrinsics.checkNotNullExpressionValue(defaultErrorToastTextColor, "defaultErrorToastTextColor(...)");
        int i2 = NativeColorExtensionsKt.toInt(defaultErrorToastTextColor);
        Intrinsics.checkNotNullParameter(text, "text");
        this.c = text;
        this.d = i;
        this.e = i2;
    }
}
