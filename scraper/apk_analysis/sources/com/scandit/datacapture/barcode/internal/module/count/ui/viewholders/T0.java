package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class T0 extends Lambda implements Function1 {
    public final /* synthetic */ Z0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public T0(Z0 z0) {
        super(1);
        this.a = z0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context it = (Context) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        Z0 z0 = this.a;
        z0.getClass();
        com.scandit.datacapture.barcode.internal.module.count.ui.p pVar = new com.scandit.datacapture.barcode.internal.module.count.ui.p(it, new V0(z0));
        pVar.setLayoutParams(z0.c());
        pVar.setVisibility(4);
        pVar.setEnabled(false);
        pVar.setId(AbstractC0341b.k);
        z0.d = pVar;
        return pVar;
    }
}
