package com.scandit.datacapture.core.internal.module.ui.hint;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.scandit.datacapture.core.R;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class p {
    public static final int a = R.id.sc_animator_tag;

    public static final void a(HintView hintView, boolean z, Function0 then) {
        Intrinsics.checkNotNullParameter(hintView, "<this>");
        Intrinsics.checkNotNullParameter(then, "then");
        if (!z) {
            int i = a;
            Object tag = hintView.getTag(i);
            Animator animator = tag instanceof Animator ? (Animator) tag : null;
            if (animator != null) {
                animator.cancel();
            }
            hintView.setTag(i, null);
            hintView.setVisibility(4);
            hintView.setAlpha(0.0f);
            then.invoke();
            return;
        }
        ValueAnimator a2 = a(hintView, 0.0f);
        m mVar = new m(hintView, then);
        int i2 = a;
        Object tag2 = hintView.getTag(i2);
        Animator animator2 = tag2 instanceof Animator ? (Animator) tag2 : null;
        if (animator2 != null) {
            animator2.cancel();
        }
        a2.addListener(new o(hintView, mVar));
        a2.start();
        hintView.setTag(i2, a2);
    }

    public static final void b(HintView hintView, boolean z, Function0 then) {
        Intrinsics.checkNotNullParameter(hintView, "<this>");
        Intrinsics.checkNotNullParameter(then, "then");
        if (!z) {
            int i = a;
            Object tag = hintView.getTag(i);
            Animator animator = tag instanceof Animator ? (Animator) tag : null;
            if (animator != null) {
                animator.cancel();
            }
            hintView.setTag(i, null);
            hintView.setVisibility(0);
            hintView.setAlpha(1.0f);
            then.invoke();
            return;
        }
        if (hintView.getVisibility() != 0) {
            hintView.setVisibility(0);
            hintView.setAlpha(0.0f);
        }
        ValueAnimator a2 = a(hintView, 1.0f);
        l lVar = new l(then);
        int i2 = a;
        Object tag2 = hintView.getTag(i2);
        Animator animator2 = tag2 instanceof Animator ? (Animator) tag2 : null;
        if (animator2 != null) {
            animator2.cancel();
        }
        a2.addListener(new o(hintView, lVar));
        a2.start();
        hintView.setTag(i2, a2);
    }

    public static final ValueAnimator a(final View view, float f) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(view.getAlpha(), f);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.setDuration(250L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.core.internal.module.ui.hint.p$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                p.a(view, valueAnimator);
            }
        });
        Intrinsics.checkNotNullExpressionValue(ofFloat, "apply(...)");
        return ofFloat;
    }

    public static final void a(View this_fadeAnimator, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this_fadeAnimator, "$this_fadeAnimator");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        this_fadeAnimator.setAlpha(((Float) animatedValue).floatValue());
    }

    public static final void a(View view, Animator animator) {
        animator.removeAllListeners();
        view.setTag(a, null);
    }
}
