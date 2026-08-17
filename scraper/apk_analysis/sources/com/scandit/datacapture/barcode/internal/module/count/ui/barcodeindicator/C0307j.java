package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountBrushHandler;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.j, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final /* synthetic */ class C0307j extends FunctionReferenceImpl implements Function2 {
    public C0307j(Object obj) {
        super(2, obj, BarcodeCountBrushHandler.class, "brushForBarcode", "brushForBarcode(Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;Lcom/scandit/datacapture/barcode/internal/module/count/ui/barcodeindicator/TrackedBarcodeStatus;)Lcom/scandit/datacapture/core/ui/style/Brush;", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        TrackedBarcode p0 = (TrackedBarcode) obj;
        W p1 = (W) obj2;
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return ((BarcodeCountBrushHandler) this.receiver).a(p0, p1);
    }
}
