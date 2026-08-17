package com.scandit.datacapture.barcode.ar.capture;

import com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSession;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeArSession a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(BarcodeArSession barcodeArSession) {
        super(0);
        this.a = barcodeArSession;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Function0 function0;
        function0 = this.a.a;
        return new BarcodeArSessionProxyAdapter((NativeBarcodeArSession) function0.invoke(), null, 2, null);
    }
}
