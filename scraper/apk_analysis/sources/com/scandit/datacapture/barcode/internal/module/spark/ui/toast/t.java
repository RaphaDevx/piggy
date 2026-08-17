package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class t extends AnimatorListenerAdapter {
    public final /* synthetic */ Function0 a;

    public t(Function0 function0) {
        this.a = function0;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        animation.removeAllListeners();
        this.a.invoke();
    }
}
