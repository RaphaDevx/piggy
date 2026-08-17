package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.popover;

import com.scandit.datacapture.barcode.ar.ui.BarcodeArViewDefaults;
import com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationAnchor;
import com.scandit.datacapture.barcode.internal.module.ui.popover.C0491m;
import com.scandit.datacapture.barcode.internal.module.ui.popover.InterfaceC0492n;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e {
    public final InterfaceC0492n a;
    public Quadrilateral b;
    public BarcodeArPopoverAnnotationAnchor c;
    public Size2 d;
    public final com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.b e;

    public e(C0491m popover) {
        Intrinsics.checkNotNullParameter(popover, "popover");
        this.a = popover;
        this.c = BarcodeArViewDefaults.getDefaultPopoverAnnotationAnchor();
        b referenceQuadGetter = new b(this);
        c anchorGetter = new c(this);
        d sizeGetter = new d(this);
        Intrinsics.checkNotNullParameter(com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.c.a, "<this>");
        Intrinsics.checkNotNullParameter(referenceQuadGetter, "referenceQuadGetter");
        Intrinsics.checkNotNullParameter(anchorGetter, "anchorGetter");
        Intrinsics.checkNotNullParameter(sizeGetter, "sizeGetter");
        this.e = new com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.b(referenceQuadGetter, anchorGetter, sizeGetter);
    }
}
