package io.sentry;

/* loaded from: classes2.dex */
public final class NoOpScopesLifecycleToken implements ISentryLifecycleToken {
    private static final NoOpScopesLifecycleToken instance = new NoOpScopesLifecycleToken();

    @Override // io.sentry.ISentryLifecycleToken, java.lang.AutoCloseable
    public void close() {
    }

    private NoOpScopesLifecycleToken() {
    }

    public static NoOpScopesLifecycleToken getInstance() {
        return instance;
    }
}
