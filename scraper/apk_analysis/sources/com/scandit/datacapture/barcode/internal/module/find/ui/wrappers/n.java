package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import android.content.Context;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class n extends Lambda implements Function1 {
    public final /* synthetic */ com.scandit.datacapture.barcode.internal.module.ui.e a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(com.scandit.datacapture.barcode.internal.module.ui.e eVar) {
        super(1);
        this.a = eVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context it = (Context) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        return this.a;
    }
}
