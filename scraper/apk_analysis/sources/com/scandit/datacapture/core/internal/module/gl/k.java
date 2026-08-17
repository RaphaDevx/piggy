package com.scandit.datacapture.core.internal.module.gl;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class k {
    public final Function1 a;
    public final int b;
    public final int c;

    public k(Function1 surfaceCallback, int i, int i2) {
        Intrinsics.checkNotNullParameter(surfaceCallback, "surfaceCallback");
        this.a = surfaceCallback;
        this.b = i;
        this.c = i2;
    }
}
