package com.scandit.datacapture.barcode.internal.module.spark.data;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class J extends Lambda implements Function0 {
    public final /* synthetic */ O a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public J(O o) {
        super(0);
        this.a = o;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        InterfaceC0429h interfaceC0429h = this.a.h;
        if (interfaceC0429h != null) {
            interfaceC0429h.onCameraOnEnd();
        }
        return Unit.INSTANCE;
    }
}
