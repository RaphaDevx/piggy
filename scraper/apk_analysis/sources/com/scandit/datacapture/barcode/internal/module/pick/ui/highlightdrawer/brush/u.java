package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush;

import android.content.Context;
import android.graphics.Paint;
import androidx.core.internal.view.SupportMenu;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public abstract class u {
    public static final Paint a;
    public static final Paint b;

    static {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float f = 255;
        paint.setColor(com.scandit.datacapture.barcode.internal.module.extensions.a.a(SupportMenu.CATEGORY_MASK, (int) (RangesKt.coerceIn(0.3f, 0.0f, 1.0f) * f)));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        a = paint;
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(com.scandit.datacapture.barcode.internal.module.extensions.a.a(-16711936, (int) (RangesKt.coerceIn(0.3f, 0.0f, 1.0f) * f)));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(PixelExtensionsKt.pxFromDp$default(2.0f, (Context) null, 1, (Object) null));
        b = paint2;
    }
}
