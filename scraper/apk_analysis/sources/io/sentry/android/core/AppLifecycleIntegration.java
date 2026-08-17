package io.sentry.android.core;

import io.sentry.IScopes;
import io.sentry.ISentryLifecycleToken;
import io.sentry.Integration;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.IntegrationUtils;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class AppLifecycleIntegration implements Integration, Closeable {
    private final AutoClosableReentrantLock lock = new AutoClosableReentrantLock();
    private SentryAndroidOptions options;
    volatile LifecycleWatcher watcher;

    @Override // io.sentry.Integration
    public void register(IScopes iScopes, SentryOptions sentryOptions) {
        Objects.requireNonNull(iScopes, "Scopes are required");
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        this.options = sentryAndroidOptions;
        sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "enableSessionTracking enabled: %s", Boolean.valueOf(this.options.isEnableAutoSessionTracking()));
        this.options.getLogger().log(SentryLevel.DEBUG, "enableAppLifecycleBreadcrumbs enabled: %s", Boolean.valueOf(this.options.isEnableAppLifecycleBreadcrumbs()));
        if (this.options.isEnableAutoSessionTracking() || this.options.isEnableAppLifecycleBreadcrumbs()) {
            ISentryLifecycleToken acquire = this.lock.acquire();
            try {
                if (this.watcher != null) {
                    if (acquire != null) {
                        acquire.close();
                    }
                } else {
                    this.watcher = new LifecycleWatcher(iScopes, this.options.getSessionTrackingIntervalMillis(), this.options.isEnableAutoSessionTracking(), this.options.isEnableAppLifecycleBreadcrumbs());
                    AppState.getInstance().addAppStateListener(this.watcher);
                    if (acquire != null) {
                        acquire.close();
                    }
                    sentryOptions.getLogger().log(SentryLevel.DEBUG, "AppLifecycleIntegration installed.", new Object[0]);
                    IntegrationUtils.addIntegrationToSdkVersion("AppLifecycle");
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

    private void removeObserver() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            LifecycleWatcher lifecycleWatcher = this.watcher;
            this.watcher = null;
            if (acquire != null) {
                acquire.close();
            }
            if (lifecycleWatcher != null) {
                AppState.getInstance().removeAppStateListener(lifecycleWatcher);
                SentryAndroidOptions sentryAndroidOptions = this.options;
                if (sentryAndroidOptions != null) {
                    sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "AppLifecycleIntegration removed.", new Object[0]);
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

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        removeObserver();
        AppState.getInstance().unregisterLifecycleObserver();
    }
}
