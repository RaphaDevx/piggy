package com.scandit.datacapture.barcode.internal.module.find.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class l0 extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;
    public final /* synthetic */ String b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l0(u0 u0Var, String str) {
        super(1);
        this.a = u0Var;
        this.b = str;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.barcode.internal.module.ui.n it = (com.scandit.datacapture.barcode.internal.module.ui.n) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.e.a(com.scandit.datacapture.barcode.internal.module.ui.l.d, this.b);
        return Unit.INSTANCE;
    }
}
