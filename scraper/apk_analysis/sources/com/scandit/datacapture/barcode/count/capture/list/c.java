package com.scandit.datacapture.barcode.count.capture.list;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeCountCaptureList a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(BarcodeCountCaptureList barcodeCountCaptureList) {
        super(0);
        this.a = barcodeCountCaptureList;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a.getA();
    }
}
