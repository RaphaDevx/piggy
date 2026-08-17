package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class l extends Lambda implements Function1 {
    public final /* synthetic */ Function1 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(Function1 function1) {
        super(1);
        this.a = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        this.a.invoke(Float.valueOf(((Number) obj).floatValue()));
        return Unit.INSTANCE;
    }
}
