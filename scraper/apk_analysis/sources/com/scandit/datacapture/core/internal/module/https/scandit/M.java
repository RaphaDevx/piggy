package com.scandit.datacapture.core.internal.module.https.scandit;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class M implements J {
    public final Lazy a;
    public final Lazy b;

    public M(n0 trustFactory) {
        Intrinsics.checkNotNullParameter(trustFactory, "trustFactory");
        this.a = LazyKt.lazy(new L(trustFactory));
        this.b = LazyKt.lazy(new K(this));
    }
}
