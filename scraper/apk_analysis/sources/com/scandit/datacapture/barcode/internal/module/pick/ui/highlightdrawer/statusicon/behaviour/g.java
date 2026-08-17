package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class g extends Lambda implements Function0 {
    public static final g a = new g();

    public g() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf((((Number) h.b.getValue()).intValue() * 2) + ((Number) h.d.getValue()).intValue());
    }
}
