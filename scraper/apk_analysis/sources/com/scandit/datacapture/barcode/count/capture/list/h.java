package com.scandit.datacapture.barcode.count.capture.list;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListSession;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class h extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeCountCaptureListSession a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(BarcodeCountCaptureListSession barcodeCountCaptureListSession) {
        super(0);
        this.a = barcodeCountCaptureListSession;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Function0 function0;
        function0 = this.a.a;
        return new BarcodeCountCaptureListSessionProxyAdapter((NativeBarcodeCountCaptureListSession) function0.invoke(), null, 2, null);
    }
}
