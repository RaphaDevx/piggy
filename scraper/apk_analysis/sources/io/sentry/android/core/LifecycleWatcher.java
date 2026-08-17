package io.sentry.android.core;

import androidx.core.app.NotificationCompat;
import io.sentry.Breadcrumb;
import io.sentry.IScope;
import io.sentry.IScopes;
import io.sentry.ISentryLifecycleToken;
import io.sentry.ScopeCallback;
import io.sentry.SentryLevel;
import io.sentry.Session;
import io.sentry.android.core.AppState;
import io.sentry.protocol.SentryThread;
import io.sentry.transport.CurrentDateProvider;
import io.sentry.transport.ICurrentDateProvider;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.LazyEvaluator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes2.dex */
final class LifecycleWatcher implements AppState.AppStateListener {
    private final ICurrentDateProvider currentDateProvider;
    private final boolean enableAppLifecycleBreadcrumbs;
    private final boolean enableSessionTracking;
    private final AtomicLong lastUpdatedSession;
    private final IScopes scopes;
    private final long sessionIntervalMillis;
    private final LazyEvaluator<Timer> timer;
    private final AutoClosableReentrantLock timerLock;
    private TimerTask timerTask;

    static /* synthetic */ Timer lambda$new$0() {
        return new Timer(true);
    }

    LifecycleWatcher(IScopes iScopes, long j, boolean z, boolean z2) {
        this(iScopes, j, z, z2, CurrentDateProvider.getInstance());
    }

    LifecycleWatcher(IScopes iScopes, long j, boolean z, boolean z2, ICurrentDateProvider iCurrentDateProvider) {
        this.lastUpdatedSession = new AtomicLong(0L);
        this.timer = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.android.core.LifecycleWatcher$$ExternalSyntheticLambda0
            @Override // io.sentry.util.LazyEvaluator.Evaluator
            public final Object evaluate() {
                return LifecycleWatcher.lambda$new$0();
            }
        });
        this.timerLock = new AutoClosableReentrantLock();
        this.sessionIntervalMillis = j;
        this.enableSessionTracking = z;
        this.enableAppLifecycleBreadcrumbs = z2;
        this.scopes = iScopes;
        this.currentDateProvider = iCurrentDateProvider;
    }

    @Override // io.sentry.android.core.AppState.AppStateListener
    public void onForeground() {
        startSession();
        addAppBreadcrumb("foreground");
    }

    private void startSession() {
        cancelTask();
        long currentTimeMillis = this.currentDateProvider.getCurrentTimeMillis();
        this.scopes.configureScope(new ScopeCallback() { // from class: io.sentry.android.core.LifecycleWatcher$$ExternalSyntheticLambda1
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                LifecycleWatcher.this.m3861lambda$startSession$1$iosentryandroidcoreLifecycleWatcher(iScope);
            }
        });
        long j = this.lastUpdatedSession.get();
        if (j == 0 || j + this.sessionIntervalMillis <= currentTimeMillis) {
            if (this.enableSessionTracking) {
                this.scopes.startSession();
            }
            this.scopes.getOptions().getReplayController().start();
        }
        this.scopes.getOptions().getReplayController().resume();
        this.lastUpdatedSession.set(currentTimeMillis);
    }

    /* renamed from: lambda$startSession$1$io-sentry-android-core-LifecycleWatcher, reason: not valid java name */
    /* synthetic */ void m3861lambda$startSession$1$iosentryandroidcoreLifecycleWatcher(IScope iScope) {
        Session session;
        if (this.lastUpdatedSession.get() != 0 || (session = iScope.getSession()) == null || session.getStarted() == null) {
            return;
        }
        this.lastUpdatedSession.set(session.getStarted().getTime());
    }

    @Override // io.sentry.android.core.AppState.AppStateListener
    public void onBackground() {
        this.lastUpdatedSession.set(this.currentDateProvider.getCurrentTimeMillis());
        this.scopes.getOptions().getReplayController().pause();
        scheduleEndSession();
        addAppBreadcrumb("background");
    }

    private void scheduleEndSession() {
        ISentryLifecycleToken acquire = this.timerLock.acquire();
        try {
            cancelTask();
            this.timerTask = new TimerTask() { // from class: io.sentry.android.core.LifecycleWatcher.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (LifecycleWatcher.this.enableSessionTracking) {
                        LifecycleWatcher.this.scopes.endSession();
                    }
                    LifecycleWatcher.this.scopes.getOptions().getReplayController().stop();
                    LifecycleWatcher.this.scopes.getOptions().getContinuousProfiler().close(false);
                }
            };
            this.timer.getValue().schedule(this.timerTask, this.sessionIntervalMillis);
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

    private void cancelTask() {
        ISentryLifecycleToken acquire = this.timerLock.acquire();
        try {
            TimerTask timerTask = this.timerTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.timerTask = null;
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

    private void addAppBreadcrumb(String str) {
        if (this.enableAppLifecycleBreadcrumbs) {
            Breadcrumb breadcrumb = new Breadcrumb();
            breadcrumb.setType(NotificationCompat.CATEGORY_NAVIGATION);
            breadcrumb.setData(SentryThread.JsonKeys.STATE, str);
            breadcrumb.setCategory("app.lifecycle");
            breadcrumb.setLevel(SentryLevel.INFO);
            this.scopes.addBreadcrumb(breadcrumb);
        }
    }

    TimerTask getTimerTask() {
        return this.timerTask;
    }

    Timer getTimer() {
        return this.timer.getValue();
    }
}
