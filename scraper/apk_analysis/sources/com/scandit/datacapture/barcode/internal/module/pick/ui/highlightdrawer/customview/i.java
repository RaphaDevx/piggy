package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class i extends Lambda implements Function1 {
    public final /* synthetic */ Function1 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.g gVar) {
        super(1);
        this.a = gVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        a drawData = (a) obj;
        Intrinsics.checkNotNullParameter(drawData, "drawData");
        this.a.invoke(drawData.e);
        return Unit.INSTANCE;
    }
}
