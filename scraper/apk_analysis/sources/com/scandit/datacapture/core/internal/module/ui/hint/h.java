package com.scandit.datacapture.core.internal.module.ui.hint;

import com.scandit.datacapture.core.ui.animation.DynamicAnimation;
import com.scandit.datacapture.core.ui.animation.SpringAnimation;
import kotlin.jvm.functions.Function0;

/* loaded from: classes2.dex */
public final class h implements DynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ SpringAnimation a;
    public final /* synthetic */ Function0 b;

    public h(SpringAnimation springAnimation, C0611e c0611e) {
        this.a = springAnimation;
        this.b = c0611e;
    }

    @Override // com.scandit.datacapture.core.ui.animation.DynamicAnimation.OnAnimationEndListener
    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        this.a.removeEndListener(this);
        this.b.invoke();
    }
}
