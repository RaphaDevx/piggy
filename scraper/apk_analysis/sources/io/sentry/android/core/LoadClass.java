package io.sentry.android.core;

import io.sentry.ILogger;
import io.sentry.SentryOptions;

@Deprecated
/* loaded from: classes2.dex */
public final class LoadClass extends io.sentry.util.LoadClass {
    private final io.sentry.util.LoadClass delegate = new io.sentry.util.LoadClass();

    @Override // io.sentry.util.LoadClass
    public Class<?> loadClass(String str, ILogger iLogger) {
        return this.delegate.loadClass(str, iLogger);
    }

    @Override // io.sentry.util.LoadClass
    public boolean isClassAvailable(String str, ILogger iLogger) {
        return this.delegate.isClassAvailable(str, iLogger);
    }

    @Override // io.sentry.util.LoadClass
    public boolean isClassAvailable(String str, SentryOptions sentryOptions) {
        return this.delegate.isClassAvailable(str, sentryOptions);
    }
}
