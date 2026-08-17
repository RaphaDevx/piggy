package com.scandit.datacapture.core.capture.serialization;

import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContextSettings;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function0 {
    public final /* synthetic */ NativeDataCaptureContextSettings a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(NativeDataCaptureContextSettings nativeDataCaptureContextSettings) {
        super(0);
        this.a = nativeDataCaptureContextSettings;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return CoreNativeTypeFactory.INSTANCE.convert(this.a);
    }
}
