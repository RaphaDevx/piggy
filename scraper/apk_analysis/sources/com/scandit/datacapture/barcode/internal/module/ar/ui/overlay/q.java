package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotationTrigger;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class q extends Lambda implements Function0 {
    public final /* synthetic */ a a;
    public final /* synthetic */ BarcodeArAugmentation$Annotation b;
    public final /* synthetic */ v c;
    public final /* synthetic */ a d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(a aVar, BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation, v vVar, a aVar2) {
        super(0);
        this.a = aVar;
        this.b = barcodeArAugmentation$Annotation;
        this.c = vVar;
        this.d = aVar2;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        a aVar = this.a;
        BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation = aVar.e;
        if (barcodeArAugmentation$Annotation != null) {
            this.c.a(barcodeArAugmentation$Annotation, aVar.d);
        }
        BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation2 = this.b;
        if (barcodeArAugmentation$Annotation2 != null) {
            v vVar = this.c;
            a aVar2 = this.d;
            BarcodeArAnnotationTrigger annotationTrigger = barcodeArAugmentation$Annotation2.a().getAnnotationTrigger();
            Intrinsics.checkNotNullParameter(annotationTrigger, "<this>");
            int i = com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.c.a[annotationTrigger.ordinal()];
            if (i != 1) {
                if (i != 2 && i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                vVar.a(aVar2.a(), aVar2.c, barcodeArAugmentation$Annotation2, aVar2.d);
            }
        }
        return Unit.INSTANCE;
    }
}
