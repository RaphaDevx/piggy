package com.scandit.datacapture.barcode.data;

import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeAbstractStructuredAppendData;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function0 {
    public final /* synthetic */ NativeAbstractStructuredAppendData a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(NativeAbstractStructuredAppendData nativeAbstractStructuredAppendData) {
        super(0);
        this.a = nativeAbstractStructuredAppendData;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        NativeAbstractStructuredAppendData _0 = this.a;
        Intrinsics.checkNotNullExpressionValue(_0, "$_0");
        return barcodeNativeTypeFactory.convert(_0);
    }
}
