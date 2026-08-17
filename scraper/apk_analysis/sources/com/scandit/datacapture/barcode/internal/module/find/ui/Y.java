package com.scandit.datacapture.barcode.internal.module.find.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class Y extends Lambda implements Function1 {
    public static final Y a = new Y();

    public Y() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.f it = (com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.f) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        it.getForeground().setLevel(1);
        return Unit.INSTANCE;
    }
}
