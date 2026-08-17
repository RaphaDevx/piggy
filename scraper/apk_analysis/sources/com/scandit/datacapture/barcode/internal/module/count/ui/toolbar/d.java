package com.scandit.datacapture.barcode.internal.module.count.ui.toolbar;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeCountToolbarView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(BarcodeCountToolbarView barcodeCountToolbarView) {
        super(0);
        this.a = barcodeCountToolbarView;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        boolean z;
        boolean z2;
        BarcodeCountToolbarView barcodeCountToolbarView = this.a;
        z = barcodeCountToolbarView.j;
        barcodeCountToolbarView.j = !z;
        this.a.c();
        b b = this.a.b();
        if (b != null) {
            z2 = this.a.j;
            b.a(z2);
        }
        return Unit.INSTANCE;
    }
}
