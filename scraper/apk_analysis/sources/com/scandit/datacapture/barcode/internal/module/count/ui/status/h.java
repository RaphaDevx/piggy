package com.scandit.datacapture.barcode.internal.module.count.ui.status;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class h extends Lambda implements Function0 {
    public final /* synthetic */ m a;
    public final /* synthetic */ boolean b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(m mVar, boolean z) {
        super(0);
        this.a = mVar;
        this.b = z;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.a(true, this.b);
        return Unit.INSTANCE;
    }
}
