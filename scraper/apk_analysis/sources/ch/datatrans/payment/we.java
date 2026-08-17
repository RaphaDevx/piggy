package ch.datatrans.payment;

import android.animation.Animator;
import kotlin.jvm.functions.Function0;

/* loaded from: classes3.dex */
public final class we implements Animator.AnimatorListener {
    public final /* synthetic */ ve a;
    public final /* synthetic */ Function0 b;

    public we(ve veVar, Function0 function0) {
        this.a = veVar;
        this.b = function0;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        Function0 function0;
        if (this.a.a || (function0 = this.b) == null) {
            return;
        }
        function0.invoke();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
    }
}
