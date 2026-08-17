package com.scandit.datacapture.barcode.pick.ui;

import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class l extends Lambda implements Function1 {
    public final /* synthetic */ BarcodePickView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(BarcodePickView barcodePickView) {
        super(1);
        this.a = barcodePickView;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        float floatValue = ((Number) obj).floatValue();
        BarcodePickView barcodePickView = this.a;
        ViewExtensionsKt.runOnMainThread(barcodePickView, new k(barcodePickView, floatValue));
        return Unit.INSTANCE;
    }
}
