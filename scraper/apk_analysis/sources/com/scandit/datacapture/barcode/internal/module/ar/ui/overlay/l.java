package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class l extends Lambda implements Function0 {
    public final /* synthetic */ v a;
    public final /* synthetic */ BarcodeArAugmentation$Annotation b;
    public final /* synthetic */ BarcodeArAugmentation$Highlight c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(v vVar, BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation, BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight) {
        super(0);
        this.a = vVar;
        this.b = barcodeArAugmentation$Annotation;
        this.c = barcodeArAugmentation$Highlight;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.a(this.b, this.c);
        return Unit.INSTANCE;
    }
}
