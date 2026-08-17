package io.sentry;

import io.sentry.util.Objects;
import io.sentry.util.SentryRandom;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes2.dex */
public final class SamplingContext {
    private final Map<String, Object> attributes;
    private final CustomSamplingContext customSamplingContext;
    private final Double sampleRand;
    private final TransactionContext transactionContext;

    @Deprecated
    public SamplingContext(TransactionContext transactionContext, CustomSamplingContext customSamplingContext) {
        this(transactionContext, customSamplingContext, Double.valueOf(SentryRandom.current().nextDouble()), null);
    }

    public SamplingContext(TransactionContext transactionContext, CustomSamplingContext customSamplingContext, Double d, Map<String, Object> map) {
        this.transactionContext = (TransactionContext) Objects.requireNonNull(transactionContext, "transactionContexts is required");
        this.customSamplingContext = customSamplingContext;
        this.sampleRand = d;
        this.attributes = map == null ? Collections.emptyMap() : map;
    }

    public CustomSamplingContext getCustomSamplingContext() {
        return this.customSamplingContext;
    }

    public TransactionContext getTransactionContext() {
        return this.transactionContext;
    }

    public Double getSampleRand() {
        return this.sampleRand;
    }

    public Object getAttribute(String str) {
        if (str == null) {
            return null;
        }
        return this.attributes.get(str);
    }
}
