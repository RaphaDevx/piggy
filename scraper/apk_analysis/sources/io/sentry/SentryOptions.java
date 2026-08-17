package io.sentry;

import com.scandit.datacapture.frameworks.core.events.EventForResult;
import io.sentry.SentryFeedbackOptions;
import io.sentry.backpressure.IBackpressureMonitor;
import io.sentry.backpressure.NoOpBackpressureMonitor;
import io.sentry.cache.IEnvelopeCache;
import io.sentry.cache.PersistingScopeObserver;
import io.sentry.clientreport.ClientReportRecorder;
import io.sentry.clientreport.DiscardReason;
import io.sentry.clientreport.IClientReportRecorder;
import io.sentry.clientreport.NoOpClientReportRecorder;
import io.sentry.internal.debugmeta.IDebugMetaLoader;
import io.sentry.internal.debugmeta.NoOpDebugMetaLoader;
import io.sentry.internal.gestures.GestureTargetLocator;
import io.sentry.internal.modules.IModulesLoader;
import io.sentry.internal.modules.NoOpModulesLoader;
import io.sentry.internal.viewhierarchy.ViewHierarchyExporter;
import io.sentry.protocol.SdkVersion;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.transport.ITransportGate;
import io.sentry.transport.NoOpEnvelopeCache;
import io.sentry.transport.NoOpTransportGate;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.LazyEvaluator;
import io.sentry.util.LoadClass;
import io.sentry.util.Platform;
import io.sentry.util.SampleRateUtils;
import io.sentry.util.StringUtils;
import io.sentry.util.thread.IThreadChecker;
import io.sentry.util.thread.NoOpThreadChecker;
import java.io.File;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes2.dex */
public class SentryOptions {
    static final SentryLevel DEFAULT_DIAGNOSTIC_LEVEL = SentryLevel.DEBUG;
    private static final String DEFAULT_ENVIRONMENT = "production";
    public static final String DEFAULT_PROPAGATION_TARGETS = ".*";
    private boolean attachServerName;
    private boolean attachStacktrace;
    private boolean attachThreads;
    private IBackpressureMonitor backpressureMonitor;
    private BeforeBreadcrumbCallback beforeBreadcrumb;
    private BeforeEnvelopeCallback beforeEnvelopeCallback;
    private BeforeSendCallback beforeSend;
    private BeforeSendCallback beforeSendFeedback;
    private BeforeSendReplayCallback beforeSendReplay;
    private BeforeSendTransactionCallback beforeSendTransaction;
    private final Set<String> bundleIds;
    private String cacheDirPath;
    private boolean captureOpenTelemetryEvents;
    IClientReportRecorder clientReportRecorder;
    private CompositePerformanceCollector compositePerformanceCollector;
    private IConnectionStatusProvider connectionStatusProvider;
    private int connectionTimeoutMillis;
    private final List<String> contextTags;
    private IContinuousProfiler continuousProfiler;
    private Cron cron;
    private final LazyEvaluator<SentryDateProvider> dateProvider;
    private long deadlineTimeout;
    private boolean debug;
    private IDebugMetaLoader debugMetaLoader;
    private ScopeType defaultScopeType;
    private final List<String> defaultTracePropagationTargets;
    private SentryLevel diagnosticLevel;
    private String dist;
    private String distinctId;
    private DistributionOptions distribution;
    private IDistributionApi distributionController;
    private String dsn;
    private String dsnHash;
    private boolean enableAppStartProfiling;
    private boolean enableAutoSessionTracking;
    private boolean enableBackpressureHandling;
    private boolean enableDeduplication;
    private boolean enableExternalConfiguration;
    private boolean enablePrettySerializationOutput;
    private boolean enableScopePersistence;
    private boolean enableScreenTracking;
    private boolean enableShutdownHook;
    private boolean enableSpotlight;
    private boolean enableTimeToFullDisplayTracing;
    private boolean enableUncaughtExceptionHandler;
    private boolean enableUserInteractionBreadcrumbs;
    private boolean enableUserInteractionTracing;
    private boolean enabled;
    private IEnvelopeCache envelopeDiskCache;
    private final LazyEvaluator<IEnvelopeReader> envelopeReader;
    private String environment;
    private final List<EventProcessor> eventProcessors;
    private ISentryExecutorService executorService;
    private final ExperimentalOptions experimental;
    private ILogger fatalLogger;
    private SentryFeedbackOptions feedbackOptions;
    private long flushTimeoutMillis;
    private boolean forceInit;
    private FullyDisplayedReporter fullyDisplayedReporter;
    private final List<GestureTargetLocator> gestureTargetLocators;
    private Boolean globalHubMode;
    private Long idleTimeout;
    private List<FilterString> ignoredCheckIns;
    private List<FilterString> ignoredErrors;
    private final Set<Class<? extends Throwable>> ignoredExceptionsForType;
    private List<FilterString> ignoredSpanOrigins;
    private List<FilterString> ignoredTransactions;
    private final List<String> inAppExcludes;
    private final List<String> inAppIncludes;
    private InitPriority initPriority;
    private Instrumenter instrumenter;
    private final List<Integration> integrations;
    private volatile TracesSampler internalTracesSampler;
    protected final AutoClosableReentrantLock lock;
    private ILogger logger;
    private Logs logs;
    private long maxAttachmentSize;
    private int maxBreadcrumbs;
    private int maxCacheItems;
    private int maxDepth;
    private int maxQueueSize;
    private RequestSize maxRequestBodySize;
    private int maxSpans;
    private long maxTraceFileSize;
    private IModulesLoader modulesLoader;
    private final List<IScopeObserver> observers;
    private OnDiscardCallback onDiscard;
    private SentryOpenTelemetryMode openTelemetryMode;
    private final List<IOptionsObserver> optionsObservers;
    private final LazyEvaluator<Dsn> parsedDsn;
    private final List<IPerformanceCollector> performanceCollectors;
    private boolean printUncaughtStackTrace;
    private ProfileLifecycle profileLifecycle;
    private Double profileSessionSampleRate;
    private Double profilesSampleRate;
    private ProfilesSamplerCallback profilesSampler;
    private String profilingTracesDirPath;
    private int profilingTracesHz;
    private String proguardUuid;
    private boolean propagateTraceparent;
    private Proxy proxy;
    private int readTimeoutMillis;
    private String release;
    private ReplayController replayController;
    private Double sampleRate;
    private SdkVersion sdkVersion;
    private boolean sendClientReports;
    private boolean sendDefaultPii;
    private boolean sendModules;
    private String sentryClientName;
    private final LazyEvaluator<ISerializer> serializer;
    private String serverName;
    private long sessionFlushTimeoutMillis;
    private SentryReplayOptions sessionReplay;
    private long sessionTrackingIntervalMillis;
    private long shutdownTimeoutMillis;
    private ISocketTagger socketTagger;
    private ISpanFactory spanFactory;
    private String spotlightConnectionUrl;
    private SSLSocketFactory sslSocketFactory;
    private boolean startProfilerOnAppStart;
    private final Map<String, String> tags;
    private IThreadChecker threadChecker;
    private boolean traceOptionsRequests;
    private List<String> tracePropagationTargets;
    private boolean traceSampling;
    private Double tracesSampleRate;
    private TracesSamplerCallback tracesSampler;
    private ITransactionProfiler transactionProfiler;
    private ITransportFactory transportFactory;
    private ITransportGate transportGate;
    private IVersionDetector versionDetector;
    private final List<ViewHierarchyExporter> viewHierarchyExporters;

    public interface BeforeBreadcrumbCallback {
        Breadcrumb execute(Breadcrumb breadcrumb, Hint hint);
    }

    public interface BeforeEmitMetricCallback {
        boolean execute(String str, Map<String, String> map);
    }

    public interface BeforeEnvelopeCallback {
        void execute(SentryEnvelope sentryEnvelope, Hint hint);
    }

    public interface BeforeSendCallback {
        SentryEvent execute(SentryEvent sentryEvent, Hint hint);
    }

    public interface BeforeSendReplayCallback {
        SentryReplayEvent execute(SentryReplayEvent sentryReplayEvent, Hint hint);
    }

    public interface BeforeSendTransactionCallback {
        SentryTransaction execute(SentryTransaction sentryTransaction, Hint hint);
    }

    public static final class DistributionOptions {
        public String orgAuthToken = "";
        public String orgSlug = "";
        public String projectSlug = "";
        public String sentryBaseUrl = "https://sentry.io";
        public String buildConfiguration = null;
    }

    public interface OnDiscardCallback {
        void execute(DiscardReason discardReason, DataCategory dataCategory, Long l);
    }

    public interface ProfilesSamplerCallback {
        Double sample(SamplingContext samplingContext);
    }

    public enum RequestSize {
        NONE,
        SMALL,
        MEDIUM,
        ALWAYS
    }

    public interface TracesSamplerCallback {
        Double sample(SamplingContext samplingContext);
    }

    /* renamed from: lambda$new$0$io-sentry-SentryOptions, reason: not valid java name */
    /* synthetic */ Dsn m3824lambda$new$0$iosentrySentryOptions() {
        return new Dsn(this.dsn);
    }

    /* renamed from: lambda$new$1$io-sentry-SentryOptions, reason: not valid java name */
    /* synthetic */ ISerializer m3825lambda$new$1$iosentrySentryOptions() {
        return new JsonSerializer(this);
    }

    /* renamed from: lambda$new$2$io-sentry-SentryOptions, reason: not valid java name */
    /* synthetic */ IEnvelopeReader m3826lambda$new$2$iosentrySentryOptions() {
        return new EnvelopeReader(this.serializer.getValue());
    }

    static /* synthetic */ SentryDateProvider lambda$new$3() {
        return new SentryAutoDateProvider();
    }

    public void addEventProcessor(EventProcessor eventProcessor) {
        this.eventProcessors.add(eventProcessor);
    }

    public List<EventProcessor> getEventProcessors() {
        return this.eventProcessors;
    }

    public void addIntegration(Integration integration) {
        this.integrations.add(integration);
    }

    public List<Integration> getIntegrations() {
        return this.integrations;
    }

    public String getDsn() {
        return this.dsn;
    }

    Dsn retrieveParsedDsn() throws IllegalArgumentException {
        return this.parsedDsn.getValue();
    }

    public void setDsn(String str) {
        this.dsn = str;
        this.parsedDsn.resetValue();
        this.dsnHash = StringUtils.calculateStringHash(this.dsn, this.logger);
    }

    public boolean isDebug() {
        return this.debug;
    }

    public void setDebug(boolean z) {
        this.debug = z;
    }

    public ILogger getLogger() {
        return this.logger;
    }

    public void setLogger(ILogger iLogger) {
        this.logger = iLogger == null ? NoOpLogger.getInstance() : new DiagnosticLogger(this, iLogger);
    }

    public ILogger getFatalLogger() {
        return this.fatalLogger;
    }

    public void setFatalLogger(ILogger iLogger) {
        if (iLogger == null) {
            iLogger = NoOpLogger.getInstance();
        }
        this.fatalLogger = iLogger;
    }

    public SentryLevel getDiagnosticLevel() {
        return this.diagnosticLevel;
    }

    public void setDiagnosticLevel(SentryLevel sentryLevel) {
        if (sentryLevel == null) {
            sentryLevel = DEFAULT_DIAGNOSTIC_LEVEL;
        }
        this.diagnosticLevel = sentryLevel;
    }

    public ISerializer getSerializer() {
        return this.serializer.getValue();
    }

    public void setSerializer(ISerializer iSerializer) {
        LazyEvaluator<ISerializer> lazyEvaluator = this.serializer;
        if (iSerializer == null) {
            iSerializer = NoOpSerializer.getInstance();
        }
        lazyEvaluator.setValue(iSerializer);
    }

    public int getMaxDepth() {
        return this.maxDepth;
    }

    public void setMaxDepth(int i) {
        this.maxDepth = i;
    }

    public IEnvelopeReader getEnvelopeReader() {
        return this.envelopeReader.getValue();
    }

    public void setEnvelopeReader(IEnvelopeReader iEnvelopeReader) {
        LazyEvaluator<IEnvelopeReader> lazyEvaluator = this.envelopeReader;
        if (iEnvelopeReader == null) {
            iEnvelopeReader = NoOpEnvelopeReader.getInstance();
        }
        lazyEvaluator.setValue(iEnvelopeReader);
    }

    public long getShutdownTimeoutMillis() {
        return this.shutdownTimeoutMillis;
    }

    public void setShutdownTimeoutMillis(long j) {
        this.shutdownTimeoutMillis = j;
    }

    public String getSentryClientName() {
        return this.sentryClientName;
    }

    public void setSentryClientName(String str) {
        this.sentryClientName = str;
    }

    public BeforeSendCallback getBeforeSend() {
        return this.beforeSend;
    }

    public void setBeforeSend(BeforeSendCallback beforeSendCallback) {
        this.beforeSend = beforeSendCallback;
    }

    public BeforeSendTransactionCallback getBeforeSendTransaction() {
        return this.beforeSendTransaction;
    }

    public void setBeforeSendTransaction(BeforeSendTransactionCallback beforeSendTransactionCallback) {
        this.beforeSendTransaction = beforeSendTransactionCallback;
    }

    public BeforeSendCallback getBeforeSendFeedback() {
        return this.beforeSendFeedback;
    }

    public void setBeforeSendFeedback(BeforeSendCallback beforeSendCallback) {
        this.beforeSendFeedback = beforeSendCallback;
    }

    public BeforeSendReplayCallback getBeforeSendReplay() {
        return this.beforeSendReplay;
    }

    public void setBeforeSendReplay(BeforeSendReplayCallback beforeSendReplayCallback) {
        this.beforeSendReplay = beforeSendReplayCallback;
    }

    public BeforeBreadcrumbCallback getBeforeBreadcrumb() {
        return this.beforeBreadcrumb;
    }

    public void setBeforeBreadcrumb(BeforeBreadcrumbCallback beforeBreadcrumbCallback) {
        this.beforeBreadcrumb = beforeBreadcrumbCallback;
    }

    public OnDiscardCallback getOnDiscard() {
        return this.onDiscard;
    }

    public void setOnDiscard(OnDiscardCallback onDiscardCallback) {
        this.onDiscard = onDiscardCallback;
    }

    public String getCacheDirPath() {
        String str = this.cacheDirPath;
        if (str == null || str.isEmpty()) {
            return null;
        }
        return this.dsnHash != null ? new File(this.cacheDirPath, this.dsnHash).getAbsolutePath() : this.cacheDirPath;
    }

    String getCacheDirPathWithoutDsn() {
        String str = this.cacheDirPath;
        if (str == null || str.isEmpty()) {
            return null;
        }
        return this.cacheDirPath;
    }

    public String getOutboxPath() {
        String cacheDirPath = getCacheDirPath();
        if (cacheDirPath == null) {
            return null;
        }
        return new File(cacheDirPath, "outbox").getAbsolutePath();
    }

    public void setCacheDirPath(String str) {
        this.cacheDirPath = str;
    }

    public int getMaxBreadcrumbs() {
        return this.maxBreadcrumbs;
    }

    public void setMaxBreadcrumbs(int i) {
        this.maxBreadcrumbs = i;
    }

    public String getRelease() {
        return this.release;
    }

    public void setRelease(String str) {
        this.release = str;
    }

    public String getEnvironment() {
        String str = this.environment;
        return str != null ? str : "production";
    }

    public void setEnvironment(String str) {
        this.environment = str;
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public Double getSampleRate() {
        return this.sampleRate;
    }

    public void setSampleRate(Double d) {
        if (!SampleRateUtils.isValidSampleRate(d)) {
            throw new IllegalArgumentException("The value " + d + " is not valid. Use null to disable or values >= 0.0 and <= 1.0.");
        }
        this.sampleRate = d;
    }

    public Double getTracesSampleRate() {
        return this.tracesSampleRate;
    }

    public void setTracesSampleRate(Double d) {
        if (!SampleRateUtils.isValidTracesSampleRate(d)) {
            throw new IllegalArgumentException("The value " + d + " is not valid. Use null to disable or values between 0.0 and 1.0.");
        }
        this.tracesSampleRate = d;
    }

    public TracesSamplerCallback getTracesSampler() {
        return this.tracesSampler;
    }

    public void setTracesSampler(TracesSamplerCallback tracesSamplerCallback) {
        this.tracesSampler = tracesSamplerCallback;
    }

    public TracesSampler getInternalTracesSampler() {
        if (this.internalTracesSampler == null) {
            ISentryLifecycleToken acquire = this.lock.acquire();
            try {
                if (this.internalTracesSampler == null) {
                    this.internalTracesSampler = new TracesSampler(this);
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
        return this.internalTracesSampler;
    }

    public List<String> getInAppExcludes() {
        return this.inAppExcludes;
    }

    public void addInAppExclude(String str) {
        this.inAppExcludes.add(str);
    }

    public List<String> getInAppIncludes() {
        return this.inAppIncludes;
    }

    public void addInAppInclude(String str) {
        this.inAppIncludes.add(str);
    }

    public ITransportFactory getTransportFactory() {
        return this.transportFactory;
    }

    public void setTransportFactory(ITransportFactory iTransportFactory) {
        if (iTransportFactory == null) {
            iTransportFactory = NoOpTransportFactory.getInstance();
        }
        this.transportFactory = iTransportFactory;
    }

    public String getDist() {
        return this.dist;
    }

    public void setDist(String str) {
        this.dist = str;
    }

    public ITransportGate getTransportGate() {
        return this.transportGate;
    }

    public void setTransportGate(ITransportGate iTransportGate) {
        if (iTransportGate == null) {
            iTransportGate = NoOpTransportGate.getInstance();
        }
        this.transportGate = iTransportGate;
    }

    public boolean isAttachStacktrace() {
        return this.attachStacktrace;
    }

    public void setAttachStacktrace(boolean z) {
        this.attachStacktrace = z;
    }

    public boolean isAttachThreads() {
        return this.attachThreads;
    }

    public void setAttachThreads(boolean z) {
        this.attachThreads = z;
    }

    public boolean isEnableAutoSessionTracking() {
        return this.enableAutoSessionTracking;
    }

    public void setEnableAutoSessionTracking(boolean z) {
        this.enableAutoSessionTracking = z;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String str) {
        this.serverName = str;
    }

    public boolean isAttachServerName() {
        return this.attachServerName;
    }

    public void setAttachServerName(boolean z) {
        this.attachServerName = z;
    }

    public long getSessionTrackingIntervalMillis() {
        return this.sessionTrackingIntervalMillis;
    }

    public void setSessionTrackingIntervalMillis(long j) {
        this.sessionTrackingIntervalMillis = j;
    }

    public String getDistinctId() {
        return this.distinctId;
    }

    public void setDistinctId(String str) {
        this.distinctId = str;
    }

    public long getFlushTimeoutMillis() {
        return this.flushTimeoutMillis;
    }

    public void setFlushTimeoutMillis(long j) {
        this.flushTimeoutMillis = j;
    }

    public boolean isEnableUncaughtExceptionHandler() {
        return this.enableUncaughtExceptionHandler;
    }

    public void setEnableUncaughtExceptionHandler(boolean z) {
        this.enableUncaughtExceptionHandler = z;
    }

    public boolean isPrintUncaughtStackTrace() {
        return this.printUncaughtStackTrace;
    }

    public void setPrintUncaughtStackTrace(boolean z) {
        this.printUncaughtStackTrace = z;
    }

    public ISentryExecutorService getExecutorService() {
        return this.executorService;
    }

    public void setExecutorService(ISentryExecutorService iSentryExecutorService) {
        if (iSentryExecutorService != null) {
            this.executorService = iSentryExecutorService;
        }
    }

    public int getConnectionTimeoutMillis() {
        return this.connectionTimeoutMillis;
    }

    public void setConnectionTimeoutMillis(int i) {
        this.connectionTimeoutMillis = i;
    }

    public int getReadTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    public void setReadTimeoutMillis(int i) {
        this.readTimeoutMillis = i;
    }

    public IEnvelopeCache getEnvelopeDiskCache() {
        return this.envelopeDiskCache;
    }

    public void setEnvelopeDiskCache(IEnvelopeCache iEnvelopeCache) {
        if (iEnvelopeCache == null) {
            iEnvelopeCache = NoOpEnvelopeCache.getInstance();
        }
        this.envelopeDiskCache = iEnvelopeCache;
    }

    public int getMaxQueueSize() {
        return this.maxQueueSize;
    }

    public void setMaxQueueSize(int i) {
        if (i > 0) {
            this.maxQueueSize = i;
        }
    }

    public SdkVersion getSdkVersion() {
        return this.sdkVersion;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    public void setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.sslSocketFactory = sSLSocketFactory;
    }

    public void setSdkVersion(SdkVersion sdkVersion) {
        SdkVersion sdkVersion2 = getSessionReplay().getSdkVersion();
        SdkVersion sdkVersion3 = this.sdkVersion;
        if (sdkVersion3 != null && sdkVersion2 != null && sdkVersion3.equals(sdkVersion2)) {
            getSessionReplay().setSdkVersion(sdkVersion);
        }
        this.sdkVersion = sdkVersion;
    }

    public boolean isSendDefaultPii() {
        return this.sendDefaultPii;
    }

    public void setSendDefaultPii(boolean z) {
        this.sendDefaultPii = z;
    }

    public void addScopeObserver(IScopeObserver iScopeObserver) {
        this.observers.add(iScopeObserver);
    }

    public List<IScopeObserver> getScopeObservers() {
        return this.observers;
    }

    public PersistingScopeObserver findPersistingScopeObserver() {
        for (IScopeObserver iScopeObserver : this.observers) {
            if (iScopeObserver instanceof PersistingScopeObserver) {
                return (PersistingScopeObserver) iScopeObserver;
            }
        }
        return null;
    }

    public void addOptionsObserver(IOptionsObserver iOptionsObserver) {
        this.optionsObservers.add(iOptionsObserver);
    }

    public List<IOptionsObserver> getOptionsObservers() {
        return this.optionsObservers;
    }

    public boolean isEnableExternalConfiguration() {
        return this.enableExternalConfiguration;
    }

    public void setEnableExternalConfiguration(boolean z) {
        this.enableExternalConfiguration = z;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public void setTag(String str, String str2) {
        if (str == null) {
            return;
        }
        if (str2 == null) {
            this.tags.remove(str);
        } else {
            this.tags.put(str, str2);
        }
    }

    public long getMaxAttachmentSize() {
        return this.maxAttachmentSize;
    }

    public void setMaxAttachmentSize(long j) {
        this.maxAttachmentSize = j;
    }

    public boolean isEnableDeduplication() {
        return this.enableDeduplication;
    }

    public void setEnableDeduplication(boolean z) {
        this.enableDeduplication = z;
    }

    public boolean isTracingEnabled() {
        return (getTracesSampleRate() == null && getTracesSampler() == null) ? false : true;
    }

    public Set<Class<? extends Throwable>> getIgnoredExceptionsForType() {
        return this.ignoredExceptionsForType;
    }

    public void addIgnoredExceptionForType(Class<? extends Throwable> cls) {
        this.ignoredExceptionsForType.add(cls);
    }

    boolean containsIgnoredExceptionForType(Throwable th) {
        return this.ignoredExceptionsForType.contains(th.getClass());
    }

    public List<FilterString> getIgnoredErrors() {
        return this.ignoredErrors;
    }

    public void setIgnoredErrors(List<String> list) {
        if (list == null) {
            this.ignoredErrors = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                arrayList.add(new FilterString(str));
            }
        }
        this.ignoredErrors = arrayList;
    }

    public void addIgnoredError(String str) {
        if (this.ignoredErrors == null) {
            this.ignoredErrors = new ArrayList();
        }
        this.ignoredErrors.add(new FilterString(str));
    }

    public int getMaxSpans() {
        return this.maxSpans;
    }

    public void setMaxSpans(int i) {
        this.maxSpans = i;
    }

    public boolean isEnableShutdownHook() {
        return this.enableShutdownHook;
    }

    public void setEnableShutdownHook(boolean z) {
        this.enableShutdownHook = z;
    }

    public int getMaxCacheItems() {
        return this.maxCacheItems;
    }

    public void setMaxCacheItems(int i) {
        this.maxCacheItems = i;
    }

    public RequestSize getMaxRequestBodySize() {
        return this.maxRequestBodySize;
    }

    public void setMaxRequestBodySize(RequestSize requestSize) {
        this.maxRequestBodySize = requestSize;
    }

    public boolean isTraceSampling() {
        return this.traceSampling;
    }

    @Deprecated
    public void setTraceSampling(boolean z) {
        this.traceSampling = z;
    }

    public long getMaxTraceFileSize() {
        return this.maxTraceFileSize;
    }

    public void setMaxTraceFileSize(long j) {
        this.maxTraceFileSize = j;
    }

    public ITransactionProfiler getTransactionProfiler() {
        return this.transactionProfiler;
    }

    public void setTransactionProfiler(ITransactionProfiler iTransactionProfiler) {
        if (this.transactionProfiler != NoOpTransactionProfiler.getInstance() || iTransactionProfiler == null) {
            return;
        }
        this.transactionProfiler = iTransactionProfiler;
    }

    public IContinuousProfiler getContinuousProfiler() {
        return this.continuousProfiler;
    }

    public void setContinuousProfiler(IContinuousProfiler iContinuousProfiler) {
        if (this.continuousProfiler != NoOpContinuousProfiler.getInstance() || iContinuousProfiler == null) {
            return;
        }
        this.continuousProfiler = iContinuousProfiler;
    }

    public boolean isProfilingEnabled() {
        Double d = this.profilesSampleRate;
        return (d != null && d.doubleValue() > 0.0d) || this.profilesSampler != null;
    }

    public boolean isContinuousProfilingEnabled() {
        Double d;
        return this.profilesSampleRate == null && this.profilesSampler == null && (d = this.profileSessionSampleRate) != null && d.doubleValue() > 0.0d;
    }

    public ProfilesSamplerCallback getProfilesSampler() {
        return this.profilesSampler;
    }

    public void setProfilesSampler(ProfilesSamplerCallback profilesSamplerCallback) {
        this.profilesSampler = profilesSamplerCallback;
    }

    public Double getProfilesSampleRate() {
        return this.profilesSampleRate;
    }

    public void setProfilesSampleRate(Double d) {
        if (!SampleRateUtils.isValidProfilesSampleRate(d)) {
            throw new IllegalArgumentException("The value " + d + " is not valid. Use null to disable or values between 0.0 and 1.0.");
        }
        this.profilesSampleRate = d;
    }

    public Double getProfileSessionSampleRate() {
        return this.profileSessionSampleRate;
    }

    public void setProfileSessionSampleRate(Double d) {
        if (!SampleRateUtils.isValidContinuousProfilesSampleRate(d)) {
            throw new IllegalArgumentException("The value " + d + " is not valid. Use values between 0.0 and 1.0.");
        }
        this.profileSessionSampleRate = d;
    }

    public ProfileLifecycle getProfileLifecycle() {
        return this.profileLifecycle;
    }

    public void setProfileLifecycle(ProfileLifecycle profileLifecycle) {
        this.profileLifecycle = profileLifecycle;
        if (profileLifecycle != ProfileLifecycle.TRACE || isTracingEnabled()) {
            return;
        }
        this.logger.log(SentryLevel.WARNING, "Profiling lifecycle is set to TRACE but tracing is disabled. Profiling will not be started automatically.", new Object[0]);
    }

    public boolean isStartProfilerOnAppStart() {
        return this.startProfilerOnAppStart;
    }

    public void setStartProfilerOnAppStart(boolean z) {
        this.startProfilerOnAppStart = z;
    }

    public long getDeadlineTimeout() {
        return this.deadlineTimeout;
    }

    public void setDeadlineTimeout(long j) {
        this.deadlineTimeout = j;
    }

    public String getProfilingTracesDirPath() {
        String str = this.profilingTracesDirPath;
        if (str != null && !str.isEmpty()) {
            if (this.dsnHash != null) {
                return new File(this.profilingTracesDirPath, this.dsnHash).getAbsolutePath();
            }
            return this.profilingTracesDirPath;
        }
        String cacheDirPath = getCacheDirPath();
        if (cacheDirPath == null) {
            return null;
        }
        return new File(cacheDirPath, "profiling_traces").getAbsolutePath();
    }

    public void setProfilingTracesDirPath(String str) {
        this.profilingTracesDirPath = str;
    }

    public List<String> getTracePropagationTargets() {
        List<String> list = this.tracePropagationTargets;
        return list == null ? this.defaultTracePropagationTargets : list;
    }

    public void setTracePropagationTargets(List<String> list) {
        if (list == null) {
            this.tracePropagationTargets = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!str.isEmpty()) {
                arrayList.add(str);
            }
        }
        this.tracePropagationTargets = arrayList;
    }

    public boolean isPropagateTraceparent() {
        return this.propagateTraceparent;
    }

    public void setPropagateTraceparent(boolean z) {
        this.propagateTraceparent = z;
    }

    public String getProguardUuid() {
        return this.proguardUuid;
    }

    public void setProguardUuid(String str) {
        this.proguardUuid = str;
    }

    public void addBundleId(String str) {
        if (str != null) {
            String trim = str.trim();
            if (trim.isEmpty()) {
                return;
            }
            this.bundleIds.add(trim);
        }
    }

    public Set<String> getBundleIds() {
        return this.bundleIds;
    }

    public List<String> getContextTags() {
        return this.contextTags;
    }

    public void addContextTag(String str) {
        this.contextTags.add(str);
    }

    public Long getIdleTimeout() {
        return this.idleTimeout;
    }

    public void setIdleTimeout(Long l) {
        this.idleTimeout = l;
    }

    public boolean isSendClientReports() {
        return this.sendClientReports;
    }

    public void setSendClientReports(boolean z) {
        this.sendClientReports = z;
        if (z) {
            this.clientReportRecorder = new ClientReportRecorder(this);
        } else {
            this.clientReportRecorder = new NoOpClientReportRecorder();
        }
    }

    public boolean isEnableUserInteractionTracing() {
        return this.enableUserInteractionTracing;
    }

    public void setEnableUserInteractionTracing(boolean z) {
        this.enableUserInteractionTracing = z;
    }

    public boolean isEnableUserInteractionBreadcrumbs() {
        return this.enableUserInteractionBreadcrumbs;
    }

    public void setEnableUserInteractionBreadcrumbs(boolean z) {
        this.enableUserInteractionBreadcrumbs = z;
    }

    @Deprecated
    public void setInstrumenter(Instrumenter instrumenter) {
        this.instrumenter = instrumenter;
    }

    public Instrumenter getInstrumenter() {
        return this.instrumenter;
    }

    public IClientReportRecorder getClientReportRecorder() {
        return this.clientReportRecorder;
    }

    public IModulesLoader getModulesLoader() {
        return this.modulesLoader;
    }

    public void setModulesLoader(IModulesLoader iModulesLoader) {
        if (iModulesLoader == null) {
            iModulesLoader = NoOpModulesLoader.getInstance();
        }
        this.modulesLoader = iModulesLoader;
    }

    public IDebugMetaLoader getDebugMetaLoader() {
        return this.debugMetaLoader;
    }

    public void setDebugMetaLoader(IDebugMetaLoader iDebugMetaLoader) {
        if (iDebugMetaLoader == null) {
            iDebugMetaLoader = NoOpDebugMetaLoader.getInstance();
        }
        this.debugMetaLoader = iDebugMetaLoader;
    }

    public List<GestureTargetLocator> getGestureTargetLocators() {
        return this.gestureTargetLocators;
    }

    public void setGestureTargetLocators(List<GestureTargetLocator> list) {
        this.gestureTargetLocators.clear();
        this.gestureTargetLocators.addAll(list);
    }

    public final List<ViewHierarchyExporter> getViewHierarchyExporters() {
        return this.viewHierarchyExporters;
    }

    public void setViewHierarchyExporters(List<ViewHierarchyExporter> list) {
        this.viewHierarchyExporters.clear();
        this.viewHierarchyExporters.addAll(list);
    }

    public IThreadChecker getThreadChecker() {
        return this.threadChecker;
    }

    public void setThreadChecker(IThreadChecker iThreadChecker) {
        this.threadChecker = iThreadChecker;
    }

    public CompositePerformanceCollector getCompositePerformanceCollector() {
        return this.compositePerformanceCollector;
    }

    public void setCompositePerformanceCollector(CompositePerformanceCollector compositePerformanceCollector) {
        this.compositePerformanceCollector = compositePerformanceCollector;
    }

    public boolean isEnableTimeToFullDisplayTracing() {
        return this.enableTimeToFullDisplayTracing;
    }

    public void setEnableTimeToFullDisplayTracing(boolean z) {
        this.enableTimeToFullDisplayTracing = z;
    }

    public FullyDisplayedReporter getFullyDisplayedReporter() {
        return this.fullyDisplayedReporter;
    }

    public void setFullyDisplayedReporter(FullyDisplayedReporter fullyDisplayedReporter) {
        this.fullyDisplayedReporter = fullyDisplayedReporter;
    }

    public boolean isTraceOptionsRequests() {
        return this.traceOptionsRequests;
    }

    public void setTraceOptionsRequests(boolean z) {
        this.traceOptionsRequests = z;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public boolean isEnablePrettySerializationOutput() {
        return this.enablePrettySerializationOutput;
    }

    public boolean isSendModules() {
        return this.sendModules;
    }

    public void setEnablePrettySerializationOutput(boolean z) {
        this.enablePrettySerializationOutput = z;
    }

    public boolean isEnableAppStartProfiling() {
        return (isProfilingEnabled() || isContinuousProfilingEnabled()) && this.enableAppStartProfiling;
    }

    public void setEnableAppStartProfiling(boolean z) {
        this.enableAppStartProfiling = z;
    }

    public void setSendModules(boolean z) {
        this.sendModules = z;
    }

    public List<FilterString> getIgnoredSpanOrigins() {
        return this.ignoredSpanOrigins;
    }

    public void addIgnoredSpanOrigin(String str) {
        if (this.ignoredSpanOrigins == null) {
            this.ignoredSpanOrigins = new ArrayList();
        }
        this.ignoredSpanOrigins.add(new FilterString(str));
    }

    public void setIgnoredSpanOrigins(List<String> list) {
        if (list == null) {
            this.ignoredSpanOrigins = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                arrayList.add(new FilterString(str));
            }
        }
        this.ignoredSpanOrigins = arrayList;
    }

    public List<FilterString> getIgnoredCheckIns() {
        return this.ignoredCheckIns;
    }

    public void addIgnoredCheckIn(String str) {
        if (this.ignoredCheckIns == null) {
            this.ignoredCheckIns = new ArrayList();
        }
        this.ignoredCheckIns.add(new FilterString(str));
    }

    public void setIgnoredCheckIns(List<String> list) {
        if (list == null) {
            this.ignoredCheckIns = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!str.isEmpty()) {
                arrayList.add(new FilterString(str));
            }
        }
        this.ignoredCheckIns = arrayList;
    }

    public List<FilterString> getIgnoredTransactions() {
        return this.ignoredTransactions;
    }

    public void addIgnoredTransaction(String str) {
        if (this.ignoredTransactions == null) {
            this.ignoredTransactions = new ArrayList();
        }
        this.ignoredTransactions.add(new FilterString(str));
    }

    public void setIgnoredTransactions(List<String> list) {
        if (list == null) {
            this.ignoredTransactions = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                arrayList.add(new FilterString(str));
            }
        }
        this.ignoredTransactions = arrayList;
    }

    public SentryDateProvider getDateProvider() {
        return this.dateProvider.getValue();
    }

    public void setDateProvider(SentryDateProvider sentryDateProvider) {
        this.dateProvider.setValue(sentryDateProvider);
    }

    public void addPerformanceCollector(IPerformanceCollector iPerformanceCollector) {
        this.performanceCollectors.add(iPerformanceCollector);
    }

    public List<IPerformanceCollector> getPerformanceCollectors() {
        return this.performanceCollectors;
    }

    public IConnectionStatusProvider getConnectionStatusProvider() {
        return this.connectionStatusProvider;
    }

    public void setConnectionStatusProvider(IConnectionStatusProvider iConnectionStatusProvider) {
        this.connectionStatusProvider = iConnectionStatusProvider;
    }

    public IBackpressureMonitor getBackpressureMonitor() {
        return this.backpressureMonitor;
    }

    public void setBackpressureMonitor(IBackpressureMonitor iBackpressureMonitor) {
        this.backpressureMonitor = iBackpressureMonitor;
    }

    public void setEnableBackpressureHandling(boolean z) {
        this.enableBackpressureHandling = z;
    }

    public IVersionDetector getVersionDetector() {
        return this.versionDetector;
    }

    public void setVersionDetector(IVersionDetector iVersionDetector) {
        this.versionDetector = iVersionDetector;
    }

    public int getProfilingTracesHz() {
        return this.profilingTracesHz;
    }

    public void setProfilingTracesHz(int i) {
        this.profilingTracesHz = i;
    }

    public boolean isEnableBackpressureHandling() {
        return this.enableBackpressureHandling;
    }

    public long getSessionFlushTimeoutMillis() {
        return this.sessionFlushTimeoutMillis;
    }

    public void setSessionFlushTimeoutMillis(long j) {
        this.sessionFlushTimeoutMillis = j;
    }

    public BeforeEnvelopeCallback getBeforeEnvelopeCallback() {
        return this.beforeEnvelopeCallback;
    }

    public void setBeforeEnvelopeCallback(BeforeEnvelopeCallback beforeEnvelopeCallback) {
        this.beforeEnvelopeCallback = beforeEnvelopeCallback;
    }

    public String getSpotlightConnectionUrl() {
        return this.spotlightConnectionUrl;
    }

    public void setSpotlightConnectionUrl(String str) {
        this.spotlightConnectionUrl = str;
    }

    public boolean isEnableSpotlight() {
        return this.enableSpotlight;
    }

    public void setEnableSpotlight(boolean z) {
        this.enableSpotlight = z;
    }

    public boolean isEnableScopePersistence() {
        return this.enableScopePersistence;
    }

    public void setEnableScopePersistence(boolean z) {
        this.enableScopePersistence = z;
    }

    public Cron getCron() {
        return this.cron;
    }

    public void setCron(Cron cron) {
        this.cron = cron;
    }

    public ExperimentalOptions getExperimental() {
        return this.experimental;
    }

    public ReplayController getReplayController() {
        return this.replayController;
    }

    public void setReplayController(ReplayController replayController) {
        if (replayController == null) {
            replayController = NoOpReplayController.getInstance();
        }
        this.replayController = replayController;
    }

    public IDistributionApi getDistributionController() {
        return this.distributionController;
    }

    public void setDistributionController(IDistributionApi iDistributionApi) {
        if (iDistributionApi == null) {
            iDistributionApi = NoOpDistributionApi.getInstance();
        }
        this.distributionController = iDistributionApi;
    }

    public boolean isEnableScreenTracking() {
        return this.enableScreenTracking;
    }

    public void setEnableScreenTracking(boolean z) {
        this.enableScreenTracking = z;
    }

    public void setDefaultScopeType(ScopeType scopeType) {
        this.defaultScopeType = scopeType;
    }

    public ScopeType getDefaultScopeType() {
        return this.defaultScopeType;
    }

    public void setInitPriority(InitPriority initPriority) {
        this.initPriority = initPriority;
    }

    public InitPriority getInitPriority() {
        return this.initPriority;
    }

    public void setForceInit(boolean z) {
        this.forceInit = z;
    }

    public boolean isForceInit() {
        return this.forceInit;
    }

    public void setGlobalHubMode(Boolean bool) {
        this.globalHubMode = bool;
    }

    public Boolean isGlobalHubMode() {
        return this.globalHubMode;
    }

    public void setOpenTelemetryMode(SentryOpenTelemetryMode sentryOpenTelemetryMode) {
        this.openTelemetryMode = sentryOpenTelemetryMode;
    }

    public SentryOpenTelemetryMode getOpenTelemetryMode() {
        return this.openTelemetryMode;
    }

    public SentryReplayOptions getSessionReplay() {
        return this.sessionReplay;
    }

    public void setSessionReplay(SentryReplayOptions sentryReplayOptions) {
        this.sessionReplay = sentryReplayOptions;
    }

    public SentryFeedbackOptions getFeedbackOptions() {
        return this.feedbackOptions;
    }

    public void setFeedbackOptions(SentryFeedbackOptions sentryFeedbackOptions) {
        this.feedbackOptions = sentryFeedbackOptions;
    }

    public void setCaptureOpenTelemetryEvents(boolean z) {
        this.captureOpenTelemetryEvents = z;
    }

    public boolean isCaptureOpenTelemetryEvents() {
        return this.captureOpenTelemetryEvents;
    }

    public ISocketTagger getSocketTagger() {
        return this.socketTagger;
    }

    public void setSocketTagger(ISocketTagger iSocketTagger) {
        if (iSocketTagger == null) {
            iSocketTagger = NoOpSocketTagger.getInstance();
        }
        this.socketTagger = iSocketTagger;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loadLazyFields() {
        getSerializer();
        retrieveParsedDsn();
        getEnvelopeReader();
        getDateProvider();
    }

    public static SentryOptions empty() {
        return new SentryOptions(true);
    }

    public SentryOptions() {
        this(false);
    }

    private SentryOptions(boolean z) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        this.eventProcessors = copyOnWriteArrayList;
        this.ignoredExceptionsForType = new CopyOnWriteArraySet();
        this.ignoredErrors = null;
        CopyOnWriteArrayList copyOnWriteArrayList2 = new CopyOnWriteArrayList();
        this.integrations = copyOnWriteArrayList2;
        this.bundleIds = new CopyOnWriteArraySet();
        this.parsedDsn = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.SentryOptions$$ExternalSyntheticLambda0
            @Override // io.sentry.util.LazyEvaluator.Evaluator
            public final Object evaluate() {
                return SentryOptions.this.m3824lambda$new$0$iosentrySentryOptions();
            }
        });
        this.shutdownTimeoutMillis = EventForResult.DEFAULT_TIMEOUT_MILLIS;
        this.flushTimeoutMillis = 15000L;
        this.sessionFlushTimeoutMillis = 15000L;
        this.logger = NoOpLogger.getInstance();
        this.fatalLogger = NoOpLogger.getInstance();
        this.diagnosticLevel = DEFAULT_DIAGNOSTIC_LEVEL;
        this.serializer = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.SentryOptions$$ExternalSyntheticLambda1
            @Override // io.sentry.util.LazyEvaluator.Evaluator
            public final Object evaluate() {
                return SentryOptions.this.m3825lambda$new$1$iosentrySentryOptions();
            }
        });
        this.envelopeReader = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.SentryOptions$$ExternalSyntheticLambda2
            @Override // io.sentry.util.LazyEvaluator.Evaluator
            public final Object evaluate() {
                return SentryOptions.this.m3826lambda$new$2$iosentrySentryOptions();
            }
        });
        this.maxDepth = 100;
        this.maxCacheItems = 30;
        this.maxQueueSize = 30;
        this.maxBreadcrumbs = 100;
        this.inAppExcludes = new CopyOnWriteArrayList();
        this.inAppIncludes = new CopyOnWriteArrayList();
        this.transportFactory = NoOpTransportFactory.getInstance();
        this.transportGate = NoOpTransportGate.getInstance();
        this.attachStacktrace = true;
        this.enableAutoSessionTracking = true;
        this.sessionTrackingIntervalMillis = 30000L;
        this.attachServerName = true;
        this.enableUncaughtExceptionHandler = true;
        this.printUncaughtStackTrace = false;
        this.executorService = NoOpSentryExecutorService.getInstance();
        this.connectionTimeoutMillis = 30000;
        this.readTimeoutMillis = 30000;
        this.envelopeDiskCache = NoOpEnvelopeCache.getInstance();
        this.sendDefaultPii = false;
        this.observers = new CopyOnWriteArrayList();
        this.optionsObservers = new CopyOnWriteArrayList();
        this.tags = new ConcurrentHashMap();
        this.maxAttachmentSize = 20971520L;
        this.enableDeduplication = true;
        this.maxSpans = 1000;
        this.enableShutdownHook = true;
        this.maxRequestBodySize = RequestSize.NONE;
        this.traceSampling = true;
        this.maxTraceFileSize = 5242880L;
        this.transactionProfiler = NoOpTransactionProfiler.getInstance();
        this.continuousProfiler = NoOpContinuousProfiler.getInstance();
        this.tracePropagationTargets = null;
        this.defaultTracePropagationTargets = Collections.singletonList(DEFAULT_PROPAGATION_TARGETS);
        this.propagateTraceparent = false;
        this.idleTimeout = 3000L;
        this.contextTags = new CopyOnWriteArrayList();
        this.sendClientReports = true;
        this.clientReportRecorder = new ClientReportRecorder(this);
        this.modulesLoader = NoOpModulesLoader.getInstance();
        this.debugMetaLoader = NoOpDebugMetaLoader.getInstance();
        this.enableUserInteractionTracing = false;
        this.enableUserInteractionBreadcrumbs = true;
        this.instrumenter = Instrumenter.SENTRY;
        this.gestureTargetLocators = new ArrayList();
        this.viewHierarchyExporters = new ArrayList();
        this.threadChecker = NoOpThreadChecker.getInstance();
        this.traceOptionsRequests = true;
        this.dateProvider = new LazyEvaluator<>(new LazyEvaluator.Evaluator() { // from class: io.sentry.SentryOptions$$ExternalSyntheticLambda3
            @Override // io.sentry.util.LazyEvaluator.Evaluator
            public final Object evaluate() {
                return SentryOptions.lambda$new$3();
            }
        });
        this.performanceCollectors = new ArrayList();
        this.compositePerformanceCollector = NoOpCompositePerformanceCollector.getInstance();
        this.enableTimeToFullDisplayTracing = false;
        this.fullyDisplayedReporter = FullyDisplayedReporter.getInstance();
        this.connectionStatusProvider = new NoOpConnectionStatusProvider();
        this.enabled = true;
        this.enablePrettySerializationOutput = true;
        this.sendModules = true;
        this.enableSpotlight = false;
        this.enableScopePersistence = true;
        this.ignoredCheckIns = null;
        this.ignoredSpanOrigins = null;
        this.ignoredTransactions = null;
        this.backpressureMonitor = NoOpBackpressureMonitor.getInstance();
        this.enableBackpressureHandling = true;
        this.enableAppStartProfiling = false;
        this.spanFactory = NoOpSpanFactory.getInstance();
        this.profilingTracesHz = 101;
        this.cron = null;
        this.replayController = NoOpReplayController.getInstance();
        this.distributionController = NoOpDistributionApi.getInstance();
        this.enableScreenTracking = true;
        this.defaultScopeType = ScopeType.ISOLATION;
        this.initPriority = InitPriority.MEDIUM;
        this.forceInit = false;
        this.globalHubMode = null;
        this.lock = new AutoClosableReentrantLock();
        this.openTelemetryMode = SentryOpenTelemetryMode.AUTO;
        this.captureOpenTelemetryEvents = false;
        this.versionDetector = NoopVersionDetector.getInstance();
        this.profileLifecycle = ProfileLifecycle.MANUAL;
        this.startProfilerOnAppStart = false;
        this.deadlineTimeout = 30000L;
        this.logs = new Logs();
        this.socketTagger = NoOpSocketTagger.getInstance();
        this.distribution = new DistributionOptions();
        SdkVersion createSdkVersion = createSdkVersion();
        this.experimental = new ExperimentalOptions(z, createSdkVersion);
        this.sessionReplay = new SentryReplayOptions(z, createSdkVersion);
        this.feedbackOptions = new SentryFeedbackOptions(new SentryFeedbackOptions.IDialogHandler() { // from class: io.sentry.SentryOptions$$ExternalSyntheticLambda4
            @Override // io.sentry.SentryFeedbackOptions.IDialogHandler
            public final void showDialog(SentryId sentryId, SentryFeedbackOptions.OptionsConfigurator optionsConfigurator) {
                SentryOptions.this.m3827lambda$new$4$iosentrySentryOptions(sentryId, optionsConfigurator);
            }
        });
        if (z) {
            return;
        }
        setSpanFactory(SpanFactoryFactory.create(new LoadClass(), NoOpLogger.getInstance()));
        SentryExecutorService sentryExecutorService = new SentryExecutorService(this);
        this.executorService = sentryExecutorService;
        sentryExecutorService.prewarm();
        copyOnWriteArrayList2.add(new UncaughtExceptionHandlerIntegration());
        copyOnWriteArrayList2.add(new ShutdownHookIntegration());
        copyOnWriteArrayList2.add(new SpotlightIntegration());
        copyOnWriteArrayList.add(new MainEventProcessor(this));
        copyOnWriteArrayList.add(new DuplicateEventDetectionEventProcessor(this));
        if (Platform.isJvm()) {
            copyOnWriteArrayList.add(new SentryRuntimeEventProcessor());
        }
        setSentryClientName("sentry.java/8.23.0");
        setSdkVersion(createSdkVersion);
        addPackageInfo();
    }

    /* renamed from: lambda$new$4$io-sentry-SentryOptions, reason: not valid java name */
    /* synthetic */ void m3827lambda$new$4$iosentrySentryOptions(SentryId sentryId, SentryFeedbackOptions.OptionsConfigurator optionsConfigurator) {
        this.logger.log(SentryLevel.WARNING, "showDialog() can only be called in Android.", new Object[0]);
    }

    public void merge(ExternalOptions externalOptions) {
        if (externalOptions.getDsn() != null) {
            setDsn(externalOptions.getDsn());
        }
        if (externalOptions.getEnvironment() != null) {
            setEnvironment(externalOptions.getEnvironment());
        }
        if (externalOptions.getRelease() != null) {
            setRelease(externalOptions.getRelease());
        }
        if (externalOptions.getDist() != null) {
            setDist(externalOptions.getDist());
        }
        if (externalOptions.getServerName() != null) {
            setServerName(externalOptions.getServerName());
        }
        if (externalOptions.getProxy() != null) {
            setProxy(externalOptions.getProxy());
        }
        if (externalOptions.getEnableUncaughtExceptionHandler() != null) {
            setEnableUncaughtExceptionHandler(externalOptions.getEnableUncaughtExceptionHandler().booleanValue());
        }
        if (externalOptions.getPrintUncaughtStackTrace() != null) {
            setPrintUncaughtStackTrace(externalOptions.getPrintUncaughtStackTrace().booleanValue());
        }
        if (externalOptions.getTracesSampleRate() != null) {
            setTracesSampleRate(externalOptions.getTracesSampleRate());
        }
        if (externalOptions.getProfilesSampleRate() != null) {
            setProfilesSampleRate(externalOptions.getProfilesSampleRate());
        }
        if (externalOptions.getDebug() != null) {
            setDebug(externalOptions.getDebug().booleanValue());
        }
        if (externalOptions.getEnableDeduplication() != null) {
            setEnableDeduplication(externalOptions.getEnableDeduplication().booleanValue());
        }
        if (externalOptions.getSendClientReports() != null) {
            setSendClientReports(externalOptions.getSendClientReports().booleanValue());
        }
        if (externalOptions.isForceInit() != null) {
            setForceInit(externalOptions.isForceInit().booleanValue());
        }
        for (Map.Entry entry : new HashMap(externalOptions.getTags()).entrySet()) {
            this.tags.put((String) entry.getKey(), (String) entry.getValue());
        }
        Iterator it = new ArrayList(externalOptions.getInAppIncludes()).iterator();
        while (it.hasNext()) {
            addInAppInclude((String) it.next());
        }
        Iterator it2 = new ArrayList(externalOptions.getInAppExcludes()).iterator();
        while (it2.hasNext()) {
            addInAppExclude((String) it2.next());
        }
        Iterator it3 = new HashSet(externalOptions.getIgnoredExceptionsForType()).iterator();
        while (it3.hasNext()) {
            addIgnoredExceptionForType((Class) it3.next());
        }
        if (externalOptions.getTracePropagationTargets() != null) {
            setTracePropagationTargets(new ArrayList(externalOptions.getTracePropagationTargets()));
        }
        Iterator it4 = new ArrayList(externalOptions.getContextTags()).iterator();
        while (it4.hasNext()) {
            addContextTag((String) it4.next());
        }
        if (externalOptions.getProguardUuid() != null) {
            setProguardUuid(externalOptions.getProguardUuid());
        }
        if (externalOptions.getIdleTimeout() != null) {
            setIdleTimeout(externalOptions.getIdleTimeout());
        }
        Iterator<String> it5 = externalOptions.getBundleIds().iterator();
        while (it5.hasNext()) {
            addBundleId(it5.next());
        }
        if (externalOptions.isEnabled() != null) {
            setEnabled(externalOptions.isEnabled().booleanValue());
        }
        if (externalOptions.isEnablePrettySerializationOutput() != null) {
            setEnablePrettySerializationOutput(externalOptions.isEnablePrettySerializationOutput().booleanValue());
        }
        if (externalOptions.isSendModules() != null) {
            setSendModules(externalOptions.isSendModules().booleanValue());
        }
        if (externalOptions.getIgnoredCheckIns() != null) {
            setIgnoredCheckIns(new ArrayList(externalOptions.getIgnoredCheckIns()));
        }
        if (externalOptions.getIgnoredTransactions() != null) {
            setIgnoredTransactions(new ArrayList(externalOptions.getIgnoredTransactions()));
        }
        if (externalOptions.getIgnoredErrors() != null) {
            setIgnoredErrors(new ArrayList(externalOptions.getIgnoredErrors()));
        }
        if (externalOptions.isEnableBackpressureHandling() != null) {
            setEnableBackpressureHandling(externalOptions.isEnableBackpressureHandling().booleanValue());
        }
        if (externalOptions.getMaxRequestBodySize() != null) {
            setMaxRequestBodySize(externalOptions.getMaxRequestBodySize());
        }
        if (externalOptions.isSendDefaultPii() != null) {
            setSendDefaultPii(externalOptions.isSendDefaultPii().booleanValue());
        }
        if (externalOptions.isCaptureOpenTelemetryEvents() != null) {
            setCaptureOpenTelemetryEvents(externalOptions.isCaptureOpenTelemetryEvents().booleanValue());
        }
        if (externalOptions.isEnableSpotlight() != null) {
            setEnableSpotlight(externalOptions.isEnableSpotlight().booleanValue());
        }
        if (externalOptions.getSpotlightConnectionUrl() != null) {
            setSpotlightConnectionUrl(externalOptions.getSpotlightConnectionUrl());
        }
        if (externalOptions.isGlobalHubMode() != null) {
            setGlobalHubMode(externalOptions.isGlobalHubMode());
        }
        if (externalOptions.getCron() != null) {
            if (getCron() == null) {
                setCron(externalOptions.getCron());
            } else {
                if (externalOptions.getCron().getDefaultCheckinMargin() != null) {
                    getCron().setDefaultCheckinMargin(externalOptions.getCron().getDefaultCheckinMargin());
                }
                if (externalOptions.getCron().getDefaultMaxRuntime() != null) {
                    getCron().setDefaultMaxRuntime(externalOptions.getCron().getDefaultMaxRuntime());
                }
                if (externalOptions.getCron().getDefaultTimezone() != null) {
                    getCron().setDefaultTimezone(externalOptions.getCron().getDefaultTimezone());
                }
                if (externalOptions.getCron().getDefaultFailureIssueThreshold() != null) {
                    getCron().setDefaultFailureIssueThreshold(externalOptions.getCron().getDefaultFailureIssueThreshold());
                }
                if (externalOptions.getCron().getDefaultRecoveryThreshold() != null) {
                    getCron().setDefaultRecoveryThreshold(externalOptions.getCron().getDefaultRecoveryThreshold());
                }
            }
        }
        if (externalOptions.isEnableLogs() != null) {
            getLogs().setEnabled(externalOptions.isEnableLogs().booleanValue());
        }
        if (externalOptions.getProfileSessionSampleRate() != null) {
            setProfileSessionSampleRate(externalOptions.getProfileSessionSampleRate());
        }
        if (externalOptions.getProfilingTracesDirPath() != null) {
            setProfilingTracesDirPath(externalOptions.getProfilingTracesDirPath());
        }
        if (externalOptions.getProfileLifecycle() != null) {
            setProfileLifecycle(externalOptions.getProfileLifecycle());
        }
    }

    private SdkVersion createSdkVersion() {
        SdkVersion sdkVersion = new SdkVersion(BuildConfig.SENTRY_JAVA_SDK_NAME, "8.23.0");
        sdkVersion.setVersion("8.23.0");
        return sdkVersion;
    }

    private void addPackageInfo() {
        SentryIntegrationPackageStorage.getInstance().addPackage("maven:io.sentry:sentry", "8.23.0");
    }

    public ISpanFactory getSpanFactory() {
        return this.spanFactory;
    }

    public void setSpanFactory(ISpanFactory iSpanFactory) {
        this.spanFactory = iSpanFactory;
    }

    public Logs getLogs() {
        return this.logs;
    }

    public void setLogs(Logs logs) {
        this.logs = logs;
    }

    public static final class Proxy {
        private String host;
        private String pass;
        private String port;
        private Proxy.Type type;
        private String user;

        public Proxy() {
            this(null, null, null, null, null);
        }

        public Proxy(String str, String str2) {
            this(str, str2, null, null, null);
        }

        public Proxy(String str, String str2, Proxy.Type type) {
            this(str, str2, type, null, null);
        }

        public Proxy(String str, String str2, String str3, String str4) {
            this(str, str2, null, str3, str4);
        }

        public Proxy(String str, String str2, Proxy.Type type, String str3, String str4) {
            this.host = str;
            this.port = str2;
            this.type = type;
            this.user = str3;
            this.pass = str4;
        }

        public String getHost() {
            return this.host;
        }

        public void setHost(String str) {
            this.host = str;
        }

        public String getPort() {
            return this.port;
        }

        public void setPort(String str) {
            this.port = str;
        }

        public String getUser() {
            return this.user;
        }

        public void setUser(String str) {
            this.user = str;
        }

        public String getPass() {
            return this.pass;
        }

        public void setPass(String str) {
            this.pass = str;
        }

        public Proxy.Type getType() {
            return this.type;
        }

        public void setType(Proxy.Type type) {
            this.type = type;
        }
    }

    public static final class Cron {
        private Long defaultCheckinMargin;
        private Long defaultFailureIssueThreshold;
        private Long defaultMaxRuntime;
        private Long defaultRecoveryThreshold;
        private String defaultTimezone;

        public Long getDefaultCheckinMargin() {
            return this.defaultCheckinMargin;
        }

        public void setDefaultCheckinMargin(Long l) {
            this.defaultCheckinMargin = l;
        }

        public Long getDefaultMaxRuntime() {
            return this.defaultMaxRuntime;
        }

        public void setDefaultMaxRuntime(Long l) {
            this.defaultMaxRuntime = l;
        }

        public String getDefaultTimezone() {
            return this.defaultTimezone;
        }

        public void setDefaultTimezone(String str) {
            this.defaultTimezone = str;
        }

        public Long getDefaultFailureIssueThreshold() {
            return this.defaultFailureIssueThreshold;
        }

        public void setDefaultFailureIssueThreshold(Long l) {
            this.defaultFailureIssueThreshold = l;
        }

        public Long getDefaultRecoveryThreshold() {
            return this.defaultRecoveryThreshold;
        }

        public void setDefaultRecoveryThreshold(Long l) {
            this.defaultRecoveryThreshold = l;
        }
    }

    public static final class Logs {
        private BeforeSendLogCallback beforeSend;
        private boolean enable = false;

        public interface BeforeSendLogCallback {
            SentryLogEvent execute(SentryLogEvent sentryLogEvent);
        }

        public boolean isEnabled() {
            return this.enable;
        }

        public void setEnabled(boolean z) {
            this.enable = z;
        }

        public BeforeSendLogCallback getBeforeSend() {
            return this.beforeSend;
        }

        public void setBeforeSend(BeforeSendLogCallback beforeSendLogCallback) {
            this.beforeSend = beforeSendLogCallback;
        }
    }

    public DistributionOptions getDistribution() {
        return this.distribution;
    }

    public void setDistribution(DistributionOptions distributionOptions) {
        if (distributionOptions == null) {
            distributionOptions = new DistributionOptions();
        }
        this.distribution = distributionOptions;
    }
}
