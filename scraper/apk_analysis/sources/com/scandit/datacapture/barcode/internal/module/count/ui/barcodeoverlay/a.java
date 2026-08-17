package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeoverlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.H;
import com.scandit.datacapture.core.ui.style.Brush;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends View {
    public final Paint a;
    public List b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Paint paint = new Paint();
        paint.setColor(0);
        paint.setStyle(Paint.Style.FILL);
        this.a = paint;
        this.b = CollectionsKt.emptyList();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        for (H h : this.b) {
            Brush brush = h.b;
            int fillColor = brush != null ? brush.getFillColor() : 0;
            if (fillColor != 0) {
                this.a.setColor(fillColor);
                canvas.drawPath(h.a, this.a);
            }
        }
    }
}
