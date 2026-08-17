package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview;

import android.graphics.Paint;
import androidx.core.internal.view.SupportMenu;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public abstract class k {
    public static final Paint a;

    static {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(com.scandit.datacapture.barcode.internal.module.extensions.a.a(SupportMenu.CATEGORY_MASK, (int) (RangesKt.coerceIn(0.3f, 0.0f, 1.0f) * 255)));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        a = paint;
    }
}
