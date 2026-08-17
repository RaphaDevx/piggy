package com.scandit.datacapture.core.capture;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function0 {
    public final /* synthetic */ DataCaptureContext a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(DataCaptureContext dataCaptureContext) {
        super(0);
        this.a = dataCaptureContext;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.getA().onActivityStopped();
        return Unit.INSTANCE;
    }
}
