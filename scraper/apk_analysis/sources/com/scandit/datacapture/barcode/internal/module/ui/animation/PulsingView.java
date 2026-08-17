package com.scandit.datacapture.barcode.internal.module.ui.animation;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class PulsingView extends View implements Animatable {
    public static final /* synthetic */ int h = 0;
    private int a;
    private int b;
    private int[] c;
    private AnimatorSet d;
    private Path e;
    private boolean f;
    private float g;

    public PulsingView(Context context, int i, int i2, int[] iArr) {
        super(context);
        Path path;
        this.a = i;
        this.b = i2;
        this.c = iArr;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColors(iArr);
        gradientDrawable.setGradientType(1);
        gradientDrawable.setShape(1);
        gradientDrawable.setGradientRadius(i / 2.0f);
        setBackground(gradientDrawable);
        setVisibility(8);
        if (i2 == 0) {
            path = null;
        } else {
            path = new Path();
            float f = i / 2;
            path.addCircle(f, f, i2 / 2, Path.Direction.CW);
        }
        this.e = path;
    }

    public final void a(int i) {
        Path path;
        if (this.b == i) {
            return;
        }
        this.b = i;
        int i2 = this.a;
        if (i == 0) {
            path = null;
        } else {
            Path path2 = new Path();
            float f = i2 / 2;
            path2.addCircle(f, f, i / 2, Path.Direction.CW);
            path = path2;
        }
        this.e = path;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.save();
        Path path = this.e;
        if (path != null) {
            canvas.clipOutPath(path);
        }
        float f = this.a / 2.0f;
        float f2 = this.g;
        canvas.scale(f2, f2, f, f);
        super.draw(canvas);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.f;
    }

    @Override // android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean z = this.f;
        if (z && z) {
            setVisibility(0);
            setAlpha(1.0f);
            this.g = 0.0f;
            invalidate();
            a(new a(this));
        }
    }

    @Override // android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatorSet animatorSet = this.d;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        this.d = null;
        setVisibility(8);
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        if (this.f) {
            return;
        }
        this.f = true;
        if (isAttachedToWindow()) {
            setVisibility(0);
            if (this.f) {
                setVisibility(0);
                setAlpha(1.0f);
                this.g = 0.0f;
                invalidate();
                a(new a(this));
            }
        }
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        AnimatorSet animatorSet = this.d;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        this.d = null;
        setVisibility(8);
        this.f = false;
    }

    public final void b(int i) {
        if (this.a == i) {
            return;
        }
        this.a = i;
        getLayoutParams().width = i;
        getLayoutParams().height = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(PulsingView this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        this$0.setAlpha(((Float) animatedValue).floatValue());
    }

    public final void a(int[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Arrays.equals(this.c, value)) {
            return;
        }
        this.c = value;
        Drawable background = getBackground();
        GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
        if (gradientDrawable == null) {
            return;
        }
        gradientDrawable.setColors(value);
    }

    public static final void a(PulsingView pulsingView) {
        if (pulsingView.f) {
            pulsingView.setVisibility(0);
            pulsingView.setAlpha(1.0f);
            pulsingView.g = 0.0f;
            pulsingView.invalidate();
            pulsingView.a(new a(pulsingView));
        }
    }

    private final void a(a aVar) {
        AnimatorSet animatorSet = this.d;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setInterpolator(new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f));
        ofFloat.setDuration(1000L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.module.ui.animation.PulsingView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                PulsingView.a(PulsingView.this, valueAnimator);
            }
        });
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat2.setInterpolator(new LinearInterpolator());
        ofFloat2.setStartDelay(600L);
        ofFloat2.setDuration(400L);
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.module.ui.animation.PulsingView$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                PulsingView.b(PulsingView.this, valueAnimator);
            }
        });
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(ofFloat, ofFloat2);
        animatorSet2.addListener(new b(this, aVar));
        animatorSet2.start();
        this.d = animatorSet2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(PulsingView this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        this$0.g = ((Float) animatedValue).floatValue();
        this$0.invalidate();
    }
}
