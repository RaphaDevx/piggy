package com.scandit.datacapture.barcode.internal.module.count.ui.status;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.view.ViewCompat;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends View {
    public final Paint a;
    public final Matrix b;
    public LinearGradient c;
    public float d;
    public float e;
    public float f;
    public final ValueAnimator g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Paint paint = new Paint(1);
        paint.setStrokeWidth(0.0f);
        this.a = paint;
        this.b = new Matrix();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(1500L);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.status.a$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                a.a(a.this, valueAnimator);
            }
        });
        this.g = ofFloat;
    }

    public static final void a(a this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        this$0.d = ((Float) animatedValue).floatValue();
        this$0.b.reset();
        this$0.b.postTranslate(this$0.f * this$0.d, 0.0f);
        LinearGradient linearGradient = this$0.c;
        if (linearGradient != null) {
            linearGradient.setLocalMatrix(this$0.b);
        }
        this$0.invalidate();
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        this.g.cancel();
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        float f = this.e;
        canvas.drawCircle(f, f, f, this.a);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float f = i;
        this.e = f / 2.0f;
        this.f = f * 2.0f;
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, this.f, 0.0f, new int[]{-1, ViewCompat.MEASURED_STATE_MASK, -1}, (float[]) null, Shader.TileMode.REPEAT);
        this.c = linearGradient;
        this.a.setShader(linearGradient);
        this.g.start();
    }
}
