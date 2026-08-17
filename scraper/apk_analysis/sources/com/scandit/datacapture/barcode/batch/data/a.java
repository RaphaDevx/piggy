package com.scandit.datacapture.barcode.batch.data;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function0 {
    public final /* synthetic */ NativeTrackedBarcode a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(NativeTrackedBarcode nativeTrackedBarcode) {
        super(0);
        this.a = nativeTrackedBarcode;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        NativeBarcode barcode = this.a.getBarcode();
        Intrinsics.checkNotNullExpressionValue(barcode, "getBarcode(...)");
        return new Barcode(barcode);
    }
}
