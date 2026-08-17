package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class I extends Lambda implements Function0 {
    public final /* synthetic */ J a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(J j) {
        super(0);
        this.a = j;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.p.shutterButtonPressed();
        this.a.A = false;
        return Unit.INSTANCE;
    }
}
