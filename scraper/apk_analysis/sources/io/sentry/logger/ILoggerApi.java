package io.sentry.logger;

import io.sentry.SentryDate;
import io.sentry.SentryLogLevel;

/* loaded from: classes2.dex */
public interface ILoggerApi {
    void debug(String str, Object... objArr);

    void error(String str, Object... objArr);

    void fatal(String str, Object... objArr);

    void info(String str, Object... objArr);

    void log(SentryLogLevel sentryLogLevel, SentryDate sentryDate, String str, Object... objArr);

    void log(SentryLogLevel sentryLogLevel, SentryLogParameters sentryLogParameters, String str, Object... objArr);

    void log(SentryLogLevel sentryLogLevel, String str, Object... objArr);

    void trace(String str, Object... objArr);

    void warn(String str, Object... objArr);
}
