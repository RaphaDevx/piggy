package com.scandit.datacapture.barcode.count.capture;

import com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountSession;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function2 {
    public final /* synthetic */ InternalBarcodeCountSession a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(InternalBarcodeCountSession internalBarcodeCountSession) {
        super(2);
        this.a = internalBarcodeCountSession;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Integer num = (Integer) obj;
        Integer num2 = (Integer) obj2;
        return (num == null || num2 == null) ? this.a.a(0, 0) : this.a.a(num.intValue(), num2.intValue());
    }
}
