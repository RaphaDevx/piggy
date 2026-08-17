package com.scandit.datacapture.barcode.internal.module.spark.data;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class z extends Lambda implements Function0 {
    public final /* synthetic */ O a;
    public final /* synthetic */ Function0 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(O o, Function0 function0) {
        super(0);
        this.a = o;
        this.b = function0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        O.a(this.a, this.b);
        return Unit.INSTANCE;
    }
}
