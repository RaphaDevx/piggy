package com.scandit.datacapture.barcode.batch.capture;

import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.batch.capture.h, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0268h extends Lambda implements Function0 {
    public final /* synthetic */ NativeJsonValue a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0268h(NativeJsonValue nativeJsonValue) {
        super(0);
        this.a = nativeJsonValue;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return CoreNativeTypeFactory.INSTANCE.convert(this.a);
    }
}
