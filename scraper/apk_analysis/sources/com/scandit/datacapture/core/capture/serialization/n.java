package com.scandit.datacapture.core.capture.serialization;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class n extends Lambda implements Function0 {
    public final /* synthetic */ DataCaptureContextDeserializerHelper a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(DataCaptureContextDeserializerHelper dataCaptureContextDeserializerHelper) {
        super(0);
        this.a = dataCaptureContextDeserializerHelper;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new DataCaptureContextDeserializerHelperReversedAdapter(this.a, null, 2, null);
    }
}
