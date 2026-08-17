package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class r extends Lambda implements Function0 {
    public final /* synthetic */ a a;
    public final /* synthetic */ v b;
    public final /* synthetic */ BarcodeArAugmentation$Highlight c;
    public final /* synthetic */ a d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(a aVar, v vVar, BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight, a aVar2) {
        super(0);
        this.a = aVar;
        this.b = vVar;
        this.c = barcodeArAugmentation$Highlight;
        this.d = aVar2;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight = this.a.d;
        if (barcodeArAugmentation$Highlight != null) {
            this.b.d.invoke(barcodeArAugmentation$Highlight);
        }
        BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight2 = this.c;
        if (barcodeArAugmentation$Highlight2 != null) {
            v vVar = this.b;
            a aVar = this.d;
            Quadrilateral b = aVar.b();
            vVar.b.invoke(aVar.c, barcodeArAugmentation$Highlight2);
            vVar.c.invoke(b, barcodeArAugmentation$Highlight2);
        }
        return Unit.INSTANCE;
    }
}
