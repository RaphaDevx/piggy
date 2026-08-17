package com.scandit.datacapture.barcode.count.capture;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterEditor;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class i extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodeClusterEditor a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(NativeBarcodeClusterEditor nativeBarcodeClusterEditor) {
        super(0);
        this.a = nativeBarcodeClusterEditor;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        NativeBarcodeClusterEditor _0 = this.a;
        Intrinsics.checkNotNullExpressionValue(_0, "$_0");
        return barcodeNativeTypeFactory.convert(_0);
    }
}
