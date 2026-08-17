package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class s extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeArAugmentation$Annotation a;
    public final /* synthetic */ v b;
    public final /* synthetic */ BarcodeArAugmentation$Highlight c;
    public final /* synthetic */ a d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation, v vVar, BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight, a aVar) {
        super(0);
        this.a = barcodeArAugmentation$Annotation;
        this.b = vVar;
        this.c = barcodeArAugmentation$Highlight;
        this.d = aVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        if (this.a.b().getParent() != null) {
            this.b.a(this.a, this.c);
        } else if (this.b.b().isEmpty()) {
            v vVar = this.b;
            Quadrilateral a = this.d.a();
            a aVar = this.d;
            vVar.a(a, aVar.c, this.a, aVar.d);
        } else {
            ArrayList b = this.b.b();
            v vVar2 = this.b;
            Iterator it = b.iterator();
            while (it.hasNext()) {
                a aVar2 = (a) it.next();
                BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation = aVar2.e;
                if (barcodeArAugmentation$Annotation != null) {
                    vVar2.a(barcodeArAugmentation$Annotation, aVar2.d);
                }
            }
        }
        return Unit.INSTANCE;
    }
}
