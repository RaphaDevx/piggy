package com.scandit.datacapture.barcode.count.ui.view;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSession;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodeCountSession a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(NativeBarcodeCountSession nativeBarcodeCountSession) {
        super(0);
        this.a = nativeBarcodeCountSession;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
