package com.scandit.datacapture.barcode.pick.capture;

import com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function0 {
    public final /* synthetic */ BarcodePickInternal a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(BarcodePickInternal barcodePickInternal) {
        super(0);
        this.a = barcodePickInternal;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodePickInternal barcodePickInternal = this.a;
        barcodePickInternal.getClass();
        return new BarcodePickSession(new com.scandit.datacapture.barcode.internal.module.pick.capture.g(barcodePickInternal));
    }
}
