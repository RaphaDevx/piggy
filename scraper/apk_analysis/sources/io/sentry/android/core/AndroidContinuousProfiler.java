package io.sentry.android.core;

import io.sentry.CompositePerformanceCollector;
import io.sentry.DataCategory;
import io.sentry.IConnectionStatusProvider;
import io.sentry.IContinuousProfiler;
import io.sentry.ILogger;
import io.sentry.IScopes;
import io.sentry.ISentryExecutorService;
import io.sentry.ISentryLifecycleToken;
import io.sentry.NoOpScopes;
import io.sentry.ProfileChunk;
import io.sentry.ProfileLifecycle;
import io.sentry.Sentry;
import io.sentry.SentryDate;
import io.sentry.SentryLevel;
import io.sentry.SentryNanotimeDate;
import io.sentry.SentryOptions;
import io.sentry.TracesSampler;
import io.sentry.android.core.AndroidProfiler;
import io.sentry.android.core.internal.util.SentryFrameMetricsCollector;
import io.sentry.protocol.SentryId;
import io.sentry.transport.RateLimiter;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.SentryRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class AndroidContinuousProfiler implements IContinuousProfiler, RateLimiter.IRateLimitObserver {
    private static final long MAX_CHUNK_DURATION_MILLIS = 60000;
    private final BuildInfoProvider buildInfoProvider;
    private final ISentryExecutorService executorService;
    private final SentryFrameMetricsCollector frameMetricsCollector;
    private final ILogger logger;
    private CompositePerformanceCollector performanceCollector;
    private final String profilingTracesDirPath;
    private final int profilingTracesHz;
    private IScopes scopes;
    private Future<?> stopFuture;
    private boolean isInitialized = false;
    private AndroidProfiler profiler = null;
    private boolean isRunning = false;
    private final List<ProfileChunk.Builder> payloadBuilders = new ArrayList();
    private SentryId profilerId = SentryId.EMPTY_ID;
    private SentryId chunkId = SentryId.EMPTY_ID;
    private final AtomicBoolean isClosed = new AtomicBoolean(false);
    private SentryDate startProfileChunkTimestamp = new SentryNanotimeDate();
    private volatile boolean shouldSample = true;
    private boolean shouldStop = false;
    private boolean isSampled = false;
    private int rootSpanCounter = 0;
    private final AutoClosableReentrantLock lock = new AutoClosableReentrantLock();
    private final AutoClosableReentrantLock payloadLock = new AutoClosableReentrantLock();

    public AndroidContinuousProfiler(BuildInfoProvider buildInfoProvider, SentryFrameMetricsCollector sentryFrameMetricsCollector, ILogger iLogger, String str, int i, ISentryExecutorService iSentryExecutorService) {
        this.logger = iLogger;
        this.frameMetricsCollector = sentryFrameMetricsCollector;
        this.buildInfoProvider = buildInfoProvider;
        this.profilingTracesDirPath = str;
        this.profilingTracesHz = i;
        this.executorService = iSentryExecutorService;
    }

    private void init() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;
        String str = this.profilingTracesDirPath;
        if (str == null) {
            this.logger.log(SentryLevel.WARNING, "Disabling profiling because no profiling traces dir path is defined in options.", new Object[0]);
        } else if (this.profilingTracesHz <= 0) {
            this.logger.log(SentryLevel.WARNING, "Disabling profiling because trace rate is set to %d", Integer.valueOf(this.profilingTracesHz));
        } else {
            this.profiler = new AndroidProfiler(str, ((int) TimeUnit.SECONDS.toMicros(1L)) / this.profilingTracesHz, this.frameMetricsCollector, null, this.logger);
        }
    }

    @Override // io.sentry.IContinuousProfiler
    public void startProfiler(ProfileLifecycle profileLifecycle, TracesSampler tracesSampler) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            if (this.shouldSample) {
                this.isSampled = tracesSampler.sampleSessionProfile(SentryRandom.current().nextDouble());
                this.shouldSample = false;
            }
            if (!this.isSampled) {
                this.logger.log(SentryLevel.DEBUG, "Profiler was not started due to sampling decision.", new Object[0]);
                if (acquire != null) {
                    acquire.close();
                    return;
                }
                return;
            }
            int i = AnonymousClass1.$SwitchMap$io$sentry$ProfileLifecycle[profileLifecycle.ordinal()];
            if (i == 1) {
                if (this.rootSpanCounter < 0) {
                    this.rootSpanCounter = 0;
                }
                this.rootSpanCounter++;
            } else if (i == 2 && isRunning()) {
                this.logger.log(SentryLevel.DEBUG, "Profiler is already running.", new Object[0]);
                if (acquire != null) {
                    acquire.close();
                    return;
                }
                return;
            }
            if (!isRunning()) {
                this.logger.log(SentryLevel.DEBUG, "Started Profiler.", new Object[0]);
                start();
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

    /* renamed from: io.sentry.android.core.AndroidContinuousProfiler$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$sentry$ProfileLifecycle;

        static {
            int[] iArr = new int[ProfileLifecycle.values().length];
            $SwitchMap$io$sentry$ProfileLifecycle = iArr;
            try {
                iArr[ProfileLifecycle.TRACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$sentry$ProfileLifecycle[ProfileLifecycle.MANUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void initScopes() {
        IScopes iScopes = this.scopes;
        if ((iScopes == null || iScopes == NoOpScopes.getInstance()) && Sentry.getCurrentScopes() != NoOpScopes.getInstance()) {
            this.scopes = Sentry.getCurrentScopes();
            this.performanceCollector = Sentry.getCurrentScopes().getOptions().getCompositePerformanceCollector();
            RateLimiter rateLimiter = this.scopes.getRateLimiter();
            if (rateLimiter != null) {
                rateLimiter.addRateLimitObserver(this);
            }
        }
    }

    private void start() {
        initScopes();
        if (this.buildInfoProvider.getSdkInfoVersion() < 22) {
            return;
        }
        init();
        if (this.profiler == null) {
            return;
        }
        IScopes iScopes = this.scopes;
        if (iScopes != null) {
            RateLimiter rateLimiter = iScopes.getRateLimiter();
            if (rateLimiter != null && (rateLimiter.isActiveForCategory(DataCategory.All) || rateLimiter.isActiveForCategory(DataCategory.ProfileChunkUi))) {
                this.logger.log(SentryLevel.WARNING, "SDK is rate limited. Stopping profiler.", new Object[0]);
                stop(false);
                return;
            } else {
                if (this.scopes.getOptions().getConnectionStatusProvider().getConnectionStatus() == IConnectionStatusProvider.ConnectionStatus.DISCONNECTED) {
                    this.logger.log(SentryLevel.WARNING, "Device is offline. Stopping profiler.", new Object[0]);
                    stop(false);
                    return;
                }
                this.startProfileChunkTimestamp = this.scopes.getOptions().getDateProvider().now();
            }
        } else {
            this.startProfileChunkTimestamp = new SentryNanotimeDate();
        }
        if (this.profiler.start() == null) {
            return;
        }
        this.isRunning = true;
        if (this.profilerId.equals(SentryId.EMPTY_ID)) {
            this.profilerId = new SentryId();
        }
        if (this.chunkId.equals(SentryId.EMPTY_ID)) {
            this.chunkId = new SentryId();
        }
        CompositePerformanceCollector compositePerformanceCollector = this.performanceCollector;
        if (compositePerformanceCollector != null) {
            compositePerformanceCollector.start(this.chunkId.toString());
        }
        try {
            this.stopFuture = this.executorService.schedule(new Runnable() { // from class: io.sentry.android.core.AndroidContinuousProfiler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AndroidContinuousProfiler.this.m3851lambda$start$0$iosentryandroidcoreAndroidContinuousProfiler();
                }
            }, 60000L);
        } catch (RejectedExecutionException e) {
            this.logger.log(SentryLevel.ERROR, "Failed to schedule profiling chunk finish. Did you call Sentry.close()?", e);
            this.shouldStop = true;
        }
    }

    /* renamed from: lambda$start$0$io-sentry-android-core-AndroidContinuousProfiler, reason: not valid java name */
    /* synthetic */ void m3851lambda$start$0$iosentryandroidcoreAndroidContinuousProfiler() {
        stop(true);
    }

    @Override // io.sentry.IContinuousProfiler
    public void stopProfiler(ProfileLifecycle profileLifecycle) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            int i = AnonymousClass1.$SwitchMap$io$sentry$ProfileLifecycle[profileLifecycle.ordinal()];
            if (i == 1) {
                int i2 = this.rootSpanCounter - 1;
                this.rootSpanCounter = i2;
                if (i2 > 0) {
                    if (acquire != null) {
                        acquire.close();
                        return;
                    }
                    return;
                } else {
                    if (i2 < 0) {
                        this.rootSpanCounter = 0;
                    }
                    this.shouldStop = true;
                }
            } else if (i == 2) {
                this.shouldStop = true;
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

    private void stop(boolean z) {
        initScopes();
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            Future<?> future = this.stopFuture;
            if (future != null) {
                future.cancel(true);
            }
            if (this.profiler != null && this.isRunning) {
                if (this.buildInfoProvider.getSdkInfoVersion() < 22) {
                    if (acquire != null) {
                        acquire.close();
                        return;
                    }
                    return;
                }
                CompositePerformanceCollector compositePerformanceCollector = this.performanceCollector;
                AndroidProfiler.ProfileEndData endAndCollect = this.profiler.endAndCollect(false, compositePerformanceCollector != null ? compositePerformanceCollector.stop(this.chunkId.toString()) : null);
                if (endAndCollect == null) {
                    this.logger.log(SentryLevel.ERROR, "An error occurred while collecting a profile chunk, and it won't be sent.", new Object[0]);
                } else {
                    ISentryLifecycleToken acquire2 = this.payloadLock.acquire();
                    try {
                        this.payloadBuilders.add(new ProfileChunk.Builder(this.profilerId, this.chunkId, endAndCollect.measurementsMap, endAndCollect.traceFile, this.startProfileChunkTimestamp, ProfileChunk.PLATFORM_ANDROID));
                        if (acquire2 != null) {
                            acquire2.close();
                        }
                    } finally {
                    }
                }
                this.isRunning = false;
                this.chunkId = SentryId.EMPTY_ID;
                IScopes iScopes = this.scopes;
                if (iScopes != null) {
                    sendChunks(iScopes, iScopes.getOptions());
                }
                if (z && !this.shouldStop) {
                    this.logger.log(SentryLevel.DEBUG, "Profile chunk finished. Starting a new one.", new Object[0]);
                    start();
                } else {
                    this.profilerId = SentryId.EMPTY_ID;
                    this.logger.log(SentryLevel.DEBUG, "Profile chunk finished.", new Object[0]);
                }
                if (acquire != null) {
                    acquire.close();
                    return;
                }
                return;
            }
            this.profilerId = SentryId.EMPTY_ID;
            this.chunkId = SentryId.EMPTY_ID;
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

    @Override // io.sentry.IContinuousProfiler
    public void reevaluateSampling() {
        this.shouldSample = true;
    }

    @Override // io.sentry.IContinuousProfiler
    public void close(boolean z) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            this.rootSpanCounter = 0;
            this.shouldStop = true;
            if (z) {
                stop(false);
                this.isClosed.set(true);
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

    @Override // io.sentry.IContinuousProfiler
    public SentryId getProfilerId() {
        return this.profilerId;
    }

    @Override // io.sentry.IContinuousProfiler
    public SentryId getChunkId() {
        return this.chunkId;
    }

    private void sendChunks(final IScopes iScopes, final SentryOptions sentryOptions) {
        try {
            sentryOptions.getExecutorService().submit(new Runnable() { // from class: io.sentry.android.core.AndroidContinuousProfiler$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AndroidContinuousProfiler.this.m3850x2762eb8a(sentryOptions, iScopes);
                }
            });
        } catch (Throwable th) {
            sentryOptions.getLogger().log(SentryLevel.DEBUG, "Failed to send profile chunks.", th);
        }
    }

    /* renamed from: lambda$sendChunks$1$io-sentry-android-core-AndroidContinuousProfiler, reason: not valid java name */
    /* synthetic */ void m3850x2762eb8a(SentryOptions sentryOptions, IScopes iScopes) {
        if (this.isClosed.get()) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.payloadBuilders.size());
        ISentryLifecycleToken acquire = this.payloadLock.acquire();
        try {
            Iterator<ProfileChunk.Builder> it = this.payloadBuilders.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().build(sentryOptions));
            }
            this.payloadBuilders.clear();
            if (acquire != null) {
                acquire.close();
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                iScopes.captureProfileChunk((ProfileChunk) it2.next());
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

    @Override // io.sentry.IContinuousProfiler
    public boolean isRunning() {
        return this.isRunning;
    }

    Future<?> getStopFuture() {
        return this.stopFuture;
    }

    public int getRootSpanCounter() {
        return this.rootSpanCounter;
    }

    @Override // io.sentry.transport.RateLimiter.IRateLimitObserver
    public void onRateLimitChanged(RateLimiter rateLimiter) {
        if (rateLimiter.isActiveForCategory(DataCategory.All) || rateLimiter.isActiveForCategory(DataCategory.ProfileChunkUi)) {
            this.logger.log(SentryLevel.WARNING, "SDK is rate limited. Stopping profiler.", new Object[0]);
            stop(false);
        }
    }
}
