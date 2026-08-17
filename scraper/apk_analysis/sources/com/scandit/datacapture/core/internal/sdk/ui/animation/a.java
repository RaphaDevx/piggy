package com.scandit.datacapture.core.internal.sdk.ui.animation;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function1 {
    public final /* synthetic */ AnimationSpecInterpolator a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(AnimationSpecInterpolator animationSpecInterpolator) {
        super(1);
        this.a = animationSpecInterpolator;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return this.a.getInterpolation(((Number) obj).floatValue());
    }
}
