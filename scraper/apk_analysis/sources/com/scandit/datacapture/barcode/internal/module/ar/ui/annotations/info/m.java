package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class m extends Lambda implements Function0 {
    public final /* synthetic */ n a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(n nVar) {
        super(0);
        this.a = nVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.a().getIconTapped().invoke(Boolean.FALSE);
        return Unit.INSTANCE;
    }
}
