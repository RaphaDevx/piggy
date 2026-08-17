package com.scandit.datacapture.core.capture;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function1 {
    public final /* synthetic */ DataCaptureMode a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(DataCaptureMode dataCaptureMode) {
        super(1);
        this.a = dataCaptureMode;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        DataCaptureMode existingMode = (DataCaptureMode) obj;
        Intrinsics.checkNotNullParameter(existingMode, "existingMode");
        return Boolean.valueOf(!existingMode._dataCaptureModeImpl().canCoexistWithMode(this.a._dataCaptureModeImpl()));
    }
}
