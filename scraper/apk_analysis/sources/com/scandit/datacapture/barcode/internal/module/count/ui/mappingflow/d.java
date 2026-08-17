package com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d extends AnimatorListenerAdapter {
    public final /* synthetic */ Function0 a;

    public d(com.scandit.datacapture.barcode.internal.module.count.ui.mode.c cVar) {
        this.a = cVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.a.invoke();
    }
}
