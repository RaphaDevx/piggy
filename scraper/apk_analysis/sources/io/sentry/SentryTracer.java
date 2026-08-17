package io.sentry;

import io.sentry.Scope;
import io.sentry.protocol.Contexts;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.protocol.TransactionNameSource;
import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.CollectionUtils;
import io.sentry.util.Objects;
import io.sentry.util.SpanUtils;
import io.sentry.util.thread.IThreadChecker;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public final class SentryTracer implements ITransaction {
    private final List<Span> children;
    private final CompositePerformanceCollector compositePerformanceCollector;
    private final Contexts contexts;
    private volatile TimerTask deadlineTimeoutTask;
    private final SentryId eventId;
    private FinishStatus finishStatus;
    private volatile TimerTask idleTimeoutTask;
    private final Instrumenter instrumenter;
    private final AtomicBoolean isDeadlineTimerRunning;
    private final AtomicBoolean isIdleFinishTimerRunning;
    private String name;
    private final Span root;
    private final IScopes scopes;
    private volatile Timer timer;
    private final AutoClosableReentrantLock timerLock;
    private final AutoClosableReentrantLock tracerLock;
    private TransactionNameSource transactionNameSource;
    private final TransactionOptions transactionOptions;

    @Override // io.sentry.ISpan
    public boolean isNoOp() {
        return false;
    }

    public SentryTracer(TransactionContext transactionContext, IScopes iScopes) {
        this(transactionContext, iScopes, new TransactionOptions(), null);
    }

    public SentryTracer(TransactionContext transactionContext, IScopes iScopes, TransactionOptions transactionOptions) {
        this(transactionContext, iScopes, transactionOptions, null);
    }

    SentryTracer(TransactionContext transactionContext, IScopes iScopes, TransactionOptions transactionOptions, CompositePerformanceCollector compositePerformanceCollector) {
        this.eventId = new SentryId();
        this.children = new CopyOnWriteArrayList();
        this.finishStatus = FinishStatus.NOT_FINISHED;
        this.timer = null;
        this.timerLock = new AutoClosableReentrantLock();
        this.tracerLock = new AutoClosableReentrantLock();
        this.isIdleFinishTimerRunning = new AtomicBoolean(false);
        this.isDeadlineTimerRunning = new AtomicBoolean(false);
        Contexts contexts = new Contexts();
        this.contexts = contexts;
        Objects.requireNonNull(transactionContext, "context is required");
        Objects.requireNonNull(iScopes, "scopes are required");
        Span span = new Span(transactionContext, this, iScopes, transactionOptions);
        this.root = span;
        this.name = transactionContext.getName();
        this.instrumenter = transactionContext.getInstrumenter();
        this.scopes = iScopes;
        this.compositePerformanceCollector = compositePerformanceCollector;
        this.transactionNameSource = transactionContext.getTransactionNameSource();
        this.transactionOptions = transactionOptions;
        setDefaultSpanData(span);
        SentryId profilerId = getProfilerId();
        if (!profilerId.equals(SentryId.EMPTY_ID) && Boolean.TRUE.equals(isSampled())) {
            contexts.setProfile(new ProfileContext(profilerId));
        }
        if (compositePerformanceCollector != null) {
            compositePerformanceCollector.start(this);
        }
        if (transactionOptions.getIdleTimeout() == null && transactionOptions.getDeadlineTimeout() == null) {
            return;
        }
        this.timer = new Timer(true);
        scheduleDeadlineTimeout();
        scheduleFinish();
    }

    @Override // io.sentry.ITransaction
    public void scheduleFinish() {
        Long idleTimeout;
        ISentryLifecycleToken acquire = this.timerLock.acquire();
        try {
            if (this.timer != null && (idleTimeout = this.transactionOptions.getIdleTimeout()) != null) {
                cancelIdleTimer();
                this.isIdleFinishTimerRunning.set(true);
                this.idleTimeoutTask = new TimerTask() { // from class: io.sentry.SentryTracer.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        SentryTracer.this.onIdleTimeoutReached();
                    }
                };
                try {
                    this.timer.schedule(this.idleTimeoutTask, idleTimeout.longValue());
                } catch (Throwable th) {
                    this.scopes.getOptions().getLogger().log(SentryLevel.WARNING, "Failed to schedule finish timer", th);
                    onIdleTimeoutReached();
                }
            }
            if (acquire != null) {
                acquire.close();
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

    /* JADX INFO: Access modifiers changed from: private */
    public void onIdleTimeoutReached() {
        SpanStatus status = getStatus();
        if (status == null) {
            status = SpanStatus.OK;
        }
        finish(status);
        this.isIdleFinishTimerRunning.set(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeadlineTimeoutReached() {
        SpanStatus status = getStatus();
        if (status == null) {
            status = SpanStatus.DEADLINE_EXCEEDED;
        }
        forceFinish(status, this.transactionOptions.getIdleTimeout() != null, null);
        this.isDeadlineTimerRunning.set(false);
    }

    @Override // io.sentry.ITransaction
    public void forceFinish(SpanStatus spanStatus, boolean z, Hint hint) {
        if (isFinished()) {
            return;
        }
        SentryDate now = this.scopes.getOptions().getDateProvider().now();
        ListIterator reverseListIterator = CollectionUtils.reverseListIterator((CopyOnWriteArrayList) this.children);
        while (reverseListIterator.hasPrevious()) {
            Span span = (Span) reverseListIterator.previous();
            span.setSpanFinishedCallback(null);
            span.finish(spanStatus, now);
        }
        finish(spanStatus, now, z, hint);
    }

    @Override // io.sentry.ITransaction
    public void finish(SpanStatus spanStatus, SentryDate sentryDate, boolean z, Hint hint) {
        SentryDate finishDate = this.root.getFinishDate();
        if (sentryDate == null) {
            sentryDate = finishDate;
        }
        if (sentryDate == null) {
            sentryDate = this.scopes.getOptions().getDateProvider().now();
        }
        for (Span span : this.children) {
            if (span.getOptions().isIdle()) {
                span.finish(spanStatus != null ? spanStatus : getSpanContext().status, sentryDate);
            }
        }
        this.finishStatus = FinishStatus.finishing(spanStatus);
        if (this.root.isFinished()) {
            return;
        }
        if (!this.transactionOptions.isWaitForChildren() || hasAllChildrenFinished()) {
            final AtomicReference atomicReference = new AtomicReference();
            final SpanFinishedCallback spanFinishedCallback = this.root.getSpanFinishedCallback();
            this.root.setSpanFinishedCallback(new SpanFinishedCallback() { // from class: io.sentry.SentryTracer$$ExternalSyntheticLambda1
                @Override // io.sentry.SpanFinishedCallback
                public final void execute(Span span2) {
                    SentryTracer.this.m3829lambda$finish$0$iosentrySentryTracer(spanFinishedCallback, atomicReference, span2);
                }
            });
            this.root.finish(this.finishStatus.spanStatus, sentryDate);
            ProfilingTraceData onTransactionFinish = (Boolean.TRUE.equals(isSampled()) && Boolean.TRUE.equals(isProfileSampled())) ? this.scopes.getOptions().getTransactionProfiler().onTransactionFinish(this, (List) atomicReference.get(), this.scopes.getOptions()) : null;
            if (this.scopes.getOptions().isContinuousProfilingEnabled() && this.scopes.getOptions().getProfileLifecycle() == ProfileLifecycle.TRACE && this.root.getSpanContext().getProfilerId().equals(SentryId.EMPTY_ID)) {
                this.scopes.getOptions().getContinuousProfiler().stopProfiler(ProfileLifecycle.TRACE);
            }
            if (atomicReference.get() != null) {
                ((List) atomicReference.get()).clear();
            }
            this.scopes.configureScope(new ScopeCallback() { // from class: io.sentry.SentryTracer$$ExternalSyntheticLambda2
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    SentryTracer.this.m3831lambda$finish$2$iosentrySentryTracer(iScope);
                }
            });
            SentryTransaction sentryTransaction = new SentryTransaction(this);
            if (this.timer != null) {
                ISentryLifecycleToken acquire = this.timerLock.acquire();
                try {
                    if (this.timer != null) {
                        cancelIdleTimer();
                        cancelDeadlineTimer();
                        this.timer.cancel();
                        this.timer = null;
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
            if (z && this.children.isEmpty() && this.transactionOptions.getIdleTimeout() != null) {
                this.scopes.getOptions().getLogger().log(SentryLevel.DEBUG, "Dropping idle transaction %s because it has no child spans", this.name);
            } else {
                sentryTransaction.getMeasurements().putAll(this.root.getMeasurements());
                this.scopes.captureTransaction(sentryTransaction, traceContext(), hint, onTransactionFinish);
            }
        }
    }

    /* renamed from: lambda$finish$0$io-sentry-SentryTracer, reason: not valid java name */
    /* synthetic */ void m3829lambda$finish$0$iosentrySentryTracer(SpanFinishedCallback spanFinishedCallback, AtomicReference atomicReference, Span span) {
        if (spanFinishedCallback != null) {
            spanFinishedCallback.execute(span);
        }
        TransactionFinishedCallback transactionFinishedCallback = this.transactionOptions.getTransactionFinishedCallback();
        if (transactionFinishedCallback != null) {
            transactionFinishedCallback.execute(this);
        }
        CompositePerformanceCollector compositePerformanceCollector = this.compositePerformanceCollector;
        if (compositePerformanceCollector != null) {
            atomicReference.set(compositePerformanceCollector.m3804lambda$start$0$iosentryDefaultCompositePerformanceCollector(this));
        }
    }

    /* renamed from: lambda$finish$2$io-sentry-SentryTracer, reason: not valid java name */
    /* synthetic */ void m3831lambda$finish$2$iosentrySentryTracer(final IScope iScope) {
        iScope.withTransaction(new Scope.IWithTransaction() { // from class: io.sentry.SentryTracer$$ExternalSyntheticLambda0
            @Override // io.sentry.Scope.IWithTransaction
            public final void accept(ITransaction iTransaction) {
                SentryTracer.this.m3830lambda$finish$1$iosentrySentryTracer(iScope, iTransaction);
            }
        });
    }

    /* renamed from: lambda$finish$1$io-sentry-SentryTracer, reason: not valid java name */
    /* synthetic */ void m3830lambda$finish$1$iosentrySentryTracer(IScope iScope, ITransaction iTransaction) {
        if (iTransaction == this) {
            iScope.clearTransaction();
        }
    }

    private void cancelIdleTimer() {
        ISentryLifecycleToken acquire = this.timerLock.acquire();
        try {
            if (this.idleTimeoutTask != null) {
                this.idleTimeoutTask.cancel();
                this.isIdleFinishTimerRunning.set(false);
                this.idleTimeoutTask = null;
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

    private void scheduleDeadlineTimeout() {
        Long deadlineTimeout = this.transactionOptions.getDeadlineTimeout();
        if (deadlineTimeout != null) {
            ISentryLifecycleToken acquire = this.timerLock.acquire();
            try {
                if (this.timer != null) {
                    cancelDeadlineTimer();
                    this.isDeadlineTimerRunning.set(true);
                    this.deadlineTimeoutTask = new TimerTask() { // from class: io.sentry.SentryTracer.2
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            SentryTracer.this.onDeadlineTimeoutReached();
                        }
                    };
                    try {
                        this.timer.schedule(this.deadlineTimeoutTask, deadlineTimeout.longValue());
                    } catch (Throwable th) {
                        this.scopes.getOptions().getLogger().log(SentryLevel.WARNING, "Failed to schedule finish timer", th);
                        onDeadlineTimeoutReached();
                    }
                }
                if (acquire != null) {
                    acquire.close();
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
    }

    private void cancelDeadlineTimer() {
        ISentryLifecycleToken acquire = this.timerLock.acquire();
        try {
            if (this.deadlineTimeoutTask != null) {
                this.deadlineTimeoutTask.cancel();
                this.isDeadlineTimerRunning.set(false);
                this.deadlineTimeoutTask = null;
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

    public List<Span> getChildren() {
        return this.children;
    }

    @Override // io.sentry.ISpan
    public SentryDate getStartDate() {
        return this.root.getStartDate();
    }

    @Override // io.sentry.ISpan
    public SentryDate getFinishDate() {
        return this.root.getFinishDate();
    }

    ISpan startChild(SpanId spanId, String str, String str2) {
        return startChild(spanId, str, str2, new SpanOptions());
    }

    ISpan startChild(SpanId spanId, String str, String str2, SpanOptions spanOptions) {
        return createChild(spanId, str, str2, spanOptions);
    }

    ISpan startChild(SpanId spanId, String str, String str2, SentryDate sentryDate, Instrumenter instrumenter) {
        SpanContext copyForChild = getSpanContext().copyForChild(str, spanId, null);
        copyForChild.setDescription(str2);
        copyForChild.setInstrumenter(instrumenter);
        SpanOptions spanOptions = new SpanOptions();
        spanOptions.setStartTimestamp(sentryDate);
        return createChild(copyForChild, spanOptions);
    }

    ISpan startChild(SpanId spanId, String str, String str2, SentryDate sentryDate, Instrumenter instrumenter, SpanOptions spanOptions) {
        SpanContext copyForChild = getSpanContext().copyForChild(str, spanId, null);
        copyForChild.setDescription(str2);
        copyForChild.setInstrumenter(instrumenter);
        spanOptions.setStartTimestamp(sentryDate);
        return createChild(copyForChild, spanOptions);
    }

    private ISpan createChild(SpanId spanId, String str, String str2, SpanOptions spanOptions) {
        SpanContext copyForChild = getSpanContext().copyForChild(str, spanId, null);
        copyForChild.setDescription(str2);
        copyForChild.setInstrumenter(Instrumenter.SENTRY);
        return createChild(copyForChild, spanOptions);
    }

    private ISpan createChild(SpanContext spanContext, SpanOptions spanOptions) {
        if (this.root.isFinished()) {
            return NoOpSpan.getInstance();
        }
        if (!this.instrumenter.equals(spanContext.getInstrumenter())) {
            return NoOpSpan.getInstance();
        }
        if (SpanUtils.isIgnored(this.scopes.getOptions().getIgnoredSpanOrigins(), spanOptions.getOrigin())) {
            return NoOpSpan.getInstance();
        }
        SpanId parentSpanId = spanContext.getParentSpanId();
        String operation = spanContext.getOperation();
        String description = spanContext.getDescription();
        if (this.children.size() < this.scopes.getOptions().getMaxSpans()) {
            Objects.requireNonNull(parentSpanId, "parentSpanId is required");
            Objects.requireNonNull(operation, "operation is required");
            cancelIdleTimer();
            Span span = new Span(this, this.scopes, spanContext, spanOptions, new SpanFinishedCallback() { // from class: io.sentry.SentryTracer$$ExternalSyntheticLambda3
                @Override // io.sentry.SpanFinishedCallback
                public final void execute(Span span2) {
                    SentryTracer.this.m3828lambda$createChild$3$iosentrySentryTracer(span2);
                }
            });
            setDefaultSpanData(span);
            this.children.add(span);
            CompositePerformanceCollector compositePerformanceCollector = this.compositePerformanceCollector;
            if (compositePerformanceCollector != null) {
                compositePerformanceCollector.onSpanStarted(span);
            }
            return span;
        }
        this.scopes.getOptions().getLogger().log(SentryLevel.WARNING, "Span operation: %s, description: %s dropped due to limit reached. Returning NoOpSpan.", operation, description);
        return NoOpSpan.getInstance();
    }

    /* renamed from: lambda$createChild$3$io-sentry-SentryTracer, reason: not valid java name */
    /* synthetic */ void m3828lambda$createChild$3$iosentrySentryTracer(Span span) {
        CompositePerformanceCollector compositePerformanceCollector = this.compositePerformanceCollector;
        if (compositePerformanceCollector != null) {
            compositePerformanceCollector.onSpanFinished(span);
        }
        FinishStatus finishStatus = this.finishStatus;
        if (this.transactionOptions.getIdleTimeout() != null) {
            if (!this.transactionOptions.isWaitForChildren() || hasAllChildrenFinished()) {
                scheduleFinish();
                return;
            }
            return;
        }
        if (finishStatus.isFinishing) {
            finish(finishStatus.spanStatus);
        }
    }

    private void setDefaultSpanData(ISpan iSpan) {
        IThreadChecker threadChecker = this.scopes.getOptions().getThreadChecker();
        SentryId profilerId = getProfilerId();
        if (!profilerId.equals(SentryId.EMPTY_ID) && Boolean.TRUE.equals(iSpan.isSampled())) {
            iSpan.setData("profiler_id", profilerId.toString());
        }
        iSpan.setData(SpanDataConvention.THREAD_ID, String.valueOf(threadChecker.currentThreadSystemId()));
        iSpan.setData(SpanDataConvention.THREAD_NAME, threadChecker.getCurrentThreadName());
    }

    private SentryId getProfilerId() {
        if (!this.root.getSpanContext().getProfilerId().equals(SentryId.EMPTY_ID)) {
            return this.root.getSpanContext().getProfilerId();
        }
        return this.scopes.getOptions().getContinuousProfiler().getProfilerId();
    }

    @Override // io.sentry.ISpan
    public ISpan startChild(String str) {
        return startChild(str, (String) null);
    }

    @Override // io.sentry.ISpan
    public ISpan startChild(String str, String str2, SentryDate sentryDate, Instrumenter instrumenter) {
        return startChild(str, str2, sentryDate, instrumenter, new SpanOptions());
    }

    @Override // io.sentry.ISpan
    public ISpan startChild(String str, String str2, SentryDate sentryDate, Instrumenter instrumenter, SpanOptions spanOptions) {
        return createChild(str, str2, sentryDate, instrumenter, spanOptions);
    }

    @Override // io.sentry.ITransaction
    public ISpan startChild(String str, String str2, SentryDate sentryDate) {
        return createChild(str, str2, sentryDate, Instrumenter.SENTRY, new SpanOptions());
    }

    @Override // io.sentry.ISpan
    public ISpan startChild(String str, String str2) {
        return startChild(str, str2, (SentryDate) null, Instrumenter.SENTRY, new SpanOptions());
    }

    @Override // io.sentry.ISpan
    public ISpan startChild(String str, String str2, SpanOptions spanOptions) {
        return createChild(str, str2, null, Instrumenter.SENTRY, spanOptions);
    }

    @Override // io.sentry.ISpan
    public ISpan startChild(SpanContext spanContext, SpanOptions spanOptions) {
        return createChild(spanContext, spanOptions);
    }

    private ISpan createChild(String str, String str2, SentryDate sentryDate, Instrumenter instrumenter, SpanOptions spanOptions) {
        if (this.root.isFinished()) {
            return NoOpSpan.getInstance();
        }
        if (!this.instrumenter.equals(instrumenter)) {
            return NoOpSpan.getInstance();
        }
        if (this.children.size() < this.scopes.getOptions().getMaxSpans()) {
            return this.root.startChild(str, str2, sentryDate, instrumenter, spanOptions);
        }
        this.scopes.getOptions().getLogger().log(SentryLevel.WARNING, "Span operation: %s, description: %s dropped due to limit reached. Returning NoOpSpan.", str, str2);
        return NoOpSpan.getInstance();
    }

    @Override // io.sentry.ISpan
    public SentryTraceHeader toSentryTrace() {
        return this.root.toSentryTrace();
    }

    @Override // io.sentry.ISpan
    public void finish() {
        finish(getStatus());
    }

    @Override // io.sentry.ISpan
    public void finish(SpanStatus spanStatus) {
        finish(spanStatus, null);
    }

    @Override // io.sentry.ISpan
    public void finish(SpanStatus spanStatus, SentryDate sentryDate) {
        finish(spanStatus, sentryDate, true, null);
    }

    @Override // io.sentry.ISpan
    public TraceContext traceContext() {
        Baggage baggage;
        if (!this.scopes.getOptions().isTraceSampling() || (baggage = getSpanContext().getBaggage()) == null) {
            return null;
        }
        updateBaggageValues(baggage);
        return baggage.toTraceContext();
    }

    private void updateBaggageValues(Baggage baggage) {
        ISentryLifecycleToken acquire = this.tracerLock.acquire();
        try {
            if (baggage.isMutable()) {
                final AtomicReference atomicReference = new AtomicReference();
                this.scopes.configureScope(new ScopeCallback() { // from class: io.sentry.SentryTracer$$ExternalSyntheticLambda4
                    @Override // io.sentry.ScopeCallback
                    public final void run(IScope iScope) {
                        atomicReference.set(iScope.getReplayId());
                    }
                });
                baggage.setValuesFromTransaction(getSpanContext().getTraceId(), (SentryId) atomicReference.get(), this.scopes.getOptions(), getSamplingDecision(), getName(), getTransactionNameSource());
                baggage.freeze();
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

    @Override // io.sentry.ISpan
    public BaggageHeader toBaggageHeader(List<String> list) {
        Baggage baggage;
        if (!this.scopes.getOptions().isTraceSampling() || (baggage = getSpanContext().getBaggage()) == null) {
            return null;
        }
        updateBaggageValues(baggage);
        return BaggageHeader.fromBaggageAndOutgoingHeader(baggage, list);
    }

    private boolean hasAllChildrenFinished() {
        ListIterator<Span> listIterator = this.children.listIterator();
        while (listIterator.hasNext()) {
            Span next = listIterator.next();
            if (!next.isFinished() && next.getFinishDate() == null) {
                return false;
            }
        }
        return true;
    }

    @Override // io.sentry.ISpan
    public void setOperation(String str) {
        if (this.root.isFinished()) {
            this.scopes.getOptions().getLogger().log(SentryLevel.DEBUG, "The transaction is already finished. Operation %s cannot be set", str);
        } else {
            this.root.setOperation(str);
        }
    }

    @Override // io.sentry.ISpan
    public String getOperation() {
        return this.root.getOperation();
    }

    @Override // io.sentry.ISpan
    public void setDescription(String str) {
        if (this.root.isFinished()) {
            this.scopes.getOptions().getLogger().log(SentryLevel.DEBUG, "The transaction is already finished. Description %s cannot be set", str);
        } else {
            this.root.setDescription(str);
        }
    }

    @Override // io.sentry.ISpan
    public String getDescription() {
        return this.root.getDescription();
    }

    @Override // io.sentry.ISpan
    public void setStatus(SpanStatus spanStatus) {
        if (this.root.isFinished()) {
            this.scopes.getOptions().getLogger().log(SentryLevel.DEBUG, "The transaction is already finished. Status %s cannot be set", spanStatus == null ? "null" : spanStatus.name());
        } else {
            this.root.setStatus(spanStatus);
        }
    }

    @Override // io.sentry.ISpan
    public SpanStatus getStatus() {
        return this.root.getStatus();
    }

    @Override // io.sentry.ISpan
    public void setThrowable(Throwable th) {
        if (this.root.isFinished()) {
            this.scopes.getOptions().getLogger().log(SentryLevel.DEBUG, "The transaction is already finished. Throwable cannot be set", new Object[0]);
        } else {
            this.root.setThrowable(th);
        }
    }

    @Override // io.sentry.ISpan
    public Throwable getThrowable() {
        return this.root.getThrowable();
    }

    @Override // io.sentry.ISpan
    public SpanContext getSpanContext() {
        return this.root.getSpanContext();
    }

    @Override // io.sentry.ISpan
    public void setTag(String str, String str2) {
        if (this.root.isFinished()) {
            this.scopes.getOptions().getLogger().log(SentryLevel.DEBUG, "The transaction is already finished. Tag %s cannot be set", str);
        } else {
            this.root.setTag(str, str2);
        }
    }

    @Override // io.sentry.ISpan
    public String getTag(String str) {
        return this.root.getTag(str);
    }

    @Override // io.sentry.ISpan
    public boolean isFinished() {
        return this.root.isFinished();
    }

    @Override // io.sentry.ISpan
    public void setData(String str, Object obj) {
        if (this.root.isFinished()) {
            this.scopes.getOptions().getLogger().log(SentryLevel.DEBUG, "The transaction is already finished. Data %s cannot be set", str);
        } else {
            this.root.setData(str, obj);
        }
    }

    @Override // io.sentry.ISpan
    public Object getData(String str) {
        return this.root.getData(str);
    }

    public void setMeasurementFromChild(String str, Number number) {
        if (this.root.getMeasurements().containsKey(str)) {
            return;
        }
        setMeasurement(str, number);
    }

    public void setMeasurementFromChild(String str, Number number, MeasurementUnit measurementUnit) {
        if (this.root.getMeasurements().containsKey(str)) {
            return;
        }
        setMeasurement(str, number, measurementUnit);
    }

    @Override // io.sentry.ISpan
    public void setMeasurement(String str, Number number) {
        this.root.setMeasurement(str, number);
    }

    @Override // io.sentry.ISpan
    public void setMeasurement(String str, Number number, MeasurementUnit measurementUnit) {
        this.root.setMeasurement(str, number, measurementUnit);
    }

    public Map<String, Object> getData() {
        return this.root.getData();
    }

    @Override // io.sentry.ISpan
    public Boolean isSampled() {
        return this.root.isSampled();
    }

    @Override // io.sentry.ITransaction
    public Boolean isProfileSampled() {
        return this.root.isProfileSampled();
    }

    @Override // io.sentry.ISpan
    public TracesSamplingDecision getSamplingDecision() {
        return this.root.getSamplingDecision();
    }

    @Override // io.sentry.ITransaction
    public void setName(String str) {
        setName(str, TransactionNameSource.CUSTOM);
    }

    @Override // io.sentry.ITransaction
    public void setName(String str, TransactionNameSource transactionNameSource) {
        if (this.root.isFinished()) {
            this.scopes.getOptions().getLogger().log(SentryLevel.DEBUG, "The transaction is already finished. Name %s cannot be set", str);
        } else {
            this.name = str;
            this.transactionNameSource = transactionNameSource;
        }
    }

    @Override // io.sentry.ITransaction
    public String getName() {
        return this.name;
    }

    @Override // io.sentry.ITransaction
    public TransactionNameSource getTransactionNameSource() {
        return this.transactionNameSource;
    }

    @Override // io.sentry.ITransaction
    public List<Span> getSpans() {
        return this.children;
    }

    @Override // io.sentry.ITransaction
    public ISpan getLatestActiveSpan() {
        ListIterator reverseListIterator = CollectionUtils.reverseListIterator((CopyOnWriteArrayList) this.children);
        while (reverseListIterator.hasPrevious()) {
            Span span = (Span) reverseListIterator.previous();
            if (!span.isFinished()) {
                return span;
            }
        }
        return null;
    }

    @Override // io.sentry.ITransaction
    public SentryId getEventId() {
        return this.eventId;
    }

    @Override // io.sentry.ISpan
    public ISentryLifecycleToken makeCurrent() {
        this.scopes.configureScope(new ScopeCallback() { // from class: io.sentry.SentryTracer$$ExternalSyntheticLambda5
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                SentryTracer.this.m3832lambda$makeCurrent$5$iosentrySentryTracer(iScope);
            }
        });
        return NoOpScopesLifecycleToken.getInstance();
    }

    /* renamed from: lambda$makeCurrent$5$io-sentry-SentryTracer, reason: not valid java name */
    /* synthetic */ void m3832lambda$makeCurrent$5$iosentrySentryTracer(IScope iScope) {
        iScope.setTransaction(this);
    }

    Span getRoot() {
        return this.root;
    }

    TimerTask getIdleTimeoutTask() {
        return this.idleTimeoutTask;
    }

    TimerTask getDeadlineTimeoutTask() {
        return this.deadlineTimeoutTask;
    }

    Timer getTimer() {
        return this.timer;
    }

    AtomicBoolean isFinishTimerRunning() {
        return this.isIdleFinishTimerRunning;
    }

    AtomicBoolean isDeadlineTimerRunning() {
        return this.isDeadlineTimerRunning;
    }

    @Override // io.sentry.ISpan
    public void setContext(String str, Object obj) {
        this.contexts.put(str, obj);
    }

    @Override // io.sentry.ISpan
    public Contexts getContexts() {
        return this.contexts;
    }

    @Override // io.sentry.ISpan
    public boolean updateEndDate(SentryDate sentryDate) {
        return this.root.updateEndDate(sentryDate);
    }

    private static final class FinishStatus {
        static final FinishStatus NOT_FINISHED = notFinished();
        private final boolean isFinishing;
        private final SpanStatus spanStatus;

        static FinishStatus finishing(SpanStatus spanStatus) {
            return new FinishStatus(true, spanStatus);
        }

        private static FinishStatus notFinished() {
            return new FinishStatus(false, null);
        }

        private FinishStatus(boolean z, SpanStatus spanStatus) {
            this.isFinishing = z;
            this.spanStatus = spanStatus;
        }
    }
}
