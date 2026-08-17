package com.scandit.datacapture.barcode.pick.capture;

import com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class g extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodeFilterSettings a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(NativeBarcodeFilterSettings nativeBarcodeFilterSettings) {
        super(0);
        this.a = nativeBarcodeFilterSettings;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        NativeBarcodeFilterSettings _0 = this.a;
        Intrinsics.checkNotNullExpressionValue(_0, "$_0");
        return barcodeNativeTypeFactory.convert(_0);
    }
}
