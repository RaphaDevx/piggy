package com.scandit.datacapture.core.capture;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class o extends Lambda implements Function0 {
    public final /* synthetic */ DataCaptureContext a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(DataCaptureContext dataCaptureContext) {
        super(0);
        this.a = dataCaptureContext;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
