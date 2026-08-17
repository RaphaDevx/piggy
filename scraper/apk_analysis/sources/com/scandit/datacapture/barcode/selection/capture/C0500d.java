package com.scandit.datacapture.barcode.selection.capture;

import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.selection.capture.d, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0500d extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcode a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0500d(NativeBarcode nativeBarcode) {
        super(0);
        this.a = nativeBarcode;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return BarcodeNativeTypeFactory.INSTANCE.convert(this.a);
    }
}
