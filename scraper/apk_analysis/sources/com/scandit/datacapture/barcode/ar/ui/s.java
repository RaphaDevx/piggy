package com.scandit.datacapture.barcode.ar.ui;

import com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.BarcodeArAugmentation$Annotation;
import com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.BarcodeArAugmentation$Highlight;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final /* synthetic */ class s extends FunctionReferenceImpl implements Function3 {
    public s(com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.i iVar) {
        super(3, iVar, com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.g.class, "updateAnnotation", "updateAnnotation(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Lcom/scandit/datacapture/barcode/internal/module/ar/ui/overlay/BarcodeArAugmentation$Annotation;Lcom/scandit/datacapture/barcode/internal/module/ar/ui/overlay/BarcodeArAugmentation$Highlight;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        Quadrilateral p0 = (Quadrilateral) obj;
        BarcodeArAugmentation$Annotation p1 = (BarcodeArAugmentation$Annotation) obj2;
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        ((com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.i) ((com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.g) this.receiver)).a(p0, p1, (BarcodeArAugmentation$Highlight) obj3);
        return Unit.INSTANCE;
    }
}
