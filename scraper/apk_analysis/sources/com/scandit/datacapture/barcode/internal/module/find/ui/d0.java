package com.scandit.datacapture.barcode.internal.module.find.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d0 extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d0(u0 u0Var) {
        super(1);
        this.a = u0Var;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.barcode.internal.module.ui.b it = (com.scandit.datacapture.barcode.internal.module.ui.b) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.k.b();
        return Unit.INSTANCE;
    }
}
