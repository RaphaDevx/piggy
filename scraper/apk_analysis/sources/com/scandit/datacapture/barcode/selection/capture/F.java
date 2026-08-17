package com.scandit.datacapture.barcode.selection.capture;

import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class F extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeSelectionSession a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F(BarcodeSelectionSession barcodeSelectionSession) {
        super(0);
        this.a = barcodeSelectionSession;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Function0 function0;
        function0 = this.a.a;
        return new BarcodeSelectionSessionProxyAdapter((NativeBarcodeSelectionSession) function0.invoke(), null, 2, null);
    }
}
