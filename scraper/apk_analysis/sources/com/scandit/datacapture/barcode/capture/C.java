package com.scandit.datacapture.barcode.capture;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class C extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeCaptureDeserializer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C(BarcodeCaptureDeserializer barcodeCaptureDeserializer) {
        super(0);
        this.a = barcodeCaptureDeserializer;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
