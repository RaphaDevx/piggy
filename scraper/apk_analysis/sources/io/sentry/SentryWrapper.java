package io.sentry;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/* loaded from: classes2.dex */
public final class SentryWrapper {
    public static <U> Callable<U> wrapCallable(final Callable<U> callable) {
        final IScopes forkedScopes = Sentry.getCurrentScopes().forkedScopes("SentryWrapper.wrapCallable");
        return new Callable() { // from class: io.sentry.SentryWrapper$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SentryWrapper.lambda$wrapCallable$0(IScopes.this, callable);
            }
        };
    }

    static /* synthetic */ Object lambda$wrapCallable$0(IScopes iScopes, Callable callable) throws Exception {
        ISentryLifecycleToken makeCurrent = iScopes.makeCurrent();
        try {
            Object call = callable.call();
            if (makeCurrent != null) {
                makeCurrent.close();
            }
            return call;
        } catch (Throwable th) {
            if (makeCurrent != null) {
                try {
                    makeCurrent.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static <U> Supplier<U> wrapSupplier(final Supplier<U> supplier) {
        final IScopes forkedScopes = Sentry.forkedScopes("SentryWrapper.wrapSupplier");
        return new Supplier() { // from class: io.sentry.SentryWrapper$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return SentryWrapper.lambda$wrapSupplier$1(IScopes.this, supplier);
            }
        };
    }

    static /* synthetic */ Object lambda$wrapSupplier$1(IScopes iScopes, Supplier supplier) {
        ISentryLifecycleToken makeCurrent = iScopes.makeCurrent();
        try {
            Object obj = supplier.get();
            if (makeCurrent != null) {
                makeCurrent.close();
            }
            return obj;
        } catch (Throwable th) {
            if (makeCurrent != null) {
                try {
                    makeCurrent.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static Runnable wrapRunnable(final Runnable runnable) {
        final IScopes forkedScopes = Sentry.forkedScopes("SentryWrapper.wrapRunnable");
        return new Runnable() { // from class: io.sentry.SentryWrapper$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                SentryWrapper.lambda$wrapRunnable$2(IScopes.this, runnable);
            }
        };
    }

    static /* synthetic */ void lambda$wrapRunnable$2(IScopes iScopes, Runnable runnable) {
        ISentryLifecycleToken makeCurrent = iScopes.makeCurrent();
        try {
            runnable.run();
            if (makeCurrent != null) {
                makeCurrent.close();
            }
        } catch (Throwable th) {
            if (makeCurrent != null) {
                try {
                    makeCurrent.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
