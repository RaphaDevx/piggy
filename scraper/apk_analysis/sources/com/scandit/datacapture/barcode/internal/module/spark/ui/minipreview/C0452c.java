package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0452c extends View {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0452c(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        GradientDrawable gradientDrawable = new GradientDrawable();
        int i = com.scandit.datacapture.barcode.internal.module.spark.ui.E.a;
        gradientDrawable.setCornerRadius(((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.m.getValue()).floatValue());
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(com.scandit.datacapture.barcode.internal.module.spark.ui.E.D);
        setBackground(gradientDrawable);
        setAlpha(0.0f);
        setVisibility(8);
    }
}
