package com.scandit.datacapture.barcode.internal.module.spark.ui;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class A extends Lambda implements Function0 {
    public static final A a = new A();

    public A() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Float.valueOf((((Number) E.n.getValue()).intValue() - ((Number) E.p.getValue()).intValue()) / 2.0f);
    }
}
