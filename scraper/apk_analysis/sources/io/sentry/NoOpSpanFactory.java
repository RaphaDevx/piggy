package io.sentry;

/* loaded from: classes2.dex */
public final class NoOpSpanFactory implements ISpanFactory {
    private static final NoOpSpanFactory instance = new NoOpSpanFactory();

    private NoOpSpanFactory() {
    }

    public static NoOpSpanFactory getInstance() {
        return instance;
    }

    @Override // io.sentry.ISpanFactory
    public ITransaction createTransaction(TransactionContext transactionContext, IScopes iScopes, TransactionOptions transactionOptions, CompositePerformanceCollector compositePerformanceCollector) {
        return NoOpTransaction.getInstance();
    }

    @Override // io.sentry.ISpanFactory
    public ISpan createSpan(IScopes iScopes, SpanOptions spanOptions, SpanContext spanContext, ISpan iSpan) {
        return NoOpSpan.getInstance();
    }
}
