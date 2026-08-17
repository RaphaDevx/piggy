package com.scandit.datacapture.core.internal.module.utils;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public static final b a = new b();

    public b() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        ((Number) obj).longValue();
        return Unit.INSTANCE;
    }
}
