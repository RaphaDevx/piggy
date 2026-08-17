package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class B extends Lambda implements Function1 {
    public final /* synthetic */ F a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(F f) {
        super(1);
        this.a = f;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context it = (Context) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        return this.a.a(it);
    }
}
