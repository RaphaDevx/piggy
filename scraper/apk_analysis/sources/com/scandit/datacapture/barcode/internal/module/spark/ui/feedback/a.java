package com.scandit.datacapture.barcode.internal.module.spark.ui.feedback;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends AnimatorListenerAdapter {
    public final /* synthetic */ b a;

    public a(b bVar) {
        this.a = bVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        animation.removeAllListeners();
        this.a.setVisibility(8);
        this.a.a = null;
    }
}
