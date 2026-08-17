package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.view.animation.AnimationUtils;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/scandit/datacapture/core/internal/sdk/ui/cardscarousel/AnimatedGradientTextView$updater$1", "Ljava/lang/Runnable;", "run", "", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AnimatedGradientTextView$updater$1 implements Runnable {
    final /* synthetic */ AnimatedGradientTextView a;

    AnimatedGradientTextView$updater$1(AnimatedGradientTextView animatedGradientTextView) {
        this.a = animatedGradientTextView;
    }

    @Override // java.lang.Runnable
    public void run() {
        long j;
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        j = this.a.e;
        long j2 = currentAnimationTimeMillis - j;
        this.a.c = (j2 % AnimatedGradientTextView.ANIMATION_DURATION) / 2500.0f;
        this.a.postDelayed(this, 16L);
        this.a.invalidate();
    }
}
