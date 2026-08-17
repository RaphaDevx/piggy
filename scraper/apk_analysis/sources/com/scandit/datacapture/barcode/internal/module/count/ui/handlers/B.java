package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class B extends Lambda implements Function0 {
    public final /* synthetic */ J a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(J j) {
        super(0);
        this.a = j;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        t tVar;
        BarcodeCountView barcodeCountView;
        this.a.p.exitButtonPressed();
        if (this.a.y.m() && (tVar = this.a.s) != null && (barcodeCountView = (BarcodeCountView) ((com.scandit.datacapture.barcode.count.ui.view.i) tVar).a.get()) != null) {
            barcodeCountView.disableScanningAndResetBarcodeIndicators$scandit_barcode_capture();
        }
        return Unit.INSTANCE;
    }
}
