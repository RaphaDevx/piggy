package com.scandit.datacapture.barcode.internal.module.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b extends AnimatorListenerAdapter {
    public final /* synthetic */ PulsingView a;
    public final /* synthetic */ Function0 b;

    public b(PulsingView pulsingView, a aVar) {
        this.a = pulsingView;
        this.b = aVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        AnimatorSet animatorSet = (AnimatorSet) animation;
        this.a.d = null;
        ArrayList<Animator> childAnimations = animatorSet.getChildAnimations();
        Intrinsics.checkNotNullExpressionValue(childAnimations, "getChildAnimations(...)");
        for (Animator animator : childAnimations) {
            Intrinsics.checkNotNull(animator, "null cannot be cast to non-null type android.animation.ValueAnimator");
            ((ValueAnimator) animator).removeAllUpdateListeners();
        }
        animatorSet.removeAllListeners();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        AnimatorSet animatorSet = (AnimatorSet) animation;
        this.a.d = null;
        ArrayList<Animator> childAnimations = animatorSet.getChildAnimations();
        Intrinsics.checkNotNullExpressionValue(childAnimations, "getChildAnimations(...)");
        for (Animator animator : childAnimations) {
            Intrinsics.checkNotNull(animator, "null cannot be cast to non-null type android.animation.ValueAnimator");
            ((ValueAnimator) animator).removeAllUpdateListeners();
        }
        animatorSet.removeAllListeners();
        this.b.invoke();
    }
}
