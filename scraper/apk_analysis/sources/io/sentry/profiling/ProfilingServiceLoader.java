package io.sentry.profiling;

import io.sentry.IContinuousProfiler;
import io.sentry.ILogger;
import io.sentry.IProfileConverter;
import io.sentry.ISentryExecutorService;
import io.sentry.NoOpContinuousProfiler;
import io.sentry.ScopesAdapter;
import io.sentry.SentryLevel;
import java.util.Iterator;
import java.util.ServiceLoader;

/* loaded from: classes2.dex */
public final class ProfilingServiceLoader {
    public static IContinuousProfiler loadContinuousProfiler(ILogger iLogger, String str, int i, ISentryExecutorService iSentryExecutorService) {
        try {
            JavaContinuousProfilerProvider javaContinuousProfilerProvider = (JavaContinuousProfilerProvider) loadSingleProvider(JavaContinuousProfilerProvider.class);
            if (javaContinuousProfilerProvider != null) {
                iLogger.log(SentryLevel.DEBUG, "Loaded continuous profiler from provider: %s", javaContinuousProfilerProvider.getClass().getName());
                return javaContinuousProfilerProvider.getContinuousProfiler(iLogger, str, i, iSentryExecutorService);
            }
            iLogger.log(SentryLevel.DEBUG, "No continuous profiler provider found, using NoOpContinuousProfiler", new Object[0]);
            return NoOpContinuousProfiler.getInstance();
        } catch (Throwable th) {
            iLogger.log(SentryLevel.ERROR, "Failed to load continuous profiler provider, using NoOpContinuousProfiler", th);
            return NoOpContinuousProfiler.getInstance();
        }
    }

    public static IProfileConverter loadProfileConverter() {
        ILogger logger = ScopesAdapter.getInstance().getGlobalScope().getOptions().getLogger();
        try {
            JavaProfileConverterProvider javaProfileConverterProvider = (JavaProfileConverterProvider) loadSingleProvider(JavaProfileConverterProvider.class);
            if (javaProfileConverterProvider != null) {
                logger.log(SentryLevel.DEBUG, "Loaded profile converter from provider: %s", javaProfileConverterProvider.getClass().getName());
                return javaProfileConverterProvider.getProfileConverter();
            }
            logger.log(SentryLevel.DEBUG, "No profile converter provider found, returning null", new Object[0]);
            return null;
        } catch (Throwable th) {
            logger.log(SentryLevel.ERROR, "Failed to load profile converter provider, returning null", th);
            return null;
        }
    }

    private static <T> T loadSingleProvider(Class<T> cls) {
        Iterator it = ServiceLoader.load(cls).iterator();
        if (it.hasNext()) {
            return (T) it.next();
        }
        return null;
    }
}
