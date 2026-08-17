package io.sentry.logger;

import io.sentry.SentryDate;
import io.sentry.SentryLogLevel;

/* loaded from: classes2.dex */
public final class NoOpLoggerApi implements ILoggerApi {
    private static final NoOpLoggerApi instance = new NoOpLoggerApi();

    @Override // io.sentry.logger.ILoggerApi
    public void debug(String str, Object... objArr) {
    }

    @Override // io.sentry.logger.ILoggerApi
    public void error(String str, Object... objArr) {
    }

    @Override // io.sentry.logger.ILoggerApi
    public void fatal(String str, Object... objArr) {
    }

    @Override // io.sentry.logger.ILoggerApi
    public void info(String str, Object... objArr) {
    }

    @Override // io.sentry.logger.ILoggerApi
    public void log(SentryLogLevel sentryLogLevel, SentryDate sentryDate, String str, Object... objArr) {
    }

    @Override // io.sentry.logger.ILoggerApi
    public void log(SentryLogLevel sentryLogLevel, SentryLogParameters sentryLogParameters, String str, Object... objArr) {
    }

    @Override // io.sentry.logger.ILoggerApi
    public void log(SentryLogLevel sentryLogLevel, String str, Object... objArr) {
    }

    @Override // io.sentry.logger.ILoggerApi
    public void trace(String str, Object... objArr) {
    }

    @Override // io.sentry.logger.ILoggerApi
    public void warn(String str, Object... objArr) {
    }

    private NoOpLoggerApi() {
    }

    public static NoOpLoggerApi getInstance() {
        return instance;
    }
}
