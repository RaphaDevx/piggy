package com.scandit.datacapture.barcode.batch.capture;

import com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSession;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class G extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeBatchSession a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public G(BarcodeBatchSession barcodeBatchSession) {
        super(0);
        this.a = barcodeBatchSession;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Function0 function0;
        function0 = this.a.a;
        return new BarcodeBatchSessionProxyAdapter((NativeBarcodeTrackingSession) function0.invoke(), null, 2, null);
    }
}
