package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function1 {
    public final /* synthetic */ Function1 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(o oVar) {
        super(1);
        this.a = oVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        BarcodeArAnnotation barcodeArAnnotation = (BarcodeArAnnotation) obj;
        this.a.invoke(barcodeArAnnotation != null ? new BarcodeArAugmentation$Annotation(barcodeArAnnotation, barcodeArAnnotation.createView()) : null);
        return Unit.INSTANCE;
    }
}
