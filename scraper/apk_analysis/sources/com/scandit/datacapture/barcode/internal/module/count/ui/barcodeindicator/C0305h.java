package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.h, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0305h extends Lambda implements Function0 {
    public final /* synthetic */ C0316t a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0305h(C0316t c0316t) {
        super(0);
        this.a = c0316t;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        com.scandit.datacapture.barcode.internal.module.count.ui.barcodeoverlay.a aVar = new com.scandit.datacapture.barcode.internal.module.count.ui.barcodeoverlay.a(this.a.a.e());
        C0316t c0316t = this.a;
        aVar.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        c0316t.a.a().addView(aVar, 0);
        return aVar;
    }
}
