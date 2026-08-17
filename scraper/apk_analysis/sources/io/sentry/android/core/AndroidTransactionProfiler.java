package io.sentry.android.core;

import android.content.Context;
import io.sentry.DateUtils;
import io.sentry.ILogger;
import io.sentry.ISentryExecutorService;
import io.sentry.ISentryLifecycleToken;
import io.sentry.ITransaction;
import io.sentry.ITransactionProfiler;
import io.sentry.PerformanceCollectionData;
import io.sentry.ProfilingTraceData;
import io.sentry.ProfilingTransactionData;
import io.sentry.ScopesAdapter;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.core.AndroidProfiler;
import io.sentry.android.core.internal.util.SentryFrameMetricsCollector;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.Objects;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
final class AndroidTransactionProfiler implements ITransactionProfiler {
    private final BuildInfoProvider buildInfoProvider;
    private final Context context;
    private ProfilingTransactionData currentProfilingTransactionData;
    private final ISentryExecutorService executorService;
    private final SentryFrameMetricsCollector frameMetricsCollector;
    private boolean isInitialized;
    private final boolean isProfilingEnabled;
    private final AutoClosableReentrantLock lock;
    private final ILogger logger;
    private long profileStartCpuMillis;
    private long profileStartNanos;
    private Date profileStartTimestamp;
    private AndroidProfiler profiler;
    private final String profilingTracesDirPath;
    private final int profilingTracesHz;
    private int transactionsCounter;

    public AndroidTransactionProfiler(Context context, SentryAndroidOptions sentryAndroidOptions, BuildInfoProvider buildInfoProvider, SentryFrameMetricsCollector sentryFrameMetricsCollector) {
        this(context, buildInfoProvider, sentryFrameMetricsCollector, sentryAndroidOptions.getLogger(), sentryAndroidOptions.getProfilingTracesDirPath(), sentryAndroidOptions.isProfilingEnabled(), sentryAndroidOptions.getProfilingTracesHz(), sentryAndroidOptions.getExecutorService());
    }

    public AndroidTransactionProfiler(Context context, BuildInfoProvider buildInfoProvider, SentryFrameMetricsCollector sentryFrameMetricsCollector, ILogger iLogger, String str, boolean z, int i, ISentryExecutorService iSentryExecutorService) {
        this.isInitialized = false;
        this.transactionsCounter = 0;
        this.profiler = null;
        this.lock = new AutoClosableReentrantLock();
        this.context = (Context) Objects.requireNonNull(ContextUtils.getApplicationContext(context), "The application context is required");
        this.logger = (ILogger) Objects.requireNonNull(iLogger, "ILogger is required");
        this.frameMetricsCollector = (SentryFrameMetricsCollector) Objects.requireNonNull(sentryFrameMetricsCollector, "SentryFrameMetricsCollector is required");
        this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider, "The BuildInfoProvider is required.");
        this.profilingTracesDirPath = str;
        this.isProfilingEnabled = z;
        this.profilingTracesHz = i;
        this.executorService = (ISentryExecutorService) Objects.requireNonNull(iSentryExecutorService, "The ISentryExecutorService is required.");
        this.profileStartTimestamp = DateUtils.getCurrentDateTime();
    }

    private void init() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;
        if (!this.isProfilingEnabled) {
            this.logger.log(SentryLevel.INFO, "Profiling is disabled in options.", new Object[0]);
            return;
        }
        String str = this.profilingTracesDirPath;
        if (str == null) {
            this.logger.log(SentryLevel.WARNING, "Disabling profiling because no profiling traces dir path is defined in options.", new Object[0]);
        } else if (this.profilingTracesHz <= 0) {
            this.logger.log(SentryLevel.WARNING, "Disabling profiling because trace rate is set to %d", Integer.valueOf(this.profilingTracesHz));
        } else {
            this.profiler = new AndroidProfiler(str, ((int) TimeUnit.SECONDS.toMicros(1L)) / this.profilingTracesHz, this.frameMetricsCollector, this.executorService, this.logger);
        }
    }

    @Override // io.sentry.ITransactionProfiler
    public void start() {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            if (this.buildInfoProvider.getSdkInfoVersion() < 22) {
                if (acquire != null) {
                    acquire.close();
                    return;
                }
                return;
            }
            init();
            int i = this.transactionsCounter + 1;
            this.transactionsCounter = i;
            if (i == 1 && onFirstStart()) {
                this.logger.log(SentryLevel.DEBUG, "Profiler started.", new Object[0]);
            } else {
                this.transactionsCounter--;
                this.logger.log(SentryLevel.WARNING, "A profile is already running. This profile will be ignored.", new Object[0]);
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

    private boolean onFirstStart() {
        AndroidProfiler.ProfileStartData start;
        AndroidProfiler androidProfiler = this.profiler;
        if (androidProfiler == null || (start = androidProfiler.start()) == null) {
            return false;
        }
        this.profileStartNanos = start.startNanos;
        this.profileStartCpuMillis = start.startCpuMillis;
        this.profileStartTimestamp = start.startTimestamp;
        return true;
    }

    @Override // io.sentry.ITransactionProfiler
    public void bindTransaction(ITransaction iTransaction) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            if (this.transactionsCounter > 0 && this.currentProfilingTransactionData == null) {
                this.currentProfilingTransactionData = new ProfilingTransactionData(iTransaction, Long.valueOf(this.profileStartNanos), Long.valueOf(this.profileStartCpuMillis));
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

    @Override // io.sentry.ITransactionProfiler
    public ProfilingTraceData onTransactionFinish(ITransaction iTransaction, List<PerformanceCollectionData> list, SentryOptions sentryOptions) {
        ISentryLifecycleToken acquire = this.lock.acquire();
        try {
            ProfilingTraceData onTransactionFinish = onTransactionFinish(iTransaction.getName(), iTransaction.getEventId().toString(), iTransaction.getSpanContext().getTraceId().toString(), false, list, sentryOptions);
            if (acquire != null) {
                acquire.close();
            }
            return onTransactionFinish;
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

    /* JADX WARN: Removed duplicated region for block: B:62:0x017b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private io.sentry.ProfilingTraceData onTransactionFinish(java.lang.String r26, java.lang.String r27, java.lang.String r28, boolean r29, java.util.List<io.sentry.PerformanceCollectionData> r30, io.sentry.SentryOptions r31) {
        /*
            Method dump skipped, instructions count: 416
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.AndroidTransactionProfiler.onTransactionFinish(java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List, io.sentry.SentryOptions):io.sentry.ProfilingTraceData");
    }

    @Override // io.sentry.ITransactionProfiler
    public boolean isRunning() {
        return this.transactionsCounter != 0;
    }

    @Override // io.sentry.ITransactionProfiler
    public void close() {
        ProfilingTransactionData profilingTransactionData = this.currentProfilingTransactionData;
        if (profilingTransactionData != null) {
            onTransactionFinish(profilingTransactionData.getName(), this.currentProfilingTransactionData.getId(), this.currentProfilingTransactionData.getTraceId(), true, null, ScopesAdapter.getInstance().getOptions());
        } else {
            int i = this.transactionsCounter;
            if (i != 0) {
                this.transactionsCounter = i - 1;
            }
        }
        AndroidProfiler androidProfiler = this.profiler;
        if (androidProfiler != null) {
            androidProfiler.close();
        }
    }

    int getTransactionsCounter() {
        return this.transactionsCounter;
    }
}
