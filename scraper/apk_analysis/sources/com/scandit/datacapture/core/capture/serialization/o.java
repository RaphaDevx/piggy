package com.scandit.datacapture.core.capture.serialization;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class o extends Lambda implements Function0 {
    public final /* synthetic */ DataCaptureContextDeserializerListener a;
    public final /* synthetic */ DataCaptureContextDeserializerProxyAdapter b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(DataCaptureContextDeserializerListener dataCaptureContextDeserializerListener, DataCaptureContextDeserializerProxyAdapter dataCaptureContextDeserializerProxyAdapter) {
        super(0);
        this.a = dataCaptureContextDeserializerListener;
        this.b = dataCaptureContextDeserializerProxyAdapter;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new DataCaptureContextDeserializerListenerReversedAdapter(this.a, this.b._deserializer(), null, 4, null);
    }
}
