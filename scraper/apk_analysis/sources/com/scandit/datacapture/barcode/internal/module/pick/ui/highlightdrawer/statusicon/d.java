package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function0 {
    public final /* synthetic */ e a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(e eVar) {
        super(0);
        this.a = eVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.f = false;
        return Unit.INSTANCE;
    }
}
