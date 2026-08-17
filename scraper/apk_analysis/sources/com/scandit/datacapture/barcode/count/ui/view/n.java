package com.scandit.datacapture.barcode.count.ui.view;

import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class n extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeCountView a;
    public final /* synthetic */ int b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(BarcodeCountView barcodeCountView, int i) {
        super(0);
        this.a = barcodeCountView;
        this.b = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        boolean z;
        BarcodeCountUiPresenter barcodeCountUiPresenter;
        z = this.a.o;
        if (z) {
            barcodeCountUiPresenter = this.a.e;
            barcodeCountUiPresenter.a(0, this.b);
        }
        return Unit.INSTANCE;
    }
}
