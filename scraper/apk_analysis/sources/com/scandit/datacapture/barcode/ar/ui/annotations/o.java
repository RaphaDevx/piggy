package com.scandit.datacapture.barcode.ar.ui.annotations;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class o extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeArInfoAnnotation a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(BarcodeArInfoAnnotation barcodeArInfoAnnotation) {
        super(0);
        this.a = barcodeArInfoAnnotation;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Function0<Unit> onAnnotationOrElementTap$scandit_barcode_capture = this.a.getOnAnnotationOrElementTap$scandit_barcode_capture();
        if (onAnnotationOrElementTap$scandit_barcode_capture != null) {
            onAnnotationOrElementTap$scandit_barcode_capture.invoke();
        }
        return Unit.INSTANCE;
    }
}
