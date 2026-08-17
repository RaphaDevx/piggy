package com.scandit.datacapture.barcode.internal.module.count.capture;

import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function0 {
    public final /* synthetic */ NativeFrameData a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(NativeFrameData nativeFrameData) {
        super(0);
        this.a = nativeFrameData;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return CoreNativeTypeFactory.INSTANCE.convert(this.a);
    }
}
