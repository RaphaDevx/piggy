package com.scandit.datacapture.barcode.count.ui.overlay;

import com.scandit.datacapture.barcode.filter.ui.overlay.NativeBarcodeFilterOverlaySettings;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class B extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodeFilterOverlaySettings a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(NativeBarcodeFilterOverlaySettings nativeBarcodeFilterOverlaySettings) {
        super(0);
        this.a = nativeBarcodeFilterOverlaySettings;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        NativeBarcodeFilterOverlaySettings _0 = this.a;
        Intrinsics.checkNotNullExpressionValue(_0, "$_0");
        return barcodeNativeTypeFactory.convert(_0);
    }
}
