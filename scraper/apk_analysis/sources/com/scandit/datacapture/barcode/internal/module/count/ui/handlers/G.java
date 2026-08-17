package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewUiListener;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class G extends Lambda implements Function0 {
    public final /* synthetic */ J a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public G(J j) {
        super(0);
        this.a = j;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeCountView barcodeCountView;
        BarcodeCountViewUiListener uiListener;
        t tVar = this.a.s;
        if (tVar != null && (barcodeCountView = (BarcodeCountView) ((com.scandit.datacapture.barcode.count.ui.view.i) tVar).a.get()) != null && (uiListener = barcodeCountView.getUiListener()) != null) {
            uiListener.onSingleScanButtonTapped(barcodeCountView);
        }
        return Unit.INSTANCE;
    }
}
