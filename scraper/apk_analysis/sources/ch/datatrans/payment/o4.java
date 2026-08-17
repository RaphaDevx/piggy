package ch.datatrans.payment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* loaded from: classes3.dex */
public final class o4 extends ReplacementSpan {
    public final int a;
    public final String b;

    public o4(String dividerSymbol, int i) {
        Intrinsics.checkNotNullParameter(dividerSymbol, "dividerSymbol");
        this.a = i;
        this.b = dividerSymbol;
    }

    @Override // android.text.style.ReplacementSpan
    public final void draw(Canvas canvas, CharSequence text, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(paint, "paint");
        float f2 = i4;
        canvas.drawText(text, i, i2, f, f2, paint);
        canvas.drawText(this.b, paint.measureText(text, i, i2) + ((this.a - paint.measureText(this.b)) / 2) + f, f2, paint);
    }

    @Override // android.text.style.ReplacementSpan
    public final int getSize(Paint paint, CharSequence text, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        Intrinsics.checkNotNullParameter(paint, "paint");
        Intrinsics.checkNotNullParameter(text, "text");
        return MathKt.roundToInt(paint.measureText(text, i, i2) + this.a);
    }
}
