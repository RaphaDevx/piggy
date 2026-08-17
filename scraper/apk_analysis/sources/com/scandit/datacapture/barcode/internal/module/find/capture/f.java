package com.scandit.datacapture.barcode.internal.module.find.capture;

import com.scandit.datacapture.barcode.find.capture.BarcodeFind;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeFind a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(BarcodeFind barcodeFind) {
        super(0);
        this.a = barcodeFind;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
