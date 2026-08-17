package com.scandit.datacapture.barcode.ar.ui.annotations;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class s extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeArPopoverAnnotation a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(BarcodeArPopoverAnnotation barcodeArPopoverAnnotation) {
        super(0);
        this.a = barcodeArPopoverAnnotation;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.h = true;
        return Unit.INSTANCE;
    }
}
