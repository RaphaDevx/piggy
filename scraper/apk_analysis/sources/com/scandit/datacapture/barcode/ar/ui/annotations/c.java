package com.scandit.datacapture.barcode.ar.ui.annotations;

import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function1 {
    public final /* synthetic */ BarcodeArInfoAnnotation a;
    public final /* synthetic */ int b;
    public final /* synthetic */ BarcodeArInfoAnnotationBodyComponent c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(BarcodeArInfoAnnotation barcodeArInfoAnnotation, int i, BarcodeArInfoAnnotationBodyComponent barcodeArInfoAnnotationBodyComponent) {
        super(1);
        this.a = barcodeArInfoAnnotation;
        this.b = i;
        this.c = barcodeArInfoAnnotationBodyComponent;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        BarcodeArInfoAnnotation.access$bodyElementIconTapped(this.a, ((Boolean) obj).booleanValue(), this.b, this.c);
        return Unit.INSTANCE;
    }
}
