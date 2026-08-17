package com.scandit.datacapture.core.ui.serialization;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class k extends Lambda implements Function0 {
    public final /* synthetic */ DataCaptureViewDeserializerListener a;
    public final /* synthetic */ DataCaptureViewDeserializerProxyAdapter b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(DataCaptureViewDeserializerListener dataCaptureViewDeserializerListener, DataCaptureViewDeserializerProxyAdapter dataCaptureViewDeserializerProxyAdapter) {
        super(0);
        this.a = dataCaptureViewDeserializerListener;
        this.b = dataCaptureViewDeserializerProxyAdapter;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new DataCaptureViewDeserializerListenerReversedAdapter(this.a, this.b._deserializer(), null, 4, null);
    }
}
