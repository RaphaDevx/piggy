package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class F extends Lambda implements Function1 {
    public static final F a = new F();

    public F() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        E it = (E) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        return it.e;
    }
}
