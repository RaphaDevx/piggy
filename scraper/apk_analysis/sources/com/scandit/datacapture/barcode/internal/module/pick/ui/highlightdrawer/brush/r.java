package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class r extends Lambda implements Function1 {
    public final /* synthetic */ Function1 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(Function1 function1) {
        super(1);
        this.a = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        a drawData = (a) obj;
        Intrinsics.checkNotNullParameter(drawData, "drawData");
        this.a.invoke(drawData.f);
        return Unit.INSTANCE;
    }
}
