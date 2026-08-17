package com.scandit.datacapture.core.json;

import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class g extends Lambda implements Function0 {
    public final /* synthetic */ NativeJsonValue a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(NativeJsonValue nativeJsonValue) {
        super(0);
        this.a = nativeJsonValue;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        CoreNativeTypeFactory coreNativeTypeFactory = CoreNativeTypeFactory.INSTANCE;
        NativeJsonValue _0 = this.a;
        Intrinsics.checkNotNullExpressionValue(_0, "$_0");
        return coreNativeTypeFactory.convert(_0);
    }
}
