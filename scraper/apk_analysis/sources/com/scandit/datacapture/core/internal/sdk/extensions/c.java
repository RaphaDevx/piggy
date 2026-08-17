package com.scandit.datacapture.core.internal.sdk.extensions;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function1 {
    public static final c a = new c();

    public c() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return Integer.valueOf(RangesKt.coerceIn(MathKt.roundToInt(((Number) obj).floatValue() * 255), 0, 255));
    }
}
