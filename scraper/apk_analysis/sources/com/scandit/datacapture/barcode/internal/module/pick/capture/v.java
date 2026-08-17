package com.scandit.datacapture.barcode.internal.module.pick.capture;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class v {
    public final Function0 a;
    public final Lazy b;

    public v(Function0 getImpl) {
        Intrinsics.checkNotNullParameter(getImpl, "getImpl");
        this.a = getImpl;
        this.b = LazyKt.lazy(new u(this));
    }
}
