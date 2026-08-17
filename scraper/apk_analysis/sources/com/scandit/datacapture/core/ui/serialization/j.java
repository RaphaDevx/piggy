package com.scandit.datacapture.core.ui.serialization;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class j extends Lambda implements Function0 {
    public final /* synthetic */ DataCaptureViewDeserializerHelper a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(DataCaptureViewDeserializerHelper dataCaptureViewDeserializerHelper) {
        super(0);
        this.a = dataCaptureViewDeserializerHelper;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new DataCaptureViewDeserializerHelperReversedAdapter(this.a, null, 2, null);
    }
}
