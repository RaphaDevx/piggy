package com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0015\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/BinDrawable;", "Landroid/graphics/drawable/Drawable;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/graphics/Rect;", "bounds", "", "onBoundsChange", "(Landroid/graphics/Rect;)V", "Landroid/graphics/Canvas;", "canvas", "draw", "(Landroid/graphics/Canvas;)V", "", "alpha", "setAlpha", "(I)V", "Landroid/graphics/ColorFilter;", "colorFilter", "setColorFilter", "(Landroid/graphics/ColorFilter;)V", "getOpacity", "()I", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BinDrawable extends Drawable {
    private final float a;
    private final float b;
    private final float c;
    private final float d;
    private final float e;
    private final Path f;
    private final Paint g;

    public BinDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        float pxFromDp = PixelExtensionsKt.pxFromDp(1.0f, context);
        this.a = pxFromDp;
        this.b = PixelExtensionsKt.pxFromDp(16.0f, context) - (pxFromDp / 2.0f);
        this.c = PixelExtensionsKt.pxFromDp(4.0f, context);
        this.d = PixelExtensionsKt.pxFromDp(80.0f, context);
        this.e = PixelExtensionsKt.pxFromDp(20.0f, context);
        this.f = new Path();
        Paint paint = new Paint();
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStrokeWidth(pxFromDp);
        paint.setStyle(Paint.Style.STROKE);
        float f = 2 * pxFromDp;
        paint.setPathEffect(new DashPathEffect(new float[]{f, f}, 0.0f));
        this.g = paint;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawPath(this.f, this.g);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        int width = bounds.width();
        int height = bounds.height();
        this.f.rewind();
        Path path = this.f;
        float f = this.a / 2.0f;
        float f2 = width;
        float f3 = height - f;
        float f4 = this.b;
        path.addRoundRect(f, f, f2 - f, f3, new float[]{f4, f4, f4, f4, f4, f4, f4, f4}, Path.Direction.CW);
        float f5 = (f2 - this.d) / 2.0f;
        this.f.moveTo(f5 - this.c, this.a);
        this.f.lineTo(f5, this.e);
        this.f.lineTo(this.d + f5, this.e);
        this.f.lineTo(f5 + this.d + this.c, this.a);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
