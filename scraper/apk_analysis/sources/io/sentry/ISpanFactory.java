package io.sentry;

/* loaded from: classes2.dex */
public interface ISpanFactory {
    ISpan createSpan(IScopes iScopes, SpanOptions spanOptions, SpanContext spanContext, ISpan iSpan);

    ITransaction createTransaction(TransactionContext transactionContext, IScopes iScopes, TransactionOptions transactionOptions, CompositePerformanceCollector compositePerformanceCollector);
}
