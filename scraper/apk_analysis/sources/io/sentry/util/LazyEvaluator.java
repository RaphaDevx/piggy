package io.sentry.util;

import io.sentry.ISentryLifecycleToken;

/* loaded from: classes3.dex */
public final class LazyEvaluator<T> {
    private final Evaluator<T> evaluator;
    private volatile T value = null;
    private final AutoClosableReentrantLock lock = new AutoClosableReentrantLock();

    public interface Evaluator<T> {
        T evaluate();
    }

    public LazyEvaluator(Evaluator<T> evaluator) {
        this.evaluator = evaluator;
    }

    public T getValue() {
        if (this.value == null) {
            ISentryLifecycleToken acquire = this.lock.acquire();
            try {
                if (this.value == null) {
                    this.value = this.evaluator.evaluate();
                }
                if (acquire != null) {
                    acquire.close();
                }
            } catch (Throwable th) {
                if (acquire != null) {
                    try {
                        acquire.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        return this.value;
    }

    public void setValue(T t) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            this.value = t;
            if (acquire != null) {
                acquire.close();
            }
        } catch (Throwable th) {
            if (acquire != null) {
                try {
                    acquire.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void resetValue() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            this.value = null;
            if (acquire != null) {
                acquire.close();
            }
        } catch (Throwable th) {
            if (acquire != null) {
                try {
                    acquire.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
