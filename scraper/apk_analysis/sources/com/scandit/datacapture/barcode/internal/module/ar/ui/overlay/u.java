package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class u extends Lambda implements Function0 {
    public final /* synthetic */ a a;
    public final /* synthetic */ v b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(a aVar, v vVar) {
        super(0);
        this.a = aVar;
        this.b = vVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        a aVar = this.a;
        BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight = aVar.d;
        if (barcodeArAugmentation$Highlight != null) {
            this.b.c.invoke(aVar.b(), barcodeArAugmentation$Highlight);
        } else {
            barcodeArAugmentation$Highlight = null;
        }
        a aVar2 = this.a;
        BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation = aVar2.e;
        if (barcodeArAugmentation$Annotation != null) {
            this.b.f.invoke(aVar2.a(), barcodeArAugmentation$Annotation, barcodeArAugmentation$Highlight);
        }
        return Unit.INSTANCE;
    }
}
