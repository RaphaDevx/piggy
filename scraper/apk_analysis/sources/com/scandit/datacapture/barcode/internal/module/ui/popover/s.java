package com.scandit.datacapture.barcode.internal.module.ui.popover;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final /* synthetic */ class s extends FunctionReferenceImpl implements Function2 {
    public s(Object obj) {
        super(2, obj, K.class, "changeToVerticalAbove", "changeToVerticalAbove(Lcom/scandit/datacapture/barcode/internal/module/ui/popover/PopoverIndicatorData;Lcom/scandit/datacapture/barcode/internal/module/ui/popover/PopoverSizes;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        O p0 = (O) obj;
        P p1 = (P) obj2;
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        K.c((K) this.receiver, p0, p1);
        return Unit.INSTANCE;
    }
}
