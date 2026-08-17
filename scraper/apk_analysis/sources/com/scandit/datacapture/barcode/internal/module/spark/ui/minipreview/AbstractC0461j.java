package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.spark.ui.SparkScanMiniPreviewSize;
import kotlin.Lazy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.j, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0461j extends RelativeLayout {
    public final ImageView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC0461j(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        ImageView imageView = new ImageView(context);
        this.a = imageView;
        int i = com.scandit.datacapture.barcode.internal.module.spark.ui.E.a;
        Lazy lazy = com.scandit.datacapture.barcode.internal.module.spark.ui.E.A;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((Number) lazy.getValue()).intValue(), ((Number) lazy.getValue()).intValue());
        layoutParams.addRule(13);
        Unit unit = Unit.INSTANCE;
        addView(imageView, layoutParams);
    }

    public final void a(SparkScanMiniPreviewSize miniPreviewSize) {
        int intValue;
        Intrinsics.checkNotNullParameter(miniPreviewSize, "miniPreviewSize");
        int i = AbstractC0460i.a[miniPreviewSize.ordinal()];
        if (i == 1) {
            int i2 = com.scandit.datacapture.barcode.internal.module.spark.ui.E.a;
            intValue = ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.B.getValue()).intValue();
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            int i3 = com.scandit.datacapture.barcode.internal.module.spark.ui.E.a;
            intValue = ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.A.getValue()).intValue();
        }
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        layoutParams.width = intValue;
        layoutParams.height = intValue;
    }

    @Override // android.view.View
    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        this.a.setEnabled(z);
    }
}
