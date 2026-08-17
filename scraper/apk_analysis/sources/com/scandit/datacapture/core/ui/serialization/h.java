package com.scandit.datacapture.core.ui.serialization;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class h extends Lambda implements Function0 {
    public final /* synthetic */ DataCaptureViewDeserializer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(DataCaptureViewDeserializer dataCaptureViewDeserializer) {
        super(0);
        this.a = dataCaptureViewDeserializer;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
