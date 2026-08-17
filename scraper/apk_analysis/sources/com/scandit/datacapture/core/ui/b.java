package com.scandit.datacapture.core.ui;

import com.scandit.datacapture.core.internal.sdk.common.NativeError;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public static final b a = new b();

    public b() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        NativeError it = (NativeError) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        String message = it.getMessage();
        Intrinsics.checkNotNullExpressionValue(message, "getMessage(...)");
        return message;
    }
}
