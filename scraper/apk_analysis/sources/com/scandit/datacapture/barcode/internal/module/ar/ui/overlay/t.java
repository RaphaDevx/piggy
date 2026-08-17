package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class t extends Lambda implements Function0 {
    public final /* synthetic */ v a;
    public final /* synthetic */ k b;
    public final /* synthetic */ a c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(v vVar, k kVar, a aVar) {
        super(0);
        this.a = vVar;
        this.b = kVar;
        this.c = aVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation;
        v vVar = this.a;
        k kVar = this.b;
        a aVar = this.c;
        vVar.getClass();
        BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight = aVar.d;
        if (barcodeArAugmentation$Highlight != null) {
            Quadrilateral b = aVar.b();
            vVar.b.invoke(aVar.c, barcodeArAugmentation$Highlight);
            vVar.c.invoke(b, barcodeArAugmentation$Highlight);
        } else {
            barcodeArAugmentation$Highlight = null;
        }
        if (kVar.b && (barcodeArAugmentation$Annotation = aVar.e) != null) {
            vVar.a(aVar.a(), aVar.c, barcodeArAugmentation$Annotation, barcodeArAugmentation$Highlight);
        }
        return Unit.INSTANCE;
    }
}
