package com.scandit.datacapture.barcode.pick.capture;

import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickScanningSession;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function0 {
    public final /* synthetic */ BarcodePickScanningSession a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(BarcodePickScanningSession barcodePickScanningSession) {
        super(0);
        this.a = barcodePickScanningSession;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Function0 function0;
        function0 = this.a.a;
        return new BarcodePickScanningSessionProxyAdapter((NativeBarcodePickScanningSession) function0.invoke(), null, 2, null);
    }
}
