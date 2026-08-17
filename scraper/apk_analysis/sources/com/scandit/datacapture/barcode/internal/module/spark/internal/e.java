package com.scandit.datacapture.barcode.internal.module.spark.internal;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e {
    public final w a;
    public final x b;

    public e(b subscribable, x listener) {
        Intrinsics.checkNotNullParameter(subscribable, "subscribable");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a = subscribable;
        this.b = listener;
    }
}
