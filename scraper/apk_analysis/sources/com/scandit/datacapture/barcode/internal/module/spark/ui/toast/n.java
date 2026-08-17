package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewDefaults;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeColorExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class n {
    public final int a;
    public final int b;

    public n() {
        NativeColor defaultToastTextColor = NativeSparkScanViewDefaults.defaultToastTextColor();
        Intrinsics.checkNotNullExpressionValue(defaultToastTextColor, "defaultToastTextColor(...)");
        this.a = NativeColorExtensionsKt.toInt(defaultToastTextColor);
        NativeColor defaultToastBackgroundColor = NativeSparkScanViewDefaults.defaultToastBackgroundColor();
        Intrinsics.checkNotNullExpressionValue(defaultToastBackgroundColor, "defaultToastBackgroundColor(...)");
        this.b = NativeColorExtensionsKt.toInt(defaultToastBackgroundColor);
    }
}
