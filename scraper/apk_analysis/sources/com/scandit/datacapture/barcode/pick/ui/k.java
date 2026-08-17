package com.scandit.datacapture.barcode.pick.ui;

import com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class k extends Lambda implements Function1 {
    public final /* synthetic */ BarcodePickView a;
    public final /* synthetic */ float b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(BarcodePickView barcodePickView, float f) {
        super(1);
        this.a = barcodePickView;
        this.b = f;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        BarcodePickDataCaptureViewWrapper barcodePickDataCaptureViewWrapper;
        BarcodePickView it = (BarcodePickView) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        barcodePickDataCaptureViewWrapper = this.a.e;
        barcodePickDataCaptureViewWrapper.a(this.b);
        this.a.requestLayout();
        return Unit.INSTANCE;
    }
}
