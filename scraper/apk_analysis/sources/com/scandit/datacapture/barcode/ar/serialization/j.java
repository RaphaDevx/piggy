package com.scandit.datacapture.barcode.ar.serialization;

import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class j extends Lambda implements Function0 {
    public final /* synthetic */ NativeJsonValue a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(NativeJsonValue nativeJsonValue) {
        super(0);
        this.a = nativeJsonValue;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return CoreNativeTypeFactory.INSTANCE.convert(this.a);
    }
}
