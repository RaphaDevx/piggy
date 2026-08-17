package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class t extends Lambda implements Function1 {
    public final /* synthetic */ u a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(u uVar) {
        super(1);
        this.a = uVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ((Number) obj).floatValue();
        this.a.a();
        this.a.a.setListener(null);
        return Unit.INSTANCE;
    }
}
