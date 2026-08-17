package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class I extends Lambda implements Function1 {
    public final /* synthetic */ N a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(N n) {
        super(1);
        this.a = n;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        this.a.a(((Number) obj).intValue());
        return Unit.INSTANCE;
    }
}
