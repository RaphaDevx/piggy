package com.scandit.datacapture.barcode.capture;

import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.capture.k, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0279k extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodeCapture a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0279k(NativeBarcodeCapture nativeBarcodeCapture) {
        super(0);
        this.a = nativeBarcodeCapture;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return BarcodeNativeTypeFactory.INSTANCE.convert(this.a);
    }
}
