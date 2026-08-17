package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0454e extends AnimatorListenerAdapter {
    public final /* synthetic */ ValueAnimator a;
    public final /* synthetic */ ValueAnimator b;
    public final /* synthetic */ Function0 c;

    public C0454e(ValueAnimator valueAnimator, ValueAnimator valueAnimator2, E e) {
        this.a = valueAnimator;
        this.b = valueAnimator2;
        this.c = e;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.a.removeAllUpdateListeners();
        this.b.removeAllUpdateListeners();
        animation.removeAllListeners();
        this.c.invoke();
    }
}
