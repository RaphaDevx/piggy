package com.scandit.datacapture.barcode.count.capture;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeCount a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(BarcodeCount barcodeCount) {
        super(0);
        this.a = barcodeCount;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a.getA();
    }
}
