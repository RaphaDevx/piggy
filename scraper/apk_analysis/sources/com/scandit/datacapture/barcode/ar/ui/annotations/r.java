package com.scandit.datacapture.barcode.ar.ui.annotations;

import com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationListener;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class r extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeArPopoverAnnotation a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(BarcodeArPopoverAnnotation barcodeArPopoverAnnotation) {
        super(0);
        this.a = barcodeArPopoverAnnotation;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeArPopoverAnnotationListener barcodeArPopoverAnnotationListener = this.a.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String();
        if (barcodeArPopoverAnnotationListener != null) {
            barcodeArPopoverAnnotationListener.onPopoverTapped(this.a);
        }
        Function0<Unit> onAnnotationOrElementTap$scandit_barcode_capture = this.a.getOnAnnotationOrElementTap$scandit_barcode_capture();
        if (onAnnotationOrElementTap$scandit_barcode_capture != null) {
            onAnnotationOrElementTap$scandit_barcode_capture.invoke();
        }
        return Unit.INSTANCE;
    }
}
