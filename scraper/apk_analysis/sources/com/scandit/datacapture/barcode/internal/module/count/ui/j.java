package com.scandit.datacapture.barcode.internal.module.count.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j extends View {
    public final float a;
    public final float b;
    public final Path c;
    public final Paint d;
    public final Path e;
    public final Paint f;
    public final RectF g;
    public float h;
    public final ValueAnimator i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = PixelExtensionsKt.pxFromDp$default(3.0f, (Context) null, 1, (Object) null);
        this.b = PixelExtensionsKt.pxFromDp$default(8.0f, (Context) null, 1, (Object) null);
        this.c = new Path();
        int argb = Color.argb(77, 255, 255, 255);
        Paint paint = new Paint();
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        Paint.Cap cap = Paint.Cap.ROUND;
        paint.setStrokeCap(cap);
        paint.setColor(argb);
        paint.setAntiAlias(true);
        this.d = paint;
        this.e = new Path();
        Paint paint2 = new Paint();
        paint2.setStyle(style);
        paint2.setStrokeCap(cap);
        paint2.setColor(-1);
        paint2.setAntiAlias(true);
        this.f = paint2;
        this.g = new RectF();
        ValueAnimator ofInt = ValueAnimator.ofInt(0, 359);
        ofInt.setDuration(500L);
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.j$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                j.b(j.this, valueAnimator);
            }
        });
        ValueAnimator ofInt2 = ValueAnimator.ofInt(0, 359);
        ofInt2.setDuration(1000L);
        ofInt2.setInterpolator(new LinearInterpolator());
        ofInt2.setRepeatMode(1);
        ofInt2.setRepeatCount(-1);
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.j$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                j.a(j.this, valueAnimator);
            }
        });
        this.i = ofInt2;
    }

    public static final void a(j this$0, ValueAnimator animator) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(animator, "animator");
        Object animatedValue = animator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        float intValue = ((Integer) animatedValue).intValue();
        Path path = this$0.e;
        path.reset();
        path.arcTo(this$0.g, intValue - 90.0f, 30.0f);
        this$0.invalidate();
    }

    public static final void b(j this$0, ValueAnimator animator) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(animator, "animator");
        Intrinsics.checkNotNull(animator.getAnimatedValue(), "null cannot be cast to non-null type kotlin.Int");
        float f = 359;
        this$0.h = ((Integer) r4).intValue() / f;
        Path path = this$0.e;
        path.reset();
        path.arcTo(this$0.g, -90.0f, this$0.h * f);
        this$0.invalidate();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawPath(this.c, this.d);
        canvas.drawPath(this.e, this.f);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        RectF rectF = this.g;
        float strokeWidth = this.f.getStrokeWidth() / 2;
        rectF.left = strokeWidth;
        rectF.top = strokeWidth;
        float f = i - strokeWidth;
        rectF.right = f;
        rectF.bottom = f;
        Path path = this.c;
        path.reset();
        path.arcTo(this.g, -90.0f, 359.0f);
    }

    public static void a(j jVar) {
        boolean z;
        float f = jVar.b;
        boolean z2 = true;
        if (jVar.f.getStrokeWidth() == f) {
            z = false;
        } else {
            jVar.f.setStrokeWidth(f);
            z = true;
        }
        if (jVar.d.getStrokeWidth() == f) {
            z2 = z;
        } else {
            jVar.d.setStrokeWidth(f);
        }
        if (z2) {
            int width = jVar.getWidth();
            RectF rectF = jVar.g;
            float strokeWidth = jVar.f.getStrokeWidth() / 2;
            rectF.left = strokeWidth;
            rectF.top = strokeWidth;
            float f2 = width - strokeWidth;
            rectF.right = f2;
            rectF.bottom = f2;
            Path path = jVar.c;
            path.reset();
            path.arcTo(jVar.g, -90.0f, 359.0f);
        }
        jVar.i.start();
    }

    public final void a(float f) {
        boolean z;
        this.i.cancel();
        float f2 = this.a;
        boolean z2 = true;
        if (this.f.getStrokeWidth() == f2) {
            z = false;
        } else {
            this.f.setStrokeWidth(f2);
            z = true;
        }
        if (this.d.getStrokeWidth() == f2) {
            z2 = z;
        } else {
            this.d.setStrokeWidth(f2);
        }
        if (z2) {
            int width = getWidth();
            RectF rectF = this.g;
            float strokeWidth = this.f.getStrokeWidth() / 2;
            rectF.left = strokeWidth;
            rectF.top = strokeWidth;
            float f3 = width - strokeWidth;
            rectF.right = f3;
            rectF.bottom = f3;
            Path path = this.c;
            path.reset();
            path.arcTo(this.g, -90.0f, 359.0f);
        }
        this.h = f;
        Path path2 = this.e;
        path2.reset();
        path2.arcTo(this.g, -90.0f, this.h * 359);
        invalidate();
    }
}
