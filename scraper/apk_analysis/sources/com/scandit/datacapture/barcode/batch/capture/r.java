package com.scandit.datacapture.barcode.batch.capture;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class r extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeBatchDeserializer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(BarcodeBatchDeserializer barcodeBatchDeserializer) {
        super(0);
        this.a = barcodeBatchDeserializer;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
