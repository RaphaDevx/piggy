package com.scandit.datacapture.core.internal.module.https.scandit.client;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements f {
    public final AtomicInteger a;

    public a() {
        AtomicInteger generator = (AtomicInteger) h.a.getValue();
        Intrinsics.checkNotNullParameter(generator, "generator");
        this.a = generator;
    }
}
