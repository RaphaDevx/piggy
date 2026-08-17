package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import com.scandit.datacapture.barcode.count.ui.BarcodeCountViewInternalUiListener;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class H extends Lambda implements Function0 {
    public final /* synthetic */ J a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(J j) {
        super(0);
        this.a = j;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        J j = this.a;
        j.C = !j.C;
        BarcodeCountViewInternalUiListener barcodeCountViewInternalUiListener = j.t;
        if (barcodeCountViewInternalUiListener != null) {
            barcodeCountViewInternalUiListener.onStatusButtonTapped();
        }
        J j2 = this.a;
        t tVar = j2.s;
        if (tVar != null) {
            boolean z = j2.C;
            BarcodeCountView barcodeCountView = (BarcodeCountView) ((com.scandit.datacapture.barcode.count.ui.view.i) tVar).a.get();
            if (barcodeCountView != null) {
                barcodeCountView.a(z);
            }
        }
        J j3 = this.a;
        j3.p.setStatusModeEnabled$scandit_barcode_capture(j3.C);
        return Unit.INSTANCE;
    }
}
