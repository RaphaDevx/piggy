package com.scandit.datacapture.barcode.batch.capture;

import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.batch.capture.g, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0267g extends Lambda implements Function0 {
    public final /* synthetic */ NativeDataCaptureContext a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0267g(NativeDataCaptureContext nativeDataCaptureContext) {
        super(0);
        this.a = nativeDataCaptureContext;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return CoreNativeTypeFactory.INSTANCE.convert(this.a);
    }
}
