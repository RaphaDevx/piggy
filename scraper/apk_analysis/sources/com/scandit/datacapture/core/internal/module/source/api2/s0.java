package com.scandit.datacapture.core.internal.module.source.api2;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class s0 extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;
    public final /* synthetic */ boolean b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(u0 u0Var, boolean z) {
        super(1);
        this.a = u0Var;
        this.b = z;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.core.internal.module.gl.l use = (com.scandit.datacapture.core.internal.module.gl.l) obj;
        Intrinsics.checkNotNullParameter(use, "$this$use");
        use.b = this.a.c;
        use.x = this.b;
        return Unit.INSTANCE;
    }
}
