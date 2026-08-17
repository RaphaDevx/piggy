package com.scandit.datacapture.barcode.internal.module.find.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class N extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public N(u0 u0Var) {
        super(1);
        this.a = u0Var;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.barcode.internal.module.ui.n it = (com.scandit.datacapture.barcode.internal.module.ui.n) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        com.scandit.datacapture.barcode.internal.module.ui.n nVar = this.a.e;
        com.scandit.datacapture.barcode.internal.module.ui.l lVar = com.scandit.datacapture.barcode.internal.module.ui.l.a;
        nVar.a();
        return Unit.INSTANCE;
    }
}
