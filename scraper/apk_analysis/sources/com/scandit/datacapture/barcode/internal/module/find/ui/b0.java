package com.scandit.datacapture.barcode.internal.module.find.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b0 extends Lambda implements Function1 {
    public final /* synthetic */ BarcodeFindViewPresenter a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(BarcodeFindViewPresenter barcodeFindViewPresenter) {
        super(1);
        this.a = barcodeFindViewPresenter;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        this.a.a(((Number) obj).floatValue());
        return Unit.INSTANCE;
    }
}
