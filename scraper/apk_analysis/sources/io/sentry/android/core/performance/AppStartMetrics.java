package io.sentry.android.core.performance;

import android.app.Activity;
import android.app.Application;
import android.content.ContentProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import io.sentry.IContinuousProfiler;
import io.sentry.ISentryLifecycleToken;
import io.sentry.ITransactionProfiler;
import io.sentry.NoOpLogger;
import io.sentry.TracesSamplingDecision;
import io.sentry.android.core.BuildInfoProvider;
import io.sentry.android.core.ContextUtils;
import io.sentry.android.core.CurrentActivityHolder;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.internal.util.FirstDrawDoneListener;
import io.sentry.util.AutoClosableReentrantLock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class AppStartMetrics extends ActivityLifecycleCallbacksAdapter {
    private static volatile AppStartMetrics instance;
    private static long CLASS_LOADED_UPTIME_MS = SystemClock.uptimeMillis();
    public static final AutoClosableReentrantLock staticLock = new AutoClosableReentrantLock();
    private AppStartType appStartType = AppStartType.UNKNOWN;
    private ITransactionProfiler appStartProfiler = null;
    private IContinuousProfiler appStartContinuousProfiler = null;
    private TracesSamplingDecision appStartSamplingDecision = null;
    private boolean isCallbackRegistered = false;
    private boolean shouldSendStartMeasurements = true;
    private final AtomicInteger activeActivitiesCounter = new AtomicInteger();
    private final AtomicBoolean firstDrawDone = new AtomicBoolean(false);
    private final TimeSpan appStartSpan = new TimeSpan();
    private final TimeSpan sdkInitTimeSpan = new TimeSpan();
    private final TimeSpan applicationOnCreate = new TimeSpan();
    private final Map<ContentProvider, TimeSpan> contentProviderOnCreates = new HashMap();
    private final List<ActivityLifecycleTimeSpan> activityLifecycles = new ArrayList();
    private boolean appLaunchedInForeground = ContextUtils.isForegroundImportance();

    public enum AppStartType {
        UNKNOWN,
        COLD,
        WARM
    }

    public static AppStartMetrics getInstance() {
        if (instance == null) {
            ISentryLifecycleToken acquire = staticLock.acquire();
            try {
                if (instance == null) {
                    instance = new AppStartMetrics();
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
        return instance;
    }

    public TimeSpan getAppStartTimeSpan() {
        return this.appStartSpan;
    }

    public TimeSpan createProcessInitSpan() {
        TimeSpan timeSpan = new TimeSpan();
        timeSpan.setup("Process Initialization", this.appStartSpan.getStartTimestampMs(), this.appStartSpan.getStartUptimeMs(), CLASS_LOADED_UPTIME_MS);
        return timeSpan;
    }

    public TimeSpan getSdkInitTimeSpan() {
        return this.sdkInitTimeSpan;
    }

    public TimeSpan getApplicationOnCreateTimeSpan() {
        return this.applicationOnCreate;
    }

    public void setAppStartType(AppStartType appStartType) {
        this.appStartType = appStartType;
    }

    public AppStartType getAppStartType() {
        return this.appStartType;
    }

    public boolean isAppLaunchedInForeground() {
        return this.appLaunchedInForeground;
    }

    public void setAppLaunchedInForeground(boolean z) {
        this.appLaunchedInForeground = z;
    }

    public List<TimeSpan> getContentProviderOnCreateTimeSpans() {
        ArrayList arrayList = new ArrayList(this.contentProviderOnCreates.values());
        Collections.sort(arrayList);
        return arrayList;
    }

    public List<ActivityLifecycleTimeSpan> getActivityLifecycleTimeSpans() {
        ArrayList arrayList = new ArrayList(this.activityLifecycles);
        Collections.sort(arrayList);
        return arrayList;
    }

    public void addActivityLifecycleTimeSpans(ActivityLifecycleTimeSpan activityLifecycleTimeSpan) {
        this.activityLifecycles.add(activityLifecycleTimeSpan);
    }

    public void onAppStartSpansSent() {
        this.shouldSendStartMeasurements = false;
        this.contentProviderOnCreates.clear();
        this.activityLifecycles.clear();
    }

    public boolean shouldSendStartMeasurements() {
        return this.shouldSendStartMeasurements && this.appLaunchedInForeground;
    }

    public long getClassLoadedUptimeMs() {
        return CLASS_LOADED_UPTIME_MS;
    }

    public TimeSpan getAppStartTimeSpanWithFallback(SentryAndroidOptions sentryAndroidOptions) {
        if (this.appStartType != AppStartType.UNKNOWN && this.appLaunchedInForeground) {
            if (sentryAndroidOptions.isEnablePerformanceV2()) {
                TimeSpan appStartTimeSpan = getAppStartTimeSpan();
                if (appStartTimeSpan.hasStarted() && appStartTimeSpan.getDurationMs() <= TimeUnit.MINUTES.toMillis(1L)) {
                    return appStartTimeSpan;
                }
            }
            TimeSpan sdkInitTimeSpan = getSdkInitTimeSpan();
            if (sdkInitTimeSpan.hasStarted() && sdkInitTimeSpan.getDurationMs() <= TimeUnit.MINUTES.toMillis(1L)) {
                return sdkInitTimeSpan;
            }
        }
        return new TimeSpan();
    }

    public void clear() {
        this.appStartType = AppStartType.UNKNOWN;
        this.appStartSpan.reset();
        this.sdkInitTimeSpan.reset();
        this.applicationOnCreate.reset();
        this.contentProviderOnCreates.clear();
        this.activityLifecycles.clear();
        ITransactionProfiler iTransactionProfiler = this.appStartProfiler;
        if (iTransactionProfiler != null) {
            iTransactionProfiler.close();
        }
        this.appStartProfiler = null;
        IContinuousProfiler iContinuousProfiler = this.appStartContinuousProfiler;
        if (iContinuousProfiler != null) {
            iContinuousProfiler.close(true);
        }
        this.appStartContinuousProfiler = null;
        this.appStartSamplingDecision = null;
        this.appLaunchedInForeground = false;
        this.isCallbackRegistered = false;
        this.shouldSendStartMeasurements = true;
        this.firstDrawDone.set(false);
        this.activeActivitiesCounter.set(0);
    }

    public ITransactionProfiler getAppStartProfiler() {
        return this.appStartProfiler;
    }

    public void setAppStartProfiler(ITransactionProfiler iTransactionProfiler) {
        this.appStartProfiler = iTransactionProfiler;
    }

    public IContinuousProfiler getAppStartContinuousProfiler() {
        return this.appStartContinuousProfiler;
    }

    public void setAppStartContinuousProfiler(IContinuousProfiler iContinuousProfiler) {
        this.appStartContinuousProfiler = iContinuousProfiler;
    }

    public void setAppStartSamplingDecision(TracesSamplingDecision tracesSamplingDecision) {
        this.appStartSamplingDecision = tracesSamplingDecision;
    }

    public TracesSamplingDecision getAppStartSamplingDecision() {
        return this.appStartSamplingDecision;
    }

    public void setClassLoadedUptimeMs(long j) {
        CLASS_LOADED_UPTIME_MS = j;
    }

    public static void onApplicationCreate(Application application) {
        long uptimeMillis = SystemClock.uptimeMillis();
        AppStartMetrics appStartMetrics = getInstance();
        if (appStartMetrics.applicationOnCreate.hasNotStarted()) {
            appStartMetrics.applicationOnCreate.setStartedAt(uptimeMillis);
            appStartMetrics.registerLifecycleCallbacks(application);
        }
    }

    public static void onApplicationPostCreate(Application application) {
        long uptimeMillis = SystemClock.uptimeMillis();
        AppStartMetrics appStartMetrics = getInstance();
        if (appStartMetrics.applicationOnCreate.hasNotStopped()) {
            appStartMetrics.applicationOnCreate.setDescription(application.getClass().getName() + ".onCreate");
            appStartMetrics.applicationOnCreate.setStoppedAt(uptimeMillis);
        }
    }

    public void registerLifecycleCallbacks(Application application) {
        if (this.isCallbackRegistered) {
            return;
        }
        boolean z = true;
        this.isCallbackRegistered = true;
        if (!this.appLaunchedInForeground && !ContextUtils.isForegroundImportance()) {
            z = false;
        }
        this.appLaunchedInForeground = z;
        application.registerActivityLifecycleCallbacks(instance);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.sentry.android.core.performance.AppStartMetrics$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AppStartMetrics.this.m3888x5b2eef10();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkCreateTimeOnMain, reason: merged with bridge method [inline-methods] */
    public void m3888x5b2eef10() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.sentry.android.core.performance.AppStartMetrics$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AppStartMetrics.this.m3885x1d07b267();
            }
        });
    }

    /* renamed from: lambda$checkCreateTimeOnMain$1$io-sentry-android-core-performance-AppStartMetrics, reason: not valid java name */
    /* synthetic */ void m3885x1d07b267() {
        if (this.activeActivitiesCounter.get() == 0) {
            this.appLaunchedInForeground = false;
            ITransactionProfiler iTransactionProfiler = this.appStartProfiler;
            if (iTransactionProfiler != null && iTransactionProfiler.isRunning()) {
                this.appStartProfiler.close();
                this.appStartProfiler = null;
            }
            IContinuousProfiler iContinuousProfiler = this.appStartContinuousProfiler;
            if (iContinuousProfiler == null || !iContinuousProfiler.isRunning()) {
                return;
            }
            this.appStartContinuousProfiler.close(true);
            this.appStartContinuousProfiler = null;
        }
    }

    @Override // io.sentry.android.core.performance.ActivityLifecycleCallbacksAdapter, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        CurrentActivityHolder.getInstance().setActivity(activity);
        if (this.activeActivitiesCounter.incrementAndGet() == 1 && !this.firstDrawDone.get()) {
            long uptimeMillis = SystemClock.uptimeMillis();
            long startUptimeMs = uptimeMillis - this.appStartSpan.getStartUptimeMs();
            if (!this.appLaunchedInForeground || startUptimeMs > TimeUnit.MINUTES.toMillis(1L)) {
                this.appStartType = AppStartType.WARM;
                this.shouldSendStartMeasurements = true;
                this.appStartSpan.reset();
                this.appStartSpan.start();
                this.appStartSpan.setStartedAt(uptimeMillis);
                CLASS_LOADED_UPTIME_MS = uptimeMillis;
                this.contentProviderOnCreates.clear();
                this.applicationOnCreate.reset();
            } else {
                this.appStartType = bundle == null ? AppStartType.COLD : AppStartType.WARM;
            }
        }
        this.appLaunchedInForeground = true;
    }

    @Override // io.sentry.android.core.performance.ActivityLifecycleCallbacksAdapter, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        CurrentActivityHolder.getInstance().setActivity(activity);
        if (this.firstDrawDone.get()) {
            return;
        }
        if (activity.getWindow() != null) {
            FirstDrawDoneListener.registerForNextDraw(activity, new Runnable() { // from class: io.sentry.android.core.performance.AppStartMetrics$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    AppStartMetrics.this.m3886xf57a779c();
                }
            }, new BuildInfoProvider(NoOpLogger.getInstance()));
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.sentry.android.core.performance.AppStartMetrics$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    AppStartMetrics.this.m3887x235311fb();
                }
            });
        }
    }

    @Override // io.sentry.android.core.performance.ActivityLifecycleCallbacksAdapter, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        CurrentActivityHolder.getInstance().setActivity(activity);
    }

    @Override // io.sentry.android.core.performance.ActivityLifecycleCallbacksAdapter, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        CurrentActivityHolder.getInstance().clearActivity(activity);
    }

    @Override // io.sentry.android.core.performance.ActivityLifecycleCallbacksAdapter, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        CurrentActivityHolder.getInstance().clearActivity(activity);
    }

    @Override // io.sentry.android.core.performance.ActivityLifecycleCallbacksAdapter, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        CurrentActivityHolder.getInstance().clearActivity(activity);
        if (this.activeActivitiesCounter.decrementAndGet() != 0 || activity.isChangingConfigurations()) {
            return;
        }
        this.appLaunchedInForeground = false;
        this.shouldSendStartMeasurements = true;
        this.firstDrawDone.set(false);
    }

    public static void onContentProviderCreate(ContentProvider contentProvider) {
        long uptimeMillis = SystemClock.uptimeMillis();
        TimeSpan timeSpan = new TimeSpan();
        timeSpan.setStartedAt(uptimeMillis);
        getInstance().contentProviderOnCreates.put(contentProvider, timeSpan);
    }

    public static void onContentProviderPostCreate(ContentProvider contentProvider) {
        long uptimeMillis = SystemClock.uptimeMillis();
        TimeSpan timeSpan = getInstance().contentProviderOnCreates.get(contentProvider);
        if (timeSpan == null || !timeSpan.hasNotStopped()) {
            return;
        }
        timeSpan.setDescription(contentProvider.getClass().getName() + ".onCreate");
        timeSpan.setStoppedAt(uptimeMillis);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: onFirstFrameDrawn, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public synchronized void m3887x235311fb() {
        if (!this.firstDrawDone.getAndSet(true)) {
            AppStartMetrics appStartMetrics = getInstance();
            appStartMetrics.getSdkInitTimeSpan().stop();
            appStartMetrics.getAppStartTimeSpan().stop();
        }
    }
}
