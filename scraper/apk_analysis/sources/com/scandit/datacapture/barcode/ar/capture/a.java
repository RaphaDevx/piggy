package com.scandit.datacapture.barcode.ar.capture;

import com.scandit.datacapture.barcode.internal.module.ar.capture.BarcodeArInternal;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeArInternal a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(BarcodeArInternal barcodeArInternal) {
        super(0);
        this.a = barcodeArInternal;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a.b();
    }
}
