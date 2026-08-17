package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class A0 extends Lambda implements Function1 {
    public final /* synthetic */ J0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A0(J0 j0) {
        super(1);
        this.a = j0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context it = (Context) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        J0 j0 = this.a;
        int i = J0.m;
        return j0.b(it);
    }
}
