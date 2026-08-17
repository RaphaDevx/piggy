package io.sentry.cache;

import io.sentry.Hint;
import io.sentry.SentryEnvelope;

/* loaded from: classes2.dex */
public interface IEnvelopeCache extends Iterable<SentryEnvelope> {
    void discard(SentryEnvelope sentryEnvelope);

    @Deprecated
    void store(SentryEnvelope sentryEnvelope, Hint hint);

    default boolean storeEnvelope(SentryEnvelope sentryEnvelope, Hint hint) {
        store(sentryEnvelope, hint);
        return true;
    }

    @Deprecated
    default void store(SentryEnvelope sentryEnvelope) {
        storeEnvelope(sentryEnvelope, new Hint());
    }
}
