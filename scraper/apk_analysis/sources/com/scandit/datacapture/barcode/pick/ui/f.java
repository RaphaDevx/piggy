package com.scandit.datacapture.barcode.pick.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function1 {
    public final /* synthetic */ BarcodePickView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(BarcodePickView barcodePickView) {
        super(1);
        this.a = barcodePickView;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        BarcodePickView it = (BarcodePickView) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        BarcodePickView.access$freezeInternal(this.a);
        return Unit.INSTANCE;
    }
}
