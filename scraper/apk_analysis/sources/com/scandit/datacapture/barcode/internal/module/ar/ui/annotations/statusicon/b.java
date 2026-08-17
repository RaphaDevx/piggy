package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.statusicon;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function0 {
    public static final b a = new b();

    public b() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf((((Number) l.c.getValue()).intValue() * 2) + ((Number) l.b.getValue()).intValue());
    }
}
