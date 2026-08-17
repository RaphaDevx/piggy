package com.scandit.datacapture.barcode.ar.ui.annotations;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class h extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeArInfoAnnotation a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(BarcodeArInfoAnnotation barcodeArInfoAnnotation) {
        super(0);
        this.a = barcodeArInfoAnnotation;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeArInfoAnnotation.access$headerOrFooterTapped(this.a, true);
        return Unit.INSTANCE;
    }
}
