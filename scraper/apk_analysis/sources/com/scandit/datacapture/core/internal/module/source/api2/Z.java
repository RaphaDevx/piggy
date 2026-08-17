package com.scandit.datacapture.core.internal.module.source.api2;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;

/* loaded from: classes2.dex */
public final class Z implements W {
    public final N b;
    public final int c;
    public final Function0 d;
    public float e;
    public float f;
    public Y g;
    public long h;
    public long i;
    public boolean j;
    public long k;

    public Z(O cameraInfo) {
        X timeProvider = X.a;
        Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        this.b = cameraInfo;
        this.c = 4;
        this.d = timeProvider;
        this.g = Y.a;
        Duration.Companion companion = Duration.INSTANCE;
        this.h = companion.m5381getZEROUwyO8pc();
        this.i = companion.m5381getZEROUwyO8pc();
        this.k = companion.m5381getZEROUwyO8pc();
    }
}
