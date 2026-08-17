package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class H extends Lambda implements Function1 {
    public final /* synthetic */ K a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(K k) {
        super(1);
        this.a = k;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context it = (Context) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        K k = this.a;
        int i = K.e;
        k.getClass();
        com.scandit.datacapture.barcode.internal.module.ui.e eVar = new com.scandit.datacapture.barcode.internal.module.ui.e(it);
        eVar.setLayoutParams(k.c());
        eVar.setVisibility(4);
        k.c = eVar;
        return eVar;
    }
}
