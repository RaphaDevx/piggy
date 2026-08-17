package com.scandit.datacapture.barcode.pick.serialization;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class p extends Lambda implements Function0 {
    public final /* synthetic */ BarcodePickDeserializer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(BarcodePickDeserializer barcodePickDeserializer) {
        super(0);
        this.a = barcodePickDeserializer;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
