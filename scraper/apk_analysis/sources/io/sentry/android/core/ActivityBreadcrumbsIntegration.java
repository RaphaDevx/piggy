package io.sentry.android.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import io.sentry.Breadcrumb;
import io.sentry.Hint;
import io.sentry.IScopes;
import io.sentry.ISentryLifecycleToken;
import io.sentry.Integration;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.Session;
import io.sentry.TypeCheckHint;
import io.sentry.protocol.SentryThread;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.IntegrationUtils;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class ActivityBreadcrumbsIntegration implements Integration, Closeable, Application.ActivityLifecycleCallbacks {
    private final Application application;
    private boolean enabled;
    private final AutoClosableReentrantLock lock = new AutoClosableReentrantLock();
    private IScopes scopes;

    public ActivityBreadcrumbsIntegration(Application application) {
        this.application = (Application) Objects.requireNonNull(application, "Application is required");
    }

    @Override // io.sentry.Integration
    public void register(IScopes iScopes, SentryOptions sentryOptions) {
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        this.scopes = (IScopes) Objects.requireNonNull(iScopes, "Scopes are required");
        this.enabled = sentryAndroidOptions.isEnableActivityLifecycleBreadcrumbs();
        sentryOptions.getLogger().log(SentryLevel.DEBUG, "ActivityBreadcrumbsIntegration enabled: %s", Boolean.valueOf(this.enabled));
        if (this.enabled) {
            this.application.registerActivityLifecycleCallbacks(this);
            sentryOptions.getLogger().log(SentryLevel.DEBUG, "ActivityBreadcrumbIntegration installed.", new Object[0]);
            IntegrationUtils.addIntegrationToSdkVersion("ActivityBreadcrumbs");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.enabled) {
            this.application.unregisterActivityLifecycleCallbacks(this);
            IScopes iScopes = this.scopes;
            if (iScopes != null) {
                iScopes.getOptions().getLogger().log(SentryLevel.DEBUG, "ActivityBreadcrumbsIntegration removed.", new Object[0]);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            addBreadcrumb(activity, "created");
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            addBreadcrumb(activity, Session.JsonKeys.STARTED);
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            addBreadcrumb(activity, "resumed");
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            addBreadcrumb(activity, "paused");
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            addBreadcrumb(activity, "stopped");
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            addBreadcrumb(activity, "saveInstanceState");
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            addBreadcrumb(activity, "destroyed");
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

    private void addBreadcrumb(Activity activity, String str) {
        if (this.scopes == null) {
            return;
        }
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType(NotificationCompat.CATEGORY_NAVIGATION);
        breadcrumb.setData(SentryThread.JsonKeys.STATE, str);
        breadcrumb.setData("screen", getActivityName(activity));
        breadcrumb.setCategory("ui.lifecycle");
        breadcrumb.setLevel(SentryLevel.INFO);
        Hint hint = new Hint();
        hint.set(TypeCheckHint.ANDROID_ACTIVITY, activity);
        this.scopes.addBreadcrumb(breadcrumb, hint);
    }

    private String getActivityName(Activity activity) {
        return activity.getClass().getSimpleName();
    }
}
