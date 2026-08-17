package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.util.Size;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.f, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0455f {
    public final ViewGroup a;
    public boolean b;
    public ViewPropertyAnimator c;

    public C0455f(ViewGroup container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = container;
    }

    public final void a(final C endAction) {
        Intrinsics.checkNotNullParameter(endAction, "endAction");
        this.c = this.a.animate().alpha(0.0f).setDuration(this.b ? 500L : 0L).setStartDelay(this.b ? 500L : 0L).setInterpolator(new LinearInterpolator()).withEndAction(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.f$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                C0455f.a(C0455f.this, endAction);
            }
        });
        this.b = false;
    }

    public static final void a(C0455f this$0, Function0 endAction) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(endAction, "$endAction");
        this$0.c = null;
        endAction.invoke();
    }

    public final void a() {
        ViewPropertyAnimator viewPropertyAnimator = this.c;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
        this.a.setAlpha(1.0f);
    }

    public final void a(D action) {
        Intrinsics.checkNotNullParameter(action, "action");
        if (this.c != null) {
            this.a.setAlpha(1.0f);
            this.b = true;
            action.invoke();
        }
    }

    public final void a(final ViewGroup.MarginLayoutParams layoutParams, PointF position, Size currentSize, Size nextSize, boolean z, E onEndAction) {
        Intrinsics.checkNotNullParameter(layoutParams, "layoutParams");
        Intrinsics.checkNotNullParameter(position, "position");
        Intrinsics.checkNotNullParameter(currentSize, "currentSize");
        Intrinsics.checkNotNullParameter(nextSize, "nextSize");
        Intrinsics.checkNotNullParameter(onEndAction, "onEndAction");
        if (!z) {
            layoutParams.width = nextSize.getWidth();
            layoutParams.height = nextSize.getHeight();
            this.a.setX(position.x);
            this.a.setY(position.y);
            onEndAction.invoke();
            return;
        }
        int width = currentSize.getWidth();
        int width2 = nextSize.getWidth();
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.f$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                C0455f.a(layoutParams, this, valueAnimator);
            }
        };
        ValueAnimator ofInt = ValueAnimator.ofInt(width, width2);
        ofInt.addUpdateListener(animatorUpdateListener);
        Intrinsics.checkNotNull(ofInt);
        int height = currentSize.getHeight();
        int height2 = nextSize.getHeight();
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener2 = new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.f$$ExternalSyntheticLambda2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                C0455f.a(layoutParams, valueAnimator);
            }
        };
        ValueAnimator ofInt2 = ValueAnimator.ofInt(height, height2);
        ofInt2.addUpdateListener(animatorUpdateListener2);
        Intrinsics.checkNotNull(ofInt2);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.a, "x", position.x);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.a, "y", position.y);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofInt, ofInt2, ofFloat, ofFloat2);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.setDuration(500L);
        animatorSet.addListener(new C0454e(ofInt, ofInt2, onEndAction));
        animatorSet.start();
    }

    public static final void a(ViewGroup.MarginLayoutParams layoutParams, C0455f this$0, ValueAnimator animation) {
        Intrinsics.checkNotNullParameter(layoutParams, "$layoutParams");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(animation, "animation");
        Object animatedValue = animation.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        layoutParams.width = ((Integer) animatedValue).intValue();
        this$0.a.requestLayout();
    }

    public static final void a(ViewGroup.MarginLayoutParams layoutParams, ValueAnimator animation) {
        Intrinsics.checkNotNullParameter(layoutParams, "$layoutParams");
        Intrinsics.checkNotNullParameter(animation, "animation");
        Object animatedValue = animation.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        layoutParams.height = ((Integer) animatedValue).intValue();
    }
}
