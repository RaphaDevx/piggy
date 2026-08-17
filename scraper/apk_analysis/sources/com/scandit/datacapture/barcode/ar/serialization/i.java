package com.scandit.datacapture.barcode.ar.serialization;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class i extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeArDeserializer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(BarcodeArDeserializer barcodeArDeserializer) {
        super(0);
        this.a = barcodeArDeserializer;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
