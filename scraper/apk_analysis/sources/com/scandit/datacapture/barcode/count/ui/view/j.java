package com.scandit.datacapture.barcode.count.ui.view;

import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class j extends Lambda implements Function1 {
    public final /* synthetic */ BarcodeCountView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(BarcodeCountView barcodeCountView) {
        super(1);
        this.a = barcodeCountView;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        BarcodeIndicatorPresenter barcodeIndicatorPresenter;
        BarcodeCountView it = (BarcodeCountView) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        barcodeIndicatorPresenter = this.a.g;
        barcodeIndicatorPresenter.reset();
        return Unit.INSTANCE;
    }
}
