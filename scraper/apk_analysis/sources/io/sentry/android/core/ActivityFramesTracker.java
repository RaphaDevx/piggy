package io.sentry.android.core;

import android.app.Activity;
import android.util.SparseIntArray;
import androidx.core.app.FrameMetricsAggregator;
import io.sentry.ISentryLifecycleToken;
import io.sentry.SentryLevel;
import io.sentry.android.core.internal.util.AndroidThreadChecker;
import io.sentry.protocol.MeasurementValue;
import io.sentry.protocol.SentryId;
import io.sentry.util.AutoClosableReentrantLock;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class ActivityFramesTracker {
    private final Map<SentryId, Map<String, MeasurementValue>> activityMeasurements;
    private final Map<Activity, FrameCounts> frameCountAtStartSnapshots;
    private FrameMetricsAggregator frameMetricsAggregator;
    private final MainLooperHandler handler;
    protected AutoClosableReentrantLock lock;
    private final SentryAndroidOptions options;

    public ActivityFramesTracker(io.sentry.util.LoadClass loadClass, SentryAndroidOptions sentryAndroidOptions, MainLooperHandler mainLooperHandler) {
        this.frameMetricsAggregator = null;
        this.activityMeasurements = new ConcurrentHashMap();
        this.frameCountAtStartSnapshots = new WeakHashMap();
        this.lock = new AutoClosableReentrantLock();
        if (loadClass.isClassAvailable("androidx.core.app.FrameMetricsAggregator", sentryAndroidOptions.getLogger())) {
            this.frameMetricsAggregator = new FrameMetricsAggregator();
        }
        this.options = sentryAndroidOptions;
        this.handler = mainLooperHandler;
    }

    public ActivityFramesTracker(io.sentry.util.LoadClass loadClass, SentryAndroidOptions sentryAndroidOptions) {
        this(loadClass, sentryAndroidOptions, new MainLooperHandler());
    }

    ActivityFramesTracker(io.sentry.util.LoadClass loadClass, SentryAndroidOptions sentryAndroidOptions, MainLooperHandler mainLooperHandler, FrameMetricsAggregator frameMetricsAggregator) {
        this(loadClass, sentryAndroidOptions, mainLooperHandler);
        this.frameMetricsAggregator = frameMetricsAggregator;
    }

    public boolean isFrameMetricsAggregatorAvailable() {
        return (this.frameMetricsAggregator == null || !this.options.isEnableFramesTracking() || this.options.isEnablePerformanceV2()) ? false : true;
    }

    public void addActivity(final Activity activity) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            if (!isFrameMetricsAggregatorAvailable()) {
                if (acquire != null) {
                    acquire.close();
                }
            } else {
                runSafelyOnUiThread(new Runnable() { // from class: io.sentry.android.core.ActivityFramesTracker$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityFramesTracker.this.m3838xd056273f(activity);
                    }
                }, "FrameMetricsAggregator.add");
                snapshotFrameCountsAtStart(activity);
                if (acquire != null) {
                    acquire.close();
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

    /* renamed from: lambda$addActivity$0$io-sentry-android-core-ActivityFramesTracker, reason: not valid java name */
    /* synthetic */ void m3838xd056273f(Activity activity) {
        this.frameMetricsAggregator.add(activity);
    }

    private void snapshotFrameCountsAtStart(Activity activity) {
        FrameCounts calculateCurrentFrameCounts = calculateCurrentFrameCounts();
        if (calculateCurrentFrameCounts != null) {
            this.frameCountAtStartSnapshots.put(activity, calculateCurrentFrameCounts);
        }
    }

    private FrameCounts calculateCurrentFrameCounts() {
        FrameMetricsAggregator frameMetricsAggregator;
        int i;
        int i2;
        SparseIntArray sparseIntArray;
        if (!isFrameMetricsAggregatorAvailable() || (frameMetricsAggregator = this.frameMetricsAggregator) == null) {
            return null;
        }
        SparseIntArray[] metrics = frameMetricsAggregator.getMetrics();
        int i3 = 0;
        if (metrics == null || metrics.length <= 0 || (sparseIntArray = metrics[0]) == null) {
            i = 0;
            i2 = 0;
        } else {
            int i4 = 0;
            i = 0;
            i2 = 0;
            while (i3 < sparseIntArray.size()) {
                int keyAt = sparseIntArray.keyAt(i3);
                int valueAt = sparseIntArray.valueAt(i3);
                i4 += valueAt;
                if (keyAt > 700) {
                    i2 += valueAt;
                } else if (keyAt > 16) {
                    i += valueAt;
                }
                i3++;
            }
            i3 = i4;
        }
        return new FrameCounts(i3, i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x007e, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0081, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setMetrics(final android.app.Activity r6, io.sentry.protocol.SentryId r7) {
        /*
            r5 = this;
            java.lang.String r0 = "none"
            io.sentry.util.AutoClosableReentrantLock r1 = r5.lock
            io.sentry.ISentryLifecycleToken r1 = r1.acquire()
            boolean r2 = r5.isFrameMetricsAggregatorAvailable()     // Catch: java.lang.Throwable -> L82
            if (r2 != 0) goto L14
            if (r1 == 0) goto L13
            r1.close()
        L13:
            return
        L14:
            io.sentry.android.core.ActivityFramesTracker$$ExternalSyntheticLambda2 r2 = new io.sentry.android.core.ActivityFramesTracker$$ExternalSyntheticLambda2     // Catch: java.lang.Throwable -> L82
            r2.<init>()     // Catch: java.lang.Throwable -> L82
            r3 = 0
            r5.runSafelyOnUiThread(r2, r3)     // Catch: java.lang.Throwable -> L82
            io.sentry.android.core.ActivityFramesTracker$FrameCounts r6 = r5.diffFrameCountsAtEnd(r6)     // Catch: java.lang.Throwable -> L82
            if (r6 == 0) goto L7c
            int r2 = io.sentry.android.core.ActivityFramesTracker.FrameCounts.access$100(r6)     // Catch: java.lang.Throwable -> L82
            if (r2 != 0) goto L36
            int r2 = io.sentry.android.core.ActivityFramesTracker.FrameCounts.access$200(r6)     // Catch: java.lang.Throwable -> L82
            if (r2 != 0) goto L36
            int r2 = io.sentry.android.core.ActivityFramesTracker.FrameCounts.access$300(r6)     // Catch: java.lang.Throwable -> L82
            if (r2 != 0) goto L36
            goto L7c
        L36:
            io.sentry.protocol.MeasurementValue r2 = new io.sentry.protocol.MeasurementValue     // Catch: java.lang.Throwable -> L82
            int r3 = io.sentry.android.core.ActivityFramesTracker.FrameCounts.access$100(r6)     // Catch: java.lang.Throwable -> L82
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L82
            r2.<init>(r3, r0)     // Catch: java.lang.Throwable -> L82
            io.sentry.protocol.MeasurementValue r3 = new io.sentry.protocol.MeasurementValue     // Catch: java.lang.Throwable -> L82
            int r4 = io.sentry.android.core.ActivityFramesTracker.FrameCounts.access$200(r6)     // Catch: java.lang.Throwable -> L82
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L82
            r3.<init>(r4, r0)     // Catch: java.lang.Throwable -> L82
            io.sentry.protocol.MeasurementValue r4 = new io.sentry.protocol.MeasurementValue     // Catch: java.lang.Throwable -> L82
            int r6 = io.sentry.android.core.ActivityFramesTracker.FrameCounts.access$300(r6)     // Catch: java.lang.Throwable -> L82
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Throwable -> L82
            r4.<init>(r6, r0)     // Catch: java.lang.Throwable -> L82
            java.util.HashMap r6 = new java.util.HashMap     // Catch: java.lang.Throwable -> L82
            r6.<init>()     // Catch: java.lang.Throwable -> L82
            java.lang.String r0 = "frames_total"
            r6.put(r0, r2)     // Catch: java.lang.Throwable -> L82
            java.lang.String r0 = "frames_slow"
            r6.put(r0, r3)     // Catch: java.lang.Throwable -> L82
            java.lang.String r0 = "frames_frozen"
            r6.put(r0, r4)     // Catch: java.lang.Throwable -> L82
            java.util.Map<io.sentry.protocol.SentryId, java.util.Map<java.lang.String, io.sentry.protocol.MeasurementValue>> r5 = r5.activityMeasurements     // Catch: java.lang.Throwable -> L82
            r5.put(r7, r6)     // Catch: java.lang.Throwable -> L82
            if (r1 == 0) goto L7b
            r1.close()
        L7b:
            return
        L7c:
            if (r1 == 0) goto L81
            r1.close()
        L81:
            return
        L82:
            r5 = move-exception
            if (r1 == 0) goto L8d
            r1.close()     // Catch: java.lang.Throwable -> L89
            goto L8d
        L89:
            r6 = move-exception
            r5.addSuppressed(r6)
        L8d:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.ActivityFramesTracker.setMetrics(android.app.Activity, io.sentry.protocol.SentryId):void");
    }

    /* renamed from: lambda$setMetrics$1$io-sentry-android-core-ActivityFramesTracker, reason: not valid java name */
    /* synthetic */ void m3840lambda$setMetrics$1$iosentryandroidcoreActivityFramesTracker(Activity activity) {
        this.frameMetricsAggregator.remove(activity);
    }

    private FrameCounts diffFrameCountsAtEnd(Activity activity) {
        FrameCounts calculateCurrentFrameCounts;
        FrameCounts remove = this.frameCountAtStartSnapshots.remove(activity);
        if (remove == null || (calculateCurrentFrameCounts = calculateCurrentFrameCounts()) == null) {
            return null;
        }
        return new FrameCounts(calculateCurrentFrameCounts.totalFrames - remove.totalFrames, calculateCurrentFrameCounts.slowFrames - remove.slowFrames, calculateCurrentFrameCounts.frozenFrames - remove.frozenFrames);
    }

    public Map<String, MeasurementValue> takeMetrics(SentryId sentryId) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            if (!isFrameMetricsAggregatorAvailable()) {
                if (acquire == null) {
                    return null;
                }
                acquire.close();
                return null;
            }
            Map<String, MeasurementValue> map = this.activityMeasurements.get(sentryId);
            this.activityMeasurements.remove(sentryId);
            if (acquire != null) {
                acquire.close();
            }
            return map;
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

    public void stop() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            if (isFrameMetricsAggregatorAvailable()) {
                runSafelyOnUiThread(new Runnable() { // from class: io.sentry.android.core.ActivityFramesTracker$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityFramesTracker.this.m3841lambda$stop$2$iosentryandroidcoreActivityFramesTracker();
                    }
                }, "FrameMetricsAggregator.stop");
                this.frameMetricsAggregator.reset();
            }
            this.activityMeasurements.clear();
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

    /* renamed from: lambda$stop$2$io-sentry-android-core-ActivityFramesTracker, reason: not valid java name */
    /* synthetic */ void m3841lambda$stop$2$iosentryandroidcoreActivityFramesTracker() {
        this.frameMetricsAggregator.stop();
    }

    private void runSafelyOnUiThread(final Runnable runnable, final String str) {
        try {
            if (AndroidThreadChecker.getInstance().isMainThread()) {
                runnable.run();
            } else {
                this.handler.post(new Runnable() { // from class: io.sentry.android.core.ActivityFramesTracker$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityFramesTracker.this.m3839x49051cca(runnable, str);
                    }
                });
            }
        } catch (Throwable unused) {
            if (str != null) {
                this.options.getLogger().log(SentryLevel.WARNING, "Failed to execute " + str, new Object[0]);
            }
        }
    }

    /* renamed from: lambda$runSafelyOnUiThread$3$io-sentry-android-core-ActivityFramesTracker, reason: not valid java name */
    /* synthetic */ void m3839x49051cca(Runnable runnable, String str) {
        try {
            runnable.run();
        } catch (Throwable unused) {
            if (str != null) {
                this.options.getLogger().log(SentryLevel.WARNING, "Failed to execute " + str, new Object[0]);
            }
        }
    }

    private static final class FrameCounts {
        private final int frozenFrames;
        private final int slowFrames;
        private final int totalFrames;

        private FrameCounts(int i, int i2, int i3) {
            this.totalFrames = i;
            this.slowFrames = i2;
            this.frozenFrames = i3;
        }
    }
}
