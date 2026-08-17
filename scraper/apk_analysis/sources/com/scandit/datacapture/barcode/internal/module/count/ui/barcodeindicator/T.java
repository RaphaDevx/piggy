package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class T extends Lambda implements Function1 {
    public static final T a = new T();

    public T() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        String utf8String = ((NativeBarcode) obj).getUtf8String();
        Intrinsics.checkNotNullExpressionValue(utf8String, "getUtf8String(...)");
        return utf8String;
    }
}
