package io.sentry;

import io.sentry.util.AutoClosableReentrantLock;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class SentryExecutorService implements ISentryExecutorService {
    private static final int INITIAL_QUEUE_SIZE = 40;
    private static final int MAX_QUEUE_SIZE = 271;
    private final Runnable dummyRunnable;
    private final ScheduledThreadPoolExecutor executorService;
    private final AutoClosableReentrantLock lock;
    private final SentryOptions options;

    static /* synthetic */ void lambda$new$0() {
    }

    SentryExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, SentryOptions sentryOptions) {
        this.lock = new AutoClosableReentrantLock();
        this.dummyRunnable = new Runnable() { // from class: io.sentry.SentryExecutorService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SentryExecutorService.lambda$new$0();
            }
        };
        this.executorService = scheduledThreadPoolExecutor;
        this.options = sentryOptions;
    }

    public SentryExecutorService(SentryOptions sentryOptions) {
        this(new ScheduledThreadPoolExecutor(1, new SentryExecutorServiceThreadFactory()), sentryOptions);
    }

    public SentryExecutorService() {
        this(new ScheduledThreadPoolExecutor(1, new SentryExecutorServiceThreadFactory()), null);
    }

    @Override // io.sentry.ISentryExecutorService
    public Future<?> submit(Runnable runnable) throws RejectedExecutionException {
        if (this.executorService.getQueue().size() < MAX_QUEUE_SIZE) {
            return this.executorService.submit(runnable);
        }
        SentryOptions sentryOptions = this.options;
        if (sentryOptions != null) {
            sentryOptions.getLogger().log(SentryLevel.WARNING, "Task " + runnable + " rejected from " + this.executorService, new Object[0]);
        }
        return new CancelledFuture();
    }

    @Override // io.sentry.ISentryExecutorService
    public <T> Future<T> submit(Callable<T> callable) throws RejectedExecutionException {
        if (this.executorService.getQueue().size() < MAX_QUEUE_SIZE) {
            return this.executorService.submit(callable);
        }
        SentryOptions sentryOptions = this.options;
        if (sentryOptions != null) {
            sentryOptions.getLogger().log(SentryLevel.WARNING, "Task " + callable + " rejected from " + this.executorService, new Object[0]);
        }
        return new CancelledFuture();
    }

    @Override // io.sentry.ISentryExecutorService
    public Future<?> schedule(Runnable runnable, long j) throws RejectedExecutionException {
        if (this.executorService.getQueue().size() < MAX_QUEUE_SIZE) {
            return this.executorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
        }
        SentryOptions sentryOptions = this.options;
        if (sentryOptions != null) {
            sentryOptions.getLogger().log(SentryLevel.WARNING, "Task " + runnable + " rejected from " + this.executorService, new Object[0]);
        }
        return new CancelledFuture();
    }

    @Override // io.sentry.ISentryExecutorService
    public void close(long j) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            if (!this.executorService.isShutdown()) {
                this.executorService.shutdown();
                try {
                    if (!this.executorService.awaitTermination(j, TimeUnit.MILLISECONDS)) {
                        this.executorService.shutdownNow();
                    }
                } catch (InterruptedException unused) {
                    this.executorService.shutdownNow();
                    Thread.currentThread().interrupt();
                }
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

    @Override // io.sentry.ISentryExecutorService
    public boolean isClosed() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            boolean isShutdown = this.executorService.isShutdown();
            if (acquire != null) {
                acquire.close();
            }
            return isShutdown;
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

    @Override // io.sentry.ISentryExecutorService
    public void prewarm() {
        try {
            this.executorService.submit(new Runnable() { // from class: io.sentry.SentryExecutorService$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    SentryExecutorService.this.m3823lambda$prewarm$1$iosentrySentryExecutorService();
                }
            });
        } catch (RejectedExecutionException e) {
            SentryOptions sentryOptions = this.options;
            if (sentryOptions != null) {
                sentryOptions.getLogger().log(SentryLevel.WARNING, "Prewarm task rejected from " + this.executorService, e);
            }
        }
    }

    /* renamed from: lambda$prewarm$1$io-sentry-SentryExecutorService, reason: not valid java name */
    /* synthetic */ void m3823lambda$prewarm$1$iosentrySentryExecutorService() {
        for (int i = 0; i < 40; i++) {
            try {
                this.executorService.schedule(this.dummyRunnable, 365L, TimeUnit.DAYS).cancel(true);
            } catch (RejectedExecutionException unused) {
                return;
            }
        }
        this.executorService.purge();
    }

    private static final class SentryExecutorServiceThreadFactory implements ThreadFactory {
        private int cnt;

        private SentryExecutorServiceThreadFactory() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder("SentryExecutorServiceThreadFactory-");
            int i = this.cnt;
            this.cnt = i + 1;
            sb.append(i);
            Thread thread = new Thread(runnable, sb.toString());
            thread.setDaemon(true);
            return thread;
        }
    }

    private static final class CancelledFuture<T> implements Future<T> {
        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            return true;
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return true;
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return true;
        }

        private CancelledFuture() {
        }

        @Override // java.util.concurrent.Future
        public T get() {
            throw new CancellationException();
        }

        @Override // java.util.concurrent.Future
        public T get(long j, TimeUnit timeUnit) {
            throw new CancellationException();
        }
    }
}
