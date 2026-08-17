package io.sentry.util.thread;

import io.sentry.protocol.SentryThread;

/* loaded from: classes3.dex */
public final class NoOpThreadChecker implements IThreadChecker {
    private static final NoOpThreadChecker instance = new NoOpThreadChecker();

    @Override // io.sentry.util.thread.IThreadChecker
    public long currentThreadSystemId() {
        return 0L;
    }

    @Override // io.sentry.util.thread.IThreadChecker
    public boolean isMainThread() {
        return false;
    }

    @Override // io.sentry.util.thread.IThreadChecker
    public boolean isMainThread(long j) {
        return false;
    }

    @Override // io.sentry.util.thread.IThreadChecker
    public boolean isMainThread(SentryThread sentryThread) {
        return false;
    }

    @Override // io.sentry.util.thread.IThreadChecker
    public boolean isMainThread(Thread thread) {
        return false;
    }

    public static NoOpThreadChecker getInstance() {
        return instance;
    }

    @Override // io.sentry.util.thread.IThreadChecker
    public String getCurrentThreadName() {
        return "";
    }
}
