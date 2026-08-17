package com.scandit.datacapture.barcode.internal.module.spark.data;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class w extends Lambda implements Function1 {
    public final /* synthetic */ O a;
    public final /* synthetic */ Function1 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(O o, Function1 function1) {
        super(1);
        this.a = o;
        this.b = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        O.a(this.a, new v(this.b, obj));
        return Unit.INSTANCE;
    }
}
