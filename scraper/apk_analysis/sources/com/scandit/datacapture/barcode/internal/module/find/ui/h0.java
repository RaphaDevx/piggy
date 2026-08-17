package com.scandit.datacapture.barcode.internal.module.find.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class h0 extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;
    public final /* synthetic */ boolean b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h0(u0 u0Var, boolean z) {
        super(1);
        this.a = u0Var;
        this.b = z;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.barcode.internal.module.ui.n it = (com.scandit.datacapture.barcode.internal.module.ui.n) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.e.setVisibility(this.b ? 0 : 8);
        return Unit.INSTANCE;
    }
}
