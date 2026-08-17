package com.scandit.datacapture.core.internal.module.ui.hint;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class y extends Lambda implements Function0 {
    public final /* synthetic */ z a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(z zVar) {
        super(0);
        this.a = zVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.b();
        return Unit.INSTANCE;
    }
}
