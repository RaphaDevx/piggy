package io.sentry;

/* loaded from: classes2.dex */
public final class DefaultSpanFactory implements ISpanFactory {
    @Override // io.sentry.ISpanFactory
    public ITransaction createTransaction(TransactionContext transactionContext, IScopes iScopes, TransactionOptions transactionOptions, CompositePerformanceCollector compositePerformanceCollector) {
        return new SentryTracer(transactionContext, iScopes, transactionOptions, compositePerformanceCollector);
    }

    @Override // io.sentry.ISpanFactory
    public ISpan createSpan(IScopes iScopes, SpanOptions spanOptions, SpanContext spanContext, ISpan iSpan) {
        return NoOpSpan.getInstance();
    }
}
