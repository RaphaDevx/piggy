package io.sentry.android.core;

import android.os.Debug;
import android.os.Process;
import android.os.SystemClock;
import io.sentry.DateUtils;
import io.sentry.ILogger;
import io.sentry.ISentryExecutorService;
import io.sentry.ISentryLifecycleToken;
import io.sentry.PerformanceCollectionData;
import io.sentry.SentryLevel;
import io.sentry.SentryNanotimeDate;
import io.sentry.SentryUUID;
import io.sentry.android.core.internal.util.SentryFrameMetricsCollector;
import io.sentry.profilemeasurements.ProfileMeasurement;
import io.sentry.profilemeasurements.ProfileMeasurementValue;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.Objects;
import java.io.File;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class AndroidProfiler {
    private static final int BUFFER_SIZE_BYTES = 3000000;
    private static final int PROFILING_TIMEOUT_MILLIS = 30000;
    private final SentryFrameMetricsCollector frameMetricsCollector;
    private String frameMetricsCollectorId;
    private final int intervalUs;
    private final ILogger logger;
    private final ISentryExecutorService timeoutExecutorService;
    private final File traceFilesDir;
    private long profileStartNanos = 0;
    private Future<?> scheduledFinish = null;
    private File traceFile = null;
    private final ArrayDeque<ProfileMeasurementValue> screenFrameRateMeasurements = new ArrayDeque<>();
    private final ArrayDeque<ProfileMeasurementValue> slowFrameRenderMeasurements = new ArrayDeque<>();
    private final ArrayDeque<ProfileMeasurementValue> frozenFrameRenderMeasurements = new ArrayDeque<>();
    private final Map<String, ProfileMeasurement> measurementsMap = new HashMap();
    private boolean isRunning = false;
    protected final AutoClosableReentrantLock lock = new AutoClosableReentrantLock();

    public static class ProfileStartData {
        public final long startCpuMillis;
        public final long startNanos;
        public final Date startTimestamp;

        public ProfileStartData(long j, long j2, Date date) {
            this.startNanos = j;
            this.startCpuMillis = j2;
            this.startTimestamp = date;
        }
    }

    public static class ProfileEndData {
        public final boolean didTimeout;
        public final long endCpuMillis;
        public final long endNanos;
        public final Map<String, ProfileMeasurement> measurementsMap;
        public final File traceFile;

        public ProfileEndData(long j, long j2, boolean z, File file, Map<String, ProfileMeasurement> map) {
            this.endNanos = j;
            this.traceFile = file;
            this.endCpuMillis = j2;
            this.measurementsMap = map;
            this.didTimeout = z;
        }
    }

    public AndroidProfiler(String str, int i, SentryFrameMetricsCollector sentryFrameMetricsCollector, ISentryExecutorService iSentryExecutorService, ILogger iLogger) {
        this.traceFilesDir = new File((String) Objects.requireNonNull(str, "TracesFilesDirPath is required"));
        this.intervalUs = i;
        this.logger = (ILogger) Objects.requireNonNull(iLogger, "Logger is required");
        this.timeoutExecutorService = iSentryExecutorService;
        this.frameMetricsCollector = (SentryFrameMetricsCollector) Objects.requireNonNull(sentryFrameMetricsCollector, "SentryFrameMetricsCollector is required");
    }

    public ProfileStartData start() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            if (this.intervalUs == 0) {
                this.logger.log(SentryLevel.WARNING, "Disabling profiling because intervaUs is set to %d", Integer.valueOf(this.intervalUs));
                if (acquire != null) {
                    acquire.close();
                }
                return null;
            }
            if (this.isRunning) {
                this.logger.log(SentryLevel.WARNING, "Profiling has already started...", new Object[0]);
                if (acquire != null) {
                    acquire.close();
                }
                return null;
            }
            this.traceFile = new File(this.traceFilesDir, SentryUUID.generateSentryId() + ".trace");
            this.measurementsMap.clear();
            this.screenFrameRateMeasurements.clear();
            this.slowFrameRenderMeasurements.clear();
            this.frozenFrameRenderMeasurements.clear();
            this.frameMetricsCollectorId = this.frameMetricsCollector.startCollection(new SentryFrameMetricsCollector.FrameMetricsCollectorListener() { // from class: io.sentry.android.core.AndroidProfiler.1
                float lastRefreshRate = 0.0f;

                @Override // io.sentry.android.core.internal.util.SentryFrameMetricsCollector.FrameMetricsCollectorListener
                public void onFrameMetricCollected(long j, long j2, long j3, long j4, boolean z, boolean z2, float f) {
                    long nanoTimestamp = new SentryNanotimeDate().nanoTimestamp();
                    long nanoTime = ((j2 - System.nanoTime()) + SystemClock.elapsedRealtimeNanos()) - AndroidProfiler.this.profileStartNanos;
                    if (nanoTime < 0) {
                        return;
                    }
                    if (z2) {
                        AndroidProfiler.this.frozenFrameRenderMeasurements.addLast(new ProfileMeasurementValue(Long.valueOf(nanoTime), Long.valueOf(j3), nanoTimestamp));
                    } else if (z) {
                        AndroidProfiler.this.slowFrameRenderMeasurements.addLast(new ProfileMeasurementValue(Long.valueOf(nanoTime), Long.valueOf(j3), nanoTimestamp));
                    }
                    if (f != this.lastRefreshRate) {
                        this.lastRefreshRate = f;
                        AndroidProfiler.this.screenFrameRateMeasurements.addLast(new ProfileMeasurementValue(Long.valueOf(nanoTime), Float.valueOf(f), nanoTimestamp));
                    }
                }
            });
            try {
                ISentryExecutorService iSentryExecutorService = this.timeoutExecutorService;
                if (iSentryExecutorService != null) {
                    this.scheduledFinish = iSentryExecutorService.schedule(new Runnable() { // from class: io.sentry.android.core.AndroidProfiler$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            AndroidProfiler.this.m3852lambda$start$0$iosentryandroidcoreAndroidProfiler();
                        }
                    }, 30000L);
                }
            } catch (RejectedExecutionException e) {
                this.logger.log(SentryLevel.ERROR, "Failed to call the executor. Profiling will not be automatically finished. Did you call Sentry.close()?", e);
            }
            this.profileStartNanos = SystemClock.elapsedRealtimeNanos();
            Date currentDateTime = DateUtils.getCurrentDateTime();
            long elapsedCpuTime = Process.getElapsedCpuTime();
            try {
                Debug.startMethodTracingSampling(this.traceFile.getPath(), BUFFER_SIZE_BYTES, this.intervalUs);
                this.isRunning = true;
                ProfileStartData profileStartData = new ProfileStartData(this.profileStartNanos, elapsedCpuTime, currentDateTime);
                if (acquire != null) {
                    acquire.close();
                }
                return profileStartData;
            } catch (Throwable th) {
                endAndCollect(false, null);
                this.logger.log(SentryLevel.ERROR, "Unable to start a profile: ", th);
                this.isRunning = false;
                if (acquire != null) {
                    acquire.close();
                }
                return null;
            }
        } catch (Throwable th2) {
            if (acquire != null) {
                try {
                    acquire.close();
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
            }
            throw th2;
        }
    }

    /* renamed from: lambda$start$0$io-sentry-android-core-AndroidProfiler, reason: not valid java name */
    /* synthetic */ void m3852lambda$start$0$iosentryandroidcoreAndroidProfiler() {
        endAndCollect(true, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041 A[Catch: all -> 0x00ba, TRY_LEAVE, TryCatch #3 {all -> 0x00ba, blocks: (B:3:0x0006, B:5:0x000c, B:12:0x0020, B:13:0x002e, B:15:0x0041, B:19:0x0052, B:22:0x005c, B:23:0x006a, B:25:0x0072, B:26:0x0080, B:28:0x0088, B:29:0x0098, B:31:0x009f, B:32:0x00a5, B:42:0x00b7, B:43:0x00b9, B:11:0x001d, B:39:0x0024), top: B:2:0x0006, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0052 A[Catch: all -> 0x00ba, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x00ba, blocks: (B:3:0x0006, B:5:0x000c, B:12:0x0020, B:13:0x002e, B:15:0x0041, B:19:0x0052, B:22:0x005c, B:23:0x006a, B:25:0x0072, B:26:0x0080, B:28:0x0088, B:29:0x0098, B:31:0x009f, B:32:0x00a5, B:42:0x00b7, B:43:0x00b9, B:11:0x001d, B:39:0x0024), top: B:2:0x0006, inners: #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public io.sentry.android.core.AndroidProfiler.ProfileEndData endAndCollect(boolean r14, java.util.List<io.sentry.PerformanceCollectionData> r15) {
        /*
            r13 = this;
            io.sentry.util.AutoClosableReentrantLock r0 = r13.lock
            io.sentry.ISentryLifecycleToken r0 = r0.acquire()
            boolean r1 = r13.isRunning     // Catch: java.lang.Throwable -> Lba
            r2 = 0
            r3 = 0
            if (r1 != 0) goto L1d
            io.sentry.ILogger r13 = r13.logger     // Catch: java.lang.Throwable -> Lba
            io.sentry.SentryLevel r14 = io.sentry.SentryLevel.WARNING     // Catch: java.lang.Throwable -> Lba
            java.lang.String r15 = "Profiler not running"
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lba
            r13.log(r14, r15, r1)     // Catch: java.lang.Throwable -> Lba
            if (r0 == 0) goto L1c
            r0.close()
        L1c:
            return r2
        L1d:
            android.os.Debug.stopMethodTracing()     // Catch: java.lang.Throwable -> L23
        L20:
            r13.isRunning = r3     // Catch: java.lang.Throwable -> Lba
            goto L2e
        L23:
            r1 = move-exception
            io.sentry.ILogger r4 = r13.logger     // Catch: java.lang.Throwable -> Lb6
            io.sentry.SentryLevel r5 = io.sentry.SentryLevel.ERROR     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r6 = "Error while stopping profiling: "
            r4.log(r5, r6, r1)     // Catch: java.lang.Throwable -> Lb6
            goto L20
        L2e:
            io.sentry.android.core.internal.util.SentryFrameMetricsCollector r1 = r13.frameMetricsCollector     // Catch: java.lang.Throwable -> Lba
            java.lang.String r4 = r13.frameMetricsCollectorId     // Catch: java.lang.Throwable -> Lba
            r1.stopCollection(r4)     // Catch: java.lang.Throwable -> Lba
            long r6 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch: java.lang.Throwable -> Lba
            long r8 = android.os.Process.getElapsedCpuTime()     // Catch: java.lang.Throwable -> Lba
            java.io.File r1 = r13.traceFile     // Catch: java.lang.Throwable -> Lba
            if (r1 != 0) goto L52
            io.sentry.ILogger r13 = r13.logger     // Catch: java.lang.Throwable -> Lba
            io.sentry.SentryLevel r14 = io.sentry.SentryLevel.ERROR     // Catch: java.lang.Throwable -> Lba
            java.lang.String r15 = "Trace file does not exists"
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lba
            r13.log(r14, r15, r1)     // Catch: java.lang.Throwable -> Lba
            if (r0 == 0) goto L51
            r0.close()
        L51:
            return r2
        L52:
            java.util.ArrayDeque<io.sentry.profilemeasurements.ProfileMeasurementValue> r1 = r13.slowFrameRenderMeasurements     // Catch: java.lang.Throwable -> Lba
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> Lba
            java.lang.String r3 = "nanosecond"
            if (r1 != 0) goto L6a
            java.util.Map<java.lang.String, io.sentry.profilemeasurements.ProfileMeasurement> r1 = r13.measurementsMap     // Catch: java.lang.Throwable -> Lba
            java.lang.String r4 = "slow_frame_renders"
            io.sentry.profilemeasurements.ProfileMeasurement r5 = new io.sentry.profilemeasurements.ProfileMeasurement     // Catch: java.lang.Throwable -> Lba
            java.util.ArrayDeque<io.sentry.profilemeasurements.ProfileMeasurementValue> r10 = r13.slowFrameRenderMeasurements     // Catch: java.lang.Throwable -> Lba
            r5.<init>(r3, r10)     // Catch: java.lang.Throwable -> Lba
            r1.put(r4, r5)     // Catch: java.lang.Throwable -> Lba
        L6a:
            java.util.ArrayDeque<io.sentry.profilemeasurements.ProfileMeasurementValue> r1 = r13.frozenFrameRenderMeasurements     // Catch: java.lang.Throwable -> Lba
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> Lba
            if (r1 != 0) goto L80
            java.util.Map<java.lang.String, io.sentry.profilemeasurements.ProfileMeasurement> r1 = r13.measurementsMap     // Catch: java.lang.Throwable -> Lba
            java.lang.String r4 = "frozen_frame_renders"
            io.sentry.profilemeasurements.ProfileMeasurement r5 = new io.sentry.profilemeasurements.ProfileMeasurement     // Catch: java.lang.Throwable -> Lba
            java.util.ArrayDeque<io.sentry.profilemeasurements.ProfileMeasurementValue> r10 = r13.frozenFrameRenderMeasurements     // Catch: java.lang.Throwable -> Lba
            r5.<init>(r3, r10)     // Catch: java.lang.Throwable -> Lba
            r1.put(r4, r5)     // Catch: java.lang.Throwable -> Lba
        L80:
            java.util.ArrayDeque<io.sentry.profilemeasurements.ProfileMeasurementValue> r1 = r13.screenFrameRateMeasurements     // Catch: java.lang.Throwable -> Lba
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> Lba
            if (r1 != 0) goto L98
            java.util.Map<java.lang.String, io.sentry.profilemeasurements.ProfileMeasurement> r1 = r13.measurementsMap     // Catch: java.lang.Throwable -> Lba
            java.lang.String r3 = "screen_frame_rates"
            io.sentry.profilemeasurements.ProfileMeasurement r4 = new io.sentry.profilemeasurements.ProfileMeasurement     // Catch: java.lang.Throwable -> Lba
            java.lang.String r5 = "hz"
            java.util.ArrayDeque<io.sentry.profilemeasurements.ProfileMeasurementValue> r10 = r13.screenFrameRateMeasurements     // Catch: java.lang.Throwable -> Lba
            r4.<init>(r5, r10)     // Catch: java.lang.Throwable -> Lba
            r1.put(r3, r4)     // Catch: java.lang.Throwable -> Lba
        L98:
            r13.putPerformanceCollectionDataInMeasurements(r15)     // Catch: java.lang.Throwable -> Lba
            java.util.concurrent.Future<?> r15 = r13.scheduledFinish     // Catch: java.lang.Throwable -> Lba
            if (r15 == 0) goto La5
            r1 = 1
            r15.cancel(r1)     // Catch: java.lang.Throwable -> Lba
            r13.scheduledFinish = r2     // Catch: java.lang.Throwable -> Lba
        La5:
            io.sentry.android.core.AndroidProfiler$ProfileEndData r15 = new io.sentry.android.core.AndroidProfiler$ProfileEndData     // Catch: java.lang.Throwable -> Lba
            java.io.File r11 = r13.traceFile     // Catch: java.lang.Throwable -> Lba
            java.util.Map<java.lang.String, io.sentry.profilemeasurements.ProfileMeasurement> r12 = r13.measurementsMap     // Catch: java.lang.Throwable -> Lba
            r5 = r15
            r10 = r14
            r5.<init>(r6, r8, r10, r11, r12)     // Catch: java.lang.Throwable -> Lba
            if (r0 == 0) goto Lb5
            r0.close()
        Lb5:
            return r15
        Lb6:
            r14 = move-exception
            r13.isRunning = r3     // Catch: java.lang.Throwable -> Lba
            throw r14     // Catch: java.lang.Throwable -> Lba
        Lba:
            r13 = move-exception
            if (r0 == 0) goto Lc5
            r0.close()     // Catch: java.lang.Throwable -> Lc1
            goto Lc5
        Lc1:
            r14 = move-exception
            r13.addSuppressed(r14)
        Lc5:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.AndroidProfiler.endAndCollect(boolean, java.util.List):io.sentry.android.core.AndroidProfiler$ProfileEndData");
    }

    public void close() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            Future<?> future = this.scheduledFinish;
            if (future != null) {
                future.cancel(true);
                this.scheduledFinish = null;
            }
            if (this.isRunning) {
                endAndCollect(true, null);
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

    private void putPerformanceCollectionDataInMeasurements(List<PerformanceCollectionData> list) {
        long elapsedRealtimeNanos = (SystemClock.elapsedRealtimeNanos() - this.profileStartNanos) - TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
        if (list != null) {
            ArrayDeque arrayDeque = new ArrayDeque(list.size());
            ArrayDeque arrayDeque2 = new ArrayDeque(list.size());
            ArrayDeque arrayDeque3 = new ArrayDeque(list.size());
            synchronized (list) {
                for (PerformanceCollectionData performanceCollectionData : list) {
                    long nanoTimestamp = performanceCollectionData.getNanoTimestamp();
                    long j = nanoTimestamp + elapsedRealtimeNanos;
                    Double cpuUsagePercentage = performanceCollectionData.getCpuUsagePercentage();
                    Long usedHeapMemory = performanceCollectionData.getUsedHeapMemory();
                    Long usedNativeMemory = performanceCollectionData.getUsedNativeMemory();
                    if (cpuUsagePercentage != null) {
                        arrayDeque3.add(new ProfileMeasurementValue(Long.valueOf(j), cpuUsagePercentage, nanoTimestamp));
                    }
                    if (usedHeapMemory != null) {
                        arrayDeque.add(new ProfileMeasurementValue(Long.valueOf(j), usedHeapMemory, nanoTimestamp));
                    }
                    if (usedNativeMemory != null) {
                        arrayDeque2.add(new ProfileMeasurementValue(Long.valueOf(j), usedNativeMemory, nanoTimestamp));
                    }
                }
            }
            if (!arrayDeque3.isEmpty()) {
                this.measurementsMap.put(ProfileMeasurement.ID_CPU_USAGE, new ProfileMeasurement(ProfileMeasurement.UNIT_PERCENT, arrayDeque3));
            }
            if (!arrayDeque.isEmpty()) {
                this.measurementsMap.put(ProfileMeasurement.ID_MEMORY_FOOTPRINT, new ProfileMeasurement(ProfileMeasurement.UNIT_BYTES, arrayDeque));
            }
            if (arrayDeque2.isEmpty()) {
                return;
            }
            this.measurementsMap.put(ProfileMeasurement.ID_MEMORY_NATIVE_FOOTPRINT, new ProfileMeasurement(ProfileMeasurement.UNIT_BYTES, arrayDeque2));
        }
    }
}
