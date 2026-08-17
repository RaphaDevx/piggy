package com.scandit.datacapture.barcode.count.capture;

import com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class k extends Lambda implements Function0 {
    public final /* synthetic */ NativeSymbologySettings a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(NativeSymbologySettings nativeSymbologySettings) {
        super(0);
        this.a = nativeSymbologySettings;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        NativeSymbologySettings _0 = this.a;
        Intrinsics.checkNotNullExpressionValue(_0, "$_0");
        return barcodeNativeTypeFactory.convert(_0);
    }
}
