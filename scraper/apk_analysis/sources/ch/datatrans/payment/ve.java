package ch.datatrans.payment;

import android.animation.ValueAnimator;

/* loaded from: classes3.dex */
public final class ve extends ValueAnimator {
    public static final /* synthetic */ int b = 0;
    public boolean a;

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final void cancel() {
        this.a = true;
        super.cancel();
    }
}
