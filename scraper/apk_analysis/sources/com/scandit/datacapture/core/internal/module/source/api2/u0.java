package com.scandit.datacapture.core.internal.module.source.api2;

import android.graphics.SurfaceTexture;
import com.scandit.datacapture.core.internal.sdk.data.Subscription;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class u0 implements p0 {
    public final Subscription a;
    public final SurfaceTexture b;
    public final int c;
    public final Lazy d;

    public u0(Subscription subscription, SurfaceTexture surfaceTexture, int i) {
        Intrinsics.checkNotNullParameter(subscription, "subscription");
        Intrinsics.checkNotNullParameter(surfaceTexture, "surfaceTexture");
        this.a = subscription;
        this.b = surfaceTexture;
        this.c = i;
        this.d = LazyKt.lazy(new t0(this));
    }
}
