package io.sentry.android.core;

import io.sentry.ILogger;
import io.sentry.IScopes;
import io.sentry.ISentryLifecycleToken;
import io.sentry.Integration;
import io.sentry.OutboxSender;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.IntegrationUtils;
import io.sentry.util.Objects;
import java.io.Closeable;

/* loaded from: classes2.dex */
public abstract class EnvelopeFileObserverIntegration implements Integration, Closeable {
    private ILogger logger;
    private EnvelopeFileObserver observer;
    private boolean isClosed = false;
    protected final AutoClosableReentrantLock startLock = new AutoClosableReentrantLock();

    abstract String getPath(SentryOptions sentryOptions);

    public static EnvelopeFileObserverIntegration getOutboxFileObserver() {
        return new OutboxEnvelopeFileObserverIntegration();
    }

    @Override // io.sentry.Integration
    public final void register(final IScopes iScopes, final SentryOptions sentryOptions) {
        Objects.requireNonNull(iScopes, "Scopes are required");
        Objects.requireNonNull(sentryOptions, "SentryOptions is required");
        this.logger = sentryOptions.getLogger();
        final String path = getPath(sentryOptions);
        if (path == null) {
            this.logger.log(SentryLevel.WARNING, "Null given as a path to EnvelopeFileObserverIntegration. Nothing will be registered.", new Object[0]);
            return;
        }
        this.logger.log(SentryLevel.DEBUG, "Registering EnvelopeFileObserverIntegration for path: %s", path);
        try {
            sentryOptions.getExecutorService().submit(new Runnable() { // from class: io.sentry.android.core.EnvelopeFileObserverIntegration$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EnvelopeFileObserverIntegration.this.m3860xc62b36a1(iScopes, sentryOptions, path);
                }
            });
        } catch (Throwable th) {
            this.logger.log(SentryLevel.DEBUG, "Failed to start EnvelopeFileObserverIntegration on executor thread.", th);
        }
    }

    /* renamed from: lambda$register$0$io-sentry-android-core-EnvelopeFileObserverIntegration, reason: not valid java name */
    /* synthetic */ void m3860xc62b36a1(IScopes iScopes, SentryOptions sentryOptions, String str) {
        ISentryLifecycleToken acquire = this.startLock.acquire();
        try {
            if (!this.isClosed) {
                startOutboxSender(iScopes, sentryOptions, str);
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

    private void startOutboxSender(IScopes iScopes, SentryOptions sentryOptions, String str) {
        EnvelopeFileObserver envelopeFileObserver = new EnvelopeFileObserver(str, new OutboxSender(iScopes, sentryOptions.getEnvelopeReader(), sentryOptions.getSerializer(), sentryOptions.getLogger(), sentryOptions.getFlushTimeoutMillis(), sentryOptions.getMaxQueueSize()), sentryOptions.getLogger(), sentryOptions.getFlushTimeoutMillis());
        this.observer = envelopeFileObserver;
        try {
            envelopeFileObserver.startWatching();
            sentryOptions.getLogger().log(SentryLevel.DEBUG, "EnvelopeFileObserverIntegration installed.", new Object[0]);
            IntegrationUtils.addIntegrationToSdkVersion("EnvelopeFileObserver");
        } catch (Throwable th) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "Failed to initialize EnvelopeFileObserverIntegration.", th);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ISentryLifecycleToken acquire = this.startLock.acquire();
        try {
            this.isClosed = true;
            if (acquire != null) {
                acquire.close();
            }
            EnvelopeFileObserver envelopeFileObserver = this.observer;
            if (envelopeFileObserver != null) {
                envelopeFileObserver.stopWatching();
                ILogger iLogger = this.logger;
                if (iLogger != null) {
                    iLogger.log(SentryLevel.DEBUG, "EnvelopeFileObserverIntegration removed.", new Object[0]);
                }
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

    private static final class OutboxEnvelopeFileObserverIntegration extends EnvelopeFileObserverIntegration {
        private OutboxEnvelopeFileObserverIntegration() {
        }

        @Override // io.sentry.android.core.EnvelopeFileObserverIntegration
        protected String getPath(SentryOptions sentryOptions) {
            return sentryOptions.getOutboxPath();
        }
    }
}
