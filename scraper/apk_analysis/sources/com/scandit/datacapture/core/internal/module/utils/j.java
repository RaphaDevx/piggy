package com.scandit.datacapture.core.internal.module.utils;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j extends Drawable {
    public final Bitmap a;
    public final float b;
    public final RectF c;
    public final Bitmap d;
    public final RectF e;
    public final Paint f;

    public j(Bitmap bitmap, float f) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.a = bitmap;
        this.b = f;
        this.c = new RectF(f, f, bitmap.getWidth(), bitmap.getHeight());
        int i = (int) (2 * f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth() + i, bitmap.getHeight() + i, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setColor(BasicMeasure.EXACTLY);
        paint.setMaskFilter(new BlurMaskFilter(f, BlurMaskFilter.Blur.OUTER));
        Bitmap extractAlpha = bitmap.extractAlpha();
        Intrinsics.checkNotNullExpressionValue(extractAlpha, "extractAlpha(...)");
        canvas.drawBitmap(extractAlpha, f, f, paint);
        extractAlpha.recycle();
        this.d = createBitmap;
        this.e = new RectF(0.0f, 0.0f, createBitmap.getWidth(), createBitmap.getHeight());
        this.f = new Paint(3);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawBitmap(this.d, (Rect) null, this.e, this.f);
        canvas.drawBitmap(this.a, (Rect) null, this.c, this.f);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.d.getHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.d.getWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        super.onBoundsChange(bounds);
        float width = this.d.getWidth() / bounds.width();
        float height = this.d.getHeight() / bounds.height();
        this.e.set(bounds);
        RectF rectF = this.c;
        rectF.set(bounds);
        float f = this.b;
        rectF.inset(width * f, f * height);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.f.getAlpha() != i) {
            this.f.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
