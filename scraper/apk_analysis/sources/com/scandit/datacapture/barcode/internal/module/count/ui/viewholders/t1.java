package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.widget.FrameLayout;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class t1 extends Lambda implements Function1 {
    public final /* synthetic */ y1 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t1(y1 y1Var) {
        super(1);
        this.a = y1Var;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context it = (Context) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        y1 y1Var = this.a;
        y1Var.getClass();
        com.scandit.datacapture.barcode.internal.module.ui.n nVar = new com.scandit.datacapture.barcode.internal.module.ui.n(it);
        nVar.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
        y1Var.b = nVar;
        return nVar;
    }
}
