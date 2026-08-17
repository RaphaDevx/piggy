package com.scandit.datacapture.barcode.pick.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* loaded from: classes2.dex */
public final /* synthetic */ class b extends FunctionReferenceImpl implements Function0 {
    public b(Object obj) {
        super(0, obj, BarcodePickView.class, "onShutterButtonClick", "onShutterButtonClick()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ((BarcodePickView) this.receiver).a();
        return Unit.INSTANCE;
    }
}
