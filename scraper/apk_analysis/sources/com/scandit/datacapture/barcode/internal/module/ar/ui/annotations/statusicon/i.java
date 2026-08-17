package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.statusicon;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class i extends Lambda implements Function0 {
    public static final i a = new i();

    public i() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Float.valueOf(((((Number) l.c.getValue()).intValue() * 2) + ((Number) l.b.getValue()).intValue()) / 2.0f);
    }
}
