package ch.datatrans.payment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.q0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0199q0 extends DynamicDrawableSpan {
    public final Drawable a;
    public final int b;

    public C0199q0(Drawable drawable, int i) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        this.a = drawable;
        this.b = i;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public final void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(paint, "paint");
        canvas.save();
        canvas.translate(f, (((paint.getFontMetricsInt().ascent + paint.getFontMetricsInt().descent) - this.a.getBounds().height()) / 2) + i4 + this.b);
        this.a.draw(canvas);
        canvas.restore();
    }

    @Override // android.text.style.DynamicDrawableSpan
    public final Drawable getDrawable() {
        return this.a;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public final int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        Intrinsics.checkNotNullParameter(paint, "paint");
        Rect bounds = this.a.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return bounds.right;
    }
}
