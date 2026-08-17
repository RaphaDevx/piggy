package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle;

import android.content.Context;
import android.graphics.Paint;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import com.scandit.datacapture.core.ui.style.Brush;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class a {
    public static final com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.m a(BarcodePickBrush barcodePickBrush, List shadows) {
        Intrinsics.checkNotNullParameter(barcodePickBrush, "<this>");
        Intrinsics.checkNotNullParameter(shadows, "shadows");
        Brush asBrush = barcodePickBrush.asBrush();
        if (asBrush == null) {
            return null;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(asBrush.getFillColor());
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(asBrush.getStrokeColor());
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(PixelExtensionsKt.pxFromDp$default(asBrush.getStrokeWidth(), (Context) null, 1, (Object) null));
        return new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.m(paint, paint2, shadows);
    }
}
