package com.scandit.datacapture.barcode.selection.capture;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class v extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeSelectionDeserializer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(BarcodeSelectionDeserializer barcodeSelectionDeserializer) {
        super(0);
        this.a = barcodeSelectionDeserializer;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
