package com.scandit.datacapture.barcode.pick.ui;

import com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.ShutterButtonWrapper;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class t extends Lambda implements Function1 {
    public final /* synthetic */ BarcodePickView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(BarcodePickView barcodePickView) {
        super(1);
        this.a = barcodePickView;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ShutterButtonWrapper shutterButtonWrapper;
        ShutterButtonWrapper shutterButtonWrapper2;
        BarcodePickView it = (BarcodePickView) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        shutterButtonWrapper = this.a.f;
        shutterButtonWrapper.b();
        shutterButtonWrapper2 = this.a.f;
        shutterButtonWrapper2.d();
        return Unit.INSTANCE;
    }
}
