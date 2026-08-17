package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class I0 extends Lambda implements Function0 {
    public final /* synthetic */ J0 a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ Function0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I0(J0 j0, boolean z, Function0 function0) {
        super(0);
        this.a = j0;
        this.b = z;
        this.c = function0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        J0 j0 = this.a;
        boolean z = this.b;
        Function0 onFinished = this.c;
        j0.getClass();
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        j0.f.a(new E0(j0, z, onFinished));
        return Unit.INSTANCE;
    }
}
