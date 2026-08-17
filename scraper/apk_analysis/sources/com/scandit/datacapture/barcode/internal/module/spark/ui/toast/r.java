package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class r extends Lambda implements Function0 {
    public final /* synthetic */ s a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(s sVar) {
        super(0);
        this.a = sVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        s sVar = this.a;
        sVar.d = null;
        sVar.a.h();
        return Unit.INSTANCE;
    }
}
