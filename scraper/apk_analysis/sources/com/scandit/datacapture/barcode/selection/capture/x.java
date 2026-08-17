package com.scandit.datacapture.barcode.selection.capture;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class x extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeSelection a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(BarcodeSelection barcodeSelection) {
        super(0);
        this.a = barcodeSelection;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
