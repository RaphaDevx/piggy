package com.scandit.datacapture.barcode.capture;

import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class H extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodeCapture a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(NativeBarcodeCapture nativeBarcodeCapture) {
        super(0);
        this.a = nativeBarcodeCapture;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        NativeBarcodeCapture _2 = this.a;
        Intrinsics.checkNotNullExpressionValue(_2, "$_2");
        return barcodeNativeTypeFactory.convert(_2);
    }
}
