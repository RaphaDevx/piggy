package io.sentry;

import io.sentry.Scope;
import io.sentry.clientreport.DiscardReason;
import io.sentry.hints.SessionEndHint;
import io.sentry.hints.SessionStartHint;
import io.sentry.logger.ILoggerApi;
import io.sentry.logger.LoggerApi;
import io.sentry.protocol.Feedback;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.protocol.User;
import io.sentry.transport.RateLimiter;
import io.sentry.util.HintUtils;
import io.sentry.util.Objects;
import io.sentry.util.SpanUtils;
import io.sentry.util.TracingUtils;
import java.io.Closeable;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes2.dex */
public final class Scopes implements IScopes {
    private final CombinedScopeView combinedScope;
    private final CompositePerformanceCollector compositePerformanceCollector;
    private final String creator;
    private final IScope globalScope;
    private final IScope isolationScope;
    private final ILoggerApi logger;
    private final Scopes parentScopes;
    private final IScope scope;

    public Scopes(IScope iScope, IScope iScope2, IScope iScope3, String str) {
        this(iScope, iScope2, iScope3, null, str);
    }

    private Scopes(IScope iScope, IScope iScope2, IScope iScope3, Scopes scopes, String str) {
        this.combinedScope = new CombinedScopeView(iScope3, iScope2, iScope);
        this.scope = iScope;
        this.isolationScope = iScope2;
        this.globalScope = iScope3;
        this.parentScopes = scopes;
        this.creator = str;
        SentryOptions options = getOptions();
        validateOptions(options);
        this.compositePerformanceCollector = options.getCompositePerformanceCollector();
        this.logger = new LoggerApi(this);
    }

    public String getCreator() {
        return this.creator;
    }

    @Override // io.sentry.IScopes
    public IScope getScope() {
        return this.scope;
    }

    @Override // io.sentry.IScopes
    public IScope getIsolationScope() {
        return this.isolationScope;
    }

    @Override // io.sentry.IScopes
    public IScope getGlobalScope() {
        return this.globalScope;
    }

    @Override // io.sentry.IScopes
    public IScopes getParentScopes() {
        return this.parentScopes;
    }

    @Override // io.sentry.IScopes
    public boolean isAncestorOf(IScopes iScopes) {
        if (iScopes == null) {
            return false;
        }
        if (this == iScopes) {
            return true;
        }
        if (iScopes.getParentScopes() != null) {
            return isAncestorOf(iScopes.getParentScopes());
        }
        return false;
    }

    @Override // io.sentry.IScopes
    public IScopes forkedScopes(String str) {
        return new Scopes(this.scope.m3803clone(), this.isolationScope.m3803clone(), this.globalScope, this, str);
    }

    @Override // io.sentry.IScopes
    public IScopes forkedCurrentScope(String str) {
        return new Scopes(this.scope.m3803clone(), this.isolationScope, this.globalScope, this, str);
    }

    @Override // io.sentry.IScopes
    public IScopes forkedRootScopes(String str) {
        return Sentry.forkedRootScopes(str);
    }

    @Override // io.sentry.IScopes
    public boolean isEnabled() {
        return getClient().isEnabled();
    }

    @Override // io.sentry.IScopes
    public SentryId captureEvent(SentryEvent sentryEvent, Hint hint) {
        return captureEventInternal(sentryEvent, hint, null);
    }

    @Override // io.sentry.IScopes
    public SentryId captureEvent(SentryEvent sentryEvent, Hint hint, ScopeCallback scopeCallback) {
        return captureEventInternal(sentryEvent, hint, scopeCallback);
    }

    private SentryId captureEventInternal(SentryEvent sentryEvent, Hint hint, ScopeCallback scopeCallback) {
        SentryId sentryId = SentryId.EMPTY_ID;
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'captureEvent' call is a no-op.", new Object[0]);
            return sentryId;
        }
        if (sentryEvent == null) {
            getOptions().getLogger().log(SentryLevel.WARNING, "captureEvent called with null parameter.", new Object[0]);
            return sentryId;
        }
        try {
            assignTraceContext(sentryEvent);
            sentryId = getClient().captureEvent(sentryEvent, buildLocalScope(getCombinedScopeView(), scopeCallback), hint);
            updateLastEventId(sentryId);
            return sentryId;
        } catch (Throwable th) {
            getOptions().getLogger().log(SentryLevel.ERROR, "Error while capturing event with id: " + sentryEvent.getEventId(), th);
            return sentryId;
        }
    }

    public ISentryClient getClient() {
        return getCombinedScopeView().getClient();
    }

    private void assignTraceContext(SentryEvent sentryEvent) {
        getCombinedScopeView().assignTraceContext(sentryEvent);
    }

    private IScope buildLocalScope(IScope iScope, ScopeCallback scopeCallback) {
        if (scopeCallback != null) {
            try {
                IScope m3803clone = iScope.m3803clone();
                scopeCallback.run(m3803clone);
                return m3803clone;
            } catch (Throwable th) {
                getOptions().getLogger().log(SentryLevel.ERROR, "Error in the 'ScopeCallback' callback.", th);
            }
        }
        return iScope;
    }

    @Override // io.sentry.IScopes
    public SentryId captureMessage(String str, SentryLevel sentryLevel) {
        return captureMessageInternal(str, sentryLevel, null);
    }

    @Override // io.sentry.IScopes
    public SentryId captureMessage(String str, SentryLevel sentryLevel, ScopeCallback scopeCallback) {
        return captureMessageInternal(str, sentryLevel, scopeCallback);
    }

    private SentryId captureMessageInternal(String str, SentryLevel sentryLevel, ScopeCallback scopeCallback) {
        SentryId sentryId = SentryId.EMPTY_ID;
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'captureMessage' call is a no-op.", new Object[0]);
        } else if (str == null) {
            getOptions().getLogger().log(SentryLevel.WARNING, "captureMessage called with null parameter.", new Object[0]);
        } else {
            try {
                sentryId = getClient().captureMessage(str, sentryLevel, buildLocalScope(getCombinedScopeView(), scopeCallback));
            } catch (Throwable th) {
                getOptions().getLogger().log(SentryLevel.ERROR, "Error while capturing message: " + str, th);
            }
        }
        updateLastEventId(sentryId);
        return sentryId;
    }

    @Override // io.sentry.IScopes
    public SentryId captureFeedback(Feedback feedback, Hint hint, ScopeCallback scopeCallback) {
        SentryId sentryId = SentryId.EMPTY_ID;
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'captureFeedback' call is a no-op.", new Object[0]);
            return sentryId;
        }
        if (feedback.getMessage().isEmpty()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "captureFeedback called with empty message.", new Object[0]);
            return sentryId;
        }
        try {
            return getClient().captureFeedback(feedback, hint, buildLocalScope(getCombinedScopeView(), scopeCallback));
        } catch (Throwable th) {
            getOptions().getLogger().log(SentryLevel.ERROR, "Error while capturing feedback: " + feedback.getMessage(), th);
            return sentryId;
        }
    }

    @Override // io.sentry.IScopes
    public SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Hint hint) {
        Objects.requireNonNull(sentryEnvelope, "SentryEnvelope is required.");
        SentryId sentryId = SentryId.EMPTY_ID;
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'captureEnvelope' call is a no-op.", new Object[0]);
            return sentryId;
        }
        try {
            SentryId captureEnvelope = getClient().captureEnvelope(sentryEnvelope, hint);
            return captureEnvelope != null ? captureEnvelope : sentryId;
        } catch (Throwable th) {
            this.getOptions().getLogger().log(SentryLevel.ERROR, "Error while capturing envelope.", th);
            return sentryId;
        }
    }

    @Override // io.sentry.IScopes
    public SentryId captureException(Throwable th, Hint hint) {
        return captureExceptionInternal(th, hint, null);
    }

    @Override // io.sentry.IScopes
    public SentryId captureException(Throwable th, Hint hint, ScopeCallback scopeCallback) {
        return captureExceptionInternal(th, hint, scopeCallback);
    }

    private SentryId captureExceptionInternal(Throwable th, Hint hint, ScopeCallback scopeCallback) {
        SentryId sentryId = SentryId.EMPTY_ID;
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'captureException' call is a no-op.", new Object[0]);
        } else if (th == null) {
            getOptions().getLogger().log(SentryLevel.WARNING, "captureException called with null parameter.", new Object[0]);
        } else {
            try {
                SentryEvent sentryEvent = new SentryEvent(th);
                assignTraceContext(sentryEvent);
                sentryId = getClient().captureEvent(sentryEvent, buildLocalScope(getCombinedScopeView(), scopeCallback), hint);
            } catch (Throwable th2) {
                getOptions().getLogger().log(SentryLevel.ERROR, "Error while capturing exception: " + th.getMessage(), th2);
            }
        }
        updateLastEventId(sentryId);
        return sentryId;
    }

    @Override // io.sentry.IScopes
    public void captureUserFeedback(UserFeedback userFeedback) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'captureUserFeedback' call is a no-op.", new Object[0]);
            return;
        }
        try {
            getClient().captureUserFeedback(userFeedback);
        } catch (Throwable th) {
            getOptions().getLogger().log(SentryLevel.ERROR, "Error while capturing captureUserFeedback: " + userFeedback.toString(), th);
        }
    }

    @Override // io.sentry.IScopes
    public void startSession() {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'startSession' call is a no-op.", new Object[0]);
            return;
        }
        Scope.SessionPair startSession = getCombinedScopeView().startSession();
        if (startSession != null) {
            if (startSession.getPrevious() != null) {
                getClient().captureSession(startSession.getPrevious(), HintUtils.createWithTypeCheckHint(new SessionEndHint()));
            }
            getClient().captureSession(startSession.getCurrent(), HintUtils.createWithTypeCheckHint(new SessionStartHint()));
            return;
        }
        getOptions().getLogger().log(SentryLevel.WARNING, "Session could not be started.", new Object[0]);
    }

    @Override // io.sentry.IScopes
    public void endSession() {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'endSession' call is a no-op.", new Object[0]);
            return;
        }
        Session endSession = getCombinedScopeView().endSession();
        if (endSession != null) {
            getClient().captureSession(endSession, HintUtils.createWithTypeCheckHint(new SessionEndHint()));
        }
    }

    public IScope getCombinedScopeView() {
        return this.combinedScope;
    }

    @Override // io.sentry.IScopes
    public void close() {
        close(false);
    }

    @Override // io.sentry.IScopes
    public void close(final boolean z) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'close' call is a no-op.", new Object[0]);
            return;
        }
        try {
            for (Integration integration : getOptions().getIntegrations()) {
                if (integration instanceof Closeable) {
                    try {
                        ((Closeable) integration).close();
                    } catch (Throwable th) {
                        getOptions().getLogger().log(SentryLevel.WARNING, "Failed to close the integration {}.", integration, th);
                    }
                }
            }
            configureScope(new ScopeCallback() { // from class: io.sentry.Scopes$$ExternalSyntheticLambda1
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    iScope.clear();
                }
            });
            configureScope(ScopeType.ISOLATION, new ScopeCallback() { // from class: io.sentry.Scopes$$ExternalSyntheticLambda2
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    iScope.clear();
                }
            });
            getOptions().getBackpressureMonitor().close();
            getOptions().getTransactionProfiler().close();
            getOptions().getContinuousProfiler().close(true);
            getOptions().getCompositePerformanceCollector().close();
            getOptions().getConnectionStatusProvider().close();
            final ISentryExecutorService executorService = getOptions().getExecutorService();
            if (z) {
                try {
                    executorService.submit(new Runnable() { // from class: io.sentry.Scopes$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            Scopes.this.m3818lambda$close$2$iosentryScopes(executorService);
                        }
                    });
                } catch (RejectedExecutionException e) {
                    getOptions().getLogger().log(SentryLevel.WARNING, "Failed to submit executor service shutdown task during restart. Shutting down synchronously.", e);
                    executorService.close(getOptions().getShutdownTimeoutMillis());
                }
            } else {
                executorService.close(getOptions().getShutdownTimeoutMillis());
            }
            configureScope(ScopeType.CURRENT, new ScopeCallback() { // from class: io.sentry.Scopes$$ExternalSyntheticLambda4
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    iScope.getClient().close(z);
                }
            });
            configureScope(ScopeType.ISOLATION, new ScopeCallback() { // from class: io.sentry.Scopes$$ExternalSyntheticLambda5
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    iScope.getClient().close(z);
                }
            });
            configureScope(ScopeType.GLOBAL, new ScopeCallback() { // from class: io.sentry.Scopes$$ExternalSyntheticLambda6
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    iScope.getClient().close(z);
                }
            });
        } catch (Throwable th2) {
            getOptions().getLogger().log(SentryLevel.ERROR, "Error while closing the Scopes.", th2);
        }
    }

    /* renamed from: lambda$close$2$io-sentry-Scopes, reason: not valid java name */
    /* synthetic */ void m3818lambda$close$2$iosentryScopes(ISentryExecutorService iSentryExecutorService) {
        iSentryExecutorService.close(getOptions().getShutdownTimeoutMillis());
    }

    @Override // io.sentry.IScopes
    public void addBreadcrumb(Breadcrumb breadcrumb, Hint hint) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'addBreadcrumb' call is a no-op.", new Object[0]);
        } else if (breadcrumb == null) {
            getOptions().getLogger().log(SentryLevel.WARNING, "addBreadcrumb called with null parameter.", new Object[0]);
        } else {
            getCombinedScopeView().addBreadcrumb(breadcrumb, hint);
        }
    }

    @Override // io.sentry.IScopes
    public void addBreadcrumb(Breadcrumb breadcrumb) {
        addBreadcrumb(breadcrumb, new Hint());
    }

    @Override // io.sentry.IScopes
    public void setLevel(SentryLevel sentryLevel) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'setLevel' call is a no-op.", new Object[0]);
        } else {
            getCombinedScopeView().setLevel(sentryLevel);
        }
    }

    @Override // io.sentry.IScopes
    public void setTransaction(String str) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'setTransaction' call is a no-op.", new Object[0]);
        } else if (str != null) {
            getCombinedScopeView().setTransaction(str);
        } else {
            getOptions().getLogger().log(SentryLevel.WARNING, "Transaction cannot be null", new Object[0]);
        }
    }

    @Override // io.sentry.IScopes
    public void setUser(User user) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'setUser' call is a no-op.", new Object[0]);
        } else {
            getCombinedScopeView().setUser(user);
        }
    }

    @Override // io.sentry.IScopes
    public void setFingerprint(List<String> list) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'setFingerprint' call is a no-op.", new Object[0]);
        } else if (list == null) {
            getOptions().getLogger().log(SentryLevel.WARNING, "setFingerprint called with null parameter.", new Object[0]);
        } else {
            getCombinedScopeView().setFingerprint(list);
        }
    }

    @Override // io.sentry.IScopes
    public void clearBreadcrumbs() {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'clearBreadcrumbs' call is a no-op.", new Object[0]);
        } else {
            getCombinedScopeView().clearBreadcrumbs();
        }
    }

    @Override // io.sentry.IScopes
    public void setTag(String str, String str2) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'setTag' call is a no-op.", new Object[0]);
        } else if (str == null || str2 == null) {
            getOptions().getLogger().log(SentryLevel.WARNING, "setTag called with null parameter.", new Object[0]);
        } else {
            getCombinedScopeView().setTag(str, str2);
        }
    }

    @Override // io.sentry.IScopes
    public void removeTag(String str) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'removeTag' call is a no-op.", new Object[0]);
        } else if (str == null) {
            getOptions().getLogger().log(SentryLevel.WARNING, "removeTag called with null parameter.", new Object[0]);
        } else {
            getCombinedScopeView().removeTag(str);
        }
    }

    @Override // io.sentry.IScopes
    public void setExtra(String str, String str2) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'setExtra' call is a no-op.", new Object[0]);
        } else if (str == null || str2 == null) {
            getOptions().getLogger().log(SentryLevel.WARNING, "setExtra called with null parameter.", new Object[0]);
        } else {
            getCombinedScopeView().setExtra(str, str2);
        }
    }

    @Override // io.sentry.IScopes
    public void removeExtra(String str) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'removeExtra' call is a no-op.", new Object[0]);
        } else if (str == null) {
            getOptions().getLogger().log(SentryLevel.WARNING, "removeExtra called with null parameter.", new Object[0]);
        } else {
            getCombinedScopeView().removeExtra(str);
        }
    }

    private void updateLastEventId(SentryId sentryId) {
        getCombinedScopeView().setLastEventId(sentryId);
    }

    @Override // io.sentry.IScopes
    public SentryId getLastEventId() {
        return getCombinedScopeView().getLastEventId();
    }

    @Override // io.sentry.IScopes
    public ISentryLifecycleToken pushScope() {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'pushScope' call is a no-op.", new Object[0]);
            return NoOpScopesLifecycleToken.getInstance();
        }
        return forkedCurrentScope("pushScope").makeCurrent();
    }

    @Override // io.sentry.IScopes
    public ISentryLifecycleToken pushIsolationScope() {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'pushIsolationScope' call is a no-op.", new Object[0]);
            return NoOpScopesLifecycleToken.getInstance();
        }
        return forkedScopes("pushIsolationScope").makeCurrent();
    }

    @Override // io.sentry.IScopes
    public ISentryLifecycleToken makeCurrent() {
        return Sentry.setCurrentScopes(this);
    }

    @Override // io.sentry.IScopes
    @Deprecated
    public void popScope() {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'popScope' call is a no-op.", new Object[0]);
            return;
        }
        Scopes scopes = this.parentScopes;
        if (scopes != null) {
            scopes.makeCurrent();
        }
    }

    @Override // io.sentry.IScopes
    public void withScope(ScopeCallback scopeCallback) {
        if (!isEnabled()) {
            try {
                scopeCallback.run(NoOpScope.getInstance());
                return;
            } catch (Throwable th) {
                getOptions().getLogger().log(SentryLevel.ERROR, "Error in the 'withScope' callback.", th);
                return;
            }
        }
        IScopes forkedCurrentScope = forkedCurrentScope("withScope");
        try {
            ISentryLifecycleToken makeCurrent = forkedCurrentScope.makeCurrent();
            try {
                scopeCallback.run(forkedCurrentScope.getScope());
                if (makeCurrent != null) {
                    makeCurrent.close();
                }
            } finally {
            }
        } catch (Throwable th2) {
            getOptions().getLogger().log(SentryLevel.ERROR, "Error in the 'withScope' callback.", th2);
        }
    }

    @Override // io.sentry.IScopes
    public void withIsolationScope(ScopeCallback scopeCallback) {
        if (!isEnabled()) {
            try {
                scopeCallback.run(NoOpScope.getInstance());
                return;
            } catch (Throwable th) {
                getOptions().getLogger().log(SentryLevel.ERROR, "Error in the 'withIsolationScope' callback.", th);
                return;
            }
        }
        IScopes forkedScopes = forkedScopes("withIsolationScope");
        try {
            ISentryLifecycleToken makeCurrent = forkedScopes.makeCurrent();
            try {
                scopeCallback.run(forkedScopes.getIsolationScope());
                if (makeCurrent != null) {
                    makeCurrent.close();
                }
            } finally {
            }
        } catch (Throwable th2) {
            getOptions().getLogger().log(SentryLevel.ERROR, "Error in the 'withIsolationScope' callback.", th2);
        }
    }

    @Override // io.sentry.IScopes
    public void configureScope(ScopeType scopeType, ScopeCallback scopeCallback) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'configureScope' call is a no-op.", new Object[0]);
            return;
        }
        try {
            scopeCallback.run(this.combinedScope.getSpecificScope(scopeType));
        } catch (Throwable th) {
            getOptions().getLogger().log(SentryLevel.ERROR, "Error in the 'configureScope' callback.", th);
        }
    }

    @Override // io.sentry.IScopes
    public void bindClient(ISentryClient iSentryClient) {
        if (iSentryClient != null) {
            getOptions().getLogger().log(SentryLevel.DEBUG, "New client bound to scope.", new Object[0]);
            getCombinedScopeView().bindClient(iSentryClient);
        } else {
            getOptions().getLogger().log(SentryLevel.DEBUG, "NoOp client bound to scope.", new Object[0]);
            getCombinedScopeView().bindClient(NoOpSentryClient.getInstance());
        }
    }

    @Override // io.sentry.IScopes
    public boolean isHealthy() {
        return getClient().isHealthy();
    }

    @Override // io.sentry.IScopes
    public void flush(long j) {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'flush' call is a no-op.", new Object[0]);
            return;
        }
        try {
            getClient().flush(j);
        } catch (Throwable th) {
            getOptions().getLogger().log(SentryLevel.ERROR, "Error in the 'client.flush'.", th);
        }
    }

    @Override // io.sentry.IScopes
    @Deprecated
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public IHub m3819clone() {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Disabled Scopes cloned.", new Object[0]);
        }
        return new HubScopesWrapper(forkedScopes("scopes clone"));
    }

    @Override // io.sentry.IScopes
    public SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext, Hint hint, ProfilingTraceData profilingTraceData) {
        Objects.requireNonNull(sentryTransaction, "transaction is required");
        SentryId sentryId = SentryId.EMPTY_ID;
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'captureTransaction' call is a no-op.", new Object[0]);
            return sentryId;
        }
        if (!sentryTransaction.isFinished()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Transaction: %s is not finished and this 'captureTransaction' call is a no-op.", sentryTransaction.getEventId());
            return sentryId;
        }
        if (!Boolean.TRUE.equals(Boolean.valueOf(sentryTransaction.isSampled()))) {
            getOptions().getLogger().log(SentryLevel.DEBUG, "Transaction %s was dropped due to sampling decision.", sentryTransaction.getEventId());
            if (getOptions().getBackpressureMonitor().getDownsampleFactor() > 0) {
                getOptions().getClientReportRecorder().recordLostEvent(DiscardReason.BACKPRESSURE, DataCategory.Transaction);
                getOptions().getClientReportRecorder().recordLostEvent(DiscardReason.BACKPRESSURE, DataCategory.Span, sentryTransaction.getSpans().size() + 1);
                return sentryId;
            }
            getOptions().getClientReportRecorder().recordLostEvent(DiscardReason.SAMPLE_RATE, DataCategory.Transaction);
            getOptions().getClientReportRecorder().recordLostEvent(DiscardReason.SAMPLE_RATE, DataCategory.Span, sentryTransaction.getSpans().size() + 1);
            return sentryId;
        }
        try {
            return getClient().captureTransaction(sentryTransaction, traceContext, getCombinedScopeView(), hint, profilingTraceData);
        } catch (Throwable th) {
            getOptions().getLogger().log(SentryLevel.ERROR, "Error while capturing transaction with id: " + sentryTransaction.getEventId(), th);
            return sentryId;
        }
    }

    @Override // io.sentry.IScopes
    public SentryId captureProfileChunk(ProfileChunk profileChunk) {
        Objects.requireNonNull(profileChunk, "profilingContinuousData is required");
        SentryId sentryId = SentryId.EMPTY_ID;
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'captureTransaction' call is a no-op.", new Object[0]);
            return sentryId;
        }
        try {
            return getClient().captureProfileChunk(profileChunk, getScope());
        } catch (Throwable th) {
            getOptions().getLogger().log(SentryLevel.ERROR, "Error while capturing profile chunk with id: " + profileChunk.getChunkId(), th);
            return sentryId;
        }
    }

    @Override // io.sentry.IScopes
    public ITransaction startTransaction(TransactionContext transactionContext, TransactionOptions transactionOptions) {
        return createTransaction(transactionContext, transactionOptions);
    }

    private ITransaction createTransaction(TransactionContext transactionContext, TransactionOptions transactionOptions) {
        ITransaction iTransaction;
        Objects.requireNonNull(transactionContext, "transactionContext is required");
        transactionContext.setOrigin(transactionOptions.getOrigin());
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'startTransaction' returns a no-op.", new Object[0]);
            iTransaction = NoOpTransaction.getInstance();
        } else if (SpanUtils.isIgnored(getOptions().getIgnoredSpanOrigins(), transactionContext.getOrigin())) {
            getOptions().getLogger().log(SentryLevel.DEBUG, "Returning no-op for span origin %s as the SDK has been configured to ignore it", transactionContext.getOrigin());
            iTransaction = NoOpTransaction.getInstance();
        } else if (!getOptions().getInstrumenter().equals(transactionContext.getInstrumenter())) {
            getOptions().getLogger().log(SentryLevel.DEBUG, "Returning no-op for instrumenter %s as the SDK has been configured to use instrumenter %s", transactionContext.getInstrumenter(), getOptions().getInstrumenter());
            iTransaction = NoOpTransaction.getInstance();
        } else if (!getOptions().isTracingEnabled()) {
            getOptions().getLogger().log(SentryLevel.INFO, "Tracing is disabled and this 'startTransaction' returns a no-op.", new Object[0]);
            iTransaction = NoOpTransaction.getInstance();
        } else {
            TracesSamplingDecision sample = getOptions().getInternalTracesSampler().sample(new SamplingContext(transactionContext, transactionOptions.getCustomSamplingContext(), getSampleRand(transactionContext), null));
            transactionContext.setSamplingDecision(sample);
            ISpanFactory spanFactory = transactionOptions.getSpanFactory();
            if (spanFactory == null) {
                spanFactory = getOptions().getSpanFactory();
            }
            if (sample.getSampled().booleanValue() && getOptions().isContinuousProfilingEnabled() && getOptions().getProfileLifecycle() == ProfileLifecycle.TRACE && transactionContext.getProfilerId().equals(SentryId.EMPTY_ID)) {
                getOptions().getContinuousProfiler().startProfiler(ProfileLifecycle.TRACE, getOptions().getInternalTracesSampler());
            }
            ITransaction createTransaction = spanFactory.createTransaction(transactionContext, this, transactionOptions, this.compositePerformanceCollector);
            if (sample.getSampled().booleanValue() && sample.getProfileSampled().booleanValue()) {
                ITransactionProfiler transactionProfiler = getOptions().getTransactionProfiler();
                if (!transactionProfiler.isRunning()) {
                    transactionProfiler.start();
                    transactionProfiler.bindTransaction(createTransaction);
                } else if (transactionOptions.isAppStartTransaction()) {
                    transactionProfiler.bindTransaction(createTransaction);
                }
            }
            iTransaction = createTransaction;
        }
        if (transactionOptions.isBindToScope()) {
            iTransaction.makeCurrent();
        }
        return iTransaction;
    }

    private Double getSampleRand(TransactionContext transactionContext) {
        Double sampleRand;
        Baggage baggage = transactionContext.getBaggage();
        return (baggage == null || (sampleRand = baggage.getSampleRand()) == null) ? getCombinedScopeView().getPropagationContext().getSampleRand() : sampleRand;
    }

    @Override // io.sentry.IScopes
    public void startProfiler() {
        if (getOptions().isContinuousProfilingEnabled()) {
            if (getOptions().getProfileLifecycle() != ProfileLifecycle.MANUAL) {
                getOptions().getLogger().log(SentryLevel.WARNING, "Profiling lifecycle is %s. Profiling cannot be started manually.", getOptions().getProfileLifecycle().name());
                return;
            } else {
                getOptions().getContinuousProfiler().startProfiler(ProfileLifecycle.MANUAL, getOptions().getInternalTracesSampler());
                return;
            }
        }
        if (getOptions().isProfilingEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Continuous Profiling is not enabled. Set profilesSampleRate and profilesSampler to null to enable it.", new Object[0]);
        }
    }

    @Override // io.sentry.IScopes
    public void stopProfiler() {
        if (getOptions().isContinuousProfilingEnabled()) {
            if (getOptions().getProfileLifecycle() != ProfileLifecycle.MANUAL) {
                getOptions().getLogger().log(SentryLevel.WARNING, "Profiling lifecycle is %s. Profiling cannot be stopped manually.", getOptions().getProfileLifecycle().name());
                return;
            } else {
                getOptions().getLogger().log(SentryLevel.DEBUG, "Stopped continuous Profiling.", new Object[0]);
                getOptions().getContinuousProfiler().stopProfiler(ProfileLifecycle.MANUAL);
                return;
            }
        }
        getOptions().getLogger().log(SentryLevel.WARNING, "Continuous Profiling is not enabled. Set profilesSampleRate and profilesSampler to null to enable it.", new Object[0]);
    }

    @Override // io.sentry.IScopes
    public void setSpanContext(Throwable th, ISpan iSpan, String str) {
        getCombinedScopeView().setSpanContext(th, iSpan, str);
    }

    @Override // io.sentry.IScopes
    public ISpan getSpan() {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'getSpan' call is a no-op.", new Object[0]);
            return null;
        }
        return getCombinedScopeView().getSpan();
    }

    @Override // io.sentry.IScopes
    public void setActiveSpan(ISpan iSpan) {
        getCombinedScopeView().setActiveSpan(iSpan);
    }

    @Override // io.sentry.IScopes
    public ITransaction getTransaction() {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'getTransaction' call is a no-op.", new Object[0]);
            return null;
        }
        return getCombinedScopeView().getTransaction();
    }

    @Override // io.sentry.IScopes
    public SentryOptions getOptions() {
        return this.combinedScope.getOptions();
    }

    @Override // io.sentry.IScopes
    public Boolean isCrashedLastRun() {
        return SentryCrashLastRunState.getInstance().isCrashedLastRun(getOptions().getCacheDirPath(), !getOptions().isEnableAutoSessionTracking());
    }

    @Override // io.sentry.IScopes
    public void reportFullyDisplayed() {
        if (getOptions().isEnableTimeToFullDisplayTracing()) {
            getOptions().getFullyDisplayedReporter().reportFullyDrawn();
        }
    }

    @Override // io.sentry.IScopes
    public TransactionContext continueTrace(String str, List<String> list) {
        final PropagationContext fromHeaders = PropagationContext.fromHeaders(getOptions().getLogger(), str, list);
        configureScope(new ScopeCallback() { // from class: io.sentry.Scopes$$ExternalSyntheticLambda7
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                iScope.withPropagationContext(new Scope.IWithPropagationContext() { // from class: io.sentry.Scopes$$ExternalSyntheticLambda0
                    @Override // io.sentry.Scope.IWithPropagationContext
                    public final void accept(PropagationContext propagationContext) {
                        IScope.this.setPropagationContext(r2);
                    }
                });
            }
        });
        if (getOptions().isTracingEnabled()) {
            return TransactionContext.fromPropagationContext(fromHeaders);
        }
        return null;
    }

    @Override // io.sentry.IScopes
    public SentryTraceHeader getTraceparent() {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'getTraceparent' call is a no-op.", new Object[0]);
        } else {
            TracingUtils.TracingHeaders trace = TracingUtils.trace(this, null, getSpan());
            if (trace != null) {
                return trace.getSentryTraceHeader();
            }
        }
        return null;
    }

    @Override // io.sentry.IScopes
    public BaggageHeader getBaggage() {
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'getBaggage' call is a no-op.", new Object[0]);
        } else {
            TracingUtils.TracingHeaders trace = TracingUtils.trace(this, null, getSpan());
            if (trace != null) {
                return trace.getBaggageHeader();
            }
        }
        return null;
    }

    @Override // io.sentry.IScopes
    public SentryId captureCheckIn(CheckIn checkIn) {
        SentryId sentryId = SentryId.EMPTY_ID;
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'captureCheckIn' call is a no-op.", new Object[0]);
        } else {
            try {
                sentryId = getClient().captureCheckIn(checkIn, getCombinedScopeView(), null);
            } catch (Throwable th) {
                getOptions().getLogger().log(SentryLevel.ERROR, "Error while capturing check-in for slug", th);
            }
        }
        updateLastEventId(sentryId);
        return sentryId;
    }

    @Override // io.sentry.IScopes
    public SentryId captureReplay(SentryReplayEvent sentryReplayEvent, Hint hint) {
        SentryId sentryId = SentryId.EMPTY_ID;
        if (!isEnabled()) {
            getOptions().getLogger().log(SentryLevel.WARNING, "Instance is disabled and this 'captureReplay' call is a no-op.", new Object[0]);
            return sentryId;
        }
        try {
            return getClient().captureReplayEvent(sentryReplayEvent, getCombinedScopeView(), hint);
        } catch (Throwable th) {
            getOptions().getLogger().log(SentryLevel.ERROR, "Error while capturing replay", th);
            return sentryId;
        }
    }

    @Override // io.sentry.IScopes
    public RateLimiter getRateLimiter() {
        return getClient().getRateLimiter();
    }

    @Override // io.sentry.IScopes
    public ILoggerApi logger() {
        return this.logger;
    }

    private static void validateOptions(SentryOptions sentryOptions) {
        Objects.requireNonNull(sentryOptions, "SentryOptions is required.");
        if (sentryOptions.getDsn() == null || sentryOptions.getDsn().isEmpty()) {
            throw new IllegalArgumentException("Scopes requires a DSN to be instantiated. Considering using the NoOpScopes if no DSN is available.");
        }
    }
}
