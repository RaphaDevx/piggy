package io.sentry.util;

import io.sentry.Baggage;
import io.sentry.BaggageHeader;
import io.sentry.FilterString;
import io.sentry.IScope;
import io.sentry.IScopes;
import io.sentry.ISpan;
import io.sentry.NoOpLogger;
import io.sentry.PropagationContext;
import io.sentry.Scope;
import io.sentry.ScopeCallback;
import io.sentry.SentryOptions;
import io.sentry.SentryTraceHeader;
import io.sentry.SpanContext;
import io.sentry.TracesSamplingDecision;
import io.sentry.W3CTraceparentHeader;
import io.sentry.util.TracingUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public final class TracingUtils {
    public static void startNewTrace(IScopes iScopes) {
        iScopes.configureScope(new ScopeCallback() { // from class: io.sentry.util.TracingUtils$$ExternalSyntheticLambda5
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                iScope.withPropagationContext(new Scope.IWithPropagationContext() { // from class: io.sentry.util.TracingUtils$$ExternalSyntheticLambda2
                    @Override // io.sentry.Scope.IWithPropagationContext
                    public final void accept(PropagationContext propagationContext) {
                        IScope.this.setPropagationContext(new PropagationContext());
                    }
                });
            }
        });
    }

    public static void setTrace(IScopes iScopes, final PropagationContext propagationContext) {
        iScopes.configureScope(new ScopeCallback() { // from class: io.sentry.util.TracingUtils$$ExternalSyntheticLambda0
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                iScope.withPropagationContext(new Scope.IWithPropagationContext() { // from class: io.sentry.util.TracingUtils$$ExternalSyntheticLambda1
                    @Override // io.sentry.Scope.IWithPropagationContext
                    public final void accept(PropagationContext propagationContext2) {
                        IScope.this.setPropagationContext(r2);
                    }
                });
            }
        });
    }

    public static TracingHeaders traceIfAllowed(IScopes iScopes, String str, List<String> list, ISpan iSpan) {
        SentryOptions options = iScopes.getOptions();
        if (options.isTraceSampling() && shouldAttachTracingHeaders(str, options)) {
            return trace(iScopes, list, iSpan);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [io.sentry.util.TracingUtils$1] */
    /* JADX WARN: Type inference failed for: r1v6 */
    public static TracingHeaders trace(IScopes iScopes, List<String> list, ISpan iSpan) {
        final SentryOptions options = iScopes.getOptions();
        W3CTraceparentHeader w3CTraceparentHeader = null;
        if (iSpan != null && !iSpan.isNoOp()) {
            SentryTraceHeader sentryTrace = iSpan.toSentryTrace();
            BaggageHeader baggageHeader = iSpan.toBaggageHeader(list);
            if (options.isPropagateTraceparent()) {
                SpanContext spanContext = iSpan.getSpanContext();
                w3CTraceparentHeader = new W3CTraceparentHeader(spanContext.getTraceId(), spanContext.getSpanId(), sentryTrace.isSampled());
            }
            return new TracingHeaders(sentryTrace, baggageHeader, w3CTraceparentHeader);
        }
        final PropagationContextHolder propagationContextHolder = new PropagationContextHolder();
        iScopes.configureScope(new ScopeCallback() { // from class: io.sentry.util.TracingUtils$$ExternalSyntheticLambda4
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                TracingUtils.PropagationContextHolder.this.propagationContext = TracingUtils.maybeUpdateBaggage(iScope, options);
            }
        });
        if (propagationContextHolder.propagationContext == null) {
            return null;
        }
        PropagationContext propagationContext = propagationContextHolder.propagationContext;
        return new TracingHeaders(new SentryTraceHeader(propagationContext.getTraceId(), propagationContext.getSpanId(), propagationContext.isSampled()), BaggageHeader.fromBaggageAndOutgoingHeader(propagationContext.getBaggage(), list), options.isPropagateTraceparent() ? new W3CTraceparentHeader(propagationContext.getTraceId(), propagationContext.getSpanId(), propagationContext.isSampled()) : 0);
    }

    public static PropagationContext maybeUpdateBaggage(final IScope iScope, final SentryOptions sentryOptions) {
        return iScope.withPropagationContext(new Scope.IWithPropagationContext() { // from class: io.sentry.util.TracingUtils$$ExternalSyntheticLambda3
            @Override // io.sentry.Scope.IWithPropagationContext
            public final void accept(PropagationContext propagationContext) {
                TracingUtils.lambda$maybeUpdateBaggage$5(IScope.this, sentryOptions, propagationContext);
            }
        });
    }

    static /* synthetic */ void lambda$maybeUpdateBaggage$5(IScope iScope, SentryOptions sentryOptions, PropagationContext propagationContext) {
        Baggage baggage = propagationContext.getBaggage();
        if (baggage.isMutable()) {
            baggage.setValuesFromScope(iScope, sentryOptions);
            baggage.freeze();
        }
    }

    private static boolean shouldAttachTracingHeaders(String str, SentryOptions sentryOptions) {
        return PropagationTargetsUtils.contain(sentryOptions.getTracePropagationTargets(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class PropagationContextHolder {
        private PropagationContext propagationContext;

        private PropagationContextHolder() {
            this.propagationContext = null;
        }
    }

    public static final class TracingHeaders {
        private final BaggageHeader baggageHeader;
        private final SentryTraceHeader sentryTraceHeader;
        private final W3CTraceparentHeader w3cTraceparentHeader;

        public TracingHeaders(SentryTraceHeader sentryTraceHeader, BaggageHeader baggageHeader) {
            this.sentryTraceHeader = sentryTraceHeader;
            this.baggageHeader = baggageHeader;
            this.w3cTraceparentHeader = null;
        }

        public TracingHeaders(SentryTraceHeader sentryTraceHeader, BaggageHeader baggageHeader, W3CTraceparentHeader w3CTraceparentHeader) {
            this.sentryTraceHeader = sentryTraceHeader;
            this.baggageHeader = baggageHeader;
            this.w3cTraceparentHeader = w3CTraceparentHeader;
        }

        public SentryTraceHeader getSentryTraceHeader() {
            return this.sentryTraceHeader;
        }

        public BaggageHeader getBaggageHeader() {
            return this.baggageHeader;
        }

        public W3CTraceparentHeader getW3cTraceparentHeader() {
            return this.w3cTraceparentHeader;
        }
    }

    public static boolean isIgnored(List<FilterString> list, String str) {
        if (str != null && list != null && !list.isEmpty()) {
            Iterator<FilterString> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().getFilterString().equalsIgnoreCase(str)) {
                    return true;
                }
            }
            Iterator<FilterString> it2 = list.iterator();
            while (it2.hasNext()) {
                if (it2.next().matches(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Baggage ensureBaggage(Baggage baggage, TracesSamplingDecision tracesSamplingDecision) {
        return ensureBaggage(baggage, tracesSamplingDecision == null ? null : tracesSamplingDecision.getSampled(), tracesSamplingDecision == null ? null : tracesSamplingDecision.getSampleRate(), tracesSamplingDecision != null ? tracesSamplingDecision.getSampleRand() : null);
    }

    public static Baggage ensureBaggage(Baggage baggage, Boolean bool, Double d, Double d2) {
        if (baggage == null) {
            baggage = new Baggage(NoOpLogger.getInstance());
        }
        if (baggage.getSampleRand() == null) {
            Double sampleRate = baggage.getSampleRate();
            if (sampleRate != null) {
                d = sampleRate;
            }
            baggage.setSampleRand(SampleRateUtils.backfilledSampleRand(d2, d, bool));
        }
        if (baggage.isMutable() && baggage.isShouldFreeze()) {
            baggage.freeze();
        }
        return baggage;
    }
}
