package io.sentry.logger;

import io.sentry.SentryLogEvent;

/* loaded from: classes2.dex */
public interface ILoggerBatchProcessor {
    void add(SentryLogEvent sentryLogEvent);

    void close(boolean z);

    void flush(long j);
}
