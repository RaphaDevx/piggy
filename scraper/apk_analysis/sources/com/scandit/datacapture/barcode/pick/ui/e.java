package com.scandit.datacapture.barcode.pick.ui;

import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function1 {
    public final /* synthetic */ BarcodePickView a;
    public final /* synthetic */ BarcodePickViewSettings b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(BarcodePickView barcodePickView, BarcodePickViewSettings barcodePickViewSettings) {
        super(1);
        this.a = barcodePickView;
        this.b = barcodePickViewSettings;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        BarcodePickDrawer barcodePickDrawer;
        BarcodePickView it = (BarcodePickView) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        barcodePickDrawer = this.a.d;
        barcodePickDrawer.a(this.b.getHighlightStyle());
        return Unit.INSTANCE;
    }
}
