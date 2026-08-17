package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class E extends Lambda implements Function0 {
    public final /* synthetic */ F a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E(F f) {
        super(0);
        this.a = f;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.k.f();
        return Unit.INSTANCE;
    }
}
