package com.scandit.datacapture.barcode.count.capture;

import com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountSession;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function0 {
    public final /* synthetic */ InternalBarcodeCountSession a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(InternalBarcodeCountSession internalBarcodeCountSession) {
        super(0);
        this.a = internalBarcodeCountSession;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a.n();
    }
}
