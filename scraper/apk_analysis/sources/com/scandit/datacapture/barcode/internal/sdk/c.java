package com.scandit.datacapture.barcode.internal.sdk;

import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcode a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(NativeBarcode nativeBarcode) {
        super(0);
        this.a = nativeBarcode;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new Barcode(this.a);
    }
}
