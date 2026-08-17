package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class v extends Lambda implements Function0 {
    public final /* synthetic */ w a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(w wVar) {
        super(0);
        this.a = wVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.setVisibility(0);
        return Unit.INSTANCE;
    }
}
