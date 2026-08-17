package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.RelativeLayout;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0450a extends RelativeLayout {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0450a(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        GradientDrawable gradientDrawable = new GradientDrawable();
        int i = com.scandit.datacapture.barcode.internal.module.spark.ui.E.a;
        gradientDrawable.setCornerRadius(((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.m.getValue()).floatValue());
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(0);
        setBackground(gradientDrawable);
        setId(com.scandit.datacapture.barcode.internal.module.spark.ui.E.d);
        setClipToOutline(true);
    }
}
