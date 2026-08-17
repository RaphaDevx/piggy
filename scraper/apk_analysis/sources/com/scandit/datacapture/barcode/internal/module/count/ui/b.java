package com.scandit.datacapture.barcode.internal.module.count.ui;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class b {
    public static final int a = Color.parseColor("#80000000");

    public static final void a(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setTextSize(2, 16.0f);
        textView.setTextColor(-1);
        textView.setBackgroundColor(0);
        textView.setShadowLayer(PixelExtensionsKt.pxFromDp$default(4, (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(0, (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(2, (Context) null, 1, (Object) null), a);
    }
}
