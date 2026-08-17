package com.scandit.datacapture.barcode.count.capture.map;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridElement;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodeSpatialGridElement a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(NativeBarcodeSpatialGridElement nativeBarcodeSpatialGridElement) {
        super(0);
        this.a = nativeBarcodeSpatialGridElement;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        NativeBarcodeSpatialGridElement _0 = this.a;
        Intrinsics.checkNotNullExpressionValue(_0, "$_0");
        return barcodeNativeTypeFactory.convert(_0);
    }
}
