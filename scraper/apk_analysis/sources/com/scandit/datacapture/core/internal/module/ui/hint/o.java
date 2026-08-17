package com.scandit.datacapture.core.internal.module.ui.hint;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class o extends AnimatorListenerAdapter {
    public final /* synthetic */ HintView a;
    public final /* synthetic */ Function0 b;

    public o(HintView hintView, Function0 function0) {
        this.a = hintView;
        this.b = function0;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        p.a(this.a, animation);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.b.invoke();
        p.a(this.a, animation);
    }
}
