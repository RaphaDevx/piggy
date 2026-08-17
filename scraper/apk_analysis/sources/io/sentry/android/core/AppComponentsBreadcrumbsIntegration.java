package io.sentry.android.core;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import androidx.core.app.NotificationCompat;
import com.salesforce.marketingcloud.UrlHandler;
import io.sentry.Breadcrumb;
import io.sentry.Hint;
import io.sentry.IScopes;
import io.sentry.Integration;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.TypeCheckHint;
import io.sentry.android.core.internal.util.AndroidCurrentDateProvider;
import io.sentry.android.core.internal.util.Debouncer;
import io.sentry.android.core.internal.util.DeviceOrientations;
import io.sentry.protocol.Device;
import io.sentry.util.IntegrationUtils;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class AppComponentsBreadcrumbsIntegration implements Integration, Closeable, ComponentCallbacks2 {
    private static final long DEBOUNCE_WAIT_TIME_MS = 60000;
    private static final Hint EMPTY_HINT = new Hint();
    private final Context context;
    private SentryAndroidOptions options;
    private IScopes scopes;
    private final Debouncer trimMemoryDebouncer = new Debouncer(AndroidCurrentDateProvider.getInstance(), 60000, 0);

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    public AppComponentsBreadcrumbsIntegration(Context context) {
        this.context = (Context) Objects.requireNonNull(ContextUtils.getApplicationContext(context), "Context is required");
    }

    @Override // io.sentry.Integration
    public void register(IScopes iScopes, SentryOptions sentryOptions) {
        this.scopes = (IScopes) Objects.requireNonNull(iScopes, "Scopes are required");
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        this.options = sentryAndroidOptions;
        sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "AppComponentsBreadcrumbsIntegration enabled: %s", Boolean.valueOf(this.options.isEnableAppComponentBreadcrumbs()));
        if (this.options.isEnableAppComponentBreadcrumbs()) {
            try {
                this.context.registerComponentCallbacks(this);
                sentryOptions.getLogger().log(SentryLevel.DEBUG, "AppComponentsBreadcrumbsIntegration installed.", new Object[0]);
                IntegrationUtils.addIntegrationToSdkVersion("AppComponentsBreadcrumbs");
            } catch (Throwable th) {
                this.options.setEnableAppComponentBreadcrumbs(false);
                sentryOptions.getLogger().log(SentryLevel.INFO, th, "ComponentCallbacks2 is not available.", new Object[0]);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.context.unregisterComponentCallbacks(this);
        } catch (Throwable th) {
            SentryAndroidOptions sentryAndroidOptions = this.options;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, th, "It was not possible to unregisterComponentCallbacks", new Object[0]);
            }
        }
        SentryAndroidOptions sentryAndroidOptions2 = this.options;
        if (sentryAndroidOptions2 != null) {
            sentryAndroidOptions2.getLogger().log(SentryLevel.DEBUG, "AppComponentsBreadcrumbsIntegration removed.", new Object[0]);
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration configuration) {
        final long currentTimeMillis = System.currentTimeMillis();
        executeInBackground(new Runnable() { // from class: io.sentry.android.core.AppComponentsBreadcrumbsIntegration$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AppComponentsBreadcrumbsIntegration.this.m3855x57f88530(currentTimeMillis, configuration);
            }
        });
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(final int i) {
        if (i >= 40 && !this.trimMemoryDebouncer.checkForDebounce()) {
            final long currentTimeMillis = System.currentTimeMillis();
            executeInBackground(new Runnable() { // from class: io.sentry.android.core.AppComponentsBreadcrumbsIntegration$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AppComponentsBreadcrumbsIntegration.this.m3856x2259e8ca(currentTimeMillis, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: captureLowMemoryBreadcrumb, reason: merged with bridge method [inline-methods] */
    public void m3856x2259e8ca(long j, int i) {
        if (this.scopes != null) {
            Breadcrumb breadcrumb = new Breadcrumb(j);
            breadcrumb.setType("system");
            breadcrumb.setCategory("device.event");
            breadcrumb.setMessage("Low memory");
            breadcrumb.setData(UrlHandler.ACTION, "LOW_MEMORY");
            breadcrumb.setData("level", Integer.valueOf(i));
            breadcrumb.setLevel(SentryLevel.WARNING);
            this.scopes.addBreadcrumb(breadcrumb, EMPTY_HINT);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: captureConfigurationChangedBreadcrumb, reason: merged with bridge method [inline-methods] */
    public void m3855x57f88530(long j, Configuration configuration) {
        String str;
        if (this.scopes != null) {
            Device.DeviceOrientation orientation = DeviceOrientations.getOrientation(this.context.getResources().getConfiguration().orientation);
            if (orientation != null) {
                str = orientation.name().toLowerCase(Locale.ROOT);
            } else {
                str = "undefined";
            }
            Breadcrumb breadcrumb = new Breadcrumb(j);
            breadcrumb.setType(NotificationCompat.CATEGORY_NAVIGATION);
            breadcrumb.setCategory("device.orientation");
            breadcrumb.setData("position", str);
            breadcrumb.setLevel(SentryLevel.INFO);
            Hint hint = new Hint();
            hint.set(TypeCheckHint.ANDROID_CONFIGURATION, configuration);
            this.scopes.addBreadcrumb(breadcrumb, hint);
        }
    }

    private void executeInBackground(Runnable runnable) {
        SentryAndroidOptions sentryAndroidOptions = this.options;
        if (sentryAndroidOptions != null) {
            try {
                sentryAndroidOptions.getExecutorService().submit(runnable);
            } catch (Throwable th) {
                this.options.getLogger().log(SentryLevel.ERROR, th, "Failed to submit app components breadcrumb task", new Object[0]);
            }
        }
    }
}
