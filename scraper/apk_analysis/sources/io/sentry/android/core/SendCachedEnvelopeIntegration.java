package io.sentry.android.core;

import io.sentry.DataCategory;
import io.sentry.IConnectionStatusProvider;
import io.sentry.IScopes;
import io.sentry.ISentryLifecycleToken;
import io.sentry.Integration;
import io.sentry.SendCachedEnvelopeFireAndForgetIntegration;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.transport.RateLimiter;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.IntegrationUtils;
import io.sentry.util.LazyEvaluator;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
final class SendCachedEnvelopeIntegration implements Integration, IConnectionStatusProvider.IConnectionStatusObserver, Closeable {
    private IConnectionStatusProvider connectionStatusProvider;
    private final SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetFactory factory;
    private SentryAndroidOptions options;
    private IScopes scopes;
    private SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForget sender;
    private final LazyEvaluator<Boolean> startupCrashMarkerEvaluator;
    private final AtomicBoolean startupCrashHandled = new AtomicBoolean(false);
    private final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private final AtomicBoolean isClosed = new AtomicBoolean(false);
    private final AutoClosableReentrantLock lock = new AutoClosableReentrantLock();

    public SendCachedEnvelopeIntegration(SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetFactory sendFireAndForgetFactory, LazyEvaluator<Boolean> lazyEvaluator) {
        this.factory = (SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetFactory) Objects.requireNonNull(sendFireAndForgetFactory, "SendFireAndForgetFactory is required");
        this.startupCrashMarkerEvaluator = lazyEvaluator;
    }

    @Override // io.sentry.Integration
    public void register(IScopes iScopes, SentryOptions sentryOptions) {
        this.scopes = (IScopes) Objects.requireNonNull(iScopes, "Scopes are required");
        this.options = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        if (!this.factory.hasValidPath(sentryOptions.getCacheDirPath(), sentryOptions.getLogger())) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "No cache dir path is defined in options.", new Object[0]);
        } else {
            IntegrationUtils.addIntegrationToSdkVersion("SendCachedEnvelope");
            sendCachedEnvelopes(iScopes, this.options);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.isClosed.set(true);
        IConnectionStatusProvider iConnectionStatusProvider = this.connectionStatusProvider;
        if (iConnectionStatusProvider != null) {
            iConnectionStatusProvider.removeConnectionStatusObserver(this);
        }
    }

    @Override // io.sentry.IConnectionStatusProvider.IConnectionStatusObserver
    public void onConnectionStatusChanged(IConnectionStatusProvider.ConnectionStatus connectionStatus) {
        if (this.scopes == null || this.options == null || connectionStatus == IConnectionStatusProvider.ConnectionStatus.DISCONNECTED) {
            return;
        }
        sendCachedEnvelopes(this.scopes, this.options);
    }

    private void sendCachedEnvelopes(final IScopes iScopes, final SentryAndroidOptions sentryAndroidOptions) {
        try {
            ISentryLifecycleToken acquire = this.lock.acquire();
            try {
                Future<?> submit = sentryAndroidOptions.getExecutorService().submit(new Runnable() { // from class: io.sentry.android.core.SendCachedEnvelopeIntegration$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        SendCachedEnvelopeIntegration.this.m3863xcf3c5fcd(sentryAndroidOptions, iScopes);
                    }
                });
                if (this.startupCrashMarkerEvaluator.getValue().booleanValue() && this.startupCrashHandled.compareAndSet(false, true)) {
                    sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "Startup Crash marker exists, blocking flush.", new Object[0]);
                    try {
                        submit.get(sentryAndroidOptions.getStartupCrashFlushTimeoutMillis(), TimeUnit.MILLISECONDS);
                    } catch (TimeoutException unused) {
                        sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "Synchronous send timed out, continuing in the background.", new Object[0]);
                    }
                }
                sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "SendCachedEnvelopeIntegration installed.", new Object[0]);
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
        } catch (RejectedExecutionException e) {
            sentryAndroidOptions.getLogger().log(SentryLevel.ERROR, "Failed to call the executor. Cached events will not be sent. Did you call Sentry.close()?", e);
        } catch (Throwable th3) {
            sentryAndroidOptions.getLogger().log(SentryLevel.ERROR, "Failed to call the executor. Cached events will not be sent", th3);
        }
    }

    /* renamed from: lambda$sendCachedEnvelopes$0$io-sentry-android-core-SendCachedEnvelopeIntegration, reason: not valid java name */
    /* synthetic */ void m3863xcf3c5fcd(SentryAndroidOptions sentryAndroidOptions, IScopes iScopes) {
        try {
            if (this.isClosed.get()) {
                sentryAndroidOptions.getLogger().log(SentryLevel.INFO, "SendCachedEnvelopeIntegration, not trying to send after closing.", new Object[0]);
                return;
            }
            if (!this.isInitialized.getAndSet(true)) {
                IConnectionStatusProvider connectionStatusProvider = sentryAndroidOptions.getConnectionStatusProvider();
                this.connectionStatusProvider = connectionStatusProvider;
                connectionStatusProvider.addConnectionStatusObserver(this);
                this.sender = this.factory.create(iScopes, sentryAndroidOptions);
            }
            IConnectionStatusProvider iConnectionStatusProvider = this.connectionStatusProvider;
            if (iConnectionStatusProvider != null && iConnectionStatusProvider.getConnectionStatus() == IConnectionStatusProvider.ConnectionStatus.DISCONNECTED) {
                sentryAndroidOptions.getLogger().log(SentryLevel.INFO, "SendCachedEnvelopeIntegration, no connection.", new Object[0]);
                return;
            }
            RateLimiter rateLimiter = iScopes.getRateLimiter();
            if (rateLimiter != null && rateLimiter.isActiveForCategory(DataCategory.All)) {
                sentryAndroidOptions.getLogger().log(SentryLevel.INFO, "SendCachedEnvelopeIntegration, rate limiting active.", new Object[0]);
                return;
            }
            SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForget sendFireAndForget = this.sender;
            if (sendFireAndForget == null) {
                sentryAndroidOptions.getLogger().log(SentryLevel.ERROR, "SendCachedEnvelopeIntegration factory is null.", new Object[0]);
            } else {
                sendFireAndForget.send();
            }
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().log(SentryLevel.ERROR, "Failed trying to send cached events.", th);
        }
    }
}
