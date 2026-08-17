package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class V0 extends Lambda implements Function0 {
    public final /* synthetic */ Z0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public V0(Z0 z0) {
        super(0);
        this.a = z0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.c.invoke();
        return Unit.INSTANCE;
    }
}
