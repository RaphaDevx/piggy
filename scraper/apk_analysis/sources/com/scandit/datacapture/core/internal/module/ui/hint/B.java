package com.scandit.datacapture.core.internal.module.ui.hint;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class B extends Animatable2.AnimationCallback {
    public final /* synthetic */ ImageView a;
    public final /* synthetic */ AnimatedVectorDrawable b;

    public B(ImageView imageView, AnimatedVectorDrawable animatedVectorDrawable) {
        this.a = imageView;
        this.b = animatedVectorDrawable;
    }

    public static final void a(AnimatedVectorDrawable animation) {
        Intrinsics.checkNotNullParameter(animation, "$animation");
        animation.start();
    }

    @Override // android.graphics.drawable.Animatable2.AnimationCallback
    public final void onAnimationEnd(Drawable drawable) {
        ImageView imageView = this.a;
        final AnimatedVectorDrawable animatedVectorDrawable = this.b;
        imageView.post(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.ui.hint.B$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                B.a(animatedVectorDrawable);
            }
        });
    }
}
