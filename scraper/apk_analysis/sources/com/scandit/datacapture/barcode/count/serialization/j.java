package com.scandit.datacapture.barcode.count.serialization;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class j extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeCountDeserializer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(BarcodeCountDeserializer barcodeCountDeserializer) {
        super(0);
        this.a = barcodeCountDeserializer;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
