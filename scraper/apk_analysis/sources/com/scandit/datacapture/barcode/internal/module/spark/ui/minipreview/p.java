package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.widget.RelativeLayout;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class p implements com.scandit.datacapture.barcode.internal.module.spark.ui.toast.x {
    public final RelativeLayout a;
    public final com.scandit.datacapture.barcode.internal.module.spark.ui.toast.w b;

    public p(RelativeLayout container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = container;
        Context context = container.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        com.scandit.datacapture.barcode.internal.module.spark.ui.toast.w wVar = new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.w(context);
        this.b = wVar;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMarginStart((int) (container.getWidth() * 0.05f));
        layoutParams.setMarginEnd((int) (container.getWidth() * 0.05f));
        layoutParams.topMargin = ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.x.getValue()).intValue();
        layoutParams.addRule(14);
        layoutParams.addRule(10);
        container.addView(wVar, layoutParams);
        wVar.setElevation(((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.k.getValue()).floatValue());
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.toast.x
    public final void a(String text, int i, int i2) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (!G.a(this.a, this.b)) {
            RelativeLayout relativeLayout = this.a;
            com.scandit.datacapture.barcode.internal.module.spark.ui.toast.w wVar = this.b;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.setMarginStart((int) (this.a.getWidth() * 0.05f));
            layoutParams.setMarginEnd((int) (this.a.getWidth() * 0.05f));
            layoutParams.topMargin = ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.x.getValue()).intValue();
            layoutParams.addRule(14);
            layoutParams.addRule(10);
            relativeLayout.addView(wVar, layoutParams);
            this.b.setElevation(((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.k.getValue()).floatValue());
        }
        this.b.bringToFront();
        com.scandit.datacapture.barcode.internal.module.spark.ui.toast.w wVar2 = this.b;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.setMarginStart((int) (this.a.getWidth() * 0.05f));
        layoutParams2.setMarginEnd((int) (this.a.getWidth() * 0.05f));
        layoutParams2.topMargin = ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.x.getValue()).intValue();
        layoutParams2.addRule(14);
        layoutParams2.addRule(10);
        wVar2.setLayoutParams(layoutParams2);
        this.b.a(text, i, i2);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.toast.x
    public final void h() {
        com.scandit.datacapture.barcode.internal.module.spark.ui.toast.w wVar = this.b;
        wVar.getClass();
        wVar.a(0.0f, new com.scandit.datacapture.barcode.internal.module.spark.ui.toast.u(wVar));
    }
}
