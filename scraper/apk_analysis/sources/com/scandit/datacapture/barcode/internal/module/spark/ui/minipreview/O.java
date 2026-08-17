package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class O extends ViewOutlineProvider {
    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(outline, "outline");
        int i = com.scandit.datacapture.barcode.internal.module.spark.ui.E.a;
        int floatValue = (int) ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.m.getValue()).floatValue();
        outline.setRoundRect(0, -floatValue, view.getWidth(), view.getHeight(), floatValue);
    }
}
