package com.scandit.datacapture.barcode.internal.module.find.serialization;

import com.scandit.datacapture.barcode.find.serialization.BarcodeFindDeserializer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class j extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeFindDeserializer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(BarcodeFindDeserializer barcodeFindDeserializer) {
        super(0);
        this.a = barcodeFindDeserializer;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
