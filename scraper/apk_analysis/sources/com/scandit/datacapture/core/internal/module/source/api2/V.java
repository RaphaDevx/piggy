package com.scandit.datacapture.core.internal.module.source.api2;

import com.scandit.datacapture.frameworks.core.events.EventForResult;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

/* loaded from: classes2.dex */
public final class V {
    public static final /* synthetic */ V a = new V();
    public static final long b;
    public static final long c;
    public static final long d;
    public static final long e;

    static {
        Duration.Companion companion = Duration.INSTANCE;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        b = DurationKt.toDuration(1000L, durationUnit);
        c = DurationKt.toDuration(EventForResult.DEFAULT_TIMEOUT_MILLIS, durationUnit);
        d = DurationKt.toDuration(500L, durationUnit);
        e = DurationKt.toDuration(250L, durationUnit);
    }
}
