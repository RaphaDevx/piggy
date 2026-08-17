package com.scandit.datacapture.barcode.internal.module.spark.ui.feedback;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b extends View {
    public Animator a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setVisibility(8);
        setBackground(new ColorDrawable(0));
    }

    public final void a(int i) {
        Animator animator = this.a;
        if (animator != null) {
            animator.cancel();
        }
        setBackground(new ColorDrawable(i));
        setAlpha(0.0f);
        setVisibility(0);
        ObjectAnimator duration = ObjectAnimator.ofFloat(this, "alpha", 1.0f).setDuration(100L);
        Intrinsics.checkNotNullExpressionValue(duration, "setDuration(...)");
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(this, "alpha", 0.0f).setDuration(0L);
        Intrinsics.checkNotNullExpressionValue(duration2, "setDuration(...)");
        ObjectAnimator duration3 = ObjectAnimator.ofFloat(this, "alpha", 0.5f).setDuration(100L);
        Intrinsics.checkNotNullExpressionValue(duration3, "setDuration(...)");
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(duration, duration2, duration3);
        animatorSet.addListener(new a(this));
        animatorSet.start();
        this.a = animatorSet;
    }
}
