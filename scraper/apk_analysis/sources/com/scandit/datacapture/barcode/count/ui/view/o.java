package com.scandit.datacapture.barcode.count.ui.view;

import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter;
import com.scandit.datacapture.barcode.internal.module.count.ui.mode.BarcodeCountInternalModePresenter;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class o extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeCountView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(BarcodeCountView barcodeCountView) {
        super(0);
        this.a = barcodeCountView;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeCountUiPresenter barcodeCountUiPresenter;
        BarcodeCountInternalModePresenter barcodeCountInternalModePresenter;
        barcodeCountUiPresenter = this.a.e;
        barcodeCountUiPresenter.a();
        barcodeCountInternalModePresenter = this.a.h;
        barcodeCountInternalModePresenter.a();
        return Unit.INSTANCE;
    }
}
