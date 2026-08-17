package com.scandit.datacapture.barcode.ar.ui;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.BarcodeArAugmentation$Highlight;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final /* synthetic */ class b extends FunctionReferenceImpl implements Function2 {
    public b(Object obj) {
        super(2, obj, BarcodeArView.class, "onHighlightTap", "onHighlightTap(Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;Lcom/scandit/datacapture/barcode/internal/module/ar/ui/overlay/BarcodeArAugmentation$Highlight;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        TrackedBarcode p0 = (TrackedBarcode) obj;
        BarcodeArAugmentation$Highlight p1 = (BarcodeArAugmentation$Highlight) obj2;
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        BarcodeArView.access$onHighlightTap((BarcodeArView) this.receiver, p0, p1);
        return Unit.INSTANCE;
    }
}
