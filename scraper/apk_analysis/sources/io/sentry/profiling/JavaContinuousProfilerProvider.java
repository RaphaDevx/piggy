package io.sentry.profiling;

import io.sentry.IContinuousProfiler;
import io.sentry.ILogger;
import io.sentry.ISentryExecutorService;

/* loaded from: classes2.dex */
public interface JavaContinuousProfilerProvider {
    IContinuousProfiler getContinuousProfiler(ILogger iLogger, String str, int i, ISentryExecutorService iSentryExecutorService);
}
