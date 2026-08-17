package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import android.content.Context;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class A extends Lambda implements Function1 {
    public final /* synthetic */ Context a;
    public final /* synthetic */ B b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A(Context context, B b) {
        super(1);
        this.a = context;
        this.b = b;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context it = (Context) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        q qVar = new q(this.a);
        qVar.g = this.b;
        return qVar;
    }
}
