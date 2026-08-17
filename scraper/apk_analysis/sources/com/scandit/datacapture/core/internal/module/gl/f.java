package com.scandit.datacapture.core.internal.module.gl;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function1 {
    public static final f a = new f();

    public f() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        l it = (l) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        it.g.sendEmptyMessage(2);
        return Unit.INSTANCE;
    }
}
