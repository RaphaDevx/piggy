package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.j;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.v;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.l;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.m;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class a extends FrameLayout {
    public com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.a a;
    public final j b;
    public final Paint c;
    public final Paint d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Context context, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.a drawData, j drawSettings, Paint tapPaint, Paint boundingBoxPaint) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(drawData, "drawData");
        Intrinsics.checkNotNullParameter(drawSettings, "drawSettings");
        Intrinsics.checkNotNullParameter(tapPaint, "tapPaint");
        Intrinsics.checkNotNullParameter(boundingBoxPaint, "boundingBoxPaint");
        this.a = drawData;
        this.b = drawSettings;
        this.c = tapPaint;
        this.d = boundingBoxPaint;
        setWillNotDraw(false);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.a aVar = this.a;
        if (this.b.a) {
            v vVar = aVar.c;
            if (vVar != null) {
                canvas.drawPath(vVar.b, this.c);
            }
            canvas.drawPath(aVar.d.b, this.d);
        }
        m mVar = aVar.a;
        if (mVar != null) {
            if (mVar.c.isEmpty()) {
                canvas.drawPath(aVar.b.b, mVar.a);
            } else {
                for (l lVar : mVar.c) {
                    Path path = aVar.b.b;
                    Paint paint = mVar.a;
                    paint.setShadowLayer(lVar.a, 0.0f, lVar.b, lVar.c);
                    canvas.drawPath(path, paint);
                    paint.clearShadowLayer();
                }
            }
            canvas.drawPath(aVar.b.b, mVar.b);
        }
        Drawable drawable = aVar.g;
        if (drawable != null) {
            Quadrilateral quadrilateral = aVar.b.a;
            Point center = QuadrilateralUtilsKt.getCenter(aVar.d.a);
            j jVar = this.b;
            float f = jVar.c;
            float f2 = jVar.d;
            float f3 = jVar.e;
            float f4 = 2;
            if (Math.min(QuadrilateralUtilsKt.getWidth(quadrilateral), QuadrilateralUtilsKt.getHeight(quadrilateral)) < (f3 * f4) + f) {
                f = RangesKt.coerceAtMost(RangesKt.coerceAtLeast((float) Math.floor((f / r9) * r2), f2), f);
            }
            float f5 = f / f4;
            drawable.setBounds((int) (center.getX() - f5), (int) (center.getY() - f5), (int) (center.getX() + f5), (int) (center.getY() + f5));
            drawable.draw(canvas);
        }
    }
}
